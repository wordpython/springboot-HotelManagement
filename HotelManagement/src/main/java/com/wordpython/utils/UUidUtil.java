package com.wordpython.utils;

import java.util.UUID;

public class UUidUtil {
	public String getUUID() {
		String uuid=UUID.randomUUID().toString();
		System.out.println("uuid = "+uuid);
		return uuid;
	}
}
