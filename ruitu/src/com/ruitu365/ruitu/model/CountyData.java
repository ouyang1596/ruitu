package com.ruitu365.ruitu.model;

public class CountyData {
	public int id;
	public int upid;
	public String name;
	public boolean isopen;
	public String pinyin;
	public String nameLetter;

	@Override
	public String toString() {
		return "CountyData [id=" + id + ", upid=" + upid + ", name=" + name + ", isopen=" + isopen + ", pinyin=" + pinyin
				+ ", nameLetter=" + nameLetter + "]";
	}

}