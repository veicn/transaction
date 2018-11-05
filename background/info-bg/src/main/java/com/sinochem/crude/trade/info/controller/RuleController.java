package com.sinochem.crude.trade.info.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.model.RuleVO;
import com.sinochem.crude.trade.info.query.RuleQuery;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.RuleService;
//import com.sinochem.it.b2b.common.user.CurrentUser;
import com.sinochem.crude.trade.member.enums.MemberRoles;

/**
 * @ClassName: RuleController
 * @Description: 资讯规则设置
 * @author pengfl
 * @date 2017年11月13日 上午11:06:25
 *
 */
@Controller
public class RuleController {

	@Autowired
	private RuleService ruleService;
	@Autowired
	private ChannelSubService channelSubService;

	Log log = LogFactory.getLog(RuleController.class);

	/**
	 * 规则列表查询
	 * 
	 * @param user
	 * @param modelMap
	 * @param page
	 * @param pageAgain
	 * @param query
	 */
	@RequestMapping(value = "/om/rule/rule.htm")
	public void rule(/* CurrentUser user, */ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, RuleQuery query) {

		// 获取角色名称
		List<MemberRoles> roles = Arrays.asList(MemberRoles.values());

		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}

		Page<Map<String, Object>> datas = (Page)ruleService.listRule(query);
		query.setTotalItem(datas.getTotal());
		modelMap.put("datas", datas);
		modelMap.put("query", query);
		modelMap.put("roles", roles); //角色集合
	}

	/**
	 * 规则保存
	 * 
	 * @param request
	 * @param user
	 * @param modelMap
	 */
	@ResponseBody
	@RequestMapping(value = "/rule/ruleSave.json", method = RequestMethod.POST)
	public Result ruleSave(RuleVO ruleVo/* , CurrentUser user */) {
		log.info("--->" + "ruleVo=" + ruleVo);
		Result res = new Result();

		// 角色信息
		MemberRoles mrs = MemberRoles.valueOf(ruleVo.getRoleConstant());
		ruleVo.setRoleCode(mrs.getCode());
		ruleVo.setRoleName(mrs.getName());

		// 频道信息
		ChannelSub channelSub = ruleService.queryChannelSub(ruleVo.getChannelUuid());
		ruleVo.setChannelId(channelSub.getId());
		ruleVo.setChannelCode(channelSub.getChannelCode());
		ruleVo.setChannelName(channelSub.getChannelName());

		//
		try {
			if (StringUtils.isBlank(ruleVo.getUuid())) { // 新增
				if (ruleService.insertRecord(ruleVo) == 1) {
					res.setStatus(Result.SUCCESS);
					res.setMessage("新增成功");
				} else {
					res.setStatus(Result.EEROR);
					res.setMessage("新增失败");
				}
			} else { // 修改
				ruleVo.setCreateDate(null);
				if (ruleService.updateRecordByUuid(ruleVo) == 1) {
					res.setStatus(Result.SUCCESS);
					res.setMessage("修改成功");
				} else {
					res.setStatus(Result.EEROR);
					res.setMessage("修改失败");
				}
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage("保存操作失败");
		}
		return res;
	}

	/**
	 * 规则删除
	 * 
	 * @param request
	 * @param user
	 * @param modelMap
	 */
	@ResponseBody
	@RequestMapping(value = "/rule/ruleDel.json", method = RequestMethod.POST)
	public Result ruleDel(String uuid/* , CurrentUser user */) {
		log.info("--->" + uuid);
		Result res = new Result();

		try {
			if (ruleService.deleteByUuid(uuid) == 1) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("删除成功");
			} else {
				res.setStatus(Result.EEROR);
				res.setMessage("删除失败");
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	/**
	 * 得到角色列表
	 */
	@ResponseBody
	@RequestMapping("/rule/getRoles.json")
	public List<Map<String, Object>> getRoles(){
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		List<MemberRoles> roles = Arrays.asList(MemberRoles.values());
		for(MemberRoles role: roles){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", role.getName());
			map.put("constant", role);
			res.add(map);
		}
		return res;
	}
	
	/**
	 * 得到子频道列表
	 */
	@ResponseBody
	@RequestMapping("/rule/getChannels.json")
	public List<Map<String, Object>> getChannelSubs(){
		return ruleService.queryChannelSubs();
	}
}