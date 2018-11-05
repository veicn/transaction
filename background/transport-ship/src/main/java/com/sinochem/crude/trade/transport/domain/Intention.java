package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Intention implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long intentionId;  
	
	/**UUID*/
	private String uuid;  
	
	/**租船意向编号（需求单号+船名+时间）*/
	private String intentionCode;  
	
	/**货盘id*/
	private Long palletId;  
	
	/**货盘uuid*/
	private String palletUuid;  
	
	/**船盘id*/
	private Long shipPlateId;  
	
	/**船盘uuid*/
	private String shipPlateUuid;  
	
	/**会员公司id*/
	private Long epMemberId;  
	
	/**会员公司名称*/
	private String epMemberName;  
	
	/**会员公司名称*/
	private String epMemberSimName;  
	
	/**船盘发布人id*/
	private Long shipPlateMemberId;  
	
	/**船盘发布人名称*/
	private String shipPlateMemberName;  
	
	/**船盘发布人简称*/
	private String shipPlateMemberSimName;  
	
	/**状态（1已询盘2已还盘3已确认租船4已关闭）*/
	private String status;  
	
	/**询盘装期开始*/
	private java.util.Date inPactBeg;  
	
	/**询盘装期结束*/
	private java.util.Date inPactEnd;  
	
	/**询盘最小货量*/
	private java.math.BigDecimal inMinQuantity;  
	
	/**询盘WS点*/
	private java.math.BigDecimal inWs;  
	
	/**询盘航速*/
	private String inPactSpeed;  
	
	/**询盘滞期费率*/
	private java.math.BigDecimal inDemurrage;  
	
	/**询盘允许装卸时间*/
	private java.math.BigDecimal inDockTime;  
	
	/**询盘卸货地点要求*/
	private String inUnloadRegion;  
	
	/**询盘装货地点要求*/
	private String inLoadRegion;  
	
	/**询盘佣金*/
	private java.math.BigDecimal inCommission;  
	
	/**询盘备注*/
	private String inRemark;  
	
	/**还盘装期开始*/
	private java.util.Date coPactBeg;  
	
	/**还盘装期结束*/
	private java.util.Date coPactEnd;  
	
	/**还盘最小货量*/
	private java.math.BigDecimal coMinQuantity;  
	
	/**还盘WS点*/
	private java.math.BigDecimal coWs;  
	
	/**还盘航速*/
	private String coPactSpeed;  
	
	/**还盘滞期费率*/
	private java.math.BigDecimal coDemurrage;  
	
	/**还盘允许装卸时间*/
	private java.math.BigDecimal coDockTime;  
	
	/**还盘卸货地点要求*/
	private String coUnloadRegion;  
	
	/**还盘装货地点要求*/
	private String coLoadRegion;  
	
	/**还盘佣金*/
	private java.math.BigDecimal coCommission;  
	
	/**还盘备注*/
	private String coRemark;  
	
	/**是否有效(1有效0无效)*/
	private String aliveFlag;  
	
	/**版本号*/
	private String version;  
	
	/**语言版本*/
	private String langVer;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**创建人*/
	private Long createUser;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**会员公司名称英文*/
	private String epMemberNameEn;  
	
	/**船盘发布人名称英文*/
	private String shipPlateMemberNameEn;  
	
		/**业务唯一键*/
	public void setIntentionId(Long intentionId){
		this.intentionId=intentionId;
	}
	/**业务唯一键*/
	public Long getIntentionId(){
		return this.intentionId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**租船意向编号（需求单号+船名+时间）*/
	public void setIntentionCode(String intentionCode){
		this.intentionCode=intentionCode;
	}
	/**租船意向编号（需求单号+船名+时间）*/
	public String getIntentionCode(){
		return this.intentionCode;
	}
	
	/**货盘id*/
	public void setPalletId(Long palletId){
		this.palletId=palletId;
	}
	/**货盘id*/
	public Long getPalletId(){
		return this.palletId;
	}
	
	/**货盘uuid*/
	public void setPalletUuid(String palletUuid){
		this.palletUuid=palletUuid;
	}
	/**货盘uuid*/
	public String getPalletUuid(){
		return this.palletUuid;
	}
	
	/**船盘id*/
	public void setShipPlateId(Long shipPlateId){
		this.shipPlateId=shipPlateId;
	}
	/**船盘id*/
	public Long getShipPlateId(){
		return this.shipPlateId;
	}
	
	/**船盘uuid*/
	public void setShipPlateUuid(String shipPlateUuid){
		this.shipPlateUuid=shipPlateUuid;
	}
	/**船盘uuid*/
	public String getShipPlateUuid(){
		return this.shipPlateUuid;
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
	
	/**会员公司名称*/
	public void setEpMemberSimName(String epMemberSimName){
		this.epMemberSimName=epMemberSimName;
	}
	/**会员公司名称*/
	public String getEpMemberSimName(){
		return this.epMemberSimName;
	}
	
	/**船盘发布人id*/
	public void setShipPlateMemberId(Long shipPlateMemberId){
		this.shipPlateMemberId=shipPlateMemberId;
	}
	/**船盘发布人id*/
	public Long getShipPlateMemberId(){
		return this.shipPlateMemberId;
	}
	
	/**船盘发布人名称*/
	public void setShipPlateMemberName(String shipPlateMemberName){
		this.shipPlateMemberName=shipPlateMemberName;
	}
	/**船盘发布人名称*/
	public String getShipPlateMemberName(){
		return this.shipPlateMemberName;
	}
	
	/**船盘发布人简称*/
	public void setShipPlateMemberSimName(String shipPlateMemberSimName){
		this.shipPlateMemberSimName=shipPlateMemberSimName;
	}
	/**船盘发布人简称*/
	public String getShipPlateMemberSimName(){
		return this.shipPlateMemberSimName;
	}
	
	/**状态（1已询盘2已还盘3已确认租船4已关闭）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1已询盘2已还盘3已确认租船4已关闭）*/
	public String getStatus(){
		return this.status;
	}
	
	/**询盘装期开始*/
	public void setInPactBeg(java.util.Date inPactBeg){
		this.inPactBeg=inPactBeg;
	}
	/**询盘装期开始*/
	public java.util.Date getInPactBeg(){
		return this.inPactBeg;
	}
	
	/**询盘装期结束*/
	public void setInPactEnd(java.util.Date inPactEnd){
		this.inPactEnd=inPactEnd;
	}
	/**询盘装期结束*/
	public java.util.Date getInPactEnd(){
		return this.inPactEnd;
	}
	
	/**询盘最小货量*/
	public void setInMinQuantity(java.math.BigDecimal inMinQuantity){
		this.inMinQuantity=inMinQuantity;
	}
	/**询盘最小货量*/
	public java.math.BigDecimal getInMinQuantity(){
		return this.inMinQuantity;
	}
	
	/**询盘WS点*/
	public void setInWs(java.math.BigDecimal inWs){
		this.inWs=inWs;
	}
	/**询盘WS点*/
	public java.math.BigDecimal getInWs(){
		return this.inWs;
	}
	
	/**询盘航速*/
	public void setInPactSpeed(String inPactSpeed){
		this.inPactSpeed=inPactSpeed;
	}
	/**询盘航速*/
	public String getInPactSpeed(){
		return this.inPactSpeed;
	}
	
	/**询盘滞期费率*/
	public void setInDemurrage(java.math.BigDecimal inDemurrage){
		this.inDemurrage=inDemurrage;
	}
	/**询盘滞期费率*/
	public java.math.BigDecimal getInDemurrage(){
		return this.inDemurrage;
	}
	
	/**询盘允许装卸时间*/
	public void setInDockTime(java.math.BigDecimal inDockTime){
		this.inDockTime=inDockTime;
	}
	/**询盘允许装卸时间*/
	public java.math.BigDecimal getInDockTime(){
		return this.inDockTime;
	}
	
	/**询盘卸货地点要求*/
	public void setInUnloadRegion(String inUnloadRegion){
		this.inUnloadRegion=inUnloadRegion;
	}
	/**询盘卸货地点要求*/
	public String getInUnloadRegion(){
		return this.inUnloadRegion;
	}
	
	/**询盘装货地点要求*/
	public void setInLoadRegion(String inLoadRegion){
		this.inLoadRegion=inLoadRegion;
	}
	/**询盘装货地点要求*/
	public String getInLoadRegion(){
		return this.inLoadRegion;
	}
	
	/**询盘佣金*/
	public void setInCommission(java.math.BigDecimal inCommission){
		this.inCommission=inCommission;
	}
	/**询盘佣金*/
	public java.math.BigDecimal getInCommission(){
		return this.inCommission;
	}
	
	/**询盘备注*/
	public void setInRemark(String inRemark){
		this.inRemark=inRemark;
	}
	/**询盘备注*/
	public String getInRemark(){
		return this.inRemark;
	}
	
	/**还盘装期开始*/
	public void setCoPactBeg(java.util.Date coPactBeg){
		this.coPactBeg=coPactBeg;
	}
	/**还盘装期开始*/
	public java.util.Date getCoPactBeg(){
		return this.coPactBeg;
	}
	
	/**还盘装期结束*/
	public void setCoPactEnd(java.util.Date coPactEnd){
		this.coPactEnd=coPactEnd;
	}
	/**还盘装期结束*/
	public java.util.Date getCoPactEnd(){
		return this.coPactEnd;
	}
	
	/**还盘最小货量*/
	public void setCoMinQuantity(java.math.BigDecimal coMinQuantity){
		this.coMinQuantity=coMinQuantity;
	}
	/**还盘最小货量*/
	public java.math.BigDecimal getCoMinQuantity(){
		return this.coMinQuantity;
	}
	
	/**还盘WS点*/
	public void setCoWs(java.math.BigDecimal coWs){
		this.coWs=coWs;
	}
	/**还盘WS点*/
	public java.math.BigDecimal getCoWs(){
		return this.coWs;
	}
	
	/**还盘航速*/
	public void setCoPactSpeed(String coPactSpeed){
		this.coPactSpeed=coPactSpeed;
	}
	/**还盘航速*/
	public String getCoPactSpeed(){
		return this.coPactSpeed;
	}
	
	/**还盘滞期费率*/
	public void setCoDemurrage(java.math.BigDecimal coDemurrage){
		this.coDemurrage=coDemurrage;
	}
	/**还盘滞期费率*/
	public java.math.BigDecimal getCoDemurrage(){
		return this.coDemurrage;
	}
	
	/**还盘允许装卸时间*/
	public void setCoDockTime(java.math.BigDecimal coDockTime){
		this.coDockTime=coDockTime;
	}
	/**还盘允许装卸时间*/
	public java.math.BigDecimal getCoDockTime(){
		return this.coDockTime;
	}
	
	/**还盘卸货地点要求*/
	public void setCoUnloadRegion(String coUnloadRegion){
		this.coUnloadRegion=coUnloadRegion;
	}
	/**还盘卸货地点要求*/
	public String getCoUnloadRegion(){
		return this.coUnloadRegion;
	}
	
	/**还盘装货地点要求*/
	public void setCoLoadRegion(String coLoadRegion){
		this.coLoadRegion=coLoadRegion;
	}
	/**还盘装货地点要求*/
	public String getCoLoadRegion(){
		return this.coLoadRegion;
	}
	
	/**还盘佣金*/
	public void setCoCommission(java.math.BigDecimal coCommission){
		this.coCommission=coCommission;
	}
	/**还盘佣金*/
	public java.math.BigDecimal getCoCommission(){
		return this.coCommission;
	}
	
	/**还盘备注*/
	public void setCoRemark(String coRemark){
		this.coRemark=coRemark;
	}
	/**还盘备注*/
	public String getCoRemark(){
		return this.coRemark;
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
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
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
	
	/**会员公司名称英文*/
	public void setEpMemberNameEn(String epMemberNameEn){
		this.epMemberNameEn=epMemberNameEn;
	}
	/**会员公司名称英文*/
	public String getEpMemberNameEn(){
		return this.epMemberNameEn;
	}
	
	/**船盘发布人名称英文*/
	public void setShipPlateMemberNameEn(String shipPlateMemberNameEn){
		this.shipPlateMemberNameEn=shipPlateMemberNameEn;
	}
	/**船盘发布人名称英文*/
	public String getShipPlateMemberNameEn(){
		return this.shipPlateMemberNameEn;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("intentionId",this.intentionId);
	map.put("uuid",this.uuid);
	map.put("intentionCode",this.intentionCode);
	map.put("palletId",this.palletId);
	map.put("palletUuid",this.palletUuid);
	map.put("shipPlateId",this.shipPlateId);
	map.put("shipPlateUuid",this.shipPlateUuid);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("epMemberSimName",this.epMemberSimName);
	map.put("shipPlateMemberId",this.shipPlateMemberId);
	map.put("shipPlateMemberName",this.shipPlateMemberName);
	map.put("shipPlateMemberSimName",this.shipPlateMemberSimName);
	map.put("status",this.status);
	map.put("inPactBeg",this.inPactBeg);
	map.put("inPactEnd",this.inPactEnd);
	map.put("inMinQuantity",this.inMinQuantity);
	map.put("inWs",this.inWs);
	map.put("inPactSpeed",this.inPactSpeed);
	map.put("inDemurrage",this.inDemurrage);
	map.put("inDockTime",this.inDockTime);
	map.put("inUnloadRegion",this.inUnloadRegion);
	map.put("inLoadRegion",this.inLoadRegion);
	map.put("inCommission",this.inCommission);
	map.put("inRemark",this.inRemark);
	map.put("coPactBeg",this.coPactBeg);
	map.put("coPactEnd",this.coPactEnd);
	map.put("coMinQuantity",this.coMinQuantity);
	map.put("coWs",this.coWs);
	map.put("coPactSpeed",this.coPactSpeed);
	map.put("coDemurrage",this.coDemurrage);
	map.put("coDockTime",this.coDockTime);
	map.put("coUnloadRegion",this.coUnloadRegion);
	map.put("coLoadRegion",this.coLoadRegion);
	map.put("coCommission",this.coCommission);
	map.put("coRemark",this.coRemark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("epMemberNameEn",this.epMemberNameEn);
	map.put("shipPlateMemberNameEn",this.shipPlateMemberNameEn);
			return map;
	}*/
}