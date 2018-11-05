package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
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
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.VoyageStartMapper;
import com.sinochem.crude.trade.transport.domain.Agency;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.AgencyNameVO;
import com.sinochem.crude.trade.transport.model.VoyageStartVO;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.model.res.VoyageStartDetail;
import com.sinochem.crude.trade.transport.model.res.VoyageStartImport;
import com.sinochem.crude.trade.transport.model.res.VoyageStartRes;
import com.sinochem.crude.trade.transport.service.AgencyService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.VoyageStartService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class VoyageStartServiceImpl implements VoyageStartService {
	@Autowired
	private VoyageStartMapper _VoyageStartMapper;
	
	@Autowired
	private ShipPactService shipPactService;
	/*@Autowired
	private ShipPlateService shipPlateService;*/
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private CommonService commonService;
	
	
	public VoyageStartMapper getMapper(){
		return _VoyageStartMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<VoyageStart> queryByEntitys(VoyageStart voyagestart){
		return  _VoyageStartMapper.queryByEntitys(voyagestart);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public VoyageStart findByPrimaryKey(Long voyageStartId){
		return  _VoyageStartMapper.findByPrimaryKey(voyageStartId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public VoyageStart findByUuid(String uuid){
		return  _VoyageStartMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(VoyageStart voyagestart) {
		 _VoyageStartMapper.updateRecord(voyagestart);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long voyageStartId  , Long deleteUser) {
		 _VoyageStartMapper.deleteRecordByKey(voyageStartId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(VoyageStart voyagestart){
		 _VoyageStartMapper.insertRecord(voyagestart);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long voyageStartId){
		 _VoyageStartMapper.deleteRecordByKey(voyageStartId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _VoyageStartMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _VoyageStartMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _VoyageStartMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_VoyageStartMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_VoyageStartMapper.updateRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 船舶航次开始信息维护
	 */
	@Override
	@Transactional
	public void saveVoyageStart(VoyageStartVO vo, CurrentUser user) {
		
		// 检验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		if (Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) || Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		
		//校验必填
		List<VoyageStartDetail> list = vo.getList();
		if (list == null || list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH040);
		}
		this.insertInList(list, vo, shipPact, user);
		//修改船合同状态
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		if (Constants.SHIP_PACT_STATUS_2.equals(shipPact.getStatus())){
			shipPact.setStatus(Constants.SHIP_PACT_STATUS_3);
			//修改协议运单状态
			Waybill waybill = new Waybill();
			waybill.setShipPactUuid(shipPact.getUuid());
			List<Waybill> list2 = waybillService.queryByEntitys(waybill);
			if (list2 != null && !list2.isEmpty()){
				Waybill waybill2 = list2.get(0);
				if (waybill2 != null){
					waybill2.setStatus("3");;
					waybill2.setUpdateDate(DateTimeUtils.currentDate());
					waybill2.setUpdateUser(user.getMemberId());
					waybillService.updateRecord(waybill2);
					Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
					if (agreement != null){
						agreement.setStatus("3");
						agreement.setUpdateDate(DateTimeUtils.currentDate());
						agreement.setUpdateUser(user.getMemberId());
						agreementService.updateRecord(agreement);
					}
				}
			}
		}
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		shipPactService.updateRecord(shipPact);
		
		
		//添加装卸港船代
		List<AgencyNameVO> listAgency = vo.getListAgency();
		if (listAgency != null && !listAgency.isEmpty()){
			for (AgencyNameVO agencyvo : listAgency) {
				Agency agency = new Agency();
				if (!StringUtils.isNullOrEmpty(agencyvo.getType()) && agencyvo.getId() != null && !StringUtils.isNullOrEmpty(agencyvo.getCode())){
				//	if (!StringUtils.isNullOrEmpty(agencyvo.getType()) && !StringUtils.isNullOrEmpty(agencyvo.getName())
				//	&& !StringUtils.isNullOrEmpty(agencyvo.getPort()) && agencyvo.getId() != null){
					EnterpriseVo emp = commonService.queryUserByEpMemberId(agencyvo.getId());
					ValueSetName name = commonService.findNameByCode(agencyvo.getType(), agencyvo.getCode());
					String zhName = name.getZhName();
					String enName = name.getEnName();
					if (StringUtils.isNullOrEmpty(zhName)) {
						zhName = agencyvo.getCode();
					}
					if (StringUtils.isNullOrEmpty(enName)) {
						enName = agencyvo.getCode();
					}
					agency.setEpMemberId(agencyvo.getId());
					agency.setEpMemberName(emp.getFullName());
					agency.setEpMemberNameEn(emp.getEnglishName());
					agency.setPort(zhName);
					agency.setPortCode(agencyvo.getCode());
					agency.setPortEn(enName);
					agency.setType(agencyvo.getType());
					agency.setShipPactId(shipPact.getShipPactId());
					agency.setShipPactUuid(shipPact.getUuid());
					agency.setUuid(KeyGenUtils.newUuid());
					agency.setCreateDate(DateTimeUtils.currentDate());
					agency.setUpdateDate(DateTimeUtils.currentDate());
					agency.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					agency.setUpdateUser(user.getMemberId());
					agency.setCreateUser(user.getMemberId());
					agencyService.insertRecord(agency);
				}
			}
		}
		
		//发送消息
		messagePushService.messagePush(1, shipPact.getShipPactId(), user.getMemberId());
		messagePushService.messagePush(18, shipPact.getShipPactId(), user.getMemberId());
		messagePushService.messagePush(19, shipPact.getShipPactId(), user.getMemberId());
	}

	/**
	 * 航次开始信息修改
	 */
	@Override
	@Transactional
	public void upateVoyageStart(VoyageStartVO vo, CurrentUser user) {
		
		//删除原有数据
		ShipPact shipPact = shipPactService.findByUuid(vo.getShipPactUuid());
		Map<String,Object> map = new HashMap<>();
		map.put("shipPactId", shipPact.getShipPactId());
		map.put("updateUser", user.getMemberId());
		this.deleteRecords(map);
		//删除装卸港船代
		agencyService.deleteRecords(map);
		
		//新增 校验必填
		List<VoyageStartDetail> list = vo.getList();
		if (list == null || list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH040);
		}
		this.insertInList(list, vo, shipPact, user);
		
		//添加装卸港船代
		List<AgencyNameVO> listAgency = vo.getListAgency();
		if (listAgency != null && !listAgency.isEmpty()){
			for (AgencyNameVO agencyvo : listAgency) {
				Agency agency = new Agency();
				if (!StringUtils.isNullOrEmpty(agencyvo.getType()) && agencyvo.getId() != null && !StringUtils.isNullOrEmpty(agencyvo.getCode())){
				//	if (!StringUtils.isNullOrEmpty(agencyvo.getType()) && !StringUtils.isNullOrEmpty(agencyvo.getName())
				//	&& !StringUtils.isNullOrEmpty(agencyvo.getPort()) && agencyvo.getId() != null){
					EnterpriseVo emp = commonService.queryUserByEpMemberId(agencyvo.getId());
					ValueSetName name = commonService.findNameByCode(agencyvo.getType(), agencyvo.getCode());
					String zhName = name.getZhName();
					String enName = name.getEnName();
					if (StringUtils.isNullOrEmpty(zhName)) {
						zhName = agencyvo.getCode();
					}
					if (StringUtils.isNullOrEmpty(enName)) {
						enName = agencyvo.getCode();
					}
					agency.setEpMemberId(agencyvo.getId());
					agency.setEpMemberName(emp.getFullName());
					agency.setEpMemberNameEn(emp.getEnglishName());
					agency.setPort(zhName);
					agency.setPortCode(agencyvo.getCode());
					agency.setPortEn(enName);
					agency.setType(agencyvo.getType());
					agency.setShipPactId(shipPact.getShipPactId());
					agency.setShipPactUuid(shipPact.getUuid());
					agency.setUuid(KeyGenUtils.newUuid());
					agency.setCreateDate(DateTimeUtils.currentDate());
					agency.setUpdateDate(DateTimeUtils.currentDate());
					agency.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					agency.setUpdateUser(user.getMemberId());
					agency.setCreateUser(user.getMemberId());
					agencyService.insertRecord(agency);
				}
			}
		}
		
		//发送消息
		messagePushService.messagePush(1, shipPact.getShipPactId(), user.getMemberId());
		messagePushService.messagePush(18, shipPact.getShipPactId(), user.getMemberId());
		messagePushService.messagePush(19, shipPact.getShipPactId(), user.getMemberId());
	}
	
	
	/**
	 * 查询航次开始信息
	 */
	@Override
	public VoyageStartRes findVoyageStartDetail(String shipPactUuid) {
		VoyageStartRes res =  new VoyageStartRes();
		
		//根据运单uuid查询运单
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		VoyageStart start = new VoyageStart();
		start.setShipPactId(shipPact.getShipPactId());
		List<VoyageStart> list = this.queryByEntitys(start);
		if (list != null && !list.isEmpty()){
			List<VoyageStartDetail> lists = BeanConvertUtils.beanToBeanInList(list, VoyageStartDetail.class);
			List<VoyageStartDetail> lists2 = new ArrayList<>();
			for (VoyageStartDetail starts : lists) {
				String oilTypeCode = starts.getOilTypeCode();
				String string = commonService.findNameByCodeAndLang("3", oilTypeCode);
				if (StringUtils.isNullOrEmpty(string)) {
					string = oilTypeCode;
				}
				starts.setOilType(string);
				lists2.add(starts);
			}
			res.setList(lists2);
			res.setAccessory(list.get(0).getAccessory());
			res.setAccessoryPath(list.get(0).getAccessoryPath());
			res.setShipPactUuid(list.get(0).getShipPactUuid());
		}
		
		//查询运单油种信息
		Waybill waybill = new Waybill();
		//waybill.setShipPactId(shipPact.getShipPactId());//数据库里没存这个字段
		waybill.setShipPactUuid(shipPact.getUuid());
		List<Waybill> list2 = waybillService.queryByEntitys(waybill);
		List<Map<String,Object>> oilList = new ArrayList<>();
		List<String> strList = new ArrayList<>();
		if (list2 != null && !list2.isEmpty()){
			for (Waybill way : list2) {
				/*String loadPort = way.getOiiType();
				if (!StringUtils.isNullOrEmpty(loadPort)){
					//分割油种
					String[] split = loadPort.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str) && !oilList.contains(str)){
								oilList.add(str);
						}
					}
				}*/
				String oilTypeCode = way.getOilTypeCode();
				if (!StringUtils.isNullOrEmpty(oilTypeCode)){
					//分割油种
					String[] split = oilTypeCode.split("/");
					for (String str : split) {
						if (!StringUtils.isNullOrEmpty(str) && !strList.contains(str)){
							Map<String,Object> map = new HashMap<>();
							String name = commonService.findNameByCodeAndLang("3", str);
							if (StringUtils.isNullOrEmpty(name)){
								name = str;
							}
							map.put("code", str);
							map.put("name", name);
							strList.add(str);
							oilList.add(map);
						}
					}
				}
			}
		}
		res.setOilList(oilList);
		
		Agency agency = new Agency();
		agency.setShipPactUuid(shipPactUuid);
		List<Agency> listAgency = agencyService.queryByEntitys(agency);
		List<Agency> agLists = new ArrayList<>();
		for (Agency ag : listAgency) {
			String code = ag.getPortCode();
			String name = commonService.findNameByCodeAndLang(ag.getType(), code);
			if (StringUtils.isNullOrEmpty(name)) {
				name = code;
			}
			ag.setPort(name);
			agLists.add(ag);
		}
		res.setListAgency(agLists);
		return res;
	}

	/**
	 * 新增
	 * @param list
	 * @param vo
	 * @param shipPact
	 * @param user
	 */
	private void insertInList(List<VoyageStartDetail> list ,VoyageStartVO vo,ShipPact shipPact,CurrentUser user){
	
		for (VoyageStartDetail starts : list) {
			VoyageStart start = BeanConvertUtils.beanToBean(starts, VoyageStart.class);
			start.setAccessory(vo.getAccessory());
			start.setAccessoryPath(vo.getAccessoryPath());
			//必填
			if (StringUtils.isNullOrEmpty(start.getOilType())){
				throw new TransportException(TransportException.TYPE.ITSH041);
			}/* else if (start.getQuantity() == null){
				throw new TransportException(TransportException.TYPE.ITSH042);
			} else if (start.getApi() == null){
				throw new TransportException(TransportException.TYPE.ITSH046);
			} else if (start.getLoadTemp() == null){
				throw new TransportException(TransportException.TYPE.ITSH043);
			} else if (start.getLoadDraft() == null){
				throw new TransportException(TransportException.TYPE.ITSH044);
			} else if (start.getUnloadDraft() == null){
				throw new TransportException(TransportException.TYPE.ITSH045);
			}*/
			start.setShipPlateId(shipPact.getShipPlateId());
			start.setShipPlateUuid(shipPact.getShipPlateUuid());
			start.setShipPactId(shipPact.getShipPactId());
			start.setShipPactUuid(shipPact.getUuid());
			start.setUuid(KeyGenUtils.newUuid());
			start.setCreateDate(DateTimeUtils.currentDate());
			start.setUpdateDate(DateTimeUtils.currentDate());
			start.setLangVer(Constants.LANG_VER);
			start.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			start.setUpdateUser(user.getMemberId());
			start.setCreateUser(user.getMemberId());
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			start.setEpMemberId(user.getEpMemberId());
			this.insertRecord(start);
		}
		
	}

	/**
	 * 校验是否维护航次开始
	 */
	@Override
	public void checkIsEdit(String shipPactUuid) {
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		VoyageStart voyageStart = new VoyageStart();
		voyageStart.setShipPactId(shipPact.getShipPactId());
		List<VoyageStart> list = this.queryByEntitys(voyageStart);
		if (list == null || list.isEmpty()){
			throw new TransportException(TransportException.TYPE.ITSH548);
		}
	}

	/**
	 * 查询航次开始油种信息
	 */
	@Override
	public List<Map<String, Object>> findOilList(String shipPactUuid) {
		List<Map<String, Object>> list  = new ArrayList<>();
		//检验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(shipPactUuid);
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		//查询油种
		VoyageStart start = new VoyageStart();
		start.setShipPactId(shipPact.getShipPactId());
		List<VoyageStart> entitys = this.queryByEntitys(start);
		if (entitys != null && !entitys.isEmpty()){
			for (VoyageStart voyageStart : entitys) {
				Map<String, Object> map = new HashMap<>();
				String code = voyageStart.getOilTypeCode();
				String name = commonService.findNameByCodeAndLang("3", code);
				map.put("code", code);
				map.put("name", name);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 导入航次开始信息
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
		List<VoyageStartImport> VoyageStarts= ImportUtils.getVoyageStart(path);
		all = VoyageStarts.size();
		// 检验船合同是否存在
		ShipPact shipPact = shipPactService.findByUuid(uuid);
		if(shipPact==null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (Constants.SHIP_PACT_STATUS_4.equals(shipPact.getStatus()) || Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		for (VoyageStartImport VoyageStart : VoyageStarts) {
			try {
				VoyageStartDetail voyageStartDetail = new VoyageStartDetail();
				BeanUtils.copyProperties(VoyageStart,voyageStartDetail);
				VoyageStart start = BeanConvertUtils.beanToBean(voyageStartDetail, VoyageStart.class);
				if (StringUtils.isNullOrEmpty(start.getOilType())){
					throw new TransportException(TransportException.TYPE.ITSH041);
				}
				//
				//查重
				VoyageStart entity = new VoyageStart();
				entity.setShipPactUuid(uuid);
				entity.setOilType(start.getOilType());
				List<VoyageStart> entitys = _VoyageStartMapper.queryByEntitys(entity);
				if(entitys!=null&&!entitys.isEmpty()){
					throw new TransportException(TransportException.TYPE.ITSH553);
				}
				start.setShipPlateId(shipPact.getShipPlateId());
				start.setShipPlateUuid(shipPact.getShipPlateUuid());
				start.setShipPactId(shipPact.getShipPactId());
				start.setShipPactUuid(shipPact.getUuid());
				start.setUuid(KeyGenUtils.newUuid());
				start.setCreateDate(DateTimeUtils.currentDate());
				start.setUpdateDate(DateTimeUtils.currentDate());
				start.setLangVer(Constants.LANG_VER);
				start.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				start.setUpdateUser(user.getMemberId());
				start.setCreateUser(user.getMemberId());
				start.setEpMemberId(user.getEpMemberId());
				this.insertRecord(start);
				suceessFn++;
			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}
}
