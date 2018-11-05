package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.BasePriceMapper;
import com.sinochem.crude.trade.info.domain.BasePrice;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface BasePriceService {
	
	public abstract BasePriceMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(BasePrice basePrice);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(BasePrice  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(BasePrice basePrice) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(BasePrice basePrice) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(BasePrice basePrice);
	
	
	/**
	 * 根据主键-查询对象
	 */
	BasePrice findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	BasePrice findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<BasePrice> queryByEntitys(BasePrice basePrice);
		
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
	 * 基价列表
	 * @return
	 */
	public Page<Map<String, Object>> queryLikeRecords(Map<String, Object> beanToMap, SimplePageInfo pageInfo);
	/**
	 * 新增或更新基价
	 * @throws BizException
	 */
	public  boolean saveOrUpdateBasePrice(BasePrice target, CurrentUser user) throws BizException;
	
	/**
	 * 查找指定模板下最新
	 */
	public BasePrice findLastUpdate(Long tempId);
}
