package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.orderexecute.commons.constants.MsgRemindingTemplateEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.MsgRemindingTypeEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.StatementStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.utils.MessageHttpUtil;
import com.sinochem.crude.trade.orderexecute.dao.OrderMsgRemindingMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderMsgReminding;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.StatementMsgParamVO;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPartyService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;

@Service
public class OrderMsgRemindingServiceImpl implements OrderMsgRemindingService {
	
	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private OrderMsgRemindingMapper orderMsgRemindingMapper;
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	@Resource(name="systemServer")
	private URLBroker systemServer;
	@Autowired
	private OrderPartyService orderPartyService;
	@Autowired
	private HttpConnectionManager httpConnectionManager;
	
	@Resource(name="messageServer")
	private URLBroker messageServer;
	
	public OrderMsgRemindingMapper getMapper(){
		return orderMsgRemindingMapper;
	} 
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 * @throws BizException 
	 */
	@Override
	public int deleteById(Long orderId, String msgCode) throws BizException {
		if (orderId == null || StringUtils.isEmpty(msgCode)) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderMsgRemindingMapper.deleteById(orderId, msgCode);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderMsgReminding findByPrimaryKey(Long orderId, String msgCode){
		return  orderMsgRemindingMapper.findByPrimaryKey(orderId, msgCode);	
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public void createMsg(MsgRemindingTemplateEnum msgTemp, Long orderId, Long receiveUserId, Date schedule, Map<String, Object> params) {
		if(orderId == null) {
			return;
		}
		
		OrderMsgReminding msg = orderMsgRemindingMapper.findByPrimaryKey(orderId, msgTemp.getMsgCode());
		if(msg != null) {
			updateSchedule(msgTemp, orderId, schedule);
		}else {
			if(receiveUserId == null) {
				return;
			}
			
			OrderMsgReminding msgReminding = new OrderMsgReminding();
			
			msgReminding.setOrderId(orderId);
			msgReminding.setMsgCode(msgTemp.getMsgCode());
			msgReminding.setMsgType(MsgRemindingTypeEnum.MSG.name());
			msgReminding.setMsgTitle(msgTemp.getTitle());
			msgReminding.setMsgTemplate(msgTemp.getTemplateName());
			msgReminding.setMsgParams(JSONObject.toJSONString(params));
			msgReminding.setReceiveUserId(receiveUserId);
			msgReminding.setSchedule(schedule);
			
			orderMsgRemindingMapper.createMsg(msgReminding);
		}
	}

	@Override
	public void removeMsg(MsgRemindingTemplateEnum typeEnum, Long orderId) {
		orderMsgRemindingMapper.removeMsg(orderId, typeEnum.getMsgCode());
	}
	
	@Override
	public void updateSchedule(MsgRemindingTemplateEnum typeEnum, Long orderId, Date schedule) {
		orderMsgRemindingMapper.updateSchedule(orderId, typeEnum.getMsgCode(), schedule);
	}

	@Override
	public void finishExecMsg(MsgRemindingTemplateEnum typeEnum, Long orderId) {
		orderMsgRemindingMapper.finishExecMsg(orderId, typeEnum.getMsgCode());
		
	}
	
	@Override
	public void finishExecMsg(Long orderId, String msgCode) {
		orderMsgRemindingMapper.finishExecMsg(orderId, msgCode);
	}
	
	@Override
	public List<OrderMsgReminding> queryScheduleMsgList(){
		return orderMsgRemindingMapper.queryScheduleMsgList();
	}
	@Override
	public void createNorUpdateReminding(Long orderId, Long receiveUserId, Date nor, Map<String, Object> params) {
		if(nor == null) {
			return;
		}
		Date schedule = DateUtils.addDays(nor, 1);
		createMsg(MsgRemindingTemplateEnum.NOR_REMINDING, orderId, receiveUserId, schedule, params);
	}
	@Override
	public void createCodUpdateReminding(Long orderId, Long receiveUserId, Date cod, Map<String, Object> params) {
		if(cod == null) {
			return;
		}
		Date schedule = DateUtils.addDays(cod, 1);
		createMsg(MsgRemindingTemplateEnum.COD_REMINDING, orderId, receiveUserId, schedule, params);
	}
	@Override
	public void createBillUpdateReminding(Long orderId, Long receiveUserId, Date billDate, Map<String, Object> params) {
		if(billDate == null) {
			return;
		}
		Date schedule = DateUtils.addDays(billDate, 1);
		createMsg(MsgRemindingTemplateEnum.BILLDATE_UPDATE_REMINDING, orderId, receiveUserId, schedule, params);
	}
	@Override
	public void createStatementCreateReminding(Long orderId, Long receiveUserId, 
			String paymentTermJson, Date payDate, Map<String, Object> params) {
		//付款日期前5天发送提醒
		Date schedule = DateUtils.addDays(payDate, -5);
		createMsg(MsgRemindingTemplateEnum.CREATE_STATEMENT_REMINDING, orderId, receiveUserId, schedule, params);
	}
	@Override
	public void createUploadPaymentVoucherReminding(Long orderId, Long receiveUserId, 
			Date payDate, Boolean formally, Map<String, Object> params) {
		if(payDate == null) {
			return;
		}
		//付款日第二天给出提醒
		Date schedule = DateUtils.addDays(payDate, 1);
		if(formally) {
			createMsg(MsgRemindingTemplateEnum.UPLOAD_PAYMENT_VOUCHER_REMINDING_F, orderId, receiveUserId, schedule, params);
		}else{
			createMsg(MsgRemindingTemplateEnum.UPLOAD_PAYMENT_VOUCHER_REMINDING_L, orderId, receiveUserId, schedule, params);
		}
	}
	@Override
	public void createCheckReceiptReminding(Long orderId, Long receiveUserId, 
			Date payDate, Map<String, Object> params) {
		if(payDate == null) {
			return;
		}
		createMsg(MsgRemindingTemplateEnum.CHECK_RECEIPT_REMINDING, orderId, receiveUserId, payDate, params);
	}

	@Override
	public void closeNorUpdateReminding(Long orderId) {
		removeMsg(MsgRemindingTemplateEnum.NOR_REMINDING, orderId);
	}

	@Override
	public void closeCodUpdateReminding(Long orderId) {
		removeMsg(MsgRemindingTemplateEnum.COD_REMINDING, orderId);
	}

	@Override
	public void closeBillUpdateReminding(Long orderId) {
		removeMsg(MsgRemindingTemplateEnum.BILLDATE_UPDATE_REMINDING, orderId);
	}

	@Override
	public void closeStatementCreateReminding(Long orderId) {
		removeMsg(MsgRemindingTemplateEnum.CREATE_STATEMENT_REMINDING, orderId);
	}

	@Override
	public void closeUploadPaymentVoucherReminding(Long orderId,Boolean formally) {
		if(formally) {
			removeMsg(MsgRemindingTemplateEnum.UPLOAD_PAYMENT_VOUCHER_REMINDING_F, orderId);
		}else {
			removeMsg(MsgRemindingTemplateEnum.UPLOAD_PAYMENT_VOUCHER_REMINDING_L, orderId);
		}
		
	}

	@Override
	public void closeCheckReceiptReminding(Long orderId) {
		removeMsg(MsgRemindingTemplateEnum.CHECK_RECEIPT_REMINDING, orderId);
	}
	
	@Override
	public void sendStatementProcessMsg(StatementStatusEnum statusEnum, StatementMsgParamVO params) {
		Long receiveUserId = params.getReceiverId();
		Long orderId = params.getOrderId();
		Long customerId = params.getCustomerId();
		
		params.setLoginLink(systemServer.get("login.htm").toString());
		
		MsgRemindingTemplateEnum msgTemplateInfo;
		switch (statusEnum) {
		case ORDER_STATUS_ITEM_3_1:
			msgTemplateInfo = MsgRemindingTemplateEnum.STATEMENT_SUBMITED_NOTIFY;
			break;
		case ORDER_STATUS_ITEM_3_2:
			msgTemplateInfo = MsgRemindingTemplateEnum.STATEMENT_CONFIRM_NOTIFY;
			break;
		case ORDER_STATUS_ITEM_3_3:
			msgTemplateInfo = MsgRemindingTemplateEnum.STATEMENT_REJECT_NOTIFY;
			break;
		case ORDER_STATUS_ITEM_3_9:
			msgTemplateInfo = MsgRemindingTemplateEnum.STATEMENT_UPLOAD_PAYMENT_VOUCHER_NOTIFY;
			break;

		default:
			return;
		}
		
		//站内消息
		List<NameValuePair> msgParams = MessageHttpUtil.generateMessageParams(
				msgTemplateInfo.getTemplateName(), 
				String.valueOf(receiveUserId),
				"3",
				msgTemplateInfo.getTitle(), 
				params.toMap());
		//app推送
		List<NameValuePair> appParams = MessageHttpUtil.generateAPPParams(msgTemplateInfo.getTemplateName(), 
				String.valueOf(receiveUserId), params.toMap());
		
		List<NameValuePair> mailParams = null;
		List<NameValuePair> smsParams = null;
		OrderParty contactUserInfo = orderPartyService.findByOrderIdAndCustomerId(orderId, customerId);
		if(contactUserInfo != null) {
			String email = contactUserInfo.getEmail();
			String tel = contactUserInfo.getTel();
			//email
			if(StringUtils.isNotBlank(email)) {
				mailParams = MessageHttpUtil.generateTplMailParams(msgTemplateInfo.getTemplateName(), 
						msgTemplateInfo.getTitle(), email, params.toMap());
			}
			if(StringUtils.isNotBlank(tel)) {
				//短信
				smsParams = MessageHttpUtil.generateSmsParams(msgTemplateInfo.getTemplateName(), tel, params.toMap());
			}
		}
		
		sendMsg(MessageHttpUtil.MESSAGE_URL, msgTemplateInfo.getTitle(), msgParams);
		sendMsg(MessageHttpUtil.APP_URL, msgTemplateInfo.getTitle(), appParams);
		if(mailParams != null) {
			sendMsg(MessageHttpUtil.MAIL_TPL_URL, msgTemplateInfo.getTitle(), mailParams);
		}
		if(smsParams != null) {
			sendMsg(MessageHttpUtil.SMS_URL, msgTemplateInfo.getTitle(), smsParams);
		}
		
	}
	
	private void sendMsg(String sendurl, String title,List<NameValuePair> msgParams) {
		log.info("开始发送消息... server:"+sendurl);
		String ret;
		try {
			ret = MessageHttpUtil.sendMessage(
					httpConnectionManager.getHttpClient(), 
					messageServer.get(sendurl).toString(),
					msgParams);
		} catch (BizException e) {
			log.error("发送消息【"+title+"】失败", e);
			return;
		}
		
		JSONObject retJson = JSONObject.parseObject(ret);
		Integer status = retJson.getInteger("status");
		String message = retJson.getString("message");
		
		if(status == 0) {
			log.info("发送消息【"+title+"】成功");
		}else {
			log.error("发送消息【"+title+"】失败："+message);
		}
	}
}
