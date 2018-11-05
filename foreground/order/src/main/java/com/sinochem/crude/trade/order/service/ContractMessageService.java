package com.sinochem.crude.trade.order.service;

public interface ContractMessageService {
    /**
     * 取消订单申请
     * @param contractId
     */
    public void applyCancelContract(Long contractId, Long epMemberId);

    /**
     * 撤销取消订单
     * @param contractId
     */
    public void revokeCancelContract(Long contractId, Long epMemberId);

    /**
     * 确认取消订单
     * @param contractId
     */
    public void confirmCancelContract(Long contractId,boolean operStatus, Long epMemberId);

}
