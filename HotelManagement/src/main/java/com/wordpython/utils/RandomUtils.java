package com.wordpython.utils;

public class RandomUtils {
	public RandomUtils() {
	}

	public static String getRandom() {
		String code = "";
		for (int i = 0; i < 6; i++) {
			code = code + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return code;
	}
}
