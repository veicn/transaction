package com.sinochem.crude.trade.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.commons.exceptions.OperationException;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.model.ChannelSubVO;
import com.sinochem.crude.trade.info.query.ChannelSubQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.service.SysCodeSetService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class ChannelSubController  {

	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private ChannelMService channelMService;
	@Autowired
	private SysCodeSetService codeSetService;
	
	Log log = LogFactory.getLog(ChannelSubController.class);
	
	/**
	 * 子频道列表
	 */
	@RightAccess(1019)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/channelSub/channelSub.htm")
	public void list(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,
			ModelMap model, ChannelSubQuery query) throws BizException {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = channelSubService.queryRecords(BeanConvertUtils.beanToMap(query), pageInfo);
		SysCodeSet queryEntity = new SysCodeSet();
		queryEntity.setSetCode("CHANNEL_TYPE");
		List<SysCodeSet> items;
		try {
			items = codeSetService.queryCodeItems(queryEntity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA009, e);
		}
		query.setTotalItem(pageList.getTotal());
		model.put("channels", pageList.getResult());
		model.put("channelTypes", items);
		model.put("query", query);
	}
	
	/**
	 * 新增子频道
	 */
	@RightAccess(1020)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/channelSub/saveOrUpdateChannelSub.json",method = RequestMethod.POST)
	public Result saveOrUpdateChannelSub(@RequestBody ChannelSubVO vo,ModelMap model)throws BizException{
		Result result = new Result();
		if(StringUtils.isBlank(vo.getChannelMUuid())){
			result.setFail("主频道类型不为空");
			return result;
		}
		if(StringUtils.isBlank(vo.getChannelCode())){
			result.setFail("子频道代码不为空");
			return result;
		}
		if(StringUtils.isBlank(vo.getChannelName())){
			result.setFail("子频道名称不为空");
			return result;
		}
		return channelSubService.saveOrUpdateChannel(vo);
	}
	
	/**
	 * 删除子频道
	 */
	@RightAccess(1023)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/channelSub/deleteChannelSub.json",method = RequestMethod.GET)
	public Result deleteChannelSub(@RequestParam(value="uuid") String uuid) throws BizException{
		if(StringUtils.isBlank(uuid)){
			throw new BizException("UUID 不为空");
		}
		return channelSubService.deleteByUUId(uuid);
	}
	
	/**
	 * 通过主频道频道获取子频道信息
	 */
	@ResponseBody
	@RequestMapping("/channelSub/selectListChannelSub.json")
	public List<Map<String,Object>> selectListChannelM(@RequestParam(value="pid") String pUUId)throws BizException{
		if(StringUtils.isBlank(pUUId)){
			throw new BizException("主频道uuid不为空");
		}
		ChannelSub param = new ChannelSub();
		ChannelM channelM = channelMService.findByUuid(pUUId);
		if(channelM == null){
			return null;
		}
		param.setChannelMId(channelM.getId());
		return channelSubService.selectListChannelSub(param);
	}
	/**
	 * 通过角色权限获取主频道信息
	 */
	@ResponseBody
	@RequestMapping(value="/channelSub/selectListChannelMByRoles.json")
	public List<Map<String,Object>> selectListChannelMByRoles(CurrentUser user)throws BizException{
		return channelSubService.selectListChannelMByRoles(user);
	}
	/**
	 * 通过主频道频道+roles获取子频道信息
	 */
	@ResponseBody
	@RequestMapping("/channelSub/selectListChannelSubByRoles.json")
	public List<Map<String,Object>> selectListChannelSubByRoles(@RequestParam(value="pid") String pUUId,CurrentUser user)throws BizException{
		if(StringUtils.isBlank(pUUId)){
			throw new BizException("主频道uuid不为空");
		}
		ChannelSub param = new ChannelSub();
		ChannelM channelM = channelMService.findByUuid(pUUId);
		if(channelM == null){
			return null;
		}
		param.setChannelMId(channelM.getId());
		return channelSubService.selectListChannelSubByRoles(param,user.getRoles());
	}
	
	/**
	 * 加载主频道类型信息
	 * @return
	 */
	private List<Map<String,String>>listChannelMTypes(){
		Constants.channel_m_desc_enum[] arr =	Constants.channel_m_desc_enum.values();
		List<Map<String,String>> list = new ArrayList<>();
		for(Constants.channel_m_desc_enum item : arr) {
			Map<String,String> ds = new HashMap<>(2);
			ds.put("type", item.getChannel_m_desc_key());
			ds.put("name", item.getChannel_m_desc_value());
			list.add(ds);
		}
		return list;
	}
}
