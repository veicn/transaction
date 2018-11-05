package com.sinochem.crude.trade.listed.model.vo;

/**
 * 用于 crudeoillobby/crudeOilBidding 请求的VO
 * @author Yichen Zhao
 */
public class CrudeOilBiddingVO {

    private Long demandId;

    private String errorMsg;
    
    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
