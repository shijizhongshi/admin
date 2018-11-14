package com.ola.qh.util;

import java.time.LocalDate;

public final class FileStore
{

    public static String buildpath()
    {
	LocalDate date = LocalDate.now();
	String dpath = new StringBuilder().append(date.getYear()).append("/").append(date.getMonthValue()).append("/").append(date.getDayOfMonth()).append("/")
		.append(KeyGen.next18()).append("/").toString();
	return dpath;
    }

    public static String rename(String fname)
    {
	int index = fname.indexOf(".");
	if (index == 0)
	    return fname;
	String urandom = KeyGen.next18() + "";
	if (index < 0)
	    return urandom;
	String suffix = fname.substring(index);
	return urandom + suffix;
    }
}
