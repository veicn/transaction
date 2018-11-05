package com.sinochem.crude.trade.orderexecute.remote;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.TransportStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.utils.PaymentTermObjectAnalyse;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipLoadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipUnloadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.DispDetailsVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.LoadDetailsVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationVO;

@Service("iShipTransportService")
public class ShipTransportServiceImpl implements IShipTransportService {

	@Autowired
	OrderShipLoadinginfoMapper orderShipLoadinginfoMapper;

	@Autowired
	OrderShipUnloadinginfoMapper orderShipUnloadinginfoMapper;

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderService orderService;

	@Autowired
	OrderShipMapper orderShipMapper;

	@Autowired
	OrderStatusService orderStatusService;

	@Autowired
	OutputService outputservice;
	
	@Autowired
	OrderMsgRemindingService msgRemindingService;
	
	@Autowired
	OrderPriceService orderPriceService;

	/**
	 * 插入装港信息
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertTransportLoading(List<TransportLoadVO> transportLoadLst,
			Long memberId) {

		// 生成订单列表和船舶列表（去重）
		Map<String, Order> orderNoToOrderMapping = new HashMap<>();
		List<TransportLoadVO> transportShipLst = new ArrayList<TransportLoadVO>();

		for (TransportLoadVO transportLoadVO : transportLoadLst) {
			if (orderNoToOrderMapping.get(transportLoadVO.getOrderNo()) == null) {
				// 验证订单有效性
				Order order = orderService.findByOrderNo(transportLoadVO.getOrderNo());
				if (order == null) {
					throw new BizException("该订单已不存在（订单编号：" + transportLoadVO.getOrderNo() + "）");
				}
				
				orderNoToOrderMapping.put(transportLoadVO.getOrderNo(), order);

				TransportLoadVO transportShip = new TransportLoadVO();
				transportShip.setOrderNo(transportLoadVO.getOrderNo());
				transportShip.setArgeementUuid(transportLoadVO
						.getArgeementUuid());
				transportShip.setType(transportLoadVO.getType());
				transportShip.setName(transportLoadVO.getName());
				transportShipLst.add(transportShip);
			}
		}

		// 录入船舶信息
		for (TransportLoadVO transportShip : transportShipLst) {

			Order order = new Order();
			order.setOrderNo(transportShip.getOrderNo());

			List<Order> orderCheckLst = orderMapper.queryByEntitys(order);

			OrderShip orderCheckShip = new OrderShip();
			orderCheckShip.setOrderId(orderCheckLst.get(0).getId());
			List<OrderShip> orderCheckShipLst = orderShipMapper
					.queryByEntitys(orderCheckShip);

			if (orderCheckShipLst.size() == 0) {
				OrderShip orderShip = new OrderShip();

				orderShip.setOrderId(orderCheckLst.get(0).getId());
				orderShip.setOrderNo(transportShip.getOrderNo());
				orderShip.setAgentUuid(transportShip.getArgeementUuid());
				orderShip.setType(transportShip.getType());
				orderShip.setName(transportShip.getName());
				orderShip.setUuid(KeyGenUtils.newUuid());
				orderShip.setCreateDate(DateTimeUtils.currentDate());
				orderShip.setUpdateDate(DateTimeUtils.currentDate());
				orderShip.setLangVer("ZH-CN");
				orderShip.setAliveFlag("1");
				orderShip.setUpdateUser(memberId);
				orderShip.setCreateUser(memberId);

				orderShipMapper.insertRecord(orderShip);
			}
		}

		// 清空装港信息
		for (String orderNo : orderNoToOrderMapping.keySet()) {

			OrderShipLoadinginfo orderShipLoadinginfoDel = new OrderShipLoadinginfo();
			orderShipLoadinginfoDel.setOrderNo(orderNo);
			orderShipLoadinginfoDel.setAliveFlag("0");
			orderShipLoadinginfoDel.setUpdateDate(DateTimeUtils.currentDate());
			orderShipLoadinginfoDel.setUpdateUser(memberId);

			orderShipLoadinginfoMapper
					.updateRecordFromShip(orderShipLoadinginfoDel);
		}

		// 录入装港信息
		for (int i = 0; i<transportLoadLst.size(); i++) {

			OrderShipLoadinginfo orderShipLoadinginfo = buildLoadingInfo(
					transportLoadLst.get(i), memberId);

			orderShipLoadinginfoMapper.insertRecord(orderShipLoadinginfo);
			
			if(i == 0 && orderShipLoadinginfo.getBlDate() != null) {
				//真实提单日信息更新，消息提醒取消
				Order orderInfo = orderNoToOrderMapping.get(orderShipLoadinginfo.getOrderNo());
				msgRemindingService.closeBillUpdateReminding(orderInfo.getId());
				//更新付款日期
				OrderPrice orderPrice = orderPriceService.findByOrderId(orderInfo.getId());
				PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(orderPrice.getPaymentTermJson());
				
				if(paymentTermObjectAnalyse.isBl()) {
					Date payDate = paymentTermObjectAnalyse.calcPayDate(orderShipLoadinginfo.getBlDate());
					if(payDate != null) {
						orderPriceService.updatePayDate(orderPrice.getOrderPriceId(), payDate);
					}
				}
			}
		}
		

		// 修改订单状态
		for (String orderNo : orderNoToOrderMapping.keySet()) {
			try {
				changeOrderStatus(orderNo, OrderStatusEnum.STATUS_3, memberId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}

		}

		// 外部系统接口调用（执行修改）
		try {
			updatePhysicalOperation(orderNoToOrderMapping.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 插入卸港信息
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertTransportUnloading(
			List<TransportUnloadVO> transportUnloadLst, Long memberId) {

		// 生成订单列表和船舶列表（去重）
		Map<String, Order> orderNoToOrderMapping = new HashMap<>();
		List<TransportUnloadVO> transportShipLst = new ArrayList<TransportUnloadVO>();

		for (TransportUnloadVO transportUnloadVO : transportUnloadLst) {

			if (orderNoToOrderMapping.get(transportUnloadVO.getOrderNo()) == null) {
				
				// 验证订单有效性
				Order order = orderService.findByOrderNo(transportUnloadVO.getOrderNo());
				if (order == null) {
					throw new BizException("该订单已不存在（订单编号：" + transportUnloadVO.getOrderNo() + "）");
				}
				orderNoToOrderMapping.put(transportUnloadVO.getOrderNo(), order);

				TransportUnloadVO transportShip = new TransportUnloadVO();
				transportShip.setOrderNo(transportUnloadVO.getOrderNo());
				transportShip.setArgeementUuid(transportUnloadVO
						.getArgeementUuid());
				transportShip.setType(transportUnloadVO.getType());
				transportShip.setName(transportUnloadVO.getName());
				transportShipLst.add(transportShip);
			}
		}

		// 录入船舶信息
		for (TransportUnloadVO transportShip : transportShipLst) {

			Order order = new Order();
			order.setOrderNo(transportShip.getOrderNo());

			List<Order> orderCheckLst = orderMapper.queryByEntitys(order);

			OrderShip orderCheckShip = new OrderShip();
			orderCheckShip.setOrderId(orderCheckLst.get(0).getId());
			List<OrderShip> orderCheckShipLst = orderShipMapper
					.queryByEntitys(orderCheckShip);

			if (orderCheckShipLst.size() == 0) {
				OrderShip orderShip = new OrderShip();

				orderShip.setOrderId(orderCheckLst.get(0).getId());
				orderShip.setOrderNo(transportShip.getOrderNo());
				orderShip.setAgentUuid(transportShip.getArgeementUuid());
				orderShip.setType(transportShip.getType());
				orderShip.setName(transportShip.getName());
				orderShip.setUuid(KeyGenUtils.newUuid());
				orderShip.setCreateDate(DateTimeUtils.currentDate());
				orderShip.setUpdateDate(DateTimeUtils.currentDate());
				orderShip.setLangVer("ZH-CN");
				orderShip.setAliveFlag("1");
				orderShip.setUpdateUser(memberId);
				orderShip.setCreateUser(memberId);

				orderShipMapper.insertRecord(orderShip);
			}
		}

		// 清空卸港信息
		for (String orderNo : orderNoToOrderMapping.keySet()) {

			OrderShipUnloadinginfo orderShipUnloadinginfoDel = new OrderShipUnloadinginfo();
			orderShipUnloadinginfoDel.setOrderNo(orderNo);
			orderShipUnloadinginfoDel.setAliveFlag("0");
			orderShipUnloadinginfoDel
					.setUpdateDate(DateTimeUtils.currentDate());
			orderShipUnloadinginfoDel.setUpdateUser(memberId);

			orderShipUnloadinginfoMapper
					.updateRecordFromShip(orderShipUnloadinginfoDel);
		}

		// 录入卸港信息
		for (int i = 0; i < transportUnloadLst.size(); i++) {

			OrderShipUnloadinginfo orderShipUnloadinginfo = buildUnoadingInfo(
					transportUnloadLst.get(i), memberId);

			orderShipUnloadinginfoMapper.insertRecord(orderShipUnloadinginfo);
		}

		// 修改订单状态
		for (String orderNo : orderNoToOrderMapping.keySet()) {
			try {
				changeOrderStatus(orderNo, OrderStatusEnum.STATUS_5, memberId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}

		// 外部系统接口调用（执行修改）
		try {
			updatePhysicalOperation(orderNoToOrderMapping.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 船舶信息信息同步
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void syncShipInfo(FindedShipInfoVO shipInfo, Long memberId) {

		if (StringUtils.isBlank(shipInfo.getOrderNo())) {
			throw new BizException("缺少参数：orderNo");
		}
		Order order = new Order();
		order.setOrderNo(shipInfo.getOrderNo());
		List<Order> orderLst = orderMapper.queryByEntitys(order);

		if (orderLst == null || orderLst.isEmpty()) {
			throw new BizException("该订单不存在或已删除");
		}
		
		Order orderInfo = orderLst.get(0);
		
		OrderShip orderShip = new OrderShip();

		orderShip.setOrderId(orderInfo.getId());
		orderShip.setOrderNo(shipInfo.getOrderNo());
		orderShip.setAgentUuid(shipInfo.getArgeementUuid());
		orderShip.setType(shipInfo.getType());
		orderShip.setName(shipInfo.getName());
		orderShip.setUuid(KeyGenUtils.newUuid());
		
		if(OrderStatusEnum.STATUS_2.getCode().compareTo(orderInfo.getStatus()) > 0) {//新增
			orderShip.setCreateUser(memberId);
			orderShip.setCreateDate(DateTimeUtils.currentDate());
			
			// 插入船舶信息
			orderShipMapper.insertRecord(orderShip);
			
			try {
				changeOrderStatus(shipInfo.getOrderNo(), OrderStatusEnum.STATUS_2,
						memberId);
			} catch (Exception e) {
				throw new BizException("同步状态机异常",e);
			}
			
		}else {//修改
			orderShip.setUpdateUser(memberId);
			orderShip.setUpdateDate(DateTimeUtils.currentDate());
			
			int num = orderShipMapper.updateRecordsByOrderNo(orderShip);
			if(num == 0) {
				throw new BizException("更新船舶信息失败");
			}
		}
	}

	/**
	 * 装港对象
	 */
	private OrderShipLoadinginfo buildLoadingInfo(
			TransportLoadVO transportLoadVO, Long memberId) {

		OrderShipLoadinginfo orderShipLoadinginfo = new OrderShipLoadinginfo();
		orderShipLoadinginfo.setBlStatus("1");//最终
		orderShipLoadinginfo.setOil(transportLoadVO.getOilType());
		orderShipLoadinginfo.setUuid(KeyGenUtils.newUuid());
		orderShipLoadinginfo.setOrderNo(transportLoadVO.getOrderNo());
		orderShipLoadinginfo.setBlDate(transportLoadVO.getBlDate());
		orderShipLoadinginfo.setBlNightstool(transportLoadVO.getBlNightstool());
		orderShipLoadinginfo.setBlHairBarrel(transportLoadVO.getBlHairBarrel());
		orderShipLoadinginfo.setBlNetTonnage(transportLoadVO.getBlNetTonnage());
		orderShipLoadinginfo.setBlHairTonnage(transportLoadVO
				.getBlHairTonnage());
		orderShipLoadinginfo.setApi(transportLoadVO.getApi());
		orderShipLoadinginfo.setWaterImpCon(transportLoadVO.getWaterImpCon());
		orderShipLoadinginfo.setWaterQuantity(transportLoadVO
				.getWaterQuantity());
		orderShipLoadinginfo.setShHairBarrel(transportLoadVO.getShHairBarrel());
		orderShipLoadinginfo.setShNetBarrel(transportLoadVO.getShNetBarrel());
		orderShipLoadinginfo.setShNetTonnage(transportLoadVO.getShNetTonnage());
		orderShipLoadinginfo.setShHairTonnage(transportLoadVO
				.getShHairTonnage());
		orderShipLoadinginfo.setRatioBarrel(transportLoadVO.getRatioBarrel());
		orderShipLoadinginfo.setRatioTonnage(transportLoadVO.getRatioTonnage());
		orderShipLoadinginfo.setQuantity(transportLoadVO.getQuantity());
		orderShipLoadinginfo.setRemark(transportLoadVO.getRemark());
		orderShipLoadinginfo.setLoadPort(transportLoadVO.getLoadPort());
		orderShipLoadinginfo.setEta(transportLoadVO.getEta());
		orderShipLoadinginfo.setExBerth(transportLoadVO.getExBerth());
		orderShipLoadinginfo.setNorDate(transportLoadVO.getNorDate());
		orderShipLoadinginfo.setWaterDate(transportLoadVO.getWaterDate());
		orderShipLoadinginfo.setAtripDate(transportLoadVO.getAtripDate());
		orderShipLoadinginfo.setBerthDate(transportLoadVO.getBerthDate());
		orderShipLoadinginfo.setAcStart(transportLoadVO.getAcStart());
		orderShipLoadinginfo.setAcFinish(transportLoadVO.getAcFinish());
		orderShipLoadinginfo.setExLeave(transportLoadVO.getExLeave());
		orderShipLoadinginfo.setAcLeave(transportLoadVO.getAcLeave());
		orderShipLoadinginfo.setRemTubeDate(transportLoadVO.getRemTubeDate());
		orderShipLoadinginfo.setInspection(transportLoadVO.getInspection());
		orderShipLoadinginfo.setAgency(transportLoadVO.getAgency());
		orderShipLoadinginfo.setCreateDate(DateTimeUtils.currentDate());
		orderShipLoadinginfo.setUpdateDate(DateTimeUtils.currentDate());
		orderShipLoadinginfo.setLangVer("ZH-CN");
		orderShipLoadinginfo.setAliveFlag("1");
		orderShipLoadinginfo.setUpdateUser(memberId);
		orderShipLoadinginfo.setCreateUser(memberId);

		return orderShipLoadinginfo;
	}

	/**
	 * 卸港对象
	 */
	private OrderShipUnloadinginfo buildUnoadingInfo(
			TransportUnloadVO transportLoadVO, Long memberId) {

		OrderShipUnloadinginfo orderShipUnloadinginfo = new OrderShipUnloadinginfo();
		orderShipUnloadinginfo.setNorStatus("1");//最终
		orderShipUnloadinginfo.setCodStatus("1");//最终
		orderShipUnloadinginfo.setUuid(KeyGenUtils.newUuid());
		orderShipUnloadinginfo.setOil(transportLoadVO.getOilType());
		orderShipUnloadinginfo.setOrderNo(transportLoadVO.getOrderNo());
		orderShipUnloadinginfo.setShipHairBar(transportLoadVO.getShipHairBar());
		orderShipUnloadinginfo.setShipHairTon(transportLoadVO.getShipHairTon());
		orderShipUnloadinginfo.setCommHairBar(transportLoadVO.getCommHairBar());
		orderShipUnloadinginfo.setCommHairTon(transportLoadVO.getCommHairTon());
		orderShipUnloadinginfo.setCommCleanBar(transportLoadVO
				.getCommCleanBar());
		orderShipUnloadinginfo.setCommCleanTon(transportLoadVO
				.getCommCleanTon());
		orderShipUnloadinginfo.setCounCleanBar(transportLoadVO
				.getCounCleanBar());
		orderShipUnloadinginfo.setCounCleanTon(transportLoadVO
				.getCounCleanTon());
		orderShipUnloadinginfo.setPotHairBar(transportLoadVO.getPotHairBar());
		orderShipUnloadinginfo.setPotHairTon(transportLoadVO.getPotHairTon());
		orderShipUnloadinginfo.setBlHairBarRate(transportLoadVO
				.getBlHairBarRate());
		orderShipUnloadinginfo.setBlHairTonRate(transportLoadVO
				.getBlHairTonRate());
		orderShipUnloadinginfo.setCounCleanBarRate(transportLoadVO
				.getCounCleanBarRate());
		orderShipUnloadinginfo.setCounCleanTonRate(transportLoadVO
				.getCounCleanTonRate());
		orderShipUnloadinginfo.setRobQuanatity(transportLoadVO
				.getRobQuanatity());
		orderShipUnloadinginfo.setRemark(transportLoadVO.getRemark());
		orderShipUnloadinginfo.setUnloadPort(transportLoadVO.getUnloadPort());
		orderShipUnloadinginfo.setEta(transportLoadVO.getEta());
		orderShipUnloadinginfo.setExBerth(transportLoadVO.getExBerth());
		orderShipUnloadinginfo.setNorDate(transportLoadVO.getNorDate());
		orderShipUnloadinginfo.setWaterDate(transportLoadVO.getWaterDate());
		orderShipUnloadinginfo.setExArrive(transportLoadVO.getExArrive());
		orderShipUnloadinginfo.setAtripDate(transportLoadVO.getAtripDate());
		orderShipUnloadinginfo.setBerthDate(transportLoadVO.getBerthDate());
		orderShipUnloadinginfo.setAcStart(transportLoadVO.getAcStart());
		orderShipUnloadinginfo.setAcFinish(transportLoadVO.getAcFinish());
		orderShipUnloadinginfo.setRemTubeDate(transportLoadVO.getRemTubeDate());
		orderShipUnloadinginfo.setExLeave(transportLoadVO.getExLeave());
		orderShipUnloadinginfo.setAcLeave(transportLoadVO.getAcLeave());
		orderShipUnloadinginfo.setInspection(transportLoadVO.getInspection());
		orderShipUnloadinginfo.setInspectionTel(transportLoadVO
				.getInspectionTel());
		orderShipUnloadinginfo.setMonitor(transportLoadVO.getMonitor());
		orderShipUnloadinginfo.setMonitorTel(transportLoadVO.getMonitorTel());
		orderShipUnloadinginfo.setAgency(transportLoadVO.getAgency());
		orderShipUnloadinginfo.setAgencyTel(transportLoadVO.getAgencyTel());
		orderShipUnloadinginfo.setRemark(transportLoadVO.getRemark());
		orderShipUnloadinginfo.setCreateDate(DateTimeUtils.currentDate());
		orderShipUnloadinginfo.setUpdateDate(DateTimeUtils.currentDate());
		orderShipUnloadinginfo.setLangVer("ZH-CN");
		orderShipUnloadinginfo.setAliveFlag("1");
		orderShipUnloadinginfo.setUpdateUser(memberId);
		orderShipUnloadinginfo.setCreateUser(memberId);

		return orderShipUnloadinginfo;
	}

	/**
	 * 订单状态变更
	 */
	private void changeOrderStatus(String orderNo, OrderStatusEnum status,
			Long memberId) throws Exception {

		Order order = new Order();
		order.setOrderNo(orderNo);

		List<Order> orderLst = orderMapper.queryByEntitys(order);

		if (orderLst.size() > 0) {

			switch (status) {
			case STATUS_2:
				if (OrderStatusEnum.STATUS_1_2.getCode().equals(
						orderLst.get(0).getStatus())) {

					// 订单状态更新
					Order changeStatusOrder = new Order();
					changeStatusOrder.setId(orderLst.get(0).getId());
					changeStatusOrder.setStatus(OrderStatusEnum.STATUS_2
							.getCode());
					changeStatusOrder
							.setUpdateDate(DateTimeUtils.currentDate());
					changeStatusOrder.setUpdateUser(memberId);
					orderMapper.updateRecordById(changeStatusOrder);

					// 调用状态机
					orderStatusService.setOrderStatus(orderLst.get(0)
							.getTradeId(), TransportStatusEnum.ORDER_STATUS_2
							.getCode(), Integer
							.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_1
									.getCode()), "", memberId);
				}

				break;
			case STATUS_3:

				// 订单状态更新
				if (OrderStatusEnum.STATUS_1.getCode().equals(
						orderLst.get(0).getStatus())
						|| OrderStatusEnum.STATUS_2.getCode().equals(
								orderLst.get(0).getStatus())) {
					Order changeStatusOrder = new Order();
					changeStatusOrder.setId(orderLst.get(0).getId());
					changeStatusOrder.setStatus(OrderStatusEnum.STATUS_3
							.getCode());
					changeStatusOrder
							.setUpdateDate(DateTimeUtils.currentDate());
					changeStatusOrder.setUpdateUser(memberId);
					orderMapper.updateRecordById(changeStatusOrder);

					// 调用状态机
					orderStatusService.setOrderStatus(orderLst.get(0)
							.getTradeId(), TransportStatusEnum.ORDER_STATUS_2
							.getCode(), Integer
							.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_2
									.getCode()), "", memberId);
				}

				break;
			case STATUS_5:

				// 订单状态更新
				if (OrderStatusEnum.STATUS_1.getCode().equals(
						orderLst.get(0).getStatus())
						|| OrderStatusEnum.STATUS_2.getCode().equals(
								orderLst.get(0).getStatus())
						|| OrderStatusEnum.STATUS_3.getCode().equals(
								orderLst.get(0).getStatus())
						|| OrderStatusEnum.STATUS_4.getCode().equals(
								orderLst.get(0).getStatus())) {
					Order changeStatusOrder = new Order();
					changeStatusOrder.setId(orderLst.get(0).getId());
					changeStatusOrder.setStatus(OrderStatusEnum.STATUS_5
							.getCode());
					changeStatusOrder
							.setUpdateDate(DateTimeUtils.currentDate());
					changeStatusOrder.setUpdateUser(memberId);
					orderMapper.updateRecordById(changeStatusOrder);

					// 调用状态机
					orderStatusService.setOrderStatus(orderLst.get(0)
							.getTradeId(), TransportStatusEnum.ORDER_STATUS_2
							.getCode(), Integer
							.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_9
									.getCode()), "", memberId);
				}

				break;
			default:
				break;
			}
		}

	}

	private void updatePhysicalOperation(Set<String> orderLst) {

		for (String orderNo : orderLst) {
			PhysicalOperationVO physicalOperation = new PhysicalOperationVO();

			physicalOperation = BeanConvertUtils.beanToBean(
					orderMapper.getTransportInfoDetails(orderNo),
					PhysicalOperationVO.class);

			List<LoadDetailsVO> loadDetails = new ArrayList<LoadDetailsVO>();
			loadDetails = BeanConvertUtils.mapToBeanInList(
					orderMapper.getLoadDetails(orderNo), LoadDetailsVO.class);
			physicalOperation.setLoad_details(loadDetails);

			List<DispDetailsVO> dispDetails = new ArrayList<DispDetailsVO>();
			dispDetails = BeanConvertUtils.mapToBeanInList(
					orderMapper.getDispDetails(orderNo), DispDetailsVO.class);
			physicalOperation.setDisp_details(dispDetails);

			Order order = new Order();
			order.setOrderNo(orderNo);

			List<Order> checkOrderLst = orderMapper.queryByEntitys(order);

			if ("1".equals(checkOrderLst.get(0).getBuyerInterfaceStatus())) {
				InterfaceSystem interfaceSystem = new InterfaceSystem();
				interfaceSystem = orderMapper.getSysNameByBuyer(orderNo);

				if (interfaceSystem != null) {

					InterfaceListVO interfaceList = new InterfaceListVO();
					interfaceList.setSysName(interfaceSystem.getSysName());

					outputservice.physicalOperationUpdate(interfaceList,
							physicalOperation);
				}
			}

			if ("1".equals(checkOrderLst.get(0).getSellerInterfaceStatus())) {
				InterfaceSystem interfaceSystem = new InterfaceSystem();
				interfaceSystem = orderMapper.getSysNameBySeller(orderNo);

				if (interfaceSystem != null) {

					InterfaceListVO interfaceList = new InterfaceListVO();
					interfaceList.setSysName(interfaceSystem.getSysName());

					outputservice.physicalOperationUpdate(interfaceList,
							physicalOperation);
				}
			}
		}
	}
}
