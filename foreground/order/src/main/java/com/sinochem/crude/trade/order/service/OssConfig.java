package com.sinochem.crude.trade.order.service;

import com.aliyun.oss.OSSClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    private static final Log log = LogFactory.getLog(OssConfig.class);

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.show.endpoint}")
    private String showHost;

    @Bean(destroyMethod = "shutdown")
    public OSSClient ossClient() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        log.info("Aliyun oss client setup complete.");
        return ossClient;
    }

   
}
