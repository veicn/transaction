package com.sinochem.crude.trade.listed.domain;

import java.util.Date;

/**
 * 报价单修改记录
 * 
 * @author kangkai
 *
 */
public class DemandBiddingHistory {

    /**
     * 主键
     */
	private Long id;

    /**
     * demand表主键
     */
	private Long demandId;

    /**
     * 变更时间
     */
	private Date updateTime;

    /**
     * 变更人
     */
	private Long updater;

    /**
     * 变更字段
     */
	private String item;

    /**
     * 变更前值
     */
	private String valueOld;

    /**
     * 变更后值
     */
	private String valueNew;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValueOld() {
		return valueOld;
	}

	public void setValueOld(String valueOld) {
		this.valueOld = valueOld;
	}

	public String getValueNew() {
		return valueNew;
	}

	public void setValueNew(String valueNew) {
		this.valueNew = valueNew;
	}

}
