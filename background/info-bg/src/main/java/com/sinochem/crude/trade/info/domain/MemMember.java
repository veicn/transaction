package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MemMember implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private String id;  
	
	/**会员id*/
	private String memberId;  
	
	/***/
	private String epMemberId;  
	
	/**中文名称*/
	private String memberName;  
	
	/**简称*/
	private String memberNameShort;  
	
	/**英文名称*/
	private String memberNameEn;  
	
	/**登录名*/
	private String loginName;  
	
	/**昵称*/
	private String nickName;  
	
	/**头像地址*/
	private String imgurl;  
	
	/**签名*/
	private String personalNote;  
	
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
	
	/**语言类型（zh：中文，en：英文）*/
	private String langVer;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/**扩展字段2*/
	private String ext2;  
	
	/**扩展字段3*/
	private String ext3;  
	
	/**扩展字段4*/
	private String ext4;  
	
	/**扩展字段5*/
	private String ext5;  
	
	/**扩展字段6*/
	private String ext6;  
	
	/**扩展字段7*/
	private String ext7;  
	
	/**扩展字段8*/
	private String ext8;  
	
	/**扩展字段9*/
	private String ext9;  
	
	/**扩展字段10*/
	private String ext10;  
	
		/**主键*/
	public void setId(String id){
		this.id=id;
	}
	/**主键*/
	public String getId(){
		return this.id;
	}
	
	/**会员id*/
	public void setMemberId(String memberId){
		this.memberId=memberId;
	}
	/**会员id*/
	public String getMemberId(){
		return this.memberId;
	}
	
	/***/
	public void setEpMemberId(String epMemberId){
		this.epMemberId=epMemberId;
	}
	/***/
	public String getEpMemberId(){
		return this.epMemberId;
	}
	
	/**中文名称*/
	public void setMemberName(String memberName){
		this.memberName=memberName;
	}
	/**中文名称*/
	public String getMemberName(){
		return this.memberName;
	}
	
	/**简称*/
	public void setMemberNameShort(String memberNameShort){
		this.memberNameShort=memberNameShort;
	}
	/**简称*/
	public String getMemberNameShort(){
		return this.memberNameShort;
	}
	
	/**英文名称*/
	public void setMemberNameEn(String memberNameEn){
		this.memberNameEn=memberNameEn;
	}
	/**英文名称*/
	public String getMemberNameEn(){
		return this.memberNameEn;
	}
	
	/**登录名*/
	public void setLoginName(String loginName){
		this.loginName=loginName;
	}
	/**登录名*/
	public String getLoginName(){
		return this.loginName;
	}
	
	/**昵称*/
	public void setNickName(String nickName){
		this.nickName=nickName;
	}
	/**昵称*/
	public String getNickName(){
		return this.nickName;
	}
	
	/**头像地址*/
	public void setImgurl(String imgurl){
		this.imgurl=imgurl;
	}
	/**头像地址*/
	public String getImgurl(){
		return this.imgurl;
	}
	
	/**签名*/
	public void setPersonalNote(String personalNote){
		this.personalNote=personalNote;
	}
	/**签名*/
	public String getPersonalNote(){
		return this.personalNote;
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
	
	/**语言类型（zh：中文，en：英文）*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型（zh：中文，en：英文）*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**扩展字段2*/
	public void setExt2(String ext2){
		this.ext2=ext2;
	}
	/**扩展字段2*/
	public String getExt2(){
		return this.ext2;
	}
	
	/**扩展字段3*/
	public void setExt3(String ext3){
		this.ext3=ext3;
	}
	/**扩展字段3*/
	public String getExt3(){
		return this.ext3;
	}
	
	/**扩展字段4*/
	public void setExt4(String ext4){
		this.ext4=ext4;
	}
	/**扩展字段4*/
	public String getExt4(){
		return this.ext4;
	}
	
	/**扩展字段5*/
	public void setExt5(String ext5){
		this.ext5=ext5;
	}
	/**扩展字段5*/
	public String getExt5(){
		return this.ext5;
	}
	
	/**扩展字段6*/
	public void setExt6(String ext6){
		this.ext6=ext6;
	}
	/**扩展字段6*/
	public String getExt6(){
		return this.ext6;
	}
	
	/**扩展字段7*/
	public void setExt7(String ext7){
		this.ext7=ext7;
	}
	/**扩展字段7*/
	public String getExt7(){
		return this.ext7;
	}
	
	/**扩展字段8*/
	public void setExt8(String ext8){
		this.ext8=ext8;
	}
	/**扩展字段8*/
	public String getExt8(){
		return this.ext8;
	}
	
	/**扩展字段9*/
	public void setExt9(String ext9){
		this.ext9=ext9;
	}
	/**扩展字段9*/
	public String getExt9(){
		return this.ext9;
	}
	
	/**扩展字段10*/
	public void setExt10(String ext10){
		this.ext10=ext10;
	}
	/**扩展字段10*/
	public String getExt10(){
		return this.ext10;
	}
	
		
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("memberId",this.memberId);
	map.put("epMemberId",this.epMemberId);
	map.put("memberName",this.memberName);
	map.put("memberNameShort",this.memberNameShort);
	map.put("memberNameEn",this.memberNameEn);
	map.put("loginName",this.loginName);
	map.put("nickName",this.nickName);
	map.put("imgurl",this.imgurl);
	map.put("personalNote",this.personalNote);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("aliveFlag",this.aliveFlag);
	map.put("marketId",this.marketId);
	map.put("langVer",this.langVer);
	map.put("ext1",this.ext1);
	map.put("ext2",this.ext2);
	map.put("ext3",this.ext3);
	map.put("ext4",this.ext4);
	map.put("ext5",this.ext5);
	map.put("ext6",this.ext6);
	map.put("ext7",this.ext7);
	map.put("ext8",this.ext8);
	map.put("ext9",this.ext9);
	map.put("ext10",this.ext10);
			return map;
	}
}