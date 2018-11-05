package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
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
 * 报价对比
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_PRODUCT_BIDDING_COMPARE)
public class MyProductBiddingCompareController {
    private final Logger LOGGER = LoggerFactory.getLogger(MyProductBiddingDetailController.class);
    @Autowired
    ExceptionHelper exceptionHelper;
    @Autowired
    private BiddingSheetService biddingSheetService;

    @RequestMapping(UrlMapping.INDEX)
    public void getBiddingCompare(CurrentUser currentUser, String uuids, ModelMap model) {
        ArrayList<BiddingSheetCombineVO> arrayList = new ArrayList<BiddingSheetCombineVO>();
        currentUser = new CurrentUser();
        uuids= "0ca50a07-3ad3-4e17-8037-7c0b80868aed,"+"bb88e9c1-20e5-487f-9272-0917223225cc,"+"282cc132-f162-4dda-a5af-2e82dfeda8cb";
        try {
            String[] strIds = uuids.split(",");
            for(String uuid : strIds){
                BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUuid(currentUser,uuid);
                BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(currentUser,biddingSheet);
                BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
                arrayList.add(biddingSheetCombineVO);
            }
            model.addAttribute("biddingSheetCombineVOList",arrayList);
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));

        }
    }
}
