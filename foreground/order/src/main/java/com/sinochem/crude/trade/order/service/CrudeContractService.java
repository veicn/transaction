package com.sinochem.crude.trade.order.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.model.form.ContractListQueryForm;
import com.sinochem.crude.trade.order.model.query.StatisticsQuery;
import com.sinochem.crude.trade.order.model.result.OrderStatusCountResult;
import com.sinochem.crude.trade.order.model.vo.DealNumStatisticsVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * @Description:
 * @Author : jasonxu
 * @Date: 15/11/2017
 */
public interface CrudeContractService {

	/**
	 * 根据主键id查询订单详情
	 * 
	 * @param contractId
	 * @return
	 */
	Contract queryContractInfo(Long contractId);

	/**
	 * 根据主键id查询订单详情
	 *
	 * @param orderId
	 * @return
	 */
	Contract queryContractInfoByOrderId(String orderId);

	/**
	 * 查询订单list
	 * 
	 * @param user
	 *            ,contractQuery
	 * @return
	 * @throws BizException
	 */
	PageInfoResult queryContractUnite(CurrentUser user,
                                      ContractListQueryForm contractListQueryForm, PageInfo query)
			throws BizException;

	/**
	 * 统计合约状态条数
	 * 
	 * @return
	 */
	OrderStatusCountResult getCountOrderStatus();

	/**
	 * 根据UUID查询订单详情
	 * 
	 * @param uuid
	 * @return
	 */
	Contract queryContractInfoByUUID(String uuid);

	/**
	 *
	 * @param contractId
	 * @param oper
	 */
	void pushOrderInfo(Long contractId, Long oper) throws BizException;

	/**
	 * 统计油品交易数据
	 * @param statisticsQuery
	 * @return
	 */
	List<DealNumStatisticsVO> statisticsOilDealDatas(StatisticsQuery statisticsQuery);

	void updateOrderStatusById(String string, Long id);
}
