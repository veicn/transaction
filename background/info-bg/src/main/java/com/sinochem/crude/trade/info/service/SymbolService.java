package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.domain.Symbol;
import com.sinochem.crude.trade.info.model.SymbolVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.SymbolMapper; 

public interface SymbolService {
	
	public abstract SymbolMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Symbol symbol);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Symbol  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Symbol symbol) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Symbol symbol) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Symbol symbol);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Symbol findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Symbol findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Symbol> queryByEntitys(Symbol symbol);
		
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

	public abstract Result saveOrUpdateSymbol(Symbol symbol,CurrentUser user);

	
	

	//**************************以下方法为开发者补充*********************************/
	
	public abstract List<Map<String, Object>> getSymbol(SymbolVO vo); 
	
	/**
	 * 获取标的商品列表
	 * @return
	 */
	List<String> getCommodityList(Map<String, Object> map);
	
	/**
	 * 获取价格源列表
	 * @return
	 */
	List<String> getPriceSourceList(Map<String, Object> map);
	
	/**
	 * 获取市场列表
	 * @return
	 */
	List<String> getMarketList(Map<String, Object> map);

	public abstract int updateSymbolNameBySymbol(Symbol symbol);

	public abstract Integer querySymbol(Symbol symbol);

	public abstract String queryBySymbol(String crudeName);

	public  void editSymbolName(String symbolCode, String symbolName);

	public abstract void saveOrUpdateExtend1(String symbolCode, String string);
}
