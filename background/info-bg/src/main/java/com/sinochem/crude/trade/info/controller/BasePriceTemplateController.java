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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.BasePriceTemplate;
import com.sinochem.crude.trade.info.model.BasePriceTemplateVO;
import com.sinochem.crude.trade.info.query.BasePriceTempQuery;
import com.sinochem.crude.trade.info.service.BasePriceTemplateService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 基价模板
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class BasePriceTemplateController  {

	@Autowired
	private BasePriceTemplateService basePriceTemplateService;
	
	public static final Log log = LogFactory.getLog(BasePriceTemplateController.class);
	
	/**
	 * 基价模板列表
	 */
	@RightAccess(1081)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.BASEPRICETEMP_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, BasePriceTempQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = basePriceTemplateService.queryLikeNameRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		for(Map<String,Object> item : pageList.getResult()) {
			item.remove("id");
		}
		query.setTotalItem(pageList.getTotal());
		model.put("basePriceTemps", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 新增或更新基价模板
	 * @return
	 */
	@RightAccess(1082)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASEPRICETEMP_SAVEORUPDATE)
	public Result saveOrUpdateBasePriceTemp(@RequestBody BasePriceTemplateVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("基价模板保存成功");
		if(StringUtils.isBlank(vo.getBaseCode())) {
			res.setFail("基价模板代码不为空");
			return res;
		}
		if(StringUtils.isBlank(vo.getBaseName())) {
			res.setFail("基价模板名称不为空");
			return res;
		}
		if(StringUtils.isBlank(vo.getBaseArea())) {
			res.setFail("基价模板地区不为空");
			return res;
		}
		try {
			BasePriceTemplate basepriceTemp = new BasePriceTemplate();
			BeanUtils.copyProperties(vo, basepriceTemp);
			basepriceTemp.setCreateUser(user.getName()); 
			basepriceTemp.setUpdateUser(user.getName()); 
			return basePriceTemplateService.saveOrUpdateBasePriceIndexTemp(basepriceTemp);
		}catch (BizException e) {
			log.error("保存基价模板失败");
			res.setFail(e.getMessage());
		}
		return res;
	}
	/**
	 * 设置基价模板状态
	 * @return
	 */
	@RightAccess(1085)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASEPRICETEMP_SETSTATUS)
	public Result setBasePriceTempStatus(@RequestParam String uuid) {
		Result res = new Result();
	    res.setMessage("基价模板状态设置成功");
	    if(basePriceTemplateService.setBasePriceTempStatus(uuid)) {
	    	return res;
	    }
	    res.setFail("基价模板设置失败,请稍后再试");
	    return res;
	}
	
}
