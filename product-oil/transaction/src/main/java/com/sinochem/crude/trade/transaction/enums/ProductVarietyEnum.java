package com.sinochem.crude.trade.transaction.enums;

/**
 * 商品品种枚举
 * @author Yichen Zhao
 * date: 20180228
 */
public enum ProductVarietyEnum {

    GASOLINE_SINGAPORE("1", "新加坡汽油", "Gasoline Singapore"),
    PHILLIPINE_GASOIL("2", "菲律宾柴油", "Philippine Gasoil"),
    SINGAPORT_GASOIL("3", "新加坡柴油", "Singapore Gasoil"),
    AUSTRALIA_GASOIL("4", "澳洲柴油", "Australia Gasoil"),
    JET_A_1("5", "航空煤油", "Jet A-1");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    ProductVarietyEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters */
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
