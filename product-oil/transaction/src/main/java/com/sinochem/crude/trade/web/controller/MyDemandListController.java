package com.sinochem.crude.trade.web.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.enums.DemandSheetStatusEnum;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.DemandSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 成品油采購资源列表
 * @author gyf
 * date: 20180519
 */
@Controller
@RequestMapping(UrlMapping.MY_DEMAND_LIST)
public class MyDemandListController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyDemandListController.class);
    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private DemandSheetService demandSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 成品油采購列表
     */
    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(DemandSheetQueryVO demandSheetQueryVO, PageInfo pageInfo, CurrentUser user){

        ModelAndView mav = new ModelAndView();

        try{
            mav.addObject("user", user);

            demandSheetQueryVO.setEnterpriseId(user.getEpMemberId());
            PageInfoResult pageInfoResult = demandSheetService.getDemandSheetList(user, demandSheetQueryVO, pageInfo);
            List<DemandSheet> demandSheetList = pageInfoResult.getList();
            if(!demandSheetList.isEmpty()){
                List<DemandSheetCombineVO> demandSheetCombinelist = new ArrayList<>();

                for(DemandSheet demandSheet : demandSheetList){
                    DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(user, demandSheet);
                    DemandSheetCombineVO demandSheetCombineVO = new DemandSheetCombineVO(demandSheetCombine);
                    demandSheetCombinelist.add(demandSheetCombineVO);
                }

                pageInfoResult.setList(demandSheetCombinelist);
            }
            mav.addObject("pageInfoList", pageInfoResult);
            mav.addObject("demandSheetQueryVO", demandSheetQueryVO);
            mav.addObject("demandType", dictionaryService.demandTypeMap().values().toArray());
            mav.addObject("category",dictionaryService.productCategoryMap().values().toArray());
            mav.addObject("demandSheetStatus",dictionaryService.demandSheetStatusMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            mav.addObject("pageInfoList",pageInfo);
            mav.addObject(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            mav.addObject("pageInfoList",pageInfo);
            mav.addObject(Message.ERROR, exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        return mav;
    }

    /**
     * 上架  /  下架
     */
    @RequestMapping(value=UrlMapping.SHELF_OR_OFFSHELF)
    @ResponseBody
    public Result productShelfOrOffShelf(String uuid, String code, CurrentUser user, ModelMap model){
        Result res = new Result();
        try{
            demandSheetService.demandShelfOrOffShelf(user,uuid,code);
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            if(DemandSheetStatusEnum.RELEASED.getCode().equals(code)){
                res.setMessage(Mark.POST_SUCCESSFULLY);
            }else{
                res.setMessage(Mark.WITHDRAW_SUCCESSFULLY);
            }
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setStatus(Mark.RESULT_DATA_ERROR);
            res.setMessage(e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setStatus(Mark.RESULT_DATA_ERROR);
            res.setMessage(e.getMessage());
        }
        //String concatUrl = UrlUtil.concatUrl(UrlMapping.MY_PRODUCT_LIST, UrlMapping.INDEX);
        return res;
    }

    /**
     * 删除
     */
    @RequestMapping(value=UrlMapping.DELETE)
    @ResponseBody
    public Result productDelete(CurrentUser currentUser, String uuid, ModelMap model){
        Result res = new Result();
        try{
            demandSheetService.deleteDemandSheetByIdLogical(currentUser,uuid);
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(Mark.DELETE_SUCCESSFULLY);
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            res.setStatus(Mark.RESULT_DATA_ERROR);
            res.setMessage(e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            res.setStatus(Mark.RESULT_DATA_ERROR);
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
