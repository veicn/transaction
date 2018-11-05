package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.ShipPlateMapper;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.query.SysShipQuery;

public interface ShipPlateService {
	
	public abstract ShipPlateMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<ShipPlate> queryByEntitys(ShipPlate shipplate);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract ShipPlate findByPrimaryKey(Long shipPlateId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract ShipPlate findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(ShipPlate shipplate);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long shipPlateId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(ShipPlate shipplate);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long shipPlateId);
	
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
	/**
	 * 根据uuid-批量修改数据
	 */
	public void updateRecordsFn(Map<String, Object> map);

	
	/**
	 * 根据主键-查询详细
	 */
	public List<Map<String,Object>> findByPrimaryKeyForground(Long shipPlateId);

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据条件-分页查询（船东/经纪人，转租船东，查询，包括平台辅助导入）
	 */
	public Page<Map<String, Object>> queryRecordsAll(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数（船东/经纪人，转租船东，查询，包括平台辅助导入）
	 */
	public int countRecordsAll(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询（om平台）
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String, Object>> queryRecordsOM(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 新增船盘信息
	 * @param vo	
	 * @param user
	 */
	public void saveShipPlate(ShipPlate vo,CurrentUser user);
	
	/**
	 * 添加船盘信息（OM平台）
	 * @param vo
	 * @param user
	 */
	public void saveShipPlateOM(ShipPlate vo,CurrentUser user);
	
	/**
	 * 修改船盘信息
	 * @param vo
	 * @param epMemberId	用户企业id
	 * @param memberId
	 */
	public void updateShipPlate(ShipPlate vo,Long epMemberId,Long memberId);
	
	/**
	 * 修改船盘状态
	 * @param vo
	 * @param saveType	操作类型（1:发布，2:保存，3:确认发布，4:下架）
	 * @param user
	 */
	public void updateShipPlateStatus(ShipPlate vo,String saveType,CurrentUser user);
	
	/**
	 * 删除船盘信息
	 * @param vo
	 * @param memberId	用户id
	 */
	public abstract void delShipPlate(ShipPlate vo, CurrentUser user);
	
	/**
	 * 删除船盘信息（om平台）
	 */
	public void delShipPlateOM(ShipPlate vo, CurrentUser user);
	
	/**
	 * 平台批量删除船盘信息
	 * @param uuid
	 * @param user
	 */
	public void batchDelShipPlateOM(String uuid, CurrentUser user);
	
	/**
	 * 查询船盘信息详细
	 * @param vo
	 * @return
	 */
	public abstract ShipPlate findShipPlateDetail(ShipPlate vo);
	
	/**
	 * 查询船盘信息列表(前台)
	 * @param vo
	 */
	public abstract List<Map<String,Object>> findShipPlateList(ShipPlate vo);
	
	/**
	 * 查询船盘信息翻页列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<ShipPlate> findShipPlatePageList(ShipPlate vo, SimplePageInfo pageInfo);
	
	/**
	 * 查询船盘信息翻页列表
	 * @param query
	 * @param user
	 * @return
	 */
	public abstract Page<Map<String,Object>> getShipPlatePageList(SysShipQuery query, CurrentUser user);
	
	
	/**
	 * 查询船盘信息翻页列表（om平台）
	 * @param query
	 * @param user
	 * @return
	 */
	public Page<Map<String,Object>> getShipPlatePageListOM(SysShipQuery query, CurrentUser user);
	
	/**
	 * 船盘列表_发送报盘（二船东）
	 * @param query
	 * @param user
	 * @return
	 */
	public Page<Map<String,Object>> sendClauseShipPlateList(SysShipQuery query, CurrentUser user);
		

		/**
	 * 查询船盘翻页列表
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryShipPlateList(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询船盘信息详细（包括船舶信息）
	 * @param vo
	 * @param user
	 * @return
	 */
	public Map<String, Object> findShipPlateDetailForground(ShipPlate vo,CurrentUser user);
	
	/**
	 * 查询全部船盘信息列表(前台接口)
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String,Object>> queryShipPlatePageList(ShipPlate vo,SimplePageInfo pageInfo);

	
	/**
	 * 推荐船盘
	 * @param vo
	 */
	public Page<Map<String, Object>> recommendShipPlate(SysShipQuery querys,Map<String, Object> map);
	
	/**
	 * 更多船盘
	 * @param query 
	 * @return
	 */
	public Page<Map<String, Object>> moreShipplate(SysShipQuery querys);
	
	
	/**
	 * 数据批量导入（中心）
	 * @param path
	 * @param user
	 * @return
	 */
	public String imports(String path, CurrentUser user);
	
	
	/**
	 * 数据批量导入（平台）
	 * @param path
	 * @param user
	 * @return
	 */
	public String importsOM(String path, CurrentUser user, Long epMemberId);
	
	/**
	 * 根据UUID查询对象
	 * @param path
	 * @param user
	 * @return
	 */
	public Map<String, Object> findMapByUuid(String uuid);
	
	/**
	 * 定时任务（船盘过期的，设置其失效），查询所有“1已发布”状态的船盘
	 */
	public void timingUpdateShipPlate();
	
	/**
	 * 校验船盘是否过期，若过期，则失效
	 * @return 
	 */
	public Boolean checkPeriodDate(ShipPlate shipPlate);
}
