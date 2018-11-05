package com.sinochem.crude.trade.shipping.domain.po;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/11
 **/
public class MessageSheet extends  BasePo{
    //用户ID
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    //订单号
    private String orderNumber;
    //船名
    private String vesselName;

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    //商品
    private String product;
    //质量
    private String quantity;
    //装货港口
    private String completedLoading;

    private String laycan;

    private String typeCode;

    private String allFast;

    private String nor;

    private String buyer;

    private String agreementCd;
    
    private String hyperlink;
    
    private String enterprise;
    
    private String laycanStrat;
    
    private String laycanEnd;
    
    private String merchantNm;
    
    private String confirmationCD;
    
    
    
    public String getMerchantNm() {
		return merchantNm;
	}

	public void setMerchantNm(String merchantNm) {
		this.merchantNm = merchantNm;
	}

	public String getConfirmationCD() {
		return confirmationCD;
	}

	public void setConfirmationCD(String confirmationCD) {
		this.confirmationCD = confirmationCD;
	}

	public String getLaycanStrat() {
		return laycanStrat;
	}

	public void setLaycanStrat(String laycanStrat) {
		this.laycanStrat = laycanStrat;
	}

	public String getLaycanEnd() {
		return laycanEnd;
	}

	public void setLaycanEnd(String laycanEnd) {
		this.laycanEnd = laycanEnd;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

    private String sailed;

    private String confirmationSheetCd;

    private String commencedLoading;

    public String getCommencedLoading() {
        return commencedLoading;
    }

    public void setCommencedLoading(String commencedLoading) {
        this.commencedLoading = commencedLoading;
    }

    public String getConfirmationSheetCd() {
        return confirmationSheetCd;

    }

    public void setConfirmationSheetCd(String confirmationSheetCd) {
        this.confirmationSheetCd = confirmationSheetCd;
    }

    public String getSailed() {
        return sailed;
    }

    public void setSailed(String sailed) {
        this.sailed = sailed;
    }

    public String getAgreementCd() {
        return agreementCd;
    }

    public void setAgreementCd(String agreementCd) {
        this.agreementCd = agreementCd;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDemandsCd() {
        return demandsCd;
    }

    public void setDemandsCd(String demandsCd) {
        this.demandsCd = demandsCd;
    }

    private String demandsCd;


    public String getNor() {
        return nor;
    }

    public void setNor(String nor) {
        this.nor = nor;
    }

    public String getAllFast() {
        return allFast;
    }

    public void setAllFast(String allFast) {
        this.allFast = allFast;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }


    public String getLaycan() {
        return laycan;
    }

    public void setLaycan(String laycan) {
        this.laycan = laycan;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCompletedLoading() {
        return completedLoading;
    }

    public void setCompletedLoading(String completedLoading) {
        this.completedLoading = completedLoading;
    }
}
