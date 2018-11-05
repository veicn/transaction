package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractVariationalSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

import java.util.Calendar;

/**
 * DUBAI月差价合约
 * @author Yichen Zhao
 * date: 20180206
 */
public class DubaiSpreadSymbolModel extends AbstractVariationalSymbolModel {

    @Override
    public boolean ordered(OilModel origin, OilModel target) throws Exception {
        if (origin == null || target == null) {
            throw new Exception("参数为空");
        }

        if (!OilModelTypeEnum.DUBAI.getCode().equals(origin.getOilType())
                || !OilModelTypeEnum.DUBAI.getCode().equals(target.getOilType())) {
            throw new Exception("参数非法");
        }

        if (origin.getCalendar() == null || target.getCalendar() == null) {
            throw new Exception("参数非法");
        }

        if (Math.abs(origin.getCalendar().get(Calendar.MONTH) - target.getCalendar().get(Calendar.MONTH)) != 1) {
            throw new Exception("参数非法");
        }

        return origin.getCalendar().before(target.getCalendar());
    }

    @Override
    public String getOilType() {
        return OilModelTypeEnum.DUBAI.getCode();
    }
}
