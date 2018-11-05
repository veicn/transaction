package com.sinochem.crude.trade.portal.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.portal.enums.OilTypeEnum;
import com.sinochem.crude.trade.portal.model.vo.EstimationTipVO;
import com.sinochem.crude.trade.portal.model.vo.SpotEstimationVO;
import com.sinochem.crude.trade.portal.service.FuturesService;
import com.sinochem.crude.trade.portal.service.impl.factory.SpotPriceStrategyFactory;
import com.sinochem.crude.trade.portal.service.impl.strategy.base.AbstractSpotPriceStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * FuturesService实现类
 * @author Yichen Zhao
 * date: 20180411
 */
@Service
public class FuturesServiceImpl implements FuturesService {

    private final int SCALE = 4;

    @Override
    public SpotEstimationVO calculateSpotEstimation(SpotEstimationVO spotEstimationVO) throws Exception {

        if (spotEstimationVO == null) {
            throw new RuntimeException("参数为空");
        }

        String oilTypeCode = spotEstimationVO.getOilTypeCode();
        if (StringUtil.isEmpty(oilTypeCode)) {
            throw new RuntimeException("油种代码为空");
        }

        AbstractSpotPriceStrategy spotPriceStrategy = SpotPriceStrategyFactory.genSpotPriceStrategy(oilTypeCode);
        if (spotPriceStrategy == null) {
            throw new RuntimeException("无法生成实货计价策略");
        }

        spotPriceStrategy.getSpotPrice(spotEstimationVO);
        return spotEstimationVO;
    }

    @Override
    public EstimationTipVO getDefaultEstimationTipVO() throws Exception {

        AbstractSpotPriceStrategy strategy = SpotPriceStrategyFactory.genSpotPriceStrategy(OilTypeEnum.OMAN.getCode());
        EstimationTipVO estimationTipVO = new EstimationTipVO();

        estimationTipVO.setCommodityInspectionFee(
                strategy.getCommodityInspectionFee().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        estimationTipVO.setForwarderAgencyFee(
                strategy.getForwarderAgencyFee().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        estimationTipVO.setHandlingCharges(
                strategy.getHandlingCharges().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        estimationTipVO.setOilPollutionCompensation(
                strategy.getOilPollutionCompensation().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        estimationTipVO.setProxySecurityCharge(
                strategy.getProxySecurityCharge().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        estimationTipVO.setProxyOilInHarbourCharge(
                strategy.getProxyOilInHarbourCharge().setScale(SCALE, BigDecimal.ROUND_HALF_UP).toPlainString()
        );

        return estimationTipVO;
    }
}
