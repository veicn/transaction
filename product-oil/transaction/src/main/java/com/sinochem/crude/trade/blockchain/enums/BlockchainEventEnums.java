package com.sinochem.crude.trade.blockchain.enums;

public enum BlockchainEventEnums {

    E1001("1001","删除商检报告正本",""),
    E1002("1002","上传商检报告正本",""),
    E1003("1003","更新商检数据",""),
    E1004("1004","提交商检数据",""),
    E2001("2001","更新报关随附单据",""),
    E2002("2002","上传报关随附单据",""),
    E2003("2003","更新报关单信息",""),
    E2004("2004","提交报关单信息",""),
    E3001("3001","删除提单正本",""),
    E3002("3002","上传提单正本",""),
    E3003("3003","更新提单信息",""),
    E3004("3004","提交提单信息",""),
    E3005("3005","删除装港事实记录正本",""),
    E3006("3006","上传装港事实记录正本",""),
    E3007("3007","更新装港事实记录",""),
    E3008("3008","提交装港事实记录",""),
    E4001("4001","删除出口代理合同正本",""),
    E4002("4002","上传出口代理合同正本",""),
    E4003("4003","删除外贸合同正本",""),
    E4004("4004","上传外贸合同正本",""),
    E4005("4005","更新合同号",""),
    E4006("4006","创建合同号",""),
    E4007("4007","交易达成",""),
    E5001("5001","委托报关",""),
    E5002("5002","委托商检",""),
    E5003("5003","委托船代","");

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

    BlockchainEventEnums(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }


    public static BlockchainEventEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (BlockchainEventEnums blockchainEventEnums : BlockchainEventEnums.values()) {
            if (blockchainEventEnums.getCode().equals(code)) {
                return blockchainEventEnums;
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
