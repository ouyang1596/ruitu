package com.ruitu365.ruitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.TopAlert;

public class ChooseServiceActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private Button mBtnAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_service);
		initView();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.choose_service);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mBtnAccount = (Button) findViewById(R.id.btn_account);
		mBtnAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, OrderConfirmActivity.class);
				startActivity(intent);
			}
		});
	}
}
