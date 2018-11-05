package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class PersonLog {
	/**
	 * 用户id
	 */
	private Long id;

    private Long personId;

    /**
	 * 用户编号，uuid
	 */
	private String code;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 证件类型
	 */
	private String cardType;

	/**
	 * 证件号码
	 */
	private String cardNo;

	/**
	 * 证件图片ID
	 */
	private String cardImg;

	/**
	 * 人员头像图片ID
	 * （证件类型：RYTX）
	 */
	private String headImg;

	/**
	 * 人员邮箱地址
	 */
	private String email;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建用户
	 */
	private String createUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 更新用户
	 */
	private String updateUser;
	
	/**
	 * members表关联ID
	 */
	private Long memberId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	
   
}