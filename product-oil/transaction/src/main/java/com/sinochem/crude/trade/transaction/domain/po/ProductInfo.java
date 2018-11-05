package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 单据的详情，包括需求单和报价单，为两者的公共字段
 * @author Yichen Zhao
 * date: 20180225
 */
public class ProductInfo extends BasePO {

    /**
     * 销售类型（SaleTypeEnum）
     */
    private String saleTypeCode;

    /**
     * 采购类型（SaleTypeEnum）
     */
    private String demandTypeCode;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 公布中标日期
     */
    private Date bidOpeningDate;

    /**
     * 商品分类（ProductCategoryEnum）
     */
    private String productCategoryCode;

    /**
     * 商品品种（ProductVarietyEnum）
     */
    private String productVarietyCode;

    /**
     * 规格（ProductSpecificationEnum）
     */
    private String productSpecificationCode;

    /**
     * 出口类型（ExportTypeEnum）
     */
    private String exportTypeCode;

    /**
     * 贸易条款（TradeTermEnum）
     */
    private String tradeTermCode;

    /**
     * 数量（吨）
     */
    private BigDecimal quantity;

    /**
     * 数量单位
     */
    private String quantityUnitCode;

    /**
     * 溢短装
     */
    private String tolerance; //一期默认值直接为5

    /**
     * 质量标准文件名称
     */
    private String qualityStandard;

    /**
     * 质量标准文件名称
     */
    private String qualityStandardName;



    /** getters and setters */

    public String getQuantityUnitCode() {
        return quantityUnitCode;
    }

    public void setQuantityUnitCode(String quantityUnitCode) {
        this.quantityUnitCode = quantityUnitCode;
    }

    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getBidOpeningDate() {
        return bidOpeningDate;
    }

    public void setBidOpeningDate(Date bidOpeningDate) {
        this.bidOpeningDate = bidOpeningDate;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getProductVarietyCode() {
        return productVarietyCode;
    }

    public void setProductVarietyCode(String productVarietyCode) {
        this.productVarietyCode = productVarietyCode;
    }

    public String getProductSpecificationCode() {
        return productSpecificationCode;
    }

    public void setProductSpecificationCode(String productSpecificationCode) {
        this.productSpecificationCode = productSpecificationCode;
    }

    public String getExportTypeCode() {
        return exportTypeCode;
    }

    public void setExportTypeCode(String exportTypeCode) {
        this.exportTypeCode = exportTypeCode;
    }

    public String getTradeTermCode() {
        return tradeTermCode;
    }

    public void setTradeTermCode(String tradeTermCode) {
        this.tradeTermCode = tradeTermCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public String getQualityStandard() {
        return qualityStandard;
    }

    public void setQualityStandard(String qualityStandard) {
        this.qualityStandard = qualityStandard;
    }

    public String getQualityStandardName() {
        return qualityStandardName;
    }

    public void setQualityStandardName(String qualityStandardName) {

        this.qualityStandardName = qualityStandardName;
    }

    public String getDemandTypeCode() {
        return demandTypeCode;
    }

    public void setDemandTypeCode(String demandTypeCode) {
        this.demandTypeCode = demandTypeCode;
    }


}
