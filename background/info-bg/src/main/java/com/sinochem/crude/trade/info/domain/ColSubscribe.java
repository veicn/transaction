package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ColSubscribe implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;

	/** 专栏订阅人 */
	private String subscribeUser;

	/** 订阅人名 */
	private String subscribeUserName;

	/** 起始有效期 */
	private java.util.Date startEffectiveDate;

	/** 截止有效期 */
	private java.util.Date endEffectiveDate;

	/** 订阅时间 */
	private java.util.Date subscribeDate;

	/** 订阅价格 */
	private java.math.BigDecimal subscribePrice;

	/** 业务主键(专栏或专题) */
	private String columnId;

	/** 业务标题名(专栏或专题) */
	private String columnName;

	/** 订阅类型 1:专栏 2:专题 */
	private String subscribeType;

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	private String columnCyc;

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	private String settleType;

	/** 状态 00:已取消 01:支付失败 10:待支付 20:支付中 30:支付成功 */
	private String status;

	/** 支付订单号 */
	private String payOrderNo;

	/** 支付平台 WX:微信 ZFB:支付宝 */
	private String payPlatform;

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
	private String ext1;

	/** 扩展字段2 */
	private String ext2;

	/** 扩展字段3 */
	private String ext3;

	/** 扩展字段4 */
	private String ext4;

	/** 扩展字段5 */
	private String ext5;

	/** 扩展字段6 */
	private String ext6;

	/** 扩展字段7 */
	private String ext7;

	/** 扩展字段8 */
	private String ext8;

	/** 扩展字段9 */
	private String ext9;

	/** 扩展字段10 */
	private String ext10;

	/** 主键 */
	public void setId(String id) {
		this.id = id;
	}

	/** 主键 */
	public String getId() {
		return this.id;
	}

	/** 专栏订阅人 */
	public void setSubscribeUser(String subscribeUser) {
		this.subscribeUser = subscribeUser;
	}

	/** 专栏订阅人 */
	public String getSubscribeUser() {
		return this.subscribeUser;
	}

	/** 订阅人名 */
	public void setSubscribeUserName(String subscribeUserName) {
		this.subscribeUserName = subscribeUserName;
	}

	/** 订阅人名 */
	public String getSubscribeUserName() {
		return this.subscribeUserName;
	}

	/** 起始有效期 */
	public void setStartEffectiveDate(java.util.Date startEffectiveDate) {
		this.startEffectiveDate = startEffectiveDate;
	}

	/** 起始有效期 */
	public java.util.Date getStartEffectiveDate() {
		return this.startEffectiveDate;
	}

	/** 截止有效期 */
	public void setEndEffectiveDate(java.util.Date endEffectiveDate) {
		this.endEffectiveDate = endEffectiveDate;
	}

	/** 截止有效期 */
	public java.util.Date getEndEffectiveDate() {
		return this.endEffectiveDate;
	}

	/** 订阅时间 */
	public void setSubscribeDate(java.util.Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	/** 订阅时间 */
	public java.util.Date getSubscribeDate() {
		return this.subscribeDate;
	}

	/** 订阅价格 */
	public void setSubscribePrice(java.math.BigDecimal subscribePrice) {
		this.subscribePrice = subscribePrice;
	}

	/** 订阅价格 */
	public java.math.BigDecimal getSubscribePrice() {
		return this.subscribePrice;
	}

	/** 业务主键(专栏或专题) */
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	/** 业务主键(专栏或专题) */
	public String getColumnId() {
		return this.columnId;
	}

	/** 业务标题名(专栏或专题) */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/** 业务标题名(专栏或专题) */
	public String getColumnName() {
		return this.columnName;
	}

	/** 订阅类型 1:专栏 2:专题 */
	public void setSubscribeType(String subscribeType) {
		this.subscribeType = subscribeType;
	}

	/** 订阅类型 1:专栏 2:专题 */
	public String getSubscribeType() {
		return this.subscribeType;
	}

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	public void setColumnCyc(String columnCyc) {
		this.columnCyc = columnCyc;
	}

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	public String getColumnCyc() {
		return this.columnCyc;
	}

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	public String getSettleType() {
		return this.settleType;
	}

	/** 状态 00:已取消 01:支付失败 10:待支付 20:支付中 30:支付成功 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 状态 00:已取消 01:支付失败 10:待支付 20:支付中 30:支付成功 */
	public String getStatus() {
		return this.status;
	}

	/** 支付订单号 */
	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	/** 支付订单号 */
	public String getPayOrderNo() {
		return this.payOrderNo;
	}

	/** 支付平台 WX:微信 ZFB:支付宝 */
	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}

	/** 支付平台 WX:微信 ZFB:支付宝 */
	public String getPayPlatform() {
		return this.payPlatform;
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
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/** 扩展字段1 */
	public String getExt1() {
		return this.ext1;
	}

	/** 扩展字段2 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/** 扩展字段2 */
	public String getExt2() {
		return this.ext2;
	}

	/** 扩展字段3 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	/** 扩展字段3 */
	public String getExt3() {
		return this.ext3;
	}

	/** 扩展字段4 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	/** 扩展字段4 */
	public String getExt4() {
		return this.ext4;
	}

	/** 扩展字段5 */
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	/** 扩展字段5 */
	public String getExt5() {
		return this.ext5;
	}

	/** 扩展字段6 */
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}

	/** 扩展字段6 */
	public String getExt6() {
		return this.ext6;
	}

	/** 扩展字段7 */
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	/** 扩展字段7 */
	public String getExt7() {
		return this.ext7;
	}

	/** 扩展字段8 */
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}

	/** 扩展字段8 */
	public String getExt8() {
		return this.ext8;
	}

	/** 扩展字段9 */
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}

	/** 扩展字段9 */
	public String getExt9() {
		return this.ext9;
	}

	/** 扩展字段10 */
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}

	/** 扩展字段10 */
	public String getExt10() {
		return this.ext10;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("subscribeUser", this.subscribeUser);
		map.put("subscribeUserName", this.subscribeUserName);
		map.put("startEffectiveDate", this.startEffectiveDate);
		map.put("endEffectiveDate", this.endEffectiveDate);
		map.put("subscribeDate", this.subscribeDate);
		map.put("subscribePrice", this.subscribePrice);
		map.put("columnId", this.columnId);
		map.put("columnName", this.columnName);
		map.put("subscribeType", this.subscribeType);
		map.put("columnCyc", this.columnCyc);
		map.put("settleType", this.settleType);
		map.put("status", this.status);
		map.put("payOrderNo", this.payOrderNo);
		map.put("payPlatform", this.payPlatform);
		map.put("createUser", this.createUser);
		map.put("createDate", this.createDate);
		map.put("updateUser", this.updateUser);
		map.put("updateDate", this.updateDate);
		map.put("aliveFlag", this.aliveFlag);
		map.put("marketId", this.marketId);
		map.put("langVer", this.langVer);
		map.put("ext1", this.ext1);
		map.put("ext2", this.ext2);
		map.put("ext3", this.ext3);
		map.put("ext4", this.ext4);
		map.put("ext5", this.ext5);
		map.put("ext6", this.ext6);
		map.put("ext7", this.ext7);
		map.put("ext8", this.ext8);
		map.put("ext9", this.ext9);
		map.put("ext10", this.ext10);
		return map;
	}
}