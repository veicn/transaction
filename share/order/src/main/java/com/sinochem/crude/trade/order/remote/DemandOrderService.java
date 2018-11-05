package com.sinochem.crude.trade.order.remote;


import com.sinochem.it.b2b.common.exception.BizException;

/**
 * Created by sijliu on 25/11/2017.
 */
public interface DemandOrderService {

    /**
     * 生成订单
     * @param demandOrderVo 挂单相关数据
     * @return 返回订单短号
     */
    public DemandOrderReturnVO createOrderByDemandData(DemandOrderVo demandOrderVo, UserVo userVo) throws BizException;

    /**
     * 确认订单
     * @param orderNo
     */
    public void confirmContract(String orderNo, Long oper, String token) throws BizException;

}
