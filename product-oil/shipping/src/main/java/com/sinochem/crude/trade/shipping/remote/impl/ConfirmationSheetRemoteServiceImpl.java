package com.sinochem.crude.trade.shipping.remote.impl;

import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.remote.ConfirmationSheetRemoteService;
import com.sinochem.crude.trade.shipping.vo.ConfirmationSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/4
 **/
@Service("confirmationSheetRemoteServiceImpl")
public class ConfirmationSheetRemoteServiceImpl implements ConfirmationSheetRemoteService {


    private final Logger logger = LoggerFactory.getLogger(AgreementRemoteServiceImpl.class);

    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper;

    @Autowired
    private AgreementMapper agreementMapper;

    /**
     * 根据订单ID更新确认单的文件
     *
     * @param confirmationSheetRemoteVO
     * @return 成功（1）失败（0）  异常（2）
     */
    @Override
    public Integer updateConfirmationSheetFileByOrderId(ConfirmationSheetRemoteVO confirmationSheetRemoteVO)  {
        try {
        if (confirmationSheetRemoteVO == null || confirmationSheetRemoteVO.getOrderId() == null) {
            throw new BizException("根据订单ID更新确认单的文件 参数为空");
        }

            ConfirmationSheet confirmationSheet = new ConfirmationSheet();
            Agreement agreement = new Agreement();
            Long orderId = confirmationSheetRemoteVO.getOrderId();
            confirmationSheet.setOrderId(orderId);
            agreement.setOrderId(orderId);
            String Q88FileUrl = confirmationSheetRemoteVO.getUploadQ88();
            if(Q88FileUrl != null){
                confirmationSheet.setUploadQ88(confirmationSheetRemoteVO.getUploadQ88());
                confirmationSheet.setUploadQ88FileNm(confirmationSheetRemoteVO.getUploadQ88FileNm());
                agreement.setUploadQ88(confirmationSheetRemoteVO.getUploadQ88());
                agreement.setUploadQ88FileNm(confirmationSheetRemoteVO.getUploadQ88FileNm());
            }else{
                confirmationSheet.setUploadQ88("");
                confirmationSheet.setUploadQ88FileNm("");
                agreement.setUploadQ88("");
                agreement.setUploadQ88FileNm("");
            }
            int flag = agreementMapper.updateFileByOrderId(agreement);
             flag += confirmationSheetMapper.updateFileByOrderId(confirmationSheet);
            return flag > 0 ? Integer.valueOf(1) : Integer.valueOf(0);
        } catch (Exception e) {
            logger.error("确认单更新单据失败",e);
            return  Integer.valueOf(2);
        }

    }
}
