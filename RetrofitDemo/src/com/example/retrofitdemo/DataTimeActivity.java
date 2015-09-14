package com.example.retrofitdemo;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class DataTimeActivity extends Activity {
	private TimePicker timePicker;
	private DatePicker datePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindowWH();
		setContentView(R.layout.activity_data_time);
		timePicker = (TimePicker) findViewById(R.id.time);
		timePicker.setIs24HourView(true);
		datePicker = (DatePicker) findViewById(R.id.date);
		// datePicker.setCalendarViewShown(false);
		reflect1();
		reflect2();
		reflect3();
		reflect4();
		reflect5();
	}

	private void reflect5() {
		LinearLayout ll = null;
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它
		try {
			Field daySpinner = datePicker.getClass().getDeclaredField("mMonthSpinner");
			daySpinner.setAccessible(true);
			ll = ((LinearLayout) daySpinner.get(datePicker));
			// ll.setVisibility(View.GONE);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams(); // 取控件textView当前的布局参数
		// linearParams.height = 50;// 控件的高强制设成20

		linearParams.width = width / 5;// 控件的宽强制设成30
		ll.setLayoutParams(linearParams);
		ll.setBackgroundColor(Color.parseColor("#ffffff"));
	}

	private void reflect4() {
		LinearLayout ll = null;
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它
		try {
			Field daySpinner = datePicker.getClass().getDeclaredField("mDaySpinner");
			daySpinner.setAccessible(true);
			ll = ((LinearLayout) daySpinner.get(datePicker));
			// ll.setVisibility(View.GONE);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams(); // 取控件textView当前的布局参数
		// linearParams.height = 50;// 控件的高强制设成20

		linearParams.width = width / 5;// 控件的宽强制设成30
		ll.setLayoutParams(linearParams);
	}

	private void reflect3() {
		LinearLayout ll = null;
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它
		try {
			Field daySpinner = timePicker.getClass().getDeclaredField("mHourSpinner");
			daySpinner.setAccessible(true);
			ll = ((LinearLayout) daySpinner.get(timePicker));
			// ll.setVisibility(View.GONE);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams(); // 取控件textView当前的布局参数
		// linearParams.height = 50;// 控件的高强制设成20

		linearParams.width = width / 5;// 控件的宽强制设成30
		ll.setLayoutParams(linearParams);
	}

	private void reflect2() {
		LinearLayout ll = null;
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它
		try {
			Field daySpinner = timePicker.getClass().getDeclaredField("mMinuteSpinner");
			daySpinner.setAccessible(true);
			ll = ((LinearLayout) daySpinner.get(timePicker));
			// ll.setVisibility(View.GONE);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams(); // 取控件textView当前的布局参数
		// linearParams.height = 50;// 控件的高强制设成20

		linearParams.width = width / 5;// 控件的宽强制设成30
		ll.setLayoutParams(linearParams);
	}

	private void reflect1() {
		LinearLayout ll = null;
		// 通过反射机制，访问private的mDaySpinner成员，并隐藏它
		try {
			Field daySpinner = datePicker.getClass().getDeclaredField("mYearSpinner");
			daySpinner.setAccessible(true);
			ll = ((LinearLayout) daySpinner.get(datePicker));
			// ll.setVisibility(View.GONE);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams(); // 取控件textView当前的布局参数
		// linearParams.height = 50;// 控件的高强制设成20

		linearParams.width = width / 5;// 控件的宽强制设成30
		ll.setLayoutParams(linearParams);
	}

	int width;
	int height;

	private void getWindowWH() {
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
	}
}
