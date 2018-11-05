package com.sinochem.crude.trade.common.mq.impl;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisMQConfig {

    @Bean
    RedisMQProducer redisMQProducer(RedissonClient redissonClient) {
        return new RedisMQProducer(redissonClient);
    }

    @Bean
    RedisMQConsumer redisMQConsumer(RedissonClient redissonClient) {
        return new RedisMQConsumer(redissonClient);
    }
}
