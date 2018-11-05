package com.sinochem.crude.trade.listed.model.vo;

/**
 * 接收转价请求的VO
 * @author Yichen Zhao
 * date: 20180203
 */
public class PriceConvertionRequestVO {

    private OilModelVO originOilModel;

    private OilModelVO targetOilModel;

    /** getters and setters */
    public OilModelVO getOriginOilModel() {
        return originOilModel;
    }

    public void setOriginOilModel(OilModelVO originOilModel) {
        this.originOilModel = originOilModel;
    }

    public OilModelVO getTargetOilModel() {
        return targetOilModel;
    }

    public void setTargetOilModel(OilModelVO targetOilModel) {
        this.targetOilModel = targetOilModel;
    }
}
