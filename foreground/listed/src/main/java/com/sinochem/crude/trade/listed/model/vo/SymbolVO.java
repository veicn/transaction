package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

/**
 * 资讯合约的VO，之所以叫Symbol是因为资讯那边叫Symbol
 * @author Yichen Zhao
 * date: 20180203
 */
public class SymbolVO implements Serializable {

    private static final long serialVersionUID = 8491813159353845596L;

    private String symbol;

    private String symbolName;

    /** geters and setters */
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
