package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeDissentMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeDissent;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeDissentVO;
import com.sinochem.crude.trade.orderexecute.query.OrderFeeDissentQuery;

public interface OrderFeeDissentService {
	
	public abstract OrderFeeDissentMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderFeeDissent orderFeeDissent);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long feeDissentId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderFeeDissent  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderFeeDissent orderFeeDissent) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderFeeDissent orderFeeDissent);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderFeeDissent findByPrimaryKey(Long feeDissentId);

	/**
	 * 根据uuid查询对象
	 */
	OrderFeeDissent findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderFeeDissent> queryByEntitys(OrderFeeDissent orderFeeDissent);
		
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
	 * 取得异议留言
	 */	
	List<Map<String, Object>> getDissentList(OrderFeeDissentQuery query);
	
	/**
	 * 保存留言
	 */
	String saveReplyContent(OrderFeeDissentVO vo, CurrentUser user);
	
	/**
	 * 保存确认信息
	 */
	String saveDissentContent(OrderFeeDissentVO vo, CurrentUser user);	
	
}
