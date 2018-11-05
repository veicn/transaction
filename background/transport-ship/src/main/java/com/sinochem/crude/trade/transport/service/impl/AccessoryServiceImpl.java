package com.sinochem.crude.trade.transport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.AccessoryMapper;
import com.sinochem.crude.trade.transport.dao.SysShipMapper;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.service.AccessoryService;

@Service
public class AccessoryServiceImpl implements AccessoryService {
	@Autowired
	private AccessoryMapper _AccessoryMapper;
	@Autowired
	private SysShipMapper _SysShipMapper;
	
	
	public AccessoryMapper getMapper(){
		return _AccessoryMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Accessory> queryByEntitys(Accessory accessory){
		return  _AccessoryMapper.queryByEntitys(accessory);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Accessory findByPrimaryKey(Long accessoryId){
		return  _AccessoryMapper.findByPrimaryKey(accessoryId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Accessory findByUuid(String uuid){
		return  _AccessoryMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Accessory accessory) {
		 _AccessoryMapper.updateRecord(accessory);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long accessoryId  , Long deleteUser) {
		 _AccessoryMapper.deleteRecordByKey(accessoryId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Accessory accessory){
		 _AccessoryMapper.insertRecord(accessory);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long accessoryId){
		 _AccessoryMapper.deleteRecordByKey(accessoryId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _AccessoryMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AccessoryMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _AccessoryMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_AccessoryMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AccessoryMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/

	/**
	 * 保存附件
	 */
	@Override
	public void saveAccessory(Accessory vo, CurrentUser user) {
		//校验
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setLangVer(Constants.LANG_VER);
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setUpdateUser(user.getEpMemberId());
		vo.setCreateUser(user.getEpMemberId());
		
		// 添加附件信息
		this.insertRecord(vo);
	}
	
	/**
	 * 删除船舶附件信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delAccessory(Accessory vo, Long memberId) {
		
		// 取得附件uuid
		String uuid = vo.getUuid();
		// 取得船舶id
		Long id = vo.getId();
		
		// 附件uuid、船舶id校验
		if (StringUtils.isNullOrEmpty(uuid) && id == null) {
			throw new TransportException(TransportException.TYPE.ITSH218);
		}
		
		//根据附件uuid或船舶id删除附件信息
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uuid", uuid);
		map.put("id", id);
		map.put("updateUser", memberId);
		
		this.deleteRecords(map);
	}
	
	/**
	 * 查询附件
	 */
	@Override
	public List<Accessory> findAccessory(Accessory vo) {
		String sysShipUuid = vo.getUuid();

		//根据船舶uuid查询船舶信息
		SysShip sysShip = _SysShipMapper.findByUuid(sysShipUuid);
		if (sysShip == null){
			throw new TransportException(TransportException.TYPE.ITSH023);
		}
		
		Long sysShipId = sysShip.getSysShipId();
		vo.setUuid("");
		vo.setId(sysShipId);
		
		// 查询附件信息列表
		List<Accessory> accessoryList = (List<Accessory>)this.queryByEntitys(vo);
		
		return accessoryList;
	}
}
