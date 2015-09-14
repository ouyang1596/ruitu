package com.ruitu365.ruitu.model;

public class OpenCityData {
	public int id;
	public int upid;
	public String name;
	public boolean isopen;
	public String pinyin;
	public String pinyin_short;
	public String nameLetter;

	@Override
	public String toString() {
		return "OpenCityData [id=" + id + ", upid=" + upid + ", name=" + name + ", isopen=" + isopen + ", pinyin=" + pinyin
				+ ", pinyin_short=" + pinyin_short + ", nameLetter=" + nameLetter + "]";
	}

}
