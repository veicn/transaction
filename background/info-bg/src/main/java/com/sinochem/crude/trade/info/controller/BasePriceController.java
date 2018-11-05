package com.sinochem.crude.trade.info.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.BasePrice;
import com.sinochem.crude.trade.info.model.BasePriceVO;
import com.sinochem.crude.trade.info.model.BasePriceVOVO;
import com.sinochem.crude.trade.info.query.BasePriceQuery;
import com.sinochem.crude.trade.info.service.BasePriceService;
import com.sinochem.crude.trade.info.service.BasePriceTemplateService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class BasePriceController  {

	@Autowired
	private BasePriceService basePriceService;
	@Autowired
	private BasePriceTemplateService basePriceTemplateService;
	
	public static final Log log = LogFactory.getLog(BasePriceController.class);
	
	/**
	 * 基价列表
	 */
	@RightAccess(1086)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.BASEPRICE_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, BasePriceQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		if(StringUtils.isBlank(query.getBaseDate())) {
			query.setBaseDate(DateUtil.convertDateToString("yyyy-MM-dd", new Date()));
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = basePriceService.queryLikeRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("basePrices", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 新增或更新基价
	 * @return
	 */
	@RightAccess(1088)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASEPRICE_SAVEORUPDATE,method=RequestMethod.POST)
	public Result saveOrUpdateBasePrice(@RequestBody BasePriceVOVO vos,CurrentUser user) {
		Result res = new Result();
		res.setMessage("保存指数成功");
		if(vos != null) {
			if(!CollectionUtils.isEmpty(vos.getVos())) {
				for(BasePriceVO vo : vos.getVos()) {
					BasePrice target = new BasePrice();
					BeanUtils.copyProperties(vo, target);
					target.setBaseDate(DateTimeUtils.toDate(vos.getBaseDate()));
					target.setBaseTemplateId(basePriceTemplateService.findByUuid(vo.getBasePriceTempUuuid()).getId());
					try {
						basePriceService.saveOrUpdateBasePrice(target,user);
					}catch (BizException e) {
						log.error("保存指数失败",e);
						res.setFail("保存指数失败");
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * 批量重置删除指数
	 * @param uuids
	 * @return
	 */
	@RightAccess(1089)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASEPRICE_DELETELIST,method=RequestMethod.POST)
	public Result deleteBasePriceList(@RequestBody List<String> uuids) {
		Result res = new Result();
		res.setMessage("重置成功");
		if(!CollectionUtils.isEmpty(uuids)) {
			for(String uuid : uuids) {
				BasePrice base = basePriceService.findByUuid(uuid);
				base.setAliveFlag("0");
				try {
					basePriceService.updateRecordByUuid(base);
				}catch (BizException e) {
					log.error("批量重置删除指数",e);
					res.setFail("批量重置删除指数失败");
				}
			}
		}
		return res;
	}
}
