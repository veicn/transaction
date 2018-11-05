package com.sinochem.crude.trade.broker.service.impl;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerDeclaration;
import com.sinochem.crude.trade.broker.model.vo.TBrokerDeclarationQueryVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/7 11:30
 * @Version: [v1.0]
 */
public interface DeclarationService {
    int deleteByUuid(String uuid);
    TBrokerDeclaration selectByUuid(String uuid);
    Page<TBrokerDeclaration> selectByQuery(TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO);

    int insertSelective(TBrokerVO tBrokerVO) throws BizException;

    int updateByUuidSelective(TBrokerVO tBrokerVO) throws BizException;

}
