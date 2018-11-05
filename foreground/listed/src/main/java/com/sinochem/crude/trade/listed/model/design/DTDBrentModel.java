package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelCategoryEnum;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;

/**
 * DTD_BRENT油种
 * @author Yichen Zhao
 * date: 20180201
 */
public class DTDBrentModel extends OilModel {

    public DTDBrentModel(Integer year, Integer month) {
        super(OilModelTypeEnum.DTD_BRENT.getCode(), year, month);
        this.oilCategory = OilModelCategoryEnum.VARIATIONAL.getCode();
    }
}
