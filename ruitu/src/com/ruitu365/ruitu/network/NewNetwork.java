package com.ruitu365.ruitu.network;

import java.net.URLEncoder;

import android.content.Context;
import android.os.Build;

import com.ruitu365.ruitu.baselib.Encrypt;
import com.ruitu365.ruitu.baselib.RuiTuApp;
import com.ruitu365.ruitu.model.Areas;
import com.ruitu365.ruitu.model.Cars;
import com.ruitu365.ruitu.model.Constants;
import com.ruitu365.ruitu.model.Countys;
import com.ruitu365.ruitu.model.OpenCitys;
import com.ruitu365.ruitu.model.UserInfo;

public class NewNetwork {
	private static final int MOBILE_MODEL = 0;
	private static final String MOBILE_BRAND = Build.BRAND;
	private static final float MOBILE_VERSION = RuiTuApp.mVersionCode;
	private static NetworkService sNetworkService;

	public static boolean Init(Context context) {
		sNetworkService = RetrofitUtils.createApi(context, NetworkService.class);
		return true;
	}

	// public static void getUpdatedVersion(Context context,
	// OnResponse<VersionInfo> cb) {
	// sNetworkService.checkUpdate(/* RuiTuApp.userInfo.uid, */111,
	// RuiTuApp.username, "school", Installation.id(context), cb);
	// }

	public static String getAvatarUrl(int uid) {
		String uidStr = lpad(9, Integer.valueOf(uid));
		return Config.IMAGE_HOST + "/avatar/" + uidStr.substring(0, 3) + "/" + uidStr.substring(3, 5) + "/" + uidStr.substring(5, 7) + "/"
				+ uidStr.substring(7, 9) + ".jpg";
	}

	private static String lpad(int length, int number) {
		String f = "%0" + length + "d";
		return String.format(f, number);
	}

	// -------------------------------post-------------------------------
	public static boolean vertifyPhoneLogin(String mobile, OnResponse<UserInfo> cb) {
		String param = String.format("{\"mobile\":\"%s\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", mobile,
				MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			String data = "p=" + URLEncoder.encode(Encrypt.encrypt(param, Constants.KEY_NETWORK_OUT));
			sNetworkService.vertifyPhoneLogin(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean submitIdentifyingCode(String mobile, OnResponse<NetworkReturn> cb) {
		String param = String.format("{\"mobile\":\"%s\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", mobile,
				MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			String data = "p=" + URLEncoder.encode(Encrypt.encrypt(param, Constants.KEY_NETWORK_OUT));
			sNetworkService.submitIdentifyingCode(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean setPassword(String password, OnResponse<NetworkReturn> cb) {
		String param = String.format("{\"password\":\"%s\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}",
				password, MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			String data = "p=" + URLEncoder.encode(Encrypt.encrypt(param, Constants.KEY_NETWORK_OUT));
			sNetworkService.setPassword(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean passwordLogin(String mobile, String password, OnResponse<UserInfo> cb) {
		String param = String.format(
				"{\"mobile\":\"%s\",\"password\":\"%s\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", mobile,
				password, MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			String data = "p=" + URLEncoder.encode(Encrypt.encrypt(param, Constants.KEY_NETWORK_OUT));
			sNetworkService.passwordLogin(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean resetPassword(String mobile, String new_pwd, OnResponse<NetworkReturn> cb) {
		String param = String.format(
				"{\"mobile\":\"%s\",\"new_pwd\":\"%s\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", mobile,
				new_pwd, MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			String data = "p=" + URLEncoder.encode(Encrypt.encrypt(param, Constants.KEY_NETWORK_OUT));
			sNetworkService.resetPassword(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ------------------------------get-----------------------------------------------------
	public static boolean openCity(OnResponse<OpenCitys> cb) {
		String data = String.format("{\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", MOBILE_MODEL, MOBILE_BRAND,
				MOBILE_VERSION);
		try {
			data = URLEncoder.encode(Encrypt.encrypt(data, Constants.KEY_NETWORK_OUT));
			sNetworkService.openCity(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean countyByCity(int cityId, OnResponse<Countys> cb) {
		String data = String.format("{\"city\":\"%d\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", cityId,
				MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			data = URLEncoder.encode(Encrypt.encrypt(data, Constants.KEY_NETWORK_OUT));
			sNetworkService.countyByCity(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean locationsByCity(int cityId, OnResponse<Areas> cb) {
		String data = String.format("{\"city\":\"%d\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", cityId,
				MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			data = URLEncoder.encode(Encrypt.encrypt(data, Constants.KEY_NETWORK_OUT));
			sNetworkService.locationsByCity(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean locationsByCounty(int countyId, OnResponse<Areas> cb) {
		String data = String.format("{\"county\":\"%d\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}", countyId,
				MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			data = URLEncoder.encode(Encrypt.encrypt(data, Constants.KEY_NETWORK_OUT));
			sNetworkService.locationsByCounty(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean carByLocation(int locationId, OnResponse<Cars> cb) {
		String data = String.format("{\"location\":\"%d\",\"mobile_model\":\"%d\",\"mobile_brand\":\"%s\",\"app_version\":\"%f\"}",
				locationId, MOBILE_MODEL, MOBILE_BRAND, MOBILE_VERSION);
		try {
			data = URLEncoder.encode(Encrypt.encrypt(data, Constants.KEY_NETWORK_OUT));
			sNetworkService.carByLocation(data, cb);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
