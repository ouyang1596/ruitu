package com.example.retrofitdemo;

import com.example.retrofitdemo.adapter.MyAdapetr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CellActivity extends Activity {
	private ListView mLv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cell);
		initView();
	}

	private void initView() {
		mLv = (ListView) findViewById(R.id.lv);
		MyAdapetr adapetr = new MyAdapetr(this, mLv);
		mLv.setAdapter(adapetr);
	}

}
