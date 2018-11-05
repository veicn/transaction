package com.sinochem.crude.trade.shipping.remote;

import com.sinochem.crude.trade.shipping.vo.VoyageStartRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * @author WGP
 * @description  更新配载计划单据的dubbo接口
 * @date 2018/5/4
 **/
public interface VoyageStartRemoteService {
    Integer updateVoyageStartFileByOrderId(VoyageStartRemoteVO voyageStartRemoteVO)throws BizException;
    Integer updateVoyageStartDiByOrderId(VoyageStartRemoteVO voyageStartRemoteVO)throws BizException;
}
