package com.sinochem.crude.trade.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.google.gson.Gson;
import com.sinochem.crude.trade.member.controller.EnterpriseController;
import com.sinochem.crude.trade.member.service.ThirdLoginService;
import com.sinochem.crude.trade.member.util.HttpRequestUtil;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyuser on 2018/5/25.
 */
@Service
public class ThirdLoginServiceImpl implements ThirdLoginService{

    Logger logger = LoggerFactory.getLogger(ThirdLoginServiceImpl.class);

    @Value("${yichemic.applicationId}")
    private String applicationId;

    @Value("${yichemic.ticket}")
    private String ticket;

    @Value("${yichemic.url}")
    private String url;

    @Autowired
    private URLBroker uploadServerBroker;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Override
    public String post(Object entity, String postMember) throws BizException {
        return postUrl(entity,postMember);
    }

    private String postUrl(Object entity,String path) throws BizException {
        String params = JSONObject.toJSONString(entity);
        logger.info("推送壹化网：" + params);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        String resultStr = HttpRequestUtil.post(httpConnectionManager.getHttpClient(), url + path + "/" + applicationId + "/" + ticket, params, headers);
        Gson gson = new Gson();
        Map resultMap = new HashMap();
        resultMap = gson.fromJson(resultStr, resultMap.getClass());
        return resultMap.get("resultCode").toString();
    }
}
