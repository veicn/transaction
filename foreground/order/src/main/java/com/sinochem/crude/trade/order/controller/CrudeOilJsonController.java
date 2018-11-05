package com.sinochem.crude.trade.order.controller;


import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.service.*;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * 合约创建
 * @Author : jasonxu
 * @Date: 15/11/2017
 */
@Controller
@RequestMapping("crudeoillobby")
@RolesAccess("trade_oper")
public class CrudeOilJsonController {

    Log log = LogFactory.getLog(CrudeOilJsonController.class);

    @Autowired
    ContractRelevanterService contractRelevanterService;

    @Autowired
    CrudeOilLongTermContractPlanService crudeOilLongTermContractPlanService;;

    @Autowired
    CrudeContractService crudeContractService;

    @Autowired
    ContractShipService contractShipService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;
    /**
     * 查询原油产品信息
     * @param crudeName
     * @return
     */
    @RequestMapping("queryCrudeOilInfos.json")
    public List<CrudeOilInfoVO> queryCrudeOilInfos(String crudeName) {
        List<CrudeOilInfoVO> vos = crudeOilInfoService.findCrudeOilInfos(crudeName, null);
        return vos;
    }


}
