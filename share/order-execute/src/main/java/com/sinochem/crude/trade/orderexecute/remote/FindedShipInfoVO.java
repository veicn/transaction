package com.sinochem.crude.trade.orderexecute.remote;

import java.io.Serializable;

/**
 * 租船信息VO
 * 
 * @author wangxinran
 *
 */
public class FindedShipInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456631868131588400L;

	/** 代理协议UUID */
	private String argeementUuid;

	/** 订单编号 */
	private String orderNo;

	/** 船舶类型 */
	private String type;

	/** 船舶名称 */
	private String name;

	/** 代理协议UUID */
	public void setArgeementUuid(String argeementUuid) {
		this.argeementUuid = argeementUuid;
	}

	/** 代理协议UUID */
	public String getArgeementUuid() {
		return this.argeementUuid;
	}

	/** 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/** 订单编号 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/** 船舶类型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 船舶类型 */
	public String getType() {
		return this.type;
	}

	/** 船舶名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 船舶名称 */
	public String getName() {
		return this.name;
	}
}
