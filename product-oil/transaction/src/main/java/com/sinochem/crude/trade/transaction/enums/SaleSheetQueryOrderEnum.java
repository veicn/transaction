package com.sinochem.crude.trade.transaction.enums;

/**
 * 销售单排序字段
 * @author Yichen Zhao
 * date: 20180307
 */
public enum SaleSheetQueryOrderEnum {

    POSTING_DATE("1", "发布时间", "Posting Date", "saleSheet.released_date"),
    QUANTITY("2", "数量", "Quantity", "productInfo.quantity"),
    DEADLINE("3", "投标截止时间", "Expired Time", "productInfo.deadline"),
    LAYCAN("4", "装港时间", "Laycan", "transportInfo.laycan_start_date");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名
     */
    private String zhName;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 列名
     */
    private String column;

    SaleSheetQueryOrderEnum(String code, String zhName, String enName, String column) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
        this.column = column;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }

    public String getColumn() {
        return column;
    }
}
