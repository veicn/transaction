package com.sinochem.crude.trade.order.service;

import java.util.Map;

public interface MessageService {
    /**
     * 发送消息，包括站内信，邮件，短信
     * @param title     主题
     * @param memberId  接收用户ID
     * @param tplName   模板名称
     * @param model     模板参数
     */
    public void sendMessage(String title, Long memberId, Long epMemberId, String tplName, Map<String, Object> model);
}
