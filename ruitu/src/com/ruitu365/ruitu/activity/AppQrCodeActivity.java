package com.ruitu365.ruitu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;

public class AppQrCodeActivity extends BaseActivity {
	private LinearLayout mLlBack;
	private TextView mTvTopical;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_qr_code);
		initView();
	}

	private void initView() {
		mTvTopical = (TextView) findViewById(R.id.txtv_top_alert_title);
		mTvTopical.setText(R.string.qr_code);
		mLlBack = (LinearLayout) findViewById(R.id.ll_top_alert_back);
		mLlBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
