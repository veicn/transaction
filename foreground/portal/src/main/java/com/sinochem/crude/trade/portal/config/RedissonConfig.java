package com.sinochem.crude.trade.portal.config;

import com.google.common.base.Strings;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.MsgPackJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private String redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.db.index}")
    private Integer dnIndex;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig serversConfig = config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort).setDatabase(dnIndex);
        if (!Strings.isNullOrEmpty(redisPassword)) {
            serversConfig.setPassword(redisPassword);
        }
        config.setCodec(new MsgPackJacksonCodec());
        return Redisson.create(config);
    }
}
