/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import redis.clients.jedis.JedisPool;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.SearcherClient;
import com.sinochem.crude.trade.common.aliyun.opensearch.OpenSearchConfig;
import com.sinochem.crude.trade.common.aliyun.opensearch.OpenSearchConfig.Factory;
import com.sinochem.crude.trade.common.aliyun.oss.FileManager;
import com.sinochem.crude.trade.common.aliyun.oss.OssConfig;
import com.sinochem.crude.trade.common.mq.MQConsumer;
import com.sinochem.crude.trade.common.mq.MQProducer;
import com.sinochem.crude.trade.common.mq.impl.RedisMQConfig;
import com.sinochem.crude.trade.common.values.RedissonConfig;
import com.sinochem.crude.trade.common.values.impl.CommonValueSetManager;

/**
 * Created on 2017/11/17. 帮助初始化工具类
 * @author Down
 *
 */
@Configuration
@Import({RedissonConfig.class, OssConfig.class, RedisMQConfig.class, OpenSearchConfig.class})
public class UtilsInit {
	private static final Log log = LogFactory.getLog(UtilsInit.class);
	
	@Value("${redis.keyPrefix}")
	private String keyPrefix;

	@Autowired
	public void setSequenceManager(SequenceManager sequenceManager) {
        SequenceUtils.init(sequenceManager);
        log.info("Init  SequenceUtils's sequenceManager...");
	}

	@Autowired
	public void setJedisPool(JedisPool jedisPool, RedissonClient redissonClient) {
        JedisUtils.init(jedisPool, redissonClient);
        JedisUtils.KEY_PREFIX = keyPrefix;
        log.info("Init  JedisUtils's jedisPool...");
	}

    @Autowired
    public void setFileManager(FileManager fileManager) {
        RemoteFileUtils.init(fileManager);
    }

    @Autowired
    public void setMQConfig(MQProducer mqProducer, MQConsumer mqConsumer) {
	    MQUtils.init(mqProducer, mqConsumer);
    }

    @Autowired
    public void setOpenSearchConfig(Factory<DocumentClient> documentClient, Factory<SearcherClient> searcherClient) {
        log.info("Initiating OpenSearchConfig...");
        OpenSearchUtils.init(documentClient, searcherClient);
    }

    @PostConstruct
	void init() {
		if (!ValueSetUtils.isInited()) {
            ValueSetUtils.init(new CommonValueSetManager());
            log.info("Init ValueSetUtils with CommonValueSetManager...");
        }
	}
}
