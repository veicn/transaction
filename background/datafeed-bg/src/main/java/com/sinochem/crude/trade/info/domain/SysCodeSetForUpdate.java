package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SysCodeSetForUpdate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**序号*/
	private String id;  
	
	/**模块代码*/
	private String modelCode;  
	
	/**值集类别代码*/
	private String setCode;  
	
	/**值集类型名称*/
	private String setName;  
	
	/**代码集代码*/
	private String itemCode;  
	
	/**代码集名称*/
	private String itemName;  
	
	/**值集排序*/
	private Integer itemSort;  
	
	/**上级代码*/
	private String itemParent;  
	
	/**相关值集*/
	private String itemRelation;  
	
	/**是否可编辑1可编辑，0不可编辑*/
	private String isEdit;  
	
	/**平台代码*/
	private String platCode;  
	
	/**值集描述*/
	private String itemDesc;  
	
	/**层级*/
	private Integer itemLevel;  
	
	/**备注*/
	private String remark;  
	
	/**状态10，正常，00禁用*/
	private String status;  
	
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

	private String oldSetCode;

	private String oldModelCode;
	
	/**序号*/
	public void setId(String id){
		this.id=id;
	}

	/**序号*/
	public String getId(){
		return this.id;
	}
	
	/**模块代码*/
	public void setModelCode(String modelCode){
		this.modelCode=modelCode;
	}
	/**模块代码*/
	public String getModelCode(){
		return this.modelCode;
	}
	
	/**值集类别代码*/
	public void setSetCode(String setCode){
		this.setCode=setCode;
	}
	/**值集类别代码*/
	public String getSetCode(){
		return this.setCode;
	}
	
	/**值集类型名称*/
	public void setSetName(String setName){
		this.setName=setName;
	}
	/**值集类型名称*/
	public String getSetName(){
		return this.setName;
	}
	
	/**代码集代码*/
	public void setItemCode(String itemCode){
		this.itemCode=itemCode;
	}
	/**代码集代码*/
	public String getItemCode(){
		return this.itemCode;
	}
	
	/**代码集名称*/
	public void setItemName(String itemName){
		this.itemName=itemName;
	}
	/**代码集名称*/
	public String getItemName(){
		return this.itemName;
	}
	
	/**值集排序*/
	public void setItemSort(Integer itemSort){
		this.itemSort=itemSort;
	}
	/**值集排序*/
	public Integer getItemSort(){
		return this.itemSort;
	}
	
	/**上级代码*/
	public void setItemParent(String itemParent){
		this.itemParent=itemParent;
	}
	/**上级代码*/
	public String getItemParent(){
		return this.itemParent;
	}
	
	/**相关值集*/
	public void setItemRelation(String itemRelation){
		this.itemRelation=itemRelation;
	}
	/**相关值集*/
	public String getItemRelation(){
		return this.itemRelation;
	}
	
	/**是否可编辑1可编辑，0不可编辑*/
	public void setIsEdit(String isEdit){
		this.isEdit=isEdit;
	}
	/**是否可编辑1可编辑，0不可编辑*/
	public String getIsEdit(){
		return this.isEdit;
	}
	
	/**平台代码*/
	public void setPlatCode(String platCode){
		this.platCode=platCode;
	}
	/**平台代码*/
	public String getPlatCode(){
		return this.platCode;
	}
	
	/**值集描述*/
	public void setItemDesc(String itemDesc){
		this.itemDesc=itemDesc;
	}
	/**值集描述*/
	public String getItemDesc(){
		return this.itemDesc;
	}
	
	/**层级*/
	public void setItemLevel(Integer itemLevel){
		this.itemLevel=itemLevel;
	}
	/**层级*/
	public Integer getItemLevel(){
		return this.itemLevel;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**状态10，正常，00禁用*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态10，正常，00禁用*/
	public String getStatus(){
		return this.status;
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

    public String getOldSetCode() {
        return oldSetCode;
    }

    public void setOldSetCode(String oldSetCode) {
        this.oldSetCode = oldSetCode;
    }

    public String getOldModelCode() {
        return oldModelCode;
    }

    public void setOldModelCode(String oldModelCode) {
        this.oldModelCode = oldModelCode;
    }

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.id);
	map.put("modelCode",this.modelCode);
	map.put("setCode",this.setCode);
	map.put("setName",this.setName);
	map.put("itemCode",this.itemCode);
	map.put("itemName",this.itemName);
	map.put("itemSort",this.itemSort);
	map.put("itemParent",this.itemParent);
	map.put("itemRelation",this.itemRelation);
	map.put("isEdit",this.isEdit);
	map.put("platCode",this.platCode);
	map.put("itemDesc",this.itemDesc);
	map.put("itemLevel",this.itemLevel);
	map.put("remark",this.remark);
	map.put("status",this.status);
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