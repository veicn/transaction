package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.util.Date;

/**
 * 合约单（订单）的VO
 * @author Yichen Zhao
 * date: 20180302
 */
public class ContractSheetVO extends BasePersistVO<ContractSheet> {

    /**
     * 合约单编号
     */
    private String serialNumber;

    /**
     * 销售单ID，此处做数据冗余，因为销售单ID可通过报价单带出
     */
    private Long saleSheetId;

    /**
     * 销售单编号，此处做数据冗余
     */
    private String saleSheetUuid;

    /**
     * 报价单ID
     */
    private Long biddingSheetId;

    /**
     * 报价单编号，此处做数据冗余
     */
    private String biddingSheetUuid;

    /**
     * 合约单的状态
     */
    private DictionaryVO contractSheetStatusVO;

    /**
     * 确认时间
     */
    private String confirmedDateAsString;

    /**
     * 完成时间
     */
    private String completedDateAsString;

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
     * 泊位信息ID
     */
    private String shipBerthCode;

    /**
     * 卖家信息ID
     */
    private Long salerInfoId;

    /**
     * 卖家ID，做数据冗余
     */
    private Long salerId;

    /**
     * 买家信息ID
     */
    private Long buyerInfoId;

    /**
     * 买家ID，做数据冗余
     */
    private Long buyerId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;

    /**
     * COQ Refinery文件地址
     */
    private String coqRefinery;

    /**
     * COQ Refinery文件名
     */
    private String coqRefineryName;

    /**
     * COQ Shore Tanks文件地址
     */
    private String coqShoreTanks;

    /**
     * COQ Shore Tanks文件名
     */
    private String coqShoreTanksName;

    /**
     * CIQ文件地址
     */
    private String ciq;

    /**
     * CIQ文件名
     */
    private String ciqName;

    /**
     * Loading Survey Report的文件地址
     */
    private String loadingSurveyReport;

    /**
     * Loading Survey Report的文件名
     */
    private String loadingSurveyReportName;

    public ContractSheetVO(ContractSheet contractSheet) {
        super(contractSheet);
    }

    @Override
    protected void convertToVO(ContractSheet domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        this.serialNumber = domain.getSerialNumber();
        this.saleSheetId = domain.getSaleSheetId();
        this.biddingSheetId = domain.getBiddingSheetId();

        String contractSheetStatusCode = domain.getContractSheetStatusCode();
        if (!StringUtil.isEmpty(contractSheetStatusCode)) {
            DictionaryVO contractSheetStatusVO = dictionaryService.getContractSheetStatus(contractSheetStatusCode);
            this.contractSheetStatusVO = contractSheetStatusVO;
        }

        Date confirmedDate = domain.getConfirmedDate();
        if (confirmedDate != null) {
            this.confirmedDateAsString = DateUtil.formatDate(confirmedDate);
        }

        Date completedDate = domain.getCompletedDate();
        if (completedDate != null) {
            this.completedDateAsString = DateUtil.formatDate(completedDate);
        }

        this.productInfoId = domain.getProductInfoId();
        this.pricingInfoId = domain.getPricingInfoId();
        this.transportInfoId = domain.getTransportInfoId();
        this.shipBerthCode = domain.getShipBerthCode();
        this.salerInfoId = domain.getSalerInfoId();
        this.salerId = domain.getSalerId();
        this.buyerInfoId = domain.getBuyerInfoId();
        this.buyerId = domain.getBuyerId();
        this.otherInfoId = domain.getOtherInfoId();

        this.coqRefinery = domain.getCoqRefinery();
        this.coqRefineryName = domain.getCoqRefineryName();
        this.coqShoreTanks = domain.getCoqShoreTanks();
        this.coqShoreTanksName = domain.getCoqShoreTanksName();
        this.ciq = domain.getCiq();
        this.ciqName = domain.getCiqName();
        this.loadingSurveyReport = domain.getLoadingSurveyReport();
        this.loadingSurveyReportName = domain.getLoadingSurveyReportName();

    }

    @Override
    protected ContractSheet convertToDomain() {
        ContractSheet contractSheet = new ContractSheet();

        contractSheet.setSerialNumber(this.serialNumber);
        contractSheet.setSaleSheetId(this.saleSheetId);
        contractSheet.setBiddingSheetId(this.biddingSheetId);

        if (this.contractSheetStatusVO != null) {
            contractSheet.setContractSheetStatusCode(this.contractSheetStatusVO.getCode());
        }

        if (!StringUtil.isEmpty(this.confirmedDateAsString)) {
            contractSheet.setConfirmedDate(DateUtil.getDate(confirmedDateAsString));
        }

        if (!StringUtil.isEmpty(this.completedDateAsString)) {
            contractSheet.setCompletedDate(DateUtil.getDate(completedDateAsString));
        }

        contractSheet.setProductInfoId(this.productInfoId);
        contractSheet.setPricingInfoId(this.pricingInfoId);
        contractSheet.setTransportInfoId(this.transportInfoId);
        contractSheet.setShipBerthCode(this.shipBerthCode);
        contractSheet.setSalerInfoId(this.salerInfoId);
        contractSheet.setBuyerInfoId(this.buyerInfoId);
        contractSheet.setOtherInfoId(this.otherInfoId);

        contractSheet.setCoqRefinery(this.coqRefinery);
        contractSheet.setCoqRefineryName(this.coqRefineryName);
        contractSheet.setCoqShoreTanks(this.coqShoreTanks);
        contractSheet.setCoqShoreTanksName(this.coqShoreTanksName);
        contractSheet.setCiq(this.ciq);
        contractSheet.setCiqName(this.ciqName);
        contractSheet.setLoadingSurveyReport(this.loadingSurveyReport);
        contractSheet.setLoadingSurveyReportName(this.loadingSurveyReportName);

        return contractSheet;
    }

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getSaleSheetId() {
        return saleSheetId;
    }

    public void setSaleSheetId(Long saleSheetId) {
        this.saleSheetId = saleSheetId;
    }

    public String getSaleSheetUuid() {
        return saleSheetUuid;
    }

    public void setSaleSheetUuid(String saleSheetUuid) {
        this.saleSheetUuid = saleSheetUuid;
    }

    public Long getBiddingSheetId() {
        return biddingSheetId;
    }

    public void setBiddingSheetId(Long biddingSheetId) {
        this.biddingSheetId = biddingSheetId;
    }

    public String getBiddingSheetUuid() {
        return biddingSheetUuid;
    }

    public void setBiddingSheetUuid(String biddingSheetUuid) {
        this.biddingSheetUuid = biddingSheetUuid;
    }

    public DictionaryVO getContractSheetStatusVO() {
        return contractSheetStatusVO;
    }

    public void setContractSheetStatusVO(DictionaryVO contractSheetStatusVO) {
        this.contractSheetStatusVO = contractSheetStatusVO;
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

    public Long getSalerInfoId() {
        return salerInfoId;
    }

    public void setSalerInfoId(Long salerInfoId) {
        this.salerInfoId = salerInfoId;
    }

    public Long getBuyerInfoId() {
        return buyerInfoId;
    }

    public void setBuyerInfoId(Long buyerInfoId) {
        this.buyerInfoId = buyerInfoId;
    }

    public Long getOtherInfoId() {
        return otherInfoId;
    }

    public void setOtherInfoId(Long otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public String getConfirmedDateAsString() {
        return confirmedDateAsString;
    }

    public void setConfirmedDateAsString(String confirmedDateAsString) {
        this.confirmedDateAsString = confirmedDateAsString;
    }

    public String getCompletedDateAsString() {
        return completedDateAsString;
    }

    public void setCompletedDateAsString(String completedDateAsString) {
        this.completedDateAsString = completedDateAsString;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getCoqRefinery() {
        return coqRefinery;
    }

    public void setCoqRefinery(String coqRefinery) {
        this.coqRefinery = coqRefinery;
    }

    public String getCoqRefineryName() {
        return coqRefineryName;
    }

    public void setCoqRefineryName(String coqRefineryName) {
        this.coqRefineryName = coqRefineryName;
    }

    public String getCoqShoreTanks() {
        return coqShoreTanks;
    }

    public void setCoqShoreTanks(String coqShoreTanks) {
        this.coqShoreTanks = coqShoreTanks;
    }

    public String getCoqShoreTanksName() {
        return coqShoreTanksName;
    }

    public void setCoqShoreTanksName(String coqShoreTanksName) {
        this.coqShoreTanksName = coqShoreTanksName;
    }

    public String getCiq() {
        return ciq;
    }

    public void setCiq(String ciq) {
        this.ciq = ciq;
    }

    public String getCiqName() {
        return ciqName;
    }

    public void setCiqName(String ciqName) {
        this.ciqName = ciqName;
    }

    public String getLoadingSurveyReport() {
        return loadingSurveyReport;
    }

    public void setLoadingSurveyReport(String loadingSurveyReport) {
        this.loadingSurveyReport = loadingSurveyReport;
    }

    public String getLoadingSurveyReportName() {
        return loadingSurveyReportName;
    }

    public void setLoadingSurveyReportName(String loadingSurveyReportName) {
        this.loadingSurveyReportName = loadingSurveyReportName;
    }
}
