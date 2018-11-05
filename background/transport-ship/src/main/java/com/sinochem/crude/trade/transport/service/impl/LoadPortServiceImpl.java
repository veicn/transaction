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
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.IShipTransportService;
import com.sinochem.crude.trade.orderexecute.remote.TransportLoadVO;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.LoadPortMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.LoadPortVO;
import com.sinochem.crude.trade.transport.model.res.LoadPortDetail;
import com.sinochem.crude.trade.transport.model.res.LoadPortRes;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.service.AccessoryService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.LoadPortService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipmentService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class LoadPortServiceImpl implements LoadPortService {
	Log log = LogFactory.getLog(LoadPortServiceImpl.class);
	@Autowired
	private LoadPortMapper _LoadPortMapper;
	@Autowired
	private ShipPactService  shipPactService;
	/*@Autowired
	private ShipPlateService shipPlateService;*/
	@Autowired
	private ShipmentService shipmentService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private SysShipService sysShipService;
	@Autowired
	private IShipTransportService iShipTransportService;
	@Autowired
	private WaybillService waybillService;
	/*@Autowired
	private VoyageStartService voyageStartService;*/
	@Autowired
	private AccessoryService accessoryService;
	/*@Autowired
	private AgencyService agencyService;*/
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private CommonService commonService;
	
	public LoadPortMapper getMapper(){
		return _LoadPortMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<LoadPort> queryByEntitys(LoadPort loadport){
		return  _LoadPortMapper.queryByEntitys(loadport);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public LoadPort findByPrimaryKey(Long loadPortId){
		return  _LoadPortMapper.findByPrimaryKey(loadPortId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public LoadPort findByUuid(String uuid){
		return  _LoadPortMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(LoadPort loadport) {
		 _LoadPortMapper.updateRecord(loadport);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long loadPortId  , Long deleteUser) {
		 _LoadPortMapper.deleteRecordByKey(loadPortId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(LoadPort loadport){
		 _LoadPortMapper.insertRecord(loadport);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long loadPortId){
		 _LoadPortMapper.deleteRecordByKey(loadPortId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _LoadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _LoadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _LoadPortMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_LoadPortMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_LoadPortMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 添加船舶装港信息
	 */
	@Override
	@Transactional
	public void saveLoadPort(LoadPortVO vo, CurrentUser user) {
		//租船代理 检验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		//新增装港信息
		List<LoadPortDetail> list = vo.getList();
		this.insertInList(list, shipPact, user, vo);
		if (list != null && list.size() > 0){
			shipPact.setLoadPort(list.get(0).getLoadPort());
		}
		//修改船合同装港状态
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		//修改船合同状态
		if (Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_3.equals(shipPact.getStatus())){
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_4);
			
			//修改协议运单状态
			Waybill waybill = new Waybill();
			waybill.setShipPactUuid(shipPact.getUuid());
			List<Waybill> list2 = waybillService.queryByEntitys(waybill);
			if (list2 != null && !list2.isEmpty()){
				Waybill waybill2 = list2.get(0);
				if (waybill2 != null){
					waybill2.setStatus("4");;
					waybill2.setUpdateDate(DateTimeUtils.currentDate());
					waybill2.setUpdateUser(user.getMemberId());
					waybillService.updateRecord(waybill2);
					Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
					if (agreement != null){
						agreement.setStatus("4");
						agreement.setUpdateDate(DateTimeUtils.currentDate());
						agreement.setUpdateUser(user.getMemberId());
						agreementService.updateRecord(agreement);
					}
				}
			}
		}
		shipPactService.updateRecord(shipPact);
		
		//发送消息
		messagePushService.messagePush(2, shipPact.getShipPactId(), user.getMemberId());
	}

	/**
	 * 修改船舶装港信息
	 */
	@Override
	@Transactional
	public void updateLoadPort(LoadPortVO vo, CurrentUser user) {
		//校验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
				
		//删除原有数据
		Map<String,Object> map = new HashMap<>();
		map.put("shipPactId", shipPact.getShipPactId());
		map.put("updateUser", user.getMemberId());
		this.deleteRecords(map);
		
		//新增数据
		List<LoadPortDetail> list = vo.getList();
		this.insertInList(list, shipPact, user, vo);
		
		//发送消息
		messagePushService.messagePush(2, shipPact.getShipPactId(), user.getMemberId());
		
	}
	
	
	/**
	 * 查询船舶装港信息
	 */
	@Override
	public LoadPortRes findLoadPortDeatil(String shipPactUuid) {
		LoadPortRes res = new LoadPortRes();
		//校验航次开始必填
		//voyageStartService.checkIsEdit(shipPactUuid);
		
		//根据运单uuid查询运单
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		LoadPort port = new LoadPort();
		port.setShipPactId(shipPact.getShipPactId());
		List<LoadPort> list = this.queryByEntitys(port);
		List<LoadPort> lists = new ArrayList<>();
		for (LoadPort load : list) {
			String code = load.getLoadPortCode();
			String name = commonService.findNameByCodeAndLang("1", code);
			load.setLoadPort(name);
			lists.add(load);
		}
		//数据封装
		if (lists != null && !lists.isEmpty()){
			res.setList(setImg(lists));
			res.setShipPactUuid(list.get(0).getShipPactUuid());
		}
		//查询运单装港信息
		Waybill waybill = new Waybill();
		waybill.setShipPactId(shipPact.getShipPactId());
		List<Waybill> list2 = waybillService.queryByEntitys(waybill);
		List<Map<String,Object>> loadList = new ArrayList<>();
		List<String> strList = new ArrayList<>();
		if (list2 != null && !list2.isEmpty()){
			for (Waybill way : list2) {
				String loadPort = way.getLoadPortCode();
				if (!StringUtils.isNullOrEmpty(loadPort)){
					//分割装港
					String[] split = loadPort.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str)){
							String name = commonService.findNameByCodeAndLang("1", str);
							Map<String,Object> maps= new HashMap<>();	
							maps.put("loadPortCode", str);
							maps.put("loadPort", name);
							maps.put("agreementUuid", way.getAgreementUuid());
							loadList.add(maps);
							if (!strList.contains(name)){
								strList.add(name);
							}
						}
					}
				}
			}
		}
		Map<String,Object> map = new HashMap<>();
		map.put("loadPortList", strList);
		map.put("loadAgreeMap", loadList);
		res.setMap(map);
		return res;
	}

	/**
	 * insert In List
	 * @param list
	 * @param shipPact
	 * @param user
	 */
	@Transactional
	private void insertInList(List<LoadPortDetail> list,ShipPact shipPact ,CurrentUser user ,LoadPortVO vo){
		
		List<Map<String,Object>> lists = new ArrayList<>();
		List<String> listStr = new ArrayList<>();
		List<String> agreementUuids = new ArrayList<>();
		List<Agreement> agreements = new ArrayList<>();
		Integer rankNum = 1;
		for (LoadPortDetail loads : list) {
			if (listStr.contains(loads.getLoadPort()+loads.getOilType())){
				throw new TransportException(TransportException.TYPE.ITSH527);
			}
			listStr.add(loads.getLoadPort()+loads.getOilType());
			LoadPort load = new LoadPort();
			BeanUtils.copyProperties(loads, load);
			
			//必填
			if (StringUtils.isNullOrEmpty(load.getLoadPort())){
				throw new TransportException(TransportException.TYPE.ITSH048);
			} else if (StringUtils.isNullOrEmpty(load.getOilType())){
				throw new TransportException(TransportException.TYPE.ITSH041);
			}
			String agreementUuid = load.getAgreementUuid();
			String[] str = agreementUuid.split(";");
			String code="";
			for (String agreeUuid : str) {
				Map<String,Object> map = new HashMap<>();
				//查询协议编号
				Agreement agreement = agreementService.findByUuid(agreeUuid);
				if (!agreementUuids.contains(agreement.getUuid())){
					agreementUuids.add(agreement.getUuid());
					agreements.add(agreement);
				}
				String agreementCode = agreement.getAgreementCode();
				code=code+agreementCode+";";
				
				map.put("agreementUuid", agreeUuid);
				map.put("loadPort", load.getLoadPortCode());
				map.put("oilType", load.getOilTypeCode());
				lists.add(map);
			}
			load.setExt1(String.valueOf(rankNum));
			rankNum = rankNum+1;
			load.setAgreementCode(code);
			ValueSetName name = commonService.findNameByCode("1", load.getLoadPortCode());
			ValueSetName oil = commonService.findNameByCode("3", load.getOilTypeCode());
			load.setLoadPort(name.getZhName());
			load.setLoadPortEn(name.getEnName());
			load.setOilType(oil.getZhName());
			load.setOilTypeEn(oil.getEnName());
			load.setShipPactUuid(vo.getShipPactUuid());
			load.setShipPactId(shipPact.getShipPactId());
			load.setShipPlateId(shipPact.getShipPlateId());
			load.setShipPlateUuid(shipPact.getShipPlateUuid());
			load.setUuid(KeyGenUtils.newUuid());
			load.setCreateDate(DateTimeUtils.currentDate());
			load.setUpdateDate(DateTimeUtils.currentDate());
			load.setLangVer(Constants.LANG_VER);
			load.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			load.setUpdateUser(user.getMemberId());
			load.setCreateUser(user.getMemberId());
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			load.setEpMemberId(user.getEpMemberId());
			this.insertRecord(load);
			//保存图片
			insertImg(loads.getImgList(), load.getLoadPortId(), user);
		}
		//删除多余的货物装港信息
		shipmentService.deletByLoadAndShipPact(lists,vo.getShipPactUuid());
		
		//同步装港数据到订单
		try {
			this.sendLoadPort(agreements,user.getMemberId());
		} catch (BizException e) {
			log.error("同步订单装港信息异常："+e);
			throw new TransportException(e.getCode(),"同步订单装港信息异常："+e.getMessage());
		} catch (Exception e) {
			log.error("同步订单装港信息异常："+e);
			throw new TransportException(TransportException.TYPE.ITSH538);
		}
	}
	
	/**
	 * 同步订单装港信息
	 */
	@Override
	@Transactional
	public void sendLoadPort(List<Agreement> agreementUuids, Long memberId){
		
		List<TransportLoadVO> list = new ArrayList<>();
		for (Agreement agreement : agreementUuids) {
			if (Constants.ORDER_NO_1.equals(agreement.getOrderNo()) && !StringUtils.isNullOrEmpty(agreement.getOrderCode())){
				//根据协议uuid查询装港信息
				LoadPort loadPort = new LoadPort();
				loadPort.setAgreementUuid(agreement.getUuid());
				List<LoadPort> list2 = this.queryByEntitys(loadPort);
				if (!list2.isEmpty()){
					for (LoadPort port : list2) {
						TransportLoadVO vo = new TransportLoadVO();
						BeanUtils.copyProperties(port, vo);
						//查询货物装港信息
						Shipment shipment = new Shipment();
						shipment.setLoadPortCode(port.getLoadPortCode());
						shipment.setOilTypeCode(port.getOilTypeCode());
						shipment.setAgreementUuid(agreement.getUuid());
						List<Shipment> list3 = shipmentService.queryByEntitys(shipment);
						if (!list3.isEmpty()){
							Shipment shipment2 = list3.get(0);
							BeanUtils.copyProperties(shipment2, vo);
						}
						vo.setOrderNo(agreement.getOrderCode());
						vo.setArgeementUuid(agreement.getUuid());
						SysShip sysShip = sysShipService.findByUuid(agreement.getSysShipUuid());
						if (sysShip == null){
							throw new TransportException(TransportException.TYPE.ITSH023);
						}
						vo.setLoadPort(port.getLoadPortCode());
						vo.setOilType(port.getOilTypeCode());
						vo.setType(sysShip.getType());
						vo.setName(sysShip.getName());
						list.add(vo);
					}
				}
			}
		}
		if (!list.isEmpty()){
			iShipTransportService.insertTransportLoading(list , memberId);
		}
	}

	
	private void insertImg(List<String> imgList , Long id , CurrentUser user){
		if (imgList != null && !imgList.isEmpty()){
			for (String string : imgList) {
				Accessory accessory = new Accessory();
				accessory.setUuid(KeyGenUtils.newUuid());
				accessory.setId(id);
				accessory.setPath(string);
				accessory.setType("3");
				accessory.setCreateDate(DateTimeUtils.currentDate());
				accessory.setUpdateDate(DateTimeUtils.currentDate());
				accessory.setLangVer(Constants.LANG_VER);
				accessory.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				accessory.setUpdateUser(user.getMemberId());
				accessory.setCreateUser(user.getMemberId());
				accessoryService.insertRecord(accessory);
			}
		}
	}
	private List<LoadPortDetail> setImg(List<LoadPort> list) {
		List<LoadPortDetail> list2 =new ArrayList<>();
		for (LoadPort loadPort : list) {
			LoadPortDetail detail= new LoadPortDetail();
			BeanUtils.copyProperties(loadPort, detail);
			Accessory accessory = new Accessory();
			accessory.setId(loadPort.getLoadPortId());
			accessory.setType("3");
			List<Accessory> list3 = accessoryService.queryByEntitys(accessory);
			List<String> imgList = new ArrayList<>();
			for (Accessory name : list3) {
				imgList.add(name.getPath());
			}
			detail.setImgList(imgList);
			list2.add(detail);
		}
		return list2;
	}
	
	
}