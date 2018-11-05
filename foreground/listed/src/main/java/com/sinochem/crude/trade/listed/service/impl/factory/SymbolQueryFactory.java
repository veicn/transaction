package com.sinochem.crude.trade.listed.service.impl.factory;

import com.sinochem.crude.trade.listed.enums.SymbolInfoCombineEnum;
import com.sinochem.crude.trade.listed.model.vo.SymbolQueryVO;

/**
 * 生成资讯合约请求的工厂类
 * @author Yichen Zhao
 * date: 20180425
 */
public class SymbolQueryFactory {

    private static final String EXTENDS2 = "1";

    public static SymbolQueryVO genSymbolQueryVO(SymbolInfoCombineEnum symbolInfoCombineEnum) {

        if (symbolInfoCombineEnum == null) {
            return null;
        }

        SymbolQueryVO symbolQueryVO = new SymbolQueryVO();

        symbolQueryVO.setCommodity(symbolInfoCombineEnum.getCommodity());
        symbolQueryVO.setPriceSource(symbolInfoCombineEnum.getPriceSource());
        symbolQueryVO.setMarket(symbolInfoCombineEnum.getMarket());

        if (SymbolInfoCombineEnum.BRENT.equals(symbolInfoCombineEnum)
                || SymbolInfoCombineEnum.WTI.equals(symbolInfoCombineEnum)) {
            symbolQueryVO.setExtend2(EXTENDS2);
        }

        return symbolQueryVO;
    }
}
