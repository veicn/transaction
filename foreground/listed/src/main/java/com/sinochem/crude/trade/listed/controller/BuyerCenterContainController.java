package com.sinochem.crude.trade.listed.controller;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.BiddingListVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.page.PageInfoResult;


/**
 * 买家中心contain的控制层
 * Created by sijliu on 20/11/2017.
 */
@Controller
@ApiSafeAccess
public class BuyerCenterContainController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DemandService demandService;

    /**
     * 需求的报价列表
     * @param user
     * @param model
     * @param demandId
     */
    @RequestMapping(value = UrlMapping.BUYCENTERCONTAIN_BIDDINGDEMANDLIST)
    public void biddingDemandTable(CurrentUser user, ModelMap model, Long demandId){
       try{
           ResourceQuery resourceQuery = new ResourceQuery();
           resourceQuery.setParentId(demandId);
           PageInfoResult pageInfoResult = demandService.queryDemandBiddingJoinTableByCondition(resourceQuery, null);
           List<DemandJoinResult> list = pageInfoResult.getList();
           List<BiddingListVO> VOlist = new ArrayList<>();
           for(DemandJoinResult demandJoinResult : list){
               VOlist.add(BiddingListVO.convertToVO(demandJoinResult));
           }
           pageInfoResult.setList(VOlist);
           model.addAttribute("pageInfoList", pageInfoResult);
           model.addAttribute("payItemMap", DictUtils.getPayItemMap());
           model.addAttribute("tradeItemMap", DictUtils.getTradeItemMap());
       }catch (Exception e){
           logger.error("需求的报价列表");
           logger.error("",e);
       }
    }

    /**
     * 状态查询
     * @param user
     * @param model
     * @param demandId
     */
    @RequestMapping(value=UrlMapping.BUYCENTERCONTAIN_DEMANDSTATUS)
    public void getDemandAndStatus(CurrentUser user, ModelMap model, Long demandId)  {
    	try{
            Demand demand = demandService.getDemandByKey(demandId);
            DemandVO demondVO = DemandVO.convertToVO(demand);
            model.addAttribute("demand", demondVO);
        }catch (Exception e){
            logger.error("状态查询");
    	    logger.error("",e);
        }
    }

    /**
     * 获取5条报价数据列表
     * @param user
     * @param model
     */
    @RequestMapping(value=UrlMapping.BUYCENTERCONTAIN_TOPFIVEBIDDINGLIST)
    public void topfivebiddinglist(CurrentUser user, ModelMap model){
        try{
            ResourceQuery resourceQuery = new ResourceQuery();
            resourceQuery.setBizType("CrudeOil");
            resourceQuery.setUserEnterpriseId(user.getEpMemberId());
            model.addAttribute("list", demandService.getTop5BiddingDatas(resourceQuery));
        }catch (Exception e){
            logger.error("获取报价数据列表失败");
            logger.error("",e);
        }
    }

    /**
     * 获取5条需求数据列表
     * @param user
     * @param model
     */
    @RequestMapping(value=UrlMapping.BUYCENTERCONTAIN_TOPFIVEDEMANDLIST)
    public void topfivedemandlist(CurrentUser user, ModelMap model){
       try{
           ResourceQuery resourceQuery = new ResourceQuery();
           resourceQuery.setBizType("CrudeOil");
           resourceQuery.setUserEnterpriseId(user.getEpMemberId());
           model.addAttribute("list", demandService.getTop5BiddingDatas(resourceQuery));
       }catch (Exception e){
           logger.error("获取需求数据列表失败");
           logger.error("",e);
       }
    }


    /**
     * 查询当前人相关的需求单数量
     * @param user
     * @param model
     */
    @RequestMapping(value = UrlMapping.BUYCENTERCONTAIN_QUERYCOUNT)
    public void queryCount(CurrentUser user, ModelMap model){

        try{
            ResourceQuery crudeOilRq = new ResourceQuery();
            crudeOilRq.setUserEnterpriseId(user.getEpMemberId());
            crudeOilRq.setUserId(user.getEpMemberId());
            crudeOilRq.setBizType("CrudeOil");
            crudeOilRq.setSelectCountType(1L); // 1 - 查询需求
            model.addAttribute("crudeOilDemandNum", demandService.countMyDemandNumByUserEpId(crudeOilRq));
            crudeOilRq.setSelectCountType(2L); // 2 - 查询资源
            model.addAttribute("crudeOilResourcesNum", demandService.countMyDemandNumByUserEpId(crudeOilRq));

            ResourceQuery productOilRq = new ResourceQuery();
            productOilRq.setUserEnterpriseId(user.getEpMemberId());
            productOilRq.setBizType("ProductOil");
            model.addAttribute("productOilNum", demandService.countMyDemandNumByCondition(productOilRq));
        }catch (Exception e){
            logger.error("查询当前人相关的需求单数量失败");
            logger.error("",e);
        }

    }
}
