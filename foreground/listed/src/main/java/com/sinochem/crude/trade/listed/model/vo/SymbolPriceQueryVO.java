package com.sinochem.crude.trade.listed.model.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * 向资讯模块请求合约价格的vo
 * @author Yichen Zhao
 * date: 20180203
 */
public class SymbolPriceQueryVO {

    private String symbol;

    private String symbolName;

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    /** getters and setters */
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }
}
