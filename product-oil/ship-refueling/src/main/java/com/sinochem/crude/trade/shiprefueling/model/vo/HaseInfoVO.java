package com.sinochem.crude.trade.shiprefueling.model.vo;


import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class HaseInfoVO extends HaseInfo {

    private static final long serialVersionUID = 1L;
    @JsonUnwrapped
    private SimplePageInfo pageInfo;//分页信息

    private ShipEnterpriseVo shipEnterpriseVo;

    public ShipEnterpriseVo getShipEnterpriseVo() {
        return shipEnterpriseVo;
    }

    public void setShipEnterpriseVo(ShipEnterpriseVo shipEnterpriseVo) {
        this.shipEnterpriseVo = shipEnterpriseVo;
    }

    private List<ChmentsVO> chments;//附件

    private List<String> urls;//附件url地址
    
    private String issueTimeStart;//发布起始时间
    private String issueTimeEnd;//发布结束时间

    private List<String> infoTypeList;

    /**
     *  是否个人中心
     *  0:否
     *  1:是
     * */
    private String personal;
  /*
   供油凭证
   */
    private String  oilVoucherUrl;
    /*
    合同路径
     */

    public String getOilVoucherUrl() {
        return oilVoucherUrl;
    }

    public void setOilVoucherUrl(String oilVoucherUrl) {
        this.oilVoucherUrl = oilVoucherUrl;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    private String  agreementUrl;
    private String frontCreateDate;
    private String frontUpdateDate;
    private String frontReleasedDate;

    public String getFrontCreateDate() {
        return frontCreateDate;
    }

    public void setFrontCreateDate(String frontCreateDate) {
        this.frontCreateDate = frontCreateDate;
    }

    public String getFrontUpdateDate() {
        return frontUpdateDate;
    }

    public void setFrontUpdateDate(String frontUpdateDate) {
        this.frontUpdateDate = frontUpdateDate;
    }

    public String getFrontReleasedDate() {
        return frontReleasedDate;
    }

    public void setFrontReleasedDate(String frontReleasedDate) {
        this.frontReleasedDate = frontReleasedDate;
    }

    /*
        发票路径
         */
    private String  invoiceUrl;


    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }




    public void domainToVo(HaseInfo domin) {
        if(domin == null){
            return;
        }

        if(null!=domin.getInfoType()&&domin.getInfoType().equals("9")){

            for (OilCrudeTypeOfShippingEnums value : OilCrudeTypeOfShippingEnums.values()) {
                if (value.getCode().equals(domin.getDeliveryWay())) {
                    this.setDeliveryWay(value.getZhName());
                    break;
                }
            }

        }else if(null!=domin.getInfoType()&&!domin.getInfoType().equals("9")){
            for (TypeOfShippingEnums value : TypeOfShippingEnums.values()) {
                if (value.getCode().equals(domin.getDeliveryWay())) {
                    this.setDeliveryWay(value.getZhName());
                    break;
                }
            }

        }



        for (PurchaseTypeEnums value : PurchaseTypeEnums.values()) {
            if (value.getCode().equals(domin.getInfoType())) {
                this.setInfoType(value.getZhName());
                break;
            }
        }
        for (DeliveryModeEnums value : DeliveryModeEnums.values()) {
            if (value.getCode().equals(domin.getTransportWay())) {
                this.setTransportWay(value.getZhName());
                break;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(domin.getCreateDate() != null){

            this.frontCreateDate = sdf.format(domin.getCreateDate());
        }
        if(domin.getUpdateDate() != null){

            this.frontUpdateDate = sdf.format(domin.getUpdateDate());
        }
        if(domin.getReleasedDate() != null){

            this.frontReleasedDate = sdf.format(domin.getReleasedDate());
        }

        for(OilTypeEnums value : OilTypeEnums.values()){
            if(value.getCode().equals(domin.getOilClassification())){
                this.setOilClassification(value.getZhName());
            }
        }
        for(FuelOilEnums value : FuelOilEnums.values()){
            if(value.getCode().equals(domin.getOilVarieties())){
                this.setOilVarieties(value.getZhName());
            }
        }
        this.setAliveFlag(domin.getAliveFlag());
        this.setCreateUser(domin.getCreateUser());
        this.setLocationProvince(domin.getLocationProvince());
        this.setLocationCity(domin.getLocationCity());
        this.setDetailedDescription(domin.getDetailedDescription());
        this.setEpMemberId(domin.getEpMemberId());
        this.setEpMemberName(domin.getEpMemberName());
        this.setExt1(domin.getExt1());
        this.setInfoId(domin.getInfoId());
        this.setInfoTitle(domin.getInfoTitle());
        this.setLangVer(domin.getLangVer());
        this.setNumber(domin.getNumber());
        this.setCreateDate(domin.getCreateDate());
        this.setReleasedDate(domin.getReleasedDate());
        this.setUnit(domin.getUnit());
        this.setUnitPrice(domin.getUnitPrice());
        this.setPort(domin.getPort());
        this.setStatus(domin.getStatus());
        this.setUpdateDate(domin.getUpdateDate());
        this.setUpdateUser(domin.getUpdateUser());
        this.setUuid(domin.getUuid());
        this.setVersion(domin.getVersion());
        this.setYpname(domin.getYpname());
        this.setGgname(domin.getGgname());

    }

    public HaseInfo voToDomain(){
        HaseInfo haseInfo = new HaseInfo();
        BeanUtils.copyProperties(this,haseInfo);

        for (PurchaseTypeEnums value : PurchaseTypeEnums.values()) {
            if (value.getZhName().equals(this.getInfoType())) {
                haseInfo.setInfoType(value.getCode());
                break;
            }
        }
        for (TypeOfShippingEnums value : TypeOfShippingEnums.values()) {
            if (value.getZhName().equals(this.getTransportWay())) {
                haseInfo.setTransportWay(value.getCode());
                break;
            }
        }
        for (TypeOfShippingEnums value : TypeOfShippingEnums.values()) {
            if (value.getZhName().equals(this.getDeliveryWay())) {
                haseInfo.setDeliveryWay(value.getCode());
                break;
            }
        }
        return haseInfo;
    }


    public void domainToOriginalVo(HaseInfo domin) {
        if(domin == null){
            return ;
        }
        BeanUtils.copyProperties(domin,this);
    }

	
	public String getIssueTimeStart() {
		return issueTimeStart;
	}

	public void setIssueTimeStart(String issueTimeStart) {
		this.issueTimeStart = issueTimeStart;
	}

	public String getIssueTimeEnd() {
		return issueTimeEnd;
	}

	public void setIssueTimeEnd(String issueTimeEnd) {
		this.issueTimeEnd = issueTimeEnd;
	}

	public List<ChmentsVO> getChments() {
		return chments;
	}
	public void setChments(List<ChmentsVO> chments) {
		this.chments = chments;
	}

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getInfoTypeList() {
        return infoTypeList;
    }

    public void setInfoTypeList(List<String> infoTypeList) {
        this.infoTypeList = infoTypeList;
    }


    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
        if(StringUtil.isNotBlank(status)){
            String[] statusArray = status.split(",");
            setStatusList(Arrays.asList(statusArray));
        }
    }

    /**状态*/
    private String status;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    /**状态列表*/
    private List<String> statusList;


}