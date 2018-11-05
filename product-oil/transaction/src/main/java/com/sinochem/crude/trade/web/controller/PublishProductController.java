package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;
import com.sinochem.crude.trade.transaction.domain.po.PricingInfo;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.domain.po.StakeholderInfo;
import com.sinochem.crude.trade.transaction.domain.po.TransportInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.*;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.OtherInfoService;
import com.sinochem.crude.trade.transaction.service.PricingInfoService;
import com.sinochem.crude.trade.transaction.service.ProductInfoService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.crude.trade.transaction.service.StakeHolderInfoService;
import com.sinochem.crude.trade.transaction.service.TransportInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 发布商品销售资源
 *
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.PUBLISH_PRODUCT)
public class PublishProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(PublishProductController.class);

    @Value("${id.singapore}")
    private Long idSingapore;

    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 跳转页面
     */
    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(CurrentUser currentUser, String uuid) {
        ModelAndView mav = new ModelAndView();

        try {
            mav.addObject("productCategoryList", dictionaryService.productCategoryMap().values().toArray());
            mav.addObject("productSpecificationList", dictionaryService.productSpecificationMap().values().toArray());
            mav.addObject("tradeTermList", dictionaryService.tradeTermMap().values().toArray());
            mav.addObject("exportTypeList", dictionaryService.exportTypeMap().values().toArray());
            mav.addObject("settlementQuantityList", dictionaryService.settlementQuantityMap().values().toArray());
            mav.addObject("priceSourceList", dictionaryService.priceSourceMap().values().toArray());
            mav.addObject("currencyList", dictionaryService.currencyMap().values().toArray());
            mav.addObject("pricingPeriodList", dictionaryService.pricingPeriodMap().values().toArray());
            mav.addObject("pricingBenchmarkList", dictionaryService.pricingBenchmarkMap().values().toArray());
            mav.addObject("priceRegionList", dictionaryService.priceRegionMap().values().toArray());
            mav.addObject("pricingUnitList", dictionaryService.pricingUnitMap().values().toArray());
            mav.addObject("paymentTermList", dictionaryService.paymentTermMap().values().toArray());
            mav.addObject("transportModeList", dictionaryService.transportModeMap().values().toArray());
            mav.addObject("loadingPortCountryList", dictionaryService.loadingPortCountryMap().values().toArray());
            mav.addObject("loadingPortList", dictionaryService.loadingPortMap().values().toArray());
            mav.addObject("dischargePortCountryList", dictionaryService.dischargePortCountryMap().values().toArray());
            mav.addObject("dischargePortList", dictionaryService.dischargePortMap().values().toArray());
            mav.addObject("productSourceList", dictionaryService.productSourceMap().values().toArray());
            mav.addObject("inspectionList",dictionaryService.inspetionMap().values().toArray());
            mav.addObject("quantityUnitList",dictionaryService.quantityUnitMap().values().toArray());

            mav.addObject("isSingapore", idSingapore.equals(currentUser.getEpMemberId()));
            if(null!=currentUser.getEpMemberId()&&currentUser.getEpMemberId()==100008253)
                mav.addObject("agentEnterpriseVo",enterpriseService.getByMemberId(100008254L));


            if (currentUser != null) {
                EnterpriseVo enterpriseVO = enterpriseService.getByMemberId(currentUser.getEpMemberId());

                if (enterpriseVO != null) {
                    mav.addObject("enterpriseName", enterpriseVO.getEnglishName());
                }
            }

            if (!StringUtil.isEmpty(uuid)) {
                SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, uuid);
                SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser, saleSheet);
                SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
                mav.addObject("saleSheetCombineVO", saleSheetCombineVO);
            }
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            mav.addObject(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            mav.addObject(Message.ERROR, exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        return mav;
    }

    /**
     * 发布商品销售资源
     */
    @RequestMapping(value=UrlMapping.POST_SALE_SHEET_JSON)
    @ResponseBody
    public Result publishProduct(SaleSheetCombineVO saleSheetCombineVO, CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();
        Result res = new Result();

        try {
            SaleSheetCombine saleSheetCombine = saleSheetCombineVO.getDomain();
            saleSheetService.postSaleSheet(currentUser, saleSheetCombine);
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(Mark.POST_OR_SAVE_SUCCESSFULLY);
            //mav.setViewName("redirect:/" + UrlMapping.MY_PRODUCT_LIST + "/" + UrlMapping.INDEX + ".htm?uuid=" + saleSheetCombine.getSaleSheet().getUuid());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
            //mav.addObject(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
            //mav.setViewName("redirect:/" + UrlMapping.PUBLISH_PRODUCT + "/" + UrlMapping.INDEX + ".htm");
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
            //mav.addObject(Message.ERROR, exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            //mav.setViewName("redirect:/" + UrlMapping.PUBLISH_PRODUCT + "/" + UrlMapping.INDEX + ".htm");
        }

        return res;
    }

    /**
     * 根据国家获得港口列表
     */
    @RequestMapping(UrlMapping.PORT_LIST)
    public ModelAndView portList(String countryCode, CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();

        ResultDatasVO resultDatasVO = new ResultDatasVO();
        resultDatasVO.setDatas(dictionaryService.getShipPortMapByCountry(countryCode).values().toArray());

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        mav.setView(new MappingJacksonJsonView());

        return mav;
    }
    /**
     * 根据商品品种选择商品规格
     */
    @RequestMapping(UrlMapping.GET_SPECIFICATION_LIST)
    public ModelAndView categorySpecifitionList(String categoryCode, CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();

        ResultDatasVO resultDatasVO = new ResultDatasVO();
        HashMap<String, ProductSpecificationVO> specificationMap = dictionaryService.getProductSpecificationMapByCategoryCode(categoryCode);

        if (specificationMap != null) {
            resultDatasVO.setDatas(specificationMap.values().toArray());
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        mav.setView(new MappingJacksonJsonView());

        return mav;
    }
}
