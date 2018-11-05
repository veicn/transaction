package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class Enterprise {

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
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 英文名称
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
	 * 组织机构代码图片ID
	 * （证件类型：ZZJGDMZ）
	 */
	private String orgCodeCert;

	/**
	 * 税务登记证件图片ID
	 * （证件类型：SWDJZ）
	 */
	private String taxRegCert;

	/**
	 * 营业执照图片ID
	 * （证件类型：YYZZ）
	 */
	private String businessLicenseCert;
	/**
	 * 社会信用证代码证图片ID
	 */
	private String socialCreditCert;

	/**
	 * 企业logo图片ID
	 * （证件类型：GSLOGO）
	 */
	private String epLogo;

	/**
	 * 创建时间
	 */
	private Date createTime;

	//类型 境内0  境外 1
	private Integer epType;

	//经营范围
	private String scope ;

	//是否使用社会信用统一证件
	private Boolean useSocialCreditCert;

	//社会信用代码
	private String socialCreditCode;

	//国家
	private String country;

	//国家编码
	private String countryCode;

	//注册证明材料
	private String registerCert;

	//银行证明材料
	private String bankCert;
	//银行证明类型
	private Integer bankCertType;

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
	 * 联系人
	 */
	private EnterpriseContact contact;
	/**
	 * 企业扩展信息
	 */
	private EnterpriseCrude enterpriseCrude;
	/**
	 * members表关联ID
	 */
	private Long memberId;


	private String userName;

	// 默认语言
	private String defaultLanguage;

	private EnterpriseCrude crude;

	private String email;

	public String getUdbUuid() {
		return udbUuid;
	}

	public void setUdbUuid(String udbUuid) {
		this.udbUuid = udbUuid;
	}

	private String udbUuid;



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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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

	public String getOrgCodeCert() {
		return orgCodeCert;
	}

	public void setOrgCodeCert(String orgCodeCert) {
		this.orgCodeCert = orgCodeCert;
	}

	public String getTaxRegCert() {
		return taxRegCert;
	}

	public void setTaxRegCert(String taxRegCert) {
		this.taxRegCert = taxRegCert;
	}

	public String getBusinessLicenseCert() {
		return businessLicenseCert;
	}

	public void setBusinessLicenseCert(String businessLicenseCert) {
		this.businessLicenseCert = businessLicenseCert;
	}

	public String getSocialCreditCert() {
		return socialCreditCert;
	}

	public void setSocialCreditCert(String socialCreditCert) {
		this.socialCreditCert = socialCreditCert;
	}

	public String getEpLogo() {
		return epLogo;
	}

	public void setEpLogo(String epLogo) {
		this.epLogo = epLogo;
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


	public String getAbbEnglishName() {
		return abbEnglishName;
	}

	public void setAbbEnglishName(String abbEnglishName) {
		this.abbEnglishName = abbEnglishName;
	}

	public EnterpriseCrude getCrude() {
		return crude;
	}

	public void setCrude(EnterpriseCrude crude) {
		this.crude = crude;
	}

	public Integer getEpType() {
		return epType;
	}

	public void setEpType(Integer epType) {
		this.epType = epType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegisterCert() {
		return registerCert;
	}

	public void setRegisterCert(String registerCert) {
		this.registerCert = registerCert;
	}

	public String getBankCert() {
		return bankCert;
	}

	public void setBankCert(String bankCert) {
		this.bankCert = bankCert;
	}

	public Integer getBankCertType() {
		return bankCertType;
	}

	public void setBankCertType(Integer bankCertType) {
		this.bankCertType = bankCertType;
	}

	public String getSocialCreditCode() {
		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}

	public Boolean getUseSocialCreditCert() {
		return useSocialCreditCert;
	}

	public void setUseSocialCreditCert(Boolean useSocialCreditCert) {
		this.useSocialCreditCert = useSocialCreditCert;
	}

	public EnterpriseContact getContact() {
		return contact;
	}

	public void setContact(EnterpriseContact contact) {
		this.contact = contact;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
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

	public EnterpriseCrude getEnterpriseCrude() {
		return enterpriseCrude;
	}

	public void setEnterpriseCrude(EnterpriseCrude enterpriseCrude) {
		this.enterpriseCrude = enterpriseCrude;
	}

	public void test(){

	}
}