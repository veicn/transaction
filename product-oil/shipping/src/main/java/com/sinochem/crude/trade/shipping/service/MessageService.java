package com.sinochem.crude.trade.shipping.service;

import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/13
 **/
public interface MessageService {

    /**
     * 根据memberId获取相应的企业名称
     * @param memberId
     * @return
     */
    EnterpriseVo getByMemberId(Long memberId);


    void sendSecondMessage( LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort);

    void sendThreeMessage(LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort);

    void sendThreeProcessMessage(ConfirmationSheet confirmationSheet);


    void sendFourMessage(LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort);
}
