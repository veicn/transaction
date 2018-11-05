package com.sinochem.crude.trade.common.utils;

import com.sinochem.crude.trade.common.mq.Handler;
import com.sinochem.crude.trade.common.mq.MQConsumer;
import com.sinochem.crude.trade.common.mq.MQProducer;

/**
 * 消息队列工具类
 * @author HuangWj
 */
public final class MQUtils {

    private MQUtils() { }

    private static MQProducer mqProducer;

    private static MQConsumer mqConsumer;

    static void init(MQProducer mqProducer, MQConsumer mqConsumer) {
        MQUtils.mqConsumer = mqConsumer;
        MQUtils.mqProducer = mqProducer;
    }

    /**
     * 订阅消息
     * @param topic 主题
     * @param tag 标签
     * @param handler 处理器
     */
    public static <T> void subscribe(String topic, String tag, Handler<T> handler) {
        mqConsumer.subscribe(topic, tag, handler);
    }

    /**
     * 取消订阅
     * @param topic 主题
     * @param tag 标签
     */
    public static void unsubscribe(String topic, String tag) {
        mqConsumer.unsubscribe(topic, tag);
    }

    /**
     * 发送消息
     * @param message 消息体
     * @param topic 主题
     * @param tag 标签
     */
    public static <T> void send(T message, String topic, String tag) {
        mqProducer.send(message, topic, tag);
    }
}
