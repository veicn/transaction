package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

/**
 * 泊位的VO
 * @author Yichen Zhao
 * date: 20180301
 */
public class ShipBerthVO {

    /**
     * 代码
     */
    private String code;

    /**
     * 港口的代码
     */
    private ShipPortVO shipPortVO;

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


    /** getters and setters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ShipPortVO getShipPortVO() {
        return shipPortVO;
    }

    public void setShipPortVO(ShipPortVO shipPortVO) {
        this.shipPortVO = shipPortVO;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getBerthTonnage() {
        return berthTonnage;
    }

    public void setBerthTonnage(String berthTonnage) {
        this.berthTonnage = berthTonnage;
    }

    public String getBerthDraft() {
        return berthDraft;
    }

    public void setBerthDraft(String berthDraft) {
        this.berthDraft = berthDraft;
    }

    public String getVesselType() {
        return vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
