package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.ExternalApi;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.enums.*;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.DemandSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/19
 **/
@Controller
@RequestMapping(UrlMapping.DEMAND_SHEET)
public class DemandSheetController {

    private final Logger logger = LoggerFactory.getLogger(DemandSheetController.class);
    @Autowired
    private DemandSheetService demandSheetService;

    @Autowired
    private BiddingSheetService biddingSheetService;

    /** 采购报价清单
     * @param model
     * @param user
     * @param demandUuid 采购单Uuid
     * @return
     */
    @RequestMapping(UrlMapping.DEMAND_BIDDING_INVENTORY)
    public ModelAndView getDemandDetails(String demandUuid,ModelAndView model, CurrentUser user) {

        try {
            //1、查询采购需求
            DemandSheetCombineVO demandSheetCombineVO = demandSheetService.findDemandDetails(user, demandUuid);
            model.addObject("demandSheetCombineVO", demandSheetCombineVO);
//            2、查询报价列表
            List<BiddingSheet> biddingSheets =
                    biddingSheetService.selectByDemendId(Long.valueOf(demandSheetCombineVO.getDemandSheetVO().getId()));
            List<BiddingSheetCombineVO> biddingSheetCombineVOList = new ArrayList<>();
            for (BiddingSheet bidding : biddingSheets) {
                BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, bidding);
                BiddingSheetCombineVO biddingCombineListVO = new BiddingSheetCombineVO(biddingSheetCombine);
                biddingSheetCombineVOList.add(biddingCombineListVO);
            }
            model.addObject("biddingSheetCombineVOList", biddingSheetCombineVOList);
            model.addObject("user",user);
        } catch (BizException biz) {
            logger.error("参数为空", biz);
        } catch (Exception e) {
            logger.error("查询采购详情发生异常", e);
        }
        return model;
    }


    /**
     * 界面Accept按钮功能  接受报价
     *
     * @param user
     * @param biddingUuid 报价单uuid
     * @return
     */
    @RequestMapping(UrlMapping.DEMAND_ACCEPT)
    @ResponseBody
    public ResultDatas<Boolean> acceptBiddingSheet(CurrentUser user, String biddingUuid) {
        ResultDatas<Boolean> result = new ResultDatas<>();
        result.setDatas(false);
        result.setMessage("fail");
        result.setStatus(ExternalApi.API_STATUS_ERROR);
        try {
            demandSheetService.acceptBidding(user, biddingUuid);
            result.setStatus(ExternalApi.API_STATUS_SUCCESS);
            result.setMessage(Mark.SAVE_TRU);
            result.setDatas(true);
        } catch (BizException biz) {
            logger.error("参数为空", biz);
        } finally {
            return result;
        }
    }


    /**
     * 根据报价单uuid查询采购单详情和当前报价单详情
     * @param model
     * @param user
     * @param biddingUuid
     * @return
     */
    @RequestMapping(UrlMapping.DEMAND_BIDDING_COMPARE)
    public ModelAndView getDemandsAndOffer(ModelAndView model, CurrentUser user, String biddingUuid) {
        try {
            //1、查询报价详情
            BiddingSheetCombineVO biddingSheetCombineVO = biddingSheetService.findBiddingSheetCombineVO(user, biddingUuid);
            model.addObject("biddingSheetCombineVO", biddingSheetCombineVO);
            BiddingSheetVO biddingSheetVO = biddingSheetCombineVO != null ? biddingSheetCombineVO.getBiddingSheetVO() : null;
            //2、查询采购需求
            if (biddingSheetVO != null) {
                DemandSheetCombineVO demandSheetCombineVO = demandSheetService.findDemandDetails(user, biddingSheetVO.getDemandSheetUuid());
                model.addObject("demandSheetCombineVO", demandSheetCombineVO);

                //查询该报价公司的对该采购单的报价历史
                List<BiddingSheetCombineVO> historyBiddingSheetCombineVOList = new ArrayList<>();
                BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
                biddingSheetQuery.setDemandSheetId(Long.valueOf(demandSheetCombineVO.getDemandSheetVO().getId()));
                biddingSheetQuery.setEnterpriseId(biddingSheetCombineVO.getBiddingSheetVO().getEnterpriseId());
                List<BiddingSheet> historyBiddingSheets = biddingSheetService.selectHistoryDemandBidding(user,biddingSheetQuery);

                for (BiddingSheet bidding:historyBiddingSheets) {
                    BiddingSheetCombine biddingSheetCombine = biddingSheetService.getBiddingSheetCombine(user, bidding);
                    BiddingSheetCombineVO biddingCombineListVO = new BiddingSheetCombineVO(biddingSheetCombine);
                    historyBiddingSheetCombineVOList.add(biddingCombineListVO);
                }
                model.addObject("biddingList",historyBiddingSheetCombineVOList);
            } else {
                logger.warn("报价单详情为空");
            }

        } catch (BizException biz) {
            logger.error("参数为空", biz);
        } catch (Exception e) {
            logger.error("发生异常", e);
        }
        return model;
    }


    /**
     * 关闭采购单 武刚鹏 -2018年5月21日14:49:53
     * @param user
     * @param demandUuid
     * @return
     */
    @RequestMapping(UrlMapping.DEMAND_CANCEL)
    @ResponseBody
    public ResultDatas<Boolean> cancelDemand(CurrentUser user, String demandUuid) {
        ResultDatas<Boolean> result = new ResultDatas<>();
        result.setStatus(ExternalApi.API_STATUS_SUCCESS);
        result.setMessage("fail");
        result.setDatas(false);
        try {
            demandSheetService.cancelDemand(user, demandUuid);
            result.setDatas(true);
            result.setMessage(MessageEnum.CANCEL_DEMAND_SHEET_SUCCESS.getEnMessage());
            result.setStatus(ExternalApi.API_STATUS_SUCCESS);
        } catch (BizException e) {
            logger.error("运行时异常", e);
        } catch (Exception e) {
            logger.error("运行时异常", e);
        } finally {
            return result;
        }
    }

}
