package com.sinochem.crude.trade.orderexecute.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.StatementSheetVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderStatementMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;

public interface OrderStatementService {
	
	public abstract OrderStatementMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderStatement orderStatement);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderStatementId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderStatement  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderStatement orderStatement) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderStatement orderStatement);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderStatement findByPrimaryKey(Long orderStatementId);

	/**
	 * 根据uuid查询对象
	 */
	OrderStatement findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderStatement> queryByEntitys(OrderStatement orderStatement);
		
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
	
	List<OrderStatement> queryByOrderId(Long orderId);
	
	/**
	 * 查询当前已确认或已结算对账单
	 * <p>有正式对账单返回正式对账单，否则返回预估对账单
	 * @param orderId
	 * @return
	 */
	OrderStatement queryActiveStatementByOrderId(Long orderId);
	
	/**
	 * 查询有效正式对账单数量
	 * @param orderId
	 * @return
	 */
	int countValidStatementByOrderId(Long orderId);
	
	/**
	 * 计算结算总金额
	 * @param orderId
	 * @return
	 */
	double calcStatementTotal(Long orderId);
	
	public Map<String,Object> selectinformation(Long orderId);
	
	public Map<String,Object> selectData(OrderStatementVO vo, CurrentUser user);
	
	/**
	 * 根据条件-分页查询(预估对账管理)
	 */
	List<Map<String, Object>> selectPage(OrderStatementQuery query, SimplePageInfo pageInfo);
	
	public void saveStatement(OrderStatementVO vo, CurrentUser user) throws ParseException;
	
	/**
	 * 外部系统-接受对账单信息
	 */
	String receiveOrderStatement(StatementSheetVO vo);
	/**
	 * 查询是否点价
	 * 
	 */
	int queryIsTrigger(Long orderId);
	/**
	 * 
	 * 转月费
	 */
	double findDifferAmount(Long orderId);
	/**
	 * 计算结算价和结算桶
	 */
	public Map<String, Object> selectDataForTriggerResult(Long orderId);


	public int updateStatusById(OrderStatementVO vo);

	public int editStatusById(OrderStatementVO vo); 
}
