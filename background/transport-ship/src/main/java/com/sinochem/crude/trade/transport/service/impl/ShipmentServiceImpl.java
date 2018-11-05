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
import com.sinochem.crude.trade.transport.dao.ShipmentMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.ShipmentVO;
import com.sinochem.crude.trade.transport.model.res.ShipmentDetail;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.remote.LoadGoodsVO;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.LoadPortService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipmentService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class ShipmentServiceImpl implements ShipmentService {
	Log log = LogFactory.getLog(ShipmentServiceImpl.class);
	@Autowired
	private ShipmentMapper _ShipmentMapper;
	@Autowired
	private WaybillService waybillService;
	/*@Autowired
	private PalletService palletSerivce;*/
	@Autowired
	private LoadPortService loadPortService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private ShipPactService pactService;
	@Autowired
	private CommonService commonService;
	
	public ShipmentMapper getMapper(){
		return _ShipmentMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Shipment> queryByEntitys(Shipment shipment){
		return  _ShipmentMapper.queryByEntitys(shipment);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Shipment findByPrimaryKey(Long shipmentId){
		return  _ShipmentMapper.findByPrimaryKey(shipmentId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Shipment findByUuid(String uuid){
		return  _ShipmentMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Shipment shipment) {
		 _ShipmentMapper.updateRecord(shipment);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long shipmentId  , Long deleteUser) {
		 _ShipmentMapper.deleteRecordByKey(shipmentId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Shipment shipment){
		 _ShipmentMapper.insertRecord(shipment);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long shipmentId){
		 _ShipmentMapper.deleteRecordByKey(shipmentId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _ShipmentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ShipmentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _ShipmentMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_ShipmentMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_ShipmentMapper.updateRecords(map);
	}
	//**************************以下方法为开发者补充*********************************/
	
	
	/**
	 * 货物装港信息维护
	 */
	@Override
	@Transactional
	public void saveShipment(ShipmentVO vo, CurrentUser user) {
		
		//检验运单是否存在
		Waybill waybill = waybillService.findByUuid(vo.getWaybillUuid());
		if (waybill == null){
			throw new TransportException(TransportException.TYPE.ITSH003);
		}
		ShipPact shipPact = pactService.findByPrimaryKey(waybill.getShipPactId());
		if (shipPact == null ){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (!Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) &&
			!Constants.SHIP_PACT_STATUS_5.equals(shipPact.getStatus()) &&
			!Constants.SHIP_PACT_STATUS_6.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		List<Agreement> agreementUuids = new ArrayList<>();
		Agreement agreement = agreementService.findByPrimaryKey(waybill.getAgreementId());
		if (agreement == null){
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		if (!Constants.ORDER_NO_0.equals(agreement.getOrderNo())){
			throw new TransportException(TransportException.TYPE.ITSH559);
		}
		//批量新增
		this.insertInList(vo.getList(),waybill,shipPact,user,agreement);
				
		
		agreementUuids.add(agreement);
		//同步订单装港信息
		/*try {
			loadPortService.sendLoadPort(agreementUuids , user.getMemberId());
		} catch (Exception e) {
			log.error("同步订单装货信息异常："+e);
			throw new TransportException(TransportException.TYPE.ITSH540);
		}*/
		
	}

	/**
	 * 货物装港信息修改
	 */
	@Override
	@Transactional
	public void updateShipment(ShipmentVO vo, CurrentUser user) {
		//删除原有数据
		if (StringUtils.isNullOrEmpty(vo.getWaybillUuid())){
			throw new TransportException(TransportException.TYPE.ITSH006);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("updateUser", user.getMemberId());
		map.put("waybillUuid", vo.getWaybillUuid());
		this.deleteRecords(map);
		//新增
		this.saveShipment(vo, user);
	}
	
	/**
	 * 查询货物装港信息
	 */
	@Override
	public List<ShipmentDetail> findShipmentDeatil(ShipmentVO vo) {
		//根据运单uuid查询运单
		Waybill  waybill = waybillService.findByUuid(vo.getWaybillUuid());
		if (waybill == null){
			throw new TransportException(TransportException.TYPE.ITSH003);
		}
		LoadPort loadPort = new LoadPort();
		loadPort.setAgreementUuid(waybill.getAgreementUuid()+";");
		List<LoadPort> list = loadPortService.queryByEntitys(loadPort);
		List<ShipmentDetail> resList = new ArrayList<>();
			for (int i = 0 ; i<list.size() ; i++) {
			LoadPort ports = list.get(i);
			String port2 = ports.getLoadPort();
			Shipment shipment = new Shipment();
			shipment.setAgreementUuid(waybill.getAgreementUuid());
			shipment.setLoadPort(port2);
			shipment.setOilType(ports.getOilType());
			List<Shipment> list2 = this.queryByEntitys(shipment);
			if (list2 != null && !list2.isEmpty()){
				Shipment shipment2 = list2.get(0);
				if (StringUtils.isNullOrEmpty(shipment2.getOilType())){
					shipment2.setOilType(ports.getOilType());
				}
				String name = commonService.findNameByCodeAndLang("1", shipment2.getLoadPortCode());
				shipment2.setLoadPort(name);
				String oil = commonService.findNameByCodeAndLang("3", shipment2.getOilTypeCode());
				shipment2.setOilType(oil);
				ShipmentDetail detail = BeanConvertUtils.beanToBean(shipment2, ShipmentDetail.class);
				detail.setType(waybill.getOrderNo());
				resList.add(detail);
			} else {
				ShipmentDetail detail = new ShipmentDetail();
				String name = commonService.findNameByCodeAndLang("1", ports.getLoadPortCode());
				String oil = commonService.findNameByCodeAndLang("3", ports.getOilTypeCode());
				detail.setLoadPort(name);
				detail.setLoadPortCode(ports.getLoadPortCode());
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
	/*private void check(ShipmentDetail vo){
		if (vo.getBlDate() == null){
			throw new TransportException(TransportException.TYPE.ITSH090);
		} else if (vo.getBlNightstool() == null){
			throw new TransportException(TransportException.TYPE.ITSH091);
		} else if (vo.getBlHairBarrel() == null){
			throw new TransportException(TransportException.TYPE.ITSH092);
		} else if (vo.getBlNetTonnage() == null){
			throw new TransportException(TransportException.TYPE.ITSH093);
		} else if (vo.getBlHairTonnage() == null){
			throw new TransportException(TransportException.TYPE.ITSH094);
		} else if (vo.getApi() == null){
			throw new TransportException(TransportException.TYPE.ITSH095);
		} else if (vo.getWaterImpCon() == null){
			throw new TransportException(TransportException.TYPE.ITSH096);
		} else if (vo.getWaterQuantity() == null){
			throw new TransportException(TransportException.TYPE.ITSH097);
		} else if (vo.getShHairBarrel() == null){
			throw new TransportException(TransportException.TYPE.ITSH098);
		} else if (vo.getShHairTonnage() == null){
			throw new TransportException(TransportException.TYPE.ITSH099);
		} else if (vo.getRatioBarrel() == null){
			throw new TransportException(TransportException.TYPE.ITSH500);
		} else if (vo.getRatioTonnage() == null){
			throw new TransportException(TransportException.TYPE.ITSH501);
		} else if (vo.getQuantity() == null){
			throw new TransportException(TransportException.TYPE.ITSH503);
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
	@Transactional
	private void insertInList(List<ShipmentDetail> list,Waybill waybill, ShipPact shipPact, CurrentUser user, Agreement agreement) {
		for (ShipmentDetail vo : list) {
			if (Constants.ORDER_NO_1.equals(agreement.getOrderNo()) && !StringUtils.isNullOrEmpty(agreement.getOrderCode())){
				//校验必填
				//check(vo);
			}
			//String loadPortUuid = vo.getLoadPortUuid();
			//vo.setLoadPort(loadPort.getLoadPort());
			ValueSetName name = commonService.findNameByCode("1", vo.getLoadPortCode());
			vo.setLoadPort(name.getZhName());
			vo.setLoadPortEn(name.getEnName());
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
			this.insertRecord(BeanConvertUtils.beanToBean(vo, Shipment.class));
		}
	}
	
	/**
	 * 根据装港删除多余货物装港信息
	 */
	@Override
	public void deletByLoadAndShipPact(List<Map<String, Object>> lists,
			String shipPactUuid) {
		_ShipmentMapper.deletByLoadAndShipPact(lists,shipPactUuid);
		
	}

	@Override
	@Transactional
	public void saveLoadGoods(List<LoadGoodsVO> list, String orderCode, Long memberId) {
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
		Shipment shipment = new Shipment();
		shipment.setWaybillId(waybill.getWaybillId());
		List<Shipment> list3 = this.queryByEntitys(shipment);
		if (list3 != null && !list3.isEmpty()){
			Map<String,Object> map = new HashMap<>();
			map.put("updateUser", memberId);
			map.put("waybillUuid", waybill.getUuid());
			this.deleteRecords(map);	
		}
		
		//批量新增
		for (LoadGoodsVO loadGoodsVO : list) {
			//校验必填
			checkLoad(loadGoodsVO,waybill.getAgreementUuid());
			Shipment vo = new Shipment();
			BeanUtils.copyProperties(loadGoodsVO, vo);
			ValueSetName name = commonService.findNameByCode("1", loadGoodsVO.getLoadPort());
			vo.setLoadPort(name.getZhName());
			vo.setLoadPortEn(name.getEnName());
			vo.setLoadPortCode(loadGoodsVO.getLoadPort());
			ValueSetName oil = commonService.findNameByCode("3", loadGoodsVO.getOilType());
			vo.setOilType(oil.getZhName());
			vo.setOilTypeEn(oil.getEnName());
			vo.setOilTypeCode(loadGoodsVO.getOilType());
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
	
	
	private void checkLoad(LoadGoodsVO loadGoodsVO, String agreementUuid){
		if (StringUtils.isNullOrEmpty(loadGoodsVO.getLoadPort())){
			throw new TransportException(TransportException.TYPE.ITSH048);
		} else if (StringUtils.isNullOrEmpty(loadGoodsVO.getOilType())){
			throw new TransportException(TransportException.TYPE.ITSH041);
		}
		//查询船装港是否存在
		/*LoadPort loadPort = new LoadPort();
		loadPort.setLoadPort(loadGoodsVO.getLoadPort());
		loadPort.setOilType(loadGoodsVO.getOilType());
		loadPort.setAgreementUuid(agreementUuid+";");
		List<LoadPort> list = loadPortService.queryByEntitys(loadPort);
		if (list == null || list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH554);
		}*/
	}
}
