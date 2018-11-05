package com.sinochem.crude.trade.listed.enums;

/**
 * 贸易链的状态
 * Made By WangTing
 */
public enum TradingChainStatus {

    SAVED(1,"已保存"),
    PUBLISHED(2,"已发布"),
    CONFIRMING(3,"确认中"),
    CANCELLED(4,"已取消"),
    COMPLETED(5,"已完成"),
    OVERDUE(6,"已逾期");


    /**
     * 状态编号
     */
    Integer code;

    /**
     * 状态名称
     */
    String name;

    public static TradingChainStatus getTradingChainStatusByCode(Integer code){
        for (TradingChainStatus tradingChainStatus : TradingChainStatus.values()) {
            if(tradingChainStatus.getCode().equals(code)){
                return tradingChainStatus;
            }
        }
        return null;
    }

    private TradingChainStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
