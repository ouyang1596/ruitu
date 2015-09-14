package com.example.retrofitdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {
	@Override
	public void onCreate() {
		LeakCanary.install(this);

	}
}
