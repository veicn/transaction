package com.sinochem.crude.trade.transport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.BasicTariffMapper;
import com.sinochem.crude.trade.transport.dao.TransitMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.Transit;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.TransitVO;
import com.sinochem.crude.trade.transport.model.res.TransitImport;
import com.sinochem.crude.trade.transport.model.res.TransitRes;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.service.AccessoryService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.TransitService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class TransitServiceImpl implements TransitService {
	@Autowired
	private TransitMapper _TransitMapper;
	
	@Autowired
	private ShipPactService shipPactService;
	
	/*@Autowired
	private ShipPlateService shipPlateService;*/
	
	@Autowired
	private WaybillService waybillService;
	
	@Autowired
	private BasicTariffMapper basicTariffMapper;
	
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MessagePushService messagePushService;
	public TransitMapper getMapper(){
		return _TransitMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Transit> queryByEntitys(Transit transit){
		return  _TransitMapper.queryByEntitys(transit);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Transit findByPrimaryKey(Long transitId){
		return  _TransitMapper.findByPrimaryKey(transitId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Transit findByUuid(String uuid){
		Transit transit = _TransitMapper.findByUuid(uuid);
		if (transit == null){
			throw new TransportException(TransportException.TYPE.ITSH009);
		}
		return  transit;
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Transit transit) {
		 _TransitMapper.updateRecord(transit);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long transitId  , Long deleteUser) {
		 _TransitMapper.deleteRecordByKey(transitId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Transit transit){
		 _TransitMapper.insertRecord(transit);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long transitId){
		 _TransitMapper.deleteRecordByKey(transitId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _TransitMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _TransitMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _TransitMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_TransitMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_TransitMapper.updateRecords(map);
	}

	
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增船舶在途信息维护
	 */
	@Override
	@Transactional
	public void saveTransit(TransitVO vo, CurrentUser user) {
		//检验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
				Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		//校验必填
		this.checkRequired(vo);
		
		//校验同一天只能维护一条数据
		Transit transit = new Transit();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(vo.getDateNow());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
		transit.setDateNow(zero);
		transit.setShipPactUuid(vo.getShipPactUuid());
		List<Transit> list = this.queryByEntitys(transit);
		if (!list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH536);
		}
		String unloadEtaEn = "";
		String unloadEta = "";
		String code = vo.getUnloadEtaCode();
		if (!StringUtils.isNullOrEmpty(code)) {
			String[] split = code.split(";");
			if (split != null) {
				for (String str : split) {
					String[] split2 = str.split("/");
					String unloadPort = split2[0];
					ValueSetName name = commonService.findNameByCode("2", unloadPort);
					unloadEta = unloadEta + name.getZhName() + "/" +split2[1] +";";
					unloadEtaEn = unloadEta + name.getEnName() + "/" +split2[1] +";";
				}
			}
		}
		vo.setUnloadEta(unloadEta);
		vo.setUnloadEtaEn(unloadEtaEn);
		vo.setShipPactId(shipPact.getShipPactId());
		vo.setShipPlateId(shipPact.getShipPlateId());
		vo.setShipPlateUuid(shipPact.getShipPlateUuid());
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
		
		//保存图片
		insertImg(vo.getImgList(), vo.getTransitId(), user);
		
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		
		//修改船合同状态
		if (Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_3.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus())){
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_5);
			
			//修改协议运单状态
			Waybill waybill = new Waybill();
			waybill.setShipPactUuid(shipPact.getUuid());
			List<Waybill> list2 = waybillService.queryByEntitys(waybill);
			if (list2 != null && !list2.isEmpty()){
				Waybill waybill2 = list2.get(0);
				if (waybill2 != null){
					waybill2.setStatus("5");;
					waybill2.setUpdateDate(DateTimeUtils.currentDate());
					waybill2.setUpdateUser(user.getMemberId());
					waybillService.updateRecord(waybill2);
					Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
					if (agreement != null){
						agreement.setStatus("5");
						agreement.setUpdateDate(DateTimeUtils.currentDate());
						agreement.setUpdateUser(user.getMemberId());
						agreementService.updateRecord(agreement);
					}
				}
			}
		}
		shipPactService.updateRecord(shipPact);
		//发送消息
		messagePushService.messagePush(3, shipPact.getShipPactId(), user.getMemberId());
	}
	
	/**
	 * 修改船舶装港信息
	 */
	@Override
	@Transactional
	public void updateTransit(TransitVO vo, CurrentUser user) {
		Transit transit = this.findByUuid(vo.getUuid());
		//校验必填
		this.checkRequired(vo);
		
		Map<String,Object> map = new HashMap<>();
		map.put("type", "4");
		map.put("id", transit.getTransitId());
		accessoryService.deleteRecords(map);
		//保存图片
		insertImg(vo.getImgList(), transit.getTransitId(), user);
		String unloadEtaEn = "";
		String unloadEta = "";
		String code = vo.getUnloadEtaCode();
		if (!StringUtils.isNullOrEmpty(code)) {
			String[] split = code.split(";");
			if (split != null) {
				for (String str : split) {
					String[] split2 = str.split("/");
					String unloadPort = split2[0];
					ValueSetName name = commonService.findNameByCode("2", unloadPort);
					unloadEta = unloadEta + name.getZhName() + "/" +split2[1] +";";
					unloadEtaEn = unloadEta + name.getEnName() + "/" +split2[1] +";";
				}
			}
		}
		vo.setUnloadEta(unloadEta);
		vo.setUnloadEtaEn(unloadEtaEn);
		vo.setTransitId(transit.getTransitId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		this.updateRecord(vo);
		
		ShipPact shipPact = shipPactService.findByUuid(transit.getShipPactUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		//发送消息
		messagePushService.messagePush(3, shipPact.getShipPactId(), user.getMemberId());
	}
	/**
	 *查询船舶在途信息详情
	 */
	@Override
	public TransitRes findTransitDetail(String uuid) {
		TransitRes res = new TransitRes();
		Transit transit = this.findByUuid(uuid);
		BeanUtils.copyProperties(transit, res);
		Long shipPactId = transit.getShipPactId();
		ShipPact pact = shipPactService.findByPrimaryKey(shipPactId);
		if (pact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		
		List<Map<String,Object>> lists = new ArrayList<>();
		String unloadEtaCode = res.getUnloadEtaCode();
		if (!StringUtils.isNullOrEmpty(unloadEtaCode)) {
			String[] split = unloadEtaCode.split(";");
			if (split != null) {
				for (String str : split) {
					Map<String,Object> map = new HashMap<>();
					String[] split2 = str.split("/");
					String unloadPort = split2[0];
					String name = commonService.findNameByCodeAndLang("2", unloadPort);
					map.put("code", unloadPort);
					map.put("name", name);
					map.put("eta",split2[1]);
					lists.add(map);
				}
			}
		}
		res.setLists(lists);
		
		Waybill waybill =new Waybill();
		waybill.setShipPactId(pact.getShipPactId());
		List<Waybill> list = waybillService.queryByEntitys(waybill);
		List<Map<String,Object>> strList = new ArrayList<>();
		List<String> strs = new ArrayList<>();
		if (list != null && !list.isEmpty()){
			for (Waybill way : list) {
				String unloadPort = way.getUnloadPortCode();
				if (!StringUtils.isNullOrEmpty(unloadPort)){
					//分割卸港
					String[] split = unloadPort.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str)){
							if (!strs.contains(str)){
								String name = commonService.findNameByCodeAndLang("2", str);
								Map<String,Object> map = new HashMap<>();
								map.put("code", str);
								map.put("name", name);
								strs.add(str);
								strList.add(map);
							}
						}
					}
				}
			}
		}
		//图片
		Accessory accessory = new Accessory();
		accessory.setId(transit.getTransitId());
		accessory.setType("4");
		List<Accessory> list3 = accessoryService.queryByEntitys(accessory);
		List<String> imgList = new ArrayList<>();
		for (Accessory name : list3) {
			imgList.add(name.getPath());
		}
		res.setImgList(imgList);
		Map<String,Object> map = new HashMap<>();
		map.put("unloadPortList", strList);
		res.setMap(map);
		return res;
	}


	/**
	 * 校验必填
	 * @param vo
	 */
	private void checkRequired(TransitVO vo){

		if (vo.getDateNow() == null){
			throw new TransportException(TransportException.TYPE.ITSH064);
		}/* else if (vo.getTimeNow() == null){
			throw new TransportException(TransportException.TYPE.ITSH065);
		} else if (StringUtils.isNullOrEmpty(vo.getPosition())){
			throw new TransportException(TransportException.TYPE.ITSH066);
		} else if (vo.getSpeedH() == null){
			throw new TransportException(TransportException.TYPE.ITSH067);
		} else if (vo.getSpeedAll() == null){
			throw new TransportException(TransportException.TYPE.ITSH068);
		} else if (vo.getRpm() == null){
			throw new TransportException(TransportException.TYPE.ITSH069);
		} else if (StringUtils.isNullOrEmpty(vo.getUnloadEta())){
			throw new TransportException(TransportException.TYPE.ITSH070);
		} else if (StringUtils.isNullOrEmpty(vo.getSea())){
			throw new TransportException(TransportException.TYPE.ITSH071);
		} else if (StringUtils.isNullOrEmpty(vo.getWater())){
			throw new TransportException(TransportException.TYPE.ITSH072);
		} else if (StringUtils.isNullOrEmpty(vo.getSulfide())){
			throw new TransportException(TransportException.TYPE.ITSH073);
		}*/
	}

	/**
	 * 查询船舶在途信息列表
	 */
	@Override
	public Page<Map<String, Object>> queryTransitList(String shipPactUuid,
			SimplePageInfo pageInfo) {
		
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		
		Transit transit = new Transit();
		transit.setShipPactId(shipPact.getShipPactId());
		Page<Map<String,Object>> page = this.queryRecords(BeanConvertUtils.beanToMap(transit), pageInfo);
		
		return page;
	}

	/**
	 * 查询船在途卸港信息
	 */
	@Override
	public List<Map<String, Object>> unloadList(String shipPactUuid) {
		ShipPact pact = shipPactService.findByUuid(shipPactUuid);
		if (pact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		Waybill waybill =new Waybill();
		waybill.setShipPactId(pact.getShipPactId());
		List<Waybill> list = waybillService.queryByEntitys(waybill);
		List<Map<String, Object>> strList = new ArrayList<>();
		List<String> strs = new ArrayList<>();
		if (list != null && !list.isEmpty()){
			for (Waybill way : list) {
				String unloadPort = way.getUnloadPortCode();
				if (!StringUtils.isNullOrEmpty(unloadPort)){
					//分割卸港
					String[] split = unloadPort.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str)){
							if (!strs.contains(str)){
								String name = commonService.findNameByCodeAndLang("2", str);
								Map<String, Object> map = new HashMap<>();
								map.put("code", str);
								map.put("name", name);
								strList.add(map);
							}
						}
					}
				}
			}
		}
		return strList;
	}

	/**
	 * 删除船在途信息
	 */
	@Override
	public void deleteTransitByUuid(String uuid, CurrentUser user) {
		Transit transit = this.findByUuid(uuid);
		if (transit == null){
			throw new TransportException(TransportException.TYPE.ITSH009);
		}
		this.deleteRecordByKey(transit.getTransitId(), user.getMemberId());
	}

	/**
	 * 导入
	 */
	@Override
	public String imports(String path, String uuid, CurrentUser user) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		List<TransitImport> transit = ImportUtils.getTransit(path);
		all = transit.size();
		for (TransitImport transitImport : transit) {
			try {
				TransitVO vo = new TransitVO();
				vo.setShipPactUuid(uuid);
				BeanUtils.copyProperties(transitImport, vo);
				this.saveTransit(vo , user);
				suceessFn++;
			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}


	/**
	 * 导入基础运价
	 */
	@Override
	public String importsBt(List<BasicTariff> list, CurrentUser user) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (BasicTariff basicTariff : list) {
			try {
				Map<String, String> btmap = new HashMap<>();
				btmap.put("loadPort1", basicTariff.getLoadPort1());
				btmap.put("unloadPort1", basicTariff.getUnloadPort1());
				btmap.put("year", basicTariff.getYear()+"");
				if(StringUtils.isNullOrEmpty(basicTariff.getLoadPort2())){
					btmap.put("loadPort2", "");
				}else{
					btmap.put("loadPort2", basicTariff.getLoadPort2());
				}
				
				if(StringUtils.isNullOrEmpty(basicTariff.getLoadPort3())){
					btmap.put("loadPort3", "");
				}else{
					btmap.put("loadPort3", basicTariff.getLoadPort3());
				}
				
				if(StringUtils.isNullOrEmpty(basicTariff.getUnloadPort2())){
					btmap.put("unloadPort2", "");
				}else{
					btmap.put("unloadPort2", basicTariff.getUnloadPort2());
				}
				
				if(StringUtils.isNullOrEmpty(basicTariff.getUnloadPort3())){
					btmap.put("unloadPort3", "");
				}else{
					btmap.put("unloadPort3", basicTariff.getUnloadPort3());
				}
				
				BasicTariff checkBt = basicTariffMapper.checkBt(btmap);
				if(checkBt == null){
					basicTariffMapper.insertRecord(basicTariff);					
				}else{
					basicTariff.setUuid(checkBt.getUuid());
					basicTariffMapper.updateRecordByUuid(basicTariff);
				}
				suceessFn++;
			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}

	@Override
	public Page<Map<String, Object>> queryMoreTransitList(String shipPactUuid,
			SimplePageInfo pageInfo) {
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		
		Transit transit = new Transit();
		transit.setShipPactId(shipPact.getShipPactId());
		Page<Map<String,Object>> page = this.queryRecords(BeanConvertUtils.beanToMap(transit), pageInfo);
		//返回数据调整
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : page) {
			
			//Transit lists = BeanConvertUtils.mapToBean(map2, Transit.class);
			String unloadEta = "";
			Object object = map.get("unloadEta");
			if (object != null){
				unloadEta=(String)object;
			}
			String unload="";
			String ETA="";
			if (!StringUtils.isNullOrEmpty(unloadEta)){
				String[] split = unloadEta.split(";");
				for (String str : split) {
					if (!StringUtils.isNullOrEmpty(str)){
						String[] split2 = str.split(" ");
						if (split2 != null && split2.length >0){
							String str2 = split2[0];
							if (!StringUtils.isNullOrEmpty(str2)){
								String[] split3 = str2.split("/");
								if (split3.length == 2){
									ETA=ETA+split3[1]+"/";
									unload=unload+split3[0]+"/";
								}
							}
						}
					} }
				if (!StringUtils.isNullOrEmpty(unload)){
					unload=unload.substring(0,unload.length()-1);
				}
				if (!StringUtils.isNullOrEmpty(ETA)){
					ETA=ETA.substring(0,ETA.length()-1);
				}
			}
			//Date dateNow = new Date();
			String dates ="";
			Object obj2 = map.get("dateNow");
			if (obj2 != null){
				 dates = format.format(obj2);
			}
			String timeNow = "";
			Object obj3 = map.get("timeNow");
			if (obj3 != null){
				timeNow = (String) obj3;
			}
			
			Map<String,Object> map2 = new HashMap<>();
			map2.put("dateNow", dates);
			map2.put("timeNow", timeNow);
			map2.put("unload", unload);
			map2.put("ETA", ETA);
			map2.put("position",map.get("position"));
			map2.put("speedH",map.get("speedH"));
			map2.put("speedAll", map.get("speedAll"));
			map2.put("rpm",map.get("rpm"));
			map2.put("sea",map.get("sea"));
			map2.put("water",map.get("water"));
			map2.put("sulfide",map.get("sulfide"));
			map2.put("remark", map.get("remark"));
			map.clear();
			map.putAll(map2);
		}
		
		return page;
	}
	private void insertImg(List<String> imgList , Long id , CurrentUser user){
		if (imgList != null && !imgList.isEmpty()){
			for (String string : imgList) {
				Accessory accessory = new Accessory();
				accessory.setUuid(KeyGenUtils.newUuid());
				accessory.setId(id);
				accessory.setPath(string);
				accessory.setType("4");
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
}
