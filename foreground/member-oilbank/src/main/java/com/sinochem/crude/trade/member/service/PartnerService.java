package com.sinochem.crude.trade.member.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.domain.Partner;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface PartnerService {

    Partner selectByPrimaryKey(Long id) throws BizException;

    void insert(Partner partner, Long memberId) throws BizException;

    List<Partner> getPartnerList(Partner partner, PageInfo pageInfo);

    void update(Partner partner, Long memberId) throws BizException;
}
