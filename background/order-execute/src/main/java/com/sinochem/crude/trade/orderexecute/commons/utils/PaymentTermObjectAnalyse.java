package com.sinochem.crude.trade.orderexecute.commons.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.common.exception.BizException;

/**
 * 付款日期对象解析
 * @author me
 *
 */
public class PaymentTermObjectAnalyse {
	/**
	 * 类型，以什么日期为准计算，
	 * 取值范围有： B/L（提单日期）、nor（船到港可以卸货日期）、cod（系统完成的时间）、ldr、date。
	 * 若类型是date，则以 “date”字段值计算
	 */
	private String typeEvent;
	/**
	 * 表示在{typeEvent}日期多少天后
	 */
	private Integer eventDays;
	/**
	 * 两个选项，from、after，from表示包含当天，after表示不包含当天
	 */
	private String eventInclusion;
	/**
	 * 固定日期
	 */
	private Date date;
	
	private Boolean isBl = false;
	private Boolean isCod = false;
	private Boolean isNor = false;
	private Boolean isLdr = false;
	private Boolean isDate = false;
	
	public PaymentTermObjectAnalyse(String paymentTermJson) throws BizException {
		if(StringUtils.isBlank(paymentTermJson)) {
			throw new BizException("付款日期JSON数据缺失");
		}
		JSONObject json = null;
		try {
			json = JSONObject.parseObject(paymentTermJson);
		} catch (Exception e) {
			throw new BizException("付款日期JSON格式不正确");
		}
		
		this.typeEvent = json.getString("typeEvent");
		this.eventInclusion = json.getString("eventInclusion");
		this.eventDays = json.getInteger("eventDays");
		
		if ("B/L".equalsIgnoreCase(typeEvent)) {
			isBl = true;
		} else if ("NOR".equalsIgnoreCase(typeEvent)) {
			isNor = true;
		} else if ("COD".equalsIgnoreCase(typeEvent)) {
			isCod = true;
		} else if ("LDR".equalsIgnoreCase(typeEvent)) {
			isLdr = true;
		} else if ("DATE".equalsIgnoreCase(typeEvent)) {
			isDate = true;
			
			try {
				date = DateUtils.parseDate(json.getString("date"), new String[] {"yyyy-MM-dd"});
			} catch (ParseException e) {
				throw new BizException("付款日期JSON格式不正确:typeEvent="+typeEvent+",date="+json.getString("date"));
			}
		} else {
			throw new BizException("付款日期JSON格式不正确:typeEvent="+typeEvent);
		}
	}
	
	public Date calcPayDate(Date d) throws BizException {
		Date paymentDate = d;
		
		if (isDate) {
			paymentDate = date;
		} else {
			if(paymentDate == null) {
				return null;
			}
			
			if("from".equalsIgnoreCase(eventInclusion)) {//包含当前天
				paymentDate = DateUtils.addDays(paymentDate, this.eventDays-1);
			}else if("after".equalsIgnoreCase(eventInclusion)) {//不包含当前天
				paymentDate = DateUtils.addDays(paymentDate, this.eventDays);
			}
		}
		
		return paymentDate;
	}
	
	public Boolean isBl() {
		return isBl;
	}
	public Boolean isCod() {
		return isCod;
	}
	public Boolean isNor() {
		return isNor;
	}
	public Boolean isLdr() {
		return isLdr;
	}
	public Boolean isDate() {
		return isDate;
	}

	public String getTypeEvent() {
		return typeEvent;
	}

	public void setTypeEvent(String typeEvent) {
		this.typeEvent = typeEvent;
	}

	public String getEventInclusion() {
		return eventInclusion;
	}

	public void setEventInclusion(String eventInclusion) {
		this.eventInclusion = eventInclusion;
	}

	public Integer getEventDays() {
		return eventDays;
	}

	public void setEventDays(Integer eventDays) {
		this.eventDays = eventDays;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
