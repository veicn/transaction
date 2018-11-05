package com.sinochem.crude.trade.transaction.enums;

/**
 * 销售类型枚举
 * @author Yichen Zhao
 * date: 20180225
 */
public enum DemandTypeEnum {

    PUBLIC("1", "公开Public", "Public"),
    DIRECTIONAL("2", "定向Directional", "Directional");

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

    DemandTypeEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取销售类型
     */
    public static DemandTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (DemandTypeEnum saleTypeEnum : DemandTypeEnum.values()) {
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
