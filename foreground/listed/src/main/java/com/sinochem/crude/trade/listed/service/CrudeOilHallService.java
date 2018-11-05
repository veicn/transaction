package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.model.form.CrudeOIlBiddingForm;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.IdForm;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface CrudeOilHallService {
    /**
     * 发布采购需求
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilFormList
     * @param
     * @param demandShip
     * @param currentUser
     */
    void publishProcurementDemand(Demand demand,
                                  List<DemandShipBerth> demandShipBerthList,
                                  List<DemandRelevanter> demandRelevanterList,
                                  IdForm idForm,
                                  List<CrudeOilForm> crudeOilFormList,
                                  List<DemandDetail> demandDetailList,
                                  DemandShip demandShip,
                                  CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException;

    /**
     * 原油报价
     * @param currentUser
     * @param biddingForm
     * @throws BizException
     */
    Demand saveQuote(CurrentUser currentUser, CrudeOIlBiddingForm biddingForm) throws Exception;

    /**
     * 保存需求单
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilFormList
     * @param demandDetailList
     * @param demandShip
     * @param currentUser
     */
    Demand saveProcurementDemand(Demand demand,
                               List<DemandShipBerth> demandShipBerthList,
                               List<DemandRelevanter> demandRelevanterList,
                               IdForm idForm,
                               List<CrudeOilForm> crudeOilFormList,
                               List<DemandDetail> demandDetailList,
                               DemandShip demandShip,
                               CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException;
    
    /**
     * 销售资源维护_保存销售需求单
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilFormList
     * @param demandDetailList
     * @param demandShip
     * @param currentUser
     */
    Demand saveSaleDemand(Demand demand,
                               List<DemandShipBerth> demandShipBerthList,
                               List<DemandRelevanter> demandRelevanterList,
                               IdForm idForm,
                               List<CrudeOilForm> crudeOilFormList,
                               List<DemandDetail> demandDetailList,
                               DemandShip demandShip,
                               CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException;
}
