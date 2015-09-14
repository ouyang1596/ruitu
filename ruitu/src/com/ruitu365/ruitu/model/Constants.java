package com.ruitu365.ruitu.model;

import com.deshang365.util.FileUtils;

public class Constants {
	public static final String KEY_WEB_URL = "web_url";
	public static final String KEY_SIGN_MODE = "sign_mode";// 签到模式 0口令签到 1蓝牙签到
	public static final String KEY_PARAMS = "params";// 储存一些参数信息的文件名

	public static final String DOWNLOAD_PATH = FileUtils.getExternalSdCardPath() + "/ruitu365/downloads";
	public static final String PICTURES_ROOT_PATH = FileUtils.getExternalSdCardPath() + "/ruitu365/pictures";
	public static final String AVATAR_PATH = PICTURES_ROOT_PATH + "/avatar/";
	public static final String KEY_NETWORK_OUT = "d]e$123sh$1123%an^g[";
}
