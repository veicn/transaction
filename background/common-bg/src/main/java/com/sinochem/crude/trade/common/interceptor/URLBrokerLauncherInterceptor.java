package com.sinochem.crude.trade.common.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eyeieye.melody.web.interceptors.AbstractHandlerInterceptor;
import com.eyeieye.melody.web.url.URLBroker;

public class URLBrokerLauncherInterceptor extends AbstractHandlerInterceptor {

	private static String myTag = "_"
			+ URLBrokerLauncherInterceptor.class.getName();

	private Map<String, URLBroker> brokers;

	public void setBrokers(Map<String, URLBroker> brokers) {
		this.brokers = brokers;
	}

	private static String tagValue = "1";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object tag = request.getAttribute(myTag);
		if (tag == null && brokers != null) {
			for (Entry<String, URLBroker> entry : brokers.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			request.setAttribute(myTag, tagValue);
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
