package com.sinochem.crude.trade.transaction.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品规格枚举
 * @author Yichen Zhao
 * date: 20180228
 */
public enum ProductSpecificationEnum {

    NO_92("1", "92#", "92#", "1"),
    NO_0("2", "95#", "95#", "1"),
    NO_98("3", "98#", "98#", "1"),
    NO_88("4", "88#", "88#", "1"),

    PPM_10("5", "10ppm", "10ppm", "2"),
    PPM_50("6", "50ppm", "50ppm", "2"),
    PPM_500("7", "500ppm", "500ppm", "2"),
    OTHERS("8", "Others", "Others", "2"),

    JET_A_1("9", "Jet A-1", "Jet A-1", "3"),

    LIGHT_NAPHTHA("10", "Light Naphtha", "Light Naphtha", "4"),
    HEAVY_NAPHTHA("11", "Heavy Naphtha", "Heavy Naphtha", "4"),
    NAPHTHA("12", "Naphtha", "Naphtha", "4");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称，目前和英文名称相同
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 商品分类代码
     */
    private String productCategoryCode;

    ProductSpecificationEnum(String code, String zhName, String enName, String productCategoryCode) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
        this.productCategoryCode = productCategoryCode;
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

    /**
     * 方便翻译
     * @return
     * @author whd
     */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }
    public static Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("1","92#");
        map.put("2","95#");
        map.put("3","98#");
        map.put("4","88#");
        map.put("5","10ppm");
        map.put("6","50ppm");
        map.put("7","500ppm");
        map.put("8","Others");
        map.put("9","Jet A-1");
        map.put("10","Light Naphtha");
        map.put("11","Heavy Naphtha");
        map.put("12","Naphtha");

        return map;
    }
}
