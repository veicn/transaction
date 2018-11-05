package com.sinochem.crude.trade.info.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ColColumn implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;

	/** 专栏标题 */
	private String columnTitle;

	/** 专栏简介 */
	private String columnBrief;

	/** 专栏简介HTML */
	private String columnBriefHtml;

	/** 专栏封面 */
	private String columnCover;

	/** 专栏分类: 行业 */
	private String columnClassify;

	/** 专栏分类名 */
	private String columnClassifyName;

	/** 币种 RMB:人民币 USD:美元 EUR:欧元 */
	private String currency;

	/** 专栏价格 */
	private java.math.BigDecimal columnPrice;

	/** 作者ID */
	private String author;

	/** 作者名 */
	private String authorName;

	/** 状态 00:取消申请 01:审核不通过 10:申请中 20:审核通过 */
	private String status;

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	private String columnCyc;

	/** 专栏周期值 1:一个月 3:三个月 12:一年 */
	private String columnCycValue;

	/** 更新频率 1:每日 2:每周 3:每月 */
	private String frequency;

	/** 更新频率名 */
	private String frequencyValue;

	/** 更新量 */
	private Integer updateQuantity;

	/** 起始有效期 */
	private java.util.Date startEffectiveDate;

	/** 截止有效期 */
	private java.util.Date endEffectiveDate;

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	private String settleType;

	/** 最近更新日期 */
	private java.util.Date latestDate;

	/** 订阅数 */
	private Integer subscribeCount;

	/** 文章数 */
	private Integer articleCount;

	/** 审核人 */
	private String examineUser;

	/** 审核时间 */
	private java.util.Date examineDate;

	/** 审核不通过描述 */
	private String examineNoDesc;

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

	/** 专栏标题 */
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	/** 专栏标题 */
	public String getColumnTitle() {
		return this.columnTitle;
	}

	/** 专栏简介 */
	public void setColumnBrief(String columnBrief) {
		this.columnBrief = columnBrief;
	}

	/** 专栏简介 */
	public String getColumnBrief() {
		return this.columnBrief;
	}

	/** 专栏简介HTML */
	public void setColumnBriefHtml(String columnBriefHtml) {
		this.columnBriefHtml = columnBriefHtml;
	}

	/** 专栏简介HTML */
	public String getColumnBriefHtml() {
		return this.columnBriefHtml;
	}

	/** 专栏封面 */
	public void setColumnCover(String columnCover) {
		this.columnCover = columnCover;
	}

	/** 专栏封面 */
	public String getColumnCover() {
		return this.columnCover;
	}

	/** 专栏分类: 行业 */
	public void setColumnClassify(String columnClassify) {
		this.columnClassify = columnClassify;
	}

	/** 专栏分类: 行业 */
	public String getColumnClassify() {
		return this.columnClassify;
	}

	/** 专栏分类名 */
	public void setColumnClassifyName(String columnClassifyName) {
		this.columnClassifyName = columnClassifyName;
	}

	/** 专栏分类名 */
	public String getColumnClassifyName() {
		return this.columnClassifyName;
	}

	/** 币种 RMB:人民币 USD:美元 EUR:欧元 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/** 币种 RMB:人民币 USD:美元 EUR:欧元 */
	public String getCurrency() {
		return this.currency;
	}

	/** 专栏价格 */
	public void setColumnPrice(java.math.BigDecimal columnPrice) {
		this.columnPrice = columnPrice;
	}

	/** 专栏价格 */
	public java.math.BigDecimal getColumnPrice() {
		return this.columnPrice;
	}

	/** 作者ID */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** 作者ID */
	public String getAuthor() {
		return this.author;
	}

	/** 作者名 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/** 作者名 */
	public String getAuthorName() {
		return this.authorName;
	}

	/** 状态 00:取消申请 01:审核不通过 10:申请中 20:审核通过 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 状态 00:取消申请 01:审核不通过 10:申请中 20:审核通过 */
	public String getStatus() {
		return this.status;
	}

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	public void setColumnCyc(String columnCyc) {
		this.columnCyc = columnCyc;
	}

	/** 专栏周期 1:一个月 3:三个月 12:一年 */
	public String getColumnCyc() {
		return this.columnCyc;
	}

	/** 专栏周期值 1:一个月 3:三个月 12:一年 */
	public void setColumnCycValue(String columnCycValue) {
		this.columnCycValue = columnCycValue;
	}

	/** 专栏周期值 1:一个月 3:三个月 12:一年 */
	public String getColumnCycValue() {
		return this.columnCycValue;
	}

	/** 更新频率 1:每日 2:每周 3:每月 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/** 更新频率 1:每日 2:每周 3:每月 */
	public String getFrequency() {
		return this.frequency;
	}

	/** 更新频率名 */
	public void setFrequencyValue(String frequencyValue) {
		this.frequencyValue = frequencyValue;
	}

	/** 更新频率名 */
	public String getFrequencyValue() {
		return this.frequencyValue;
	}

	/** 更新量 */
	public void setUpdateQuantity(Integer updateQuantity) {
		this.updateQuantity = updateQuantity;
	}

	/** 更新量 */
	public Integer getUpdateQuantity() {
		return this.updateQuantity;
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

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	/** 结算方式 10:周结 20:月结 30:季结 40:年结 */
	public String getSettleType() {
		return this.settleType;
	}

	/** 最近更新日期 */
	public void setLatestDate(java.util.Date latestDate) {
		this.latestDate = latestDate;
	}

	/** 最近更新日期 */
	public java.util.Date getLatestDate() {
		return this.latestDate;
	}

	/** 订阅数 */
	public void setSubscribeCount(Integer subscribeCount) {
		this.subscribeCount = subscribeCount;
	}

	/** 订阅数 */
	public Integer getSubscribeCount() {
		return this.subscribeCount;
	}

	/** 文章数 */
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	/** 文章数 */
	public Integer getArticleCount() {
		return this.articleCount;
	}

	/** 审核人 */
	public void setExamineUser(String examineUser) {
		this.examineUser = examineUser;
	}

	/** 审核人 */
	public String getExamineUser() {
		return this.examineUser;
	}

	/** 审核时间 */
	public void setExamineDate(java.util.Date examineDate) {
		this.examineDate = examineDate;
	}

	/** 审核时间 */
	public java.util.Date getExamineDate() {
		return this.examineDate;
	}

	/** 审核不通过描述 */
	public void setExamineNoDesc(String examineNoDesc) {
		this.examineNoDesc = examineNoDesc;
	}

	/** 审核不通过描述 */
	public String getExamineNoDesc() {
		return this.examineNoDesc;
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
		map.put("columnTitle", this.columnTitle);
		map.put("columnBrief", this.columnBrief);
		map.put("columnBriefHtml", this.columnBriefHtml);
		map.put("columnCover", this.columnCover);
		map.put("columnClassify", this.columnClassify);
		map.put("columnClassifyName", this.columnClassifyName);
		map.put("currency", this.currency);
		map.put("columnPrice", this.columnPrice);
		map.put("author", this.author);
		map.put("authorName", this.authorName);
		map.put("status", this.status);
		map.put("columnCyc", this.columnCyc);
		map.put("columnCycValue", this.columnCycValue);
		map.put("frequency", this.frequency);
		map.put("frequencyValue", this.frequencyValue);
		map.put("updateQuantity", this.updateQuantity);
		map.put("startEffectiveDate", this.startEffectiveDate);
		map.put("endEffectiveDate", this.endEffectiveDate);
		map.put("settleType", this.settleType);
		map.put("latestDate", this.latestDate);
		map.put("subscribeCount", this.subscribeCount);
		map.put("articleCount", this.articleCount);
		map.put("examineUser", this.examineUser);
		map.put("examineDate", this.examineDate);
		map.put("examineNoDesc", this.examineNoDesc);
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