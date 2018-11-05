package com.sinochem.crude.trade.listed.service.impl.factory;

import com.sinochem.crude.trade.listed.model.design.base.*;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * 简单工厂模式构建合约的键
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolKeyModelFactory {

    public static SymbolKeyModel getSymbolKeyModel(SymbolModel symbolModel) {
        if (symbolModel == null) {
            return null;
        }

        if (symbolModel instanceof AbstractDifferenceSymbolModel) {
            DifferenceSymbolKeyModel symbolKeyModel = new DifferenceSymbolKeyModel();

            AbstractDifferenceSymbolModel differenceSymbolModel = (AbstractDifferenceSymbolModel) symbolModel;
            symbolKeyModel.setCalendar(differenceSymbolModel.getCalendar());
            symbolKeyModel.setOilTypePair(convertToTreeSet(differenceSymbolModel.getOilTypePair()));

            return symbolKeyModel;
        } else if (symbolModel instanceof AbstractFixedPriceSymbolModel) {
            FixedPriceSymbolKeyModel symbolKeyModel = new FixedPriceSymbolKeyModel();

            AbstractFixedPriceSymbolModel fixedPriceSymbolModel = (AbstractFixedPriceSymbolModel) symbolModel;
            symbolKeyModel.setCalendar(fixedPriceSymbolModel.getCalendar());
            symbolKeyModel.setOilType(fixedPriceSymbolModel.getOilType());

            return symbolKeyModel;
        } else if (symbolModel instanceof AbstractVariationalSymbolModel) {
            VariationalSymbolKeyModel symbolKeyModel = new VariationalSymbolKeyModel();

            AbstractVariationalSymbolModel variationalSymbolModel = (AbstractVariationalSymbolModel) symbolModel;

            TreeSet<Calendar> calendarPair = new TreeSet<>();
            calendarPair.add(variationalSymbolModel.getStartCalendar());
            calendarPair.add(variationalSymbolModel.getEndCalendar());
            symbolKeyModel.setCalendarPair(calendarPair);

            symbolKeyModel.setOilType(variationalSymbolModel.getOilType());

            return symbolKeyModel;
        }

        return null;
    }

    public static SymbolKeyModel getSymbolKeyModel(String oilType, Calendar calendar) {
        FixedPriceSymbolKeyModel symbolKeyModel = new FixedPriceSymbolKeyModel();
        symbolKeyModel.setOilType(oilType);
        symbolKeyModel.setCalendar(calendar);

        return symbolKeyModel;
    }

    public static SymbolKeyModel getSymbolKeyModel(String oilType1, String oilType2, Calendar calendar) {
        DifferenceSymbolKeyModel symbolKeyModel = new DifferenceSymbolKeyModel();
        symbolKeyModel.setCalendar(calendar);

        TreeSet<String> oilTypePair = new TreeSet<>();
        oilTypePair.add(oilType1);
        oilTypePair.add(oilType2);

        symbolKeyModel.setOilTypePair(oilTypePair);

        return symbolKeyModel;
    }

    public static SymbolKeyModel getSymbolKeyModel(String oilType, Calendar calendar1, Calendar calendar2) {
        VariationalSymbolKeyModel symbolKeyModel = new VariationalSymbolKeyModel();
        symbolKeyModel.setOilType(oilType);

        TreeSet<Calendar> calendarPair = new TreeSet<>();
        calendarPair.add(calendar1);
        calendarPair.add(calendar2);
        symbolKeyModel.setCalendarPair(calendarPair);

        return symbolKeyModel;
    }

    private static <T> TreeSet<T> convertToTreeSet(Set<T> set) {
        TreeSet<T> treeSet = new TreeSet<>();

        for (T instance : set) {
            treeSet.add(instance);
        }

        return treeSet;
    }
}
