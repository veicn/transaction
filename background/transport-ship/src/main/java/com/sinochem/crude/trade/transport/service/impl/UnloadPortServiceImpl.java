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
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.IShipTransportService;
import com.sinochem.crude.trade.orderexecute.remote.TransportUnloadVO;
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.UnloadPortMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Appoint;
import com.sinochem.crude.trade.transport.domain.Disburden;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.UnloadPortVO;
import com.sinochem.crude.trade.transport.model.res.UnloadPortDetail;
import com.sinochem.crude.trade.transport.model.res.UnloadPortImport;
import com.sinochem.crude.trade.transport.model.res.UnloadPortRes;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.service.AccessoryService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.AppointService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.DisburdenService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.UnloadPortService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class UnloadPortServiceImpl implements UnloadPortService {
	Log log = LogFactory.getLog(UnloadPortServiceImpl.class);
	@Autowired
	private UnloadPortMapper _UnloadPortMapper;
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private IShipTransportService iShipTransportService;
	/*@Autowired
	private ShipPlateService shipPlateService;*/
	@Autowired
	private SysShipService sysShipService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private DisburdenService disburdenService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private AppointService appointService;
	/*@Autowired
	private VoyageStartService voyageStartService;*/
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private CommonService commonService;
	
	
	public UnloadPortMapper getMapper(){
		return _UnloadPortMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<UnloadPort> queryByEntitys(UnloadPort unloadport){
		return  _UnloadPortMapper.queryByEntitys(unloadport);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public UnloadPort findByPrimaryKey(Long unloadPortId){
		return  _UnloadPortMapper.findByPrimaryKey(unloadPortId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public UnloadPort findByUuid(String uuid){
		return  _UnloadPortMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(UnloadPort unloadport) {
		 _UnloadPortMapper.updateRecord(unloadport);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long unloadPortId  , Long deleteUser) {
		 _UnloadPortMapper.deleteRecordByKey(unloadPortId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(UnloadPort unloadport){
		 _UnloadPortMapper.insertRecord(unloadport);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long unloadPortId){
		 _UnloadPortMapper.deleteRecordByKey(unloadPortId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _UnloadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _UnloadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _UnloadPortMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_UnloadPortMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_UnloadPortMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 保存船舶卸港信息
	 */
	@Override
	@Transactional
	public void saveUnloadPort(UnloadPortVO vo, CurrentUser user) {
		
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		//校验必填
		this.insertInList(vo.getList(), shipPact, user, vo);
		
		//修改船合同卸港状态
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		
		if (Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_3.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_5.equals(shipPact.getStatus())){
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_6);
			
			//修改协议运单状态
			Waybill waybill = new Waybill();
			waybill.setShipPactUuid(shipPact.getUuid());
			List<Waybill> list2 = waybillService.queryByEntitys(waybill);
			if (list2 != null && !list2.isEmpty()){
				Waybill waybill2 = list2.get(0);
				if (waybill2 != null){
					waybill2.setStatus("6");;
					waybill2.setUpdateDate(DateTimeUtils.currentDate());
					waybill2.setUpdateUser(user.getMemberId());
					waybillService.updateRecord(waybill2);
					Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
					if (agreement != null){
						agreement.setStatus("6");
						agreement.setUpdateDate(DateTimeUtils.currentDate());
						agreement.setUpdateUser(user.getMemberId());
						agreementService.updateRecord(agreement);
					}
				}
			}
			
			
			}
		shipPactService.updateRecord(shipPact);
		
		//发送消息
		messagePushService.messagePush(4, shipPact.getShipPactId(), user.getMemberId());
	}
	
	/**
	 * 修改船舶卸港信息
	 */
	@Override
	@Transactional
	public void updateUnloadPort(UnloadPortVO vo, CurrentUser user) {
		//校验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
				
		//删除原有数据
		Map<String,Object> map = new HashMap<>();
		map.put("shipPactId", shipPact.getShipPactId());
		map.put("updateUser", user.getMemberId());
		this.deleteRecords(map);
		
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		//校验必填
		this.insertInList(vo.getList(), shipPact, user, vo);
		
		//修改船合同卸港状态
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		
		if (Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_3.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_5.equals(shipPact.getStatus())){
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_6);
			}
		shipPactService.updateRecord(shipPact);
		
		//发送消息
	    messagePushService.messagePush(4, shipPact.getShipPactId(), user.getMemberId());
	}
	
	
	/**
	 * 查询船舶卸港信息
	 */
	@Override
	public UnloadPortRes findUnloadPortDeatil(String shipPactUuid) {
		UnloadPortRes res = new UnloadPortRes();
		//voyageStartService.checkIsEdit(shipPactUuid);
		//校验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);

		UnloadPort port = new UnloadPort();
		port.setShipPactId(shipPact.getShipPactId());
		List<UnloadPort> list = this.queryByEntitys(port);
		List<UnloadPort> lists = new ArrayList<>();
		for (UnloadPort load : list) {
			String code = load.getUnloadPortCode();
			String name = commonService.findNameByCodeAndLang("2", code);
			load.setUnloadPort(name);
			lists.add(load);
		}
		if (lists != null && !lists.isEmpty()){
			res.setList(setImg(lists));
		}
		//查询运单卸港信息
		Waybill waybill = new Waybill();
		waybill.setShipPactId(shipPact.getShipPactId());
		List<Waybill> list2 = waybillService.queryByEntitys(waybill);
		List<Map<String,Object>> loadList = new ArrayList<>();
		List<String> strList = new ArrayList<>();
		if (list2 != null && !list2.isEmpty()){
			for (Waybill way : list2) {
				String unloadPort = way.getUnloadPortCode();
				if (!StringUtils.isNullOrEmpty(unloadPort)){
					//分割卸港
					String[] split = unloadPort.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str)){
							Map<String,Object> maps= new HashMap<>();
							String name = commonService.findNameByCodeAndLang("2", str);
							maps.put("unloadPortCode", str);
							maps.put("unloadPort", name);
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
		map.put("unloadPortList", strList);
		map.put("unloadAgreeMap", loadList);
		res.setMap(map);
		return res;
	}

	/**
	 * insert in list
	 * @param list
	 * @param shipPact
	 * @param user
	 * @param vo
	 */
	@Transactional
	private void insertInList(List<UnloadPortDetail> list,ShipPact shipPact ,CurrentUser user ,UnloadPortVO vo){
		List<Map<String,Object>> lists = new ArrayList<>();
		List<String> listStr = new ArrayList<>();
		List<String> agreementUuids = new ArrayList<>();
		List<Agreement> agreements = new ArrayList<>();
		Integer rankNum =1;
		for (UnloadPortDetail loads : list) {
			if (listStr.contains(loads.getUnloadPort()+loads.getOilType())){
				throw new TransportException(TransportException.TYPE.ITSH528);
			}
			listStr.add(loads.getUnloadPort()+loads.getOilType());
			UnloadPort load = new UnloadPort();
			BeanUtils.copyProperties(loads, load);
			
			load.setShipPactUuid(vo.getShipPactUuid());
			load.setMonitor(vo.getMonitor());
			load.setMonitorTel(vo.getMonitorTel());
			load.setAgency(vo.getAgency());
			load.setAgencyTel(vo.getAgencyTel());
			load.setInspection(vo.getInspection());
			load.setInspectionTel(vo.getInspectionTel());
			
			//必填
			if (StringUtils.isNullOrEmpty(load.getUnloadPort())){
				throw new TransportException(TransportException.TYPE.ITSH075);
			}
			String agreementUuid = load.getAgreementUuid();
			String[] str = agreementUuid.split(";");
			String code="";
			for (String agreeUuid : str) {
				Agreement agreement = agreementService.findByUuid(agreeUuid);
				if (!agreementUuids.contains(agreement.getUuid())){
					agreementUuids.add(agreement.getUuid());
					agreements.add(agreement);
				}
				
				String agreementCode = agreement.getAgreementCode();
				code=code+agreementCode+";";
			
				Map<String,Object> map = new HashMap<>();
				map.put("agreementUuid", agreeUuid);
				map.put("unloadPort", load.getUnloadPortCode());
				map.put("oilType", load.getOilTypeCode());
				lists.add(map);
			}
			load.setExt1(String.valueOf(rankNum));
			rankNum = rankNum+1;
			load.setAgreementCode(code);
			ValueSetName name = commonService.findNameByCode("2", load.getUnloadPortCode());
			ValueSetName oil = commonService.findNameByCode("3", load.getOilTypeCode());
			load.setUnloadPort(name.getZhName());
			load.setUnloadPortEn(name.getEnName());
			load.setOilType(oil.getZhName());
			load.setOilTypeEn(oil.getEnName());
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
			
			//添加卸港协议关系
			if (loads.getList() == null || loads.getList().isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH544);
			}
			this.addAppoint(loads.getList(),user,load);
			
			//保存图片
			insertImg(loads.getImgList(), load.getUnloadPortId(), user);
		}
		//删除多余的货物卸港信息
		disburdenService.deletByLoadAndShipPact(lists,vo.getShipPactUuid());
		
		//同步卸港数据到订单
		try {
			this.sendUnloadPort(agreements,user.getMemberId());
		} catch (BizException e) {
			log.error("同步订单卸港信息异常："+e);
			throw new TransportException(e.getCode(),"同步订单卸港信息异常："+e.getMessage());
		} catch (Exception e) {
			log.error("同步订单卸港信息异常："+e);
			throw new TransportException(TransportException.TYPE.ITSH539);
		}
	}
	
	/**
	 * 添加协议指定
	 * @param list
	 * @param user
	 * @param load
	 */
	@Transactional
	private void addAppoint(List<Appoint> list, CurrentUser user,UnloadPort load) {
		
		for (Appoint appoint : list) {
			//校验必填
			appoint.setUnloadPortUuid(load.getUuid());
			this.checkAppoint(appoint);
			
			appoint.setUuid(KeyGenUtils.newUuid());
			appoint.setCreateDate(DateTimeUtils.currentDate());
			appoint.setUpdateDate(DateTimeUtils.currentDate());
			appoint.setLangVer(Constants.LANG_VER);
			appoint.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			appoint.setUpdateUser(user.getMemberId());
			appoint.setCreateUser(user.getMemberId());
			appoint.setEpMemberId(user.getEpMemberId());
			appoint.setUnloadPort(load.getUnloadPort());
			appoint.setUnloadPortId(load.getUnloadPortId());
			appointService.insertRecord(appoint);
		}
	}
	

	/**
	 * 校验协议指定必填
	 * @param appoint
	 */
	private void checkAppoint(Appoint appoint) {
		if ( StringUtils.isNullOrEmpty(appoint.getAgreementUuid())){
			throw new TransportException(TransportException.TYPE.ITSH026);
		}/* else if ( StringUtils.isNullOrEmpty(appoint.getInspection()) ){
			throw new TransportException(TransportException.TYPE.ITSH079);
		} else if ( StringUtils.isNullOrEmpty(appoint.getInspectionTel())){
			throw new TransportException(TransportException.TYPE.ITSH080);
		} else if ( StringUtils.isNullOrEmpty(appoint.getMonitor())){
			throw new TransportException(TransportException.TYPE.ITSH081);
		} else if ( StringUtils.isNullOrEmpty(appoint.getMonitorTel())){
			throw new TransportException(TransportException.TYPE.ITSH082);
		} else if ( StringUtils.isNullOrEmpty(appoint.getAgency())){
			throw new TransportException(TransportException.TYPE.ITSH083);
		} else if ( StringUtils.isNullOrEmpty(appoint.getAgencyTel())){
			throw new TransportException(TransportException.TYPE.ITSH084);
		}*/ else if ( StringUtils.isNullOrEmpty(appoint.getUnloadPortUuid())){
			throw new TransportException(TransportException.TYPE.ITSH525);
		}
	}

	/**
	 * 同步订单卸港信息
	 */
	@Override
	@Transactional
	public void sendUnloadPort(List<Agreement> agreementUuids, Long memberId){
		List<TransportUnloadVO> list = new ArrayList<>();
		for (Agreement agreement : agreementUuids) {
			if (Constants.ORDER_NO_1.equals(agreement.getOrderNo()) && !StringUtils.isNullOrEmpty(agreement.getOrderCode())){
				//根据协议uuid查询卸港信息
				UnloadPort unloadPort = new UnloadPort();
				unloadPort.setAgreementUuid(agreement.getUuid());
				List<UnloadPort> list2 = this.queryByEntitys(unloadPort);
				if (!list2.isEmpty()){
					for (UnloadPort port : list2) {
						TransportUnloadVO vo = new TransportUnloadVO();
						BeanUtils.copyProperties(port, vo);
						//查询货物卸港信息
						Disburden disburden = new Disburden();
						disburden.setUnloadPortCode(port.getUnloadPortCode());
						disburden.setOilTypeCode(port.getOilTypeCode());
						disburden.setAgreementUuid(agreement.getUuid());
						List<Disburden> list3 = disburdenService.queryByEntitys(disburden);
						if (!list3.isEmpty()){
							Disburden shipment2 = list3.get(0);
							BeanUtils.copyProperties(shipment2, vo);
						}
						vo.setOrderNo(agreement.getOrderCode());
						vo.setArgeementUuid(agreement.getUuid());
						SysShip sysShip = sysShipService.findByUuid(agreement.getSysShipUuid());
						if (sysShip == null){
							throw new TransportException(TransportException.TYPE.ITSH023);
						}
						vo.setUnloadPort(port.getUnloadPortCode());
						vo.setOilType(port.getOilTypeCode());
						vo.setType(sysShip.getType());
						vo.setName(sysShip.getName());
						list.add(vo);
					}
				}
			}
		}
		if (!list.isEmpty()){
			iShipTransportService.insertTransportUnloading(list , memberId);
		}
	}
	
	/**
	 * 导入卸港信息
	 * @param path
	 * @param uuid
	 * @param user
	 * @return 
	 */
	@Override
	public String imports(String path, String uuid, CurrentUser user) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		List<UnloadPortImport> unloadPorts = ImportUtils.getUnLoadPort(path);
		List<String> listStr = new ArrayList<>();
		List<UnloadPortDetail> unloadPortDetails = new ArrayList<>();
		UnloadPortVO vo = new UnloadPortVO();
		vo.setList(unloadPortDetails);
		vo.setShipPactUuid(uuid);
		for (UnloadPortImport unloadPortImport : unloadPorts) {
			if (!listStr.contains(unloadPortImport.getUnloadPort()+unloadPortImport.getOilType())){
				listStr.add(unloadPortImport.getUnloadPort()+unloadPortImport.getOilType());
				UnloadPortDetail unloadPortDetail = new UnloadPortDetail();
				unloadPortDetail.setUnloadPort(unloadPortImport.getUnloadPort());
				unloadPortDetail.setOilType(unloadPortImport.getOilType());
				unloadPortDetail.setEta(unloadPortImport.getEta());
				unloadPortDetail.setExBerth(unloadPortImport.getExBerth());
				unloadPortDetail.setNorDate(unloadPortImport.getNorDate());
				unloadPortDetail.setWaterDate(unloadPortImport.getWaterDate());
				unloadPortDetail.setAtripDate(unloadPortImport.getAtripDate());
				unloadPortDetail.setBerthDate(unloadPortImport.getBerthDate());
				unloadPortDetail.setAcStart(unloadPortImport.getAcStart());
				unloadPortDetail.setAcFinish(unloadPortImport.getAcFinish());
				unloadPortDetail.setRemTubeDate(unloadPortImport.getRemTubeDate());
				unloadPortDetail.setExLeave(unloadPortImport.getExLeave());
				unloadPortDetail.setAcLeave(unloadPortImport.getAcLeave());
				unloadPortDetails.add(unloadPortDetail);
			}
		}
		for (int i=0;i<listStr.size();i++) {
			String str = "";
			List<Appoint> appoints = new ArrayList<>();
			unloadPortDetails.get(i).setList(appoints);
			for (UnloadPortImport unloadPortImport : unloadPorts) {
				if(listStr.get(i).equals(unloadPortImport.getUnloadPort()+unloadPortImport.getOilType())){
					Appoint appoint = new Appoint();
					appoint.setAgreementUuid(unloadPortImport.getAgreementUuid());
					str = str + unloadPortImport.getAgreementUuid()+";";
					appoint.setAgreementCode(unloadPortImport.getAgreementCode());
					appoint.setInspection(unloadPortImport.getInspection());
					appoint.setInspectionTel(unloadPortImport.getInspectionTel());
					appoint.setAgency(unloadPortImport.getAgency());
					appoint.setAgencyTel(unloadPortImport.getAgencyTel());
					appoint.setMonitor(unloadPortImport.getMonitor());
					appoint.setMonitorTel(unloadPortImport.getMonitorTel());
					appoints.add(appoint);
					all++;
				}
			}
			unloadPortDetails.get(i).setAgreementUuid(str);
		}
		UnloadPort port = new UnloadPort();
		port.setShipPactUuid(vo.getShipPactUuid());
		List<UnloadPort> list = this.queryByEntitys(port);
		//校验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(uuid);
		if (list != null && !list.isEmpty()){
			//删除原有数据
			Map<String,Object> map = new HashMap<>();
			map.put("shipPactId", shipPact.getShipPactId());
			map.put("updateUser", user.getMemberId());
			this.deleteRecords(map);
		}
		//插入数据
		List<Map<String,Object>> lists = new ArrayList<>();
		List<String> agreementUuids = new ArrayList<>();
		List<Agreement> agreements = new ArrayList<>();
		Integer rankNum =1;
		try{
			for (UnloadPortDetail loads : unloadPortDetails) {
				listStr.add(loads.getUnloadPort()+loads.getOilType());
				UnloadPort load = BeanConvertUtils.beanToBean(loads, UnloadPort.class);
	//			load.setAccessory1(vo.getAccessory1());
	//			load.setAccessory2(vo.getAccessory2());
	//			load.setAccessory3(vo.getAccessory3());
	//			load.setAccessory4(vo.getAccessory4());
	//			load.setAccessory1Path(vo.getAccessory1Path());
	//			load.setAccessory2Path(vo.getAccessory2Path());
	//			load.setAccessory3Path(vo.getAccessory3Path());
	//			load.setAccessory4Path(vo.getAccessory4Path());
				load.setShipPactUuid(vo.getShipPactUuid());
				load.setMonitor(vo.getMonitor());
				load.setMonitorTel(vo.getMonitorTel());
				load.setAgency(vo.getAgency());
				load.setAgencyTel(vo.getAgencyTel());
				load.setInspection(vo.getInspection());
				load.setInspectionTel(vo.getInspectionTel());
				
				//必填
				if (StringUtils.isNullOrEmpty(load.getUnloadPort())){
					throw new TransportException(TransportException.TYPE.ITSH075);
				}/* else if (load.getEta() == null){
					throw new TransportException(TransportException.TYPE.ITSH049);
				} else if (load.getExBerth() == null){
					throw new TransportException(TransportException.TYPE.ITSH050);
				} else if (load.getNorDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH051);
				} else if (load.getWaterDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH052);
				} else if (load.getAtripDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH053);
				} else if (load.getBerthDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH054);
				} else if (load.getAcStart() == null){
					throw new TransportException(TransportException.TYPE.ITSH077);
				} else if (load.getAcFinish() == null){
					throw new TransportException(TransportException.TYPE.ITSH078);
				} else if (load.getExLeave() == null){
					throw new TransportException(TransportException.TYPE.ITSH057);
				} else if (load.getAcLeave() == null){
					throw new TransportException(TransportException.TYPE.ITSH058);
				} else if (load.getRemTubeDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH059);
				} else if (StringUtils.isNullOrEmpty(load.getInspection()) ){
					throw new TransportException(TransportException.TYPE.ITSH079);
				} else if ( StringUtils.isNullOrEmpty(load.getInspectionTel())){
					throw new TransportException(TransportException.TYPE.ITSH080);
				} else if ( StringUtils.isNullOrEmpty(load.getMonitor())){
					throw new TransportException(TransportException.TYPE.ITSH081);
				} else if ( StringUtils.isNullOrEmpty(load.getMonitorTel())){
					throw new TransportException(TransportException.TYPE.ITSH082);
				} else if ( StringUtils.isNullOrEmpty(load.getAgency())){
					throw new TransportException(TransportException.TYPE.ITSH083);
				} else if ( StringUtils.isNullOrEmpty(load.getAgencyTel())){
					throw new TransportException(TransportException.TYPE.ITSH084);
				} else if (StringUtils.isNullOrEmpty(load.getAgreementUuid())){
					throw new TransportException(TransportException.TYPE.ITSH026);
				}*/
				String agreementUuid = load.getAgreementUuid();
				String[] str = agreementUuid.split(";");
				String code="";
				for (String agreeUuid : str) {
					Agreement agreement = agreementService.findByUuid(agreeUuid);
					//修改协议执行中
					if (!Constants.AGREEMENT_STATUS_2.equals(agreement.getStatus()) && !Constants.AGREEMENT_STATUS_3.equals(agreement.getStatus())){
						throw new TransportException(TransportException.TYPE.ITSH001);
					}
					agreement.setStatus(Constants.AGREEMENT_STATUS_3);
					agreement.setUpdateDate(DateTimeUtils.currentDate());
					agreement.setUpdateUser(user.getMemberId());
					agreementService.updateRecord(agreement);
					
					if (!agreementUuids.contains(agreement.getUuid())){
						agreementUuids.add(agreement.getUuid());
						agreements.add(agreement);
					}
					
					String agreementCode = agreement.getAgreementCode();
					code=code+agreementCode+";";
				
					Map<String,Object> map = new HashMap<>();
					map.put("agreementUuid", agreeUuid);
					map.put("unloadPort", load.getUnloadPort());
					map.put("oilType", load.getOilType());
					lists.add(map);
				}
				load.setExt1(String.valueOf(rankNum));
				rankNum = rankNum+1;
				load.setAgreementCode(code);
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
				
				//添加卸港协议关系
				if (loads.getList() == null || loads.getList().isEmpty()){
					throw new TransportException(TransportException.TYPE.ITSH544);
				}
				for (Appoint appoint :loads.getList()) {
					//校验必填
					appoint.setUnloadPortUuid(load.getUuid());
					this.checkAppoint(appoint);
					
					appoint.setUuid(KeyGenUtils.newUuid());
					appoint.setCreateDate(DateTimeUtils.currentDate());
					appoint.setUpdateDate(DateTimeUtils.currentDate());
					appoint.setLangVer(Constants.LANG_VER);
					appoint.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					appoint.setUpdateUser(user.getMemberId());
					appoint.setCreateUser(user.getMemberId());
					appoint.setEpMemberId(user.getEpMemberId());
					appoint.setUnloadPort(load.getUnloadPort());
					appoint.setUnloadPortId(load.getUnloadPortId());
					appointService.insertRecord(appoint);
					suceessFn++;
				}
			}
		}catch (Exception e) {
			falseFn++;
			e.printStackTrace();
		}
		falseFn=all-suceessFn;
		//删除多余的货物卸港信息
		disburdenService.deletByLoadAndShipPact(lists,vo.getShipPactUuid());
		
		//同步卸港数据到订单
		try {
			this.sendUnloadPort(agreements,user.getMemberId());
		} catch (BizException e) {
			log.error("同步订单卸港信息异常："+e);
			throw new TransportException(e.getCode(),"同步订单卸港信息异常："+e.getMessage());
		} catch (Exception e) {
			log.error("同步订单卸港信息异常："+e);
			throw new TransportException(TransportException.TYPE.ITSH539);
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}
	
	
	
	private void insertImg(List<String> imgList , Long id , CurrentUser user){
		if (imgList != null && !imgList.isEmpty()){
			for (String string : imgList) {
				Accessory accessory = new Accessory();
				accessory.setUuid(KeyGenUtils.newUuid());
				accessory.setId(id);
				accessory.setPath(string);
				accessory.setType("5");
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
	private List<UnloadPortDetail> setImg(List<UnloadPort> list) {
		List<UnloadPortDetail> list2 =new ArrayList<>();
		for (UnloadPort loadPort : list) {
			UnloadPortDetail detail= new UnloadPortDetail();
			BeanUtils.copyProperties(loadPort, detail);
			//图片
			Accessory accessory = new Accessory();
			accessory.setId(loadPort.getUnloadPortId());
			accessory.setType("5");
			List<Accessory> list3 = accessoryService.queryByEntitys(accessory);
			List<String> imgList = new ArrayList<>();
			for (Accessory name : list3) {
				imgList.add(name.getPath());
			}
			detail.setImgList(imgList);
			//指定
			Appoint appoint = new Appoint();
			appoint.setUnloadPortUuid(detail.getUuid());
			List<Appoint> list4 = appointService.queryByEntitys(appoint);
			detail.setList(list4);
			list2.add(detail);
		}
		return list2;
	}
}
