package com.sinochem.crude.trade.xxl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.orderexecute.commons.constants.MsgRemindingTypeEnum;
import com.sinochem.crude.trade.orderexecute.commons.utils.MessageHttpUtil;
import com.sinochem.crude.trade.orderexecute.domain.OrderMsgReminding;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import net.sf.json.JSONObject;

/**
 * 订单相关消息提醒
 * @author me
 *
 */
@Component
@JobHander("messageRemindingJob")
public class MessageRemindingJobHandler extends IJobHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private HttpConnectionManager httpConnectionManager;
	
	@Resource(name="messageServer")
	private URLBroker messageServer;
	
	@Autowired
	private OrderMsgRemindingService msgRemindingService;

	@Override
	public ReturnT<String> execute(String... arg0) throws Exception {
		List<OrderMsgReminding> msgList = msgRemindingService.queryScheduleMsgList();
		
		if(msgList != null && !msgList.isEmpty()) {
			for(OrderMsgReminding msg : msgList) {
				sendMsg(msg);
			}
		}
		
		return ReturnT.SUCCESS;
	}
	
	private void sendMsg(OrderMsgReminding msg) {
		//消息类型
		MsgRemindingTypeEnum remindingType = MsgRemindingTypeEnum.getByName(msg.getMsgType());
		if(remindingType == null) {
			return;
		}
		
		List<NameValuePair> params = null;
		switch (remindingType) {
		case APP:
			params = MessageHttpUtil.generateAPPParams(
					msg.getMsgTemplate(), 
					String.valueOf(msg.getReceiveUserId()),
					msg.getMsgParams());
			break;
		case MAIL:
			
			break;
		case MSG:
			params = MessageHttpUtil.generateMessageParams(
					msg.getMsgTemplate(), 
					String.valueOf(msg.getReceiveUserId()),
					"3",
					msg.getMsgTitle(), 
					msg.getMsgParams());
			break;
		case SMS:
			
			break;

		default:
			return;
		}
		
		try {
			String ret = MessageHttpUtil.sendMessage(
					httpConnectionManager.getHttpClient(), 
					messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
					params);
			JSONObject retJson = JSONObject.fromObject(ret);
			Integer status = retJson.getInt("status");
			String message = retJson.getString("message");
			
			if(status == 0) {
				msgRemindingService.finishExecMsg(msg.getOrderId(), msg.getMsgCode());
			}
			
			logger.info("消息提醒【"+msg.getMsgTitle()+"】【"+msg.getMsgType()+"】发送完成。返回结果：" + (status==0?"成功":message));
			
		} catch (BizException e) {
			logger.error("消息发送失败！orderId:"+msg.getOrderId()+",msgCode:"+msg.getMsgCode(), e);
		}
	}

}
