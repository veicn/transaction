package com.sinochem.crude.trade.member.remote.service;


import com.sinochem.crude.trade.member.remote.vo.PersonVo;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/22
 */
public interface PersonService {

    PersonVo getByMemberId(Long memberId);

}
