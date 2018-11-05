package com.sinochem.crude.trade.member.controller;

import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.BusinessCards;
import com.sinochem.crude.trade.member.domain.query.BusinessCardApplyQuery;
import com.sinochem.crude.trade.member.domain.query.BusinessCardQuery;
import com.sinochem.crude.trade.member.service.BusinessCardService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 名片Controller
 * Created by AlterEgo on 2018/5/4.
 */
@Controller
public class BusinessCardController {

    final static Logger logger  = LoggerFactory.getLogger(BusinessCardController.class);

    @Autowired
    private BusinessCardService businessCardService;

    /**
     * 获取个人名片
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.BUSINESSCARD)
    @ResponseBody
    public ResultDatas<BusinessCards> getBusinessCard(CurrentUser currentUser) {
        Long memberId = currentUser.getMemberId();
        ResultDatas<BusinessCards> result = new ResultDatas<BusinessCards>();
        BusinessCards businessCard = businessCardService.getInfoByMemberId(memberId);
        if (businessCard != null) {
            result.setDatas(businessCard);
        } else {
            result.setDatas(new BusinessCards());
            logger.error("名片信息查询接口调用失败");
        }
        return result;
    }

    /**
     * 编辑个人名片
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.BUSINESSCARD_UPDATE,method = RequestMethod.POST)
    @ResponseBody
    public Result businessCardUpdate(CurrentUser currentUser,@RequestBody BusinessCards businessCard) {
        Result result = new Result();
        try {
            Long memberId = currentUser.getMemberId();
            businessCardService.updateBusinessCard(memberId,businessCard);
        } catch (BizException e) {
            result.setFail(e.getMessage());
            logger.error("接口调用失败",e);
        }
        return result;
    }

    /**
     * 发送名片申请
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.BUSINESSCARD_SEND)
    @ResponseBody
    public Result businessCardSend(CurrentUser currentUser,Long applyMemberId) {
        Result result = new Result();
        try {
            Long memberId = currentUser.getMemberId();
            businessCardService.sendBusinessCard(memberId,applyMemberId);
        } catch (BizException e) {
            result.setFail(e.getMessage());
            logger.error("接口调用失败",e);
        }
        return result;
    }

    /**
     * 发起的名片申请列表
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.BUSINESSCARDAPPLY_LAUNCH_LIST)
    @ResponseBody
    public ResultDatas<List<BusinessCardApplyQuery>> businessCardApplyLaunchList(CurrentUser currentUser,String applyStatus) {
        ResultDatas result = new ResultDatas();
        List<BusinessCardApplyQuery> list = businessCardService.getBusinessCardApplyByMemberId(currentUser.getMemberId(), applyStatus);
        result.setDatas(list);
        return result;
    }

    /**
     * 收到的名片申请列表
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.BUSINESSCARDAPPLY_RECEIVED_LIST)
    @ResponseBody
    public ResultDatas<List<BusinessCardApplyQuery>> businessCardApplyReceivedList(CurrentUser currentUser,String applyStatus ) {
        ResultDatas result = new ResultDatas();
        List<BusinessCardApplyQuery> list = businessCardService.getBusinessCardApplyByApplyMemberId(currentUser.getMemberId(),applyStatus);
        result.setDatas(list);
        return result;
    }

    /**
     * 确认名片申请
     * @param sendApplyMemberId
     * @param applyStatus
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.BUSINESSCARDAPPLY_CONFIRM)
    @ResponseBody
    public Result BusinessCardApplyConfirm(CurrentUser currentUser,Long sendApplyMemberId,String applyStatus) {
        Result result = new Result();
        try {
            Long memberId = currentUser.getMemberId();
            businessCardService.BusinessCardApplyConfirm(memberId,sendApplyMemberId,applyStatus);
        } catch (BizException e) {
            result.setFail(e.getMessage());
            logger.error("接口调用失败",e);
        }
        return result;
    }

    /**
     * 搜索名片列表
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.BUSINESSCARDAPPLY_SEARCH,method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<List<BusinessCardApplyQuery>> businessCardSearch(CurrentUser currentUser,@RequestBody BusinessCardQuery queryParam) {
        ResultDatas result = new ResultDatas();
        List<BusinessCards> list = businessCardService.getBusinessCardByQuery(queryParam);
        result.setDatas(list);
        return result;
    }

    /**
     * 搜索我收藏的名片列表
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.BUSINESSCARDAPPLY_MY_SEARCH , method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<List<BusinessCardApplyQuery>> businessCardCollectSearch(CurrentUser currentUser,@RequestBody BusinessCardQuery queryParam) {
        ResultDatas result = new ResultDatas();
        if(queryParam==null){
            queryParam = new BusinessCardQuery();
        }
        queryParam.setMemberId(currentUser.getMemberId());
        List<BusinessCards> list = businessCardService.getBusinessCardByQuery(queryParam);
        result.setDatas(list);
        return result;
    }

    /**
     * 删除我收藏的名片
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.BUSINESSCARDAPPLY_MY_DELETE , method = RequestMethod.GET)
    @ResponseBody
    public Result businessCardCollectDelete(CurrentUser currentUser,Long collectMemberId) {
        Result result = new Result();
        if(collectMemberId != null){
            businessCardService.deleteCollectBusinessCard(currentUser.getMemberId(),collectMemberId);
        }
        return result;
    }
}
