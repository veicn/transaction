package com.sinochem.crude.trade.operation.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.domain.Enu;
import com.sinochem.crude.trade.operation.dao.EnuMapper; 

public interface EnuService {
	
	public abstract EnuMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Enu enu);
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Enu  record);
	
	
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Enu enu);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Enu findByPrimaryKey();

	/**
	 * 根据uuid查询对象
	 */
	Enu findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Enu> queryByEntitys(Enu enu);
		
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
	/**
	 * 新增或更新菜单
	 * @return
	 */
	public abstract Result saveOrUpdateMenu(Enu enu, CurrentUser user);

	int updateRecordByMenuId(Enu enu) throws BizException;

	public abstract int tpMenuDelete(String menuId); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
}
