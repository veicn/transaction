package com.sinochem.crude.trade.web.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Interaction;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.enums.SheetFlagEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.DemandSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageInfoWithParams;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成品油商品列表
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_LIST)
public class ProductListController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductListController.class);

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private DemandSheetService demandSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ExceptionHelper exceptionHelper;

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long quanzhou;
    /**
     * 成品油资源列表
     */
    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(CurrentUser currentUser,
                              SaleSheetQueryVO saleSheetQueryVO,String sheetFlag,DemandSheetQueryVO demndSheetQueryVO,
                              PageInfo pageInfo) throws BizException {

        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();
        if(null==sheetFlag)
            sheetFlag="1";
        try {
            HashMap<String, Object> viewMap = new HashMap<>();
            if(("2").equals(sheetFlag)){
                demndSheetQueryVO.setDemandTypeCode(saleSheetQueryVO.getSaleTypeCode());

                pageInfo.setPageSize(Interaction.DEFAULT_PAGE_SIZE);
                PageInfoResult pageInfoResult = demandSheetService.getVisibleDemandSheetList(currentUser, demndSheetQueryVO, pageInfo);

                List<DemandSheet> demandSheetList = pageInfoResult.getList();
                List<DemandSheetCombineVO> demandSheetCombineVOlist = new ArrayList<>();
                for (DemandSheet demandSheet : demandSheetList) {
                    DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(currentUser, demandSheet);

                    DemandSheetCombineVO demandSheetCombineVO = new DemandSheetCombineVO(demandSheetCombine);
                    demandSheetCombineVOlist.add(demandSheetCombineVO);
                }
                pageInfoResult.setList(demandSheetCombineVOlist);
                mav.addObject("pageInfoResult", pageInfoResult);
            }else {
                pageInfo.setPageSize(Interaction.DEFAULT_PAGE_SIZE);
                PageInfoResult pageInfoResult = saleSheetService.getVisibleSaleSheetList(currentUser, saleSheetQueryVO, pageInfo);

                List<SaleSheet> saleSheetList = pageInfoResult.getList();
                List<SaleSheetCombineVO> saleSheetCombineVOlist = new ArrayList<>();
                for (SaleSheet saleSheet : saleSheetList) {
                    SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser, saleSheet);

                    SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
                    saleSheetCombineVOlist.add(saleSheetCombineVO);
                }
                pageInfoResult.setList(saleSheetCombineVOlist);
                mav.addObject("pageInfoResult", pageInfoResult);
            }
                //这里有待优化，需要问下为何PageInfoResult不能被序列化，理应是下面的写法
                //viewMap.put("saleSheetCombineVOList", saleSheetCombineVOlist.toArray());
                //resultDatasVO.setDatas(viewMap);

            mav.addObject("saleSheetQueryVO", saleSheetQueryVO);
            mav.addObject("userId", currentUser.getEpMemberId());
            mav.addObject("idSingapore", idSingapore);
            boolean idquanzhou = quanzhou.equals(currentUser.getEpMemberId());
            mav.addObject("idquanzhou", idquanzhou);



        } catch (BizException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.getBizExceptionByCode(e.getCode()));
            mav.addObject("pageInfoResult", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
            mav.addObject("pageInfoResult", pageInfo);
        }

        mav.addObject("sheetFlag", sheetFlag);
        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }

    /**
     * 成品油资源列表查询条件
     */
    @RequestMapping(UrlMapping.SALE_QUERY)
    public ModelAndView saleQuery(CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            HashMap<String, Object> viewMap = new HashMap<>();

            //sheet类型
            viewMap.put("sheetFlagVOList", dictionaryService.sheetFlagMap().values().toArray());

            //销售类型
            viewMap.put("saleTypeVOList", dictionaryService.saleTypeMap().values().toArray());

            //商品分类
            viewMap.put("productCategoryVOList", dictionaryService.productCategoryMap().values().toArray());

            //商品规格
            viewMap.put("productSpecificationVOList", dictionaryService.productSpecificationMap().values().toArray());

            //出口类型
            viewMap.put("exportTypeVOList", dictionaryService.exportTypeMap().values().toArray());

            resultDatasVO.setDatas(viewMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        //mav.addObject("sheetFlag", sheetFlag);
        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }

    /**
     * 成品油资源列表排序条件
     */
    @RequestMapping(UrlMapping.SALE_QUERY_ORDER)
    public ModelAndView saleQueryOrder(CurrentUser currentUser) {

        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            HashMap<String, Object> viewMap = new HashMap<>();

            /*排序类型*/
            viewMap.put("orderByColumnList", dictionaryService.saleSheetQueryOrderMap());

            resultDatasVO.setDatas(viewMap);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }
}
