package com.sinochem.crude.trade.orderexecute.model;

import java.util.List;

import com.sinochem.crude.trade.orderexecute.domain.OrderFee;

public class OrderFeeVO extends OrderFee {

	private static final long serialVersionUID = 1L;	
	private List<OrderFeeItemVO> feeItemList ;
	public List<OrderFeeItemVO> getFeeItemList() {
		return feeItemList;
	}
	public void setFeeItemList(List<OrderFeeItemVO> feeItemList) {
		this.feeItemList = feeItemList;
	}
	
}