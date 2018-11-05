package com.sinochem.crude.trade.blockchain.enums;

public enum AppointStatusEnums {

    NotAppoint("1", "待委托", "Not Appoint"),
    Comfirmed("2", "已确认", "Comfirmed"),
    Completed("3", "已完成", "Completed");

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

    AppointStatusEnums(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获取销售需求单状态
     */
    public static AppointStatusEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (AppointStatusEnums saleSheetStatusEnum : AppointStatusEnums.values()) {
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
