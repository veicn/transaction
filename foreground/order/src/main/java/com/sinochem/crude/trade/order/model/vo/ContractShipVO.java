package com.sinochem.crude.trade.order.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.Constart;
import com.sinochem.crude.trade.order.domain.ContractShip;
import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;

/**
 * ContractShip的VO
 * @author Yichen Zhao
 * date: 20180125
 */
public class ContractShipVO {

    /**
     */
    private Long id;

    /**
     * 运输方式
     */
    private Integer transportModes;
    private String transportModesContent;

    /**
     * 采购单信息
     */
    private Long contractId;

    /**
     * 卸货港
     */
    private String dischargePort;

    /**
     * 装货港
     */
    private String shipmentPort;

    /**
     * 装货开始时间
     */
    private Date shipmentStartTime;
    private String shipmentStartTimeAsString;

    /**
     * 装货结束时间
     */
    private Date shipmentEndTime;
    private String shipmentEndTimeAsString;

    /**
     * 到货开始时间
     */
    private Date dischargeStartTime;
    private String dischargeStartTimeAsString;

    /**
     * 到货结束时间
     */
    private Date dischargeEndTime;
    private String dischargeEndTimeAsString;

    /**
     * 泊位信息
     */
    private List<ContractShipBerthVO> contractShipBerths;

    /**
     * 是否可以外轮靠岸
     */
    private Boolean abroad;

    /**
     * 船型
     */
    private Integer shipType;
    private String shipTypeAsString;

    /**
     * 创建人MemberID
     */
    private Long creater;

    /**
     * 创建时间
     */
    private Date createTime;
    private String createTimeAsString;

    /**
     * 修改人
     */
    private Long updater;

    /**
     * 修改时间
     */
    private Date updateTime;
    private String updateTimeAsString;

    /**
     * 转换为ContractShipVO
     */
    public static void convertToContractShipVO(
            ContractShip contractShip,
            ContractShipVO contractShipVO) {
        if (contractShip != null) {
           // return;
        }

        Map<Object, String> shipTypeMap;

        contractShipVO.setId(contractShip.getId());
        contractShipVO.setTransportModes(contractShip.getTransportModes());
        contractShipVO.setContractId(contractShip.getContractId());
        contractShipVO.setDischargePort(contractShip.getDischargePort());
        if(StringUtils.isNotBlank(contractShip.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(contractShip.getDischargePort())){
			String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(contractShip.getDischargePort());
			String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
			contractShipVO.setDischargePort(unLoadPort);
		}
        contractShipVO.setShipmentPort(contractShip.getShipmentPort());

        Date shipmentStartTime = contractShip.getShipmentStartTime();
        if (shipmentStartTime != null) {
            contractShipVO.setShipmentStartTime(shipmentStartTime);
            contractShipVO.setShipmentStartTimeAsString(DateUtil.formatDate(shipmentStartTime));
        }

        Date shipmentEndTime = contractShip.getShipmentEndTime();
        if (shipmentEndTime != null) {
            contractShipVO.setShipmentEndTime(shipmentEndTime);
            contractShipVO.setShipmentEndTimeAsString(DateUtil.formatDate(shipmentEndTime));
        }

        Date dischargeStartTime = contractShip.getDischargeStartTime();
        if (dischargeStartTime != null) {
            contractShipVO.setDischargeStartTime(dischargeStartTime);
            contractShipVO.setDischargeStartTimeAsString(DateUtil.formatDate(dischargeStartTime));
        }

        Date dischargeEndTime = contractShip.getDischargeEndTime();
        if (dischargeEndTime != null) {
            contractShipVO.setDischargeEndTime(dischargeEndTime);
            contractShipVO.setDischargeEndTimeAsString(DateUtil.formatDate(dischargeEndTime));
        }

        List<ContractShipBerth> contractShipBerthList = contractShip.getContractShipBerths();
        if (contractShipBerthList != null && contractShipBerthList.size() != 0) {
            List<ContractShipBerthVO> contractShipBerthVOList = new ArrayList<>();

            for (ContractShipBerth contractShipBerth : contractShipBerthList) {
                ContractShipBerthVO contractShipBerthVO = new ContractShipBerthVO();
                ContractShipBerthVO.convertToContractShipBerthVO(contractShipBerth, contractShipBerthVO);
                contractShipBerthVOList.add(contractShipBerthVO);
            }

            contractShipVO.setContractShipBerths(contractShipBerthVOList);
        }

        contractShipVO.setAbroad(contractShip.isAbroad());

        Integer shipType = contractShip.getShipType();
        if (shipType != null) {
            contractShipVO.setShipType(shipType);

            shipTypeMap = DictUtils.getShipTypes();
            if(shipType != 0){
            	contractShipVO.setShipTypeAsString(shipTypeMap.get(shipType).toString());
            }
        }

        contractShipVO.setCreater(contractShip.getCreater());

        Date createTime = contractShip.getCreateTime();
        if (createTime != null) {
            contractShipVO.setCreateTime(createTime);
            contractShipVO.setCreateTimeAsString(DateUtil.formatDate(createTime));
        }

        contractShipVO.setUpdater(contractShip.getUpdater());

        Date updateTime = contractShip.getUpdateTime();
        if (updateTime != null) {
            contractShipVO.setUpdateTime(updateTime);
            contractShipVO.setUpdateTimeAsString(DateUtil.formatDate(updateTime));
        }
    }

    /**
     * 转换成ContractShip
     */
    public static void convertToContractShip(
            ContractShipVO contractShipVO,
            ContractShip contractShip) {
        if (contractShipVO == null) {
            return;
        }

        contractShip.setId(contractShipVO.getId());
        contractShip.setTransportModes(contractShipVO.getTransportModes());
        contractShip.setContractId(contractShipVO.getContractId());
        contractShip.setShipmentPort(contractShipVO.getShipmentPort());
        contractShip.setDischargePort(contractShipVO.getDischargePort());
        contractShip.setShipmentStartTime(contractShipVO.getShipmentStartTime());
        contractShip.setShipmentEndTime(contractShipVO.getShipmentEndTime());
        contractShip.setDischargeStartTime(contractShipVO.getDischargeStartTime());
        contractShip.setDischargeEndTime(contractShipVO.getDischargeEndTime());

        List<ContractShipBerthVO> contractShipBerthVOList = contractShipVO.getContractShipBerths();
        if (contractShipBerthVOList != null && contractShipBerthVOList.size() != 0) {
            List<ContractShipBerth> contractShipBerthList = new ArrayList<>();

            for (ContractShipBerthVO contractShipBerthVO : contractShipBerthVOList) {
                ContractShipBerth contractShipBerth = new ContractShipBerth();
                ContractShipBerthVO.convertToContractShipBerth(contractShipBerthVO, contractShipBerth);
                contractShipBerthList.add(contractShipBerth);
            }

            contractShip.setContractShipBerths(contractShipBerthList);
        }

        Boolean aboard = contractShipVO.getAbroad();
        if (aboard != null) {
            contractShip.setAbroad(aboard);
        }

        contractShip.setShipType(contractShipVO.getShipType());
        contractShip.setCreater(contractShipVO.getCreater());
        contractShip.setCreateTime(contractShipVO.getCreateTime());
        contractShip.setUpdater(contractShipVO.getUpdater());
        contractShip.setUpdateTime(contractShipVO.getUpdateTime());
    }

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransportModes() {
        return transportModes;
    }

    public void setTransportModes(Integer transportModes) {
        this.transportModes = transportModes;
    }

    public String getTransportModesContent() {
        return transportModesContent;
    }

    public void setTransportModesContent(String transportModesContent) {
        this.transportModesContent = transportModesContent;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public Date getShipmentStartTime() {
        return shipmentStartTime;
    }

    public void setShipmentStartTime(Date shipmentStartTime) {
        this.shipmentStartTime = shipmentStartTime;
    }

    public String getShipmentStartTimeAsString() {
        return shipmentStartTimeAsString;
    }

    public void setShipmentStartTimeAsString(String shipmentStartTimeAsString) {
        this.shipmentStartTimeAsString = shipmentStartTimeAsString;
    }

    public Date getShipmentEndTime() {
        return shipmentEndTime;
    }

    public void setShipmentEndTime(Date shipmentEndTime) {
        this.shipmentEndTime = shipmentEndTime;
    }

    public String getShipmentEndTimeAsString() {
        return shipmentEndTimeAsString;
    }

    public void setShipmentEndTimeAsString(String shipmentEndTimeAsString) {
        this.shipmentEndTimeAsString = shipmentEndTimeAsString;
    }

    public Date getDischargeStartTime() {
        return dischargeStartTime;
    }

    public void setDischargeStartTime(Date dischargeStartTime) {
        this.dischargeStartTime = dischargeStartTime;
    }

    public String getDischargeStartTimeAsString() {
        return dischargeStartTimeAsString;
    }

    public void setDischargeStartTimeAsString(String dischargeStartTimeAsString) {
        this.dischargeStartTimeAsString = dischargeStartTimeAsString;
    }

    public Date getDischargeEndTime() {
        return dischargeEndTime;
    }

    public void setDischargeEndTime(Date dischargeEndTime) {
        this.dischargeEndTime = dischargeEndTime;
    }

    public String getDischargeEndTimeAsString() {
        return dischargeEndTimeAsString;
    }

    public void setDischargeEndTimeAsString(String dischargeEndTimeAsString) {
        this.dischargeEndTimeAsString = dischargeEndTimeAsString;
    }

    public List<ContractShipBerthVO> getContractShipBerths() {
        return contractShipBerths;
    }

    public void setContractShipBerths(List<ContractShipBerthVO> contractShipBerths) {
        this.contractShipBerths = contractShipBerths;
    }

    public Boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(Boolean abroad) {
        this.abroad = abroad;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public String getShipTypeAsString() {
        return shipTypeAsString;
    }

    public void setShipTypeAsString(String shipTypeAsString) {
        this.shipTypeAsString = shipTypeAsString;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeAsString() {
        return createTimeAsString;
    }

    public void setCreateTimeAsString(String createTimeAsString) {
        this.createTimeAsString = createTimeAsString;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeAsString() {
        return updateTimeAsString;
    }

    public void setUpdateTimeAsString(String updateTimeAsString) {
        this.updateTimeAsString = updateTimeAsString;
    }
}
