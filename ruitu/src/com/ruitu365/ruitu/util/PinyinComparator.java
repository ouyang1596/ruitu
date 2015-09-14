package com.ruitu365.ruitu.util;

import java.util.Comparator;

import com.ruitu365.ruitu.model.OpenCityData;

/**
 * 
 * @author xiaanming
 * 
 */
public class PinyinComparator implements Comparator<OpenCityData> {

	public int compare(OpenCityData o1, OpenCityData o2) {
		if (o1.nameLetter.equals("@") || o2.nameLetter.equals("#")) {
			return -1;
		} else if (o1.nameLetter.equals("#") || o2.nameLetter.equals("@")) {
			return 1;
		} else {
			return o1.nameLetter.compareTo(o2.nameLetter);
		}
	}

}
