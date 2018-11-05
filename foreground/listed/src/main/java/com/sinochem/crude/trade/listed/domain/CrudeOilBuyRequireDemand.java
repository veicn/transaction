package com.sinochem.crude.trade.listed.domain;

import com.sinochem.crude.trade.listed.enums.BizType;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandType;

/**
 * 原油的采购需求单
 * @author Yichen Zhao
 * date: 20180111
 */
public class CrudeOilBuyRequireDemand extends Demand {

    public CrudeOilBuyRequireDemand() {
        super();
        super.setBizType(BizType.CRUDE_OIL.getCode());
        super.setDealType(DealType.BUY.getCode());
        super.setType(DemandType.REQUIRE.getCode());
    }
}
