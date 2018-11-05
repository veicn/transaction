package com.sinochem.crude.trade.listed.remote.mock;

import com.sinochem.crude.trade.order.remote.DemandOrderReturnVO;
import com.sinochem.crude.trade.order.remote.DemandOrderService;
import com.sinochem.crude.trade.order.remote.DemandOrderVo;
import com.sinochem.crude.trade.order.remote.UserVo;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.stereotype.Service;

/**
 * Created by sijliu on 26/11/2017.
 */
@Service
public class DemandOrderServiceImpl implements DemandOrderService {


    public DemandOrderReturnVO createOrderByDemandData(DemandOrderVo demandOrderVo, UserVo userVo) throws BizException {
        return null;
    }

    @Override
    public void confirmContract(String orderNo, Long oper, String token) throws BizException {

    }
}
