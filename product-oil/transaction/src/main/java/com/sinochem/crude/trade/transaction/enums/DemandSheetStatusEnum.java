package com.sinochem.crude.trade.transaction.enums;

/**
 * 销售需求单状态枚举
 * @author Yichen Zhao
 * date: 20180227
 */
public enum DemandSheetStatusEnum {

    SAVED("1", "已保存", "Saved"),
    RELEASED("2", "已发布", "Released"),
    CANCELLED("3", "已作废", "Cancelled"),
    COMPLETED("4", "已成交", "Completed"),
    EXPIRED("5", "已逾期", "Expired");

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

    DemandSheetStatusEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取销售需求单状态
     */
    public static DemandSheetStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (DemandSheetStatusEnum saleSheetStatusEnum : DemandSheetStatusEnum.values()) {
            if (saleSheetStatusEnum.getCode().equals(code)) {
                return saleSheetStatusEnum;
            }
        }

        return null;
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
