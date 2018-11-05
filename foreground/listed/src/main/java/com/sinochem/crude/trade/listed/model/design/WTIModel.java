package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelCategoryEnum;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

/**
 * WTI油种
 * @author Yichen Zhao
 * date: 20180201
 */
public class WTIModel extends OilModel {

    public WTIModel(Integer year, Integer month) {
        super(OilModelTypeEnum.WTI.getCode(), year, month);
        this.oilCategory = OilModelCategoryEnum.FIXED.getCode();
    }
}
