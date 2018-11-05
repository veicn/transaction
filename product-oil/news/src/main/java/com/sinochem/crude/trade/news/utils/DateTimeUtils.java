/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.news.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HuangWj on 2016/10/25. Default date time utils implement.
 */
public class DateTimeUtils {
	/**
     * 默认日期格式：yyyy-MM-dd
     */
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    /**
     * 默认日期格式：yyyyMM
     */
	private static final String YEARMONTH_PATTERN = "yyyyMM";

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间戳格式，到毫秒 yyyy-MM-dd HH:mm:ss SSS
     */
	private static final String DATEDETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * ISO 8601
     */
	private static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss:SSSZZ";

    /**
     * 1天折算成毫秒数
     */
	private static final long MILLIS_A_DAY = 24 * 3600 * 1000;

    /**
     * 取得系统当前年份
     */
    public static int currentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 取得当前系统日期
     *
     * @return 当前日期
     */
    public static Date currentDate() {
        return new Date();
    }

    /**
     * 取得系统当前日期，返回默认日期格式的字符串。
     *
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String currentDateString(String pattern) {
        return toDateString(currentDate(), pattern);
    }

    /**
     * 取得当前系统时间戳
     *
     * @return 当前时间戳
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 将日期字符串转换为java.util.Date对象
     *
     * @param dateString 日期字符串
     * @param pattern 日期格式
     * @return 日期对象
     */
    public static Date toDate(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期字符串转换为java.util.Date对象，使用默认日期格式
     *
     * @param dateString 日期字符串
     * @return 日期对象
     */
    public static Date toDate(String dateString) {
        return toDate(dateString, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转换为java.util.Date对象
     *
     * @param dateString 日期字符串
     * @return 日期对象
     */
    public static Date toDateTime(String dateString) {
        return toDate(dateString, DATETIME_PATTERN);
    }

    /**
     * 将java.util.Date对象转换为字符串
     *
     * @param pattern 日期格式
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String toDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将java.util.Date对象转换为字符串，使用默认日期格式
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String toDateString(Date date) {
        return toDateString(date, DEFAULT_PATTERN);
    }

    /**
     * 将java.util.Date对象转换为时间字符串，使用默认日期格式
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String toDateTimeString(Date date) {
        return toDateString(date, DATETIME_PATTERN);
    }

    /**
     * 日期减少天数
     *
     * @param date 原始日期
     * @param day 减少的天数
     * @return 结果日期
     */
    public static Date decreaseDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime() - day * MILLIS_A_DAY);
        return c.getTime();
    }

    /**
     * 获取两个时间相差的月数
     *
     * @param date1 被减数日期
     * @param date2 减数日期
     * @return 相差月份数
     */
    public static int monthBetweenDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal1.setTime(date1);
        return (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12
                + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
    }

    /**
     * 获取两个时间相差的年数
     *
     * @param date1 被减数日期
     * @param date2 减数日期
     * @return 相差年份数
     */
    public static int yearBetweenDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal1.setTime(date1);
        return cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
    }

    /**
     * 日期增加天数
     *
     * @param date 原始日期
     * @param day 增加的天数
     * @return 结果日期
     */
    public static Date IncreaseDays(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime() + day * MILLIS_A_DAY);
        return c.getTime();
    }

    /**
     * 日期增加年数
     *
     * @param date 原始日期
     * @param year 增加的年数
     * @return 结果日期
     */
    public static Date IncreaseYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }

    /**
     * 日期增加月数
     *
     * @param date 原始日期
     * @param month 增加的月份数
     * @return 结果日期
     */
    public static Date IncreaseMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * 根据季度获得相应的月份
     *
     * @param quarter 季度
     * @return 月份
     */
    public static int getFirstMonthOfQuarter(int quarter) {
        int result = quarter * 3 - 2;
        return (result > 0 && result < 12)
                ? result
                : 0;
    }

    /**
     * 根据月份获得相应的季度
     *
     * @param month 月份
     * @return 季度
     */
    public static int getQuarterByMonth(int month) {
        if (month > 12 || month < 1) {
            return 0;
        }
        return (month - 1) / 3 + 1;
    }

    /**
     * 获取月份起始日期
     *
     * @param date 日期字符串
     * @return 起始日期字符串
     */
    public static String getFirstDateOfMonth(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
            return sdf.format(getFirstDateOfMonth(sdf.parse(date)));
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取月份起始日期
     *
     * @param date 日期对象
     * @return 起始日期对象
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 获取月份最后日期
     *
     * @param date 日期字符串
     * @return 最后日期字符串
     */
    public static String getLastDateOfMonth(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
            return sdf.format(getLastDateOfMonth(sdf.parse(date)));
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取月份最后日期
     *
     * @param date 日期对象
     * @return 最后日期对象
     */
    public static Date getLastDateOfMonth(Date date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 获取日期所在星期的第几天，这里设置第一天为星期日
     *
     * @param datestr 日期字符串
     * @return 所在天数
     */
    public static String getFirstDateOfWeek(String datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            Date date = sdf.parse(datestr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//这里设置从周日开始
            return sdf.format(cal.getTime());

        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取日期所在当年的第几周
     *
     * @param datestr 日期字符串
     * @return 周数
     */
    public static int getWeekOfYear(String datestr) {
        Date date = toDate(datestr);
        if (null != date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.WEEK_OF_YEAR);
        } else {
            return 0;
        }
    }

    /**
     * 获取日期是星期几
     *
     * @param datestr 日期字符串
     * @return 星期字符串
     */
    public static String getWeekday(String datestr) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate(datestr));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w < 0 ? 0 : w];
    }

    /**
     * 将对象转换成日期
     *
     * @param object 待转换的对象
     * @return 转换后的日期对象
     */
    public static Date getDate(Object object) {

        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
            if (object instanceof String) {
                try {
                    date = format.parse((String) object);
                } catch (Exception e) {
                    try {
                        date = format2.parse((String) object);
                    } catch (Exception ee) {
                        date = format3.parse((String) object);
                    }
                }
            } else if (object instanceof Date) {
                date = (Date) object;
            } else {
                date = new Date();
            }
        } catch (Exception e) {
            // Do nothing.
        }
        return date;
    }
}
