package com.sinochem.crude.trade.orderexecute.model;

import java.util.Date;

public class InspectionOrderListVO {
	/**
	 * 卸港ID
	 */
	private Long id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 船名
	 */
	private String vessel;
	/**
	 * 港口
	 */
	private String dischargePort;
	/**
	 * 码头
	 */
	private String terminal;
	/**
	 * 品种
	 */
	private String grade;
	/**
	 * 数量
	 */
	private String qty;
	/**
	 * 到货开始时间
	 */
	private Date ddrStart;
	/**
	 * 到货结束时间
	 */
	private Date ddrEnd;
	
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getDischargePort() {
		return dischargePort;
	}
	public void setDischargePort(String dischargePort) {
		this.dischargePort = dischargePort;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public Date getDdrStart() {
		return ddrStart;
	}
	public void setDdrStart(Date ddrStart) {
		this.ddrStart = ddrStart;
	}
	public Date getDdrEnd() {
		return ddrEnd;
	}
	public void setDdrEnd(Date ddrEnd) {
		this.ddrEnd = ddrEnd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
