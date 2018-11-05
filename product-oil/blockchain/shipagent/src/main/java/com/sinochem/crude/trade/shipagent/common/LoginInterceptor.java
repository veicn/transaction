package com.sinochem.crude.trade.shipagent.common;

import com.sinochem.crude.trade.shipagent.domain.TShipagentUser;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    HttpFeignClient httpFeignClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//保存本地信息
		ThreadContextHolder.setHttpRequest(request);
		ThreadContextHolder.setHttpResponse(response);
        //
//        Map<String, String> user = new HashMap();
//        user.put("_epId", "100088891");
//        user.put("_mid", "100088891");
//        request.getSession().setAttribute("_user", user);

    	HttpSession session=request.getSession();
//        Map user = (HashMap)session.getAttribute("_user");
        if(session.getAttribute("_user")!=null){
            return true;
        }else {
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
            /*response.setHeader("REDIRECT", "REDIRECT");
            response.setHeader("CONTENTPATH", basePath+"/login.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            System.out.print("################basePath:"+basePath);*/
            //response.sendRedirect(basePath + "/login.html");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
