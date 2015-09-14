package com.ruitu365.ruitu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.TopAlert;

public class OrderDetailsActivity extends Activity {
	private TopAlert mTopAlert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
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
		mTopAlert.getmTvTopical().setText(R.string.order_detail);
	}
}
