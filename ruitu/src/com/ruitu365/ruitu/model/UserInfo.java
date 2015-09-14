package com.ruitu365.ruitu.model;

public class UserInfo extends NetworkReturnBase {
	public UserInfoData data;

	@Override
	public String toString() {
		return "UserInfo [result=" + result + ", msg=" + msg + ", data=" + data + "]";
	}
}
