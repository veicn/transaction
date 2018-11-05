package com.sinochem.crude.trade.shipping.remote;

import com.sinochem.crude.trade.shipping.vo.ConfirmationSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * @author WGP
 * @description 更新确认单 单据字段的dubbo接口
 * @date 2018/5/4
 **/
public interface ConfirmationSheetRemoteService {
    Integer updateConfirmationSheetFileByOrderId(ConfirmationSheetRemoteVO confirmationSheetRemoteVO)throws BizException;

}
