package com.sinochem.crude.trade.transaction.enums;

/**
 * 销售类型枚举
 * @author Yichen Zhao
 * date: 20180225
 */
public enum SheetFlagEnum {

    SALE("1", "SALE", "SALE"),
    PURCHASE("2", "PURCHASE", "PURCHASE");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    String zhName;

    /**
     * 英文名称
     */
    String enName;

    SheetFlagEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取销售类型
     */
    public static SheetFlagEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (SheetFlagEnum sheetFlagEnum : SheetFlagEnum.values()) {
            if (code.equals(sheetFlagEnum.getCode())) {
                return sheetFlagEnum;
            }
        }

        return null;
    }

    /**getters*/
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
}
