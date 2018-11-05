package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class EnterpriseCrudeLog {
	/**
	 * 企业编号
	 */
	private Long id;

    private Long enterpriseId;
	// 关联企业会员Id
	private Long memberId;
    // 法人姓名
    private String legalName;

    // 法人证件类型
    private String legalCertificateType;
    // 法人证件号码
    private String legalCertificateCode;
    // 电话
    private String tel;
    // 传真
    private String fax;
    // email
    private String email;
    // 网址
    private String website;

    // 企业注册时间
    private Date registerTime;

    // 注册资本
    private Long registerFund;

    // 主营产品
    private String mainProducts;


    // 所在国家
    private String countryCode;

    // 所在省
    private String provinceCode;

   // 所在市
    private String cityCode;

    // 所在地区编码
    private String areaCode;

    // 详细地址
    private String addressDetail;

   // 邮政编码
    private String postCode;

    //注册来源
    private String registerResource;

    // 联系人姓名
    private String contactName;

    // 联系人电话
    private String contactMobile;

    // 纳税人类型
    private String taxManType;

    // 纳税人资格证书
    private String taxManFile;

   // 发票-开户银行
    private String billingBankName;
    // 发票-开户账号
    private String billingBankAccount;



    //删除标志
    private String delFlg;

    //创建时间
    private Date createTime;

    //创建人
    private Long createUser;

    //修改时间
    private Date updateTime;

    //修改人
    private Long updateUser;
    // 描述
    private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getLegalCertificateType() {
		return legalCertificateType;
	}
	public void setLegalCertificateType(String legalCertificateType) {
		this.legalCertificateType = legalCertificateType;
	}
	public String getLegalCertificateCode() {
		return legalCertificateCode;
	}
	public void setLegalCertificateCode(String legalCertificateCode) {
		this.legalCertificateCode = legalCertificateCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
	public Long getRegisterFund() {
		return registerFund;
	}
	public void setRegisterFund(Long registerFund) {
		this.registerFund = registerFund;
	}
	public String getMainProducts() {
		return mainProducts;
	}
	public void setMainProducts(String mainProducts) {
		this.mainProducts = mainProducts;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getRegisterResource() {
		return registerResource;
	}
	public void setRegisterResource(String registerResource) {
		this.registerResource = registerResource;
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
	public String getTaxManType() {
		return taxManType;
	}
	public void setTaxManType(String taxManType) {
		this.taxManType = taxManType;
	}
	public String getTaxManFile() {
		return taxManFile;
	}
	public void setTaxManFile(String taxManFile) {
		this.taxManFile = taxManFile;
	}
	public String getBillingBankName() {
		return billingBankName;
	}
	public void setBillingBankName(String billingBankName) {
		this.billingBankName = billingBankName;
	}
	public String getBillingBankAccount() {
		return billingBankAccount;
	}
	public void setBillingBankAccount(String billingBankAccount) {
		this.billingBankAccount = billingBankAccount;
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
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}