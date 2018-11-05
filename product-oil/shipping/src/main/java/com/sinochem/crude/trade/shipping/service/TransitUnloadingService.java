package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.TransitUnloadingMapper;
import com.sinochem.crude.trade.shipping.domain.TransitUnloading;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface TransitUnloadingService {
	

	
	/**
	 * 新增
	 */
	Integer insertRecord(TransitUnloading transitUnloading,CurrentUser currentUser)throws BizException;
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long transitUnloadingId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TransitUnloading  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TransitUnloading transitUnloading) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(TransitUnloading transitUnloading) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TransitUnloading transitUnloading);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TransitUnloading findByPrimaryKey(Long transitUnloadingId);

	/**
	 * 根据uuid查询对象
	 */
	TransitUnloading findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TransitUnloading> queryByEntitys(TransitUnloading transitUnloading);
		
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
	
	

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增(判断用户角色)
	 */
	int insertRecordByCurrentUser(TransitUnloading transitUnloading,CurrentUser currentUser);

	/**
	 * 查询船在途信息
	 * @param confirmationSheetId
	 * @return
	 */
	 List<TransitUnloading> findByConfirmationSheetId(Long confirmationSheetId);


	/**
	 * 根据uuid删除船舶在途信息
	 * @param uuid
	 * @return
	 */
	Boolean deleteRecordsByUuid(String uuid)throws BizException;
}
