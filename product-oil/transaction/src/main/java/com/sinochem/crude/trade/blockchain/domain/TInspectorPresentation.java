package com.sinochem.crude.trade.blockchain.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TInspectorPresentation  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String uuid;

    private BigDecimal billLadingTon;

    private BigDecimal billLadingBucket;

    private BigDecimal shipInspectionTon;

    private BigDecimal shipInspectionBucket;

    private Date billLadingDate;

    private BigDecimal gasolineDensity;

    private BigDecimal gasolineBoilingPoint;

    private BigDecimal gasolineVaporTension;

    private BigDecimal gasolineInductionPeriod;

    private BigDecimal gasolineSulfurContent;

    private BigDecimal gasolineOxygenContent;

    private BigDecimal dieseloilSulfurContent;

    private BigDecimal gasolineGum;

    private BigDecimal gasolineDistillation;

    private BigDecimal gasolineOxidationStability;

    private BigDecimal gasolineAcidAlkali;

    private String gasolineCopperCorrosion;

    private BigDecimal gasolineOctaneNumber;

    private BigDecimal dieseloilDensity;

    private BigDecimal dieseloilCetaneValue;

    private BigDecimal dieseloilFlashPoint;

    private BigDecimal dieseloilAsh;

    private String dieseloilCopperCorrosion;

    private BigDecimal dieseloilClosingFlash;

    private BigDecimal dieseloilAcidity;

    private BigDecimal dieseloilCondensationPoint;

    private BigDecimal dieseloilWaterContent;

    private BigDecimal dieseloilViscosity;

    private String linkman;

    private String contactNumber;

    private String remark;

    private String state;

    private String dealNo;

    private String inspectorId;

    private String aliveFlag;

    private Date createDate;

    private Date updateDate;

    private Long createUser;

    private Long updateUser;

    private String billno;

    private Long inspAppId;

    public BigDecimal getDieseloilSulfurContent() {
        return dieseloilSulfurContent;
    }

    public void setDieseloilSulfurContent(BigDecimal dieseloilSulfurContent) {
        this.dieseloilSulfurContent = dieseloilSulfurContent;
    }

    public Long getInspAppId() {
        return inspAppId;
    }

    public void setInspAppId(Long inspAppId) {
        this.inspAppId = inspAppId;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public BigDecimal getBillLadingTon() {
        return billLadingTon;
    }

    public void setBillLadingTon(BigDecimal billLadingTon) {
        this.billLadingTon = billLadingTon;
    }

    public BigDecimal getBillLadingBucket() {
        return billLadingBucket;
    }

    public void setBillLadingBucket(BigDecimal billLadingBucket) {
        this.billLadingBucket = billLadingBucket;
    }

    public BigDecimal getShipInspectionTon() {
        return shipInspectionTon;
    }

    public void setShipInspectionTon(BigDecimal shipInspectionTon) {
        this.shipInspectionTon = shipInspectionTon;
    }

    public BigDecimal getShipInspectionBucket() {
        return shipInspectionBucket;
    }

    public void setShipInspectionBucket(BigDecimal shipInspectionBucket) {
        this.shipInspectionBucket = shipInspectionBucket;
    }

    public Date getBillLadingDate() {
        return billLadingDate;
    }

    public void setBillLadingDate(Date billLadingDate) {
        this.billLadingDate = billLadingDate;
    }

    public BigDecimal getGasolineDensity() {
        return gasolineDensity;
    }

    public void setGasolineDensity(BigDecimal gasolineDensity) {
        this.gasolineDensity = gasolineDensity;
    }

    public BigDecimal getGasolineBoilingPoint() {
        return gasolineBoilingPoint;
    }

    public void setGasolineBoilingPoint(BigDecimal gasolineBoilingPoint) {
        this.gasolineBoilingPoint = gasolineBoilingPoint;
    }

    public BigDecimal getGasolineVaporTension() {
        return gasolineVaporTension;
    }

    public void setGasolineVaporTension(BigDecimal gasolineVaporTension) {
        this.gasolineVaporTension = gasolineVaporTension;
    }

    public BigDecimal getGasolineInductionPeriod() {
        return gasolineInductionPeriod;
    }

    public void setGasolineInductionPeriod(BigDecimal gasolineInductionPeriod) {
        this.gasolineInductionPeriod = gasolineInductionPeriod;
    }

    public BigDecimal getGasolineSulfurContent() {
        return gasolineSulfurContent;
    }

    public void setGasolineSulfurContent(BigDecimal gasolineSulfurContent) {
        this.gasolineSulfurContent = gasolineSulfurContent;
    }

    public BigDecimal getGasolineOxygenContent() {
        return gasolineOxygenContent;
    }

    public void setGasolineOxygenContent(BigDecimal gasolineOxygenContent) {
        this.gasolineOxygenContent = gasolineOxygenContent;
    }

    public BigDecimal getGasolineGum() {
        return gasolineGum;
    }

    public void setGasolineGum(BigDecimal gasolineGum) {
        this.gasolineGum = gasolineGum;
    }

    public BigDecimal getGasolineDistillation() {
        return gasolineDistillation;
    }

    public void setGasolineDistillation(BigDecimal gasolineDistillation) {
        this.gasolineDistillation = gasolineDistillation;
    }

    public BigDecimal getGasolineOxidationStability() {
        return gasolineOxidationStability;
    }

    public void setGasolineOxidationStability(BigDecimal gasolineOxidationStability) {
        this.gasolineOxidationStability = gasolineOxidationStability;
    }

    public BigDecimal getGasolineAcidAlkali() {
        return gasolineAcidAlkali;
    }

    public void setGasolineAcidAlkali(BigDecimal gasolineAcidAlkali) {
        this.gasolineAcidAlkali = gasolineAcidAlkali;
    }

    public String getGasolineCopperCorrosion() {
        return gasolineCopperCorrosion;
    }

    public void setGasolineCopperCorrosion(String gasolineCopperCorrosion) {
        this.gasolineCopperCorrosion = gasolineCopperCorrosion == null ? null : gasolineCopperCorrosion.trim();
    }

    public BigDecimal getGasolineOctaneNumber() {
        return gasolineOctaneNumber;
    }

    public void setGasolineOctaneNumber(BigDecimal gasolineOctaneNumber) {
        this.gasolineOctaneNumber = gasolineOctaneNumber;
    }

    public BigDecimal getDieseloilDensity() {
        return dieseloilDensity;
    }

    public void setDieseloilDensity(BigDecimal dieseloilDensity) {
        this.dieseloilDensity = dieseloilDensity;
    }

    public BigDecimal getDieseloilCetaneValue() {
        return dieseloilCetaneValue;
    }

    public void setDieseloilCetaneValue(BigDecimal dieseloilCetaneValue) {
        this.dieseloilCetaneValue = dieseloilCetaneValue;
    }

    public BigDecimal getDieseloilFlashPoint() {
        return dieseloilFlashPoint;
    }

    public void setDieseloilFlashPoint(BigDecimal dieseloilFlashPoint) {
        this.dieseloilFlashPoint = dieseloilFlashPoint;
    }

    public BigDecimal getDieseloilAsh() {
        return dieseloilAsh;
    }

    public void setDieseloilAsh(BigDecimal dieseloilAsh) {
        this.dieseloilAsh = dieseloilAsh;
    }

    public String getDieseloilCopperCorrosion() {
        return dieseloilCopperCorrosion;
    }

    public void setDieseloilCopperCorrosion(String dieseloilCopperCorrosion) {
        this.dieseloilCopperCorrosion = dieseloilCopperCorrosion == null ? null : dieseloilCopperCorrosion.trim();
    }

    public BigDecimal getDieseloilClosingFlash() {
        return dieseloilClosingFlash;
    }

    public void setDieseloilClosingFlash(BigDecimal dieseloilClosingFlash) {
        this.dieseloilClosingFlash = dieseloilClosingFlash;
    }

    public BigDecimal getDieseloilAcidity() {
        return dieseloilAcidity;
    }

    public void setDieseloilAcidity(BigDecimal dieseloilAcidity) {
        this.dieseloilAcidity = dieseloilAcidity;
    }

    public BigDecimal getDieseloilCondensationPoint() {
        return dieseloilCondensationPoint;
    }

    public void setDieseloilCondensationPoint(BigDecimal dieseloilCondensationPoint) {
        this.dieseloilCondensationPoint = dieseloilCondensationPoint;
    }

    public BigDecimal getDieseloilWaterContent() {
        return dieseloilWaterContent;
    }

    public void setDieseloilWaterContent(BigDecimal dieseloilWaterContent) {
        this.dieseloilWaterContent = dieseloilWaterContent;
    }

    public BigDecimal getDieseloilViscosity() {
        return dieseloilViscosity;
    }

    public void setDieseloilViscosity(BigDecimal dieseloilViscosity) {
        this.dieseloilViscosity = dieseloilViscosity;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo == null ? null : dealNo.trim();
    }

    public String getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(String inspectorId) {
        this.inspectorId = inspectorId == null ? null : inspectorId.trim();
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }


    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",this.id);
        map.put("uuid",this.uuid);
        map.put("billLadingTon",this.billLadingTon);
        map.put("billLadingBucket",this.billLadingBucket);
        map.put("shipInspectionTon",this.shipInspectionTon);
        map.put("shipInspectionBucket",this.shipInspectionBucket);
        map.put("billLadingDate",this.billLadingDate);
        map.put("gasolineDensity",this.gasolineDensity);
        map.put("gasolineBoilingPoint",this.gasolineBoilingPoint);
        map.put("gasolineVaporTension",this.gasolineVaporTension);
        map.put("gasolineInductionPeriod",this.gasolineInductionPeriod);
        map.put("gasolineSulfurContent",this.gasolineSulfurContent);
        map.put("gasolineOxygenContent",this.gasolineOxygenContent);
        map.put("gasolineGum",this.gasolineGum);
        map.put("gasolineDistillation",this.gasolineDistillation);
        map.put("gasolineOxidationStability",this.gasolineOxidationStability);
        map.put("gasolineAcidAlkali",this.gasolineAcidAlkali);
        map.put("gasolineCopperCorrosion",this.gasolineCopperCorrosion);
        map.put("gasolineOctaneNumber",this.gasolineOctaneNumber);
        map.put("dieseloilDensity",this.dieseloilDensity);
        map.put("dieseloilCetaneValue",this.dieseloilCetaneValue);
        map.put("dieseloilFlashPoint",this.dieseloilFlashPoint);
        map.put("dieseloilAsh",this.dieseloilAsh);
        map.put("dieseloilCopperCorrosion",this.dieseloilCopperCorrosion);
        map.put("dieseloilClosingFlash",this.dieseloilClosingFlash);
        map.put("dieseloilAcidity",this.dieseloilAcidity);
        map.put("dieseloilCondensationPoint",this.dieseloilCondensationPoint);
        map.put("dieseloilWaterContent",this.dieseloilWaterContent);
        map.put("dieseloilViscosity",this.dieseloilViscosity);
        map.put("linkman",this.linkman);
        map.put("contactNumber",this.contactNumber);
        map.put("remark",this.remark);
        map.put("state",this.state);
        map.put("dealNo",this.dealNo);
        map.put("inspectorId",this.inspectorId);
        map.put("aliveFlag",this.aliveFlag);
        map.put("createDate",this.createDate);
        map.put("updateDate",this.updateDate);
        map.put("createUser",this.createUser);
        map.put("updateUser",this.updateUser);
        map.put("billno",this.billno);

        return map;
    }
}