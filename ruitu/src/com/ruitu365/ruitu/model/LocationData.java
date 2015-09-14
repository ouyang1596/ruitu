package com.ruitu365.ruitu.model;

public class LocationData {
	public String name;
	public String address;
	public String logo;
	public double lat;
	public double lng;
	public String contact;

	@Override
	public String toString() {
		return "locationdata [name=" + name + ", address=" + address + ", logo=" + logo + ", lat=" + lat + ", lng=" + lng + ", contact="
				+ contact + "]";
	}

}