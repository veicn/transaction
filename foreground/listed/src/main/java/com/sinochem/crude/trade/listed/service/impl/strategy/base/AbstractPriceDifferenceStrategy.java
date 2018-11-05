package com.sinochem.crude.trade.listed.service.impl.strategy.base;

import com.sinochem.crude.trade.listed.model.design.base.OilModel;

import java.math.BigDecimal;

/**
 * 策略模式设计转价功能
 * @author Yichen Zhao
 * date: 20180201
 */
public interface AbstractPriceDifferenceStrategy {

    BigDecimal getValue(OilModel origin, OilModel target) throws Exception;
}
