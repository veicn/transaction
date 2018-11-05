package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.PricingInfoMapper;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;
import com.sinochem.crude.trade.transaction.domain.po.PricingInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.service.PricingInfoService;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class PricingInfoServiceImpl implements PricingInfoService{

	@Autowired
	private PricingInfoMapper pricingInfoMapper;

	@Autowired
	private IdentificationHelper identificationHelper;

	
	@Override
	public Long savePricingInfo(CurrentUser currentUser, PricingInfo pricingInfo) throws BizException {

		if (pricingInfo == null) {
			return null;
		}

		pricingInfo.setUuid(identificationHelper.generateUuid());
		pricingInfoMapper.insert(pricingInfo);
		
		return pricingInfo.getId();
	}

	@Override
	public Long saveAdditionalPricingInfo(CurrentUser currentUser, Long originalId, PricingInfo pricingInfo) throws BizException {

		if(pricingInfo != null){
			pricingInfo.setUuid(identificationHelper.generateUuid());
		}
		if (originalId == null) {
			return savePricingInfo(currentUser, pricingInfo);
		}

		PricingInfo originalPricingInfo = getPricingInfoById(currentUser, originalId);
		if (originalPricingInfo == null) {
			return savePricingInfo(currentUser, pricingInfo);
		}
		originalPricingInfo.setUuid(identificationHelper.generateUuid());
		Long id = savePricingInfo(currentUser, originalPricingInfo);
		pricingInfo.setId(id);
		pricingInfoMapper.updateByPrimaryKeySelective(pricingInfo);

		return id;
	}

	@Override
	public void deletePricingInfoById(CurrentUser currentUser, Long id) throws BizException {

	}

	@Override
	public void updatePricingInfo(CurrentUser currentUser, PricingInfo pricingInfo) throws BizException {

	}

	@Override
	public void updatePricingInfoSelective(CurrentUser currentUser, PricingInfo pricingInfo) throws BizException {

	}

	@Override
	public PricingInfo getPricingInfoById(CurrentUser currentUser, Long id) throws BizException {

		if (id == null) {
			return null;
		}

		PricingInfo pricingInfo = pricingInfoMapper.selectByPrimaryKey(id);
		return pricingInfo;
	}

	@Override
	public PricingInfo getPricingInfoByUuid(CurrentUser currentUser, String uuid) throws BizException {

		if (StringUtil.isEmpty(uuid)) {
			return null;
		}

		PricingInfo pricingInfo = pricingInfoMapper.selectByUuid(uuid);
		return pricingInfo;
	}

	@Override
	public void deletePricingInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException {
		BizException bizException = new BizException();
		if(currentUser==null||id==null){
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			throw bizException;
		}
		PricingInfo pricingInfoForUpdate = new PricingInfo();
		pricingInfoForUpdate.setId(id);
		pricingInfoForUpdate.setAliveFlag(Mark.DELETED);
		pricingInfoMapper.updateByPrimaryKeySelective(pricingInfoForUpdate);
	}

}
