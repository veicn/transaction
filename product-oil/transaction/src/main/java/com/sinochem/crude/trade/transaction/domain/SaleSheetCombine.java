package com.sinochem.crude.trade.transaction.domain;

import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.model.vo.*;

/**
 * 组合各部分信息的销售单
 * @author Yichen Zhao
 * date: 20180302
 */
public class SaleSheetCombine {

    /**
     * 销售单信息
     */
    private SaleSheet saleSheet;

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
     * 卖家信息
     */
    private StakeholderInfo salerInfo;

    /**
     * 其它信息
     */
    private OtherInfo otherInfo;

    /** getters and setters */
    public SaleSheet getSaleSheet() {
        return saleSheet;
    }

    public void setSaleSheet(SaleSheet saleSheet) {
        this.saleSheet = saleSheet;
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

    public StakeholderInfo getSalerInfo() {
        return salerInfo;
    }

    public void setSalerInfo(StakeholderInfo salerInfo) {
        this.salerInfo = salerInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }
}
