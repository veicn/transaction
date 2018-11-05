package com.sinochem.crude.trade.orderexecute.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.orderexecute.dao.OrderMessagePushMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderMessagePushService;


@Service
public class OrderMessagePushServiceImpl implements OrderMessagePushService {
	
	private static final Log log = LogFactory.getLog(OrderMessagePushServiceImpl.class);
	
	@Autowired
	private OrderMessagePushMapper orderMessagePushMapper;
	
	@Value("${app.server.host}")
	private String appHost;
	
	@Value("${orderexecute.server.host}")
	private String orderHost;	
	
	@Value("${member.server.host}")
	private String memberHost;
	
	public OrderMessagePushMapper getMapper(){
		return orderMessagePushMapper;
	}

	private String MESSAGE_001 = "订单已生成，请上传外贸合同、代理协议，点击进入》";
	private String MESSAGE_002 = "卖家已对订单{0}上传外贸合同、原油进口代理协议，点击可查看》";
	private String MESSAGE_003 = "该订单{0}下的货品即将离港，请上传装港单据，点击进入》";
	private String MESSAGE_004 = "该订单{0}已上传提单、品质证书、原产地证书、数量证书，点击可查看》";
	private String MESSAGE_005 = "该订单{0}下的货品即将到港，请上传报关报检单据，点击进入》";
	private String MESSAGE_006 = "该订单{0}下的货品即将到港，请上传报关报检单据，点击进入》";
	private String MESSAGE_007 = "该订单{0}已上传进口许可证、报关委托、报检委托、符合性申明、公式定价、临时价格说明、最终价格说明、临时报关发票、最终报关发票，点击可查看》";
	private String MESSAGE_008 = "该订单{0}已上传符合性申明、公式定价、临时价格说明、最终价格说明，点击可查看》";
	private String MESSAGE_009 = "该订单{0}已上传临时报关发票、最终报关发票，点击可查看》";
	private String MESSAGE_010 = "该订单{0}下的货品即将完成卸货，请上传结算单据，点击进入》";
	private String MESSAGE_011 = "该订单{0}下的货品即将完成卸货，请上传结算单据，点击进入》";
	private String MESSAGE_012 = "该订单{0}已上传独立商检报告、CIQ证书、船代文件，点击可查看》";
	private String MESSAGE_013 = "该订单{0}已上传CIQ证书、船代文件，点击可查看》";
	private String MESSAGE_014 = "该订单{0}已上传独立商检报告，点击可查看》";
	private String MESSAGE_015 = "请于该订单{0}的付款日{1}前上传结算单据，点击进入》";
	private String MESSAGE_016 = "该订单{0}已上传临时发票、最终发票，点击可查看》";
	private String MESSAGE_017 = "订单已生成，请上传外贸合同、代理协议、出口许可证，点击进入》";
	private String MESSAGE_018 = "卖家已对订单{0}上传外贸合同、代理协议、出口许可证，点击可查看》";
	private String MESSAGE_019 = "该订单{0}下的货品即将装港，请上传报关报检单据，点击进入》";
	private String MESSAGE_020 = "该订单{0}已上传报关委托、报检委托、符合性申明、临时报关发票、最终报关发票、最终价格说明、舱单、装箱单、产品质量合格证，点击可查看》";
	private String MESSAGE_021 = "该订单{0}下的货品即将完成装货，请上传装货单据，点击进入》";
	private String MESSAGE_022 = "该订单{0}已上传独立商检报告、提单、品质证书、原产地证书、数量证书，点击可查看》";
	private String MESSAGE_023 = "请于该订单{0}的付款日{1}前上传结算单据，点击进入》";
	private String MESSAGE_024 = "该订单{0}已上传临时发票、最终发票，点击可查看》";
	
	// 1卖家；2买家；3贸易商
	private int SELLER = 1;
	private int BUYER = 2;
	private int TRADER = 3;

	/**
	 * 消息推送
	 */
	@Override
	public void messagePush(int level, String uuid, Long memberId, String documenType, String paymentDate) {
		if(level == 1) {
			if("T10".equals(documenType)) {
				// 上传外贸合同、代理协议
				// 订单生成后提醒卖家，卖家完成后，再通知买家
				// 卖家执行人、贸易公司执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_001, paymentDate);
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_001, paymentDate);
			}
			
			if("T11".equals(documenType)) {
				// 上传装港单据
				// 装期最后一天起算，倒数第5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_003, paymentDate);
			}
			
			if("T12".equals(documenType)) {
				// 上传报关报检单据
				// 到港前10天（ETA前10天提醒）起提醒贸易公司/卖家，贸易公司/卖家完成后，再通知买家/卖家/贸易公司
				
				// 贸易公司执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_005, paymentDate);
				
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_006, paymentDate);				
			}
			
			if("T13".equals(documenType)) {
				// 上传卸货单据
				// 卸货后5天起提醒卖家/贸易公司，卖家/贸易公司完成后，再通知买家/卖家/贸易公司
				
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_010, paymentDate);
				
				// 贸易公司执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_011, paymentDate);				
			}
			
			if("T14".equals(documenType)) {
				// 上传结算单据
				// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// （临时价格说明根据系统是否生成预估结算单且确认判断是否发送，最终价格说明根据系统是否生成正式结算单判断是否发送）
				
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_015, paymentDate);				
			}
			
			if("T20".equals(documenType)) {
				// 上传外贸合同、代理协议、出口许可证
				// 订单生成后提醒卖家，卖家完成后，再通知买家、贸易公司
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_017, paymentDate);
			}
			
			if("T21".equals(documenType)) {
				// 上传报关报检单据
				// 船到装港前10天（装期前10天）起提醒贸易公司，贸易公司完成后，再通知买家、卖家
				// 贸易公司执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_019, paymentDate);
			}
			
			if("T22".equals(documenType)) {
				// 上传装货单据
				// 装货后5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_021, paymentDate);
			}
			
			if("T24".equals(documenType)) {
				// 上传结算单据
				// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_023, paymentDate);
			}

		}
		
		if(level == 2) {
			if("T10".equals(documenType)) {
				// 上传外贸合同、代理协议
				// 订单生成后提醒卖家，卖家完成后，再通知买家
				// 买家执行人
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_002, paymentDate);
			}
			
			if("T11".equals(documenType)) {
				// 上传装港单据
				// 装期最后一天起算，倒数第5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 贸易公司执行人、买家执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_004, paymentDate);
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_004, paymentDate);
			}
			
			if("T12".equals(documenType)) {
				// 上传报关报检单据
				// 到港前10天（ETA前10天提醒）起提醒贸易公司/卖家，贸易公司/卖家完成后，再通知买家/卖家/贸易公司
				
				// 买家执行人
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_007, paymentDate);
				
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_008, paymentDate);
				
				// 贸易公司执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_009, paymentDate);				
			}
			
			if("T13".equals(documenType)) {
				// 上传卸货单据
				// 卸货后5天起提醒卖家/贸易公司，卖家/贸易公司完成后，再通知买家/卖家/贸易公司
				
				// 买家执行人
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_012, paymentDate);
				
				// 卖家执行人
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_013, paymentDate);	
				
				// 贸易公司执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_014, paymentDate);	
			}
			
			if("T14".equals(documenType)) {
				// 上传结算单据
				// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// （临时价格说明根据系统是否生成预估结算单且确认判断是否发送，最终价格说明根据系统是否生成正式结算单判断是否发送）
				
				// 贸易公司执行人、买家执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_016, paymentDate);
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_016, paymentDate);
			}
			
			if("T20".equals(documenType)) {
				// 上传外贸合同、代理协议、出口许可证
				// 订单生成后提醒卖家，卖家完成后，再通知买家、贸易公司
				// 贸易公司执行人、买家执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_018, paymentDate);
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_018, paymentDate);
			}
			
			if("T21".equals(documenType)) {
				// 上传报关报检单据
				// 船到装港前10天（装期前10天）起提醒贸易公司，贸易公司完成后，再通知买家、卖家
				// 买家执行人、卖家执行人
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_020, paymentDate);
				pushToPerson(level, SELLER, uuid, memberId, MESSAGE_020, paymentDate);
			}
			
			if("T22".equals(documenType)) {
				// 上传装货单据
				// 装货后5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 贸易公司执行人、买家执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_022, paymentDate);
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_022, paymentDate);
			}
			
			if("T24".equals(documenType)) {
				// 上传结算单据
				// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
				// 贸易公司执行人、买家执行人
				pushToPerson(level, TRADER, uuid, memberId, MESSAGE_024, paymentDate);
				pushToPerson(level, BUYER, uuid, memberId, MESSAGE_024, paymentDate);
			}
			
		}
	}

	private void pushToPerson(int level, int role, String uuid, Long memberId, String message, String paymentDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		map.put("role", role);
		List<Map<String, Object>> orderAndPeison = orderMessagePushMapper.getOrderAndPeison(map);
		
		if(orderAndPeison != null && orderAndPeison.size() > 0) {
			message = MessageFormat.format(message, String.valueOf(orderAndPeison.get(0).get("orderNo")), paymentDate);
			
			for(int i = 0; i < orderAndPeison.size(); i++) {
				try{	
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("fromId", memberId);
					jsonMap.put("level", level);
					jsonMap.put("createUser", memberId);
					jsonMap.put("content", message);
					jsonMap.put("toId", (Long) orderAndPeison.get(i).get("memberId"));
					jsonMap.put("callback", "http://" + orderHost + "/buyerCenter/orderDocument/documentList.htm?uuid=" + uuid);
					
					String ret = HttpUtils.sendRequest("http://" + memberHost + "/message/message/push.json", new JSONObject(jsonMap).toString(), "");
					//String ret = HttpUtils.sendPost("http://member8.1chemic.com/message/message/push.json", new JSONObject(jsonMap).toString(), "");
					log.info("发送参数：" + new JSONObject(jsonMap).toString());
					log.info("返回值：" + ret);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void checkDocument() {
		Long defaultMemberId = (long) 0;
		
		// 上传装港单据
		// 装期最后一天起算，倒数第5天起提醒卖家，卖家完成后，再通知买家、贸易公司
		List<Map<String, Object>> listT11 = orderMessagePushMapper.checkT11("T11");
		if(listT11 != null && listT11.size() > 0) {
			log.info("MessagePushJob ----> " + "T11上传装港单据" + listT11.size());
			
			for(int i = 0; i < listT11.size(); i++) {
				messagePush(1, 
						String.valueOf(listT11.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT11.get(i).get("documentType")), 
						null);
			}
		} else {
			log.info("MessagePushJob ----> " + "T11上传装港单据" + ":无");
		}
		
		// 上传报关报检单据
		// 到港前10天（ETA前10天提醒）起提醒贸易公司/卖家，贸易公司/卖家完成后，再通知买家/卖家/贸易公司
		List<Map<String, Object>> listT12 = orderMessagePushMapper.checkT12("T12");
		if(listT12 != null && listT12.size() > 0) {
			log.info("MessagePushJob ----> " + "T12上传报关报检单据" + listT12.size());
			
			for(int i = 0; i < listT12.size(); i++) {
				messagePush(1, 
						String.valueOf(listT12.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT12.get(i).get("documentType")), 
						null);
			}
		} else {
			log.info("MessagePushJob ----> " + "T12上传报关报检单据" + ":无");
		}
		
		// 上传卸货单据
		// 卸货后5天起提醒卖家/贸易公司，卖家/贸易公司完成后，再通知买家/卖家/贸易公司
		List<Map<String, Object>> listT13 = orderMessagePushMapper.checkT13("T13");
		if(listT13 != null && listT13.size() > 0) {
			log.info("MessagePushJob ----> " + "T13上传卸货单据" + listT13.size());
			
			for(int i = 0; i < listT13.size(); i++) {
				messagePush(1, 
						String.valueOf(listT13.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT13.get(i).get("documentType")), 
						null);
			}
		} else {
			log.info("MessagePushJob ----> " + "T13上传卸货单据" + ":无");
		}
		
		// 上传结算单据
		// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
		// （临时价格说明根据系统是否生成预估结算单且确认判断是否发送，最终价格说明根据系统是否生成正式结算单判断是否发送）
		List<Map<String, Object>> listT14 = null;
		Map<String, Object> quertT14 = new HashMap<String, Object>();
		// 临时
		quertT14.put("documentType", "T14");
		quertT14.put("fileCode", "T1401");
		quertT14.put("settlementType", "1");
		listT14 = orderMessagePushMapper.checkT14(quertT14);
		if(listT14 != null && listT14.size() > 0) {
			log.info("MessagePushJob ----> " + "T1401上传结算单据-临时" + listT14.size());
			
			for(int i = 0; i < listT14.size(); i++) {
				messagePush(1, 
						String.valueOf(listT14.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT14.get(i).get("documentType")), 
						String.valueOf(listT14.get(i).get("paymentDate")));
			}
		} else {
			log.info("MessagePushJob ----> " + "T1401上传结算单据-临时" + ":无");
		}
		
		// 最终
		quertT14.put("documentType", "T14");
		quertT14.put("fileCode", "T1402");
		quertT14.put("settlementType", "2");
		listT14 = orderMessagePushMapper.checkT14(quertT14);
		if(listT14 != null && listT14.size() > 0) {
			log.info("MessagePushJob ----> " + "T1402上传结算单据-最终" + listT14.size());
			
			for(int i = 0; i < listT14.size(); i++) {
				messagePush(1, 
						String.valueOf(listT14.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT14.get(i).get("documentType")), 
						String.valueOf(listT14.get(i).get("paymentDate")));
			}
		} else {
			log.info("MessagePushJob ----> " + "T1402上传结算单据-最终" + ":无");
		}
		
		// 上传报关报检单据
		// 船到装港前10天（装期前10天）起提醒贸易公司，贸易公司完成后，再通知买家、卖家
		List<Map<String, Object>> listT21 = orderMessagePushMapper.checkT21("T21");
		if(listT21 != null && listT21.size() > 0) {
			log.info("MessagePushJob ----> " + "T21上传报关报检单据" + listT21.size());
			
			for(int i = 0; i < listT21.size(); i++) {
				messagePush(1, 
						String.valueOf(listT21.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT21.get(i).get("documentType")), 
						null);
			}
		} else {
			log.info("MessagePushJob ----> " + "T21上传报关报检单据" + ":无");
		}
		
		// 上传装货单据
		// 装货后5天起提醒卖家，卖家完成后，再通知买家、贸易公司
		List<Map<String, Object>> listT22 = orderMessagePushMapper.checkT22("T22");
		if(listT22 != null && listT22.size() > 0) {
			log.info("MessagePushJob ----> " + "T22上传装货单据" + listT22.size());
			
			for(int i = 0; i < listT22.size(); i++) {
				messagePush(1, 
						String.valueOf(listT22.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT22.get(i).get("documentType")), 
						null);
			}
		} else {
			log.info("MessagePushJob ----> " + "T22上传装货单据" + ":无");
		}
		
		// 上传结算单据
		// 根据成交确认中的付款日计算方式确定付款日，在付款日前5天起提醒卖家，卖家完成后，再通知买家、贸易公司
		List<Map<String, Object>> listT24 = null;
		Map<String, Object> quertT24 = new HashMap<String, Object>();
		// 临时
		quertT24.put("documentType", "T24");
		quertT24.put("fileCode", "T2401");
		quertT24.put("settlementType", "1");
		listT24 = orderMessagePushMapper.checkT24(quertT24);
		if(listT24 != null && listT24.size() > 0) {
			log.info("MessagePushJob ----> " + "T2401上传结算单据-临时" + listT24.size());
			
			for(int i = 0; i < listT24.size(); i++) {
				messagePush(1, 
						String.valueOf(listT24.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT24.get(i).get("documentType")), 
						String.valueOf(listT24.get(i).get("paymentDate")));
			}
		} else {
			log.info("MessagePushJob ----> " + "T2401上传结算单据-临时" + ":无");
		}
		
		// 最终
		quertT24.put("documentType", "T24");
		quertT24.put("fileCode", "T2402");
		quertT24.put("settlementType", "2");
		listT24 = orderMessagePushMapper.checkT24(quertT24);
		if(listT24 != null && listT24.size() > 0) {
			log.info("MessagePushJob ----> " + "T2402上传结算单据-最终" + listT24.size());
			
			for(int i = 0; i < listT24.size(); i++) {
				messagePush(1, 
						String.valueOf(listT24.get(i).get("uuid")), 
						defaultMemberId, 
						String.valueOf(listT24.get(i).get("documentType")), 
						String.valueOf(listT24.get(i).get("paymentDate")));
			}
		} else {
			log.info("MessagePushJob ----> " + "T2402上传结算单据-最终" + ":无");
		}
		
	}

}
