package com.sinochem.crude.trade.member.service.impl;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.dao.PartnerMapper;
import com.sinochem.crude.trade.member.domain.Partner;
import com.sinochem.crude.trade.member.service.PartnerService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public Partner selectByPrimaryKey(Long id) throws BizException {
        if (id == null) throw new BizException("id不能为空！");
        return this.partnerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void insert(Partner partner, Long memberId) throws BizException {
        validatePartner(partner);
        Date now = new Date();
        partner.setDelFlg(false);
        partner.setCreater(memberId);
        partner.setCreateTime(now);
        partner.setUpdater(memberId);
        partner.setUpdateTime(now);
        this.partnerMapper.insert(partner);
    }

    @Override
    public List<Partner> getPartnerList(Partner partner, PageInfo pageInfo) {
        PageUtils.page(pageInfo);
//        partner.setDelFlg(false);
        return this.partnerMapper.selectByPrimary(partner);
    }

    @Override
    @Transactional
    public void update(Partner partner, Long memberId) throws BizException {
        if (partner.getId() == null) throw new BizException("id不能为空！");
        partner.setUpdater(memberId);
        partner.setUpdateTime(new Date());
        this.partnerMapper.updateByPrimaryKeySelective(partner);
    }


    private void validatePartner(Partner partner) throws BizException {
        String errorMsg = null;
        if (StringUtils.isBlank(partner.getName())) errorMsg = "名称不能为空！";
        if (StringUtils.isBlank(partner.getUrl())) errorMsg = "链接地址不能为空！";
        if (StringUtils.isBlank(partner.getType())) errorMsg = "类型不能为空！";

        if (StringUtils.isNotBlank(errorMsg)) throw new BizException(errorMsg);
    }
}
