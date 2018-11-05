package com.sinochem.crude.trade.shipagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class BootApplication {

	public static void main(String[] args)throws Exception {
		SpringApplication.run(BootApplication.class, args);
	}
}
