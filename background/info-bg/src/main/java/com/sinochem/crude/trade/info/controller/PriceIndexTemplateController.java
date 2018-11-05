package com.sinochem.crude.trade.info.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
import com.sinochem.crude.trade.info.model.PriceIndexTemplateVO;
import com.sinochem.crude.trade.info.query.PriceIndexTempQuery;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class PriceIndexTemplateController  {

	@Autowired
	private PriceIndexTemplateService priceIndexTemplateService;
	
	public static final Log log = LogFactory.getLog(PriceIndexTemplateController.class);
	
	/**
	 * 指数模板列表
	 */
	@RightAccess(1069)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.PRICEINDEXTEMP_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, PriceIndexTempQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = priceIndexTemplateService.queryLikeNameRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("priceIndexTemps", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 新增指数模板
	 */
	@RightAccess(1070)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.PRICEINDEXTEMP_SAVE_PRICEINDEX, method = RequestMethod.POST)
	public Result saveOrUpdatePriceIndex(@RequestBody PriceIndexTemplateVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("指数模板新增成功");
		if(StringUtils.isBlank(vo.getIndexCode())) {
			res.setFail("指数模板代码不为空");
			return res;
		}
		if(StringUtils.isBlank(vo.getIndexName())) {
			res.setFail("指数模板名称不为空");
			return res;
		}
		if(vo.getPriority() == null || vo.getPriority() <= 0) {
			res.setFail("指数模板排序不小于1");
			return res;
		}
		try {
			PriceIndexTemplate priceIndex = new PriceIndexTemplate();
			BeanUtils.copyProperties(vo, priceIndex);
			return priceIndexTemplateService.saveOrUpdatePriceIndexTemp(priceIndex,user);
		}catch (BizException e) {
			log.error("新增指数模板失败");
			res.setFail(e.getMessage());
		}
		return res;
	}
	/**
	 * 设置指数模板状态
	 * @return
	 */
	@RightAccess(1073)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.PRICEINDEXTEMP_SETPRICETEMPSTATUS)
	public Result setPriceIndexTempStatus(@RequestParam String uuid) {
		Result res = new Result();
	    res.setMessage("指数模板状态设置成功");
	    if(priceIndexTemplateService.setPriceIndexTempStatus(uuid)) {
	    	return res;
	    }
	    res.setFail("指数模板设置失败,请稍后再试");
	    return res;
	}
}
