package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TransitRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**业务唯一键*/
	private Long transitId;  
	
	/**UUDI*/
	private String uuid;  
	
	/**船盘id*/
	private Long shipPlateId;  
	
	/**船盘uuid*/
	private String shipPlateUuid;  
	
	/**船合同uuid*/
	private String shipPactUuid;  
	
	/**船合同id*/
	private Long shipPactId;  
	
	/**公司id*/
	private Long epMemberId;  
	
	/**日期*/
	private java.util.Date dateNow;  
	
	/**时间*/
	private String timeNow;  
	
	/**位置*/
	private String position;  
	
	/**平均速度（24h）*/
	private String speedH;  
	
	/**平均速度（全程）*/
	private String speedAll;  
	
	/**RPM*/
	private String rpm;  
	
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	private String unloadEta;  
	
	/**海况*/
	private String sea;  
	
	/**明水*/
	private String water;  
	
	/**气相硫化氢*/
	private String sulfide;  
	
	/**备注*/
	private String remark;  
	
	/**附件*/
	private String accessory;  
	
	/**附件路径*/
	private String accessoryPath;  
	
	/**扩展字段1*/
	private String ext1;  
	
	/***/
	private Map<String,Object> map;
	
	/**图片*/
	private List<String> imgList;
	
	/**卸港英文*/
	private String unloadEtaEn;  
	
	/**卸港code*/
	private String unloadEtaCode;  
	
	/**卸港ETA*/
	private List<Map<String,Object>> lists;
	
	
	public List<Map<String, Object>> getLists() {
		return lists;
	}
	public void setLists(List<Map<String, Object>> lists) {
		this.lists = lists;
	}
	public String getUnloadEtaEn() {
		return unloadEtaEn;
	}
	public void setUnloadEtaEn(String unloadEtaEn) {
		this.unloadEtaEn = unloadEtaEn;
	}
	public String getUnloadEtaCode() {
		return unloadEtaCode;
	}
	public void setUnloadEtaCode(String unloadEtaCode) {
		this.unloadEtaCode = unloadEtaCode;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	/**业务唯一键*/
	public void setTransitId(Long transitId){
		this.transitId=transitId;
	}
	/**业务唯一键*/
	public Long getTransitId(){
		return this.transitId;
	}
	
	/**UUDI*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUDI*/
	public String getUuid(){
		return this.uuid;
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
	
	/**船合同uuid*/
	public void setShipPactUuid(String shipPactUuid){
		this.shipPactUuid=shipPactUuid;
	}
	/**船合同uuid*/
	public String getShipPactUuid(){
		return this.shipPactUuid;
	}
	
	/**船合同id*/
	public void setShipPactId(Long shipPactId){
		this.shipPactId=shipPactId;
	}
	/**船合同id*/
	public Long getShipPactId(){
		return this.shipPactId;
	}
	
	/**公司id*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**公司id*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**日期*/
	public void setDateNow(java.util.Date dateNow){
		this.dateNow=dateNow;
	}
	/**日期*/
	public java.util.Date getDateNow(){
		return this.dateNow;
	}
	
	/**时间*/
	public void setTimeNow(String timeNow){
		this.timeNow=timeNow;
	}
	/**时间*/
	public String getTimeNow(){
		return this.timeNow;
	}
	
	/**位置*/
	public void setPosition(String position){
		this.position=position;
	}
	/**位置*/
	public String getPosition(){
		return this.position;
	}
	
	/**平均速度（24h）*/
	public void setSpeedH(String speedH){
		this.speedH=speedH;
	}
	/**平均速度（24h）*/
	public String getSpeedH(){
		return this.speedH;
	}
	
	/**平均速度（全程）*/
	public void setSpeedAll(String speedAll){
		this.speedAll=speedAll;
	}
	/**平均速度（全程）*/
	public String getSpeedAll(){
		return this.speedAll;
	}
	
	/**RPM*/
	public void setRpm(String rpm){
		this.rpm=rpm;
	}
	/**RPM*/
	public String getRpm(){
		return this.rpm;
	}
	
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	public void setUnloadEta(String unloadEta){
		this.unloadEta=unloadEta;
	}
	/**卸港eta（卸港:ETA时间戳;卸港:ETA时间戳;）*/
	public String getUnloadEta(){
		return this.unloadEta;
	}
	
	/**海况*/
	public void setSea(String sea){
		this.sea=sea;
	}
	/**海况*/
	public String getSea(){
		return this.sea;
	}
	
	/**明水*/
	public void setWater(String water){
		this.water=water;
	}
	/**明水*/
	public String getWater(){
		return this.water;
	}
	
	/**气相硫化氢*/
	public void setSulfide(String sulfide){
		this.sulfide=sulfide;
	}
	/**气相硫化氢*/
	public String getSulfide(){
		return this.sulfide;
	}
	
	/**备注*/
	public void setRemark(String remark){
		this.remark=remark;
	}
	/**备注*/
	public String getRemark(){
		return this.remark;
	}
	
	/**附件*/
	public void setAccessory(String accessory){
		this.accessory=accessory;
	}
	/**附件*/
	public String getAccessory(){
		return this.accessory;
	}
	
	/**附件路径*/
	public void setAccessoryPath(String accessoryPath){
		this.accessoryPath=accessoryPath;
	}
	/**附件路径*/
	public String getAccessoryPath(){
		return this.accessoryPath;
	}
	
	/**扩展字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**扩展字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
		
	
	/*public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("transitId",this.transitId);
	map.put("uuid",this.uuid);
	map.put("shipPlateId",this.shipPlateId);
	map.put("shipPlateUuid",this.shipPlateUuid);
	map.put("shipPactUuid",this.shipPactUuid);
	map.put("shipPactId",this.shipPactId);
	map.put("epMemberId",this.epMemberId);
	map.put("dateNow",this.dateNow);
	map.put("timeNow",this.timeNow);
	map.put("position",this.position);
	map.put("speedH",this.speedH);
	map.put("speedAll",this.speedAll);
	map.put("rpm",this.rpm);
	map.put("unloadEta",this.unloadEta);
	map.put("sea",this.sea);
	map.put("water",this.water);
	map.put("sulfide",this.sulfide);
	map.put("remark",this.remark);
	map.put("accessory",this.accessory);
	map.put("accessoryPath",this.accessoryPath);
	map.put("aliveFlag",this.aliveFlag);
	map.put("version",this.version);
	map.put("langVer",this.langVer);
	map.put("createDate",this.createDate);
	map.put("updateDate",this.updateDate);
	map.put("createUser",this.createUser);
	map.put("updateUser",this.updateUser);
	map.put("ext1",this.ext1);
			return map;
	}*/
}