package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.domain.OilToHarbor;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.OilToHarborMapper; 

public interface OilToHarborService {
	
	public abstract OilToHarborMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OilToHarbor oilToHarbor);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OilToHarbor  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OilToHarbor oilToHarbor) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(OilToHarbor oilToHarbor) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OilToHarbor oilToHarbor);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OilToHarbor findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	OilToHarbor findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OilToHarbor> queryByEntitys(OilToHarbor oilToHarbor);
		
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

	public abstract Result saveOrUpdate(OilToHarbor oil, CurrentUser user);

	public abstract ResultDatas<Void> importExcel(InputStream inputStream, CurrentUser user, ResultDatas<Void> res);

	
	

	//**************************以下方法为开发者补充*********************************/
	
}
