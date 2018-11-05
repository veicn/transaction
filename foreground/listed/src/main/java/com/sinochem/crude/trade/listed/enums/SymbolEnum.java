package com.sinochem.crude.trade.listed.enums;

/**
 * 资讯合约枚举
 * @author Yichen Zhao
 * date: 20180204
 */
public enum SymbolEnum {

    DTD_BRENT_SPREAD_1(SymbolInfoCombineEnum.DTD_BRENT_SPREAD, 1),
    DTD_BRENT_SPREAD_2(SymbolInfoCombineEnum.DTD_BRENT_SPREAD, 2),
    DTD_BRENT_SPREAD_3(SymbolInfoCombineEnum.DTD_BRENT_SPREAD, 3),

    DUBAI_SPREAD_1(SymbolInfoCombineEnum.DUBAI_SPREAD, 1),
    DUBAI_SPREAD_2(SymbolInfoCombineEnum.DUBAI_SPREAD, 2),
    DUBAI_SPREAD_3(SymbolInfoCombineEnum.DUBAI_SPREAD, 3),

    EFS_1(SymbolInfoCombineEnum.EFS, 1),
    EFS_2(SymbolInfoCombineEnum.EFS, 2),
    EFS_3(SymbolInfoCombineEnum.EFS, 3),

    DTD_BRENT_DUBAI_1(SymbolInfoCombineEnum.DTD_BRENT_DUBAI, 1),
    DTD_BRENT_DUBAI_2(SymbolInfoCombineEnum.DTD_BRENT_DUBAI, 2),
    DTD_BRENT_DUBAI_3(SymbolInfoCombineEnum.DTD_BRENT_DUBAI, 3),

    BRENT_1(SymbolInfoCombineEnum.BRENT, 1),
    BRENT_2(SymbolInfoCombineEnum.BRENT, 2),
    BRENT_3(SymbolInfoCombineEnum.BRENT, 3),
    BRENT_4(SymbolInfoCombineEnum.BRENT, 4),
    BRENT_5(SymbolInfoCombineEnum.BRENT, 5),
    BRENT_6(SymbolInfoCombineEnum.BRENT, 6),
    BRENT_7(SymbolInfoCombineEnum.BRENT, 7),
    BRENT_8(SymbolInfoCombineEnum.BRENT, 8),
    BRENT_9(SymbolInfoCombineEnum.BRENT, 9),
    BRENT_10(SymbolInfoCombineEnum.BRENT, 10),
    BRENT_11(SymbolInfoCombineEnum.BRENT, 11),
    BRENT_12(SymbolInfoCombineEnum.BRENT, 12),

    WTI_1(SymbolInfoCombineEnum.WTI, 1),
    WTI_2(SymbolInfoCombineEnum.WTI, 2),
    WTI_3(SymbolInfoCombineEnum.WTI, 3),
    WTI_4(SymbolInfoCombineEnum.WTI, 4),
    WTI_5(SymbolInfoCombineEnum.WTI, 5),
    WTI_6(SymbolInfoCombineEnum.WTI, 6),
    WTI_7(SymbolInfoCombineEnum.WTI, 7),
    WTI_8(SymbolInfoCombineEnum.WTI, 8),
    WTI_9(SymbolInfoCombineEnum.WTI, 9),
    WTI_10(SymbolInfoCombineEnum.WTI, 10),
    WTI_11(SymbolInfoCombineEnum.WTI, 11),
    WTI_12(SymbolInfoCombineEnum.WTI, 12);

    SymbolInfoCombineEnum symbolInfoCombineEnum;
    int order;

    SymbolEnum(SymbolInfoCombineEnum symbolInfoCombineEnum, int order) {
        this.symbolInfoCombineEnum = symbolInfoCombineEnum;
        this.order = order;
    }

    public static SymbolEnum getSymbolEnumByCombineAndOrder(SymbolInfoCombineEnum symbolInfoCombineEnum, int order) {
        for (SymbolEnum symbolEnum : SymbolEnum.values()) {
            if (symbolEnum.getSymbolInfoCombineEnum() != null) {
                if (symbolEnum.getSymbolInfoCombineEnum().equals(symbolInfoCombineEnum)
                        && symbolEnum.getOrder() == order) {
                    return symbolEnum;
                }
            }
        }

        return null;
    }

    /** getters and setters */
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public SymbolInfoCombineEnum getSymbolInfoCombineEnum() {
        return symbolInfoCombineEnum;
    }

    public void setSymbolInfoCombineEnum(SymbolInfoCombineEnum symbolInfoCombineEnum) {
        this.symbolInfoCombineEnum = symbolInfoCombineEnum;
    }
}
