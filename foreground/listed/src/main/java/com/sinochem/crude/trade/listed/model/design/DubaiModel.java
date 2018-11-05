package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelCategoryEnum;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

/**
 * DUBAI油种
 * @author Yichen Zhao
 * date: 20180201
 */
public class DubaiModel extends OilModel {

    public DubaiModel(Integer year, Integer month) {
        super(OilModelTypeEnum.DUBAI.getCode(), year, month);
        this.oilCategory = OilModelCategoryEnum.VARIATIONAL.getCode();
    }
}
