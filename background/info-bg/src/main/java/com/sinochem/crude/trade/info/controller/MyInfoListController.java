package com.sinochem.crude.trade.info.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.query.MyInfoListQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.MyInfoListService;
import com.sinochem.crude.trade.member.user.CurrentUser;

/**
 * 我的资讯列表<br/>
 * Created by pengfl on 2017年11月26日
 */
@Controller
public class MyInfoListController {

	@Autowired
	private ChannelMService channelMService;
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private MyInfoListService myInfoListService;
	@Autowired
	private InfoService infoService;

	Log log = LogFactory.getLog(MyInfoListController.class);

	/**
	 * 我的资讯列表(全部)
	 */
	@RequestMapping(value = "/om/myInfoList/myInfoList.htm")
	public void myInfoList(CurrentUser user, ModelMap modelMap, MyInfoListQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		if (null == user) { // 未登录状态
			datas = null;
		} else {

			// 分页
			if (pageAgain != null) {
				query.setCurrentPage(pageAgain);
			} else {
				query.setCurrentPage(page);
			}

			Map<String, String> map = query.getParameters();
			map.put("userName", user.getName()); // 当前用户名称
			map.put("tab", ""); //TODO

			// 判断搜索框中是否选中主频道
			if (null == query.getChannelM() || "".equals(query.getChannelM())) {
				map.put("channelIds", "");
			} else {
				String cMUuid = query.getChannelM();
				ChannelM channelM = channelMService.findByUuid(cMUuid); //

				// 判断搜索框中是否选中子频道
				if (null == query.getChannel() || "".equals(query.getChannel())) {
					String ids = ""; //
					ChannelSub param = new ChannelSub();
					param.setChannelMId(channelM.getId());
					List<Map<String, Object>> list = channelSubService.selectListChannelSub(param);
					for (Map<String, Object> m : list) {
						if (null != m.get("id")) {
							ids += m.get("id") + ",";
						}
					}
					map.put("channelIds", ids.substring(0, ids.length() - 1)); // 去掉字符串最后一个逗号
				} else {
					ChannelSub param = channelSubService.findByUuid(query.getChannel());
					map.put("channelId", String.valueOf(param.getId()));
				}
			}

			// 取得资讯列表
			datas = myInfoListService.listInfo(map);
		}

		// 加载主频道列表
		Map<String, Object> mapn = null;
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);

		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}
	
	/**
	 * 我的资讯列表(已发布)
	 */
	@RequestMapping(value = "/om/myInfoList/myInfoListReleased.htm")
	public void myInfoListReleased(CurrentUser user, ModelMap modelMap, MyInfoListQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		if (null == user) { // 未登录状态
			datas = null;
		} else {

			// 分页
			if (pageAgain != null) {
				query.setCurrentPage(pageAgain);
			} else {
				query.setCurrentPage(page);
			}

			Map<String, String> map = query.getParameters();
			map.put("userName", user.getName()); // 当前用户名称
			map.put("tab", "1"); //TODO

			// 判断搜索框中是否选中主频道
			if (null == query.getChannelM() || "".equals(query.getChannelM())) {
				map.put("channelIds", "");
			} else {
				String cMUuid = query.getChannelM();
				ChannelM channelM = channelMService.findByUuid(cMUuid); //

				// 判断搜索框中是否选中子频道
				if (null == query.getChannel() || "".equals(query.getChannel())) {
					String ids = ""; //
					ChannelSub param = new ChannelSub();
					param.setChannelMId(channelM.getId());
					List<Map<String, Object>> list = channelSubService.selectListChannelSub(param);
					for (Map<String, Object> m : list) {
						if (null != m.get("id")) {
							ids += m.get("id") + ",";
						}
					}
					map.put("channelIds", ids.substring(0, ids.length() - 1)); // 去掉字符串最后一个逗号
				} else {
					ChannelSub param = channelSubService.findByUuid(query.getChannel());
					map.put("channelId", String.valueOf(param.getId()));
				}
			}

			// 取得资讯列表
			datas = myInfoListService.listInfo(map);
		}

		// 加载主频道列表
		Map<String, Object> mapn = null;
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);

		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}
	
	/**
	 * 我的资讯列表(未发布)
	 */
	@RequestMapping(value = "/om/myInfoList/myInfoListUnreleased.htm")
	public void myInfoListUnreleased(CurrentUser user, ModelMap modelMap, MyInfoListQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		if (null == user) { // 未登录状态
			datas = null;
		} else {

			// 分页
			if (pageAgain != null) {
				query.setCurrentPage(pageAgain);
			} else {
				query.setCurrentPage(page);
			}

			Map<String, String> map = query.getParameters();
			map.put("userName", user.getName()); // 当前用户名称
			map.put("tab", "2"); //TODO

			// 判断搜索框中是否选中主频道
			if (null == query.getChannelM() || "".equals(query.getChannelM())) {
				map.put("channelIds", "");
			} else {
				String cMUuid = query.getChannelM();
				ChannelM channelM = channelMService.findByUuid(cMUuid); //

				// 判断搜索框中是否选中子频道
				if (null == query.getChannel() || "".equals(query.getChannel())) {
					String ids = ""; //
					ChannelSub param = new ChannelSub();
					param.setChannelMId(channelM.getId());
					List<Map<String, Object>> list = channelSubService.selectListChannelSub(param);
					for (Map<String, Object> m : list) {
						if (null != m.get("id")) {
							ids += m.get("id") + ",";
						}
					}
					map.put("channelIds", ids.substring(0, ids.length() - 1)); // 去掉字符串最后一个逗号
				} else {
					ChannelSub param = channelSubService.findByUuid(query.getChannel());
					map.put("channelId", String.valueOf(param.getId()));
				}
			}

			// 取得资讯列表
			datas = myInfoListService.listInfo(map);
		}

		// 加载主频道列表
		Map<String, Object> mapn = null;
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);

		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}
	
	/**
	 * 我的资讯列表(草稿)
	 */
	@RequestMapping(value = "/om/myInfoList/myInfoListDraft.htm")
	public void myInfoListDraft(CurrentUser user, ModelMap modelMap, MyInfoListQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		if (null == user) { // 未登录状态
			datas = null;
		} else {

			// 分页
			if (pageAgain != null) {
				query.setCurrentPage(pageAgain);
			} else {
				query.setCurrentPage(page);
			}

			Map<String, String> map = query.getParameters();
			map.put("userName", user.getName()); // 当前用户名称
			map.put("tab", "3"); //TODO

			// 判断搜索框中是否选中主频道
			if (null == query.getChannelM() || "".equals(query.getChannelM())) {
				map.put("channelIds", "");
			} else {
				String cMUuid = query.getChannelM();
				ChannelM channelM = channelMService.findByUuid(cMUuid); //

				// 判断搜索框中是否选中子频道
				if (null == query.getChannel() || "".equals(query.getChannel())) {
					String ids = ""; //
					ChannelSub param = new ChannelSub();
					param.setChannelMId(channelM.getId());
					List<Map<String, Object>> list = channelSubService.selectListChannelSub(param);
					for (Map<String, Object> m : list) {
						if (null != m.get("id")) {
							ids += m.get("id") + ",";
						}
					}
					map.put("channelIds", ids.substring(0, ids.length() - 1)); // 去掉字符串最后一个逗号
				} else {
					ChannelSub param = channelSubService.findByUuid(query.getChannel());
					map.put("channelId", String.valueOf(param.getId()));
				}
			}

			// 取得资讯列表
			datas = myInfoListService.listInfo(map);
		}

		// 加载主频道列表
		Map<String, Object> mapn = null;
		List<Map<String, Object>> channelMList = channelMService.queryRecords(mapn);

		modelMap.put("datas", datas);
		modelMap.put("cms", channelMList);
		modelMap.put("query", query);
	}

	/**
	 * 我的资讯详情
	 */
	@RequestMapping(value = "/om/myInfoList/myInfoDetail.htm")
	public void infoDetail(@RequestParam(value = "uuid") String uuid, ModelMap modelMap) {

		Map<String, Object> data = infoService.selectByUuid(uuid);

		if (null != data.get("channelId")) {
			Long channelId = (Long) data.get("channelId");
			ChannelSub cs = channelSubService.selectByPrimaryKey(channelId);
			ChannelM cm = channelMService.selectByPrimaryKey(cs.getChannelMId());
			data.put("channelMName", cm.getChannelMName()); // 主频道名称
			data.put("channelSubName", cs.getChannelName()); // 子频道名称
		}

		modelMap.put("data", data);
	}

	/**
	 * 资讯撤销
	 */
	@ResponseBody
	@RequestMapping(value = "/myInfoList/revokeInfo.json")
	public Result revokeInfo(@RequestParam(value = "uuid") String uuid, CurrentUser user) {
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行撤销操作");
			return res;
		}

		try {
			if (myInfoListService.revoke(uuid, user)) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("已成功撤销");
			} else {
				res.setStatus(Result.EEROR);
				res.setMessage("撤销失败");
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	/**
	 * 资讯删除
	 */
	@ResponseBody
	@RequestMapping(value = "/myInfoList/delInfo.json")
	public Result delInfo(@RequestParam(value = "uuid") String uuid) {
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行删除操作");
			return res;
		}

		try {
			if (myInfoListService.delete(uuid)) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("已成功删除");
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
	 * 根据主频道信息取得子频道列表
	 */
	@ResponseBody
	@RequestMapping(value = "/myInfoList/getChannels.json")
	public List<Map<String, Object>> getChannelSubs(@RequestParam(value = "channelMUuid") String channelMUuid) {

		// 判断uuid是否为空
		if (StringUtils.isBlank(channelMUuid)) {
			return null;
		}

		// 判断主频道信息是否为空
		ChannelM channelM = channelMService.findByUuid(channelMUuid);
		if (channelM == null) {
			return null;
		}

		// 取得子频道列表
		ChannelSub param = new ChannelSub();
		param.setChannelMId(channelM.getId());
		List<Map<String, Object>> list = channelSubService.selectListChannelSub(param);
		return list;
	}
}
