package com.sinochem.crude.trade.transport.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.transport.model.MemberInfoVO;
import com.sinochem.crude.trade.transport.service.MemberInfoService;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;

/**
 * http://www.sinochem.com/sys/member/queryMemberInfo.json?memberId=100008396
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService {
    private static final Logger logger = LoggerFactory.getLogger(MemberInfoServiceImpl.class);
    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Autowired
    private URLBroker systemServer;

    @Override
    public MemberInfoVO memberInfo(Long memberId) {
        MemberInfoVO vo = new MemberInfoVO();
        vo.setMemberId(memberId);
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();
        String requestUrl = systemServer.get("member/queryMemberInfo.json?memberId=" + memberId).toString();
        HttpGet httpGet = new HttpGet(requestUrl);
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
            JSONObject responseJson = JSONObject.parseObject(responseString.toString());
            logger.info("根据memberId获取手机邮箱：" + responseString);
            JSONObject dataJsonArray = responseJson.getJSONObject("datas");
            if(dataJsonArray != null){
                vo.setEmail(dataJsonArray.getString("email"));
                vo.setMobile(dataJsonArray.getString("mobile"));
                vo.setUsername(dataJsonArray.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return vo;
    }
}
