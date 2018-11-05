package com.sinochem.crude.trade.listed.service.impl.factory;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.DTDBrentModel;
import com.sinochem.crude.trade.listed.model.design.DubaiModel;
import com.sinochem.crude.trade.listed.model.design.ICEBrentModel;
import com.sinochem.crude.trade.listed.model.design.WTIModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

/**
 * 简单工厂模式构建油种
 * @author Yichen Zhao
 * date: 20180203
 */
public class OilModelFactory {

    public static OilModel getOilModel(String oilType, Integer year, Integer month) {
        if (oilType == null) {
            return null;
        }

        if (OilModelTypeEnum.DTD_BRENT.getCode().equals(oilType)) {
            return new DTDBrentModel(year, month == null ? null : month - 1);
        } else if (OilModelTypeEnum.DUBAI.getCode().equals(oilType)) {
            return new DubaiModel(year, month == null ? null : month - 1);
        } else if (OilModelTypeEnum.ICE_BRENT.getCode().equals(oilType)) {
            return new ICEBrentModel(year, month == null ? null : month - 1);
        } else if (OilModelTypeEnum.WTI.getCode().equals(oilType)) {
            return new WTIModel(year, month == null ? null : month - 1);
        } else {
            return null;
        }
    }
}
