package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 自增主键 */
	private Long id;

	/** 32位的唯一键 */
	private String uuid;

	/** 角色id */
	private Long roleId;

	/** 角色code */
	private String roleCode;

	/** 角色名称 */
	private String roleName;

	/** 频道id */
	private Long channelId;

	/** 频道code */
	private String channelCode;

	/** 频道名称 */
	private String channelName;

	/** 是否允许发布 0:否 1:是 */
	private String isRelease;

	/** 是否限制当日发布次数(1限制，0不限) */
	private String isLimitCount;

	/** 当日发布次数上线 */
	private Integer releaseCount;

	/** 审核类型 1:人工审核 2:自动审核 */
	private String auditType;

	/** 创建者 */
	private String createUser;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 修改者 */
	private String updateUser;

	/** 修改时间 */
	private java.util.Date updateDate;

	/** 数据状态 */
	private String aliveFlag;

	/** 市场ID */
	private String marketId;

	/** 语言类型（ZH：中文，EN：英文） */
	private String langVer;

	/** 扩展字段1 */
	private String extend1;

	/** 扩展字段2 */
	private String extend2;

	/** 扩展字段3 */
	private String extend3;

	/** 扩展字段4 */
	private String extend4;

	/** 扩展字段5 */
	private String extend5;

	/** 扩展字段6 */
	private String extend6;

	/** 扩展字段7 */
	private String extend7;

	/** 扩展字段8 */
	private String extend8;

	/** 扩展字段9 */
	private String extend9;

	/** 扩展字段10 */
	private String extend10;

	/** 自增主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 自增主键 */
	public Long getId() {
		return this.id;
	}

	/** 32位的唯一键 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/** 32位的唯一键 */
	public String getUuid() {
		return this.uuid;
	}

	/** 角色id */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 角色id */
	public Long getRoleId() {
		return this.roleId;
	}

	/** 角色code */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/** 角色code */
	public String getRoleCode() {
		return this.roleCode;
	}

	/** 角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** 角色名称 */
	public String getRoleName() {
		return this.roleName;
	}

	/** 频道id */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/** 频道id */
	public Long getChannelId() {
		return this.channelId;
	}

	/** 频道code */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	/** 频道code */
	public String getChannelCode() {
		return this.channelCode;
	}

	/** 频道名称 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** 频道名称 */
	public String getChannelName() {
		return this.channelName;
	}

	/** 是否允许发布 0:否 1:是 */
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}

	/** 是否允许发布 0:否 1:是 */
	public String getIsRelease() {
		return this.isRelease;
	}

	/** 是否限制当日发布次数(1限制，0不限) */
	public void setIsLimitCount(String isLimitCount) {
		this.isLimitCount = isLimitCount;
	}

	/** 是否限制当日发布次数(1限制，0不限) */
	public String getIsLimitCount() {
		return this.isLimitCount;
	}

	/** 当日发布次数上线 */
	public void setReleaseCount(Integer releaseCount) {
		this.releaseCount = releaseCount;
	}

	/** 当日发布次数上线 */
	public Integer getReleaseCount() {
		return this.releaseCount;
	}

	/** 审核类型 1:人工审核 2:自动审核 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	/** 审核类型 1:人工审核 2:自动审核 */
	public String getAuditType() {
		return this.auditType;
	}

	/** 创建者 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/** 创建者 */
	public String getCreateUser() {
		return this.createUser;
	}

	/** 创建时间 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/** 创建时间 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/** 修改者 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/** 修改者 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/** 修改时间 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 修改时间 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/** 数据状态 */
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	/** 数据状态 */
	public String getAliveFlag() {
		return this.aliveFlag;
	}

	/** 市场ID */
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	/** 市场ID */
	public String getMarketId() {
		return this.marketId;
	}

	/** 语言类型（ZH：中文，EN：英文） */
	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	/** 语言类型（ZH：中文，EN：英文） */
	public String getLangVer() {
		return this.langVer;
	}

	/** 扩展字段1 */
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	/** 扩展字段1 */
	public String getExtend1() {
		return this.extend1;
	}

	/** 扩展字段2 */
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	/** 扩展字段2 */
	public String getExtend2() {
		return this.extend2;
	}

	/** 扩展字段3 */
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	/** 扩展字段3 */
	public String getExtend3() {
		return this.extend3;
	}

	/** 扩展字段4 */
	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	/** 扩展字段4 */
	public String getExtend4() {
		return this.extend4;
	}

	/** 扩展字段5 */
	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}

	/** 扩展字段5 */
	public String getExtend5() {
		return this.extend5;
	}

	/** 扩展字段6 */
	public void setExtend6(String extend6) {
		this.extend6 = extend6;
	}

	/** 扩展字段6 */
	public String getExtend6() {
		return this.extend6;
	}

	/** 扩展字段7 */
	public void setExtend7(String extend7) {
		this.extend7 = extend7;
	}

	/** 扩展字段7 */
	public String getExtend7() {
		return this.extend7;
	}

	/** 扩展字段8 */
	public void setExtend8(String extend8) {
		this.extend8 = extend8;
	}

	/** 扩展字段8 */
	public String getExtend8() {
		return this.extend8;
	}

	/** 扩展字段9 */
	public void setExtend9(String extend9) {
		this.extend9 = extend9;
	}

	/** 扩展字段9 */
	public String getExtend9() {
		return this.extend9;
	}

	/** 扩展字段10 */
	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}

	/** 扩展字段10 */
	public String getExtend10() {
		return this.extend10;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("uuid", this.uuid);
		map.put("roleId", this.roleId);
		map.put("roleCode", this.roleCode);
		map.put("roleName", this.roleName);
		map.put("channelId", this.channelId);
		map.put("channelCode", this.channelCode);
		map.put("channelName", this.channelName);
		map.put("isRelease", this.isRelease);
		map.put("isLimitCount", this.isLimitCount);
		map.put("releaseCount", this.releaseCount);
		map.put("auditType", this.auditType);
		map.put("createUser", this.createUser);
		map.put("createDate", this.createDate);
		map.put("updateUser", this.updateUser);
		map.put("updateDate", this.updateDate);
		map.put("aliveFlag", this.aliveFlag);
		map.put("marketId", this.marketId);
		map.put("langVer", this.langVer);
		map.put("extend1", this.extend1);
		map.put("extend2", this.extend2);
		map.put("extend3", this.extend3);
		map.put("extend4", this.extend4);
		map.put("extend5", this.extend5);
		map.put("extend6", this.extend6);
		map.put("extend7", this.extend7);
		map.put("extend8", this.extend8);
		map.put("extend9", this.extend9);
		map.put("extend10", this.extend10);
		return map;
	}

	@Override
	public String toString() { // id是否需要返回？？？？？？？？？？？？？？？
		return "Rule [id=" + id + ", uuid=" + uuid + ", roleId=" + roleId + ", roleCode=" + roleCode + ", roleName="
				+ roleName + ", channelId=" + channelId + ", channelCode=" + channelCode + ", channelName="
				+ channelName + ", isRelease=" + isRelease + ", isLimitCount=" + isLimitCount + ", releaseCount="
				+ releaseCount + ", auditType=" + auditType + ", createUser=" + createUser + ", createDate="
				+ createDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + ", aliveFlag=" + aliveFlag
				+ ", marketId=" + marketId + ", langVer=" + langVer + ", extend1=" + extend1 + ", extend2=" + extend2
				+ ", extend3=" + extend3 + ", extend4=" + extend4 + ", extend5=" + extend5 + ", extend6=" + extend6
				+ ", extend7=" + extend7 + ", extend8=" + extend8 + ", extend9=" + extend9 + ", extend10=" + extend10
				+ "]";
	}

}