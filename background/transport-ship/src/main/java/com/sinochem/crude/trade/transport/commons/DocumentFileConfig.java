package com.sinochem.crude.trade.transport.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;

@Configuration
public class DocumentFileConfig {
	@Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	
	@Autowired
	private OSSClient ossClient;
	
	@Bean
	public DocumentFileManage createDocumentFileManage(){
		return new DocumentFileManage(ossClient, bucket);
	}

}
