package com.sinochem.crude.trade.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.sinochem.crude.trade.info.util.Uploader;

@Configuration
public class InfoInitConfig {
	@Value("${aliyun.oss.group.public}")
	public String bucket;

    @PostConstruct
    void init() {
    	Uploader.bucket = bucket;
//        DataFeedUtils.registerHandler(new RedisHandler());
//        DataFeedUtils.start();
    }

}
