package com.ruitu365.ruitu.network;

import org.codehaus.jackson.map.ObjectMapper;

import com.ruitu365.ruitu.util.Logger;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Response;
import android.content.Context;
import android.util.Log;

public class RetrofitUtils {

	private static String cookies = "";

	private static RestAdapter singleton;

	public static <T> T createApi(Context context, Class<T> clazz) {
		if (singleton == null) {
			RestAdapter.Builder builder = new RestAdapter.Builder();
			builder.setEndpoint(Config.HOST);
			builder.setRequestInterceptor(COOKIES_REQUEST_INTERCEPTOR);
			builder.setClient(new OkClient(OkHttpUtils.getInstance(context)));
			// builder.setConverter(new FastJsonHttpMessageConverter());
			builder.setConverter(new JacksonConverter(new ObjectMapper()));
			builder.setLogLevel(Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
			singleton = builder.build();
		}
		return singleton.create(clazz);
	}

	public static String getCookies() {
		Logger.show("bm", cookies);
		return cookies;
	}

	public static void setCookies(String cookies) {
		cookies = cookies;
	}

	public static void setCookies(Response response) {
		for (Header header : response.getHeaders()) {
			if (null != header.getName() && header.getName().equals("Set-Cookie")) {
				String cookie = header.getValue();
				cookies += cookie.substring(0, cookie.indexOf(";") + 1);
				Log.i("bm", "cookies1=" + cookies);
			}
		}
		Network.mLoginCookieString = cookies;
	}

	/**
	 * Injects cookies to every request
	 */
	private static final RequestInterceptor COOKIES_REQUEST_INTERCEPTOR = new RequestInterceptor() {
		@Override
		public void intercept(RequestFacade request) {
			if (null != cookies && cookies.length() > 0) {
				request.addHeader("Cookie", cookies);
			}
		}
	};
}