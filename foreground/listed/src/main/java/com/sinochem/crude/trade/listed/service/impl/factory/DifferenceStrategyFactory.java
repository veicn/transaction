package com.sinochem.crude.trade.listed.service.impl.factory;

import com.sinochem.crude.trade.listed.enums.OilModelCategoryEnum;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.service.impl.strategy.DifferenceStrategy;
import com.sinochem.crude.trade.listed.service.impl.strategy.FixedToFixedStrategy;
import com.sinochem.crude.trade.listed.service.impl.strategy.VariationStrategy;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractPriceDifferenceStrategy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 简单工厂模式用于生成相应策略
 * @author Yichen Zhao
 * date: 20180202
 */
public class DifferenceStrategyFactory {

    /**
     * 可以用于两两互转的油种
     */
    private static Set<Set<String>> availableOilModelPairSet;

    static {
        availableOilModelPairSet = new HashSet<>();

        Set<String> ICE_DUBAI = new HashSet<>();
        ICE_DUBAI.add(OilModelTypeEnum.ICE_BRENT.getCode());
        ICE_DUBAI.add(OilModelTypeEnum.DUBAI.getCode());
        availableOilModelPairSet.add(ICE_DUBAI);

        Set<String> DUBAI_DTD = new HashSet<>();
        DUBAI_DTD.add(OilModelTypeEnum.DUBAI.getCode());
        DUBAI_DTD.add(OilModelTypeEnum.DTD_BRENT.getCode());
        availableOilModelPairSet.add(DUBAI_DTD);
    }

    public static AbstractPriceDifferenceStrategy genStrategy(
            String origin, String target) {
                if (origin == null || target == null) {
            return null;
        }

        OilModel originOilModel = OilModelFactory.getOilModel(origin, null, null);
        OilModel targetOilModel = OilModelFactory.getOilModel(target, null, null);

        if (OilModelCategoryEnum.FIXED.getCode().equals(originOilModel.getOilCategory()) // 固定价格转固定价格
                && OilModelCategoryEnum.FIXED.getCode().equals(targetOilModel.getOilCategory())) {
            return new FixedToFixedStrategy();
        } else if (originOilModel.getOilType().equals(targetOilModel.getOilType())) { // 相邻月同种油种转价
            return new VariationStrategy();
        } else if (containsPair(origin, target)) { // 同月不同油种可转价
            return new DifferenceStrategy();
        } else {
            return null;
        }
    }

    private static boolean containsPair(String origin, String target) {
        Iterator<Set<String>> iterator = availableOilModelPairSet.iterator();

        while(iterator.hasNext()) {
            Set<String> pairSet = iterator.next();

            if (pairSet.contains(origin) && pairSet.contains(target)) {
                return true;
            }
        }

        return false;
    }
}
