package com.sinochem.crude.trade.info.service;

import com.sinochem.crude.trade.info.model.ExternalIn;
import com.sinochem.crude.trade.info.model.ExternalOut;

public interface ExternalService {
	
	
	ExternalOut process(ExternalIn in) throws Exception;
	
	void useAll() throws Exception;

}
