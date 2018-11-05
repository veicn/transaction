package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.UnloadPortMapper;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface UnloadPortService {

	/**
	 * 新增
	 */
	Integer insertRecord(UnloadPort unloadPort,CurrentUser currentUser)throws BizException;

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long unloadPortId) throws BizException;

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	public int deleteRecords(UnloadPort record);

	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(UnloadPort unloadPort) throws BizException;

	/**
	 * 根据uuid-修改对象
	 */
	Integer updateRecordByUuid(UnloadPort unloadPort,CurrentUser currentUser) throws BizException;

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(UnloadPort unloadPort);

	/**
	 * 根据主键-查询对象
	 */
	UnloadPort findByPrimaryKey(Long unloadPortId);

	/**
	 * 根据uuid查询对象
	 */
	UnloadPort findByUuid(String uuid);

	/**
	 * 根据对象-查询对象列表
	 */
	List<UnloadPort> queryByEntitys(UnloadPort unloadPort);

	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);

	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/

	/**
	 * 新增(判断用户角色)
	 */
	Integer insertRecordByCurrentUser(UnloadPort unloadPort, CurrentUser currentUser);
	
	/**
	 * 查询船舶卸港信息
	 * @param confirmationSheetId
	 * @return
	 */
	 UnloadPort findByShipConfirmationSheetId(Long confirmationSheetId);



}
