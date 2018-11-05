package com.sinochem.crude.trade.member.domain;

import java.math.BigDecimal;

public class CrudeShipAgencyInfo extends SualificationInfo{
    private Long id;

    private Integer shipNum;

    private BigDecimal shipTonnage;

    private BigDecimal volumeOfFreight;

    private Integer containerNum;

    private BigDecimal totalRevenue;

    private BigDecimal shipAgencyRevenue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShipNum() {
        return shipNum;
    }

    public void setShipNum(Integer shipNum) {
        this.shipNum = shipNum;
    }

    public BigDecimal getShipTonnage() {
        return shipTonnage;
    }

    public void setShipTonnage(BigDecimal shipTonnage) {
        this.shipTonnage = shipTonnage;
    }

    public BigDecimal getVolumeOfFreight() {
        return volumeOfFreight;
    }

    public void setVolumeOfFreight(BigDecimal volumeOfFreight) {
        this.volumeOfFreight = volumeOfFreight;
    }

    public Integer getContainerNum() {
        return containerNum;
    }

    public void setContainerNum(Integer containerNum) {
        this.containerNum = containerNum;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getShipAgencyRevenue() {
        return shipAgencyRevenue;
    }

    public void setShipAgencyRevenue(BigDecimal shipAgencyRevenue) {
        this.shipAgencyRevenue = shipAgencyRevenue;
    }

}