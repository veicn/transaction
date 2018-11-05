package com.sinochem.crude.trade.listed.model.form;

import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipBerthVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;

import javax.validation.Valid;

/**
 * 原油报价页面表单
 * Created by sijliu on 08/01/2018.
 */
public class CrudeOIlBiddingForm {

    @Valid
    private DemandVO bidding = new DemandVO();

    /**
     * 选中的油种信息
     */
    @Valid
    private CrudeOilForm crudeOil = new CrudeOilForm();

    /**
     * 供应商信息
     */
    @Valid
    private DemandRelevanterVO demandRelevanter = new DemandRelevanterVO();

    /**
     * 选中泊位信息
     */
    private DemandShipBerthVO demandShipBerth = new DemandShipBerthVO();

    /**
     * 选中运输信息
     */
    @Valid
    private DemandShipVO demandShip = new DemandShipVO();

    // 空采购报价，1销售报价
    private Integer oilSaleFlag;

    public DemandVO getBidding() {
        return bidding;
    }

    public void setBidding(DemandVO bidding) {
        this.bidding = bidding;
    }

    public CrudeOilForm getCrudeOil() {
        return crudeOil;
    }

    public void setCrudeOil(CrudeOilForm crudeOil) {
        this.crudeOil = crudeOil;
    }

    public DemandRelevanterVO getDemandRelevanter() {
        return demandRelevanter;
    }

    public void setDemandRelevanter(DemandRelevanterVO demandRelevanter) {
        this.demandRelevanter = demandRelevanter;
    }

    public DemandShipBerthVO getDemandShipBerth() {
        return demandShipBerth;
    }

    public void setDemandShipBerth(DemandShipBerthVO demandShipBerth) {
        this.demandShipBerth = demandShipBerth;
    }

    public DemandShipVO getDemandShip() {
        return demandShip;
    }

    public void setDemandShip(DemandShipVO demandShip) {
        this.demandShip = demandShip;
    }

	public Integer getOilSaleFlag() {
		return oilSaleFlag;
	}

	public void setOilSaleFlag(Integer oilSaleFlag) {
		this.oilSaleFlag = oilSaleFlag;
	}

}
