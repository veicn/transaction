package com.sinochem.crude.trade.broker.common;

import com.sinochem.crude.trade.broker.domain.TShipagentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author songhaiqiang
 */
@Service
public class ThreadContextHolder {
	protected static final Logger logger = LoggerFactory.getLogger(ThreadContextHolder.class);
	
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();



	public TShipagentUser getCurrentUser(){
		TShipagentUser user=null ;
		try {
			Map<String, String> userMap = (Map<String, String>) getHttpRequest().getSession().getAttribute("_user");
			user = new TShipagentUser();
			user.setEpMemberId(Long.valueOf(userMap.get("_epId")));
			user.setMemberId(Long.valueOf(userMap.get("_mid")));
		}
		catch (Exception e){
			logger.error(e.getMessage());
		}
		return user;
	}


	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	
	public static HttpServletRequest getHttpRequest(){
		return  HttpRequestThreadLocalHolder.get();
	}	
	
	public static void setHttpResponse(HttpServletResponse response){
		HttpResponseThreadLocalHolder.set(response);	
	}
	
	public static HttpServletResponse getHttpResponse(){
		
		return HttpResponseThreadLocalHolder.get();
	}

}
