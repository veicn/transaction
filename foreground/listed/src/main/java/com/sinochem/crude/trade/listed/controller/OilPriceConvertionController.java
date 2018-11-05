package com.sinochem.crude.trade.listed.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.enums.OilModelTypeEnum;
import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.model.vo.OilModelVO;
import com.sinochem.crude.trade.listed.model.vo.PriceConvertionRequestVO;
import com.sinochem.crude.trade.listed.model.vo.SymbolYearMonthPairVO;
import com.sinochem.crude.trade.listed.service.OilPriceConvertionService;
import com.sinochem.crude.trade.listed.service.impl.factory.OilModelFactory;
import com.sinochem.crude.trade.listed.service.impl.strategy.YearMonthPairStrategy;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 用于转价功能的Controller
 * @author Yichen Zhao
 * date: 20180203
 */
@Controller
@WithoutAccess
public class OilPriceConvertionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppApiController.class);

    @Autowired
    private OilPriceConvertionService oilPriceConvertionService;

    @Autowired
    private YearMonthPairStrategy yearMonthPairStrategy;

    /**
     * 油种类型列表
     * @return
     */
    @RequestMapping(value = UrlMapping.OILPRICECONVERTION_GETOILMODELTYPELIST)
    @ResponseBody
    public ResultDatas getOilModelTypeList() {
        List<String> oilModelTypeList = OilModelTypeEnum.convertToList();
        ResultDatas resultDatas = new ResultDatas();

        try {
            resultDatas.setDatas(oilModelTypeList);
        } catch (Exception e) {
            LOGGER.error("",e);
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0052));
        }

        return resultDatas;
    }

    /**
     * 获取资讯合约时间列表
     * @param oilType
     * @return
     */
    @RequestMapping(value = UrlMapping.OILPRICECONVERTION_GETSYMBOLDATELIST)
    public ResultDatas getSymbolDateList(String oilType) {
        ResultDatas resultDatas = new ResultDatas();

        try {
            List<Calendar> calendarList = oilPriceConvertionService.getSymbolDateListByOilType(oilType);

            List<SymbolYearMonthPairVO> symbolYearMonthPairVOList = new ArrayList<>();
            for (Calendar calendar : calendarList) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;

                SymbolYearMonthPairVO symbolYearMonthPairVO = new SymbolYearMonthPairVO();
                symbolYearMonthPairVO.setYear(Integer.valueOf(year));
                symbolYearMonthPairVO.setMonth(Integer.valueOf(month));

                String yearString = String.valueOf(year);
                String monthString = month <= 9 ? "0" + month : String.valueOf(month);
                symbolYearMonthPairVO.setYearMonthPair(yearString + monthString);

                symbolYearMonthPairVOList.add(symbolYearMonthPairVO);
            }

            resultDatas.setDatas(symbolYearMonthPairVOList);
        } catch (Exception e) {
            LOGGER.error("",e);
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0052));
        }

        return resultDatas;
    }

    @RequestMapping(UrlMapping.OILPRICECONVERTION_GETDUBAISYMBOLPRICE)
    public ModelAndView getDubaiSymbolPrice(String targetYearMonthPair) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new MappingJacksonJsonView());
        com.sinochem.it.b2b.common.result.ResultDatas resultDatas = new com.sinochem.it.b2b.common.result.ResultDatas();

        try {
            Calendar targetCalendar = yearMonthPairStrategy.convertYearMonthPairToCalendar(targetYearMonthPair);
            BigDecimal dubaiPrice = oilPriceConvertionService.getDubaiSymbolPrice(targetCalendar);

            resultDatas.setDatas(dubaiPrice);
        } catch (Exception e) {
            resultDatas.setFail("");
        }

        mav.addAllObjects((Map<String, ?>)resultDatas.toMap());
        return mav;
    }

    /**
     * 获取差价
     * @param priceConvertionRequestVO
     * @return
     */
    @RequestMapping(value = UrlMapping.OILPRICECONVERTION_GETPRICEDIFFERENCE)
    @ResponseBody
    @WithoutAccess
    public ResultDatas getPriceDifference(@RequestBody PriceConvertionRequestVO priceConvertionRequestVO) {
        long start = System.currentTimeMillis();
        ResultDatas resultDatas = new ResultDatas();
        try {
            OilModelVO origin = priceConvertionRequestVO.getOriginOilModel();
            if (origin == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0053));
            }

            OilModelVO target = priceConvertionRequestVO.getTargetOilModel();
            if (target == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0054));
            }

            String originOilType = origin.getOilType();
            if (StringUtil.isEmpty(originOilType)) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0055));
            }

            Integer originYear = origin.getYear();
            if (originYear == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0056));
            }

            Integer originMonth = origin.getMonth();
            if (originMonth == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0057));
            }

            String targetOilType = target.getOilType();
            if (StringUtil.isEmpty(targetOilType)) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0058));
            }

            Integer targetYear = target.getYear();
            if (targetYear == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0059));
            }

            Integer targetMonth = target.getMonth();
            if (targetMonth == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0060));
            }

            OilModel originOilModel = OilModelFactory.getOilModel(originOilType, originYear, originMonth);
            if (originOilModel == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0061));
            }

            OilModel targetOilModel = OilModelFactory.getOilModel(targetOilType, targetYear, targetMonth);
            if (targetOilModel == null) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0062));
            }

            BigDecimal priceDifference = oilPriceConvertionService.getOilPriceConvertion(originOilModel, targetOilModel);
            resultDatas.setDatas(priceDifference.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        } catch (Exception e) {
            LOGGER.error("",e);
            resultDatas.setFail(e.getMessage());
        }
        long end = System.currentTimeMillis();
        LOGGER.info("getPriceDifference duartion : " + (end - start));
        return resultDatas;
    }
}
