package com.sinochem.crude.trade.member.domain;

public class EnterpriseContact {
    private Long id;

    private String udbUuid;



    private Integer gender;

    private String name;

    private String mobile;

    private String phone;

    private String mail;

    private String address;

    private String accountCert;

    private String adminCert;

    private Long enterpriseId;

    private Long memberId;

    public String getUdbUuid() {
        return udbUuid;
    }

    public void setUdbUuid(String udbUuid) {
        this.udbUuid = udbUuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAccountCert() {
        return accountCert;
    }

    public void setAccountCert(String accountCert) {
        this.accountCert = accountCert == null ? null : accountCert.trim();
    }

    public String getAdminCert() {
        return adminCert;
    }

    public void setAdminCert(String adminCert) {
        this.adminCert = adminCert == null ? null : adminCert.trim();
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

}