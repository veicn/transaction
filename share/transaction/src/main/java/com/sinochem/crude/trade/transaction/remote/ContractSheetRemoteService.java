package com.sinochem.crude.trade.transaction.remote;

import com.sinochem.crude.trade.transaction.vo.ContractSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 合约（订单）的Service
 * @author Yichen Zhao
 * date: 20180323
 */

public interface ContractSheetRemoteService {

    /**
     * 根据uuid查询合约单（订单）信息
     */
    ContractSheetRemoteVO getContractSheetByUuid(String uuid) throws BizException;

    /**
     * 关闭订单, 返回1表示成功，0表示失败
     */
    int completeContractSheet(Long id);

    /**
     * 根据id 获取 对象
     */
    ContractSheetRemoteVO getContractSheetById(Long id);
}
