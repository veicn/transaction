package com.sinochem.crude.trade.orderexecute.model;

public class InspectionMemberSaveFormVO {
	/**
	 * 装卸港主键
	 */
	private Long id;
	
	/**
	 * 商检公司ID
	 */
	private Long memberId;
	/**
	 * 商检公司名称
	 */
	private String memberName;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 码头
	 */
	private String terminal;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 联系邮箱
	 */
	private String mail;
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
