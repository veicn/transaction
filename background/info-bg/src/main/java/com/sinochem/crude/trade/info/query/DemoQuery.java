package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class DemoQuery extends QueryBase {

	private static final long serialVersionUID = 4607791991273674516L;

	private String userName;
	
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		demo.put("p1", "aaaa");
		demo.put("v6", "tyuuuu");
		return demo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
