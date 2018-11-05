package com.sinochem.crude.trade.transaction.enums;

/**
 * 出口类型枚举
 * @author Yichen Zhao
 * date: 20180225
 */
public enum ExportTypeEnum {

    NORMAL_EXPORTS("1", "一般贸易出口", "Normal Exports"),
    PROCESSING_EXPORTS("2", "来料加工出口", "Processing Exports");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    ExportTypeEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
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
}
