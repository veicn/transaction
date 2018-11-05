package com.sinochem.crude.trade.orderexecute.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.TransportStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.utils.PaymentTermObjectAnalyse;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortDischargeInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipUnloadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderShipUnloadinginfoVO;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipUnloadinginfoService;
import com.sinochem.crude.trade.transport.remote.IShipGoodsService;
import com.sinochem.crude.trade.transport.remote.UnloadGoodsVO;

import net.sf.json.JSONArray;

@Service
public class OrderShipUnloadinginfoServiceImpl implements OrderShipUnloadinginfoService {
	@Autowired
	private OrderShipUnloadinginfoMapper orderShipUnloadinginfoMapper;
	@Autowired
	private OrderShipService orderShipService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private IShipGoodsService iShipGoodsService;
	@Autowired
	private OrderMsgRemindingService msgRemindingService;
	@Autowired
	private OrderPriceService orderPriceService;
	
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	@Resource(name="systemServer")
	private URLBroker systemServer;
	
	Log log = LogFactory.getLog(OrderShipUnloadinginfoService.class);
	
	public OrderShipUnloadinginfoMapper getMapper(){
		return orderShipUnloadinginfoMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderShipUnloadinginfo ordershipunloadinginfo){
		 return orderShipUnloadinginfoMapper.insertRecord(ordershipunloadinginfo);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderShipUnloadinginfoMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderShipUnloadinginfo  record){
    	return orderShipUnloadinginfoMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderShipUnloadinginfo orderShipUnloadinginfo) throws BizException{
		if ( orderShipUnloadinginfo.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderShipUnloadinginfoMapper.updateRecordById(orderShipUnloadinginfo);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderShipUnloadinginfoMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderShipUnloadinginfo orderShipUnloadinginfo){
		return orderShipUnloadinginfoMapper.updateRecords(orderShipUnloadinginfo.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderShipUnloadinginfo findByPrimaryKey(Long id){
		return  orderShipUnloadinginfoMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderShipUnloadinginfo findByUuid(String uuid){
		return  orderShipUnloadinginfoMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderShipUnloadinginfo> queryByEntitys(OrderShipUnloadinginfo orderShipUnloadinginfo){
		return  orderShipUnloadinginfoMapper.queryByEntitys(orderShipUnloadinginfo);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderShipUnloadinginfoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderShipUnloadinginfoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderShipUnloadinginfoMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<OrderShipUnloadinginfo> findByOrderNo(String orderNo) {
		if(StringUtils.isEmpty(orderNo)){
			return new ArrayList<>();
		}
		
		OrderShipUnloadinginfo query = new OrderShipUnloadinginfo();
		query.setOrderNo(orderNo);
		
		return orderShipUnloadinginfoMapper.queryByEntitys(query);
	}
	
	/**
	 * 外部系统-同步卸港信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUnloadingInfo(PortDischargeInfoVO vo, Order order) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		// 同步卸港信息
		OrderShipUnloadinginfo orderShipUnloadinginfo = new OrderShipUnloadinginfo();
		orderShipUnloadinginfo.setUuid(KeyGenUtils.newUuid());
		orderShipUnloadinginfo.setOrderNo(order.getOrderNo());
		try {
			orderShipUnloadinginfo.setAcStart(StringUtils.isBlank(vo.getAcStart()) ?  null : format.parse(vo.getAcStart()));
			orderShipUnloadinginfo.setAcFinish(StringUtils.isBlank(vo.getAcFinish()) ? null : format.parse(vo.getAcFinish()));
			orderShipUnloadinginfo.setNorDate(StringUtils.isBlank(vo.getNorDate()) ? null : format.parse(vo.getNorDate()));
		} catch (ParseException e) {
			throw new OrderExecException("orderexecute.code.00088","请输入正确日期形式");
		}
		orderShipUnloadinginfo.setCommCleanBar(StringUtils.isBlank(vo.getCommCleanBar()) ? null : new BigDecimal(vo.getCommCleanBar()));
		orderShipUnloadinginfo.setCommCleanTon(StringUtils.isBlank(vo.getCommCleanTon()) ? null : new BigDecimal(vo.getCommCleanTon()));
		orderShipUnloadinginfo.setCounCleanBar(StringUtils.isBlank(vo.getCounCleanBar()) ? null : new BigDecimal(vo.getCounCleanBar()));
		orderShipUnloadinginfo.setCounCleanTon(StringUtils.isBlank(vo.getCounCleanTon()) ? null : new BigDecimal(vo.getCounCleanTon()));
		orderShipUnloadinginfo.setUnloadPort(vo.getUnloadPort());
		orderShipUnloadinginfo.setUpdateDate(DateTimeUtils.currentDate());
		orderShipUnloadinginfo.setUpdateUser(9999L);
		
		OrderShipUnloadinginfo checkOrderShipUnloadinginfo = orderShipUnloadinginfoMapper.findbyOrderNo(order.getOrderNo());

		if (checkOrderShipUnloadinginfo != null) {
			orderShipUnloadinginfo.setId(checkOrderShipUnloadinginfo.getId());
			
			orderShipUnloadinginfoMapper.updateRecordById(orderShipUnloadinginfo);
		} else {
			orderShipUnloadinginfo.setLangVer(Constants.LANG_VER);
			orderShipUnloadinginfo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			orderShipUnloadinginfo.setCreateDate(DateTimeUtils.currentDate());
			orderShipUnloadinginfo.setCreateUser(9999L);

			orderShipUnloadinginfoMapper.insertRecord(orderShipUnloadinginfo);
		}
		// 订单状态变更
				if (OrderStatusEnum.STATUS_1.getCode().equals(order.getStatus())
						|| OrderStatusEnum.STATUS_2.getCode().equals(order.getStatus())
						|| OrderStatusEnum.STATUS_3.getCode().equals(order.getStatus())
						|| OrderStatusEnum.STATUS_4.getCode().equals(order.getStatus())) {
					Order changeStatusOrder = new Order();
					changeStatusOrder.setId(order.getId());
					changeStatusOrder.setStatus(OrderStatusEnum.STATUS_5.getCode());
					changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
					changeStatusOrder.setUpdateUser(9999L);
					orderService.updateRecordById(changeStatusOrder);
					
					try {
						orderStatusService.setOrderStatus(order.getTradeId(), 
								TransportStatusEnum.ORDER_STATUS_2.getCode(), 
								Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_9.getCode()), 
								"", 9999L);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new BizException(e.getMessage());
					} catch (com.sinochem.it.b2b.common.exception.BizException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new BizException(e.getMessage());
					}
				}	
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateShipUnloadingInfo(OrderShipUnloadinginfoVO vo,CurrentUser user){
		try{
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNotBlank(vo.getNorDateDesc())){
				vo.setNorDate(sdf.parse(vo.getNorDateDesc()));
			}
			if(StringUtils.isNotBlank(vo.getCodDesc())){
				vo.setCod(sdf.parse(vo.getCodDesc()));
			}
			if(StringUtils.isNotBlank(vo.getAcStartDesc())){
				vo.setAcStart(sdf.parse(vo.getAcStartDesc()));
			}
			if(StringUtils.isNotBlank(vo.getAcFinishDesc())){
				vo.setAcFinish(sdf.parse(vo.getAcFinishDesc()));
			}
			
			orderShipUnloadinginfoMapper.updateRecordById(vo);
			
			OrderShip orderShip = new OrderShip();
			orderShip.setOrderNo(vo.getOrderNo());
			String shipName = "";
			List<OrderShip> shipList = orderShipService.queryByEntitys(orderShip);
			if (shipList!=null && shipList.size() > 0) {
				shipName = shipList.get(0).getName();
				String agentUuid = shipList.get(0).getAgentUuid();//代理协议号
				if(StringUtils.isNotBlank(agentUuid)){
					//同步到船务 TODO
					log.info("同步卸港信息到船务系统中......");
					OrderShipUnloadinginfo orderShipUnloading = new OrderShipUnloadinginfo();
					orderShipUnloading.setOrderNo(vo.getOrderNo());
					List<OrderShipUnloadinginfo> unloadingList = orderShipUnloadinginfoMapper.queryByEntitys(orderShipUnloading);
					List<UnloadGoodsVO> unloadGoodsVOList = new ArrayList<UnloadGoodsVO>();
					if(unloadingList != null && unloadingList.size() > 0){						
						for (OrderShipUnloadinginfo orderShipUnloadinginfo : unloadingList) {
							UnloadGoodsVO beanToBean = BeanConvertUtils.beanToBean(orderShipUnloadinginfo, UnloadGoodsVO.class);
							beanToBean.setOilType(orderShipUnloadinginfo.getOil());
							unloadGoodsVOList.add(beanToBean);
						}
					}
					log.info("卸港信息：" + JSONArray.fromObject(unloadGoodsVOList).toString());
					
					iShipGoodsService.saveUnloadGoods(unloadGoodsVOList, vo.getOrderNo(),user.getMemberId());		
					log.info("同步卸港信息到船务系统成功");
				}
			} 
			
			//更新消息提醒
			Order orderInfo = orderService.findByOrderNo(vo.getOrderNo());
			Map<String, Object> msgParams = new HashMap<>();
			msgParams.put("shipName", shipName);
			msgParams.put("orderNo", orderInfo.getOrderNo());
			msgParams.put("orderDetailLink", orderExecuteServer.get("sellerCenter/order/detail.htm")+"?uid="+orderInfo.getUuid());
			msgParams.put("loginLink", systemServer.get("login.htm").toString());
			
			if("1".equals(vo.getNorStatus())) {
				msgRemindingService.createNorUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(), vo.getNorDate(), msgParams);
			}else {
				msgRemindingService.closeNorUpdateReminding(orderInfo.getId());
			}
			
			if("1".equals(vo.getCodStatus())) {
				msgRemindingService.createCodUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(), vo.getCod(), msgParams);
			}else {
				msgRemindingService.closeCodUpdateReminding(orderInfo.getId());
			}
			//更新付款日期
			OrderPrice orderPrice = orderPriceService.findByOrderId(orderInfo.getId());
			
			if(StringUtils.isNotBlank(orderPrice.getPaymentTermJson())) {
				PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(orderPrice.getPaymentTermJson());
				Date payDate = null;
				if(paymentTermObjectAnalyse.isNor()) {
					payDate = paymentTermObjectAnalyse.calcPayDate(vo.getNorDate());
				}else if(paymentTermObjectAnalyse.isCod()) {
					payDate = paymentTermObjectAnalyse.calcPayDate(vo.getCod());
				}
				
				if(payDate != null) {
					orderPriceService.updatePayDate(orderPrice.getOrderPriceId(), payDate);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertShipUnloading(OrderShipUnloadinginfoVO vo, CurrentUser user) {

		vo.setUuid(KeyGenUtils.newUuid());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setLangVer(Constants.LANG_VER);
		// orderShipUnloadinginfoMapper.insertRecord(vo);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotBlank(vo.getNorDateDesc())) {
				vo.setNorDate(sdf.parse(vo.getNorDateDesc()));
			}
			if (StringUtils.isNotBlank(vo.getCodDesc())) {
				vo.setCod(sdf.parse(vo.getCodDesc()));
			}
			if (StringUtils.isNotBlank(vo.getAcStartDesc())) {
				vo.setAcStart(sdf.parse(vo.getAcStartDesc()));
			}
			if (StringUtils.isNotBlank(vo.getAcFinishDesc())) {
				vo.setAcFinish(sdf.parse(vo.getAcFinishDesc()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderExecException("orderexecute.code.00088", "请输入正确日期形式");
		}

		vo.setUpdateUser(user.getMemberId());
		vo.setCreateUser(user.getMemberId());

		orderShipUnloadinginfoMapper.insertRecord(vo);

		Order order = new Order();
		order.setOrderNo(vo.getOrderNo());

		Order orderInfo = orderService.findByOrderNo(vo.getOrderNo());

		OrderPrice orderPrice = orderPriceService.findByOrderId(orderInfo.getId());
		// 更新付款日期
		if (StringUtils.isNotBlank(orderPrice.getPaymentTermJson())) {
			try {
				PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(
						orderPrice.getPaymentTermJson());
				Date payDate = null;
				if (paymentTermObjectAnalyse.isNor()) {
					payDate = paymentTermObjectAnalyse.calcPayDate(vo.getNorDate());
				} else if (paymentTermObjectAnalyse.isCod()) {
					payDate = paymentTermObjectAnalyse.calcPayDate(vo.getCod());
				}

				if (payDate != null) {
					orderPriceService.updatePayDate(orderPrice.getOrderPriceId(), payDate);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new OrderExecException("orderexecute.code.00005", "更新付款日期异常");
			}
		}

		List<Order> orderList = orderService.queryByEntitys(order);

		// 订单状态更新
		if (OrderStatusEnum.STATUS_1.getCode().equals(orderList.get(0).getStatus())
				|| OrderStatusEnum.STATUS_2.getCode().equals(orderList.get(0).getStatus())
				|| OrderStatusEnum.STATUS_3.getCode().equals(orderList.get(0).getStatus())
				|| OrderStatusEnum.STATUS_4.getCode().equals(orderList.get(0).getStatus())) {
			Order changeStatusOrder = new Order();
			changeStatusOrder.setId(orderList.get(0).getId());
			changeStatusOrder.setStatus(OrderStatusEnum.STATUS_5.getCode());
			changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
			changeStatusOrder.setUpdateUser(user.getMemberId());
			orderService.updateRecordById(changeStatusOrder);

			// 调用状态机
			try {
				orderStatusService.setOrderStatus(orderList.get(0).getTradeId(),
						TransportStatusEnum.ORDER_STATUS_2.getCode(),
						Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_9.getCode()), "", user.getMemberId());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			} catch (com.sinochem.it.b2b.common.exception.BizException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}
		try {
			// 更新消息提醒
			String shipName = "";
			OrderShip shipInfo = orderShipService.findByOrderNo(vo.getOrderNo());
			if (shipInfo != null) {
				shipName = shipInfo.getName();
			}
			Map<String, Object> msgParams = new HashMap<>();
			msgParams.put("shipName", shipName);
			msgParams.put("orderNo", orderInfo.getOrderNo());
			msgParams.put("orderDetailLink",
					orderExecuteServer.get("sellerCenter/order/detail.htm") + "?uid=" + orderInfo.getUuid());
			msgParams.put("loginLink", systemServer.get("login.htm").toString());

			if ("1".equals(vo.getNorStatus())) {
				msgRemindingService.createNorUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(),
						vo.getNorDate(), msgParams);
			} else {
				msgRemindingService.closeNorUpdateReminding(orderInfo.getId());
			}

			if ("1".equals(vo.getCodStatus())) {
				msgRemindingService.createCodUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(),
						vo.getCod(), msgParams);
			} else {
				msgRemindingService.closeCodUpdateReminding(orderInfo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
