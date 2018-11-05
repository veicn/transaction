package com.sinochem.crude.trade.listed.model.vo;

import com.sinochem.crude.trade.listed.domain.DemandShip;
/**
 * 采购需求信息
 * @author x
 */
public class BuyingDemandVO {
	
	   /**
     * 数量
     */
    private Long num;
	  /**
     * 溢短装
     */
    private String numfloat;
    
    /**
     * 贸易条款
     */
    private String tradeItem;
    /**
     * 计价基准
     */
    private String valuationBase;
    
    
    /**
     * 计价期类型（全月、期间、期末）
     */
    private String valuationProidType;
    
    
    /**
     * 付款日期
     */
    private String payTime;
    
    //航运信息
    private DemandShip demanShip;
    
    /**
     * 备注
     */
    private String remark;

    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
     * 采购方式
     */
    private Integer purchaseMode;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getNumfloat() {
		return numfloat;
	}

	public void setNumfloat(String numfloat) {
		this.numfloat = numfloat;
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

	public String getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(String valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public DemandShip getDemanShip() {
		return demanShip;
	}

	public void setDemanShip(DemandShip demanShip) {
		this.demanShip = demanShip;
	}

	public Integer getPurchaseMode() {
		return purchaseMode;
	}

	public void setPurchaseMode(Integer purchaseMode) {
		this.purchaseMode = purchaseMode;
	}
    
    
    


}
