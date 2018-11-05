package com.sinochem.crude.trade.member.model.to;


import com.sinochem.crude.trade.member.domain.Member;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class_name: MemberSSOVO
 * package: com.sinochem.open.vo.member
 * describe: MIC用户信息
 * create_user: wj
 * create_date: 2018/05/15
 * create_time: 2:54 PM
 **/
@SuppressWarnings("unused")
public class MSGMemberTO implements Serializable {

	private static final long serialVersionUID = -6079236874085883384L;

	/** 源系统中的用户唯一编号**/
	private String uid;
	/** 手机号**/
	private String mobile;
	/** 登陆名称**/
	private String loginName;
	/** 密码，MD5加密**/
	private String password;
	/** 账户冻结标识**/
	private String isAccountLocked;
	/** 是否已激活**/
	private String isActive;
	/** 是否禁用**/
	private String isForbidden;
	/** 更新数据来源**/
	private String updateSource;
	/** 修改时间**/
	private Date updateTime;
	/** 创建人**/
	private String createUser;
	/** 创建数据来源**/
	private String createSource;
	/** 创建时间**/
	private Date createTime;
	/** 删除标志**/
	private String delFlg;

	public MSGMemberTO(){}

	public MSGMemberTO(Member member){
		Date now = new Date();
		this.uid = member.getCode();
		this.mobile = member.getMobile();
		this.loginName = member.getUserName();
		this.password = member.getPassword();
		this.isAccountLocked = "0";
		this.isActive = "1";
		this.isForbidden = "0";
		this.updateSource = "mycrudeoil";
		this.updateTime = now;
		this.createSource = "mycrudeoil";
		this.createTime = now;
		this.delFlg = "0";
		this.createUser = "mycrudeoil";
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(String isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsForbidden() {
		return isForbidden;
	}

	public void setIsForbidden(String isForbidden) {
		this.isForbidden = isForbidden;
	}

	public String getUpdateSource() {
		return updateSource;
	}

	public void setUpdateSource(String updateSource) {
		this.updateSource = updateSource;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}


	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


}
