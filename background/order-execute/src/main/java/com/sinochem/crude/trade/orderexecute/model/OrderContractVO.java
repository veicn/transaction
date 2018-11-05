package com.sinochem.crude.trade.orderexecute.model;

import java.util.List;

import com.sinochem.crude.trade.orderexecute.domain.OrderContract;

public class OrderContractVO extends OrderContract {

	private static final long serialVersionUID = 1L;
	
	/** 跳转url */
	private String redirect;
	/** 订单UUID */
	String ouid;
	
	/** 合同附件名*/
	List<OrderContractFileVO> contractFileList;

	public List<OrderContractFileVO> getContractFileList() {
		return contractFileList;
	}

	public void setContractFileList(List<OrderContractFileVO> contractFileList) {
		this.contractFileList = contractFileList;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}
}