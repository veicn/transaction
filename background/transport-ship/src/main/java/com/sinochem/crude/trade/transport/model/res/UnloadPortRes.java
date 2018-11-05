package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UnloadPortRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**卸港商检指定*/
	private String inspection;  
	
	/**卸港商检指定联系方式*/
	private String inspectionTel;  
	
	/**卸港监卸*/
	private String monitor;  
	
	/**卸港监卸联系方式*/
	private String monitorTel;  
	
	/**卸港船代*/
	private String agency;  
	
	/**联系方卸港船代联系方式*/
	private String agencyTel;  
	
	/**附件一*/
	private String accessory1;  
	
	/**附件二*/
	private String accessory2;  
	
	/**附件三*/
	private String accessory3;  
	
	/**附件四*/
	private String accessory4;  
	
	/**附件一路径*/
	private String accessory1Path;  
	
	/**附件二路径*/
	private String accessory2Path;  
	
	/**附件三路径*/
	private String accessory3Path;  
	
	/**附件四路径*/
	private String accessory4Path;  
	
	/**卸港信息*/
	private List<UnloadPortDetail> list;
	
	/**协议卸港信息*/
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
	public String getAccessory4Path() {
		return accessory4Path;
	}
	public void setAccessory4Path(String accessory4Path) {
		this.accessory4Path = accessory4Path;
	}
	public List<UnloadPortDetail> getList() {
		return list;
	}
	public void setList(List<UnloadPortDetail> list) {
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
	
	/**卸港商检指定*/
	public void setInspection(String inspection){
		this.inspection=inspection;
	}
	/**卸港商检指定*/
	public String getInspection(){
		return this.inspection;
	}
	
	/**卸港商检指定联系方式*/
	public void setInspectionTel(String inspectionTel){
		this.inspectionTel=inspectionTel;
	}
	/**卸港商检指定联系方式*/
	public String getInspectionTel(){
		return this.inspectionTel;
	}
	
	/**卸港监卸*/
	public void setMonitor(String monitor){
		this.monitor=monitor;
	}
	/**卸港监卸*/
	public String getMonitor(){
		return this.monitor;
	}
	
	/**卸港监卸联系方式*/
	public void setMonitorTel(String monitorTel){
		this.monitorTel=monitorTel;
	}
	/**卸港监卸联系方式*/
	public String getMonitorTel(){
		return this.monitorTel;
	}
	
	/**卸港船代*/
	public void setAgency(String agency){
		this.agency=agency;
	}
	/**卸港船代*/
	public String getAgency(){
		return this.agency;
	}
	
	/**联系方卸港船代联系方式*/
	public void setAgencyTel(String agencyTel){
		this.agencyTel=agencyTel;
	}
	/**联系方卸港船代联系方式*/
	public String getAgencyTel(){
		return this.agencyTel;
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
	
	/**附件四*/
	public void setAccessory4(String accessory4){
		this.accessory4=accessory4;
	}
	/**附件四*/
	public String getAccessory4(){
		return this.accessory4;
	}
	
}