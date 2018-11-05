package com.sinochem.crude.trade.web.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Interaction;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的采購报价列表
 * @author gyf
 * date: 20180522
 */
@Controller
@RequestMapping(UrlMapping.MY_DEMAND_BIDDING_LIST)
@WithoutAccess
public class MyDemandBiddingListController {
    private final Logger LOGGER = LoggerFactory.getLogger(MyDemandBiddingListController.class);
    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 我的报价列表
     */
    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView myBiddingList(BiddingSheetQueryVO queryVO, PageInfo pageInfo, CurrentUser user) {
    	
        ModelAndView model = new ModelAndView();
        try {
            if (pageInfo != null) {
                pageInfo.setPageSize(Interaction.DEFAULT_PAGE_SIZE);
            }

            model.addObject("user", user);
            PageInfoResult pageInfoResult = biddingSheetService.getBiddingSheetList(user, queryVO, pageInfo);
            List<BiddingSheet> biddingSheetList = pageInfoResult.getList();

            if (!biddingSheetList.isEmpty()) {
                List<BiddingSheetCombineVO> biddingSheetCombinelist = new ArrayList<>();
                for (BiddingSheet biddingSheet : biddingSheetList){
                    BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, biddingSheet);
                    BiddingSheetCombineVO biddingSheetCombineVO = new BiddingSheetCombineVO(biddingSheetCombine);
                    biddingSheetCombinelist.add(biddingSheetCombineVO);
                }

                pageInfoResult.setList(biddingSheetCombinelist);
            }
            model.addObject("bidingSheetQueryVO",queryVO);
            model.addObject("myBiddingListResult", pageInfoResult);
            model.addObject("categoryVOList", dictionaryService.productCategoryMap().values().toArray());
            model.addObject("biddingSheetStatusVOList", dictionaryService.biddingSheetStatusMap().values().toArray());
            model.addObject("tradeTermVOList", dictionaryService.tradeTermMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addObject(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
            model.addObject("myBiddingListResult", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addObject(Message.ERROR, exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            model.addObject("myBiddingListResult", pageInfo);
        }
        return model;
    }

}
