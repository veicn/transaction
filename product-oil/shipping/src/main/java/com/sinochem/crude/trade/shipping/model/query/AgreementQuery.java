package com.sinochem.crude.trade.shipping.model.query;


import java.util.HashMap;
import java.util.Map;

public class AgreementQuery{
	
	/**UUID*/
	private String uuid;  
	
	/**租船合同编号*/
	private String charterPartyNumber;
	
	/**产品*/
	private String productNm;
	
	/**状态*/
	private String status;
	
	/**查询开始日期*/
	private String queryStart; 
	
	/**查询结束日期*/
	private String queryEnd;
	
	/**角色id*/
	private Long roleId;
	//中化新ID
	private Long singaporeId;

	public String getProductLoadingAndDischarge() {
		return productLoadingAndDischarge;
	}

	public void setProductLoadingAndDischarge(String productLoadingAndDischarge) {
		this.productLoadingAndDischarge = productLoadingAndDischarge;
	}

	//模糊查询字段
	private String productLoadingAndDischarge;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getQueryStart() {
		return queryStart;
	}

	public void setQueryStart(String queryStart) {
		this.queryStart = queryStart;
	}

	public String getQueryEnd() {
		return queryEnd;
	}

	public void setQueryEnd(String queryEnd) {
		this.queryEnd = queryEnd;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCharterPartyNumber() {
		return charterPartyNumber;
	}

	public void setCharterPartyNumber(String charterPartyNumber) {
		this.charterPartyNumber = charterPartyNumber;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSingaporeId() {
		return singaporeId;
	}

	public void setSingaporeId(Long singaporeId) {
		this.singaporeId = singaporeId;
	}

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
	map.put("uuid",this.uuid);
	map.put("charterPartyNumber",this.charterPartyNumber);
	map.put("productNm",this.productNm);
	map.put("queryStart",this.queryStart);
	map.put("queryEnd",this.queryEnd);
	map.put("status",this.status);
	map.put("roleId",this.roleId);
		map.put("singaporeId",this.singaporeId);
			return map;
	}

}
