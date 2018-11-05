package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.SysShipMapper;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.model.SysShipVO;
import com.sinochem.crude.trade.transport.query.SysShipQuery;

public interface SysShipService {
	
	public abstract SysShipMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<SysShip> queryByEntitys(SysShip sysship);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract SysShip findByPrimaryKey(Long sysShipId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract SysShip findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(SysShip sysship);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long sysShipId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(SysShip sysship);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long sysShipId);
	
	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map); 
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public abstract void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);
	

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据主键-查询对象详细信息
	 */
	public Map<String, Object> findByUUIDForward(String sysShipId);
	/**
	 * 新增平台船舶信息
	 * @param vo
	 */
	public abstract Long saveSysShip(SysShipVO vo, CurrentUser user);
	
	/**
	 * 修改平台船舶信息
	 * @param vo
	 */
	public abstract void updateSysShip(SysShipVO vo, CurrentUser user);
	
	/**
	 * 修改平台船舶状态
	 * @param vo
	 */
	public abstract void updateSysShipStatus(SysShip vo);
	
	/**
	 * 删除平台船舶信息
	 * @param vo
	 */
	public abstract void delSysShip(SysShip vo, Long memberId);
	
	/**
	 * 查询平台船舶信息详细
	 * @param vo
	 */
	public abstract SysShip findSysShipDetail(SysShip vo);
	
	/**
	 * 查询平台船舶信息列表
	 * @param vo
	 */
	public abstract List<SysShip> findSysShipList(SysShip vo);
	
	/**
	 * 查询平台船舶信息翻页列表(前台接口)
	 * @param vo
	 */
	public abstract Page<Map<String, Object>> findSysShipPageList(SysShip vo, SimplePageInfo pageInfo);
	
	/**
	 * 查询平台船舶信息翻页列表
	 * @param vo
	 */
	public abstract List<Map<String, Object>> getSysShipPageList(SysShipQuery query);
	
	/**
	 * 根据uuid、查询船舶状态
	 * @param vo
	 */
	public abstract List<Map<String,Object>> findSysShipStatus(SysShip vo);
	
	/**
	 * 船舶审核  1:审核通过 2:审核驳回
	 * @param vo
	 */
	public void checkShip(SysShipVO vo ,CurrentUser user);
	
	/**
	 * 查询船舶翻页列表（船东/经纪人）
	 */
	public Page<Map<String, Object>> shipOwnerSysShipPageList(SysShipQuery query,CurrentUser user);
	
	/**
	 * 数据批量导入
	 */
	public String imports(String path, CurrentUser user, String type);
	
	/**
	 * 根据IMO、MMSI、船名查船舶信息(可视化地图接口)
	 * @param keyword  IMO、MMSI、船名
	 * @return
	 */
	
	public List<Map<String,Object>> findSysShipByImoOrMmsiOrName(String keyword);
}
