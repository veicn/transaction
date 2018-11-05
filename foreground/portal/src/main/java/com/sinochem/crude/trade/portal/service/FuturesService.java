package com.sinochem.crude.trade.portal.service;

import com.sinochem.crude.trade.portal.helper.ExchangeRateHelper;
import com.sinochem.crude.trade.portal.model.vo.EstimationTipVO;
import com.sinochem.crude.trade.portal.model.vo.SpotEstimationVO;

/**
 * 期货相关服务
 * @author Yichen Zhao
 * date: 20180411
 */
public interface FuturesService {

    /**
     * 计算实货价格
     */
    SpotEstimationVO calculateSpotEstimation(SpotEstimationVO spotEstimationVO) throws Exception;

    /**
     * 获取提示信息VO
     */
    EstimationTipVO getDefaultEstimationTipVO() throws Exception;
}
