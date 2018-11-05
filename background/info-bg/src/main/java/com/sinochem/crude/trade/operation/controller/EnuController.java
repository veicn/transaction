package com.sinochem.crude.trade.operation.controller;

import java.util.Map;

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
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.domain.Enu;
import com.sinochem.crude.trade.operation.service.EnuService;
import com.sinochem.crude.trade.operation.vo.EnuQuery;
import com.sinochem.crude.trade.operation.vo.EnuVO;
import com.sinochem.it.b2b.member.access.RightAccess;


@Controller
public class EnuController  {

	@Autowired
	private EnuService enuService;
	
	public static final Log log = LogFactory.getLog(EnuController.class);
	
	@RightAccess(1174)
	@RequestMapping(value="om/tpMenu/tpMenu.htm")
	public void tpMenu(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, EnuQuery query){
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = enuService.queryRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("datas", pageList.getResult());
		model.put("query", query);
	}
	/**
	 * 新增或更新菜单
	 * @return
	 */
	@RightAccess(1175)
	@ResponseBody
	@RequestMapping(value="/tpMenu/saveOrUpdateMenu.json",method=RequestMethod.POST)
	public Result saveOrUpdateMenu(@RequestBody EnuVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("新增或修改成功");
		try {
			Enu enu = new Enu();
			BeanUtils.copyProperties(vo, enu);
			return enuService.saveOrUpdateMenu(enu,user);
		}catch (BizException e) {
			log.error("新增或修改失败", e);
			res.setFail(e.getMessage());
		}
		return res;
	}
	/**
	 * 菜单删除
	 */
	@RightAccess(1177)
	@ResponseBody
	@RequestMapping(value="/tpMenu/tpMenuDelete.json",method = RequestMethod.GET)
	public Result tpMenuDelete(@RequestParam(value="menuId") String menuId) throws BizException{
		
		Result result = new Result();
		
		try {
			enuService.tpMenuDelete(menuId);	
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("删除失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
