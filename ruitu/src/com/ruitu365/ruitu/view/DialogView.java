package com.ruitu365.ruitu.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public class DialogView {
	private AlertDialog mDialog;

	public DialogView(Context context) {
		if (mDialog == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			mDialog = builder.create();
			mDialog.setCanceledOnTouchOutside(true);
		}
	}

	public void showDialog(View view) {
		mDialog.show();
		mDialog.getWindow().setContentView(view);
	}

	public void cancel() {
		mDialog.cancel();
	}

	/**
	 * 解决EditText获得焦点而不能弹出输入法问题
	 * */
	public void setView(View view) {
		mDialog.setView(view);
	}
}
