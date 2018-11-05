package com.sinochem.crude.trade.portal.model.vo;

import java.io.Serializable;

public class SymbolResponseVO implements Serializable {

    private static final long serialVersionUID = 4332285821096692321L;

    private String symbol;

    private String symbolName;

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
