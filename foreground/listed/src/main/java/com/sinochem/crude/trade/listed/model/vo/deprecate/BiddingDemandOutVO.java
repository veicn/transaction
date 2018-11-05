package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BiddingDemandOutVO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 单据编号
	 */
	private String demandNo;

	/**
	 * 供应商
	 */
	private String pEnterpriseName;

	/**
	 * 原油油种
	 */
	private String oil;

	/**
	 * 数量
	 */
	private String num;

	/**
	 * 贸易条款
	 */
	private String tradeItem;

	/**
	 * 计价公式
	 */
	private String valuationFormula;

	/**
	 * 计价期类型
	 */
	private String valuationProidType;

	/**
	 * 付款条款
	 */
	private String payItem;

	/**
	 * 到货开始时间
	 */
	private String dischargeStartTime;

	/**
	 * 到货结束时间
	 */
	private String dischargeEndTime;

	private String createTime;

	/**
	 * 状态
	 * 包括需求状态和报价状态
	 * 0：删除
	 * 需求状态：1：新建  2：发布  3：完成
	 * 报价状态：10：报价  20：中标  30：结束
	 */
	private Integer status;
	
	/**
     * 报价类型，1-意向报价，2-正式报价
     */
    private Integer biddingType;

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

	public String getpEnterpriseName() {
		return pEnterpriseName;
	}

	public void setpEnterpriseName(String pEnterpriseName) {
		this.pEnterpriseName = pEnterpriseName;
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

	public String getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(String valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public String getDischargeStartTime() {
		return dischargeStartTime;
	}

	public void setDischargeStartTime(String dischargeStartTime) {
		this.dischargeStartTime = dischargeStartTime;
	}

	public String getDischargeEndTime() {
		return dischargeEndTime;
	}

	public void setDischargeEndTime(String dischargeEndTime) {
		this.dischargeEndTime = dischargeEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public Integer getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
        this.biddingType = biddingType;
    }
}
