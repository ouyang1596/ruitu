package com.ruitu365.ruitu.baselib;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.baidu.mapapi.SDKInitializer;
import com.ruitu365.ruitu.model.Constants;
import com.ruitu365.ruitu.network.NewNetwork;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

public class RuiTuApp extends Application {
	public static Context mContext;
	public static int mVersionCode = -1;// 版本号
	public static String mVersionName;
	public static SharedPreferences mParamsSharePrefreces;
	public static boolean netTips = true;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		// 在使用百度地图 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
		// 内存泄露检测工具初始化
		LeakCanary.install(this);
		NewNetwork.Init(mContext);
		mParamsSharePrefreces = getSharedPreferences(Constants.KEY_PARAMS, 0);
		StatConfig.setDebugEnable(true);
		StatService.trackCustomEvent(this, "onCreate", "");
		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		// 如果app启用了远程的service，此application:onCreate会被调用2次
		// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
		// 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
		// name就立即返回
		if (processAppName == null || !processAppName.equalsIgnoreCase("com.ruitu365.ruitu")) {
			return;
		}
		// 环信SDK默认是自动登录的
		initAppData();
		try {
			mVersionCode = getVersionCode();
			mVersionName = getVersionName();
		} catch (Exception e) {
		}
	}

	/** 环信SDK方法 获取processAppName */
	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}

	private String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		String version = packInfo.versionName;
		return version;
	}

	private int getVersionCode() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		return packInfo.versionCode;

	}

	private void initAppData() {
		File file = new File(Constants.DOWNLOAD_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(Constants.PICTURES_ROOT_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(Constants.AVATAR_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// crashHandler.init(getApplicationContext());
	}

}
