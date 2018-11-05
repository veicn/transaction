package com.sinochem.crude.trade.info.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class RuleQuery extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String roleName;

	@Override
	public Map<String, String> getParameters() {

		Map<String, String> param = new HashMap<String, String>();
		param.put("roleName", getRoleName());

		return null;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
