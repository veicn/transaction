package com.sinochem.crude.trade.listed.model.vo;

import java.util.List;


public class DemandPageVO {


	private String bizType;
	private String pageType;
	private DemandVO demand;
	private DemandShipVO ship;
	private DemandRelevanterSupplierVO provider;
	private DemandRelevanterAgentVO merchant;
	private List<DemandShipBerthVO> demandShipBerthList;
	private List<CrudeOilVO> crudeOil;
	private List<DemandBiddingHistoryVO> biddingHistory;
	private Integer stopBid;
	private Integer oilSaleFlag;
	
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public DemandVO getDemand() {
		return demand;
	}

	public void setDemand(DemandVO demand) {
		this.demand = demand;
	}

	public DemandShipVO getShip() {
		return ship;
	}

	public void setShip(DemandShipVO ship) {
		this.ship = ship;
	}

	public DemandRelevanterSupplierVO getProvider() {
		return provider;
	}

	public void setProvider(DemandRelevanterSupplierVO provider) {
		this.provider = provider;
	}

	public DemandRelevanterAgentVO getMerchant() {
		return merchant;
	}

	public void setMerchant(DemandRelevanterAgentVO merchant) {
		this.merchant = merchant;
	}

	public List<DemandShipBerthVO> getDemandShipBerthList() {
		return demandShipBerthList;
	}

	public void setDemandShipBerthList(List<DemandShipBerthVO> demandShipBerthList) {
		this.demandShipBerthList = demandShipBerthList;
	}

	public List<CrudeOilVO> getCrudeOil() {
		return crudeOil;
	}

	public void setCrudeOil(List<CrudeOilVO> crudeOil) {
		this.crudeOil = crudeOil;
	}

	public List<DemandBiddingHistoryVO> getBiddingHistory() {
		return biddingHistory;
	}

	public void setBiddingHistory(List<DemandBiddingHistoryVO> biddingHistory) {
		this.biddingHistory = biddingHistory;
	}

	public Integer getStopBid() {
		return stopBid;
	}

	public void setStopBid(Integer stopBid) {
		this.stopBid = stopBid;
	}

	public Integer getOilSaleFlag() {
		return oilSaleFlag;
	}

	public void setOilSaleFlag(Integer oilSaleFlag) {
		this.oilSaleFlag = oilSaleFlag;
	}
}
