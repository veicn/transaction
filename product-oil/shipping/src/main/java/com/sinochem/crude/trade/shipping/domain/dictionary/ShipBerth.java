package com.sinochem.crude.trade.shipping.domain.dictionary;

/**
 * 泊位信息，包括需求单和报价单
 * @author Yichen Zhao
 * date: 20180225
 */
public class ShipBerth {

    /**
     * ID
     */
    private Integer berthId;

    /**
     * 港口的ID
     */
    private Integer shipPortId;

    /**
     * 泊位中文名称
     */
    private String zhName;

    /**
     * 泊位英文名称
     */
    private String enName;

    /**
     * 泊位吨位
     */
    private String berthTonnage;

    /**
     * 泊位吃水
     */
    private String berthDraft;

    /**
     * 适合船型
     */
    private String vesselType;

    /**
     * 泊位说明
     */
    private String remark;
}
