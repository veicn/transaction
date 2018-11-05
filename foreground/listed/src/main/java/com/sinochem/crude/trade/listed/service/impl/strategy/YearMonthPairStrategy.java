package com.sinochem.crude.trade.listed.service.impl.strategy;

import com.eyeieye.melody.util.StringUtil;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用于处理请求获得的合约的月份对应关系策略
 */
@Component
public class YearMonthPairStrategy {

    public Calendar convertYearMonthPairToCalendar(String yearMonthPair) throws Exception {

        if (StringUtil.isEmpty(yearMonthPair)) {
            throw new RuntimeException("参数为空");
        }

        if (yearMonthPair.length() != 4) {
            throw new RuntimeException("年月格式不正确");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        format.set2DigitYearStart(new Date());

        Date time = format.parse(yearMonthPair);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        return calendar;
    }

    public String convertCalendarToYearMonthPair(Calendar calendar) throws Exception {

        if (calendar == null) {
            throw new RuntimeException("参数为空");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        format.set2DigitYearStart(new Date());

        return format.format(calendar.getTime());
    }
}
