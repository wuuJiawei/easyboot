package com.easyboot.config.geetest;

import com.easyboot.common.Constants;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	private static String geetest_id;
	private static String geetest_key;
	private static final boolean newfailback = true;
	
	public static final String getGeetest_id() {
		geetest_id = geetest_id == null ? Constants.properties.getProperty("geetest.id") : geetest_id;
		return geetest_id;
	}

	public static final String getGeetest_key() {
		geetest_key = geetest_key == null ? Constants.properties.getProperty("geetest.key") : geetest_key;
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
