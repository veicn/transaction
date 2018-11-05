package com.sinochem.crude.trade.listed.service.impl.strategy;

import com.sinochem.crude.trade.listed.helper.SymbolModelHelper;
import com.sinochem.crude.trade.listed.model.design.base.AbstractVariationalSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractPriceDifferenceStrategy;

import java.math.BigDecimal;

/**
 * 无法获知月价格，只能获知相邻月差价的油种的差价获取，为原子性业务
 * @author Yihen Zhao
 * date: 20180201
 */
public class VariationStrategy implements AbstractPriceDifferenceStrategy {

    @Override
    public BigDecimal getValue(OilModel origin, OilModel target) throws Exception {
        AbstractVariationalSymbolModel symbolModel =
                (AbstractVariationalSymbolModel) SymbolModelHelper.getSymbolModel(origin, target);

        return symbolModel.ordered(origin, target) ?
                symbolModel.getSettlementPrice() : symbolModel.getSettlementPrice().multiply(BigDecimal.valueOf(-1));
    }
}
