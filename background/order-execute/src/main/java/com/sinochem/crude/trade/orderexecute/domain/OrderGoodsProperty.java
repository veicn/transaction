package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class OrderGoodsProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long goodsPropertyId;  
	
	/**UUID*/
	private String goodsPropertyUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**ORDER_GOODS_ID*/
	private Long orderGoodsId;  
	
	/**属性名称*/
	private String name;  
	
	/**属性值*/
	private String value;  
	
	/**属性排列顺序*/
	private Integer rank;  
	
	/**语言类型*/
	private String langVer;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建人*/
	private Long createUser;  
	
	/**创建时间*/
	private java.util.Date createDate;  
	
	/**修改人*/
	private Long updateUser;  
	
	/**修改时间*/
	private java.util.Date updateDate;  
	
	/**版本号*/
	private Integer version;  
	
		/**ID*/
	public void setGoodsPropertyId(Long goodsPropertyId){
		this.goodsPropertyId=goodsPropertyId;
	}
	/**ID*/
	public Long getGoodsPropertyId(){
		return this.goodsPropertyId;
	}
	
	/**UUID*/
	public void setGoodsPropertyUuid(String goodsPropertyUuid){
		this.goodsPropertyUuid=goodsPropertyUuid;
	}
	/**UUID*/
	public String getGoodsPropertyUuid(){
		return this.goodsPropertyUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**ORDER_GOODS_ID*/
	public void setOrderGoodsId(Long orderGoodsId){
		this.orderGoodsId=orderGoodsId;
	}
	/**ORDER_GOODS_ID*/
	public Long getOrderGoodsId(){
		return this.orderGoodsId;
	}
	
	/**属性名称*/
	public void setName(String name){
		this.name=name;
	}
	/**属性名称*/
	public String getName(){
		return this.name;
	}
	
	/**属性值*/
	public void setValue(String value){
		this.value=value;
	}
	/**属性值*/
	public String getValue(){
		return this.value;
	}
	
	/**属性排列顺序*/
	public void setRank(Integer rank){
		this.rank=rank;
	}
	/**属性排列顺序*/
	public Integer getRank(){
		return this.rank;
	}
	
	/**语言类型*/
	public void setLangVer(String langVer){
		this.langVer=langVer;
	}
	/**语言类型*/
	public String getLangVer(){
		return this.langVer;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建人*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建人*/
	public Long getCreateUser(){
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
	
	/**修改人*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**修改人*/
	public Long getUpdateUser(){
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
	
	/**版本号*/
	public void setVersion(Integer version){
		this.version=version;
	}
	/**版本号*/
	public Integer getVersion(){
		return this.version;
	}
	
		
	
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsPropertyId",this.goodsPropertyId);
	map.put("goodsPropertyUuid",this.goodsPropertyUuid);
	map.put("orderId",this.orderId);
	map.put("orderGoodsId",this.orderGoodsId);
	map.put("name",this.name);
	map.put("value",this.value);
	map.put("rank",this.rank);
	map.put("langVer",this.langVer);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
	map.put("version",this.version);
			return map;
	}
}