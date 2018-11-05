package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.Symbol;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.info.model.SymbolVO;

//@Mapper
public interface SymbolMapper {

	public int insertRecord(Symbol entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Symbol entity);
	
	public int updateRecordById(Symbol entity);
	
	public int updateRecordByUuid(Symbol entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Symbol findByPrimaryKey( Long id);	
	
	public Symbol findByUuid(String uuid);	
	
	//返回对象的List
	public List<Symbol> queryByEntitys(Symbol entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	public List<Map<String, Object>> getSymbol(SymbolVO vo);
	
	/**
	 * 获取标的商品列表
	 * @return
	 */
	public List<String> getCommodityList(Map<String, Object> map);
	
	/**
	 * 获取价格源列表
	 * @return
	 */
	public List<String> getPriceSourceList(Map<String, Object> map);
	
	/**
	 * 获取市场列表
	 * @return
	 */
	public List<String> getMarketList(Map<String, Object> map);

	public int updateSymbolNameBySymbol(Symbol symbol);

	public Integer querySymbol(Symbol symbol);

	public String queryBySymbol(String crudeName);
	
}
