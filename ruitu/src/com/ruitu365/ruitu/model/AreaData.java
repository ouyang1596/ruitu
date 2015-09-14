package com.ruitu365.ruitu.model;

import java.util.List;

public class AreaData {
	public List<LocationData> locations;
	public String name;
	public int isopen;
	public int id;

	@Override
	public String toString() {
		return "AreaData [locations=" + locations + ", name=" + name + ", isopen=" + isopen + ", id=" + id + "]";
	}

}
