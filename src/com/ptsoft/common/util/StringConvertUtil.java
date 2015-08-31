/**
 * 
 */
package com.ptsoft.common.util;

/**
 * 字符串转换工具
 */
public class StringConvertUtil
{
	/**
	 * 将类似userName转换成user_name形式
	 * 
	 * @param s
	 *            CameCase字符串
	 * @return 转换后的字符串
	 */
	public static String CamelCaseToDb(String s)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (Character.isUpperCase(c) && i != 0)
			{
				builder.append("_");
			}
			builder.append(Character.toLowerCase(c));
		}
		return builder.toString();
	}

}
