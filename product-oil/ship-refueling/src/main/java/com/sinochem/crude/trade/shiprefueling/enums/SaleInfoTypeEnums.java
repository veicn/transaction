package com.sinochem.crude.trade.shiprefueling.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/28
 **/
public enum SaleInfoTypeEnums {


    SALE_INFOTYPE_ONE("1","船燃贸易", ""),
    SALE_INFOTYPE_TWO("2","船舶加油(内贸)", ""),
    SALE_INFOTYPE_THREE("3","船舶加油(保税)",""),
    SALE_INFOTYPE_FOUR("9","原料油销售","");
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

    private SaleInfoTypeEnums(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }

        /**
     * 转map
     */
    public static Map<String,String> toMap() {
        Map<String ,String > map = new HashMap<String ,String >();

        for (SaleInfoTypeEnums infoTypeWEnums : SaleInfoTypeEnums.values()) {
            map.put(infoTypeWEnums.getCode(),infoTypeWEnums.getZhName());
        }

        return map;
    }
}
