package com.ola.qh.util;

import java.util.Random;

/**
 * 生成4位验证码
 * 
 * @author guoyuxue
 *
 */
public final class RBuilder
{

    private int length = 4;
    private boolean hasletter = false;
    private char[] candidate_number = new char[]
    {
	    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private char[] candidate_letter = new char[]
    {
	    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'
    };

    /**
     * @param length
     *            序列长度，默认为4，如果指定值为小于零，则采用默认值
     * @return this
     */
    public RBuilder length(int length)
    {
	this.length = length > 0 ? length : 4;
	return this;
    }

    /**
     * @param yes
     *            随机序列是否包含字母，默认不包含
     * @return this
     */
    public RBuilder hasletter(boolean yes)
    {
	this.hasletter = yes;
	return this;
    }

    /**
     * 生成下一个随机字符序列
     * 
     * @return 生成的字符序列
     */
    public char[] next()
    {
	char[] whole = new char[candidate_number.length];
	if (hasletter)
	{
	    whole = new char[candidate_number.length + candidate_letter.length];
	}
	System.arraycopy(candidate_number, 0, whole, 0, candidate_number.length);
	if (hasletter)
	{
	    System.arraycopy(candidate_letter, 0, whole, candidate_number.length, candidate_letter.length);
	}
	char[] result = new char[length];
	Random rand = new Random();
	for (int i = 0; i < length; i++)
	{
	    int index = rand.nextInt(whole.length);
	    result[i] = whole[index];
	}
	return result;
    }
}
