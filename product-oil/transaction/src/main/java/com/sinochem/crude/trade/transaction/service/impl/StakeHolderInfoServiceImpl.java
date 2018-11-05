package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.StakeholderInfoMapper;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.domain.po.StakeholderInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.service.StakeHolderInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class StakeHolderInfoServiceImpl implements StakeHolderInfoService{

    @Autowired
    private StakeholderInfoMapper stakeholderInfoMapper;

    @Autowired
    private IdentificationHelper identificationHelper;

    @Override
    public Long saveStakeholderInfo(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException {

        if (stakeholderInfo == null) {
            return null;
        }

        stakeholderInfo.setUuid(identificationHelper.generateUuid());
        stakeholderInfoMapper.insert(stakeholderInfo);
        return stakeholderInfo.getId();
    }

    @Override
    public void deleteStakeholderInfoById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    public void updateStakeholderInfo(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || stakeholderInfo == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        stakeholderInfoMapper.updateByPrimaryKey(stakeholderInfo);
    }

    @Override
    public void updateStakeholderInfoSelective(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException {
        BizException bizException = new BizException();
        if ( stakeholderInfo == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        stakeholderInfoMapper.updateByPrimaryKeySelective(stakeholderInfo);
    }

    @Override
    public StakeholderInfo getStakeholderInfoById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        StakeholderInfo stakeholderInfo = stakeholderInfoMapper.selectByPrimaryKey(id);
        return stakeholderInfo;
    }

    @Override
    public StakeholderInfo getStakeholderInfoByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        StakeholderInfo stakeholderInfo = stakeholderInfoMapper.selectByUuid(uuid);
        return stakeholderInfo;
    }

    @Override
    public void deleteStakeholderInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || id == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode()); //使用ExceptionEnum来枚举异常的code
            throw bizException;
        }

        StakeholderInfo stakeholderInfo = stakeholderInfoMapper.selectByPrimaryKey(id);
        if (stakeholderInfo == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        StakeholderInfo stakeholderInfoForUpdate = new StakeholderInfo();
        stakeholderInfoForUpdate.setId(id);
        stakeholderInfoForUpdate.setAliveFlag(Mark.DELETED);
        stakeholderInfoMapper.updateByPrimaryKeySelective(stakeholderInfoForUpdate);
    }
}
