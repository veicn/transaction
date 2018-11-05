package com.sinochem.crude.trade.transaction.domain;

import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息的销售单
 * @author Yichen Zhao
 * date: 20180302
 */
public class DemandSheetCombine {

    /**
     * 销售单信息
     */
    private DemandSheet demandSheet;

    /**
     * 商品信息
     */
    private ProductInfo productInfo;

    /**
     * 计价信息
     */
    private PricingInfo pricingInfo;

    /**
     * 运输信息
     */
    private TransportInfo transportInfo;

    /**
     * 买家信息
     */
    private StakeholderInfo buyerInfo;

    /**
     * 其它信息
     */
    private OtherInfo otherInfo;

    /** getters and setters */
    public DemandSheet getDemandSheet() {
        return demandSheet;
    }

    public void setDemandSheet(DemandSheet demandSheet) {
        this.demandSheet = demandSheet;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public PricingInfo getPricingInfo() {
        return pricingInfo;
    }

    public void setPricingInfo(PricingInfo pricingInfo) {
        this.pricingInfo = pricingInfo;
    }

    public TransportInfo getTransportInfo() {
        return transportInfo;
    }

    public void setTransportInfo(TransportInfo transportInfo) {
        this.transportInfo = transportInfo;
    }

    public StakeholderInfo getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(StakeholderInfo buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }
}
