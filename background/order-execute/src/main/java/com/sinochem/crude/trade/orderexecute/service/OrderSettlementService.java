package com.sinochem.crude.trade.orderexecute.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.dao.OrderSettlementMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery; 

public interface OrderSettlementService {
	
	public abstract OrderSettlementMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderSettlement orderSettlement);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderSettlementId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderSettlement  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderSettlement orderSettlement) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderSettlement orderSettlement);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderSettlement findByPrimaryKey(Long orderSettlementId);

	/**
	 * 根据uuid查询对象
	 */
	OrderSettlement findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderSettlement> queryByEntitys(OrderSettlement orderSettlement);
		
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
	 * 根据条件-分页查询（预估结算管理）
	 */
	List<Map<String, Object>> selectAccountsPage(OrderStatementQuery query, SimplePageInfo pageInfo);

	public Result saveSettlement(OrderStatementVO vo, CurrentUser user) throws ParseException;

	public Map<String,Object> selectinformation(Long orderId);
	
	List<OrderSettlement> queryByOrderId(Long orderId);
	
	/**
	 * 查询当前有效结算单
	 * <p>有正式结算单返回正式结算单，否则返回预估结算单
	 * @param orderId
	 * @return
	 */
	OrderSettlement queryActiveSettlementByOrderId(Long orderId);
	/**
	 * 计算结算总金额
	 * @param orderId
	 * @return
	 */
	double calcSettlementTotal(Long orderId);
	
}
