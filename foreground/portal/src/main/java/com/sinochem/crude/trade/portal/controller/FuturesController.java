package com.sinochem.crude.trade.portal.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.portal.constant.MsgConstant;
import com.sinochem.crude.trade.portal.constant.UrlMapping;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.enums.PriceCompareResultEnum;
import com.sinochem.crude.trade.portal.helper.ExchangeRateHelper;
import com.sinochem.crude.trade.portal.helper.SymbolModelHelper;
import com.sinochem.crude.trade.portal.model.vo.PriceCompareVO;
import com.sinochem.crude.trade.portal.model.vo.SpotEstimationVO;
import com.sinochem.crude.trade.portal.service.DictionaryService;
import com.sinochem.crude.trade.portal.service.FuturesService;
import com.sinochem.crude.trade.portal.service.impl.strategy.YearMonthPairStrategy;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.math.BigDecimal;
import java.util.Calendar;

@Controller
public class FuturesController {

    private final Logger LOGGER = LoggerFactory.getLogger(FuturesController.class);

    private final int SCALE = 4;

    @Autowired
    private FuturesService futuresService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private SymbolModelHelper symbolModelHelper;

    @Autowired
    private YearMonthPairStrategy yearMonthPairStrategy;

    @Autowired
    private ExchangeRateHelper exchangeRateHelper;

    @RequestMapping(UrlMapping.PRICE_COMPARE)
    public ModelAndView priceCompare(@RequestBody PriceCompareVO priceCompareVO) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new MappingJacksonJsonView());

        try {
            String futuresYearMonthPair = priceCompareVO.getFuturesYearMonthPair();
            if (StringUtil.isEmpty(futuresYearMonthPair)) {
                throw new RuntimeException(VisitorLocale.getByCurrentLanguage(MsgConstant.PORTAL0000));
            }

            String currencyCode = priceCompareVO.getCurrencyCode();
            if (StringUtil.isEmpty(currencyCode)) {
                throw new RuntimeException(VisitorLocale.getByCurrentLanguage(MsgConstant.PORTAL0000));
            }
            BigDecimal exchangeRate = exchangeRateHelper.getExchangeRate(currencyCode);

            String futuresPriceString = priceCompareVO.getFuturesPrice();
            if (StringUtil.isEmpty(futuresPriceString)) {
                throw new RuntimeException(VisitorLocale.getByCurrentLanguage(MsgConstant.PORTAL0000));
            }
            BigDecimal futuresPrice = new BigDecimal(futuresPriceString);

            String spotPriceString = priceCompareVO.getSpotPrice();
            if (StringUtil.isEmpty(spotPriceString)) {
                throw new RuntimeException(VisitorLocale.getByCurrentLanguage(MsgConstant.PORTAL0000));
            }
            BigDecimal spotPrice = new BigDecimal(spotPriceString).multiply(exchangeRate);

            Calendar futuresCalendar = yearMonthPairStrategy.convertYearMonthPairToCalendar(futuresYearMonthPair);;
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.add(Calendar.MONTH, 3);

            mav.addObject("currencyCode", currencyCode);

            if (futuresCalendar.compareTo(currentCalendar) < 0 || spotPrice.equals(futuresPrice)) {
                mav.addObject("type", PriceCompareResultEnum.NO_PROFIT_OPPORTUNITY.getCode());
            } else {
                BigDecimal result = futuresPrice.subtract(spotPrice).divide(exchangeRate, SCALE, BigDecimal.ROUND_HALF_UP);

                if (result.compareTo(BigDecimal.ZERO) < 0) {
                    mav.addObject("type", PriceCompareResultEnum.PHYSICAL_UNDERESTIMATED.getCode());
                    mav.addObject("result", result.abs().toPlainString());
                } else {
                    mav.addObject("type", PriceCompareResultEnum.PHYSICAL_OVERVALUED.getCode());
                    mav.addObject("result", result.abs().toPlainString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    @RequestMapping(UrlMapping.GET_INE_SYMBOL_PRICE_REALTIME)
    public ModelAndView getIneSymbolPriceRealtime(CurrentUser currentUser, String symbolName) throws BizException {
        ModelAndView mav = new ModelAndView();
        mav.setView(new MappingJacksonJsonView());

        try {
            if (symbolName != null) {
                mav.addObject("symbolPriceRealTime", symbolModelHelper.getIneSymbolPriceRealtime(symbolName).toPlainString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    @RequestMapping(UrlMapping.FUTURES_INDEX)
    public ModelAndView futuresIndex(CurrentUser currentUser, SpotEstimationVO spotEstimationVO) throws BizException {
        ModelAndView mav = new ModelAndView();

        try {
            if (spotEstimationVO != null) {
                String oilTypeCode = spotEstimationVO.getOilTypeCode();

                if (StringUtil.isEmpty(oilTypeCode)) {
                    spotEstimationVO.setOilTypeCode(OilTypeEnum.OMAN.getCode());
                }

                SpotEstimationVO spotEstimationResultVO = futuresService.calculateSpotEstimation(spotEstimationVO);
                mav.addObject("spotEstimationVO", spotEstimationResultVO);
            }

            mav.addObject("estimationTipVO", futuresService.getDefaultEstimationTipVO());

        } catch (Exception e) {
            if (e instanceof BizException) {
                throw (BizException) e;
            } else {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
                mav.addObject("error", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
            }
        } finally {
            try {
                mav.addObject("ineSymbolList", symbolModelHelper.getINESymbolLost());
                mav.addObject("oilTypeList", dictionaryService.oilTypeMap().values().toArray());
                mav.addObject("availableDischargePortList", dictionaryService.availableDischargePortMap().values().toArray());
                mav.addObject("availableArrivalMonthList", dictionaryService.availableArrivalMonthMap().values().toArray());
                mav.addObject("currencyList", dictionaryService.currencyMap().values().toArray());
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
                mav.addObject("error", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
            }
        }

        return mav;
    }
}
