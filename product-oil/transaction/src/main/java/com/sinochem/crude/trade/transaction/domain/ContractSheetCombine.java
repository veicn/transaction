package com.sinochem.crude.trade.transaction.domain;

import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息的合约单（订单）
 * @author Yichen Zhao
 * date: 20180302
 */
public class ContractSheetCombine {

    /**
     * 合约单（订单）信息
     */
    private ContractSheet contractSheet;

    /**
     * 销售单信息
     */
    private SaleSheet saleSheet;

    /**
     * 报价单信息
     */
    private BiddingSheet biddingSheet;

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
     * 买家信息
     */
    private StakeholderInfo buyerInfo;

    /**
     * 其它信息
     */
    private OtherInfo otherInfo;

    private TBrokerAppoint tBrokerAppoint;
    private TShipagentAppoint tShipagentAppoint;
    private TInspectorAppoint tInspectorAppoint;

    /** getters and setters */
    public ContractSheet getContractSheet() {
        return contractSheet;
    }

    public void setContractSheet(ContractSheet contractSheet) {
        this.contractSheet = contractSheet;
    }

    public SaleSheet getSaleSheet() {
        return saleSheet;
    }

    public void setSaleSheet(SaleSheet saleSheet) {
        this.saleSheet = saleSheet;
    }

    public BiddingSheet getBiddingSheet() {
        return biddingSheet;
    }

    public void setBiddingSheet(BiddingSheet biddingSheet) {
        this.biddingSheet = biddingSheet;
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


    public TBrokerAppoint gettBrokerAppoint() {
        return tBrokerAppoint;
    }

    public void settBrokerAppoint(TBrokerAppoint tBrokerAppoint) {
        this.tBrokerAppoint = tBrokerAppoint;
    }

    public TShipagentAppoint gettShipagentAppoint() {
        return tShipagentAppoint;
    }

    public void settShipagentAppoint(TShipagentAppoint tShipagentAppoint) {
        this.tShipagentAppoint = tShipagentAppoint;
    }

    public TInspectorAppoint gettInspectorAppoint() {
        return tInspectorAppoint;
    }

    public void settInspectorAppoint(TInspectorAppoint tInspectorAppoint) {
        this.tInspectorAppoint = tInspectorAppoint;
    }


}
