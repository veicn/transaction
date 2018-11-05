package com.sinochem.crude.trade.member.model.udbvo;

/**
 * Created by wgp on 2018/7/25.
 */
public class EnterpriseDetailUdbVo {
    private String accountUid;
    private String addressDetailCh;
    private String fullName;
    private String registerArea;
    private String webSite;

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getAddressDetailCh() {
        return addressDetailCh;
    }

    public void setAddressDetailCh(String addressDetailCh) {
        this.addressDetailCh = addressDetailCh;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
