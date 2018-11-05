package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class LoadPortRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**附件一*/
	private String accessory1;  
	
	/**附件二*/
	private String accessory2;  
	
	/**附件三*/
	private String accessory3;  
	
	/**附件一路径*/
	private String accessory1Path;  
	
	/**附件二路径*/
	private String accessory2Path;  
	
	/**附件三路径*/
	private String accessory3Path;  
	
	/**回显协议港口*/
	private Map<String,Object> map;
	
	
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getAccessory1Path() {
		return accessory1Path;
	}
	public void setAccessory1Path(String accessory1Path) {
		this.accessory1Path = accessory1Path;
	}
	public String getAccessory2Path() {
		return accessory2Path;
	}
	public void setAccessory2Path(String accessory2Path) {
		this.accessory2Path = accessory2Path;
	}
	public String getAccessory3Path() {
		return accessory3Path;
	}
	public void setAccessory3Path(String accessory3Path) {
		this.accessory3Path = accessory3Path;
	}
	/**装港信息*/
	private List<LoadPortDetail> list;
	
	
	
	public List<LoadPortDetail> getList() {
		return list;
	}
	public void setList(List<LoadPortDetail> list) {
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
	
	
	/**附件一*/
	public void setAccessory1(String accessory1){
		this.accessory1=accessory1;
	}
	/**附件一*/
	public String getAccessory1(){
		return this.accessory1;
	}
	
	/**附件二*/
	public void setAccessory2(String accessory2){
		this.accessory2=accessory2;
	}
	/**附件二*/
	public String getAccessory2(){
		return this.accessory2;
	}
	
	/**附件三*/
	public void setAccessory3(String accessory3){
		this.accessory3=accessory3;
	}
	/**附件三*/
	public String getAccessory3(){
		return this.accessory3;
	}
	

}