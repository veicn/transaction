package com.sinochem.crude.trade.transaction.enums;

/**
 * 消息枚举
 * @author Yichen Zhao
 * date: 20180307
 */
public enum MessageEnum {

    CONFIRM_BIDDING_SHEET_SUCCESS("1", "确认中标成功", "Bidding sheet successfully confirmed"),
    CONFIRM_BIDDING_SHEET_ERROR("2", "确认中标失败", "Bidding sheet confirmation failed"),

    CANCEL_SALE_SHEET_SUCCESS("3", "已成功流标", "Sale Sheet cancelled successfully"),
    CANCEL_DEMAND_SHEET_SUCCESS("3", "已成功流标", "Procurement  Sheet cancelled successfully"),
    CANCEL_SALE_SHEET_ERROR("4", "流标失败", "Failed to cancel sale sheet");

    /**
     * 消息代码
     */
    private String code;

    /**
     * 中文信息
     */
    private String zhMessage;

    /**
     * 英文信息
     */
    private String enMessage;

    MessageEnum(String code, String zhMessage, String enMessage) {
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public String getZhMessage() {
        return zhMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }
}
