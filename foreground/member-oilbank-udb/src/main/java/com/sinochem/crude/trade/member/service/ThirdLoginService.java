package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.model.to.MSGMemberTO;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * Created by xyuser on 2018/5/25.
 */
public interface ThirdLoginService {


    String post(Object entity, String postMember) throws BizException;


}
