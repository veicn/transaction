package com.sinochem.crude.trade.inspector.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor getSessionInterceptor() {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        return loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/logincall","/index","/index.html");
        super.addInterceptors(registry);
    }
}
