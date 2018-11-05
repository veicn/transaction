package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.BiddingSheetStatusEnum;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.*;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sinochem.it.b2b.common.result.Result;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * 商品报价
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BIDDING)
public class ProductBiddingController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductBiddingController.class);
    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private DemandSheetService demandSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private StakeHolderInfoService stakeHolderInfoService;

    @Autowired
    private EnterpriseService enterpriseService;
    /**
     * 商品报价页面
     */
    @RequestMapping(UrlMapping.INDEX)
    public void index(@RequestParam("uuid")String uuid,String biddingSheetUuid,CurrentUser user,ModelMap model){
        try{

            model.addAttribute("productCategoryList", dictionaryService.productCategoryMap().values().toArray());
            model.addAttribute("productSpecificationList", dictionaryService.productSpecificationMap().values().toArray());
            model.addAttribute("tradeTermList", dictionaryService.tradeTermMap().values().toArray());
            model.addAttribute("exportTypeList", dictionaryService.exportTypeMap().values().toArray());
            model.addAttribute("settlementQuantityList", dictionaryService.settlementQuantityMap().values().toArray());
            model.addAttribute("priceSourceList", dictionaryService.priceSourceMap().values().toArray());
            model.addAttribute("currencyList", dictionaryService.currencyMap().values().toArray());
            model.addAttribute("pricingPeriodList", dictionaryService.pricingPeriodMap().values().toArray());
            model.addAttribute("pricingBenchmarkList", dictionaryService.pricingBenchmarkMap().values().toArray());
            model.addAttribute("priceRegionList", dictionaryService.priceRegionMap().values().toArray());
            model.addAttribute("pricingUnitList", dictionaryService.pricingUnitMap().values().toArray());
            model.addAttribute("paymentTermList", dictionaryService.paymentTermMap().values().toArray());
            model.addAttribute("transportModeList", dictionaryService.transportModeMap().values().toArray());
            model.addAttribute("loadingPortCountryList", dictionaryService.loadingPortCountryMap().values().toArray());
            model.addAttribute("loadingPortList", dictionaryService.loadingPortMap().values().toArray());
            model.addAttribute("dischargePortCountryList", dictionaryService.dischargePortCountryMap().values().toArray());
            model.addAttribute("dischargePortList", dictionaryService.dischargePortMap().values().toArray());
            model.addAttribute("productSourceList", dictionaryService.productSourceMap().values().toArray());
            model.addAttribute("inspectionList",dictionaryService.inspetionMap().values().toArray());
            model.addAttribute("quantityUnitList",dictionaryService.quantityUnitMap().values().toArray());

            //报价回显
            if(!StringUtil.isEmpty(biddingSheetUuid)){
                BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUuid(user, biddingSheetUuid);
                BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, biddingSheet);
                BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
                model.addAttribute("biddingSheetCombineVO",biddingSheetCombineVO);
            }

            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(user, uuid);
            SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(user, saleSheet);
            SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
            model.addAttribute("saleSheetCombineVO",saleSheetCombineVO);

            model.addAttribute("tradeTerms", dictionaryService.tradeTermMap().values().toArray());
            model.addAttribute("paymentTerm", dictionaryService.paymentTermMap().values().toArray());

            if (user != null) {
                Long epMemberId = user.getEpMemberId();

                if (epMemberId != null) {
                    EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(epMemberId);

                    model.addAttribute("buyerCompanyVO", enterpriseVo);
                }
            }

            Long sellerId = saleSheet.getEnterpriseId();
            if (sellerId != null) {
                EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(sellerId);

                model.addAttribute("sellerCompanyVO", enterpriseVo);
            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }

    /**
     * 商品报价
     * @param user
     * @param biddingSheetCombineVO
     * @param model
     */
    @RequestMapping(UrlMapping.QUOTE)
    @ResponseBody
    public Result productBidding(CurrentUser user, BiddingSheetCombineVO biddingSheetCombineVO, ModelMap model){
        Result res = new Result();
        try{
            Long saleSheetId = biddingSheetCombineVO.getBiddingSheetVO().getSaleSheetId();

            //根据saleSheetId 获取其他商品信息
            SaleSheet saleSheet = saleSheetService.getSaleSheetById(user, saleSheetId);
            if(saleSheet != null){
                if("2".equals(saleSheet.getSaleSheetStatusCode())){
                    //根据根据saleSheetId 获取当前用户所有报价单并把状态改为已作废
                    List<BiddingSheet> biddingSheets = biddingSheetService.selectBySaleSheetIdForMe(user,saleSheetId);
                    if(biddingSheets.size() > 0){
                        for (BiddingSheet bidding:biddingSheets) {
                            biddingSheetService.changeBiddingSheetStatus(user,bidding, BiddingSheetStatusEnum.CANCELLED.getCode());
                        }
                    }

                    SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(user, saleSheet);

                    /*OtherInfo otherInfo = saleSheetCombine.getOtherInfo();
                    PricingInfo pricingInfo = saleSheetCombine.getPricingInfo();
                    TransportInfo transportInfo = saleSheetCombine.getTransportInfo();*/

                    ProductInfo productInfo = saleSheetCombine.getProductInfo();

                    BiddingSheetCombine biddingSheetCombine = biddingSheetCombineVO.getDomain();
                    //页面数据封装
                    productInfo.setQuantity(biddingSheetCombine.getProductInfo().getQuantity());
                    productInfo.setQuantityUnitCode(biddingSheetCombine.getProductInfo().getQuantityUnitCode());
                    productInfo.setSaleTypeCode(saleSheet.getSaleTypeCode());
                    productInfo.setTradeTermCode(biddingSheetCombine.getProductInfo().getTradeTermCode());
                    productInfo.setTolerance(biddingSheetCombine.getProductInfo().getTolerance());


                    StakeholderInfo buyerInfo = biddingSheetCombine.getBuyerInfo();
                    buyerInfo.setEnterpriseId(user.getEpMemberId());

                    biddingSheetCombine.setBuyerInfo(buyerInfo);
                    biddingSheetCombine.setProductInfo(productInfo);
                    /*biddingSheetCombine.setOtherInfo(otherInfo);
                    biddingSheetCombine.setPricingInfo(pricingInfo);
                    biddingSheetCombine.setTransportInfo(transportInfo);*/

                    biddingSheetService.postBiddingSheet(user, biddingSheetCombine);
                    //报价成功
                    res.setStatus(Mark.RESULT_DATA_SUCCESS);
                    res.setMessage(Mark.SUBMIT_SUCCESSFULLY);
                }else{
                    //该商品已不能报价
                    res.setStatus(Mark.RESULT_DATA_ERROR);
                    res.setMessage(Mark.UNABLE_TO_QUOTE);
                    //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
                }
            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }
        //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
        return res;

    }



    /**
     * 获取用户是否已经报价
     * @param user
     * @param uuid
     * @param model
     */
    @RequestMapping(UrlMapping.BID_JSON)
    @ResponseBody
    public Result bidJson(CurrentUser user, String uuid, ModelMap model){
        Result res = new Result();

        try{
            if(!StringUtil.isEmpty(uuid)){
                BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUser(user, uuid);
                if(null!=biddingSheet){
                    res.setStatus(1);
                    res.setMessage(biddingSheet.getUuid());
                }

            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }
        //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
        return res;

    }


    /**
     * 采购商品报价页面
     */
    @RequestMapping(UrlMapping.DEMAND_INDEX)
    public void demandIndex(@RequestParam("uuid")String uuid,String biddingSheetUuid,CurrentUser user,ModelMap model){
        try{

            model.addAttribute("productCategoryList", dictionaryService.productCategoryMap().values().toArray());
            model.addAttribute("productSpecificationList", dictionaryService.productSpecificationMap().values().toArray());
            model.addAttribute("tradeTermList", dictionaryService.tradeTermMap().values().toArray());
            model.addAttribute("exportTypeList", dictionaryService.exportTypeMap().values().toArray());
            model.addAttribute("settlementQuantityList", dictionaryService.settlementQuantityMap().values().toArray());
            model.addAttribute("priceSourceList", dictionaryService.priceSourceMap().values().toArray());
            model.addAttribute("currencyList", dictionaryService.currencyMap().values().toArray());
            model.addAttribute("pricingPeriodList", dictionaryService.pricingPeriodMap().values().toArray());
            model.addAttribute("pricingBenchmarkList", dictionaryService.pricingBenchmarkMap().values().toArray());
            model.addAttribute("priceRegionList", dictionaryService.priceRegionMap().values().toArray());
            model.addAttribute("pricingUnitList", dictionaryService.pricingUnitMap().values().toArray());
            model.addAttribute("paymentTermList", dictionaryService.paymentTermMap().values().toArray());
            model.addAttribute("transportModeList", dictionaryService.transportModeMap().values().toArray());
            model.addAttribute("loadingPortCountryList", dictionaryService.loadingPortCountryMap().values().toArray());
            model.addAttribute("loadingPortList", dictionaryService.loadingPortMap().values().toArray());
            model.addAttribute("dischargePortCountryList", dictionaryService.dischargePortCountryMap().values().toArray());
            model.addAttribute("dischargePortList", dictionaryService.dischargePortMap().values().toArray());
            model.addAttribute("productSourceList", dictionaryService.productSourceMap().values().toArray());
            model.addAttribute("inspectionList",dictionaryService.inspetionMap().values().toArray());
            model.addAttribute("quantityUnitList",dictionaryService.quantityUnitMap().values().toArray());

            //报价回显
            if(!StringUtil.isEmpty(biddingSheetUuid)){
                BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUuid(user, biddingSheetUuid);
                BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, biddingSheet);
                BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
                model.addAttribute("biddingSheetCombineVO",biddingSheetCombineVO);
            }

            DemandSheet demandSheet = demandSheetService.getDemandSheetByUuid(user, uuid);
            DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(user, demandSheet);
            DemandSheetCombineVO demandSheetCombineVO = new DemandSheetCombineVO(demandSheetCombine);
            model.addAttribute("saleSheetCombineVO",demandSheetCombineVO);

            model.addAttribute("tradeTerms", dictionaryService.tradeTermMap().values().toArray());
            model.addAttribute("paymentTerm", dictionaryService.paymentTermMap().values().toArray());

            if (user != null) {
                Long epMemberId = user.getEpMemberId();

                if (epMemberId != null) {
                    EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(epMemberId);

                    model.addAttribute("sellerCompanyVO", enterpriseVo);
                }
            }

            Long sellerId = demandSheet.getEnterpriseId();
            if (sellerId != null) {
                EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(sellerId);

                model.addAttribute("buyerCompanyVO", enterpriseVo);
            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }


    /**
     * 获取采购需求的用户是否已经报价
     * @param user
     * @param uuid
     * @param model
     */
    @RequestMapping(UrlMapping.DEMANDBID_JSON)
    @ResponseBody
    public Result demandBidJson(CurrentUser user, String uuid, ModelMap model){
        Result res = new Result();

        try{
            if(!StringUtil.isEmpty(uuid)){
                BiddingSheet biddingSheet = biddingSheetService.getBiddingDemandSheetByUser(user, uuid);
                if(null!=biddingSheet){
                    res.setStatus(1);
                    res.setMessage(biddingSheet.getUuid());
                }

            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }
        //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
        return res;

    }


    /**
     * 采购商品报价
     * @param user
     * @param biddingSheetCombineVO
     * @param model
     */
    @RequestMapping(UrlMapping.DEMAND_QUOTE)
    @ResponseBody
    public Result demandBidding(CurrentUser user, BiddingSheetCombineVO biddingSheetCombineVO, ModelMap model){
        Result res = new Result();
        try{
            Long demandSheetId = biddingSheetCombineVO.getBiddingSheetVO().getDemandSheetId();

            //根据saleSheetId 获取其他商品信息
            DemandSheet demandSheet = demandSheetService.getDemandSheetById(user, demandSheetId);
            if(demandSheet != null){
                if("2".equals(demandSheet.getDemandSheetStatusCode())){
                    //根据根据saleSheetId 获取当前用户所有报价单并把状态改为已作废
                    List<BiddingSheet> biddingSheets = biddingSheetService.selectByDemandSheetIdForMe(user,demandSheetId);
                    if(biddingSheets.size() > 0){
                        for (BiddingSheet bidding:biddingSheets) {
                            biddingSheetService.changeBiddingSheetStatus(user,bidding, BiddingSheetStatusEnum.CANCELLED.getCode());
                        }
                    }

                    DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(user, demandSheet);

                    /*OtherInfo otherInfo = saleSheetCombine.getOtherInfo();
                    PricingInfo pricingInfo = saleSheetCombine.getPricingInfo();
                    TransportInfo transportInfo = saleSheetCombine.getTransportInfo();*/

                    ProductInfo productInfo = demandSheetCombine.getProductInfo();

                    BiddingSheetCombine biddingSheetCombine = biddingSheetCombineVO.getDomain();
                    //页面数据封装
                    productInfo.setQuantity(biddingSheetCombine.getProductInfo().getQuantity());
                    productInfo.setQuantityUnitCode(biddingSheetCombine.getProductInfo().getQuantityUnitCode());
                    productInfo.setDemandTypeCode(demandSheet.getDemandTypeCode());
                    productInfo.setTradeTermCode(biddingSheetCombine.getProductInfo().getTradeTermCode());
                    productInfo.setTolerance(biddingSheetCombine.getProductInfo().getTolerance());


                    StakeholderInfo sellerInfo = biddingSheetCombine.getSalerInfo();
                    sellerInfo.setEnterpriseId(user.getEpMemberId());

                    biddingSheetCombine.setSalerInfo(sellerInfo);
                    biddingSheetCombine.setProductInfo(productInfo);
                    /*biddingSheetCombine.setOtherInfo(otherInfo);
                    biddingSheetCombine.setPricingInfo(pricingInfo);
                    biddingSheetCombine.setTransportInfo(transportInfo);*/

                    biddingSheetService.postBiddingDemandSheet(user, biddingSheetCombine);
                    //报价成功
                    res.setStatus(Mark.RESULT_DATA_SUCCESS);
                    res.setMessage(Mark.SUBMIT_SUCCESSFULLY);
                }else{
                    //该商品已不能报价
                    res.setStatus(Mark.RESULT_DATA_ERROR);
                    res.setMessage(Mark.UNABLE_TO_QUOTE);
                    //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
                }
            }

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            //model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
        }
        //return UrlMapping.MY_BIDDING_LIST_Index_HTM;
        return res;

    }

}
