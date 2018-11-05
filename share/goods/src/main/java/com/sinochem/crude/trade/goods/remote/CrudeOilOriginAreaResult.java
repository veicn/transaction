package com.sinochem.crude.trade.goods.remote;

import java.io.Serializable;
import java.util.Date;

/**
 * TCrudeOriginArea的返回映射对象
 * @author Yichen Zhao
 * date: 20180109
 */
public class CrudeOilOriginAreaResult implements Serializable {

    private static final long serialVersionUID = 345760996586842801L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * t_crude_origin_area.UUID
     */
    private String uuid;

    /**
     * 区域英文名
     */
    private String areaNameE;

    /**
     * 区域中文名
     */
    private String areaNameC;

    /**
     * 有效标志
     */
    private String aliveFlag;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String modifyPerson;

    /**
     * 备用字段1
     */
    private String extend1;

    /**
     * 备用字段2
     */
    private String extend2;

    /**
     * 备用字段3
     */
    private String extend3;

    /**
     * 备用字段4
     */
    private String extend4;

    /**
     * 备用字段5
     */
    private String extend5;

    /**
     * 备用字段6
     */
    private String extend6;

    /**
     * 备用字段7
     */
    private String extend7;

    /**
     * 备用字段8
     */
    private String extend8;

    /**
     * 备用字段9
     */
    private String extend9;

    /**
     * 备用字段10
     */
    private String extend10;

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
        this.uuid = uuid;
    }

    public String getAreaNameE() {
        return areaNameE;
    }

    public void setAreaNameE(String areaNameE) {
        this.areaNameE = areaNameE;
    }

    public String getAreaNameC() {
        return areaNameC;
    }

    public void setAreaNameC(String areaNameC) {
        this.areaNameC = areaNameC;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4;
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5;
    }

    public String getExtend6() {
        return extend6;
    }

    public void setExtend6(String extend6) {
        this.extend6 = extend6;
    }

    public String getExtend7() {
        return extend7;
    }

    public void setExtend7(String extend7) {
        this.extend7 = extend7;
    }

    public String getExtend8() {
        return extend8;
    }

    public void setExtend8(String extend8) {
        this.extend8 = extend8;
    }

    public String getExtend9() {
        return extend9;
    }

    public void setExtend9(String extend9) {
        this.extend9 = extend9;
    }

    public String getExtend10() {
        return extend10;
    }

    public void setExtend10(String extend10) {
        this.extend10 = extend10;
    }
}
