package com.sinochem.crude.trade.transport.model;

import java.util.ArrayList;

import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.SysShip;

public class SysShipVO extends SysShip {

	private static final long serialVersionUID = 1L;

	/**船舶保存类型（1:待审核，2:有效）  */
	private String saveType;
	
	/**船舶审核标识 1:审核通过 2:审核驳回*/
	private String checkFlag;
	
	/**审核驳回信息 */
	private String refuseMessage;

	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	
	/**船舶审核标识 1:审核通过 2:审核驳回*/
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	/**审核驳回信息 */
	public String getRefuseMessage() {
		return refuseMessage;
	}
	public void setRefuseMessage(String refuseMessage) {
		this.refuseMessage = refuseMessage;
	}



	/** 文件信息 */
	private ArrayList<Accessory> fileInfoList;

	public ArrayList<Accessory> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(ArrayList<Accessory> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
}