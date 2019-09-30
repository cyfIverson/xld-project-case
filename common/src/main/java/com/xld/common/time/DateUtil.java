package com.xld.common.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.xld.common.time.DateFormatEnum.DATE_FORMAT_5;

/**
 * 日期转换
 * @author xld
 */
public class DateUtil {
	
	/** 时间字符串格式：yyyy-MM-dd HH:mm:ss.SSS */
	public static final String dateFormat1 = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/** 时间字符串格式：yyyy-MM-dd HH:mm:ss */
	public static final String dateFormat2 = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间字符串格式：yyyy-MM-dd HH:mm */
	public static final String dateFormat3 = "yyyy-MM-dd HH:mm";
	
	/** 时间字符串格式：yyyy-MM-dd */
	public static final String dateFormat4 = "yyyy-MM-dd";
	
	/** 时间字符串格式：HH:mm:ss */
	public static final String dateFormat5 = "HH:mm:ss";
	
	/** 时间字符串格式：yyyyMMddHHmmss */
	public static final String dateFormat6 = "yyyyMMddHHmmss";
	
	/** 时间字符串格式：yyyyMMddHHmm */
	public static final String dateFormat7 = "yyyyMMddHHmm";
	
	/** 时间字符串格式：yyMMddHHmmss */
	public static final String dateFormat8 = "yyMMddHHmmss";
	
	/** 时间字符串格式：yyMMddHHmm */
	public static final String dateFormat9 = "yyMMddHHmm";
	
	/** 时间字符串格式：yyMMdd */
	public static final String dateFormat10 = "yyMMdd";
	
	/** 时间字符串格式：yyyyMMdd */
	public static final String dateFormat11 = "yyyyMMdd";

	/** 时间字符串格式：yyyy-MM-dd HH:mm:00.0 */
	public static final String dateFormat12 = "yyyy-MM-dd HH:mm:00.0";

	/** 时间字符串格式：yyyy-MM-dd HH:00:00.0 */
	public static final String dateFormat13 = "yyyy-MM-dd HH:00:00.0";

	/** 时间字符串格式：yyyy-MM-dd 00:00:00.0 */
	public static final String dateFormat14= "yyyy-MM-dd 00:00:00.0";

	/** 前端显示需要格式：yyyy.MM.dd 00:00 */
	public static final String dateFormat15= "yyyy.MM.dd HH:mm";

    /** 前端显示需要格式：yyyy.MM.dd 00:00:00 */
    public static final String dateFormat16= "yyyy.MM.dd HH:mm:ss";

	/** 前端显示需要格式：yyyy.MM.dd */
	public static final String dateFormat17= "yyyy.MM.dd";

	/**
	 * 获取当前时间的时间戳毫秒数
	 * 推荐此种方法，执行速度快
	 * @return 时间毫秒数
	 */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前时间的时间戳
	 * 推荐此种方法，执行速度快
	 * @return 时间戳
	 */
	public static Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取当天凌晨的时间戳毫秒数
	 * @return 时间毫秒数
	 */
	public static long lingchenLong(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String dataStr = formatter.format(date);
		
		long dateLong = 0;
		try {
			Date newDate = formatter.parse(dataStr);
			dateLong = newDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateLong;
	}
	
	/**
	 * 将时间字符串转换时间格式
	 * @param timeStr 时间字符串
	 * @return 时间戳
	 */
	public static Timestamp stringToTimestamp(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(timeStr);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 将时间戳毫秒数转换字符串
	 * @param timeLong 时间戳毫秒数
	 * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串格式的文本
	 */
	public static String longToTimeStr(long timeLong, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date(timeLong);
		String timeStr = formatter.format(date);
		
		return timeStr;
	}

	/**
	 * 将时间字符串转换成时间戳毫秒数
	 * @param timeStr  时间字符串，格式：yyyy-MM-dd HH:mm:ss
	 * @return 时间戳毫秒数
	 */
	public static Timestamp stringToTimestampByFormatStr(String timeStr, String formatStr){
		if(isEmpty(timeStr)){
			return null;
		}

		SimpleDateFormat sdf= new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(timeStr);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将时间字符串转换成时间戳毫秒数
	 * @param timeStr  时间字符串，格式：yyyy-MM-dd HH:mm:ss
	 * @return 时间戳毫秒数
	 */
	public static long timeStrToLong(String timeStr){
		if(isEmpty(timeStr)){
			return 0;
		}

		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = null;
		try {
			date = sdf.parse(timeStr);
			long timeL = date.getTime();
			return timeL;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	/**
	 * 将时间戳毫秒数转换时间戳格式
	 * @param timeLong 时间戳毫秒数
	 * @param formatStr 时间字符串，例如格式：yyyy-MM-dd HH:mm:ss
	 * @return 时间戳
	 */
	public static Timestamp longToTimestampByFormat(long timeLong, String formatStr){
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		Date date = new Date(timeLong);
		String dataStr = formatter.format(date);
		Timestamp timestamp = Timestamp.valueOf(dataStr);

		return timestamp;
	}

	/**
	 * 将数字转换成时间格式显示
	 * @param time 数字
	 * @return 返回时间格式字符串
	 */
	public static String NumberToTime(int time) {
		String timeStr = "";
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0) {
			return "00:00:00";
		} else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99) {
					return "99:59:59";
				}
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	/**
	 * Str判空检查
	 * @param value 值
	 * @return 为空返回：true，不为空返回：false
	 */
	private static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}

	/**
	 * 数字为个位数，数字前补0
	 * @param i
	 * @return
	 */
	private static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10) {
			retStr = "0" + Integer.toString(i);
		} else {
			retStr = "" + i;
		}
		return retStr;
	}

	/**
	 * 获取当天凌晨的时间戳毫秒数
	 *
	 * @return 时间毫秒数
	 */
	public static long getDawnLong() {
		return getDawnLong(System.currentTimeMillis());
	}

	/**
	 * 获取某天凌晨的时间戳毫秒数
	 *
	 * @param timeLong 时间戳
	 * @return 时间毫秒数
	 */
	public static long getDawnLong(long timeLong) {
		return longFormat(timeLong, DATE_FORMAT_5);
	}

	/**
	 * 格式化时间戳
	 *
	 * @param timeLong 时间戳
	 * @param format   时间格式枚举类
	 * @return 格式化后时间毫秒数
	 */
	public static long longFormat(long timeLong, IDateFormat format){
		return longFormat(timeLong, format.getFormat());
	}

	/**
	 * 格式化时间戳
	 * @param timeLong 时间戳
	 * @param format 格式
	 * @return 格式化后时间毫秒数
	 */
	public static long longFormat(long timeLong, String format){
		Date date = new Date(timeLong);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dataStr = formatter.format(date);

		long dateLong = 0;
		try {
			Date newDate = formatter.parse(dataStr);
			dateLong = newDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateLong;
	}

	/**
	 * 将时间字符串准换为时间戳毫秒数
	 * @param timeStr 时间字符串
	 * @param format 时间格式枚举类
	 * @return 时间戳毫秒数
	 */
	public static long strToLong(String timeStr, IDateFormat format) {
		return strToLong(timeStr, format.getFormat());
	}

	/**
	 * 将时间字符串准换为时间戳毫秒数
	 * @param timeStr 时间字符串
	 * @param format 时间格式
	 * @return 时间戳毫秒数
	 */
	public static long strToLong(String timeStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(timeStr);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 将时间字符串转换时间戳
	 * @param timeStr 时间字符串
	 * @param format 时间格式枚举类
	 * @return 时间戳
	 */
	public static Timestamp strToTimestamp(String timeStr, IDateFormat format){
		return strToTimestamp(timeStr, format.getFormat());
	}

	/**
	 * 将时间字符串转换时间戳
	 * @param timeStr 时间字符串
	 * @return 时间戳
	 */
	public static Timestamp strToTimestamp(String timeStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(timeStr);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 将时间戳毫秒数转换时间戳
	 * @param timeLong 时间戳毫秒数
	 * @param format 时间格式枚举类
	 * @return 时间戳
	 */
	public static Timestamp longToTimestamp(long timeLong, IDateFormat format){
		return longToTimestamp(timeLong, format.getFormat());
	}

	/**
	 * 将时间戳毫秒数转换时间戳
	 * @param timeLong 时间戳毫秒数
	 * @param formatStr 时间字符串，例如格式：yyyy-MM-dd HH:mm:ss
	 * @return 时间戳
	 */
	public static Timestamp longToTimestamp(long timeLong, String formatStr){
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		Date date = new Date(timeLong);
		String dataStr = formatter.format(date);
		Timestamp timestamp = Timestamp.valueOf(dataStr);

		return timestamp;
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timeLong 时间戳毫秒数
	 * @param format 时间格式枚举类
	 * @return 时间字符串格式
	 */
	public static String longToStr(long timeLong, IDateFormat format){
		return longToStr(timeLong, format.getFormat());
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timeLong 时间戳毫秒数
	 * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串格式
	 */
	public static String longToStr(long timeLong, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date(timeLong);
		return formatter.format(date);
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timestamp 时间戳
	 * @param format 时间格式枚举类
	 * @return 时间字符串格式
	 */
	public static String timestampToStr(Timestamp timestamp, IDateFormat format){
		return timestampToStr(timestamp, format.getFormat());
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timestamp 时间戳
	 * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串格式
	 */
	public static String timestampToStr(Timestamp timestamp, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date(timestamp.getTime());
		return formatter.format(date);
	}

	/** 秒 */
	private static final long ns = 1000L;
	/** 分 */
	private static final long nm = ns * 60;
	/** 时 */
	private static final long nh = nm * 60;
	/** 天 */
	private static final long nd = nh * 24;
	/** 月 */
	private static final long nmonth = nd * 30;
	/** 年 */
	private static final long nyer = nmonth * 12;

	/**
	 * 获取时间差的表示（例如：2月4天12时59分47秒、20天23时28分2秒、9时28分2秒、48分2秒）
	 * @param timeLong 两时间戳的差
	 * @return 时间差的表示
	 */
	public static String getTimeSubtract(long timeLong) {
		long lo, time;
		String str = (time = (lo = timeLong % nyer) / nmonth) > 0 ? time + "月" : "";
		str = (time = (lo = lo % nmonth) / nd) > 0 ? str + time + "天": str;
		str = (time = (lo = lo % nd )/ nh) > 0 ?  str + time + "时" : str;
		str = (time = (lo = lo % nh) / nm) > 0 ? str + time + "分" : str;
		return (time = lo % nm / ns) > 0 ? str + time + "秒" : str;
	}


	/**
	 * 获取过去第几天的日期
	 * @param past 过去几天
	 * @return 日期
	 */
	public static Timestamp getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		Timestamp day = DateUtil.strToTimestamp(result,dateFormat4);
		return day;
	}


	/**
	 * 获取当前月第一天
	 * @return 日期
	 */
	public static Timestamp getCurrentMoneyFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String first = format.format(c.getTime());
		Timestamp day = DateUtil.strToTimestamp(first,dateFormat4);
		return day;
	}


	/**
	 * 获取上个月第一天
	 * @return 日期
	 */
	public static Date getBeforeMoneyFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String first = format.format(c.getTime());
		Timestamp day = DateUtil.strToTimestamp(first,dateFormat4);
		return day;
	}

	/**
	 * 获取当前年份的第一天
	 * @return
	 */
	public static Date getFirstDay(){
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
		ca.setTime(new Date()); //设置时间为当前时间
		int month = ca.get(Calendar.MONTH);
		ca.add(Calendar.MONTH,0-month);
		ca.add(Calendar.YEAR, 0); //年份减
		ca.set(Calendar.DAY_OF_MONTH,1);// 1
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String first = format.format(ca.getTime());
		Date day = DateUtil.strToTimestamp(first,dateFormat4);
		return day;
	}

	/**
	 * 获取某月的天数
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int month){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.MONTH,month - 1);
		a.set(Calendar.DATE,1);
		a.roll(Calendar.DATE,-1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}


	/**
	 * 获取当前月份
	 * @return
	 */
	public static  int getNowMonth(){
		Calendar calendar=Calendar.getInstance();
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH)+1;
		return month;
	}

	/**
	 * 获取当前年份
	 * @return
	 */
	public static  String getNowYear(){
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		return year+"";
	}
}
