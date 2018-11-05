package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 执行修改VO
 * @author Down
 *
 */
public class PhysicalOperationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单UUID
	 */
	private String uuid;
	
	/**
	 * 船名
	 */
	private String phy_ex_vessel;
	
	/**
	 * 预抵港日
	 */
	private String phy_ex_eta;
	
	/**
	 * 装港净重MT
	 */
	private String phy_ex_load_qty_net_mt;
	
	/**
	 * 装港毛重MT
	 */
	private String phy_ex_load_qty_gross_mt;
	
	/**
	 * 装港净重BBL
	 */
	private String phy_ex_load_qty_net_bbl;
	
	/**
	 * 装港毛重BBL
	 */
	private String phy_ex_load_qty_gross_bbl;
	
	/**
	 * 卸港毛重MT
	 */
	private String phy_ex_disp_qty_gross_mt;
	
	/**
	 * 卸港净重BBL
	 */
	private String phy_ex_disp_qty_net_bbl;
	
	/**
	 * 卸港毛重BBL
	 */
	private String phy_ex_disp_qty_gross_bbl;
	
	/**
	 * 卸港净重MT
	 */
	private String phy_ex_disp_qty_net_mt;
	
	/**
	 * 装港明细
	 */
	private List<LoadDetailsVO> load_details;
	
	/**
	 * 卸港明细
	 */
	private List<DispDetailsVO> disp_details;
	
	/**
	 * 提单日
	 */
	private Date bl_date;


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPhy_ex_vessel() {
		return phy_ex_vessel;
	}

	public void setPhy_ex_vessel(String phy_ex_vessel) {
		this.phy_ex_vessel = phy_ex_vessel;
	}

	public String getPhy_ex_eta() {
		return phy_ex_eta;
	}

	public void setPhy_ex_eta(String phy_ex_eta) {
		this.phy_ex_eta = phy_ex_eta;
	}

	public String getPhy_ex_load_qty_net_mt() {
		return phy_ex_load_qty_net_mt;
	}

	public void setPhy_ex_load_qty_net_mt(String phy_ex_load_qty_net_mt) {
		this.phy_ex_load_qty_net_mt = phy_ex_load_qty_net_mt;
	}

	public String getPhy_ex_load_qty_gross_mt() {
		return phy_ex_load_qty_gross_mt;
	}

	public void setPhy_ex_load_qty_gross_mt(String phy_ex_load_qty_gross_mt) {
		this.phy_ex_load_qty_gross_mt = phy_ex_load_qty_gross_mt;
	}

	public String getPhy_ex_load_qty_net_bbl() {
		return phy_ex_load_qty_net_bbl;
	}

	public void setPhy_ex_load_qty_net_bbl(String phy_ex_load_qty_net_bbl) {
		this.phy_ex_load_qty_net_bbl = phy_ex_load_qty_net_bbl;
	}

	public String getPhy_ex_load_qty_gross_bbl() {
		return phy_ex_load_qty_gross_bbl;
	}

	public void setPhy_ex_load_qty_gross_bbl(String phy_ex_load_qty_gross_bbl) {
		this.phy_ex_load_qty_gross_bbl = phy_ex_load_qty_gross_bbl;
	}

	public String getPhy_ex_disp_qty_gross_mt() {
		return phy_ex_disp_qty_gross_mt;
	}

	public void setPhy_ex_disp_qty_gross_mt(String phy_ex_disp_qty_gross_mt) {
		this.phy_ex_disp_qty_gross_mt = phy_ex_disp_qty_gross_mt;
	}

	public String getPhy_ex_disp_qty_net_bbl() {
		return phy_ex_disp_qty_net_bbl;
	}

	public void setPhy_ex_disp_qty_net_bbl(String phy_ex_disp_qty_net_bbl) {
		this.phy_ex_disp_qty_net_bbl = phy_ex_disp_qty_net_bbl;
	}

	public String getPhy_ex_disp_qty_gross_bbl() {
		return phy_ex_disp_qty_gross_bbl;
	}

	public void setPhy_ex_disp_qty_gross_bbl(String phy_ex_disp_qty_gross_bbl) {
		this.phy_ex_disp_qty_gross_bbl = phy_ex_disp_qty_gross_bbl;
	}

	public String getPhy_ex_disp_qty_net_mt() {
		return phy_ex_disp_qty_net_mt;
	}

	public void setPhy_ex_disp_qty_net_mt(String phy_ex_disp_qty_net_mt) {
		this.phy_ex_disp_qty_net_mt = phy_ex_disp_qty_net_mt;
	}

	public List<LoadDetailsVO> getLoad_details() {
		return load_details;
	}

	public void setLoad_details(List<LoadDetailsVO> load_details) {
		this.load_details = load_details;
	}

	public List<DispDetailsVO> getDisp_details() {
		return disp_details;
	}

	public void setDisp_details(List<DispDetailsVO> disp_details) {
		this.disp_details = disp_details;
	}

	public Date getBl_date() {
		return bl_date;
	}

	public void setBl_date(Date bl_date) {
		this.bl_date = bl_date;
	}	
}