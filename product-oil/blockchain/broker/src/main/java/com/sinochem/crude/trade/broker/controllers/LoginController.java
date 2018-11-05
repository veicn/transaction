package com.sinochem.crude.trade.broker.controllers;

import com.sinochem.crude.trade.broker.common.ThreadContextHolder;
import com.sinochem.crude.trade.broker.domain.TDataPartner;
import com.sinochem.crude.trade.broker.feign.HttpFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @Autowired
    private HttpFeignClient httpFeignClient;

    @Value("${userDomin}")
    private String userDomin;

    @RequestMapping("/logincall")
    public void logincall(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            String _epId = request.getParameter("_epId");
            String _mid = request.getParameter("_mid");
           // String webDomain = httpFeignClient.getWebDemainByUserId(_epId);
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();

            //if(null!=webDomain&&webDomain.equals(baseUrl)){
            Map<String, String> user = new HashMap();
            user.put("_epId", _epId);
            user.put("_mid", _mid);
            session.setAttribute("_user", user);
            ThreadContextHolder.setHttpRequest(request);
            ThreadContextHolder.setHttpResponse(response);
            //}

            response.sendRedirect("index.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("/getLoginUser")
    public int getLoginUser(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        Map _user=(HashMap) session.getAttribute("_user");
        int status=0;
        if(null!=_user&&null!=_user.get("_epId"))
            status=1;
        return status;
    }

    @RequestMapping("/getLoginOut")
    public void getLoginOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            if (session != null) {
                session.removeAttribute("_user");
                session.invalidate();
            }
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
            response.sendRedirect(userDomin+"/secure/logout.htm?returnUrl="+baseUrl+"/logincall");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/getLoginDataByUrl")
    public TDataPartner getLoginDataByUrl(HttpServletRequest request, HttpServletResponse response){
        String baseUrl = request.getScheme() + "://" + request.getServerName() +request.getContextPath();
        TDataPartner tDataPartner = httpFeignClient.getLoginDataByUrl(baseUrl);

        return tDataPartner;
    }
}