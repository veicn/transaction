package com.sinochem.crude.trade.memb.domain;

/**
 * 用户可以访问的模块
 * 
 * @author Leo
 *
 */
public class MemberModule {

	/**
	 * PK
	 * 
	 */
	private Long id;

	/**
	 * 对应用户
	 */
	private Long memberId;

	/**
	 * 和会员一样，是企业，还是个人
	 */
	private String memberType;

	/**
	 * 模块id
	 */
	private Long moduleId;

	/**
	 * 是否审核通过，一个member需要开展不退的大业务模块的业务，需要在这里有记录，没有记录，是没有资格读取权限
	 */
	private boolean audit;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public boolean isAudit() {
		return audit;
	}

	public void setAudit(boolean audit) {
		this.audit = audit;
	}

}
