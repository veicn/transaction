package com.sinochem.crude.trade.transaction.enums;

/**
 * 合约单状态枚举
 * @author Yichen Zhao
 * date: 20180227
 */
public enum ContractSheetStatusEnum {

    CONFIRMED("1", "已确认", "Confirmed"),
    UNDELIVERED("2", "待发货", "Undelivered"),
    COMPLETED("3", "已完成", "Completed");

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

    ContractSheetStatusEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /**
     * 根据代码获得合约单状态
     */
    public static ContractSheetStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (ContractSheetStatusEnum contractSheetStatusEnum : ContractSheetStatusEnum.values()) {
            if (contractSheetStatusEnum.getCode().equals(code)) {
                return contractSheetStatusEnum;
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
