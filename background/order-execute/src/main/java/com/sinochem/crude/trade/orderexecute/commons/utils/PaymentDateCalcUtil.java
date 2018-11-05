//package com.sinochem.crude.trade.orderexecute.commons.utils;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.time.DateUtils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.sinochem.crude.trade.common.exception.BizException;
//
///**
// * 计算付款日期逻辑工具类
// * @author me
// *
// */
//public class PaymentDateCalcUtil {
//	
//	/**
//	 * 计算付款日期
//	 * @param paymentTermJson 付款日期JSON元数据
//	 * @param bl    提单日期
//	 * @param nor   船到港可以卸货日期
//	 * @param cod   卸港完成的时间
//	 * @param ldr
//	 * @return
//	 * @throws BizException
//	 */
//	public static Date calc(String paymentTermJson, Date bl, Date nor, Date cod, Date ldr) throws BizException {
//		Date paymentDate = null;
//		JSONObject json = null;
//		
//		if(StringUtils.isBlank(paymentTermJson)) {
//			throw new BizException("付款日期JSON数据缺失");
//		}
//		try {
//			json = JSONObject.parseObject(paymentTermJson);
//		} catch (Exception e) {
//			throw new BizException("付款日期JSON格式不正确");
//		}
//		String typeEvent = json.getString("typeEvent");
//		String eventInclusion = json.getString("eventInclusion");
//		String eventDays = json.getString("eventDays");
//		String date = json.getString("date");
//		
//		int eventDay = 0;
//		if(StringUtils.isNotBlank(eventDays)) {
//			eventDay += Integer.parseInt(eventDays);
//		}
//		
//		if ("B/L".equalsIgnoreCase(typeEvent)) {
//			paymentDate = bl;
//		} else if ("NOR".equalsIgnoreCase(typeEvent)) {
//			paymentDate = nor;
//		} else if ("COD".equalsIgnoreCase(typeEvent)) {
//			paymentDate = cod;
//		} else if ("LDR".equalsIgnoreCase(typeEvent)) {
//			paymentDate = ldr;
//		} else if ("DATE".equalsIgnoreCase(typeEvent)) {
//			try {
//				paymentDate = DateUtils.parseDate(date, new String[] {"yyyy-MM-dd"});
//			} catch (ParseException e) {
//				throw new BizException("付款日期JSON格式不正确:typeEvent="+typeEvent+",date="+date);
//			}
//		} else {
//			throw new BizException("付款日期JSON格式不正确:typeEvent="+typeEvent);
//		}
//		
//		if (paymentDate == null) {
//			return null;
//		} else {
//			if("from".equalsIgnoreCase(eventInclusion)) {//包含当前天
//				paymentDate = DateUtils.addDays(paymentDate, eventDay-1);
//			}else if("after".equalsIgnoreCase(eventInclusion)) {//不包含当前天
//				paymentDate = DateUtils.addDays(paymentDate, eventDay);
//			}
//		}
//		
//		return paymentDate;
//		
//	}
//	
//	public static Map<String, String> analyse(String paymentTermJson) {
//		JSONObject json;
//		try {
//			json = JSONObject.parseObject(paymentTermJson);
//		} catch (Exception e) {
//			throw new BizException("付款日期JSON格式不正确");
//		}
//		String typeEvent = json.getString("typeEvent");
//		if("B/L".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("NOR".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("COD".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("LDR".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("DATE".equalsIgnoreCase(typeEvent)) {
//			
//		}else {
//			throw new BizException("付款日期JSON数据不正确，typeEvent:"+typeEvent);
//		}
//		
//		String eventInclusion = json.getString("eventInclusion");
//		String eventDays = json.getString("eventDays");
//		String date = json.getString("date");
//		
//		Map<String, String> dataMap = new HashMap<>();
//		dataMap.put("typeEvent", typeEvent);
//		dataMap.put("eventInclusion", eventInclusion);
//		dataMap.put("eventDays", eventDays);
//		dataMap.put("date", date);
//		
//		return dataMap;
//	}
//	
//	/**
//	 * 是否影响付款日期
//	 * @param paymentTermJson
//	 * @return
//	 */
//	public static boolean canEffectPayDate(String paymentTermJson){
//		Map<String, String> dataMap = analyse(paymentTermJson);
//		
//		String typeEvent = dataMap.get("typeEvent");
//		if("B/L".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("NOR".equalsIgnoreCase(typeEvent)) {
//			
//		}else if("COD".equalsIgnoreCase(typeEvent)) {
//			
//		}else {
//			return false;
//		}
//		
//		return true;
//	}
//}
