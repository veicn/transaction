package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.domain.Fabulous;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.FabulousMapper; 

public interface FabulousService {
	
	public abstract FabulousMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Fabulous fabulous);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	

	 /**
    * 根据条件-物理删除对象执行delete语句, 慎用！！！
    */
    public int deleteRecords(Fabulous  record);
	
    /**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Fabulous fabulous) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Fabulous fabulous) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Fabulous fabulous);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Fabulous findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Fabulous findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Fabulous> queryByEntitys(Fabulous fabulous);
		
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
	
	/*踩资讯、评论*/
	public abstract void treadInfo(FabulousVO vo, CurrentUser user);

	/*点赞资讯、评论*/
	public abstract void praiseInfo(FabulousVO vo, CurrentUser user); 
	
}
