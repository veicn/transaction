/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.mq;

/**
 * 异步消息生产者
 *
 * @author HuangWj
 */
public interface MQProducer {

    /**
     * 发送异步消息
     * @param message 消息体
     * @param topic 主题
     * @param tag 标签
     */
    <T> void send(T message, String topic, String tag);
}
