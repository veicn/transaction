package com.sinochem.crude.trade.datafeed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.sinochem.crude.trade.info.service.FeedClassifyService;

@Configuration
public class DataFeedConfig {
	@Value("${dataFeed.tcp.host:211.157.2.89}")
	private String dataFeedHost = "211.157.2.89";

	@Value("${dataFeed.tcp.port:2920}")
	private Integer dataFeedPort = 2920;
	
	@Autowired
	private RedisHandler redisHandler;

    @PostConstruct
    void init() {
    	DataFeedInit.init(dataFeedHost, dataFeedPort);
    	
        DataFeedInit.registerHandler(redisHandler);
        DataFeedInit.start();
    }

}
