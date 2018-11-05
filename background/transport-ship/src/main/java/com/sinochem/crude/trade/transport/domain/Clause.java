package com.sinochem.crude.trade.transport.domain;

import java.io.Serializable;

public class Clause implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long clauseId;  
	
	/**UUID*/
	private String uuid;  
	
	/**货物意向编号*/
	private String clauseCode;  
	
	/**货盘id*/
	private Long palletId;  
	
	/**货盘uuid*/
	private String palletUuid;  
	
	/**租船意向id*/
	private Long intentionId;  
	
	/**租船意向uuid*/
	private String intentionUuid;  
	
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
	
	/**货盘发布人id*/
	private Long palletMemberId;  
	
	/**货盘发布人名称*/
	private String palletMemberName;  
	
	/**货盘发布人简称*/
	private String palletMemberSimName;  
	
	/**状态（1待确认2已确认3已关闭）*/
	private String status;  
	
	/**受载期开始*/
	private java.util.Date pactBeg;  
	
	/**受载期结束*/
	private java.util.Date pactEnd;  
	
	/**装货地点要求*/
	private String loadRegion;  
	
	/**卸货地点要求*/
	private String unloadRegion;  
	
	/**最小货量*/
	private java.math.BigDecimal minQuantity;  
	
	/**WS点*/
	private java.math.BigDecimal ws;  
	
	/**合同规定航速*/
	private String pactSpeed;  
	
	/**滞期费率*/
	private java.math.BigDecimal demurrage;  
	
	/**允许装卸时间（小时）*/
	private java.math.BigDecimal dockTime;  
	
	/**代理费*/
	private java.math.BigDecimal commission;  
	
	/**备注*/
	private String remark;  
	
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
	
	/**货盘发布人名称英文*/
	private String palletMemberNameEn;  
	
		/**业务唯一键*/
	public void setClauseId(Long clauseId){
		this.clauseId=clauseId;
	}
	/**业务唯一键*/
	public Long getClauseId(){
		return this.clauseId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**货物意向编号*/
	public void setClauseCode(String clauseCode){
		this.clauseCode=clauseCode;
	}
	/**货物意向编号*/
	public String getClauseCode(){
		return this.clauseCode;
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
	
	/**租船意向id*/
	public void setIntentionId(Long intentionId){
		this.intentionId=intentionId;
	}
	/**租船意向id*/
	public Long getIntentionId(){
		return this.intentionId;
	}
	
	/**租船意向uuid*/
	public void setIntentionUuid(String intentionUuid){
		this.intentionUuid=intentionUuid;
	}
	/**租船意向uuid*/
	public String getIntentionUuid(){
		return this.intentionUuid;
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
	
	/**货盘发布人id*/
	public void setPalletMemberId(Long palletMemberId){
		this.palletMemberId=palletMemberId;
	}
	/**货盘发布人id*/
	public Long getPalletMemberId(){
		return this.palletMemberId;
	}
	
	/**货盘发布人名称*/
	public void setPalletMemberName(String palletMemberName){
		this.palletMemberName=palletMemberName;
	}
	/**货盘发布人名称*/
	public String getPalletMemberName(){
		return this.palletMemberName;
	}
	
	/**货盘发布人简称*/
	public void setPalletMemberSimName(String palletMemberSimName){
		this.palletMemberSimName=palletMemberSimName;
	}
	/**货盘发布人简称*/
	public String getPalletMemberSimName(){
		return this.palletMemberSimName;
	}
	
	/**状态（1待确认2已确认3已关闭）*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态（1待确认2已确认3已关闭）*/
	public String getStatus(){
		return this.status;
	}
	
	/**受载期开始*/
	public void setPactBeg(java.util.Date pactBeg){
		this.pactBeg=pactBeg;
	}
	/**受载期开始*/
	public java.util.Date getPactBeg(){
		return this.pactBeg;
	}
	
	/**受载期结束*/
	public void setPactEnd(java.util.Date pactEnd){
		this.pactEnd=pactEnd;
	}
	/**受载期结束*/
	public java.util.Date getPactEnd(){
		return this.pactEnd;
	}
	
	/**装货地点要求*/
	public void setLoadRegion(String loadRegion){
		this.loadRegion=loadRegion;
	}
	/**装货地点要求*/
	public String getLoadRegion(){
		return this.loadRegion;
	}
	
	/**卸货地点要求*/
	public void setUnloadRegion(String unloadRegion){
		this.unloadRegion=unloadRegion;
	}
	/**卸货地点要求*/
	public String getUnloadRegion(){
		return this.unloadRegion;
	}
	
	/**最小货量*/
	public void setMinQuantity(java.math.BigDecimal minQuantity){
		this.minQuantity=minQuantity;
	}
	/**最小货量*/
	public java.math.BigDecimal getMinQuantity(){
		return this.minQuantity;
	}
	
	/**WS点*/
	public void setWs(java.math.BigDecimal ws){
		this.ws=ws;
	}
	/**WS点*/
	public java.math.BigDecimal getWs(){
		return this.ws;
	}
	
	/**合同规定航速*/
	public void setPactSpeed(String pactSpeed){
		this.pactSpeed=pactSpeed;
	}
	/**合同规定航速*/
	public String getPactSpeed(){
		return this.pactSpeed;
	}
	
	/**滞期费率*/
	public void setDemurrage(java.math.BigDecimal demurrage){
		this.demurrage=demurrage;
	}
	/**滞期费率*/
	public java.math.BigDecimal getDemurrage(){
		return this.demurrage;
	}
	
	/**允许装卸时间（小时）*/
	public void setDockTime(java.math.BigDecimal dockTime){
		this.dockTime=dockTime;
	}
	/**允许装卸时间（小时）*/
	public java.math.BigDecimal getDockTime(){
		return this.dockTime;
	}
	
	/**代理费*/
	public void setCommission(java.math.BigDecimal commission){
		this.commission=commission;
	}
	/**代理费*/
	public java.math.BigDecimal getCommission(){
		return this.commission;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
	
	/**货盘发布人名称英文*/
	public void setPalletMemberNameEn(String palletMemberNameEn){
		this.palletMemberNameEn=palletMemberNameEn;
	}
	/**货盘发布人名称英文*/
	public String getPalletMemberNameEn(){
		return this.palletMemberNameEn;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clauseId",this.clauseId);
	map.put("uuid",this.uuid);
	map.put("clauseCode",this.clauseCode);
	map.put("palletId",this.palletId);
	map.put("palletUuid",this.palletUuid);
	map.put("intentionId",this.intentionId);
	map.put("intentionUuid",this.intentionUuid);
	map.put("shipPlateId",this.shipPlateId);
	map.put("shipPlateUuid",this.shipPlateUuid);
	map.put("epMemberId",this.epMemberId);
	map.put("epMemberName",this.epMemberName);
	map.put("epMemberSimName",this.epMemberSimName);
	map.put("palletMemberId",this.palletMemberId);
	map.put("palletMemberName",this.palletMemberName);
	map.put("palletMemberSimName",this.palletMemberSimName);
	map.put("status",this.status);
	map.put("pactBeg",this.pactBeg);
	map.put("pactEnd",this.pactEnd);
	map.put("loadRegion",this.loadRegion);
	map.put("unloadRegion",this.unloadRegion);
	map.put("minQuantity",this.minQuantity);
	map.put("ws",this.ws);
	map.put("pactSpeed",this.pactSpeed);
	map.put("demurrage",this.demurrage);
	map.put("dockTime",this.dockTime);
	map.put("commission",this.commission);
	map.put("remark",this.remark);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
	map.put("epMemberNameEn",this.epMemberNameEn);
	map.put("palletMemberNameEn",this.palletMemberNameEn);
			return map;
	}*/
}