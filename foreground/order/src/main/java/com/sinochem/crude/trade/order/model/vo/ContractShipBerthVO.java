package com.sinochem.crude.trade.order.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import com.sinochem.crude.trade.order.util.DictUtils;

import java.math.BigDecimal;
import java.util.Map;

public class ContractShipBerthVO {
    /**
     * ID
     */
    private Long id;

    /**
     * 泊位名称
     */
    private String berthName;

    /**
     * 泊位等级
     */
    private String berthGrade;

    /**
     * 吃水限制
     */
    private BigDecimal draftLimitation;

    /**
     * 最大载重区间
     */
    private Integer carryingCapacityMax;

    /**
     * 最小载重
     */
    private Integer carryingCapacityMin;

    /**
     * 订单信息
     */
    private Long contractId;

    /**
     * 船型
     */
    private String shipType;
    private String shipTypeContent;

    /**
     * 泊位说明
     */
    private String berthDesc;

    /**
     * 转换成ContractShipBerthVO
     */
    public static void convertToContractShipBerthVO(
            ContractShipBerth contractShipBerth,
            ContractShipBerthVO contractShipBerthVO) {
        if (contractShipBerth == null) {
            return;
        }

        contractShipBerthVO.setId(contractShipBerth.getId());
        contractShipBerthVO.setBerthName(contractShipBerth.getBerthName());
        contractShipBerthVO.setBerthGrade(contractShipBerth.getBerthGrade().toString());
        contractShipBerthVO.setDraftLimitation(contractShipBerth.getDraftLimitation());
        contractShipBerthVO.setCarryingCapacityMax(contractShipBerth.getCarryingCapacityMax());
        contractShipBerthVO.setCarryingCapacityMin(contractShipBerth.getCarryingCapacityMin());
        contractShipBerthVO.setContractId(contractShipBerth.getContractId());

        Integer shipType = contractShipBerth.getShipType();
        if (shipType != null) {
            Map<Object, String> shipTypeMap = DictUtils.getShipTypes();
            contractShipBerthVO.setShipType(shipType.toString());
            contractShipBerthVO.setShipTypeContent(shipTypeMap.get(shipType).toString());
        }

        contractShipBerthVO.setBerthDesc(contractShipBerth.getBerthDesc());
    }

    /**
     * 转换为ContractShipBerth
     */
    public static void convertToContractShipBerth(
            ContractShipBerthVO contractShipBerthVO,
            ContractShipBerth contractShipBerth) {
        if (contractShipBerthVO == null) {
            return;
        }

        contractShipBerth.setId(contractShipBerthVO.getId());
        contractShipBerth.setBerthName(contractShipBerthVO.getBerthName());

        String berthGrade = contractShipBerthVO.getBerthGrade();
        if (!StringUtil.isEmpty(berthGrade)) {
            contractShipBerth.setBerthGrade(Integer.parseInt(berthGrade));
        }

        contractShipBerth.setDraftLimitation(contractShipBerthVO.getDraftLimitation());
        contractShipBerth.setCarryingCapacityMax(contractShipBerthVO.getCarryingCapacityMax());
        contractShipBerth.setCarryingCapacityMin(contractShipBerthVO.getCarryingCapacityMin());
        contractShipBerth.setContractId(contractShipBerthVO.getContractId());

        String shipType = contractShipBerthVO.getShipType();
        if (!StringUtil.isEmpty(shipType)) {
            contractShipBerth.setShipType(Integer.parseInt(shipType));
        }

        contractShipBerth.setBerthDesc(contractShipBerthVO.getBerthDesc());
    }

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBerthName() {
        return berthName;
    }

    public void setBerthName(String berthName) {
        this.berthName = berthName;
    }

    public String getBerthGrade() {
        return berthGrade;
    }

    public void setBerthGrade(String berthGrade) {
        this.berthGrade = berthGrade;
    }

    public BigDecimal getDraftLimitation() {
        return draftLimitation;
    }

    public void setDraftLimitation(BigDecimal draftLimitation) {
        this.draftLimitation = draftLimitation;
    }

    public Integer getCarryingCapacityMax() {
        return carryingCapacityMax;
    }

    public void setCarryingCapacityMax(Integer carryingCapacityMax) {
        this.carryingCapacityMax = carryingCapacityMax;
    }

    public Integer getCarryingCapacityMin() {
        return carryingCapacityMin;
    }

    public void setCarryingCapacityMin(Integer carryingCapacityMin) {
        this.carryingCapacityMin = carryingCapacityMin;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getBerthDesc() {
        return berthDesc;
    }

    public void setBerthDesc(String berthDesc) {
        this.berthDesc = berthDesc;
    }

    public String getShipTypeContent() {
        return shipTypeContent;
    }

    public void setShipTypeContent(String shipTypeContent) {
        this.shipTypeContent = shipTypeContent;
    }
}
