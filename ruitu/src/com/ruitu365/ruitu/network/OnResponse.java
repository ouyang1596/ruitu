package com.ruitu365.ruitu.network;

import java.util.Properties;

import retrofit.Callback;
import retrofit.RetrofitError;

import com.ruitu365.ruitu.baselib.RuiTuApp;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.StatService;

public abstract class OnResponse<T> implements Callback<T> {
	private StatAppMonitor mMonitor;
	private long mStartTime;
	Properties mProp;
	private String mApiName;

	public OnResponse(String apiName) {
		mMonitor = new StatAppMonitor(apiName);
		mStartTime = System.currentTimeMillis();
		StatService.trackCustomEvent(RuiTuApp.mContext, apiName, "OK ");

		mApiName = apiName;
		StatService.trackCustomBeginEvent(RuiTuApp.mContext, apiName, "OK ");
	}

	@Override
	public void failure(RetrofitError error) {
		long difftime = System.currentTimeMillis() - mStartTime;
		mMonitor.setMillisecondsConsume(difftime);
		mMonitor.setReturnCode(StatAppMonitor.FAILURE_RESULT_TYPE);
		StatService.reportAppMonitorStat(RuiTuApp.mContext, mMonitor);
		StatService.trackCustomEndEvent(RuiTuApp.mContext, mApiName, "OK ");
		StatService.reportError(RuiTuApp.mContext, error.toString());
	}

	public void success(T result, retrofit.client.Response response) {
		long difftime = System.currentTimeMillis() - mStartTime;
		mMonitor.setMillisecondsConsume(difftime);
		mMonitor.setReturnCode(StatAppMonitor.SUCCESS_RESULT_TYPE);
		StatService.reportAppMonitorStat(RuiTuApp.mContext, mMonitor);
		StatService.trackCustomEndEvent(RuiTuApp.mContext, mApiName, "OK ");
	}
}
