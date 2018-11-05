package com.sinochem.crude.trade.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.sinochem.crude.trade.info.model.ChannelMVO;
import com.sinochem.crude.trade.info.query.ChannelMQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
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
public class ChannelMController  {

	@Autowired
	private ChannelMService channelMService;
	@Autowired
	private SysCodeSetService codeSetService;
	
	Log log = LogFactory.getLog(ChannelMController.class);


	/**
	 * 新增或编辑主频道
	 */
	@RightAccess(1015)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/channelM/saveOrUpdateChannelM.json",method = RequestMethod.POST)
	public Result saveOrUpdateChannelM(@RequestBody ChannelMVO vo,CurrentUser user){
		Result result = new Result();
		if(StringUtils.isBlank(vo.getChannelMName())){
			result.setFail("主频道名不能为空");
		}
		if(StringUtils.isBlank(vo.getChannelMCode())){
			result.setFail("主频道代码不能为空");
		}
		if(StringUtils.isBlank(vo.getChannelMDesc())){
			result.setFail("主频道类型不为空");
		}
		vo.setCreateUser(user.getName());
		vo.setUpdateUser(user.getName());
		return channelMService.saveOrUpdateChannelM(vo);
	}
	
	/**
	 * 主频道列表分页
	 */
	@RightAccess(1014)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/channelM/channelM.htm")
	public void list(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,
			ModelMap model, ChannelMQuery query) throws BizException {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = channelMService.queryRecords(BeanConvertUtils.beanToMap(query), pageInfo);
		SysCodeSet queryEntity = new SysCodeSet();
		queryEntity.setSetCode("CHANNEL_TYPE");
		List<SysCodeSet> items;
		try {
			items = codeSetService.queryCodeItems(queryEntity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA009, e);
		}
		query.setTotalItem(pageList.getTotal());
		model.put("channelMs", pageList.getResult());
		model.put("channelTypes", items);
		model.put("query", query);
	}
	
	/**
	 * 删除主频道
	 */
	@RightAccess(1018)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/channelM/deleteChannelM.json",method = RequestMethod.GET)
	public Result deleteChannelM(@RequestParam(value="uuid") String uuid, CurrentUser user){
		if(StringUtils.isBlank(uuid)){
			throw new BizException("主频道uuid不为空");
		}
		return channelMService.deleteChannelM(uuid, "");
	}
	
	/**
	 * 主频道列表
	 */
	@ResponseBody
	@RequestMapping("/channelM/selectListChannelM.json")
	public List<Map<String,Object>> selectListChannelM(ChannelM channelM)throws BizException{
		return channelMService.selectListChannelM(channelM);
	}
	
	/**
	 * 主频道类型
	 */
	@ResponseBody
	@RequestMapping("/channelM/listChannelMTypes.json")
	public List<Map<String,String>>listChannelMTypes(){
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
