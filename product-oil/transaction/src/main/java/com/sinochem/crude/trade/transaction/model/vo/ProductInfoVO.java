package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 商品信息VO
 * @author Yichen Zhao
 * date: 20180302
 */
public class ProductInfoVO extends BasePersistVO<ProductInfo> {

    /**
     * 销售类型（SaleTypeEnum）
     */
    private DictionaryVO saleTypeVO;

    /**
     * 采购类型（DemandTypeEnum）
     */
    private DictionaryVO demandTypeVO;

    /**
     * 投标截止日期
     */
    private String deadlineAsString;

    /**
     * 公布中标日期
     */
    private String bidOpeningDateAsString;

    /**
     * 商品分类（ProductCategoryEnum）
     */
    private DictionaryVO productCategoryVO;

    /**
     * 商品品种（ProductVarietyEnum）
     */
    private DictionaryVO productVarietyVO;

    /**
     * 规格（ProductSpecificationEnum）
     */
    private ProductSpecificationVO productSpecificationVO;

    /**
     * 出口类型（ExportTypeEnum）
     */
    private DictionaryVO exportTypeVO;

    /**
     * 贸易条款（TradeTermEnum）
     */
    private DictionaryVO tradeTermVO;

    /**
     * 数量
     */
    private String quantityAsString;

    /**
     * 数量单位(UintEnum)
     */
    private DictionaryVO quantityUnitVO;

    /**
     * 溢短装
     */
    private String tolerance;

    /**
     * 质量标准文件地址
     */
    private String qualityStandard;

    /**
     * 质量标准文件名称
     */
    private String qualityStandardName;

    /**
     * 是否预期
     */
    private Boolean overdue;

    public ProductInfoVO(){
    	
    }
    public ProductInfoVO(ProductInfo productInfo) {
        super(productInfo);
    }

    @Override
    protected void convertToVO(ProductInfo domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        String saleTypeCode = domain.getSaleTypeCode();
        if (!StringUtil.isEmpty(saleTypeCode)) {
            DictionaryVO saleTypeVO = dictionaryService.getSaleType(saleTypeCode);
            this.saleTypeVO = saleTypeVO;
        }

        String demandTypeCode = domain.getDemandTypeCode();
        if (!StringUtil.isEmpty(demandTypeCode)) {
            DictionaryVO demandTypeVO = dictionaryService.getSaleType(demandTypeCode);
            this.demandTypeVO = demandTypeVO;
        }

        Date deadline = domain.getDeadline();
        if (deadline != null) {
            this.deadlineAsString = DateUtil.formatDateTime(deadline);
        }

        Date bidOpeningDate = domain.getBidOpeningDate();
        if (bidOpeningDate != null) {
            this.bidOpeningDateAsString = DateUtil.formatDateTime(bidOpeningDate);
        }

        String productCategoryCode = domain.getProductCategoryCode();
        if (!StringUtil.isEmpty(productCategoryCode)) {
            DictionaryVO productCategoryVO = dictionaryService.getProductCategory(productCategoryCode);
            this.productCategoryVO = productCategoryVO;
        }

        String productVarietyCode = domain.getProductVarietyCode();
        if (!StringUtil.isEmpty(productVarietyCode)) {
            DictionaryVO productVarietyVO = dictionaryService.getProductVariety(productVarietyCode);
            this.productVarietyVO = productVarietyVO;
        }

        String productSpecificationCode = domain.getProductSpecificationCode();
        if (!StringUtil.isEmpty(productSpecificationCode)) {
            ProductSpecificationVO productSpecificationVO = dictionaryService.getProductSpecification(productSpecificationCode);
            this.productSpecificationVO = productSpecificationVO;
        }

        String exportTypeCode = domain.getExportTypeCode();
        if (!StringUtil.isEmpty(exportTypeCode)) {
            DictionaryVO exportTypeVO = dictionaryService.getExportType(exportTypeCode);
            this.exportTypeVO = exportTypeVO;
        }

        String tradeTermCode = domain.getTradeTermCode();
        if (!StringUtil.isEmpty(tradeTermCode)) {
            DictionaryVO tradeTermVO = dictionaryService.getTradeTerm(tradeTermCode);
            this.tradeTermVO = tradeTermVO;
        }

        String quantityUnitCode = domain.getQuantityUnitCode();
        if (!StringUtil.isEmpty(quantityUnitCode)) {
            DictionaryVO quantityUnitVO = dictionaryService.getQuantityUnit(quantityUnitCode);
            this.quantityUnitVO = quantityUnitVO;
        }
        BigDecimal quantity = domain.getQuantity();
        if (quantity != null) {
            DecimalFormat df = new DecimalFormat("#.##");
            this.quantityAsString = new BigDecimal(df.format(quantity)).toString();
            //this.quantityAsString = quantity.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        if(domain.getDeadline() != null
                && com.sinochem.it.b2b.common.utils.DateUtil.formatDate(new Date()).compareTo(com.sinochem.it.b2b.common.utils.DateUtil.formatDate(domain.getDeadline())) > 0){
            this.overdue = true;
        }else{
            this.overdue = false;
        }

        this.tolerance = domain.getTolerance();
        this.qualityStandard = domain.getQualityStandard();
        this.qualityStandardName = domain.getQualityStandardName();
    }

    @Override
    protected ProductInfo convertToDomain() {
        ProductInfo productInfo = new ProductInfo();

        if (this.saleTypeVO != null) {
            productInfo.setSaleTypeCode(this.saleTypeVO.getCode());
        }
        if (this.demandTypeVO != null) {
            productInfo.setDemandTypeCode(this.demandTypeVO.getCode());
        }
        if (this.quantityUnitVO != null) {
            productInfo.setQuantityUnitCode(this.quantityUnitVO.getCode());
        }

        if (!StringUtil.isEmpty(this.deadlineAsString)) {
            productInfo.setDeadline(DateUtil.getDateTime(this.deadlineAsString));
        }

        if (!StringUtil.isEmpty(this.bidOpeningDateAsString)) {
            productInfo.setBidOpeningDate(DateUtil.getDateTime(this.bidOpeningDateAsString));
        }

        if (this.productCategoryVO != null) {
            productInfo.setProductCategoryCode(this.productCategoryVO.getCode());
        }

        if (this.productVarietyVO != null) {
            productInfo.setProductVarietyCode(this.productVarietyVO.getCode());
        }

        if (this.productSpecificationVO != null) {
            productInfo.setProductSpecificationCode(this.productSpecificationVO.getCode());
        }

        if (this.exportTypeVO != null) {
            productInfo.setExportTypeCode(this.exportTypeVO.getCode());
        }

        if (this.tradeTermVO != null) {
            productInfo.setTradeTermCode(this.tradeTermVO.getCode());
        }

        if (!StringUtil.isEmpty(this.quantityAsString)) {
            productInfo.setQuantity(new BigDecimal(this.quantityAsString));
        }

        productInfo.setTolerance(this.tolerance);
        productInfo.setQualityStandard(this.qualityStandard);
        productInfo.setQualityStandardName(this.qualityStandardName);

        return productInfo;
    }

    /** getters and setters */
    public DictionaryVO getSaleTypeVO() {
        return saleTypeVO;
    }

    public void setSaleTypeVO(DictionaryVO saleTypeVO) {
        this.saleTypeVO = saleTypeVO;
    }

    public String getDeadlineAsString() {
        return deadlineAsString;
    }

    public void setDeadlineAsString(String deadlineAsString) {
        this.deadlineAsString = deadlineAsString;
    }

    public String getBidOpeningDateAsString() {
        return bidOpeningDateAsString;
    }

    public void setBidOpeningDateAsString(String bidOpeningDateAsString) {
        this.bidOpeningDateAsString = bidOpeningDateAsString;
    }

    public DictionaryVO getProductCategoryVO() {
        return productCategoryVO;
    }

    public void setProductCategoryVO(DictionaryVO productCategoryVO) {
        this.productCategoryVO = productCategoryVO;
    }

    public DictionaryVO getProductVarietyVO() {
        return productVarietyVO;
    }

    public void setProductVarietyVO(DictionaryVO productVarietyVO) {
        this.productVarietyVO = productVarietyVO;
    }

    public ProductSpecificationVO getProductSpecificationVO() {
        return productSpecificationVO;
    }

    public void setProductSpecificationVO(ProductSpecificationVO productSpecificationVO) {
        this.productSpecificationVO = productSpecificationVO;
    }

    public DictionaryVO getExportTypeVO() {
        return exportTypeVO;
    }

    public void setExportTypeVO(DictionaryVO exportTypeVO) {
        this.exportTypeVO = exportTypeVO;
    }

    public DictionaryVO getTradeTermVO() {
        return tradeTermVO;
    }

    public void setTradeTermVO(DictionaryVO tradeTermVO) {
        this.tradeTermVO = tradeTermVO;
    }

    public String getQuantityAsString() {
        return quantityAsString;
    }

    public void setQuantityAsString(String quantityAsString) {
        this.quantityAsString = quantityAsString;
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

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    public DictionaryVO getDemandTypeVO() {
        return demandTypeVO;
    }

    public void setDemandTypeVO(DictionaryVO demandTypeVO) {
        this.demandTypeVO = demandTypeVO;
    }

    public DictionaryVO getQuantityUnitVO() {
        return quantityUnitVO;
    }

    public void setQuantityUnitVO(DictionaryVO quantityUnitVO) {
        this.quantityUnitVO = quantityUnitVO;
    }
}
