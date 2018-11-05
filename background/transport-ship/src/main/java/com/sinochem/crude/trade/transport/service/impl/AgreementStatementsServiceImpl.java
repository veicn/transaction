package com.sinochem.crude.trade.transport.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.AgreementStatementsMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.AgreementStatements;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.model.AgreementStatementsVO;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.AgreementStatementsService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;

@Service
public class AgreementStatementsServiceImpl implements AgreementStatementsService {
	@Autowired
	private AgreementStatementsMapper _AgreementStatementsMapper;
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private PalletService palletService;
	@Autowired
	private MessagePushService messagePushService;
	
	public AgreementStatementsMapper getMapper(){
		return _AgreementStatementsMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<AgreementStatements> queryByEntitys(AgreementStatements agreementstatements){
		return  _AgreementStatementsMapper.queryByEntitys(agreementstatements);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public AgreementStatements findByPrimaryKey(Long agreementStatementsId){
		return  _AgreementStatementsMapper.findByPrimaryKey(agreementStatementsId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public AgreementStatements findByUuid(String uuid){
		return  _AgreementStatementsMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(AgreementStatements agreementstatements) {
		 _AgreementStatementsMapper.updateRecord(agreementstatements);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long agreementStatementsId  , Long deleteUser) {
		 _AgreementStatementsMapper.deleteRecordByKey(agreementStatementsId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(AgreementStatements agreementstatements){
		 _AgreementStatementsMapper.insertRecord(agreementstatements);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long agreementStatementsId){
		 _AgreementStatementsMapper.deleteRecordByKey(agreementStatementsId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _AgreementStatementsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AgreementStatementsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _AgreementStatementsMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_AgreementStatementsMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AgreementStatementsMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	//保存
	@Override
	@Transactional
	public void saveStatements(AgreementStatementsVO vo, CurrentUser user) {
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (!Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		Agreement agreement = agreementService.findByUuid(vo.getShipAgreementUuid());
		if (agreement == null){
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		Pallet pallet = palletService.findByUuid(agreement.getPalletUuid());
		if (pallet == null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		AgreementStatementsVO vo2 = new AgreementStatementsVO();
		if(vo.getFreightQuantily()!=null&&vo.getFreightOverageQuantily()!=null&&vo.getFreightOverageRate()!=null&&vo.getFreightOverageFlatrate()!=null){
			vo2.setFreightTotal(vo.getFreightQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)))).setScale(2, BigDecimal.ROUND_HALF_UP));
			vo2.setFreightOverageTotal(vo.getFreightOverageQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)).multiply(vo.getFreightOveragePct()))).setScale(2, BigDecimal.ROUND_HALF_UP));
			vo2.setAddressCommissionTotal((vo2.getFreightTotal().add(vo2.getFreightOverageTotal())).multiply(vo.getAddressCommissionPct().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP));
			vo2.setBalance(vo2.getFreightTotal().add(vo2.getFreightOverageTotal()).subtract(vo2.getAddressCommissionTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		AgreementStatements as = new AgreementStatements();
		BeanUtils.copyProperties(vo, as);
		as.setFreightTotal(vo2.getFreightTotal());
		as.setFreightOverageTotal(vo2.getFreightOverageTotal());
		as.setAddressCommissionTotal(vo2.getAddressCommissionTotal());
		as.setBalance(vo2.getBalance());
		as.setUuid(KeyGenUtils.newUuid());
		as.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		as.setCreateDate(DateTimeUtils.currentDate());
		as.setCreateUser(user.getEpMemberId());
		as.setUpdateDate(DateTimeUtils.currentDate());
		as.setUpdateUser(user.getEpMemberId());
		as.setAgentMemberId(user.getEpMemberId());
		as.setShipPactId(shipPact.getShipPactId());
		as.setShipPactUuid(vo.getShipPactUuid());
		as.setEpMemberId(pallet.getEpMemberId());
		as.setShipAgreementId(agreement.getAgreementId());
		this.insertRecord(as);
		//修改船合同状态
		shipPact.setStatus(Constants.SHIP_PACT_STATUS_8);
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		shipPactService.updateRecord(shipPact);
		
		//发送消息
		messagePushService.messagePush(20, as.getAgreementStatementsId(), user.getMemberId());
	}

	//根据uuid逻辑删除对象
	@Override
	public void deleteStatements(Map<String, Object> map) {
		_AgreementStatementsMapper.deleteByUuid(map);
	}

	//修改
	@Override
	public void updateStatemnets(AgreementStatementsVO vo, CurrentUser user) {
		if(vo != null){
			Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
			map.put("updateUser", user.getEpMemberId());
			map.put("updateDate", DateTimeUtils.currentDate());
			_AgreementStatementsMapper.updateRecords(map);			
		}
	}
	/**
	 * 根据条件-分页查询-om
	 */
	public Page<Map<String, Object>> queryRecordsOm(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AgreementStatementsMapper.queryRecordsOm(map);
	}
	
}
