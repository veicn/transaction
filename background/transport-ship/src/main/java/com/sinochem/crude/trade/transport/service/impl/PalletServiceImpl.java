package com.sinochem.crude.trade.transport.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.ForShipOrderInfo;
import com.sinochem.crude.trade.orderexecute.remote.IFindShipService;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.PalletMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Concurrent;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.PalletPort;
import com.sinochem.crude.trade.transport.domain.PortRegion;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.ClauseVO;
import com.sinochem.crude.trade.transport.model.PalletVO;
import com.sinochem.crude.trade.transport.model.ShipPlateVO;
import com.sinochem.crude.trade.transport.model.res.PalletPortList;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.query.OrderQuery;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.ConcurrentService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletPortService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.PortRegionService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.WaybillService;

@Service
public class PalletServiceImpl implements PalletService {
	Log log = LogFactory.getLog(PalletServiceImpl.class);
	@Autowired
	private PalletMapper _PalletMapper;
	@Autowired
	private PalletPortService platePortService;
	@Autowired
	private IFindShipService iFindShipService;
	@Autowired
	private ConcurrentService concurrentService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private ShipPlateService shipPlateService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private PortRegionService portRegionService;
	@Autowired
	private IntentionService intentionService; 
	@Autowired
	private ClauseService clauseService;
	@Autowired
	private SysShipService sysShipService;
	@Autowired
	private MessagePushService messagePushService;
	
	
	public PalletMapper getMapper() {
		return _PalletMapper;
	}

	/**
	 * 根据对象-查询对象列表
	 */
	public List<Pallet> queryByEntitys(Pallet pallet) {
		return _PalletMapper.queryByEntitys(pallet);
	}

	/**
	 * 根据主键-查询对象
	 */
	public Pallet findByPrimaryKey(Long palletId) {
		return _PalletMapper.findByPrimaryKey(palletId);
	}

	/**
	 * 根据UUID-查询对象
	 */
	public Pallet findByUuid(String uuid) {
		return _PalletMapper.findByUuid(uuid);
	}

	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Pallet pallet) {
		_PalletMapper.updateRecord(pallet);
	}

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long palletId, Long deleteUser) {
		_PalletMapper.deleteRecordByKey(palletId, deleteUser);
	}

	/**
	 * 新增
	 */
	public void insertRecord(Pallet pallet) {
		_PalletMapper.insertRecord(pallet);
	}

	/*
	 * 根据主键删除数据 public void deleteRecordByKey(Long palletId){
	 * _PalletMapper.deleteRecordByKey(palletId); }
	 */
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return _PalletMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map,SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String, Object>> page =  (Page<Map<String, Object>>) _PalletMapper.queryPallet(map);
		 return page;
	}

	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map) {
		return _PalletMapper.countRecords(map);
	}

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) {
		_PalletMapper.deleteRecords(map);
	}

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_PalletMapper.updateRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/
	/**
	 * 根据PalletUUID-查询对象
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> findPalletByUuid(String uuid) {
		return (Map<String, Object>) _PalletMapper.findPalletByUuid(uuid);
	}

	/***
	 * 租船信息_无订单列表
	 * @param pageInfo 
	 */
	public Page<Map<String, Object>> queryPallet(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _PalletMapper.queryPallet(map);
	}

	/***
	 * 租船信息_有订单列表
	 */
	public Page<Map<String, Object>> findPallet(Map<String, Object> map,SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _PalletMapper.findPallet(map);
	}

	/***
	 * 租船信息_新增
	 */
	@Transactional
	public void savePallet(PalletVO vo, CurrentUser user) {
		// 校验必填
		List<PalletPortList> list = vo.getList();
		if (list == null || list.isEmpty()) {
			throw new TransportException(TransportException.TYPE.ITSH113);
		}
		this.insertionPallet(list, vo,  user);
	}

	/***
	 * 租船信息_新增
	 */
	@Transactional
	public void savePallett(List<PalletPortList> list, PalletVO vo, CurrentUser user) {
	
		// 校验是否有订单租船(1有0无)
		if ("1".equals(vo.getOrderNo())) {
			// 校验必填 订单编号
			String orderCode = vo.getOrderCode();
			if (StringUtils.isNullOrEmpty(orderCode)) {
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			//查询订单详情
			try {
				ForShipOrderInfo sendOrderDetail = iFindShipService.sendOrderDetail(orderCode);
				iFindShipService.generateCharterDemand(orderCode);
				vo.setOilName(sendOrderDetail.getName());
				vo.setOrderPact(sendOrderDetail.getContractNo());
			} catch (BizException e) {
				log.error("查询订单详情接口异常："+e);
				throw new TransportException(e.getCode(),"查询订单详情接口异常："+e.getMessage());
			} catch (Exception e) {
				log.error("查询订单详情接口异常："+e);
				throw new TransportException(TransportException.TYPE.ITSH543);
			}
		}
		
		if(StringUtils.isNullOrEmpty(vo.getPalletType())){
			vo.setPalletType("1");
		}
		//需求类型
		if (StringUtils.isNullOrEmpty(vo.getPalletType())){
			throw new TransportException(TransportException.TYPE.ITSH533);
		}
		if (!Constants.PALLET_TYPE_1.equals(vo.getPalletType()) && !Constants.PALLET_TYPE_2.equals(vo.getPalletType())){
			throw new TransportException(TransportException.TYPE.ITSH534);
		}
		//校验一条订单只能生产一条需求
		if (Constants.ORDER_NO_1.equals(vo.getOrderNo()) && !StringUtils.isNullOrEmpty(vo.getOrderCode())){
			try {
				Concurrent concurrent =new Concurrent();
				concurrent.setId(vo.getOrderCode());
				concurrent.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				concurrent.setCreateDate(DateTimeUtils.currentDate());
				concurrent.setUpdateDate(DateTimeUtils.currentDate());
				concurrentService.insertRecord(concurrent);
			} catch (Exception e) {
				log.error("已生成租船SQL：",e);
				throw new TransportException(TransportException.TYPE.ITSH532);
			}
		}
		vo.setPalletCode(SequenceUtils.nextSequence(Constants.PALLET_CODE));
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		try {
			EnterpriseVo byMemberId = enterpriseService.getByMemberId(user.getEpMemberId());
			vo.setEpMemberName(byMemberId.getFullName());
		} catch (Exception e) {
			log.error("查询企业名称出错：",e);
			//throw new TransportException(TransportException.TYPE.ITSH545);
		}
		vo.setEpMemberId(user.getEpMemberId());
		vo.setLangVer(Constants.LANG_VER);
		vo.setStatus(Constants.PALLET_STATUS_1);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setCreateUser(user.getMemberId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		BigDecimal quantity = new BigDecimal(0);
		for (PalletPortList Port : list) {
			if(Port.getQuantity() != null){
				quantity = quantity.add(Port.getQuantity());
			}
		}
		this.insertRecord(vo);
		for (PalletPortList Port : list) {
			PalletPort port = BeanConvertUtils.beanToBean(Port, PalletPort.class);
			port.setQuantity(Port.getQuantity());
			port.setUuid(KeyGenUtils.newUuid());
			port.setCreateDate(DateTimeUtils.currentDate());
			port.setUpdateDate(DateTimeUtils.currentDate());
			port.setLangVer(Constants.LANG_VER);
			port.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			port.setCreateUser(user.getMemberId()); 
			port.setUpdateUser(user.getMemberId()); 
			port.setPalletUuid(vo.getUuid());                                
			port.setPallletId(vo.getPalletId());
			platePortService.insertRecord(port);
		}
	}

	/***
	 * 租船信息_关闭
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateStatus(PalletVO vo, CurrentUser user) {
		// 租船uuid校验
		if (StringUtils.isNullOrEmpty(vo.getUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Pallet pallet = this.findByUuid(vo.getUuid());
		if (pallet != null) {
			//订单只能生产一条需求
			if (Constants.ORDER_NO_1.equals(pallet.getOrderNo()) && !StringUtils.isNullOrEmpty(pallet.getOrderCode())){
				concurrentService.deleteRecordByKey(pallet.getOrderCode());
			}
			String status = pallet.getStatus();
			if ("1".equals(status)) {
				if(org.apache.commons.lang.StringUtils.isNotBlank(pallet.getOrderCode())){
					try {
						iFindShipService.cancelCharter(pallet.getOrderCode());
					} catch (com.sinochem.it.b2b.common.exception.BizException e) {
						log.error("关闭订单接口异常：",e);
						throw new BizException(e.getMessage());
					}
				}
				pallet.setStatus(Constants.PALLET_STATUS_5);
				pallet.setUpdateUser(user.getMemberId());
				pallet.setUpdateDate(DateTimeUtils.currentDate());
				this.updateRecord(pallet);
			} else {
				throw new TransportException(TransportException.TYPE.ITSH104);
			}
		} else {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
	}

	/**
	 * 
	 * 租船信息_详情
	 * 
	 */
	@Override
	public Map<String, Object> palletDeatil(PalletVO vo) {
		Map<String, Object> map = new HashMap<>();
		// 获取对象
		Pallet pallet = this.findByUuid(vo.getUuid());
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		if ("en".equals(string)){
			pallet.setCompanyName(pallet.getCompanyNameEn());
		} 
		 PalletPort palletport = new PalletPort();
		 palletport.setPalletUuid(vo.getUuid());
		 List<PalletPort> portList =  platePortService.queryByEntitys(palletport);
		 List<PalletPortList> listLoad = new ArrayList<>();
		 List<PalletPortList> listUnload = new ArrayList<>();
		 List<PalletPortList> listRegion = new ArrayList<>();
		 List<PalletPortList> listUnregion = new ArrayList<>();
		 for (PalletPort pallets : portList) {
			  PalletPortList palletPortList = new PalletPortList();
			  BeanUtils.copyProperties(pallets, palletPortList);
			  if (!StringUtils.isNullOrEmpty(pallets.getLoadPort()) && pallets.getQuantity() != null){
				 /* if ("en".equals(string)){
						 if("吨".equals(palletPortList.getUnitName())||"MT".equals(palletPortList.getUnitName())){
							 palletPortList.setUnitName("MT");
						 }
						 if("桶".equals(palletPortList.getUnitName())||"BBL".equals(palletPortList.getUnitName())){
							 palletPortList.setUnitName("BBL");
						 }
					 }else{
						 if("吨".equals(palletPortList.getUnitName())||"MT".equals(palletPortList.getUnitName())){
							 palletPortList.setUnitName("吨");
						 }
						 if("桶".equals(palletPortList.getUnitName())||"BBL".equals(palletPortList.getUnitName())){
							 palletPortList.setUnitName("桶");
						 }
					 }*/
				  String oilTypeCode = pallets.getOilTypeCode();
				  if(!StringUtils.isNullOrEmpty(oilTypeCode)){
					  String oilType = commonService.findNameByCodeAndLang("3", oilTypeCode);
					  if(!"".equals(oilType)){//判断是否为自定义
						  palletPortList.setOilType(oilType);
					  }
				  }else{
					  palletPortList.setOilType("");
				  }
				  String loadPortCode = pallets.getLoadPortCode();
				  if(!StringUtils.isNullOrEmpty(loadPortCode)){
					  String loadPort = commonService.findNameByCodeAndLang("1", loadPortCode);
					  if(!"".equals(loadPort)){//判断是否为自定义
						  palletPortList.setLoadPort(loadPort);
					  }
				  }else{
					  palletPortList.setLoadPort("");
				  }
				  listLoad.add(palletPortList);
			  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadPort()) ){
				  String unloadPortCode = pallets.getUnloadPortCode();
				  if(!StringUtils.isNullOrEmpty(unloadPortCode)){
					  String unloadPort = commonService.findNameByCodeAndLang("2", unloadPortCode);
					  if(!"".equals(unloadPort)){
						  palletPortList.setUnloadPort(unloadPort);
					  }
				  }else{
					  palletPortList.setUnloadPort("");
				  }
				  listUnload.add(palletPortList);
			  } else if (!StringUtils.isNullOrEmpty(pallets.getLoadRegion()) && pallets.getQuantity() != null){
				  listRegion.add(palletPortList);
			  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadRegion()) ){
				  listUnregion.add(palletPortList);
			  }
		}
		  String type ="1";
		  if (listRegion.size() > 0){
			  type = "2";
		  }
		  String str = pallet.getShipType();
			if(str != null){
				str = str.substring(0, str.length()-1);
				pallet.setShipType(str);
			}
			/*String str2 = pallet.getShipAge();
			if(str2 != null){
				str2 = str2.substring(0, str2.length()-1);
				pallet.setShipAge(str2);
			}*/
		  map.put("type", type);
		  map.put("listLoad", listLoad);
		  map.put("listUnload", listUnload);
		  map.put("listRegion", listRegion);
		  map.put("listUnregion", listUnregion);
		  map.put("pallet", pallet);
		return map;
	}
	/**
	 * 
	 * 回显租船协议页面
	 * 
	 */
	@Override
	public Map<String, Object> palletDeatilByUuid(PalletVO vo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		Map<String, Object> map = new HashMap<>();
		
		if(vo.getType().equals("2")){
			Clause clause = clauseService.findByUuid(vo.getUuid());			/**为报盘，查询报盘信息s*/
			//查询船盘信息
			ShipPlateVO plateVo = new ShipPlateVO();
			plateVo.setUuid(clause.getShipPlateUuid());
			ShipPlate ship = shipPlateService.findShipPlateDetail(plateVo);
			//查询租船港口信息
			PalletPort prot = new PalletPort();
			prot.setPalletUuid(clause.getPalletUuid());
			List<PalletPort> portList =  platePortService.queryByEntitys(prot);
			
			List<PalletPortList> listLoad = new ArrayList<>();
			List<PalletPortList> listUnload = new ArrayList<>();
			List<PalletPortList> listRegion = new ArrayList<>();
			List<PalletPortList> listUnregion = new ArrayList<>();
			
			//拼接装港、卸港、油种信息
			for (PalletPort pallets : portList) {
				  PalletPortList palletPortList = new PalletPortList();
				  BeanUtils.copyProperties(pallets, palletPortList);
				  if (!StringUtils.isNullOrEmpty(pallets.getLoadPort()) && pallets.getQuantity() != null){
					  listLoad.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadPort()) ){
					  listUnload.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getLoadRegion()) && pallets.getQuantity() != null){
					  listRegion.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadRegion()) ){
					  listUnregion.add(palletPortList);
				  }
			}
			String type ="1";
			if (listRegion.size() > 0){
				type = "2";
			}
			map.put("type", type);
			map.put("ship", ship);
			map.put("clause", clause);
			map.put("listLoad", listLoad);
			map.put("listUnload", listUnload);
			map.put("listRegion", listRegion);
			map.put("listUnregion", listUnregion);
			
		}else{
			Intention inten = intentionService.findByUuid(vo.getUuid());	/**为询盘，查询询盘信息*/
			if ("en".equals(lang)){
				//epMemberName
				if(!StringUtils.isNullOrEmpty(inten.getEpMemberNameEn())){
					inten.setEpMemberName(inten.getEpMemberNameEn());
				}else{
					inten.setEpMemberName(commonService.queryUserByEpMemberId(inten.getEpMemberId()).getEnglishName());
				}
				//shipPlateMemberName
				if(!StringUtils.isNullOrEmpty(inten.getShipPlateMemberNameEn())){
					inten.setShipPlateMemberName(inten.getShipPlateMemberNameEn());
				}else{
					inten.setShipPlateMemberName(commonService.queryUserByEpMemberId(inten.getShipPlateMemberId()).getEnglishName());
				}
			}
			
			//查询船盘信息
			ShipPlateVO plateVo = new ShipPlateVO();
			plateVo.setUuid(inten.getShipPlateUuid());
			ShipPlate ship = shipPlateService.findShipPlateDetail(plateVo);
			//查询租船港口信息
			PalletPort prot = new PalletPort();
			prot.setPalletUuid(inten.getPalletUuid());
			List<PalletPort> portList =  platePortService.queryByEntitys(prot);
			
			List<PalletPortList> listLoad = new ArrayList<>();
			List<PalletPortList> listUnload = new ArrayList<>();
			List<PalletPortList> listRegion = new ArrayList<>();
			List<PalletPortList> listUnregion = new ArrayList<>();
			
			//拼接装港、卸港、油种信息
			for (PalletPort pallets : portList) {
				  PalletPortList palletPortList = new PalletPortList();
				  BeanUtils.copyProperties(pallets, palletPortList);
				  
				  if (!StringUtils.isNullOrEmpty(pallets.getLoadPort()) && pallets.getQuantity() != null){
					  String loadPortCode = palletPortList.getLoadPortCode();
					  if(!StringUtils.isNullOrEmpty(loadPortCode)){
						  String loadPortData=commonService.findNameByCodeAndLang("1", loadPortCode);
						  if(!StringUtils.isNullOrEmpty(loadPortData)){
							  //非自定义
							  palletPortList.setLoadPort(loadPortData);
						  }
					  }else{
						  palletPortList.setLoadPort("");
					  }
					  listLoad.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadPort()) ){
					  String unloadPortCode = palletPortList.getUnloadPortCode();
					  if(!StringUtils.isNullOrEmpty(unloadPortCode)){
						  String unloadPortData=commonService.findNameByCodeAndLang("2", unloadPortCode);
						  if(!StringUtils.isNullOrEmpty(unloadPortData)){
							  //非自定义
							  palletPortList.setUnloadPort(unloadPortData);
						  }
					  }else{
						  palletPortList.setUnloadPort("");
					  }
					  listUnload.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getLoadRegion()) && pallets.getQuantity() != null){
					  listRegion.add(palletPortList);
				  } else if (!StringUtils.isNullOrEmpty(pallets.getUnloadRegion()) ){
					  listUnregion.add(palletPortList);
				  }
			}
			String type ="1";
			if(listRegion.size()>0){
				  type = "2";
			}
			map.put("type", type);
			map.put("ship", ship);
			map.put("inten", inten);
			map.put("listLoad", listLoad);
			map.put("listUnload", listUnload);
			map.put("listRegion", listRegion);
			map.put("listUnregion", listUnregion);
		}
		
		return map;
	}

	/***
	 * 
	 * 租船信息_取消
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delPallet(PalletVO vo, CurrentUser user) {
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		// uuid校验
		if (StringUtils.isNullOrEmpty(vo.getUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet pallet = this.findByUuid(vo.getUuid());
		if (pallet != null) {
			Long updateUser = user.getMemberId();
			this.deleteRecordByKey(pallet.getPalletId(), updateUser);
		} else {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
	}


	/**
	 * 修改货盘
	 */
	@Override
	@Transactional
	public void updatePallet(PalletVO vo, CurrentUser user) {
		// 检验货盘是否存在
		Pallet pallet = this.findByUuid(vo.getUuid());
		if (pallet == null ){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		// 校验必填
		List<PalletPortList> list = vo.getList();
		if (list == null || list.isEmpty()) {
			throw new TransportException(TransportException.TYPE.ITSH113);
		}
		// 修改租船信息
		
		//设置企业中英文
		EnterpriseVo byMemberId = commonService.queryUserByEpMemberId(user.getEpMemberId());
		vo.setCompanyName(byMemberId.getFullName());
		vo.setCompanyNameEn(byMemberId.getEnglishName());
		//设置会员公司中英文
		vo.setEpMemberName(byMemberId.getFullName());
		vo.setEpMemberNameEn(byMemberId.getEnglishName());
		
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		pallet.setUpdateUser(user.getMemberId());
		pallet.setContacts(vo.getContacts());
		pallet.setEmail(vo.getEmail());
		
		pallet.setTel(vo.getTel());
		pallet.setLaycanBeg(vo.getLaycanBeg());
		pallet.setLaycanEnd(vo.getLaycanEnd());
		pallet.setShipAgeBeg(vo.getShipAgeBeg());
		
		pallet.setShipAgeEnd(vo.getShipAgeEnd());
		String shipAge = vo.getShipAgeBeg()+"-"+vo.getShipAgeEnd();
		pallet.setShipAge(shipAge);
		pallet.setRemark(vo.getRemark());
		
		pallet.setShipType(vo.getShipType());
		BigDecimal quantity = new BigDecimal(0);
		
		for (PalletPortList Port : list) {
			if(Port.getQuantity() != null){
				quantity = quantity.add(Port.getQuantity());
			}
		}
		pallet.setQuantity(quantity);
		//删除原有港口数据
		Map<String,Object> map = new HashMap<>();
		map.put("updateUser", user.getMemberId());
		map.put("palletUuid", pallet.getUuid());
		platePortService.deleteRecords(map);
		
		String portUUID = KeyGenUtils.newUuid();
		pallet.setFLoadPort(portUUID);
		this.updateRecord(pallet);
		boolean flag = true;
		for (PalletPortList Port : list) {
			//油种和装卸港国际化
			PalletPort port = BeanConvertUtils.beanToBean(Port, PalletPort.class);
			if(!StringUtils.isNullOrEmpty(port.getOilType())){
				ValueSetName name = commonService.findNameByCode("3",port.getOilTypeCode());
				if(name.getEnName()==null){
					port.setOilTypeEn(Port.getOilType());
				}else{
					port.setOilType(name.getZhName());
					port.setOilTypeEn(name.getEnName());
				}
				if(!StringUtils.isNullOrEmpty(port.getLoadPort())){
					ValueSetName name2 = commonService.findNameByCode("1",port.getLoadPortCode());
					if(name2.getEnName()==null){
						port.setLoadPortEn(port.getLoadPort());
					}else{
						port.setLoadPort(name2.getZhName());
						port.setLoadPortEn(name2.getEnName());
					}
				}
			}else{
				ValueSetName name3 = commonService.findNameByCode("2",port.getUnloadPortCode());
				if(name3.getEnName()==null){
					port.setUnloadPortEn(port.getUnloadPortCode());
				}else{
					port.setUnloadPort(name3.getZhName());
					port.setUnloadPortEn(name3.getEnName());
				}
			}
			port.setQuantity(Port.getQuantity());
			port.setUuid(KeyGenUtils.newUuid());
			port.setCreateDate(DateTimeUtils.currentDate());
			port.setUpdateDate(DateTimeUtils.currentDate());
			port.setLangVer(Constants.LANG_VER);
			port.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			port.setCreateUser(user.getMemberId()); 
			port.setUpdateUser(user.getMemberId()); 
			port.setPalletUuid(pallet.getUuid());                                
			port.setPallletId(pallet.getPalletId());
			if(flag){
				port.setUuid(portUUID);
				flag=false;
			}
			platePortService.insertRecord(port);
		}
	}

	/**
	 * 我要租船订单翻页列表
	 */
	@Override
	public List<Map<String, Object>> getOrderPageList(OrderQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", query.getName());
		
		List<Map<String, Object>> list = this.queryRecords(map);
		
		return list;
	}
	
	/**
	 * 租船信息_有订单列表
	 */
	@Override
	public Page<Map<String, Object>> findPalletListTrander(Map<String,Object> map,SimplePageInfo simplePageInfo) {
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		Page<Map<String, Object>> palletList = this.findPallet(map,simplePageInfo);
		
		for (Map<String, Object> pallet : palletList) {
		if ("en".equals(string)){
			if(!StringUtils.isNullOrEmpty((String) pallet.get("companyNameEn"))){
				pallet.put("companyName",pallet.get("companyNameEn"));
			}else{//如果companyNameEn为空
				Object obj = pallet.get("epMemberId");
				if(obj!=null){
					Long epMemberId = (Long)obj;
					String companyName = commonService.findNameByEpMemberId(epMemberId);
					pallet.put("companyName",companyName);
				}else{
					pallet.put("companyName","");
				}
			}
		}
	    String palletUuid= (String) pallet.get("uuid");
	    PalletPort palletport = new PalletPort();
	    palletport.setPalletUuid(palletUuid);
	    List<PalletPort> portList =  platePortService.queryByEntitys(palletport);
		String unloadPort = "";
		String loadPort = "";
		String unloadPortRegion ="";
		String loadPortRegion ="";
		String quantity = "";
		String oilType = "";
		for (PalletPort port : portList) {
			if(port != null){
				if(port.getQuantity() != null){
					 if(quantity.equals("")){
						 quantity = port.getQuantity()+"";
						 if ("en".equals(string)){
							 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
								 quantity = port.getQuantity() +"MT";
							 }
							 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
								 quantity = port.getQuantity() +"BBL";
							 }
						 }else{
							 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
								 quantity = port.getQuantity() +"吨";
							 }
							 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
								 quantity = port.getQuantity() +"桶";
							 }
						 }
					 }else{
						 quantity = quantity+"/"+ port.getQuantity();	
						 if ("en".equals(string)){
							 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
								 quantity = quantity+"MT"+"/";	
							 }
							 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
								 quantity = quantity+"BBL"+"/";	
							 }
						 }else{
							 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
								 quantity = quantity+"吨"+"/";	
							 }
							 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
								 quantity = quantity+"桶"+"/";	
							 }
						 }
						 quantity = quantity.substring(0,quantity.length() - 1);
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getUnloadPortCode())){
					 if(unloadPort.equals("")){
						String unloadPortTemp = commonService.findNameByCodeAndLang("2",port.getUnloadPortCode());
						if(!"".equals(unloadPortTemp)){
							unloadPort = unloadPortTemp;
						}else{
							unloadPort = port.getUnloadPortCode();
						}
					 }else{
						 String unloadPortTemp = commonService.findNameByCodeAndLang("2",port.getUnloadPortCode());
						 if(!"".equals(unloadPortTemp)){
							 unloadPort = unloadPort+"/"+unloadPortTemp;
						 }else{
							 unloadPort =  unloadPort+"/"+port.getUnloadPortCode();
						 }
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getLoadPortCode())){
					 if(loadPort.equals("")){
						 String loadPortTemp = commonService.findNameByCodeAndLang("1",port.getLoadPortCode());
						 if(!"".equals(loadPortTemp)){
							 loadPort = loadPortTemp;
						 }else{
							 loadPort = port.getLoadPortCode();
						 }
					 }else{
						 String loadPortTemp = commonService.findNameByCodeAndLang("1",port.getLoadPortCode()); 
						 if(!"".equals(loadPortTemp)){
							 loadPort = loadPort + "/"+loadPortTemp;
						 }else{
							 loadPort = loadPort + "/"+port.getLoadPortCode();
						 }
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getUnloadRegion())){
					 if(unloadPortRegion.equals("")){
						 unloadPortRegion = port.getUnloadRegion() + "";
					 }else{
						 unloadPortRegion = unloadPortRegion+"/"+port.getUnloadRegion();					 
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getLoadRegion())){
					 if(loadPortRegion.equals("")){
						 loadPortRegion = port.getLoadRegion() + "";
					 }else{
						 loadPortRegion =loadPortRegion+"/"+ port.getLoadRegion();					 
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getOilTypeCode())){
					 if(oilType.equals("")){
						 String oilTypeTemp = commonService.findNameByCodeAndLang("3", port.getOilTypeCode());
						 if(!"".equals(oilTypeTemp)){
							 oilType = oilTypeTemp;
						 }else{
							 oilType =  port.getOilTypeCode();
						 }
					 }else{
						 String oilTypeTemp = commonService.findNameByCodeAndLang("3", port.getOilTypeCode());
						 if(!"".equals(oilTypeTemp)){
							 oilType = oilType+"/"+oilTypeTemp;
						 }else{
							 oilType = oilType+"/"+port.getOilTypeCode();
						 }
					 }
				 }
			}
		}
		 pallet.put("loadPortRegion", loadPortRegion);
		 pallet.put("unloadPort", unloadPort);
		 pallet.put("loadPort", loadPort);
		 pallet.put("unloadPortRegion", unloadPortRegion);
		 pallet.put("quantity", quantity);
		 pallet.put("oilType",oilType);
		}
		return palletList;
	}
	/**
	 * 租船信息_无订单列表
	 */
	@Override
	public Page<Map<String, Object>> getPalletListTrader(Map<String,Object> map,SimplePageInfo pageInfo) {
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		Page<Map<String, Object>> palletList = this.queryPallet(map,pageInfo);
		for (Map<String, Object> pallet2 : palletList) {
			if ("en".equals(string)){
				if(!StringUtils.isNullOrEmpty((String) pallet2.get("companyNameEn"))){
					pallet2.put("companyName",pallet2.get("companyNameEn"));
				}else{//如果companyNameEn为空
					Object obj = pallet2.get("epMemberId");
					if(obj!=null){
						Long epMemberId = (Long)obj;
						String companyName = commonService.findNameByEpMemberId(epMemberId);
						pallet2.put("companyName",companyName);
					}else{
						pallet2.put("companyName","");
					}
				}
			}
			  String palletUuid= (String) pallet2.get("uuid");
			  PalletPort palletport = new PalletPort();
			  palletport.setPalletUuid(palletUuid);
			  List<PalletPort> portList =  platePortService.queryByEntitys(palletport);
				String unloadPort = "";
				String loadPort = "";
				String unloadPortRegion ="";
				String loadPortRegion ="";
				String quantity = "";
				String oilType = "";
				for (PalletPort port : portList) {
					if(port != null){
						if(port.getQuantity() != null){
							 if(quantity.equals("")){
								 quantity = port.getQuantity()+"";
								 if ("en".equals(string)){
									 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
										 quantity = port.getQuantity() +"MT";
									 }
									 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
										 quantity = port.getQuantity() +"BBL";
									 }
								 }else{
									 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
										 quantity = port.getQuantity() +"吨";
									 }
									 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
										 quantity = port.getQuantity() +"桶";
									 }
								 }
							 }else{
								 quantity = quantity+"/"+ port.getQuantity();	
								 if ("en".equals(string)){
									 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
										 quantity = quantity+"MT"+"/";	
									 }
									 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
										 quantity = quantity+"BBL"+"/";	
									 }
								 }else{
									 if("吨".equals(port.getUnitName())||"MT".equals(port.getUnitName())){
										 quantity = quantity+"吨"+"/";	
									 }
									 if("桶".equals(port.getUnitName())||"BBL".equals(port.getUnitName())){
										 quantity = quantity+"桶"+"/";	
									 }
								 }
								 quantity = quantity.substring(0,quantity.length() - 1);
							 }
						 }
						 if(!StringUtils.isNullOrEmpty(port.getUnloadPortCode())){
							 if(unloadPort.equals("")){
								String unloadPortTemp = commonService.findNameByCodeAndLang("2",port.getUnloadPortCode());
								if(!"".equals(unloadPortTemp)){
									unloadPort = unloadPortTemp;
								}else{
									unloadPort = port.getUnloadPortCode();
								}
							 }else{
								 String unloadPortTemp = commonService.findNameByCodeAndLang("2",port.getUnloadPortCode());
								 if(!"".equals(unloadPortTemp)){
									 unloadPort = unloadPort+"/"+unloadPortTemp;
								 }else{
									 unloadPort =  unloadPort+"/"+port.getUnloadPortCode();
								 }
							 }
						 }
						 if(!StringUtils.isNullOrEmpty(port.getLoadPortCode())){
							 if(loadPort.equals("")){
								 String loadPortTemp = commonService.findNameByCodeAndLang("1",port.getLoadPortCode());
								 if(!"".equals(loadPortTemp)){
									 loadPort = loadPortTemp;
								 }else{
									 loadPort = port.getLoadPortCode();
								 }
							 }else{
								 String loadPortTemp = commonService.findNameByCodeAndLang("1",port.getLoadPortCode()); 
								 if(!"".equals(loadPortTemp)){
									 loadPort = loadPort + "/"+loadPortTemp;
								 }else{
									 loadPort = loadPort + "/"+port.getLoadPortCode();
								 }
							 }
						 }
						 if(!StringUtils.isNullOrEmpty(port.getUnloadRegion())){
							 if(unloadPortRegion.equals("")){
								 unloadPortRegion = port.getUnloadRegion() + "";
							 }else{
								 unloadPortRegion = unloadPortRegion+"/"+port.getUnloadRegion();					 
							 }
						 }
						 if(!StringUtils.isNullOrEmpty(port.getLoadRegion())){
							 if(loadPortRegion.equals("")){
								 loadPortRegion = port.getLoadRegion() + "";
							 }else{
								 loadPortRegion =loadPortRegion+"/"+ port.getLoadRegion();					 
							 }
						 }
						 if(!StringUtils.isNullOrEmpty(port.getOilTypeCode())){
							 if(oilType.equals("")){
								 String oilTypeTemp = commonService.findNameByCodeAndLang("3", port.getOilTypeCode());
								 if(!"".equals(oilTypeTemp)){
									 oilType = oilTypeTemp;
								 }else{
									 oilType =  port.getOilTypeCode();
								 }
							 }else{
								 String oilTypeTemp = commonService.findNameByCodeAndLang("3", port.getOilTypeCode());
								 if(!"".equals(oilTypeTemp)){
									 oilType = oilType+"/"+oilTypeTemp;
								 }else{
									 oilType = oilType+"/"+port.getOilTypeCode();
								 }
							 }
						 }
					}
				}
			 pallet2.put("loadPortRegion", loadPortRegion);
			 pallet2.put("unloadPort", unloadPort);
			 pallet2.put("loadPort", loadPort);
			 pallet2.put("unloadPortRegion", unloadPortRegion);
			 pallet2.put("quantity", quantity);
			 pallet2.put("oilType",oilType);
		  }
		return palletList;     
	}
	/**
	 * 校验是否租船
	 */
	@Override
	public void checkAgreementIsExsit(String orderCode) {
		Concurrent concurrent = concurrentService.findByPrimaryKey(orderCode);
		if (concurrent != null){
			throw new TransportException(TransportException.TYPE.ITSH532);
		}
	}

	@Override
	public int countRecords2(Map<String, Object> map) {
		return _PalletMapper.countRecords2(map);
	}

	@Override
	public int countRecords3(Map<String, Object> map) {
		return _PalletMapper.countRecords3(map);
	}

	/**********************************************3/31********************************/
	/**
	 * 获取任务数量提示
	 */
	@Override
	public Map<String, Object> getTaskNum(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("traderId", map.get("traderId"));
		map1.put("task", _PalletMapper.getTaskNum(param));
		map1.put("untask", _PalletMapper.getUntaskNum(param));
		return map1;
	}
	
	/***
	 * 租船信息_新增
	 */
	@Override
	@Transactional
	public void insertionPallet(List<PalletPortList> list, Pallet vo, CurrentUser user) {
		// 校验是否有船盘租船(1有0无)
		if(Constants.ORDER_SHIPPALLET_1.equals(vo.getPlateSel())){
			String shipPlateUuid  = vo.getShipPlateUuid();
			if (StringUtils.isNullOrEmpty(shipPlateUuid)) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			ShipPlate shipPlate = shipPlateService.findByUuid(shipPlateUuid);
			vo.setShipPlateId(shipPlate.getShipPlateId());
		} 
		// 校验是否有订单租船(1有0无)
		if (Constants.ORDER_NO_1.equals(vo.getOrderNo())) {
			// 校验必填 订单编号
			String orderCode = vo.getOrderCode();
			if (StringUtils.isNullOrEmpty(orderCode)) {
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			//查询订单详情
			try {
				ForShipOrderInfo sendOrderDetail = iFindShipService.sendOrderDetail(orderCode);
				iFindShipService.generateCharterDemand(orderCode);
				vo.setOilName(sendOrderDetail.getName());
				vo.setOrderPact(sendOrderDetail.getContractNo());
			} catch (BizException e) {
				log.error("查询订单详情接口异常："+e);
				throw new TransportException(e.getCode(),"查询订单详情接口异常："+e.getMessage());
			} catch (Exception e) {
				log.error("查询订单详情接口异常："+e);
				throw new TransportException(TransportException.TYPE.ITSH543);
			}
		}
		//需求类型
		if (StringUtils.isNullOrEmpty(vo.getPalletType())){
			throw new TransportException(TransportException.TYPE.ITSH533);
		}
		if (!Constants.PALLET_TYPE_1.equals(vo.getPalletType()) && !Constants.PALLET_TYPE_2.equals(vo.getPalletType())){
			throw new TransportException(TransportException.TYPE.ITSH534);
		}
		//校验一条订单只能生产一条需求
		if (Constants.ORDER_NO_1.equals(vo.getOrderNo()) && !StringUtils.isNullOrEmpty(vo.getOrderCode())){
			try {
				Concurrent concurrent =new Concurrent();
				concurrent.setId(vo.getOrderCode());
				concurrent.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				concurrent.setCreateDate(DateTimeUtils.currentDate());
				concurrent.setUpdateDate(DateTimeUtils.currentDate());
				concurrentService.insertRecord(concurrent);
			} catch (Exception e) {
				log.error("已生成租船SQL：",e);
				throw new TransportException(TransportException.TYPE.ITSH532);
			}
		}
		//设置企业中英文
		EnterpriseVo byMemberId = commonService.queryUserByEpMemberId(user.getEpMemberId());
		vo.setCompanyName(byMemberId.getFullName());
		vo.setCompanyNameEn(byMemberId.getEnglishName());
		//设置会员公司中英文
		vo.setEpMemberName(byMemberId.getFullName());
		vo.setEpMemberNameEn(byMemberId.getEnglishName());
		
		vo.setPalletCode(SequenceUtils.nextSequence(Constants.PALLET_CODE));
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		String shipAge = vo.getShipAgeBeg()+"-"+vo.getShipAgeEnd();
		vo.setShipAge(shipAge);
		vo.setEpMemberId(user.getEpMemberId());
		vo.setLangVer(Constants.LANG_VER);
		vo.setStatus(Constants.PALLET_STATUS_1);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setCreateUser(user.getMemberId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		BigDecimal quantity = new BigDecimal(0);
		for (PalletPortList Port : list) {
			if(Port.getQuantity() != null){
				quantity = quantity.add(Port.getQuantity());
			}
		}
		vo.setQuantity(quantity);
		String FLoadPortUUID = KeyGenUtils.newUuid();
		vo.setFLoadPort(FLoadPortUUID);
		this.insertRecord(vo);
		boolean flag = true;
		for (PalletPortList Port : list) {
			//油种和装卸港国际化
			PalletPort port = BeanConvertUtils.beanToBean(Port, PalletPort.class);
			if(!StringUtils.isNullOrEmpty(port.getOilType())){
				ValueSetName name = commonService.findNameByCode("3",port.getOilTypeCode());
				if(name.getEnName()==null){
					port.setOilTypeEn(port.getOilType());
				}else{
					port.setOilType(name.getZhName());
					port.setOilTypeEn(name.getEnName());
				}
				if(!StringUtils.isNullOrEmpty(port.getLoadPort())){
					ValueSetName name2 = commonService.findNameByCode("1",port.getLoadPortCode());
					if(name2.getEnName()==null){
						port.setLoadPortEn(port.getLoadPort());
					}else{
						port.setLoadPort(name2.getZhName());
						port.setLoadPortEn(name2.getEnName());
					}
				}
			}else{
				ValueSetName name3 = commonService.findNameByCode("2",port.getUnloadPortCode());
				if(name3.getEnName()==null){
					port.setUnloadPortEn(port.getUnloadPortCode());
				}else{
					port.setUnloadPort(name3.getZhName());
					port.setUnloadPortEn(name3.getEnName());
				}
			}
			port.setQuantity(Port.getQuantity());
			port.setUuid(KeyGenUtils.newUuid());
			port.setCreateDate(DateTimeUtils.currentDate());
			port.setUpdateDate(DateTimeUtils.currentDate());
			port.setLangVer(Constants.LANG_VER);
			port.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			port.setCreateUser(user.getMemberId()); 
			port.setUpdateUser(user.getMemberId()); 
			port.setPalletUuid(vo.getUuid()); 
			port.setPallletId(vo.getPalletId());
			if(flag){
				port.setUuid(FLoadPortUUID);
				flag=false;
			}
			platePortService.insertRecord(port);
		}
	}
	public Map<String, Object> getPallet(Pallet vo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 获取对象
		Map<String, Object> pallet = _PalletMapper.findMapByUuid(vo.getUuid());
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		PalletPort palletport = new PalletPort();
		palletport.setPalletUuid(vo.getUuid());
		List<PalletPort> portList =  platePortService.queryByEntitys(palletport);
		String unloadPort = "";
		String loadPort = "";
		String unloadPortRegion ="";
		String loadPortRegion ="";
		String quantity = "";
		for (PalletPort port : portList) {
			if(port.getQuantity() != null){
				 if(quantity.equals("")){
					 quantity = port.getQuantity() +""+port.getUnitName()+"";
				 }else{
					 quantity = quantity+"/"+ port.getQuantity()+""+port.getUnitName()+"";					 						 
				 }
			 }
			 if(port != null){
				 if(!StringUtils.isNullOrEmpty(port.getUnloadPort())){
					 if(unloadPort.equals("")){
						 unloadPort = port.getUnloadPort() +"";
					 }else{
						 unloadPort = unloadPort+"/"+port.getUnloadPort();//装港|装港区域
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getLoadPort())){
					 if(loadPort.equals("")){
						 loadPort = port.getLoadPort() + "";
					 }else{
						 loadPort = loadPort+"/"+port.getLoadPort();					 
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getUnloadRegion())){
					 if(unloadPortRegion.equals("")){
						 unloadPortRegion = port.getUnloadRegion() + "";
					 }else{
						 unloadPortRegion = unloadPortRegion+"/"+port.getUnloadRegion();					 
					 }
				 }
				 if(!StringUtils.isNullOrEmpty(port.getLoadRegion())){
					 if(loadPortRegion.equals("")){
						 loadPortRegion = port.getLoadRegion() + "";
					 }else{
						 loadPortRegion =loadPortRegion+"/"+ port.getLoadRegion();					 
					 }
				 }
			 }
	    }
		pallet.put("loadPortRegion", loadPortRegion);
		pallet.put("unloadPort", unloadPort);
		pallet.put("loadPort", loadPort);
		pallet.put("unloadPortRegion", unloadPortRegion);
		pallet.put("quantity", quantity);
		
		Object obj = pallet.get("laycanBeg");
		if (obj != null){
			String format = sdf.format((Date)obj);
			pallet.put("laycanBeg", format);
		}
		Object obj2 = pallet.get("laycanEnd");
		if (obj2 != null){
			String format1 = sdf.format((Date)obj2);
			pallet.put("laycanEnd", format1);
		}
		Object obj3 = pallet.get("shipType");
		if(obj3 != null){
			String str = (String)obj3;
			String str1 = str.substring(0, str.length()-1);
			pallet.put("shipType", str1);
		}
		return pallet;
	}
	/*
	 * 指定转租船东
	 */
	public void appointTradertrader(Pallet vo,CurrentUser user) {
		// 租船uuid校验
		if (StringUtils.isNullOrEmpty(vo.getUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Pallet pallet = this.findByUuid(vo.getUuid());
		if(pallet==null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		if("1".equals(pallet.getTraderSel())){
			throw new TransportException(TransportException.TYPE.ITSH018);
		}
		/*try {
			List<EnterpriseVo> list2 = enterpriseService.selectByCredentials(Constants.MEMBER_APTITUDE_7);
			if (list2 != null && !list2.isEmpty()){
				EnterpriseVo enterpriseVo = list2.get(0);
				
				log.info("指定的租船船东为===>"+BeanConvertUtils.beanToMap(enterpriseVo));
				
				pallet.setTraderSel("1");
				pallet.setTraderId(enterpriseVo.getMemberId());
				pallet.setTraderName(enterpriseVo.getFullName());
				this.updateRecord(pallet);
			}
			
		} catch (Exception e) {
			log.error("根据资质查询转租船东接口异常",e);
			throw new TransportException(TransportException.TYPE.ITSH545);
		}*/
		String ids = "";
		Map<String, CodeValue> group = ValueSetUtils.getValuesByGroup(Constants.VALUE_SET_18);
		for (Map.Entry<String, CodeValue> e : group.entrySet()) {
			CodeValue value = e.getValue();
			ids = value.getValue();
			break;
		}
		pallet.setTraderSel("1");
		if (StringUtils.isNullOrEmpty(ids)){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		pallet.setTraderId(Long.valueOf(ids));
		EnterpriseVo byMemberId = commonService.queryUserByEpMemberId(Long.valueOf(ids));
		log.info("指定的租船船东为===>"+BeanConvertUtils.beanToMap(byMemberId));
		if (byMemberId.getMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		pallet.setTraderName(byMemberId.getFullName());
		pallet.setTraderNameEn(byMemberId.getEnglishName());
		this.updateRecord(pallet);
		//发送消息
		messagePushService.messagePush(21, pallet.getPalletId(), user.getMemberId());
	}

	@Override
	@Transactional
	public void cancelOrder(String orderCode, Long memberId) {
		//根据订单编号查询需求是否存在
		Pallet pallet = new Pallet();
		pallet.setOrderCode(orderCode);
		pallet.setOrderNo(Constants.ORDER_NO_1);
		List<Pallet> list = this.queryByEntitys(pallet);
		if (list != null && !list.isEmpty()){
			Pallet pallet2 = list.get(0);
			//修改需求为无订单租船
			pallet2.setOrderNo(Constants.ORDER_NO_0);
			pallet2.setUpdateDate(DateTimeUtils.currentDate());
			pallet2.setUpdateUser(memberId);
			this.updateRecord(pallet2);
			//修改协议为无订单租船
			Agreement agreement = new Agreement();
			agreement.setPalletId(pallet2.getPalletId());
			List<Agreement> list2 = agreementService.queryByEntitys(agreement);
			if (list2 != null && !list2.isEmpty()){
				Agreement agreement2 = list2.get(0);
				agreement2.setOrderNo(Constants.ORDER_NO_0);
				agreement2.setUpdateDate(DateTimeUtils.currentDate());
				agreement2.setUpdateUser(memberId);
				agreementService.updateRecord(agreement2);
				
				//修改运单为无订单租船
				Waybill waybill = new Waybill();
				waybill.setAgreementId(agreement2.getAgreementId());
				List<Waybill> list3 = waybillService.queryByEntitys(waybill);
				if (list3 != null && !list3.isEmpty()){
					
					Waybill waybill2 = list3.get(0);
					waybill2.setOrderNo(Constants.ORDER_NO_0);
					waybill2.setUpdateDate(DateTimeUtils.currentDate());
					waybill2.setUpdateUser(memberId);
					waybillService.updateRecord(waybill2);
					
				}
			}
			//取消租船
			concurrentService.deleteRecordByKey(pallet.getOrderCode());
		}
	}

	/**
	 * 货盘滚动列表
	 */
	@Override
	public List<Map<String, Object>> findPalletList(PalletVO vo) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<Pallet> list = _PalletMapper.findPalletList(vo);
		for (Pallet pallet : list) {
			Map<String, Object> map = getDetail(pallet);
			maps.add(map);
			}
		return maps;
	}

	@Override
	public Page<Map<String, Object>> findMorePalletList(Map<String, Object> maps,
			SimplePageInfo pageInfo) {
		Page<Map<String, Object>> pages = new Page<>();
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		maps.put("lang", lang);
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String, Object>> page =  (Page<Map<String, Object>>) _PalletMapper.findMorePalletList(maps);
		List<Pallet> list = BeanConvertUtils.mapToBeanInList(page, Pallet.class);
		for (Pallet pallet : list) {
			Map<String, Object> map = getDetail(pallet);
			pages.add(map);
			}
		pages.setPageNum(page.getPageNum());
		pages.setPageSize(page.getPageSize());
		pages.setPages(page.getPages());
		pages.setTotal(page.getTotal());
		return pages;
	}

	@Override
	public Map<String, Object> findPalletDetail(PalletVO vo) {

		Pallet pallet = this.findByUuid(vo.getUuid());
		if (pallet == null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		Map<String, Object> map = getDetail(pallet);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String,Object> getDetail (Pallet pallet){
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		Map<String, Object> map = new HashMap<>();
		PalletPort palletPort = new PalletPort();
		palletPort.setPalletUuid(pallet.getUuid());
		List<PalletPort> list2 = platePortService.queryByEntitys(palletPort);
		String quantity = "";
		String load = "";
		String unload = "";
		String loadReg = "";
		String unloadReg = "";
		String laycan = "";
		String createDate = "";
		String status = "";
		String uuid = "";
		List<String> list = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		for (PalletPort port : list2) {
			BigDecimal quantity2 = port.getQuantity();
			
			String unitName = port.getUnitName();
			/*if ("吨".equals(unitName) && "en".equals(lang)){
				unitName="MT";
			} else if ("桶".equals(unitName) && "en".equals(lang)){
				unitName="BBL";
			} else if ("BBL".equals(unitName) && "zh".equals(lang)){
				unitName="桶";
			} else if ("MT".equals(unitName) && "zh".equals(lang)){
				unitName="吨";
			}*/
			if(quantity2 != null){
				NumberFormat format = NumberFormat.getInstance();
				String string = format.format(quantity2);
				 if(quantity.equals("")){
					 quantity = string +""+unitName+"";
				 }else{
					 quantity = quantity+"/"+ string+""+unitName+"";					 						 
				 }
			 }
			if(port != null){
			 if(!StringUtils.isNullOrEmpty(port.getUnloadPort())){
				 Map<String,Object> maps = getPort(unload,port.getUnloadPort(),list3);
					list3 = (List<String>) maps.get("list");
					unload=(String) maps.get("load");
			}
			 if(!StringUtils.isNullOrEmpty(port.getLoadPort())){
				Map<String,Object> maps = getPort(load,port.getLoadPort(),list);
				list = (List<String>) maps.get("list");
				load=(String) maps.get("load");
			 }
		 }
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		createDate = format.format(pallet.getCreateDate());
		uuid=pallet.getUuid();
		Date laycanBeg = pallet.getLaycanBeg();
		Date laycanEnd = pallet.getLaycanEnd();
		//装期格式修改
		String beg = formDate(laycanBeg);
		String end = formDate(laycanEnd);
		laycan=beg+"/"+end;
		//laycan=format.format(laycanBeg)+"/"+format.format(laycanEnd);
		String status2 = pallet.getStatus();
		
		
		switch (status2) {
		case "2":
			if ("en".equals(lang)){
				status=Constants.message_033_en;
			} else {
				status=Constants.message_033_zh;
			}
			break;
		case "3":
			if ("en".equals(lang)){
				status=Constants.message_033_en;
			} else {
				status=Constants.message_033_zh;
			}
			break;
		case "4":
			if ("en".equals(lang)){
				status=Constants.message_034_en;
			} else {
				status=Constants.message_034_zh;
			}
			break;
		default:
			break;
		}
		if (StringUtils.isNullOrEmpty(load)){
			map.put("load",loadReg );
		} else {
			map.put("load",load );
		}
		if (StringUtils.isNullOrEmpty(unload)){
			map.put("unload",unloadReg );
		} else {
			map.put("unload",unload );
		}
		 String epMemberName = "****";
		 /*if (StringUtils.isNullOrEmpty(epMemberName) && pallet.getEpMemberId() != null){
			 try {
				EnterpriseVo vo = commonService.queryUserByEpMemberId(pallet.getEpMemberId());
				 epMemberName=vo.getFullName();
			} catch (Exception e) {
			log.error(e);
			}
		 }*/
		map.put("quantity", quantity);
		map.put("laycan", laycan);
		map.put("status", status);
		map.put("createDate", createDate);
		map.put("uuid", uuid);
		map.put("epMemberName",epMemberName);
		return map;
	}
	
	private String formDate(Date laycanBeg){
		String str = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(laycanBeg);
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH)+1;//获取月份
	    int day=cal.get(Calendar.DATE);//获取日
		String days="";//EARLY上旬，MID中旬，END下旬
		String months="";//Jan,Feb,Mar,Apr,May,June,July,Aug,Sept,Oct,Nov,Dec;
		if (1 <= day && day <= 10){
			days="EARLY";
		} else if (11 <= day && day <= 20){
			days="MID";
		} else if (21 <= day && day <= 31){
			days="END";
		}
		switch (month) {
		case 1:
			months="JAN";
			break;
		case 2:
			months="FEB";
			break;
		case 3:
			months="MAR";
			break;
		case 4:
			months="APR";
			break;
		case 5:
			months="MAY";
			break;
		case 6:
			months="JUNE";
			break;
		case 7:
			months="JULY";
			break;
		case 8:
			months="AUG";
			break;
		case 9:
			months="SEPT";
			break;
		case 10:
			months="OCT";
			break;
		case 11:
			months="NOV";
			break;
		case 12:
			months="DEC";
			break;

		default:
			break;
		}
		str=days+"-"+months+" "+year;
		return str;
		
	}
	
	private Map<String,Object> getPort(String load,String str,List<String> lists){
		Map<String,Object> map = new HashMap<>();
		PortRegion portregion = new PortRegion();
		portregion.setPortName(str);
		List<PortRegion> list = portRegionService.queryByEntitys(portregion );
		if (!list.isEmpty()){
			PortRegion region = list.get(0);
			String string = region.getRegion();
			if (!lists.contains(string)){
				if(load.equals("")){
					load = string + "";
				}else{
					load = load+"/"+string;					 
				}
				lists.add(string);
			}
		}
		map.put("load", load);
		map.put("list", lists);
		return map;
	}
	/*
	 *根据货盘Uuid查询确认详情
	 */
	public Map<String,Object> confirmsDetails(ClauseVO vo){
		//判断语言环境
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Pallet pallet = this.findByUuid(vo.getPalletUuid());
		if (pallet == null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		if("1".equals(pallet.getTraderSel())){
			// 获取报盘信息
 			 List<Map<String, Object>> clauses = clauseService.queryRecords(param);
			 if(clauses==null||clauses.isEmpty()){
				 throw new TransportException(TransportException.TYPE.ITSH602);
			 }
			 Map<String, Object> clause = clauses.get(0);
			if (clause == null) {
				throw new TransportException(TransportException.TYPE.ITSH602);
			}
			// 获取船盘信息
			String shipPlateUuid = (String)clause.get("shipPlateUuid");
			if(StringUtils.isNullOrEmpty(shipPlateUuid)){
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			Map<String, Object> shipPlate = shipPlateService.findMapByUuid(shipPlateUuid);
			if(shipPlate==null){
				throw new TransportException(TransportException.TYPE.ITSH013);
			}
			
			String sysShipUuid = (String)shipPlate.get("sysShipUuid");
			if(StringUtils.isNullOrEmpty(sysShipUuid)){
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			//船舶信息
			SysShip sysShip = sysShipService.findByUuid(sysShipUuid);
			if(sysShip==null){
				throw new TransportException(TransportException.TYPE.ITSH023);
			}
			if ("en".equals(string)){
				//船东中英文
				String shipOwnerDate=(String) shipPlate.get("shipOwnerEn");
				if(!StringUtils.isNullOrEmpty(shipOwnerDate)){
					shipPlate.put("shipOwner",shipOwnerDate);
				}else{
					Object obj=shipPlate.get("shipOwnerId");
					if(obj!=null){
						shipPlate.put("shipOwner",commonService.findNameByEpMemberId((Long)obj));
					}else{
						shipPlate.put("shipOwner","");
					}
				}
			}
			//1货主
			if("1".equals(vo.getRoleFlag())){
				if ("en".equals(string)){
					String  epMemberNameEn = (String) clause.get("epMemberNameEn");
					if(!StringUtils.isNullOrEmpty(epMemberNameEn)){
						map.put("epMemberName",epMemberNameEn);
					}else{
						Object obj=clause.get("epMemberId");
						if(obj!=null){
							Long  epMemberId = (Long)obj;
							map.put("epMemberName",commonService.findNameByEpMemberId(epMemberId));
						}else{
							map.put("epMemberName",epMemberNameEn);
						}
					}
				}else{
					map.put("epMemberName",clause.get("epMemberName"));
				}
			}
			//2二船东
			if("2".equals(vo.getRoleFlag())){
				if ("en".equals(string)){
					String  palletMemberNameEn = (String) clause.get("palletMemberNameEn");
					if(!StringUtils.isNullOrEmpty(palletMemberNameEn)){
						map.put("epMemberName",palletMemberNameEn);
					}else{
						Object obj=clause.get("palletMemberId");
						if(obj!=null){
							Long palletMemberId = (Long) obj;
							map.put("epMemberName",commonService.findNameByEpMemberId(palletMemberId));
						}else{
							map.put("epMemberName","");
						}
					}
				}else{
					map.put("epMemberName",clause.get("palletMemberName"));
				}
			}
			
			map.put("affirm",clause);
			map.put("shipPlate",shipPlate);
			map.put("sysShip", sysShip);
			return map; 
		}else if("0".equals(pallet.getTraderSel())){
			Map<String, Object> affirm = new HashMap<>();
			param.put("palletUuid", vo.getPalletUuid());
			param.put("status", Constants.SHIP_PLATE_STATUS_3);
			List<Map<String, Object>> intentions = intentionService.queryRecords(param);
			 if(intentions==null||intentions.isEmpty()){
				 throw new TransportException(TransportException.TYPE.ITSH405);
			}
			 Map<String, Object> intention = intentions.get(0);
			 affirm.put("uuid",intention.get("uuid"));
			 affirm.put("pactBeg",intention.get("coPactBeg"));
			 affirm.put("pactEnd",intention.get("coPactEnd"));
			 affirm.put("minQuantity",intention.get("coMinQuantity"));
			 affirm.put("loadRegion",intention.get("coLoadRegion"));
			 affirm.put("unloadRegion",intention.get("coUnloadRegion"));
			 affirm.put("ws",intention.get("coWs"));
			 affirm.put("demurrage",intention.get("coDemurrage"));
			 affirm.put("dockTime", intention.get("coDockTime"));
			 affirm.put("pactSpeed", intention.get("coPactSpeed"));
			 affirm.put("commission", intention.get("coCommission"));
			 affirm.put("remark", intention.get("coRemark"));
			// 获取船盘信息
			String shipPlateUuid = (String)intention.get("shipPlateUuid");
			if(StringUtils.isNullOrEmpty(shipPlateUuid)){
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			Map<String, Object> shipPlate = shipPlateService.findMapByUuid(shipPlateUuid);
			if(shipPlate==null){
				throw new TransportException(TransportException.TYPE.ITSH013);
			}
			String sysShipUuid = (String)shipPlate.get("sysShipUuid");
			if(StringUtils.isNullOrEmpty(sysShipUuid)){
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			//船舶信息
			SysShip sysShip = sysShipService.findByUuid(sysShipUuid);
			if(sysShip==null){
				throw new TransportException(TransportException.TYPE.ITSH023);
			}
			//判断角色
			//1货主
			Long epMemberId = null;
			if("1".equals(vo.getRoleFlag())){
			epMemberId = pallet.getEpMemberId();
			}
			//0二船东
			if("2".equals(vo.getRoleFlag())){
			epMemberId = (Long)shipPlate.get("epMemberId");
			}
			EnterpriseVo  epMember = commonService.queryUserByEpMemberId(epMemberId);
			map.put("epMemberName",epMember.getFullName());
			map.put("affirm",affirm);
			map.put("shipPlate",shipPlate);
			map.put("sysShip", sysShip);
	}
		map.put("pallet", pallet);
		return map;
	}
	/**
	 * om租船需求列表
	 */
	@Override
	public Page<Map<String, Object>> queryPallets(Map<String,Object> map,SimplePageInfo simplePageInfo) {
		Page<Map<String, Object>> palletList = this.queryPalletOm(map,simplePageInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> pallet : palletList) {
				String orderNo = (String) pallet.get("orderNo");
				if("1".equals(orderNo)){
					pallet.put("orderNoText","有订单租船");
				}else if("0".equals(orderNo)){
					pallet.put("orderNoText","无订单租船");
				}else{
					pallet.put("orderNoText","");
				}
				Object obj = pallet.get("laycanBeg");
				if (obj != null){
					String format = sdf.format((Date)obj);
					pallet.put("laycanBeg", format);
				}
				Object obj2 = pallet.get("laycanEnd");
				if (obj2 != null){
					String format1 = sdf.format((Date)obj2);
					pallet.put("laycanEnd", format1);
				}
				Object obj3 = pallet.get("shipType");
				if(obj3 != null){
					String str = (String)obj3;
					String str1 = str.substring(0, str.length()-1);
					String str2 = str1.replace(";", "/");
					pallet.put("shipType", str2);
				}
				
		    String palletUuid= (String) pallet.get("uuid");
		    PalletPort palletport = new PalletPort();
		    palletport.setPalletUuid(palletUuid);
		    List<PalletPort> portList =  platePortService.queryByEntitys(palletport);
			String unloadPort = "";
			String loadPort = "";
			String unloadPortRegion ="";
			String loadPortRegion ="";
			String quantity = "";
			String oilType = "";
			for (PalletPort port : portList) {
				if(port.getQuantity() != null){
					 if(quantity.equals("")){
						 quantity = port.getQuantity() +""+port.getUnitName()+"";
					 }else{
						 quantity = quantity+"/"+ port.getQuantity()+""+port.getUnitName()+"";					 						 
					 }
				 }
				 if(port != null){
					 if(!StringUtils.isNullOrEmpty(port.getUnloadPort())){
						 if(unloadPort.equals("")){
							 unloadPort = port.getUnloadPort() +"";
						 }else{
							 unloadPort = unloadPort+"/"+port.getUnloadPort();//装港|装港区域
						 }
					 }
					 if(!StringUtils.isNullOrEmpty(port.getLoadPort())){
						 if(loadPort.equals("")){
							 loadPort = port.getLoadPort() + "";
						 }else{
							 loadPort = loadPort+"/"+port.getLoadPort();					 
						 }
					 }
					 if(!StringUtils.isNullOrEmpty(port.getUnloadRegion())){
						 if(unloadPortRegion.equals("")){
							 unloadPortRegion = port.getUnloadRegion() + "";
						 }else{
							 unloadPortRegion = unloadPortRegion+"/"+port.getUnloadRegion();					 
						 }
					 }
					 if(!StringUtils.isNullOrEmpty(port.getLoadRegion())){
						 if(loadPortRegion.equals("")){
							 loadPortRegion = port.getLoadRegion() + "";
						 }else{
							 loadPortRegion =loadPortRegion+"/"+ port.getLoadRegion();					 
						 }
					 }
					 if(!StringUtils.isNullOrEmpty(port.getOilType())){
						 if(oilType.equals("")){
							 oilType = port.getOilType() +"";
						 }else{
							 oilType = oilType+"/"+port.getOilType();//油种
						 }
					 }
				}
			}
			 pallet.put("loadPortRegion", loadPortRegion);
			 pallet.put("unloadPort", unloadPort);
			 pallet.put("loadPort", loadPort);
			 pallet.put("unloadPortRegion", unloadPortRegion);
			 pallet.put("quantity", quantity);
			 pallet.put("oilType",oilType);
		}
		return palletList;
	}
	/**
	 * om租船需求分页
	 */
	private Page<Map<String, Object>> queryPalletOm(Map<String,Object> map,SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>)_PalletMapper.queryPalletOm(map);
	}
}