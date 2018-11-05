package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售单的VO
 * @author Yichen Zhao
 * date: 20180302
 */
public class SaleSheetVO extends BasePersistVO<SaleSheet> {

    /**
     * 销售单编号
     */
    private String serialNumber;

    /**
     * 销售单状态（SaleSheetStatusEnum）
     */
    private DictionaryVO saleSheetStatusVO;

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
    private DictionaryVO saleTypeVO;

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
     * 卖家信息ID
     */
    private Long stakeholderInfoId;

    /**
     * 其它信息ID
     */
    private Long otherInfoId;

    
    public SaleSheetVO(){
    	
    }
    public SaleSheetVO(SaleSheet saleSheet) {
        super(saleSheet);
    }

    @Override
    protected void convertToVO(SaleSheet domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        this.serialNumber = domain.getSerialNumber();

        String saleSheetStatusCode = domain.getSaleSheetStatusCode();
        if (!StringUtil.isEmpty(saleSheetStatusCode)) {
            DictionaryVO saleSheetStatusVO = dictionaryService.getSaleSheetStatus(saleSheetStatusCode);
            this.saleSheetStatusVO = saleSheetStatusVO;
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

        String saleTypeCode = domain.getSaleTypeCode();
        if (!StringUtil.isEmpty(saleTypeCode)) {
            DictionaryVO saleTypeVO = dictionaryService.getSaleType(saleTypeCode);
            this.saleTypeVO = saleTypeVO;
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
    protected SaleSheet convertToDomain() {
        SaleSheet saleSheet = new SaleSheet();

        saleSheet.setSerialNumber(this.serialNumber);

        if (this.saleSheetStatusVO != null) {
            saleSheet.setSaleSheetStatusCode(this.saleSheetStatusVO.getCode());
        }

        if (!StringUtil.isEmpty(this.savedDateAsString)) {
            saleSheet.setSavedDate(DateUtil.getDate(this.savedDateAsString));
        }

        if (!StringUtil.isEmpty(this.releasedDateAsString)) {
            saleSheet.setReleasedDate(DateUtil.getDate(this.releasedDateAsString));
        }

        if (!StringUtil.isEmpty(this.cancelledDateAsString)) {
            saleSheet.setCancelledDate(DateUtil.getDate(this.cancelledDateAsString));
        }

        if (!StringUtil.isEmpty(this.completedDateAsString)) {
            saleSheet.setCompletedDate(DateUtil.getDate(this.completedDateAsString));
        }

        if (this.saleTypeVO != null) {
            saleSheet.setSaleTypeCode(this.saleTypeVO.getCode());
        }

        if (this.productSourceVO != null) {
            saleSheet.setProductSourceCode(this.productSourceVO.getCode());
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
            saleSheet.setSpecifiedEnterpriseIdList(enterpriseIdList);
        }

        saleSheet.setEnterpriseId(this.enterpriseId);
        saleSheet.setProductInfoId(this.productInfoId);
        saleSheet.setPricingInfoId(this.pricingInfoId);
        saleSheet.setTransportInfoId(this.transportInfoId);
        saleSheet.setShipBerthCode(this.shipBerthCode);
        saleSheet.setStakeholderInfoId(this.stakeholderInfoId);
        saleSheet.setOtherInfoId(this.otherInfoId);

        return saleSheet;
    }

    /** getters and setters */
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DictionaryVO getSaleSheetStatusVO() {
        return saleSheetStatusVO;
    }

    public void setSaleSheetStatusVO(DictionaryVO saleSheetStatusVO) {
        this.saleSheetStatusVO = saleSheetStatusVO;
    }

    public DictionaryVO getSaleTypeVO() {
        return saleTypeVO;
    }

    public void setSaleTypeVO(DictionaryVO saleTypeVO) {
        this.saleTypeVO = saleTypeVO;
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
