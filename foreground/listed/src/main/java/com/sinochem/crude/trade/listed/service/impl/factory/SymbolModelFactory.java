package com.sinochem.crude.trade.listed.service.impl.factory;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.enums.SymbolInfoCombineEnum;
import com.sinochem.crude.trade.listed.model.design.*;
import com.sinochem.crude.trade.listed.model.design.base.SymbolModel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 简单工厂模式生成SymbolModel，其核心功能是进行月份转换的业务逻辑
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolModelFactory {

    public static String getCorrespondingOilType(SymbolInfoCombineEnum combine) {
        if (combine == null) {
            return null;
        }

        if (SymbolInfoCombineEnum.DTD_BRENT_SPREAD.equals(combine)) {
            return OilModelTypeEnum.DTD_BRENT.getCode();
        } else if (SymbolInfoCombineEnum.WTI.equals(combine)) {
            return OilModelTypeEnum.WTI.getCode();
        } else if (SymbolInfoCombineEnum.BRENT.equals(combine)) {
            return OilModelTypeEnum.ICE_BRENT.getCode();
        } else if (SymbolInfoCombineEnum.DUBAI_SPREAD.equals(combine)) {
            return OilModelTypeEnum.DUBAI.getCode();
        } else {
            return null;
        }
    }

    public static SymbolModel cloneSymbolModel(SymbolModel origin) {
        if (origin == null) {
            return null;
        }

        SymbolModel clone;

        if (origin instanceof WTISymbolModel) {
            clone = new WTISymbolModel();
        } else if (origin instanceof BrentSymbolModel) {
            clone = new BrentSymbolModel();
        } else if (origin instanceof DTDBrentDubaiSymbolModel) {
            clone = new DTDBrentDubaiSymbolModel();
        } else if (origin instanceof DTDBrentSpreadSymbolModel) {
            clone = new DTDBrentSpreadSymbolModel();
        } else if (origin instanceof EFSSymbolModel) {
            clone = new EFSSymbolModel();
        } else if (origin instanceof DubaiSpreadSymbolModel) {
            clone = new DubaiSpreadSymbolModel();
        } else {
            return null;
        }

        clone.setCommodity(origin.getCommodity());
        clone.setPriceSource(origin.getPriceSource());
        clone.setMarket(origin.getMarket());
        clone.setSymbolName(origin.getSymbolName());
        clone.setSymbolCode(origin.getSymbolCode());
        clone.setSettlementPrice(origin.getSettlementPrice().add(BigDecimal.ZERO));

        return clone;
    }

    public static Set<Calendar> getCalendarByCombineAndSymbolName(
            SymbolInfoCombineEnum combine, String symbolName) throws Exception {
        if (combine == null) {
            return null;
        }

        if (SymbolInfoCombineEnum.DTD_BRENT_SPREAD.equals(combine)
                || SymbolInfoCombineEnum.DUBAI_SPREAD.equals(combine)) {
            Set<Calendar> calendarPair = new HashSet<>(2);

            for (int suffix = 1; suffix < 4; suffix++) {
                if (symbolName.endsWith(String.valueOf(suffix))) {
                    Calendar startCalendar = Calendar.getInstance();
                    startCalendar.add(Calendar.MONTH, suffix);
                    calendarPair.add(startCalendar);

                    Calendar endCalendar = Calendar.getInstance();
                    endCalendar.add(Calendar.MONTH, suffix + 1);
                    calendarPair.add(endCalendar);
                }
            }

            return calendarPair;
        } else if (SymbolInfoCombineEnum.WTI.equals(combine)
                || SymbolInfoCombineEnum.BRENT.equals(combine)) {
            Set<Calendar> singleCalendar = new HashSet<>(1); //实质上，这个set只有一个calendar
            Calendar calendar = convertSymbolCodeToCalendar(symbolName);
            singleCalendar.add(calendar);

            return singleCalendar;
        }

        return null;
    }

    public static SymbolModel getSymbolModel(
            SymbolInfoCombineEnum combine,
            String symbolName,
            String symbolCode,
            BigDecimal settlementPrice,
            int order) throws Exception {
        if (combine == null
                || StringUtil.isEmpty(symbolName)
                || StringUtil.isEmpty(symbolCode)
                || settlementPrice == null) {
            return null;
        }

        if (SymbolInfoCombineEnum.DTD_BRENT_SPREAD.equals(combine)) {
            DTDBrentSpreadSymbolModel symbolModel = new DTDBrentSpreadSymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.add(Calendar.MONTH, order);

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.add(Calendar.MONTH, order + 1);

            symbolModel.setStartCalendar(startCalendar);
            symbolModel.setEndCalendar(endCalendar);

            return symbolModel;
        } else if (SymbolInfoCombineEnum.DUBAI_SPREAD.equals(combine)) {
            DubaiSpreadSymbolModel symbolModel = new DubaiSpreadSymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.add(Calendar.MONTH, order);

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.add(Calendar.MONTH, order + 1);

            symbolModel.setStartCalendar(startCalendar);
            symbolModel.setEndCalendar(endCalendar);

            return symbolModel;
        } else if (SymbolInfoCombineEnum.EFS.equals(combine)) {
            EFSSymbolModel symbolModel = new EFSSymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, order);

            symbolModel.setCalendar(calendar);

            return symbolModel;
        } else if (SymbolInfoCombineEnum.DTD_BRENT_DUBAI.equals(combine)) {
            DTDBrentDubaiSymbolModel symbolModel = new DTDBrentDubaiSymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, order);

            symbolModel.setCalendar(calendar);

            return symbolModel;
        } else if (SymbolInfoCombineEnum.BRENT.equals(combine)) {
            BrentSymbolModel symbolModel = new BrentSymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar calendar = convertSymbolCodeToCalendar(symbolName);
            symbolModel.setCalendar(calendar);

            return symbolModel;
        } else if (SymbolInfoCombineEnum.WTI.equals(combine)) {
            WTISymbolModel symbolModel = new WTISymbolModel();
            symbolModel.setCommodity(combine.getCommodity());
            symbolModel.setPriceSource(combine.getPriceSource());
            symbolModel.setMarket(combine.getMarket());
            symbolModel.setSymbolName(symbolName);
            symbolModel.setSymbolCode(symbolCode);
            symbolModel.setSettlementPrice(settlementPrice);

            Calendar calendar = convertSymbolCodeToCalendar(symbolName);
            symbolModel.setCalendar(calendar);

            return symbolModel;
        }

        return null;
    }

    private static Calendar convertSymbolCodeToCalendar(String symbolCode) throws Exception {
        int length = symbolCode.length();
        String timeString = symbolCode.substring(length - 4);

        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        format.set2DigitYearStart(new Date());

        Date time = format.parse(timeString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());

        return calendar;
    }
}
