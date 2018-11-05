package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.helper.SymbolModelHelper;
import com.sinochem.crude.trade.listed.model.design.OilGraphModel;
import com.sinochem.crude.trade.listed.model.design.OilVertexModel;
import com.sinochem.crude.trade.listed.model.design.base.SymbolModel;
import com.sinochem.crude.trade.listed.service.OilPriceConvertionService;
import com.sinochem.crude.trade.listed.model.design.base.AbstractGraphModel;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.model.design.base.VertexModel;
import com.sinochem.crude.trade.listed.service.impl.factory.DifferenceStrategyFactory;
import com.sinochem.crude.trade.listed.service.impl.factory.OilModelFactory;
import com.sinochem.crude.trade.listed.service.impl.strategy.OilGraphDFSStrategy;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractGraphTraverseStrategy;
import com.sinochem.crude.trade.listed.service.impl.strategy.base.AbstractPriceDifferenceStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Queue;

/**
 * 实时转计价功能的Service实现
 * @author Yichen Zhao
 * date: 20180202
 */
@Service
public class OilPriceConvertionServiceImpl implements OilPriceConvertionService {

    private ThreadLocal<AbstractGraphModel> oilGraphLocal = new ThreadLocal<AbstractGraphModel>() {
        @Override
        protected AbstractGraphModel initialValue() {
            return new OilGraphModel();
        }
    };

    private ThreadLocal<AbstractGraphTraverseStrategy> traverseStrategyLocal = new ThreadLocal<AbstractGraphTraverseStrategy>(){
        @Override
        protected AbstractGraphTraverseStrategy initialValue() {
            return new OilGraphDFSStrategy();
        }
    };

    @Override
    public BigDecimal getOilPriceConvertion(OilModel origin, OilModel target) throws Exception {
        /*获取最短路径*/
        Queue<VertexModel> shortestPath = traverseStrategyLocal.get().getShortestPath(
                oilGraphLocal.get(), new OilVertexModel(origin), new OilVertexModel(target));

        /*相邻两个节点间生成策略，并计算总价*/
        List<OilVertexModel> oilVertexModelList = new ArrayList<>();
        while (!shortestPath.isEmpty()) {
            OilVertexModel oilVertexModel = (OilVertexModel) shortestPath.poll();
            oilVertexModelList.add(oilVertexModel);
        }

        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < oilVertexModelList.size() - 1; i++) {
            OilModel first = oilVertexModelList.get(i).getOilModel();
            OilModel next = oilVertexModelList.get(i + 1).getOilModel();

            AbstractPriceDifferenceStrategy strategy =
                    DifferenceStrategyFactory.genStrategy(first.getOilType(), next.getOilType());

            BigDecimal priceDifference = strategy.getValue(first, next);
            total = total.add(priceDifference);
        }

        return total;
    }

    @Override
    public List<Calendar> getSymbolDateListByOilType(String oilType) throws Exception {
        return SymbolModelHelper.getSymbolDateListByOilType(oilType);
    }

    @Override
    public BigDecimal getDubaiSymbolPrice(Calendar targetCalendar) throws Exception {
        Calendar today = Calendar.getInstance();

        OilModel oilModelWTICurrentMonthPlus2 = OilModelFactory.getOilModel(
                OilModelTypeEnum.WTI.getCode(),
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH) + 3
        );

        OilModel oilModelDubai = OilModelFactory.getOilModel(
                OilModelTypeEnum.DUBAI.getCode(),
                targetCalendar.get(Calendar.YEAR),
                targetCalendar.get(Calendar.MONTH) + 1
        );

        SymbolModel currentMonthPlus2WTI = SymbolModelHelper.getSymbolModel(oilModelWTICurrentMonthPlus2);
        return currentMonthPlus2WTI.getSettlementPrice()
                .add(getOilPriceConvertion(oilModelWTICurrentMonthPlus2, oilModelDubai));
    }
}
