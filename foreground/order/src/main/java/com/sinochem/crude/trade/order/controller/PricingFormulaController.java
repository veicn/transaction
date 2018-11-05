package com.sinochem.crude.trade.order.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.common.enums.PriceFormulaType;
import com.sinochem.crude.trade.order.model.vo.PricingFormulaInfoVO;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 计价公式Controller
 * @author zuliu
 *
 */
@Controller
@RequestMapping("pricingFormula")
//@RolesAccess("trade_oper")
public class PricingFormulaController {
	
	/**
	 * 计价编辑器初始化
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("physicalPricingEditor")
    public String priceEditor(ModelMap modelMap){
    	modelMap.put("currencyMap", DictUtils.getValuationCurrencyMap());//计价货币
    	modelMap.put("unitMap", DictUtils.getPricingMeasureUnitMap());//定价计量单位
    	modelMap.put("eventTypeMap", DictUtils.getEventPricingType());
    	modelMap.put("hlcMap", DictUtils.getHLCType());
    	
    	return "create/physicalPricingEditor";
    }
    
	/**
	 * 取计价公式信息
	 * @param info
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value = "getPricingInfo.json")
	public ResultDatas<PricingFormulaInfoVO> getPricingInfo(PricingFormulaInfoVO info) {
		ResultDatas<PricingFormulaInfoVO> res = new ResultDatas<PricingFormulaInfoVO>();
		try{
			processFormula(info);
			res.setDatas(info);
			return res;
		}catch (Exception e){
			e.printStackTrace();
			res.setStatus(ResultDatas.ERROR);
			res.setMessage(e+"");
			return res;
		}
	}
    
    /**
     * 处理计价公式
     * @param info
     */
    private void processFormula(PricingFormulaInfoVO info){
    	String formulaType = DictUtils.getPricingModeMap().get(info.getFormulaType());
//    	PriceFormulaType formulaType = PriceFormulaType.getFormulaTypeByCode(info.getFormulaType());
    	if(PriceFormulaType.FIXED.getFormulaType().equalsIgnoreCase(formulaType)){//固定价
    		processFixedFormula(info);
        	
    	}else if(PriceFormulaType.AVERAGE.getFormulaType().equalsIgnoreCase(formulaType)){//平均价
    		processAverageFormula(info);
    		
    	}else if(PriceFormulaType.EVENT.getFormulaType().equalsIgnoreCase(formulaType)){//事件计价
    		processEventFormula(info);
    		
    	}
    }
    
    /**
     * 固定价公式
     * @param info
     */
    private void processFixedFormula(PricingFormulaInfoVO info){
    	Map<String,Object> detailMp = new LinkedHashMap<String,Object>();
    	detailMp.put("price", String.valueOf(info.getPrice()));
    	detailMp.put("uom", info.getUom());
    	detailMp.put("currency", info.getCurrency());
    	
    	Map<String,Object> formudMp = new HashMap<String,Object>();
    	formudMp.put("fixed_pricing_detail", detailMp);
    	
    	String structure = new JSONObject(formudMp).toJSONString();
    	info.setPriceStructure(structure);//计价公式结构
    	
    	StringBuffer formula = new StringBuffer() //计价公式显示
    			.append(info.getPrice()).append(" ")
    			.append(info.getCurrency()).append("/").append(info.getUom());
    	info.setFormula(formula.toString());
    }
    
    /**
     * 平均价公式
     * @param info
     */
    private void processAverageFormula(PricingFormulaInfoVO info){
    	Map<String,Object> quoteMp = new LinkedHashMap<String,Object>();//一个市场合约
    	quoteMp.put("commodity", info.getCommodity());//报价商品
    	quoteMp.put("price_source", info.getPriceSource());//来源
    	quoteMp.put("market", info.getMarket());//市场
    	quoteMp.put("period", info.getPeriod());//合约期 1ST NRBY
    	quoteMp.put("h_l", info.getHlc());//H/L/C
    	quoteMp.put("price_term", "Basis");//Basis或者Flat Price
    	quoteMp.put("average", "");//Weighted Average或者Simple Average
    	
    	Map<String,Object> detailMp = new LinkedHashMap<String,Object>();
    	detailMp.put("quote", quoteMp);
    	detailMp.put("mark", info.getMark());
    	detailMp.put("premium", info.getPremium());
    	detailMp.put("uom", info.getUom());
    	detailMp.put("currency", info.getCurrency());
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy",Locale.ENGLISH); 
    	detailMp.put("date1", dateFormat.format(info.getStartDate()));//"Jan/01/2018", //计价期起
    	detailMp.put("date2", dateFormat.format(info.getEndDate()));//"Jan/31/2018"//计价期止
    	
    	Map<String,Object> formudMp = new HashMap<String,Object>();
    	formudMp.put("average_pricing_detail", detailMp);
    	
    	String structure = new JSONObject(formudMp).toJSONString();
    	info.setPriceStructure(structure);//计价公式结构
    	
    	StringBuffer formula = new StringBuffer() //计价公式显示
    			.append(info.getCommodity()).append("/")
    			.append(info.getPriceSource()).append("/")
    			.append(info.getMarket()).append("/")
    			.append(info.getPeriod()).append("/")
    			.append(info.getHlc()).append(" ")
    			.append(info.getMark()).append(info.getPremium()).append(" ")
    			.append(info.getCurrency()).append("/").append(info.getUom());
    	
    	SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    	formula.append(" Average Pricing From: ")
    		.append(dateFormat2.format(info.getStartDate()))
    		.append(" To: ").append(dateFormat2.format(info.getEndDate()));
    	
    	info.setFormula(formula.toString());
    	
    }
    
    /**
     * 事件计价公式
     * @param info
     */
    private void processEventFormula(PricingFormulaInfoVO info){
    	Map<String,Object> quoteMp = new LinkedHashMap<String,Object>();//一个市场合约
    	quoteMp.put("commodity", info.getCommodity());//报价商品
    	quoteMp.put("price_source", info.getPriceSource());//来源
    	quoteMp.put("market", info.getMarket());//市场
    	quoteMp.put("period", info.getPeriod());//合约期 1ST NRBY
    	quoteMp.put("h_l", info.getHlc());//H/L/C
    	quoteMp.put("price_term", "Basis");//Basis或者Flat Price
    	quoteMp.put("average", "");//Weighted Average或者Simple Average
    	
    	Map<String,Object> detailMp = new LinkedHashMap<String,Object>();
    	detailMp.put("quote", quoteMp);
    	detailMp.put("mark", info.getMark());
    	detailMp.put("premium", info.getPremium());
    	detailMp.put("uom", info.getUom());
    	detailMp.put("currency", info.getCurrency());
    	detailMp.put("event_type", info.getEventType());
    	
    	detailMp.put("event_days", info.isDaysEvent());
    	detailMp.put("event_days_num1", StringUtils.trimToEmpty(info.getEventDaysNum1()));
    	detailMp.put("event_days_num2", StringUtils.trimToEmpty(info.getEventDaysNum2()));
    	detailMp.put("event_days_num3", StringUtils.trimToEmpty(info.getEventDaysNum3()));
    	detailMp.put("event_days_num4", StringUtils.trimToEmpty(info.getEventDaysNum4()));
    	detailMp.put("event_days_num5", StringUtils.trimToEmpty(info.getEventDaysNum5()));
    	detailMp.put("event_days_num6", StringUtils.trimToEmpty(info.getEventDaysNum6()));
    	detailMp.put("deem_event", info.isDeemEvent());
    	detailMp.put("event_days_deem_date", info.getDeemDate()==null?"":info.getDeemDate());
    	
    	detailMp.put("event_days0", info.isDaysEvent0());
    	detailMp.put("event_days0_num1", StringUtils.trimToEmpty(info.getEventDays0Num1()));
    	detailMp.put("from_mark", StringUtils.trimToEmpty(info.getFromMark()));
    	detailMp.put("event_days0_num2", StringUtils.trimToEmpty(info.getEventDays0Num2()));
    	detailMp.put("event_days0_num2_day", StringUtils.trimToEmpty(info.getEventDays0Num2Day()));
    	detailMp.put("to_mark", StringUtils.trimToEmpty(info.getToMark()));
    	detailMp.put("event_days0_num3", StringUtils.trimToEmpty(info.getEventDays0Num3()));
    	detailMp.put("event_days0_num3_day", StringUtils.trimToEmpty(info.getEventDays0Num3Day()));

    	detailMp.put("event_weeks", info.isWeeksEvent());
    	detailMp.put("event_weeks_num1", StringUtils.trimToEmpty(info.getEventWeeksNum1()));
    	detailMp.put("event_weeks_num2", StringUtils.trimToEmpty(info.getEventWeeksNum2()));
    	
    	detailMp.put("event_months", info.isMonthsEvent());
    	detailMp.put("month_mark", StringUtils.trimToEmpty(info.getMonthMark()));
    	detailMp.put("event_month_num1", StringUtils.trimToEmpty(info.getEventMonthsNum1()));

    	Map<String,Object> formudMp = new HashMap<String,Object>();
    	formudMp.put("event_pricing_detail", detailMp);
    	
    	String structure = new JSONObject(formudMp).toJSONString();
    	info.setPriceStructure(structure);//计价公式结构
    	
    	StringBuffer formula = new StringBuffer() //计价公式显示
    			.append(info.getCommodity()).append("/")
    			.append(info.getPriceSource()).append("/")
    			.append(info.getMarket()).append("/")
    			.append(info.getPeriod()).append("/")
    			.append(info.getHlc()).append(" ")
    			.append(info.getMark()).append(info.getPremium()).append(" ")
    			.append(info.getCurrency()).append("/").append(info.getUom());
    	
    	info.setFormula(formula.toString());
    }
}
