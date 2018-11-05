package com.sinochem.crude.trade.listed.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.DemandForm;
import com.sinochem.crude.trade.listed.model.form.QueryForm;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilVO;
import com.sinochem.crude.trade.listed.model.vo.DemandDetailVO;
import com.sinochem.crude.trade.listed.model.vo.DemandListVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterAgentVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipBerthVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandSpecifyEnterpriseVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.DemandOrderService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: OilSaleResourcesController
 * @Description: 销售资源维护
 * @author wangn
 * @date 2018-02-24 16:22:30
 * @version V1.0
 */
@Controller
@RolesAccess(MemberConstants.SALES_TRADER)
public class OilSaleResourcesController {
    private Logger LOGGER = LoggerFactory.getLogger(PurchaseManageController.class);

    @Autowired
    private DemandService demandService;
    
    @Autowired
    private CrudeOilHallService crudeOilHallService;

    @Autowired
    private URLBroker memberServer;

    @Autowired
    private URLBroker orderServer;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandShipBerthService demandShipBerthService;

    @Autowired
    private ResourceManagerService resourceManagerService;

    @Autowired
    private DemandDetailService demandDetailService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired(required = false)
    private DemandOrderService demandOrderService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private DemandBiddingHistoryService demandBiddingHistoryService;

    @Autowired
    private DemandMessageService demandMessageService;
    
    /**
     * 销售资源维护_销售资源列表页面
     * @param user 当前用户
     * @param model
     * @return 
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_RESOURCESLIST)
    public String resourcesList(@ModelAttribute("queryForm") QueryForm queryForm, PageInfo pageInfo, CurrentUser user, ModelMap model){
        try{
            model.addAttribute("user", user);
            ResourceQuery resourceQuery = new ResourceQuery();
            resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
            resourceQuery.setType(Constant.DEMAND_TYPE_D);//需求单
            resourceQuery.setDealType(DealType.SELL.getCode());//卖
            resourceQuery.setUserEnterpriseId(user.getEpMemberId());
            resourceQuery.setPubDateStart(queryForm.getPubDateStart());
            resourceQuery.setPubDateEnd(queryForm.getPubDateEnd());
            resourceQuery.setPayItem(queryForm.getPayItem());
            resourceQuery.setDemandCode(queryForm.getDemandCode());
            PageInfoResult pageInfoResult = demandService.queryDemandJoinTableByCondition(resourceQuery, pageInfo);
            List<DemandJoinResult> list = pageInfoResult.getList();
            List<DemandListVO> VOlist = new ArrayList<>();
            for(DemandJoinResult demandJoinResult : list){
                VOlist.add(DemandListVO.convertToVO(demandJoinResult));
            }
            pageInfoResult.setList(VOlist);
            model.addAttribute("pageInfoList", pageInfoResult);
            Map<Object, String> payItemMap = DictUtils.getPayItemMap();
            model.addAttribute("payItemMap", payItemMap);
            return "/buyerCenter/oilSaleResources/resourcesList";
        }catch (Exception e){
            LOGGER.error("销售资源维护_销售资源列表页面 失败");
            LOGGER.error("",e);
            return "/buyerCenter/oilSaleResources/resourcesList";
        }
    }
    
    /**
     * 销售资源维护_批量上架
     * @param ids 待查看上架资源id串
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_BATCHADDED)
    public Result batchAdded(String ids) {
        Result result = new Result();
        try{
            if(checkResource(ids,3)){
                result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0067));
                return result;
            }
            //批量查看是否上架
            if(checkResource(ids,2)){
                result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0073));
                return result;
            }
            //批量上架
            resourceManagerService.batchUpdateStatus(ids, 2);
            result.setStatus(Result.SUCCESS);
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0068));
            return result;
        }catch (BizException e){
            LOGGER.error("批量上架失败{}",ids);
            LOGGER.error("",e);
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0069));
            return result;
        }
    }

    /**
     * 销售资源维护_批量下架
     * @param ids 待下架资源id串
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_BATCHUNDERCHARGED)
    public Result batchUnderCharged(String ids){
        Result result = new Result();
        try{
            if(checkResource(ids,3)){
                result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0067));
                return result;
            }
            //批量查看是否上架
            if(checkResource(ids,1)){
                result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0073));
                return result;
            }
            // 批量下架
            resourceManagerService.batchUpdateStatus(ids, 1);
            result.setStatus(Result.SUCCESS);
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0070));
            return result;
        }catch (BizException e){
            LOGGER.error("销售资源维护_批量下架失败{}",ids);
            LOGGER.error("",e);
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0071));
            return result;
        }
    }

    private boolean checkResource(String ids, Integer status) throws BizException{
        String opts = resourceManagerService.batchUpdateStatused(ids, status);
        if(opts.length() > 0){
            return true;
        }
        return false;
    }


    /**
     * 销售资源维护_待删除资源id串
     * @param ids
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_BATCHDELETE)
    public String batchDelete(String ids) {
       try{
           // 批量删除
           resourceManagerService.batchUpdateStatus(ids, DemandStatus.DEMAND_STATUS_0.getCode());
           // 重定向到当前页面
           return "redirect:/oilSaleResources/resourcesList.htm";
       }catch (BizException e){
           LOGGER.error("销售资源维护_待删除资源 异常{}",ids);
           LOGGER.error("",e);
           return "redirect:/oilSaleResources/resourcesList.htm";
       }
    }
    
    /**
     * 销售资源维护_增加销售需求
     * @param
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_NEWSALELEADS)
    public String newSaleLeads(CurrentUser user, ModelMap modelMap, Long demandId, String isCopy){
        try{
            modelMap.put("user", user);
            if (demandId != null) {
                DemandForm demandForm = new DemandForm();
                DemandVO demandVO = DemandVO.convertToVO(demandService.getDemandByKey(demandId));
                demandForm.setDemand(demandVO);

                DemandRelevanter demandRelevanterBuyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.SUPPLIER.getCode());
                DemandRelevanterBuyerVO demandRelevanterBuyerVO = new DemandRelevanterBuyerVO(demandRelevanterBuyer);
                demandForm.setBuyerRelevanter(demandRelevanterBuyerVO);

                DemandRelevanter demandRelevanterAgent = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId,DemandRelevanterType.AGENT.getCode());
                DemandRelevanterAgentVO demandRelevanterAgentVO = new DemandRelevanterAgentVO(demandRelevanterAgent);
                demandForm.setAgentRelevanter(demandRelevanterAgentVO);

                List<CrudeOil> curdeOilList = crudeOilResourceService.getCrudeListDemandId(demandId);
                List<CrudeOilVO> curdeOilVoList = CrudeOilVO.convertDomainToVoList(curdeOilList);
                demandForm.setCrudeOils(curdeOilVoList);

                List<DemandShip> ships = demandShipService.getShipByDemandId(demandId);
                demandForm.setDemandDetailList(DemandDetailVO.convertListToVo(demandDetailService.getDetailByDemandId(demandId)));
                if (CollectionUtils.isNotEmpty(ships)) {
                    DemandShipVO dmandShipVO = DemandShipVO.convertDomainToVo(ships.get(0));
                    demandForm.setDemandShip(dmandShipVO);
                }

                List<DemandShipBerth> demandShipBerthList = demandShipBerthService.getShipBerthByDemandId(demandId);
                List<DemandShipBerthVO> demandShipBerthVOList = DemandShipBerthVO.convertListToVo(demandShipBerthList);
                demandForm.setDemandShipBerthList(demandShipBerthVOList);

                // 复制功能需要把id清除
                if("1".equals(isCopy)) {
                    demandForm.getDemand().setId(null);
                    demandForm.getDemand().setUuid(null);

                    if(demandForm.getBuyerRelevanter() != null) {
                        demandForm.getBuyerRelevanter().setId(null);
                    }

                    if(demandForm.getAgentRelevanter() != null) {
                        demandForm.getAgentRelevanter().setId(null);
                    }

                    if(demandForm.getDemandShip() != null) {
                        demandForm.getDemandShip().setId(null);
                    }

                    if(demandForm.getCrudeOils() != null) {
                        for(int i = 0; i < demandForm.getCrudeOils().size(); i++) {
                            demandForm.getCrudeOils().get(i).setId(null);
                        }
                    }

                    if(demandForm.getDemandShipBerthList() != null) {
                        for(int i = 0; i < demandForm.getDemandShipBerthList().size(); i++) {
                            demandForm.getDemandShipBerthList().get(i).setId(null);
                        }
                    }

                    demandForm.getBuyerRelevanter().setEnterpriseName(null);

                } else {
                    modelMap.put("demandId", demandId);
                }

                // 传值
                modelMap.put("demandForm", demandForm);
            }
            EnterpriseVo ev =  enterpriseService.getByMemberId(user.getEpMemberId());
            modelMap.put("enterprise", ev);
            int center =1;
            modelMap.put("personal",center);
            modelMap.put("contractKindMap",DictUtils.getContractKindMap());
            modelMap.put("layout","layout/buyerCenter/default.vm");
            return "buyerCenter/oilSaleResources/oilSaleResources";
        }catch (BizException e){
            LOGGER.error("销售资源维护_增加销售需求失败");
            LOGGER.error("",e);
            return "buyerCenter/oilSaleResources/oilSaleResources";
        }catch (Exception e){
            LOGGER.error("销售资源维护_增加销售需求异常");
            LOGGER.error("",e);
            return "buyerCenter/oilSaleResources/oilSaleResources";
        }
    }
    
    /**
     * 销售资源维护_销售详情页面
     * @param user 当前用户
     * @param modelMap
     * @param demandId 采购需求id
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_OILSALERESOURCESDETAIL)
    public String oilSaleResourcesDetail(CurrentUser user, ModelMap modelMap, Long demandId) {
        try {
            modelMap.addAttribute("demand", demandService.getDemandByKeyAndCurrentUser(demandId, user.getEpMemberId()));
            modelMap.put("originMap",DictUtils.getCrudeOilOrigin());
        } catch (BizException biz) {
            LOGGER.error("需求查看异常！");
            LOGGER.error("",biz);
        } catch (Exception e) {
            LOGGER.error("后台处理异常！");
            LOGGER.error("",e);
        }
        int center = 1;
        modelMap.put("personal",center);
        modelMap.put("layout","layout/buyerCenter/default.vm");
        return "buyerCenter/oilSaleResources/oilSaleResourcesDetail";
    }
    
    /**
     * 销售资源维护_延期
     * @return
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_POSTPONE)
    @ResponseBody
    public Result postpone(String demandId, String stopBidTime) {
        Result result = null;
        try {
            result = demandService.postpone(demandId, stopBidTime);
        } catch (BizException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e1) {
            LOGGER.error(e1.getMessage());
            result = new Result();
            result.setCode("ERROR");
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0072));
        }

        return result;
    }
    
    /**
     * 销售资源维护_保存销售需求单  保存或发布（status:1保存 2发布）
     * @param
     * @param currentUser
     * @return
     * @throws BizException
     */
    @RequestMapping(value = UrlMapping.OILSALERESOURCES_SAVEDEMAND, method = RequestMethod.POST)
    public String saveProcurementDemand(@ModelAttribute("demandForm") @Valid DemandForm demandForm, BindingResult bindingResult, ModelMap modelMap, CurrentUser currentUser ) {
        demandForm.setSpecifyEnterpriseList();

        try {
            validForm(demandForm, bindingResult);
            if (bindingResult.hasErrors()) {
                modelMap.put("errors", bindingResult.getAllErrors());
                if(StringUtils.isNotBlank(demandForm.getPageType())
                        && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
                	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                	modelMap.put("demandForm", demandForm);
                	modelMap.put("layout","layout/crudeoillobby/default.vm");
                    return "crudeoillobby/sellingDemand";
                }else{
                	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                    modelMap.put("layout","layout/buyerCenter/default.vm");
                    return "buyerCenter/oilSaleResources/oilSaleResources";
                }
            }

            List list = new ArrayList();
            list.add(DemandRelevanterBuyerVO.convertVoToDomain(demandForm.getBuyerRelevanter()));

            DemandShip demandShip = new DemandShip();
            if (demandForm.getDemandShip() != null) {
            	demandShip = demandForm.getDemandShip().convertVoToDomain();
            }
            // 采购需求指定发布  by sijiliu 2018-01-18
            List<DemandSpecifyEnterprise> specifyEnterpriseList = CollectionUtils.isNotEmpty(demandForm.getSpecifyEnterpriseList()) ?
                    DemandSpecifyEnterpriseVO.convertListToDomain(demandForm.getSpecifyEnterpriseList()) : null;

            Demand demand = crudeOilHallService.saveSaleDemand(DemandVO.convertToDomain(demandForm.getDemand()),
                    null, //demandShipBerth被删去
                    list,
                    demandForm.getIdForm(),
                    demandForm.getCrudeOilFormList(),
                    DemandDetailVO.convertListToDomain(demandForm.getDemandDetailList()),
                    demandShip,
                    currentUser,
                    specifyEnterpriseList);
            modelMap.put("idForm", demandForm.getIdForm());

            // 状态为发布时
            if (DemandStatus.DEMAND_STATUS_2.getCode() == demandForm.getDemand().getStatus()) {
                //向所有定向企业发送消息
                demandMessageService.demandDirectionalRelease(demand.getId());
            }

            return "redirect:/oilSaleResources/resourcesList.htm";
        } catch(BizException e) {
            modelMap.put("errorMessage", e.getMessage());
            e.printStackTrace();
            if(StringUtils.isNotBlank(demandForm.getPageType())
                    && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
            	modelMap.put("demandForm", demandForm);
            	modelMap.put("layout","layout/crudeoillobby/default.vm");
            	return "crudeoillobby/sellingDemand";
            }else{
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                modelMap.put("layout","layout/buyerCenter/default.vm");
                return "buyerCenter/oilSaleResources/oilSaleResources";
            }
        } catch (Exception ex){
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0040));
            LOGGER.error("",ex);
            if(StringUtils.isNotBlank(demandForm.getPageType())
                    && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
            	modelMap.put("demandForm", demandForm);
            	modelMap.put("layout","layout/crudeoillobby/default.vm");
            	return "crudeoillobby/sellingDemand";
            }else{
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                modelMap.put("layout","layout/buyerCenter/default.vm");
                return "buyerCenter/oilSaleResources/oilSaleResources";
            }
        }
    }
    
    /**
     * 销售资源维护_保存销售需求单  保存或发布（status:1保存 2发布）校验
     * @param
     * @return
     */
    private void validForm(DemandForm demandForm, BindingResult bindingResult) throws BizException {
    	DemandRelevanterBuyerVO buyerRelevanter = demandForm.getBuyerRelevanter();
        if (buyerRelevanter != null) {
//            String phoneNo = buyerRelevanter.getPhoneNo();
//            if (StringUtils.isNotBlank(phoneNo) && !Pattern.matches("^((0\\d{2,3}-\\d{7,8})|(1\\d{10}))$", phoneNo))
//                bindingResult.rejectValue("buyerRelevanter.phoneNo", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0074));
            String email = buyerRelevanter.getFamil();
            if (StringUtils.isNotBlank(email) && !Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$", email))
                bindingResult.rejectValue("buyerRelevanter.famil", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0075));
        }

        int  i = 0;
        for(CrudeOilForm dd : demandForm.getCrudeOilFormList()){
            if("".equals(dd.getName())){
                bindingResult.rejectValue("crudeOilFormList["+i+"].name", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0076));
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0077).replaceAll("XXX",(i+1)+""));
            }
            i++;
        }

        if(null == demandForm.getCrudeOilFormList() || demandForm.getCrudeOilFormList().size()==0)
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0078));
        if(demandForm.getDemand().getValuationProidType()==4)
            if(demandForm.getDemand().getValuationProidEnd()==null || demandForm.getDemand().getValuationProidStart()==null)
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0079));
        if(demandForm.getDemandShip().getDischargeStartTime()!=null && demandForm.getDemandShip().getDischargeEndTime() !=null){
            if(demandForm.getDemandShip().getDischargeEndTime().getTime() < demandForm.getDemandShip().getDischargeStartTime().getTime()){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0080));
            }
        }
        if(demandForm.getDemandShip().getShipmentStartTime()!=null && demandForm.getDemandShip().getShipmentEndTime() !=null){
            if(demandForm.getDemandShip().getShipmentStartTime().getTime() > demandForm.getDemandShip().getShipmentEndTime().getTime()){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0081));
            }
        }

        if(demandForm.getDemand().getSpecified() != null && demandForm.getDemand().getSpecified() == 1) {
            if(StringUtil.isEmpty(demandForm.getEpMemberIds()))
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0082));
        }
    }
    
}
