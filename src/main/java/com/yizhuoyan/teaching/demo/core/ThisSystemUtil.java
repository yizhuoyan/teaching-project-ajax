package com.yizhuoyan.teaching.demo.core;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class ThisSystemUtil {
	public static String md5(String s) {
		try {
			s+="^oa$";
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			String newstr = base64en.encode(md5.digest(s.getBytes("utf-8")));
			return newstr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isBlank(String s) {
		return s==null||s.trim().length()==0;
	}
	/**
	 * 清理字符串前后空格,如果字符串为空白字符串，则返回null
	 * 
	 * @param s
	 * @return
	 */
	public static String $(String s,String other) {
		if (s != null) {
			if ((s = s.trim()).length() == 0)
				return other;
			else
				return s;
		}
		return other;
	}

	/**
	 * 解析字符串为整数
	 * 
	 * @param s
	 * @param defaultInt
	 * @return
	 */
	public static int parseInt(String s, int defaultInt) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultInt;
		}
	}
}
