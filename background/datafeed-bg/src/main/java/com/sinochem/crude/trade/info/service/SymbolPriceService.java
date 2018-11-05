package com.sinochem.crude.trade.info.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.domain.SymbolPrice;
import com.sinochem.crude.trade.info.model.SymbolPriceVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.SymbolPriceMapper; 

public interface SymbolPriceService {
	
	public abstract SymbolPriceMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(SymbolPrice symbolPrice);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(SymbolPrice  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(SymbolPrice symbolPrice) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(SymbolPrice symbolPrice) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(SymbolPrice symbolPrice);
	
	
	/**
	 * 根据主键-查询对象
	 */
	SymbolPrice findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	SymbolPrice findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<SymbolPrice> queryByEntitys(SymbolPrice symbolPrice);
		
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

	public  Page<Map<String, Object>> querySymbolPriceIndex(Map<String, Object> beanToMap,
			SimplePageInfo pageInfo);

	public  boolean saveOrUpdateSymbolPrice(SymbolPrice target, CurrentUser user);

	public abstract List<Map<String,Object>> getSymbolPrice(SymbolPriceVO vo); 
	
	

	//**************************以下方法为开发者补充*********************************/
	public abstract void exportList(HttpServletResponse response,SymbolPriceVO vo);

	public abstract ResultDatas<Void> crudeOilImport(InputStream inputStream, CurrentUser user);

	public abstract Map<String, Object> getNewSymbolPrice(SymbolPriceVO vo); 
}
