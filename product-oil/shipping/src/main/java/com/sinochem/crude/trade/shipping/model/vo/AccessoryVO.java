package com.sinochem.crude.trade.shipping.model.vo;

import java.util.ArrayList;

import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.shipping.domain.Accessory;


public class AccessoryVO extends BaseDomainVO<Accessory>{

	/**主键_ID*/
	private Long accessoryId;
	
	/**UUID*/
	private String uuid;
	
	/**船舶_ID*/
	private Long sysShipId;
	
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
	public void setAccessoryId(Long accessoryId){
		this.accessoryId=accessoryId;
	}
	/**主键_ID*/
	public Long getAccessoryId(){
		return this.accessoryId;
	}
	
	/**船舶_ID*/
	public void setSysShipId(Long sysShipId){
		this.sysShipId=sysShipId;
	}
	/**船舶_ID*/
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
	protected void convertToVO(Accessory domain) {
		
        //主键_ID
        this.accessoryId = domain.getAccessoryId();
                
        //船舶_ID
        this.sysShipId = domain.getSysShipId();
        //UUID
        this.uuid = domain.getUuid();
        //
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
	protected Accessory convertToDomain() {
		
		Accessory accessory = new Accessory();
		
		accessory.setAccessoryId(this.accessoryId);

		accessory.setUuid(this.uuid);

		accessory.setSysShipId(this.sysShipId);
		
		accessory.setAccessoryFileNm(this.accessoryFileNm);

		accessory.setAccessory(this.accessory);

		accessory.setExt1(this.ext1);

		accessory.setVersion(this.version);

		accessory.setAliveFlag(this.aliveFlag);

		accessory.setCreateUser(this.createUser);

		accessory.setUpdateUser(this.updateUser);

		return accessory;
	}
	
    /**
     * 使用构造函数获得VO
     */
    public AccessoryVO() {
        super();
    }
	
    /**
     * 使用构造函数获得VO
     */
    public AccessoryVO(Accessory domain) {
        super(domain);
    }
}