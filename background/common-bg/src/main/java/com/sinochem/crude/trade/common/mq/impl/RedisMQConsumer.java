package com.sinochem.crude.trade.common.mq.impl;

import com.sinochem.crude.trade.common.mq.Handler;
import com.sinochem.crude.trade.common.mq.MQConsumer;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;

public class RedisMQConsumer implements MQConsumer {

    private RedissonClient redisson;

    public RedisMQConsumer(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public <T> void subscribe(String topic, String tag, final Handler<T> handler) {
        RTopic<T> queue = redisson.getTopic(topic + "." + tag);
        queue.addListener(new MessageListener<T>() {
            @Override
            public void onMessage(String s, T t) {
                try {
                    handler.onReceive(t);
                } catch (Throwable e) {
                    handler.onFail(e);
                }
            }
        });
    }

    @Override
    public void unsubscribe(String topic, String tag) {
        RTopic queue = redisson.getTopic(topic + "." + tag);
        queue.removeAllListeners();
    }

}
