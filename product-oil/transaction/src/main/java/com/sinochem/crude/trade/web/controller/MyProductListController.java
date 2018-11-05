package com.sinochem.crude.trade.web.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.enums.SaleSheetStatusEnum;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 成品油资源列表
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_PRODUCT_LIST)
public class MyProductListController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyProductListController.class);
    @Autowired
    private ExceptionHelper exceptionHelper;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 成品油资源列表
     */
    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo, CurrentUser user){

        ModelAndView mav = new ModelAndView();

        try{
            mav.addObject("user", user);

            saleSheetQueryVO.setEnterpriseId(user.getEpMemberId());
            PageInfoResult pageInfoResult = saleSheetService.getSaleSheetList(user, saleSheetQueryVO, pageInfo);
            List<SaleSheet> saleSheetList = pageInfoResult.getList();
            if(!saleSheetList.isEmpty()){
                List<SaleSheetCombineVO> saleSheetCombinelist = new ArrayList<>();

                for(SaleSheet saleSheet : saleSheetList){
                    SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(user, saleSheet);
                    SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
                    saleSheetCombinelist.add(saleSheetCombineVO);
                }

                pageInfoResult.setList(saleSheetCombinelist);
            }
            mav.addObject("pageInfoList", pageInfoResult);
            mav.addObject("saleSheetQueryVO", saleSheetQueryVO);
            mav.addObject("saleType", dictionaryService.saleTypeMap().values().toArray());
            mav.addObject("category",dictionaryService.productCategoryMap().values().toArray());
            mav.addObject("saleSheetStatus",dictionaryService.saleSheetStatusMap().values().toArray());
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
    public Result productShelfOrOffShelf(String uuid,String code, CurrentUser user, ModelMap model){
        Result res = new Result();
        try{
            saleSheetService.productShelfOrOffShelf(user,uuid,code);
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            if(SaleSheetStatusEnum.RELEASED.getCode().equals(code)){
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
            saleSheetService.deleteSaleSheetByIdLogical(currentUser,uuid);
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
