package com.sinochem.crude.trade.info.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.xxl.DataFeedHandler;
import com.sinochem.crude.trade.xxl.SymbolPriceHandler;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class JKXTestController {

	@Autowired
	DataFeedHandler dataFeedHandler;
	@Autowired
	SymbolPriceHandler symbolPriceHandler;
	
	@RequestMapping(value="/test.json")
	public void test() {
		try {
			symbolPriceHandler.execute(null);
		} catch (Exception e) {
		}
	}
	
}
