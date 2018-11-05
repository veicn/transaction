package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.model.query.DemandSheetQuery;
import com.sinochem.crude.trade.transaction.model.query.SaleSheetQuery;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.io.Serializable;

public class DemandSheetQueryVO implements Serializable {
    /**
     * 采购类型
     */
    private String demandTypeCode;

    /**
     * 商品分类
     */
    private String productCategoryCode;
    
    /**
     * 商品规格
     */
    private String productSpecificationCode;
    

    /**
     * 采购单状态
     */
    private String demandSheetStatusCode;

    /**
     * 需求单据号
     */
    private String serialNumber;

    /**
     * 发布时间-开始
     */
    private String releasedDateStartAsString;

    /**
     * 发布时间-结束
     */
    private String releasedDateEndAsString;

    /**
     * 创建时间-开始
     */
    private String gmtCreatedStartAsString;

    /**
     * 创建时间-结束
     */
    private String gmtCreatedEndAsString;

    /**
     * 排序规则
     */
    private String orderByColumn;

    /**
     * 排序类型（升序或降序）
     */
    private String orderType;

    /**
     * 发布企业id
     */
    private Long enterpriseId;

    public DemandSheetQuery getDemandSheetQuery() {

        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        DemandSheetQuery demandSheetQuery = new DemandSheetQuery();

        if(!StringUtil.isEmpty(this.getDemandTypeCode())){

            demandSheetQuery.setDemandTypeCode(this.getDemandTypeCode());
        }
        if(!StringUtil.isEmpty(this.getProductCategoryCode())){

            demandSheetQuery.setProductCategoryCode(this.getProductCategoryCode());
        }
        if(!StringUtil.isEmpty(this.getProductSpecificationCode())){

            demandSheetQuery.setProductSpecificationCode(this.getProductSpecificationCode());
        }
        if(!StringUtil.isEmpty(this.getSerialNumber())){

            demandSheetQuery.setSerialNumber(this.getSerialNumber());
        }

        if(!StringUtil.isEmpty(this.getDemandSheetStatusCode())){
            demandSheetQuery.setDemandSheetStatusCode(this.getDemandSheetStatusCode());
        }
        String releasedDateStartAsString = this.getReleasedDateStartAsString();
        if(!StringUtil.isEmpty(releasedDateStartAsString)){
            demandSheetQuery.setReleasedDateStart(DateUtil.getDate(releasedDateStartAsString));
        }

        String releasedDateEndAsString = this.getReleasedDateEndAsString();
        if(!StringUtil.isEmpty(releasedDateEndAsString)){
            demandSheetQuery.setReleasedDateEnd(DateUtil.getDate(releasedDateEndAsString));
        }

        String gmtCreatedStartAsString = this.getGmtCreatedStartAsString();
        if(!StringUtil.isEmpty(gmtCreatedStartAsString)){
            demandSheetQuery.setGmtCreatedStart(DateUtil.getDate(gmtCreatedStartAsString));
        }

        String gmtCreatedEndAsString = this.getGmtCreatedEndAsString();
        if(!StringUtil.isEmpty(gmtCreatedEndAsString)){
            demandSheetQuery.setGmtCreatedEnd(DateUtil.getDate(gmtCreatedEndAsString));
        }
        if(!StringUtil.isEmpty(this.getOrderByColumn())){

            demandSheetQuery.setOrderByColumn(dictionaryService.getDemandSheetQueryOrder(this.getOrderByColumn()));
        }
        if(!StringUtil.isEmpty(this.getOrderType())){

            demandSheetQuery.setOrderType(this.getOrderType());
        }
        if(this.getEnterpriseId() != null){

            demandSheetQuery.setEnterpriseId(this.getEnterpriseId());
        }

        return demandSheetQuery;
    }

    /** getters and setters */

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }
    
    public String getProductSpecificationCode() {
		return productSpecificationCode;
	}

	public void setProductSpecificationCode(String productSpecificationCode) {
		this.productSpecificationCode = productSpecificationCode;
	}

    public String getDemandTypeCode() {
        return demandTypeCode;
    }

    public void setDemandTypeCode(String demandTypeCode) {
        this.demandTypeCode = demandTypeCode;
    }

    public String getDemandSheetStatusCode() {
        return demandSheetStatusCode;
    }

    public void setDemandSheetStatusCode(String demandSheetStatusCode) {
        this.demandSheetStatusCode = demandSheetStatusCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getReleasedDateStartAsString() {
        return releasedDateStartAsString;
    }

    public void setReleasedDateStartAsString(String releasedDateStartAsString) {
        this.releasedDateStartAsString = releasedDateStartAsString;
    }

    public String getReleasedDateEndAsString() {
        return releasedDateEndAsString;
    }

    public void setReleasedDateEndAsString(String releasedDateEndAsString) {
        this.releasedDateEndAsString = releasedDateEndAsString;
    }

    public String getGmtCreatedStartAsString() {
        return gmtCreatedStartAsString;
    }

    public void setGmtCreatedStartAsString(String gmtCreatedStartAsString) {
        this.gmtCreatedStartAsString = gmtCreatedStartAsString;
    }

    public String getGmtCreatedEndAsString() {
        return gmtCreatedEndAsString;
    }

    public void setGmtCreatedEndAsString(String gmtCreatedEndAsString) {
        this.gmtCreatedEndAsString = gmtCreatedEndAsString;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
