package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.http.MessageHttpUtil;
import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.member.service.MemberInfoService;
import com.sinochem.crude.trade.member.service.MessageService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Autowired
    private URLBroker messageServer;
    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Autowired
    private MemberInfoService memberInfoService;

    /**
     * 站内信推送
     */
    @Override
    public String sendPush(Long memberId, Integer level, String title, String content, Long receiver) throws BizException {
        String res = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                MessageHttpUtil.generateNomalPushParams(title, content, level, memberId, receiver));
        return res;
    }

    /**
     * 发送非模板短信
     */
    @Override
    public String sendSms(Long memberId, String content, String mobile) throws BizException {
        String res = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
                MessageHttpUtil.generateNomalSmsParams(mobile, content, memberId));
        return res;
    }

    /**
     * 发送模板短信
     *
     * @param memberId
     * @param mobile
     * @param tplName
     * @param model
     * @return
     * @throws BizException
     */
    @Override
    public String sendSms(Long memberId, String mobile, String tplName, Map<String, Object> model) throws BizException {
        String res = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
                MessageHttpUtil.generateSmsParams(tplName, mobile, model));
        return res;
    }

    /**
     * 发送普通邮件
     */
    public String sendMail(Long memberId, String receiver, String content, String subject) throws BizException {
        String res = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MAIL_URL).toString(),
                MessageHttpUtil.generateMailParams(memberId, receiver, content, subject));
        return res;
    }

    /**
     * 发送消息，包括站内信，邮件，短信
     *
     * @param title    主题
     * @param memberId 接收用户ID
     * @param tplName  模板名称
     * @param model    模板参数
     */
    @Override
    public void sendMessage(String title, Long memberId, String tplName, Map<String, Object> model) {
        try {
            // 向被定向的企业发送站内信
            MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                    MessageHttpUtil.generateMessageParams(tplName, memberId + "", "3", title, model));
        } catch (Exception e) {
            logger.error("发送企业站内信异常" + e);
        }
        MemberInfoVO memberInfoVO = null;
        try {
            memberInfoVO = memberInfoService.memberInfo(memberId);
        } catch (Exception e) {
            logger.error("获取账户信息异常" + e);
        }
        if (memberInfoVO != null) {
            if (StringUtil.isNotEmpty(memberInfoVO.getEmail())) {
                try {
                    // 发送邮件
                    MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
                            MessageHttpUtil.generateTplMailParams(tplName, title, memberInfoVO.getEmail(), model));
                } catch (Exception e) {
                    logger.error("发送邮件异常" + e);
                }
            }
            if (StringUtil.isNotEmpty(memberInfoVO.getMobile())) {
                try {
                    // 发送短信
                    MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
                            MessageHttpUtil.generateSmsParams(tplName, memberInfoVO.getMobile(), model));
                } catch (Exception e) {
                    logger.error("发送短信异常" + e);
                }
            }
        } else {
            logger.error("未获取到账户信息!");
        }
    }
}
