package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.dao.EnterpriseContactMapper;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseContactVo;
import com.sinochem.crude.trade.member.service.EnterpriseContactService;
import com.sinochem.crude.trade.member.service.udbservice.EnterpriseContactUdbService;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by x on 2018/3/21.
 */
@Component
public class EnterpriseContactServiceImpl implements EnterpriseContactService{
    @Autowired
    EnterpriseContactMapper enterpriseContactMapper;

    @Autowired
	EnterpriseContactUdbService enterpriseContactUdbService;



    @Override
    public EnterpriseContact findContactWithMbIdAndEpId(EnterpriseContact contact){
        return enterpriseContactMapper.findContactWithMbIdAndEpId(contact);
    }

	@Override
	public int insert(EnterpriseContact contact) throws BizException {
		UdbResult<UdbEnterpriseContactVo> result = enterpriseContactUdbService.udbInsert(contact);
		if(UdbCodeConstant.SUCCESS.equals(result.getCode())){
			contact.setUdbUuid(result.getResponse().getEntpContsUid());
			return enterpriseContactMapper.insert(contact);
		}else{
			throw new BizException(result.getMessage());
		}
	}
	
	@Override
	public void updateByPrimaryKey(EnterpriseContact contact)
			throws BizException {
    	UdbResult result = enterpriseContactUdbService.udbUpdate(contact);
    	if(UdbCodeConstant.SUCCESS.equals(result.getCode())){
			enterpriseContactMapper.updateByPrimaryKey(contact);
		}else{
    		throw new BizException(result.getMessage());
		}
	}

}
