package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
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
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException.TYPE;
import com.sinochem.crude.trade.transport.dao.IntentionMapper;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.query.IntentionQuery;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;

@Service
public class IntentionServiceImpl implements IntentionService {
	@Autowired
	private IntentionMapper _IntentionMapper;
	@Autowired
	private ShipPlateService _ShipPlateService;
	@Autowired
	private PalletService _PalletService;
	@Autowired
	private CommonService _commonService;
	@Autowired
	private ClauseService _clauseService;	
	@Autowired
	private MessagePushService messagePushService;

	public IntentionMapper getMapper() {
		return _IntentionMapper;
	}

	Log log = LogFactory.getLog(ShipPlateServiceImpl.class);

	/**
	 * 根据对象-查询对象列表
	 */
	public List<Intention> queryByEntitys(Intention intention) {
		return _IntentionMapper.queryByEntitys(intention);
	}

	/**
	 * 根据主键-查询对象
	 */
	public Intention findByPrimaryKey(Long intentionId) {
		return _IntentionMapper.findByPrimaryKey(intentionId);
	}

	/**
	 * 根据UUID-查询对象
	 */
	public Intention findByUuid(String uuid) {
		return _IntentionMapper.findByUuid(uuid);
	}

	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Intention intention) {
		_IntentionMapper.updateRecord(intention);
	}

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long intentionId, Long deleteUser) {
		_IntentionMapper.deleteRecordByKey(intentionId, deleteUser);
	}

	/**
	 * 新增
	 */
	public void insertRecord(Intention intention) {
		_IntentionMapper.insertRecord(intention);
	}

	/*
	 * 根据主键删除数据 public void deleteRecordByKey(Long intentionId){
	 * _IntentionMapper.deleteRecordByKey(intentionId); }
	 */
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return _IntentionMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _IntentionMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map) {
		return _IntentionMapper.countRecords(map);
	}

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) {
		_IntentionMapper.deleteRecords(map);
	}

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_IntentionMapper.updateRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 根据uuid-批量修改数据
	 */
	public void updateRecordsFn(Map<String, Object> map) {
		_IntentionMapper.updateRecordsFn(map);
	}

	/**
	 * 添加询盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveIntention(Intention vo, CurrentUser user) {

		String shipPlateUuid = vo.getShipPlateUuid();
		String palletUuid = vo.getPalletUuid();
		Long epMemberId = user.getEpMemberId();
		Long memberId = user.getMemberId();
		
		//获取船盘信息
		ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TYPE.ITSH013);
		}
		//获取货盘信息
		Pallet pallet = _PalletService.findByUuid(palletUuid);
		if(pallet==null){
			throw new TransportException(TYPE.ITSH017);
		}
		
		
		//校验，是否指定承运商，若指定，不能再询盘
		String traderSel = pallet.getTraderSel();	//是否指定承运商（1是0否）
		if(traderSel!=null && traderSel.equals("1")){
			throw new TransportException(TYPE.ITSH417);
		}
		
		
		//校验，当前询的船盘，关联的其它询盘的状态是否有为“3已确认租船”，有询盘操作失败
		Intention intention=new Intention();
		intention.setShipPlateUuid(shipPlateUuid);
		List<Intention> intentionList = this.queryByEntitys(intention);
		if(intentionList!=null && intentionList.size()>0){
			for (Intention in : intentionList) {
				String status = in.getStatus();
				if(!StringUtils.isNullOrEmpty(status) && status.equals("3")){
					throw new TransportException(TYPE.ITSH014);
				}
			}
		}
		//校验，当前询的船盘，关联的其他的报盘的状态是否有为“2已确认”，有，询盘确认失败
		Clause clauseQuery=new Clause();
		clauseQuery.setShipPlateUuid(shipPlateUuid);
		List<Clause> clauseList = _clauseService.queryByEntitys(clauseQuery);
		if(clauseList!=null && clauseList.size()>0){
			for (Clause clause : clauseList) {
				if(!StringUtils.isNullOrEmpty(clause.getStatus()) && clause.getStatus().equals("2")){
					throw new TransportException(TYPE.ITSH014);
				}
			}
		}
		
		//设置会员公司名称
		EnterpriseVo enterpriseVo = _commonService.queryUserByEpMemberId(epMemberId);
		if(enterpriseVo!=null){
			vo.setEpMemberName(enterpriseVo.getFullName());
			vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		}
		
		//设置船盘发布人
		EnterpriseVo enterpriseVo2 = _commonService.queryUserByEpMemberId(shipPlate.getEpMemberId());
		if(enterpriseVo2!=null){
			vo.setShipPlateMemberName(enterpriseVo2.getFullName());
			vo.setShipPlateMemberNameEn(enterpriseVo2.getEnglishName());
		}

		//获取租船意向编号（需求单号+船名+时间）
		String palletCode = pallet.getPalletCode();
		String name = shipPlate.getName();
		String currentTime = DateTimeUtils.currentDateString("yyMMdd");
		String intentionCode = palletCode + name + currentTime;
		
		
		//修改当前货盘状态为“2处理中”
		Pallet pallet2 = _PalletService.findByUuid(palletUuid);
		if(pallet2.getStatus().equals("1")){
			Pallet palletUpdate=new Pallet();
			palletUpdate.setStatus(Constants.PALLET_STATUS_2);
			palletUpdate.setPalletId(pallet2.getPalletId());
			palletUpdate.setUpdateDate(DateTimeUtils.currentDate());
			palletUpdate.setUpdateUser(user.getMemberId());
			_PalletService.updateRecord(palletUpdate);
		}
		
		
		//修改当前船盘状态为“2洽谈中”
		ShipPlate shipPlate2 = _ShipPlateService.findByUuid(shipPlateUuid);
		if(shipPlate2.getStatus().equals("1")){
			ShipPlate shipPlateUpdate=new ShipPlate();
			shipPlateUpdate.setStatus(Constants.SHIP_PLATE_STATUS_2);
			shipPlateUpdate.setShipPlateId(shipPlate2.getShipPlateId());
			shipPlateUpdate.setUpdateDate(DateTimeUtils.currentDate());
			shipPlateUpdate.setUpdateUser(user.getMemberId());
			_ShipPlateService.updateRecord(shipPlateUpdate);
		}
		

		vo.setUuid(KeyGenUtils.newUuid());
		vo.setIntentionCode(intentionCode);
		vo.setShipPlateId(shipPlate.getShipPlateId());
		vo.setShipPlateUuid(shipPlate.getUuid());
		vo.setPalletId(pallet.getPalletId());
		vo.setPalletUuid(pallet.getUuid());
		vo.setEpMemberId(epMemberId);
		vo.setShipPlateMemberId(shipPlate.getEpMemberId());
		vo.setStatus("1");
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setCreateUser(memberId);
		vo.setUpdateUser(memberId);

		//添加询盘信息
		this.insertRecord(vo);
		//发送消息
		messagePushService.messagePush(8, vo.getIntentionId(), user.getMemberId());
	}

	/**
	 * 询盘列表（船东/二船东，询盘状态不为：3已确认租船）
	 */
	@Override
	public Page<Map<String, Object>> findIntentionList(CurrentUser user, IntentionQuery query,
			SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		//获取询盘信息（根据船盘uuid，船盘发布人查询）
		Map<String, Object> map = new HashMap<>();
		map.put("shipPlateMemberId", user.getEpMemberId());
		map.put("shipPlateUuid", query.getShipPlateUuid());
		map.put("status", Constants.INTENTION_STATUS_3);	//查询状态不为“ 3已确认租船”
		Page<Map<String, Object>> page = this.findIntentionListQueryRecords(map, pageInfo);

		//获取船盘信息
		ShipPlate shipPlate = _ShipPlateService.findByUuid(query.getShipPlateUuid());

		if (page != null && page.size()>0 && shipPlate != null) {
			//拼接船名、船东
			for (Map<String, Object> map2 : page) {
				if(!StringUtils.isNullOrEmpty(shipPlate.getName())){
					map2.put("shipName", shipPlate.getName());
				}
				if(!StringUtils.isNullOrEmpty(shipPlate.getShipOwner())){
					map2.put("shipOwner", shipPlate.getShipOwner());
				}
				
				//根据语言环境设置 epMemberName、shipPlateMemberName
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=map2.get("epMemberNameEn");
					Object obj2=map2.get("epMemberId");
					if(obj1!=null){
						map2.put("epMemberName", (String)obj1);
					}else if(obj2!=null){
						map2.put("epMemberName", _commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipPlateMemberName
					Object obj3=map2.get("shipPlateMemberNameEn");
					Object obj4=map2.get("shipPlateMemberId");
					if(obj3!=null){
						map2.put("shipPlateMemberName", (String)obj3);
					}else if(obj4!=null){
						map2.put("shipPlateMemberName", _commonService.findNameByEpMemberId((Long)obj4));
					}
				}
			}
		}
		return page;
	}
	
	
	/**
	 * 根据条件-分页查询（船东/二船东，询盘状态不等于...）
	 */
	@Override
	public Page<Map<String, Object>> findIntentionListQueryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) _IntentionMapper.findIntentionListQueryRecords(map);
	}
	
	
	/**
	 * 根据条件-查询全部（询盘状态不等于...）
	 */
	@Override
	public List<Map<String, Object>> findPageListStatusEqualsNo(Map<String, Object> map) {
		return _IntentionMapper.findIntentionListQueryRecords(map);
	}
	
	
	/**
	 * 询盘列表（货主，租船需求管理-询盘信息）
	 */
	@Override
	public Page<Map<String, Object>> findIntentionLists(CurrentUser user, IntentionQuery query, SimplePageInfo pageInfo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		//获取询盘信息（根据货盘uuid，epMemberId查询）
		Map<String, Object> map = new HashMap<>();
		map.put("epMemberId", user.getEpMemberId());
		map.put("palletUuid", query.getPalletUuid());
		Page<Map<String, Object>> page = this.queryRecords(map, pageInfo);

		//根据每个询盘，获取船名
		if (page!=null && page.size()>0) {
			for (Map<String, Object> intentionMap : page) {
				//获取船盘
				String shipPlateUuid=(String) intentionMap.get("shipPlateUuid");
				if(!StringUtils.isNullOrEmpty(shipPlateUuid)){
					ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
					if(shipPlate!=null){
						String shipNameStr = shipPlate.getName();
						//put入船名到当前询盘中
						intentionMap.put("shipName", shipNameStr);
					}
				}
				
				//根据语言环境设置 epMemberName、shipPlateMemberName
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=intentionMap.get("epMemberNameEn");
					Object obj2=intentionMap.get("epMemberId");
					if(obj1!=null){
						intentionMap.put("epMemberName", (String)obj1);
					}else if(obj2!=null){
						intentionMap.put("epMemberName", _commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipPlateMemberName
					Object obj3=intentionMap.get("shipPlateMemberNameEn");
					Object obj4=intentionMap.get("shipPlateMemberId");
					if(obj3!=null){
						intentionMap.put("shipPlateMemberName", (String)obj3);
					}else if(obj4!=null){
						intentionMap.put("shipPlateMemberName", _commonService.findNameByEpMemberId((Long)obj4));
					}
				}
			}
		}
		return page;
	}

	/**
	 * 查看询盘详细信息
	 */
	@Override
	public Intention findIntentionDetail(Intention vo, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		//获取询盘信息
		Intention intention = this.findByUuid(vo.getUuid());
		if (intention == null) {
			throw new TransportException(TransportException.TYPE.ITSH405);
		}
		
		//根据语言环境设置 epMemberName、shipPlateMemberName
		if ("en".equals(lang)){
			//epMemberName
			if(!StringUtils.isNullOrEmpty(intention.getEpMemberNameEn())){
				intention.setEpMemberName(intention.getEpMemberNameEn());
			}else if(intention.getEpMemberId()!=null){
				intention.setEpMemberName(_commonService.findNameByEpMemberId(intention.getEpMemberId()));
			}
			//shipPlateMemberName
			if(!StringUtils.isNullOrEmpty(intention.getShipPlateMemberNameEn())){
				intention.setShipPlateMemberName(intention.getShipPlateMemberNameEn());
			}else if(intention.getShipPlateMemberId()!=null){
				intention.setShipPlateMemberName(_commonService.findNameByEpMemberId(intention.getShipPlateMemberId()));
			}
		}
		
		return	intention;
	}

	/**
	 * 修改询盘状态（1已询盘2已还盘3已确认租船4已关闭）终止操作
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateIntentionStatus(Intention vo, CurrentUser user) {

		//取得uuid
		String uuid = vo.getUuid();

		//根据询盘uuid查询询盘信息
		Intention intention = this.findByUuid(uuid);
		if (intention == null) {
			throw new TransportException(TransportException.TYPE.ITSH405);
		}

		//判断询盘状态
		if (intention.getStatus().equals("4")) {
			throw new TransportException(TransportException.TYPE.ITSH406);
		}
		

		//终止操作，设置询盘状态为 "4已关闭"
		intention.setStatus(Constants.INTENTION_STATUS_4);
		intention.setUpdateDate(DateTimeUtils.currentDate());
		intention.setUpdateUser(user.getMemberId());

		//修改询盘状态
		this.updateRecord(intention);
		
		
		//发送消息
		if(intention.getEpMemberId()!=null && user.getMemberId().equals(intention.getCreateUser())){		
			//12询盘人终止询盘-询盘
			messagePushService.messagePush(12, intention.getIntentionId(), user.getMemberId());
		}else if(intention.getShipPlateMemberId()!=null && user.getEpMemberId().equals(intention.getShipPlateMemberId())){	
			//11船盘发布人终止询盘-询盘
			messagePushService.messagePush(11, intention.getIntentionId(), user.getMemberId());
		}
		
	}

	/**
	 * 修改询盘信息（提交还盘）
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateIntention(Intention vo, CurrentUser user) {

		//获取uuid
		String uuid = vo.getUuid();

		//询盘uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH404);
		}
		
		Intention intention = this.findByUuid(uuid);

		
		//校验当前船盘的状态
		String shipPlateUuid = intention.getShipPlateUuid();
		ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
		if(shipPlate==null){
			throw new TransportException(TYPE.ITSH013);
		}else{
			if(!(shipPlate.getStatus().equals(Constants.SHIP_PLATE_STATUS_2))){		//不是“2 洽谈中”
				throw new TransportException(TYPE.ITSH014);
			}
		}
		
		
		//校验询盘状态
		if(intention != null && !(intention.getStatus().equals(Constants.INTENTION_STATUS_1))){	//不是“ 1已询盘”
			throw new TransportException(TYPE.ITSH406);
		}
		

		vo.setIntentionId(intention.getIntentionId());
		vo.setStatus(Constants.INTENTION_STATUS_2);	//修改询盘状态为“已还盘”
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(user.getMemberId());
		//修改询盘信息
		this.updateRecord(vo);
		
		//发送消息	9还盘-询盘
		messagePushService.messagePush(9, vo.getIntentionId(), user.getMemberId());
	}

	/**
	 * 查询询船盘信息翻页列表（船东/船东经纪人）
	 */
	@Override
	public Page<Map<String,Object>> getIntentionPageList(IntentionQuery query, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		//根据询盘单编号、船盘发布人，条件查询
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = query.getIntentionCode();
		/*if(intentionCode!=null && intentionCode.replaceAll(" +","").equals("")){
			map.put("intentionCode", null);
		}else{
			map.put("intentionCode", intentionCode);
		}*/
		
		map.put("intentionCode", intentionCode);
		map.put("shipPlateMemberId", user.getEpMemberId());
		
		Page<Map<String,Object>> page = this.queryRecords(map, query.getPageInfo());
		
		//设置船名
		if(page !=null && page.size()>0){
			for(Map<String,Object> maps:page){
				//获取船盘信息
				String shipPlateUuid = (String) maps.get("shipPlateUuid");
				if(!StringUtils.isNullOrEmpty(shipPlateUuid)){
					ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
					if (shipPlate != null && !StringUtils.isNullOrEmpty(shipPlate.getName())) {
						maps.put("shipName", shipPlate.getName());	//获取船名
					}
				}
				
				//根据语言环境设置 epMemberName、shipPlateMemberName
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=maps.get("epMemberNameEn");
					Object obj2=maps.get("epMemberId");
					if(obj1!=null){
						maps.put("epMemberName", (String)obj1);
					}else if(obj2!=null){
						maps.put("epMemberName", _commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipPlateMemberName
					Object obj3=maps.get("shipPlateMemberNameEn");
					Object obj4=maps.get("shipPlateMemberId");
					if(obj3!=null){
						maps.put("shipPlateMemberName", (String)obj3);
					}else if(obj4!=null){
						maps.put("shipPlateMemberName", _commonService.findNameByEpMemberId((Long)obj4));
					}
				}
			}
		}
		return page;
	}

	/**
	 * 查询询船盘信息翻页列表（货主/代理）
	 */
	@Override
	public Page<Map<String, Object>> findIntentionPageList(IntentionQuery query, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		//根据询盘单编号、船盘发布人，条件查询
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", query.getIntentionCode());
		map.put("epMemberId", user.getEpMemberId());
		
		Page<Map<String,Object>> page = this.queryRecords(map, query.getPageInfo());
		
		//设置船名
		if(page !=null && page.size()>0){
			for(Map<String,Object> maps:page){
				String shipPlateUuid = (String) maps.get("shipPlateUuid");
				if(!StringUtils.isNullOrEmpty(shipPlateUuid)){
					//获取船盘信息
					ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
					if (shipPlate != null && !StringUtils.isNullOrEmpty(shipPlate.getName())) {
						maps.put("shipName", shipPlate.getName());	//获取船名
					}
					
					//根据语言环境设置 epMemberName、shipPlateMemberName
					if ("en".equals(lang)){
						//epMemberName
						Object obj1=maps.get("epMemberNameEn");
						Object obj2=maps.get("epMemberId");
						if(obj1!=null){
							maps.put("epMemberName", (String)obj1);
						}else if(obj2!=null){
							maps.put("epMemberName", _commonService.findNameByEpMemberId((Long)obj2));
						}
						//shipPlateMemberName
						Object obj3=maps.get("shipPlateMemberNameEn");
						Object obj4=maps.get("shipPlateMemberId");
						if(obj3!=null){
							maps.put("shipPlateMemberName", (String)obj3);
						}else if(obj4!=null){
							maps.put("shipPlateMemberName", _commonService.findNameByEpMemberId((Long)obj4));
						}
					}
				}
			}
		}
		return page;
	}

	/**
	 * 询盘详细信息（船盘uuid、status、船盘发布人）
	 */
	@Override
	public Map<String, List<Map<String, Object>>> findCharterShipConfirm(Intention vo, CurrentUser user) {
		Map<String, List<Map<String, Object>>> resultMap=new HashMap<>();
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		

		//获取租船确认信息（还盘信息）
		Map<String, Object> map=new HashMap<>();
		map.put("shipPlateUuid", vo.getShipPlateUuid());
		map.put("shipPlateMemberId", user.getEpMemberId());
		map.put("status", Constants.INTENTION_STATUS_3);	//租船意向状态，3已确认租船
		List<Map<String, Object>> intentionList = this.queryRecords(map);
		
		if(intentionList!=null){
			if(intentionList.size()>1){
				//询盘多条异常
				throw new TransportException(TransportException.TYPE.ITSH400);
			}
		}
		
		//根据语言环境设置 epMemberName、shipPlateMemberName
		if(intentionList!=null && intentionList.size()>0){
			for (Map<String, Object> intentionMap : intentionList) {
				//根据语言环境设置 epMemberName、shipPlateMemberName
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=intentionMap.get("epMemberNameEn");
					Object obj2=intentionMap.get("epMemberId");
					if(obj1!=null){
						intentionMap.put("epMemberName", (String)obj1);
					}else if(obj2!=null){
						intentionMap.put("epMemberName", _commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipPlateMemberName
					Object obj3=intentionMap.get("shipPlateMemberNameEn");
					Object obj4=intentionMap.get("shipPlateMemberId");
					if(obj3!=null){
						intentionMap.put("shipPlateMemberName", (String)obj3);
					}else if(obj4!=null){
						intentionMap.put("shipPlateMemberName", _commonService.findNameByEpMemberId((Long)obj4));
					}
				}
			}
		}
		resultMap.put("intentionList", intentionList);

		
		//获取报盘信息
		Map<String, Object> map2=new HashMap<>();
		map2.put("epMemberId", user.getEpMemberId());
		map2.put("shipPlateUuid", vo.getShipPlateUuid());
		map2.put("status", Constants.CLAUSE_STATUS_2);	//报盘状态，2已确认
		List<Map<String, Object>> clauseList = _clauseService.queryRecords(map2);
		
		//根据语言环境设置 epMemberName、palletMemberName
		if(clauseList!=null && clauseList.size()>0){
			for (Map<String, Object> clauseMap : clauseList) {
				if ("en".equals(lang)){
					clauseMap.put("epMemberName", clauseMap.get("epMemberNameEn"));
					clauseMap.put("palletMemberName", clauseMap.get("palletMemberNameEn"));
				}
			}
		}
		resultMap.put("clauseList", clauseList);
		
		return resultMap;
	}

	/**
	 * 修改询盘状态（确认成交）
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateConfirmTransaction(Intention vo, CurrentUser user) {

		//查询船盘信息
		String shipPlateUuid = vo.getShipPlateUuid();
		ShipPlate shipPlate = _ShipPlateService.findByUuid(shipPlateUuid);
		
		//查询货盘信息
		String palletUuid = vo.getPalletUuid();
		Pallet pallet = _PalletService.findByUuid(palletUuid);
		
		
		//校验，当前询的船盘，关联的其它询盘的状态是否有为“3已确认租船”，有，询盘确认失败
		Intention intentionQuery=new Intention();
		intentionQuery.setShipPlateUuid(shipPlateUuid);
		List<Intention> intentions = this.queryByEntitys(intentionQuery);
		if(intentions!=null && intentions.size()>0){
			for (Intention in : intentions) {
				String status = in.getStatus();
				if(status.equals("3")){
					throw new TransportException(TYPE.ITSH014);
				}
			}
		}
		//校验，当前询的船盘，关联的其他的报盘的状态是否有为“2已确认”，有，询盘确认失败
		Clause clauseQuery=new Clause();
		clauseQuery.setShipPlateUuid(shipPlateUuid);
		List<Clause> clauseList = _clauseService.queryByEntitys(clauseQuery);
		if(clauseList!=null && clauseList.size()>0){
			for (Clause clause : clauseList) {
				if(clause.getStatus().equals("2")){
					throw new TransportException(TYPE.ITSH014);
				}
			}
		}
				
				
		//查询询盘信息
		String uuid = vo.getUuid();
		Intention intention = this.findByUuid(uuid);
		if (intention == null) {
			throw new TransportException(TransportException.TYPE.ITSH405);
		}
		//校验询盘的状态
		if (!(intention.getStatus()).equals("2")) {
			throw new TransportException(TransportException.TYPE.ITSH406);
		}

		//修改船盘状态（3已确认租船）
		if(shipPlate.getStatus().equals("2")){
			ShipPlate shipPlateUPdate=new ShipPlate();
			shipPlateUPdate.setShipPlateId(shipPlate.getShipPlateId());
			shipPlateUPdate.setStatus(Constants.SHIP_PLATE_STATUS_3);
			shipPlateUPdate.setUpdateDate(DateTimeUtils.currentDate());
			shipPlateUPdate.setUpdateUser(user.getMemberId());
			_ShipPlateService.updateRecord(shipPlateUPdate);
		}
		
		//修改询盘的状态（3已确认租船）
		Intention intentionUPdate=new Intention();
		intentionUPdate.setIntentionId(intention.getIntentionId());
		intentionUPdate.setStatus(Constants.INTENTION_STATUS_3);
		intentionUPdate.setUpdateDate(DateTimeUtils.currentDate());
		intentionUPdate.setUpdateUser(user.getMemberId());
		this.updateRecord(intentionUPdate);
		
		//修改货盘状态（4已确认）
		if(pallet.getStatus().equals("2")){
			Pallet palletUpdate=new Pallet();
			palletUpdate.setPalletId(pallet.getPalletId());
			palletUpdate.setStatus(Constants.PALLET_STATUS_4);
			palletUpdate.setUpdateDate(DateTimeUtils.currentDate());
			palletUpdate.setUpdateUser(user.getMemberId());
			_PalletService.updateRecord(palletUpdate);
		}
		
		
		//修改与当前货盘关联的其它询盘的状态（4已关闭）
		Intention in2=new Intention();
		in2.setPalletUuid(palletUuid);
		List<Intention> intentionList = this.queryByEntitys(in2);	//根据货盘uuid查询所有询盘对象
		List<String> intentionUuids_pallet=new ArrayList<>();
		for (Intention inObj : intentionList) {
			intentionUuids_pallet.add(inObj.getUuid());
		}
		if(intentionUuids_pallet.contains(uuid)){
			intentionUuids_pallet.remove(uuid);	//从所有询盘中移除当前询盘
		}
		for (String otherUuid : intentionUuids_pallet) {	//修改其它询盘状态
			Intention otherIntention = this.findByUuid(otherUuid);
			otherIntention.setStatus(Constants.INTENTION_STATUS_4);
			otherIntention.setUpdateDate(DateTimeUtils.currentDate());
			otherIntention.setUpdateUser(user.getMemberId());
			this.updateRecord(otherIntention);
		}
		
		//修改与当前船盘关联的其它询盘的状态（4已关闭）
		Intention in=new Intention();
		in.setShipPlateUuid(shipPlateUuid);
		List<Intention> list = this.queryByEntitys(in);	//根据船盘uuid查询所有询盘对象
		List<String> intentionUuids_shipPlate=new ArrayList<>();
		for (Intention inObj : list) {
			intentionUuids_shipPlate.add(inObj.getUuid());
		}
		if(intentionUuids_shipPlate.contains(uuid)){
			intentionUuids_shipPlate.remove(uuid);	//从所有询盘中移除当前询盘
		}
		for (String otherUuid : intentionUuids_shipPlate) {	//修改其它询盘状态
			Intention otherIntention = this.findByUuid(otherUuid);
			otherIntention.setStatus(Constants.INTENTION_STATUS_4);
			otherIntention.setUpdateDate(DateTimeUtils.currentDate());
			otherIntention.setUpdateUser(user.getMemberId());
			this.updateRecord(otherIntention);
		}
		
		//发送消息，10确认租船-询盘
		messagePushService.messagePush(10, intention.getIntentionId(), user.getMemberId());
		
	}
	/**
	 * 根据船盘uuid-批量修改状态
	 */
	public void changeStatus(Map<String, Object> map) {
		 _IntentionMapper.changeStatus(map);
	}

}
