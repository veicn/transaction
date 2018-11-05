package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.query.AuditQuery;
import com.sinochem.crude.trade.info.service.AuditService;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class StatementController {

	@Autowired
	private AuditService auditService;
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private ChannelMService channelMService;

	Log log = LogFactory.getLog(StatementController.class);

	/**
	 * 列表查询(待审核)
	 * 
	 * @param user
	 * @param modelMap
	 * @param page
	 * @param pageAgain
	 * @param query
	 * admin - 系统管理员
	 * info_oper - 内容维护人员
	 */
	@RightAccess(1137)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/statement/StatementAudit.htm")
	public void audit(/* CurrentUser user, */ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, AuditQuery query) {

		Map<String, String> map = query.getParameters();
		// 分页
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		map.put("tab", "0");
		// 判断搜索框中是否选中主频道
		if(StringUtils.isNotBlank(query.getChannelM()))
			map.put("channelMUuid", query.getChannelM());
		//是否选中子频道
		if(StringUtils.isNotBlank(query.getChannel()))
			map.put("channelSubUuid", query.getChannel());
		map.put("infoType", "STAT");
		// 取得资讯列表
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> datas = (Page<Map<String, Object>>) auditService.listInfo(map,pageInfo);
		
		//处理taglist
		for(Map<String,Object> item : datas) {
			Object obj = item.get("tagList");
			if(obj != null) {
				item.put("tagList", ((String)obj).replace(",", " "));
			}
		
		}

		// 加载主频道列表
		Map<String, Object> mapn = new HashMap<>();
		mapn.put("channelMDesc", Constants.channel_m_desc_enum.channel_m_desc_STAT.getChannel_m_desc_key());
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);
		
		//回填子频道
		if(StringUtils.isNotBlank(query.getChannelM())){
			ChannelM m = channelMService.findByUuid(query.getChannelM());
			mapn.put("channelMId", m.getId());
			List<Map<String, Object>> channelSubList = channelSubService.queryRecords(mapn);
			modelMap.put("subList", channelSubList);
		}

		query.setTotalItem(datas.getTotal());

		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}

	/**
	 * 列表查询(已发布)
	 * 
	 * @param user
	 * @param modelMap
	 * @param page
	 * @param pageAgain
	 * @param query
	 * admin - 系统管理员
	 * info_oper - 内容维护人员
	 */
	@RightAccess(1143)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/statement/StatementPublish.htm")
	public void publish(/* CurrentUser user, */ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, AuditQuery query) {
		// 分页
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}

		Map<String, String> map = query.getParameters();
		map.put("tab", "1");
		// 判断搜索框中是否选中主频道
		if(StringUtils.isNotBlank(query.getChannelM()))
			map.put("channelMUuid", query.getChannelM());
		//是否选中子频道
		if(StringUtils.isNotBlank(query.getChannel()))
			map.put("channelSubUuid", query.getChannel());

		map.put("infoType", "STAT");
		// 取得资讯列表
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		
		Page<Map<String, Object>> datas = (Page<Map<String, Object>>)auditService.listInfo(map,pageInfo);
		
		//处理taglist
		for(Map<String,Object> item : datas.getResult()) {
			Object obj = item.get("tagList");
			if(obj != null) {
				item.put("tagList", ((String)obj).replace(",", " "));
			}
		}
		
		// 加载主频道列表
		Map<String, Object> mapn = new HashMap<>();
		mapn.put("channelMDesc", Constants.channel_m_desc_enum.channel_m_desc_STAT.getChannel_m_desc_key());
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);
		
		//回填子频道
		if(StringUtils.isNotBlank(query.getChannelM())){
			ChannelM m = channelMService.findByUuid(query.getChannelM());
			mapn.put("channelMId", m.getId());
			List<Map<String, Object>> channelSubList = channelSubService.queryRecords(mapn);
			modelMap.put("subList", channelSubList);
		}
		query.setTotalItem(datas.getTotal());
		
		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}


}
