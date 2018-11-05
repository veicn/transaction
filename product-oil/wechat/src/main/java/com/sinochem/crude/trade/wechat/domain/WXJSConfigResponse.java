package com.sinochem.crude.trade.wechat.domain;

/**
 * @Author: fengzk
 * @CreateDate: 2018/5/16 9:58
 * @Version: [v1.0]
 */
public class WXJSConfigResponse {
    private String timestamp;
    private String nonceStr;
    private String signature;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    private String appId;
}
