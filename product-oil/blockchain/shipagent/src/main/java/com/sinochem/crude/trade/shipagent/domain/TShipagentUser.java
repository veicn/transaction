package com.sinochem.crude.trade.shipagent.domain;

import java.io.Serializable;

/**
 *
 * @author songhaiqiang
 * @date 2018/10/12
 */
public class TShipagentUser implements Serializable{


	private static final long serialVersionUID = 3485713459231452721L;
	/**企业ID*/
	private Long epMemberId;

	/**用户ID*/
	private Long memberId;


	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
