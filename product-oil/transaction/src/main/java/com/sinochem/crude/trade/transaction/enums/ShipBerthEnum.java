package com.sinochem.crude.trade.transaction.enums;

/**
 * 泊位信息枚举
 * @author Yichen Zhao
 * date: 20180228
 */
public enum ShipBerthEnum {

    BERTH1("1", "1", "6#", "6#", "10000 MTS", "9M", "", ""),
    BERTH2("2", "1", "5#", "5#", "30000 MTS", "11.5M", "", ""),
    BERTH3("3", "1", "4#", "4#", "50000 MTS", "13.5M", "MR", ""),
    BERTH4("4", "1", "3#", "3#", "100000 MTS", "18.5M", "LR1/MR", "");

    /*BERTH5("5", "6", "3#", "3#", "10000 MTS", "9M", "MR", ""),
    BERTH6("6", "6", "4#", "4#", "30000 MTS", "11.5M", "MR", ""),
    BERTH7("7", "6", "5#", "5#", "50000 MTS", "13.5M", "LR/MR", ""),
    BERTH8("8", "6", "6#", "6#", "100000 MTS", "18.5M", "LR/MR", "");*/

    /**
     * 代码
     */
    private String code;

    /**
     * 港口的代码
     */
    private String shipPortCode;

    /**
     * 泊位中文名称
     */
    private String zhName;

    /**
     * 泊位英文名称
     */
    private String enName;

    /**
     * 泊位吨位
     */
    private String berthTonnage;

    /**
     * 泊位吃水
     */
    private String berthDraft;

    /**
     * 适合船型
     */
    private String vesselType;

    /**
     * 泊位说明
     */
    private String remark;

    ShipBerthEnum(String code, String shipPortCode, String zhName, String enName,
                  String berthTonnage, String berthDraft, String vesselType, String remark) {
        this.code = code;
        this.shipPortCode = shipPortCode;
        this.zhName = zhName;
        this.enName = enName;
        this.berthTonnage = berthTonnage;
        this.berthDraft = berthDraft;
        this.vesselType = vesselType;
        this.remark = remark;
    }

    /** getters and setters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShipPortCode() {
        return shipPortCode;
    }

    public void setShipPortCode(String shipPortCode) {
        this.shipPortCode = shipPortCode;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getBerthTonnage() {
        return berthTonnage;
    }

    public void setBerthTonnage(String berthTonnage) {
        this.berthTonnage = berthTonnage;
    }

    public String getBerthDraft() {
        return berthDraft;
    }

    public void setBerthDraft(String berthDraft) {
        this.berthDraft = berthDraft;
    }

    public String getVesselType() {
        return vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
