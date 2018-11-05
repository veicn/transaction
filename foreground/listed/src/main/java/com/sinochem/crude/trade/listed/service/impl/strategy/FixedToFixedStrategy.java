package com.sinochem.crude.trade.listed.service.impl.strategy;

import com.sinochem.crude.trade.listed.helper.SymbolModelHelper;
import com.sinochem.crude.trade.listed.model.design.base.AbstractFixedPriceSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractPriceDifferenceStrategy;

import java.math.BigDecimal;

/**
 * 固定价格油种转固定价格油种
 * @author Yichen Zhao
 * date: 20180201
 */
public class FixedToFixedStrategy implements AbstractPriceDifferenceStrategy {

    @Override
    public BigDecimal getValue(OilModel origin, OilModel target) throws Exception {
        AbstractFixedPriceSymbolModel originSymbolModel =
                (AbstractFixedPriceSymbolModel) SymbolModelHelper.getSymbolModel(origin);
        AbstractFixedPriceSymbolModel targetSymbolModel =
                (AbstractFixedPriceSymbolModel) SymbolModelHelper.getSymbolModel(target);

        return originSymbolModel.getSettlementPrice().subtract(targetSymbolModel.getSettlementPrice());
    }
}
