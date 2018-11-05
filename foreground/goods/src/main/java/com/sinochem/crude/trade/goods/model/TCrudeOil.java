package com.sinochem.crude.trade.goods.model;

import java.math.BigDecimal;
import java.util.Date;

public class TCrudeOil {
    /**
    * t_crude_oil.ID自增主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
    * t_crude_oil.UUIDUUID
     *
     * @mbggenerated
     */
    private String uuid;

    /**
    * t_crude_oil.CRUDE_NAME_E原油英文名
     *
     * @mbggenerated
     */
    private String crudeNameE;

    /**
    * t_crude_oil.CRUDE_NAME_C英文全称
     *
     * @mbggenerated
     */
    private String crudeNameC;

    /**
    * t_crude_oil.FULL_NAME_E原油中文名
     *
     * @mbggenerated
     */
    private String fullNameE;

    /**
    * t_crude_oil.ORIGIN_AREA_ID产地区域id
     *
     * @mbggenerated
     */
    private Long originAreaId;

    /**
    * t_crude_oil.ORIGIN_ID产地id
     *
     * @mbggenerated
     */
    private Long originId;

    /**
    * t_crude_oil.CATAGORY_ID种类id
     *
     * @mbggenerated
     */
    private Long catagoryId;

    /**
    * t_crude_oil.TRANSACTION_MODE交易模式(1-现货/2-期货)
     *
     * @mbggenerated
     */
    private String transactionMode;

    /**
    * t_crude_oil.PRICE_BASE_FLAG是否基准价 1/0，真/假
     *
     * @mbggenerated
     */
    private String priceBaseFlag;

    /**
    * t_crude_oil.PRICE_MODE价格模式(osp/osp_dis/base_dis，1/2/3，只有现货原油品种有这个属性)
     *
     * @mbggenerated
     */
    private String priceMode;

    /**
    * t_crude_oil.PRIORITY排序
     *
     * @mbggenerated
     */
    private Integer priority;

    /**
    * t_crude_oil.TON_BUCKET桶吨比
     *
     * @mbggenerated
     */
    private BigDecimal tonBucket;

    /**
    * t_crude_oil.ALIVE_FLAG有效标志
     *
     * @mbggenerated
     */
    private String aliveFlag;

    /**
    * t_crude_oil.CREATE_DATE创建日期
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
    * t_crude_oil.CREATE_PERSON创建人
     *
     * @mbggenerated
     */
    private String createPerson;

    /**
    * t_crude_oil.MODIFY_DATE修改日期
     *
     * @mbggenerated
     */
    private Date modifyDate;

    /**
    * t_crude_oil.MODIFY_PERSON修改人
     *
     * @mbggenerated
     */
    private String modifyPerson;

    /**
    * t_crude_oil.EXTEND1备用字段1
     *
     * @mbggenerated
     */
    private String extend1;

    /**
    * t_crude_oil.EXTEND2备用字段2
     *
     * @mbggenerated
     */
    private String extend2;

    /**
    * t_crude_oil.EXTEND3备用字段3
     *
     * @mbggenerated
     */
    private String extend3;

    /**
    * t_crude_oil.EXTEND4备用字段4
     *
     * @mbggenerated
     */
    private String extend4;

    /**
    * t_crude_oil.EXTEND5备用字段5
     *
     * @mbggenerated
     */
    private String extend5;

    /**
    * t_crude_oil.EXTEND6备用字段6
     *
     * @mbggenerated
     */
    private String extend6;

    /**
    * t_crude_oil.EXTEND7备用字段7
     *
     * @mbggenerated
     */
    private String extend7;

    /**
    * t_crude_oil.EXTEND8备用字段8
     *
     * @mbggenerated
     */
    private String extend8;

    /**
    * t_crude_oil.EXTEND9备用字段9
     *
     * @mbggenerated
     */
    private String extend9;

    /**
    * t_crude_oil.EXTEND10备用字段10
     *
     * @mbggenerated
     */
    private String extend10;

    /**
    t_crude_oil.ID
     *
     * @return  t_crude_oil.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
    t_crude_oil.ID
     *
     * @param idt_crude_oil.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    t_crude_oil.UUID
     *
     * @return  t_crude_oil.UUID
     *
     * @mbggenerated
     */
    public String getUuid() {
        return uuid;
    }

    /**
    t_crude_oil.UUID
     *
     * @param uuidt_crude_oil.UUID
     *
     * @mbggenerated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
    t_crude_oil.CRUDE_NAME_E
     *
     * @return  t_crude_oil.CRUDE_NAME_E
     *
     * @mbggenerated
     */
    public String getCrudeNameE() {
        return crudeNameE;
    }

    /**
    t_crude_oil.CRUDE_NAME_E
     *
     * @param crudeNameEt_crude_oil.CRUDE_NAME_E
     *
     * @mbggenerated
     */
    public void setCrudeNameE(String crudeNameE) {
        this.crudeNameE = crudeNameE == null ? null : crudeNameE.trim();
    }

    /**
    t_crude_oil.CRUDE_NAME_C
     *
     * @return  t_crude_oil.CRUDE_NAME_C
     *
     * @mbggenerated
     */
    public String getCrudeNameC() {
        return crudeNameC;
    }

    /**
    t_crude_oil.CRUDE_NAME_C
     *
     * @param crudeNameCt_crude_oil.CRUDE_NAME_C
     *
     * @mbggenerated
     */
    public void setCrudeNameC(String crudeNameC) {
        this.crudeNameC = crudeNameC == null ? null : crudeNameC.trim();
    }

    /**
    t_crude_oil.FULL_NAME_E
     *
     * @return  t_crude_oil.FULL_NAME_E
     *
     * @mbggenerated
     */
    public String getFullNameE() {
        return fullNameE;
    }

    /**
    t_crude_oil.FULL_NAME_E
     *
     * @param fullNameEt_crude_oil.FULL_NAME_E
     *
     * @mbggenerated
     */
    public void setFullNameE(String fullNameE) {
        this.fullNameE = fullNameE == null ? null : fullNameE.trim();
    }

    /**
    t_crude_oil.ORIGIN_AREA_ID
     *
     * @return  t_crude_oil.ORIGIN_AREA_ID
     *
     * @mbggenerated
     */
    public Long getOriginAreaId() {
        return originAreaId;
    }

    /**
    t_crude_oil.ORIGIN_AREA_ID
     *
     * @param originAreaIdt_crude_oil.ORIGIN_AREA_ID
     *
     * @mbggenerated
     */
    public void setOriginAreaId(Long originAreaId) {
        this.originAreaId = originAreaId;
    }

    /**
    t_crude_oil.ORIGIN_ID
     *
     * @return  t_crude_oil.ORIGIN_ID
     *
     * @mbggenerated
     */
    public Long getOriginId() {
        return originId;
    }

    /**
    t_crude_oil.ORIGIN_ID
     *
     * @param originIdt_crude_oil.ORIGIN_ID
     *
     * @mbggenerated
     */
    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    /**
    t_crude_oil.CATAGORY_ID
     *
     * @return  t_crude_oil.CATAGORY_ID
     *
     * @mbggenerated
     */
    public Long getCatagoryId() {
        return catagoryId;
    }

    /**
    t_crude_oil.CATAGORY_ID
     *
     * @param catagoryIdt_crude_oil.CATAGORY_ID
     *
     * @mbggenerated
     */
    public void setCatagoryId(Long catagoryId) {
        this.catagoryId = catagoryId;
    }

    /**
    t_crude_oil.TRANSACTION_MODE
     *
     * @return  t_crude_oil.TRANSACTION_MODE
     *
     * @mbggenerated
     */
    public String getTransactionMode() {
        return transactionMode;
    }

    /**
    t_crude_oil.TRANSACTION_MODE
     *
     * @param transactionModet_crude_oil.TRANSACTION_MODE
     *
     * @mbggenerated
     */
    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode == null ? null : transactionMode.trim();
    }

    /**
    t_crude_oil.PRICE_BASE_FLAG
     *
     * @return  t_crude_oil.PRICE_BASE_FLAG
     *
     * @mbggenerated
     */
    public String getPriceBaseFlag() {
        return priceBaseFlag;
    }

    /**
    t_crude_oil.PRICE_BASE_FLAG
     *
     * @param priceBaseFlagt_crude_oil.PRICE_BASE_FLAG
     *
     * @mbggenerated
     */
    public void setPriceBaseFlag(String priceBaseFlag) {
        this.priceBaseFlag = priceBaseFlag == null ? null : priceBaseFlag.trim();
    }

    /**
    t_crude_oil.PRICE_MODE
     *
     * @return  t_crude_oil.PRICE_MODE
     *
     * @mbggenerated
     */
    public String getPriceMode() {
        return priceMode;
    }

    /**
    t_crude_oil.PRICE_MODE
     *
     * @param priceModet_crude_oil.PRICE_MODE
     *
     * @mbggenerated
     */
    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode == null ? null : priceMode.trim();
    }

    /**
    t_crude_oil.PRIORITY
     *
     * @return  t_crude_oil.PRIORITY
     *
     * @mbggenerated
     */
    public Integer getPriority() {
        return priority;
    }

    /**
    t_crude_oil.PRIORITY
     *
     * @param priorityt_crude_oil.PRIORITY
     *
     * @mbggenerated
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
    t_crude_oil.TON_BUCKET
     *
     * @return  t_crude_oil.TON_BUCKET
     *
     * @mbggenerated
     */
    public BigDecimal getTonBucket() {
        return tonBucket;
    }

    /**
    t_crude_oil.TON_BUCKET
     *
     * @param tonBuckett_crude_oil.TON_BUCKET
     *
     * @mbggenerated
     */
    public void setTonBucket(BigDecimal tonBucket) {
        this.tonBucket = tonBucket;
    }

    /**
    t_crude_oil.ALIVE_FLAG
     *
     * @return  t_crude_oil.ALIVE_FLAG
     *
     * @mbggenerated
     */
    public String getAliveFlag() {
        return aliveFlag;
    }

    /**
    t_crude_oil.ALIVE_FLAG
     *
     * @param aliveFlagt_crude_oil.ALIVE_FLAG
     *
     * @mbggenerated
     */
    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    /**
    t_crude_oil.CREATE_DATE
     *
     * @return  t_crude_oil.CREATE_DATE
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
    t_crude_oil.CREATE_DATE
     *
     * @param createDatet_crude_oil.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
    t_crude_oil.CREATE_PERSON
     *
     * @return  t_crude_oil.CREATE_PERSON
     *
     * @mbggenerated
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
    t_crude_oil.CREATE_PERSON
     *
     * @param createPersont_crude_oil.CREATE_PERSON
     *
     * @mbggenerated
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    /**
    t_crude_oil.MODIFY_DATE
     *
     * @return  t_crude_oil.MODIFY_DATE
     *
     * @mbggenerated
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
    t_crude_oil.MODIFY_DATE
     *
     * @param modifyDatet_crude_oil.MODIFY_DATE
     *
     * @mbggenerated
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
    t_crude_oil.MODIFY_PERSON
     *
     * @return  t_crude_oil.MODIFY_PERSON
     *
     * @mbggenerated
     */
    public String getModifyPerson() {
        return modifyPerson;
    }

    /**
    t_crude_oil.MODIFY_PERSON
     *
     * @param modifyPersont_crude_oil.MODIFY_PERSON
     *
     * @mbggenerated
     */
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson == null ? null : modifyPerson.trim();
    }

    /**
    t_crude_oil.EXTEND1
     *
     * @return  t_crude_oil.EXTEND1
     *
     * @mbggenerated
     */
    public String getExtend1() {
        return extend1;
    }

    /**
    t_crude_oil.EXTEND1
     *
     * @param extend1t_crude_oil.EXTEND1
     *
     * @mbggenerated
     */
    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    /**
    t_crude_oil.EXTEND2
     *
     * @return  t_crude_oil.EXTEND2
     *
     * @mbggenerated
     */
    public String getExtend2() {
        return extend2;
    }

    /**
    t_crude_oil.EXTEND2
     *
     * @param extend2t_crude_oil.EXTEND2
     *
     * @mbggenerated
     */
    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    /**
    t_crude_oil.EXTEND3
     *
     * @return  t_crude_oil.EXTEND3
     *
     * @mbggenerated
     */
    public String getExtend3() {
        return extend3;
    }

    /**
    t_crude_oil.EXTEND3
     *
     * @param extend3t_crude_oil.EXTEND3
     *
     * @mbggenerated
     */
    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    /**
    t_crude_oil.EXTEND4
     *
     * @return  t_crude_oil.EXTEND4
     *
     * @mbggenerated
     */
    public String getExtend4() {
        return extend4;
    }

    /**
    t_crude_oil.EXTEND4
     *
     * @param extend4t_crude_oil.EXTEND4
     *
     * @mbggenerated
     */
    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }

    /**
    t_crude_oil.EXTEND5
     *
     * @return  t_crude_oil.EXTEND5
     *
     * @mbggenerated
     */
    public String getExtend5() {
        return extend5;
    }

    /**
    t_crude_oil.EXTEND5
     *
     * @param extend5t_crude_oil.EXTEND5
     *
     * @mbggenerated
     */
    public void setExtend5(String extend5) {
        this.extend5 = extend5 == null ? null : extend5.trim();
    }

    /**
    t_crude_oil.EXTEND6
     *
     * @return  t_crude_oil.EXTEND6
     *
     * @mbggenerated
     */
    public String getExtend6() {
        return extend6;
    }

    /**
    t_crude_oil.EXTEND6
     *
     * @param extend6t_crude_oil.EXTEND6
     *
     * @mbggenerated
     */
    public void setExtend6(String extend6) {
        this.extend6 = extend6 == null ? null : extend6.trim();
    }

    /**
    t_crude_oil.EXTEND7
     *
     * @return  t_crude_oil.EXTEND7
     *
     * @mbggenerated
     */
    public String getExtend7() {
        return extend7;
    }

    /**
    t_crude_oil.EXTEND7
     *
     * @param extend7t_crude_oil.EXTEND7
     *
     * @mbggenerated
     */
    public void setExtend7(String extend7) {
        this.extend7 = extend7 == null ? null : extend7.trim();
    }

    /**
    t_crude_oil.EXTEND8
     *
     * @return  t_crude_oil.EXTEND8
     *
     * @mbggenerated
     */
    public String getExtend8() {
        return extend8;
    }

    /**
    t_crude_oil.EXTEND8
     *
     * @param extend8t_crude_oil.EXTEND8
     *
     * @mbggenerated
     */
    public void setExtend8(String extend8) {
        this.extend8 = extend8 == null ? null : extend8.trim();
    }

    /**
    t_crude_oil.EXTEND9
     *
     * @return  t_crude_oil.EXTEND9
     *
     * @mbggenerated
     */
    public String getExtend9() {
        return extend9;
    }

    /**
    t_crude_oil.EXTEND9
     *
     * @param extend9t_crude_oil.EXTEND9
     *
     * @mbggenerated
     */
    public void setExtend9(String extend9) {
        this.extend9 = extend9 == null ? null : extend9.trim();
    }

    /**
    t_crude_oil.EXTEND10
     *
     * @return  t_crude_oil.EXTEND10
     *
     * @mbggenerated
     */
    public String getExtend10() {
        return extend10;
    }

    /**
    t_crude_oil.EXTEND10
     *
     * @param extend10t_crude_oil.EXTEND10
     *
     * @mbggenerated
     */
    public void setExtend10(String extend10) {
        this.extend10 = extend10 == null ? null : extend10.trim();
    }
}