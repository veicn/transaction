package com.sinochem.crude.trade.info.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.eyeieye.melody.util.DateUtil;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.result.PMIFuturePriceRest;
import com.sinochem.crude.trade.info.result.PMISAgioRest;
import com.sinochem.crude.trade.info.result.PMISOfficialRest;
import com.sinochem.crude.trade.info.result.PMIVendPriceRest;
import com.sinochem.crude.trade.info.result.QualityRest;
import com.sinochem.crude.trade.info.service.AgioService;
import com.sinochem.crude.trade.info.service.CrudeBasePriceService;
import com.sinochem.crude.trade.info.service.FuturesPriceService;
import com.sinochem.crude.trade.info.service.MesticProductPriceService;
import com.sinochem.crude.trade.info.service.QualityService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
@WithoutAccess
public class PIMSController {

	private static final Log log = LogFactory.getLog(PIMSController.class);
	@Autowired
	private AgioService agioService;
	@Autowired
	private CrudeBasePriceService crudeBasePriceService;
	@Autowired
	private FuturesPriceService futuresPriceService;
	
	@Autowired
	private MesticProductPriceService mesticProductPriceService;
	
	@Autowired
	private QualityService qualityService;
	
	
	//返回结果
	class Ret extends HashMap<String,Object>{
		public Ret() {
			this.put("status", "0");
			this.put("message", "");
			this.put("dataList", null);
		}
		public Ret setNo(String message) {
			this.put("status", "0");
			this.put("message", message);
			this.put("dataList", null);
			return this;
		}
		public Ret setOk(String message,Object dataList) {
			this.put("status", "1");
			this.put("message", message);
			this.put("dataList", dataList);
			return this;
		}
	}
	
	/**
	 * PMIS查询贴水
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.PMISAGIO_QUERY, method = RequestMethod.POST)
	public Map<String, Object> getPMISAgio(@RequestBody PIMSVo vo){
		Ret result = new Ret();
		try {
			checkParam(vo);
			List<Map<String, Object>> agioMaps = agioService.queryAgio(vo);
			return result.setOk("查询成功", BeanConvertUtils.mapToBeanInList(agioMaps, PMISAgioRest.class));
		} catch (Exception e) {  
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	/**
	 * PMIS查询官价
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.PMISOFFICIAL_QUERY, method = RequestMethod.POST)
	public Map<String, Object> getPMISOfficial(@RequestBody PIMSVo vo){
		Ret result = new Ret();
		try {
			checkParam(vo);
			List<Map<String, Object>> officialMaps = crudeBasePriceService.queryOfficialPrice(vo);
			return result.setOk("查询成功", BeanConvertUtils.mapToBeanInList(officialMaps, PMISOfficialRest.class));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	
	/**
	 * PMIS查询期货结算价格
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.PMIFUTUREPRICE_QUERY, method = RequestMethod.POST)
	public Map<String, Object> getPMIFuturePrice(@RequestBody PIMSVo vo){
		Ret result = new Ret();
		try {
			checkParam(vo);
			List<Map<String, Object>> futurePriceMaps = futuresPriceService.queryFuturePrice(vo);
		    return result.setOk("查询成功", BeanConvertUtils.mapToBeanInList(futurePriceMaps, PMIFuturePriceRest.class));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	
	/**
	 * PMIS查询厂家报价
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.PMIVENDPRICE_QUERY, method = RequestMethod.POST)
	public Map<String, Object> getPMIVendPrice(@RequestBody PIMSVo vo){
		Ret result = new Ret();
		try {
			checkParam(vo);
			List<Map<String, Object>> vendPriceMaps = mesticProductPriceService.queryVendPrice(vo);
		    return result.setOk("查询成功", BeanConvertUtils.mapToBeanInList(vendPriceMaps, PMIVendPriceRest.class));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 查询原油品质信息
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.QUALITY_QUERY, method = RequestMethod.POST)
	public Map<String, Object> getQuality(@RequestBody PIMSVo vo){
		Ret result = new Ret();
		try {
			List<Map<String, Object>> qualityMaps = qualityService.queryQuality(vo);
		    return result.setOk("查询成功", BeanConvertUtils.mapToBeanInList(qualityMaps, QualityRest.class));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 校验参数
	 * @param vo
	 * @throws Exception
	 */
	void checkParam(PIMSVo vo) throws Exception{
		if(StringUtils.isBlank(vo.getDateStart()))
			throw new Exception("开始日期不能为空！");
		if(StringUtils.isBlank(vo.getDateEnd()))
			throw new Exception("结束日期不能为空！");
		Date start = DateUtil.strToDate(vo.getDateStart(), "yyyy-MM-dd");
		Date end = DateUtil.strToDate(vo.getDateEnd(), "yyyy-MM-dd");
		if((end.getTime()-start.getTime())/(1000*60*60*24)>30)
			throw new Exception("日期间隔不能大于30天");
	}
}
