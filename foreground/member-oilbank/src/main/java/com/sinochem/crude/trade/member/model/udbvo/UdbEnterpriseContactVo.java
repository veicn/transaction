package com.sinochem.crude.trade.member.model.udbvo;

import com.sinochem.crude.trade.member.domain.EnterpriseContact;

/**
 * 对接udb实体 企业联系人实体
 * Created by wgp on 2018/7/30.
 */
public class UdbEnterpriseContactVo {
    /**
     * 账户信息全局uid
     */
    private String accountUid;

    /**
     * 企业信息全局uid
     */
    private String enterpriseUid;

    /**
     * 企业联系人uid
     */
    private String entpContsUid;



    /**
     * 平台管理员证
     */
    private String adminCert;
    /**
     * 证件编码
     */
    private String certificateCode;
    /**
     * 	证件类型 1:身份证 2:护照 3:军官证
     */
    private String certificateType;
    /**
     * 联系人电话
     */
    private String mobile;

    /**
     *联系人姓名
     */
    private String name;

    /**
     * 企业联系人邮箱
     */
    private String email;

    /**
     * 网站站点
     */
    private String webSite;


    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }
    public String getEntpContsUid() {
        return entpContsUid;
    }

    public void setEntpContsUid(String entpContsUid) {
        this.entpContsUid = entpContsUid;
    }

    public String getAdminCert() {
        return adminCert;
    }

    public void setAdminCert(String adminCert) {
        this.adminCert = adminCert;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(String enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }


    /**
     * 转换为  udb企业联系人实体 的转换方法
     * @param contact
     * @param accountUid
     * @param enterpriseUid
     * @param webSite
     */
    public void convertToUdbVo(EnterpriseContact contact,String accountUid,String enterpriseUid,String webSite){
        this.setEntpContsUid(contact.getUdbUuid());
        this.setAccountUid(accountUid);
        this.setAdminCert(contact.getAdminCert());
        this.setEnterpriseUid(enterpriseUid);
        this.setMobile(contact.getMobile());
        this.setName(contact.getName());
        this.setEmail(contact.getMail());
        this.setWebSite(webSite);

    }

    /**
     * 转换为 业务系统企业联系人方法
     * @param memberId
     * @param enterpriseId
     * @return
     */
    public EnterpriseContact convertToEnterpriseContactVo(Long memberId,Long enterpriseId){
        EnterpriseContact contact = new EnterpriseContact();
        contact.setUdbUuid(this.getEntpContsUid());
        contact.setAdminCert(this.getAdminCert());
        contact.setMobile(this.getMobile());
        contact.setName(this.getName());
        contact.setMemberId(memberId);
        contact.setEnterpriseId(enterpriseId);
        contact.setMail(this.email);
        return contact;
    }

}
