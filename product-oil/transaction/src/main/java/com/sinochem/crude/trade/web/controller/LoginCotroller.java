package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.service.PersonService;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sinochem.it.b2b.member.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(UrlMapping.LOGIN)
public class LoginCotroller {

    @Autowired
    private URLBroker transactionServer;

    /**
     * 推出登录
     * @param request
     * @return
     */
    @RequestMapping(UrlMapping.LOG_OUT)
    public ModelAndView logout(HttpServletRequest request){

        ModelAndView mav = new ModelAndView();

        //清楚session用户记录
        request.getSession().setAttribute("_user",null);

        mav.setViewName(UrlMapping.HOME_PAGE_INDEX);
        return mav;
    }



}
