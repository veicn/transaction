package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.transport.domain.Agency;

public class VoyageStartRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**配载计划*/
	private List<VoyageStartDetail> list;
	
	/**装卸港维护*/
	private List<Agency> listAgency;
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**附件*/
	private String accessory;  
	
	/**合同路径*/
	private String accessoryPath;  
	
	/**油种*/
	private List<Map<String,Object>> oilList;
	
	
	public List<Map<String,Object>> getOilList() {
		return oilList;
	}
	public void setOilList(List<Map<String,Object>> oilList) {
		this.oilList = oilList;
	}
	public String getAccessoryPath() {
		return accessoryPath;
	}
	public void setAccessoryPath(String accessoryPath) {
		this.accessoryPath = accessoryPath;
	}
	public List<VoyageStartDetail> getList() {
		return list;
	}
	public void setList(List<VoyageStartDetail> list) {
		this.list = list;
	}
	/**船合同uuid*/
	public void setShipPactUuid(String shipPactUuid){
		this.shipPactUuid=shipPactUuid;
	}
	/**船合同uuid*/
	public String getShipPactUuid(){
		return this.shipPactUuid;
	}
	
	/**附件*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件*/
	public String getAccessory(){
		return this.accessory;
	}
	public List<Agency> getListAgency() {
		return listAgency;
	}
	public void setListAgency(List<Agency> listAgency) {
		this.listAgency = listAgency;
	}
	
	
}