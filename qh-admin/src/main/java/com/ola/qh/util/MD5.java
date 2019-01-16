package com.ola.qh.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Objects;


public class MD5
{
    /**
     * 生成md5
     * 
     * @param message
     * @return
     */
    public static String digest(String message)
    {
	if (message == null || message == "")
	{
	    return "";
	}
	byte[] input = new byte[0];
	try
	{
	    input = message.getBytes("UTF-8");
	}
	catch (UnsupportedEncodingException e)
	{
	}
	return digest(input);
    }

    public static String digest(byte[] input)
    {
	if (Objects.isNull(input))
	{
	    return "";
	}
	String md5str = "";
	try
	{
	    // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    // 3 计算后获得字节数组,这就是那128位了
	    byte[] buff = md.digest(input);
	    // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
	    md5str = Bytes.hex(buff);
	}
	catch (Exception e)
	{
	    throw new RuntimeException(e);
	}
	return md5str;
    }

}
