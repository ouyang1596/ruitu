package com.example.retrofitdemo;

import com.example.retrofitdemo.view.CustomView;

import android.app.Activity;
import android.os.Bundle;

public class CustomViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_custom_view);
		setContentView(new CustomView(this));
	}
}
