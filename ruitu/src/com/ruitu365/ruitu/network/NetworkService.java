package com.ruitu365.ruitu.network;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.ruitu365.ruitu.model.Areas;
import com.ruitu365.ruitu.model.Cars;
import com.ruitu365.ruitu.model.Countys;
import com.ruitu365.ruitu.model.OpenCitys;
import com.ruitu365.ruitu.model.UserInfo;
import com.ruitu365.ruitu.model.VersionInfo;

public interface NetworkService {

	@GET(Config.API_UPDATE)
	public void checkUpdate(@Query("userId") int userId, @Query("userName") String userName, @Query("schoolId") String schoolId,
			@Query("deviceId") String deviceId, OnResponse<VersionInfo> cb);

	// -----------------------------------------------------------------------------
	@POST(Config.API_VERTIFY_PHONE_LOGIN)
	public void vertifyPhoneLogin(@Body String data, OnResponse<UserInfo> cb);

	@POST(Config.API_RESET_PASSWORD)
	public void resetPassword(@Body String data, OnResponse<NetworkReturn> cb);

	@POST(Config.API_SUBMIT_IDENTIFYING_CODE)
	public void submitIdentifyingCode(@Body String data, OnResponse<NetworkReturn> cb);

	@POST(Config.API_SET_PASSWORD)
	public void setPassword(@Body String data, OnResponse<NetworkReturn> cb);

	@POST(Config.API_PASSWORD_LOGIN)
	public void passwordLogin(@Body String data, OnResponse<UserInfo> cb);

	@GET(Config.API_OPEN_CITY)
	public void openCity(@Query("p") String data, OnResponse<OpenCitys> cb);

	@GET(Config.API_COUNTRY_BY_CITY)
	public void countyByCity(@Query("p") String data, OnResponse<Countys> cb);

	@GET(Config.API_LOCATIONS_BY_CITY)
	public void locationsByCity(@Query("p") String data, OnResponse<Areas> cb);

	@GET(Config.API_LOCATIONS_BY_COUNTY)
	public void locationsByCounty(@Query("p") String data, OnResponse<Areas> cb);

	@GET(Config.API_CAR_BY_LOCATION)
	public void carByLocation(@Query("p") String data, OnResponse<Cars> cb);
}
