package com.sinochem.crude.trade.transport.model;

import com.sinochem.crude.trade.transport.domain.Clause;

public class ClauseVO extends Clause {
	String roleFlag;
	
	public String getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	private static final long serialVersionUID = 1L;	
	
}