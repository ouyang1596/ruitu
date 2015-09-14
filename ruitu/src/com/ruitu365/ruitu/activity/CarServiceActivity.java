package com.ruitu365.ruitu.activity;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.activity.CarServiceActivity.MyAdapter.CustomViewHolder;
import com.ruitu365.ruitu.view.DialogView;
import com.ruitu365.ruitu.view.TopAlert;

public class CarServiceActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private LinearLayout mLlStartPlace, mLlSetTime, mLlStartTime;
	private TextView mTvCancel, mTvConfirm, mTvStartTime;
	private ImageView mImgvAdd, mImgvSub;
	private Button mBtnNext;
	private EditText mEtCarCount;
	private RecyclerView mRv;
	private DatePicker mDatePicker;
	private TimePicker mTimePicker;
	private String[] stringArr = { "ai", "zuo", "wn", "g黄真伊", "he河智苑", "@", "&&*(*", "?? ??? ???", "?", "擬好", "上饶", "厦门", "深圳", "武林",
			"text1", "+*())*&%$^", "11112", "6666", "898和", "阿拉伯", "阿镇", "下午", "责打", "浙江", "浙江", "阿布", "北京", "北城", "成", "城市", "123a",
			"234b", "678c", "得", "额", "方", "搞", "广州", "黄石", "黄冈", "杭州", "上海", "武林" };
	private RecyclerView.LayoutManager mLm;
	// 用来获取View的宽高
	private ViewTreeObserver mViewTreeObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_service);
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.car_server);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTvStartTime = (TextView) findViewById(R.id.txtv_start_time);
		mDatePicker = (DatePicker) findViewById(R.id.date);
		// 这个需要API level 11以上（3.0以上版本）
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
			mDatePicker.setCalendarViewShown(false);
		}
		mDatePicker.init(2015, 8, 5, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

			}
		});
		mTimePicker = (TimePicker) findViewById(R.id.time);
		mTimePicker.setIs24HourView(true);
		mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

			}
		});
		mRv = (RecyclerView) findViewById(R.id.rv_car_type);
		mLm = new LinearLayoutManager(this);
		((LinearLayoutManager) mLm).setOrientation(LinearLayoutManager.HORIZONTAL);
		mRv.setLayoutManager(mLm);
		MyAdapter adapter = new MyAdapter(stringArr);
		mRv.setAdapter(adapter);
		mLlStartPlace = (LinearLayout) findViewById(R.id.ll_start_place);
		mLlStartPlace.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogView dialogView = new DialogView(mContext);
				View startPlaceView = View.inflate(mContext, R.layout.dialog_start_place, null);
				dialogView.setView(new EditText(mContext));
				dialogView.showDialog(startPlaceView);
			}
		});
		mLlSetTime = (LinearLayout) findViewById(R.id.ll_set_time);
		mViewTreeObserver = mLlSetTime.getViewTreeObserver();
		mViewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				if (mLlSetTime.getWidth() > 0) {
					mLlSetTime.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					hideChildView();
				}
			}
		});
		mLlSetTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		mTvCancel = (TextView) findViewById(R.id.txtv_cancel);
		mTvCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mLlSetTime.setVisibility(View.GONE);
			}
		});
		mTvConfirm = (TextView) findViewById(R.id.txtv_confirm);
		mTvConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mLlSetTime.setVisibility(View.GONE);
				Log.i("bm", "" + mDatePicker.getMonth() + "月" + mDatePicker.getDayOfMonth() + "号" + mTimePicker.getDrawingTime());
			}
		});
		mLlStartTime = (LinearLayout) findViewById(R.id.ll_start_time);
		mLlStartTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mLlSetTime.setVisibility(View.VISIBLE);
			}
		});
		mEtCarCount = (EditText) findViewById(R.id.et_car_count);
		mImgvAdd = (ImageView) findViewById(R.id.imgv_add);
		mImgvAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEtCarCount.length() <= 0) {
					mEtCarCount.setText("1");
					return;
				}
				int carCount = Integer.parseInt(mEtCarCount.getText().toString());
				carCount++;
				mEtCarCount.setText("" + carCount);
			}
		});
		mImgvSub = (ImageView) findViewById(R.id.imgv_sub);
		mImgvSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mEtCarCount.length() <= 0) {
					mEtCarCount.setText("0");
					return;
				}
				int carCount = Integer.parseInt(mEtCarCount.getText().toString());
				if (carCount == 0) {
					return;
				}
				carCount--;
				mEtCarCount.setText("" + carCount);
			}
		});
		mBtnNext = (Button) findViewById(R.id.btn_next);
		mBtnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, OrderConfirmActivity.class);
				startActivity(intent);
			}
		});
	}

	@SuppressLint("NewApi")
	private void hideChildView() {
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它(不同的版本字段可能有区别)
		try {
			Field daySpinner = mDatePicker.getClass().getDeclaredField("mYearSpinner");
			daySpinner.setAccessible(true);
			NumberPicker np = (NumberPicker) daySpinner.get(mDatePicker);
			np.setVisibility(View.GONE);
			Field incrementB = np.getClass().getDeclaredField("mIncrementButton");
			incrementB.setAccessible(true);
			ImageButton ib = (ImageButton) incrementB.get(np);
			ib.setBackgroundColor(0xffffff00);
			Field decrementButton = np.getClass().getDeclaredField("mDecrementButton");
			decrementButton.setAccessible(true);
			ImageButton ib2 = (ImageButton) decrementButton.get(np);
			ib2.setBackgroundColor(0xffff0000);
			Field inputText = np.getClass().getDeclaredField("mInputText");
			inputText.setAccessible(true);
			EditText et = (EditText) inputText.get(np);
			// et.setWidth(mLlSetTime.getWidth() / 2);
			et.setTextSize(10.0f);
			et.setBackgroundColor(0xff00ff00);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	class MyAdapter extends Adapter<CustomViewHolder> {
		String[] data;
		private int checkedPosition = -1;

		public MyAdapter(String[] data) {
			this.data = data;
		}

		@Override
		public int getItemCount() {
			return data == null ? 0 : data.length;
		}

		@Override
		public void onBindViewHolder(CustomViewHolder view, int position) {
			view.mImgvCar.setImageResource(R.drawable.wolaiye);
			Log.i("bm", "checkdpositin:" + checkedPosition + "getPosition:" + view.getPosition());
			if (checkedPosition == view.getPosition()) {
				view.mImgvChecked.setImageResource(R.drawable.rb_checked);
			} else {
				view.mImgvChecked.setImageResource(R.drawable.rb_default);
			}
		}

		@Override
		public CustomViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View view = View.inflate(mContext, R.layout.list_item, null);
			return new CustomViewHolder(view);
		}

		public class CustomViewHolder extends RecyclerView.ViewHolder {
			public ImageView mImgvCar, mImgvChecked;

			public CustomViewHolder(View view) {
				super(view);
				mImgvCar = (ImageView) view.findViewById(R.id.imgv_car);
				mImgvChecked = (ImageView) view.findViewById(R.id.imgv_checked);
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// Toast.makeText(mContext, "position:" + getPosition(),
						// Toast.LENGTH_SHORT).show();
						checkedPosition = getPosition();
						notifyDataSetChanged();
					}
				});
			}
		}
	}

	// int width;
	// int height;
	//
	// private void getwindowWH() {
	// WindowManager wm = (WindowManager)
	// this.getSystemService(Context.WINDOW_SERVICE);
	// width = wm.getDefaultDisplay().getWidth();
	// height = wm.getDefaultDisplay().getHeight();
	// }
}
