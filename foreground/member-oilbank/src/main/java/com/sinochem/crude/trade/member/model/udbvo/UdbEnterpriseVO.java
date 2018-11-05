package com.sinochem.crude.trade.member.model.udbvo;

import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/*
公司详情信息 vo
 */


public class UdbEnterpriseVO {

    /*账户信息全局uid",required = true*/
    private String accountUid;

    /*企业全局uuid*/
    private String enterpriseUid;
    /*企业名称"*/
    @Size(min = 2, max = 100)
    private String nameCh;

    /*企业全称" ,required = true*/
    @NotBlank
    @Size(min = 2, max = 200)
    private String fullNameCh;

    //英文全称
    private String fullNameEn;

    /*英文名称"*/
    @Size(min = 2, max = 128)
    private String nameEn;


    //银行证明材料
    private String bankCert;

    //银行证明材料类型
    private String bankCertType;

private String enterpriseCert;

    /*英文地址"*/
    @Size(min = 2, max = 255)
    private String addressDetailEn;

    /*企业logo*/
    private String logo;

    /*所在国家编码*/
    private String countryCode;

    /*所在省编码"*/
    private String provinceCode;

    /*所在市编码*/
    private String cityCode;

    /*所在地区编码*/
    private String areaCode;

    /*详细地址"*/
    @Size(min = 2, max = 512)
    private String addressDetailCh;

    /*工商执照号码*/
    private String businessLicenseCode;

    /*工商执照附件*/
    private String businessLicenseFile;

    /*证件类型/是否三证合一(1：是(是三证合一); 0：否(没有三证合一*/
    @Size(max = 1)
    private String fileType;

    /*统一企业信用代码"*/
    private String creditCode;

    /*企业信用证附件"*/
    private String creditFile;

    /*组织机构代码"*/
    private String organizationCode;

    /*组织机构证件附件"*/
    private String organizationFile;

    /*税务登记号码"*/
    private String taxCode;

    /*税务登记附件"*/
    private String taxCodeFile;

    /*邮箱"*/
    private String email;

    /*电话"*/
    private String tel;

    /*传真"*/
    private String fax;

    /*邮政编码"*/
    @Size(min = 2, max = 32)
    private String postCode;

    /*法人姓名"*/
    @Size(min = 2, max = 64)
    private String legalName;

    /*法人证件类型 1:身份证 2:护照 3:军官证"*/
    @Size(max = 1)
    private String legalCertificateType;

    /*法人证件号码"*/
    private String legalCertificateCode;

    /*注册资本"*/
    private String registerFund;

    /*主营产品"*/
    @Size(min = 2, max = 255)
    private String mainProductsCh;

    /*英文主营产品"*/
    @Size(min = 2, max = 255)
    private String mainProductsEn;

    /*注册区域(0:境内 1:境外)", required = true)*/
    @Size(max = 1)
    @NotBlank
    private String registerArea;

    /*企业注册时间"*/
    private Long registerTime;

    /*企业描述"*/
    @Size(min = 2, max = 255)
    private String descriptionCh;

    /*英文企业描述"*/
    @Size(min = 2, max = 255)
    private String descriptionEn;

    /*是否买家(0: 否；1: 是)*/
    @Size(max = 1)
    private String isBuyer;

    /*是否卖家(0: 否 1:是*/
    @Size(max = 1)
    private String isSeller;

    /*审核状态(0: 草稿 1: 待审核 2: 审核不通过；3: 审核通过(待激活; 4: 正常；5: 禁用)*/
    @Size(max = 1)
    private String auditStatus;

    /*审核所在平台,网站标识(参照码表)*/
    private String auditPlace;

    /*"企业类型"*/
    private String[] enterpriseType;

    private String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /*子系统标识*/
    private String webSite;

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(String enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public String getFullNameCh() {
        return fullNameCh;
    }

    public void setFullNameCh(String fullNameCh) {
        this.fullNameCh = fullNameCh;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getBankCert() {
        return bankCert;
    }

    public void setBankCert(String bankCert) {
        this.bankCert = bankCert;
    }

    public String getBankCertType() {
        return bankCertType;
    }

    public void setBankCertType(String bankCertType) {
        this.bankCertType = bankCertType;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getEnterpriseCert() {
        return enterpriseCert;
    }

    public void setEnterpriseCert(String enterpriseCert) {
        this.enterpriseCert = enterpriseCert;
    }

    public String getAddressDetailEn() {
        return addressDetailEn;
    }

    public void setAddressDetailEn(String addressDetailEn) {
        this.addressDetailEn = addressDetailEn;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getAddressDetailCh() {
        return addressDetailCh;
    }

    public void setAddressDetailCh(String addressDetailCh) {
        this.addressDetailCh = addressDetailCh;
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

    public String getOrganizationFile() {
        return organizationFile;
    }

    public void setOrganizationFile(String organizationFile) {
        this.organizationFile = organizationFile;
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

    public String getRegisterFund() {
        return registerFund;
    }

    public void setRegisterFund(String registerFund) {
        this.registerFund = registerFund;
    }

    public String getMainProductsCh() {
        return mainProductsCh;
    }

    public void setMainProductsCh(String mainProductsCh) {
        this.mainProductsCh = mainProductsCh;
    }

    public String getMainProductsEn() {
        return mainProductsEn;
    }

    public void setMainProductsEn(String mainProductsEn) {
        this.mainProductsEn = mainProductsEn;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public String getDescriptionCh() {
        return descriptionCh;
    }

    public void setDescriptionCh(String descriptionCh) {
        this.descriptionCh = descriptionCh;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
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

    public String getAuditPlace() {
        return auditPlace;
    }

    public void setAuditPlace(String auditPlace) {
        this.auditPlace = auditPlace;
    }

    public String[] getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String[] enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }


    /**
     * 只需要转换公司信息时，使用该方法
     * @param enterprise
     * @param webSite
     * @param accountUid
     */
    public void convertToUdbVo(Enterprise enterprise, String webSite, String accountUid) {
        this.setAccountUid(accountUid);
        this.setEnterpriseUid(enterprise.getUdbUuid());
        this.setFullNameCh(enterprise.getFullName());
        this.setFullNameEn(enterprise.getEnglishName());
        this.setRegisterArea(enterprise.getEpType()==null?null:enterprise.getEpType().toString());
        this.setWebSite(webSite);
        this.setBusinessLicenseCode(enterprise.getLicenseNo());
        this.setBusinessLicenseFile(enterprise.getBusinessLicenseCert());
        this.setCountryCode(enterprise.getCountryCode());
        this.setCreditCode(enterprise.getSocialCreditCode());
        this.setCreditFile(enterprise.getSocialCreditCert());
        this.setDescriptionCh(enterprise.getDescription());
        this.setDescriptionEn(enterprise.getDescription());
        this.setEmail(enterprise.getEmail());
        this.setLogo(enterprise.getEpLogo());
        this.setNameCh(enterprise.getName());
        this.setNameEn(enterprise.getAbbEnglishName());
        this.setOrganizationCode(enterprise.getOrganizationCode());
        this.setOrganizationFile(enterprise.getOrgCodeCert());
        this.setRegisterTime(enterprise.getRegisterTime()==null?null:enterprise.getRegisterTime().getTime());
        this.setTaxCode(enterprise.getTaxNo());
        this.setBankCert(enterprise.getBankCert());
        this.setBankCertType(enterprise.getBankCertType()==null?null:enterprise.getBankCertType().toString());
        this.setTaxCodeFile(enterprise.getTaxRegCert());
        this.setUpdateTime(String.valueOf(new Date().getTime()));
        this.setFileType(enterprise.getUseSocialCreditCert()==null || enterprise.getUseSocialCreditCert()==false?"0":"1");
//        审核状态(0: 草稿 1: 待审核 2: 审核不通过；3: 审核通过;)
        this.setAuditStatus("3");
    }


    /**
     * 只需要更新法人信息时  使用该方法
     * @param enterpriseCrude
     * @param webSite
     * @param accountUid
     * @param enterpriseUid
     */
    public void convertToEnterpriseCrudeUdbVo(EnterpriseCrude enterpriseCrude, String webSite, String accountUid,String enterpriseUid) {
        if(enterpriseCrude == null){
            return;
        }
        this.setAccountUid(accountUid);
        this.setEnterpriseUid(enterpriseUid);
        this.setAddressDetailEn(enterpriseCrude.getAddressDetail());
        this.setAddressDetailCh(enterpriseCrude.getAddressDetail());
        this.setAreaCode(enterpriseCrude.getAreaCode());
       this.setBusinessLicenseFile(enterpriseCrude.getBusinessLicenseFile());
        this.setCityCode(enterpriseCrude.getCityCode());
        this.setTel(enterpriseCrude.getContactMobile());
        this.setCountryCode(enterpriseCrude.getCountryCode());
        this.setEmail(enterpriseCrude.getEmail());
        this.setFax(enterpriseCrude.getFax());
        this.setLegalCertificateCode(enterpriseCrude.getLegalCertificateCode());
        this.setLegalCertificateType(enterpriseCrude.getLegalCertificateType());
        this.setLegalName(enterpriseCrude.getLegalName());
        this.setMainProductsEn(enterpriseCrude.getMainProducts());
        this.setMainProductsCh(enterpriseCrude.getMainProducts());
        this.setPostCode(enterpriseCrude.getPostCode());
        this.setProvinceCode(enterpriseCrude.getProvinceCode());
        this.setRegisterFund(enterpriseCrude.getRegisterFund()==null?null:enterpriseCrude.getRegisterFund().toString());
        this.setRegisterTime(enterpriseCrude.getRegisterTime()==null?null:enterpriseCrude.getRegisterTime().getTime());
        this.setWebSite(webSite);

    }


    /**
     * 需要同时插入法人信息和公司信息 使用该转换方法
     * @param enterprise
     * @param enterpriseCrude
     * @param webSite
     * @param accountUid
     * @param enterpriseUid
     */
    public void convertAllToUdbVo(Enterprise enterprise,EnterpriseCrude enterpriseCrude, String webSite, String accountUid,String enterpriseUid) {
        //同时插入时，公司的uuid为null
        this.convertToEnterpriseCrudeUdbVo(enterpriseCrude,webSite,accountUid,enterpriseUid);
        this.convertToUdbVo(enterprise,webSite,accountUid);
    }

    public EnterpriseCrude convertToEnterpriseCrudeVo(Long memberId,Long enterpriseId){
        EnterpriseCrude enterpriseCrude = new EnterpriseCrude();

        enterpriseCrude.setAddressDetail(this.getAddressDetailCh());

        enterpriseCrude.setAreaCode(this.getAreaCode());

        enterpriseCrude.setBusinessLicenseFile(this.getBusinessLicenseFile());
        enterpriseCrude.setCityCode(this.getCityCode());
        enterpriseCrude.setContactMobile(this.getTel());
        enterpriseCrude.setCountryCode(this.getCountryCode());
        enterpriseCrude.setEmail(this.getEmail());
        enterpriseCrude.setFax(this.getFax());
        enterpriseCrude.setLegalCertificateCode(this.getLegalCertificateCode());
        enterpriseCrude.setLegalCertificateType(this.getLegalCertificateType());
        enterpriseCrude.setLegalName(this.getLegalName());
        enterpriseCrude.setMainProducts(this.getMainProductsCh());
        enterpriseCrude.setPostCode(this.getPostCode());
        enterpriseCrude.setProvinceCode(this.getProvinceCode());
        enterpriseCrude.setRegisterFund(this.getRegisterFund()==null?null:Long.valueOf(this.getRegisterFund()));
        enterpriseCrude.setRegisterTime(this.getRegisterTime()==null?null:new Date(this.getRegisterTime()));
        enterpriseCrude.setUdbUuid(this.getEnterpriseUid());
        enterpriseCrude.setMemberId(memberId);
        enterpriseCrude.setEnterpriseId(enterpriseId);

        return enterpriseCrude;

    }

    /**
     * 转换为公司信息实体方法
     * @return
     */
    public Enterprise convertToEnterpriseVo() {
        Enterprise enterprise = new Enterprise();
        enterprise.setUdbUuid(this.getEnterpriseUid());
        //enterprise.setMemberId(0L);
        enterprise.setFullName(this.fullNameCh);

        enterprise.setEpType(this.registerArea==null ? null : Integer.valueOf(this.registerArea));
        enterprise.setLicenseNo(this.businessLicenseCode);
        enterprise.setBusinessLicenseCert(this.businessLicenseFile);
        enterprise.setCountryCode(this.countryCode);
        enterprise.setSocialCreditCode(this.creditCode);
        enterprise.setSocialCreditCert(this.creditFile);
        enterprise.setDescription(this.descriptionCh);
        enterprise.setDescription(this.descriptionEn);
        enterprise.setEmail(this.email);
        enterprise.setEpLogo(this.logo);
        enterprise.setName(this.nameCh);
        enterprise.setFullName(this.fullNameCh);
        enterprise.setAbbEnglishName(this.nameEn);
        enterprise.setEnglishName(this.fullNameEn);
        enterprise.setOrganizationCode(this.organizationCode);
        enterprise.setOrgCodeCert(this.organizationFile);
        enterprise.setRegisterTime(this.registerTime==null?null:new Date(this.registerTime));
        enterprise.setTaxNo(this.taxCode);
        enterprise.setTaxRegCert(this.taxCodeFile);
        enterprise.setBankCert(this.bankCert);
        enterprise.setBankCertType(this.bankCertType==null?null:new Integer(this.bankCertType));
        Date date =null;
        date = new Date(Long.valueOf(this.updateTime));
        enterprise.setUpdateTime(date);
        return enterprise;

    }


}