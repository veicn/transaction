package com.sinochem.crude.trade.member.domain;

/**
 * 
 * @author 胡凌 用户和资质对应关系
 */
public class MemberCredentials {

	/**
	 * PK
	 * 
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long memberId;

	/**
	 * 资质编号
	 */
	private String credentialsCode;

	/**
	 * 是否审核
	 */
	private Integer audit;

	private String userName;
	private String email;
	private String mobile;
	private String name;
	private String mail;

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

	public String getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(String credentialsCode) {
		//ZJ 这里在角色清洗时使用，如果使用名称，则清洗全部角色
		this.credentialsCode = credentialsCode;//EnterpriseCredentialsEnum.toName(Integer.parseInt(credentialsCode));
	}

//	public boolean isAudits() {
//		if(audit!=null&&audit==1){
//			return true;
//		}
//		return false;
//	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAudit() {
		return audit;
	}
}
