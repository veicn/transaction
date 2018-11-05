package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.LoadingProgressMapper;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface LoadingProgressService {
	
	public abstract LoadingProgressMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(LoadingProgress loadingProgress);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long shipLoadingProgressId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(LoadingProgress  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(LoadingProgress loadingProgress) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(LoadingProgress loadingProgress) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(LoadingProgress loadingProgress);
	
	
	/**
	 * 根据主键-查询对象
	 */
	LoadingProgress findByPrimaryKey(Long shipLoadingProgressId);

	/**
	 * 根据uuid查询对象
	 */
	LoadingProgress findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<LoadingProgress> queryByEntitys(LoadingProgress loadingProgress);
		
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
	
	
	List<LoadingProgress> findByConfirmationSheetId(LoadingProgressQuery loadingProgressQuery);

	Integer insertRecordOrUpdate(LoadingProgress domain, CurrentUser currentUser, String confuuid) throws BizException;

	/**
	 * 船舶装港进度表
	 * @param confirmationSheetId
	 * @return
	 */
	LoadingProgress findByConfirmationSheetId(Long confirmationSheetId);

	/**
	 * 保存进度表   微信API端
	 * @param domain
	 * @param currentUser
	 * @return
	 */
	Integer saveOrUpdateLoadingProgress(LoadingProgress domain, CurrentUser currentUser);
	
	public List<LoadingProgress> findListByConfirmationSheetId(Long confirmationSheetId);
}
