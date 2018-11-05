package com.sinochem.crude.trade.transaction.service.impl;

import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.dao.ContractExtendMapper;
import com.sinochem.crude.trade.transaction.domain.po.ContractExtend;
import com.sinochem.crude.trade.transaction.model.vo.ContractExtendVO;
import com.sinochem.crude.trade.transaction.service.ContractExtendService;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/12 15:50
 * @Version: [v1.0]
 */
@Service
public class ContractExtendServiceImpl implements ContractExtendService{

    @Autowired
    ContractExtendMapper contractExtendMapper;

    @Override
    public int insertSelective(ContractExtend record) {
        return contractExtendMapper.insertSelective(record);
    }

    @Override
    public ContractExtend selectByPrimaryKey(Long id) {
        return contractExtendMapper.selectByPrimaryKey(id);
    }

    @Override
    public ContractExtend selectByContractId(Long contractId) {
        return contractExtendMapper.selectByContractId(contractId);
    }

    @Override
    public int updateByPrimaryKeySelective(ContractExtend record) {
        return contractExtendMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return contractExtendMapper.deleteByPrimaryKey(id);
    }

    public int alterContractExtend(ContractExtendVO contractExtendVO){
        int num=0;
        ContractExtend contractExtend=new ContractExtend();
        if(StringHelper.isNullOrEmptyString(contractExtendVO.getContractId()))
        {
            return 0;
        }
       Long contractId=Long.valueOf(contractExtendVO.getContractId());
       ContractExtend contractExtend1= contractExtendMapper.selectByContractId(contractId);
       if(contractExtend1==null) {
           contractExtend.setRefContractId(contractId);
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getCreateTime())) {
               contractExtend.setGmtCreated(DateUtil.getDateTime(contractExtendVO.getCreateTime()));
           }
           contractExtend.setUserCreated(Long.valueOf(contractExtendVO.getCreateUser()));
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getSettleAmt())) {
               contractExtend.setSettleAmt(new BigDecimal(contractExtendVO.getSettleAmt()));
           }
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getSettleNum())) {
               contractExtend.setSettleNum(new BigDecimal(contractExtendVO.getSettleNum()));
           }
           contractExtend.setContractDate(contractExtendVO.getContractDate());
           contractExtend.setContractNo(contractExtendVO.getContractNo());
           contractExtend.setAttr21(contractExtendVO.getSettleNumUnit());
           contractExtend.setAttr22(contractExtendVO.getSettleAmtUnit());
           num = contractExtendMapper.insertSelective(contractExtend);
       }
       else {
//           contractExtend1.setRefContractId(contractId);
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getCreateTime())) {
               contractExtend1.setGmtModified(DateUtil.getDateTime(contractExtendVO.getCreateTime()));
           }
           contractExtend1.setUserModified(Long.valueOf(contractExtendVO.getCreateUser()));
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getSettleAmt())) {
               contractExtend1.setSettleAmt(new BigDecimal(contractExtendVO.getSettleAmt()));
               contractExtend1.setAttr22(contractExtendVO.getSettleAmtUnit());
           }
           if (!StringHelper.isNullOrEmptyString(contractExtendVO.getSettleNum())) {
               contractExtend1.setSettleNum(new BigDecimal(contractExtendVO.getSettleNum()));
               contractExtend1.setAttr21(contractExtendVO.getSettleNumUnit());
           }
           contractExtend1.setContractDate(contractExtendVO.getContractDate());
           contractExtend1.setContractNo(contractExtendVO.getContractNo());
           num = contractExtendMapper.updateByPrimaryKeySelective(contractExtend1);
       }
        return  num;
    }

}
