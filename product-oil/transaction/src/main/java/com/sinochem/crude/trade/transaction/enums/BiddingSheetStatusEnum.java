package com.sinochem.crude.trade.transaction.enums;

/**
 * 报价单状态
 * @author Yichen Zhao
 * date: 20180227
 */
public enum BiddingSheetStatusEnum {

    RELEASED("1", "已发布", "Released"),
    CANCELLED("2", "已作废", "Cancelled"),
    WON("3", "已中标", "Won"),
    LOST("4", "未中标", "Lost");

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

    BiddingSheetStatusEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取报价单状态
     */
    public static BiddingSheetStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (BiddingSheetStatusEnum biddingSheetStatusEnum : BiddingSheetStatusEnum.values()) {
            if (biddingSheetStatusEnum.getCode().equals(code)) {
                return biddingSheetStatusEnum;
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
