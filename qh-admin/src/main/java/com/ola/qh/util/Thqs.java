package com.ola.qh.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Thqs {

	/**
	 * 云点播加密
	 * 
	 * @param key
	 * @param treeMap
	 * @return
	 */
	public static String getThqstreeMap(String key, Map treeMap) {

		String qs = createQueryString(treeMap);
		if (qs == null) {
			return null;
		}
		// 生成时间片
		long time = new Date().getTime() / 1000;
		// 生成HASH码值
		String hash = md5(String.format("%s&time=%s&salt=%s", qs, time, key));
		// System.out.println(hash);
		// 生成地址
		/** address1 为获取账户信息 接口地址 */
		// 第一步
		String address = qs + "&time=" + time + "&hash=" + hash;
		return address;

	}
	/** 以下为THQS算法的相关函数 */

	/**
	 * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
	 * 
	 * @param queryString
	 * @return
	 */
	public static String createQueryString(Map<String, String> queryMap) {

		if (queryMap == null) {
			return null;
		}

		try {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : queryMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				String key = entry.getKey().trim();
				String value = URLEncoder.encode(entry.getValue().trim(), "utf-8");
				sb.append(String.format("%s=%s&", key, value));
			}
			return sb.substring(0, sb.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能：计算字符串的md5值
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return digest(src, "MD5");
	}

	/**
	 * 功能：根据指定的散列算法名，得到字符串的散列结果。
	 * 
	 * @param src
	 * @param name
	 * @return
	 */
	private static String digest(String src, String name) {
		try {
			MessageDigest alg = MessageDigest.getInstance(name);
			byte[] result = alg.digest(src.getBytes());
			return byte2hex(result);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

	/**
	 * 功能：将byte数组转换成十六进制可读字符串。
	 * 
	 * @param b 需要转换的byte数组
	 * @return 如果输入的数组为null，则返回null；否则返回转换后的字符串。
	 */
	public static String byte2hex(byte[] b) {

		if (b == null) {
			return null;
		}
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0" + stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}
}
