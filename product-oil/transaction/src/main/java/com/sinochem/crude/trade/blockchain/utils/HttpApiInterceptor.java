package com.sinochem.crude.trade.blockchain.utils;


import com.eyeieye.melody.web.interceptors.AbstractHandlerInterceptor;
import com.sinochem.crude.trade.blockchain.enums.HttpKeyEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class HttpApiInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(HttpApiInterceptor.class);


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler) throws Exception {
        String url = request.getRequestURI();
        String parter = request.getHeader("parter");
        String apikey = request.getHeader("apikey");

        if(HttpKeyEnums.getByCode(parter).getName().equals(apikey)){
            return true;
        }
        else {
            return false;
        }

    }


     @Override
     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

     }

     @Override
     public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

     }


}