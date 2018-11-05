package com.sinochem.crude.trade.transaction.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/26 14:49
 * @Version: [v1.0]
 */
public class DateUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";

    private static String tranDateStr(String datestr, String source, String target) {
        String result = "";
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(source);
            Date date = sdf.parse(datestr);
            SimpleDateFormat sdf1 = new SimpleDateFormat(target);
            result = sdf1.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /*
    中文年月日转成英文日月年
     */
    public static String ChDateEn(String datestr) {
        return tranDateStr(datestr, "yyyy-MM-dd", "dd MM yyyy");
    }

    /*
       英文日月年转成中文年月日
        */
    public static String ENDateCH(String datestr) {
        return tranDateStr(datestr, "dd MM yyyy", "yyyy-MM-dd");
    }

    /*
         英文日月年时分秒转成中文年月日时分秒
          */
    public static String ENDateTimeCH(String datestr) {
        return tranDateStr(datestr, "dd MM yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
    }

   /**
    * 时间格式转化字符串,默认转化类型为:yyyy-MM-dd
    * @param date
    * @return
    * @throws ParseException
    */
    public static String dateFormat(Date date) throws ParseException {
        return DateUtil.dateFormat(date , null);
    }

   /**
    * 时间格式化成字符串
    * @param date Date
    * @param pattern DateUtil.DATE_TIME_PATTERN || DateUtil.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
    * @return
    * @throws ParseException
    */
    public static String dateFormat(Date date, String pattern) throws ParseException {
        if(StringUtils.isBlank(pattern)){
         pattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 日期相加减天数
     * @param date 如果为Null，则为当前时间
     * @param days 加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return
     * @throws ParseException
     */
     public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException{
        if(date == null){
            date = new Date();
        }
        if(!includeTime){
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
     }


    /**
     * 字符串转换日期
     *
     * @param strDate 日期字符串
     * @param pattern 转化格式
     * @return
     * @throws ParseException
     */
     public static Date strToDate(String strDate , String pattern)throws ParseException{
         ParsePosition pos = new ParsePosition(0);
         if(pattern == null || "".equals(pattern.trim())){
             pattern = DateUtil.DATE_PATTERN;
         }
         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
         return sdf.parse(strDate  ,pos);

     }


    /**
     * 获取当前日期星期几
     *
     * @param date
     * @return
     */
     public static String getWeekOfDate(Date date){
         SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
         String currSun = dateFm.format(date);
        return currSun;
     }


    public static void main(String[] arg) {
        String aaa = ENDateCH("30 05 2018 17:05:54");
        System.out.println(aaa);
    }
}
