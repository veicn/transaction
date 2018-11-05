package com.sinochem.crude.trade.member.domain;

import java.io.Serializable;

public class AgentRegister extends Enterprise implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String[] types;

	private Integer useSocialCreditCertInt;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Integer getUseSocialCreditCertInt() {
		return useSocialCreditCertInt;
	}

	public void setUseSocialCreditCertInt(Integer useSocialCreditCertInt) {
		this.useSocialCreditCertInt = useSocialCreditCertInt;
	}

}