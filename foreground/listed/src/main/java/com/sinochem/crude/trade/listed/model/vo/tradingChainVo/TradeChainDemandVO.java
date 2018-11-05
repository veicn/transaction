package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;

import com.sinochem.crude.trade.listed.model.vo.*;

import javax.validation.Valid;
import java.io.Serializable;

public class  TradeChainDemandVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Valid
    private DemandVO bidding = new DemandVO();
    @Valid
    private DemandShipVO demandShip = new DemandShipVO();
    @Valid
    private DemandRelevanterBuyerVO buyerRelevanter = new DemandRelevanterBuyerVO();
    @Valid
    private DemandRelevanterSupplierVO supplierRelevanter = new DemandRelevanterSupplierVO();

    /**
     * 作为判断草约订单的顺序
     */
    private Integer ordersequence;

    public DemandVO getBidding() {
        return bidding;
    }

    public void setBidding(DemandVO bidding) {
        this.bidding = bidding;
    }

    public DemandShipVO getDemandShip() {
        return demandShip;
    }

    public void setDemandShip(DemandShipVO demandShip) {
        this.demandShip = demandShip;
    }

    public DemandRelevanterBuyerVO getBuyerRelevanter() {
        return buyerRelevanter;
    }

    public void setBuyerRelevanter(DemandRelevanterBuyerVO buyerRelevanter) {
        this.buyerRelevanter = buyerRelevanter;
    }

    public DemandRelevanterSupplierVO getSupplierRelevanter() {
        return supplierRelevanter;
    }

    public void setSupplierRelevanter(DemandRelevanterSupplierVO supplierRelevanter) {
        this.supplierRelevanter = supplierRelevanter;
    }

    public Integer getOrdersequence() {
        return ordersequence;
    }

    public void setOrdersequence(Integer ordersequence) {
        this.ordersequence = ordersequence;
    }
}
