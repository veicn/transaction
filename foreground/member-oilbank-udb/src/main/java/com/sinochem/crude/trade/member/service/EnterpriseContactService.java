package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * Created by x on 2018/3/21.
 */
public interface EnterpriseContactService {

    public EnterpriseContact findContactWithMbIdAndEpId(EnterpriseContact contact);
    
    public int insert(EnterpriseContact record) throws BizException;
    
    public void updateByPrimaryKey(EnterpriseContact contact) throws BizException;
}
