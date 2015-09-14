package com.example.retrofitdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button mBtn_okhttp, mBtnCheckNet, mBtnGreendao, mBtn_annonation, btn_circle, mBtn_recycleView, mBtn_cell, btn_observerMode,
			mBtn_fragment, mBtnCustomView, mBtnRetrofit, mBtnSurfaceView, mBtnDialogActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Log.i("bm", Build.BRAND);
		initView();
	}

	private void initView() {
		mBtnDialogActivity = (Button) findViewById(R.id.btn_dialog_activity);
		mBtnDialogActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, DialogActivity.class));
			}
		});
		mBtnSurfaceView = (Button) findViewById(R.id.btn_surfaceView);
		mBtnSurfaceView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, SurfaceViewActivity.class));
			}
		});
		mBtnRetrofit = (Button) findViewById(R.id.btn_retrofit);
		mBtnRetrofit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
			}
		});
		mBtn_okhttp = (Button) findViewById(R.id.btn_okhttp);
		mBtn_okhttp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
			}
		});
		mBtn_annonation = (Button) findViewById(R.id.btn_annonation);
		mBtn_annonation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, AnnonationActivity.class));
			}
		});
		btn_circle = (Button) findViewById(R.id.btn_circle);
		btn_circle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, CustomCircleActivity.class));
			}
		});
		mBtn_recycleView = (Button) findViewById(R.id.btn_recycle_view);
		mBtn_recycleView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
			}
		});
		mBtn_cell = (Button) findViewById(R.id.btn_cell);
		mBtn_cell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, CellActivity.class));
			}
		});
		btn_observerMode = (Button) findViewById(R.id.btn_observer_mode);
		btn_observerMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, WifiChangeListenerActivity.class));
			}
		});
		mBtnCheckNet = (Button) findViewById(R.id.btn_check_net);
		mBtnCheckNet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, DataTimeActivity.class));
			}
		});
		mBtn_fragment = (Button) findViewById(R.id.btn_fragment);
		mBtn_fragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, FragmentActivity.class));
			}
		});
		mBtnGreendao = (Button) findViewById(R.id.btn_greendao);
		mBtnGreendao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, GreendaoActivity.class));
			}
		});
		mBtnCustomView = (Button) findViewById(R.id.btn_customView);
		mBtnCustomView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
			}
		});
	}
}
