package com.sinochem.crude.trade.wechat.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Long wechatUserId;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 微信openid
     */
    private String openid;
    
    /**
     * 成品油账号
     */
    private String userName;
    
    /**
     * 成品油密码
     */
    private String password;

    /**
     * 成品油账号id
     */
    private Long memberId;

    /**
     * 成品油账号企业id
     */
    private Long epMemberId;

    /**
     * 角色
     */
    private String role;

    /**
     * 创建时间
     */
    private java.util.Date createDate;

    /**
     * 修改时间
     */
    private java.util.Date updateDate;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 是否有效（1有效  0无效）
     */
    private String aliveFlag;

    /**
     * 版本号
     */
    private String version;

    /**
     * 语言版本
     */
    private String langVer;

    /**
     * 扩展字段
     */
    private String etx1;

    private String nickname;

    private String headimgurl;

    
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * 主键自增
     */
    public Long getWechatUserId() {
        return this.wechatUserId;
    }

    /**
     * 主键自增
     */
    public void setWechatUserId(Long wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    /**
     * uuid
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 微信openid
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 成品油账号id
     */
    public Long getMemberId() {
        return this.memberId;
    }

    /**
     * 成品油账号id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 成品油账号企业id
     */
    public Long getEpMemberId() {
        return this.epMemberId;
    }

    /**
     * 成品油账号企业id
     */
    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    /**
     * 角色
     */
    public String getRole() {
        return this.role;
    }

    /**
     * 角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 创建时间
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 修改时间
     */
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 修改时间
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 创建人
     */
    public Long getCreateUser() {
        return this.createUser;
    }

    /**
     * 创建人
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * 修改人
     */
    public Long getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 修改人
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 是否有效（1有效  0无效）
     */
    public String getAliveFlag() {
        return this.aliveFlag;
    }

    /**
     * 是否有效（1有效  0无效）
     */
    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    /**
     * 版本号
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 语言版本
     */
    public String getLangVer() {
        return this.langVer;
    }

    /**
     * 语言版本
     */
    public void setLangVer(String langVer) {
        this.langVer = langVer;
    }

    /**
     * 扩展字段
     */
    public String getEtx1() {
        return this.etx1;
    }

    /**
     * 扩展字段
     */
    public void setEtx1(String etx1) {
        this.etx1 = etx1;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wechatUserId", this.wechatUserId);
        map.put("uuid", this.uuid);
        map.put("openid", this.openid);
        map.put("memberId", this.memberId);
        map.put("epMemberId", this.epMemberId);
        map.put("role", this.role);
        map.put("createDate", this.createDate);
        map.put("updateDate", this.updateDate);
        map.put("createUser", this.createUser);
        map.put("updateUser", this.updateUser);
        map.put("aliveFlag", this.aliveFlag);
        map.put("version", this.version);
        map.put("langVer", this.langVer);
        map.put("etx1", this.etx1);
        return map;
    }

}