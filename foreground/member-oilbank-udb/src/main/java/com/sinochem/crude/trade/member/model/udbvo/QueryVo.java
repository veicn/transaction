package com.sinochem.crude.trade.member.model.udbvo;

/**
 * Created by wgp on 2018/7/25.
 */
public class QueryVo {
    private String enterpriseUid;
    private String accountUid;
    private String webSite;

    public String getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(String enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;

    }
    //    http://udb.sinochem.tech/udb/api/v1/enterprises/datails?enterpriseUid=2349452389155938335&webSite=crudeoil&accountUid=2349446066964078603

}
