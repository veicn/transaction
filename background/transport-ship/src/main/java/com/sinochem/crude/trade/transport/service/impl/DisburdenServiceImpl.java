package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.DisburdenMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Disburden;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.DisburdenVO;
import com.sinochem.crude.trade.transport.model.res.DisburdenDetail;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.remote.UnloadGoodsVO;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.DisburdenService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipmentService;
import com.sinochem.crude.trade.transport.service.UnloadPortService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class DisburdenServiceImpl implements DisburdenService {
	Log log = LogFactory.getLog(DisburdenServiceImpl.class);
	@Autowired
	private DisburdenMapper _DisburdenMapper;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private ShipPactService pactService;
	@Autowired
	private ShipmentService shipmentService;
	@Autowired
	private UnloadPortService unloadPortService;
	@Autowired
	private CommonService commonService;
	
	public DisburdenMapper getMapper(){
		return _DisburdenMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Disburden> queryByEntitys(Disburden disburden){
		return  _DisburdenMapper.queryByEntitys(disburden);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Disburden findByPrimaryKey(Long disburdenId){
		return  _DisburdenMapper.findByPrimaryKey(disburdenId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Disburden findByUuid(String uuid){
		return  _DisburdenMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Disburden disburden) {
		 _DisburdenMapper.updateRecord(disburden);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long disburdenId  , Long deleteUser) {
		 _DisburdenMapper.deleteRecordByKey(disburdenId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Disburden disburden){
		 _DisburdenMapper.insertRecord(disburden);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long disburdenId){
		 _DisburdenMapper.deleteRecordByKey(disburdenId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _DisburdenMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _DisburdenMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _DisburdenMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_DisburdenMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_DisburdenMapper.updateRecords(map);
	}
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 货物卸港信息维护
	 */
	@Override
	@Transactional
	public void saveDisburden(DisburdenVO vo, CurrentUser user) {
			//校验必填
			//check(vo);
		
			//检验运单是否存在
			Waybill waybill = waybillService.findByUuid(vo.getWaybillUuid());
			if (waybill == null){
				throw new TransportException(TransportException.TYPE.ITSH003);
			}
			
			//校验状态
			ShipPact shipPact = pactService.findByPrimaryKey(waybill.getShipPactId());
			if (shipPact == null ){
				throw new TransportException(TransportException.TYPE.ITSH039);
			}
			if (!Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) &&
					!Constants.SHIP_PACT_STATUS_5.equals(shipPact.getStatus()) &&
					!Constants.SHIP_PACT_STATUS_6.equals(shipPact.getStatus())){
					throw new TransportException(TransportException.TYPE.ITSH087);
				}
			//校验是否已经维护装货信息
			Shipment shipment = new Shipment();
			shipment.setWaybillUuid(waybill.getUuid());
			List<Shipment> list = shipmentService.queryByEntitys(shipment);
			if (list == null ||  list.isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH537);
			}
			//同步订单卸港信息
			List<Agreement> agreementUuids = new ArrayList<>();
			Agreement agreement = agreementService.findByPrimaryKey(waybill.getAgreementId());
			if (agreement == null){
				throw new TransportException(TransportException.TYPE.ITSH027);
			}
			if (!Constants.ORDER_NO_0.equals(agreement.getOrderNo())){
				throw new TransportException(TransportException.TYPE.ITSH559);
			}
			
			this.insertInList(vo.getList(), waybill, shipPact, user,agreement);
			agreementUuids.add(agreement);
			
			/*try {
				unloadPortService.sendUnloadPort(agreementUuids, user.getMemberId());
			} catch (Exception e) {
				log.error("同步订单卸货信息异常："+e);
				throw new TransportException(TransportException.TYPE.ITSH541);
			}*/
	}
	
	/**
	 * 货物卸港信息修改
	 */
	@Override
	@Transactional
	public void updateDisburden(DisburdenVO vo, CurrentUser user) {
		if (StringUtils.isNullOrEmpty(vo.getWaybillUuid())){
			throw new TransportException(TransportException.TYPE.ITSH006);
		}
		//删除原有数据
		Map<String,Object> map = new HashMap<>();
		map.put("updateUser", user.getMemberId());
		map.put("waybillUuid", vo.getWaybillUuid());
		this.deleteRecords(map);
		//新增
		this.saveDisburden(vo, user);
	}
	/**
	 * 查询货物卸港信息
	 */
	@Override
	public List<DisburdenDetail> findDisburdenDeatil(DisburdenVO vo) {
		//根据运单uuid查询运单
		Waybill  waybill = waybillService.findByUuid(vo.getWaybillUuid());
		if (waybill == null){
			throw new TransportException(TransportException.TYPE.ITSH003);
		}
		UnloadPort loadPort = new UnloadPort();
		loadPort.setAgreementUuid(waybill.getAgreementUuid()+";");
		List<UnloadPort> list = unloadPortService.queryByEntitys(loadPort);
		List<DisburdenDetail> resList = new ArrayList<>();
		for (UnloadPort ports : list) {
			String port2 = ports.getUnloadPort();
			Disburden shipment = new Disburden();
			shipment.setAgreementUuid(waybill.getAgreementUuid());
			shipment.setUnloadPort(port2);
			shipment.setOilType(ports.getOilType());
			List<Disburden> list2 = this.queryByEntitys(shipment);
			if (list2 != null && !list2.isEmpty()){
				Disburden disburden = list2.get(0);
				String name = commonService.findNameByCodeAndLang("2", disburden.getUnloadPortCode());
				disburden.setUnloadPort(name);
				String oil = commonService.findNameByCodeAndLang("3", disburden.getOilTypeCode());
				disburden.setOilType(oil);
				DisburdenDetail detail = BeanConvertUtils.beanToBean(disburden, DisburdenDetail.class);
				detail.setType(waybill.getOrderNo());
				resList.add(detail);
			} else {
				DisburdenDetail detail = new DisburdenDetail();
				String name = commonService.findNameByCodeAndLang("2", ports.getUnloadPortCode());
				String oil = commonService.findNameByCodeAndLang("3", ports.getOilTypeCode());
				detail.setUnloadPort(name);
				detail.setUnloadPortCode(ports.getUnloadPortCode());
				detail.setOilTypeCode(ports.getOilTypeCode());
				detail.setOilType(oil);
				detail.setAgreementUuid(ports.getAgreementUuid());
				detail.setAgreementCode(waybill.getAgreementCode());
				detail.setType(waybill.getOrderNo());
				resList.add(detail);
			}
		}
		return resList;
	}

	/**
	 * 校验必填
	 * @param vo
	 */
	/*private void check(DisburdenDetail vo){
		if (vo.getShipHairBar() == null){
			throw new TransportException(TransportException.TYPE.ITSH504);
		} else if (vo.getShipHairTon() == null){
			throw new TransportException(TransportException.TYPE.ITSH505);
		} else if (vo.getCommHairBar() == null){
			throw new TransportException(TransportException.TYPE.ITSH506);
		} else if (vo.getCommHairTon() == null){
			throw new TransportException(TransportException.TYPE.ITSH507);
		} else if (vo.getCommCleanBar() == null){
			throw new TransportException(TransportException.TYPE.ITSH508);
		} else if (vo.getCommCleanTon() == null){
			throw new TransportException(TransportException.TYPE.ITSH509);
		} else if (vo.getCounCleanBar() == null){
			throw new TransportException(TransportException.TYPE.ITSH510);
		} else if (vo.getCounCleanTon() == null){
			throw new TransportException(TransportException.TYPE.ITSH511);
		} else if (vo.getPotHairBar() == null){
			throw new TransportException(TransportException.TYPE.ITSH512);
		} else if (vo.getPotHairTon() == null){
			throw new TransportException(TransportException.TYPE.ITSH513);
		} else if (vo.getBlHairBarRate() == null){
			throw new TransportException(TransportException.TYPE.ITSH514);
		} else if (vo.getBlHairTonRate() == null){
			throw new TransportException(TransportException.TYPE.ITSH515);
		} else if (vo.getCounCleanBarRate() == null){
			throw new TransportException(TransportException.TYPE.ITSH516);
		} else if (vo.getCounCleanTonRate() == null){
			throw new TransportException(TransportException.TYPE.ITSH517);
		} else if (vo.getRobQuanatity() == null){
			throw new TransportException(TransportException.TYPE.ITSH518);
		}
	}*/
	/**
	 * 
	 * 多条新增
	 * @param list
	 * @param vo
	 * @param waybill 
	 * @param shipPact
	 * @param user
	 * @param agreement 
	 */
	private void insertInList(List<DisburdenDetail> list, 
			Waybill waybill, ShipPact shipPact, CurrentUser user, Agreement agreement) {
		for (DisburdenDetail vo : list) {
			if (Constants.ORDER_NO_1.equals(agreement.getOrderNo()) && !StringUtils.isNullOrEmpty(agreement.getOrderCode())){
				//校验必填
				//check(vo);
			}			
			ValueSetName name = commonService.findNameByCode("2", vo.getUnloadPortCode());
			vo.setUnloadPort(name.getZhName());
			vo.setUnloadPortEn(name.getEnName());
			ValueSetName oil = commonService.findNameByCode("3", vo.getOilTypeCode());
			vo.setOilType(oil.getZhName());
			vo.setOilTypeEn(oil.getEnName());
			vo.setPalletId(waybill.getPalletId());
			vo.setPalletUuid(waybill.getPalletUuid());
			vo.setWaybillId(waybill.getWaybillId());
			vo.setWaybillUuid(waybill.getUuid());
			vo.setAgreementId(waybill.getAgreementId());
			vo.setAgreementUuid(waybill.getAgreementUuid());
			vo.setShipPactId(waybill.getShipPactId());
			vo.setShipPactUuid(waybill.getShipPactUuid());
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
			
			this.insertRecord(BeanConvertUtils.beanToBean(vo, Disburden.class));
		}
	}

	/**
	 * 删除多余的货物卸港信息
	 */
	@Override
	public void deletByLoadAndShipPact(List<Map<String, Object>> lists,
			String shipPactUuid) {
		_DisburdenMapper.deletByLoadAndShipPact(lists,shipPactUuid);
		
	}
	@Override
	public void saveUnloadGoods(List<UnloadGoodsVO> list, String orderCode, Long memberId) {
		log.info("#############传入的订单编号为："+orderCode);

		
		//根据订单编号查询运单
		Waybill waybills = new Waybill();
		waybills.setOrderCode(orderCode);
		waybills.setOrderNo(Constants.ORDER_NO_1);
		List<Waybill> list2 = waybillService.queryByEntitys(waybills);
		if (list2 == null || list2.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH558);
		}
		Waybill waybill = list2.get(0);
		
		//修改先删除原有数据
		Disburden disburden = new Disburden();
		disburden.setWaybillId(waybill.getWaybillId());
		List<Disburden> list3 = this.queryByEntitys(disburden);
		if (list3 != null && !list3.isEmpty()){
			Map<String,Object> map = new HashMap<>();
			map.put("updateUser", memberId);
			map.put("waybillUuid", waybill.getUuid());
			this.deleteRecords(map);	
		}
		//批量新增
		for (UnloadGoodsVO unloadGoodsVO : list) {
			this.checkLoad(unloadGoodsVO, waybill.getAgreementUuid());
			Disburden vo = new Disburden();
			BeanUtils.copyProperties(unloadGoodsVO, vo);
			ValueSetName name = commonService.findNameByCode("2", unloadGoodsVO.getUnloadPort());
			vo.setUnloadPort(name.getZhName());
			vo.setUnloadPortEn(name.getEnName());
			vo.setUnloadPortCode(unloadGoodsVO.getUnloadPort());
			ValueSetName oil = commonService.findNameByCode("3", unloadGoodsVO.getOilType());
			vo.setOilType(oil.getZhName());
			vo.setOilTypeEn(oil.getEnName());
			vo.setOilTypeCode(unloadGoodsVO.getOilType());
			vo.setPalletId(waybill.getPalletId());
			vo.setPalletUuid(waybill.getPalletUuid());
			vo.setWaybillId(waybill.getWaybillId());
			vo.setWaybillUuid(waybill.getUuid());
			vo.setAgreementId(waybill.getAgreementId());
			vo.setAgreementUuid(waybill.getAgreementUuid());
			vo.setAgreementCode(waybill.getAgreementCode());
			vo.setShipPactId(waybill.getShipPactId());
			vo.setShipPactUuid(waybill.getShipPactUuid());
			vo.setUuid(KeyGenUtils.newUuid());
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setUpdateUser(memberId);
			vo.setCreateUser(memberId);
			vo.setEpMemberId(999L);
			this.insertRecord(vo);
		}
	}
	private void checkLoad(UnloadGoodsVO unloadGoodsVO, String agreementUuid){
		if (StringUtils.isNullOrEmpty(unloadGoodsVO.getUnloadPort())){
			throw new TransportException(TransportException.TYPE.ITSH075);
		} else if (StringUtils.isNullOrEmpty(unloadGoodsVO.getOilType())){
			throw new TransportException(TransportException.TYPE.ITSH041);
		}
		//查询船卸港是否存在
		/*UnloadPort loadPort = new UnloadPort();
		loadPort.setUnloadPort(unloadGoodsVO.getUnloadPort());
		loadPort.setOilType(unloadGoodsVO.getOilType());
		loadPort.setAgreementUuid(agreementUuid+";");
		List<UnloadPort> list = unloadPortService.queryByEntitys(loadPort);
		if (list == null || list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH555);
		}*/
	}
}
