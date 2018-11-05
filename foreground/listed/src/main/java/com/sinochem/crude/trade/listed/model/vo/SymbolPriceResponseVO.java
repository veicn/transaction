package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用于接收资讯模块返回资讯价格的VO
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolPriceResponseVO implements Serializable {

    private static final long serialVersionUID = -3404816149530828543L;

    private BigDecimal settlementPrice;

    /** getters and setters */
    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }
}
