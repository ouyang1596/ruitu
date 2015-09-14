package com.ruitu365.ruitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.TopAlert;

public class RentalCarActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private Button mBtnChooseCar;
	private TextView mTvLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rental_car);
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
		mTopAlert.getmTvTopical().setText(R.string.rent_car);
		mBtnChooseCar = (Button) findViewById(R.id.btn_choose_car);
		mBtnChooseCar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, ChooseCarActivity.class));
			}
		});
		mTvLocation = (TextView) findViewById(R.id.txtv_location);
		mTvLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, CityServedActivity.class);
				startActivity(intent);
			}
		});
	}
}
