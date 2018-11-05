package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.common.constant.HttpRequestAttributes;
import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.ShipBerthVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(UrlMapping.COMPONENTS)
public class ComponentsController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ExceptionHelper exceptionHelper;

    @RequestMapping(UrlMapping.BERTH_LIST)
    public ModelAndView berthList(CurrentUser currentUser, String shipPortCode, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            HashMap<String, ShipBerthVO> shipBerthVOMap = dictionaryService.getShipBerthMapByShipPort(shipPortCode);
            resultDatasVO.setDatas(shipBerthVOMap.values().toArray());
        } catch (RuntimeException e) {
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        if (((List<?>) request.getAttribute(HttpRequestAttributes.MEDIA_TYPES)).contains(MediaType.APPLICATION_JSON)) {
            mav.setView(new MappingJacksonJsonView());
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }
    @RequestMapping("berth_list2")
    public ModelAndView berthList2(CurrentUser currentUser, String shipPortCode, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        ResultDatasVO resultDatasVO = new ResultDatasVO();

        try {
            HashMap<String, ShipBerthVO> shipBerthVOMap = dictionaryService.getShipBerthMapByShipPort(shipPortCode);
            resultDatasVO.setDatas(shipBerthVOMap.values().toArray());
        } catch (RuntimeException e) {
            resultDatasVO.setMessageVO(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

        if (((List<?>) request.getAttribute(HttpRequestAttributes.MEDIA_TYPES)).contains(MediaType.APPLICATION_JSON)) {
            mav.setView(new MappingJacksonJsonView());
        }

        mav.addAllObjects((Map<String, ?>) resultDatasVO.toMap());
        return mav;
    }
}
