package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;

public class DemandShipVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;


    /**
     * 运输方式
     */
    private Integer transportModes;

    /**
     * 运输方式内容
     */
    private String transportModesContent;

    /**
     * 需求id
     */
    private Long demandId;

    /**
     * 卸货港
     */
    @Length(max=100)
    private String dischargePort;

    /**
     * 装货港
     */
    @Length(max=100)
    private String shipmentPort;


    /**
     * 装货开始时间
     */
    private Date shipmentStartTime;

    /**
     * 装货结束时间
     */
    private Date shipmentEndTime;

    /**
     * 到货开始时间
     */
    private Date dischargeStartTime;

    /**
     * 到货结束时间
     */
    private Date dischargeEndTime;
    
    /**
     * 装期 
     */
    private String shipmentPeriod;

    /**
     * 到货期
     */
    private String dischargePeriod;

    /**
     * 船型
     */
    private Integer shipType;

    /**
     * 船型 内容
     */
    private String shipTypeContent;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateTime;

    public DemandShipVO (){}
    /**
     * 构造函数
     * 将领域模型转换为vo
     * @param demandShip 需求的领域模型
     */
    public DemandShipVO (DemandShip demandShip) {
        Map<Object, String> shipTypeMap =  DictUtils.getShipTypes();
        this.id = demandShip.getId();
        this.transportModes = demandShip.getTransportModes();
        if(null == transportModes || transportModes == 0 ){
            this.transportModesContent = "海运";
        }
        this.demandId = demandShip.getDemandId();
        this.dischargePort = demandShip.getDischargePort();

        Map dischargePortMap = DictUtils.getUnLoadPortMap();
        String dischargePort = demandShip.getDischargePort();
        String[][] dischargePortDictionary = (String[][]) dischargePortMap.get(dischargePort);
        if (dischargePortDictionary != null) {
            this.setDischargePort(com.sinochem.it.b2b.common.utils.VisitorLocale.getByCurrentLanguage(dischargePortDictionary));
        } else {
            this.setDischargePort(dischargePort);
        }

        this.shipmentStartTime = demandShip.getShipmentStartTime();
        this.shipmentEndTime = demandShip.getShipmentEndTime();
        this.dischargeStartTime = demandShip.getDischargeStartTime();
        this.dischargeEndTime = demandShip.getDischargeEndTime();
        this.shipmentPeriod = DateUtil.formatDate(shipmentStartTime) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(shipmentEndTime);
        this.dischargePeriod = DateUtil.formatDate(dischargeStartTime) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(dischargeEndTime);
        this.shipType = demandShip.getShipType();
        if(shipType != null){
            this.shipTypeContent = shipTypeMap.get(shipType);
        }
        this.creater = demandShip.getCreater();
        this.createTime = demandShip.getCreateTime();
        this.updater = demandShip.getUpdater();
        this.updateTime = demandShip.getUpdateTime();
    }
    
    /**
     * 将vo转换为领域模型
     * @return
     */
    public DemandShip convertVoToDomain() {
        DemandShip demandShip = new DemandShip();
        demandShip.setCreater(this.getCreater());
        demandShip.setCreateTime(this.getCreateTime());
        demandShip.setDemandId(this.getDemandId());
        demandShip.setDischargeEndTime(this.getDischargeEndTime());
        demandShip.setDischargePort(this.getDischargePort());
        demandShip.setDischargeStartTime(this.getDischargeStartTime());
        demandShip.setId(this.getId());
        demandShip.setShipmentEndTime(this.getShipmentEndTime());
        demandShip.setShipmentPort(this.getShipmentPort());
        demandShip.setShipmentStartTime(this.getShipmentStartTime());
        demandShip.setShipType(this.getShipType());
        demandShip.setTransportModes(this.getTransportModes());
        demandShip.setUpdater(this.getUpdater());
        demandShip.setUpdateTime(this.getUpdateTime());
        
        return demandShip;
    }
    
    /**
     * 将领域模型转换为vos
     * @param demandShips
     * @return
     */
    public static List<DemandShipVO> convertDomainToVOList(List<DemandShip> demandShips) {
    	if(CollectionUtils.isEmpty(demandShips)) {
    		return new ArrayList<DemandShipVO>();
    	}
    	List<DemandShipVO> productOilDetailDemandShipVOs = new ArrayList<>();
    	
    	for(DemandShip item : demandShips) {
			productOilDetailDemandShipVOs.add(new DemandShipVO(item));
    	}
		return productOilDetailDemandShipVOs;
	}

    /**
     * 将vo转换为领域模型
     * @return
     */
    public static DemandShipVO convertDomainToVo(DemandShip domain) {
    	DemandShipVO vo = new DemandShipVO();
    	vo.setCreater(domain.getCreater());
    	vo.setCreateTime(domain.getCreateTime());
    	vo.setDemandId(domain.getDemandId());
    	vo.setDischargeEndTime(domain.getDischargeEndTime());
    	vo.setDischargePort(domain.getDischargePort());
    	vo.setDischargeStartTime(domain.getDischargeStartTime());
    	vo.setId(domain.getId());
    	vo.setShipmentEndTime(domain.getShipmentEndTime());
    	vo.setShipmentPort(domain.getShipmentPort());
    	vo.setShipmentStartTime(domain.getShipmentStartTime());
    	vo.setShipType(domain.getShipType());
    	vo.setTransportModes(domain.getTransportModes());
    	vo.setUpdater(domain.getUpdater());
    	vo.setUpdateTime(domain.getUpdateTime());
        
        return vo;
    }
    
    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandShipVO> convertListToVo(List<DemandShip> domainList) {
    	if(domainList == null) {
    		return null;
    	}
    	
    	List<DemandShipVO> voList = new ArrayList<DemandShipVO>();
    	for(int i = 0; i < domainList.size(); i++) {
    		voList.add(convertDomainToVo(domainList.get(i)));
    	}
    	
    	return voList;
    }
    
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

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    //@NotEmpty(message = "卸货港不能为空！")
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

    public Date getShipmentEndTime() {
        return shipmentEndTime;
    }

    public void setShipmentEndTime(Date shipmentEndTime) {
        this.shipmentEndTime = shipmentEndTime;
    }

    public Date getDischargeStartTime() {
        return dischargeStartTime;
    }

    public void setDischargeStartTime(Date dischargeStartTime) {
        this.dischargeStartTime = dischargeStartTime;
    }

    public Date getDischargeEndTime() {
        return dischargeEndTime;
    }

    public void setDischargeEndTime(Date dischargeEndTime) {
        this.dischargeEndTime = dischargeEndTime;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
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

	public String getShipmentPeriod() {
		return shipmentPeriod;
	}

	public void setShipmentPeriod(String shipmentPeriod) {
		this.shipmentPeriod = shipmentPeriod;
	}

	public String getDischargePeriod() {
		return dischargePeriod;
	}

	public void setDischargePeriod(String dischargePeriod) {
		this.dischargePeriod = dischargePeriod;
	}

    public String getTransportModesContent() {
        return transportModesContent;
    }

    public void setTransportModesContent(String transportModesContent) {
        this.transportModesContent = transportModesContent;
    }

    public String getShipTypeContent() {
        return shipTypeContent;
    }

    public void setShipTypeContent(String shipTypeContent) {
        this.shipTypeContent = shipTypeContent;
    }
}