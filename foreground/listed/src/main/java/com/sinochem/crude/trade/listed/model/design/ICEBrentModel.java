package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelCategoryEnum;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

/**
 * ICE_BRENT油种
 * @author Yichen Zhao
 * date: 20180201
 */
public class ICEBrentModel extends OilModel {

    public ICEBrentModel(Integer year, Integer month) {
        super(OilModelTypeEnum.ICE_BRENT.getCode(), year, month);
        this.oilCategory = OilModelCategoryEnum.FIXED.getCode();
    }
}
