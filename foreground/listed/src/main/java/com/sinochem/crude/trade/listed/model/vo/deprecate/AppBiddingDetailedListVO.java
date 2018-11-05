package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;

@Deprecated
public class AppBiddingDetailedListVO implements Serializable {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 单据编号
	 */
	private String demandNo;

	/**
	 * 油种
	 */
	private String oil;

	/**
	 * 数量
	 */
	private String num;

	/**
	 * 购买商
	 */
	private String bEnterpriseName;

	/**
	 * 联系人
	 */
	private String bContacter;

	/**
	 * 贸易条款
	 */
	private String tradeItem;

	/**
	 * 计价公式
	 */
	private String valuationFormula;

	/**
	 * 贴水
	 */
	private String agio;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 状态 包括需求状态和报价状态 0：删除 需求状态：1：新建 2：发布 3：完成 报价状态：10：报价 20：中标 30：结束
	 */
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDemandNo() {
		return demandNo;
	}

	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getbEnterpriseName() {
		return bEnterpriseName;
	}

	public void setbEnterpriseName(String bEnterpriseName) {
		this.bEnterpriseName = bEnterpriseName;
	}

	public String getbContacter() {
		return bContacter;
	}

	public void setbContacter(String bContacter) {
		this.bContacter = bContacter;
	}

	public String getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(String tradeItem) {
		this.tradeItem = tradeItem;
	}

	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public String getAgio() {
		return agio;
	}

	public void setAgio(String agio) {
		this.agio = agio;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
