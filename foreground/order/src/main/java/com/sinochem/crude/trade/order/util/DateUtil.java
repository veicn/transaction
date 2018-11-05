package com.sinochem.crude.trade.order.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/24
 */
public class DateUtil {

    public static Date getDateTime(Integer month){
        Date dNow = new Date();   //当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(calendar.MONTH, -month);  //设置为前3月
        return  calendar.getTime();
    }
    public static  String convertStr(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(date);
    }
}
