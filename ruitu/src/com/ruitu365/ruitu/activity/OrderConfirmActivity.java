package com.ruitu365.ruitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.TopAlert;

public class OrderConfirmActivity extends BaseActivity {
	private Button mBtnSubmit;
	private TopAlert mTopAlert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_confirm);
		initView();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.confirm_order);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mBtnSubmit = (Button) findViewById(R.id.btn_submit);
		mBtnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, OrderSuccessActivity.class);
				startActivity(intent);
			}
		});
	}
}
