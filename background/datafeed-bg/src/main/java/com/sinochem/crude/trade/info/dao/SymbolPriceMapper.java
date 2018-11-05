package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.SymbolPrice;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.info.model.SymbolPriceVO;

//@Mapper
public interface SymbolPriceMapper {

	public int insertRecord(SymbolPrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(SymbolPrice entity);
	
	public int updateRecordById(SymbolPrice entity);
	
	public int updateRecordByUuid(SymbolPrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public SymbolPrice findByPrimaryKey( Long id);	
	
	public SymbolPrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<SymbolPrice> queryByEntitys(SymbolPrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	public Page<Map<String, Object>> querySymbolPriceIndex(Map<String, Object> beanToMap);

	public List<Map<String, Object>> getSymbolPrice(SymbolPriceVO vo);

	public Map<String, Object> getNewSymbolPrice(SymbolPriceVO vo);
	
}
