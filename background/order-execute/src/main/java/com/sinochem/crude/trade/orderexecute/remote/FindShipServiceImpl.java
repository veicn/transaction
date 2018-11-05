package com.sinochem.crude.trade.orderexecute.remote;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;

@Service("iFindShipService")
public class FindShipServiceImpl implements IFindShipService {

	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 推送订单信息
	 */
	@Override
	public FindShipOrderResult sendOrderInfo(Integer pageNum, Integer pageSize, Long memberId) {
		
		FindShipOrderResult result = new FindShipOrderResult();
		
		try {		
			// 订单信息查询
			PageHelper.startPage(pageNum, pageSize);
			Page<Map<String,Object>> page = (Page<Map<String, Object>>)orderMapper.selectFindShipRecords(memberId);
			List<ForShipOrderInfo> list = BeanConvertUtils.mapToBeanInList(page, ForShipOrderInfo.class);
			
			result.setTotal(page.getTotal());
			result.setOrderInfoList(list);
			
		} catch (BizException e){
			throw new BizException();
		} catch (Exception e) {
			throw new BizException();
		}

		return result;
	}

	/**
	 * 推送订单详情
	 */
	@Override
	public ForShipOrderInfo sendOrderDetail(String orderNo) {

		ForShipOrderInfo forShipOrderInfo = new ForShipOrderInfo();
		
		try {			
			Map<String, Object> orderInfoMap = orderMapper.selectFindShipRecordByOrderNo(orderNo);
			forShipOrderInfo = BeanConvertUtils.mapToBean(orderInfoMap, ForShipOrderInfo.class);
		} catch (BizException e) {
			throw new BizException();
		} catch (Exception e) {
			throw new BizException();
		}
		return forShipOrderInfo;
	}

	@Override
	public void generateCharterDemand(String orderNo) throws com.sinochem.it.b2b.common.exception.BizException {
		Order update = new Order(); 
		update.setOrderNo(orderNo);
		update.setStatus(OrderStatusEnum.STATUS_1_2.getCode());
		
		int num = orderMapper.updateRecordByOrderNo(update);
		if(num <= 0) {
			throw new com.sinochem.it.b2b.common.exception.BizException("更新失败，请检查参数是否正确");
		}
	}

	@Override
	public void cancelCharter(String orderNo) throws com.sinochem.it.b2b.common.exception.BizException {
		Order update = new Order(); 
		update.setOrderNo(orderNo);
		update.setStatus(OrderStatusEnum.STATUS_1.getCode());
		
		int num = orderMapper.updateRecordByOrderNo(update);
		if(num <= 0) {
			throw new com.sinochem.it.b2b.common.exception.BizException("更新失败，请检查参数是否正确");
		}
	}
}
