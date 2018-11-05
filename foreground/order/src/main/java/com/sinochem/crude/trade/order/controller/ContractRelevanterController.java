package com.sinochem.crude.trade.order.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.domain.ContractShip;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.model.vo.CrudeOilLongTermContractPlanVO;
import com.sinochem.crude.trade.order.service.ContractRelevanterService;
import com.sinochem.crude.trade.order.service.ContractService;
import com.sinochem.crude.trade.order.service.ContractShipService;
import com.sinochem.crude.trade.order.service.CrudeContractService;
import com.sinochem.crude.trade.order.service.CrudeOilLongTermContractPlanService;
import com.sinochem.crude.trade.order.service.CrudeOilResourceService;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @Description:
 * 合约创建
 * @Author : jasonxu
 * @Date: 15/11/2017
 */
@Controller
@RequestMapping("/longTermContractContain")
@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
public class ContractRelevanterController {

    Logger logger = LoggerFactory.getLogger(ContractRelevanterController.class);

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
     * 查询买家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longQueryBuyer")
    public void queryBuyer(Long contractId,String type, ModelMap modelMap){
        try {
            ContractRelevanter contractRelevanter = contractRelevanterService.query(contractId,type);
            modelMap.addAttribute("contractRelevanter",contractRelevanter);
        } catch (Exception e) {
            logger.error("查询买家信息{}",contractId,type);
            logger.error("",e);
            modelMap.addAttribute("contractRelevanter",null);
        }

    }



    /**
     * 查询买家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longEditBuyer")
    public void editBuyer(Long contractId,String type, ModelMap modelMap) {
        try {
            ContractRelevanter contractRelevanter  = contractRelevanterService.query(contractId,type);
            modelMap.addAttribute("contractRelevanter",contractRelevanter);
        } catch (Exception e) {
            logger.error("查询买家信息{}",contractId,type);
            logger.error("",e);
            modelMap.addAttribute("contractRelevanter",null);
        }
    }


    /**
     * 查询中间商家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longQueryAgency")
    public void queryAgency(Long contractId,String type, ModelMap modelMap){
        try{
            ContractRelevanter contractRelevanter =contractRelevanterService.query(contractId,type);
            modelMap.addAttribute("contractRelevanter",contractRelevanter);
        }catch (Exception e){
            logger.error("查询中间商家信息{}", contractId, type);
            logger.error("", e);
            modelMap.addAttribute("contractRelevanter",null);
        }
    }


    /**
     * 查询中间商家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longEditAgency")
    public void editAgency(Long contractId,String type, ModelMap modelMap) {
       try{
           ContractRelevanter contractRelevanter =contractRelevanterService.query(contractId,type);
           modelMap.addAttribute("contractRelevanter",contractRelevanter);
       }catch (Exception e){
           logger.error("查询中间商家信息{}", contractId, type);
           logger.error("", e);
           modelMap.addAttribute("contractRelevanter",null);
       }
    }

    /**
     * 查询中间商家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longEditAgencyForValid")
    public void editAgencyForValid(Long contractId,String type, ModelMap modelMap) {
        try{
            if (contractId != null) {
                ContractRelevanter contractRelevanter =contractRelevanterService.query(contractId,type);
                modelMap.addAttribute("traderRelevanter",contractRelevanter);
            }
        }catch (Exception e){
            logger.error("查询中间商家信息{}", contractId, type);
            logger.error("", e);
            modelMap.addAttribute("traderRelevanter",null);
        }
    }


    /**
     * 查询供应商信息家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longQuerySupply")
    public void querySupply(Long contractId,String type, ModelMap modelMap){
        try {
            ContractRelevanter contractRelevanter =contractRelevanterService.query(contractId,type);
            modelMap.addAttribute("contractRelevanter",contractRelevanter);
        }catch (Exception e){
            logger.error("查询供应商信息家信息{}", contractId, type);
            logger.error("", e);
            modelMap.addAttribute("contractRelevanter",null);
        }
    }

    /**
     * 查询供应商信息家信息
     * @param contractId
     * @param type
     * @param modelMap
     */
    @RequestMapping("longEditSupply")
    public void editSupply(Long contractId,String type, ModelMap modelMap) {
        try{
            ContractRelevanter contractRelevanter =contractRelevanterService.query(contractId,type);
            modelMap.addAttribute("contractRelevanter",contractRelevanter);
        }catch (Exception e){
            logger.error("查询供应商信息家信息{}", contractId, type);
            logger.error("", e);
            modelMap.addAttribute("contractRelevanter",null);
        }
    }


    /**
     * 查询采购计划信息
     * @param contractId
     * @param
     * @param modelMap
     */
    @RequestMapping("longQueryPlan")
    public void queryPlan(Long contractId, ModelMap modelMap) {
        List<CrudeOilLongTermContractPlan> planList =crudeOilLongTermContractPlanService.query(contractId);
        List<CrudeOilLongTermContractPlanVO> voList = new ArrayList<CrudeOilLongTermContractPlanVO>();
        
        for (CrudeOilLongTermContractPlan crudePlan:planList) {
            Long crudeOilId =crudePlan.getCrudeOilId();
            CrudeOilResource crudeOilResource = crudeOilResourceService.selectById(crudeOilId);
            crudePlan.setCrudeOilResource(crudeOilResource);
            
            CrudeOilLongTermContractPlanVO vo = new CrudeOilLongTermContractPlanVO();
            CrudeOilLongTermContractPlanVO.convertToCrudeOilLongTernContractPlanVO(crudePlan, vo);
            voList.add(vo);
        }
        modelMap.addAttribute("planList",voList);
    }


    /**
     * 查询采购信息
     * @param contractId
     * @param
     * @param modelMap
     */
    @RequestMapping("longQueryPurchase")
    public void queryPurchase(Long contractId, ModelMap modelMap) {
        try{
            Contract contract =contractService.getContract(contractId);
            ContractShip contractShip = contractShipService.queryByContractId(contractId);
            
        	if(StringUtils.isNotBlank(contractShip.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(contractShip.getDischargePort())){
				String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(contractShip.getDischargePort());
				String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
				contractShip.setDischargePort(unLoadPort);
			}
            
            CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contractId);
            String oil = crudeOilResource.getName();
            modelMap.addAttribute("oil",oil);
            
            modelMap.addAttribute("contract",contract);
            modelMap.addAttribute("contractShip",contractShip);

            //贸易条款
            Map tradeItemMap = DictUtils.getTradeItemMap();
            String tradeItemName = (String) tradeItemMap.get(contract.getTradeItem());
            modelMap.addAttribute("tradeItemName",tradeItemName);
            
            //信用条款
            Map authItemMap = DictUtils.getCreditItem();
            String authItemName = (String) authItemMap.get(Integer.valueOf(contract.getAuthItem()));
            modelMap.addAttribute("authItemName",authItemName);
            //商检费分摊
            Map inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
            String inspectionFeeSharingTypeName = (String) inspectionFeeSharingTypeMap.get(contract.getInspectionFeeSharingType());
            modelMap.addAttribute("inspectionFeeSharingTypeName",inspectionFeeSharingTypeName);
            //TODO
            //付款条款
            Map payItemMap = DictUtils.getPayItemMap();
            String payItemName = (String)contract.getPayItem();
            modelMap.addAttribute("payItemName",payItemName);
        }catch (BizException e){
            logger.error("查询采购信息{}",contractId);
            logger.error("",e);
            modelMap.addAttribute("contract",null);
            modelMap.addAttribute("contractShip",null);
            modelMap.addAttribute("tradeItemName",null);
            modelMap.addAttribute("payItemName",null);
        }
    }



    /**
     * 查询原油产品信息
     * @param crudeName
     * @return
     */
    @RequestMapping("queryCrudeOilInfos.json")
    public List<CrudeOilInfoVO> queryCrudeOilInfos(String crudeName){
        List<CrudeOilInfoVO> vos = crudeOilInfoService.findCrudeOilInfos(crudeName, null);
        return vos;
    }



}
