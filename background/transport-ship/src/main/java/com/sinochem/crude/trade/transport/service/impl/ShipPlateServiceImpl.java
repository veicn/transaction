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
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException.TYPE;
import com.sinochem.crude.trade.transport.dao.ShipPlateMapper;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.model.ShipPlateVO;
import com.sinochem.crude.trade.transport.model.res.ShipPlateImport;
import com.sinochem.crude.trade.transport.query.SysShipQuery;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;

@Service
public class ShipPlateServiceImpl implements ShipPlateService {
	
	@Autowired
	private ShipPlateMapper _ShipPlateMapper;
	@Autowired
	private SysShipService _sysShipService;
	@Autowired  
	private CommonService _commonService;
	@Autowired  
	private IntentionService intentionService; 
	@Autowired  
	private ClauseService clauseService; 
	@Autowired
	private MessagePushService messagePushService;
	Log log = LogFactory.getLog(ShipPlateServiceImpl.class);
	
	public ShipPlateMapper getMapper(){
		return _ShipPlateMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<ShipPlate> queryByEntitys(ShipPlate shipplate){
		return  _ShipPlateMapper.queryByEntitys(shipplate);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public ShipPlate findByPrimaryKey(Long shipPlateId){
		return  _ShipPlateMapper.findByPrimaryKey(shipPlateId);	
	}
	
	/**
	 * 根据UUID-查询对象
	 */
	public ShipPlate findByUuid(String uuid){
		return  _ShipPlateMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(ShipPlate shipplate) {
		 _ShipPlateMapper.updateRecord(shipplate);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long shipPlateId  , Long deleteUser) {
		 _ShipPlateMapper.deleteRecordByKey(shipPlateId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(ShipPlate shipplate){
		 _ShipPlateMapper.insertRecord(shipplate);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _ShipPlateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ShipPlateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _ShipPlateMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_ShipPlateMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_ShipPlateMapper.updateRecords(map);
	}
	
	/**
	 * 根据uuid-批量修改数据
	 */
	public void updateRecordsFn(Map<String, Object> map) {
		_ShipPlateMapper.updateRecordsFn(map);
	}
	

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据条件-分页查询（船东/经纪人，转租船东，查询，包括平台辅助导入）
	 */
	public Page<Map<String, Object>> queryRecordsAll(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ShipPlateMapper.queryRecordsAll(map);
	}
	
	/**
	 * 根据条件-查询记录条数（船东/经纪人，转租船东，查询，包括平台辅助导入）
	 */
	public int countRecordsAll(Map<String, Object> map){
		return _ShipPlateMapper.countRecordsAll(map);
	}
	
	/**
	 * 根据主键-查询详细
	 */
	public List<Map<String,Object>> findByPrimaryKeyForground(Long shipPlateId){
		return  _ShipPlateMapper.queryByPrimaryKeyForground(shipPlateId);	
	}
	
	/**
	 * 根据条件-分页查询（om平台）
	 */
	@Override
	public Page<Map<String, Object>> queryRecordsOM(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ShipPlateMapper.queryRecordsOM(map);
	}
	
	/**
	 * 添加船盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED)
	public void saveShipPlate(ShipPlate vo,CurrentUser user) {
		
		//登录用户的企业id
		Long epMemberId=user.getEpMemberId();
		//登录用户的用户编号
		Long memberId=user.getMemberId();
		

		//设置船盘发布时间
		Date publishTime = DateTimeUtils.currentDate();
		vo.setPublishTime(publishTime);
		
		//设置有效日期
		Integer period = vo.getPeriod();   //获取时效
		Date periodDate = DateTimeUtils.IncreaseDays(publishTime, period);
		vo.setPeriodDate(periodDate);
		
		//判断用户角色，获取船盘发布类型(船东/经纪人或二船东)
		String relType="";
		String[] roles = user.getRoles();
		Boolean ShipOwnerBool=false;	//当前用户是否具有船东/船东经纪人角色
		Boolean ShipOwnerTowrBool=false;	//当前用户是否具有二船东角色
		if(roles!=null && roles.length>0){
			for (String role : roles) {
				if(role.equals("ship_owner") || role.equals("ship_broker")){	//1船东/船东经纪人
					relType="1";	
					ShipOwnerBool=true;
				}
				if(role.equals("ship_trader") || role.equals("ship_executor")){	//2“转租船东”
					relType="2";	
					ShipOwnerTowrBool=true;
				}
			}
		}
		if(ShipOwnerBool && ShipOwnerTowrBool){	//若当前用户具有多角色
			//TODO:ZhengC 船盘新增，若当前用户具有多角色，报错
			throw new TransportException(TYPE.ITSH415);	
		}
		if(relType.equals("")){
			throw new TransportException(TransportException.TYPE.ITSH407);
		}
		
		
		//leaveFlag是否执行中，设置为零
		vo.setLeaveFlag("0");
		
		//获取email
		String email="";
		EnterpriseVo enterpriseVo = _commonService.queryUserByEpMemberId(epMemberId);	//查询企业信息
		if (enterpriseVo != null){
			email=enterpriseVo.getEmail();
		}
		
		//计算船龄
		if(!StringUtils.isNullOrEmpty(vo.getCompleteDate())){
			int completeYear = Integer.parseInt(vo.getCompleteDate());
			int currentYear = DateTimeUtils.currentYear();
			int shipAge=currentYear-completeYear;
			if(shipAge==0){
				shipAge=1;
			}else if(shipAge<0){
				shipAge=0;
			}
			vo.setShipAge(String.valueOf(shipAge));
		}
				
		
		//设置发布人（中/英）
		vo.setEpMemberName(enterpriseVo.getFullName());
		vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		//设置经纪人（中/英）
		if(vo.getBrokerId()!=null){
			EnterpriseVo enterpriseVo2 = _commonService.queryUserByEpMemberId(vo.getBrokerId());
			vo.setBrokerName(enterpriseVo2.getFullName());
			vo.setBrokerNameEn(enterpriseVo2.getEnglishName());
		}else{
			vo.setBrokerNameEn(vo.getBrokerName());	/**批量导入的数据，中英文名相同*/
		}
		//设置船东（中/英）
		if(vo.getShipOwnerId()!=null){
			EnterpriseVo enterpriseVo3 = _commonService.queryUserByEpMemberId(vo.getShipOwnerId());
			vo.setShipOwner(enterpriseVo3.getFullName());
			vo.setShipOwnerEn(enterpriseVo3.getEnglishName());
		}else{
			vo.setShipOwnerEn(vo.getShipOwner());	/**批量导入的数据，中英文名相同*/
		}
		
		
		//获取船舶imo、id
		String sysShipUuid = vo.getSysShipUuid();
		SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
		String imo = sysShip.getImo();
		Long sysShipId = sysShip.getSysShipId();

		
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setStatus(Constants.SHIP_PLATE_STATUS_1);
		vo.setEpMemberId(epMemberId);
		vo.setRelType(relType);
		vo.setEmail(email);
		vo.setImo(imo);
		vo.setSysShipId(sysShipId);
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(memberId);
		vo.setCreateUser(memberId);
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		
		//添加船盘信息
		this.insertRecord(vo);
		System.out.println(vo.getShipPlateId());
	}
	
	
	/**
	 * 批量添加船盘信息（OM平台）
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED)
	public void saveShipPlateOM(ShipPlate vo,CurrentUser user) {

		//登录用户的用户编号
		Long memberId=user.getMemberId();
		//平台选择的公司
		Long epMemberId = vo.getEpMemberId();

		
		//设置船盘发布时间
		Date publishTime = DateTimeUtils.currentDate();
		vo.setPublishTime(publishTime);
		
		//设置有效日期
		Integer period = vo.getPeriod();   //获取时效
		Date periodDate = DateTimeUtils.IncreaseDays(publishTime, period);
		vo.setPeriodDate(periodDate);
		
		//leaveFlag是否执行中，设置为零
		vo.setLeaveFlag("0");
		
		//判断用户角色，获取船盘发布类型(船东/经纪人或二船东)
		String relType="3";	//om平台
		
		
		if(epMemberId!=null){
			//获取email
			String email="";
			EnterpriseVo enterpriseVo = _commonService.queryUserByEpMemberId(epMemberId);	//查询企业信息
			if (enterpriseVo != null){
				email=enterpriseVo.getEmail();
			}
			vo.setEmail(email);
			
			//设置发布人（中/英）
			vo.setEpMemberName(enterpriseVo.getFullName());
			vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		}
		
		//设置船东中英文
		vo.setShipOwnerEn(vo.getShipOwner());
		
		//计算船龄
		if(!StringUtils.isNullOrEmpty(vo.getCompleteDate())){
			int completeYear = Integer.parseInt(vo.getCompleteDate());
			int currentYear = DateTimeUtils.currentYear();
			int shipAge=currentYear-completeYear;
			if(shipAge==0){
				shipAge=1;
			}else if(shipAge<0){
				shipAge=0;
			}
			vo.setShipAge(String.valueOf(shipAge));
		}
		
		
		//获取船舶imo、id
		String sysShipUuid = vo.getSysShipUuid();
		SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
		String imo = sysShip.getImo();
		Long sysShipId = sysShip.getSysShipId();
		
		
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setStatus(Constants.SHIP_PLATE_STATUS_1);
		vo.setRelType(relType);
		vo.setImo(imo);
		vo.setSysShipId(sysShipId);
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(memberId);
		vo.setCreateUser(memberId);
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		
		//添加船盘信息
		this.insertRecord(vo);
		System.out.println(vo.getShipPlateId());
	}
	

	/**
	 * 修改船盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateShipPlate(ShipPlate vo,Long epMemberId,Long memberId) {

		String uuid = vo.getUuid();
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
			
		//设置船盘发布时间
		Date publishTime = DateTimeUtils.currentDate();
		vo.setPublishTime(publishTime);
		
		//设置有效日期
		Integer period = vo.getPeriod();   //获取时效
		if(period==null){
			throw new TransportException(TransportException.TYPE.ITSH408);
		}
		Date periodDate = DateTimeUtils.IncreaseDays(publishTime, period);
		vo.setPeriodDate(periodDate);
		
		
		//根据船舶uuid，重新设置船舶imo、id
		if(!StringUtils.isNullOrEmpty(vo.getSysShipUuid())){
			String sysShipUuid = vo.getSysShipUuid();
			SysShip sysShip = _sysShipService.findByUuid(sysShipUuid);
			String imo = sysShip.getImo();
			Long sysShipId = sysShip.getSysShipId();
			vo.setImo(imo);
			vo.setSysShipId(sysShipId);
		}
		
		
		EnterpriseVo enterpriseVo = _commonService.queryUserByEpMemberId(epMemberId);
		
		//设置发布人（中/英）
		vo.setEpMemberName(enterpriseVo.getFullName());
		vo.setEpMemberNameEn(enterpriseVo.getEnglishName());
		//设置经纪人（中/英）
		if(vo.getBrokerId()!=null){
			EnterpriseVo enterpriseVo2 = _commonService.queryUserByEpMemberId(vo.getBrokerId());
			vo.setBrokerName(enterpriseVo2.getFullName());
			vo.setBrokerNameEn(enterpriseVo2.getEnglishName());
		}
		//设置船东（中/英）
		if(vo.getShipOwnerId()!=null){
			EnterpriseVo enterpriseVo3 = _commonService.queryUserByEpMemberId(vo.getShipOwnerId());
			vo.setShipOwner(enterpriseVo3.getFullName());
			vo.setShipOwnerEn(enterpriseVo3.getEnglishName());
		}
		
		
		vo.setShipPlateId(shipPlate.getShipPlateId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(memberId);
		
		// 修改船盘信息
		this.updateRecord(vo);
		System.out.println(vo.getShipPlateId());
	}

	
	/**
	 * 修改船盘状态（1已发布2洽谈中3已确认租船）(作废)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateShipPlateStatus(ShipPlate vo,String saveType,CurrentUser user) {
		
		//取得uuid
		String uuid = vo.getUuid();
		
		//船盘uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		//判断船盘操作类型
		if(saveType.equals("3")){	// saveType=3：确认发布，保存后船盘状态为：已发布
			if(StringUtils.isNullOrEmpty(shipPlate.getStatus()) || !(shipPlate.getStatus()).equals("5")){
				throw new TransportException(TransportException.TYPE.ITSH014);
			}
			vo.setStatus(Constants.SHIP_PLATE_STATUS_1);
			
			//设置发布日期
			Date publishTime = DateTimeUtils.currentDate();
			vo.setPublishTime(publishTime);
			
			//设置有效日期
			Integer period = shipPlate.getPeriod();   //获取时效
			if(period==null){
				//period=7;	//时效赋默认值，7天
				throw new TransportException(TransportException.TYPE.ITSH408);
			}
			Date periodDate = DateTimeUtils.IncreaseDays(publishTime, period);
			vo.setPeriodDate(periodDate);
		}
		
		vo.setShipPlateId(shipPlate.getShipPlateId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		
		//修改船盘状态
		this.updateRecord(vo);
		System.out.println(vo.getShipPlateId());
	}

	
	/**
	 * 删除船盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delShipPlate(ShipPlate vo, CurrentUser user) {
		
		String uuid = vo.getUuid();
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		if(shipPlate.getStatus().equals("3")){	//已确认租船，撤销抛异常
			throw new TransportException(TYPE.ITSH014);
		}
		
		//校验，若船盘状态为“洽谈中”，撤销操作，需提醒已询盘的货主
		if(shipPlate.getStatus().equals("2")){
			//并关闭与当前船盘关联的所有询盘、报盘(转租船东)
			//改变询盘状态“4已关闭”，关闭1、2状态
			Map<String, Object> inMap=new HashMap<>();
			inMap.put("shipPlateUuid", uuid);
			inMap.put("shipPlateMemberId",user.getEpMemberId());
			inMap.put("status", Constants.INTENTION_STATUS_3);	//查询状态不为“3已确认租船”
			List<Map<String, Object>> inList = intentionService.findPageListStatusEqualsNo(inMap);
			if(inList!=null && !inList.isEmpty()){
				for (Map<String, Object> map : inList) {
					Intention intentionUpdate=new Intention();
					intentionUpdate.setIntentionId((Long)(map.get("intentionId")));
					intentionUpdate.setStatus(Constants.INTENTION_STATUS_4);
					intentionUpdate.setUpdateDate(DateTimeUtils.currentDate());
					intentionUpdate.setUpdateUser(user.getMemberId());
					intentionService.updateRecord(intentionUpdate);
				}
			}

			
			//若是转租船东，还需修改报盘状态
			Boolean bool=false;
			String[] roles = user.getRoles();
			for (String role : roles) {
				if(role.equals("ship_trader") || role.equals("ship_executor")){	//转租船东
					bool=true;
				}
			}
			if(bool){
				//改变报盘状态“3已关闭”，关闭 1状态
				Clause clauseVo=new Clause();
				clauseVo.setShipPlateUuid(uuid);
				clauseVo.setEpMemberId(user.getEpMemberId());
				clauseVo.setStatus(Constants.CLAUSE_STATUS_1);
				List<Clause> clauseList = clauseService.queryByEntitys(clauseVo);
				if(clauseList!=null && !clauseList.isEmpty()){
					for (Clause clause : clauseList) {
						Clause clauseUpdate=new Clause();
						clauseUpdate.setClauseId(clause.getClauseId());
						clauseUpdate.setStatus(Constants.CLAUSE_STATUS_3);
						clauseUpdate.setUpdateDate(DateTimeUtils.currentDate());
						clauseUpdate.setUpdateUser(user.getMemberId());
						clauseService.updateRecord(clauseUpdate);
					}
				}
			}
		}
		
		
		// 取得船盘主键
		Long shipPlateId = shipPlate.getShipPlateId();
		
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		
		// 删除船盘信息
		this.deleteRecordByKey(shipPlateId, user.getMemberId());
		System.out.println(vo.getShipPlateId());
	}
	
	
	/**
	 * 删除船盘信息（om平台）
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delShipPlateOM(ShipPlate vo, CurrentUser user) {
		
		String uuid = vo.getUuid();
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		if(shipPlate.getStatus().equals(Constants.SHIP_PLATE_STATUS_2) || shipPlate.getStatus().equals(Constants.SHIP_PLATE_STATUS_3)){		/**已确认租船/洽谈中*/
			throw new TransportException(TYPE.ITSH014);
		}
		
		// 取得船盘主键
		Long shipPlateId = shipPlate.getShipPlateId();
		
		shipPlate.setUpdateDate(DateTimeUtils.currentDate());
		shipPlate.setUpdateUser(user.getMemberId());
		
		// 删除船盘信息
		this.deleteRecordByKey(shipPlateId, user.getMemberId());
		System.out.println(vo.getShipPlateId());
	}
	
	
	/**
	 * 平台批量删除船盘信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchDelShipPlateOM(String uuid, CurrentUser user) {
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		if(shipPlate.getStatus().equals("3")){	//已确认租船，撤销抛异常
			throw new TransportException(TYPE.ITSH014);
		}
		
		//校验，若船盘状态为“洽谈中”，撤销操作，需提醒已询盘的货主
		if(shipPlate.getStatus().equals("2")){
			//并关闭与当前船盘关联的所有询盘、报盘(转租船东)
			//改变询盘状态“4已关闭”，关闭1、2状态
			Map<String, Object> inMap=new HashMap<>();
			inMap.put("shipPlateUuid", uuid);
			inMap.put("shipPlateMemberId",user.getEpMemberId());
			inMap.put("status", Constants.INTENTION_STATUS_3);	//查询状态不为“3已确认租船”
			List<Map<String, Object>> inList = intentionService.findPageListStatusEqualsNo(inMap);
			if(inList!=null && !inList.isEmpty()){
				for (Map<String, Object> map : inList) {
					Intention intentionUpdate=new Intention();
					intentionUpdate.setIntentionId((Long)(map.get("intentionId")));
					intentionUpdate.setStatus(Constants.INTENTION_STATUS_4);
					intentionUpdate.setUpdateDate(DateTimeUtils.currentDate());
					intentionUpdate.setUpdateUser(user.getMemberId());
					intentionService.updateRecord(intentionUpdate);
				}
			}
			
			
			//若是转租船东，还需修改报盘状态
			Boolean bool=false;
			String[] roles = user.getRoles();
			for (String role : roles) {
				if(role.equals("ship_trader") || role.equals("ship_executor")){	//转租船东
					bool=true;
				}
			}
			if(bool){
				//改变报盘状态“3已关闭”，关闭 1状态
				Clause clauseVo=new Clause();
				clauseVo.setShipPlateUuid(uuid);
				clauseVo.setEpMemberId(user.getEpMemberId());
				clauseVo.setStatus(Constants.CLAUSE_STATUS_1);
				List<Clause> clauseList = clauseService.queryByEntitys(clauseVo);
				if(clauseList!=null && !clauseList.isEmpty()){
					for (Clause clause : clauseList) {
						Clause clauseUpdate=new Clause();
						clauseUpdate.setClauseId(clause.getClauseId());
						clauseUpdate.setStatus(Constants.CLAUSE_STATUS_3);
						clauseUpdate.setUpdateDate(DateTimeUtils.currentDate());
						clauseUpdate.setUpdateUser(user.getMemberId());
						clauseService.updateRecord(clauseUpdate);
					}
				}
			}
		}
		
		// 取得船盘主键
		Long shipPlateId = shipPlate.getShipPlateId();
		// 删除船盘信息
		this.deleteRecordByKey(shipPlateId, user.getMemberId());
	}

	/**
	 * 查询船盘信息详细
	 */
	@Override
	public ShipPlate findShipPlateDetail(ShipPlate vo) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		// 取得uuid
		String uuid = vo.getUuid();
		// 船盘uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		// 取得船盘主键
		Long shipPlateId = shipPlate.getShipPlateId();
		// 查询船盘信息
		ShipPlate shipPlateDetail = this.findByPrimaryKey(shipPlateId);
		
		
		/**
		 * 根据语言环境设置epMemberName、shipOwner、broker
		 */
		if ("en".equals(lang)){
			//epMemberName
			if(!StringUtils.isNullOrEmpty(shipPlateDetail.getEpMemberNameEn())){
				shipPlateDetail.setEpMemberName(shipPlateDetail.getEpMemberNameEn());
			}else if(shipPlateDetail.getEpMemberId()!=null){
				shipPlateDetail.setEpMemberName(_commonService.findNameByEpMemberId(shipPlateDetail.getEpMemberId()));
			}
			//shipOwner
			if(!StringUtils.isNullOrEmpty(shipPlateDetail.getShipOwnerEn())){
				shipPlateDetail.setShipOwner(shipPlateDetail.getShipOwnerEn());
			}else if(shipPlateDetail.getShipOwnerId()!=null){
				shipPlateDetail.setShipOwner(_commonService.findNameByEpMemberId(shipPlateDetail.getShipOwnerId()));
			}
			//broker
			if(!StringUtils.isNullOrEmpty(shipPlateDetail.getBrokerNameEn())){
				shipPlateDetail.setBrokerName(shipPlateDetail.getBrokerNameEn());
			}else if(shipPlateDetail.getBrokerId()!=null){
				shipPlateDetail.setBrokerName(_commonService.findNameByEpMemberId(shipPlateDetail.getBrokerId()));
			}
		}
		
		return shipPlateDetail;
	}
	

	/**
	 * 查询船盘信息列表(前台接口)
	 */
	@Override
	public List<Map<String,Object>> findShipPlateList(ShipPlate vo) {
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		// 查询船盘信息列表
		List<Map<String,Object>> shipPlateList = (List<Map<String,Object>>)_ShipPlateMapper.queryByEntitysForground(vo);
		
		
		/**
		 * 根据语言环境设置epMemberName、shipOwner、broker
		 */
		if(shipPlateList!=null && !shipPlateList.isEmpty()){
			for (Map<String,Object> map : shipPlateList) {
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=map.get("epMemberNameEn");
					Object obj2=map.get("epMemberId");
					if(obj1!=null){
						map.put("epMemberName",(String)obj1);
					}else if(obj2!=null){
						map.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipOwner
					Object obj3=map.get("shipOwnerEn");
					Object obj4=map.get("shipOwnerId");
					if(obj3!=null){
						map.put("shipOwner",(String)obj3);
					}else if(obj4!=null){
						map.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
					}
					//broker
					Object obj5=map.get("brokerNameEn");
					Object obj6=map.get("brokerId");
					if(obj5!=null){
						map.put("brokerName",(String)obj5);
					}else if(obj6!=null){
						map.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
					}
				}
			}
		}
		
		return shipPlateList;
	}
	
	
	/**
	 * 查询船盘信息翻页列表
	 */
	@Override
	public Page<ShipPlate> findShipPlatePageList(ShipPlate vo, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<ShipPlate> shipPlateList = (Page<ShipPlate>)this.queryByEntitys(vo);
		
		return shipPlateList;
	}
	
	/**
	 * 查询船盘信息翻页列表
	 */
	@Override
	public Page<Map<String,Object>> getShipPlatePageList(SysShipQuery query, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		// 船舶名称
		Map<String, Object> map = new HashMap<>();
		map.put("name", query.getName());
		map.put("status", query.getStatus());
		map.put("epMemberId", user.getEpMemberId());
		map.put("relType", query.getRelType());		//1船东/船东经纪人2转租船东
		
		Page<Map<String,Object>> page = this.queryRecordsAll(map, query.getPageInfo());
		

		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");		/**添加发布日期、open的字符串*/
		
		if(page!=null && !page.isEmpty()){
			for (Map<String, Object> shipPlateMap : page) {
				//发布日期
				Date publishTime=(Date)shipPlateMap.get("publishTime");
				if(publishTime!=null){
					String dateStr = sf.format(publishTime);
					shipPlateMap.put("publishTimeStr", dateStr);
				}
				//open
				Date open=(Date)shipPlateMap.get("open");
				if(open!=null){
					String openStr = sf.format(open);
					shipPlateMap.put("openStr", openStr);
				}
				
				//根据语言环境设置epMemberName、shipOwner、broker
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=shipPlateMap.get("epMemberNameEn");
					Object obj2=shipPlateMap.get("epMemberId");
					if(obj1!=null){
						shipPlateMap.put("epMemberName",(String)obj1);
					}else if(obj2!=null){
						shipPlateMap.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipOwner
					Object obj3=shipPlateMap.get("shipOwnerEn");
					Object obj4=shipPlateMap.get("shipOwnerId");
					if(obj3!=null){
						shipPlateMap.put("shipOwner",(String)obj3);
					}else if(obj4!=null){
						shipPlateMap.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
					}
					//broker
					Object obj5=shipPlateMap.get("brokerNameEn");
					Object obj6=shipPlateMap.get("brokerId");
					if(obj5!=null){
						shipPlateMap.put("brokerName",(String)obj5);
					}else if(obj6!=null){
						shipPlateMap.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
					}
				}
			}
		}
		
		return page;
	}
	
	
	/**
	 * 查询船盘信息翻页列表（om平台）
	 */
	@Override
	public Page<Map<String,Object>> getShipPlatePageListOM(SysShipQuery query, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", query.getName());
		map.put("type", query.getType());
		map.put("ageBeg", query.getAgeBeg());
		map.put("ageEnd", query.getAgeEnd());
		map.put("epMemberName", query.getEpMembername());
		map.put("shipOwner", query.getShipOwner());
		map.put("brokerName", query.getBrokerName());
		map.put("publishBeg", query.getPublishBeg());
		map.put("publishEnd", query.getPublishEnd());
		map.put("openBeg", query.getOpenBeg());
		map.put("openEnd", query.getOpenEnd());
		map.put("ETABeg", query.getETABeg());
		map.put("ETAEnd", query.getETAEnd());
		map.put("quantityBeg", query.getQuantityBeg());
		map.put("quantityEnd", query.getQuantityEnd());
		map.put("status", query.getStatus());
		map.put("relType", query.getRelType());
		
		Page<Map<String,Object>> page = this.queryRecordsOM(map, query.getPageInfo());
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");		/**添加发布日期、open的字符串*/
		if(page!=null && !page.isEmpty()){
			for (Map<String, Object> shipPlateMap : page) {
				//发布日期
				Date publishTime=(Date)shipPlateMap.get("publishTime");
				if(publishTime!=null){
					String dateStr = sf.format(publishTime);
					shipPlateMap.put("publishTimeStr", dateStr);
				}
				
				//open
				Date open=(Date)shipPlateMap.get("open");
				if(open!=null){
					String openStr = sf.format(open);
					shipPlateMap.put("openStr", openStr);
				}
				
				//根据语言环境设置epMemberName、shipOwner、broker
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=shipPlateMap.get("epMemberNameEn");
					Object obj2=shipPlateMap.get("epMemberId");
					if(obj1!=null){
						shipPlateMap.put("epMemberName",(String)obj1);
					}else if(obj2!=null){
						shipPlateMap.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipOwner
					Object obj3=shipPlateMap.get("shipOwnerEn");
					Object obj4=shipPlateMap.get("shipOwnerId");
					if(obj3!=null){
						shipPlateMap.put("shipOwner",(String)obj3);
					}else if(obj4!=null){
						shipPlateMap.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
					}
					//broker
					Object obj5=shipPlateMap.get("brokerNameEn");
					Object obj6=shipPlateMap.get("brokerId");
					if(obj5!=null){
						shipPlateMap.put("brokerName",(String)obj5);
					}else if(obj6!=null){
						shipPlateMap.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
					}
				}
			}
		}
		
		return page;
	}
	
	/**
	 * 船盘列表_发送报盘（二船东）
	 */
	@Override
	public Page<Map<String,Object>> sendClauseShipPlateList(SysShipQuery query, CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		Map<String, Object> map = new HashMap<>();
		map.put("leaveFlag", "0");	//不在执行中
		map.put("epMemberId", user.getEpMemberId());
		map.put("relType", "2");	//船盘发布类型（1船东/船东经纪人2转租船东）
		
		Page<Map<String,Object>> page = this.queryRecords(map, query.getPageInfo());
		
		//添加发布日期、open的字符串
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(page!=null && !page.isEmpty()){
			for (Map<String, Object> shipPlateMap : page) {
				//发布日期
				Date publishTime=(Date)shipPlateMap.get("publishTime");
				if(publishTime!=null){
					String dateStr = sf.format(publishTime);
					shipPlateMap.put("publishTimeStr", dateStr);
				}
				//open
				Date open=(Date)shipPlateMap.get("open");
				if(open!=null){
					String openStr = sf.format(open);
					shipPlateMap.put("openStr", openStr);
				}
				
				//根据语言环境设置epMemberName、shipOwner、broker
				if ("en".equals(lang)){
					//epMemberName
					Object obj1=shipPlateMap.get("epMemberNameEn");
					Object obj2=shipPlateMap.get("epMemberId");
					if(obj1!=null){
						shipPlateMap.put("epMemberName",(String)obj1);
					}else if(obj2!=null){
						shipPlateMap.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
					}
					//shipOwner
					Object obj3=shipPlateMap.get("shipOwnerEn");
					Object obj4=shipPlateMap.get("shipOwnerId");
					if(obj3!=null){
						shipPlateMap.put("shipOwner",(String)obj3);
					}else if(obj4!=null){
						shipPlateMap.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
					}
					//broker
					Object obj5=shipPlateMap.get("brokerNameEn");
					Object obj6=shipPlateMap.get("brokerId");
					if(obj5!=null){
						shipPlateMap.put("brokerName",(String)obj5);
					}else if(obj6!=null){
						shipPlateMap.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
					}
				}
			}
		}
		
		return page;
	}
	
	/**
	 * 查询船盘列表
	 */
	@Override
	public Page<Map<String, Object>> queryShipPlateList(Map<String, Object> map, SimplePageInfo pageInfo) {
		Page<Map<String,Object>> page = this.queryRecords(map, pageInfo);
		return page;
	}

	/**
	 * 查询船盘信息详细（包括船舶信息）
	 */
	@Override
	public Map<String, Object> findShipPlateDetailForground(ShipPlate vo,CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();

		
		Map<String, Object> resultMap=new HashMap<>();
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船盘uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		
		//根据船盘uuid查询船盘信息
		ShipPlate shipPlate = this.findByUuid(uuid);
		
		if (shipPlate == null){
			throw new TransportException(TransportException.TYPE.ITSH013);
		}
		
		// 取得船盘主键
		Long shipPlateId = shipPlate.getShipPlateId();
		
		// 查询船盘详细信息
		List<Map<String, Object>> shipPlateList = this.findByPrimaryKeyForground(shipPlateId);
		if(shipPlateList!=null && !shipPlateList.isEmpty()){
			resultMap.putAll(shipPlateList.get(0));
		}
		
		// 获取船舶id
		String UUID = shipPlate.getSysShipUuid();
		
		// 查询船舶详细信息
		Map<String, Object> sysShipList = _sysShipService.findByUUIDForward(UUID);
		resultMap.put("ship",sysShipList);
		
		
		//获取邮件
		String email="";
		try {
			EnterpriseVo byMemberId = _commonService.queryUserByEpMemberId(shipPlate.getEpMemberId());
			email=byMemberId.getEmail();
		} catch (Exception e) {
			log.error("查询企业名称出错：",e);
		}
		resultMap.put("email", email);
		
		
		/**
		 * 根据语言环境设置epMemberName、shipOwner、broker
		 */
		if ("en".equals(lang)){
			//epMemberName、createName
			Object obj1=resultMap.get("epMemberNameEn");
			Object obj2=resultMap.get("epMemberId");
			if(obj1!=null){
				resultMap.put("epMemberName",(String)obj1);
				resultMap.put("createName",(String)obj1);
			}else if(obj2!=null){
				resultMap.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
				resultMap.put("createName",_commonService.findNameByEpMemberId((Long)obj2));
			}
			//shipOwner
			Object obj3=resultMap.get("shipOwnerEn");
			Object obj4=resultMap.get("shipOwnerId");
			if(obj3!=null){
				resultMap.put("shipOwner",(String)obj3);
			}else if(obj4!=null){
				resultMap.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
			}
			//broker
			Object obj5=resultMap.get("brokerNameEn");
			Object obj6=resultMap.get("brokerId");
			if(obj5!=null){
				resultMap.put("brokerName",(String)obj5);
			}else if(obj6!=null){
				resultMap.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
			}
		}
		
		return resultMap;
	}
	
	/**
	 * 查询全部船盘信息列表(前台接口)
	 */
	@Override
	public Page<Map<String,Object>> queryShipPlatePageList(ShipPlate vo,SimplePageInfo pageInfo) {
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		// 查询船盘信息列表
		Page<Map<String,Object>> page = (Page<Map<String,Object>>)_ShipPlateMapper.queryShipPlatePageList(vo);
		
		return page;
	}

	/**
	 * 推荐船东/经纪人船盘
	 * @param vo
	 */
	public Page<Map<String, Object>> recommendShipPlate(SysShipQuery query,Map<String, Object> map){
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		query.setPageSize(10);
		Page<Map<String, Object>> shipPalletList = this.queryMoreRecords(map,query.getPageInfo());
		query.setTotalItem(Long.valueOf(shipPalletList.getTotal()));
		for (Map<String, Object> temp : shipPalletList) {
			
			//根据语言环境设置epMemberName、shipOwner、broker
			if ("en".equals(lang)){
				//epMemberName
				Object obj1=temp.get("epMemberNameEn");
				Object obj2=temp.get("epMemberId");
				if(obj1!=null){
					temp.put("epMemberName",(String)obj1);
				}else if(obj2!=null){
					temp.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
				}
				//shipOwner
				Object obj3=temp.get("shipOwnerEn");
				Object obj4=temp.get("shipOwnerId");
				if(obj3!=null){
					temp.put("shipOwner",(String)obj3);
				}else if(obj4!=null){
					temp.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
				}
				//broker
				Object obj5=temp.get("brokerNameEn");
				Object obj6=temp.get("brokerId");
				if(obj5!=null){
					temp.put("brokerName",(String)obj5);
				}else if(obj6!=null){
					temp.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
				}
			}
			
			//查询是否已询盘
			String shipPlateUuid = (String)temp.get("uuid");
			Map<String,Object> intention = new HashMap<>();
			intention.put("palletUuid", query.getPalletUuid());
			intention.put("shipPlateUuid", shipPlateUuid);
			List<Map<String, Object>> intentions =  intentionService.queryRecords(intention);
			if(intentions!=null&&!intentions.isEmpty()){
				temp.put("haveIntention", "0");
				temp.put("intention",null);
				for (Map<String, Object> map2 : intentions) {
					String status = (String)map2.get("status");
					if(!"4".equals(status)){
						temp.put("haveIntention","1");
						temp.put("intention",map2);
						break;
					}
				}
			}else{
				temp.put("haveIntention", "0");
				temp.put("intention",null);
			}
		}
		return shipPalletList;
	}
	
	/**
	 * 更多船盘
	 * @param vo
	 * @param user
	 * @return
	 */
	public Page<Map<String, Object>> moreShipplate(SysShipQuery query) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
				
		Map<String,Object> map = new HashMap<>();
		map.put("shipName", query.getName());
		query.setPageSize(10);
		Page<Map<String, Object>> shipPalletList = this.queryMoreRecords(map,query.getPageInfo());
		query.setTotalItem(Long.valueOf(shipPalletList.getTotal()));
		for (Map<String, Object> temp : shipPalletList) {
			
			//根据语言环境设置epMemberName、shipOwner、broker
			if ("en".equals(lang)){
				//epMemberName
				Object obj1=temp.get("epMemberNameEn");
				Object obj2=temp.get("epMemberId");
				if(obj1!=null){
					temp.put("epMemberName",(String)obj1);
				}else if(obj2!=null){
					temp.put("epMemberName",_commonService.findNameByEpMemberId((Long)obj2));
				}
				//shipOwner
				Object obj3=temp.get("shipOwnerEn");
				Object obj4=temp.get("shipOwnerId");
				if(obj3!=null){
					temp.put("shipOwner",(String)obj3);
				}else if(obj4!=null){
					temp.put("shipOwner",_commonService.findNameByEpMemberId((Long)obj4));
				}
				//broker
				Object obj5=temp.get("brokerNameEn");
				Object obj6=temp.get("brokerId");
				if(obj5!=null){
					temp.put("brokerName",(String)obj5);
				}else if(obj6!=null){
					temp.put("brokerName",_commonService.findNameByEpMemberId((Long)obj6));
				}
			}
			
			//查询是否已询盘
			String shipPlateUuid = (String)temp.get("uuid");
			Map<String,Object> intention = new HashMap<String, Object>();
			intention.put("palletUuid", query.getPalletUuid());
			intention.put("shipPlateUuid", shipPlateUuid);
			List<Map<String, Object>> intentions =  intentionService.queryRecords(intention);
			if(intentions!=null&&!intentions.isEmpty()){
				temp.put("haveIntention","1");
				temp.put("intention",intentions.get(0));
			}else{
				temp.put("haveIntention", "0");
				temp.put("intention",null);
			}
		}
		return shipPalletList;
	}
	
	/**
	 * 更多船盘-分页查询
	 */
	public Page<Map<String, Object>> queryMoreRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ShipPlateMapper.queryMoreRecords(map);
	}
	

	/**
	 * 数据批量导入（中心）
	 */
	@Override
	public String imports(String path, CurrentUser user) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		StringBuilder failName=new StringBuilder();
		
		Boolean failBool=false;	//记录是否失败
		
		List<ShipPlateImport> list = ImportUtils.getShipPlate(path);
		all = list.size();
		
		int recordIndex=0;	//记录第几条数据失败
		for (ShipPlateImport shipPlateImport : list) {
			recordIndex++;
			try {
				ShipPlateVO vo=new ShipPlateVO();
				
				//船名校验
				if(!StringUtils.isNullOrEmpty(shipPlateImport.getName())){
					vo.setName(shipPlateImport.getName());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船名为空");
            		throw new TransportException(TYPE.ITSH030);
				}
            	
            	//船位校验
            	if(!StringUtils.isNullOrEmpty(shipPlateImport.getPosition())){
            		vo.setPosition(shipPlateImport.getPosition());
            	}else{
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，船位为空");
            		throw new TransportException(TYPE.ITSH237);
            	}
            	
            	//open校验
            	if(shipPlateImport.getOpen()!=null){
            		vo.setOpen(shipPlateImport.getOpen());
            	}else{
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，open为空");
            		throw new TransportException(TYPE.ITSH411);
            	}
            	
            	//eta、etaCabinda校验
            	String eta = shipPlateImport.getEta();
            	String etaCabinda = shipPlateImport.getEtaCabinda();
            	if(StringUtils.isNullOrEmpty(eta) && StringUtils.isNullOrEmpty(etaCabinda)){
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，eta和etaCabinda都为空");
            		throw new TransportException(TYPE.ITSH412);
            	}
            	if(!StringUtils.isNullOrEmpty(eta)){
            		vo.setEta(shipPlateImport.getEta());
            	}
            	if(!StringUtils.isNullOrEmpty(etaCabinda)){
            		vo.setEtaCabinda(shipPlateImport.getEtaCabinda());
            	}
            	
            	//船东校验
            	if(!StringUtils.isNullOrEmpty(shipPlateImport.getShipOwner())){
            		vo.setShipOwner(shipPlateImport.getShipOwner());
            	}else{
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，船东为空");
            		throw new TransportException(TYPE.ITSH029);
            	}
            	
            	//时效校验
            	if(shipPlateImport.getPeriod()!=null){
            		vo.setPeriod(shipPlateImport.getPeriod());
            	}else{
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，时效为空");
            		throw new TransportException(TYPE.ITSH413);
            	}
            	
				
            	//根据船名获取船的信息        	
            	SysShip sysShip=new SysShip();
            	sysShip.setName(shipPlateImport.getName());
            	List<SysShip> sysShipList = _sysShipService.queryByEntitys(sysShip);
            	if(sysShipList==null || sysShipList.isEmpty() || sysShipList.size()>1){
            		//当前船盘数据导入失败
            		System.out.println("########第 "+recordIndex+" 条数据保存失败，船名匹配船异常");
            		throw new TransportException(TYPE.ITSH410);
            	}else{
            		SysShip ship = sysShipList.get(0);
            		vo.setSysShipUuid(ship.getUuid());
            		vo.setCompleteDate(ship.getCompleteDate());	//设置建成年份
            		vo.setLoadQuantity(ship.getLoadQuantity());	//设置载重吨
            		
            		//船型校验
            		String type = ship.getType();
            		if(!StringUtils.isNullOrEmpty(type)){
            			vo.setType(type);
            		}else{
            			System.out.println("########第 "+recordIndex+" 条数据保存失败，船型为空");
            			throw new TransportException(TYPE.ITSH205);
            		}
            	}
            	
            	this.saveShipPlate(BeanConvertUtils.beanToBean(vo, ShipPlate.class),user);
            	System.out.println("########第 "+recordIndex+" 条数据保存成功");
				suceessFn++;
			} catch (Exception e) {
				falseFn++;	//记录失败条数
				failBool=true;
				failName.append(shipPlateImport.getName()+"，");	//记录船名
				e.printStackTrace();
			}
		}

		if(failBool){
			//存在失败数据
			String str = failName.toString();
			str = str.substring(0,str.lastIndexOf("，"));
			mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条，"+"失败的船名："+"[ "+str+" ]";
		}else{
			//没失败数据
			mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		}
		return mess;
	}
	
	
	
	/**
	 * 数据批量导入（OM平台）
	 */
	@Override
	public String importsOM(String path, CurrentUser user, Long epMemberId) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		StringBuilder failName=new StringBuilder();
		
		Boolean failBool=false;	//记录是否失败
		
		List<ShipPlateImport> list = ImportUtils.getShipPlate(path);
		all = list.size();
		
		//epMemberId 校验
		if(epMemberId==null){
			System.out.println("数据保存失败，epMemberId 为空");
			return "数据保存失败，epMemberId 为空";
		}
		
		int recordIndex=0;	//记录第几条数据失败
		for (ShipPlateImport shipPlateImport : list) {
			recordIndex++;
			try {
				ShipPlateVO vo=new ShipPlateVO();
				vo.setEpMemberId(epMemberId);
				
				//船名校验
				if(!StringUtils.isNullOrEmpty(shipPlateImport.getName())){
					vo.setName(shipPlateImport.getName());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船名为空");
					throw new TransportException(TYPE.ITSH030);
				}
				
				//船位校验
				if(!StringUtils.isNullOrEmpty(shipPlateImport.getPosition())){
					vo.setPosition(shipPlateImport.getPosition());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船位为空");
					throw new TransportException(TYPE.ITSH237);
				}
				
				//open校验
				if(shipPlateImport.getOpen()!=null){
					vo.setOpen(shipPlateImport.getOpen());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，open为空");
					throw new TransportException(TYPE.ITSH411);
				}
				
				//eta、etaCabinda校验
				String eta = shipPlateImport.getEta();
				String etaCabinda = shipPlateImport.getEtaCabinda();
				if(StringUtils.isNullOrEmpty(eta) && StringUtils.isNullOrEmpty(etaCabinda)){
					System.out.println("########第 "+recordIndex+" 条数据保存失败，eta和etaCabinda都为空");
					throw new TransportException(TYPE.ITSH412);
				}
				if(!StringUtils.isNullOrEmpty(eta)){
					vo.setEta(shipPlateImport.getEta());
				}
				if(!StringUtils.isNullOrEmpty(etaCabinda)){
					vo.setEtaCabinda(shipPlateImport.getEtaCabinda());
				}
				
				//船东校验
				if(!StringUtils.isNullOrEmpty(shipPlateImport.getShipOwner())){
					vo.setShipOwner(shipPlateImport.getShipOwner());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船东为空");
					throw new TransportException(TYPE.ITSH029);
				}
				
				//时效校验
				if(shipPlateImport.getPeriod()!=null){
					vo.setPeriod(shipPlateImport.getPeriod());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，时效为空");
					throw new TransportException(TYPE.ITSH413);
				}
				
				
				//根据船名获取船的信息        	
				SysShip sysShip=new SysShip();
				sysShip.setName(shipPlateImport.getName());
				List<SysShip> sysShipList = _sysShipService.queryByEntitys(sysShip);
				if(sysShipList==null || sysShipList.isEmpty() || sysShipList.size()>1){
					//当前船盘数据导入失败
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船名匹配船异常");
					throw new TransportException(TYPE.ITSH410);
				}else{
					SysShip ship = sysShipList.get(0);
					vo.setSysShipUuid(ship.getUuid());
					vo.setCompleteDate(ship.getCompleteDate());	//设置建成年份
					vo.setLoadQuantity(ship.getLoadQuantity());	//设置载重吨
					
					//船型校验
					String type = ship.getType();
					if(!StringUtils.isNullOrEmpty(type)){
						vo.setType(type);
					}else{
						System.out.println("########第 "+recordIndex+" 条数据保存失败，船型为空");
						throw new TransportException(TYPE.ITSH205);
					}
				}
				
				this.saveShipPlateOM(BeanConvertUtils.beanToBean(vo, ShipPlate.class),user);
				System.out.println("########第 "+recordIndex+" 条数据保存成功");
				suceessFn++;
			} catch (Exception e) {
				falseFn++;	//记录失败条数
				failBool=true;
				failName.append(shipPlateImport.getName()+"，");	//记录船名
				e.printStackTrace();
			}
		}
		
		if(failBool){
			//存在失败数据
			String str = failName.toString();
			str = str.substring(0,str.lastIndexOf("，"));
			mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条，"+"失败的船名："+"[ "+str+" ]";
		}else{
			//没失败数据
			mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		}
		return mess;
	}
	
	/**
	 * 根据UUID-查询对象
	 */
	public Map<String, Object> findMapByUuid(String uuid){
		Map<String, Object> shipPlate = _ShipPlateMapper.findMapByUuid(uuid);	
 		return shipPlate;
	} 
	
	/**
	 * 定时任务（船盘过期的，设置其失效），查询所有“1已发布”状态的船盘
	 */
	@Override
	public void timingUpdateShipPlate(){
		ShipPlate shipPlate=new ShipPlate();
		shipPlate.setStatus(Constants.SHIP_PLATE_STATUS_1);
		List<ShipPlate> list = this.queryByEntitys(shipPlate);
		Integer all =list.size();
		Integer succ= 0;
		Integer fal= 0;
		if(list!=null && !list.isEmpty()){
			for (ShipPlate sp : list) {
				try{
					 Boolean flag = this.checkPeriodDate(sp);
					 if (flag){
						succ++; 
					 }
				} catch (BizException e) {
					fal++;
					log.error("船盘失效定时任务执行失败，shipPlateUuid="+sp.getUuid(), e);
					e.printStackTrace();
				} catch (Exception e) {
					fal++;
					log.error("船盘失效定时任务执行失败，shipPlateUuid="+sp.getUuid(), e);
					e.printStackTrace();
				}
			}
		}
		log.info("==================>本次定时任务共查询已发布状态"+all+"条，失效船盘更新成功"+succ+"条，失败"+fal+"条。");
		
	}
	
	/**
	 * 校验船盘是否过期，若过期，则失效
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED)
	public Boolean checkPeriodDate(ShipPlate shipPlate){
		Boolean flag =false;
		//获取有效日期
		Date periodDate = shipPlate.getPeriodDate();
		if(periodDate != null && periodDate.getTime()<DateTimeUtils.currentDate().getTime()){
			//过期
			shipPlate.setAliveFlag("0");
			shipPlate.setUpdateDate(DateTimeUtils.currentDate());
			shipPlate.setUpdateUser(999L);
			//修改船盘为“失效”
			this.updateRecord(shipPlate);
			flag = true;
			//发送消息
			messagePushService.messagePush(7, shipPlate.getShipPlateId(), 999L);
		}
		return flag;
		
	}
}
