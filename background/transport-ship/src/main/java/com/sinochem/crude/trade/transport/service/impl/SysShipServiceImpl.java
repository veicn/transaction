package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException.TYPE;
import com.sinochem.crude.trade.transport.dao.SysShipMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.model.SysShipVO;
import com.sinochem.crude.trade.transport.model.res.SysShipImport;
import com.sinochem.crude.trade.transport.query.SysShipQuery;
import com.sinochem.crude.trade.transport.service.AccessoryService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.SysShipService;

@Service
public class SysShipServiceImpl implements SysShipService {
	@Autowired
	private SysShipMapper _SysShipMapper;
	@Autowired
	private AccessoryService accessoryService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MessagePushService messagePushService;
	
	
	public SysShipMapper getMapper(){
		return _SysShipMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<SysShip> queryByEntitys(SysShip sysship){
		return  _SysShipMapper.queryByEntitys(sysship);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public SysShip findByPrimaryKey(Long sysShipId){
		return  _SysShipMapper.findByPrimaryKey(sysShipId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public SysShip findByUuid(String uuid){
		return  _SysShipMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(SysShip sysship) {
		 _SysShipMapper.updateRecord(sysship);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long sysShipId  , Long deleteUser) {
		 _SysShipMapper.deleteRecordByKey(sysShipId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(SysShip sysship){
		 _SysShipMapper.insertRecord(sysship);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long sysShipId){
		 _SysShipMapper.deleteRecordByKey(sysShipId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _SysShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _SysShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _SysShipMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_SysShipMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_SysShipMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据船舶UUID-查询首页船舶详细信息
	 */
	public Map<String, Object> findByUUIDForward(String UUID){
		return  _SysShipMapper.findByUUIDForward(UUID);	
	}
	
	/**
	 * 添加平台船舶信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long saveSysShip(SysShipVO vo, CurrentUser user) {
		
		//判断船舶保存类型
		String saveType=vo.getSaveType();
		if(saveType.equals("1")){	// saveType=1：（1:待审核） 
			vo.setStatus(Constants.SYS_SHIP_STATUS_1);
			
		}else if(saveType.equals("2")){		// saveType=2：(2:有效)
			vo.setStatus(Constants.SYS_SHIP_STATUS_2);
		}
		
		//获取企业信息
		if(user.getEpMemberId()!=null){
			EnterpriseVo enterpriseVo = commonService.queryUserByEpMemberId(user.getEpMemberId());
			if(enterpriseVo!=null){
				String epMemberName = enterpriseVo.getFullName();
				vo.setEpMemberName(epMemberName);
			}
		}
		
		
		//判断用户角色，获取船舶发布类型(船东/经纪人，二船东，平台)
		String publisherType="";
		String[] roles = user.getRoles();
		Boolean ShipOwnerBool=false;		//当前用户是否具有船东/船东经纪人角色
		Boolean ShipOwnerTowrBool=false;	//当前用户是否具有二船东角色
		Boolean platformBool=false;			//当前用户是否具有平台角色
		if(roles!=null && roles.length>0){
			for (String role : roles) {
				if(role.equals("ship_owner") || role.equals("ship_broker")){	//1船东/船东经纪人
					publisherType="1";	
					ShipOwnerBool=true;
				}
				if(role.equals("ship_trader") || role.equals("ship_executor")){	//2转租船东
					publisherType="2";	
					ShipOwnerTowrBool=true;
				}
				if(role.equals("admin") || role.equals("info_oner")){	//3平台
					publisherType="3";	
					platformBool=true;
				}
			}
		}
		if(ShipOwnerBool && ShipOwnerTowrBool || ShipOwnerBool&&platformBool || ShipOwnerTowrBool&&platformBool){
			//TODO:ZhengC 船舶新增，若当前用户具有多角色
			throw new TransportException(TYPE.ITSH415);	
		}
		if(publisherType.equals("")){
			throw new TransportException(TransportException.TYPE.ITSH407);
		}
		
		//校验IMO是否已存在
		List<SysShip> sysShipLists = this.queryByEntitys(null);	//查询所有船舶的IMO
		List<String> imoArr=new ArrayList<>();	//imoArr：imo库
		for (SysShip sysShip : sysShipLists) {	//将数据库中的imo添加到imo库中
			String imo = sysShip.getImo();
			imoArr.add(imo);
		}
		if(imoArr.contains(vo.getImo())){
			throw new TransportException(TransportException.TYPE.ITSH416);
		}
		
		vo.setPublisherType(publisherType);
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(user.getMemberId());
		vo.setCreateUser(user.getMemberId());
		vo.setEpMemberId(user.getEpMemberId());
		
		// 添加船舶信息
		this.insertRecord(vo);
		
		// 取得新增船务ID
		Long sysShipId = vo.getSysShipId();

		// 插入附件
		if (vo.getFileInfoList() != null) {
			for(int i = 0; i < vo.getFileInfoList().size(); i++) {
				Accessory accessory = vo.getFileInfoList().get(i);
				accessory.setId(sysShipId);
				accessory.setType("1");	//业务类型（1船舶2船航次开始3船装港4船在途5船卸港）
				accessoryService.saveAccessory(accessory, user);
			}
		}
		
		return sysShipId;
	}
	
	/**
	 * 修改平台船舶信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateSysShip(SysShipVO vo, CurrentUser user) {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		Long sysShipId = sysShip.getSysShipId();
		vo.setSysShipId(sysShipId);
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());
		
		// 修改船舶信息
		this.updateRecord(vo);
		
		// 插入附件
		if (vo.getFileInfoList() != null) {
			for (int i = 0; i < vo.getFileInfoList().size(); i++) {
				Accessory accessory = vo.getFileInfoList().get(i);
				accessory.setId(sysShipId);
				accessoryService.saveAccessory(accessory, user);
			}
		}
	}

	/**
	 * 修改平台船舶状态
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateSysShipStatus(SysShip vo) {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		vo.setSysShipId(sysShip.getSysShipId());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(1L);
		
		// 修改船舶状态
		this.updateRecord(vo);
		System.out.println(vo.getSysShipId());
	}

	/**
	 * 删除平台船舶信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delSysShip(SysShip vo, Long memberId) {
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		// 取得船舶主键
		Long sysShipId = sysShip.getSysShipId();
		
		// 删除船舶信息
		this.deleteRecordByKey(sysShipId, memberId);
		
		// 删除平台船舶附件
		Accessory accessory = new Accessory();
		accessory.setId(sysShipId);
		accessoryService.delAccessory(accessory, memberId);
	}

	/**
	 * 查询平台船舶信息详细
	 */
	@Override
	public SysShip findSysShipDetail(SysShip vo) {
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		// 取得船舶主键
		Long sysShipId = sysShip.getSysShipId();
		
		// 查询船舶信息
		SysShip sysShipDetail = this.findByPrimaryKey(sysShipId);
		
		return sysShipDetail;
	}

	/**
	 * 查询平台船舶名称下拉列表
	 */
	@Override
	public List<SysShip> findSysShipList(SysShip vo) {
		// 查询船舶信息列表
		List<SysShip> sysShipList = (List<SysShip>)this.queryByEntitys(vo);
		List<SysShip> list = new ArrayList<>();
		for (SysShip sysShip : sysShipList) {
			if (!StringUtils.isNullOrEmpty(sysShip.getName())){
				list.add(sysShip);
			}
		}
		return list;
	}
	
	/**
	 * 查询平台船舶信息翻页列表（前台接口）
	 */
	@Override
	public Page<Map<String, Object>> findSysShipPageList(SysShip vo, SimplePageInfo pageInfo) {
		// 页码设定
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		// 查询船舶信息
		Page<Map<String, Object>> sysShipList = (Page<Map<String, Object>>)_SysShipMapper.findSysShipByImoName(vo);
		
		// 船舶记录数
		Map<String,Object> map = new HashMap<String,Object>();
		map = BeanConvertUtils.beanToMap(vo);
		int total = this.countRecords(map);
		sysShipList.setTotal(total);
				
		// 附件追加
		if (sysShipList != null) {
			for(int i = 0; i < sysShipList.size(); i++) {
				Map<String, Object> sysShip = sysShipList.get(i);
				Long sysShipId = (Long) sysShip.get("sysShipId");
				vo.setSysShipId(sysShipId);
				// 查询船舶附件
				List<Map<String, Object>> sysShipAccessoryList = (List<Map<String, Object>>)_SysShipMapper.findSysShipAccessoryByImoName(vo);
				sysShip.put("url", sysShipAccessoryList);
			}
		}
		
		return sysShipList;
	}
	
	/**
	 * 查询平台船舶信息翻页列表
	 */
	@Override
	public List<Map<String, Object>> getSysShipPageList(SysShipQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 船舶名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", query.getName());
		map.put("status", "3");	// 查询为 "1待审核"和"2有效" 状态的船舶
		
		List<Map<String, Object>> list = _SysShipMapper.findShipRecords(map);
		
		//添加船舶状态字段（1待审核2有效3已驳回 ）
		for (Map<String, Object> shipMap : list) {
			String statusCode=(String)shipMap.get("status");
			String statusMsg="";
			if(statusCode.equals("1")){
				statusMsg="待审核";
			}else if(statusCode.equals("2")){
				statusMsg="审核通过";
			}
			shipMap.put("statusMsg", statusMsg);
		}
		
		return list;
	}
	
	/**
	 * 根据uuid、查询船舶状态
	 */
	@Override
	public List<Map<String,Object>> findSysShipStatus(SysShip vo) {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		
		// 查询船舶状态
		List<Map<String,Object>> sysShipStatus = (List<Map<String,Object>>)_SysShipMapper.findSysShipStatus(vo);
		
		return sysShipStatus;
	}
	
	
	
	/**
	 * 船舶审核  1:审核通过 2:审核驳回
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void checkShip(SysShipVO vo ,CurrentUser user) {
		
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		int type=0;
		if(vo.getCheckFlag().equals("1")){
			//审核通过
			sysShip.setStatus("2");
			
			//发送消息	
			type=16;

		}else if(vo.getCheckFlag().equals("2")){
			//审核驳回
			if(StringUtils.isNullOrEmpty(vo.getRefuseMessage())){
				throw new TransportException(TransportException.TYPE.ITSH403);
			}
			
			sysShip.setStatus("3");
			sysShip.setExt1(vo.getRefuseMessage());
			
			//发送消息	
			type=17;
		}
		
		sysShip.setUpdateDate(DateTimeUtils.currentDate());
		sysShip.setUpdateUser(user.getMemberId());
		// 修改船舶状态
		this.updateRecord(sysShip);
		
		//发送消息	16船舶审核通过-船舶 / 17船舶审核驳回-船舶
		messagePushService.messagePush(type, sysShip.getSysShipId(), user.getMemberId());
	}
	
	//---------------------------------------------
	/**
	 * 查询船舶翻页列表（船东/经纪人）
	 */
	@Override
	public Page<Map<String, Object>> shipOwnerSysShipPageList(SysShipQuery query,CurrentUser user) {
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		
		
		// 船舶名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", query.getName());
		map.put("epMemberId", user.getEpMemberId());	// 根据当前用户企业id查询
		map.put("status", query.getStatus());
		
		Page<Map<String,Object>> page = this.queryRecords(map, query.getPageInfo());
		
		
		//根据语言环境设置船舶状态字段（1待审核2有效3已驳回 ）
		for (Map<String, Object> shipMap : page) {
			String statusCode=(String)shipMap.get("status");
			String statusMsg="";
			
			if ("en".equals(lang)){
				if(statusCode.equals("1")){
					statusMsg="Under Approval";
				}else if(statusCode.equals("2")){
					statusMsg="Approved";
				}else if(statusCode.equals("3")){
					statusMsg="Rejected";
				}
			} else {
				if(statusCode.equals("1")){
					statusMsg="待审核";
				}else if(statusCode.equals("2")){
					statusMsg="审核通过";
				}else if(statusCode.equals("3")){
					statusMsg="审核驳回";
				}
			}
			shipMap.put("statusMsg", statusMsg);
		}
		
		return page;
	}
	
	
	/**
	 * 数据批量导入
	 */
	@Override
	public String imports(String path, CurrentUser user, String type) {
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		List<SysShipImport> list = ImportUtils.getSysShip(path);
		all = list.size();
		
		//查询所有船舶的IMO
		List<SysShip> sysShipLists = this.queryByEntitys(null);
		List<String> imoArr=new ArrayList<>();	//imoArr：imo库
		for (SysShip sysShip : sysShipLists) {	//将数据库中的imo添加到imo库中
			String imo = sysShip.getImo();
			imoArr.add(imo);
		}
		
		int recordIndex=0;	//记录第几条数据失败
		
		for (SysShipImport sysShipImport: list) {
			Boolean updateFlag=false;	//更新 or 新增
			recordIndex++;
			try {
				SysShipVO vo=new SysShipVO();
				
				//imo校验，若imo已存在->根据中心/平台，保存 or 更新
				String imoData = sysShipImport.getImo();
				if(!StringUtils.isNullOrEmpty(imoData) && !imoArr.contains(imoData)){
					vo.setImo(imoData);
				}else{
					if(StringUtils.isNullOrEmpty(imoData)){
						System.out.println("########第 "+recordIndex+" 条数据保存失败，船舶代码imo为空");
						throw new TransportException(TYPE.ITSH202);
					}else if(imoArr.contains(imoData)){
						if(!StringUtils.isNullOrEmpty(type) && "7".equals(type)){
							//判断，若是平台导入，imo相同则更新
							updateFlag=true;
							vo.setImo(imoData);
						}else if(!StringUtils.isNullOrEmpty(type) && "6".equals(type)){
							//若是中心导入，imo相同，不保存
							System.out.println("########第 "+recordIndex+" 条数据保存失败，船舶代码imo已存在");
							throw new TransportException(TYPE.ITSH416);
						}
					}
				}
				
				//建造年份校验
				if(!StringUtils.isNullOrEmpty(sysShipImport.getCompleteDate())){
					vo.setCompleteDate(sysShipImport.getCompleteDate());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，建造年份为空");
            		throw new TransportException(TYPE.ITSH208);
				}
				
				//船名校验
				if(!StringUtils.isNullOrEmpty(sysShipImport.getName())){
					vo.setName(sysShipImport.getName());
				}else{
					System.out.println("########第 "+recordIndex+" 条数据保存失败，船名为空");
            		throw new TransportException(TYPE.ITSH030);
				}
				
				vo.setMmsi(sysShipImport.getMmsi());
				vo.setUseType(sysShipImport.getUseType());
				vo.setType(sysShipImport.getType());
				vo.setHull(sysShipImport.getHull());
				vo.setLength(sysShipImport.getLength());
				vo.setWide(sysShipImport.getWide());
				vo.setDraft(sysShipImport.getDraft());
				vo.setLoadQuantity(sysShipImport.getLoadQuantity());
				vo.setCapacity(sysShipImport.getCapacity());
				vo.setPennant(sysShipImport.getPennant());
				
				if(!StringUtils.isNullOrEmpty(type)){
					if("6".equals(type)){		//6船舶信息：船东/经纪人
						//船舶保存后状态为 "待审核"
						vo.setSaveType("1");	
						//vo.setPublisherType("1");
					}else if("7".equals(type)){		//7船舶信息：平台
						//船舶保存后状态为 "有效"
						vo.setSaveType("2");
						//vo.setPublisherType("3");
					}
				}
				
				if(updateFlag){
					//平台导入，imo相同，则更新
					SysShip sysShipQuery=new SysShip();
					sysShipQuery.setImo(imoData);
					List<SysShip> list2 = this.queryByEntitys(sysShipQuery);
					if(list2!=null && list2.size()>0){
						SysShip sysShip = list2.get(0);
						Long sysShipId = sysShip.getSysShipId();
						vo.setSysShipId(sysShipId);
						vo.setUpdateDate(DateTimeUtils.currentDate());
						this.updateRecord(vo);
					}
				}else{
					this.saveSysShip(vo, user);
					//若当前条数据保存成功，将当前imo添加到imo库中，便于下一条数据校验imo
					imoArr.add(imoData);
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
	public List<Map<String, Object>> findSysShipByImoOrMmsiOrName(String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		return _SysShipMapper.findSysShipByImoOrMmsiOrName(map);
				
	}
	
}
