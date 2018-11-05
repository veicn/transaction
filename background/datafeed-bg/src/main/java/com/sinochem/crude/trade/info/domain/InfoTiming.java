package com.sinochem.crude.trade.info.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class InfoTiming implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增主键*/
	private Long id;  
	
	/**32位的唯一键*/
	private String uuid;  
	
	/**资讯id*/
	private Long infoId;  
	
	/**处理状态：00：待处理，10：已处理*/
	private String status;  
	
	/**定时类型：00：立即推送，01：定时推送，30：定时发布*/
	private String timingType;  
	
	/**预设处理时间*/
	private java.util.Date timingDate;  
	
	/**推送类别标签*/
	private String pushTag;  
	
	/**推送内容*/
	private String pushContent;  
	
	/**推送文章链接*/
	private String infoDetail;  
	
	/**来源*/
	private String origin;  
	
	/**推送人ID*/
	private String empId;  
	
	/**推送人NAME*/
	private String empName;  
	
	/**推送人（公司/个人）ID*/
	private String memberId;  
	
	/**创建者*/
	private String createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改者*/
	private String updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**市场ID*/
	private String marketId;  
	
	/**语言类型（ZH：中文，EN：英文）*/
	private String langVer;  
	
	/**扩展字段1*/
	private String extend1;  
	
	/**扩展字段2*/
	private String extend2;  
	
	/**扩展字段3*/
	private String extend3;  
	
	/**扩展字段4*/
	private String extend4;  
	
	/**扩展字段5*/
	private String extend5;  
	
	/**扩展字段6*/
	private String extend6;  
	
	/**扩展字段7*/
	private String extend7;  
	
	/**扩展字段8*/
	private String extend8;  
	
	/**扩展字段9*/
	private String extend9;  
	
	/**扩展字段10*/
	private String extend10;  
	
		/**自增主键*/
	public void setId(Long id){
		this.id=id;
	}
	/**自增主键*/
	public Long getId(){
		return this.id;
	}
	
	/**32位的唯一键*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**32位的唯一键*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**资讯id*/
	public void setInfoId(Long infoId){
		this.infoId=infoId;
	}
	/**资讯id*/
	public Long getInfoId(){
		return this.infoId;
	}
	
	/**处理状态：00：待处理，10：已处理*/
	public void setStatus(String status){
		this.status=status;
	}
	/**处理状态：00：待处理，10：已处理*/
	public String getStatus(){
		return this.status;
	}
	
	/**定时类型：00：立即推送，01：定时推送，30：定时发布*/
	public void setTimingType(String timingType){
		this.timingType=timingType;
	}
	/**定时类型：00：立即推送，01：定时推送，30：定时发布*/
	public String getTimingType(){
		return this.timingType;
	}
	
	/**预设处理时间*/
	public void setTimingDate(java.util.Date timingDate){
		this.timingDate=timingDate;
	}
	/**预设处理时间*/
	public java.util.Date getTimingDate(){
		return this.timingDate;
	}
	
	/**推送类别标签*/
	public void setPushTag(String pushTag){
		this.pushTag=pushTag;
	}
	/**推送类别标签*/
	public String getPushTag(){
		return this.pushTag;
	}
	
	/**推送内容*/
	public void setPushContent(String pushContent){
		this.pushContent=pushContent;
	}
	/**推送内容*/
	public String getPushContent(){
		return this.pushContent;
	}
	
	/**推送文章链接*/
	public void setInfoDetail(String infoDetail){
		this.infoDetail=infoDetail;
	}
	/**推送文章链接*/
	public String getInfoDetail(){
		return this.infoDetail;
	}
	
	/**来源*/
	public void setOrigin(String origin){
		this.origin=origin;
	}
	/**来源*/
	public String getOrigin(){
		return this.origin;
	}
	
	/**推送人ID*/
	public void setEmpId(String empId){
		this.empId=empId;
	}
	/**推送人ID*/
	public String getEmpId(){
		return this.empId;
	}
	
	/**推送人NAME*/
	public void setEmpName(String empName){
		this.empName=empName;
	}
	/**推送人NAME*/
	public String getEmpName(){
		return this.empName;
	}
	
	/**推送人（公司/个人）ID*/
	public void setMemberId(String memberId){
		this.memberId=memberId;
	}
	/**推送人（公司/个人）ID*/
	public String getMemberId(){
		return this.memberId;
	}
	
	/**创建者*/
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public String getCreateUser(){
		return this.createUser;
	}
	
	/**创建时间*/
	public void setCreateDate(java.util.Date createDate){
		this.createDate=createDate;
	}
	/**创建时间*/
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	
	/**修改者*/
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	/**修改者*/
	public String getUpdateUser(){
		return this.updateUser;
	}
	
	/**修改时间*/
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate=updateDate;
	}
	/**修改时间*/
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
	
	/**数据状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**数据状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**市场ID*/
	public void setMarketId(String marketId){
		this.marketId=marketId;
	}
	/**市场ID*/
	public String getMarketId(){
		return this.marketId;
	}
	
	/**语言类型（ZH：中文，EN：英文）*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型（ZH：中文，EN：英文）*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**扩展字段1*/
	public void setExtend1(String extend1){
		this.extend1=extend1;
	}
	/**扩展字段1*/
	public String getExtend1(){
		return this.extend1;
	}
	
	/**扩展字段2*/
	public void setExtend2(String extend2){
		this.extend2=extend2;
	}
	/**扩展字段2*/
	public String getExtend2(){
		return this.extend2;
	}
	
	/**扩展字段3*/
	public void setExtend3(String extend3){
		this.extend3=extend3;
	}
	/**扩展字段3*/
	public String getExtend3(){
		return this.extend3;
	}
	
	/**扩展字段4*/
	public void setExtend4(String extend4){
		this.extend4=extend4;
	}
	/**扩展字段4*/
	public String getExtend4(){
		return this.extend4;
	}
	
	/**扩展字段5*/
	public void setExtend5(String extend5){
		this.extend5=extend5;
	}
	/**扩展字段5*/
	public String getExtend5(){
		return this.extend5;
	}
	
	/**扩展字段6*/
	public void setExtend6(String extend6){
		this.extend6=extend6;
	}
	/**扩展字段6*/
	public String getExtend6(){
		return this.extend6;
	}
	
	/**扩展字段7*/
	public void setExtend7(String extend7){
		this.extend7=extend7;
	}
	/**扩展字段7*/
	public String getExtend7(){
		return this.extend7;
	}
	
	/**扩展字段8*/
	public void setExtend8(String extend8){
		this.extend8=extend8;
	}
	/**扩展字段8*/
	public String getExtend8(){
		return this.extend8;
	}
	
	/**扩展字段9*/
	public void setExtend9(String extend9){
		this.extend9=extend9;
	}
	/**扩展字段9*/
	public String getExtend9(){
		return this.extend9;
	}
	
	/**扩展字段10*/
	public void setExtend10(String extend10){
		this.extend10=extend10;
	}
	/**扩展字段10*/
	public String getExtend10(){
		return this.extend10;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("uuid",this.uuid);
	map.put("infoId",this.infoId);
	map.put("status",this.status);
	map.put("timingType",this.timingType);
	map.put("timingDate",this.timingDate);
	map.put("pushTag",this.pushTag);
	map.put("pushContent",this.pushContent);
	map.put("infoDetail",this.infoDetail);
	map.put("origin",this.origin);
	map.put("empId",this.empId);
	map.put("empName",this.empName);
	map.put("memberId",this.memberId);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("marketId",this.marketId);
	map.put("langVer",this.langVer);
	map.put("extend1",this.extend1);
	map.put("extend2",this.extend2);
	map.put("extend3",this.extend3);
	map.put("extend4",this.extend4);
	map.put("extend5",this.extend5);
	map.put("extend6",this.extend6);
	map.put("extend7",this.extend7);
	map.put("extend8",this.extend8);
	map.put("extend9",this.extend9);
	map.put("extend10",this.extend10);
			return map;
	}
	
}