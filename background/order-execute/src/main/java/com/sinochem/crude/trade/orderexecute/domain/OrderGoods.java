package com.sinochem.crude.trade.orderexecute.domain;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;

import java.io.Serializable;

public class OrderGoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**ID*/
	private Long orderGoodsId;  
	
	/**UUID*/
	private String orderGoodsUuid;  
	
	/**ORDER_ID*/
	private Long orderId;  
	
	/**资源ID*/
	private Long resourceId;  
	
	/**中文名称*/
	private String zhName;  
	
	/**英文名称*/
	private String enName;  
	
	/**区域*/
	private String region;  
	
	/**产地*/
	private String countryOrigin;  
	
	/**数量*/
	private java.math.BigDecimal quantity;  
	
	/**数量单位*/
	private String quantityUnit;  
	
	/**计量方式*/
	private String measureMode;  
	
	/**规格（成品油）*/
	private String spec;  
	
	/**api度*/
	private java.math.BigDecimal api;  
	
	/**溢短装*/
	private java.math.BigDecimal moreLess;  
	
	/**备注*/
	private String remark;  
	
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
	public void setOrderGoodsId(Long orderGoodsId){
		this.orderGoodsId=orderGoodsId;
	}
	/**ID*/
	public Long getOrderGoodsId(){
		return this.orderGoodsId;
	}
	
	/**UUID*/
	public void setOrderGoodsUuid(String orderGoodsUuid){
		this.orderGoodsUuid=orderGoodsUuid;
	}
	/**UUID*/
	public String getOrderGoodsUuid(){
		return this.orderGoodsUuid;
	}
	
	/**ORDER_ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**ORDER_ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**资源ID*/
	public void setResourceId(Long resourceId){
		this.resourceId=resourceId;
	}
	/**资源ID*/
	public Long getResourceId(){
		return this.resourceId;
	}
	
	/**中文名称*/
	public void setZhName(String zhName){
		this.zhName=zhName;
	}
	/**中文名称*/
	public String getZhName(){
		return this.zhName;
	}
	
	/**英文名称*/
	public void setEnName(String enName){
		this.enName=enName;
	}
	/**英文名称*/
	public String getEnName(){
		return this.enName;
	}
	
	/**区域*/
	public void setRegion(String region){
		this.region=region;
	}
	/**区域*/
	public String getRegion(){
		return this.region;
	}
	
	/**产地*/
	public void setCountryOrigin(String countryOrigin){
		this.countryOrigin=countryOrigin;
	}
	/**产地*/
	public String getCountryOrigin(){
		return this.countryOrigin;
	}
	
	/**数量*/
	public void setQuantity(java.math.BigDecimal quantity){
		this.quantity=quantity;
	}
	/**数量*/
	public java.math.BigDecimal getQuantity(){
		return this.quantity;
	}
	
	/**数量单位*/
	public void setQuantityUnit(String quantityUnit){
		this.quantityUnit=quantityUnit;
	}
	/**数量单位*/
	public String getQuantityUnit(){
		return this.quantityUnit;
	}
	
	/**计量方式*/
	public void setMeasureMode(String measureMode){
		this.measureMode=measureMode;
	}
	/**计量方式*/
	public String getMeasureMode(){
		return this.measureMode;
	}
	
	/**规格（成品油）*/
	public void setSpec(String spec){
		this.spec=spec;
	}
	/**规格（成品油）*/
	public String getSpec(){
		return this.spec;
	}
	
	/**api度*/
	public void setApi(java.math.BigDecimal api){
		this.api=api;
	}
	/**api度*/
	public java.math.BigDecimal getApi(){
		return this.api;
	}
	
	/**溢短装*/
	public void setMoreLess(java.math.BigDecimal moreLess){
		this.moreLess=moreLess;
	}
	/**溢短装*/
	public java.math.BigDecimal getMoreLess(){
		return this.moreLess;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
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
		map.put("orderGoodsId",this.orderGoodsId);
	map.put("orderGoodsUuid",this.orderGoodsUuid);
	map.put("orderId",this.orderId);
	map.put("resourceId",this.resourceId);
	map.put("zhName",this.zhName);
	map.put("enName",this.enName);
	map.put("region",this.region);
	map.put("countryOrigin",this.countryOrigin);
	map.put("quantity",this.quantity);
	map.put("quantityUnit",this.quantityUnit);
	map.put("measureMode",this.measureMode);
	map.put("spec",this.spec);
	map.put("api",this.api);
	map.put("moreLess",this.moreLess);
	map.put("remark",this.remark);
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