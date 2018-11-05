package com.sinochem.crude.trade.member.model.udbvo;

import com.sinochem.crude.trade.member.domain.EnterpriseCrude;

/**
 * Created by wgp on 2018/8/4.
 */
public class EnterpriseBankUdbVo {
    private String accountUid;
    private String entpBankUid;
    private String billingBankAccount;
    private String billingBankName;
    private String contactTelephone;
    private String enterpriseUid;
    private String invoiceAddress;
    private String webSite;

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }
    public String getEntpBankUid() {
        return entpBankUid;
    }

    public void setEntpBankUid(String entpBankUid) {
        this.entpBankUid = entpBankUid;
    }
    public String getBillingBankAccount() {
        return billingBankAccount;
    }

    public void setBillingBankAccount(String billingBankAccount) {
        this.billingBankAccount = billingBankAccount;
    }

    public String getBillingBankName() {
        return billingBankName;
    }

    public void setBillingBankName(String billingBankName) {
        this.billingBankName = billingBankName;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(String enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void convertToEnterpriseBankUdbVo(EnterpriseCrude enterpriseCrude,String accountUid,String enterpriseUid,String webSite){
        this.setAccountUid(accountUid);
        this.setEnterpriseUid(enterpriseUid);
        this.setEntpBankUid(enterpriseCrude.getUdbUuid());
        this.setInvoiceAddress(enterpriseCrude.getBillingAddress());
        this.setBillingBankAccount(enterpriseCrude.getBillingBankAccount());
        this.setBillingBankName(enterpriseCrude.getBillingBankName());
        this.setContactTelephone(enterpriseCrude.getBillingMobile());
        this.setWebSite(webSite);
    }
}
