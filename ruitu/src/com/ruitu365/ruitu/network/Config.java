package com.ruitu365.ruitu.network;

public class Config {
	public static final boolean DEBUG = true;
	public static final String IMAGE_HOST = "http://img.wlyeah.com";
	// public static final String HOST = "http://meeting.deshang365.com";
	// public static final String HOST = "http://api2.wlyeah.com";
	public static final int HTTP_CONNECT_TIMEOUT = 10 * 1000;
	public static final int HTTP_READ_TIMEOUT = 10 * 1000;
	public static final int RESPONSE_CACHE_SIZE = 1 * 1024 * 1024;
	public static final String RESPONSE_CACHE = "http_cache";
	// public static final String API_UPDATE =
	// "http://resource.deshang365.com/ugc/meeting_update.json?userId=%s&userName=%s&schoolId=%s&deviceId=%s";
	public static final String API_UPDATE = "/api/user_des2/check_update";
	// ------------------------------RT------------------------------------
	// public static final String HOST = "http://10.13.1.3:3333";
	// public static final String HOST = "http://10.13.1.3:2626";
	public static final String HOST = "http://10.13.1.3:9999";
	public static final String API_VERTIFY_PHONE_LOGIN = "/api/passport/login_by_authcode";
	public static final String API_SUBMIT_IDENTIFYING_CODE = "/api/passport/get_authcode";
	public static final String API_SET_PASSWORD = "/api/passport/set_pwd";
	public static final String API_RESET_PASSWORD = "/api/passport/change_pwd_by_authcode";
	public static final String API_PASSWORD_LOGIN = "/api/passport/login";
	public static final String API_OPEN_CITY = "/api/location/open_city";
	public static final String API_COUNTRY_BY_CITY = "/api/location/county_by_city";
	public static final String API_LOCATIONS_BY_CITY = "/api/location/locations_by_city";
	public static final String API_LOCATIONS_BY_COUNTY = "/api/location/locations_by_county";
	public static final String API_CAR_BY_LOCATION = "/api/car/car_by_location";

}
