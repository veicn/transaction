package com.sinochem.crude.trade.listed.service.impl.strategy;

import com.sinochem.crude.trade.listed.helper.SymbolModelHelper;
import com.sinochem.crude.trade.listed.model.design.base.AbstractDifferenceSymbolModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractPriceDifferenceStrategy;

import java.math.BigDecimal;

/**
 * 同月的不同油种的差价，为原子性业务
 * @author Yichen Zhao
 * date: 20180201
 */
public class DifferenceStrategy implements AbstractPriceDifferenceStrategy {

    @Override
    public BigDecimal getValue(OilModel origin, OilModel target) throws Exception {
        AbstractDifferenceSymbolModel symbolModel =
                (AbstractDifferenceSymbolModel) SymbolModelHelper.getSymbolModel(origin, target);

        return symbolModel.ordered(origin, target) ?
                symbolModel.getSettlementPrice() :
                symbolModel.getSettlementPrice().multiply(BigDecimal.valueOf(-1));
    }
}
