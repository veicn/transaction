package com.sinochem.crude.trade.listed.model.vo.deprecate;

import java.io.Serializable;


/**
 * Created by wangn on 03/01/2018
 */
@Deprecated
public class BiddingOutVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 单号
	 */
	private String demandNo;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 采购需求方
	 */
	private String bEnterpriseName;

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
	 * 计价基准
	 */
	private String valuationBase;

	/**
	 * 付款条款
	 */
	private String payItem;

	/**
	 * 计价期
	 */
	private String valuationProidType;
	/**
	 * 到货期
	 */
	private String arrivalDate;

	/**
	 * 计价公式
	 */
	private String valuationFormula;

	/**
	 * 状态
	 * 包括需求状态和报价状态
	 * 0：删除
	 * 需求状态：1：新建  2：发布  3：完成
	 * 报价状态：10：报价  20：中标  30：结束
	 */
	private Integer status;

	private Long imgEMemberId;
	
	/**
     * 报价类型，1-意向报价，2-正式报价
     */
    private Integer biddingType;

	public Long getImgEMemberId() {
		return imgEMemberId;
	}

	public void setImgEMemberId(Long imgEMemberId) {
		this.imgEMemberId = imgEMemberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getbEnterpriseName() {
		return bEnterpriseName;
	}

	public void setbEnterpriseName(String bEnterpriseName) {
		this.bEnterpriseName = bEnterpriseName;
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

	public String getValuationBase() {
		return valuationBase;
	}

	public void setValuationBase(String valuationBase) {
		this.valuationBase = valuationBase;
	}

	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public String getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(String valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDemandNo() {
		return demandNo;
	}

	public void setDemandNo(String demandNo) {
		this.demandNo = demandNo;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
        this.biddingType = biddingType;
    }
}
