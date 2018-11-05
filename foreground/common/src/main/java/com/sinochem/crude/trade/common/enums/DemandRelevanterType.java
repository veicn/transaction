package com.sinochem.crude.trade.common.enums;

/**
 * 需求报价角色枚举
 * @author Yichen Zhao
 * date: 20180108
 */
public enum DemandRelevanterType {
    /**买方*/
    BUYER("CBB"),

    /**代理商/贸易商*/
    AGENT("CBT"),

    /**供应商*/
    SUPPLIER("P");

    private String code;

    DemandRelevanterType(String code) {
        this.code = code;
    }

    /**
     * 根据Code获取枚举
     *
     * @param code
     * @return
     */
    public static DemandRelevanterType getByCode(String code){
        for(DemandRelevanterType demandRelevanterType : DemandRelevanterType.values()){
            if(demandRelevanterType.getCode().equals(code)){
                return demandRelevanterType;
            }
        }

        return null;
    }

    /*getters and setters*/
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
