package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;

/**
 * 订单状态统计VO
 * @author hexinfang
 *
 */
public class CountOrderStatusVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1688002866273800239L;
	
	/** 状态代码 */
	private String statusCode;
	/** 统计条数 */
	private Integer count;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
