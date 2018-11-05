package com.sinochem.crude.trade.transport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.FindedShipInfoVO;
import com.sinochem.crude.trade.orderexecute.remote.IShipTransportService;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.AgreementMapper;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.AgreementVO;
import com.sinochem.crude.trade.transport.model.res.AgreementRes;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.WaybillService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class AgreementServiceImpl implements AgreementService {
	Log log = LogFactory.getLog(AgreementServiceImpl.class);
	@Autowired
	private AgreementMapper _AgreementMapper;
	@Autowired
	private PalletService palletService;
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private SysShipService sysShipService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IntentionService intentionService;
	@Autowired
	private ClauseService clauseService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ShipPlateService shipPlateService;
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private IShipTransportService iShipTransportService;
	
	
	public AgreementMapper getMapper() {
		return _AgreementMapper;
	}

	/**
	 * 根据对象-查询对象列表
	 */
	public List<Agreement> queryByEntitys(Agreement agreement) {
		return _AgreementMapper.queryByEntitys(agreement);
	}

	/**
	 * 根据主键-查询对象
	 */
	public Agreement findByPrimaryKey(Long agreementId) {
		return _AgreementMapper.findByPrimaryKey(agreementId);
	}

	/**
	 * 根据UUID-查询对象
	 */
	public Agreement findByUuid(String uuid) {
		Agreement agreement = _AgreementMapper.findByUuid(uuid);
		if (agreement == null) {
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		return agreement;
	}

	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Agreement agreement) {
		_AgreementMapper.updateRecord(agreement);
	}

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long agreementId, Long deleteUser) {
		_AgreementMapper.deleteRecordByKey(agreementId, deleteUser);
	}

	/**
	 * 新增
	 */
	public void insertRecord(Agreement agreement) {
		_AgreementMapper.insertRecord(agreement);
	}

	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return _AgreementMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map,
			SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(),
				pageInfo.getCount());
		return (Page<Map<String, Object>>) _AgreementMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map) {
		return _AgreementMapper.countRecords(map);
	}

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) {
		_AgreementMapper.deleteRecords(map);
	}

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AgreementMapper.updateRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/

	/**
	 * 生成代理协议
	 */
	@Override
	@Transactional
	public void saveAgreement(AgreementVO vo, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		//必填校验
		if (StringUtils.isNullOrEmpty(vo.getFirParty())) {
			throw new TransportException(TransportException.TYPE.ITSH021);
		} else if (StringUtils.isNullOrEmpty(vo.getSecParty())) {
			throw new TransportException(TransportException.TYPE.ITSH022);
		} else if (StringUtils.isNullOrEmpty(vo.getShipName())
				|| StringUtils.isNullOrEmpty(vo.getSysShipUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH023);
		} else if (vo.getSignDate() == null) {
			throw new TransportException(TransportException.TYPE.ITSH024);
		}

		Pallet pallet = palletService.findByUuid(vo.getPalletUuid());
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		
		//校验货盘状态（只有已发布才可生成代理协议）
		if (!Constants.PALLET_STATUS_1.equals(pallet.getStatus())) {
			throw new TransportException(TransportException.TYPE.ITSH018);
		}
		if (user.getEpMemberId() == null
				|| !pallet.getTraderId().equals(user.getEpMemberId())) {
			throw new TransportException(TransportException.TYPE.ITSH546);
		}

		
		//代理协议编号（船名+委托方英文简称+日期+油种）
		String oilType = vo.getOilType();
		String oil = "";
		if (!StringUtils.isNullOrEmpty(oilType)) {
			String[] strings = oilType.split("/");
			for (String str : strings) {
				if (!StringUtils.isNullOrEmpty(str)) {
					String[] split = str.split(" ");
					if (split.length > 0) {
						String string = split[0];
						oil = oil + string + "/";
					}
				}
			}
			if (oil.length() > 0) {
				oil = oil.substring(0, oil.length() - 1);
			}
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String dateString = formatter.format(vo.getSignDate());
		
		
		//查询委托方英文简称
		String name = this.queryEngName(pallet.getEpMemberId());
		String code = vo.getShipName() + "/" + name + "/" + dateString + "/"+ oil;
		
		
		//修改货盘状态为已生成代理协议
		pallet.setStatus(Constants.PALLET_STATUS_2);
		pallet.setAgreementCode(code);
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		pallet.setUpdateUser(user.getMemberId());
		palletService.updateRecord(pallet);
		
		//查询船舶是否存在
		SysShip sysShip = sysShipService.findByUuid(vo.getSysShipUuid());
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH025);
		}
		
		
		/**
		 * 设置中英文
		 */
		//公司信息
		EnterpriseVo enterpriseVo = commonService.queryUserByEpMemberId(user.getEpMemberId());
		vo.setEpMemberId(user.getEpMemberId());
		vo.setEpMemberName(enterpriseVo.getFullName());
		vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		//租船人信息
		if(vo.getCarrierId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getCarrierId());
			vo.setCarrierName(enterVo.getFullName());
			vo.setCarrierNameEn(enterVo.getEnglishName());
		}
		//船东信息
		if(vo.getShipOwnerId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getShipOwnerId());
			vo.setShipOwner(enterVo.getFullName());
			vo.setShipOwnerEn(enterVo.getEnglishName());
		}
		//经纪人
		if(vo.getBrokerId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getBrokerId());
			vo.setBrokerName(enterVo.getFullName());
			vo.setBrokerNameEn(enterVo.getEnglishName());
		}
		//装港
		if(!StringUtils.isNullOrEmpty(vo.getLoadPortCode())){
			String loadPort="";
			String loadPortEn="";
			String loadPortCodes = vo.getLoadPortCode();
			String[] loadPortCodeArr = loadPortCodes.split("/");
			for (String loadPortCode : loadPortCodeArr) {
				if(!StringUtils.isNullOrEmpty(loadPortCode)){
					ValueSetName valueSetName = commonService.findNameByCode("1", loadPortCode);		/**（1装港2卸港3油种）*/
					if(!StringUtils.isNullOrEmpty(valueSetName.getZhName()) && !StringUtils.isNullOrEmpty(valueSetName.getEnName())){
						//非自定义
						loadPort+=valueSetName.getZhName()+"/";
						loadPortEn+=valueSetName.getEnName()+"/";
					}else{
						//自定义
						loadPort+=loadPortCode+"/";
						loadPortEn+=loadPortCode+"/";
					}
				}
			}
			vo.setLoadPort(loadPort);
			vo.setLoadPortEn(loadPortEn);
		}
		//卸港
		if(!StringUtils.isNullOrEmpty(vo.getUnloadPortCode())){
			String unloadPort="";
			String unloadPortEn="";
			String unloadPortCodes = vo.getUnloadPortCode();
			String[] unloadPortCodeArr = unloadPortCodes.split("/");
			for (String unloadPortCode : unloadPortCodeArr) {
				if(!StringUtils.isNullOrEmpty(unloadPortCode)){
					ValueSetName valueSetName = commonService.findNameByCode("2", unloadPortCode);
					if(!StringUtils.isNullOrEmpty(valueSetName.getZhName()) && !StringUtils.isNullOrEmpty(valueSetName.getEnName())){
						//非自定义
						unloadPort+=valueSetName.getZhName()+"/";
						unloadPortEn+=valueSetName.getEnName()+"/";
					}else{
						//自定义
						unloadPort+=unloadPortCode+"/";
						unloadPortEn+=unloadPortCode+"/";
					}
				}
			}
			vo.setUnloadPort(unloadPort);
			vo.setUnloadPortEn(unloadPortEn);
		}
		//油种
		if(!StringUtils.isNullOrEmpty(vo.getOilTypeCode())){
			String oilType2="";
			String oilTypeEn="";
			String oilTypeCodes = vo.getOilTypeCode();
			String[] oilTypeCodeArr = oilTypeCodes.split("/");
			for (String oilTypeCode : oilTypeCodeArr) {
				if(!StringUtils.isNullOrEmpty(oilTypeCode)){
					ValueSetName valueSetName = commonService.findNameByCode("3", oilTypeCode);
					if(!StringUtils.isNullOrEmpty(valueSetName.getZhName()) && !StringUtils.isNullOrEmpty(valueSetName.getEnName())){
						//非自定义
						oilType2+=valueSetName.getZhName()+"/";
						oilTypeEn+=valueSetName.getEnName()+"/";
					}else{
						//自定义
						oilType2+=oilTypeCode+"/";
						oilTypeEn+=oilTypeCode+"/";
					}
				}
			}
			vo.setOilType(oilType2);
			vo.setOilTypeEn(oilTypeEn);
		}
		
		//数量，根据语言环境设置 quantity,quantityEn
		if(!StringUtils.isNullOrEmpty(vo.getQuantity())){
			if ("en".equals(lang)){
				//若英文，设置 quantity,quantityEn
				String quantitys="";
				vo.setQuantityEn(vo.getQuantity());									/**设置 quantityEn*/
				String quantityEn = vo.getQuantityEn();
				String[] quantityArr = quantityEn.split("/");
				for (String quantity : quantityArr) {
					if(!StringUtils.isNullOrEmpty(quantity)){
						if(quantity.contains(Constants.VALUE_SET_20)){				/**BBL*/
							//获取数量+单位
							int index = quantity.indexOf(Constants.VALUE_SET_20);
							String quantityStr = quantity.substring(0, index);		/**数量*/
							String unitStr = Constants.VALUE_SET_19;				/**单位,吨(中文)*/
							quantitys+=quantityStr+unitStr+"/";
							
						}else if(quantity.contains(Constants.VALUE_SET_22)){		/**MT*/
							//获取数量+单位
							int index = quantity.indexOf(Constants.VALUE_SET_22);
							String quantityStr = quantity.substring(0, index);		/**数量*/
							String unitStr = Constants.VALUE_SET_21;				/**单位,桶(中文)*/
							quantitys+=quantityStr+unitStr+"/";
						}
					}
				}
				vo.setQuantity(quantitys);											/**设置 quantity*/
			} else {
				//若中文，设置 quantityEn
				String quantityEn="";
				String quantitys = vo.getQuantity();
				String[] quantityArr = quantitys.split("/");
				for (String quantity : quantityArr) {
					if(!StringUtils.isNullOrEmpty(quantity)){
						String quantityStr=quantity.substring(0, quantity.length()-1);					/**获取数量，如：100桶*/
						String unitStr=quantity.substring(quantity.length()-1, quantity.length());		/**获取单位，如：100桶*/
						String unitStrEn="";
						//判断单位
						if(!StringUtils.isNullOrEmpty(unitStr) && Constants.VALUE_SET_19.equals(unitStr)){			/**吨（中）*/
							unitStrEn=Constants.VALUE_SET_20;
						}else if(!StringUtils.isNullOrEmpty(unitStr) && Constants.VALUE_SET_21.equals(unitStr)){	/**桶（中）*/
							unitStrEn=Constants.VALUE_SET_22;
						}
						quantityEn+=quantityStr+unitStrEn+"/";
					}
				}
				vo.setQuantityEn(quantityEn);
			} 
		}
		
		vo.setAgreementCode(code);
		vo.setOrderCode(pallet.getOrderCode());
		vo.setOrderNo(pallet.getOrderNo());
		vo.setSysShipId(sysShip.getSysShipId());
		vo.setPalletId(pallet.getPalletId());
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setCreateUser(user.getMemberId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setStatus(Constants.AGREEMENT_STATUS_1);
		
		this.insertRecord(vo);
	}

	/**
	 * 生成代理协议
	 * @throws BizException 
	 */
	@Override
	@Transactional
	public void agreementSave(AgreementVO vo, CurrentUser user) throws BizException {
				
		ShipPlate plate = new ShipPlate();
		plate.setUuid(vo.getShipPlateUuid());
		plate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlateService.updateRecords(BeanConvertUtils.beanToMap(plate));
		
		//船合同列表
		ShipPact pact = new ShipPact();
		pact.setShipPlateUuid(vo.getShipPlateUuid());
		List<ShipPact> list = shipPactService.queryByEntitys(pact);
		
		//船盘
		ShipPlate shipPlate = shipPlateService.findByUuid(vo.getShipPlateUuid());
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		//船舶
		SysShip sys = sysShipService.findByUuid(vo.getSysShipUuid());
		if (sys == null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		
		String palletUuid = "";
		String loadReg = "";
		String unloadReg = "";
		if ("1".equals(vo.getType())) {
			// 获取询盘信息
			Intention intention = intentionService.findByUuid(vo.getUuid());
			if (intention == null){
				throw new TransportException(TransportException.TYPE.ITSH405);
			}
			vo.setShipIntentionId(intention.getIntentionId());
			vo.setShipIntentionUuid(intention.getUuid());
			loadReg = intention.getCoLoadRegion();
			unloadReg = intention.getCoUnloadRegion();
			palletUuid = intention.getPalletUuid();
		} else {
			//报盘
			Clause clause = clauseService.findByUuid(vo.getUuid());
			if (clause == null){
				throw new TransportException(TransportException.TYPE.ITSH602);
			}
			vo.setClauseId(clause.getClauseId());
			vo.setClauseUuid(clause.getUuid());
			loadReg = clause.getLoadRegion();
			unloadReg = clause.getUnloadRegion();
			palletUuid = clause.getPalletUuid();
		}
		
		
		String shipPactUuid = "";
		String shipPactCode = "";
		Long  shipPactId ;
		if (list == null || list.isEmpty()) {
			//如果list为空说明没有合同(没有合同新增一条)
			String newUuid = KeyGenUtils.newUuid();
			ShipPact ship = insertFn(vo, shipPlate, newUuid, user, sys.getMmsi(),loadReg,unloadReg);
			shipPactUuid = newUuid ;
			shipPactId = ship.getShipPactId();
			shipPactCode = ship.getPactCode();
		} else {
			//已有租船合同
			ShipPact shipPact = list.get(0);
			shipPactUuid = shipPact.getUuid();
			shipPactId = shipPact.getShipPactId();
			shipPactCode = shipPact.getPactCode();
		}
		
		//租船需求
		Pallet pallet = palletService.findByUuid(palletUuid);
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		if ("1".equals(pallet.getAgreementFlag())){
			throw new TransportException(TransportException.TYPE.ITSH560);
		}
		// 代理协议编号（船名+委托方英文简称+日期+油种）
		String code = getCode(vo.getSignDate(), vo.getOilType(), pallet.getEpMemberId(), vo.getShipName());
		// 修改需求为已生成协议标志
		pallet.setAgreementFlag("1");
		pallet.setAgreementCode(code);
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		pallet.setUpdateUser(user.getMemberId());
		palletService.updateRecord(pallet);

		
		
		//=================新增协议=================
		/**
		 * 设置中英文
		 */
		//公司信息
		EnterpriseVo enterpriseVo = commonService.queryUserByEpMemberId(user.getEpMemberId());
		vo.setEpMemberId(user.getEpMemberId());
		vo.setEpMemberName(enterpriseVo.getFullName());
		vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		//租船人信息
		if(vo.getCarrierId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getCarrierId());
			vo.setCarrierName(enterVo.getFullName());
			vo.setCarrierNameEn(enterVo.getEnglishName());
		}
		//船东信息
		if(vo.getShipOwnerId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getShipOwnerId());
			vo.setShipOwner(enterVo.getFullName());
			vo.setShipOwnerEn(enterVo.getEnglishName());
		}
		//经纪人
		if(vo.getBrokerId()!=null){
			EnterpriseVo enterVo = commonService.queryUserByEpMemberId(vo.getBrokerId());
			vo.setBrokerName(enterVo.getFullName());
			vo.setBrokerNameEn(enterVo.getEnglishName());
		}
		//装港
		if(!StringUtils.isNullOrEmpty(vo.getLoadPortCode())){
			String loadPort="";
			String loadPortEn="";
			String loadPortCodes = vo.getLoadPortCode();
			String[] loadPortCodeArr = loadPortCodes.split("/");
			for (String loadPortCode : loadPortCodeArr) {
				if(!StringUtils.isNullOrEmpty(loadPortCode)){
					ValueSetName valueSetName = commonService.findNameByCode("1", loadPortCode);		/**（1装港2卸港3油种）*/
					String en=valueSetName.getEnName();
					String zh=valueSetName.getZhName();
					if(!StringUtils.isNullOrEmpty(en) && !StringUtils.isNullOrEmpty(zh)){
						loadPort+=zh+"/";
						loadPortEn+=en+"/";
					}else{
						//为自定义，中英文都存code
						loadPort+=loadPortCode+"/";
						loadPortEn+=loadPortCode+"/";
					}
				}
			}
			vo.setLoadPort(loadPort);
			vo.setLoadPortEn(loadPortEn);
		}
		//卸港
		if(!StringUtils.isNullOrEmpty(vo.getUnloadPortCode())){
			String unloadPort="";
			String unloadPortEn="";
			String unloadPortCodes = vo.getUnloadPortCode();
			String[] unloadPortCodeArr = unloadPortCodes.split("/");
			for (String unloadPortCode : unloadPortCodeArr) {
				if(!StringUtils.isNullOrEmpty(unloadPortCode)){
					ValueSetName valueSetName = commonService.findNameByCode("2", unloadPortCode);
					String en=valueSetName.getEnName();
					String zh=valueSetName.getZhName();
					if(!StringUtils.isNullOrEmpty(en) && !StringUtils.isNullOrEmpty(zh)){
						unloadPort+=zh+"/";
						unloadPortEn+=en+"/";
					}else{
						//为自定义，中英文都存code
						unloadPort+=unloadPortCode+"/";
						unloadPortEn+=unloadPortCode+"/";
					}
				}
			}
			vo.setUnloadPort(unloadPort);
			vo.setUnloadPortEn(unloadPortEn);
		}
		//油种
		if(!StringUtils.isNullOrEmpty(vo.getOilTypeCode())){
			String oilType2="";
			String oilTypeEn="";
			String oilTypeCodes = vo.getOilTypeCode();
			String[] oilTypeCodeArr = oilTypeCodes.split("/");
			for (String oilTypeCode : oilTypeCodeArr) {
				if(!StringUtils.isNullOrEmpty(oilTypeCode)){
					ValueSetName valueSetName = commonService.findNameByCode("3", oilTypeCode);
					String en=valueSetName.getEnName();
					String zh=valueSetName.getZhName();
					if(!StringUtils.isNullOrEmpty(en) && !StringUtils.isNullOrEmpty(zh)){
						oilType2+=zh+"/";
						oilTypeEn+=en+"/";
					}else{
						//为自定义，中英文都存code
						oilType2+=oilTypeCode+"/";
						oilTypeEn+=oilTypeCode+"/";
					}
				}
			}
			vo.setOilType(oilType2);
			vo.setOilTypeEn(oilTypeEn);
		}
		
		
		
		//设置数量
		vo.setQuantityEn(vo.getQuantity());
		
		
		//数量，根据语言环境设置 quantity,quantityEn
		/*if(!StringUtils.isNullOrEmpty(vo.getQuantity())){
			if ("en".equals(lang)){
				//若英文，设置 quantity,quantityEn
				String quantitys="";
				vo.setQuantityEn(vo.getQuantity());									*//**设置 quantityEn*//*
				String quantityEn = vo.getQuantityEn();
				String[] quantityArr = quantityEn.split("/");
				for (String quantity : quantityArr) {
					if(!StringUtils.isNullOrEmpty(quantity)){
						if(quantity.contains(Constants.VALUE_SET_20)){				*//**BBL*//*
							//获取数量+单位
							int index = quantity.indexOf(Constants.VALUE_SET_20);
							String quantityStr = quantity.substring(0, index);		*//**数量*//*
							String unitStr = Constants.VALUE_SET_19;				*//**单位,吨(中文)*//*
							quantitys+=quantityStr+unitStr+"/";
							
						}else if(quantity.contains(Constants.VALUE_SET_22)){		*//**MT*//*
							//获取数量+单位
							int index = quantity.indexOf(Constants.VALUE_SET_22);
							String quantityStr = quantity.substring(0, index);		*//**数量*//*
							String unitStr = Constants.VALUE_SET_21;				*//**单位,桶(中文)*//*
							quantitys+=quantityStr+unitStr+"/";
						}
					}
				}
				vo.setQuantity(quantitys);											*//**设置 quantity*//*
			} else {
				//若中文，设置 quantityEn
				String quantityEn="";
				String quantitys = vo.getQuantity();
				String[] quantityArr = quantitys.split("/");
				for (String quantity : quantityArr) {
					if(!StringUtils.isNullOrEmpty(quantity)){
						String quantityStr=quantity.substring(0, quantity.length()-1);					*//**获取数量，如：100桶*//*
						String unitStr=quantity.substring(quantity.length()-1, quantity.length());		*//**获取单位，如：100桶*//*
						String unitStrEn="";
						//判断单位
						if(!StringUtils.isNullOrEmpty(unitStr) && Constants.VALUE_SET_19.equals(unitStr)){			*//**吨（中）*//*
							unitStrEn=Constants.VALUE_SET_20;
						}else if(!StringUtils.isNullOrEmpty(unitStr) && Constants.VALUE_SET_21.equals(unitStr)){	*//**桶（中）*//*
							unitStrEn=Constants.VALUE_SET_22;
						}
						quantityEn+=quantityStr+unitStrEn+"/";
					}
				}
				vo.setQuantityEn(quantityEn);
			} 
		}*/
		
		vo.setSysShipId(sys.getSysShipId());
		vo.setPalletId(pallet.getPalletId());
		vo.setPalletUuid(pallet.getUuid());
		vo.setOrderNo(pallet.getOrderNo());
		vo.setOrderCode(pallet.getOrderCode());
		vo.setShipPactUuid(shipPactUuid);
		vo.setShipPactId(shipPactId);
		vo.setAgreementCode(code);
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setCreateUser(user.getMemberId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setEpMemberId(user.getEpMemberId());
		vo.setStatus(Constants.AGREEMENT_STATUS_1);
		this.insertRecord(vo);
		
		//同步执行已租船
		if(vo.getOrderNo()!=null && "1".equals(vo.getOrderNo())){	/**有订单租船*/
			FindedShipInfoVO findedShipInfoVO=new FindedShipInfoVO();
			findedShipInfoVO.setArgeementUuid(vo.getUuid());
			findedShipInfoVO.setName(sys.getName());
			findedShipInfoVO.setOrderNo(vo.getOrderCode());
			findedShipInfoVO.setType(sys.getType());
			iShipTransportService.syncShipInfo(findedShipInfoVO, user.getMemberId());
		}
		
		// 添加运单
		Waybill bill = new Waybill();
		BeanUtils.copyProperties(vo, bill);
		bill.setUuid(KeyGenUtils.newUuid());
		bill.setWaybillCode(SequenceUtils.nextSequence(Constants.WAYBILL_CODE));
		bill.setPactCode(shipPactCode);
		bill.setAgreementUuid(vo.getUuid());
		bill.setOiiType(vo.getOilType());
		waybillService.insertRecord(bill);
		
		//发送消息
		messagePushService.messagePush(6, vo.getAgreementId(), user.getMemberId());
	}

	/**
	 * 查询委托方英文名
	 * 
	 * @param epMemberId
	 * @return
	 */
	private String queryEngName(Long epMemberId) {
		String name = "";
		try {
			EnterpriseVo enterpriseVo = enterpriseService
					.getByMemberId(epMemberId);
			if (enterpriseVo != null) {
				log.info("########################代理企业英文简称："
						+ enterpriseVo.getAbbEnglishName());
				if (!StringUtils
						.isNullOrEmpty(enterpriseVo.getAbbEnglishName())) {
					name = enterpriseVo.getAbbEnglishName();
				}
			}
		} catch (Exception e) {
			log.error("查询委托方英文简称报错", e);
		}
		return name;
	}

	/**
	 * 修改代理协议
	 */
	@Override
	public void updateAgreement(AgreementVO vo, CurrentUser user) {
		Agreement agreement = this.findByUuid(vo.getUuid());
		if (agreement == null) {
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		// 必填校验
		if (StringUtils.isNullOrEmpty(vo.getFirParty())) {
			throw new TransportException(TransportException.TYPE.ITSH021);
		} else if (StringUtils.isNullOrEmpty(vo.getSecParty())) {
			throw new TransportException(TransportException.TYPE.ITSH022);
		} else if (StringUtils.isNullOrEmpty(vo.getShipName())
				|| StringUtils.isNullOrEmpty(vo.getSysShipUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH023);
		} else if (vo.getSignDate() == null) {
			throw new TransportException(TransportException.TYPE.ITSH024);
		}
		if (!Constants.AGREEMENT_STATUS_1.equals(agreement.getStatus())) {
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		
		// 查询船舶是否存在
		SysShip sysShip = sysShipService.findByUuid(vo.getSysShipUuid());
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH025);
		}
		Pallet pallet = palletService.findByUuid(vo.getPalletUuid());
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		
		// 代理协议编号（船名+委托方英文简称+日期+油种）
		String oilType = vo.getOilType();
		String oil = "";
		if (!StringUtils.isNullOrEmpty(oilType)) {
			String[] strings = oilType.split("/");
			for (String str : strings) {
				if (!StringUtils.isNullOrEmpty(str)) {
					String[] split = str.split(" ");
					if (split.length > 0) {
						String string = split[0];
						oil = oil + string + "/";
					}
				}
			}
			if (oil.length() > 0) {
				oil = oil.substring(0, oil.length() - 1);
			}
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String dateString = formatter.format(vo.getSignDate());
		
		// 查询委托方英文简称
		String name = this.queryEngName(pallet.getEpMemberId());
		String code = vo.getShipName() + "/" + name + "/" + dateString + "/"+ oil;
		
		
		agreement.setAgreementCode(code);
		agreement.setLoadPort(vo.getLoadPort());
		agreement.setOilType(vo.getOilType());
		agreement.setQuantity(vo.getQuantity());
		agreement.setUnloadPort(vo.getUnloadPort());
		agreement.setUpdateDate(DateTimeUtils.currentDate());
		agreement.setUpdateUser(user.getMemberId());
		agreement.setFirParty(vo.getFirParty());
		agreement.setSecParty(vo.getSecParty());
		agreement.setSignDate(vo.getSignDate());
		agreement.setShipName(vo.getShipName());
		agreement.setSysShipId(sysShip.getSysShipId());
		agreement.setSysShipUuid(vo.getSysShipUuid());
		agreement.setAccessory(vo.getAccessory());
		agreement.setOilTypeCopy(vo.getOilTypeCopy());
		agreement.setLoadPortCopy(vo.getLoadPortCopy());
		agreement.setUnloadPortCopy(vo.getUnloadPortCopy());
		agreement.setAccessoryPath(vo.getAccessoryPath());
		this.updateRecord(agreement);
	}

	/**
	 * 查询代理协议详情
	 */
	@Override
	public AgreementRes findAgreementDetail(String uuid) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		AgreementRes res = new AgreementRes();
		Agreement agreement = this.findByUuid(uuid);
		
		if (agreement == null) {
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		
		//根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
		if ("en".equals(lang)){
			//epMemberName
			if(!StringUtils.isNullOrEmpty(agreement.getEpMemberNameEn())){
				agreement.setEpMemberName(agreement.getEpMemberNameEn());
			}else if(agreement.getEpMemberId()!=null){
				agreement.setEpMemberName(commonService.findNameByEpMemberId(agreement.getEpMemberId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(agreement.getCarrierNameEn())){
				agreement.setCarrierName(agreement.getCarrierNameEn());
			}else if(agreement.getCarrierId()!=null){
				agreement.setCarrierName(commonService.findNameByEpMemberId(agreement.getCarrierId()));
			}
			//shipOwner
			if(!StringUtils.isNullOrEmpty(agreement.getShipOwnerEn())){
				agreement.setShipOwner(agreement.getShipOwnerEn());
			}else if(agreement.getShipOwnerId()!=null){
				agreement.setShipOwner(commonService.findNameByEpMemberId(agreement.getShipOwnerId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(agreement.getBrokerNameEn())){
				agreement.setBrokerName(agreement.getBrokerNameEn());
			}else if(agreement.getBrokerId()!=null){
				agreement.setBrokerName(commonService.findNameByEpMemberId(agreement.getBrokerId()));
			}
		}
		
		BeanUtils.copyProperties(agreement, res);

		Map<String, Object> map = new HashMap<>();
		
		//查询港口数据
		String laycan = agreement.getLaycan();
		String loadPort = agreement.getLoadPort();
		String unloadPort = agreement.getUnloadPort();
		String oilType = agreement.getOilType();
		String quantity = agreement.getQuantity();
		
		if (!StringUtils.isNullOrEmpty(laycan)) {
			res.setLaycan(laycan.replaceAll("/", "--"));
		}
		if (!StringUtils.isNullOrEmpty(loadPort)) {
			res.setLoadPort(loadPort.substring(0, loadPort.length() - 1));
		}
		if (!StringUtils.isNullOrEmpty(unloadPort)) {
			res.setUnloadPort(unloadPort.substring(0, unloadPort.length() - 1));
		}
		if (!StringUtils.isNullOrEmpty(oilType)) {
			res.setOilType(oilType.substring(0, oilType.length() - 1));
		}
		if (!StringUtils.isNullOrEmpty(quantity)) {
			res.setQuantity(quantity.substring(0, quantity.length() - 1));
		}
		if (!StringUtils.isNullOrEmpty(laycan)) {
			String[] split = laycan.split("/");
			if (split.length >= 2) {
				map.put("pactBeg", split[0]);
				map.put("pactEnd", split[1]);
			}
		}

		
		/**
		 * 处理装港、卸港、油种、数量中英文
		 */
		List<Map<String, Object>> list = new ArrayList<>();
		String loadPortCode = agreement.getLoadPortCode();
		String unloadPortCode1 = agreement.getUnloadPortCode();
		String oilTypeCode = agreement.getOilTypeCode();
		String quantityStr=null;
		
		if ("en".equals(lang)){		
			/**数量*/
			quantityStr = agreement.getQuantityEn();
			res.setQuantity(quantityStr.substring(0, quantityStr.length()-1));
		}else{
			quantityStr = agreement.getQuantity();
		}
		
		/**装港*/
		String lp="";
		if(!StringUtils.isNullOrEmpty(loadPortCode)){
			String[] lpCodeArr = loadPortCode.split("/");
			for (String lpCode : lpCodeArr) {
				if(!StringUtils.isNullOrEmpty(lpCode)){
					String data = commonService.findNameByCodeAndLang("1", lpCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						lp+=data+"/";
					}else{
						lp+=lpCode+"/";
					}
				}
			}
			if(lp.length()>0){
				res.setLoadPort(lp. substring(0,lp.length()-1));
			}
		}
		
		/**卸港*/
		String unlp="";
		if(!StringUtils.isNullOrEmpty(unloadPortCode1)){
			String[] unlpCodeArr = unloadPortCode1.split("/");
			for (String unlpCode : unlpCodeArr) {
				if(!StringUtils.isNullOrEmpty(unlpCode)){
					String data=commonService.findNameByCodeAndLang("2", unlpCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						unlp+=data+"/";
					}else{
						unlp+=unlpCode+"/";
					}
				}
			}
			if(lp.length()>0){
				res.setUnloadPort(unlp.substring(0,unlp.length()-1));
			}
		}
		
		/**油种*/
		String oilStrData="";
		if(!StringUtils.isNullOrEmpty(oilTypeCode)){
			String[] oilCodeArr = oilTypeCode.split("/");
			for (String oilCode : oilCodeArr) {
				if(!StringUtils.isNullOrEmpty(oilCode)){
					String data=commonService.findNameByCodeAndLang("3", oilCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						oilStrData+=data+"/";
					}else{
						oilStrData+=oilCode+"/";
					}
				}
			}
			if(oilStrData.length()>0){
				res.setOilType(oilStrData.substring(0, oilStrData.length()-1));
			}
		}
		
		
		if (!StringUtils.isNullOrEmpty(loadPortCode)) {
			String[] lpCode = loadPortCode.split("/");
			
			for(int i=0;i<lpCode.length;i++){
				Map<String, Object> maps = new HashMap<>();
				
				//装港
				String data=commonService.findNameByCodeAndLang("1", lpCode[i]);
				if(!StringUtils.isNullOrEmpty(data)){
					//非自定义
					maps.put("loadPort", data);
				}else{
					maps.put("loadPort", lpCode[i]);
				}

				//数量
				if(!StringUtils.isNullOrEmpty(quantityStr)){
					String[] quantityS = quantityStr.split("/");
					if (quantityS.length - 1 >= i) {
						String quntityData="";
						String unitNameData="";
						if(quantityS[i].contains(Constants.VALUE_SET_19)){			/**"吨"*/
							quntityData=quantityS[i].substring(0, quantityS[i].indexOf(Constants.VALUE_SET_19));
							unitNameData=Constants.VALUE_SET_19;
						}else if(quantityS[i].contains(Constants.VALUE_SET_20)){	/**BBL*/
							quntityData=quantityS[i].substring(0, quantityS[i].indexOf(Constants.VALUE_SET_20));
							unitNameData=Constants.VALUE_SET_20;
						}else if(quantityS[i].contains(Constants.VALUE_SET_21)){	/**桶*/
							quntityData=quantityS[i].substring(0, quantityS[i].indexOf(Constants.VALUE_SET_21));
							unitNameData=Constants.VALUE_SET_21;
						}else if(quantityS[i].contains(Constants.VALUE_SET_22)){	/**MT*/
							quntityData=quantityS[i].substring(0, quantityS[i].indexOf(Constants.VALUE_SET_22));
							unitNameData=Constants.VALUE_SET_22;
						}
						maps.put("quantity", quntityData);
						maps.put("unitName", unitNameData);
					}
				}
				
				//油种
				if(!StringUtils.isNullOrEmpty(oilTypeCode)){
					String[] oilTypeCodeArr = oilTypeCode.split("/");
					if (oilTypeCodeArr.length - 1 >= i) {
						String oilData=commonService.findNameByCodeAndLang("3", oilTypeCodeArr[i]);
						if(!StringUtils.isNullOrEmpty(oilData)){
							//非自定义
							maps.put("oilName", oilData);
						}else{
							maps.put("oilName", oilTypeCodeArr[i]);
						}
					}
				}
				list.add(maps);
			}
		}
		
		
		/**
		 * 卸港
		 */
		List<Map<String, Object>> unlaodPortList = new ArrayList<>();
		String unloadPortCode = agreement.getUnloadPortCode();
		if(!StringUtils.isNullOrEmpty(unloadPortCode)){
			String[] upCodes = unloadPortCode.split("/");
			for (String code : upCodes) {
				Map<String, Object> maps = new HashMap<>();
				String unData=commonService.findNameByCodeAndLang("2", code);
				if(!StringUtils.isNullOrEmpty(unData)){
					//非自定义
					maps.put("unloadPort",unData);
				}else{
					maps.put("unloadPort",code);
				}
				unlaodPortList.add(maps);
			}
		}
		
		map.put("listLoad", list);
		map.put("listUnload", unlaodPortList);
		res.setMap(map);
		return res;
	}

	/**
	 * 查询代理协议详情(货盘uuid)
	 */
	@Override
	public AgreementRes findAgreementDetailByPalletUuid(String uuid) {
		
		Pallet pallet = palletService.findByUuid(uuid);
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		if (!Constants.PALLET_STATUS_2.equals(pallet.getStatus())
				&& !Constants.PALLET_STATUS_3.equals(pallet.getStatus())) {
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		Agreement agreements = new Agreement();
		agreements.setPalletUuid(uuid);
		List<Agreement> lists = _AgreementMapper.queryByPalletUuid(agreements);
		if (lists.size() > 1) {
			throw new TransportException(TransportException.TYPE.ITSH088);
		}
		Agreement agreement = lists.get(0);
		
		AgreementRes res = this.findAgreementDetail(agreement.getUuid());
		
		return res;
	}

	/**
	 * 查询协议列表
	 */
	@Override
	public Page<Map<String, Object>> queryAgreementList(Map<String, Object> map, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(),pageInfo.getCount());
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) _AgreementMapper.queryAgreementList(map);
		
		//根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
		if ("en".equals(lang)){
			for (Map<String, Object> map2 : page) {
				//epMemberName
				Object obj1=map2.get("epMemberNameEn");
				Object obj2=map2.get("epMemberId");
				if(obj1!=null){
					map2.put("epMemberName", (String)obj1);
				}else if(obj2!=null){
					map2.put("epMemberName", commonService.findNameByEpMemberId((Long)obj2));
				}
				//shipOwner
				Object obj3=map2.get("shipOwnerEn");
				Object obj4=map2.get("shipOwnerId");
				if(obj3!=null){
					map2.put("shipOwner", (String)obj3);
				}else if(obj4!=null){
					map2.put("shipOwner", commonService.findNameByEpMemberId((Long)obj4));
				}
				//carrierName
				Object obj5=map2.get("carrierNameEn");
				Object obj6=map2.get("carrierId");
				if(obj5!=null){
					map2.put("carrierName", (String)obj5);
				}else if(obj6!=null){
					map2.put("carrierName", commonService.findNameByEpMemberId((Long)obj6));
				}
				//brokerName
				Object obj7=map2.get("brokerNameEn");
				Object obj8=map2.get("brokerId");
				if(obj7!=null){
					map2.put("brokerName", (String)obj7);
				}else if(obj8!=null){
					map2.put("brokerName", commonService.findNameByEpMemberId((Long)obj8));
				}
			}
		}
		
		
		//处理装港、卸港、油种、数量
		for (Map<String, Object> mapData : page) { 
			String loadPortCode = (String)mapData.get("loadPortCode");
			String unloadPortCode = (String)mapData.get("unloadPortCode");
			String oilTypeCode = (String)mapData.get("oilTypeCode");
			
			//装港
			String loadPort="";
			if(!StringUtils.isNullOrEmpty(loadPortCode)){
				String[] lpCodes = loadPortCode.split("/");
				for (String lpCode : lpCodes) {
					if(!StringUtils.isNullOrEmpty(lpCode)){
						String data=commonService.findNameByCodeAndLang("1", lpCode);
						if(!StringUtils.isNullOrEmpty(data)){
							//非自定义
							loadPort+=data+"/";
						}else{
							loadPort+=lpCode+"/";
						}
					}
				}
				if(loadPort.length()>0){
					mapData.put("loadPort", loadPort.substring(0,loadPort.length()-1));
				}
			}

			//卸港
			String unloadPort="";
			if(!StringUtils.isNullOrEmpty(unloadPortCode)){
				String[] unloadCodeArr = unloadPortCode.split("/");
				for (String unloadCode : unloadCodeArr) {
					if(!StringUtils.isNullOrEmpty(unloadCode)){
						String data=commonService.findNameByCodeAndLang("2", unloadCode);
						if(!StringUtils.isNullOrEmpty(data)){
							//非自定义
							unloadPort+=data+"/";
						}else{
							unloadPort+=unloadCode+"/";
						}
					}
				}
				if(unloadPort.length()>0){
					mapData.put("unloadPort", unloadPort.substring(0,unloadPort.length()-1));
				}
			}
			
			//油种
			String oilType="";
			if(!StringUtils.isNullOrEmpty(oilTypeCode)){
				String[] oilTypeArr = oilTypeCode.split("/");
				for (String oilCode : oilTypeArr) {
					if(!StringUtils.isNullOrEmpty(oilCode)){
						String data=commonService.findNameByCodeAndLang("3", oilCode);
						if(!StringUtils.isNullOrEmpty(data)){
							//非自定义
							oilType+=data+"/";
						}else{
							oilType+=oilCode+"/";
						}
					}
				}
				if(oilType.length()>0){
					mapData.put("oilType", oilType.substring(0, oilType.length()-1));
				}
			}
			
			//数量
			if ("en".equals(lang)){
				String quantityEn =(String) mapData.get("quantityEn");
				mapData.put("quantity", quantityEn);
			}
		}
		
		return page;
	}

	/**
	 * 撤销代理协议
	 */
	@Override
	@Transactional
	public void revokeAgreement(String uuid, CurrentUser user) {
		Agreement agreement = this.findByUuid(uuid);
		if (agreement == null) {
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		// 校验状态能否撤销匹配
		if (!Constants.AGREEMENT_STATUS_1.equals(agreement.getStatus())) {
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		agreement.setStatus(Constants.AGREEMENT_STATUS_5);
		agreement.setUpdateDate(DateTimeUtils.currentDate());
		agreement.setUpdateUser(user.getMemberId());
		this.updateRecord(agreement);

		// 修改货盘状态
		Pallet pallet = palletService.findByPrimaryKey(agreement.getPalletId());
		if (pallet == null) {
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		pallet.setStatus(Constants.PALLET_STATUS_1);
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		pallet.setUpdateUser(user.getMemberId());
		palletService.updateRecord(pallet);
	}

	/**
	 * 删除协议
	 */
	@Override
	public void deleteAgreement(String uuid, CurrentUser user) {
		Agreement agreement = this.findByUuid(uuid);
		if (agreement == null) {
			throw new TransportException(TransportException.TYPE.ITSH027);
		}
		// 校验状态
		if (!Constants.AGREEMENT_STATUS_5.equals(agreement.getStatus())) {
			throw new TransportException(TransportException.TYPE.ITSH086);
		}
		this.deleteRecordByKey(agreement.getAgreementId(), user.getMemberId());
	}

	/**
	 * 查询代理协议详情多个
	 */
	@Override
	public List<AgreementRes> findAgreementDetailMany(List<String> agreementUuids) {
		
		List<AgreementRes> list = new ArrayList<>();
		for (String string : agreementUuids) {
			AgreementRes res = this.findAgreementDetail(string);
			list.add(res);
		}
		
		return list;
	}
	
	/**
	 * 根据船合同uuid查询协议详情
	 */
	@Override
	public Agreement findAgreementDetailByShipPact(AgreementVO vo,CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		Agreement agr = new Agreement();
		ShipPact pact = shipPactService.findByUuid(vo.getUuid());
		if(pact != null){
			Agreement entity = new Agreement();
			entity.setShipPactUuid(pact.getUuid());
			entity.setEpMemberId(user.getEpMemberId());
			 agr = _AgreementMapper.queryByEntity(entity);
		}
		
		
		//根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
		if ("en".equals(lang)){
			//epMemberName
			if(!StringUtils.isNullOrEmpty(agr.getEpMemberNameEn())){
				agr.setEpMemberName(agr.getEpMemberNameEn());
			}else if(agr.getEpMemberId()!=null){
				agr.setEpMemberName(commonService.findNameByEpMemberId(agr.getEpMemberId()));
			}
			//carrierName
			if(!StringUtils.isNullOrEmpty(agr.getCarrierNameEn())){
				agr.setCarrierName(agr.getCarrierNameEn());
			}else if(agr.getCarrierId()!=null){
				agr.setCarrierName(commonService.findNameByEpMemberId(agr.getCarrierId()));
			}
			//shipOwner
			if(!StringUtils.isNullOrEmpty(agr.getShipOwnerEn())){
				agr.setShipOwner(agr.getShipOwnerEn());
			}else if(agr.getShipOwnerId()!=null){
				agr.setShipOwner(commonService.findNameByEpMemberId(agr.getShipOwnerId()));
			}
			//brokerName
			if(!StringUtils.isNullOrEmpty(agr.getBrokerNameEn())){
				agr.setBrokerName(agr.getBrokerNameEn());
			}else if(agr.getBrokerId()!=null){
				agr.setBrokerName(commonService.findNameByEpMemberId(agr.getBrokerId()));
			}
		}
		
		
		/**
		 * 处理装港、卸港、油种、数量中英文
		 */
		String loadPortCode = agr.getLoadPortCode();
		String unloadPortCode1 = agr.getUnloadPortCode();
		String oilTypeCode = agr.getOilTypeCode();
		String quantityStr=null;
		
		if ("en".equals(lang)){		
			/**数量*/
			quantityStr = agr.getQuantityEn();
			if(quantityStr.length()>0){
				agr.setQuantity(quantityStr.substring(0, quantityStr.length()-1));
			}
		}
		
		/**装港*/
		String lp="";
		if(!StringUtils.isNullOrEmpty(loadPortCode)){
			String[] lpCodeArr = loadPortCode.split("/");
			for (String lpCode : lpCodeArr) {
				if(!StringUtils.isNullOrEmpty(lpCode)){
					String data=commonService.findNameByCodeAndLang("1", lpCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						lp+=data+"/";
					}else{
						lp+=lpCode+"/";
					}
				}
			}
			if(lp.length()>0){
				agr.setLoadPort(lp.substring(0,lp.length()-1));
			}
		}
		
		/**卸港*/
		String unlp="";
		if(!StringUtils.isNullOrEmpty(unloadPortCode1)){
			String[] unlpCodeArr = unloadPortCode1.split("/");
			for (String unlpCode : unlpCodeArr) {
				if(!StringUtils.isNullOrEmpty(unlpCode)){
					String data=commonService.findNameByCodeAndLang("2", unlpCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						unlp+=data+"/";;
					}else{
						unlp+=unlpCode+"/";;
					}
				}
			}
			if(unlp.length()>0){
				agr.setUnloadPort(unlp.substring(0,unlp.length()-1));
			}
		}
		
		/**油种*/
		String oilStrData="";
		if(!StringUtils.isNullOrEmpty(oilTypeCode)){
			String[] oilCodeArr = oilTypeCode.split("/");
			for (String oilCode : oilCodeArr) {
				if(!StringUtils.isNullOrEmpty(oilCode)){
					String data=commonService.findNameByCodeAndLang("3", oilCode);
					if(!StringUtils.isNullOrEmpty(data)){
						//非自定义
						oilStrData+=data+"/";
					}else{
						oilStrData+=oilCode+"/";
					}
				}
			}
			if(oilStrData.length()>0){
				agr.setOilType(oilStrData.substring(0, oilStrData.length()-1));
			}
		}

		return agr;
	}
	
	
	private String getCode(Date signDate,String oilType,Long epMemberId,String shipName){
		String oil = "";
		if (!StringUtils.isNullOrEmpty(oilType)) {
			String[] strings = oilType.split("/");
			for (String str : strings) {
				if (!StringUtils.isNullOrEmpty(str)) {
					String[] split = str.split(" ");
					if (split.length > 0) {
						String string = split[0];
						oil = oil + string + "/";
					}
				}
			}
			if (oil.length() > 0) {
				oil = oil.substring(0, oil.length() - 1);
			}
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String dateString = formatter.format(signDate);
		// 查询委托方英文简称
		String name = this.queryEngName(epMemberId);
		String code = shipName + "/" + name + "/" + dateString
				+ "/" + oil;
		return code;
	}
	
	private ShipPact insertFn(AgreementVO vo , ShipPlate shipPlate ,String newUuid ,CurrentUser user,String mmsi, String loadReg, String unloadReg){
		ShipPact ship = new ShipPact();
		// 船合同编号
		SimpleDateFormat format =  new SimpleDateFormat("yyMMdd");
		Date signDate = vo.getSignDate();
		ship.setPactCode(vo.getShipName()+format.format(signDate));
		ship.setShipPlateId(shipPlate.getShipPlateId());
		ship.setShipPlateUuid(shipPlate.getUuid());
		ship.setSignDate(vo.getSignDate());
		ship.setLoadingArea(loadReg);
		ship.setUnloadingArea(unloadReg);
		ship.setCarrierId(vo.getCarrierId());
		ship.setCarrierName(vo.getCarrierName());
		ship.setShipOwner(vo.getShipOwner());
		ship.setShipOwnerId(vo.getShipOwnerId());
		ship.setShipName(vo.getShipName());
		ship.setPactBeg(vo.getPactBeg());
		ship.setPactEnd(vo.getPactEnd());
		ship.setMinQuantity(vo.getMinQuantity());
		ship.setWs(vo.getWs());
		ship.setWsRes(vo.getWsRes());
		ship.setPactSpeed(vo.getPactSpeed());
		ship.setDemurrage(vo.getDemurrage());
		ship.setBrokerId(vo.getBrokerId());
		ship.setBrokerName(vo.getBrokerName());
		ship.setDockTime(vo.getDockTime());
		ship.setRemark(vo.getRemark());
		ship.setMmsi(mmsi);
		ship.setCreateDate(DateTimeUtils.currentDate());
		ship.setCreateUser(user.getMemberId());
		ship.setUuid(newUuid);
		ship.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		ship.setEpMemberId(user.getEpMemberId());
		ship.setStatus(Constants.SHIP_PACT_STATUS_1);
		ship.setLangVer(Constants.LANG_VER);
		ship.setSysShipUuid(vo.getSysShipUuid());
		
		EnterpriseVo enterVo = commonService.queryUserByEpMemberId(ship.getEpMemberId());
		ship.setEpMemberNameEn(enterVo.getEnglishName());
		
		//设置船东、经纪人、租船人中英文
		EnterpriseVo shipEnterVo = commonService.queryUserByEpMemberId(ship.getShipOwnerId());
		ship.setShipOwner(shipEnterVo.getFullName());
		ship.setShipOwnerEn(shipEnterVo.getEnglishName());
		
		EnterpriseVo brokerEnterVo = commonService.queryUserByEpMemberId(ship.getBrokerId());
		ship.setBrokerName(brokerEnterVo.getFullName());
		ship.setBrokerNameEn(brokerEnterVo.getEnglishName());
		
		EnterpriseVo carryEnterVo = commonService.queryUserByEpMemberId(ship.getCarrierId());
		ship.setCarrierName(carryEnterVo.getFullName());
		ship.setCarrierNameEn(carryEnterVo.getEnglishName());
		
		shipPactService.insertRecord(ship);
		return ship;
	}

	@Override
	public Page<Map<String, Object>> queryAgreementPlatform(Map<String, Object> maps, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		Page<Map<String, Object>> page = this.queryRecords(maps, pageInfo);
		
		if ("en".equals(lang)){
			for (Map<String, Object> map2 : page) {
				//根据语言环境设置 epMemberName、shipOwner、carrierName、brokerName
				//epMemberName
				Object obj1=map2.get("epMemberNameEn");
				Object obj2=map2.get("epMemberId");
				if(obj1!=null){
					map2.put("epMemberName", (String)obj1);
				}else if(obj2!=null){
					map2.put("epMemberName", commonService.findNameByEpMemberId((Long)obj2));
				}
				//shipOwner
				Object obj3=map2.get("shipOwnerEn");
				Object obj4=map2.get("shipOwnerId");
				if(obj3!=null){
					map2.put("shipOwner", (String)obj3);
				}else if(obj4!=null){
					map2.put("shipOwner", commonService.findNameByEpMemberId((Long)obj4));
				}
				//carrierName
				Object obj5=map2.get("carrierNameEn");
				Object obj6=map2.get("carrierId");
				if(obj5!=null){
					map2.put("carrierName", (String)obj5);
				}else if(obj6!=null){
					map2.put("carrierName", commonService.findNameByEpMemberId((Long)obj6));
				}
				//brokerName
				Object obj7=map2.get("brokerNameEn");
				Object obj8=map2.get("brokerId");
				if(obj7!=null){
					map2.put("brokerName", (String)obj7);
				}else if(obj8!=null){
					map2.put("brokerName", commonService.findNameByEpMemberId((Long)obj8));
				}

				//数量
				map2.put("quantity", map2.get("quantityEn"));
			}
		}
		
		
		//处理装港、卸港、油种
		for (Map<String, Object> mapData : page) { 
			String loadPortCode = (String)mapData.get("loadPortCode");
			String unloadPortCode = (String)mapData.get("unloadPortCode");
			String oilTypeCode = (String)mapData.get("oilTypeCode");
			
			//装港
			String loadPort="";
			if(!StringUtils.isNullOrEmpty(loadPortCode)){
				String[] lpCodes = loadPortCode.split("/");
				for (String lpCode : lpCodes) {
					if(!StringUtils.isNullOrEmpty(lpCode)){
						loadPort+=commonService.findNameByCodeAndLang("1", lpCode)+"/";
					}
				}
				if(loadPort.length()>0){
					mapData.put("loadPort", loadPort.substring(0,loadPort.length()-1));
				}
			}

			//卸港
			String unloadPort="";
			if(!StringUtils.isNullOrEmpty(unloadPortCode)){
				String[] unloadCodeArr = unloadPortCode.split("/");
				for (String unloadCode : unloadCodeArr) {
					if(!StringUtils.isNullOrEmpty(unloadCode)){
						unloadPort+=commonService.findNameByCodeAndLang("2", unloadCode)+"/";
					}
				}
				if(unloadPort.length()>0){
					mapData.put("unloadPort", unloadPort.substring(0,unloadPort.length()-1));
				}
			}
			
			//油种
			String oilType="";
			if(!StringUtils.isNullOrEmpty(oilTypeCode)){
				String[] oilTypeArr = oilTypeCode.split("/");
				for (String oilCode : oilTypeArr) {
					if(!StringUtils.isNullOrEmpty(oilCode)){
						oilType+=commonService.findNameByCodeAndLang("3", oilCode)+"/";
					}
				}
				if(oilType.length()>0){
					mapData.put("oilType", oilType.substring(0, oilType.length()-1));
				}
			}
		}
		
		return page;
	}

}
