package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.BiddingSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.OtherInfoMapper;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.service.OtherInfoService;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class OtherInfoServiceImpl implements OtherInfoService{

	@Autowired
	private  OtherInfoMapper otherInfoMapper;

	@Autowired
	private IdentificationHelper identificationHelper;

	
	
	@Override
	public Long saveOtherInfo(CurrentUser currentUser, OtherInfo otherInfo) throws BizException {
		if (otherInfo == null) {
			return null;
		}

		otherInfo.setUuid(identificationHelper.generateUuid());
		otherInfoMapper.insert(otherInfo);
		return otherInfo.getId();
	}

	@Override
	public Long saveAdditionalOtherInfo(CurrentUser currentUser, Long originalId, OtherInfo otherInfo) throws BizException {

		if(otherInfo != null){
			otherInfo.setUuid(identificationHelper.generateUuid());
		}
		if (originalId == null) {
			return saveOtherInfo(currentUser, otherInfo);
		}

		OtherInfo originalOtherInfo = getOtherInfoById(currentUser, originalId);
		if (originalOtherInfo == null) {
			return saveOtherInfo(currentUser, otherInfo);
		}
		originalOtherInfo.setUuid(identificationHelper.generateUuid());
		Long id = saveOtherInfo(currentUser, originalOtherInfo);
		otherInfo.setId(id);
		otherInfoMapper.updateByPrimaryKeySelective(otherInfo);

		return id;
	}

	@Override
	public void deleteOtherInfoById(CurrentUser currentUser, Long id) throws BizException {

	}

	@Override
	public void updateOtherInfo(CurrentUser currentUser, OtherInfo otherInfo) throws BizException {

	}

	@Override
	public void updateOtherInfoSelective(CurrentUser currentUser, OtherInfo otherInfo) throws BizException {

	}

	@Override
	public OtherInfo getOtherInfoById(CurrentUser currentUser, Long id) throws BizException {

		if (id == null) {
			return null;
		}

		OtherInfo otherInfo = otherInfoMapper.selectByPrimaryKey(id);
		return otherInfo;
	}

	@Override
	public OtherInfo getOtherInfoByUuid(CurrentUser currentUser, String uuid) throws BizException {

		if (StringUtil.isEmpty(uuid)) {
			return null;
		}

		OtherInfo otherInfo = otherInfoMapper.selectByUuid(uuid);
		return otherInfo;
	}

	@Override
	public void deleteOtherInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException {
		BizException bizException = new BizException();
		if(currentUser==null||id==null){
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			throw bizException;
		}
		OtherInfo otherInfoForUpdate = new OtherInfo();
		otherInfoForUpdate.setId(id);
		otherInfoForUpdate.setAliveFlag(Mark.DELETED);
		otherInfoMapper.updateByPrimaryKeySelective(otherInfoForUpdate);
	}
}
