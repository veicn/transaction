package com.sinochem.crude.trade.transaction.enums;

/**
 * 站内信，通知的枚举
 * @author Yichen Zhao
 * date: 20180318
 */
public enum NotificationEnum {

    //成品油销售资源提醒
    NEW_SALE_SHEET_PUBLISHED("pro_new_sale_sheet_published", "成品油销售资源提醒", ""),

    //新的成品油买家报价/投标提醒
    NEW_BIDDING_SHEET("pro_new_bidding_sheet", "成品油买家投标提醒", ""),

    //销售单快截止时成品油报价/投标提醒
    SALE_SHEET_APPROACH_DEADLINE("pro_sale_sheet_approach_deadline", "成品油报价/投标提醒", ""),

    //成品油截止提醒
    SALE_SHEET_REACH_DEADLINE("pro_sale_sheet_reach_deadline", "成品油截止提醒", ""),

    //招标/询盘逾期未操作提醒卖家
    NO_OPERATION_BEFORE_BID_OPENING_DATE("pro_no_operation_before_bid_opening_date", "逾期未操作提醒", ""),

    //成品油中标/成交提醒(给买家发)
    BIDDING_SHEET_WON("pro_bidding_sheet_won", "成品油中标提醒", ""),

    //成品油中标/成交提醒(给卖家发)
    BIDDING_SHEET_WON_SALER("pro_bidding_sheet_won_saler", "成品油中标提醒", ""),

    //成品油未中标/成交提醒（因未中标）
    BIDDING_SHEET_LOST("pro_bidding_sheet_lost", "成品油未中标提醒", ""),

    //成品油未中标/成交提醒（因销售单流标）
    SALE_SHEET_CANCELLED("pro_sale_sheet_cancelled", "成品油未中标提醒", ""),

    //成品油销售资源提醒
    NEW_SALE_SHEET_PUBLISHED_EN("pro_new_sale_sheet_published_en", "成品油销售资源提醒", ""),

    //新的成品油买家报价/投标提醒
    NEW_BIDDING_SHEET_EN("pro_new_bidding_sheet_en", "成品油买家投标提醒", ""),

    //销售单快截止时成品油报价/投标提醒
    SALE_SHEET_APPROACH_DEADLINE_EN("pro_sale_sheet_approach_deadline_en", "成品油报价/投标提醒", ""),

    //成品油截止提醒
    SALE_SHEET_REACH_DEADLINE_EN("pro_sale_sheet_reach_deadline_en", "成品油截止提醒", ""),

    //招标/询盘逾期未操作提醒卖家
    NO_OPERATION_BEFORE_BID_OPENING_DATE_EN("pro_no_operation_before_bid_opening_date_en", "逾期未操作提醒", ""),

    //成品油中标/成交提醒(给买家发)
    BIDDING_SHEET_WON_EN("pro_bidding_sheet_won_en", "成品油中标提醒", ""),

    //成品油中标/成交提醒(给卖家发)
    BIDDING_SHEET_WON_SALER_EN("pro_bidding_sheet_won_saler_en", "成品油中标提醒", ""),

    //成品油未中标/成交提醒（因未中标）
    BIDDING_SHEET_LOST_EN("pro_bidding_sheet_lost_en", "成品油未中标提醒", ""),

    //成品油未中标/成交提醒（因销售单流标）
    SALE_SHEET_CANCELLED_EN("pro_sale_sheet_cancelled_en", "成品油未中标提醒", "");
    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 中文标题
     */
    private String zhTitle;

    /**
     * 英文标题
     */
    private String enTitle;

    NotificationEnum(String templateName, String zhTitle, String enTitle) {
        this.templateName = templateName;
        this.zhTitle = zhTitle;
        this.enTitle = enTitle;
    }

    /** getters */
    public String getZhTitle() {
        return zhTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public String getTemplateName() {
        return templateName;
    }
}
