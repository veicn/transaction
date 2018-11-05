package com.sinochem.crude.trade.shipagent.controller;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSof;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetailTemplate;
import com.sinochem.crude.trade.shipagent.service.SofTemplateService;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 *
 * @author admin1
 * @date 2018/10/25
 */
@Controller
@RequestMapping("/blockchain/shipagent/sof/detail/template")
public class SofTemplateController {


	@Autowired
	private SofTemplateService sofTemplateService;

	@RequestMapping("/list.json")
	@WithoutAccess
	public ResultData list(@RequestBody TShipagentSofDetailTemplate template){
		ResultData result = new ResultData();
		try {
			List<TShipagentSofDetailTemplate> templateList = sofTemplateService.list(template);

			result.setData(templateList);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
