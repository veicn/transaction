package com.sinochem.crude.trade.goods.model;

import java.math.BigDecimal;
import java.util.Date;

public class TCrudeQuality {
    /**
    * t_crude_quality.ID自增主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
    * t_crude_quality.UUIDuuid,唯一键
     *
     * @mbggenerated
     */
    private String uuid;

    /**
    * t_crude_quality.CRUDE_ID原油产品id
     *
     * @mbggenerated
     */
    private Long crudeId;

    /**
    * t_crude_quality.SIMPLE_DATE品质等级日期
     *
     * @mbggenerated
     */
    private Date simpleDate;

    /**
    * t_crude_quality.QUALITY_VERSION品质版本号
     *
     * @mbggenerated
     */
    private String qualityVersion;

    /**
    * t_crude_quality.APIAPI度
     *
     * @mbggenerated
     */
    private BigDecimal api;

    /**
    * t_crude_quality.SULPHUR硫含量,单位%wt，质量百分比
     *
     * @mbggenerated
     */
    private BigDecimal sulphur;

    /**
    * t_crude_quality.ACIDITY酸值,单位mgKOH/g
     *
     * @mbggenerated
     */
    private BigDecimal acidity;

    /**
    * t_crude_quality.FREEZING_POINT凝点,单位℃
     *
     * @mbggenerated
     */
    private String freezingPoint;

    /**
    * t_crude_quality.FLASH_POINT闪点,单位℃
     *
     * @mbggenerated
     */
    private String flashPoint;

    /**
    * t_crude_quality.VISCOSITY黏度（50℃）
     *
     * @mbggenerated
     */
    private String viscosity;

    /**
    * t_crude_quality.CARBON_RESIDUE残碳,单位%wt，质量百分比
     *
     * @mbggenerated
     */
    private String carbonResidue;

    /**
    * t_crude_quality.NICKEL镍含量,ppm wt
     *
     * @mbggenerated
     */
    private String nickel;

    /**
    * t_crude_quality.VANADIUM钒含量,ppm wt
     *
     * @mbggenerated
     */
    private String vanadium;

    /**
    * t_crude_quality.ALIVE_FLAG数据状态
     *
     * @mbggenerated
     */
    private String aliveFlag;

    /**
    * t_crude_quality.YIELD>350℃质量收率
     *
     * @mbggenerated
     */
    private String yield;

    /**
    * t_crude_quality.CREATE_DATE创建日期
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
    * t_crude_quality.CREATE_PERSON创建人
     *
     * @mbggenerated
     */
    private String createPerson;

    /**
    * t_crude_quality.MODIFY_DATE修改日期
     *
     * @mbggenerated
     */
    private Date modifyDate;

    /**
    * t_crude_quality.MODIFY_PERSON修改人
     *
     * @mbggenerated
     */
    private String modifyPerson;

    /**
    * t_crude_quality.SOURCE数据来源(1:转自接口，2:本系统页面维护)
     *
     * @mbggenerated
     */
    private String source;

    /**
    * t_crude_quality.INTERFACE_ID中间表对应的id
     *
     * @mbggenerated
     */
    private Long interfaceId;

    /**
    * t_crude_quality.EXTEND1备用字段1
     *
     * @mbggenerated
     */
    private String extend1;

    /**
    * t_crude_quality.EXTEND2备用字段2
     *
     * @mbggenerated
     */
    private String extend2;

    /**
    * t_crude_quality.EXTEND3备用字段3
     *
     * @mbggenerated
     */
    private String extend3;

    /**
    * t_crude_quality.EXTEND4备用字段4
     *
     * @mbggenerated
     */
    private String extend4;

    /**
    * t_crude_quality.EXTEND5备用字段5
     *
     * @mbggenerated
     */
    private String extend5;

    /**
    * t_crude_quality.EXTEND6备用字段6
     *
     * @mbggenerated
     */
    private String extend6;

    /**
    * t_crude_quality.EXTEND7备用字段7
     *
     * @mbggenerated
     */
    private String extend7;

    /**
    * t_crude_quality.EXTEND8备用字段8
     *
     * @mbggenerated
     */
    private String extend8;

    /**
    * t_crude_quality.EXTEND9备用字段9
     *
     * @mbggenerated
     */
    private String extend9;

    /**
    * t_crude_quality.EXTEND10备用字段10
     *
     * @mbggenerated
     */
    private String extend10;

    /**
    t_crude_quality.ID
     *
     * @return  t_crude_quality.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
    t_crude_quality.ID
     *
     * @param idt_crude_quality.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    t_crude_quality.UUID
     *
     * @return  t_crude_quality.UUID
     *
     * @mbggenerated
     */
    public String getUuid() {
        return uuid;
    }

    /**
    t_crude_quality.UUID
     *
     * @param uuidt_crude_quality.UUID
     *
     * @mbggenerated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
    t_crude_quality.CRUDE_ID
     *
     * @return  t_crude_quality.CRUDE_ID
     *
     * @mbggenerated
     */
    public Long getCrudeId() {
        return crudeId;
    }

    /**
    t_crude_quality.CRUDE_ID
     *
     * @param crudeIdt_crude_quality.CRUDE_ID
     *
     * @mbggenerated
     */
    public void setCrudeId(Long crudeId) {
        this.crudeId = crudeId;
    }

    /**
    t_crude_quality.SIMPLE_DATE
     *
     * @return  t_crude_quality.SIMPLE_DATE
     *
     * @mbggenerated
     */
    public Date getSimpleDate() {
        return simpleDate;
    }

    /**
    t_crude_quality.SIMPLE_DATE
     *
     * @param simpleDatet_crude_quality.SIMPLE_DATE
     *
     * @mbggenerated
     */
    public void setSimpleDate(Date simpleDate) {
        this.simpleDate = simpleDate;
    }

    /**
    t_crude_quality.QUALITY_VERSION
     *
     * @return  t_crude_quality.QUALITY_VERSION
     *
     * @mbggenerated
     */
    public String getQualityVersion() {
        return qualityVersion;
    }

    /**
    t_crude_quality.QUALITY_VERSION
     *
     * @param qualityVersiont_crude_quality.QUALITY_VERSION
     *
     * @mbggenerated
     */
    public void setQualityVersion(String qualityVersion) {
        this.qualityVersion = qualityVersion == null ? null : qualityVersion.trim();
    }

    /**
    t_crude_quality.API
     *
     * @return  t_crude_quality.API
     *
     * @mbggenerated
     */
    public BigDecimal getApi() {
        return api;
    }

    /**
    t_crude_quality.API
     *
     * @param apit_crude_quality.API
     *
     * @mbggenerated
     */
    public void setApi(BigDecimal api) {
        this.api = api;
    }

    /**
    t_crude_quality.SULPHUR
     *
     * @return  t_crude_quality.SULPHUR
     *
     * @mbggenerated
     */
    public BigDecimal getSulphur() {
        return sulphur;
    }

    /**
    t_crude_quality.SULPHUR
     *
     * @param sulphurt_crude_quality.SULPHUR
     *
     * @mbggenerated
     */
    public void setSulphur(BigDecimal sulphur) {
        this.sulphur = sulphur;
    }

    /**
    t_crude_quality.ACIDITY
     *
     * @return  t_crude_quality.ACIDITY
     *
     * @mbggenerated
     */
    public BigDecimal getAcidity() {
        return acidity;
    }

    /**
    t_crude_quality.ACIDITY
     *
     * @param acidityt_crude_quality.ACIDITY
     *
     * @mbggenerated
     */
    public void setAcidity(BigDecimal acidity) {
        this.acidity = acidity;
    }

    /**
    t_crude_quality.FREEZING_POINT
     *
     * @return  t_crude_quality.FREEZING_POINT
     *
     * @mbggenerated
     */
    public String getFreezingPoint() {
        return freezingPoint;
    }

    /**
    t_crude_quality.FREEZING_POINT
     *
     * @param freezingPointt_crude_quality.FREEZING_POINT
     *
     * @mbggenerated
     */
    public void setFreezingPoint(String freezingPoint) {
        this.freezingPoint = freezingPoint == null ? null : freezingPoint.trim();
    }

    /**
    t_crude_quality.FLASH_POINT
     *
     * @return  t_crude_quality.FLASH_POINT
     *
     * @mbggenerated
     */
    public String getFlashPoint() {
        return flashPoint;
    }

    /**
    t_crude_quality.FLASH_POINT
     *
     * @param flashPointt_crude_quality.FLASH_POINT
     *
     * @mbggenerated
     */
    public void setFlashPoint(String flashPoint) {
        this.flashPoint = flashPoint == null ? null : flashPoint.trim();
    }

    /**
    t_crude_quality.VISCOSITY
     *
     * @return  t_crude_quality.VISCOSITY
     *
     * @mbggenerated
     */
    public String getViscosity() {
        return viscosity;
    }

    /**
    t_crude_quality.VISCOSITY
     *
     * @param viscosityt_crude_quality.VISCOSITY
     *
     * @mbggenerated
     */
    public void setViscosity(String viscosity) {
        this.viscosity = viscosity == null ? null : viscosity.trim();
    }

    /**
    t_crude_quality.CARBON_RESIDUE
     *
     * @return  t_crude_quality.CARBON_RESIDUE
     *
     * @mbggenerated
     */
    public String getCarbonResidue() {
        return carbonResidue;
    }

    /**
    t_crude_quality.CARBON_RESIDUE
     *
     * @param carbonResiduet_crude_quality.CARBON_RESIDUE
     *
     * @mbggenerated
     */
    public void setCarbonResidue(String carbonResidue) {
        this.carbonResidue = carbonResidue == null ? null : carbonResidue.trim();
    }

    /**
    t_crude_quality.NICKEL
     *
     * @return  t_crude_quality.NICKEL
     *
     * @mbggenerated
     */
    public String getNickel() {
        return nickel;
    }

    /**
    t_crude_quality.NICKEL
     *
     * @param nickelt_crude_quality.NICKEL
     *
     * @mbggenerated
     */
    public void setNickel(String nickel) {
        this.nickel = nickel == null ? null : nickel.trim();
    }

    /**
    t_crude_quality.VANADIUM
     *
     * @return  t_crude_quality.VANADIUM
     *
     * @mbggenerated
     */
    public String getVanadium() {
        return vanadium;
    }

    /**
    t_crude_quality.VANADIUM
     *
     * @param vanadiumt_crude_quality.VANADIUM
     *
     * @mbggenerated
     */
    public void setVanadium(String vanadium) {
        this.vanadium = vanadium == null ? null : vanadium.trim();
    }

    /**
    t_crude_quality.ALIVE_FLAG
     *
     * @return  t_crude_quality.ALIVE_FLAG
     *
     * @mbggenerated
     */
    public String getAliveFlag() {
        return aliveFlag;
    }

    /**
    t_crude_quality.ALIVE_FLAG
     *
     * @param aliveFlagt_crude_quality.ALIVE_FLAG
     *
     * @mbggenerated
     */
    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    /**
    t_crude_quality.YIELD
     *
     * @return  t_crude_quality.YIELD
     *
     * @mbggenerated
     */
    public String getYield() {
        return yield;
    }

    /**
    t_crude_quality.YIELD
     *
     * @param yieldt_crude_quality.YIELD
     *
     * @mbggenerated
     */
    public void setYield(String yield) {
        this.yield = yield == null ? null : yield.trim();
    }

    /**
    t_crude_quality.CREATE_DATE
     *
     * @return  t_crude_quality.CREATE_DATE
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
    t_crude_quality.CREATE_DATE
     *
     * @param createDatet_crude_quality.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
    t_crude_quality.CREATE_PERSON
     *
     * @return  t_crude_quality.CREATE_PERSON
     *
     * @mbggenerated
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
    t_crude_quality.CREATE_PERSON
     *
     * @param createPersont_crude_quality.CREATE_PERSON
     *
     * @mbggenerated
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    /**
    t_crude_quality.MODIFY_DATE
     *
     * @return  t_crude_quality.MODIFY_DATE
     *
     * @mbggenerated
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
    t_crude_quality.MODIFY_DATE
     *
     * @param modifyDatet_crude_quality.MODIFY_DATE
     *
     * @mbggenerated
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
    t_crude_quality.MODIFY_PERSON
     *
     * @return  t_crude_quality.MODIFY_PERSON
     *
     * @mbggenerated
     */
    public String getModifyPerson() {
        return modifyPerson;
    }

    /**
    t_crude_quality.MODIFY_PERSON
     *
     * @param modifyPersont_crude_quality.MODIFY_PERSON
     *
     * @mbggenerated
     */
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson == null ? null : modifyPerson.trim();
    }

    /**
    t_crude_quality.SOURCE
     *
     * @return  t_crude_quality.SOURCE
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
    t_crude_quality.SOURCE
     *
     * @param sourcet_crude_quality.SOURCE
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
    t_crude_quality.INTERFACE_ID
     *
     * @return  t_crude_quality.INTERFACE_ID
     *
     * @mbggenerated
     */
    public Long getInterfaceId() {
        return interfaceId;
    }

    /**
    t_crude_quality.INTERFACE_ID
     *
     * @param interfaceIdt_crude_quality.INTERFACE_ID
     *
     * @mbggenerated
     */
    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
    t_crude_quality.EXTEND1
     *
     * @return  t_crude_quality.EXTEND1
     *
     * @mbggenerated
     */
    public String getExtend1() {
        return extend1;
    }

    /**
    t_crude_quality.EXTEND1
     *
     * @param extend1t_crude_quality.EXTEND1
     *
     * @mbggenerated
     */
    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    /**
    t_crude_quality.EXTEND2
     *
     * @return  t_crude_quality.EXTEND2
     *
     * @mbggenerated
     */
    public String getExtend2() {
        return extend2;
    }

    /**
    t_crude_quality.EXTEND2
     *
     * @param extend2t_crude_quality.EXTEND2
     *
     * @mbggenerated
     */
    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    /**
    t_crude_quality.EXTEND3
     *
     * @return  t_crude_quality.EXTEND3
     *
     * @mbggenerated
     */
    public String getExtend3() {
        return extend3;
    }

    /**
    t_crude_quality.EXTEND3
     *
     * @param extend3t_crude_quality.EXTEND3
     *
     * @mbggenerated
     */
    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    /**
    t_crude_quality.EXTEND4
     *
     * @return  t_crude_quality.EXTEND4
     *
     * @mbggenerated
     */
    public String getExtend4() {
        return extend4;
    }

    /**
    t_crude_quality.EXTEND4
     *
     * @param extend4t_crude_quality.EXTEND4
     *
     * @mbggenerated
     */
    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }

    /**
    t_crude_quality.EXTEND5
     *
     * @return  t_crude_quality.EXTEND5
     *
     * @mbggenerated
     */
    public String getExtend5() {
        return extend5;
    }

    /**
    t_crude_quality.EXTEND5
     *
     * @param extend5t_crude_quality.EXTEND5
     *
     * @mbggenerated
     */
    public void setExtend5(String extend5) {
        this.extend5 = extend5 == null ? null : extend5.trim();
    }

    /**
    t_crude_quality.EXTEND6
     *
     * @return  t_crude_quality.EXTEND6
     *
     * @mbggenerated
     */
    public String getExtend6() {
        return extend6;
    }

    /**
    t_crude_quality.EXTEND6
     *
     * @param extend6t_crude_quality.EXTEND6
     *
     * @mbggenerated
     */
    public void setExtend6(String extend6) {
        this.extend6 = extend6 == null ? null : extend6.trim();
    }

    /**
    t_crude_quality.EXTEND7
     *
     * @return  t_crude_quality.EXTEND7
     *
     * @mbggenerated
     */
    public String getExtend7() {
        return extend7;
    }

    /**
    t_crude_quality.EXTEND7
     *
     * @param extend7t_crude_quality.EXTEND7
     *
     * @mbggenerated
     */
    public void setExtend7(String extend7) {
        this.extend7 = extend7 == null ? null : extend7.trim();
    }

    /**
    t_crude_quality.EXTEND8
     *
     * @return  t_crude_quality.EXTEND8
     *
     * @mbggenerated
     */
    public String getExtend8() {
        return extend8;
    }

    /**
    t_crude_quality.EXTEND8
     *
     * @param extend8t_crude_quality.EXTEND8
     *
     * @mbggenerated
     */
    public void setExtend8(String extend8) {
        this.extend8 = extend8 == null ? null : extend8.trim();
    }

    /**
    t_crude_quality.EXTEND9
     *
     * @return  t_crude_quality.EXTEND9
     *
     * @mbggenerated
     */
    public String getExtend9() {
        return extend9;
    }

    /**
    t_crude_quality.EXTEND9
     *
     * @param extend9t_crude_quality.EXTEND9
     *
     * @mbggenerated
     */
    public void setExtend9(String extend9) {
        this.extend9 = extend9 == null ? null : extend9.trim();
    }

    /**
    t_crude_quality.EXTEND10
     *
     * @return  t_crude_quality.EXTEND10
     *
     * @mbggenerated
     */
    public String getExtend10() {
        return extend10;
    }

    /**
    t_crude_quality.EXTEND10
     *
     * @param extend10t_crude_quality.EXTEND10
     *
     * @mbggenerated
     */
    public void setExtend10(String extend10) {
        this.extend10 = extend10 == null ? null : extend10.trim();
    }
}