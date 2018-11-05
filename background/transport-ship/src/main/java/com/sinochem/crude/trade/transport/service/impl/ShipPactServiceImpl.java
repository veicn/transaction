package com.sinochem.crude.trade.transport.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.FindedShipInfoVO;
import com.sinochem.crude.trade.orderexecute.remote.IShipTransportService;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.ShipPactMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Appoint;
import com.sinochem.crude.trade.transport.domain.Disburden;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.Transit;
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.model.res.LoadNameSort;
import com.sinochem.crude.trade.transport.model.res.LoadTransit;
import com.sinochem.crude.trade.transport.model.res.ShipPactRes;
import com.sinochem.crude.trade.transport.model.res.ShipRealTime;
import com.sinochem.crude.trade.transport.model.res.TransitRes;
import com.sinochem.crude.trade.transport.service.AccessoryService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.AppointService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.DisburdenService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.LoadPortService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.ShipmentService;
import com.sinochem.crude.trade.transport.service.SumCountService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.TransitService;
import com.sinochem.crude.trade.transport.service.UnloadPortService;
import com.sinochem.crude.trade.transport.service.VoyageStartService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class ShipPactServiceImpl implements ShipPactService {
	
	@Autowired
	private ShipPactMapper _ShipPactMapper;
	@Autowired
	private ShipPlateService shipPlateService;
	@Autowired
	private PalletService palletService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private VoyageStartService voyageStartService;
	@Autowired
	private LoadPortService loadPortService;
	@Autowired
	private ShipmentService shipmentService;
	@Autowired
	private DisburdenService disburdenService;
	@Autowired
	private TransitService transitService;
	@Autowired
	private UnloadPortService unloadPortService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private SysShipService sysShipService;
	@Autowired
	private SumCountService sumCountService; 
	@Autowired
	private AccessoryService accessoryService; 
	@Autowired
	private IntentionService intentionService; 
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private IShipTransportService iShipTransportService;
	
	
	public ShipPactMapper getMapper() {
		return _ShipPactMapper;
	}

	/**
	 * 根据对象-查询对象列表
	 */
	public List<ShipPact> queryByEntitys(ShipPact shippact) {
		return _ShipPactMapper.queryByEntitys(shippact);
	}

	/**
	 * 根据主键-查询对象
	 */
	public ShipPact findByPrimaryKey(Long shipPactId) {
		return _ShipPactMapper.findByPrimaryKey(shipPactId);
	}

	/**
	 * 根据UUID-查询对象
	 */
	public ShipPact findByUuid(String uuid){
		
		ShipPact shipPact = _ShipPactMapper.findByUuid(uuid);
		if (shipPact == null ){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		return  shipPact;
		
		
		
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(ShipPact shippact) {
		_ShipPactMapper.updateRecord(shippact);
	}

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long shipPactId, Long deleteUser) {
		_ShipPactMapper.deleteRecordByKey(shipPactId, deleteUser);
	}

	/**
	 * 新增
	 */
	public void insertRecord(ShipPact shippact) {
		_ShipPactMapper.insertRecord(shippact);
	}

	/*
	 * 根据主键删除数据 public void deleteRecordByKey(Long shipPactId){
	 * _ShipPactMapper.deleteRecordByKey(shipPactId); }
	 */
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return _ShipPactMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _ShipPactMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map) {
		return _ShipPactMapper.countRecords(map);
	}

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) {
		_ShipPactMapper.deleteRecords(map);
	}

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_ShipPactMapper.updateRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/
	
	private Page<Map<String, Object>> queryRecordsByPer(
			Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _ShipPactMapper.queryRecordsByPer(map);
	}
	
	
	/**
	 * 生成船合同
	 */
	@Override
	@Transactional
	public void saveShipPact(ShipPactVO vo, CurrentUser user) {
		Map<String,Object> map = new HashMap<>();
		map.put("uuid", vo.getShipPlateUuid());
		map.put("updateDate",DateTimeUtils.currentDate());
		shipPlateService.updateRecordsFn(map);
		//校验必填
		this.checkShipPactRequired(vo);
		//校验船盘
		ShipPlate shipPlate = shipPlateService.findByUuid(vo.getShipPlateUuid());
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		if (!Constants.SHIP_PLATE_STATUS_3.equals(shipPlate.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH014);
		}
		SysShip ship = sysShipService.findByUuid(vo.getSysShipUuid());
		if (ship != null){
			vo.setMmsi(ship.getMmsi());
		}
		
		// 上传附件 船合同编号
		SimpleDateFormat format =  new SimpleDateFormat("yyMMdd");
		Date signDate = vo.getSignDate();
		vo.setPactCode(vo.getShipName()+format.format(signDate));
		vo.setShipPlateId(shipPlate.getShipPlateId());
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setCreateUser(user.getMemberId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		vo.setEpMemberId(user.getEpMemberId());
		vo.setStatus(Constants.SHIP_PACT_STATUS_1);
		vo.setDetailStatus(Constants.SHIP_PACT_DETAIL_STATUS_1);
		
		
		/**设置企业、经纪人、船东、租船人中英文*/
		EnterpriseVo broker = commonService.queryUserByEpMemberId(vo.getBrokerId());
		EnterpriseVo emp = commonService.queryUserByEpMemberId(user.getEpMemberId());
		EnterpriseVo own = commonService.queryUserByEpMemberId(vo.getShipOwnerId());
		EnterpriseVo car = commonService.queryUserByEpMemberId(vo.getCarrierId());
		
		vo.setEpMemberNameEn(emp.getEnglishName());
		vo.setBrokerName(broker.getFullName());
		vo.setBrokerNameEn(broker.getEnglishName());
		vo.setCarrierName(car.getFullName());
		vo.setCarrierNameEn(car.getEnglishName());
		vo.setShipOwner(own.getFullName());
		vo.setShipOwnerEn(own.getEnglishName());
		this.insertRecord(vo);
		
		
		//修改船盘状态
		shipPlate.setStatus(Constants.SHIP_PLATE_STATUS_2);
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		shipPlateService.updateRecord(shipPlate);
		
		Intention intention  = new Intention();
		intention.setIntentionId(vo.getShipIntentionId());
		intention.setUuid(vo.getShipIntentionUuid());
		intention.setStatus(Constants.INTENTION_STATUS_4);
		intention.setUpdateDate(DateTimeUtils.currentDate());
		intention.setUpdateUser(user.getMemberId());
		intentionService.updateRecord(intention);
	}
	
	
	/**
	 * 校验必填
	 * @param vo
	 */
	private void checkShipPactRequired(ShipPactVO vo) {
		if (vo.getSignDate() == null){
			throw new TransportException(TransportException.TYPE.ITSH024);	
		} else if (StringUtils.isNullOrEmpty(vo.getCarrierName()) ){
			throw new TransportException(TransportException.TYPE.ITSH028);
		} else if (StringUtils.isNullOrEmpty(vo.getShipOwner())){
			throw new TransportException(TransportException.TYPE.ITSH029);
		}
		/* else if (StringUtils.isNullOrEmpty(vo.getSysShipUuid())){
			throw new TransportException(TransportException.TYPE.ITSH030);
		}*/ 
		else if (vo.getPactBeg() == null || vo.getPactEnd() == null){
			throw new TransportException(TransportException.TYPE.ITSH031);
		} else if (vo.getMinQuantity() == null){
			throw new TransportException(TransportException.TYPE.ITSH032);
		} else if ( StringUtils.isNullOrEmpty(vo.getWsRes()) ){
			throw new TransportException(TransportException.TYPE.ITSH033);
		} else if (StringUtils.isNullOrEmpty(vo.getPactSpeed())  ){
			throw new TransportException(TransportException.TYPE.ITSH034);
		} else if (vo.getDemurrage() == null ){
			throw new TransportException(TransportException.TYPE.ITSH035);
		} else if (StringUtils.isNullOrEmpty(vo.getBrokerName()) ){
			throw new TransportException(TransportException.TYPE.ITSH036);
		} else if (vo.getDockTime() == null){
			throw new TransportException(TransportException.TYPE.ITSH037);
		}
	}

	/**
	 * 查询船合同详情
	 */
	@Override
	public ShipPact findShipPactDetail(ShipPactVO vo) {
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		ShipPact pact = this.findByUuid(vo.getUuid());
		if (pact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		
		//根据语言环境设置 shipOwner、carrierName、brokerName
		if ("en".equals(lang)){
			//shipOwner
			if(!StringUtils.isNullOrEmpty(pact.getShipOwnerEn())){
				pact.setShipOwner(pact.getShipOwnerEn());
			}else if(pact.getShipOwnerId()!=null){
				pact.setShipOwner(commonService.findNameByEpMemberId(pact.getShipOwnerId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(pact.getCarrierNameEn())){
				pact.setCarrierName(pact.getCarrierNameEn());
			}else if(pact.getCarrierId()!=null){
				pact.setCarrierName(commonService.findNameByEpMemberId(pact.getCarrierId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(pact.getBrokerNameEn())){
				pact.setBrokerName(pact.getBrokerNameEn());
			}else if(pact.getBrokerId()!=null){
				pact.setBrokerName(commonService.findNameByEpMemberId(pact.getBrokerId()));
			}
		}
		
		return pact;
	}

	/**
	 * 查询船合同列表
	 */
	@Override
	public Page<Map<String,Object>> queryShipPactList(Map<String, Object> map, SimplePageInfo pageInfo) {
		Page<Map<String,Object>> page = this.queryRecordsByPer(map, pageInfo);
		NumberFormat nf = NumberFormat.getInstance();  
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		for (Map<String,Object> maps : page) {
			
			/**
			 * 根据语言环境设置 shipOwner、carrierName、brokerName
			 */
			if ("en".equals(lang)){
				//shipOwner
				Object obj1=maps.get("shipOwnerEn");
				Object obj2=maps.get("shipOwnerId");
				if(obj1!=null){
					maps.put("shipOwner", (String)obj1);
				}else if(obj2!=null){
					maps.put("shipOwner", commonService.findNameByEpMemberId((Long)obj2));
				}
				//carrierName
				Object obj3=maps.get("carrierNameEn");
				Object obj4=maps.get("carrierId");
				if(obj3!=null){
					maps.put("carrierName", (String)obj3);
				}else if(obj4!=null){
					maps.put("carrierName", commonService.findNameByEpMemberId((Long)obj4));
				}
				//brokerName
				Object obj5=maps.get("brokerNameEn");
				Object obj6=maps.get("brokerId");
				if(obj5!=null){
					maps.put("brokerName", (String)obj5);
				}else if(obj6!=null){
					maps.put("brokerName", commonService.findNameByEpMemberId((Long)obj6));
				}
			}
			
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormaters = new SimpleDateFormat("yyyy-MM-dd");
			if (maps.get("minQuantity") != null){
				BigDecimal min = (BigDecimal)maps.get("minQuantity");
				maps.put("minQuantity", nf.format(min));
			}
			maps.put("signDates", dateFormater.format(maps.get("signDate")));
			if (maps.get("pactBeg") != null && maps.get("pactEnd") != null){
				maps.put("pactBeg", dateFormaters.format(maps.get("pactBeg"))+"--"+dateFormaters.format(maps.get("pactEnd")));
			} else {
				maps.put("pactBeg", "");
			}
			//查询运单信息
			long shipPactId = (long) maps.get("shipPactId");
			Waybill waybill = new Waybill();
			waybill.setShipPactId(shipPactId);
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			List<Waybill> list2 = new ArrayList<>();
			for (Waybill waybill2 : list) {
				
				/**
				 * 根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
				 */
				if ("en".equals(lang)){
					//epMemberName
					if(!StringUtils.isNullOrEmpty(waybill2.getEpMemberNameEn())){
						waybill2.setEpMemberName(waybill2.getEpMemberNameEn());
					}else if(waybill2.getEpMemberId()!=null){
						waybill2.setEpMemberName(commonService.findNameByEpMemberId(waybill2.getEpMemberId()));
					}
					//shipOwner
					if(!StringUtils.isNullOrEmpty(waybill2.getShipOwnerEn())){
						waybill2.setShipOwner(waybill2.getShipOwnerEn());
					}else if(waybill2.getShipOwnerId()!=null){
						waybill2.setShipOwner(commonService.findNameByEpMemberId(waybill2.getShipOwnerId()));
					}
					//carrierName
					if(!StringUtils.isNullOrEmpty(waybill2.getCarrierNameEn())){
						waybill2.setCarrierName(waybill2.getCarrierNameEn());
					}else if(waybill2.getCarrierId()!=null){
						waybill2.setCarrierName(commonService.findNameByEpMemberId(waybill2.getCarrierId()));
					}
					//brokerName
					if(!StringUtils.isNullOrEmpty(waybill2.getBrokerNameEn())){
						waybill2.setBrokerName(waybill2.getBrokerNameEn());
					}else if(waybill2.getBrokerId()!=null){
						waybill2.setBrokerName(commonService.findNameByEpMemberId(waybill2.getBrokerId()));
					}
				}
				
				//获取运单油种
				String oiiType="";
				String oilTypeCode = waybill2.getOilTypeCode();
				if(!StringUtils.isNullOrEmpty(oilTypeCode)){
					String[] oilCodeArr = oilTypeCode.split("/");
					for (String oilCode : oilCodeArr) {
						if(!StringUtils.isNullOrEmpty(oilCode)){
							oiiType+=commonService.findNameByCodeAndLang("3", oilCode)+"/";
						}
					}
				}else{
					oiiType=waybill2.getOiiType();
				}
				//获取装港
				String loadPort="";
				String loadCodes = waybill2.getLoadPortCode();
				if(!StringUtils.isNullOrEmpty(loadCodes)){
					String[] codeArr = loadCodes.split("/");
					for (String loadCodeData : codeArr) {
						if(!StringUtils.isNullOrEmpty(loadCodeData)){
							loadPort+=commonService.findNameByCodeAndLang("1", loadCodeData)+"/";
						}
					}
				}else{
					loadPort=waybill2.getLoadPort();
				}
				//获取卸港
				String unloadPort="";
				String unloadCodes = waybill2.getUnloadPortCode();
				if(!StringUtils.isNullOrEmpty(unloadCodes)){
					String[] unCodeArr = unloadCodes.split("/");
					for (String loadCodeData : unCodeArr) {
						if(!StringUtils.isNullOrEmpty(loadCodeData)){
							unloadPort+=commonService.findNameByCodeAndLang("2", loadCodeData)+"/";
						}
					}
				}else{
					unloadPort=waybill2.getUnloadPort();
				}
				//数量处理
				String quantity = "";
				if ("en".equals(lang)){
					quantity=waybill2.getQuantityEn();
				}else{
					quantity=waybill2.getQuantity();
				}
				
				Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
				if (agreement != null){
					waybill2.setExt1(agreement.getShipName());
				}
				if (!StringUtils.isNullOrEmpty(oiiType)){
					waybill2.setOiiType(oiiType.substring(0,oiiType.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(loadPort)){
					waybill2.setLoadPort(loadPort.substring(0,loadPort.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(unloadPort)){
					waybill2.setUnloadPort(unloadPort.substring(0,unloadPort.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(quantity)){
					waybill2.setQuantity(quantity.substring(0,quantity.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(waybill2.getLaycan())){
					waybill2.setLaycan(waybill2.getLaycan().replaceAll("/", "--"));
				}
				list2.add(waybill2);
			}
			maps.put("list", list2);
		}
		return page;
	}
	/**
	 * 查询船合同列表
	 */
	@Override
	public Page<Map<String, Object>> queryShipPactListAgency(Map<String, Object> map, SimplePageInfo pageInfo) {
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String,Object>> page =(Page<Map<String, Object>>) _ShipPactMapper.queryRecordsFn(map);
		
		for (Map<String, Object> map2 : page) {
			
			/**
			 * 根据语言环境设置 shipOwner、carrierName、brokerName
			 */
			if ("en".equals(lang)){
				//shipOwner
				Object obj1=map2.get("shipOwnerEn");
				Object obj2=map2.get("shipOwnerId");
				if(obj1!=null){
					map2.put("shipOwner", (String)obj1);
				}else if(obj2!=null){
					map2.put("shipOwner", commonService.findNameByEpMemberId((Long)obj2));
				}
				//carrierName
				Object obj3=map2.get("carrierNameEn");
				Object obj4=map2.get("carrierId");
				if(obj3!=null){
					map2.put("carrierName", (String)obj3);
				}else if(obj4!=null){
					map2.put("carrierName", commonService.findNameByEpMemberId((Long)obj4));
				}
				//brokerName
				Object obj5=map2.get("brokerNameEn");
				Object obj6=map2.get("brokerId");
				if(obj5!=null){
					map2.put("brokerName", (String)obj5);
				}else if(obj6!=null){
					map2.put("brokerName", commonService.findNameByEpMemberId((Long)obj6));
				}
			}
			
			SimpleDateFormat dateFormaters = new SimpleDateFormat("yyyy-MM-dd");
			map2.put("pactBeg", dateFormaters.format(map2.get("pactBeg"))+"--"+dateFormaters.format(map2.get("pactEnd")));
		}
		return page;
	}

	

	/**
	 * 查询船合同和协议信息
	 */
	@Override
	public Map<String, Object> queryAgreementAndPact(ShipPactVO vo) {
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		Map<String, Object> map = new HashMap<>();
		ShipPact shipPact = this.findByUuid(vo.getUuid());
		Agreement agreement = agreementService.findByUuid(vo.getAgreementUuid());
		
		if ("en".equals(lang)){
			/**
			 * 船合同：根据语言环境设置船合同 shipOwner、carrierName、brokerName
			 */
			//shipOwner
			if(!StringUtils.isNullOrEmpty(shipPact.getShipOwnerEn())){
				shipPact.setShipOwner(shipPact.getShipOwnerEn());
			}else if(shipPact.getShipOwnerId()!=null){
				shipPact.setShipOwner(commonService.findNameByEpMemberId(shipPact.getShipOwnerId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(shipPact.getCarrierNameEn())){
				shipPact.setCarrierName(shipPact.getCarrierNameEn());
			}else if(shipPact.getCarrierId()!=null){
				shipPact.setCarrierName(commonService.findNameByEpMemberId(shipPact.getCarrierId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(shipPact.getBrokerNameEn())){
				shipPact.setBrokerName(shipPact.getBrokerNameEn());
			}else if(shipPact.getBrokerId()!=null){
				shipPact.setBrokerName(commonService.findNameByEpMemberId(shipPact.getBrokerId()));
			}
			
			/**
			 * 协议：根据语言环境设置协议 epMemberName、shipOwner、carrierName、brokerName
			 */
			//epMemberName
			if(!StringUtils.isNullOrEmpty(agreement.getEpMemberNameEn())){
				agreement.setEpMemberName(agreement.getEpMemberNameEn());
			}else if(agreement.getEpMemberId()!=null){
				agreement.setEpMemberName(commonService.findNameByEpMemberId(agreement.getEpMemberId()));
			}
			//shipOwner
			if(!StringUtils.isNullOrEmpty(agreement.getShipOwnerEn())){
				agreement.setShipOwner(agreement.getShipOwnerEn());
			}else if(agreement.getShipOwnerId()!=null){
				agreement.setShipOwner(commonService.findNameByEpMemberId(agreement.getShipOwnerId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(agreement.getCarrierNameEn())){
				agreement.setCarrierName(agreement.getCarrierNameEn());
			}else if(agreement.getCarrierId()!=null){
				agreement.setCarrierName(commonService.findNameByEpMemberId(agreement.getCarrierId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(agreement.getBrokerNameEn())){
				agreement.setBrokerName(agreement.getBrokerNameEn());
			}else if(agreement.getBrokerId()!=null){
				agreement.setBrokerName(commonService.findNameByEpMemberId(agreement.getBrokerId()));
			}
			
			/**
			 * 数量
			 */
			agreement.setOilType(agreement.getOilTypeEn());
		} 
		
		/**
		 * 根据语言环境，设置协议装港、卸港、油种
		 */
		//装港
		String loadPort="";
		String loadPortCodes = agreement.getLoadPortCode();
		if(!StringUtils.isNullOrEmpty(loadPortCodes)){
			String[] loadCodeArr = loadPortCodes.split("/");
			for (String loadCode : loadCodeArr) {
				if(!StringUtils.isNullOrEmpty(loadCode)){
					loadPort+=commonService.findNameByCodeAndLang("1", loadCode)+"/";
				}
			}
			agreement.setLoadPort(loadPort.substring(0, loadPort.length()-1));
		}
		//卸港
		String unloadPort="";
		String unloadPortCodes = agreement.getUnloadPortCode();
		if(!StringUtils.isNullOrEmpty(unloadPortCodes)){
			String[] unloadCodeArr = unloadPortCodes.split("/");
			for (String unloadCode : unloadCodeArr) {
				if(!StringUtils.isNullOrEmpty(unloadCode)){
					unloadPort+=commonService.findNameByCodeAndLang("2", unloadCode);
				}
			}
			agreement.setUnloadPort(unloadPort.substring(0, unloadPort.length()-1));
		}
		//油种
		String oilType="";
		String oilCodes = agreement.getOilTypeCode();
		if(!StringUtils.isNullOrEmpty(oilCodes)){
			String[] oilCodeArr = oilCodes.split("/");
			for (String oilCode : oilCodeArr) {
				if(!StringUtils.isNullOrEmpty(oilCode)){
					oilType+=commonService.findNameByCodeAndLang("3", oilCode);
				}
			}
			agreement.setOilType(oilType.substring(0, oilType.length()-1));
		}
		
		map.put("shipPact", shipPact);
		map.put("agreemnet", agreement);
		return map;
	}

	/**
	 * 航次结束
	 */
	@Override
	@Transactional
	public void finishShipPact(String uuid, CurrentUser user) {

		ShipPact shipPact = this.findByUuid(uuid);
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		//修改船合同状态
		shipPact.setStatus(Constants.SHIP_PACT_STATUS_7);
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		this.updateRecord(shipPact);
		//修改运单状态
		Waybill waybill = new Waybill();
		waybill.setShipPactId(shipPact.getShipPactId());
		List<Waybill> list = waybillService.queryByEntitys(waybill);
		if (list != null && !list.isEmpty()){
			for (Waybill bill : list) {
				
				//修改代理协议状态
				Agreement agreement = agreementService.findByPrimaryKey(bill.getAgreementId());
				agreement.setUpdateDate(DateTimeUtils.currentDate());
				agreement.setUpdateUser(user.getMemberId());
				agreement.setStatus(Constants.AGREEMENT_STATUS_7);
				agreementService.updateRecord(agreement);
				
				//修改运单状态
				bill.setStatus(Constants.WAYBILL_STATUS_4);
				bill.setUpdateDate(DateTimeUtils.currentDate());
				bill.setUpdateUser(user.getMemberId());
				waybillService.updateRecord(waybill);
				
				//修改货盘状态
				Pallet pallet = palletService.findByPrimaryKey(bill.getPalletId());
				if (pallet == null){
					throw new TransportException(TransportException.TYPE.ITSH017);
				}
				pallet.setStatus(Constants.PALLET_STATUS_6);
				pallet.setUpdateDate(DateTimeUtils.currentDate());
				pallet.setUpdateUser(user.getMemberId());
				palletService.updateRecord(pallet);
				
				// 更新航次结束次数
				sumCountService.updatefinishShipPactCount();
			}
		}
		//修改船盘状态
		/*ShipPlate shipPlate = shipPlateService.findByPrimaryKey(shipPact.getShipPlateId());
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		shipPlate.setStatus(Constants.SHIP_PLATE_STATUS_3);
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		shipPlateService.updateRecord(shipPlate);*/
		//发送消息
		messagePushService.messagePush(5, shipPact.getShipPactId(), user.getMemberId());
	}

	/**
	 * 校验航次结束
	 */
	@Override
	public Map<String,Object> checkShipPactFinish(String uuid) {
		
		Map<String,Object> map = new HashMap<>();
		
		ShipPact shipPact = this.findByUuid(uuid);
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		//校验船合同状态
		if (Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_7.equals(shipPact.getStatus()) ||
			Constants.SHIP_PACT_STATUS_8.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		//校验有订单租船是否填写装卸港信息
		//this.checkOrder(shipPact);
		
		//String status = shipPact.getDetailStatus();
		String mess = "";
		boolean flag = true;
		/*switch (status) {
			case "2":
				mess="您船合同的装港信息还没有维护，是否确认航次结束？";
				flag=false;
				break;
			case "3":
				mess="您船合同的在途信息还没有维护，是否确认航次结束？";
				flag=false;
				break;
			case "4":
				mess="您船合同的卸港信息还没有维护，是否确认航次结束？";
				flag=false;
				break;
			default:
				break;
		}*/
		map.put("flag", flag);
		map.put("mess", mess);
		return map;
	}

	/**
	 * 校验有订单租船装卸港信息
	 * @param shipPact
	 */
/*	private void checkOrder(ShipPact shipPact) {
		
		//查询有订单租船协议
		Waybill waybill = new Waybill();
		waybill.setShipPactId(shipPact.getShipPactId());
		List<Waybill> list = waybillService.queryByEntitys(waybill );
		if (!list.isEmpty()){
			for (Waybill waybill2 : list) {
				Agreement agreement = agreementService.findByPrimaryKey(waybill2.getAgreementId());
				if (agreement == null){
					throw new TransportException(TransportException.TYPE.ITSH027);
				}
				//有订单租船需要校验
				if (Constants.ORDER_NO_1.equals(agreement.getOrderNo()) && !StringUtils.isNullOrEmpty(agreement.getOrderCode())){
					//查询装卸港信息
					Shipment shipment =new Shipment();
					shipment.setAgreementUuid(agreement.getUuid());
					List<Shipment> list2 = shipmentService.queryByEntitys(shipment);
					if (list2.isEmpty()){
						throw new TransportException(TransportException.TYPE.ITSH530);
					}
					Disburden disburden =new Disburden();
					disburden.setAgreementUuid(agreement.getUuid());
					List<Disburden> list3 = disburdenService.queryByEntitys(disburden);
					if (list3.isEmpty()){
						throw new TransportException(TransportException.TYPE.ITSH531);
					}
				}
			}
		}
	}*/

	/**
	 * 修改船合同
	 */
	@Override
	public void updateShipPact(ShipPactVO vo, CurrentUser user) {
		
		//校验必填
		this.checkShipPactRequired(vo);
		
		ShipPact shipPact = this.findByUuid(vo.getUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (!Constants.SHIP_PACT_STATUS_1.equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		
		//获取船信息
		SysShip ship = sysShipService.findByUuid(vo.getSysShipUuid());
		if (ship != null){
			vo.setMmsi(ship.getMmsi());
		}
		SimpleDateFormat format =  new SimpleDateFormat("yyMMdd");
		vo.setPactCode(vo.getShipName()+format.format(vo.getSignDate()));
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		vo.setShipPactId(shipPact.getShipPactId());
		
		
		/**
		 * 设置 epMemberName、经纪人、租船人、船东中英文
		 */
		//epMemberName
		EnterpriseVo emp = commonService.queryUserByEpMemberId(user.getEpMemberId());
		vo.setEpMemberNameEn(emp.getEnglishName());
		//经纪人
		if(vo.getBrokerId()!=null){
			EnterpriseVo broker = commonService.queryUserByEpMemberId(vo.getBrokerId());
			vo.setBrokerName(broker.getFullName());
			vo.setBrokerNameEn(broker.getEnglishName());
		}
		//船东
		if(vo.getShipOwnerId()!=null){
			EnterpriseVo own = commonService.queryUserByEpMemberId(vo.getShipOwnerId());
			vo.setShipOwner(own.getFullName());
			vo.setShipOwnerEn(own.getEnglishName());
		}
		//租船人
		if(vo.getCarrierId()!=null){
			EnterpriseVo car = commonService.queryUserByEpMemberId(vo.getCarrierId());
			vo.setCarrierName(car.getFullName());
			vo.setCarrierNameEn(car.getEnglishName());
		}else{
			//自定义，不处理
			vo.setCarrierName(null);
		}
		
		this.updateRecord(vo);
		
		
		try {
		//获取运单
		Waybill waybill=new Waybill();
		waybill.setShipPactUuid(vo.getUuid());
		List<Waybill> wbList = waybillService.queryByEntitys(waybill);
		if(wbList!=null && !wbList.isEmpty()){
			for (Waybill wb : wbList) {
				//同步执行已租船
				if(wb.getOrderNo()!=null && "1".equals(wb.getOrderNo())){		/**有订单租船*/
					FindedShipInfoVO findedShipInfoVO=new FindedShipInfoVO();
					findedShipInfoVO.setArgeementUuid(wb.getAgreementUuid());
					findedShipInfoVO.setName(ship.getName());
					findedShipInfoVO.setOrderNo(wb.getOrderCode());
					findedShipInfoVO.setType(ship.getType());
					iShipTransportService.syncShipInfo(findedShipInfoVO, user.getMemberId());
				}
			}
		 }
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransportException(TransportException.TYPE.ITSH561);
		}
		
		
	}

	/**
	 * 查询值集
	 */
	@Override
	public List<Map<String, Object>> queryValueSet(ShipPactVO vo) {
		List<Map<String, Object>> list= new ArrayList<>();
		Boolean flag = false;
		if (!StringUtils.isNullOrEmpty(vo.getValueSetTypes())){
			String list2 = vo.getValueSetTypes();
			String[] split = list2.split(";");
			for (String string : split) {
				String str = "";
				switch (string) {
				case "1":
					str=Constants.VALUE_SET_1;
					break;
				case "2":
					str=Constants.VALUE_SET_2;
					break;
				case "3":
					str=Constants.VALUE_SET_3;
					break;
				case "4":
					str=Constants.VALUE_SET_4;
					break;
				case "5":
					str=Constants.VALUE_SET_5;
					break;
				case "6":
					str=Constants.VALUE_SET_6;
					break;
				case "7":
					str=Constants.VALUE_SET_7;
					break;
				case "8":
					str=Constants.VALUE_SET_8;
					break;
				case "9":
					str=Constants.VALUE_SET_9;
					break;
				case "10":
					str=Constants.VALUE_SET_10;
					break;
				case "11":
					str=Constants.VALUE_SET_11;
					break;
				case "12":
					str=Constants.VALUE_SET_12;
					break;
				case "13":
					str=Constants.VALUE_SET_13;
					break;
				case "14":
					str=Constants.VALUE_SET_14;
					break;
				case "15":
					str=Constants.VALUE_SET_15;
					break;
				case "16":
					str=Constants.VALUE_SET_16;
					break;
				case "17":
					str=Constants.VALUE_SET_17;
					break;
				case "100":
					flag =true;
					str=vo.getSubGroup();
					break;
				default:
					break;
				}
				//查询值集
				if (flag){
					Map<String, CodeValue> group = ValueSetUtils.getValuesByParentId(str);
					for (Map.Entry<String, CodeValue> e : group.entrySet()) {
						CodeValue value = e.getValue();
						Map<String,Object> map = new LinkedHashMap<>();
						map.put("code", value.getCode());
						map.put("subGroup", value.getValue());
						map.put("value", value.getValue());
						map.put("type", string);
						list.add(map);
					}
				} else {
					Map<String, CodeValue> group;
					if (str != Constants.VALUE_SET_10 && str != Constants.VALUE_SET_5 && str != Constants.VALUE_SET_4 && str != Constants.VALUE_SET_9 && str != Constants.VALUE_SET_12 && str != Constants.VALUE_SET_17){
						group = ValueSetUtils.getValuesByGroup(str,"zh");
					} else {
						group = ValueSetUtils.getValuesByGroup(str);
					}
					for (Map.Entry<String, CodeValue> e : group.entrySet()) {
						CodeValue value = e.getValue();
						Map<String,Object> map = new LinkedHashMap<>();
						map.put("code", value.getCode());
						map.put("subGroup", value.getValue());
						map.put("value", value.getValue());
						map.put("type", string);
						list.add(map);
					}
				}
			}
		} else {
			String str = "";
			switch (vo.getValueSetType()) {
			case "1":
				str=Constants.VALUE_SET_1;
				break;
			case "2":
				str=Constants.VALUE_SET_2;
				break;
			case "3":
				str=Constants.VALUE_SET_3;
				break;
			case "4":
				str=Constants.VALUE_SET_4;
				break;
			case "5":
				str=Constants.VALUE_SET_5;
				break;
			case "6":
				str=Constants.VALUE_SET_6;
				break;
			case "7":
				str=Constants.VALUE_SET_7;
				break;
			case "8":
				str=Constants.VALUE_SET_8;
				break;
			case "9":
				str=Constants.VALUE_SET_9;
				break;
			case "10":
				str=Constants.VALUE_SET_10;
				break;
			case "11":
				str=Constants.VALUE_SET_11;
				break;
			case "12":
				str=Constants.VALUE_SET_12;
				break;
			case "13":
				str=Constants.VALUE_SET_13;
				break;
			case "14":
				str=Constants.VALUE_SET_14;
				break;
			case "15":
				str=Constants.VALUE_SET_15;
				break;
			case "16":
				str=Constants.VALUE_SET_16;
				break;
			case "17":
				str=Constants.VALUE_SET_17;
				break;
			case "100":
				flag =true;
				str=vo.getSubGroup();
				break;
			default:
				break;
			}
			//查询值集
			if (flag){
				Map<String, CodeValue> group = ValueSetUtils.getValuesByParentId(str);
				for (Map.Entry<String, CodeValue> e : group.entrySet()) {
					CodeValue value = e.getValue();
					Map<String,Object> map = new LinkedHashMap<>();
					map.put("code", value.getCode());
					map.put("subGroup", value.getValue());
					map.put("value", value.getValue());
					map.put("type", vo.getValueSetType());
					list.add(map);
				}
			} else {
				Map<String, CodeValue> group;
				if (str != Constants.VALUE_SET_10 && str != Constants.VALUE_SET_5 && str != Constants.VALUE_SET_4 && str != Constants.VALUE_SET_9 && str != Constants.VALUE_SET_12 && str != Constants.VALUE_SET_17){
					group = ValueSetUtils.getValuesByGroup(str,"zh");
				} else {
					group = ValueSetUtils.getValuesByGroup(str);
				}
				for (Map.Entry<String, CodeValue> e : group.entrySet()) {
					CodeValue value = e.getValue();
					Map<String,Object> map = new LinkedHashMap<>();
					map.put("code", value.getCode());
					map.put("subGroup", value.getValue());
					map.put("value", value.getValue());
					map.put("type", vo.getValueSetType());
					list.add(map);
				}
			}
		}
		
		return list;
	}

	/**
	 * 查询物流跟踪
	 */
	@Override
	public Map<String, Object> queryTrack(ShipPactVO vo, CurrentUser user) {
		Map<String,Object> map = new HashMap<>();
		List<String> idList = new ArrayList<>();
		ShipPact shipPact = new ShipPact();
		String agreementCode ="";
		if (!StringUtils.isNullOrEmpty(vo.getUuid())){
			//船代查询物流信息
			ShipPact shipPacts = this.findByUuid(vo.getUuid());
			if (shipPacts == null){
				throw new TransportException(TransportException.TYPE.ITSH039);
			}
			/*if (user != null && shipPacts.getEpMemberId() != user.getEpMemberId()){
				throw new TransportException(TransportException.TYPE.ITSH549);
			}*/
			BeanUtils.copyProperties(shipPacts, shipPact);
			Waybill waybill = new Waybill();
			waybill.setShipPactId(shipPact.getShipPactId());
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			if (list != null && !list.isEmpty()){
				for (Waybill way : list) {
					agreementCode=agreementCode+way.getAgreementCode()+";";
					idList.add(way.getAgreementUuid());
				}
			}
		} else if (!StringUtils.isNullOrEmpty(vo.getAgreementUuid())){
			idList.add(vo.getAgreementUuid());
			//查询协议信息
			Waybill waybill = new Waybill();
			waybill.setAgreementUuid(vo.getAgreementUuid());
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			if (!list.isEmpty()){
				Waybill way = list.get(0);
				agreementCode=way.getAgreementCode()+";";
				Long shipPactId = way.getShipPactId();
				ShipPact pact = this.findByPrimaryKey(shipPactId);
				if (pact == null ){
					throw new TransportException(TransportException.TYPE.ITSH039);
				}
				BeanUtils.copyProperties(pact, shipPact);
			}
		}
		//船信息
		String code ="";
		Waybill waybill = new Waybill();
		waybill.setShipPactUuid(shipPact.getUuid());
		List<Waybill> list4 = waybillService.queryByEntitys(waybill);
		if (!list4.isEmpty()){
			for (Waybill waybill2 : list4) {
				Pallet pallet = new Pallet();
				pallet.setUuid(waybill2.getPalletUuid());
				List<Pallet> list = palletService.queryByEntitys(pallet);
				if (!list.isEmpty()){
					String orderPact = list.get(0).getOrderPact();
					if (!StringUtils.isNullOrEmpty(orderPact)){
						code=code+orderPact+";";
					}
				}
			}
		}
		if (!StringUtils.isNullOrEmpty(code)){
			code=code.substring(0,code.length()-1);
		}
		if (!StringUtils.isNullOrEmpty(agreementCode)){
			agreementCode=agreementCode.substring(0,agreementCode.length()-1);
		}
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		ShipPactRes res = new ShipPactRes();
		String status = shipPact.getStatus();
		String sts = "1";
		switch (status) {
		case "3":
			sts = "2";
			break;
		case "4":
			sts = "3";
			break;
		case "5":
			sts = "4";
			break;
		case "6":
			sts = "5";
			break;
		case "7":
			sts = "6";
			break;
		case "8":
			sts = "6";
			break;
		default:
			break;
		}
		res.setDetailStatus(sts);
		res.setShipPactCode(shipPact.getPactCode());
		res.setPactCode(code);
		res.setCarrierName(shipPact.getCarrierName());
		res.setLoadPort(shipPact.getLoadPort());
		res.setShipName(shipPact.getShipName());
		res.setShipOwner(shipPact.getShipOwner());
		if (shipPact.getSignDate() != null){
			res.setSignDate(format.format(shipPact.getSignDate()));
		}
		res.setAgreementCode(agreementCode);
		res.setMmsi(shipPact.getMmsi());
		res.setLaycan(format.format(shipPact.getPactBeg())+"--"+format.format(shipPact.getPactEnd()));
		res.setUuid(shipPact.getUuid());
		SysShip ship = new SysShip();
		ship.setName(shipPact.getShipName());
		ship.setMmsi(shipPact.getMmsi());
		List<SysShip> list5 = sysShipService.queryByEntitys(ship);
		if (list5 != null && !list5.isEmpty()){
			SysShip sysShip = list5.get(0);
			if (sysShip != null){
				res.setImo(sysShip.getImo());
			}
		}
		//航线日期
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
		String type = "2";
		//查询第一个装港eta时间
		LoadPort loadPorts = new LoadPort();
		loadPorts.setShipPactId(shipPact.getShipPactId());
		loadPorts.setShipPactUuid(shipPact.getUuid());
		List<LoadPort> list6 = loadPortService.queryByEntitys(loadPorts);
		if (list6 != null && !list6.isEmpty()){
			Date date = list6.get(0).getEta();
			if (date != null){
				type ="1";
				res.setTimeBeg(format3.format(date));
				//查询最后卸港的实际离港时间
				UnloadPort unloadPort = new UnloadPort();
				unloadPort.setShipPactId(shipPact.getShipPactId());
				unloadPort.setShipPactUuid(shipPact.getUuid());
				List<UnloadPort> list = unloadPortService.queryByEntitys(unloadPort);
				Date date2 = DateTimeUtils.currentDate();
				
				if (list != null && !list.isEmpty()){
					UnloadPort port = list.get(list.size()-1);
					Date acLeave = port.getAcLeave();
					if (acLeave != null){
						date2 = acLeave;
					}
				}
				res.setTimeEnd(format3.format(date2));
			}
		}
		res.setType(type);
		//物流信息
		List<Map<String,Object>> trackList = this.queryTrackByIds(idList,shipPact.getShipPactId());
		
		//实时概况
		ShipRealTime time = new ShipRealTime();
		time.setShipName(shipPact.getShipName());
		LoadPort loadPort = new LoadPort();
		loadPort.setShipPactId(shipPact.getShipPactId());
		List<LoadPort> list2 = loadPortService.queryByEntitys(loadPort);
		if (!list2.isEmpty()){
			LoadPort port = list2.get(0);
			time.setLoadPort(port.getLoadPort());
			if (port.getEta() != null){
				time.setLoadPortETA(format.format(port.getEta()));
			}
			if (port.getAcLeave() != null){
				time.setLoadPortLEAVEDATE(format.format(port.getAcLeave()));
			}
		}
		UnloadPort unloadPort = new UnloadPort();
		unloadPort.setShipPactId(shipPact.getShipPactId());
		List<UnloadPort> list3 = unloadPortService.queryByEntitys(unloadPort);
		if (!list3.isEmpty()){
			UnloadPort port = list3.get(0);
			time.setUnloadPort(port.getUnloadPort());
			if (port.getEta() != null){
				time.setUnloadPortETA(format.format(port.getEta()));
			}
			if (port.getAcLeave() != null){
				time.setUnloadPortLEAVEDATE(format.format(port.getAcLeave()));
			}
			if (port.getAcFinish() != null){
				time.setAcFinish(format.format(port.getAcFinish()));
			}
		}
		
		//在途信息
		Transit transit = new Transit();
		transit.setShipPactId(shipPact.getShipPactId());
		List<Transit> list = transitService.queryByEntitys(transit);
		List<Map<String,Object>> transitList = new ArrayList<>();
		
		List<LoadTransit> imgList = new ArrayList<>();
		List<LoadTransit> mp4List = new ArrayList<>();
		
		 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" ); 
		for (int i = 0; i < list.size(); i++) {
				Transit lists = list.get(i);
				Date dateNow = lists.getDateNow();
				String timeNow = lists.getTimeNow();
				String format2 = format.format(dateNow);
				String date = format2+" "+timeNow;
				Date parse = null;
				try {
					 parse = sdf.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//查询附件信息
				if (!StringUtils.isNullOrEmpty(lists.getAccessoryPath()) && !StringUtils.isNullOrEmpty(lists.getAccessoryPath())) {
					LoadTransit  loads = new LoadTransit();
					loads.setName(lists.getAccessory());
					loads.setPath(lists.getAccessoryPath());
					loads.setTime(parse);
					loads.setTimes(date);
					loads.setType("3");
					mp4List.add(loads);
				}
				//查询图片信息
				Accessory accessory = new Accessory();
				accessory.setType("4");
				accessory.setId(lists.getTransitId());
				List<Accessory> entitys = accessoryService.queryByEntitys(accessory);
				for (Accessory acc : entitys) {
					String path = acc.getPath();
					if (!StringUtils.isNullOrEmpty(path) && parse != null){
						LoadTransit  load = new LoadTransit();
						int index = path.lastIndexOf(".");
						int length = path.length();
						String str = path.substring(index+1, length);
						if ("jpg".equals(str) || "jpeg".equals(str) || "bmp".equals(str)|| "png".equals(str)){
							load.setType("1");
						} else {
							load.setType("2");
						}
						load.setTimes(date);
						load.setTime(parse);
						load.setPath(path);
						imgList.add(load);
					}
				}
				
				
				String unloadEta = lists.getUnloadEtaCode();
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
										String name = commonService.findNameByCodeAndLang("2", unload);
										unload=name+split3[0]+"/";
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
				Map<String, Object> map2 = new HashMap<>();
			
				map2.put("dateNow", format2+" "+timeNow);
				map2.put("timeNow", timeNow);
				map2.put("unload", unload);
				map2.put("ETA", ETA);
				map2.put("position", lists.getPosition());
				map2.put("speedH", lists.getSpeedH());
				map2.put("speedAll", lists.getSpeedAll());
				map2.put("rpm", lists.getRpm());
				map2.put("sea", lists.getSea());
				map2.put("water", lists.getWater());
				map2.put("sulfide", lists.getSulfide());
				map2.put("remark", lists.getRemark());
				
				transitList.add(map2);
			}
		Collections.sort(imgList);
		Collections.sort(mp4List);
		map.put("imgList", imgList);
		map.put("accessList", mp4List);
		map.put("shipPact", res);
		map.put("trackList", trackList);
		map.put("shipRealTime", time);
		map.put("transitList", transitList);
		return map;
	}
	/**
	 * 移动查询物流跟踪
	 */
	@Override
	public Map<String, Object> MobileQueryTrack(ShipPactVO vo, CurrentUser user) {
		
		Map<String,Object> map = new HashMap<>();
		List<String> idList = new ArrayList<>();
		ShipPact shipPact = new ShipPact();
		String agreementCode ="";
		if (!StringUtils.isNullOrEmpty(vo.getUuid())){
			//船代查询物流信息
			ShipPact shipPacts = this.findByUuid(vo.getUuid());
			if (shipPacts == null){
				throw new TransportException(TransportException.TYPE.ITSH039);
			}
			/*if (user != null && shipPacts.getEpMemberId() != user.getEpMemberId()){
				throw new TransportException(TransportException.TYPE.ITSH549);
			}*/
			BeanUtils.copyProperties(shipPacts, shipPact);
			Waybill waybill = new Waybill();
			waybill.setShipPactId(shipPact.getShipPactId());
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			if (list != null && !list.isEmpty()){
				for (Waybill way : list) {
					agreementCode=agreementCode+way.getAgreementCode()+";";
					idList.add(way.getAgreementUuid());
				}
			}
		} else if (!StringUtils.isNullOrEmpty(vo.getAgreementUuid())){
			idList.add(vo.getAgreementUuid());
			//查询协议信息
			Waybill waybill = new Waybill();
			waybill.setAgreementUuid(vo.getAgreementUuid());
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			if (!list.isEmpty()){
				Waybill way = list.get(0);
				agreementCode=way.getAgreementCode()+";";
				Long shipPactId = way.getShipPactId();
				ShipPact pact = this.findByPrimaryKey(shipPactId);
				if (pact == null ){
					throw new TransportException(TransportException.TYPE.ITSH039);
				}
				BeanUtils.copyProperties(pact, shipPact);
			}
		}
		//船信息
		String code ="";
		Waybill waybill = new Waybill();
		waybill.setShipPactUuid(shipPact.getUuid());
		List<Waybill> list4 = waybillService.queryByEntitys(waybill);
		if (!list4.isEmpty()){
			for (Waybill waybill2 : list4) {
				Pallet pallet = new Pallet();
				pallet.setUuid(waybill2.getPalletUuid());
				List<Pallet> list = palletService.queryByEntitys(pallet);
				if (!list.isEmpty()){
					String orderPact = list.get(0).getOrderPact();
					if (!StringUtils.isNullOrEmpty(orderPact)){
						code=code+orderPact+";";
					}
				}
			}
		}
		if (!StringUtils.isNullOrEmpty(code)){
			code=code.substring(0,code.length()-1);
		}
		if (!StringUtils.isNullOrEmpty(agreementCode)){
			agreementCode=agreementCode.substring(0,agreementCode.length()-1);
		}
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		ShipPactRes res = new ShipPactRes();
		res.setDetailStatus(shipPact.getDetailStatus());
		res.setShipPactCode(shipPact.getPactCode());
		res.setPactCode(code);
		res.setCarrierName(shipPact.getCarrierName());
		res.setLoadPort(shipPact.getLoadPort());
		res.setShipName(shipPact.getShipName());
		res.setShipOwner(shipPact.getShipOwner());
		res.setSignDate(format.format(shipPact.getSignDate()));
		res.setAgreementCode(agreementCode);
		res.setMmsi(shipPact.getMmsi());
		res.setLaycan(format.format(shipPact.getPactBeg())+"--"+format.format(shipPact.getPactEnd()));
		
		//物流信息
		List<Map<String,Object>> trackList = this.MobileQueryTrackByIds(idList,shipPact.getShipPactId());
		
		//实时概况
		ShipRealTime time = new ShipRealTime();
		time.setShipName(shipPact.getShipName());
		LoadPort loadPort = new LoadPort();
		loadPort.setShipPactId(shipPact.getShipPactId());
		List<LoadPort> list2 = loadPortService.queryByEntitys(loadPort);
		if (!list2.isEmpty()){
			LoadPort port = list2.get(0);
			time.setLoadPort(port.getLoadPort());
			if (port.getEta() != null){
				time.setLoadPortETA(format.format(port.getEta()));
			}
			if (port.getAcLeave() != null){
				time.setLoadPortLEAVEDATE(format.format(port.getAcLeave()));
			}
		}
		UnloadPort unloadPort = new UnloadPort();
		unloadPort.setShipPactId(shipPact.getShipPactId());
		List<UnloadPort> list3 = unloadPortService.queryByEntitys(unloadPort);
		if (!list3.isEmpty()){
			UnloadPort port = list3.get(0);
			time.setUnloadPort(port.getUnloadPort());
			if (port.getEta() != null){
				time.setUnloadPortETA(format.format(port.getEta()));
			}
			if (port.getAcLeave() != null){
				time.setUnloadPortLEAVEDATE(format.format(port.getAcLeave()));
			}
			if (port.getAcFinish() != null){
				time.setAcFinish(format.format(port.getAcFinish()));
			}
		}
		//在途信息
		Transit transit = new Transit();
		transit.setShipPactId(shipPact.getShipPactId());
		List<Transit> list = transitService.queryByEntitys(transit);
		List<Map<String,Object>> transitList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			if (i < 5){
				Transit lists = list.get(i);
				String unloadEta = lists.getUnloadEta();
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
				Map<String, Object> map2 = new HashMap<>();
				Date dateNow = lists.getDateNow();
				String timeNow = lists.getTimeNow();
				String format2 = format.format(dateNow);
				map2.put("dateNow", format2);
				map2.put("timeNow", timeNow);
				map2.put("unload", unload);
				map2.put("ETA", ETA);
				map2.put("position", lists.getPosition());
				map2.put("speedH", lists.getSpeedH());
				map2.put("speedAll", lists.getSpeedAll());
				map2.put("rpm", lists.getRpm());
				map2.put("sea", lists.getSea());
				map2.put("water", lists.getWater());
				map2.put("sulfide", lists.getSulfide());
				map2.put("remark", lists.getRemark());
				transitList.add(map2);
			}
		}
		map.put("shipPact", res);
		map.put("trackList", trackList);
		map.put("shipRealTime", time);
		map.put("transitList", transitList);
		return map;
	}


	/**
	 * 查询物流跟踪信息
	 * @param idList
	 * @param shipPActId 
	 * @return
	 */
	private List<Map<String, Object>> MobileQueryTrackByIds(List<String> idList, Long shipPactId) {
		List<Map<String, Object>> VoyageStartList = new ArrayList<>();
		List<Map<String, Object>> list = new ArrayList<>();
		for (String uuid : idList) {
			Map<String, Object> map = new LinkedHashMap<>();
			Agreement agreement = agreementService.findByUuid(uuid);
			if (agreement == null){
				throw new TransportException(TransportException.TYPE.ITSH027);
			}
			//航次开始
			VoyageStart voyageStart = new VoyageStart();
			voyageStart.setShipPactId(shipPactId);
			List<VoyageStart> VoyageStartLists = voyageStartService.queryByEntitys(voyageStart);
			for (VoyageStart voyageStart1 : VoyageStartLists) {
				Map<String, Object> voyageStart2 = new HashMap<String, Object>();
				voyageStart2.put("oilType", voyageStart1.getOilType());
				voyageStart2.put("quantityCask", ShipPactServiceImpl.reserveTwo(voyageStart1.getQuantityCask()));
				voyageStart2.put("quantity", ShipPactServiceImpl.reserveTwo(voyageStart1.getQuantity()));
				voyageStart2.put("api", ShipPactServiceImpl.reserveTwo(voyageStart1.getApi()));
				voyageStart2.put("loadTemp",ShipPactServiceImpl.reserveTwo(voyageStart1.getLoadTemp()));
				voyageStart2.put("loadDraft", ShipPactServiceImpl.reserveTwo(voyageStart1.getLoadDraft()));
				voyageStart2.put("unloadDraft", ShipPactServiceImpl.reserveTwo(voyageStart1.getUnloadDraft()));
				VoyageStartList.add(voyageStart2);
			}
			//装港
			LoadPort loadPort = new LoadPort();
			loadPort.setAgreementUuid(uuid+";");
			List<LoadPort> list2 = loadPortService.queryByEntitys(loadPort);
			List<Map<String,Object>> listMap = new ArrayList<>();
			if (!list2.isEmpty()){
				for (LoadPort load2 : list2) {
					Map<String,Object> maps = new HashMap<>();
					Shipment shipment = new Shipment();
					shipment.setLoadPort(load2.getLoadPort());
					shipment.setOilType(load2.getOilType());
					shipment.setShipPactId(shipPactId);
					List<Shipment> list3 = shipmentService.queryByEntitys(shipment);
					Shipment shipment3 = new Shipment();
					if (!list3.isEmpty()){
						Shipment shipment2 = list3.get(0);
						BeanUtils.copyProperties(shipment2, shipment3);
					}
					maps.put("ship", load2);
					maps.put("goods", shipment3);
					listMap.add(maps);
				}
			}
			//卸港
			UnloadPort unloadPort = new UnloadPort();
			unloadPort.setAgreementUuid(uuid+";");
			List<UnloadPort> unlist2 = unloadPortService.queryByEntitys(unloadPort);
			List<Map<String,Object>> unlistMap = new ArrayList<>();
			List<Map<String,Object>> unloadAndETAs = new ArrayList<>();
			if (!unlist2.isEmpty()){
				for (UnloadPort load2 : unlist2) {
					Map<String,Object> unloadAndETA = new HashMap<String, Object>();
					unloadAndETA.put("unload", load2.getUnloadPort());
					unloadAndETA.put("ETA", load2.getEta());
					unloadAndETAs.add(unloadAndETA);
					Appoint appoint = new Appoint();
					appoint.setUnloadPortId(load2.getUnloadPortId());
					appoint.setAgreementUuid(uuid);
					List<Appoint> list4 = appointService.queryByEntitys(appoint);
					if (list4 != null && !list4.isEmpty()){
						Appoint appoint2 = list4.get(0);
						load2.setInspection(appoint2.getInspection());
						load2.setInspectionTel(appoint2.getInspectionTel());
						load2.setAgency(appoint2.getAgency());
						load2.setAgencyTel(appoint2.getAgencyTel());
						load2.setMonitor(appoint2.getMonitor());
						load2.setMonitorTel(appoint2.getMonitorTel());
					}
					Map<String,Object> maps = new HashMap<>();
					Disburden disburden = new Disburden();
					disburden.setUnloadPort(load2.getUnloadPort());
					disburden.setOilType(load2.getOilType());
					disburden.setShipPactId(shipPactId);
					List<Disburden> list3 = disburdenService.queryByEntitys(disburden);
					Disburden disburden3 = new Disburden();
					if (!list3.isEmpty()){
						Disburden shipment2 = list3.get(0);
						BeanUtils.copyProperties(shipment2, disburden3);
					}
					maps.put("ship", load2);
					maps.put("goods", disburden3);
					unlistMap.add(maps);
				}
			}
			//在途
			Transit transit = new Transit();
			transit.setShipPactId(shipPactId);
			List<Transit> list3 = transitService.queryByEntitys(transit);
			List<Transit> list4 = new ArrayList<>();
			String unload ="";
			String eta = "";
			//for (Transit transit2 : list3) {
			for(int i=0;i<list3.size();i++){
				if ( i == 0){
					Transit transit2 = list3.get(i);
					String unloadEta = transit2.getUnloadEta();
					if (!StringUtils.isNullOrEmpty(unloadEta)){
						String[] split = unloadEta.split(";");
						for (String str : split) {
							String[] split2 = str.split("/");
							if (split2.length>=2){
								unload=unload+split2[0]+"/";
								String[] split3 = split2[1].split("\\ ");
								eta=eta+split3[0]+"/";
							}
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							unload=unload.substring(0,unload.length()-1);
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							eta=eta.substring(0,eta.length()-1);
						}
					}
					transit2.setUnloadEta(unload);
					transit2.setExt1(eta);
					list4.add(transit2);
					
				}
			}
			map.put("agreementCode", agreement.getAgreementCode());
			map.put("VoyageStart", VoyageStartList);
			map.put("loadPort", listMap);
			map.put("unloadPort", unlistMap);
			map.put("transit", list4);
			map.put("unloadAndETAs", unloadAndETAs);
			list.add(map);
		}
		return list;
	}
	/**
	 * 查询物流跟踪信息
	 * @param idList
	 * @param shipPActId 
	 * @return
	 */
	private List<Map<String, Object>> queryTrackByIds(List<String> idList, Long shipPactId) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (String uuid : idList) {
			Map<String, Object> map = new LinkedHashMap<>();
			Agreement agreement = agreementService.findByUuid(uuid);
			if (agreement == null){
				throw new TransportException(TransportException.TYPE.ITSH027);
			}
			//航次开始
			VoyageStart voyageStart = new VoyageStart();
			voyageStart.setShipPactId(shipPactId);
			List<VoyageStart> VoyageStartList = voyageStartService.queryByEntitys(voyageStart);
			//装港
			LoadPort loadPort = new LoadPort();
			loadPort.setAgreementUuid(uuid+";");
			List<LoadPort> list2 = loadPortService.queryByEntitys(loadPort);
			List<Map<String,Object>> listMap = new ArrayList<>();
			if (!list2.isEmpty()){
				for (LoadPort load2 : list2) {
					Map<String,Object> maps = new HashMap<>();
					Shipment shipment = new Shipment();
					shipment.setLoadPort(load2.getLoadPort());
					shipment.setOilType(load2.getOilType());
					shipment.setShipPactId(shipPactId);
					List<Shipment> list3 = shipmentService.queryByEntitys(shipment);
					Shipment shipment3 = new Shipment();
					if (!list3.isEmpty()){
						Shipment shipment2 = list3.get(0);
						String load = commonService.findNameByCodeAndLang("1", shipment2.getLoadPortCode());
						String oil = commonService.findNameByCodeAndLang("3", shipment2.getOilTypeCode());
						shipment2.setLoadPort(load);
						shipment2.setOilType(oil);
						BeanUtils.copyProperties(shipment2, shipment3);
					}
					//查询图片信息
					Accessory accessory = new Accessory();
					accessory.setType("3");
					accessory.setId(load2.getLoadPortId());
					List<Accessory> entitys = accessoryService.queryByEntitys(accessory);
					List<Map<String,Object>> imgList = new ArrayList<>();
					for (Accessory acc : entitys) {
						Map<String,Object> accMap = new HashMap<>();
						String path = acc.getPath();
						if (!StringUtils.isNullOrEmpty(path)){
							int index = path.lastIndexOf(".");
							int length = path.length();
							String str = path.substring(index+1, length);
							if ("jpg".equals(str) || "jpeg".equals(str) || "bmp".equals(str)|| "png".equals(str)){
								accMap.put("type", "1");
							} else {
								accMap.put("type", "2");
							}
							accMap.put("path", path);
							imgList.add(accMap);
						}
					}
					List<LoadNameSort> time = getTime(load2);
					List<List<LoadNameSort>> formatList = formatList(time);
					maps.put("timeList", formatList);
					maps.put("imgList", imgList);
					maps.put("ship", load2);
					maps.put("goods", shipment3);
					listMap.add(maps);
				}
			}
			//卸港
			UnloadPort unloadPort = new UnloadPort();
			unloadPort.setAgreementUuid(uuid+";");
			List<UnloadPort> unlist2 = unloadPortService.queryByEntitys(unloadPort);
			List<Map<String,Object>> unlistMap = new ArrayList<>();
			List<Map<String,Object>> unloadAndETAs = new ArrayList<>();
			if (!unlist2.isEmpty()){
				for (UnloadPort load2 : unlist2) {
					//TODO 移动端专用
					Map<String,Object> unloadAndETA = new HashMap<String, Object>();
					unloadAndETA.put("unload", load2.getUnloadPort());
					unloadAndETA.put("ETA", load2.getEta());
					unloadAndETAs.add(unloadAndETA);
					
					Appoint appoint = new Appoint();
					appoint.setUnloadPortId(load2.getUnloadPortId());
					appoint.setAgreementUuid(uuid);
					List<Appoint> list4 = appointService.queryByEntitys(appoint);
					if (list4 != null && !list4.isEmpty()){
						Appoint appoint2 = list4.get(0);
						load2.setInspection(appoint2.getInspection());
						load2.setInspectionTel(appoint2.getInspectionTel());
						load2.setAgency(appoint2.getAgency());
						load2.setAgencyTel(appoint2.getAgencyTel());
						load2.setMonitor(appoint2.getMonitor());
						load2.setMonitorTel(appoint2.getMonitorTel());
					}
					Map<String,Object> maps = new HashMap<>();
					Disburden disburden = new Disburden();
					disburden.setUnloadPort(load2.getUnloadPort());
					disburden.setOilType(load2.getOilType());
					disburden.setShipPactId(shipPactId);
					List<Disburden> list3 = disburdenService.queryByEntitys(disburden);
					Disburden disburden3 = new Disburden();
					if (!list3.isEmpty()){
						Disburden shipment2 = list3.get(0);
						String load = commonService.findNameByCodeAndLang("2", shipment2.getUnloadPortCode());
						String oil = commonService.findNameByCodeAndLang("3", shipment2.getOilTypeCode());
						shipment2.setUnloadPort(load);
						shipment2.setOilType(oil);
						BeanUtils.copyProperties(shipment2, disburden3);
					}
					//查询图片信息
					Accessory accessory = new Accessory();
					accessory.setType("5");
					accessory.setId(load2.getUnloadPortId());
					List<Accessory> entitys = accessoryService.queryByEntitys(accessory);
					List<Map<String,Object>> imgList = new ArrayList<>();
					for (Accessory acc : entitys) {
						Map<String,Object> accMap = new HashMap<>();
						String path = acc.getPath();
						if (!StringUtils.isNullOrEmpty(path)){
							int index = path.lastIndexOf(".");
							int length = path.length();
							String str = path.substring(index+1, length);
							if ("jpg".equals(str) || "jpeg".equals(str) || "bmp".equals(str)|| "png".equals(str)){
								accMap.put("type", "1");
							} else {
								accMap.put("type", "2");
							}
							accMap.put("path", path);
							imgList.add(accMap);
						}
					}
					List<LoadNameSort> sortList = getTimes(load2);
					List<List<LoadNameSort>> formatList = formatList(sortList);
					maps.put("timeList", formatList);
					maps.put("imgList", imgList);
					maps.put("ship", load2);
					maps.put("goods", disburden3);
					unlistMap.add(maps);
				}
			}
			//在途
			Transit transit = new Transit();
			transit.setShipPactId(shipPactId);
			List<Transit> list3 = transitService.queryByEntitys(transit);
			List<TransitRes> list4 = new ArrayList<>();
			String unload ="";
			String eta = "";
			//for (Transit transit2 : list3) {
			for(int i=0;i<list3.size();i++){
				if ( i == 0){
					Transit transit2 = list3.get(i);
					String unloadEta = transit2.getUnloadEta();
					if (!StringUtils.isNullOrEmpty(unloadEta)){
						String[] split = unloadEta.split(";");
						for (String str : split) {
							String[] split2 = str.split("/");
							if (split2.length>=2){
								unload=commonService.findNameByCodeAndLang("2", unload)+split2[0]+"/";
								String[] split3 = split2[1].split("\\ ");
								eta=eta+split3[0]+"/";
							}
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							unload=unload.substring(0,unload.length()-1);
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							eta=eta.substring(0,eta.length()-1);
						}
					}
					transit2.setUnloadEta(unload);
					transit2.setExt1(eta);
					
					TransitRes res = new TransitRes();
					BeanUtils.copyProperties(transit2, res);
					//查询图片信息
					Accessory accessory = new Accessory();
					accessory.setType("4");
					accessory.setId(res.getTransitId());
					List<Accessory> entitys = accessoryService.queryByEntitys(accessory);
					List<String> imgList = new ArrayList<>();
					for (Accessory acc : entitys) {
						imgList.add(acc.getPath());
					}
					res.setImgList(imgList);
					list4.add(res);
					
				}
			}
			map.put("agreementCode", agreement.getAgreementCode());
			map.put("VoyageStart", VoyageStartList);
			map.put("loadPort", listMap);
			map.put("unloadPort", unlistMap);
			map.put("transit", list4);
			map.put("flag", agreement.getOrderNo());
			map.put("unloadAndETAs", unloadAndETAs);
			list.add(map);
		}
		return list;
	}

	/*	
	 * (non-Javadoc)
	 * @see com.sinochem.crude.trade.transport.service.ShipPactService#queryShipOwnerShipPactList(java.util.Map, com.sinochem.crude.trade.common.SimplePageInfo)
	 */
	public Page<Map<String, Object>> queryShipOwnerShipPactList(Map<String, Object> map, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) _ShipPactMapper.queryShipOwnerShipPact(map);
		
		/**
		 * 根据语言环境设置 shipOwner、carrierName、brokerName
		 */
		if ("en".equals(lang)){
			for (Map<String, Object> map2 : page) {
				//shipOwner
				Object obj1=map2.get("shipOwnerEn");
				Object obj2=map2.get("shipOwnerId");
				if(obj1!=null){
					map2.put("shipOwner", (String)obj1);
				}else if(obj2!=null){
					map2.put("shipOwner", commonService.findNameByEpMemberId((Long)obj2));
				}
				//carrierName
				Object obj3=map2.get("carrierNameEn");
				Object obj4=map2.get("carrierId");
				if(obj3!=null){
					map2.put("carrierName", (String)obj3);
				}else if(obj4!=null){
					map2.put("carrierName", commonService.findNameByEpMemberId((Long)obj4));
				}
				//brokerName
				Object obj5=map2.get("brokerNameEn");
				Object obj6=map2.get("brokerId");
				if(obj5!=null){
					map2.put("brokerName", (String)obj5);
				}else if(obj6!=null){
					map2.put("brokerName", commonService.findNameByEpMemberId((Long)obj6));
				}
			}
		}
		
		return page;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sinochem.crude.trade.transport.service.ShipPactService#queryShipAgentShipPactList(java.util.Map, com.sinochem.crude.trade.common.SimplePageInfo)
	 */
	public Page<Map<String, Object>> queryShipAgentShipPactList(Map<String, Object> map, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
				
		Page<Map<String,Object>> page = this.queryRecords(map, pageInfo);
		
		/**
		 * 根据语言环境设置 shipOwner、carrierName、brokerName
		 */
		if ("en".equals(lang)){
			for (Map<String, Object> map2 : page) {
				//shipOwner
				Object obj1=map2.get("shipOwnerEn");
				Object obj2=map2.get("shipOwnerId");
				if(obj1!=null){
					map2.put("shipOwner", (String)obj1);
				}else if(obj2!=null){
					map2.put("shipOwner", commonService.findNameByEpMemberId((Long)obj2));
				}
				//carrierName
				Object obj3=map2.get("carrierNameEn");
				Object obj4=map2.get("carrierId");
				if(obj3!=null){
					map2.put("carrierName", (String)obj3);
				}else if(obj4!=null){
					map2.put("carrierName", commonService.findNameByEpMemberId((Long)obj4));
				}
				//brokerName
				Object obj5=map2.get("brokerNameEn");
				Object obj6=map2.get("brokerId");
				if(obj5!=null){
					map2.put("brokerName", (String)obj5);
				}else if(obj6!=null){
					map2.put("brokerName", commonService.findNameByEpMemberId((Long)obj6));
				}
			}
		}
				
		return page;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sinochem.crude.trade.transport.service.ShipPactService#queryCargoOwnerShipPactList(java.util.Map, com.sinochem.crude.trade.common.SimplePageInfo)
	 */
	public Page<Map<String, Object>> queryCargoOwnerShipPactList(
			Map<String, Object> map, SimplePageInfo pageInfo) {
		Page<Map<String,Object>> page = this.queryRecords(map, pageInfo);
		return page;
	}
	
	
	 public static String reserveTwo(BigDecimal i){
	    	if(i!=null){
	    		DecimalFormat df1 = new DecimalFormat("0.00");  
	    		String str = df1.format(i);
	    		return str;
	    	}
	    	return null;
	    }
	//***************************************************************************************
	
	/**
	 * 根据询盘uuid或者船盘UUID查询合同信息
	 * @param shippact
	 * @return
	 */
	public ShipPact queryIntentionOrPlateUuid(ShipPactVO vo) {
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
				
		
	/*	//获取询盘uuid
		String intentionUuid = vo.getShipIntentionUuid();
		Intention intention = intentionService.findByUuid(intentionUuid);

		//获取船盘uuid
		String shipPlateUuid = intention.getShipPlateUuid();
		
		//根据船盘uuid，查询船合同对象
		Map<String, Object> map=new HashMap<>();
		map.put("shipPlateUuid", shipPlateUuid);
		List<Map<String,Object>> list = this.queryRecords(map);
		
		List<ShipPact> list2 = BeanConvertUtils.mapToBeanInList(list, ShipPact.class);
		return list2;*/
		String intentionUuid = vo.getShipIntentionUuid();
		String shipPlateUuid = vo.getShipPlateUuid();
		Map<String, Object> map=new HashMap<>();
		map.put("shipIntentionUuid", intentionUuid);
		map.put("shipPlateUuid", shipPlateUuid);
		ShipPact shipPact =  _ShipPactMapper.findPactByIntentionOrPlateUuid(map);
		
		//根据语言环境设置 shipOwner、carrierName、brokerName
		if ("en".equals(lang)){
			//shipOwner
			if(!StringUtils.isNullOrEmpty(shipPact.getShipOwnerEn())){
				shipPact.setShipOwner(shipPact.getShipOwnerEn());
			}else if(shipPact.getShipOwnerId()!=null){
				shipPact.setShipOwner(commonService.findNameByEpMemberId(shipPact.getShipOwnerId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(shipPact.getCarrierNameEn())){
				shipPact.setCarrierName(shipPact.getCarrierNameEn());
			}else if(shipPact.getCarrierId()!=null){
				shipPact.setCarrierName(commonService.findNameByEpMemberId(shipPact.getCarrierId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(shipPact.getBrokerNameEn())){
				shipPact.setBrokerName(shipPact.getBrokerNameEn());
			}else if(shipPact.getBrokerId()!=null){
				shipPact.setBrokerName(commonService.findNameByEpMemberId(shipPact.getBrokerId()));
			}
		}
		
		return shipPact;
	}

	@Override
	public Page<ShipPact> queryShipPactLists(Map<String, Object> map,SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<ShipPact> list = (Page<ShipPact>) _ShipPactMapper.selectPactByAgreementCode(map);
		return list;
	}

	@Override
	public int queryShipPactListCount(Map<String, Object> map) {
		int i = 0;
		i=_ShipPactMapper.selectPactByAgreementCodeCount(map);
		return i;
	}

	@Override
	@Transactional
	public void updateShipPactStatus(ShipPactVO vo, CurrentUser user) {
		
		//修改船合同状态
		ShipPact shipPact = this.findByUuid(vo.getUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (!"1".equals(shipPact.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH087);
		}
		shipPact.setStatus(Constants.SHIP_PACT_STATUS_2);
		shipPact.setUpdateDate(DateTimeUtils.currentDate());
		shipPact.setUpdateUser(user.getMemberId());
		this.updateRecord(shipPact);
		
		//修改船盘 leaveFlag=1
		ShipPlate shipPlate = shipPlateService.findByUuid(shipPact.getShipPlateUuid());
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		if (!"3".equals(shipPlate.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH014);
		}
		shipPlate.setLeaveFlag("1");
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		shipPlateService.updateRecord(shipPlate);
	}

	@Override
	public ShipPact findByEntitys(ShipPact pact) {
		return _ShipPactMapper.findByEntitys(pact);
	}
	
	
	private List<LoadNameSort> getTime(LoadPort load2){
		List<LoadNameSort> sortList  = new ArrayList<>();
		SimpleDateFormat dateFormat;
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		if ("en".equals(lang)){
			dateFormat = new SimpleDateFormat("EEEE",Locale.ENGLISH);
		} else {
			dateFormat = new SimpleDateFormat("EEEE",Locale.CHINESE);
		}
		if (load2.getNorDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("NOR Tendere");
			} else {
				sort1.setName("递交NOR");
			}
			sort1.setTime(load2.getNorDate());
			sort1.setWeek(dateFormat.format(load2.getNorDate()));
			sortList.add(sort1);
		}
		if (load2.getWaterDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Pilot on Board");
			} else {
				sort1.setName("引水上船");
			}
			sort1.setTime(load2.getWaterDate());
			sort1.setWeek(dateFormat.format(load2.getWaterDate()));
			sortList.add(sort1);
		}
		if (load2.getAtripDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Anchor Up");
			} else {
				sort1.setName("起锚");
			}
			sort1.setTime(load2.getAtripDate());
			sort1.setWeek(dateFormat.format(load2.getAtripDate()));
			sortList.add(sort1);
		}
		if (load2.getBerthDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Mooring Complete");
			} else {
				sort1.setName("靠泊完成");
			}
			sort1.setTime(load2.getBerthDate());
			sort1.setWeek(dateFormat.format(load2.getBerthDate()));
			sortList.add(sort1);
		}
		if (load2.getAcStart() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Commenced Loading");
			} else {
				sort1.setName("装货开始");
			}
			sort1.setTime(load2.getAcStart());
			sort1.setWeek(dateFormat.format(load2.getAcStart()));
			sortList.add(sort1);
		}
		if (load2.getAcFinish() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Completed Loading");
			} else {
				sort1.setName("装货完成");
			}
			sort1.setTime(load2.getAcFinish());
			sort1.setWeek(dateFormat.format(load2.getAcFinish()));
			sortList.add(sort1);
		}
		if (load2.getExLeave() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Estimated Departure");
			} else {
				sort1.setName("预计离港");
			}
			sort1.setTime(load2.getExLeave());
			sort1.setWeek(dateFormat.format(load2.getExLeave()));
			sortList.add(sort1);
		}
		if (load2.getAcLeave() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Departure");
			} else {
				sort1.setName("实际离港");
			}
			sort1.setTime(load2.getAcLeave());
			sort1.setWeek(dateFormat.format(load2.getAcLeave()));
			sortList.add(sort1);
		}
		if (load2.getRemTubeDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Cargo Hoses Disconnected");
			} else {
				sort1.setName("拆管");
			}
			sort1.setTime(load2.getRemTubeDate());
			sort1.setWeek(dateFormat.format(load2.getRemTubeDate()));
			sortList.add(sort1);
		}
		Collections.sort(sortList);
		return sortList;
	}
	private List<LoadNameSort> getTimes(UnloadPort load2){
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		List<LoadNameSort> sortList  = new ArrayList<>();
		SimpleDateFormat dateFormat ;
		if ("en".equals(lang)){
			dateFormat = new SimpleDateFormat("EEEE",Locale.ENGLISH);
		} else {
			dateFormat = new SimpleDateFormat("EEEE",Locale.CHINESE);
		}
		if (load2.getNorDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("NOR Tendere");
			} else {
				sort1.setName("递交NOR");
			}
			sort1.setTime(load2.getNorDate());
			sort1.setWeek(dateFormat.format(load2.getNorDate()));
			sortList.add(sort1);
		}
		if (load2.getWaterDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Pilot on Board");
			} else {
				sort1.setName("引水上船");
			}
			sort1.setTime(load2.getWaterDate());
			sort1.setWeek(dateFormat.format(load2.getWaterDate()));
			sortList.add(sort1);
		}
		if (load2.getAtripDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Anchor Up");
			} else {
				sort1.setName("起锚");
			}
			sort1.setTime(load2.getAtripDate());
			sort1.setWeek(dateFormat.format(load2.getAtripDate()));
			sortList.add(sort1);
		}
		if (load2.getBerthDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Mooring Complete");
			} else {
				sort1.setName("靠泊完成");
			}
			sort1.setTime(load2.getBerthDate());
			sort1.setWeek(dateFormat.format(load2.getBerthDate()));
			sortList.add(sort1);
		}
		if (load2.getAcStart() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Commenced Discharging");
			} else {
				sort1.setName("卸货开始");
			}
			sort1.setTime(load2.getAcStart());
			sort1.setWeek(dateFormat.format(load2.getAcStart()));
			sortList.add(sort1);
		}
		if (load2.getAcFinish() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Completed Discharging");
			} else {
				sort1.setName("卸货完成");
			}
			sort1.setTime(load2.getAcFinish());
			sort1.setWeek(dateFormat.format(load2.getAcFinish()));
			sortList.add(sort1);
		}
		if (load2.getExLeave() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Estimated Departure");
			} else {
				sort1.setName("预计离港");
			}
			sort1.setTime(load2.getExLeave());
			sort1.setWeek(dateFormat.format(load2.getExLeave()));
			sortList.add(sort1);
		}
		if (load2.getAcLeave() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Departure");
			} else {
				sort1.setName("实际离港");
			}
			sort1.setTime(load2.getAcLeave());
			sort1.setWeek(dateFormat.format(load2.getAcLeave()));
			sortList.add(sort1);
		}
		if (load2.getRemTubeDate() != null){
			LoadNameSort sort1 = new LoadNameSort();
			if ("en".equals(lang)){
				sort1.setName("Cargo Hoses Disconnected");
			} else {
				sort1.setName("拆管");
			}
			sort1.setTime(load2.getRemTubeDate());
			sort1.setWeek(dateFormat.format(load2.getRemTubeDate()));
			sortList.add(sort1);
		}
		Collections.sort(sortList);
		return sortList;
	}
	
	private List<List<LoadNameSort>> formatList(List<LoadNameSort> list){
		List<List<LoadNameSort>> lists = new ArrayList<>();
		if (list != null && !list.isEmpty()){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String> strList = new ArrayList<>();
			List<LoadNameSort> list1 = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				LoadNameSort load = list.get(i);
				Date time = load.getTime();
				String str = dateFormat.format(time);
				if (!strList.contains(str) && !list1.isEmpty()){
					lists.add(list1);
					list1 = new ArrayList<>();
				}
					strList.add(str);
					list1.add(load);
				if (i == list.size()-1){
					lists.add(list1);
				}
			}
		}
			return lists;
	}

	@Override
	public Page<Map<String, Object>> queryShipPactPlatform(Map<String, Object> map, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) _ShipPactMapper.queryShipPactPlatform(map);
			NumberFormat nf = NumberFormat.getInstance();  
			
			for (Map<String,Object> maps : page) {
				
				/**
				 * 设置 shipOwner、carrierName、brokerName中英文
				 */
				if ("en".equals(lang)){
					//shipOwner
					Object obj1=maps.get("shipOwnerEn");
					Object obj2=maps.get("shipOwnerId");
					if(obj1!=null){
						maps.put("shipOwner", (String)obj1);
					}else if(obj2!=null){
						maps.put("shipOwner", commonService.findNameByEpMemberId((Long)obj2));
					}
					//carrierName
					Object obj3=maps.get("carrierNameEn");
					Object obj4=maps.get("carrierId");
					if(obj3!=null){
						maps.put("carrierName", (String)obj3);
					}else if(obj4!=null){
						maps.put("carrierName", commonService.findNameByEpMemberId((Long)obj4));
					}
					//brokerName
					Object obj5=maps.get("brokerNameEn");
					Object obj6=maps.get("brokerId");
					if(obj5!=null){
						maps.put("brokerName", (String)obj5);
					}else if(obj6!=null){
						maps.put("brokerName", commonService.findNameByEpMemberId((Long)obj6));
					}
				}
				
				
				ShipPlate shipPlate = shipPlateService.findByUuid((String)maps.get("shipPlateUuid"));
				maps.put("epMemberNameEn", shipPlate.getEpMemberName());
				SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormaters = new SimpleDateFormat("yyyy-MM-dd");
				if (maps.get("minQuantity") != null){
					BigDecimal min = (BigDecimal)maps.get("minQuantity");
					maps.put("minQuantity", nf.format(min));
				}
				maps.put("signDates", dateFormater.format(maps.get("signDate")));
				if (maps.get("pactBeg") != null && maps.get("pactEnd") != null){
					maps.put("pactBeg", dateFormaters.format(maps.get("pactBeg"))+"--"+dateFormaters.format(maps.get("pactEnd")));
				} else {
					maps.put("pactBeg", "");
				}
				//查询运单信息
				long shipPactId = (long) maps.get("shipPactId");
				Waybill waybill = new Waybill();
				waybill.setShipPactId(shipPactId);
				List<Waybill> list = waybillService.queryByEntitys(waybill);
				List<Waybill> list2 = new ArrayList<>();
				String oil = "";
				String quants = "";
				for (Waybill waybill2 : list) {
					
					//获取运单油种
					String oiiType="";
					String oilTypeCode = waybill2.getOilTypeCode();
					if(!StringUtils.isNullOrEmpty(oilTypeCode)){
						String[] oilCodeArr = oilTypeCode.split("/");
						for (String oilCode : oilCodeArr) {
							if(!StringUtils.isNullOrEmpty(oilCode)){
								oiiType+=commonService.findNameByCodeAndLang("3", oilCode)+"/";
							}
						}
					}
					//获取装港
					String loadPort="";
					String loadCodes = waybill2.getLoadPortCode();
					if(!StringUtils.isNullOrEmpty(loadCodes)){
						String[] codeArr = loadCodes.split("/");
						for (String loadCodeData : codeArr) {
							if(!StringUtils.isNullOrEmpty(loadCodeData)){
								loadPort+=commonService.findNameByCodeAndLang("1", loadCodeData)+"/";
							}
						}
					}
					//获取卸港
					String unloadPort="";
					String unloadCodes = waybill2.getUnloadPortCode();
					if(!StringUtils.isNullOrEmpty(unloadCodes)){
						String[] unCodeArr = unloadCodes.split("/");
						for (String loadCodeData : unCodeArr) {
							if(!StringUtils.isNullOrEmpty(loadCodeData)){
								unloadPort+=commonService.findNameByCodeAndLang("2", loadCodeData)+"/";
							}
						}
					}
					
					String quantity = "";
					if ("en".equals(lang)){
						quantity=waybill2.getQuantityEn();
					}else{
						quantity=waybill2.getQuantity();
					}
					
					oil=oil+oiiType;
					quants = quants+quantity;
					
					Agreement agreement = agreementService.findByUuid(waybill2.getAgreementUuid());
					if (agreement != null){
						waybill2.setExt1(agreement.getShipName());
					}
					if (!StringUtils.isNullOrEmpty(oiiType)){
						waybill2.setOiiType(oiiType.substring(0,oiiType.length()-1));
					}
					if (!StringUtils.isNullOrEmpty(loadPort)){
						waybill2.setLoadPort(loadPort.substring(0,loadPort.length()-1));
					}
					if (!StringUtils.isNullOrEmpty(unloadPort)){
						waybill2.setUnloadPort(unloadPort.substring(0,unloadPort.length()-1));
					}
					if (!StringUtils.isNullOrEmpty(quantity)){
						waybill2.setQuantity(quantity.substring(0,quantity.length()-1));
					}
					if (!StringUtils.isNullOrEmpty(waybill2.getLaycan())){
						waybill2.setLaycan(waybill2.getLaycan().replaceAll("/", "--"));
					}
					
					/**
					 * 根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
					 */
					if ("en".equals(lang)){
						//epMemberName
						if(!StringUtils.isNullOrEmpty(waybill2.getEpMemberNameEn())){
							waybill2.setEpMemberName(waybill2.getEpMemberNameEn());
						}else if(waybill2.getEpMemberId()!=null){
							waybill2.setEpMemberName(commonService.findNameByEpMemberId(waybill2.getEpMemberId()));
						}
						//shipOwner
						if(!StringUtils.isNullOrEmpty(waybill2.getShipOwnerEn())){
							waybill2.setShipOwner(waybill2.getShipOwnerEn());
						}else if(waybill2.getShipOwnerId()!=null){
							waybill2.setShipOwner(commonService.findNameByEpMemberId(waybill2.getShipOwnerId()));
						}
						//carrierName
						if(!StringUtils.isNullOrEmpty(waybill2.getCarrierNameEn())){
							waybill2.setCarrierName(waybill2.getCarrierNameEn());
						}else if(waybill2.getCarrierId()!=null){
							waybill2.setCarrierName(commonService.findNameByEpMemberId(waybill2.getCarrierId()));
						}
						//brokerName
						if(!StringUtils.isNullOrEmpty(waybill2.getBrokerNameEn())){
							waybill2.setBrokerName(waybill2.getBrokerNameEn());
						}else if(waybill2.getBrokerId()!=null){
							waybill2.setBrokerName(commonService.findNameByEpMemberId(waybill2.getBrokerId()));
						}
					}
					
					list2.add(waybill2);
				}
				if (!StringUtils.isNullOrEmpty(oil)){
					oil = oil.substring(0,oil.length()-1);
				}
				if (!StringUtils.isNullOrEmpty(quants)){
					quants = quants.substring(0,quants.length()-1);
				}
				maps.put("oilTypes", oil);
				maps.put("quantitys", quants);
				maps.put("list", list2);
		}
		return page;
	}

	@Override
	public List<Map<String, Object>> queryValueSetEn(ShipPactVO vo) {
		List<Map<String, Object>> list= new ArrayList<>();
		//Boolean flag = false;
		if (!StringUtils.isNullOrEmpty(vo.getValueSetTypes())){
			String list2 = vo.getValueSetTypes();
			String[] split = list2.split(";");
			for (String string : split) {
				String str = "";
				switch (string) {
				case "1":
					str=Constants.VALUE_SET_1;
					break;
				case "2":
					str=Constants.VALUE_SET_2;
					break;
				case "3":
					str=Constants.VALUE_SET_3;
					break;
				case "4":
					str=Constants.VALUE_SET_4;
					break;
				case "5":
					str=Constants.VALUE_SET_5;
					break;
				case "6":
					str=Constants.VALUE_SET_6;
					break;
				case "7":
					str=Constants.VALUE_SET_7;
					break;
				case "8":
					str=Constants.VALUE_SET_8;
					break;
				case "9":
					str=Constants.VALUE_SET_9;
					break;
				case "10":
					str=Constants.VALUE_SET_10;
					break;
				case "11":
					str=Constants.VALUE_SET_11;
					break;
				case "12":
					str=Constants.VALUE_SET_12;
					break;
				case "13":
					str=Constants.VALUE_SET_13;
					break;
				case "14":
					str=Constants.VALUE_SET_14;
					break;
				case "15":
					str=Constants.VALUE_SET_15;
					break;
				case "16":
					str=Constants.VALUE_SET_16;
					break;
				case "17":
					str=Constants.VALUE_SET_17;
					break;
				case "100":
					//flag =true;
					str=vo.getSubGroup();
					break;
				default:
					break;
				}
				//查询值集
					Map<String, CodeValue> group;
					if (str != Constants.VALUE_SET_10 && str != Constants.VALUE_SET_5 && str != Constants.VALUE_SET_4 && str != Constants.VALUE_SET_9 && str != Constants.VALUE_SET_12 && str != Constants.VALUE_SET_17){
						group = ValueSetUtils.getValuesByGroup(str,"zh");
					} else if (str == Constants.VALUE_SET_4){
						group = ValueSetUtils.getValuesByGroup(str,"en");
					} else {
						group = ValueSetUtils.getValuesByGroup(str);
					}
					for (Map.Entry<String, CodeValue> e : group.entrySet()) {
						CodeValue value = e.getValue();
						Map<String,Object> map = new LinkedHashMap<>();
						map.put("code", value.getCode());
						map.put("subGroup", value.getValue());
						map.put("value", value.getValue());
						map.put("type", string);
						list.add(map);
					}
			}
		} else {
			String str = "";
			switch (vo.getValueSetType()) {
			case "1":
				str=Constants.VALUE_SET_1;
				break;
			case "2":
				str=Constants.VALUE_SET_2;
				break;
			case "3":
				str=Constants.VALUE_SET_3;
				break;
			case "4":
				str=Constants.VALUE_SET_4;
				break;
			case "5":
				str=Constants.VALUE_SET_5;
				break;
			case "6":
				str=Constants.VALUE_SET_6;
				break;
			case "7":
				str=Constants.VALUE_SET_7;
				break;
			case "8":
				str=Constants.VALUE_SET_8;
				break;
			case "9":
				str=Constants.VALUE_SET_9;
				break;
			case "10":
				str=Constants.VALUE_SET_10;
				break;
			case "11":
				str=Constants.VALUE_SET_11;
				break;
			case "12":
				str=Constants.VALUE_SET_12;
				break;
			case "13":
				str=Constants.VALUE_SET_13;
				break;
			case "14":
				str=Constants.VALUE_SET_14;
				break;
			case "15":
				str=Constants.VALUE_SET_15;
				break;
			case "16":
				str=Constants.VALUE_SET_16;
				break;
			case "17":
				str=Constants.VALUE_SET_17;
				break;
			case "100":
				//flag =true;
				str=vo.getSubGroup();
				break;
			default:
				break;
			}
			//查询值集
			Map<String, CodeValue> group;
			if (str != Constants.VALUE_SET_10 && str != Constants.VALUE_SET_5 && str != Constants.VALUE_SET_4 && str != Constants.VALUE_SET_9 && str != Constants.VALUE_SET_12 && str != Constants.VALUE_SET_17){
				group = ValueSetUtils.getValuesByGroup(str,"zh");
			} else if (str == Constants.VALUE_SET_4){
				group = ValueSetUtils.getValuesByGroup(str,"en");
			} else {
				group = ValueSetUtils.getValuesByGroup(str);
			}
			for (Map.Entry<String, CodeValue> e : group.entrySet()) {
				CodeValue value = e.getValue();
				Map<String,Object> map = new LinkedHashMap<>();
				map.put("code", value.getCode());
				map.put("subGroup", value.getValue());
				map.put("value", value.getValue());
				map.put("type", vo.getValueSetType());
				list.add(map);
			}
		}
		
		return list;
	}
}
