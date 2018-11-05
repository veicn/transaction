package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.TransitLoadingMapper;
import com.sinochem.crude.trade.shipping.domain.TransitLoading;
import com.sinochem.crude.trade.shipping.model.query.TransitLoadingQuery;
import com.sinochem.it.b2b.common.exception.BizException;

public interface TransitLoadingService {
	
	public abstract TransitLoadingMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TransitLoading transitLoading);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long transitLoadingId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TransitLoading  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TransitLoading transitLoading) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(TransitLoading transitLoading) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TransitLoading transitLoading);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TransitLoading findByPrimaryKey(Long transitLoadingId);

	/**
	 * 根据uuid查询对象
	 */
	TransitLoading findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TransitLoading> queryByEntitys(TransitLoading transitLoading);
		
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
	List<TransitLoading> queryRecordsList(TransitLoadingQuery domain);

	 Integer transitLocigDelete(TransitLoading domain) throws BizException;

	 Integer insertTransit(List<TransitLoading> listdomain,
			CurrentUser currentUser, String conuuid)throws BizException;

	 //查看航程信息
	List<TransitLoading> findByShipConfirmationSheetId(Long confirmationSheetId);

	Integer saveTransitLoading(TransitLoading domain,CurrentUser currentUser)throws BizException;



}
