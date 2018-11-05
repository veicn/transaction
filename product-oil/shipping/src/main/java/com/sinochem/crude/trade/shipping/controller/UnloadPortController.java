package com.sinochem.crude.trade.shipping.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;
import com.sinochem.crude.trade.shipping.model.vo.UnloadPortVO;
import com.sinochem.crude.trade.shipping.service.UnloadPortService;
import com.sinochem.it.b2b.common.result.Result;

/**
 * 卸港信息维护
 * 
 * @author Zhu Tao
 * @createtime 2018年3月11日下午2:28:58
 * @details
 */
@Controller
@RequestMapping(value = "pages/back/unloadport")
public class UnloadPortController {

	public static final Log log = LogFactory.getLog(UnloadPortController.class);
	@Autowired
	private UnloadPortService unloadPortService;

	/**
	 * 卸港信息保存  -武刚鹏 -2018年3月19日19:30:42
	 * @param vo
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value = "saveDischarge.json",method = RequestMethod.POST)
	@ResponseBody
	public Result saveDischarge(@RequestBody UnloadPortVO vo,
			CurrentUser currentUser) {
		Result result = new Result();
		UnloadPort unloadPort = new UnloadPort();
		try {
			unloadPort = vo.voToDomain();
			Integer code;
			if(StringUtil.isEmpty(unloadPort.getUuid())){
				code = unloadPortService.insertRecord(unloadPort,currentUser);
			}else{
				code = unloadPortService.updateRecordByUuid(unloadPort,currentUser);
			}
			if(code >0){
				result.setMessage(Constants.SAVE_TRU);
			}else{
				result.setMessage(Constants.SAVE_FALSE);
			}
		}catch (Exception e) {
			log.error("getConfirmationSheet error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		} finally {
			return result;
		}
	}

}
