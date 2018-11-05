package com.sinochem.crude.trade.info.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.model.ShareVO;
import com.sinochem.crude.trade.info.service.ShareService;
import com.sinochem.crude.trade.member.user.CurrentUser;


@Controller
public class ShareController  {

	@Autowired
	private ShareService shareService;
	
	Log log = LogFactory.getLog(ShareController.class);


	/**
	 * 资讯分享
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.SHAREINFO_UPDATE)
	public Result shareInfo(@RequestBody Map<String,Object> map,CurrentUser user){
		if(!map.containsKey("infoUUId")){
			throw new BizException("资讯主键不存在");
		}
		String infoUUId = String.valueOf(map.get("infoUUId"));
		String sharePlatform = String.valueOf(map.get("sharePlatform"));
		if(StringUtils.isEmpty(infoUUId)){
			throw new BizException("资讯主键不能为空");
		}

		if(StringUtils.isBlank(sharePlatform)){
			throw new BizException("分享平台不能为空");
		}

		return shareService.shareInfo(infoUUId,sharePlatform,user);
	}

    /**
     * 分享列表查询
     */
    @ResponseBody
    public ResultDatas<List<ShareVO>> queryList(@RequestBody ShareVO vo) {
        return shareService.queryList(vo);
    }
}
