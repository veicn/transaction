package com.sinochem.crude.trade.blockchain.service.impl;

import com.sinochem.crude.trade.blockchain.dao.TShipagentSofDetailTemplateMapper;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetailTemplate;
import com.sinochem.crude.trade.blockchain.service.TShipagentSofDetailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin1 on 2018/10/24.
 */
@Service
public class TShipagentSofDetailTemplateServiceImpl implements TShipagentSofDetailTemplateService {


	@Autowired
	private TShipagentSofDetailTemplateMapper tShipagentSofDetailTemplateMapper;


	@Override
	public List<TShipagentSofDetailTemplate> selectByParameter(TShipagentSofDetailTemplate template){
		return tShipagentSofDetailTemplateMapper.selectByParamter(template);
	}
}
