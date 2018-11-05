package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.model.query.LongContractQuery;
import com.sinochem.crude.trade.order.model.query.StatisticsQuery;
import com.sinochem.crude.trade.order.model.result.*;
import com.sinochem.crude.trade.order.model.query.ContractQuery;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ContractMapper {
    /**
     *根据主键删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     *
     * 插入合约表
     *
     */
    Long insert(Contract record);

    /**
     *
     * 按不为空的字段插入合约表
     *
     */
    Long insertSelective(Contract record);

    /**
     *
     * 根据主键查询
     *
     */
    Contract selectByPrimaryKey(@Param(value="id")Long id);

    /**
     *
     * 根据合约表中的不为空的字段更新数据
     *
     */
    void updateByPrimaryKeySelective(Contract record);

    /**
     *
     * 根据主键更新合约表
     *
     */
    void updateByPrimaryKey(Contract record);

    /**
     * 查询合约表list
     */
    List<Contract> selectByContractQuery(ContractQuery contractQuery);

    /**
     * 查询订单list
     */
    List<ContractListResult> uniteSelectByPrimaryKey(ContractQuery contractQuery);

    /**
     * 根据订单状态统计数量
     */
    OrderStatusCountResult countOrderStatus();

    /**
     * 关联船务查询商品信息
     */
    ContractProductResult queryOrderInfoProduct(@Param(value="contractId")Long contractId);

    /**
     * 根据订单号查询合约表
     * @param uuid
     * @return
     */
    Contract selectByUUID(@Param(value="uuid")String uuid);
    /**
     * 根据订单号查询合约表
     * @param orderNo
     * @return
     */
    Contract selectByOrderNo(@Param(value="orderNo")String orderNo);


    /**
     * 查询长协列表
     * @param query
     * @return
     */
    List<LongContractQueryResult> queryByLongContract(LongContractQuery query);
    /**
     *
     * 根据需求单号查询订单
     *
     */
    Contract selectByBiddingId(@Param(value="biddingId")Long biddingId);

    /**
     * 相关人原油条数
     * @return
     * @param memberid
     */
    Integer selectCountCrude(@Param("memberId") Long memberId);

    /**
     * 相关人成品油条数
     * @return
     * @param memberid
     */
    Integer selectCountProduct(@Param("memberId") Long memberId);

    /**
     * 根据id修改订单状态
     * @param orderStatus
     * @param id
     */
    void updateOrderStatusById(@Param("orderStatus") String orderStatus, @Param("id") Long id);

    /**
     * 统计交易数量
     * @param statisticsQuery
     * @return
     */
    List<CrudeStatisticsResult> statisticeDealNumData(StatisticsQuery statisticsQuery);
}