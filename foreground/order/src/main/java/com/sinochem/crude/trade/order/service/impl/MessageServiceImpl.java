package com.sinochem.crude.trade.order.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.http.MessageHttpUtil;
import com.sinochem.crude.trade.order.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.order.service.MemberInfoService;
import com.sinochem.crude.trade.order.service.MessageService;
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
     * 发送消息，包括站内信，邮件，短信
     * @param title     主题
     * @param memberId  接收用户ID
     * @param tplName   模板名称
     * @param model     模板参数
     */
    @Override
    public void sendMessage(String title, Long memberId, Long epMemberId, String tplName, Map<String, Object> model) {
        try{
            // 发送站内信
            String result = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                    MessageHttpUtil.generateMessageParams(tplName,epMemberId + "," + memberId,"3",title,model));
            logger.info("发送站内信:" + result);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("发送站内信异常" + e);
        }
        try{
            // 发送站内信
            String result = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.APP_URL).toString(),
                    MessageHttpUtil.generateAPPParams(tplName,memberId+"",model));
            logger.info("推送APP消息:" + result);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("推送APP消息异常" + e);
        }
        MemberInfoVO memberInfoVO = null;
        try{
            memberInfoVO = memberInfoService.memberInfo(memberId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取账户信息异常" + e);
        }
        if(memberInfoVO != null){
            if(StringUtil.isNotEmpty(memberInfoVO.getEmail())){
                try{
                    // 发送邮件
                    String result = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
                            MessageHttpUtil.generateTplMailParams(tplName,title,memberInfoVO.getEmail(),model));
                    logger.info("发送邮件:" + result);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("发送邮件异常" + e);
                }
            }
            if(StringUtil.isNotEmpty(memberInfoVO.getMobile())){
                try{
                    // 发送短信
                    String result = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
                            MessageHttpUtil.generateSmsParams(tplName,memberInfoVO.getMobile(),model));
                    logger.info("发送短信:" + result);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("发送短信异常" + e);
                }
            }
        }else{
            logger.error("未获取到账户信息!");
        }
    }
}
