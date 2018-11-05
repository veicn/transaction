package com.sinochem.crude.trade.blockchain.service;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetailTemplate;

import java.util.List;

/**
 * Created by admin1 on 2018/10/24.
 */
public interface TShipagentSofDetailTemplateService {


	public List<TShipagentSofDetailTemplate> selectByParameter(TShipagentSofDetailTemplate template);
}
