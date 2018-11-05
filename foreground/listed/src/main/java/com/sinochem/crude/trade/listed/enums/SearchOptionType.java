package com.sinochem.crude.trade.listed.enums;

/**
 * 搜索条件的分类枚举
 * @author Yichen Zhao
 * date: 20180110
 */
public enum SearchOptionType {
    PURCHASE_TYPE(1L, "交易方式", false),
    CRUDE_OIL_ORIGIN(2L, "原油产地", false),
    CRUDE_OIL_KIND(3L, "油种", true),
    QUANTITY(4L, "数量", true);

    Long code;
    String name;
    boolean canExpand;

    SearchOptionType(Long code, String name, boolean canExpand) {
        this.code = code;
        this.name = name;
        this.canExpand = canExpand;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanExpand() {
        return canExpand;
    }

    public void setCanExpand(boolean canExpand) {
        this.canExpand = canExpand;
    }
}
