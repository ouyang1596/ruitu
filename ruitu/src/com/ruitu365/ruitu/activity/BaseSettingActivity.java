package com.ruitu365.ruitu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.DialogView;
import com.ruitu365.ruitu.view.TopAlert;

public class BaseSettingActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private LinearLayout mLlSetSex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_setting);
		initView();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.base_setting);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mLlSetSex = (LinearLayout) findViewById(R.id.ll_set_sex);
		mLlSetSex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = View.inflate(mContext, R.layout.dialog_sex, null);
				TextView txMan = (TextView) view.findViewById(R.id.txtv_man);
				TextView txGirl = (TextView) view.findViewById(R.id.txtv_girl);
				final DialogView dialogView = new DialogView(mContext);
				dialogView.showDialog(view);
				txMan.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialogView.cancel();
					}
				});
				txGirl.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialogView.cancel();
					}
				});
			}
		});
	}
}
