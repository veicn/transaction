package com.sinochem.crude.trade.shiprefueling.model.vo;


import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.enums.SaleInfoTypeEnums;
import com.sinochem.it.b2b.common.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InfoVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	/**业务唯一键*/
	private Long infoId;

	/**附件url*/
	private List<String> urls;

	/**UUID*/
	private String uuid;

	/**信息类型(1:船燃销售 2:船油供销)*/
	private String infoType;

	/**信息标题*/
	private String infoTitle;

	/**油品分类*/
	private String oilClassification;

	/**品种*/
	private String oilVarieties;

	/**交货方式(1:送供 2:自提)*/
	private String deliveryWay;

	/**单价*/
	private java.math.BigDecimal unitPrice;

	/**单位*/
	private String unit;

	/**所在地区-省*/
	private String locationProvince;

	/**所在地区-市*/
	private String locationCity;

	/**详细说明*/
	private String detailedDescription;

	/**港口*/
	private String port;


	/**运输方式(1:油轮运输,2:油罐车运输,3:驳船送供,4:自提,5:面议)*/
	private String transportWay;

	/**会员公司id*/
	private Long epMemberId;

	/**会员公司名称*/
	private String epMemberName;

	/**是否有效(1有效0无效)*/
	private String aliveFlag;

	/**版本号*/
	private String version;

	/**语言版本*/
	private String langVer;

	/**创建时间*/
	private String createDate;

	/**修改时间*/
	private String updateDate;

	/**创建人*/
	private Long createUser;

	/**修改人*/
	private Long updateUser;

	/**扩展字段1*/
	private String ext1;

	/**状态*/
	private String status;

	/**发布日期*/
	private String releasedDate;
	/**附件信息*/
	private List<ChmentsVO> chmentsVOList;

	/**业务唯一键*/
	public void setInfoId(Long infoId){
		this.infoId=infoId;
	}
	/**业务唯一键*/
	public Long getInfoId(){
		return this.infoId;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}

	/**信息类型(1:船燃销售 2:船油供销)*/
	public void setInfoType(String infoType){
		this.infoType=infoType;
	}
	/**信息类型(1:船燃销售 2:船油供销)*/
	public String getInfoType(){
		return this.infoType;
	}

	/**信息标题*/
	public void setInfoTitle(String infoTitle){
		this.infoTitle=infoTitle;
	}
	/**信息标题*/
	public String getInfoTitle(){
		return this.infoTitle;
	}

	/**油品分类*/
	public void setOilClassification(String oilClassification){
		this.oilClassification=oilClassification;
	}
	/**油品分类*/
	public String getOilClassification(){
		return this.oilClassification;
	}

	/**品种*/
	public void setOilVarieties(String oilVarieties){
		this.oilVarieties=oilVarieties;
	}
	/**品种*/
	public String getOilVarieties(){
		return this.oilVarieties;
	}

	/**交货方式(1:送供 2:自提)*/
	public void setDeliveryWay(String deliveryWay){
		this.deliveryWay=deliveryWay;
	}
	/**交货方式(1:送供 2:自提)*/
	public String getDeliveryWay(){
		return this.deliveryWay;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**单价*/
	public void setUnitPrice(java.math.BigDecimal unitPrice){
		this.unitPrice=unitPrice;
	}
	/**单价*/
	public java.math.BigDecimal getUnitPrice(){
		return this.unitPrice;
	}

	/**所在地区-省*/
	public void setLocationProvince(String locationProvince){
		this.locationProvince=locationProvince;
	}
	/**所在地区-省*/
	public String getLocationProvince(){
		return this.locationProvince;
	}

	/**所在地区-市*/
	public void setLocationCity(String locationCity){
		this.locationCity=locationCity;
	}
	/**所在地区-市*/
	public String getLocationCity(){
		return this.locationCity;
	}

	/**详细说明*/
	public void setDetailedDescription(String detailedDescription){
		this.detailedDescription=detailedDescription;
	}
	/**详细说明*/
	public String getDetailedDescription(){
		return this.detailedDescription;
	}

	/**港口*/
	public void setPort(String port){
		this.port=port;
	}
	/**港口*/
	public String getPort(){
		return this.port;
	}

	/**运输方式*/
	public void setTransportWay(String transportWay){
		this.transportWay=transportWay;
	}
	/**运输方式*/
	public String getTransportWay(){
		return this.transportWay;
	}

	/**会员公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**会员公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}

	/**会员公司名称*/
	public void setEpMemberName(String epMemberName){
		this.epMemberName=epMemberName;
	}
	/**会员公司名称*/
	public String getEpMemberName(){
		return this.epMemberName;
	}

	/**是否有效(1有效0无效)*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**是否有效(1有效0无效)*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}

	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}

	/**语言版本*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言版本*/
	public String getLangVer(){
		return this.langVer;
	}

	/**创建时间*/
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public String getCreateDate(){
		return this.createDate;
	}

	/**修改时间*/
	public void setUpdateDate(String updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public String getUpdateDate(){
		return this.updateDate;
	}

	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
		return this.createUser;
	}

	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
		return this.updateUser;
	}

	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}

	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}

	public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }


	public List<ChmentsVO> getChmentsVOList() {
		return chmentsVOList;
	}

	public void setChmentsVOList(List<ChmentsVO> chmentsVOList) {
		this.chmentsVOList = chmentsVOList;
	}

	public InfoVO toInfovo(Info info){
		if(info == null){
			return new InfoVO();
		}
        InfoVO infoVO = new InfoVO();
        if (StringUtil.isNotBlank(info.getEpMemberName())){
            infoVO.setEpMemberName(info.getEpMemberName());
        }
        if (info.getEpMemberId() != null){
            infoVO.setEpMemberId(info.getEpMemberId());
        }
        if (StringUtil.isNotBlank(info.getInfoTitle())) {
            infoVO.setInfoTitle(info.getInfoTitle());
        }

        if (StringUtil.isNotBlank(info.getInfoType())) {
        	for(SaleInfoTypeEnums value : SaleInfoTypeEnums.values()){
				if(value.getCode().equals(info.getInfoType())){
					infoVO.setInfoType(value.getZhName());
					break;
				}
			}
        }
        if (info.getInfoId() != null) {
            infoVO.setInfoId(info.getInfoId());
        }
        if (info.getCreateDate() != null) {
            String s = DateUtil.formatDate(info.getCreateDate());
            infoVO.setCreateDate(s);
        }
        if (info.getCreateUser() != null) {
            infoVO.setCreateUser(info.getCreateUser());
        }
        if (StringUtil.isNotBlank(info.getDeliveryWay())) {
			if(Constants.INFO_TYPE_CRUDE.equals(info.getInfoType())){
				for(OilCrudeTypeOfShippingEnums value: OilCrudeTypeOfShippingEnums.values()){
					if(value.getCode().equals(info.getDeliveryWay())){
						infoVO.setDeliveryWay(value.getZhName());
						break;
					}
				}
			}else {
				for(TypeOfShippingEnums value: TypeOfShippingEnums.values()){
					if(value.getCode().equals(info.getDeliveryWay())){
						infoVO.setDeliveryWay(value.getZhName());
						break;
					}
				}
			}
        }
        if (StringUtil.isNotBlank(info.getDetailedDescription())) {
            infoVO.setDetailedDescription(info.getDetailedDescription());
        }
        if (StringUtil.isNotBlank(info.getLangVer())) {
            infoVO.setLangVer(info.getLangVer());
        }
        if (StringUtil.isNotBlank(info.getLocationCity())){
            infoVO.setLocationCity(info.getLocationCity());
        }
        if (StringUtil.isNotBlank(info.getLocationProvince())) {
            infoVO.setLocationProvince(info.getLocationProvince());
        }
        if (StringUtil.isNotBlank(infoVO.getStatus())) {
            infoVO.setStatus(info.getStatus());
        }
        if (StringUtil.isNotBlank(info.getPort())) {
            infoVO.setPort(info.getPort());
        }
        if (StringUtil.isNotBlank(info.getUuid())) {
            infoVO.setUuid(info.getUuid());
        }
        if (StringUtil.isNotBlank(info.getOilVarieties())) {
			/*for(FuelOilEnums value : FuelOilEnums.values()){
				if(value.getCode().equals(info.getOilVarieties())){
					infoVO.setOilVarieties(value.getZhName());
				}
			}*/
            infoVO.setOilVarieties(info.getOilVarieties());
        }
        if (StringUtil.isNotBlank(info.getOilClassification())) {
			/*for(OilTypeEnums value : OilTypeEnums.values()){
				if(value.getCode().equals(info.getOilClassification())){
					infoVO.setOilClassification(value.getZhName());
				}
			}*/
            infoVO.setOilClassification(info.getOilClassification());
        }
        if (StringUtil.isNotBlank(info.getTransportWay())){
			for (DeliveryModeEnums value : DeliveryModeEnums.values()) {
				if (value.getCode().equals(info.getTransportWay())) {
					infoVO.setTransportWay(value.getZhName());
					break;
				}
			}
        }

        if(StringUtils.isNotBlank(info.getUnit())){
        	infoVO.setUnit(info.getUnit());
		}

        if (info.getUnitPrice() != null) {
            infoVO.setUnitPrice(info.getUnitPrice());
        }
        if (info.getUpdateDate() != null) {
			String s = DateUtil.formatDate(info.getUpdateDate());
			infoVO.setUpdateDate(s);
		}
		if (info.getUpdateUser() != null) {
			infoVO.setUpdateUser(info.getUpdateUser());
		}
		if (StringUtil.isNotBlank(info.getStatus())) {
        	infoVO.setStatus(info.getStatus());
		}

		if(info.getReleasedDate() != null ){
			String s = DateUtil.formatDate(info.getReleasedDate());
        	infoVO.setReleasedDate(s);
		}



        return infoVO;
	}

	public Info voToInfo(){
        Info info = new Info();
        if (StringUtil.isNotBlank(this.getUuid())){
        	info.setUuid(this.getUuid());
		}
        if (this.getCreateUser()!= null) {
            info.setCreateUser(this.getCreateUser());
        }
        if (this.getEpMemberId() != null) {
            info.setEpMemberId(this.getEpMemberId());
        }
        if (this.getInfoId() != null) {
            info.setInfoId(this.getInfoId());
        }
        if (this.getUpdateUser() != null ) {
            info.setUpdateUser(this.getUpdateUser());
        }
        if (StringUtil.isNotBlank(this.getStatus())) {
            info.setStatus(this.getStatus());
        }
        if (StringUtil.isNotBlank(this.getDeliveryWay())) {
            info.setDeliveryWay(this.getDeliveryWay());

        }
        if (StringUtil.isNotBlank(this.getTransportWay())) {
            info.setTransportWay(this.getTransportWay());

        }
        if(StringUtil.isNotBlank(this.getUnit())){
        	info.setUnit(this.getUnit());
		}
        if (StringUtil.isNotBlank(this.getDetailedDescription())) {
            info.setDetailedDescription(this.getDetailedDescription());
        }
        if (StringUtil.isNotBlank(this.getEpMemberName())) {
            info.setEpMemberName(this.getEpMemberName());
        }
        if (StringUtil.isNotBlank(this.getInfoTitle())) {
            info.setInfoTitle(this.getInfoTitle());
        }
        if (StringUtil.isNotBlank(this.getInfoType())) {
            info.setInfoType(this.getInfoType());
        }
        if (StringUtil.isNotBlank(this.getLangVer())) {
            info.setLangVer(this.getLangVer());
        }
        if (StringUtil.isNotBlank(this.getLocationCity())) {
            info.setLocationCity(this.getLocationCity());
        }
        if (StringUtil.isNotBlank(this.getLocationProvince())) {
            info.setLocationProvince(this.getLocationProvince());
        }
        if (StringUtil.isNotBlank(this.getOilClassification())) {
            info.setOilClassification(this.getOilClassification());
        }
        if (StringUtil.isNotBlank(this.getOilVarieties())) {
            info.setOilVarieties(this.getOilVarieties());
        }
        if(StringUtil.isNotBlank(this.releasedDate)){
			Date releasedDate = DateUtil.getDate(this.releasedDate);
        	info.setReleasedDate(releasedDate);
		}
		if(this.unitPrice != null){
			info.setUnitPrice(this.unitPrice);
		}
		if(StringUtil.isNotBlank(this.unit)){
			info.setUnit(this.unit);
		}


        return info;
    }
}