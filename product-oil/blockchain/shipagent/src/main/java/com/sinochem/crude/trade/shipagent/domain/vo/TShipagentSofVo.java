package com.sinochem.crude.trade.shipagent.domain.vo;


import com.sinochem.crude.trade.shipagent.domain.TShipagentSofDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin1
 * @date 2018/10/11
 */
public class TShipagentSofVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fullName;

	private String englishName;

	private String logo;


	/**任务标识*/
	private Long taskId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	private Long id;

	private String uuid;

	private Long enterpriseId;

	private String vessel;

	private String voyage;

	private String imo;

	private String goodsName;

	private BigDecimal metricTon;

	private Date sofDate;

	private String port;

	private String contactPerson;

	private String contactNumber;

	private String remark;

	private BigDecimal shipGrossWeight;

	private BigDecimal shipNetWeight;

	private String aliveFlag;

	private String status;

	private Long createUser;

	private Date createDate;

	private Long updateUser;

	private Date updateDate;

	private List<TShipagentSofDetail> detailList;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getMetricTon() {
		return metricTon;
	}

	public void setMetricTon(BigDecimal metricTon) {
		this.metricTon = metricTon;
	}

	public Date getSofDate() {
		return sofDate;
	}

	public void setSofDate(Date sofDate) {
		this.sofDate = sofDate;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getShipGrossWeight() {
		return shipGrossWeight;
	}

	public void setShipGrossWeight(BigDecimal shipGrossWeight) {
		this.shipGrossWeight = shipGrossWeight;
	}

	public BigDecimal getShipNetWeight() {
		return shipNetWeight;
	}

	public void setShipNetWeight(BigDecimal shipNetWeight) {
		this.shipNetWeight = shipNetWeight;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<TShipagentSofDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TShipagentSofDetail> detailList) {
		this.detailList = detailList;
	}
}
