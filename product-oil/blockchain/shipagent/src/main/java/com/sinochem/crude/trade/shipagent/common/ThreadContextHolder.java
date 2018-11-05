package com.sinochem.crude.trade.shipagent.common;

import com.sinochem.crude.trade.shipagent.domain.TShipagentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author songhaiqiang
 */
public class ThreadContextHolder  {

	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();

	public static TShipagentUser getCurrentUser()throws Exception{
		TShipagentUser user = new TShipagentUser();
		Map<String,String> userMap = (Map<String, String>) getHttpRequest().getSession().getAttribute("_user");

		user.setEpMemberId(Long.valueOf(userMap.get("_epId")));
		user.setMemberId(Long.valueOf(userMap.get("_mid")));
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
