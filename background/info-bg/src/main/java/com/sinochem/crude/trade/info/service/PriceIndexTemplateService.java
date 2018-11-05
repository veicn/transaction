package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.PriceIndexTemplateMapper;
import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface PriceIndexTemplateService {
	
	public abstract PriceIndexTemplateMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(PriceIndexTemplate priceIndexTemplate);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(PriceIndexTemplate  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(PriceIndexTemplate priceIndexTemplate) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(PriceIndexTemplate priceIndexTemplate) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(PriceIndexTemplate priceIndexTemplate);
	
	
	/**
	 * 根据主键-查询对象
	 */
	PriceIndexTemplate findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	PriceIndexTemplate findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<PriceIndexTemplate> queryByEntitys(PriceIndexTemplate priceIndexTemplate);
		
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
	 * 新增或更新指数模板
	 * @return
	 */
	public Result saveOrUpdatePriceIndexTemp(PriceIndexTemplate priceIndexTemp,CurrentUser user) throws BizException;

	/**
	 * 根据指数模糊查询
	 */
	public Page<Map<String, Object>> queryLikeNameRecords(Map<String, Object> beanToMap, SimplePageInfo pageInfo);

	/**
	 * 设置指数模板状态
	 */
	public boolean setPriceIndexTempStatus(String uuid);
	
	/**
	 * 根据油名称查询到对应的指数模板
	 * @param crudeNameC
	 * @return
	 */
	public  PriceIndexTemplate findBycrudeNameC(String crudeNameC);

	/**
	 * 根据名称查ID
	 * @param tempName
	 * @return
	 */
	public  Long findByName(String tempName);
	
	/**
	 * 查询所有
	 * @return
	 */
	public  List<Map<String, Object>> findAll();

	/**
	 * 根据指数编码查询模板
	 * @param indexCode
	 * @return
	 */
	public PriceIndexTemplate findByIndexCode(String indexCode); 
	
}
