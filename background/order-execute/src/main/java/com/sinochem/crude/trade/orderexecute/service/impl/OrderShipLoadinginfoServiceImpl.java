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
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortLoadingInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipLoadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderShipLoadinginfoVO;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipLoadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.transport.remote.IShipGoodsService;
import com.sinochem.crude.trade.transport.remote.LoadGoodsVO;

import net.sf.json.JSONArray;

@Service
public class OrderShipLoadinginfoServiceImpl implements OrderShipLoadinginfoService {
	@Autowired
	private OrderShipLoadinginfoMapper orderShipLoadinginfoMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderShipService orderShipService;
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
	
	Log log = LogFactory.getLog(OrderShipLoadinginfoService.class);
	
	public OrderShipLoadinginfoMapper getMapper(){
		return orderShipLoadinginfoMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderShipLoadinginfo ordershiploadinginfo){
		 return orderShipLoadinginfoMapper.insertRecord(ordershiploadinginfo);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderShipLoadinginfoMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderShipLoadinginfo  record){
    	return orderShipLoadinginfoMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderShipLoadinginfo orderShipLoadinginfo) throws BizException{
		if ( orderShipLoadinginfo.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderShipLoadinginfoMapper.updateRecordById(orderShipLoadinginfo);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderShipLoadinginfoMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderShipLoadinginfo orderShipLoadinginfo){
		return orderShipLoadinginfoMapper.updateRecords(orderShipLoadinginfo.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderShipLoadinginfo findByPrimaryKey(Long id){
		return  orderShipLoadinginfoMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderShipLoadinginfo findByUuid(String uuid){
		return  orderShipLoadinginfoMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderShipLoadinginfo> queryByEntitys(OrderShipLoadinginfo orderShipLoadinginfo){
		return  orderShipLoadinginfoMapper.queryByEntitys(orderShipLoadinginfo);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderShipLoadinginfoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderShipLoadinginfoMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderShipLoadinginfoMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<OrderShipLoadinginfo> findByOrderNo(String orderNo) {
		if(StringUtils.isEmpty(orderNo)){
			return new ArrayList<>();
		}
		
		OrderShipLoadinginfo query = new OrderShipLoadinginfo();
		query.setOrderNo(orderNo);
		
		return orderShipLoadinginfoMapper.queryByEntitys(query);
		
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveLoadingInfo(PortLoadingInfoVO vo, Order order) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		// 同步装港信息
		OrderShipLoadinginfo orderShipLoadinginfo = new OrderShipLoadinginfo();
		orderShipLoadinginfo.setUuid(KeyGenUtils.newUuid());
		orderShipLoadinginfo.setOrderNo(order.getOrderNo());
		try {
			orderShipLoadinginfo.setAcStart(StringUtils.isBlank(vo.getAcStart()) ?  null : format.parse(vo.getAcStart()));
			orderShipLoadinginfo.setAcFinish(StringUtils.isBlank(vo.getAcFinish()) ? null : format.parse(vo.getAcFinish()));
			orderShipLoadinginfo.setBlDate(StringUtils.isBlank(vo.getBlDate()) ? null : format.parse(vo.getBlDate()));
			orderShipLoadinginfo.setEta(StringUtils.isBlank(vo.getEtaDesc()) ? null : format.parse(vo.getEtaDesc()));
			orderShipLoadinginfo.setNorDate(StringUtils.isBlank(vo.getNorDate()) ? null : format.parse(vo.getNorDate()));
		} catch (ParseException e) {
			throw new OrderExecException("orderexecute.code.00088","请输入正确日期形式");
		}
		orderShipLoadinginfo.setBlHairBarrel(StringUtils.isBlank(vo.getBlHairBarrel()) ? null : new BigDecimal(vo.getBlHairBarrel()));
		orderShipLoadinginfo.setBlHairTonnage(StringUtils.isBlank(vo.getBlHairTonnage()) ? null : new BigDecimal(vo.getBlHairTonnage()));
		orderShipLoadinginfo.setBlNightstool(StringUtils.isBlank(vo.getBlNightstool()) ? null : new BigDecimal(vo.getBlNightstool()));
		orderShipLoadinginfo.setBlNetTonnage(StringUtils.isBlank(vo.getBlNetTonnage()) ? null : new BigDecimal(vo.getBlNetTonnage()));
		orderShipLoadinginfo.setLoadPort(vo.getLoadPort());
		orderShipLoadinginfo.setShNetBarrel(StringUtils.isBlank(vo.getShNetBarrel()) ? null : new BigDecimal(vo.getShNetBarrel()));
		orderShipLoadinginfo.setShNetTonnage(StringUtils.isBlank(vo.getShNetTonnage()) ? null : new BigDecimal(vo.getShNetTonnage()));
		orderShipLoadinginfo.setUpdateDate(DateTimeUtils.currentDate());
		orderShipLoadinginfo.setUpdateUser(9999L);
		orderShipLoadinginfo.setLangVer(Constants.LANG_VER);
		orderShipLoadinginfo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		orderShipLoadinginfo.setCreateDate(DateTimeUtils.currentDate());
		orderShipLoadinginfo.setCreateUser(9999L);
		
		orderShipLoadinginfoMapper.insertRecord(orderShipLoadinginfo);
		

		// 订单状态变更
		if (OrderStatusEnum.STATUS_1.getCode().equals(order.getStatus())
				|| OrderStatusEnum.STATUS_2.getCode().equals(order.getStatus())) {
			Order changeStatusOrder = new Order();
			changeStatusOrder.setId(order.getId());
			changeStatusOrder.setStatus(OrderStatusEnum.STATUS_3.getCode());
			changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
			changeStatusOrder.setUpdateUser(9999L);
			orderService.updateRecordById(changeStatusOrder);
			
			try {
				orderStatusService.setOrderStatus(order.getTradeId(), 
						TransportStatusEnum.ORDER_STATUS_2.getCode(), 
						Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_2.getCode()), 
						"", 9999L);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			} catch (com.sinochem.it.b2b.common.exception.BizException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}		
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user) throws OrderExecException {

		vo.setUuid(KeyGenUtils.newUuid());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setLangVer(Constants.LANG_VER);
		// orderShipLoadinginfoMapper.insertRecord(vo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			if (StringUtils.isNotBlank(vo.getBlDateS())) {
				vo.setBlDate(sdf.parse(vo.getBlDateS()));
			}
			if (StringUtils.isNotBlank(vo.getAcStartS())) {
				vo.setAcStart(sdf.parse(vo.getAcStartS()));
			}
			if (StringUtils.isNotBlank(vo.getAcFinishS())) {
				vo.setAcFinish(sdf.parse(vo.getAcFinishS()));
			}
		} catch (Exception e) {
			throw new OrderExecException("orderexecute.code.00005","日期格式错误");
		}

		vo.setUpdateUser(user.getMemberId());
		vo.setCreateUser(user.getMemberId());

		orderShipLoadinginfoMapper.insertRecord(vo);

		Order orderInfo = orderService.findByOrderNo(vo.getOrderNo());
		
		OrderPrice orderPrice = orderPriceService.findByOrderId(orderInfo.getId());
		if(StringUtils.isNotBlank(orderPrice.getPaymentTermJson())) {
			try {
				// 更新付款日期
				PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(
						orderPrice.getPaymentTermJson());
				if (paymentTermObjectAnalyse.isBl()) {
					Date payDate = paymentTermObjectAnalyse.calcPayDate(vo.getBlDate());
					if (payDate != null) {
						orderPriceService.updatePayDate(orderPrice.getOrderPriceId(), payDate);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new OrderExecException("orderexecute.code.00005","更新付款日期异常");
			}
		}
		
		// 订单状态更新
		if (OrderStatusEnum.STATUS_1.getCode().equals(orderInfo.getStatus())
				|| OrderStatusEnum.STATUS_2.getCode().equals(orderInfo.getStatus())) {
			Order changeStatusOrder = new Order();
			changeStatusOrder.setId(orderInfo.getId());
			changeStatusOrder.setStatus(OrderStatusEnum.STATUS_3.getCode());
			changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
			changeStatusOrder.setUpdateUser(user.getMemberId());
			orderService.updateRecordById(changeStatusOrder);

			// 调用状态机
			try {
				orderStatusService.setOrderStatus(orderInfo.getTradeId(), TransportStatusEnum.ORDER_STATUS_2.getCode(),
						Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_2.getCode()), "", user.getMemberId());
			} catch (NumberFormatException e) {
				throw new OrderExecException("orderexecute.code.00005","调用状态机异常");
			} catch (com.sinochem.it.b2b.common.exception.BizException e) {
				throw new OrderExecException("orderexecute.code.00005","调用状态机异常:" + e.getMessage());
			}
		}

		try {
			// 更新消息提醒
			if ("1".equals(vo.getBlStatus())) {
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
				msgRemindingService.createBillUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(),
						vo.getBlDate(), msgParams);
			} else {
				msgRemindingService.closeBillUpdateReminding(orderInfo.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user) {
		try {
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			if(StringUtils.isNotBlank(vo.getBlDateS())){
				vo.setBlDate(sdf.parse(vo.getBlDateS()));
			}
			if(StringUtils.isNotBlank(vo.getAcStartS())){
				vo.setAcStart(sdf.parse(vo.getAcStartS()));
			}
			if(StringUtils.isNotBlank(vo.getAcFinishS())){
				vo.setAcFinish(sdf.parse(vo.getAcFinishS()));
			}
			orderShipLoadinginfoMapper.updateRecordById(vo);
			
			OrderShip orderShip = new OrderShip();
			orderShip.setOrderNo(vo.getOrderNo());
			List<OrderShip> shipList = orderShipService.queryByEntitys(orderShip);
			String shipName = "";
			if (shipList!=null && shipList.size() > 0) {
				String agentUuid = shipList.get(0).getAgentUuid();//代理协议号
				shipName = shipList.get(0).getName();
				if(StringUtils.isNotBlank(agentUuid)){
					//同步到船务 TODO
					log.info("同步装港信息到船务系统中......");
					OrderShipLoadinginfo orderShipLoading = new OrderShipLoadinginfo();
					orderShipLoading.setOrderNo(vo.getOrderNo());
					List<OrderShipLoadinginfo> loadingList = orderShipLoadinginfoMapper.queryByEntitys(orderShipLoading);
					List<LoadGoodsVO> loadGoodsVOList = new ArrayList<LoadGoodsVO>();
					if(loadingList != null && loadingList.size() > 0){						
						for (OrderShipLoadinginfo orderShipLoadinginfo : loadingList) {
							LoadGoodsVO beanToBean = BeanConvertUtils.beanToBean(orderShipLoadinginfo, LoadGoodsVO.class);
							beanToBean.setOilType(orderShipLoadinginfo.getOil());
							loadGoodsVOList.add(beanToBean);
						}
					}
					log.info("装港信息：" + JSONArray.fromObject(loadGoodsVOList).toString());
					iShipGoodsService.saveLoadGoods(loadGoodsVOList, vo.getOrderNo(),user.getMemberId());
					log.info("同步装港信息到船务系统成功");
				}
			} 
			
			//更新消息提醒
			Order orderInfo = orderService.findByOrderNo(vo.getOrderNo());
			if("1".equals(vo.getBlStatus())) {
				Map<String, Object> msgParams = new HashMap<>();
				msgParams.put("shipName", shipName);
				msgParams.put("orderNo", orderInfo.getOrderNo());
				msgParams.put("orderDetailLink", orderExecuteServer.get("sellerCenter/order/detail.htm")+"?uid="+orderInfo.getUuid());
				msgParams.put("loginLink", systemServer.get("login.htm").toString());
				msgRemindingService.createBillUpdateReminding(orderInfo.getId(), orderInfo.getSellerPersonId(), vo.getBlDate(), msgParams);
			}else {
				msgRemindingService.closeBillUpdateReminding(orderInfo.getId());
			}
			//更新付款日期
			OrderPrice orderPrice = orderPriceService.findByOrderId(orderInfo.getId());
			
			if(StringUtils.isNotBlank(orderPrice.getPaymentTermJson())) {
				PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(orderPrice.getPaymentTermJson());
				if(paymentTermObjectAnalyse.isBl()) {
					Date payDate = paymentTermObjectAnalyse.calcPayDate(vo.getBlDate());
					if(payDate != null) {
						orderPriceService.updatePayDate(orderPrice.getOrderPriceId(), payDate);
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}
	}

}
