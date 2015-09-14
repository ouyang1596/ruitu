package com.ruitu365.ruitu.model;

import java.util.List;

public class Cars extends NetworkReturnBase {
	public List<CarData> data;

	@Override
	public String toString() {
		return "Cars [data=" + data + "]";
	}
}
