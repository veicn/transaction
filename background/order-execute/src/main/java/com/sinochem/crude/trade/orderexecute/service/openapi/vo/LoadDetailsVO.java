package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;

/**
 * 装港明细
 */
public class LoadDetailsVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 装港起始
	 */
	private String from;
	
	/**
	 * 装港结束
	 */
	private String to;
	
	/**
	 * 装港港口
	 */
	private String port;
	
	/**
	 * 装港净重MT
	 */
	private String net_mt;
	
	/**
	 * 装港毛重MT
	 */
	private String gross_mt;
	
	/**
	 * 装港净重BBL
	 */
	private String net_bbl;
	
	/**
	 * 装港毛重 BBL
	 */
	private String gross_bbl;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getNet_mt() {
		return net_mt;
	}

	public void setNet_mt(String net_mt) {
		this.net_mt = net_mt;
	}

	public String getGross_mt() {
		return gross_mt;
	}

	public void setGross_mt(String gross_mt) {
		this.gross_mt = gross_mt;
	}

	public String getNet_bbl() {
		return net_bbl;
	}

	public void setNet_bbl(String net_bbl) {
		this.net_bbl = net_bbl;
	}

	public String getGross_bbl() {
		return gross_bbl;
	}

	public void setGross_bbl(String gross_bbl) {
		this.gross_bbl = gross_bbl;
	}
}
