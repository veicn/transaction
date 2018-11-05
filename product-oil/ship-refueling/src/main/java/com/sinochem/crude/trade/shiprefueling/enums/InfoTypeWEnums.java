//package com.sinochem.crude.trade.shiprefueling.enums;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 信息类型
// * @author wdh
// * @descriptioncrude-trade
// * @date 2018/5/28
// **/
//public enum InfoTypeWEnums {
//
//
//    INFOTYPE_ONE("1","船然贸易", ""),
//    INFOTYPE_TWO("2","船舶加油（内贸）", ""),
//    INFOTYPE_THREE("3","船舶加油（保税）", ""),
//    INFOTYPE_NINE("9","原料油", "");
//
//    /**
//     * 代码
//     */
//    private String code;
//
//    /**
//     * 中文名称
//     */
//    private String zhName;
//
//    /**
//     * 英文名称
//     */
//    private String enName;
//
//    private InfoTypeWEnums(String code, String zhName, String enName) {
//        this.code = code;
//        this.zhName = zhName;
//        this.enName = enName;
//    }
//    public String getCode() {
//        return code;
//    }
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getZhName() {
//        return zhName;
//    }
//
//    public String getEnName() {
//        return enName;
//    }
//    /**
//     * 转map
//     */
//    public static Map<String,String> toMap() {
//        Map<String ,String > map = new HashMap<String ,String >();
//
//        for (InfoTypeWEnums infoTypeWEnums : InfoTypeWEnums.values()) {
//            map.put(infoTypeWEnums.getCode(),infoTypeWEnums.getZhName());
//        }
//
//        return map;
//    }
//}
