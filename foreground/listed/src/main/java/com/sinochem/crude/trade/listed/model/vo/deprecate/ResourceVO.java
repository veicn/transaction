package com.sinochem.crude.trade.listed.model.vo.deprecate;

import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.DemandVO;

import java.util.List;

/**
 * 单个成品油销售需求的VO
 * @author Yichen Zhao
 * date: 20180103
 */
@Deprecated
public class ResourceVO {
    //资源信息
    private DemandVO demandVO;

    //原油信息
    private List<CrudeOil> oil;

    //装船信息
    private DemandShipVO[] demandShipsVO;

    /*getters and setters*/
    public DemandVO getDemandVO() {
        return demandVO;
    }

    public void setDemandVO(DemandVO demandVO) {
        this.demandVO = demandVO;
    }

    public DemandShipVO[] getDemandShipsVO() {
        return demandShipsVO;
    }

    public void setDemandShipsVO(DemandShipVO[] demandShipsVO) {
        this.demandShipsVO = demandShipsVO;
    }

    public List<CrudeOil> getOil() {
        return oil;
    }

    public void setOil(List<CrudeOil> oil) {
        this.oil = oil;
    }
}
