package com.ola.qh.bokecc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class HashUtil {
	/**
	 * 计算字符串的md5值
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {			
		return digest(src, "MD5");			
	}
	
	/**
	 * 计算文件的md5值
	 * 
	 * @param src
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(File file){
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int length = -1;			
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}		
			fis.close();
			return byte2hex(md.digest(buffer));			
		} catch (IOException e){
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * 计算字符串的sha1值
	 * 
	 * @param src
	 * @return
	 */
	public static String sha1(String src) {			
		return digest(src, "SHA-1");	
	}
	
	/**
	 * 根据指定的散列算法名，得到字符串的散列结果。
	 * 
	 * @param src
	 * @param name
	 * @return
	 */
	private static String digest(String src, String name){
		try {
			MessageDigest alg = MessageDigest.getInstance(name);
			byte[] result = alg.digest(src.getBytes());
			return byte2hex(result);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}	
	}
	
	/**
	 * 将byte数组转换成十六进制可读字符串。
	 * @param b 需要转换的byte数组
	 * @return 如果输入的数组为null，则返回null；否则返回转换后的字符串。
	 */
	public static String byte2hex(byte[] b) {
		
		if (b == null){
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

	/**
	 * 将十六进制可读字符串转换成byte数组。输入必须是长度为偶数的字符串，其中每个字符必须是一个合法的十六进制字符，即在0～f之间。
	 * 
	 * @param str 十六进制字符串。
	 * @return 如果str为null，或不是一个合法的偶长度十六进制字符串，则返回null；如果str长度为0，则返回new byte[0];否则返回转换后的结果。
	 */
	public static byte[] hex2byte(String str) {
		
		if (str == null){
			return null;
		}
		
		if (str.equals("")){
			return new byte[0];
		}

		// 判断是否为合法的十六进制表示字符串
		Pattern pattern = Pattern.compile("[0-9a-fA-F]+");
		Matcher match = pattern.matcher(str);
		if (!match.matches()) {
			return null;
		}

		// 判断长度是否为偶数
		if ((str.length() % 2) != 0) {
			return null;
		}

		// 进行正常的转换流程
		byte[] b = str.getBytes();
		byte[] ret = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			ret[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return ret;
	}
}
