package com.sinochem.crude.trade.broker.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
@ConfigurationProperties
public class HttpFeignInterceptor implements RequestInterceptor {
    private String parter;
    private String apikey;
    public String getParter() {
        return parter;
    }
    public void setParter(String parter) {
        this.parter = parter;
    }
    public String getApikey() { return apikey; }
    public void setApikey(String apikey) { this.apikey = apikey; }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("parter", parter);
        requestTemplate.header("apikey", apikey);
    }
}