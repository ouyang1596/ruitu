package com.ruitu365.ruitu.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.adapter.CarTypeAdapter;
import com.ruitu365.ruitu.view.TopAlert;

public class ChooseCarActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private ListView mLvCar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_car);
		initView();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mTopAlert.getmTvTopical().setText(R.string.choose_car);
		mLvCar = (ListView) findViewById(R.id.lv_car);
		CarTypeAdapter adapter = new CarTypeAdapter(this, new ArrayList<String>());
		mLvCar.setAdapter(adapter);
	}
}
