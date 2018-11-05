package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface PriceIndexTemplateMapper {

	public int insertRecord(PriceIndexTemplate entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(PriceIndexTemplate entity);
	
	public int updateRecordById(PriceIndexTemplate entity);
	
	public int updateRecordByUuid(PriceIndexTemplate entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public PriceIndexTemplate findByPrimaryKey( Long id);	
	
	public PriceIndexTemplate findByUuid(String uuid);	
	
	//返回对象的List
	public List<PriceIndexTemplate> queryByEntitys(PriceIndexTemplate entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 模糊分页列表查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryLikeRecords(Map<String, Object> map);

	/**
	 * 通过名称查询ID
	 * @param tempName
	 * @return
	 */
	public Long findByName(String tempName);

	/**
	 * 根据指数编码查询模板
	 * @param indexCode
	 * @return
	 */
	public PriceIndexTemplate queryByIndexCode(String indexCode);
	
}
