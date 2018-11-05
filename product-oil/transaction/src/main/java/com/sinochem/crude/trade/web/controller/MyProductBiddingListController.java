package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.enums.MessageEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.helper.MessageHelper;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 报价清单
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_PRODUCT_BIDDING_LIST)
public class MyProductBiddingListController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyProductBiddingListController.class);

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private MessageHelper messageHelper;

    @RequestMapping(UrlMapping.INDEX)
    public void getMyProductBiddingList(CurrentUser  user, @RequestParam(value="uuid",required=false) String uuid, ModelMap model){
        try{
            model.put("user", user);
            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(user, uuid);
            SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(user, saleSheet);
            SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
            model.put("saleSheetCombineVO", saleSheetCombineVO);

            List<BiddingSheet> biddingSheets = biddingSheetService.selectBySaleSheetId(saleSheet.getId());
            List<BiddingSheetCombineVO> biddingSheetCombineVOList = new ArrayList<>();
            for (BiddingSheet biddingSheet:biddingSheets) {
                BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, biddingSheet);
                BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
                biddingSheetCombineVOList.add(biddingSheetCombineVO);
            }
            model.put("biddingSheetCombineVOList", biddingSheetCombineVOList);
            model.put("user",user);
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
     * 确认中标
     * @param currentUser
     * @param biddingSheetUuid
     * @param saleSheetUuid
     * @return
     */
    @WithoutAccess
    @RequestMapping(value=UrlMapping.CONFIRM_BIDDING_JSON,method = RequestMethod.POST)
    @ResponseBody
    public Result confirmBidding(CurrentUser currentUser,
                                 String biddingSheetUuid,
                                 String saleSheetUuid) {
        BizException bizException = new BizException();
        Result res = new Result();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, saleSheetUuid);
            if (saleSheet == null) {
                bizException.setCode(ExceptionEnum.NO_DATA.getCode());
                throw bizException;
            }

            BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetByUuid(currentUser, biddingSheetUuid);
            if (biddingSheet == null) {
                bizException.setCode(ExceptionEnum.NO_DATA.getCode());
                throw bizException;
            }

            saleSheetService.confirmBidding(currentUser, saleSheet, biddingSheet);
            res.setMessage(Mark.SAVE_TRU);
        } catch (BizException e) {
            LOGGER.error(e.getMessage(), e);
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);

        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            res.setMessage(e.getMessage());
            res.setStatus(Mark.RESULT_DATA_ERROR);

        }

        return res;
    }

    /**
     * 流标
     * @param currentUser
     * @param saleSheetUuid
     * @return
     */
    @RequestMapping(UrlMapping.CANCEL_SALE_SHEET)
    @ResponseBody
    public Result cancelSaleSheet(CurrentUser currentUser,
                                        @RequestParam("saleSheetUuid") String saleSheetUuid) {
        BizException bizException = new BizException();
        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();
        Result res = new Result();
        try {
            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, saleSheetUuid);
            if (saleSheet == null) {
                bizException.setCode(ExceptionEnum.NO_DATA.getCode());
                throw bizException;
            }

            saleSheetService.cancelBiddingSheet(currentUser, saleSheet);
            resultDatasVO.setMessageVO(messageHelper.convertToMessageVO(MessageEnum.CANCEL_SALE_SHEET_SUCCESS));
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(MessageEnum.CANCEL_SALE_SHEET_SUCCESS.getEnMessage());
        } catch (BizException e) {
            resultDatasVO.setMessageVO(messageHelper.convertToMessageVO(MessageEnum.CANCEL_SALE_SHEET_ERROR));
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(MessageEnum.CANCEL_SALE_SHEET_ERROR.getEnMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(MessageEnum.CANCEL_SALE_SHEET_ERROR.getEnMessage());
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        mav.setViewName("redirect:/" + UrlMapping.MY_PRODUCT_LIST + "/" + UrlMapping.INDEX + ".htm");

        return res;
    }
}
