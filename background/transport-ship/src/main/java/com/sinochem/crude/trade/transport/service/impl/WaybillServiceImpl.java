package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

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
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.WaybillMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.WaybillVO;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class WaybillServiceImpl implements WaybillService {
	@Autowired
	private WaybillMapper _WaybillMapper;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private ShipPactService shipPactService;
	/*@Autowired
	private SysShipService sysShipService;*/
	
	
	public WaybillMapper getMapper(){
		return _WaybillMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Waybill> queryByEntitys(Waybill waybill){
		return  _WaybillMapper.queryByEntitys(waybill);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Waybill findByPrimaryKey(Long waybillId){
		return  _WaybillMapper.findByPrimaryKey(waybillId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Waybill findByUuid(String uuid){
		return  _WaybillMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Waybill waybill) {
		 _WaybillMapper.updateRecord(waybill);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long waybillId  , Long deleteUser) {
		 _WaybillMapper.deleteRecordByKey(waybillId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Waybill waybill){
		 _WaybillMapper.insertRecord(waybill);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long waybillId){
		 _WaybillMapper.deleteRecordByKey(waybillId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _WaybillMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _WaybillMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _WaybillMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_WaybillMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_WaybillMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 生成运单
	 */
	@Override
	@Transactional
	public void saveWaybill(WaybillVO vo, CurrentUser user) {
		//校验协约
		Agreement agreement = agreementService.findByUuid(vo.getAgreementUuid());
		if (agreement == null){
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		// 协约状态校验
		if (!Constants.AGREEMENT_STATUS_1.equals(agreement.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		//船合同
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		// 船合同状态校验
		if (!Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus()) && !Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		vo.setFirParty(agreement.getFirParty());
		vo.setSecParty(agreement.getSecParty());
		vo.setLoadPort(agreement.getLoadPort());
		vo.setUnloadPort(agreement.getUnloadPort());
		vo.setQuantity(agreement.getQuantity());
		vo.setOiiType(agreement.getOilType());
		vo.setLaycan(agreement.getLaycan());
		vo.setStatus(Constants.WAYBILL_STATUS_1);
		vo.setOrderCode(agreement.getOrderCode());
		vo.setOrderNo(agreement.getOrderNo());
		vo.setShipPactCode(shipPact.getPactCode());
		vo.setShipPactId(shipPact.getShipPactId());
		vo.setAgreementId(agreement.getAgreementId());
		vo.setAgreementCode(agreement.getAgreementCode());
		vo.setPalletId(agreement.getPalletId());
		vo.setPalletUuid(agreement.getPalletUuid());
		vo.setWaybillCode(SequenceUtils.nextSequence(Constants.WAYBILL_CODE));
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(user.getMemberId());
		vo.setCreateUser(user.getMemberId());
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		vo.setEpMemberId(user.getEpMemberId());
		this.insertRecord(vo);
		
		//修改协议状态
		agreement.setStatus(Constants.AGREEMENT_STATUS_2);
		agreement.setUpdateDate(DateTimeUtils.currentDate());
		agreement.setUpdateUser(user.getMemberId());
		agreementService.updateRecord(agreement);
		//修改船合同状态
		shipPact.setStatus(Constants.SHIP_PACT_STATUS_2);
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		shipPactService.updateRecord(shipPact);
		
		//修改订单状态添加租船信息
		//this.changeOrder(shipPact,agreement,user.getMemberId());
	}
	
	/**
	 * 生成运单多个协议匹配
	 */
	@Override
	@Transactional
	public void saveWaybillMany(WaybillVO vo, CurrentUser user) {
		List<String> list = vo.getAgreementUuids();
		for (String string : list) {
			WaybillVO waybillVO = new WaybillVO();
			waybillVO.setAgreementUuid(string);
			waybillVO.setShipPactUuid(vo.getShipPactUuid());
			this.saveWaybill(waybillVO, user);
		}
	}	
	/**
	 * 修改订单状态添加租船信息
	 * @param shipPact
	 * @param agreement
	 * @param memberId
	 *//*
	private void changeOrder(ShipPact shipPact, Agreement agreement,
			Long memberId) {
		SysShip ship = sysShipService.findByPrimaryKey(shipPact.getShipPlateId());
		if (ship == null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		FindedShipInfoVO shipInfo = new FindedShipInfoVO();
		shipInfo.setType(ship.getType());
		shipInfo.setArgeementUuid(agreement.getUuid());
		shipInfo.setName(ship.getName());
		shipInfo.setOrderNo(agreement.getOrderCode());
		iShipTransportService.getShipInfo(shipInfo , memberId);
		
	}*/

	/**
	 * 查询运单列表
	 */
	@Override
	public Page<Map<String, Object>> queryWaybillList(WaybillVO vo,
			SimplePageInfo pageInfo) {

		Page<Map<String,Object>> page = this.queryRecords(BeanConvertUtils.beanToMap(vo), pageInfo);
		return page;
	}

	/**
	 * 取消协议匹配
	 */
	@Override
	@Transactional
	public void cancelWaybill(String uuid, CurrentUser user) {
		
		Waybill waybill = this.findByUuid(uuid);
		if (waybill == null){
			throw new TransportException(TransportException.TYPE.ITSH003);
		}
		if (!Constants.WAYBILL_STATUS_1.equals(waybill.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH019);
		}
		ShipPact shipPact = shipPactService.findByPrimaryKey(waybill.getShipPactId());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		
		Waybill waybills = new Waybill();
		waybills.setShipPactId(waybill.getShipPactId());
		List<Waybill> list = this.queryByEntitys(waybills);
		if (list != null && list.size() <= 1){
			//修改船合同状态为未匹配
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_1);
			shipPact.setUpdateDate(DateTimeUtils.currentDate());
			shipPact.setUpdateUser(user.getMemberId());
			shipPactService.updateRecord(shipPact);
		}
		//修改代理协议状态为未匹配
		Agreement agreement = agreementService.findByPrimaryKey(waybill.getAgreementId());
		if (agreement == null){
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		if (!Constants.AGREEMENT_STATUS_2.equals(agreement.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		agreement.setStatus(Constants.AGREEMENT_STATUS_1);
		agreement.setUpdateDate(DateTimeUtils.currentDate());
		agreement.setUpdateUser(user.getMemberId());
		agreementService.updateRecord(agreement);
		
		//删除运单
		this.deleteRecordByKey(waybill.getWaybillId(), user.getMemberId());
	}

	

	
}
