package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.InfoVO;
import com.sinochem.crude.trade.info.query.AuditQuery;
import com.sinochem.crude.trade.info.service.AuditService;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
//import com.sinochem.it.b2b.common.user.CurrentUser;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: AuditYesInfoController
 * @Description: 审核通过
 * @author pengfl
 * @date 2017年11月13日 下午5:27:58
 * 
 */
@Controller
public class AuditInfoController {

	@Autowired
	private AuditService auditService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private ChannelMService channelMService;

	Log log = LogFactory.getLog(AuditInfoController.class);

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
	@RightAccess(1024)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/audit/audit.htm")
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
		map.put("infoType", "ZX");
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
		mapn.put("channelMDesc", Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
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
	@RightAccess(1031)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/audit/publish.htm")
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

		map.put("infoType", "ZX");
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
		mapn.put("channelMDesc", Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
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
	 * 记录详情
	 * 
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/infoDetail.json")
	public ResultDatas<Info> infoDetail(String uuid) {
		log.info("--->" + uuid);

		ResultDatas<Info> res = new ResultDatas<Info>();
		Info info = infoService.findByUuid(uuid);
		res.setDatas(info);
		return res;
	}

	/**
	 * 审核通过
	 * 
	 * @param infoVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/auditYes.json")
	public Result auditYesInfo(String uuid) {
		log.info("--->" + uuid);
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行审核操作");
			return res;
		}

		//
		try {
			if (auditService.auditYes(uuid)) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("已通过审核");
			} else {
				res.setStatus(Result.EEROR);
				res.setMessage("审核失败");
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	/**
	 * 审核驳回
	 * 
	 * @param infoVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/auditNo.json", method = RequestMethod.POST)
	public Result auditNoInfo(@RequestBody InfoVO infoVo) {
		log.info("--->" + infoVo.toString());
		Result res = new Result();
		Info info = new Info();

		// 判断uuid是否为空
		if (StringUtils.isBlank(infoVo.getUuid())) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行审核操作");
			return res;
		}

		//
		try {
			BeanUtils.copyProperties(info, infoVo);
			if (auditService.auditNo(info)) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("已审核驳回");
			} else {
				res.setStatus(Result.EEROR);
				res.setMessage("驳回失败");
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	/**
	 * 批量审核通过
	 * 
	 * @param array
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/batchAudit.json", method = RequestMethod.POST)
	public Map<String, Object> batchAudit(String[] uuids) throws BizException {
		for (String uuid : uuids) {
			if (StringUtils.isBlank(uuid)) {
				throw new BizException("存在uuid为空的情况，请重新选择");
			}
		}
		return auditService.batchAudit(uuids);
	}

	/**
	 * 资讯撤销
	 * 
	 * @param infoVo
	 * @return
	 */
	@RightAccess(1038)
	@ResponseBody
	@RequestMapping(value = "/audit/revokeInfo.json")
	public Result revokeInfo(@RequestParam(value = "uuid") String uuid) {
		log.info("--->" + uuid);
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行撤销操作");
			return res;
		}

		//
		try {
			if (auditService.revoke(uuid)) {
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
	 * 根据主频道信息取得子频道列表
	 * 
	 * @param channelMUuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/getChannels.json")
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

	/**
	 * 置顶或取消置顶
	 * @param uuid
	 * @return
	 */
	@RightAccess(1036)
	@ResponseBody
	@RequestMapping(value="/audit/settingTop.json")
	public Result settingTop(@RequestParam String uuid){
		Result res = new Result();
		res.setMessage("置顶成功");
		Info info = infoService.findByUuid(uuid);
		if(infoService.settingInfoTopByUUId(info)){
			return res;
		}else{
			res.setFail("置顶失败，稍后再试");
			return res;
		}
	}
	
	/**
	 * 精选或取消精选
	 * @param uuid
	 * @return
	 */
	@RightAccess(1035)
	@ResponseBody
	@RequestMapping(value="/audit/selected.json")
	public Result selected(@RequestParam String uuid){
		Result res = new Result();
		res.setMessage("设置精选成功");
		Info info = infoService.findByUuid(uuid);
		if(infoService.selectedByUuid(info)){
			return res;
		}else{
			res.setFail("设置精选失败，稍后再试");
			return res;
		}
	}
	
	/**
	 * 资讯删除
	@RightAccess(1028)
	 */
	@ResponseBody
	@RequestMapping(value = "/audit/delInfo.json")
	public Result delInfo(@RequestParam(value = "uuid") String uuid,CurrentUser user) {
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行删除操作");
			return res;
		}
		Info info = infoService.findByUuid(uuid);
		info.setUpdateUser(user.getName());
		try {
			if (infoService.delete(info)) {
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
}
