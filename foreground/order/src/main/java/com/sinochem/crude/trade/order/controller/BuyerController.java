package com.sinochem.crude.trade.order.controller;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.enums.InspectionFeeSharingType;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.domain.ContractShip;
import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.domain.ProductOilResource;
import com.sinochem.crude.trade.order.enums.EnumContractBizType;
import com.sinochem.crude.trade.order.model.form.ContractListQueryForm;
import com.sinochem.crude.trade.order.model.result.OrderStatusCountResult;
import com.sinochem.crude.trade.order.model.vo.ProductOilInfoVO;
import com.sinochem.crude.trade.order.service.ContractRelevanterService;
import com.sinochem.crude.trade.order.service.ContractShipBerthService;
import com.sinochem.crude.trade.order.service.ContractShipService;
import com.sinochem.crude.trade.order.service.CrudeContractService;
import com.sinochem.crude.trade.order.service.CrudeOilResourceService;
import com.sinochem.crude.trade.order.service.ProductOilResourceService;
import com.sinochem.crude.trade.order.util.Base64Helper;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 买家控制层
 */
@Controller
@RolesAccess("trade_oper")
public class BuyerController {
    Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @Autowired
    private CrudeContractService crudeContractService;
    @Autowired
    private ContractShipService contractShipService;
    @Autowired
    private ContractRelevanterService contractRelevanterService;
    @Autowired
    private ContractShipBerthService contractShipBerthService;
    @Autowired
    private CrudeOilResourceService crudeOilResourceService;
    @Autowired
    private ProductOilResourceService productOilResourceService;
    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @RequestMapping("/buyer/queryContractList")
    public void queryContractList(CurrentUser user, ContractListQueryForm form, PageInfo query, ModelMap modelMap){
       try{
           PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
           modelMap.addAttribute("contractList",pageInfoResult);
           modelMap.addAttribute("user",user);
       }catch (BizException e){
           logger.error("查询订单list{}",form);
           logger.error("",e);
           modelMap.addAttribute("contractList",null);
           modelMap.addAttribute("user",user);
       }
    }
    @RequestMapping("/buyer/queryContractInfo")
    public void queryContractInfo(CurrentUser user,Long contractId, ModelMap modelMap){
       Contract contractInfo = crudeContractService.queryContractInfo(contractId);
       modelMap.addAttribute("biddingId",contractInfo.getBiddingId());
    }
    @RequestMapping("/buyer/orderInfoStatus")
    public void orderInfoStatus(CurrentUser user,Long contractId, ModelMap modelMap) {
        modelMap.addAttribute("orderstatus","success");
    }

    @RequestMapping("/buyer/queryOrderListForm")
    public void countOrderStatus(CurrentUser user,ModelMap modelMap){
        OrderStatusCountResult orderStatusCountResult = crudeContractService.getCountOrderStatus();
        modelMap.addAttribute("orderStatusCount", orderStatusCountResult);
    }
    //订单详情页面分割
    /**
     * 原油详情页面跳转
     * @param user
     * @param uuid
     * @param modelMap
     */
    @RequestMapping("/buyer/crudeOilOrderInfo")
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    public void crudeOilOrderInfo(CurrentUser user,String uuid, String errorMessage,ModelMap modelMap){
        Contract contract = crudeContractService.queryContractInfoByUUID(uuid);
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("user",user);
        if(StringUtil.isNotBlank(errorMessage)){
            errorMessage = new String(Base64Helper.decode(errorMessage));
        }
        modelMap.addAttribute("errorMessage", errorMessage);
    }
    @RequestMapping("/buyer/crudeOilProduct")
    public void crudeOilProduct(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId != null) {
            CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contractId);
            if(crudeOilResource != null && crudeOilResource.getOrigin() != null) {
                CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findByENameAndAreaId(crudeOilResource.getOrigin());
                if (crudeOilInfoVO != null) {
                    String originNameC = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}});
                    modelMap.addAttribute("originNameC",originNameC);
                }
            }
            modelMap.addAttribute("crudeOilResource",crudeOilResource);
        }
    }
    @RequestMapping("/buyer/crudeOilProductPrice")
    public void crudeOilProductPrice(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId == null) {
            return;
        }
        Contract contract = crudeContractService.queryContractInfo(contractId);
        String valuationBase = contract.getValuationBase();
        //计价基准
        Map valuationBaseMap = DictUtils.getValuationBaseMap();
        String valuationBaseName = (String) valuationBaseMap.get(valuationBase);
        modelMap.addAttribute("valuationBaseName",valuationBaseName);
        //计价期类型

        Map valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
        String valuationProidTypeName = (String) valuationProidTypeMap.get(contract.getValuationProidType());
        modelMap.addAttribute("valuationProidTypeName",valuationProidTypeName);
        //付款日期
        Map payItemMap = DictUtils.getPayItemMap();
        String payItemName = (String)contract.getPayItem();
        modelMap.addAttribute("payItemName",payItemName);

        //采购方式
        Map purchaseModeMap = DictUtils.getPurchaseModes();
        String purchaseModeName = (String) purchaseModeMap.get(contract.getPurchaseMode());
        modelMap.addAttribute("purchaseModeName",purchaseModeName);

        modelMap.addAttribute("contract",contract);

    }
    @RequestMapping("/buyer/crudeOilBerthListInfo")
    public void crudeOilBerthListInfo(CurrentUser user,Long contractId, ModelMap modelMap){
        if(contractId != null) {
            List<ContractShipBerth> listData = contractShipBerthService.queryBycontractId(contractId);
            modelMap.addAttribute("berthlist",listData);
        }
    }
    @RequestMapping("/buyer/crudeOilPayInfo")
    public void crudeOilPayInfo(CurrentUser user,Long contractId, ModelMap modelMap) throws BizException {
    }
    @RequestMapping("/buyer/crudeOilShipment")
    public void crudeOilShipment(CurrentUser user,Long contractId, ModelMap modelMap) throws BizException {
    }

    @RequestMapping("/buyer/crudeOilOrderInfoPrice")
    public void crudeOilOrderInfoPrice(CurrentUser user,Long contractId, ModelMap modelMap) {
        if (contractId == null) {
            return;
        }
        Contract contract = crudeContractService.queryContractInfo(contractId);
        ContractShip contractShip = contractShipService.queryByContractId(contractId);
        CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contractId);
        if(null!=crudeOilResource){
            String crudeOilName = crudeOilResource.getName();
            modelMap.addAttribute("crudeOilName",crudeOilName);
        }
        Integer inspectionFeeSharingType = contract.getInspectionFeeSharingType();
        if (inspectionFeeSharingType != null) {
            InspectionFeeSharingType type = InspectionFeeSharingType.getInspectionFeeSharingTypeByCode(inspectionFeeSharingType);
            if (type != null) {
            	contract.setInspectionFeeSharingTypeContent(type.getName());
            }
        }
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("contractShip",contractShip);


        //计价基准
        Map valuationBaseMap = DictUtils.getValuationBaseMap();
        String valuationBaseName = (String) valuationBaseMap.get(contract.getValuationBase());
        modelMap.addAttribute("valuationBaseName",valuationBaseName);

        //付款日期
        Map payItemMap = DictUtils.getPayItemMap();
        String payItemName = (String)contract.getPayItem();
        modelMap.addAttribute("payItemName",payItemName);

        //贸易条款

        Map tradeItemMap = DictUtils.getTradeItemMap();
        String tradeItemName = (String) tradeItemMap.get(contract.getTradeItem());
        modelMap.addAttribute("tradeItemName",tradeItemName);

        modelMap.addAttribute("measureModeMap",DictUtils.getMeasureModeMap());

    }
    /**
     * 成品油详情页面
     * @param user
     * @param uuid
     * @param modelMap
     */
    @RequestMapping("/buyer/productOilOrderInfo")
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    public void orderInfo(CurrentUser user,String uuid, String errorMessage,ModelMap modelMap){
        if (StringUtils.isNotEmpty(uuid)) {
            Contract contract = crudeContractService.queryContractInfoByUUID(uuid);
            modelMap.addAttribute("contract",contract);
        }
        modelMap.addAttribute("user",user);
        if(StringUtil.isNotBlank(errorMessage)){
            errorMessage = new String(Base64Helper.decode(errorMessage));
        }
        modelMap.addAttribute("errorMessage", errorMessage);
    }
    @RequestMapping("/buyer/productOilGoodsInfo")
    public void productOilGoodsInfo(CurrentUser user,Long contractId, ModelMap modelMap){
        if(contractId == null) {
            return;
        }
        Contract contract = crudeContractService.queryContractInfo(contractId);
        ProductOilResource productOilResource = productOilResourceService.queryProductOilResource(contractId);
        modelMap.addAttribute("contract",contract);

        ProductOilInfoVO productOilInfoVO = new ProductOilInfoVO();
        if(productOilResource != null){
            Map<Integer,String> kindMap = DictUtils.getProductOilKind();
            Map<Integer,String> specsMap = DictUtils.getProductOilSpecs();
            Map<Object,String> classifyMap = DictUtils.getProductOilClassifyMap();
            productOilInfoVO.setProductOilClassifyName(classifyMap.get(productOilResource.getProductOilClassify()));
            productOilInfoVO.setProductOilKindName(kindMap.get(productOilResource.getProductOilKind()));
            productOilInfoVO.setProductOilSpecsName(specsMap.get(productOilResource.getProductOilSpecs()));
        }
        modelMap.addAttribute("productOilInfo", productOilInfoVO);
        //贸易条款
        Map tradeItemMap = DictUtils.getTradeItemMap();
        String tradeItemName = (String) tradeItemMap.get(contract.getTradeItem());
        modelMap.addAttribute("tradeItemName",tradeItemName);
    }
    @RequestMapping("/buyer/productPricelist")
    public void productPricelist(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId != null) {
            Contract contract = crudeContractService.queryContractInfo(contractId);
            modelMap.addAttribute("contract",contract);
        }
    }
    @RequestMapping("/buyer/shipInput")
    public void shipInput(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId != null) {
            ContractShip contractShip = contractShipService.queryByContractId(contractId);
            modelMap.addAttribute("contractShip",contractShip);
        }
    }
    @Deprecated
    @RequestMapping("/buyer/orderInfoProduct")
    public void orderInfoProduct(CurrentUser user,Long contractId, ModelMap modelMap) {
        //ContractProduct contractProduct = crudeContractService.queryOrderInfoProduct(contractId);
        //modelMap.addAttribute("contractProduct",contractProduct);
    }
    @RequestMapping("/buyer/orderInfoPrice")
    public void orderInfoPrice(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId == null) {
            return;
        }
        Contract contract = crudeContractService.queryContractInfo(contractId);
        modelMap.addAttribute("contract",contract);

        //计价基准
        Map valuationBaseMap;
        if (EnumContractBizType.ProductOilContract.getCode().equals(contract.getBizType())) {
            valuationBaseMap = DictUtils.getProductOilValuationBaseMap();
        } else {
            valuationBaseMap = DictUtils.getValuationBaseMap();
        }
        modelMap.addAttribute("valuationBaseName",valuationBaseMap.get(contract.getValuationBase()));

        //付款条款
        Map payItemMap = DictUtils.getPayItemMap();
        String payItemName = (String) contract.getPayItem();
        modelMap.addAttribute("payItemName",payItemName);
    }
    @RequestMapping("/buyer/orderInfoSupplier")
    public void orderInfoSupplier(CurrentUser user,Long contractId, ModelMap modelMap)  {
        try{
            if (contractId != null) {
                ContractRelevanter contractRelevanter = contractRelevanterService.query(contractId,"S");
                modelMap.addAttribute("contractRelevanter",contractRelevanter);
            }
        }catch (Exception e){
            logger.error("",e);
            modelMap.addAttribute("contractRelevanter",null);
        }
    }
    @RequestMapping("/buyer/orderInfoCustomer")
    public void orderInfoCustomer(CurrentUser user,Long contractId, ModelMap modelMap){
       try{
           if (contractId != null) {
               ContractRelevanter contractRelevanter = contractRelevanterService.query(contractId,"B");
               modelMap.addAttribute("contractRelevanter",contractRelevanter);
           }
       }catch (Exception e){
           logger.error("",e);
           modelMap.addAttribute("contractRelevanter",null);
       }
    }
    @RequestMapping("/buyer/orderInfoAgent")
    public void orderInfoAgent(CurrentUser user,Long contractId, ModelMap modelMap){
        try{
            if (contractId != null) {
                ContractRelevanter contractRelevanter = contractRelevanterService.query(contractId,"T");
                modelMap.addAttribute("contractRelevanter",contractRelevanter);
            }
        }catch (Exception e){
           logger.error("",e);
           modelMap.addAttribute("contractRelevanter",null);
        }
    }
    @RequestMapping("/buyer/orderInfoBerth")
    public void orderInfoBerth(CurrentUser user,Long contractId, ModelMap modelMap) {
    }
    @RequestMapping("/buyer/orderInfoShipment")
    public void orderInfoShipment(CurrentUser user,Long contractId, ModelMap modelMap)  {
    }
    @RequestMapping("/buyer/orderInfoDischarge")
    public void orderInfoDischarge(CurrentUser user,Long contractId, ModelMap modelMap) {
    }
    @RequestMapping("/buyer/orderInfoClosing")
    public void orderInfoClosing(CurrentUser user,Long contractId, ModelMap modelMap) {
    }
    @RequestMapping("/buyer/orderInfoOther")
    public void orderInfoOther(CurrentUser user,Long contractId, ModelMap modelMap){
       if (contractId == null) {
           return;
       }
       Contract contract = crudeContractService.queryContractInfo(contractId);
       modelMap.addAttribute("contract",contract);

       //商检机构
       Map BusinessCheckOrgMap = DictUtils.getBusinessCheckOrg ();
       String businessCheckOrgName = (String) BusinessCheckOrgMap.get(contract.getBusinessCheckOrg());
       modelMap.addAttribute("businessCheckOrgName",businessCheckOrgName);
       //信用条款
       Map creditItemMap = DictUtils.getCreditItem();
       String authItemName = (String) creditItemMap.get(Integer.valueOf(contract.getAuthItem()));
       modelMap.addAttribute("authItemName",authItemName);
    }

}

