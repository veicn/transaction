package com.sinochem.crude.trade.portal.service.impl.factory;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.service.impl.strategy.OmanSpotPriceStrategy;
import com.sinochem.crude.trade.portal.service.impl.strategy.base.AbstractSpotPriceStrategy;
import org.springframework.web.context.ContextLoader;

/**
 * 简单工厂模式生成计算策略
 * @author Yichen Zhao
 * date: 20180411
 */
public class SpotPriceStrategyFactory {

    public static AbstractSpotPriceStrategy genSpotPriceStrategy(String oilTypeCode) {
        if (StringUtil.isEmpty(oilTypeCode)) {
            return null;
        }

        if (OilTypeEnum.OMAN.getCode().equals(oilTypeCode)) {
            return ContextLoader.getCurrentWebApplicationContext().getBean(OmanSpotPriceStrategy.class);
        }

        return null;
    }
}
