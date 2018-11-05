package com.sinochem.crude.trade.listed.service;

public interface DemandMessageService {
    /**
     * 定向发布
     * @param demandId
     */
    public void demandDirectionalRelease(Long demandId);

    /**
     * 发起报价
     * @param biddingId
     */
    public void biddingRelease(Long biddingId);

    /**
     * 修改报价
     * @param biddingId
     */
    public void biddingUpdate(Long biddingId);

    /**
     * 确认报价
     * @param biddingId
     * @param orderNo 订单编码
     * @param flag   是否中标 true 中标  false 未中标
     */
    public void confirmBidding(Long biddingId,String orderNo,String uuid, boolean flag);

}
