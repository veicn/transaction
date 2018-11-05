package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.service.udbservice.EnterpriseContactUdbService;
import com.sinochem.crude.trade.member.service.udbservice.EnterpriseCrudeUdbService;
import com.sinochem.crude.trade.member.util.UdbResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.member.dao.EnterpriseCrudeLogMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseCrudeMapper;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.domain.EnterpriseCrudeLog;
import com.sinochem.crude.trade.member.service.EnterpriseCrudeService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnterpriseCrudeServiceImpl implements EnterpriseCrudeService {

    @Autowired
    private EnterpriseCrudeMapper enterpriseCrudeMapper;
    @Autowired
	private EnterpriseCrudeLogMapper enterpriseCrudeLogMapper;

    @Autowired
	private EnterpriseCrudeUdbService enterpriseCrudeUdbService;

    @Override
    public void add(EnterpriseCrude enterprise) throws BizException {

    	UdbResult udbResult = enterpriseCrudeUdbService.udbInsert(enterprise);
    	if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
    		enterprise.setUdbUuid(udbResult.getResponse().toString());
			enterpriseCrudeMapper.insert(enterprise);
		}else{
    		throw new BizException(udbResult.getMessage());
		}

    	EnterpriseCrudeLog enterpriseLog = new EnterpriseCrudeLog();
        BeanUtils.copyProperties(enterprise,enterpriseLog);
        enterpriseLog.setId(null);
        enterpriseLog.setEnterpriseId(enterpriseLog.getId());
        enterpriseCrudeLogMapper.insert(enterpriseLog);
    }

    @Override
    public void update(EnterpriseCrude enterprise) throws BizException {

    	UdbResult udbResult  = enterpriseCrudeUdbService.udbUpdate(enterprise);
    	if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
			enterpriseCrudeMapper.updateByPrimaryKeySelective(enterprise);
		}else{
    		throw  new BizException(udbResult.getMessage());
		}

    	EnterpriseCrudeLog enterpriseLog = new EnterpriseCrudeLog();
        BeanUtils.copyProperties(enterprise,enterpriseLog);
        enterpriseLog.setId(null);
        enterpriseLog.setEnterpriseId(enterpriseLog.getId());
        enterpriseCrudeLogMapper.insert(enterpriseLog);
    }

	@Override
	public void deleteEnterprise(Long id)throws BizException {
    	UdbResult udbResult = enterpriseCrudeUdbService.udbDelete(id);
    	if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
			enterpriseCrudeMapper.deleteByPrimaryKey(id);
		}else{
    		throw new BizException(udbResult.getMessage());
		}
	}

	@Override
	public EnterpriseCrude selectByMemberId(Long memberId) {
		return enterpriseCrudeMapper.selectByMemberId(memberId);
	}

	@Override
	public EnterpriseCrude selectByEnterpriseId(Long enterpriseId) {
		return enterpriseCrudeMapper.selectByEnterpriseId(enterpriseId);
	}

	@Transactional
	@Override
	public void saveOrUpdate(EnterpriseCrude enterpriseCrude) throws BizException {
    	if (enterpriseCrude == null) {
    		return;
		}
		if(enterpriseCrude.getId() != null){
			update(enterpriseCrude);
		}else{
			add(enterpriseCrude);
		}
	}


}
