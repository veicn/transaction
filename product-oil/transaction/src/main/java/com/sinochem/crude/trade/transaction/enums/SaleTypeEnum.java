package com.sinochem.crude.trade.transaction.enums;

/**
 * 销售类型枚举
 * @author Yichen Zhao
 * date: 20180225
 */
public enum SaleTypeEnum {

    PUBLIC("1", "招标Public", "Public"),
    DIRECTIONAL("2", "询价Directional", "Directional");

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

    SaleTypeEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取销售类型
     */
    public static SaleTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (SaleTypeEnum saleTypeEnum : SaleTypeEnum.values()) {
            if (code.equals(saleTypeEnum.getCode())) {
                return saleTypeEnum;
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
