package com.ruitu365.ruitu.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.adapter.CityAdapter;
import com.ruitu365.ruitu.adapter.HotCityAdapter;
import com.ruitu365.ruitu.baselib.CharacterParser;
import com.ruitu365.ruitu.model.Areas;
import com.ruitu365.ruitu.model.Cars;
import com.ruitu365.ruitu.model.Countys;
import com.ruitu365.ruitu.model.OpenCityData;
import com.ruitu365.ruitu.model.OpenCitys;
import com.ruitu365.ruitu.network.NewNetwork;
import com.ruitu365.ruitu.network.OnResponse;
import com.ruitu365.ruitu.util.Logger;
import com.ruitu365.ruitu.util.PinyinComparator;
import com.ruitu365.ruitu.view.ClearEditText;
import com.ruitu365.ruitu.view.SideBar;
import com.ruitu365.ruitu.view.SideBar.OnTouchingLetterChangedListener;
import com.ruitu365.ruitu.view.TopAlert;

public class CityServedActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private ListView mLvCity;
	private GridView mGvCity;
	private LinearLayout mLlSearch, mLlHotCity;
	private SideBar mSideBar;
	private TextView mTvDialog;
	private CityAdapter mCityAdapter;
	private OpenCitys mOpenCitys;
	private ClearEditText mEtvSearch;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser mCharacterParser;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator mPinyinComparator;
	private List<String> mCityDatas = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_served);
		initAnimations();
		initView();
		initHotCity();
		initCity();
		setOnListener();
		openCity();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.choose_city);
		mLvCity = (ListView) findViewById(R.id.lv_city);
		mGvCity = (GridView) findViewById(R.id.gv_hot_city);
		mSideBar = (SideBar) findViewById(R.id.sidrbar);
		mTvDialog = (TextView) findViewById(R.id.dialog);
		mSideBar.setTextView(mTvDialog);
		mEtvSearch = (ClearEditText) findViewById(R.id.et_search);
		mLlSearch = (LinearLayout) findViewById(R.id.ll_search);
		mLlHotCity = (LinearLayout) findViewById(R.id.ll_hot_city);
	}

	private void initHotCity() {
		mCityDatas.add("北京");
		mCityDatas.add("上海");
		mCityDatas.add("广州");
		mCityDatas.add("深圳");
		mCityDatas.add("福州");
		mCityDatas.add("厦门");
		HotCityAdapter adpater = new HotCityAdapter(mContext, mCityDatas);
		mGvCity.setAdapter(adpater);
	}

	private void initCity() {
		// 实例化汉字转拼音类
		mCharacterParser = CharacterParser.getInstance();
		mPinyinComparator = new PinyinComparator();
		mCityAdapter = new CityAdapter(mContext);
		mLvCity.setAdapter(mCityAdapter);
	}

	private Animation mHiddenAction;
	private Animation mShowAction;

	private void initAnimations() {
		mHiddenAction = AnimationUtils.loadAnimation(this, R.anim.sliding_out);
		mShowAction = AnimationUtils.loadAnimation(this, R.anim.sliding_in);
	}

	private void setOnListener() {
		mLlSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSearchAnimation();
			}
		});
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});
		// 设置右侧触摸监听
		mSideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = mCityAdapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					mLvCity.setSelection(position);
				}

			}
		});
		mLvCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				showToastStr(((OpenCityData) mCityAdapter.getItem(position)).name);
				// locationsByCity(291);
				locationByCounty(3058);
			}
		});
		// 根据输入框输入值的改变来过滤搜索
		mEtvSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		mGvCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CountyByCity(291);
			}
		});
	}

	/**
	 * 将汉字转成拼音并获取首个字母的大写
	 */
	public void setNameLetters(OpenCitys openCitys) {
		if (openCitys == null) {
			return;
		}
		List<OpenCityData> openCityDatas = openCitys.data;
		for (int i = 0; i < openCityDatas.size(); i++) {
			OpenCityData openCityData = openCityDatas.get(i);
			// 汉字转换成拼音
			String pinyin = mCharacterParser.getSelling(openCityData.name);
			// String pinyin = mCharacterParser.getSelling("厦门");
			String firstLetter = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (firstLetter.matches("[A-Z]")) {
				openCityData.nameLetter = firstLetter;
			} else {
				openCityData.nameLetter = "#";
			}
		}
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		if (mOpenCitys == null) {
			return;
		}
		List<OpenCityData> filterDateList = new ArrayList<OpenCityData>();
		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = mOpenCitys.data;
		} else {
			filterDateList.clear();
			for (OpenCityData openCityData : mOpenCitys.data) {
				String name = openCityData.name;
				if (name.indexOf(filterStr.toString()) != -1 || mCharacterParser.getSelling(name).startsWith(filterStr.toString())) {
					filterDateList.add(openCityData);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, mPinyinComparator);
		mCityAdapter.updateListView(filterDateList);
	}

	/**
	 * 显示键盘
	 */
	public static void showInputMethod(Context context, View view) {
		InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		im.showSoftInput(view, 0);
	}

	@Override
	public void onBackPressed() {
		if (!mLlHotCity.isShown()) {
			hideSearchAnimation();
		} else {
			finish();
		}
	}

	private void showSearchAnimation() {
		mLlSearch.setVisibility(View.GONE);
		mLlSearch.startAnimation(mHiddenAction);
		mLlHotCity.setVisibility(View.GONE);
		mLlHotCity.startAnimation(mHiddenAction);
		mEtvSearch.setVisibility(View.VISIBLE);
		mEtvSearch.startAnimation(mShowAction);
		mLvCity.startAnimation(mShowAction);
		mEtvSearch.requestFocus();
		showInputMethod(mContext, mEtvSearch);
	}

	private void hideSearchAnimation() {
		mLlSearch.setVisibility(View.VISIBLE);
		mLlSearch.startAnimation(mShowAction);
		mLlHotCity.setVisibility(View.VISIBLE);
		mLlHotCity.startAnimation(mShowAction);
		mEtvSearch.setVisibility(View.GONE);
		mEtvSearch.startAnimation(mShowAction);
		// mLvCity.startAnimation(mSlidingAction);
	}

	public void openCity() {
		NewNetwork.openCity(new OnResponse<OpenCitys>("") {
			@Override
			public void success(OpenCitys result, Response response) {
				super.success(result, response);
				if (result.result != 1) {
					showToastStr(result.msg);
					return;
				}
				mOpenCitys = result;
				setNameLetters(mOpenCitys);
				// 根据a-z进行排序源数据
				Collections.sort(mOpenCitys.data, mPinyinComparator);
				if (mCityAdapter != null) {
					mCityAdapter.updateListView(mOpenCitys.data);
				}
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Logger.show("bm", error.getLocalizedMessage());
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void CountyByCity(int cityId) {
		NewNetwork.countyByCity(cityId, new OnResponse<Countys>("") {
			@Override
			public void success(Countys result, Response response) {
				super.success(result, response);
				Logger.show("bm", result.toString());
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Logger.show("bm", error.getLocalizedMessage());
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void locationsByCity(int cityId) {
		NewNetwork.locationsByCity(cityId, new OnResponse<Areas>("") {
			@Override
			public void success(Areas result, Response response) {
				super.success(result, response);
				Logger.show("bm", result.toString());
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Logger.show("bm", error.getLocalizedMessage());
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void locationByCounty(int countyId) {
		NewNetwork.locationsByCounty(countyId, new OnResponse<Areas>("") {
			@Override
			public void success(Areas result, Response response) {
				super.success(result, response);
				Logger.show("bm", result.toString());
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Logger.show("bm", error.getLocalizedMessage());
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void carByLocation(int locationId) {
		NewNetwork.carByLocation(locationId, new OnResponse<Cars>("") {
			@Override
			public void success(Cars result, Response response) {
				super.success(result, response);
				Logger.show("bm", result.toString());
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Logger.show("bm", error.getLocalizedMessage());
				showToastInt(R.string.network_request_failure);
			}
		});
	}
}
