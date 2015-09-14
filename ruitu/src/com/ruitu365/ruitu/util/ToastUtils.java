package com.ruitu365.ruitu.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	private static Toast mToast;

	public static void showToastStr(Context context, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			mToast.show();
		} else {
			mToast.setText(text);
			mToast.show();
		}
	}

	public static void showToastInt(Context context, int res) {
		if (mToast == null) {
			mToast = Toast.makeText(context, res, Toast.LENGTH_SHORT);
			mToast.show();
		} else {
			mToast.setText(res);
			mToast.show();
		}
	}

	public static void setmToast(Toast mToast) {
		ToastUtils.mToast = mToast;
	}
}
