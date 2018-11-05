package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.order.model.vo.OrderStatusVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface OrderStatusDisplayService {
    List<OrderStatusVO> getStatusList(Long orderId) throws BizException;
}
