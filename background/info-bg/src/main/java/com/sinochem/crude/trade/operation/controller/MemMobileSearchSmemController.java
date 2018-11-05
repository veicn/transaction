package com.sinochem.crude.trade.operation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.service.MemMemberSmemService;
import com.sinochem.crude.trade.operation.vo.AppMenuOut;
import com.sinochem.crude.trade.operation.vo.MemberMenu;
import com.sinochem.crude.trade.operation.vo.MemberMenuSave;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class MemMobileSearchSmemController {

	private static Logger log = LoggerFactory.getLogger(MemMobileSearchSmemController.class);

	@Autowired
	private MemMemberSmemService memmembersmemservice;

	/**
	 * 保存我的关注(app)
	 *
	 * @param memberMenu
	 * @return
	 */
	@RequestMapping(value = URLMapping.APPS_SAVEATTENTIONFORAPP, method = RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<Boolean> saveAttentionForApp(CurrentUser user, @RequestBody MemberMenuSave memberMenu) {
		ResultDatas<Boolean> result = new ResultDatas<>();
		/*if (StringUtils.isBlank(memberMenu.getDeviceId())) {
			result.setDatas(false);
			result.setCode(OperationException.TYPE.BADV003.getCode());
			result.setFail(OperationException.TYPE.BADV003.getDesc());
			log.warn(OperationException.TYPE.BADV003.getDesc());
			return result;
		}*/

		Boolean aso = this.memmembersmemservice.saveAttentionToMySqlAndMongonDB(user, memberMenu);
		result.setDatas(aso);

		return result;
	}

	/**
	 * 获取个人所有菜单
	 * 
	 * @param request
	 * @param memberMenu
	 * @return
	 */
	@RequestMapping(value = URLMapping.APPS_QUERYMENUALL, method = RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<List<AppMenuOut>> queryMenuAll(CurrentUser user, HttpServletRequest request, @RequestBody MemberMenu memberMenu) {
		ResultDatas<List<AppMenuOut>> result = new ResultDatas<>();
		
		List<AppMenuOut> aso = this.memmembersmemservice.queryMenusAll(memberMenu, user);
		result.setDatas(aso);
		return result;
	}

}
