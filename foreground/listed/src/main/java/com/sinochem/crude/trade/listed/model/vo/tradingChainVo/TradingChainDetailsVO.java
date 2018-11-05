package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;

import com.sinochem.crude.trade.listed.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 贸易链详情Vo
 * 用于查询贸易链的详情然后返回给前台页面
 * Made By WangTing
 */
public class TradingChainDetailsVO {

    //贸易链信息
    private TradingChain tradingChain;
    // 商家信息（炼厂，贸易商，供应商） 操作信息
    private List<TradingChainMember> tradingChainMemberList ;
    //
    private List<TradingChainDemand> tradingChainDemandList ;
    // 草约订单
    private List<Demand> demandList;

    // 船务信息
    private List<DemandShip> demandShipList ;

    // 草约订单1 买家，供应商
    private List<DemandRelevanter> demandRelevanterList1 ;

    // 草约订单2 买家，供应商
    private List<DemandRelevanter> demandRelevanterList2 ;

    public TradingChain getTradingChain() {
        return tradingChain;
    }

    public void setTradingChain(TradingChain tradingChain) {
        this.tradingChain = tradingChain;
    }

    public List<TradingChainMember> getTradingChainMemberList() {
        return tradingChainMemberList;
    }

    public void setTradingChainMemberList(List<TradingChainMember> tradingChainMemberList) {
        this.tradingChainMemberList = tradingChainMemberList;
    }

    public List<TradingChainDemand> getTradingChainDemandList() {
        return tradingChainDemandList;
    }

    public void setTradingChainDemandList(List<TradingChainDemand> tradingChainDemandList) {
        this.tradingChainDemandList = tradingChainDemandList;
    }

    public List<Demand> getDemandList() {
        return demandList;
    }

    public void setDemandList(List<Demand> demandList) {
        this.demandList = demandList;
    }

    public List<DemandShip> getDemandShipList() {
        return demandShipList;
    }

    public void setDemandShipList(List<DemandShip> demandShipList) {
        this.demandShipList = demandShipList;
    }

    public List<DemandRelevanter> getDemandRelevanterList1() {
        return demandRelevanterList1;
    }

    public void setDemandRelevanterList1(List<DemandRelevanter> demandRelevanterList1) {
        this.demandRelevanterList1 = demandRelevanterList1;
    }

    public List<DemandRelevanter> getDemandRelevanterList2() {
        return demandRelevanterList2;
    }

    public void setDemandRelevanterList2(List<DemandRelevanter> demandRelevanterList2) {
        this.demandRelevanterList2 = demandRelevanterList2;
    }
}
