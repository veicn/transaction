package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.transaction.domain.po.ContractExtend;
import com.sinochem.crude.trade.transaction.model.vo.ContractExtendVO;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/12 15:46
 * @Version: [v1.0]
 */
public interface ContractExtendService {

    /**
     * 插入数据
     */
    int insertSelective(ContractExtend record);
    /**
     * 根据主键查询对象
     */
    ContractExtend selectByPrimaryKey(Long id);
    ContractExtend selectByContractId(Long contractId);

    int updateByPrimaryKeySelective(ContractExtend record);

    int deleteByPrimaryKey(Long id);
    /**
     * 根据contractid如果有就修改，如果没有就新增
     */
    int alterContractExtend(ContractExtendVO contractExtendVO);
}
