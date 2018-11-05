package com.sinochem.crude.trade.listed.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.util.DateUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.enums.InspectionFeeSharingType;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandDetail;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.enums.BizType;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.enums.PurchaseType;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilVO;
import com.sinochem.crude.trade.listed.model.vo.DemandJoinVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterSupplierVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipBerthVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;
import com.sinochem.crude.trade.listed.service.CrudeOilResourceService;
import com.sinochem.crude.trade.listed.service.DemandDetailService;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.listed.service.DemandShipBerthService;
import com.sinochem.crude.trade.listed.service.DemandShipService;
import com.sinochem.crude.trade.listed.service.SpecifyEnterpriceService;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 需求详情页面
 */

@Controller
@RolesAccess("trade_oper")
public class CrudeOilHallFormController {

    private Logger LOGGER = LoggerFactory.getLogger(CrudeOilHallFormController.class);

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandShipBerthService demandShipBerthService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private DemandDetailService demandDetailService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired(required=false)
    private EnterpriseService enterpriseService;

    @Autowired
    private SpecifyEnterpriceService specifyEnterpriceService;

      /**
     * 采购信息回显 PathVariable
     *
     * @param demandId
     * @param modelMap
     */
      //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_PRICEDETAIL,method = RequestMethod.GET)
    public void queryDemand(@RequestParam("demandId") Long demandId, ModelMap modelMap){
        //通过demandId 查询
        try{
            if(demandId != null) {
                Demand demand =  demandService.getDemandByKey(demandId);
                DemandVO demondVo = DemandVO.convertToVO(demand);
                modelMap.put("demand",demondVo);
            }
            modelMap.put("demandShips", getShipByDemandId(demandId));
            Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
            modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> payItemMap =DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> purchaseModeMap = DictUtils.getPurchaseModes();
            modelMap.put("purchaseModeMap",purchaseModeMap);
            modelMap.put("valuationBaseMap", DictUtils.getValuationBaseMap());
            modelMap.put("measureModeMap", DictUtils.getMeasureModeMap());
        }catch (BizException e){
            LOGGER.error("采购信息回显");
            LOGGER.error("",e);
        }catch (Exception e){
            LOGGER.error("采购信息回显");
            LOGGER.error("",e);
        }
    }

    /**
     * 需求、采购信息对比回显 PathVariable
     *
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_PRICEDETAIL_COMPARE)
    public void queryDemandCompare(@RequestParam("demandId") Long demandId, ModelMap modelMap){
        //通过demandId 查询
        try {
            if (demandId != null) {
                Demand demand =  demandService.getDemandByKey(demandId);
                DemandVO demandVO = DemandVO.convertToVO(demand);

                modelMap.put("demand", demandVO);
                modelMap.put("demandShips", getShipByDemandId(demandId));
                if (null != demand.getParentId()) {//查询需求单
                    modelMap.put("demandParent", DemandVO.convertToVO(demandService.getDemandByKey(demand.getParentId())));
                    modelMap.put("demandParentShips", getShipByDemandId(demand.getParentId()));
                }
            }
            Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
            modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> payItemMap =DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> purchaseModeMap = DictUtils.getPurchaseModes();
            modelMap.put("purchaseModeMap",purchaseModeMap);
            modelMap.put("valuationBaseMap", DictUtils.getValuationBaseMap());
            modelMap.put("authItemBaseMap", DictUtils.getCreditItem());
            modelMap.put("measureModeMap", DictUtils.getMeasureModeMap());
        }catch (BizException e){
            LOGGER.error("需求、采购信息对比回显失败");
            LOGGER.error("",e);
        }catch (Exception e){
            LOGGER.error("需求、采购信息对比回显失败");
            LOGGER.error("",e);
        }
    }

    /**
     *查询泊位信息
     * @param demandId
     * @param modelMap
     */

    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_BERTHLIST)
    public void queryDemandBerth(Long demandId,ModelMap modelMap){
       try{
           if (demandId!=null){
               List<DemandShipBerth> demandShipBerthList =  demandShipBerthService.getShipBerthByDemandId(demandId);
               List<DemandShipBerthVO> demandShipBerthVOList = new ArrayList<>();
               for (DemandShipBerth demandShipBerth : demandShipBerthList) {
                   DemandShipBerthVO demandShipBerthVO = DemandShipBerthVO.convertDomainToVo(demandShipBerth);
                   demandShipBerthVOList.add(demandShipBerthVO);
               }

               modelMap.put("demandShipBerthList", demandShipBerthVOList);
           }
       }catch (BizException e){
           LOGGER.error("",e);
       }catch (Exception e){
           LOGGER.error("",e);
       }
    }

    /**
     *查询运输、泊位信息
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_BERTHLISTT)
    public void queryDemandBerthT (Long demandId,ModelMap modelMap) {
        try {
            if (demandId!=null){
                // 泊位信息
                List<DemandShipBerth> demandShipBerthList =  demandShipBerthService.getShipBerthByDemandId(demandId);
                modelMap.put("demandShipBerthList",demandShipBerthList);
                // 运输信息
                List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
                if (CollectionUtils.isNotEmpty(demandShips)) {
                    modelMap.put("demandShips",demandShips.get(0));
                }
                // 船型字典项
                modelMap.put("shipTypeMap", DictUtils.getShipTypes());
            }
        } catch (BizException e) {
            LOGGER.error("运输泊位信息获取异常：", e.getMessage());
            LOGGER.error("",e);
        }catch (Exception e){
            LOGGER.error("运输泊位信息获取异常");
            LOGGER.error("",e);
        }
    }

    /**
     *查询demand通过uuid
     * @param uuid
     * @param modelMap
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_QUERYDEMANDBYUUID)
    public String queryDemandByUuid(@RequestParam("uuid") String uuid, ModelMap modelMap){
       try{
           //通过uuid查询demand
           Demand demand = demandService.getDemandByUuid(uuid);
           DemandVO demandVO = DemandVO.convertToVO(demand);
           modelMap.put("demand", demandVO);

           //通过demandId查询demandShip
           List<DemandShip> demandShips =  demandShipService.getShipByDemandId(demand.getId());
           DemandShip demandShip =  demandShips.get(0);
           DemandShipVO demandShipVO = DemandShipVO.convertDomainToVo(demandShip);
           modelMap.put("demandShip", demandShipVO);

           return "crudeoillobby/purchasingDemand";
       }catch (BizException e){
           LOGGER.error("",e);
           return "crudeoillobby/purchasingDemand";
       }catch (Exception e){
           LOGGER.error("",e);
           return "crudeoillobby/purchasingDemand";
       }
    }

    /**
     *查询denand列表
     * @param modelMap
     * @param demandId
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_PRICELIST)
    public void queryDemandList(ModelMap modelMap,Long demandId){
        try{
            if(demandId!=null){
                Demand demand = demandService.getDemandByKey(demandId);
                DemandVO demandVO = DemandVO.convertToVO(demand);
                modelMap.put("demand",demandVO);

                List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
                DemandShip demandShip = demandShips.get(0);
                DemandShipVO demandShipVO = DemandShipVO.convertDomainToVo(demandShip);
                modelMap.put("demandShip",demandShipVO);
            }
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
            modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
            modelMap.put("purchaseModes",purchaseModes);
            Map<Object,String> valuationBase = DictUtils.getValuationBaseMap();
            modelMap.put("valuationBase",valuationBase);
            Map<Object,String> payItemMap = DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> measureModeMap = DictUtils.getMeasureModeMap();
            modelMap.put("measureModeMap",measureModeMap);
            Map<Object,String> contractKindMap = DictUtils.getContractKindMap();
            modelMap.put("contractKindMap", contractKindMap);
        }catch (BizException e){
            LOGGER.error("",e);
        }catch (Exception e){
            LOGGER.error("",e);
        }

    }

    /**
     *查询demand相关项
     * @param demandId
     * @param modelMap
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_BUYERLIST)
    public void quertDemandRelevanter1(Long demandId,ModelMap modelMap){
       try{
           DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.BUYER.getCode());
           DemandRelevanterBuyerVO vo = new DemandRelevanterBuyerVO(demandRelevanter1);
           modelMap.put("demandRelevanter1", vo);
       }catch (Exception e){
           LOGGER.error("",e);
       }
    }

    /**
     * 查询demand相关项
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SUPPLIERLIST)
    public void quertDemandRelevanter3(Long demandId,ModelMap modelMap){
       try {
           DemandRelevanter demandRelevanter3 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId,DemandRelevanterType.SUPPLIER.getCode());
           DemandRelevanterSupplierVO vo = new DemandRelevanterSupplierVO(demandRelevanter3);
           modelMap.put("demandRelevanter3", vo);
       }catch (BizException e){
           LOGGER.error("",e);
       }catch (Exception e){
           LOGGER.error("",e);
       }
    }

    /**
     *查询原油列表
     * @param demandId
     * @param modelMap
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_OILTYPELIST)
    public void queryCrudeOilList(Long demandId,ModelMap modelMap){
       List<CrudeOil> crudeOilList =  crudeOilResourceService.getCrudeListDemandId(demandId);

        for (CrudeOil cor: crudeOilList) {
            if(cor.getOrigin() != null) {
                CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findByENameAndAreaId(cor.getOrigin());
                if (crudeOilInfoVO != null) {
                	cor.setAreaString(VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}}));
                }
            }
        }

        List<CrudeOilVO> crudeOilListVO = CrudeOilVO.convertDomainToVoList(crudeOilList);

        modelMap.put("crudeOilList", crudeOilListVO);
    }

    /**
     * 查询原油列表
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDEOILLIST)
    public void queryCrudeOilListt(Long demandId,ModelMap modelMap){
        try{
            if (demandId != null) {
                List<CrudeOil> crudeOilList =  crudeOilResourceService.getCrudeListDemandId(demandId);
                for (CrudeOil cor: crudeOilList) {

                    CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findByENameAndAreaId(cor.getOrigin());
                    if (crudeOilInfoVO != null) {
                        cor.setAreaString(VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}}));
                        cor.setOriginName(VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}}));
                    }
                }
                modelMap.put("crudeOilResourceList", crudeOilList);
            }
        }catch (Exception e){
            LOGGER.error("",e);
        }
    }

    /**
     * added by Yichen Zhao on 20180114
     * 原油大厅的需求招标
     * @param pageInfo
     * @param map
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDEOILBIDDINGLIST)
    public void getCrudeOilBiddingList(CurrentUser user, PageInfo pageInfo, ModelMap map) {
        try {
            if(user == null || user.getEpMemberId() == null){
                return;
            }
            
            pageInfo.setPageSize(Constant.TRADE_MALL_MAX_DATA_QUANTITY);

            /**招标*/
            ResourceQuery biddingDemandQuery = new ResourceQuery();
            biddingDemandQuery.setStatus(DemandStatus.DEMAND_STATUS_2.getCode().toString());
            biddingDemandQuery.setPurchaseType(PurchaseType.BIDDING.getCode().toString());
            biddingDemandQuery.setBizType(BizType.CRUDE_OIL.getCode());
            biddingDemandQuery.setType(Constant.DEMAND_TYPE_D);//需求单
            biddingDemandQuery.setDealType(DealType.BUY.getCode());//买
            biddingDemandQuery.setStopBidTimeBefore(new Date());
            biddingDemandQuery.setEpMemberId(user.getEpMemberId());

            PageInfoResult biddingDemandPageInfoResult = demandService.queryDemandJoinTableByCondition(biddingDemandQuery, pageInfo);
            List<DemandJoinResult> biddingDemandResultList = biddingDemandPageInfoResult.getList();
            
            // 有效截止日期记录数
            int cnt = biddingDemandResultList.size();
            
            if (cnt == 0) {
            	biddingDemandPageInfoResult = demandService.queryInvalidDemandJoinTableByCondition(biddingDemandQuery, pageInfo);
                biddingDemandResultList = biddingDemandPageInfoResult.getList();
            }
            
            List<DemandJoinVO> biddingDemandVOList = new ArrayList<>();
            for (DemandJoinResult biddingDemandJoinResult : biddingDemandResultList) {
                DemandJoinVO biddingDemandJoinVO = DemandJoinVO.convertToVO(biddingDemandJoinResult);
                decorateDemandJoinVO(biddingDemandJoinVO, biddingDemandJoinResult);
                biddingDemandVOList.add(biddingDemandJoinVO);
                // 只显示一条无效截止日期记录
                if (cnt == 0) {
                	break;
                }
            }
            biddingDemandPageInfoResult.setList(biddingDemandVOList);
            map.put("bidding", biddingDemandPageInfoResult);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * added by Yichen Zhao on 20180114
     * 原油大厅的需求询价
     * @param pageInfo
     * @param map
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDEOILREQUIRELIST)
    public void getCrudeOilRequireList(CurrentUser user, PageInfo pageInfo, ModelMap map) {
        try {
            if(user == null || user.getEpMemberId() == null){
                return;
            }
            
            pageInfo.setPageSize(Constant.TRADE_MALL_MAX_DATA_QUANTITY);

            /**询价*/
            ResourceQuery requireDemandQuery = new ResourceQuery();
            requireDemandQuery.setStatus(DemandStatus.DEMAND_STATUS_2.getCode().toString());
            requireDemandQuery.setPurchaseType(PurchaseType.ENQUIRY.getCode().toString());
            requireDemandQuery.setBizType(BizType.CRUDE_OIL.getCode());
            requireDemandQuery.setType(Constant.DEMAND_TYPE_D);//需求单
            requireDemandQuery.setDealType(DealType.BUY.getCode());//买
            requireDemandQuery.setStopBidTimeBefore(new Date());

            requireDemandQuery.setEpMemberId(user.getEpMemberId());

            PageInfoResult enquiryDemandPageInfoResult =  demandService.queryDemandJoinTableByCondition(requireDemandQuery, pageInfo);
            List<DemandJoinResult> enquiryDemandResultList = enquiryDemandPageInfoResult.getList();
            
            // 有效截止日期记录数
            int cnt = enquiryDemandResultList.size();
            
            if (cnt == 0) {
            	enquiryDemandPageInfoResult =  demandService.queryInvalidDemandJoinTableByCondition(requireDemandQuery, pageInfo);
                enquiryDemandResultList = enquiryDemandPageInfoResult.getList();
            }
            
            List<DemandJoinVO> enquiryDemandVOList = new ArrayList<>();
            for (DemandJoinResult enquiryDemandJoinResult : enquiryDemandResultList) {
                DemandJoinVO enquiryDemandJoinVO = DemandJoinVO.convertToVO(enquiryDemandJoinResult);
                decorateDemandJoinVO(enquiryDemandJoinVO, enquiryDemandJoinResult);
                enquiryDemandVOList.add(enquiryDemandJoinVO);
                // 只显示一条无效截止日期记录
                if (cnt == 0) {
                	break;
                }
            }
            enquiryDemandPageInfoResult.setList(enquiryDemandVOList);
            map.put("require", enquiryDemandPageInfoResult);

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * added by Yichen Zhao on 20180114
     * 原油大厅的热点需求
     * @param pageInfo
     * @param map
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDEOILHOTDEMANDLIST)
    public void getCrudeOilHotDemandList(PageInfo pageInfo, ModelMap map, CurrentUser user) {
        try {
            pageInfo.setPageSize(Constant.TRADE_MALL_MAX_DATA_QUANTITY);

            /**热点需求*/
            ResourceQuery hotDemandQuery = new ResourceQuery();
            hotDemandQuery.setStatus(DemandStatus.DEMAND_STATUS_2.getCode().toString());
            hotDemandQuery.setBizType(BizType.CRUDE_OIL.getCode());
            hotDemandQuery.setStopBidTimeBefore(new Date());
            hotDemandQuery.setEpMemberId(user.getEpMemberId());

            pageInfo.setSize(Constant.HOT_DEMAND_QUANTITY);
            PageInfoResult hotDemandPageInfoResult =  demandService.queryDemandJoinTableByCondition(hotDemandQuery, pageInfo);
            List<DemandJoinResult> hotDemandResultList = hotDemandPageInfoResult.getList();
            List<DemandJoinVO> hotDemandVOList = new ArrayList<>();
            for (DemandJoinResult hotDemandResult : hotDemandResultList) {
                DemandJoinVO hotDemandVO = DemandJoinVO.convertToVO(hotDemandResult);
                decorateDemandJoinVO(hotDemandVO, hotDemandResult);
                hotDemandVOList.add(hotDemandVO);
            }
            hotDemandPageInfoResult.setList(hotDemandVOList);
            map.put("hotDemand", hotDemandPageInfoResult);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    /**
     * 通过demandid获取demand
     * @param user
     * @param demandId
     * @param modelMap
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SKETCHLIST)
    public void getDemandByDemandId(CurrentUser user,@RequestParam("demandId") Long demandId, ModelMap modelMap){
        try{
            modelMap.put("user",user);
            if(demandId!=null){
                Demand demand = demandService.getDemandByKey(demandId);
                DemandVO demandVO = DemandVO.convertToVO(demand);
                modelMap.put("demand",demandVO);
                Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
                modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            }
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> payItemMap = DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
            modelMap.put("valuationBaseMap",valuationBaseMap);
        }catch (Exception e){
            LOGGER.error("",e);
        }
    }

    /**
     * 查询demand详情
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_RICHTEXT,method = RequestMethod.GET)
    public void queryDemandDetail(Long demandId,ModelMap modelMap){
        if(demandId!=null){
            List<DemandDetail> demandDetail = demandDetailService.getDetailByDemandId(demandId);
            modelMap.put("demandDetail",demandDetail.get(0));
        }
    }

    /**
     * 查询demand船务
     * @param demandId
     * @param modelMap
     * @return
     * @throws BizException
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_PRICELISTTWO)
    public String queryDemandShip(Long demandId, ModelMap modelMap) throws BizException {
        /*if(demandId!=null){
            Demand demand = demandService.getDemandByKey(demandId);
            DemandVO demandVO = DemandVO.convertToVO(demand);
            modelMap.put("demand", demandVO);
            modelMap.put("demandShips", getShipByDemandId(demandId));
        }*/

        Map<Object, String> inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
        modelMap.put("inspectionFeeSharingTypeMap", inspectionFeeSharingTypeMap);
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.put("tradeItemMap",tradeItemMap);
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.put("purchaseModes",purchaseModes);
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.put("payItemMap",payItemMap);
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.put("creditItem",creditItem);
        Map<Object,String> shipTypes = DictUtils.getShipTypes();
        modelMap.put("shipTypes",shipTypes);
        modelMap.put("valuationProidTypeMap", DictUtils.getValuationProidTypeMap());
        Map<Object,String> valuationBase = DictUtils.getValuationBaseMap();
        modelMap.put("valuationBase",valuationBase);
        Map<Object,String> measureModeMap = DictUtils.getMeasureModeMap();
        modelMap.put("measureModeMap",measureModeMap);
        Map<Object,String> contractKindMap = DictUtils.getContractKindMap();
        modelMap.put("contractKindMap",contractKindMap);

        return "/crudeoilhallform/pricelistTwo";
    }

    /**
     * 查询泊位信息
     * @param demandId
     * @param modelMap
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_BERTHLISTNEW)
    public void queryDemandShipBerth(Long demandId, ModelMap modelMap){
        List<DemandShipBerth> demandShipBerthList = null;
        try {
            demandShipBerthList = demandShipBerthService.getShipBerthByDemandId(demandId);
            List<DemandShipBerthVO> demandShipBerthVOList = DemandShipBerthVO.convertListToVo(demandShipBerthList);
            modelMap.put("demandShipBerthList",demandShipBerthVOList);

            Map<Object,String> shipTypeMap = DictUtils.getShipTypes();
            modelMap.put("shipTypeMap",shipTypeMap);
        } catch (BizException e) {
            LOGGER.error("泊位信息读取异常：", e.getMessage());
        }catch (Exception e){
            LOGGER.error("", e);
        }
    }

    /**
     * 查询供应商详情
     * @param user
     * @param demandAgain
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SUPPLIERDETAIL)
    public void querySupplierDetail(CurrentUser user,Long demandAgain,ModelMap modelMap){
    	// 回显删除
        /*if(user!=null){
            EnterpriseVo ev =  enterpriseService.getByMemberId(user.getEpMemberId());
            modelMap.put("enterprise", ev);
        }
        modelMap.put("user",user);
        modelMap.put("demandAgainModel","0");
        if(demandAgain != null){
            modelMap.put("demandAgainModel","1");
            modelMap.put("demandRelevanter",demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandAgain,"P"));
        }*/
    	
    	modelMap.put("user",user);
    }

    /**
     * 查询买家详情
     * @param user
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SALEBUYERDETAIL)
    public void saleBuyerDetail(CurrentUser user,ModelMap modelMap){
    	// 回显删除
        /*try{
            if(user!=null){
                EnterpriseVo ev =  enterpriseService.getByMemberId(user.getEpMemberId());
                modelMap.put("enterprise", ev);
            }
            modelMap.put("user",user);
        }catch (Exception e){
            LOGGER.error("",e);
        }*/
    	
    	modelMap.put("user",user);
    }

    /**
     * 查询买家企业信息
     * @param modelMap
     * @param currentUser
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_BUYERDETATIL)
    public void queryBuyyer(ModelMap modelMap,CurrentUser currentUser){
        try{
            if(currentUser!=null){
                EnterpriseVo ev =  enterpriseService.getByMemberId(currentUser.getEpMemberId());
                modelMap.put("enterprise", ev);
                int judge = 1;
                modelMap.put("judge",judge);
            }
            modelMap.put("user",currentUser);
        }catch (Exception e){
            LOGGER.error("",e);
        }
    }

    /**
     * 详情页面获取需求的定向企业信息
     * @param model
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SPECIFYENTERPRISESHOW)
    public void specifyEnterpriseShow(ModelMap model, Long demandId) {
       try{
           model.put("specifyEnterpriseList", specifyEnterpriceService.getDemandSpecifyEnterpriseListByDemandId(demandId));
       }catch (Exception e){
           LOGGER.error("",e);
       }
    }

    /**
     * 定向企业信息列表
     * @param user
     * @param model
     * @param demandId
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SPECIFYENTERPRISE)
    public void specifyEnterprise(CurrentUser user,ModelMap model, Long demandId) {
       try{
           model.put("user", user);
           if (demandId != null) {
               model.put("demand", demandService.getDemandByKey(demandId));
               model.put("specifyEnterpriseList", specifyEnterpriceService.getDemandSpecifyEnterpriseListByDemandId(demandId));
           }
       }catch (Exception e){
           LOGGER.error("",e);
       }
    }

    /**
     * 根据demandId获取船务信息
     * @param demandId
     */
    private DemandShipVO getShipByDemandId(Long demandId) throws BizException {
        if(demandId == null) {
            return null;
        }
        List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
        if (CollectionUtils.isNotEmpty(demandShips)) {
            DemandShip demandship = demandShips.get(0);
            DemandShipVO demandShipVO = new DemandShipVO();
            demandShipVO.setDischargePort(demandship.getDischargePort());
            
            if(StringUtils.isNotBlank(demandship.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(demandship.getDischargePort())){
    			String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(demandship.getDischargePort());
    			String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
    			demandShipVO.setDischargePort(unLoadPort);
    		}
            
            demandShipVO.setShipmentPort(demandship.getShipmentPort());
            demandShipVO.setShipmentStartTime(demandship.getShipmentStartTime());
            demandShipVO.setDischargeEndTime(demandship.getDischargeEndTime());
            demandShipVO.setShipmentEndTime(demandship.getShipmentEndTime());
            demandShipVO.setDischargeStartTime(demandship.getDischargeStartTime());
            return demandShipVO;
        }
        return null;
    }

    private void decorateDemandJoinVO(DemandJoinVO demandJoinVO, DemandJoinResult demandJoinResult) {
        Long num = demandJoinResult.getNum();
        if (num != null) {
            DecimalFormat df = new DecimalFormat("#0");
            double numConvert = num / (Constant.DEMAND_NUM_AMPLIFY_SCALE * Constant.TEN_THOUSAND);
            double numBBL = num / (Constant.DEMAND_NUM_AMPLIFY_SCALE);
            demandJoinVO.setNum(df.format(numConvert));
            demandJoinVO.setNumBBL(df.format(numBBL));
        }

        String crudeOilOptions = demandJoinResult.getCrudeOilOptions();
        if (!StringUtil.isEmpty(crudeOilOptions) && crudeOilOptions.endsWith(",")) {
            demandJoinVO.setCrudeOilOptions(crudeOilOptions.substring(0, crudeOilOptions.length() - 1));
        }

        Date stopBidTime = demandJoinResult.getStopBidTime();
        if(stopBidTime != null){
            try {
                Date today = DateUtil.getToday().getTime();
                demandJoinVO.setOverdue(today.after(stopBidTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            demandJoinVO.setOverdue(false);
        }
    }
    
    /**
     * 销售资源维护_原油大厅_查看销售资源（概要信息）
     * @param
     * @return
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SELLSKETCHLIST)
    public void getSellDemandByDemandId(CurrentUser user,@RequestParam("demandId") Long demandId, ModelMap modelMap){
       try{
           modelMap.put("user",user);
           if(demandId!=null){
               Demand demand = demandService.getDemandByKey(demandId);
               DemandVO demandVO = DemandVO.convertToVO(demand);
               modelMap.put("demand",demandVO);
               Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
               modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
           }
           Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
           modelMap.put("tradeItemMap",tradeItemMap);
           Map<Object,String> payItemMap = DictUtils.getPayItemMap();
           modelMap.put("payItemMap",payItemMap);
           Map<Object,String> valuationBaseMap = DictUtils.getValuationBaseMap();
           modelMap.put("valuationBaseMap",valuationBaseMap);
       }catch (Exception e){
           LOGGER.error("销售资源维护_原油大厅_查看销售资源 失败");
           LOGGER.error("",e);
       }
    }
    
    /**
     * 销售资源维护_原油大厅_查看销售资源（卖家信息）
     * @param
     * @return
     */
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
	@RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SELLERLIST)
    public void queryDemandRelevanter(Long demandId,ModelMap modelMap){
       try{
           DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.SUPPLIER.getCode());
           DemandRelevanterBuyerVO vo = new DemandRelevanterBuyerVO(demandRelevanter1);
           modelMap.put("demandRelevanter1", vo);
       }catch (Exception e){
           LOGGER.error("销售资源维护_原油大厅_查看销售资源失败");
           LOGGER.error("",e);
       }
    }
    
	/**
     * 销售资源维护_新增销售资源（销售信息）
     * @param
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SELLPRICELIST)
    public void querySellDemandList(ModelMap modelMap,Long demandId){
        try{
            if(demandId!=null){
                Demand demand = demandService.getDemandByKey(demandId);
                DemandVO demandVO = DemandVO.convertToVO(demand);
                modelMap.put("demand",demandVO);

                List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
                DemandShip demandShip = demandShips.get(0);
                DemandShipVO demandShipVO = DemandShipVO.convertDomainToVo(demandShip);
                modelMap.put("demandShip",demandShipVO);
            }
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
            modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
            modelMap.put("purchaseModes",purchaseModes);
            Map<Object,String> valuationBase = DictUtils.getValuationBaseMap();
            modelMap.put("valuationBase",valuationBase);
            Map<Object,String> payItemMap = DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> measureModeMap = DictUtils.getMeasureModeMap();
            modelMap.put("measureModeMap",measureModeMap);
            Map<Object,String> inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
            modelMap.put("inspectionFeeSharingTypeMap",inspectionFeeSharingTypeMap);

            Map<Object,String> contractKindMap = DictUtils.getContractKindMap();
            modelMap.put("contractKindMap", contractKindMap);
        }catch (BizException e){
            LOGGER.error("销售资源维护_新增销售资源失败");
            LOGGER.error("",e);
        }catch (Exception e){
            LOGGER.error("销售资源维护_新增销售资源失败");
            LOGGER.error("",e);
        }
    }
    
    /**
     * 销售资源维护_新增销售资源（卖家信息）
     * @param
     * @param currentUser
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SELLERDETAIL)
    public void querySeller(ModelMap modelMap,CurrentUser currentUser){
       try{
           if(currentUser!=null){
               EnterpriseVo ev =  enterpriseService.getByMemberId(currentUser.getEpMemberId());
               modelMap.put("enterprise", ev);
               int judge = 1;
               modelMap.put("judge",judge);
           }
           modelMap.put("user",currentUser);
       }catch (Exception e){
           LOGGER.error("销售资源维护_新增销售资源失败");
           LOGGER.error("",e);
       }
    }
    
    /**
     * 销售资源维护_查看销售资源（销售信息）
     * @param
     * @return
     */
	//@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
	@RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_SELLPRICEDETAIL,method = RequestMethod.GET)
    public void querySellDemand(@RequestParam("demandId") Long demandId, ModelMap modelMap){
       try{
           //通过demandId 查询
           if(demandId != null) {
               Demand demand =  demandService.getDemandByKey(demandId);
               DemandVO demondVo = DemandVO.convertToVO(demand);
               modelMap.put("demand",demondVo);
           }
           modelMap.put("demandShips", getShipByDemandId(demandId));
           Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
           modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
           Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
           modelMap.put("tradeItemMap",tradeItemMap);
           Map<Object,String> payItemMap =DictUtils.getPayItemMap();
           modelMap.put("payItemMap",payItemMap);
           Map<Object,String> purchaseModeMap = DictUtils.getPurchaseModes();
           modelMap.put("purchaseModeMap",purchaseModeMap);
           modelMap.put("valuationBaseMap", DictUtils.getValuationBaseMap());
           modelMap.put("measureModeMap", DictUtils.getMeasureModeMap());
       }catch (BizException e){
           LOGGER.error("销售资源维护_查看销售资源失败");
           LOGGER.error("",e);
       }catch (Exception e){
           LOGGER.error("销售资源维护_查看销售资源失败");
           LOGGER.error("",e);
       }
    }
    
    /**
     * 销售资源维护_原油大厅的需求招标
     * @param
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDESELLBIDDINGLIST)
    public void getCrudeSellBiddingList(CurrentUser user, PageInfo pageInfo, ModelMap map) {
        try {
            if(user == null || user.getEpMemberId() == null){
                return;
            }
            
            pageInfo.setPageSize(Constant.TRADE_MALL_MAX_DATA_QUANTITY);

            /**招标*/
            ResourceQuery biddingDemandQuery = new ResourceQuery();
            biddingDemandQuery.setStatus(DemandStatus.DEMAND_STATUS_2.getCode().toString());
            biddingDemandQuery.setPurchaseType(PurchaseType.BIDDING.getCode().toString());
            biddingDemandQuery.setBizType(BizType.CRUDE_OIL.getCode());
            biddingDemandQuery.setType(Constant.DEMAND_TYPE_D);//需求单
            biddingDemandQuery.setDealType(DealType.SELL.getCode());//卖
            biddingDemandQuery.setStopBidTimeBefore(new Date());
            biddingDemandQuery.setEpMemberId(user.getEpMemberId());

            PageInfoResult biddingDemandPageInfoResult = demandService.queryDemandJoinTableByCondition(biddingDemandQuery, pageInfo);
            List<DemandJoinResult> biddingDemandResultList = biddingDemandPageInfoResult.getList();
            
            // 有效截止日期记录数
            int cnt = biddingDemandResultList.size();
            
            if (cnt == 0) {
            	biddingDemandPageInfoResult = demandService.queryInvalidDemandJoinTableByCondition(biddingDemandQuery, pageInfo);
            	biddingDemandResultList = biddingDemandPageInfoResult.getList();
            }
            
            List<DemandJoinVO> biddingDemandVOList = new ArrayList<>();
            for (DemandJoinResult biddingDemandJoinResult : biddingDemandResultList) {
                DemandJoinVO biddingDemandJoinVO = DemandJoinVO.convertToVO(biddingDemandJoinResult);
                decorateDemandJoinVO(biddingDemandJoinVO, biddingDemandJoinResult);
                biddingDemandVOList.add(biddingDemandJoinVO);
                // 只显示一条无效截止日期记录
                if (cnt == 0) {
                	break;
                }
            }
            biddingDemandPageInfoResult.setList(biddingDemandVOList);
            map.put("bidding", biddingDemandPageInfoResult);
            
        } catch (Exception ex) {
            LOGGER.error("销售资源维护_原油大厅的需求招标失败");
            LOGGER.error("",ex);
        }
    }
    
    /**
     * 销售资源维护_原油大厅的需求询价
     * @param
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILHALLFORM_CRUDESELLREQUIRELIST)
    public void getCrudeSellRequireList(CurrentUser user, PageInfo pageInfo, ModelMap map) {
        try {
            if(user == null || user.getEpMemberId() == null){
                return;
            }
            
            pageInfo.setPageSize(Constant.TRADE_MALL_MAX_DATA_QUANTITY);

            /**询价*/
            ResourceQuery requireDemandQuery = new ResourceQuery();
            requireDemandQuery.setStatus(DemandStatus.DEMAND_STATUS_2.getCode().toString());
            requireDemandQuery.setPurchaseType(PurchaseType.ENQUIRY.getCode().toString());
            requireDemandQuery.setBizType(BizType.CRUDE_OIL.getCode());
            requireDemandQuery.setType(Constant.DEMAND_TYPE_D);//需求单
            requireDemandQuery.setDealType(DealType.SELL.getCode());//卖
            requireDemandQuery.setStopBidTimeBefore(new Date());

            requireDemandQuery.setEpMemberId(user.getEpMemberId());

            PageInfoResult enquiryDemandPageInfoResult =  demandService.queryDemandJoinTableByCondition(requireDemandQuery, pageInfo);
            List<DemandJoinResult> enquiryDemandResultList = enquiryDemandPageInfoResult.getList();
            
            // 有效截止日期记录数
            int cnt = enquiryDemandResultList.size();
            
            if (cnt == 0) {
            	enquiryDemandPageInfoResult = demandService.queryInvalidDemandJoinTableByCondition(requireDemandQuery, pageInfo);
                enquiryDemandResultList = enquiryDemandPageInfoResult.getList();
            }
            
            List<DemandJoinVO> enquiryDemandVOList = new ArrayList<>();
            for (DemandJoinResult enquiryDemandJoinResult : enquiryDemandResultList) {
                DemandJoinVO enquiryDemandJoinVO = DemandJoinVO.convertToVO(enquiryDemandJoinResult);
                decorateDemandJoinVO(enquiryDemandJoinVO, enquiryDemandJoinResult);
                enquiryDemandVOList.add(enquiryDemandJoinVO);
                // 只显示一条无效截止日期记录
                if (cnt == 0) {
                	break;
                }
            }
            enquiryDemandPageInfoResult.setList(enquiryDemandVOList);
            map.put("require", enquiryDemandPageInfoResult);
            
        } catch (Exception ex) {
            LOGGER.error("销售资源维护_原油大厅的需求询价失败");
            LOGGER.error("",ex);
        }
    }
    
}


