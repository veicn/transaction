package com.sinochem.crude.trade.order.domain;

import com.sinochem.crude.trade.goods.domain.BodyProperties;
import com.sinochem.crude.trade.goods.enums.CrudeOilIndicator;

import java.util.Date;

public class ProductOilResource extends BodyProperties<CrudeOilIndicator> {

    /**
     *油号
     */
    private String no;

    /**
     * 名字
     */
    private String name;

    /**
     * 中文名字
     */
    private String cnName;

    /**
     * 产地
     */
    private Long origin;
    /**
     * 成品油分类
     */
    private Integer productOilClassify;
    /**
     * 成品油种类
     */
    private Integer productOilKind;
    /**
     * 成品油规格
     */
    private Integer productOilSpecs;

    /**
     * 物性表id
     */
    private Long propertyId;

    /**
     * 发布的版本
     */
    private Date version;

    /**
     * 备注
     */
    private String desc;

    /**
     * 合约id
     */
    private Long contractId;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Integer getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(Integer productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public Integer getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(Integer productOilKind) {
        this.productOilKind = productOilKind;
    }

    public Integer getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(Integer productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}