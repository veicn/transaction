package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.AbstractFixedPriceSymbolModel;

/**
 * BRENT固定月价合约
 * @author Yichen Zhao
 * date: 20180204
 */
public class BrentSymbolModel extends AbstractFixedPriceSymbolModel {

    @Override
    public String getOilType() {
        return OilModelTypeEnum.ICE_BRENT.getCode();
    }
}
