package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.util.Date;

/**
 * 报价单的VO
 */
public class BiddingSheetVO extends BasePersistVO<BiddingSheet> {

    /**
     * 报价单编号
     */
    private String serialNumber;

    /**
     * 报价单状态
     */
    private DictionaryVO biddingSheetStatusVO;

    /**
     * 发布时间
     */
    private String releasedDateAsString;

    /**
     * 作废时间
     */
    private String cancelledDateAsString;

    /**
     * 中标时间
     */
    private String wonDateAsString;

    /**
     * 未中标时间
     */
    private String lostDateAsString;

    /**
     * 关联的销售需求单的ID
     */
    private Long saleSheetId;

    /**
     * 关联的销售需求单的UUID
     */
    private String saleSheetUuid;

    /**
     * 关联的采购需求单的ID
     */
    private Long demandSheetId;

    /**
     * 关联的采购需求单的UUID
     */
    private String demandSheetUuid;

    /**
     * 报价标识
     */
    private String biddingFlag;

    /**
     * 发布报价单的企业ID
     */
    private Long enterpriseId;

    /**
     * 基本信息ID
     */
    private Long productInfoId;

    /**
     * 价格信息ID
     */
    private Long pricingInfoId;

    /**
     * 运输信息ID
     */
    private Long transportInfoId;

    /**
     * 买家泊位信息代码
     */
    private String shipBerthCode;

    /**
     * 买家信息ID
     */
    private Long buyerId;

    /**
     * 卖家信息ID
     */
    private Long salerId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;

    public BiddingSheetVO(BiddingSheet biddingSheet) {
        super(biddingSheet);
    }

    public BiddingSheetVO() {

    }

    @Override
    protected void convertToVO(BiddingSheet domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        this.serialNumber = domain.getSerialNumber();

        String biddingSheetStatusCode = domain.getBiddingSheetStatusCode();
        if (!StringUtil.isEmpty(biddingSheetStatusCode)) {
            DictionaryVO biddingSheetStatusVO = dictionaryService.getBiddingSheetStatus(biddingSheetStatusCode);
            this.biddingSheetStatusVO = biddingSheetStatusVO;
        }

        String biddingFlag = domain.getBiddingFlag();
        if (!StringUtil.isEmpty(biddingFlag)) {
            this.biddingFlag = biddingFlag;
        }

        Date releasedDate = domain.getReleasedDate();
        if (releasedDate != null) {
            this.releasedDateAsString = DateUtil.formatDate(releasedDate);
        }

        Date cancelledDate = domain.getCancelledDate();
        if (cancelledDate != null) {
            this.cancelledDateAsString = DateUtil.formatDate(cancelledDate);
        }

        Date wonDate = domain.getWonDate();
        if (wonDate != null) {
            this.wonDateAsString = DateUtil.formatDate(wonDate);
        }

        Date lostDate = domain.getLostDate();
        if (lostDate != null) {
            this.lostDateAsString = DateUtil.formatDate(lostDate);
        }

        this.enterpriseId = domain.getEnterpriseId();
        this.saleSheetId = domain.getSaleSheetId();
        this.saleSheetUuid = domain.getSaleSheetUuid();
        this.demandSheetId = domain.getDemandSheetId();
        this.demandSheetUuid = domain.getDemandSheetUuid();
        this.productInfoId = domain.getProductInfoId();
        this.pricingInfoId = domain.getPricingInfoId();
        this.transportInfoId = domain.getTransportInfoId();
        this.shipBerthCode = domain.getShipBerthCode();
        this.buyerId = domain.getStakeholderInfoId();
        this.salerId = domain.getSalerInfoId();
        this.otherInfoId = domain.getOtherInfoId();
    }

    @Override
    protected BiddingSheet convertToDomain() {
        BiddingSheet biddingSheet = new BiddingSheet();

        biddingSheet.setSerialNumber(this.serialNumber);

        if (this.biddingSheetStatusVO != null) {
            biddingSheet.setBiddingSheetStatusCode(this.biddingSheetStatusVO.getCode());
        }

        if (!StringUtil.isEmpty(this.releasedDateAsString)) {
            biddingSheet.setReleasedDate(DateUtil.getDate(this.releasedDateAsString));
        }

        if (!StringUtil.isEmpty(this.cancelledDateAsString)) {
            biddingSheet.setCancelledDate(DateUtil.getDate(this.cancelledDateAsString));
        }

        if (!StringUtil.isEmpty(this.wonDateAsString)) {
            biddingSheet.setWonDate(DateUtil.getDate(this.wonDateAsString));
        }

        if (!StringUtil.isEmpty(this.lostDateAsString)) {
            biddingSheet.setLostDate(DateUtil.getDate(this.lostDateAsString));
        }
        if (!StringUtil.isEmpty(this.biddingFlag)) {
            biddingSheet.setBiddingFlag(this.biddingFlag);
        }

        biddingSheet.setEnterpriseId(this.getEnterpriseId());
        biddingSheet.setSaleSheetId(this.getSaleSheetId());
        biddingSheet.setSaleSheetUuid(this.getSaleSheetUuid());
        biddingSheet.setDemandSheetId(this.getDemandSheetId());
        biddingSheet.setDemandSheetUuid(this.getDemandSheetUuid());
        biddingSheet.setProductInfoId(this.getProductInfoId());
        biddingSheet.setPricingInfoId(this.getPricingInfoId());
        biddingSheet.setTransportInfoId(this.getTransportInfoId());
        biddingSheet.setShipBerthCode(this.getShipBerthCode());
        biddingSheet.setSalerInfoId(this.getSalerId());
        biddingSheet.setStakeholderInfoId(this.getBuyerId());
        biddingSheet.setOtherInfoId(this.getOtherInfoId());

        return biddingSheet;
    }

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DictionaryVO getBiddingSheetStatusVO() {
        return biddingSheetStatusVO;
    }

    public void setBiddingSheetStatusVO(DictionaryVO biddingSheetStatusVO) {
        this.biddingSheetStatusVO = biddingSheetStatusVO;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
    }

    public void setSaleSheetId(Long saleSheetId) {
        this.saleSheetId = saleSheetId;
    }

    public Long getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Long productInfoId) {
        this.productInfoId = productInfoId;
    }

    public Long getPricingInfoId() {
        return pricingInfoId;
    }

    public void setPricingInfoId(Long pricingInfoId) {
        this.pricingInfoId = pricingInfoId;
    }

    public Long getTransportInfoId() {
        return transportInfoId;
    }

    public void setTransportInfoId(Long transportInfoId) {
        this.transportInfoId = transportInfoId;
    }

    public String getShipBerthCode() {
        return shipBerthCode;
    }

    public void setShipBerthCode(String shipBerthCode) {
        this.shipBerthCode = shipBerthCode;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public Long getOtherInfoId() {
        return otherInfoId;
    }

    public void setOtherInfoId(Long otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public String getReleasedDateAsString() {
        return releasedDateAsString;
    }

    public void setReleasedDateAsString(String releasedDateAsString) {
        this.releasedDateAsString = releasedDateAsString;
    }

    public String getCancelledDateAsString() {
        return cancelledDateAsString;
    }

    public void setCancelledDateAsString(String cancelledDateAsString) {
        this.cancelledDateAsString = cancelledDateAsString;
    }

    public String getWonDateAsString() {
        return wonDateAsString;
    }

    public void setWonDateAsString(String wonDateAsString) {
        this.wonDateAsString = wonDateAsString;
    }

    public String getLostDateAsString() {
        return lostDateAsString;
    }

    public void setLostDateAsString(String lostDateAsString) {
        this.lostDateAsString = lostDateAsString;
    }

    public String getSaleSheetUuid() {
        return saleSheetUuid;
    }

    public void setSaleSheetUuid(String saleSheetUuid) {
        this.saleSheetUuid = saleSheetUuid;
    }

    public Long getDemandSheetId() {
        return demandSheetId;
    }

    public void setDemandSheetId(Long demandSheetId) {
        this.demandSheetId = demandSheetId;
    }

    public String getDemandSheetUuid() {
        return demandSheetUuid;
    }

    public void setDemandSheetUuid(String demandSheetUuid) {
        this.demandSheetUuid = demandSheetUuid;
    }

    public String getBiddingFlag() {
        return biddingFlag;
    }

    public void setBiddingFlag(String biddingFlag) {
        this.biddingFlag = biddingFlag;
    }
}
