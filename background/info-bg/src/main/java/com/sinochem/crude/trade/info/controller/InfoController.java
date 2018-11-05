package com.sinochem.crude.trade.info.controller;

import java.util.Map;

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

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.model.InfoVO;
import com.sinochem.crude.trade.info.query.AuditQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

@Controller
public class InfoController  {

	@Autowired
	private InfoService infoService;
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private ChannelMService channelMService;
	
	Log log = LogFactory.getLog(InfoController.class);
	
	/**
	 * 跳转资讯发布页
	 */
	@RightAccess(1026)
	@RequestMapping(value="/om/info/info.htm")
	public void toInfo(){
	}
	/**
	 * 跳转石油发布页
	 */
	@RightAccess(1056)
	@RequestMapping(value="/om/info/oilInfo.htm")
	public void toOilInfo(){
	}
	/**
	 * 跳转帮助中心发布页
	 */
	@RightAccess(1112)
	@RequestMapping(value="/om/info/HelpCenter.htm")
	public void toHelpCenter(){
	}
	/**
	 * 跳转平台声明发布页
	 */
	@RightAccess(1138)
	@RequestMapping(value="/om/info/Statement.htm")
	public void toStatement(){
	}
	/**
	 * 跳转成品油发布页
	 */
	@RightAccess(1041)
	@RequestMapping(value="/om/info/ProductOil.htm")
	public void toProductOil(ModelMap modelMap,AuditQuery query){
		modelMap.put("query", query);
	}
	
	/**
	 * 保存或更新资讯
	 * admin - 系统管理员
	 * info_oper - 内容维护人员
	 */
	@ResponseBody
	@RequestMapping(value="/info/saveOrUpdateInfo.json",method=RequestMethod.POST)
	public Result saveOrUpdateInfo(@RequestBody InfoVO vo,CurrentUser user){
		Result res = new Result();
		if(StringUtils.isBlank(vo.getChannelUUId())){
			res.setFail("栏目不为空");
		}
		if(StringUtils.isBlank(vo.getContent()) || StringUtils.isBlank(vo.getContentWithHtml())){
			res.setFail("发布资讯内容不为空");
		}
		if(StringUtils.isBlank(vo.getTitle())){
			res.setFail("发布资讯标题不为空");
		}
		try{
			res = infoService.saveOrUpdateInfo(vo,user);
		}catch(BizException e){
			log.error("保存或更新资讯失败",e);
		}
		return res;
	}

	/**
	 * 审核查看详情
	 * 
	 * @param uuid
	 * @return
	 */
	@RightAccess(1030)
	@RequestMapping(value = "/om/audit/infoDetail1.htm")
	public void infoDetail(@RequestParam(value = "type") String type, @RequestParam(value = "uuid") String uuid,
			ModelMap modelMap) {
		
		Map<String, Object> data = infoService.selectByUuid(uuid);
		
		if (null != data.get("channelId")) {
			Long channelId = (Long) data.get("channelId");
			ChannelSub cs = channelSubService.selectByPrimaryKey(channelId);
			ChannelM cm = channelMService.selectByPrimaryKey(cs.getChannelMId());
			data.put("channelMName", cm.getChannelMName()); // 主频道名称
			data.put("channelSubName", cs.getChannelName()); // 子频道名称
		}
		modelMap.put("data", data); // 查询内容
		modelMap.put("type", type); // audit:审核 detail:详情
	}
	
	/**
	 * 跳转到资讯编辑
	 */
	@RightAccess(1029)
	@RequestMapping(value = "/om/info/edit.htm")
	public void toEdit(@RequestParam(value="uuid") String uuid,ModelMap model){
		try{
			if(StringUtils.isBlank(uuid)){
				return;
			}
			InfoVO vo = infoService.findInfoToEdit(uuid);
			vo.setUuid(uuid);
			model.put("info", vo);
			ChannelM channelM = new ChannelM();
			channelM.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			model.put("channelMs", channelMService.selectListChannelM(channelM));
			ChannelSub sub = new ChannelSub();
			sub.setChannelMId(channelMService.findByUuid(vo.getChannelMUUId()).getId());
			model.put("channelSubs", channelSubService.selectListChannelSub(sub));
		}catch(BizException e){
			log.error("跳转到资讯编失败", e);
		}
	}
	/**
	 * 跳转到石油编辑
	 */
	@RightAccess(1059)
	@RequestMapping(value = "/om/info/oilEdit.htm")
	public void toOilEdit(@RequestParam(value="uuid") String uuid,ModelMap model){
		try{
			if(StringUtils.isBlank(uuid)){
				return;
			}
			InfoVO vo = infoService.findInfoToEdit(uuid);
			vo.setUuid(uuid);
			model.put("info", vo);
			model.put("channelMs", channelMService.selectListChannelM(null));
			ChannelSub sub = new ChannelSub();
			sub.setChannelMId(channelMService.findByUuid(vo.getChannelMUUId()).getId());
			model.put("channelSubs", channelSubService.selectListChannelSub(sub));
		}catch(BizException e){
			log.error("跳转到资讯编失败", e);
		}
	}
	/**
	 * 跳转到帮助中心编辑
	 */
	@RightAccess(1114)
	@RequestMapping(value = "/om/info/HelpCenterEdit.htm")
	public void toHelpCenterEdit(@RequestParam(value="uuid") String uuid,ModelMap model){
		try{
			if(StringUtils.isBlank(uuid)){
				return;
			}
			InfoVO vo = infoService.findInfoToEdit(uuid);
			vo.setUuid(uuid);
			model.put("info", vo);
			model.put("channelMs", channelMService.selectListChannelM(null));
			ChannelSub sub = new ChannelSub();
			sub.setChannelMId(channelMService.findByUuid(vo.getChannelMUUId()).getId());
			model.put("channelSubs", channelSubService.selectListChannelSub(sub));
		}catch(BizException e){
			log.error("跳转到资讯编失败", e);
		}
	}
	
	 /**
	  * 跳转到平台声明编辑
	  */
	@RightAccess(1140)
	@RequestMapping(value = "/om/info/StatementEdit.htm")
	public void toStatementEdit(@RequestParam(value="uuid") String uuid,ModelMap model,String Type){
		try{
			if(StringUtils.isBlank(uuid)){
				return;
			}
			InfoVO vo = infoService.findInfoToEdit(uuid);
			vo.setUuid(uuid);
			model.put("info", vo);
			model.put("channelMs", channelMService.selectListChannelM(null));
			ChannelSub sub = new ChannelSub();
			sub.setChannelMId(channelMService.findByUuid(vo.getChannelMUUId()).getId());
			model.put("channelSubs", channelSubService.selectListChannelSub(sub));
		}catch(BizException e){
			log.error("跳转到资讯编失败", e);
		}
	}
	
	/**
	  * 跳转到成品油编辑
	  */
	@RightAccess(1044)
	@RequestMapping(value = "/om/info/ProductOilEdit.htm")
	public void toProductOilEdit(@RequestParam(value="uuid") String uuid,ModelMap model,String Type){
		try{
			if(StringUtils.isBlank(uuid)){
				return;
			}
			InfoVO vo = infoService.findInfoToEdit(uuid);
			vo.setUuid(uuid);
			model.put("info", vo);
			model.put("channelMs", channelMService.selectListChannelM(null));
			ChannelSub sub = new ChannelSub();
			sub.setChannelMId(channelMService.findByUuid(vo.getChannelMUUId()).getId());
			model.put("channelSubs", channelSubService.selectListChannelSub(sub));
			model.put("Type", Type);
		}catch(BizException e){
			log.error("跳转到资讯编失败", e);
		}
	}
	
	/**
	 * 资讯预览
	 */
	@RequestMapping(value="/om/info/previewInfo.htm")
	public void findInfoDetails(@RequestParam String uuid,ModelMap model) throws BizException{
		model.put("info", infoService.findPreviewInfo(uuid));
	}
	/**
	 * APP端保存或更新资讯
	 * admin - 系统管理员
	 * info_oper - 内容维护人员
	 */
	@ResponseBody
	@RequestMapping(value="/info/appSaveOrUpdateInfo.json",method=RequestMethod.POST)
	public Result appSaveOrUpdateInfo(@RequestBody InfoVO vo,CurrentUser user){
		Result res = new Result();
		if(StringUtils.isBlank(vo.getChannelUUId())){
			res.setFail("栏目不为空");
		}
		if(StringUtils.isBlank(vo.getContent()) || StringUtils.isBlank(vo.getContentWithHtml())){
			res.setFail("发布资讯内容不为空");
		}
		if(StringUtils.isBlank(vo.getTitle())){
			res.setFail("发布资讯标题不为空");
		}
		try{
			res = infoService.saveOrUpdateInfo(vo,user);
		}catch(BizException e){
			log.error("保存或更新资讯失败",e);
		}
		return res;
	}
}
