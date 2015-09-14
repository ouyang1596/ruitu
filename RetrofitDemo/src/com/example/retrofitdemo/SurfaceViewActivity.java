package com.example.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;

import com.example.retrofitdemo.view.ScaleView;

public class SurfaceViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ScaleView(this));
	}
}
