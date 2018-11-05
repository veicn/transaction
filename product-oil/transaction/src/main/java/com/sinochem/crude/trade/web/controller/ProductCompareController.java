package com.sinochem.crude.trade.web.controller;


import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;

import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;

import com.sinochem.crude.trade.transaction.service.DemandSheetService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


/**
 * 商品比较
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_COMPARE)
public class ProductCompareController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCompareController.class);
    @Autowired
    ExceptionHelper exceptionHelper;
    @Autowired
    private SaleSheetService saleSheetService;
    @Autowired
    private DemandSheetService demandSheetService;

    @RequestMapping(UrlMapping.INDEX)
    public void index(CurrentUser currentUser, ModelMap model, String uuids){
        ArrayList arrayList = new ArrayList<>();
        try{
            String[] strIds = uuids.split(",");
            for (String uuid:strIds) {
                SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser,uuid);
                if(saleSheet != null){
                    SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser,saleSheet);
                    SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
                    arrayList.add(saleSheetCombineVO);
                }
            }
            model.addAttribute("saleSheetCombineVOList",arrayList);
            model.addAttribute("userId", currentUser.getEpMemberId());
        }catch (BizException e){
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        }catch (RuntimeException e){
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }

    @RequestMapping(UrlMapping.DEMAND_INDEX)
    public void demandIndex(CurrentUser currentUser, ModelMap model, String uuids){
        ArrayList arrayList = new ArrayList<>();
        try{
            String[] strIds = uuids.split(",");
            for (String uuid:strIds) {
                DemandSheet demandSheet = demandSheetService.getDemandSheetByUuid(currentUser,uuid);
                if(demandSheet != null){
                    DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(currentUser,demandSheet);
                    DemandSheetCombineVO demandSheetCombineVO = new DemandSheetCombineVO(demandSheetCombine);
                    arrayList.add(demandSheetCombineVO);
                }
            }
            model.addAttribute("saleSheetCombineVOList",arrayList);
            model.addAttribute("userId", currentUser.getEpMemberId());
        }catch (BizException e){
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        }catch (RuntimeException e){
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }
}
