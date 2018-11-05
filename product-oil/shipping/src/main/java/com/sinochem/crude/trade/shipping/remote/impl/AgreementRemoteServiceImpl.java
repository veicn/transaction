package com.sinochem.crude.trade.shipping.remote.impl;


import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.remote.AgreementRemoteService;
import com.sinochem.crude.trade.shipping.vo.AgreementRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/4
 **/

@Service("agreementRemoteServiceImpl")
public class AgreementRemoteServiceImpl implements AgreementRemoteService {

    private final  Logger logger = LoggerFactory.getLogger(AgreementRemoteServiceImpl.class);

    @Autowired
    private AgreementMapper agreementMapper;
    /**
     * 根据订单ID更新协议单的文件
     * @param agreementRemoteVO
     * @return 成功（1)  失败 （0） 异常（2）
     */
    @Override
    public Integer updateAgreementFileByOrderId(AgreementRemoteVO agreementRemoteVO)throws BizException{
        if(agreementRemoteVO == null || agreementRemoteVO.getOrderId()==null){
            throw new BizException("参数为空！");
        }
            Agreement agreement = new Agreement();
            agreement.setOrderId(agreementRemoteVO.getOrderId());
            String Q88FileUrl = agreementRemoteVO.getUploadQ88();
            if(Q88FileUrl != null){
                agreement.setUploadQ88(Q88FileUrl);
                agreement.setUploadQ88FileNm(agreementRemoteVO.getUploadQ88FileNm());
            }else{
                agreement.setUploadQ88("");
                agreement.setUploadQ88FileNm("");
            }
            try{
                int flag =  agreementMapper.updateFileByOrderId(agreement);
                return flag>0?Integer.valueOf(1):Integer.valueOf(0);
            }catch (Exception e){
                logger.error("更新协议失败",e);
                return Integer.valueOf(2);
            }


    }
}
