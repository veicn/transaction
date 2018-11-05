package com.sinochem.crude.trade.transaction.enums;

/**
 * 计价基准枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum PricingBenchmarkEnum {

    GASOIL_10PPM("1", "Gasoil 10ppm", "Gasoil 10ppm"),
    GASOIL_50PPM("2", "Gasoil 50ppm", "Gasoil 50ppm"),
    GASOIL_500PPM("3", "Gasoil 500ppm", "Gasoil 500ppm"),
    GASOLINE_92RON("4", "Gasoline 92ron", "Gasoline 92ron"),
    GASOLINE_95RON("5", "Gasoline 95ron", "Gasoline 95ron"),
    GASOLINE_97RON("6", "Gasoline 97ron", "Gasoline 97ron"),
    KEROSENE("7", "Kerosene", "Kerosene"),
    JPA_NAPHTHA("8", "JPN Naphtha", "JPN Naphtha");

    /**
     * 代码
     */
    private String code;

    /**
     * 计价基准中文名称
     */
    private String zhName;

    /**
     * 计价基准英文行程
     */
    private String enName;

    PricingBenchmarkEnum(String code, String zhName, String enName) {
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
