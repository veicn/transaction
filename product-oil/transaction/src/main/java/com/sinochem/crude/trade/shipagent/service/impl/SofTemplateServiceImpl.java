package com.sinochem.crude.trade.shipagent.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TShipagentSofDetailTemplateMapper;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetailTemplate;
import com.sinochem.crude.trade.shipagent.service.SofTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author admin1
 * @date 2018/10/25
 */
@Service("SofTemplateService")
public class SofTemplateServiceImpl implements SofTemplateService{



	@Autowired
	private TShipagentSofDetailTemplateMapper tShipagentSofDetailTemplateMapper;


	@Override
	public List<TShipagentSofDetailTemplate> list(TShipagentSofDetailTemplate template){
		return tShipagentSofDetailTemplateMapper.selectByParamter(template);
	}





}
