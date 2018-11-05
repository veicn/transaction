package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 报价详情
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_PRODUCT_BIDDING_DETAIL)
public class MyProductBiddingDetailController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyProductBiddingDetailController.class);

    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(CurrentUser currentUser, String uuid, ModelMap model) {

        ModelAndView mav = new ModelAndView();

        try {
            //报价Combine
            BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUuid(currentUser, uuid);
            BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(currentUser, biddingSheet);
            BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
            mav.addObject("biddingSheetCombineVO", biddingSheetCombineVO);

            //报价历史
            Long saleSheetId = biddingSheet.getSaleSheetId();
            BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
            Long buyId = biddingSheetCombine.getBiddingSheet().getUserCreated();
            biddingSheetQuery.setEnterpriseId(buyId);
            biddingSheetQuery.setSaleSheetId(saleSheetId);
            List<BiddingSheet> biddingSheets = biddingSheetService.selectHistoryBidding(currentUser, biddingSheetQuery);
            List<BiddingSheetCombineVO> biddingSheetCombineListVO = new ArrayList<>();
            for (BiddingSheet  bidding: biddingSheets) {
                BiddingSheetCombine biddingSheetCombine1 = biddingSheetService.getBiddingSheetCombine(currentUser,bidding);
                BiddingSheetCombineVO biddingCombineListVO = new BiddingSheetCombineVO(biddingSheetCombine1);
                biddingSheetCombineListVO.add(biddingCombineListVO);
            }
            mav.addObject("biddingSheetCombineListVO", biddingSheetCombineListVO);

            //需求单
            SaleSheet saleSheet = saleSheetService.getSaleSheetById(currentUser, saleSheetId);
            SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser,saleSheet);
            SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
            SaleSheetVO saleSheetVO = new SaleSheetVO(saleSheet);
            mav.addObject("saleSheetCombineVO", saleSheetCombineVO);
            mav.addObject("user",currentUser);

            if(null!=saleSheet&&saleSheet.getEnterpriseId()==100008253)
                mav.addObject("agentEnterpriseVo",enterpriseService.getByMemberId(100008254L));

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            mav.addObject("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            mav.addObject("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        return mav;
    }

}
