package com.sinochem.crude.trade.common.utils;

import com.eyeieye.melody.web.locale.VisitorLocale;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final String DATE_DEFAULT_FORMAT = "dd MM yyyy";
    private static final String DATE_TIME_DEFAULT_FORMAT = "dd MM yyyy HH:mm:ss";

    public DateUtil() {
    }

    public static Date getCurrentDate() {
    	Date now = new Date();
    	return now;
    }
    
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            if (StringUtils.isEmpty(pattern)) {
                pattern = DATE_DEFAULT_FORMAT;
            }

            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            //SimpleDateFormat sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT, VisitorLocale.getCurrent());
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT, Locale.ENGLISH);
            return sdf.format(date);
        }
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            //SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_DEFAULT_FORMAT, VisitorLocale.getCurrent());
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_DEFAULT_FORMAT, Locale.ENGLISH);
            return sdf.format(date);
        }
    }

    public static Date getDate(String dateStr) {
        return getDate(dateStr, DATE_DEFAULT_FORMAT);
    }

    public static Date getDateTime(String dateStr) {
        if (StringUtils.isNotEmpty(dateStr) && !dateStr.contains(":")) {
            return getDate(dateStr, DATE_DEFAULT_FORMAT);
        }
        return getDate(dateStr, DATE_TIME_DEFAULT_FORMAT);
    }

    public static Date getDate(String dateStr, String pattern) {
        Date dateTemp = new Date();
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE_DEFAULT_FORMAT;
        }

        //SimpleDateFormat sdf = new SimpleDateFormat(pattern, VisitorLocale.getCurrent());
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);

        try {
            dateTemp = sdf.parse(dateStr);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return dateTemp;
    }
}
