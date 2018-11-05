package com.sinochem.crude.trade.listed.model.vo.deprecate;

import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;

/**
 * 单个成品油投标详情的VO
 * @author Yichen Zhao
 * date: 20180103
 */
@Deprecated
public class BiddingDetailVO {
    private String bidding;

    //资源信息
    private DemandVO demandVO;

    //装船信息
    private DemandShipVO[] demandShipsVO;

    /*getters and setters*/
    public DemandVO getDemandVO() {
        return demandVO;
    }

    public void setDemandVO(DemandVO demandVO) {
        this.demandVO = demandVO;
    }

    public DemandShipVO[] getDemandShipsVO() {
        return demandShipsVO;
    }

    public void setDemandShipsVO(DemandShipVO[] demandShipsVO) {
        this.demandShipsVO = demandShipsVO;
    }

    public String getBidding() {
        return bidding;
    }

    public void setBidding(String bidding) {
        this.bidding = bidding;
    }
}
