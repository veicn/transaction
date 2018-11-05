package com.sinochem.crude.trade.orderexecute.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.constants.MsgRemindingTemplateEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.StatementStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderMsgRemindingMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderMsgReminding;
import com.sinochem.crude.trade.orderexecute.model.StatementMsgParamVO;
import com.sinochem.it.b2b.common.exception.BizException; 

public interface OrderMsgRemindingService {
	
	public abstract OrderMsgRemindingMapper getMapper(); 
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderId, String msgCode) throws BizException;
	
	/**
	 * 根据主键-查询对象
	 */
	OrderMsgReminding findByPrimaryKey(Long orderId, String msgCode);

	void createMsg(MsgRemindingTemplateEnum msgTemp, Long orderId, Long receiveUserId, Date schedule, Map<String, Object> params);
	void removeMsg(MsgRemindingTemplateEnum typeEnum, Long orderId);
	void finishExecMsg(MsgRemindingTemplateEnum typeEnum, Long orderId);
	void finishExecMsg(Long orderId, String msgCode);
	void updateSchedule(MsgRemindingTemplateEnum typeEnum, Long orderId, Date schedule);
	List<OrderMsgReminding> queryScheduleMsgList();
	
	/**
	 * 增加NOR更新提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param nor nor时间
	 * @param params 模板参数
	 */
	public void createNorUpdateReminding(Long orderId, Long receiveUserId, Date nor, Map<String, Object> params);
	/**
	 * 关闭NOR提醒
	 * @param orderId
	 */
	public void closeNorUpdateReminding(Long orderId);
	
	/**
	 * 增加COD更新提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param cod cod时间
	 * @param params 模板参数
	 */
	public void createCodUpdateReminding(Long orderId, Long receiveUserId, Date cod, Map<String, Object> params);
	/**
	 * 关闭COD提醒
	 * @param orderId
	 */
	public void closeCodUpdateReminding(Long orderId);
	/**
	 * 增加提单日更新提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param billDate 提单日
	 * @param params 模板参数
	 */
	public void createBillUpdateReminding(Long orderId, Long receiveUserId, Date billDate, Map<String, Object> params);
	/**
	 * 关闭提单日更新提醒
	 * @param orderId
	 */
	public void closeBillUpdateReminding(Long orderId);
	/**
	 * 增加创建对账单提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param paymentTermJson 付款日期JSON元数据
	 * @param payDate 付款日期
	 * @param params 模板参数
	 */
	public void createStatementCreateReminding(Long orderId, Long receiveUserId, 
			String paymentTermJson, Date payDate, Map<String, Object> params);
	/**
	 * 关闭对账单创建提醒
	 * @param orderId
	 */
	public void closeStatementCreateReminding(Long orderId);
	/**
	 * 增加上传付款凭证提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param payDate 付款日期
	 * @param formally true:针对正式对账单，false:针对预估对账单
	 * @param params 模板参数
	 */
	public void createUploadPaymentVoucherReminding(Long orderId, Long receiveUserId, 
			Date payDate, Boolean formally,Map<String, Object> params);
	/**
	 * 关闭上传付款凭证提醒
	 * @param orderId
	 * @param formally true:针对正式对账单，false:针对预估对账单
	 */
	public void closeUploadPaymentVoucherReminding(Long orderId, Boolean formally);
	/**
	 * 增加确认收款提醒
	 * @param orderId 订单ID
	 * @param receiveUserId 接收用户ID
	 * @param payDate 付款日期
	 * @param params 模板参数
	 */
	public void createCheckReceiptReminding(Long orderId, Long receiveUserId, 
			Date payDate, Map<String, Object> params);
	/**
	 * 关闭确认收款提醒
	 * @param orderId
	 */
	public void closeCheckReceiptReminding(Long orderId);
	
	/**
	 * 发送结算单相关操作消息通知
	 * @param statusEnum
	 * @param params
	 */
	public void sendStatementProcessMsg(StatementStatusEnum statusEnum, StatementMsgParamVO params);
}
