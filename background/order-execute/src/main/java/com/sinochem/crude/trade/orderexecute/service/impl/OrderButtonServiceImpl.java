package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.TransportStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderButtonMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.service.OrderButtonService;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class OrderButtonServiceImpl implements OrderButtonService {
	@Autowired
	private OrderButtonMapper orderButtonMapper;
	@Autowired
	private OrderStatusService statusService;
	
	public OrderButtonMapper getMapper(){
		return orderButtonMapper;
	}

	/**
	 * 取得可以显示的按钮列表
	 */
	@Override
	public List<String> getButtonList(Order order, CurrentUser user, boolean isSeller) {
		List<String> buttonList = new ArrayList<>();
		
		Long orderId = order.getId();
		
		if (orderId == null || orderId == 0) {
			return new ArrayList<>();
		}
		
		if (user == null) {
			return new ArrayList<>();
		}
		
		if("00".equals(order.getStatus())){
			return new ArrayList<>();
		}
		//获取物流状态
		int transStatus = statusService.getCurrentStatus(order.getTradeId(), TransportStatusEnum.ORDER_STATUS_2.getCode());
		//至少是装船后，会有装卸港损益提醒
		if(transStatus >= Integer.valueOf(TransportStatusEnum.ORDER_STATUS_ITEM_2_2.getCode())) {
			buttonList.add("sytx");
		}
		
		if(isSeller) {
			//卖家按钮列表
			getSellerButtonList(buttonList, orderId);
		} else {
			//买家按钮列表
			getBuyerButtonList(buttonList, orderId);
		}
		
		return buttonList;
	}

	private void getBuyerButtonList(List<String> buttonList, Long orderId) {
		// 合同管理-生效订单均显示
		if(checkContract(orderId)) {
			buttonList.add("htgl");
		}
		
		// 物流跟踪-订单状态为已租船-且有船务租船协议号
		if(checkLogistics(orderId)) {
			buttonList.add("wlgz");
		}	
		
		//航线查询-订单状态为以租船-且手动维护船盘无租船协议号
		if(checkRouteQuery(orderId)){
			buttonList.add("hxcx");
		}
		
		// 单证管理-生效订单均显示
		if(checkDocument(orderId)) {
			buttonList.add("dzgl");
		}
		
		// 预估对账单-已维护预估对账单-且预估对账单不是初始状态
		if(checkStatementPreBuy(orderId)) {
			buttonList.add("ygdzd");
		}			
		
		// 预估结算单-已生成预估结算单
		if(checkSettlementPreBuy(orderId)) {
			buttonList.add("ygjsd");
		}
		
		// 正式对账单-已维护正式对账单-且正式对账单不是初始状态
		if(checkStatementFinBuy(orderId)) {
			buttonList.add("zsdzd");
		}				
		
		// 正式结算单-已生成正式结算单
		if(checkSettlementFinBuy(orderId)) {
			buttonList.add("zsjsd");
		}
	}

	private void getSellerButtonList(List<String> buttonList, Long orderId) {
		// 合同管理-生效订单均显示
		if(checkContract(orderId)) {
			buttonList.add("htgl");
		}
		
		// 物流跟踪-订单状态为已租船-且有船务租船协议号
		if(checkLogistics(orderId)) {
			buttonList.add("wlgz");
		}	
		
		//航线查询-订单状态为以租船-且手动维护船盘无租船协议号
		if(checkRouteQuery(orderId)){
			buttonList.add("hxcx");
		}
		
		// 单证管理-生效订单均显示
		if(checkDocument(orderId)) {
			buttonList.add("dzgl");
		}
		
		// 费用录入-生效订单均显示
		if(checkFee(orderId)) {
			buttonList.add("fylr");
		}
		
		// 预估对账单-已维护费用信息，查询订单是否已经点价（计价类型为trigger和合约数量与拆单数量相等）
		if(checkStatementPreSell(orderId)) {
			/*if(checkIsTrigger(orderId)){
				if(checkIsQuantity(orderId)){						
					buttonList.add("ygdzd");
				}
			}else{*/						
				buttonList.add("ygdzd");
			//}
		}					
		
		// 正式对账单-已维护费用信息，查询订单是否已经点价（计价类型为trigger和合约数量与拆单数量相等）
		if(checkStatementFinSell(orderId)) {
			if(checkIsTrigger(orderId)){
				if(checkIsQuantity(orderId)){						
					buttonList.add("zsdzd");
				}
			}else{						
				buttonList.add("zsdzd");
			}
			
		}				
		
		// 预估结算单-预估对账单已确认
		if(checkSettlementPreSell(orderId)) {
			buttonList.add("ygjsd");
		}
		
		// 正式结算单-正式对账单已确认
		if(checkSettlementFinSell(orderId)) {
			buttonList.add("zsjsd");
		}
		
		// 确认收款-订单状态已结算
		if(checkReceiveAmount(orderId)) {
			buttonList.add("qrsk");
		}
	} 
	
	/**
	 * 合同管理-生效订单均显示
	 */
	private boolean checkContract(Long orderId) {
		return true;
	}

	/**
	 * 物流跟踪-订单状态为已租船-且有船务租船协议号
	 */
	private boolean checkLogistics(Long orderId) {
		int count = orderButtonMapper.checkLogistics(orderId);
		return count > 0;
	}
	
	/**
	 * 航线查询-订单状态为已租船-且已手动维护船盘无船务租船协议号
	 */
	private boolean checkRouteQuery(Long orderId) {
		int count = orderButtonMapper.checkRouteQuery(orderId);
		return count > 0;
	}

	/**
	 * 单证管理-生效订单均显示
	 */
	private boolean checkDocument(Long orderId) {
		return true;
	}

	/**
	 * 费用录入-生效订单均显示
	 */
	private boolean checkFee(Long orderId) {
		return true;
	}
	
	/**
	 * 对账单 - 计价类型为trigger
	 */
	private boolean checkIsTrigger(Long orderId) {
		int count = orderButtonMapper.checkIsTrigger(orderId);
		return count > 0;
	}
	/**
	 * 对账单 - 合约数量与拆单数量相等
	 */
	private boolean checkIsQuantity(Long orderId) {
		int count = orderButtonMapper.checkIsQuantity(orderId);
		return count > 0;
	}
	/**
	 * 预估对账单-已维护费用信息
	 */
	private boolean checkStatementPreSell(Long orderId) {
		int count = orderButtonMapper.checkFee(orderId);
		return count > 0;
	}
	
	/**
	 * 预估对账单-已维护预估对账单-且预估对账单不是初始状态
	 */
	private boolean checkStatementPreBuy(Long orderId) {
		int count = orderButtonMapper.checkStatementPreBuy(orderId);
		return count > 0;
	}
	
	/**
	 * 预估结算单-预估对账单已确认
	 */
	private boolean checkSettlementPreSell(Long orderId) {
		int count = orderButtonMapper.checkSettlementPreSell(orderId);
		return count > 0;
	}

	/**
	 * 预估结算单-已生成预估结算单
	 */
	private boolean checkSettlementPreBuy(Long orderId) {
		int count = orderButtonMapper.checkSettlementPreBuy(orderId);
		return count > 0;
	}
	
	/**
	 * 正式对账单-已维护费用信息
	 */
	private boolean checkStatementFinSell(Long orderId) {
		int count = orderButtonMapper.checkFee(orderId);
		return count > 0;
	}

	/**
	 * 正式对账单-已维护正式对账单-且正式对账单不是初始状态
	 */
	private boolean checkStatementFinBuy(Long orderId) {
		int count = orderButtonMapper.checkStatementFinBuy(orderId);
		return count > 0;
	}	
	
	/**
	 * 正式结算单-正式对账单已确认
	 */
	private boolean checkSettlementFinSell(Long orderId) {
		int count = orderButtonMapper.checkSettlementFinSell(orderId);
		return count > 0;
	}

	/**
	 * 正式结算单-已生成正式结算单
	 */
	private boolean checkSettlementFinBuy(Long orderId) {
		int count = orderButtonMapper.checkSettlementFinBuy(orderId);
		return count > 0;
	}
	
	/**
	 * 确认收款-订单状态已结算
	 */
	private boolean checkReceiveAmount(Long orderId) {
		int count = orderButtonMapper.checkReceiveAmount(orderId);
		return count > 0;
	}
		
}
