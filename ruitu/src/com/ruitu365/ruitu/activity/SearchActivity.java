package com.ruitu365.ruitu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionResult.SuggestionInfo;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.adapter.PoiInfoAdapter;
import com.ruitu365.ruitu.view.ClearEditText;
import com.ruitu365.ruitu.view.TopAlert;

public class SearchActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private LinearLayout mLlSearch;
	private ClearEditText mEtSearch;
	private ListView mLvAddrDatails;
	private PoiSearch mPoiSearch;
	private SuggestionSearch mSuggestionSearch;
	private PoiInfoAdapter mPoiInfoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
		initSearch();
		setListener();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.search);
		mEtSearch = (ClearEditText) findViewById(R.id.et_search);
		mLlSearch = (LinearLayout) findViewById(R.id.ll_search);
		mLlSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mLlSearch.setVisibility(View.GONE);
				mEtSearch.setVisibility(View.VISIBLE);
				mEtSearch.requestFocus();
				showInputMethod(mContext, mEtSearch);
			}
		});
		mLvAddrDatails = (ListView) findViewById(R.id.lv_addr_detail);
		mPoiInfoAdapter = new PoiInfoAdapter(mContext);
		mLvAddrDatails.setAdapter(mPoiInfoAdapter);
	}

	private void initSearch() {
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(mOnGetPoiSearchResultListener);
		mSuggestionSearch = SuggestionSearch.newInstance();
		mSuggestionSearch.setOnGetSuggestionResultListener(mOnGetSuggestionResultListener);
		// mPoiSearch
		// .searchInCity((new
		// PoiCitySearchOption()).city("深圳市").keyword(mEtSearch.getText().toString()).pageNum(0).pageCapacity(10));
	}

	private void setListener() {
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		/**
		 * 当输入关键字变化时，动态更新建议列表
		 */
		mEtSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				if (cs.length() <= 0) {
					if (mPoiInfoAdapter != null) {
						mPoiInfoAdapter.clearData();
					}
					return;
				}
				/**
				 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
				 */
				mSuggestionSearch.requestSuggestion((new SuggestionSearchOption()).keyword(cs.toString()).city("深圳市"));
			}
		});
	}

	private OnGetPoiSearchResultListener mOnGetPoiSearchResultListener = new OnGetPoiSearchResultListener() {

		@Override
		public void onGetPoiResult(PoiResult poiResult) {
			List<PoiInfo> allPoi = poiResult.getAllPoi();
			for (PoiInfo poi : allPoi) {
				Log.i("bm", "name=" + poi.name + "addr=" + poi.address);
			}
		}

		@Override
		public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

		}
	};
	private OnGetSuggestionResultListener mOnGetSuggestionResultListener = new OnGetSuggestionResultListener() {
		private List<PoiInfo> mPoiInfoList = new ArrayList<PoiInfo>();

		@Override
		public void onGetSuggestionResult(SuggestionResult suggestionResult) {
			if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
				return;
			}
			mPoiInfoList.clear();
			List<SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
			for (SuggestionInfo poi : allSuggestions) {
				// Log.i("bm", "city=" + poi.city + "name=" + poi.key + "uid=" +
				// poi.uid + "district=" + poi.district);
				PoiInfo poiInfo = new PoiInfo();
				poiInfo.city = poi.city + poi.district;
				poiInfo.name = poi.key;
				mPoiInfoList.add(poiInfo);
			}
			mPoiInfoAdapter.setData(mPoiInfoList);
		}
	};

	/**
	 * 显示键盘
	 */
	public static void showInputMethod(Context context, View view) {
		InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		im.showSoftInput(view, 0);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mPoiSearch.destroy();
		mSuggestionSearch.destroy();
		super.onDestroy();
	}
}
