package com.ola.qh.weixin.handler;

import java.util.Objects;

public class Strings {
	/**
	 * 判断输入是否为空 <code><p>
	 * empty("")==true<p>
	 * empty(" ")==true<p>
	 * empty("	")==true<p>
	 * empty(" a ")==false<p>
	 * empty("a b")==false
	 * </code>
	 * 
	 * @param input
	 * @return
	 */
	public static boolean empty(String input) {
		return Objects.isNull(input) || "".equals(input.trim());
	}
}
