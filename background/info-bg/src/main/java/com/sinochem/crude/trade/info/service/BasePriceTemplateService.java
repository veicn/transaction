package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.BasePriceTemplateMapper;
import com.sinochem.crude.trade.info.domain.BasePriceTemplate;

public interface BasePriceTemplateService {
	
	public abstract BasePriceTemplateMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(BasePriceTemplate basePriceTemplate);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(BasePriceTemplate  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(BasePriceTemplate basePriceTemplate) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(BasePriceTemplate basePriceTemplate) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(BasePriceTemplate basePriceTemplate);
	
	
	/**
	 * 根据主键-查询对象
	 */
	BasePriceTemplate findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	BasePriceTemplate findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<BasePriceTemplate> queryByEntitys(BasePriceTemplate basePriceTemplate);
		
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
	 * 基价模板列表
	 * @return
	 */
	public  Page<Map<String, Object>> queryLikeNameRecords(Map<String, Object> beanToMap,
			SimplePageInfo pageInfo);
	/**
	 * 新增或编辑基价
	 * @throws BizException
	 */
	public  Result saveOrUpdateBasePriceIndexTemp(BasePriceTemplate basepriceTemp) throws BizException;
	
	/**
	 * 设置基价模板状态
	 * @return
	 */
	public  boolean setBasePriceTempStatus(String uuid);

	/**
	 * 根据模板编码查找模板
	 * @param baseCode
	 * @return
	 */
	public BasePriceTemplate findByBaseCode(String baseCode);
	
}
