package com.ptsoft.common.util;

public class StringUtil
{
	/** 向左填充字符串到指定位数 */
	public static String padLeft(String s, int length, String symbol)
	{
		while (s.length() < length)
		{
			s = symbol + s;
		}
		return s;
	}

	/** 向右填充字符串到指定位数 */
	public static String padRight(String s, int length, String symbol)
	{
		while (s.length() < length)
		{
			s = s + symbol;
		}
		return s;
	}
}
