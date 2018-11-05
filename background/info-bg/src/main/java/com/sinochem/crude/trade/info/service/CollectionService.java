package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.CollectionMapper;
import com.sinochem.crude.trade.info.domain.Collection;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface CollectionService {
	
	public abstract CollectionMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Collection collection);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Collection record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Collection collection) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Collection collection);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Collection findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Collection findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Collection> queryByEntitys(Collection collection);
		
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
	 * 收藏资讯
	 */
	public Result collectInfo(String infoUUId, String collectUserId,CurrentUser user) throws BizException;

	public abstract List<Map<String, Object>> findByMemberId(Long memberId);

	public abstract Page<Map<String, Object>> queryCollectionInfo(Map<String, Object> map,
			SimplePageInfo pageInfo);

}
