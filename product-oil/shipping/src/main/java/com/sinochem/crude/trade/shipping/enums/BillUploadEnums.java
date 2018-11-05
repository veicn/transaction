package com.sinochem.crude.trade.shipping.enums;

/**
 * @author WGP
 * @description 票据中心的枚举类
 * @date 2018/5/7
 **/
public enum BillUploadEnums {
    DEAL_RECAP("00","Deal Recap",""),
    VESSEL_ACCEPTANCE("01","Vessel Acceptance",""),
    DI("02","Di",""),
    STOWAGE_PLAN("03","Stowage Plan",""),
    CUSTOMS_DECLARATION("04","Customs Declaration",""),
    INSPECTION_REPORT("05","Inspection Report",""),
    BILL_OF_LOADING("06","Bill Of Loading",""),
    LOADING_CARGO_DOCUMENTS("07","Loading Cargo Documents",""),
    DISCHARGE_CARGO_DOCUMENTS("08","Discharge Cargo Documents",""),
    VESSEL_DOCUMENTS("09","Vessel Documents","");



    BillUploadEnums(String code,String enName,String zhName){
        this.code = code;
        this.enName = enName;
        this.zhName = zhName;
    }
    private String code;
    private String enName;
    private String zhName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }
}
