package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.PriceIndex;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface PriceIndexMapper {

	public int insertRecord(PriceIndex entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(PriceIndex entity);
	
	public int updateRecordById(PriceIndex entity);
	
	public int updateRecordByUuid(PriceIndex entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public PriceIndex findByPrimaryKey( Long id);	
	
	public PriceIndex findByUuid(String uuid);	
	
	//返回对象的List
	public List<PriceIndex> queryByEntitys(PriceIndex entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 指数列表
	 * @return
	 */
	public List<Map<String, Object>> queryPriceIndexWithTemp(Map<String, Object> beanToMap);
	/**
	 * 通过模板id查找指数记录
	 * @return
	 */
	public PriceIndex findLastRecordByTempId(Long tempId);
	
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

	public PriceIndex findPreRecordByTempId(Map<String, Object> beanToMap);

	public PriceIndex findNewestPrice(Long id);
}
