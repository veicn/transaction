package com.sinochem.crude.trade.orderexecute.model;

import java.util.ArrayList;

import com.sinochem.crude.trade.orderexecute.domain.OrderDocument;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;

public class OrderDocumentVO extends OrderDocument {

	private static final long serialVersionUID = 1L;	
	
	/**订单UUID*/
	private String uuid;
	
	/**交易大类（1原油；2成品油；3化工品）*/
	private String tradeCategory;
	
	/**单证类型*/
	private String documentType;
	
	/**文件信息*/
	private ArrayList<OrderDocumentFile> fileInfoList;

	
	/**订单UUID*/
	public String getUuid() {
		return uuid;
	}
	/**订单UUID*/
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**交易大类（1原油；2成品油；3化工品）*/
	public String getTradeCategory() {
		return tradeCategory;
	}
	/**交易大类（1原油；2成品油；3化工品）*/
	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}
	/**单证类型*/
	public void setDocumentType(String documentType){
		this.documentType=documentType;
	}
	/**单证类型*/
	public String getDocumentType(){
		return this.documentType;
	}
	/**文件信息*/
	public ArrayList<OrderDocumentFile> getFileInfoList() {
		return fileInfoList;
	}
	/**文件信息*/
	public void setFileInfoList(ArrayList<OrderDocumentFile> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
}