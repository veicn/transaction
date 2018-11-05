package com.sinochem.crude.trade.shipping.remote;

import com.sinochem.crude.trade.shipping.vo.AgreementRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * @author WGP
 * @description  更新协议单 单据字段的dubbo接口
 * @date 2018/5/4
 **/
public interface AgreementRemoteService {
    Integer updateAgreementFileByOrderId(AgreementRemoteVO agreementRemoteVO)throws BizException;
}
