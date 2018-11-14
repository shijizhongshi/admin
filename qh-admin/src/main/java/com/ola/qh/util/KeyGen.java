package com.ola.qh.util;

import java.util.Base64;
import java.util.UUID;

public class KeyGen {

	/**
	 * token的获取
	 * 
	 * @return
	 */
	public static String uuid() {

		UUID uuid = UUID.randomUUID();
		long l = uuid.getLeastSignificantBits();
		long m = uuid.getMostSignificantBits();
		byte[] buffer = new byte[16];

		for (int j = 0; j < 8; j++) {
			buffer[j] = (byte) (m >>> 8 * (7 - j));
		}
		for (int j = 8; j < 16; j++) {
			buffer[j] = (byte) (l >>> 8 * (7 - j));
		}
		String es = Base64.getUrlEncoder().encodeToString(buffer);
		return es.replaceAll("=", "");
	}

	/**
	 * 订单号的获取
	 * 
	 * @return
	 */
	public static long next18() {
		long re = gen();
		while (re < 99999999999999999L) {
			re = gen();
		}
		return re;
	}

	public static final long next() {
		long re = gen();
		while (re < 999999999999999999L) {
			re = gen();
		}
		return re;
	}

	public static long gen() {
		UUID uid = UUID.randomUUID();
		long f = uid.getLeastSignificantBits();
		long l = uid.getMostSignificantBits();
		long sd = f + l;
		long re = (sd > 0 ? sd : -sd);
		return re;
	}
}
