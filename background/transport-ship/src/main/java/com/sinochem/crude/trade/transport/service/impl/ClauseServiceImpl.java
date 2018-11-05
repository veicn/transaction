package com.sinochem.crude.trade.transport.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.ClauseMapper;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.model.ClauseVO;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;

@Service
public class ClauseServiceImpl implements ClauseService {
	@Autowired
	private ClauseMapper _clauseMapper;
	@Autowired
	private ShipPlateService _shipPlateService;
	@Autowired
	private PalletService _palletService;
	@Autowired
	private CommonService _commonService;
	@Autowired
	private IntentionService _intentionService;
	@Autowired
	private SysShipService _sysShipService;
	@Autowired
	private MessagePushService messagePushService;
	/*@Autowired
	private CommonService commonService;*/
	
	Log log = LogFactory.getLog(ShipPlateServiceImpl.class);
	public ClauseMapper getMapper(){
		return _clauseMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Clause> queryByEntitys(Clause clause){
		return  _clauseMapper.queryByEntitys(clause);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Clause findByPrimaryKey(Long clauseId){
		return  _clauseMapper.findByPrimaryKey(clauseId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Clause findByUuid(String uuid){
		return  _clauseMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Clause clause) {
		_clauseMapper.updateRecord(clause);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long clauseId  , Long deleteUser) {
		_clauseMapper.deleteRecordByKey(clauseId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Clause clause){
		_clauseMapper.insertRecord(clause);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long clauseId){
		 _ClauseMapper.deleteRecordByKey(clauseId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _clauseMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _clauseMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _clauseMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_clauseMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_clauseMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 添加报盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveClause(Clause vo, CurrentUser user) {
		
		String shipPlateUuid = vo.getShipPlateUuid();
		String palletUuid = vo.getPalletUuid();
		Long epMemberId = user.getEpMemberId();
		Long memberId = user.getMemberId();
	
		// 获取船盘信息
		ShipPlate shipPlate = _shipPlateService.findByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		// 获取货盘信息
		Pallet pallet = _palletService.findByUuid(palletUuid);
		if(pallet==null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		// 设置会员公司名称
		EnterpriseVo enterpriseVo = _commonService.queryUserByEpMemberId(epMemberId);
		EnterpriseVo palletEnterpriseVo = _commonService.queryUserByEpMemberId(pallet.getEpMemberId());
		vo.setEpMemberName(enterpriseVo.getFullName());
		vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
	
		//设置发布货盘公司信息
		vo.setEpMemberId(epMemberId);
		vo.setPalletMemberId(pallet.getEpMemberId());
		vo.setPalletMemberName(palletEnterpriseVo.getFullName());
		vo.setPalletMemberNameEn(palletEnterpriseVo.getEnglishName());
		
		// 获取货物意向编号（需求单号+船名+时间）
		String palletCode = pallet.getPalletCode();
		String name = shipPlate.getName();
		String currentTime = DateTimeUtils.currentDateString("yyMMdd");
		String clauseCode = palletCode + name + currentTime;
	
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setClauseCode(clauseCode);
		vo.setShipPlateId(shipPlate.getShipPlateId());
		vo.setPalletId(pallet.getPalletId());
		
		vo.setStatus(Constants.CLAUSE_STATUS_1);
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setCreateUser(memberId);
		vo.setUpdateUser(memberId);
		// 添加报盘信息
		this.insertRecord(vo);
		//修改需求状态	
		if(!Constants.PALLET_STATUS_1.equals(pallet.getStatus())){
			throw new TransportException(TransportException.TYPE.ITSH018);
		}
		pallet.setStatus(Constants.PALLET_STATUS_3);
		pallet.setUpdateUser(memberId);
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		_palletService.updateRecord(pallet);
		if(!"2".equals(shipPlate.getRelType())){
			throw new TransportException(TransportException.TYPE.ITSH014);
		}
		//修改船盘状态
		if("1".equals(shipPlate.getLeaveFlag())){
			throw new TransportException(TransportException.TYPE.ITSH014);
		}
		if(Constants.SHIP_PLATE_STATUS_1.equals(shipPlate.getStatus())){
			shipPlate.setStatus(Constants.SHIP_PLATE_STATUS_2);
			shipPlate.setUpdateUser(memberId);
			shipPlate.setUpdateDate(DateTimeUtils.currentDate());
			_shipPlateService.updateRecord(shipPlate);
		}
		
		//发送消息
		messagePushService.messagePush(13, vo.getClauseId(), user.getMemberId());
		
	}

	
	/**
	 * 查看报盘详细信息(报盘列表)
	 */
	@Override
	public Map<String, Object> findClauseDetails(ClauseVO vo, CurrentUser user) {
		Map<String, Object> map = new HashMap<>();
		//判断语言环境
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		// 获取报盘信息
		Clause clause = this.findByUuid(vo.getUuid());
		if (clause == null) {
			throw new TransportException(TransportException.TYPE.ITSH602);
		}
		// 获取船盘信息
		String shipPlateUuid = clause.getShipPlateUuid();
		if(StringUtils.isNullOrEmpty(shipPlateUuid)){
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		Map<String, Object> shipPlate = _shipPlateService.findMapByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		if ("en".equals(string)){
			//船东中英文
			String shipOwnerDate=(String) shipPlate.get("shipOwnerEn");
			if(!StringUtils.isNullOrEmpty(shipOwnerDate)){
				shipPlate.put("shipOwner",shipOwnerDate);
			}else{
				Object obj=shipPlate.get("shipOwnerId");
				if(obj!=null){
					shipPlate.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj));
				}else{
					shipPlate.put("shipOwner","");
				}
			}
		}
		
		String sysShipUuid = (String)shipPlate.get("sysShipUuid");
		if(StringUtils.isNullOrEmpty(sysShipUuid)){
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		//船舶信息
		SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
		if(sysShip==null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		// 获取租船需求信息
		String palletUuid =clause.getPalletUuid();
		if(StringUtils.isNullOrEmpty(palletUuid)){
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet palletVO = new Pallet();
		palletVO.setUuid(palletUuid);
		Map<String, Object> pallet= _palletService.getPallet(palletVO);
		
		//1货主
		if("1".equals(vo.getRoleFlag())){
			if ("en".equals(string)){
				if(!StringUtils.isNullOrEmpty(clause.getEpMemberNameEn())){
					map.put("epMemberName",clause.getEpMemberNameEn());
				}else{
					map.put("epMemberName", _commonService.findNameByEpMemberId(clause.getEpMemberId()));
				}
			}else{
				map.put("epMemberName",clause.getEpMemberName());
			}
		}
		//2二船东
		if("2".equals(vo.getRoleFlag())){
			if ("en".equals(string)){
				if(!StringUtils.isNullOrEmpty(clause.getPalletMemberNameEn())){
					map.put("epMemberName",clause.getPalletMemberNameEn());
				}else{
					map.put("epMemberName", _commonService.findNameByEpMemberId(clause.getPalletMemberId()));
				}
			}else{
				map.put("epMemberName",clause.getPalletMemberName());
			}
		}
		map.put("agreementFlag", pallet.get("agreementFlag"));
		map.put("clause", clause);
		map.put("shipPlate",shipPlate);
		map.put("sysShip", sysShip);
		map.put("roleFlag",vo.getRoleFlag());
		return map; 
	}
	/**
	 * 撤销
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteClause(Clause vo, CurrentUser user) {

		// 取得uuid
		String uuid = vo.getUuid();
		// 根据报盘uuid查询报盘盘信息
		Clause clause = this.findByUuid(uuid);
		if (clause == null) {
			throw new TransportException(TransportException.TYPE.ITSH602);
		}
		String PalletUuid = clause.getPalletUuid();
		if(StringUtils.isNullOrEmpty(PalletUuid)){
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet pallet = _palletService.findByUuid(PalletUuid);
		//判断货盘状态
		if(pallet.getStatus().equals(Constants.PALLET_STATUS_1)){
			throw new TransportException(TransportException.TYPE.ITSH018);
		}
		pallet.setStatus(Constants.PALLET_STATUS_1);
		pallet.setUpdateDate(DateTimeUtils.currentDate());
		pallet.setUpdateUser(user.getMemberId());
		
		// 判断报盘状态
		if (clause.getStatus().equals(Constants.CLAUSE_STATUS_3)) {
			throw new TransportException(TransportException.TYPE.ITSH603);
		}
		clause.setStatus(Constants.CLAUSE_STATUS_3);
		clause.setUpdateDate(DateTimeUtils.currentDate());
		clause.setUpdateUser(user.getMemberId());
		clause.setPalletId(0l);
		clause.setPalletUuid("");
		clause.setShipPlateId(0l);
		clause.setShipPlateUuid("");
		
		// 修改报盘状态
		this.updateRecord(clause);
		//修改货盘状态
		_palletService.updateRecord(pallet);
		
		//发送消息
		messagePushService.messagePush(15, clause.getClauseId(), user.getMemberId());
		
	}

	/**
	 * 根据条件报盘分页列表
	 */
	public Page<Map<String, Object>> queryClauseList(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _clauseMapper.queryRecords(map);
	}
	
	/**
	 * 洽谈报盘列表
	 */
	public Page<Map<String, Object>> talkingClause(Map<String, Object> map, SimplePageInfo pageInfo){
		Page<Map<String, Object>> clauseList = this.queryClauseList(map, pageInfo);
		ShipPlate shipPlate = _shipPlateService.findByUuid((String)map.get("shipPlateUuid"));
		String shipName = "";
		if(shipPlate!=null){
			shipName = shipPlate.getName();
		}
		if(clauseList!=null){
			for (Map<String, Object> map2 : clauseList) {
				String pactBeg = (String)map2.get("pactBeg");
				String pactEnd = (String)map2.get("pactEnd");
				if(pactBeg!=null&&pactEnd!=null){
					String loadRange = pactBeg+"-"+pactEnd;
					map2.put("loadRange", loadRange);
				}else{
					map2.put("loadRange","");
				}
				map2.put("shipName",shipName);
			}
		}
		return clauseList;  
	}
	/**
	 * 根据条件-分页查询（代理）
	 */
	public Page<Map<String, Object>> queryClauseAgency(Map<String, Object> map, SimplePageInfo pageInfo){
		Page<Map<String, Object>> clauseList = this.queryRecords(map,pageInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(clauseList!=null){
			for (Map<String, Object> map2 : clauseList) {
				//Pallet pallet = _PalletService.findByUuid((String) map2.get("palletUuid"));
				if(map2.get("shipPlateUuid")!=null&&!"".equals(map2.get("shipPlateUuid"))){
					ShipPlate shipPlate = _shipPlateService.findByUuid((String) map2.get("shipPlateUuid"));
					if(shipPlate!=null){
						map2.put("shipName", shipPlate.getName());
					}
				}
				Date pactBeg = (Date)map2.get("pactBeg");
				Date pactEnd = (Date)map2.get("pactEnd");
				if(pactBeg!=null){
					String loadRange = sdf.format(pactBeg)+"-"+sdf.format(pactEnd);
					map2.put("loadRange", loadRange);
				}
			}
		}
		return clauseList;  
	}
	/**
	 * 报盘确认
	 */
	@Transactional
	public void affirmClause(Clause vo, CurrentUser user) {
		// 查询报盘信息
		String uuid = vo.getUuid();
		Clause clause = this.findByUuid(uuid);
		if (clause == null) {
			throw new TransportException(TransportException.TYPE.ITSH602);
		}
		String palletUuid = clause.getPalletUuid();
		if (StringUtils.isNullOrEmpty(palletUuid)) {
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		String shipPlateUuid = clause.getShipPlateUuid();
		if (StringUtils.isNullOrEmpty(shipPlateUuid)) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		ShipPlate shipPlate = _shipPlateService.findByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		Pallet Pallet = _palletService.findByUuid(palletUuid);
		if(Pallet==null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}	
		//检验货盘状态
		if(!Pallet.getStatus().equals(Constants.PALLET_STATUS_3)){
			throw new TransportException(TransportException.TYPE.ITSH018);
		}
		//检验船盘状态
		if(shipPlate.getStatus().equals(Constants.SHIP_PLATE_STATUS_1)){
			throw new TransportException(TransportException.TYPE.ITSH014);
		}
		// 校验报盘的状态
		if (!(clause.getStatus().equals(Constants.CLAUSE_STATUS_1))) {
			throw new TransportException(TransportException.TYPE.ITSH603);
		}
		Pallet.setStatus(Constants.PALLET_STATUS_4);
		Pallet.setUpdateDate(DateTimeUtils.currentDate());
		Pallet.setUpdateUser(user.getMemberId());
		clause.setStatus(Constants.CLAUSE_STATUS_2);
		clause.setUpdateDate(DateTimeUtils.currentDate());
		clause.setUpdateUser(user.getMemberId());
		shipPlate.setStatus(Constants.SHIP_PLATE_STATUS_3);
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		_shipPlateService.updateRecord(shipPlate);
		this.updateRecord(clause);
		_palletService.updateRecord(Pallet);
		//将其他询盘关闭
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status",Constants.INTENTION_STATUS_4);
		params.put("shipPlateUuid",shipPlateUuid);
		params.put("updateDate",DateTimeUtils.currentDate());
		params.put("updateUser",user.getMemberId());
		_intentionService.changeStatus(params);
		
		//发送消息
		messagePushService.messagePush(14, clause.getClauseId(), user.getMemberId());
		
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Map<String, Object> findMapByUuid(String uuid){
		return  _clauseMapper.findMapByUuid(uuid);	
	}
	/**
	 * 发送报盘页面数据
	 */
	@Override
	public Map<String, Object> findShipAndShipPlate(Clause vo, CurrentUser user) {
		//判断语言环境
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		Map<String, Object> map = new HashMap<>();
		// 获取船盘信息
		String shipPlateUuid = vo.getShipPlateUuid();
		Map<String, Object> shipPlate = _shipPlateService.findMapByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		String sysShipUuid = (String)shipPlate.get("sysShipUuid");
		if(StringUtils.isNullOrEmpty(sysShipUuid)){
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		//shipOwner
		if ("en".equals(string)){
			Object obj3= shipPlate.get("shipOwnerEn");
			Object obj4= shipPlate.get("shipOwnerId");
			if(obj3!=null){
				shipPlate.put("shipOwner",(String)obj3);
			}else if(obj4!=null){
				shipPlate.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
			}
		}
		//船舶信息
		SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
		if(sysShip==null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		//需求信息
		Pallet pallet = _palletService.findByUuid(vo.getPalletUuid());
		if(pallet==null){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		
		//对家企业全称
		String  epMemberName = "";
		if ("en".equals(string)){
			if(!StringUtils.isNullOrEmpty(pallet.getEpMemberNameEn())){
				epMemberName = pallet.getEpMemberNameEn();
			}else{
				Long epMemberId = pallet.getEpMemberId();
				epMemberName = _commonService.findNameByEpMemberId(epMemberId);
			}
		}else{
			epMemberName = pallet.getEpMemberName();
		}
		if(StringUtils.isNullOrEmpty(epMemberName)){
			throw new TransportException(TransportException.TYPE.ITSH606);
		}
		//报盘带入信息
		
		map.put("epMemberName",epMemberName);
		map.put("shipPlate", shipPlate);
		map.put("sysShip", sysShip);
		map.put("pallet", pallet);
		return map; 
	}
	/**
	 * 查看报盘详细信息(需求页面)
	 */
	@Override
	public Map<String, Object> findClauseDetails1(ClauseVO vo, CurrentUser user) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		//判断语言环境
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		// 获取报盘信息
		 param.put("palletUuid", vo.getPalletUuid());
		 List<Map<String, Object>> clauses = this.queryRecords(param);
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
		Map<String, Object> shipPlate = _shipPlateService.findMapByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		String sysShipUuid = (String)shipPlate.get("sysShipUuid");
		if(StringUtils.isNullOrEmpty(sysShipUuid)){
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		//shipOwner
		if ("en".equals(string)){
			Object obj3= shipPlate.get("shipOwnerEn");
			Object obj4= shipPlate.get("shipOwnerId");
			if(obj3!=null){
				shipPlate.put("shipOwner",(String)obj3);
			}else if(obj4!=null){
				shipPlate.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
			}
		}
		//船舶信息
		SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
		if(sysShip==null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		// 获取租船需求信息
		String palletUuid = (String)clause.get("palletUuid");
		if(StringUtils.isNullOrEmpty(palletUuid)){
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet palletVO = new Pallet();
		palletVO.setUuid(palletUuid);
		Map<String, Object> pallet= _palletService.getPallet(palletVO);
		
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
						map.put("epMemberName",_commonService.findNameByEpMemberId(epMemberId));
					}else{
						map.put("epMemberName","");
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
						map.put("epMemberName",_commonService.findNameByEpMemberId(palletMemberId));
					}else{
						map.put("epMemberName","");
					}
				}
			}else{
				map.put("epMemberName",clause.get("palletMemberName"));
			}
		}
		map.put("clause", clause);
		map.put("shipPlate",shipPlate);
		map.put("sysShip", sysShip);
		map.put("pallet",pallet);
		map.put("roleFlag",vo.getRoleFlag());
		return map; 
	}
	
	@Override
	public Clause findClauseDetail(ClauseVO vo) {
		return  _clauseMapper.findByUuid(vo.getUuid());
	}
}
