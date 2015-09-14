package com.ruitu365.ruitu.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ruitu365.ruitu.model.Constants;
import com.ruitu365.ruitu.util.ToastUtils;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.stat.StatService;

public class BaseActivity extends FragmentActivity {
	protected Context mContext;
	protected ProgressDialog mWaitingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mContext = this;
	}

	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
		XGPushClickedResult result = XGPushManager.onActivityStarted(this);
		if (null != result) {
			String customContent = result.getCustomContent();
			if (customContent != null && customContent.length() != 0) {
				try {
					JSONObject json = new JSONObject(customContent);
					if (json.getString("type").equals("1")) {
						String url = json.getString("data");
						Intent intent = new Intent();
						intent.putExtra(Constants.KEY_WEB_URL, url);
						intent.setClass(mContext, WebActivity.class);
						mContext.startActivity(intent);
					}

				} catch (JSONException e) {

				}
			}
		}
	}

	public void showWaitingDialog() {
		showWaitingDialog("加载中...");
	}

	public void showWaitingDialog(String text) {
		if (mWaitingDialog == null) {
			mWaitingDialog = ProgressDialog.show(mContext, "", text, true, false);
		} else if (!mWaitingDialog.isShowing()) {
			mWaitingDialog.show();
		}
	}

	public void hideWaitingDialog() {
		if (mWaitingDialog != null) {
			mWaitingDialog.dismiss();
			mWaitingDialog = null;
		}
	}

	public void showToastStr(String text) {
		ToastUtils.showToastStr(mContext, text);
	}

	public void showToastInt(int resId) {
		ToastUtils.showToastInt(mContext, resId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
		XGPushManager.onActivityStoped(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ToastUtils.setmToast(null);
	}
}
