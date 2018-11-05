package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购单的VO
 * @author Yichen Zhao
 * date: 20180302
 */
public class DemandSheetVO extends BasePersistVO<DemandSheet> {

    /**
     * 销售单编号
     */
    private String serialNumber;

    /**
     * 采购单状态（demandSheetStatusEnum）
     */
    private DictionaryVO demandSheetStatusVO;

    /**
     * 保存时间
     */
    private String savedDateAsString;

    /**
     * 发布时间
     */
    private String releasedDateAsString;

    /**
     * 作废时间
     */
    private String cancelledDateAsString;

    /**
     * 成交时间
     */
    private String completedDateAsString;

    /**
     * 销售类型（SaleTypeEnum）
     */
    private DictionaryVO demandTypeVO;

    /**
     * 商品来源（ProductSourceEnum，只有当卖家为中化新的时候才会有此选项）
     */
    private DictionaryVO productSourceVO;

    /**
     * 若为询价（定向），则需指定定向的企业ID，以逗号分隔
     */
    private String specifiedEnterpriseIdList;

    /**
     * 定向的企业VO列表
     */
    private List<EnterpriseVo> specifiedEnterpriseVOList;

    /**
     * 发布销售需求单的企业ID
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
     * 卖家泊位信息代码
     */
    private String shipBerthCode;

    /**
     * 买家信息ID
     */
    private Long stakeholderInfoId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;


    public DemandSheetVO(){

    }
    public DemandSheetVO(DemandSheet demandSheet) {
        super(demandSheet);
    }

    @Override
    protected void convertToVO(DemandSheet domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        this.serialNumber = domain.getSerialNumber();

        String demandSheetStatusCode = domain.getDemandSheetStatusCode();
        if (!StringUtil.isEmpty(demandSheetStatusCode)) {
            DictionaryVO demandSheetStatusVO = dictionaryService.getDemandSheetStatus(demandSheetStatusCode);
            this.demandSheetStatusVO = demandSheetStatusVO;
        }

        Date savedDate = domain.getSavedDate();
        if (savedDate != null) {
            this.savedDateAsString = DateUtil.formatDate(savedDate);
        }

        Date releasedDate = domain.getReleasedDate();
        if (releasedDate != null) {
            this.releasedDateAsString = DateUtil.formatDate(releasedDate);
        }

        Date cancelledDate = domain.getCancelledDate();
        if (cancelledDate != null) {
            this.cancelledDateAsString = DateUtil.formatDate(cancelledDate);
        }

        Date completedDate = domain.getCompletedDate();
        if (completedDate != null) {
            this.completedDateAsString = DateUtil.formatDate(completedDate);
        }

        String demandTypeCode = domain.getDemandTypeCode();
        if (!StringUtil.isEmpty(demandTypeCode)) {
            DictionaryVO demandTypeVO = dictionaryService.getDemandType(demandTypeCode);
            this.demandTypeVO = demandTypeVO;
        }

        String productSourceCode = domain.getProductSourceCode();
        if (!StringUtil.isEmpty(productSourceCode)) {
            DictionaryVO productSourceVO = dictionaryService.getProductSource(productSourceCode);
            this.productSourceVO = productSourceVO;
        }

        String specifiedEnterpriseIdList = domain.getSpecifiedEnterpriseIdList();
        if (!StringUtil.isEmpty(specifiedEnterpriseIdList)) {
            EnterpriseService enterpriseService = ContextLoader.getCurrentWebApplicationContext().getBean(EnterpriseService.class);

            this.specifiedEnterpriseIdList = specifiedEnterpriseIdList;

            String[] specifiedEnterpriseIdArray = specifiedEnterpriseIdList.split(",");
            List<EnterpriseVo> specifiedEnterpriseVOList = new ArrayList<>(specifiedEnterpriseIdArray.length);
            for (String specifiedEnterpriseId : specifiedEnterpriseIdArray) {
                EnterpriseVo enterpriseVO = enterpriseService.getByMemberId(Long.parseLong(specifiedEnterpriseId));
                specifiedEnterpriseVOList.add(enterpriseVO);
            }

            this.specifiedEnterpriseVOList = specifiedEnterpriseVOList;
        }

        this.enterpriseId = domain.getEnterpriseId();
        this.productInfoId = domain.getProductInfoId();
        this.pricingInfoId = domain.getPricingInfoId();
        this.transportInfoId = domain.getTransportInfoId();
        this.shipBerthCode = domain.getShipBerthCode();
        this.stakeholderInfoId = domain.getStakeholderInfoId();
        this.otherInfoId = domain.getOtherInfoId();
    }

    @Override
    protected DemandSheet convertToDomain() {
        DemandSheet demandSheet = new DemandSheet();

        demandSheet.setSerialNumber(this.serialNumber);

        if (this.demandSheetStatusVO != null) {
            demandSheet.setDemandSheetStatusCode(this.demandSheetStatusVO.getCode());
        }

        if (!StringUtil.isEmpty(this.savedDateAsString)) {
            demandSheet.setSavedDate(DateUtil.getDate(this.savedDateAsString));
        }

        if (!StringUtil.isEmpty(this.releasedDateAsString)) {
            demandSheet.setReleasedDate(DateUtil.getDate(this.releasedDateAsString));
        }

        if (!StringUtil.isEmpty(this.cancelledDateAsString)) {
            demandSheet.setCancelledDate(DateUtil.getDate(this.cancelledDateAsString));
        }

        if (!StringUtil.isEmpty(this.completedDateAsString)) {
            demandSheet.setCompletedDate(DateUtil.getDate(this.completedDateAsString));
        }

        if (this.demandTypeVO != null) {
            demandSheet.setDemandTypeCode(this.demandTypeVO.getCode());
        }

        if (this.productSourceVO != null) {
            demandSheet.setProductSourceCode(this.productSourceVO.getCode());
        }
        if(!StringUtil.isEmpty(this.specifiedEnterpriseIdList)){
            String[] split = this.specifiedEnterpriseIdList.split(",");
            List<String> list = new ArrayList<>();
            for(int i=0;i<split.length;i++){
                if(list.indexOf(split[i]) == -1){
                    list.add(split[i]);
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            if(list.size() > 0){
                for(int i = 0;i<list.size()-1;i++){
                    stringBuffer.append(list.get(i));
                    stringBuffer.append(",");
                }
                stringBuffer.append(list.get(list.size()-1));
            }
            String enterpriseIdList = stringBuffer.toString();
            demandSheet.setSpecifiedEnterpriseIdList(enterpriseIdList);
        }

        demandSheet.setEnterpriseId(this.enterpriseId);
        demandSheet.setProductInfoId(this.productInfoId);
        demandSheet.setPricingInfoId(this.pricingInfoId);
        demandSheet.setTransportInfoId(this.transportInfoId);
        demandSheet.setShipBerthCode(this.shipBerthCode);
        demandSheet.setStakeholderInfoId(this.stakeholderInfoId);
        demandSheet.setOtherInfoId(this.otherInfoId);

        return demandSheet;
    }

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DictionaryVO getDemandSheetStatusVO() {
        return demandSheetStatusVO;
    }

    public void setDemandSheetStatusVO(DictionaryVO demandSheetStatusVO) {
        this.demandSheetStatusVO = demandSheetStatusVO;
    }

    public DictionaryVO getDemandTypeVO() {
        return demandTypeVO;
    }

    public void setDemandTypeVO(DictionaryVO demandTypeVO) {
        this.demandTypeVO = demandTypeVO;
    }

    public String getSpecifiedEnterpriseIdList() {
        return specifiedEnterpriseIdList;
    }

    public void setSpecifiedEnterpriseIdList(String specifiedEnterpriseIdList) {
        this.specifiedEnterpriseIdList = specifiedEnterpriseIdList;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Long getStakeholderInfoId() {
        return stakeholderInfoId;
    }

    public void setStakeholderInfoId(Long stakeholderInfoId) {
        this.stakeholderInfoId = stakeholderInfoId;
    }

    public Long getOtherInfoId() {
        return otherInfoId;
    }

    public void setOtherInfoId(Long otherInfoId) {
        this.otherInfoId = otherInfoId;
    }

    public String getSavedDateAsString() {
        return savedDateAsString;
    }

    public void setSavedDateAsString(String savedDateAsString) {
        this.savedDateAsString = savedDateAsString;
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

    public String getCompletedDateAsString() {
        return completedDateAsString;
    }

    public void setCompletedDateAsString(String completedDateAsString) {
        this.completedDateAsString = completedDateAsString;
    }

    public DictionaryVO getProductSourceVO() {
        return productSourceVO;
    }

    public void setProductSourceVO(DictionaryVO productSourceVO) {
        this.productSourceVO = productSourceVO;
    }

    public List<EnterpriseVo> getSpecifiedEnterpriseVOList() {
        return specifiedEnterpriseVOList;
    }

    public void setSpecifiedEnterpriseVOList(List<EnterpriseVo> specifiedEnterpriseVOList) {
        this.specifiedEnterpriseVOList = specifiedEnterpriseVOList;
    }
}
