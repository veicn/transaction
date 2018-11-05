package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.PriceIndexMapper;
import com.sinochem.crude.trade.info.domain.PriceIndex;
import com.sinochem.crude.trade.info.query.PriceExcelQuery;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface PriceIndexService {
	
	public abstract PriceIndexMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(PriceIndex priceIndex);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(PriceIndex  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(PriceIndex priceIndex) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(PriceIndex priceIndex) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(PriceIndex priceIndex);
	
	
	/**
	 * 根据主键-查询对象
	 */
	PriceIndex findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	PriceIndex findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<PriceIndex> queryByEntitys(PriceIndex priceIndex);
		
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
	 * 指数列表
	 * @return
	 */
	public Page<Map<String, Object>> queryPriceIndexWithTemp(Map<String, Object> beanToMap, SimplePageInfo pageInfo);
	/**
	 * 新增或更新指数
	 * @return
	 */
	public  boolean saveOrUpdatePriceIndex(PriceIndex target,CurrentUser user) throws BizException;
	
	/**
	 * 获取模板下的最新记录
	 * @param tempId
	 * @return
	 */
	public PriceIndex findLastUpdate(Long tempId);
	
	/**
	 * 通过模板id查找指数前一天记录
	 * @param beanToMap
	 * @return
	 */
	public PriceIndex findPreRecordByTempId(Map<String, Object> beanToMap);
	
	/**
	 * 根据模板id取最近30条数据
	 */
	List<Map<String, Object>> queryLatest30(Long templateId);
	
	/**
	 * 根据模板id取区间的上下限
	 */
	Map<String, Object> queryInterval(Long templateId);
	
	/**
	 * 获取当天的指数数据
	 */
	List<Map<String, Object>> queryLatest1(Map<String, Object> map);
	
	/**
	 * 获取昨天收盘的指数数据
	 */
	List<Map<String, Object>> queryZS(Map<String, Object> map);

	/**
	 * 获取开始时间到结束时间的记录
	 * @param query
	 * @return
	 */
	public List<Map<String, Object>> queryByStartAndEndDate(PriceExcelQuery query);
	

}
