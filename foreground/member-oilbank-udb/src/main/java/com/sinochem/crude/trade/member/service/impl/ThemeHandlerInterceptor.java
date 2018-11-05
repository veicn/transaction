package com.sinochem.crude.trade.member.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.eyeieye.melody.web.interceptors.AbstractHandlerInterceptor;

@Component
public class ThemeHandlerInterceptor extends AbstractHandlerInterceptor {

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
				if(request != null && request.getCookies() != null && request.getCookies().length > 0){
					for (Cookie cookie : request.getCookies()) {
						if(modelAndView != null && modelAndView.getViewName()!=null){
							if (cookie.getName().equals("_theme")&& modelAndView.getViewName().indexOf(":") == -1) {
								if (cookie.getValue().equals("product")) {
									if(!modelAndView.getViewName().contains("product")){
										modelAndView.setViewName("/"+cookie.getValue() + "/"+ modelAndView.getViewName());
									}
								}
							}
						}
					}
		  	}
			}

}
