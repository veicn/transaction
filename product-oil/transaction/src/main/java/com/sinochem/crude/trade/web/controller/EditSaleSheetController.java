package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 修改销售需求单
 * @author Yichen Zhao
 * date: 20180326
 */
@Controller
@RequestMapping(UrlMapping.EDIT_SALE_SHEET)
public class EditSaleSheetController {

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
            mav.addObject("dischargePortCountryList", dictionaryService.dischargePortCountryMap().values().toArray());
            mav.addObject("productSourceList", dictionaryService.productSourceMap().values().toArray());
            mav.addObject("inspectionList",dictionaryService.inspetionMap().values().toArray());
            mav.addObject("quantityUnitList",dictionaryService.quantityUnitMap().values().toArray());

            mav.addObject("isSingapore", idSingapore.equals(currentUser.getEpMemberId()));

            if (currentUser != null) {
                EnterpriseVo enterpriseVO = enterpriseService.getByMemberId(currentUser.getEpMemberId());

                if (enterpriseVO != null) {
                    mav.addObject("enterpriseName", enterpriseVO.getEnglishName());
                }
            }

            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, uuid);
            SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser, saleSheet);
            SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
            mav.addObject("saleSheetCombineVO", saleSheetCombineVO);
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
     * 修改商品销售资源
     */
    @RequestMapping(value=UrlMapping.COMMIT_SALE_SHEET_EDIT)
    @ResponseBody
    public Result editProduct(SaleSheetCombineVO saleSheetCombineVO, CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();
        Result res = new Result();
        try {
            SaleSheetCombine saleSheetCombine = saleSheetCombineVO.getDomain();
            saleSheetService.updateSaleSheetCombineSaveHistory(currentUser, saleSheetCombine);

            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(Mark.EDIT_SUCCESSFULLY);
            mav.setViewName("redirect:/" + UrlMapping.MY_PRODUCT_LIST + "/" + UrlMapping.INDEX + ".htm?uuid=" + saleSheetCombine.getSaleSheet().getUuid());
        } catch (BizException e) {
            e.printStackTrace();
            int exceptionCode = e.getCode();
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
            mav.addObject(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
            mav.setViewName("redirect:/" + UrlMapping.EDIT_SALE_SHEET + "/" + UrlMapping.INDEX + ".htm");
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);
            mav.addObject(Message.ERROR, exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            mav.setViewName("redirect:/" + UrlMapping.EDIT_SALE_SHEET + "/" + UrlMapping.INDEX + ".htm");
        }

        return res;
    }
}
