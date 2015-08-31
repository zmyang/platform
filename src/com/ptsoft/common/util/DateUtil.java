package com.ptsoft.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

/**
 * 日期工具类
 */
public class DateUtil
{
	private static Logger log = Logger.getLogger(DateUtil.class);

	private static String datePattern = "yyyy-MM-dd";

	private static String timePattern = "HH:mm";

	/**
	 * Return 缺省的日期格式 (yyyy/MM/dd)
	 * 
	 * @return 在页面中显示的日期格式
	 */
	public static String getDatePattern()
	{
		return datePattern;
	}

	/**
	 * 取当前日期时间戳
	 * */
	public static final String getDateTimeSn()
	{
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 格式化当前日期格式
	 * 
	 * @return 格式化后的日期的页面显示字符串
	 */
	public static final String getCurrentDate()
	{
		try
		{
			return getDate(getToday().getTime());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 根据日期格式，返回日期按datePattern格式转换后的字符串
	 * 
	 * @param aDate
	 *            日期对象
	 * @return 格式化后的日期的页面显示字符串
	 */
	public static final String getDate(Date aDate)
	{
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null)
		{
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 按照日期格式，将字符串解析为日期对象
	 * 
	 * @param aMask
	 *            输入字符串的格式
	 * @param strDate
	 *            一个按aMask格式排列的日期的字符串描述
	 * @return Date 对象
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException
	{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled())
		{
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try
		{
			date = df.parse(strDate);
		} catch (ParseException pe)
		{
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: yyyy/MM/dd HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime)
	{
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: yyyy-MM-dd
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException
	{
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(datePattern);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate)
	{
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null)
		{
			log.error("aDate is null!");
		} else
		{
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 根据日期格式，返回日期按datePattern格式转换后的字符串
	 * 
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(Date aDate)
	{
		return getDateTime(datePattern, aDate);
	}

	/**
	 * 按照日期格式，将字符串解析为日期对象
	 * 
	 * @param strDate
	 *            (格式 yyyy-MM-dd)
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException
	{
		Date aDate = null;

		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("converting date with pattern: " + datePattern);
			}

			aDate = convertStringToDate(datePattern, strDate);
		} catch (ParseException pe)
		{
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 时间相加
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateAdd(Date date, int day)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long dateDiffer(Date date1, Date date2)
	{
		return (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
	}

}
