package com.sinochem.crude.trade.shipping.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;

/**
 * @author Zhu Tao
 * @createtime 2018年3月10日下午2:07:34
 * @details 查看船舶确认单详情
 */
@Controller
@RequestMapping(UrlMapping.VESSEL_ACCEPTANCE_DETAILS)
public class VesselAcceptanceDetailsController {

	@Autowired
	private ConfirmationSheetService confirmationSheetService;
	public static final Log log = LogFactory.getLog(VesselAcceptanceDetailsController.class);

	/**
	 * 查看船舶确认单详情
	 * 
	 * @return
	 */
	@RequestMapping(UrlMapping.INDEX)
	public void shipConfirmationDetails(String confirmationSheetUuid, CurrentUser currentUser, ModelMap modelMap) {

		// TODO
		//confirmationSheetUuid = "1313123221321";
		// 根据uuid查询confirmationSheet
		ConfirmationSheet confirmationSheet = new ConfirmationSheet();
		confirmationSheet = confirmationSheetService.findByUuid(confirmationSheetUuid);
		// domain转vo
		ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
		confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);

		// 将confirmationSheetVO装入map，传到vm页面
		modelMap.put("confirmationSheetVO", confirmationSheetVO);

	}

}
