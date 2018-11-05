package com.sinochem.crude.trade.listed.enums;

/**
 * 贸易链参与企业的操作枚举
 * Made By WangTing
 */
public enum TradingChainEnterpriseOperate {
    WITHDRAW("10","撤回"),
    CONFIRM("20","确认"),
    REJECT("30","驳回");

    private String code;

    private String operate;

    private TradingChainEnterpriseOperate(String code, String name) {
        this.code = code;
        this.operate = operate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
