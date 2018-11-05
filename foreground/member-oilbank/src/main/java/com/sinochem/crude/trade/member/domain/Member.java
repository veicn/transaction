package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class Member {
	/**
	 * 用户id
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 用户编号，uuid
	 */
	private String code;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 用户类型，0-个人，1-企业
	 */
	private String type;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * qq号码
	 */
	private String qqNo;

	/**
	 * qq的openID，用于从qq登录
	 */
	private String qqOpenId;

	/**
	 * 头像地址
	 */
	private String headUrl;

	/**
	 * 登录失败计数
	 */
	private Integer loginFailureCount;

	/**
	 * 登录时间
	 */
	private Date loginDate;

	/**
	 * 密码找回的密钥
	 */
	private String passwordRecoverKey;

	/**
	 * 密钥失效时间
	 */
	private Date keyLostTime;

	/**
	 * 是否被锁定,一般系统输入密码超过5次，被锁定
	 */
	private Boolean locked;

	/**
	 * 是否被禁用，外部系统出发，本系统不控制
	 */
	private Boolean disable;

	public Boolean getLocked() {
		return locked;
	}

	public Boolean getDisable() {
		return disable;
	}

	public String getUdbUUid() {
		return udbUUid;
	}

	public void setUdbUUid(String udbUUid) {
		this.udbUUid = udbUUid;
	}

	/**

	 * 删除标记
	 */
	private boolean delFlg;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建用户
	 */
	private Long creater;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String tempPwd;

	/**
	 * 更新用户
	 */
	private Long updater;
	//UDB个人用户ID
	private  String udbUUid;


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}

	public Date getKeyLostTime() {
		return keyLostTime;
	}

	public void setKeyLostTime(Date keyLostTime) {
		this.keyLostTime = keyLostTime;
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

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTempPwd() {
		return tempPwd;
	}

	public void setTempPwd(String tempPwd) {
		this.tempPwd = tempPwd;
	}


}