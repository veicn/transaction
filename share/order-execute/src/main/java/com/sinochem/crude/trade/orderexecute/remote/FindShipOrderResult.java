package com.sinochem.crude.trade.orderexecute.remote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 推送订单相关信息结果
 * @author wangxinran
 *
 */
public class FindShipOrderResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private Long total;
	
	/**
	 * 查询结果
	 */
	private List<ForShipOrderInfo> orderInfoList = new ArrayList<ForShipOrderInfo>();

	/**
	 * 总记录数
	 * @return
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * 总记录数
	 * @param total
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * 查询结果
	 * @return
	 */
	public List<ForShipOrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	/**
	 * 查询结果
	 * @param orderInfoList
	 */
	public void setOrderInfoList(List<ForShipOrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}
}
