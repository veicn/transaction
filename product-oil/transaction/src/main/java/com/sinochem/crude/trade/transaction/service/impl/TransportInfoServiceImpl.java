package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.TransportInfoMapper;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.domain.po.TransportInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.service.TransportInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class TransportInfoServiceImpl implements TransportInfoService {

    @Autowired
    private TransportInfoMapper transportInfoMapper;

    @Autowired
    private IdentificationHelper identificationHelper;

    @Override
    public Long saveTransportInfo(CurrentUser currentUser, TransportInfo transportInfo) throws BizException {

        if (transportInfo == null) {
            return null;
        }

        transportInfo.setUuid(identificationHelper.generateUuid());
        transportInfoMapper.insert(transportInfo);
        return transportInfo.getId();
    }

    @Override
    public Long saveAdditionalTransportInfo(CurrentUser currentUser, Long originalId, TransportInfo transportInfo) throws BizException {

        if(transportInfo != null){
            transportInfo.setUuid(identificationHelper.generateUuid());
        }
        if (originalId == null) {
            return saveTransportInfo(currentUser, transportInfo);
        }

        TransportInfo originalTransportInfo = getTransportInfoById(currentUser, originalId);
        if (originalTransportInfo == null) {
            return saveTransportInfo(currentUser, transportInfo);
        }

        originalTransportInfo.setUuid(identificationHelper.generateUuid());
        Long id = saveTransportInfo(currentUser, originalTransportInfo);
        transportInfo.setId(id);
        transportInfoMapper.updateByPrimaryKeySelective(transportInfo);

        return id;
    }

    @Override
    public void deleteTransportInfoById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    public void updateTransportInfo(CurrentUser currentUser, TransportInfo transportInfo) throws BizException {
        BizException bizException = new BizException();
        if ( transportInfo == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        transportInfoMapper.updateByPrimaryKey(transportInfo);
    }

    @Override
    public void updateTransportInfoSelective(CurrentUser currentUser, TransportInfo transportInfo) throws BizException {
        BizException bizException = new BizException();
        if ( transportInfo == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        transportInfoMapper.updateByPrimaryKeySelective(transportInfo);
    }

    @Override
    public TransportInfo getTransportInfoById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        TransportInfo transportInfo = transportInfoMapper.selectByPrimaryKey(id);
        return transportInfo;
    }

    @Override
    public TransportInfo getTransportInfoByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        TransportInfo transportInfo = transportInfoMapper.selectByUuid(uuid);
        return transportInfo;
    }

    @Override
    public void deleteTransportInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || id == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode()); //使用ExceptionEnum来枚举异常的code
            throw bizException;
        }

        TransportInfo transportInfo = transportInfoMapper.selectByPrimaryKey(id);
        if (transportInfo == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        TransportInfo transportInfoForUpdate = new TransportInfo();
        transportInfoForUpdate.setId(id);
        transportInfoForUpdate.setAliveFlag(Mark.DELETED);
        transportInfoMapper.updateByPrimaryKeySelective(transportInfoForUpdate);
    }
}
