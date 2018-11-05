package com.sinochem.crude.trade.shipagent.model.query;

import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author admin1
 * @date 2018/9/19
 */
public class TShipagentDocumentQuery extends TShipagentDocument implements Serializable {


	private static final long serialVersionUID = 1L;


	private String sofStatus;
	private String port;
	private String goodsName;
	private String metricTon;
	private String sofFileStatus;
	private String blFileStatus;
	private String blStatus;

	/**外贸合同号*/
	private String purchaseContractNo;
	/**航次*/
	private String voyage;
	/**imo*/
	private String imo;
	/**船名*/
	private String vessel;
	/** 单证时间*/
	private String blNo;
	/** 单证状态*/
	private String status;
	/** 时间*/
	private String queryDate;

	private Date sofDate;


	private Integer pageNum = Integer.valueOf(1);
	private Integer pageSize = Integer.valueOf(10);
	@JsonUnwrapped
	private SimplePageInfo simplePageInfo;


	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getSofStatus() {
		return sofStatus;
	}

	public void setSofStatus(String sofStatus) {
		this.sofStatus = sofStatus;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getMetricTon() {
		return metricTon;
	}

	public void setMetricTon(String metricTon) {
		this.metricTon = metricTon;
	}

	public String getSofFileStatus() {
		return sofFileStatus;
	}

	public void setSofFileStatus(String sofFileStatus) {
		this.sofFileStatus = sofFileStatus;
	}

	public String getBlFileStatus() {
		return blFileStatus;
	}

	public void setBlFileStatus(String blFileStatus) {
		this.blFileStatus = blFileStatus;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getBlStatus() {
		return blStatus;
	}

	public void setBlStatus(String blStatus) {
		this.blStatus = blStatus;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public SimplePageInfo getSimplePageInfo() {
		return simplePageInfo;
	}

	public void setSimplePageInfo(SimplePageInfo simplePageInfo) {
		this.simplePageInfo = simplePageInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}


	public String getPurchaseContractNo() {
		return purchaseContractNo;
	}

	public void setPurchaseContractNo(String purchaseContractNo) {
		this.purchaseContractNo = purchaseContractNo;
	}

	public Date getSofDate() {
		return sofDate;
	}

	public void setSofDate(Date sofDate) {
		this.sofDate = sofDate;
	}
}
