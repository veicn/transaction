package com.sinochem.crude.trade.member.model.udbvo;

/**
 * 更新udb对象实体
 * Created by wgp on 2018/8/22.
 */
public class EnterpriseCredentialUdbVo {

    private String accountUid;
    private String[] enterpriseType;
    private String enterpriseUid;
    private String webSite;

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String[] getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String[] enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(String enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
