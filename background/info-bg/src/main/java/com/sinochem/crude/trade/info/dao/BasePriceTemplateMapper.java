package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.BasePriceTemplate;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface BasePriceTemplateMapper {

	public int insertRecord(BasePriceTemplate entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(BasePriceTemplate entity);
	
	public int updateRecordById(BasePriceTemplate entity);
	
	public int updateRecordByUuid(BasePriceTemplate entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public BasePriceTemplate findByPrimaryKey( Long id);	
	
	public BasePriceTemplate findByUuid(String uuid);	
	
	//返回对象的List
	public List<BasePriceTemplate> queryByEntitys(BasePriceTemplate entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 基价模板列表
	 * @param beanToMap
	 * @return
	 */
	public List<Map<String,Object>> queryLikeNameRecords(Map<String, Object> beanToMap);

	/**
	 * 根据模板编码查找模板
	 * @param baseCode
	 * @return
	 */
	public BasePriceTemplate queryBaseCode(String baseCode);

	/**
	 * 更新基价模板禁用启用状态
	 * @param basePriceTemp
	 * @return
	 */
	public int updateBasePriceTempStatus(BasePriceTemplate basePriceTemp);
}
