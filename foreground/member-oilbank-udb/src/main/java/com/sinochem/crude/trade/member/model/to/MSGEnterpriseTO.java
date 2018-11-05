package com.sinochem.crude.trade.member.model.to;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.model.EnterpriseRegisterForm;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class_name: MSGEnterpriseTO
 * package: com.sinochem.open.vo.member
 * describe: MIC接口企业信息
 * create_user: wj
 * create_date: 2018/05/14
 * create_time: 21:47 PM
 **/
public class MSGEnterpriseTO implements Serializable {

	private static final long serialVersionUID = -4804420341231118962L;
	/** 企业唯一标识**/
	private String uid;
	/** 企业名称**/
	private String name;
	/** 企业全称**/
	private String fullName;
	/** 英文名称**/
	private String englishName;
	/** 英文地址**/
	private String englishAddress;
	/** 企业logo**/
	private String logo;
	/** 详细地址**/
	private String addressDetail;
	/** 工商执照号码**/
	private String businessLicenseCode;
	/** 工商执照附件**/
	private String businessLicenseFile;
	/** 证件类型 "ALL_IN_ONE 多证合一 、NORMAL 普通证件 **/
	private String fileType;
	/** 企业信用号**/
	private String creditCode;
	/** 企业信用证附件**/
	private String creditFile;
	/** 组织机构代码**/
	private String organizationCode;
	/** 组织机构证件附件**/
	private String organizationFileName;
	/** 税务登记号码**/
	private String taxCode;
	/** 税务登记附件**/
	private String taxCodeFile;
	/** 授权委托书附件**/
	private String authorizationFile;
	/** 邮箱**/
	private String email;
	/** 电话**/
	private String tel;
	/** 传真**/
	private String fax;
	/** 邮政编码**/
	private String postCode;
	/** 联系人姓名**/
	private String contactName;
	/** 联系人电话**/
	private String contactMobile;
	/** 联系人证件类型 "ID 身份证,PASSPORT 护照 **/
	private String contactCertificateType;
	/** 联系人证件编码**/
	private String contactCertificateCode;
	/** 法人姓名**/
	private String legalName;
	/** 企业所属类型  字符串（1工厂,2贸易商,3个体）**/
	private String enterpriseType;
	/** 开票银行**/
	private String billingBankName;
	/** 开户银行账号**/
	private String billingBankAccount;
	/** 开票电话 **/
	private String contactTelephone;
	/** 开票地址**/
	private String invoiceAddress;
	/** 企业注册时间**/
	private Date registerTime;
	/** 是否买家 （0否 1是）**/
	private String isBuyer;
	/** 是否卖家 （0否 1是）**/
	private String isSeller;
	/** 审核状态 审核状态(0草稿 1待审核 2审核不通过 3审核通过（待激活） 4正常 5禁用) **/
	private String auditStatus;
	/** 更新数据来源 网站标识(ec 壹化网 op 成品油采购,logistics 物流) **/
	private String updateSource;
	/** 修改人**/
	private String updateUser;
	/** 修改时间**/
	private Date updateTime;
	/** 创建数据来源 网站标识(ec 壹化网， nec 新壹化网， op 成品油采购,logistics 物流) **/
	private String createSource;
	/** 创建人**/
	private String createUser;
	/** 创建时间**/
	private Date createTime;
	/** 激活状态 （0待激活 1已激活）**/
	private String isActive;
	/** 企业类型 0-第三方;1-塑料；2-化销； 4、第三方 **/
	private String erpId;
	/** 删除标志 0 正常,1 删除**/
	private String delFlg;

	public MSGEnterpriseTO(){}

	public MSGEnterpriseTO(Enterprise enterprise, EnterpriseCrude enterpriseCrude,URLBroker uploadServer,String mobile){
		Date now = new Date();
		this.uid = enterprise.getCode();
		this.name = enterprise.getName();
		this.fullName = enterprise.getFullName();
		this.addressDetail = enterpriseCrude.getAddressDetail();
		this.fileType = "ALL_IN_ONE";
		if(enterprise.getUseSocialCreditCert() != null && enterprise.getUseSocialCreditCert()){ //是否三证合一  1-是 0-否
			this.creditCode = enterprise.getSocialCreditCode();
			if(StringUtils.isNotEmpty(enterprise.getBusinessLicenseCert())){
				this.creditFile = uploadServer.get("/img/"+enterprise.getBusinessLicenseCert()+".img").toString();
			}
		}else{
			this.creditCode = enterprise.getLicenseNo();
			if(StringUtils.isNotEmpty(enterprise.getBusinessLicenseCert())){
				this.creditFile = uploadServer.get("/img/"+enterprise.getBusinessLicenseCert()+".img").toString();
			}
		}
		if(StringUtils.isNotEmpty(enterpriseCrude.getYihuaRegisterFile())){
			this.authorizationFile = uploadServer.get("/fs/"+enterpriseCrude.getYihuaRegisterFile()+".img").toString();
		}
		this.email = enterpriseCrude.getEmail();
		this.contactName = enterprise.getContact().getName();
		this.contactMobile = enterprise.getContact().getMobile();
		this.billingBankName = enterpriseCrude.getBillingBankName();
		this.billingBankAccount = enterpriseCrude.getBillingBankAccount();//待定
		this.contactTelephone = enterpriseCrude.getBillingMobile();
		this.invoiceAddress = enterpriseCrude.getBillingAddress();
		this.registerTime = now;
		this.isBuyer = "0";
		this.isSeller = "0";
		this.auditStatus = "1";
		this.updateUser = mobile;
		this.updateSource = "mycrudeoil";
		this.updateTime = now;
		this.createUser = mobile;
		this.createSource = "mycrudeoil";
		this.createTime = now;
		this.isActive = "0";
		this.erpId = "4";
		this.delFlg = "0";
		this.legalName = enterpriseCrude.getLegalName();
	}



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getEnglishAddress() {
		return englishAddress;
	}

	public void setEnglishAddress(String englishAddress) {
		this.englishAddress = englishAddress;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getBusinessLicenseCode() {
		return businessLicenseCode;
	}

	public void setBusinessLicenseCode(String businessLicenseCode) {
		this.businessLicenseCode = businessLicenseCode;
	}

	public String getBusinessLicenseFile() {
		return businessLicenseFile;
	}

	public void setBusinessLicenseFile(String businessLicenseFile) {
		this.businessLicenseFile = businessLicenseFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCreditFile() {
		return creditFile;
	}

	public void setCreditFile(String creditFile) {
		this.creditFile = creditFile;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationFileName() {
		return organizationFileName;
	}

	public void setOrganizationFileName(String organizationFileName) {
		this.organizationFileName = organizationFileName;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxCodeFile() {
		return taxCodeFile;
	}

	public void setTaxCodeFile(String taxCodeFile) {
		this.taxCodeFile = taxCodeFile;
	}

	public String getAuthorizationFile() {
		return authorizationFile;
	}

	public void setAuthorizationFile(String authorizationFile) {
		this.authorizationFile = authorizationFile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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

	public String getContactCertificateType() {
		return contactCertificateType;
	}

	public void setContactCertificateType(String contactCertificateType) {
		this.contactCertificateType = contactCertificateType;
	}

	public String getContactCertificateCode() {
		return contactCertificateCode;
	}

	public void setContactCertificateCode(String contactCertificateCode) {
		this.contactCertificateCode = contactCertificateCode;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
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

	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getIsBuyer() {
		return isBuyer;
	}

	public void setIsBuyer(String isBuyer) {
		this.isBuyer = isBuyer;
	}

	public String getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(String isSeller) {
		this.isSeller = isSeller;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUpdateSource() {
		return updateSource;
	}

	public void setUpdateSource(String updateSource) {
		this.updateSource = updateSource;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

}
