package com.sinochem.crude.trade.common.mq.impl;

import com.sinochem.crude.trade.common.mq.MQProducer;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

public class RedisMQProducer implements MQProducer {

    private RedissonClient redisson;

    public RedisMQProducer(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public <T> void send(T message, String topic, String tag) {
        RTopic<T> queue = redisson.getTopic(topic + "." + tag);
        queue.publish(message);
    }
}
