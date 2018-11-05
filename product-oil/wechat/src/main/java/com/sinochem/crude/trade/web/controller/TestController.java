package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.wechat.constant.AccessTokenUtil;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.sinochem.it.b2b.common.cache.CacheEntry;
import com.sinochem.it.b2b.common.cache.CacheManager;
import com.sinochem.it.b2b.common.cache.CacheManagerImpl;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping("test")
public class TestController {
    @Value("${wechat.appid}")
    public   String appid;
    @Value("${wechat.appsecret}")
    public   String appsecret;
    @Autowired
    URLBroker systemServer;
    @Autowired
    URLBroker memberServer;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    AccessTokenService accessTokenService;
    @RequestMapping("test")
    @WithoutAccess
    public void test(ModelMap modelMap, Locale locale) {
        modelMap.put("message", locale.getDisplayCountry());
    }

    @RequestMapping(value = "restTest.json")
    @ResponseBody
    @WithoutAccess
    public ResultDatas test(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResultDatas resultDatas = new ResultDatas();
        String url=memberServer.get("/login.json").toString();
        url+="；appid:"+appid+"；appsecret："+appsecret;
//        String url = appServer.get("/product_images/" + contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getCode() + "/vertical.jpg").toString();

//       resultDatas.setDatas(request.getLocale().getDisplayCountry()+"20180418");

          resultDatas.setDatas(url);
        return resultDatas;
    }


}


