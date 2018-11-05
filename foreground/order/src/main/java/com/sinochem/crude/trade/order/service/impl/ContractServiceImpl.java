package com.sinochem.crude.trade.order.service.impl;

import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.sinochem.crude.trade.common.enums.SerialNumberBizType;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.dao.*;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContract;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.enums.*;
import com.sinochem.crude.trade.order.remote.CancelOrderVO;
import com.sinochem.crude.trade.order.remote.OrderInfoService;
import com.sinochem.crude.trade.order.service.ContractMessageService;
import com.sinochem.crude.trade.order.service.CrudeContractService;
import com.sinochem.crude.trade.order.util.SerialNumberUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.order.service.ContractService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
	private Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private ContractRelevanterMapper contractRelevanterMapper;
	@Autowired
	private ContractShipMapper contractShipMapper;
	@Autowired
	private ContractShipBerthMapper contractShipBerthMapper;
	@Autowired
	private CrudeOilLongTermContractPlanMapper crudeOilLongTermContractPlanMapper;
	@Autowired
	private ProductOilResourceMapper productOilResourceMapper;
	@Autowired
	private CrudeOilResourceMapper crudeOilResourceMapper;
	@Autowired
	private UUIDGenerator uuidGenerator;
	@Autowired
	private ContractLogMapper contractLogMapper;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private CrudeContractService crudeContractService;
	@Autowired
	private ContractMessageService contractMessageService;

	@Transactional(value = "transactionManager",rollbackFor = Exception.class)
	@Override
	public void createContract(Contract contract, Long oper) throws BizException{
		//检查订单的各项参数
		contract.check(oper);
		//将订单各个状态归0
		Date now = new Date();
		contract.setUuid(uuidGenerator.gain());
		contract.setCreater(oper);
		contract.setCreateTime(now);
		contract.setUpdater(oper);
		contract.setUpdateTime(now);
		contract.setVersion(0L);
		contract.setOrderStatus("0");

		contract.setInitiator(oper);
		contract.setToken(String.valueOf(System.currentTimeMillis()));
		//业务类型（原油、成品油、长协）
		SerialNumberBizType bizType = SerialNumberBizType.CrudeOilOrder;
		//判断并初始化类型
		if (contract instanceof CrudeOilLongTermContract) {//长协原油
			bizType = SerialNumberBizType.LongOilOrder;
			contract.setBizType(EnumContractBizType.CrudeOilContract.getCode());//原油
			contract.setContractType(EnumContractType.LongTermContract.getCode());//长协订单
			contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());//双签
			if("0".equals(contract.getIsSubmit())){
				contract.setDoubleSign("00");
			}else{
				contract.confirm(oper, "00");//通过设置操作人 来重新定义双签字段
			}
		} else if (contract instanceof ProductOilSaleContract){//成品油
			bizType = SerialNumberBizType.ProductOilOrder;
			contract.setContractType(EnumContractType.NormalContract.getCode());//普通订单
			contract.setBizType(EnumContractBizType.ProductOilContract.getCode());//成品油
		} else if (contract instanceof CrudeOilBuyContract) {//原油
			bizType = SerialNumberBizType.CrudeOilOrder;
			contract.setBizType(EnumContractBizType.CrudeOilContract.getCode());//原油
			contract.setContractType(EnumContractType.NormalContract.getCode());//普通订单
		}
		//生成订单序列号
		String serialNumber = SerialNumberUtils.getSerialNumber12Len(bizType, now);
		contract.setOrderNo(serialNumber);
		//保存订单
		contractMapper.insert(contract);
		//保存油品信息
		if (contract instanceof CrudeOilLongTermContract) {//长协原油
			
			CrudeOilResource crudeOilResource = ((CrudeOilBuyContract) contract).getCrudeOilResource();
			if (null != crudeOilResource) {
				crudeOilResource.setContractId(contract.getId());
				crudeOilResourceMapper.insert(crudeOilResource);
			}
			//保存长约采购细节
			List<CrudeOilLongTermContractPlan> longTermContractPlanList = ((CrudeOilLongTermContract)contract).getPlans();
			if (CollectionUtils.isNotEmpty(longTermContractPlanList)) {
				for (CrudeOilLongTermContractPlan crudeOilLongTermContractPlan : longTermContractPlanList) {
					CrudeOilResource crudeOilResource1 = crudeOilLongTermContractPlan.getCrudeOilResource();
					//crudeOilResourceMapper.insert(crudeOilResource1);
					
					crudeOilLongTermContractPlan.setCrudeOilId(crudeOilResource.getId());
					crudeOilLongTermContractPlan.setContractId(contract.getId());
					crudeOilLongTermContractPlanMapper.insert(crudeOilLongTermContractPlan);
				}
			}
		} else if (contract instanceof ProductOilSaleContract){//成品油
			ProductOilResource productOilResource = ((ProductOilSaleContract) contract).getProductOilResource();
			if (null != productOilResource) {
				productOilResource.setContractId(contract.getId());
				productOilResourceMapper.insert(productOilResource);
			}
		} else if (contract instanceof CrudeOilBuyContract) {//原油
			CrudeOilResource crudeOilResource = ((CrudeOilBuyContract) contract).getCrudeOilResource();
			if (null != crudeOilResource) {
				crudeOilResource.setContractId(contract.getId());
				crudeOilResourceMapper.insert(crudeOilResource);
			}
		}
		//保存买卖家信息
		if(contract.getContractTrader()!=null){
			contract.getContractTrader().setContractId(contract.getId());
		}
		contractRelevanterMapper.insert(contract.getContractTrader());
		contract.getContractSupplier().setContractId(contract.getId());
		contractRelevanterMapper.insert(contract.getContractSupplier());
		contract.getContractBuyer().setContractId(contract.getId());
		contractRelevanterMapper.insert(contract.getContractBuyer());
		//保存船务信息
		ContractShip contractShip = contract.getContractShip();
		if (null != contractShip) {
			contract.getContractShip().setContractId(contract.getId());
			contractShipMapper.insert(contract.getContractShip());
			List<ContractShipBerth> shipBerthList = contract.getContractShip().getContractShipBerths();
			if (CollectionUtils.isNotEmpty(shipBerthList)) {
				for(ContractShipBerth shipBerth: shipBerthList){
					shipBerth.setContractId(contract.getId());
					contractShipBerthMapper.insertSelective(shipBerth);
				}
			}
		}
	}

	/**
	 * 申请取消订单
	 * @param uuid
	 * @param remark
	 * @param user
	 * @throws BizException
	 */
	@Transactional(value = "transactionManager",rollbackFor = Exception.class)
	@Override
	public void applyCancelContract(String uuid,String remark, CurrentUser user) throws BizException{
		Contract dbContract = contractMapper.selectByUUID(uuid);
		if(EnumDoubleSignType.CANCEL_CONTRACT.getCode().equals(dbContract.getDoubleStatus())){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0012));
		}
		if(user.getEpMemberId() == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0013));
		}
		//修改订单状态等
		Contract contract = new Contract();
		contract.setId(dbContract.getId());
		contract.setSeller(dbContract.getSeller());
		contract.setBuyer(dbContract.getBuyer());
		contract.setDoubleStatus(EnumDoubleSignType.CANCEL_CONTRACT.getCode());
		contract.confirm(user.getEpMemberId(), "00");//通过设置操作人 来重新定义双签字段
		contract.setUpdater(user.getMemberId());
		contract.setUpdateTime(new Date());
		contractMapper.updateByPrimaryKeySelective(contract);
		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(dbContract.getId());
		contractLog.setOperationType(EnumOperationType.SQQX_CONTRACT.getCode());
		contractLog.setDescribes(remark);
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);

		if(!"0".equals(dbContract.getOrderStatus())){ //订单状态不为0，说明已经发送订单执行
			//调用订单执行接口
			CancelOrderVO cancelOrderVO = new CancelOrderVO();
			cancelOrderVO.setTradeId(dbContract.getId());
			cancelOrderVO.setCancelType(EnumCancelType.SQQX_CONTRACT.getCode());
			cancelOrderVO.setUserId(user.getMemberId());
			orderInfoService.cancelOrder(cancelOrderVO);
		}
		//发送消息
		contractMessageService.applyCancelContract(dbContract.getId(),user.getEpMemberId());
	}

	/**
	 * 撤销取消订单
	 * @param uuid
	 * @param remark
	 * @param user
	 * @throws BizException
	 */
	@Transactional(value = "transactionManager",rollbackFor = Exception.class)
	@Override
	public void revokeCancelContract(String uuid, String remark, CurrentUser user) throws BizException {
		Contract dbContract = contractMapper.selectByUUID(uuid);
		if(!EnumDoubleSignType.CANCEL_CONTRACT.getCode().equals(dbContract.getDoubleStatus())){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0014));
		}
		if(dbContract.isCancelFlag()){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0015));
		}
		if(user.getEpMemberId() == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0016));
		}
		//修改订单状态等
		Contract contract = new Contract();
		contract.setId(dbContract.getId());
		if("0".equals(dbContract.getOrderStatus())){
			contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
			contract.setDoubleSign("00");//这里回归，订单确认双签成功
		}else{
			contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
			contract.setDoubleSign("11");//这里回归，订单确认双签成功
		}
		contract.setUpdater(user.getMemberId());
		contract.setUpdateTime(new Date());
		contractMapper.updateByPrimaryKeySelective(contract);
		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(dbContract.getId());
		contractLog.setOperationType(EnumOperationType.CHQX_CONTRACT.getCode());
		contractLog.setDescribes(remark);
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);
		if(!"0".equals(dbContract.getOrderStatus())){ //订单状态不为0，说明已经发送订单执行
			//调用订单执行接口
			CancelOrderVO cancelOrderVO = new CancelOrderVO();
			cancelOrderVO.setTradeId(dbContract.getId());
			cancelOrderVO.setCancelType(EnumCancelType.CHQX_CONTRACT.getCode());
			cancelOrderVO.setUserId(user.getMemberId());
			orderInfoService.cancelOrder(cancelOrderVO);
		}
		//发送消息
		contractMessageService.revokeCancelContract(dbContract.getId(),user.getEpMemberId());
	}

	/**
	 * 确认取消
	 * @param uuid
	 * @param remark
	 * @param user
	 * @throws BizException
	 */
	@Transactional(value = "transactionManager",rollbackFor = Exception.class)
	@Override
	public void confirmCancelContract(String uuid, String remark, boolean operStatus, CurrentUser user) throws BizException {
		Contract dbContract = contractMapper.selectByUUID(uuid);
		if(!EnumDoubleSignType.CANCEL_CONTRACT.getCode().equals(dbContract.getDoubleStatus())){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0017));
		}
		if(dbContract.isCancelFlag()){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0018));
		}
		if(user.getEpMemberId() == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0016));
		}
		//修改订单状态等
		Contract contract = new Contract();
		contract.setId(dbContract.getId());
		if(operStatus){//同意
			contract.setSeller(dbContract.getSeller());
			contract.setBuyer(dbContract.getBuyer());
			contract.setDoubleStatus(EnumDoubleSignType.CANCEL_CONTRACT.getCode());
			contract.confirm(user.getEpMemberId(), dbContract.getDoubleSign());//通过设置操作人 来重新定义双签字段
			contract.setCancelFlag(true);
		}else{
			if("0".equals(dbContract.getOrderStatus())){
				contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
				contract.setDoubleSign("00");//这里回归，订单确认双签成功
			}else{
				contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
				contract.setDoubleSign("11");//这里回归，订单确认双签成功
			}
		}
		contract.setUpdater(user.getMemberId());
		contract.setUpdateTime(new Date());
		contractMapper.updateByPrimaryKeySelective(contract);
		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(dbContract.getId());
		if(operStatus){//同意
			contractLog.setOperationType(EnumOperationType.TYQX_CONTRACT.getCode());
		}else{
			contractLog.setOperationType(EnumOperationType.JJQX_CONTRACT.getCode());
		}
		contractLog.setDescribes(remark);
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);
		if(!"0".equals(dbContract.getOrderStatus())){ //订单状态不为0，说明已经发送订单执行
			//调用订单执行接口
			CancelOrderVO cancelOrderVO = new CancelOrderVO();
			cancelOrderVO.setTradeId(dbContract.getId());
			if(operStatus){//同意
				cancelOrderVO.setCancelType(EnumCancelType.TYQX_CONTRACT.getCode());
			}else{
				cancelOrderVO.setCancelType(EnumCancelType.JJQX_CONTRACT.getCode());
			}
			cancelOrderVO.setUserId(user.getMemberId());
			orderInfoService.cancelOrder(cancelOrderVO);
		}
		//发送消息
		contractMessageService.confirmCancelContract(dbContract.getId(),operStatus,user.getEpMemberId());
	}


	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void confirmContract(Long orderId, Long oper, String token) throws BizException {
		Contract contract = new Contract();
		contract.setId(orderId);
		contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
		contract.setDoubleSign("11");//这里取消再次确认操作，直接默认双方确认
		contract.setUpdater(oper);
		contract.setOrderStatus("0000");
		contract.setUpdateTime(new Date());
		contractMapper.updateByPrimaryKeySelective(contract);

		// 已经双方确认的情况下，推给订单执行
		if (contract.isConfirm()) {
			try {
				contract = contractMapper.selectByPrimaryKey(orderId);
				if (contract != null) {
					//若不是长协订单，则推送至订单执行
					if(!EnumContractType.LongTermContract.getCode().equals(contract.getContractType())) {
						crudeContractService.pushOrderInfo(orderId, oper);
					}
				}
			} catch (BizException e) {
				logger.error("订单确认失败：" + e);
				throw new BizException(e.getMessage());
			}catch (RuntimeException e) {
				logger.error("订单确认失败：" + e);
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0019));
			}
		}
	}
	
	/**
	 * 根据报价单号查询订单，防止报价单重复推送
	 *
	 * @param biddingId
	 */
	@Override
	public Contract selectByBiddingId(Long biddingId) {
		return contractMapper.selectByBiddingId(biddingId);
	}

	@Override
	public List<ContractLog> contractOperList(Long orderId) {
		List<ContractLog> contractLogs = contractLogMapper.selectByContractId(orderId);
		if(contractLogs != null){
			for(ContractLog contractLog : contractLogs){
				contractLog.setOperationType(EnumOperationType.getDoubleSignTypeByCode(contractLog.getOperationType()).getDesc());
				if("提交订单".equals(contractLog.getDescribes())){
					contractLog.setDescribes(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER1008));
				}
			}
		}
		return contractLogs;
	}

	@Override
	public Contract getContract(Long orderId) {
		return contractMapper.selectByPrimaryKey(orderId);
	}

	@Override
	@Transactional("transactionManager")
	public void updateContract(Contract contract, Long oper) throws BizException{
		Contract dbContract = contractMapper.selectByPrimaryKey(contract.getId());
		if (null == dbContract) {
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0020));
		}
		//判断状态，双签就不允许修改
		if (dbContract.isConfirm() == true) {
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0021));
		}
		//检查参数
		contract.check(oper);
		//更新token，重新设置双签
		if("0".equals(contract.getIsSubmit())){
			contract.setDoubleSign("00");
		}else{
			contract.confirm(oper, "00");//通过设置操作人 来重新定义双签字段
		}
		contract.setOrderStatus("0");
		contract.setUpdater(oper);
		contract.setUpdateTime(new Date());
		contract.addVersion();//version增加
		
		contractMapper.updateByPrimaryKeySelective(contract);
		//保存油品
		if (contract instanceof CrudeOilLongTermContract) {//长协原油
			CrudeOilResource crudeOilResource = ((CrudeOilBuyContract) contract).getCrudeOilResource();
			if (null != crudeOilResource) {
				crudeOilResourceMapper.updateByPrimaryKeySelective(crudeOilResource);
			}
			//更新长约采购细节
			crudeOilLongTermContractPlanMapper.removeAllByContractId(contract.getId());
			List<CrudeOilLongTermContractPlan> longTermContractPlanList = ((CrudeOilLongTermContract)contract).getPlans();
			if (CollectionUtils.isNotEmpty(longTermContractPlanList)) {
				for (CrudeOilLongTermContractPlan crudeOilLongTermContractPlan : longTermContractPlanList) {
					//CrudeOilResource crudeOilResource1 = crudeOilLongTermContractPlan.getCrudeOilResource();
					//crudeOilResource1.setContractId(contract.getId());
					//crudeOilResourceMapper.insert(crudeOilResource1);
					
					crudeOilLongTermContractPlan.setCrudeOilId(crudeOilResource.getId());
					crudeOilLongTermContractPlan.setContractId(contract.getId());
					crudeOilLongTermContractPlanMapper.insert(crudeOilLongTermContractPlan);
				}
			}
		} else if (contract instanceof ProductOilSaleContract){//成品油
			ProductOilResource productOilResource = ((ProductOilSaleContract) contract).getProductOilResource();
			if (null != productOilResource) {
				productOilResourceMapper.updateByPrimaryKeySelective(productOilResource);
			}
		} else if(contract instanceof CrudeOilBuyContract) {//原油
		CrudeOilResource crudeOilResource = ((CrudeOilBuyContract) contract).getCrudeOilResource();
		if (null != crudeOilResource) {
			crudeOilResourceMapper.updateByPrimaryKeySelective(crudeOilResource);
			}
		}
		//更新其他信息
		if(contract.getContractTrader() != null)
			contractRelevanterMapper.updateByPrimaryKeySelective(contract.getContractTrader());
		contractRelevanterMapper.updateByPrimaryKeySelective(contract.getContractSupplier());
		contractRelevanterMapper.updateByPrimaryKeySelective(contract.getContractBuyer());
		ContractShip contractShip = contract.getContractShip();
		if (null != contractShip) {
			contractShipMapper.updateByPrimaryKeySelective(contract.getContractShip());
			contractShipBerthMapper.removeAllByContractId(contract.getId());
			List<ContractShipBerth> shipBerthList = contract.getContractShip().getContractShipBerths();
			if (CollectionUtils.isNotEmpty(shipBerthList)){
				List<ContractShipBerth> contractShipBerthList =
						new ArrayList<ContractShipBerth>(shipBerthList.size());
				for(ContractShipBerth shipBerth: shipBerthList){
					shipBerth.setContractId(contract.getId());
					contractShipBerthList.add(shipBerth);
				}
				contractShipBerthMapper.batchSave(contractShipBerthList);
			}
		}
	}

	/**
	 * 确认订单
	 * @param uuid
	 * @param oper
	 * @throws BizException
	 */
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void confirmContractNew(String uuid, Long oper, String token, CurrentUser user) throws BizException {
		Contract originalContract = contractMapper.selectByUUID(uuid);
		if(originalContract == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0020));
		}else{
			if(originalContract.isConfirm(oper, originalContract.getDoubleSign())){
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0043));
			}
			if ("L".equals(originalContract.getContractType())) {//长协原油
				Contract contract = new Contract();
				contract.setId(originalContract.getId());
				contract.setSeller(originalContract.getSeller());
				contract.setBuyer(originalContract.getBuyer());
				contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
				contract.confirm(oper, originalContract.getDoubleSign());//通过设置操作人 来重新定义双签字段
				contract.setUpdater(oper);
				contract.setUpdateTime(new Date());
				contractMapper.updateByPrimaryKeySelective(contract);
			}else{
				//非长协订单确认，之后可在这里开发
			}
		}

		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(originalContract.getId());
		contractLog.setOperationType(EnumOperationType.QR_CONTRACT.getCode());
		contractLog.setDescribes("提交订单"); 
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);
	}

	/**
	 * 拒绝订单
	 * @param uuid
	 * @param oper
	 * @param token
	 * @throws BizException
	 */
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void rejectedContractNew(String uuid, Long oper, String remark, String token, CurrentUser user) throws BizException {
		Contract originalContract = contractMapper.selectByUUID(uuid);
		if(originalContract == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0020));
		}else{
			if(originalContract.isConfirm(oper, originalContract.getDoubleSign())){
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0043));
			}
			if ("L".equals(originalContract.getContractType())) {//长协原油
				Contract contract = new Contract();
				contract.setId(originalContract.getId());
				contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
				contract.setDoubleSign("00");//这里确认操作，直接默认双方确认
				contract.setUpdater(oper);
				contract.setUpdateTime(new Date());
				contractMapper.updateByPrimaryKeySelective(contract);
			}else{
				//非长协订单确认，之后可在这里开发
			}
		}
		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(originalContract.getId());
		contractLog.setOperationType(EnumOperationType.JJ_CONTRACT.getCode());
		contractLog.setDescribes(remark);
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);
	}

	/**
	 * 撤回订单
	 * @param uuid
	 * @param oper
	 * @param token
	 * @throws BizException
	 */
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Exception.class)
	public void revokeContractNew(String uuid, Long oper, String remark, String token, CurrentUser user) throws BizException {
		Contract originalContract = contractMapper.selectByUUID(uuid);
		if(originalContract == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0020));
		}else{
			if(originalContract.isConfirm()){
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0043));
			}
			if(!originalContract.isConfirm(oper, originalContract.getDoubleSign())){
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0044));
			}
			if ("L".equals(originalContract.getContractType())) {//长协原油
				Contract contract = new Contract();
				contract.setId(originalContract.getId());
				contract.setDoubleStatus(EnumDoubleSignType.CONFIRM_CONTRACT.getCode());
				contract.setDoubleSign("00");//这里确认操作，直接默认双方确认
				contract.setUpdater(oper);
				contract.setUpdateTime(new Date());
				contractMapper.updateByPrimaryKeySelective(contract);
			}else{
				//非长协订单确认，之后可在这里开发
			}
		}
		//记录订单操作日志
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(originalContract.getId());
		contractLog.setOperationType(EnumOperationType.CH_CONTRACT.getCode());
		contractLog.setDescribes(remark);
		contractLog.setOperName(user.getName());
		contractLog.setCreater(user.getMemberId());
		contractLog.setCreateTime(new Date());
		contractLogMapper.insert(contractLog);
	}

}
