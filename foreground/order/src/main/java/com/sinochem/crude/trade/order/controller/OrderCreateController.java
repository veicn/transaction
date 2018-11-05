package com.sinochem.crude.trade.order.controller;


import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.enums.EnumContractBizType;
import com.sinochem.crude.trade.order.model.form.ContractListQueryForm;
import com.sinochem.crude.trade.order.model.query.LongContractQuery;
import com.sinochem.crude.trade.order.service.*;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;

/**
 *
 * 合约创建
 */
@Controller
@RolesAccess("trade_oper")
public class OrderCreateController {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderCreateController.class);

    @Autowired
    CrudeContractService crudeContractService;
    @Autowired
    private ContractShipService contractShipService;
    @Autowired
    private CrudeOilResourceService crudeOilResourceService;
    @Autowired
    private ContractShipBerthService contractShipBerthService;
    @Autowired
    CrudeOilInfoService crudeOilInfoService;
    @Autowired
    private ProductOilResourceService productOilResourceService;

    @Autowired
    private URLBroker appServerBroker;

    /**
     * 成品油个人中心列表显示
     * @param user
     * @param form
     * @param modelMap
     * @param query
     */
    @RequestMapping("/personal/productOilContractList")
    @RolesAccess({"trade_executor", "trade_oper"})
    public void productOilContractList(CurrentUser user, ContractListQueryForm form, ModelMap modelMap, PageInfo query){
        try{
            query.setPageSize(5);
            form.setBizType("P");
            PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
            modelMap.addAttribute("contractList",pageInfoResult);
            modelMap.addAttribute("user",user);
        }catch (BizException e){
            LOGGER.error("成品油个人中心列表显示{}",form);
            LOGGER.error("",e);
            modelMap.addAttribute("contractList",null);
            modelMap.addAttribute("user",user);
        }
    }
    /**
     * 原油个人中心列表显示
     * @param user
     * @param form
     * @param modelMap
     * @param query
     */
    @RequestMapping("/personal/crudeOilContractList")
    @RolesAccess({"trade_executor", "trade_oper"})
    public void contractList(CurrentUser user,ContractListQueryForm form,ModelMap modelMap,PageInfo query) {
       try{
           query.setPageSize(5);
           form.setBizType("C");
           PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
           modelMap.addAttribute("contractList",pageInfoResult);
           modelMap.addAttribute("user",user);
       }catch (BizException e){
           LOGGER.error("原油个人中心列表显示{}",form);
           LOGGER.error("",e);
           modelMap.addAttribute("contractList",null);
           modelMap.addAttribute("user",user);
       }
    }


    /**
     * 个人中心前五列表显示（短协）
     * @param user
     * @param form
     * @param modelMap
     * @param query
     */
    @RequestMapping("/personal/queryContractList")
    public void queryContractList(CurrentUser user,ContractListQueryForm form,ModelMap modelMap,PageInfo query){
       try {
           query.setPageSize(5);
           PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
           modelMap.addAttribute("contractList",pageInfoResult);
       }catch (BizException e){
           LOGGER.error("个人中心前五列表显示（短协）{}",form);
           LOGGER.error("",e);
           modelMap.addAttribute("contractList",null);
       }
    }
    /**
     * 个人中心前五列表显示（长协）
     * @param user
     * @param form
     * @param modelMap
     * @param query
     */
    @Deprecated
    @RequestMapping("/personal/queryLongContractList")
    public void queryLongContractList(CurrentUser user, LongContractQuery form, ModelMap modelMap, PageInfo query) {
        //query.setPageSize(5);
        //PageInfoResult pageInfoResult  = crudeContractService.queryContractList(form,query);
        //modelMap.addAttribute("longContractList",pageInfoResult);
    }

    /**
     * 跳转到新增原油订单页面
     * @param user
     * @param modelMap
     */
    @RequestMapping("/create/createCrudeOilInfo")
    public void createCrudeOilInfo(CurrentUser user,ModelMap modelMap) {
        modelMap.addAttribute("user",user);

        //贸易条款
        Map<Integer,String> TradeItemMap = DictUtils.getTradeItemMap();
        //计价期类别
        Map<Object,String> ValuationProidType = DictUtils.getValuationProidTypeMap();
        //付款条款
        Map<Object,String> PayItemMap = DictUtils.getPayItemMap();
        //采购方式
        Map<Object,String> PurchaseModes = DictUtils.getPurchaseModes();
        //计价基准
        Map<Object,String> ValuationBase = DictUtils.getValuationBaseMap();

        modelMap.addAttribute("TradeItemMap",TradeItemMap);
        modelMap.addAttribute("ValuationProidType",ValuationProidType);
        modelMap.addAttribute("PayItemMap",PayItemMap);
        modelMap.addAttribute("PurchaseModes",PurchaseModes);
        modelMap.addAttribute("ValuationBase",ValuationBase);
    }
    /**
     * 跳转到修改原油订单页面
     * @param user
     * @param uuid
     * @param modelMap
     */
    @RequestMapping("/update/createCrudeOilInfo")
    public String updateCrudeOilInfo(CurrentUser user,String uuid,ModelMap modelMap){
        Contract contract = crudeContractService.queryContractInfoByUUID(uuid);
        if(contract!=null){
            modelMap.addAttribute("contractId",contract.getId());
        }
        return "/create/updateCrudeOilInfo";
    }
    /**
     * 跳转到修改成品油订单页面
     * @param user
     * @param uuid
     * @param modelMap
     */
    @RequestMapping("/update/createProductOilInfo")
    public String createProductOilInfo(CurrentUser user,String uuid,ModelMap modelMap)  {
        Contract contract = crudeContractService.queryContractInfoByUUID(uuid);
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("productOilKindAndSpecsMap", DictUtils.getProductOilKindAndSpecs());
        return "/create/updateProductOilInfo";
    }

    /**
     * 跳转到新增成品油订单页面
     * @param user
     * @return
     */
    @RequestMapping("/create/createProductOilInfo")
    public void createProductOilInfo(CurrentUser user,ModelMap modelMap){
        modelMap.addAttribute("user",user);
        //信用条款
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.addAttribute("creditItem",creditItem);
        //商检机构
        Map<Object,String> businessCheckOrg = DictUtils.getBusinessCheckOrg();
        modelMap.addAttribute("businessCheckOrg",businessCheckOrg);
        //采购方式
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.addAttribute("purchaseModes",purchaseModes);
        //付款条款
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.addAttribute("payItemMap",payItemMap);
        //计价基准
        Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
        modelMap.addAttribute("valuationBaseMap",valuationBaseMap);
        //计价期类型
        Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
        modelMap.addAttribute("valuationProidTypeMap",valuationProidTypeMap);
        //贸易条款
        Map<Integer,String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.addAttribute("tradeItemMap",tradeItemMap);
        //地区
        Map<Object,String> regionProductOil = DictUtils.getRegionProductOil();
        modelMap.addAttribute("regionProductOil",regionProductOil);
        //出口类型
        Map<Object,String> exportType = DictUtils.getExportType();
        modelMap.addAttribute("exportType",exportType);
        //品种
        Map<Integer,String> productOilKind = DictUtils.getProductOilKind();
        modelMap.addAttribute("productOilKind",productOilKind);
        //规格
        Map<Integer,String> productOilSpecs = DictUtils.getProductOilSpecs();
        modelMap.addAttribute("productOilSpecs",productOilSpecs);
        modelMap.addAttribute("productOilKindAndSpecsMap", DictUtils.getProductOilKindAndSpecs());

    }
    /**
     * 原油更新回现信息
     * @param contractId
     * @param modelMap
     */
    @RequestMapping("/create/oiltypedetail")
    public void oiltypedetail(Long contractId,ModelMap modelMap) {
        if(contractId != null) {
            CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contractId);
            if(crudeOilResource!=null){
                Long id = crudeOilResource.getPropertyId();
                if(id != null){
                    CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(id);
                    String originNameC = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}});
                    modelMap.addAttribute("originNameC",originNameC);
                }
            }
            modelMap.addAttribute("crudeOilResource",crudeOilResource);
        }
    }

    /**
     * 原油更新回现信息
     * @param user
     * @param contractId
     * @param modelMap
     */
    @RequestMapping("/create/crudeOiltypedetail")
    public void crudeOiltypedetail(CurrentUser user,Long contractId,ModelMap modelMap) {
        CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contractId);
        if(crudeOilResource!=null){
            Long id = crudeOilResource.getPropertyId();
            if(id != null){
                CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(id);
                String originNameC = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}});
                modelMap.addAttribute("originNameC",originNameC);
            }
        }
        modelMap.addAttribute("crudeOilResource",crudeOilResource);

    }

    /**
     * 成品油更新回现信息
     * @param user
     * @param contractId
     * @param modelMap
     */
    @RequestMapping("/create/productOilGoodsInfo")
    public void productOilGoodsInfo(CurrentUser user,Long contractId,ModelMap modelMap)  {
        Contract contract = crudeContractService.queryContractInfo(contractId);
        ProductOilResource productOilResource = productOilResourceService.queryProductOilResource(contractId);
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("productOilResource",productOilResource);

        //信用条款
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.addAttribute("creditItem",creditItem);
        //商检机构
        Map<Object,String> businessCheckOrg = DictUtils.getBusinessCheckOrg();
        modelMap.addAttribute("businessCheckOrg",businessCheckOrg);
        //采购方式
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.addAttribute("purchaseModes",purchaseModes);
        //付款条款
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.addAttribute("payItemMap",payItemMap);
        //计价基准
        Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
        modelMap.addAttribute("valuationBaseMap",valuationBaseMap);
        //计价期类型
        Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
        modelMap.addAttribute("valuationProidTypeMap",valuationProidTypeMap);
        //贸易条款
        Map<Integer,String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.addAttribute("tradeItemMap",tradeItemMap);
        //地区
        Map<Object,String> regionProductOil = DictUtils.getRegionProductOil();
        modelMap.addAttribute("regionProductOil",regionProductOil);
        //出口类型
        Map<Object,String> exportType = DictUtils.getExportType();
        modelMap.addAttribute("exportType",exportType);
        //品种
        Map<Integer,String> productOilKind = DictUtils.getProductOilKind();
        modelMap.addAttribute("productOilKind",productOilKind);
        //规格
        Map<Integer,String> productOilSpecs = DictUtils.getProductOilSpecs();
        modelMap.addAttribute("productOilSpecs",productOilSpecs);


    }
    @RequestMapping("/create/shipInput")
    public void shipInput(CurrentUser user,Long contractId,ModelMap modelMap)  {
        ContractShip contractShip = contractShipService.queryByContractId(contractId);
        modelMap.addAttribute("contractShip",contractShip);
    }
    @RequestMapping("/create/productPricelist")
    public void productPricelist(CurrentUser user,Long contractId,ModelMap modelMap) throws BizException {
        Contract contract = crudeContractService.queryContractInfo(contractId);
        modelMap.addAttribute("contract",contract);

        //信用条款
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.addAttribute("creditItem",creditItem);
        //商检机构
        Map<Object,String> businessCheckOrg = DictUtils.getBusinessCheckOrg();
        modelMap.addAttribute("businessCheckOrg",businessCheckOrg);
        //采购方式
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.addAttribute("purchaseModes",purchaseModes);
        //付款条款
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.addAttribute("payItemMap",payItemMap);
        //计价基准
        Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
        modelMap.addAttribute("valuationBaseMap",valuationBaseMap);
        //计价期类型
        Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
        modelMap.addAttribute("valuationProidTypeMap",valuationProidTypeMap);
        //贸易条款
        Map<Integer,String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.addAttribute("tradeItemMap",tradeItemMap);
        //地区
        Map<Object,String> regionProductOil = DictUtils.getRegionProductOil();
        modelMap.addAttribute("regionProductOil",regionProductOil);
        //出口类型
        Map<Object,String> exportType = DictUtils.getExportType();
        modelMap.addAttribute("exportType",exportType);
        //品种
        Map<Integer,String> productOilKind = DictUtils.getProductOilKind();
        modelMap.addAttribute("productOilKind",productOilKind);
        //规格
        Map<Integer,String> productOilSpecs = DictUtils.getProductOilSpecs();
        modelMap.addAttribute("productOilSpecs",productOilSpecs);


    }
    @RequestMapping("/create/otherInfoInput")
    public void otherInfoInput(CurrentUser user,Long contractId,ModelMap modelMap) {
        Contract contract = crudeContractService.queryContractInfo(contractId);
        modelMap.addAttribute("contract",contract);

        //信用条款
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.addAttribute("creditItem",creditItem);
        //商检机构
        Map<Object,String> businessCheckOrg = DictUtils.getBusinessCheckOrg();
        modelMap.addAttribute("businessCheckOrg",businessCheckOrg);
        //采购方式
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.addAttribute("purchaseModes",purchaseModes);
        //付款条款
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.addAttribute("payItemMap",payItemMap);
        //计价基准
        Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
        modelMap.addAttribute("valuationBaseMap",valuationBaseMap);
        //计价期类型
        Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
        modelMap.addAttribute("valuationProidTypeMap",valuationProidTypeMap);
        //贸易条款
        Map<Integer,String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.addAttribute("tradeItemMap",tradeItemMap);
        //地区
        Map<Object,String> regionProductOil = DictUtils.getRegionProductOil();
        modelMap.addAttribute("regionProductOil",regionProductOil);
        //出口类型
        Map<Object,String> exportType = DictUtils.getExportType();
        modelMap.addAttribute("exportType",exportType);
        //品种
        Map<Integer,String> productOilKind = DictUtils.getProductOilKind();
        modelMap.addAttribute("productOilKind",productOilKind);
        //规格
        Map<Integer,String> productOilSpecs = DictUtils.getProductOilSpecs();
        modelMap.addAttribute("productOilSpecs",productOilSpecs);


    }

    @RequestMapping("/create/pricelist")
    public void pricelist(CurrentUser user,Long contractId,ModelMap modelMap) {
        Contract contract = crudeContractService.queryContractInfo(contractId);
        ContractShip contractShip = contractShipService.queryByContractId(contractId);

        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("contractShip",contractShip);

        //贸易条款
        Map<Integer,String> TradeItemMap = DictUtils.getTradeItemMap();
        //计价期类别
        Map<Object,String> ValuationProidType = DictUtils.getValuationProidTypeMap();
        //付款条款
        Map<Object,String> PayItemMap = DictUtils.getPayItemMap();
        //采购方式
        Map<Object,String> PurchaseModes = DictUtils.getPurchaseModes();
        //计价基准
        Map<Object,String> ValuationBase = DictUtils.getValuationBaseMap();

        modelMap.addAttribute("TradeItemMap",TradeItemMap);
        modelMap.addAttribute("ValuationProidType",ValuationProidType);
        modelMap.addAttribute("PayItemMap",PayItemMap);
        modelMap.addAttribute("PurchaseModes",PurchaseModes);
        modelMap.addAttribute("ValuationBase",ValuationBase);
    }

    @RequestMapping("/create/transportInfoInput")
    public void berthlist(CurrentUser user,Long contractId,ModelMap modelMap) {
        if (contractId != null) {
            List<ContractShipBerth> listData = contractShipBerthService.queryBycontractId(contractId);
            modelMap.addAttribute("berthlist",listData);
        }
    }

    @Deprecated
    @RequestMapping("/create/staticUserInfo")
    public void staticUserInfo(CurrentUser user,Integer t,ModelMap modelMap) {
        modelMap.addAttribute("t",t);
    }

    /**
     * 确定订单推送信息
     * @param user
     * @param contractId
     * @param modelMap
     * @return
     * @throws BizException
     */
    @RequestMapping("/push/orderInfo")
    public void pushOrderInfo(CurrentUser user,Long contractId,ModelMap modelMap) {
        // 发送订单数据到后台
        try {
            crudeContractService.pushOrderInfo(contractId, user.getMemberId());
        } catch (BizException e) {
            LOGGER.error("确定订单推送信息{}",contractId);
            LOGGER.error("",e);
        }
    }

    /**
     * 查询原油订单列表
     * @param user
     * @param form
     * @param query
     * @param modelMap
     */
    @RequestMapping("/queryCurdeOilList")
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    public void queryCurdeOilList(CurrentUser user, ContractListQueryForm form, PageInfo query, ModelMap modelMap){
        try {
    		// roleType没有时默认成1
    		if(form.getRoleType() == null) {
    			form.setRoleType(1);
    		}
    		
            form.setBizType("CrudeOil");
            PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
            modelMap.addAttribute("contractListResult",pageInfoResult);
            modelMap.addAttribute("form",form);
            modelMap.addAttribute("user",user);
            //贸易条款
            Map<Integer,String> TradeItemMap = DictUtils.getTradeItemMap();
            modelMap.addAttribute("TradeItemMap",TradeItemMap);
            //付款条款
            Map<Object,String> PayItemMap = DictUtils.getPayItemMap();
            modelMap.addAttribute("PayItemMap",PayItemMap);
        } catch (BizException e) {
            LOGGER.error("查询原油订单列表{}",form);
            LOGGER.error("",e);
            modelMap.addAttribute("contractListResult",null);
            modelMap.addAttribute("form",form);
            modelMap.addAttribute("user",user);
        } catch (Exception e) {
            LOGGER.error("查询原油订单列表异常{}",form);
            LOGGER.error("",e);
            modelMap.addAttribute("contractListResult",null);
            modelMap.addAttribute("form",form);
            modelMap.addAttribute("user",user);
        }
    }

    /**
     * 查询成品油订单列表
     * @param user
     * @param form
     * @param query
     * @param modelMap
     */
    @RequestMapping("/queryProductOilList")
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    public void queryProductOilList(CurrentUser user, ContractListQueryForm form, PageInfo query, ModelMap modelMap){
    	try{
            form.setBizType("ProductOil");
            PageInfoResult pageInfoResult = crudeContractService.queryContractUnite(user,form,query);
            modelMap.addAttribute("contractListResult",pageInfoResult);
            modelMap.addAttribute("form",form);
            modelMap.addAttribute("user",user);
            //贸易条款
            Map<Integer,String> TradeItemMap = DictUtils.getTradeItemMap();
            modelMap.addAttribute("TradeItemMap",TradeItemMap);
            //付款条款
            Map<Object,String> PayItemMap = DictUtils.getPayItemMap();
            modelMap.addAttribute("PayItemMap",PayItemMap);
        }catch (BizException e){
    	    LOGGER.error("查询成品油订单列表{}",form);
    	    LOGGER.error("",e);
            modelMap.addAttribute("contractListResult",null);
            modelMap.addAttribute("form",form);
            modelMap.addAttribute("user",user);
        }
    }

    /**
     * 报价单跳转到修改原油订单页面
     * @param user
     * @param orderNo
     * @param modelMap
     */
    @RequestMapping("/skip/createCrudeOilInfo")
    public String skipupdateCrudeOilInfo(CurrentUser user,String orderNo,ModelMap modelMap){
        Contract contract = crudeContractService.queryContractInfoByOrderId(orderNo);
        if(contract!=null){
            modelMap.addAttribute("contractId",contract.getId());
        }
        return "/create/updateCrudeOilInfo";
    }
    /**
     * 报价单跳转到修改成品油订单页面
     * @param user
     * @param orderNo
     * @param modelMap
     */
    @RequestMapping("/skip/createProductOilInfo")
    public String skipProductOilInfo(CurrentUser user,String orderNo,ModelMap modelMap){
        Contract contract = crudeContractService.queryContractInfoByOrderId(orderNo);
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("productOilKindAndSpecsMap", DictUtils.getProductOilKindAndSpecs());
        return "/create/updateProductOilInfo";
    }

    /**
     * 报价单跳转到订单页面
     * @param orderNo
     */
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping("/skip/crudeOilInfoDetail")
    public String skipContractInfo(String orderNo, String errorMessage) {
        String uuid = "";
        if (StringUtils.isNotEmpty(orderNo)) {
            Contract contract = crudeContractService.queryContractInfoByOrderId(orderNo);
            if (contract != null) {
                uuid = contract.getUuid();
                if(EnumContractBizType.ProductOilContract.getCode().equals(contract.getBizType())) {
                    //成品油
                    return "redirect:" + appServerBroker.get("/buyer/productOilOrderInfo.htm").put("uuid", uuid).put("errorMessage", errorMessage);
                }
            }
        }
        //默认到原油
        return "redirect:" + appServerBroker.get("/buyer/crudeOilOrderInfo.htm").put("uuid", uuid).put("errorMessage", errorMessage);
    }

}
