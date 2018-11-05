package com.sinochem.crude.trade.member.remote.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/22
 */
public class EnterpriseVo implements Serializable{

	/**
	 * 企业编号
	 */
	private Long id;

	/**
	 * 企业UUID
	 */
	private String code;

	/**
	 * 企业名称
	 */
	private String name;

	/**
	 * 全名
	 */
	private String fullName;

	/**
	 * 英文名
	 */
	private String englishName;

	/**
	 * 英文缩写名
	 */
	private String abbEnglishName;

	/**
	 * 组织机构证号
	 */
	private String organizationCode;

	/**
	 * 税号
	 */
	private String taxCode;

	/**
	 * 工商执照证件号码
	 */
	private String licenseNo;

	/**
	 * 税务登记证件
	 */
	private String taxNo;

	/**
	 * 注册时间
	 */
	private Date registerTime;

	/**
	 * 删除标记
	 */
	private String delFlg;

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
	 * 描述
	 */
	private String description;

	/**
	 * 企业logo
	 */
	private String epLogo;

	/**
	 * members表关联ID
	 */
	private Long memberId;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 企业资质
	 * 1-- 炼厂 2-- 贸易商 3-- 商检 4-- 船东 5-- 船经纪
	 * 6-- 船代 7-- 转租船东 8-- 银行 9-- 期货经纪商 10-- 原油供应商
	 */
	private String[] credentialsCode;
	
	/**
	 * 详细地址
	 */
	private String addressDetail;

	private String contactName;

	private String contactMobile;

	private String contactPhone;

	private String contactMail;

	private String contactAddress;

	private String country;

	private String defaultLanguage;

	//经营范围
	private String scope;

	private Integer eptype;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getEptype() {
		return eptype;
	}

	public void setEptype(Integer eptype) {
		this.eptype = eptype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getAbbEnglishName() {
		return abbEnglishName;
	}

	public void setAbbEnglishName(String abbEnglishName) {
		this.abbEnglishName = abbEnglishName;
	}

	public String getEpLogo() {
		return epLogo;
	}

	public void setEpLogo(String epLogo) {
		this.epLogo = epLogo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String[] getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(String[] credentialsCode) {
		this.credentialsCode = credentialsCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
}
