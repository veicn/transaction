package com.sinochem.crude.trade.shipping.model.vo;

import java.util.ArrayList;

import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.shipping.domain.Accessory;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.enums.VesselSizeEnums;


public class SysShipVO extends BaseDomainVO<SysShip>{


	/**主键_ID*/
	private Long sysShipId;  
	
	/**UUID*/
	private String uuid;  
	
	/***/
	private String vesselName;  
	
	/***/
	private String imo;  
	
	/***/
	private String built;  
	
	/***/
	private String vesselType;  
	
	/***/
	private java.math.BigDecimal vesselSize; 
	
	/***/
	private VesselSizeEnums vesselSizeName; 
	
	/***/
	private java.math.BigDecimal cubic;  
	
	/***/
	private java.math.BigDecimal sdwt;  
	
	/***/
	private java.math.BigDecimal loa;  
	
	/***/
	private java.math.BigDecimal beam;  
	
	/***/
	private java.math.BigDecimal draft;  
	
	/***/
	private String hullType;  
	
	/**
	 * 文件名称
	 */
	private String accessoryFileNm;  
	
	/**
	 * 附件地址
	 */
	private String accessory;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**创建者*/
	private Long createUser;  
	
	/**更新者*/
	private Long updateUser;  
	
		/**主键_ID*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**主键_ID*/
	public Long getSysShipId(){
		return this.sysShipId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/***/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/***/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/***/
	public void setImo(String imo){
		this.imo=imo;
	}
	/***/
	public String getImo(){
		return this.imo;
	}
	
	/***/
	public void setBuilt(String built){
		this.built=built;
	}
	/***/
	public String getBuilt(){
		return this.built;
	}
	
	/***/
	public void setVesselType(String vesselType){
		this.vesselType=vesselType;
	}
	/***/
	public String getVesselType(){
		return this.vesselType;
	}
	
	/***/
	public void setVesselSize(java.math.BigDecimal vesselSize){
		this.vesselSize=vesselSize;
	}
	/***/
	public java.math.BigDecimal getVesselSize(){
		return this.vesselSize;
	}
	
	public VesselSizeEnums getVesselSizeName() {
		return vesselSizeName;
	}
	public void setVesselSizeName(VesselSizeEnums vesselSizeName) {
		this.vesselSizeName = vesselSizeName;
	}
	/***/
	public void setCubic(java.math.BigDecimal cubic){
		this.cubic=cubic;
	}
	/***/
	public java.math.BigDecimal getCubic(){
		return this.cubic;
	}
	
	/***/
	public void setSdwt(java.math.BigDecimal sdwt){
		this.sdwt=sdwt;
	}
	/***/
	public java.math.BigDecimal getSdwt(){
		return this.sdwt;
	}
	
	/***/
	public void setLoa(java.math.BigDecimal loa){
		this.loa=loa;
	}
	/***/
	public java.math.BigDecimal getLoa(){
		return this.loa;
	}
	
	/***/
	public void setBeam(java.math.BigDecimal beam){
		this.beam=beam;
	}
	/***/
	public java.math.BigDecimal getBeam(){
		return this.beam;
	}
	
	/***/
	public void setDraft(java.math.BigDecimal draft){
		this.draft=draft;
	}
	/***/
	public java.math.BigDecimal getDraft(){
		return this.draft;
	}
	
	/***/
	public void setHullType(String hullType){
		this.hullType=hullType;
	}
	/***/
	public String getHullType(){
		return this.hullType;
	}
	
	/**
	 * @return the accessoryFileNm
	 */
	public String getAccessoryFileNm() {
		return this.accessoryFileNm;
	}
	/**
	 * @param aCCESSORY_FILE_NM the aCCESSORY_FILE_NM to set
	 */
	public void setAccessoryFileNm(String accessoryFileNm) {
		this.accessoryFileNm = accessoryFileNm;
	}
	/**
	 * @return the accessory
	 */
	public String getAccessory() {
		return this.accessory;
	}
	/**
	 * @param accessory the accessory to set
	 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	/**创建者*/
	public void setCreateUser(Long createUser){
		this.createUser=createUser;
	}
	/**创建者*/
	public Long getCreateUser(){
		return this.createUser;
	}
	
	/**更新者*/
	public void setUpdateUser(Long updateUser){
		this.updateUser=updateUser;
	}
	/**更新者*/
	public Long getUpdateUser(){
		return this.updateUser;
	}
	
	/**文件信息*/
	private ArrayList<Accessory> fileInfoList;

	public ArrayList<Accessory> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(ArrayList<Accessory> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	
	@Override
	public void convertToVO(SysShip domain) {
		
        //主键_ID
        this.sysShipId = domain.getSysShipId();
        //UUID
        this.uuid = domain.getUuid();
        //
        this.vesselName = domain.getVesselName();
        //
        this.imo= domain.getImo();
        //
        this.built = domain.getBuilt() ;
        //
        this.vesselType = domain.getVesselType();
        //
        this.vesselSize = domain.getVesselSize();
        //
        this.cubic = domain.getCubic();
        //
        this.sdwt = domain.getSdwt();
        //
        this.loa = domain.getLoa();
        //
        this.beam = domain.getBeam();
        //
        this.draft = domain.getDraft();
        //
        this.hullType = domain.getHullType();
        // 文件名称
        this.accessoryFileNm = domain.getAccessoryFileNm();
        //附件地址
        this.accessory = domain.getAccessory();
        //预留字段1
        this.ext1 = domain.getExt1();
        //版本号
        this.version = domain.getVersion();
        //当前有效状态
        this.aliveFlag = domain.getAliveFlag();
        //创建者
        this.createUser = domain.getCreateUser();
        //更新者
        this.updateUser = domain.getUpdateUser();
		
	}

	@Override
	protected SysShip convertToDomain() {
		
		SysShip sysShip = new SysShip();
		
		sysShip.setSysShipId(this.sysShipId);

		sysShip.setUuid(this.uuid);

		sysShip.setVesselName(this.vesselName);

		sysShip.setImo(this.imo);

		sysShip.setBuilt(this.built);

		sysShip.setVesselType(this.vesselType);

		sysShip.setVesselSize(this.vesselSize);

		sysShip.setCubic(this.cubic);

		sysShip.setSdwt(this.sdwt);

		sysShip.setLoa(this.loa);

		sysShip.setBeam(this.beam);

		sysShip.setDraft(this.draft);

		sysShip.setHullType(this.hullType);

		sysShip.setAccessoryFileNm(this.accessoryFileNm);

		sysShip.setAccessory(this.accessory);

		sysShip.setExt1(this.ext1);

		sysShip.setVersion(this.version);

		sysShip.setAliveFlag(this.aliveFlag);

		sysShip.setCreateUser(this.createUser);

		sysShip.setUpdateUser(this.updateUser);

		return sysShip;
	}
	
    /**
     * 使用构造函数获得VO
     */
    public SysShipVO() {
        super();
    }
    
    /**
     * 使用构造函数获得VO
     */
    public SysShipVO(SysShip domain) {
        super(domain);
    }
}