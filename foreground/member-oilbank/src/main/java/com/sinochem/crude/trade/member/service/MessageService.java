package com.sinochem.crude.trade.member.service;

import com.sinochem.it.b2b.common.exception.BizException;

import java.util.Map;

public interface MessageService {
    String sendPush(Long memberId, Integer level,String title, String content, Long receiver) throws BizException;

    String sendSms(Long memberId, String content, String mobile) throws BizException;
    String sendSms(Long memberId, String mobile,String tplName,Map<String,Object> model) throws BizException;

    String sendMail(Long memberId, String receiver, String content, String subject) throws BizException;

    /**
     * 发送消息，包括站内信，邮件，短信
     *
     * @param title    主题
     * @param memberId 接收用户ID
     * @param tplName  模板名称
     * @param model    模板参数
     */
    public void sendMessage(String title, Long memberId, String tplName, Map<String, Object> model);
}
