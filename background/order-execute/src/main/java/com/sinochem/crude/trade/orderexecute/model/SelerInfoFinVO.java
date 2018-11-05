package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
/**
 * 
 * APP结算单详情接口通用对象
 */
public class SelerInfoFinVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
    private Map<String, Object> stateData;
    
    private Map<String, Object> inforData;
    
    private List<OrderFeeItem> feeDataList;
    
    private String isEdit;
    
	public Map<String, Object> getStateData() {
		return stateData;
	}
	public void setStateData(Map<String, Object> stateData) {
		this.stateData = stateData;
	}
	public Map<String, Object> getInforData() {
		return inforData;
	}
	public void setInforData(Map<String, Object> inforData) {
		this.inforData = inforData;
	}
	public List<OrderFeeItem> getFeeDataList() {
		return feeDataList;
	}
	public void setFeeDataList(List<OrderFeeItem> feeDataList) {
		this.feeDataList = feeDataList;
	}
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
