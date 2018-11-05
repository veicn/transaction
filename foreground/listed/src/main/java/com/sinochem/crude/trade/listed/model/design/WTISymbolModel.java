package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractFixedPriceSymbolModel;

/**
 * WTI固定月价合约
 * @author Yichen Zhao
 * date: 20180206
 */
public class WTISymbolModel extends AbstractFixedPriceSymbolModel {

    @Override
    public String getOilType() {
        return OilModelTypeEnum.WTI.getCode();
    }
}
