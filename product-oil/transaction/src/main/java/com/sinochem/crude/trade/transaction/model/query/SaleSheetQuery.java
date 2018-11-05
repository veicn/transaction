package com.sinochem.crude.trade.transaction.model.query;

import java.util.Date;

/**
 * 销售单的查询条件对象
 * @author Yichen Zhao
 * date: 20180307
 */
public class SaleSheetQuery {

    /**
     * 销售类型
     */
    private String saleTypeCode;

    /**
     * 商品分类
     */
    private String productCategoryCode;

    /**
     * 商品规格
     */
    private String productSpecificationCode;

    /**
     * 出口类型
     */
    private String exportTypeCode;

    /**
     * 销售单单状态
     */
    private String saleSheetStatusCode;

    /**
     * 销售单据号
     */
    private String serialNumber;

    /**
     * 发布时间-开始
     */
    private Date releasedDateStart;

    /**
     * 发布时间-结束
     */
    private Date releasedDateEnd;

    /**
     * 创建时间-开始
     */
    private Date gmtCreatedStart;

    /**
     * 创建时间-结束
     */
    private Date gmtCreatedEnd;

    /**
     * 创建企业ID
     */
    private Long enterpriseId;

    /**
     * 发起查询的企业id，用于定向筛选
     */
    private Long queryEnterpriseId;

    /**
     * 投标截止时间早于某个时间
     */
    private Date deadlineBefore;

    /**
     * 投标截止时间晚于某个时间
     */
    private Date deadlineAfter;

    /**
     * 公布投标时间早于某个时间
     */
    private Date bidOpeningDateBefore;

    /**
     * 公布投标时间晚于某个时间
     */
    private Date bidOpeningDateAfter;

    /**
     * 排序规则
     */
    private String orderByColumn;

    /**
     * 排序类型（升序或降序）
     */
    private String orderType;

    public SaleSheetQuery() { }

    /** getters and setters */
    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getExportTypeCode() {
        return exportTypeCode;
    }

    public void setExportTypeCode(String exportTypeCode) {
        this.exportTypeCode = exportTypeCode;
    }

    public String getSaleSheetStatusCode() {
        return saleSheetStatusCode;
    }

    public void setSaleSheetStatusCode(String saleSheetStatusCode) {
        this.saleSheetStatusCode = saleSheetStatusCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getReleasedDateStart() {
        return releasedDateStart;
    }

    public void setReleasedDateStart(Date releasedDateStart) {
        this.releasedDateStart = releasedDateStart;
    }

    public Date getReleasedDateEnd() {
        return releasedDateEnd;
    }

    public void setReleasedDateEnd(Date releasedDateEnd) {
        this.releasedDateEnd = releasedDateEnd;
    }

    public Date getGmtCreatedStart() {
        return gmtCreatedStart;
    }

    public void setGmtCreatedStart(Date gmtCreatedStart) {
        this.gmtCreatedStart = gmtCreatedStart;
    }

    public Date getGmtCreatedEnd() {
        return gmtCreatedEnd;
    }

    public void setGmtCreatedEnd(Date gmtCreatedEnd) {
        this.gmtCreatedEnd = gmtCreatedEnd;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getProductSpecificationCode() {
        return productSpecificationCode;
    }

    public void setProductSpecificationCode(String productSpecificationCode) {
        this.productSpecificationCode = productSpecificationCode;
    }

    public Date getDeadlineBefore() {
        return deadlineBefore;
    }

    public void setDeadlineBefore(Date deadlineBefore) {
        this.deadlineBefore = deadlineBefore;
    }

    public Date getBidOpeningDateBefore() {
        return bidOpeningDateBefore;
    }

    public void setBidOpeningDateBefore(Date bidOpeningDateBefore) {
        this.bidOpeningDateBefore = bidOpeningDateBefore;
    }

    public Date getDeadlineAfter() {
        return deadlineAfter;
    }

    public void setDeadlineAfter(Date deadlineAfter) {
        this.deadlineAfter = deadlineAfter;
    }

    public Date getBidOpeningDateAfter() {
        return bidOpeningDateAfter;
    }

    public void setBidOpeningDateAfter(Date bidOpeningDateAfter) {
        this.bidOpeningDateAfter = bidOpeningDateAfter;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getQueryEnterpriseId() {
        return queryEnterpriseId;
    }

    public void setQueryEnterpriseId(Long queryEnterpriseId) {
        this.queryEnterpriseId = queryEnterpriseId;
    }
}
