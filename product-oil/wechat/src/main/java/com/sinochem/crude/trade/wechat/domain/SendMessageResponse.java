package com.sinochem.crude.trade.wechat.domain;

public class SendMessageResponse {
    private String succeednum;
    private String failnum;

    public String getSucceednum() {
        return succeednum;
    }

    public void setSucceednum(String succeednum) {
        this.succeednum = succeednum;
    }

    public String getFailnum() {
        return failnum;
    }

    public void setFailnum(String failnum) {
        this.failnum = failnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
