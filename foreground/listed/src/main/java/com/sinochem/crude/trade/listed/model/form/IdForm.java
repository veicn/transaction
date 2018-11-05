package com.sinochem.crude.trade.listed.model.form;

public class IdForm {

    private Long demandId;

    private Long DemandRelevanter1Id;

    private Long DemandRelevanter2Id;

    private Long crudeOilId;

    private Long demandShipBerthId;

    private String nameOil;//供应商提供油种名称

    private Long demandShipId;

    public Long getDemandShipId() {
        return demandShipId;
    }

    public void setDemandShipId(Long demandShipId) {
        this.demandShipId = demandShipId;
    }

    public String getNameOil() {
        return nameOil;
    }

    public void setNameOil(String nameOil) {
        this.nameOil = nameOil;
    }

    private Integer supShipType; //供应商提供船型

    public Integer getSupShipType() {
        return supShipType;
    }

    public void setSupShipType(Integer supShipType) {
        this.supShipType = supShipType;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Long getDemandRelevanter1Id() {
        return DemandRelevanter1Id;
    }

    public void setDemandRelevanter1Id(Long demandRelevanter1Id) {
        DemandRelevanter1Id = demandRelevanter1Id;
    }

    public Long getDemandRelevanter2Id() {
        return DemandRelevanter2Id;
    }

    public void setDemandRelevanter2Id(Long demandRelevanter2Id) {
        DemandRelevanter2Id = demandRelevanter2Id;
    }

    public Long getCrudeOilId() {
        return crudeOilId;
    }

    public void setCrudeOilId(Long crudeOilId) {
        this.crudeOilId = crudeOilId;
    }

    public Long getDemandShipBerthId() {
        return demandShipBerthId;
    }

    public void setDemandShipBerthId(Long demandShipBerthId) {
        this.demandShipBerthId = demandShipBerthId;
    }
}
