/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.mq;

/**
 * 异步消息消费者
 *
 * @author HuangWj
 */
public interface MQConsumer {

    /**
     * 订阅消息
     * @param topic 主题
     * @param tag 标签
     * @param handler 处理器
     */
    <T> void subscribe(String topic, String tag, final Handler<T> handler);

    /**
     * 取消订阅
     * @param topic 主题
     * @param tag 标签
     */
    void unsubscribe(String topic, String tag);
}
