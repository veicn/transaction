package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractDifferenceSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DTDBrentDubaiSymbolModel extends AbstractDifferenceSymbolModel {

    @Override
    public boolean ordered(OilModel origin, OilModel target) throws Exception {
        if (origin == null || target == null) {
            throw new Exception("参数为空");
        }

        if (origin.getCalendar() == null || target.getCalendar() == null) {
            throw new Exception("参数非法");
        }

        Set<String> oilTypeSet = new HashSet<>();
        oilTypeSet.add(origin.getOilType());
        oilTypeSet.add(target.getOilType());
        if (!oilTypeSet.contains(OilModelTypeEnum.DTD_BRENT.getCode())
                || !oilTypeSet.contains(OilModelTypeEnum.DUBAI.getCode())) {
            throw new Exception("参数非法");
        }

        Calendar originCalendar = origin.getCalendar();
        Calendar targetCalendar = target.getCalendar();
        if (originCalendar.get(Calendar.YEAR) != targetCalendar.get(Calendar.YEAR)
                || originCalendar.get(Calendar.MONTH) != originCalendar.get(Calendar.MONTH)) {
            throw new Exception("参数非法");
        }

        return OilModelTypeEnum.DTD_BRENT.getCode().equals(origin.getOilType());
    }

    @Override
    public Set<String> getOilTypePair() {
        Set<String> oilTypeSet = new HashSet<>();
        oilTypeSet.add(OilModelTypeEnum.DTD_BRENT.getCode());
        oilTypeSet.add(OilModelTypeEnum.DUBAI.getCode());

        return oilTypeSet;
    }
}
