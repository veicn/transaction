package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资源详情
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_PRODUCT_DETAIL)
public class MyProductDetailController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyProductDetailController.class);

    @Value("${id.singapore}")
    private Long idSingapore;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ExceptionHelper exceptionHelper;

    @RequestMapping(UrlMapping.INDEX)
    public ModelAndView index(CurrentUser currentUser, String uuid) {
        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, uuid);
            //查询定向企业信息
            String enterpriseIdList = saleSheet.getSpecifiedEnterpriseIdList();
            List<EnterpriseVo> list = new ArrayList<>();
            if(enterpriseIdList != null){
                String[] stringEnterpriseIdList = enterpriseIdList.split(",");
                for (String  enterpriseId:stringEnterpriseIdList) {
                    long id = Long.parseLong(enterpriseId);
                    EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(id);
                    list.add(enterpriseVo);
                }
            }
            mav.addObject("enterpriseList",list);
            SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser, saleSheet);
            SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
            mav.addObject("isSingapore", idSingapore.equals(currentUser.getEpMemberId()));
            mav.addObject("saleSheetCombineVO", saleSheetCombineVO);

            if(null!=currentUser.getEpMemberId()&&currentUser.getEpMemberId()==100008253)
                mav.addObject("agentEnterpriseVo",enterpriseService.getByMemberId(100008254L));


        } catch (BizException e) {
            int exceptionCode = e.getCode();
            resultDatasVO.setMessageVO(exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }
}
