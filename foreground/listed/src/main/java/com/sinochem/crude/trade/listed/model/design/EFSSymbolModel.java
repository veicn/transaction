package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractDifferenceSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * EFS(ICE和DUBAI差价)合约
 * @author Yichen Zhao
 * date: 20180206
 */
public class EFSSymbolModel extends AbstractDifferenceSymbolModel {

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
        if (!oilTypeSet.contains(OilModelTypeEnum.ICE_BRENT.getCode())
                || !oilTypeSet.contains(OilModelTypeEnum.DUBAI.getCode())) {
            throw new Exception("参数非法");
        }

        Calendar originCalendar = origin.getCalendar();
        Calendar targetCalendar = target.getCalendar();
        if (originCalendar.get(Calendar.YEAR) != targetCalendar.get(Calendar.YEAR)
                || originCalendar.get(Calendar.MONTH) != originCalendar.get(Calendar.MONTH)) {
            throw new Exception("参数非法");
        }

        return OilModelTypeEnum.ICE_BRENT.getCode().equals(origin.getOilType());
    }

    @Override
    public Set<String> getOilTypePair() {
        Set<String> oilTypePair = new HashSet<>();
        oilTypePair.add(OilModelTypeEnum.ICE_BRENT.getCode());
        oilTypePair.add(OilModelTypeEnum.DUBAI.getCode());

        return oilTypePair;
    }
}
