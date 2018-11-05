package com.sinochem.crude.trade.listed.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.listed.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.listed.service.MemberInfoService;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.http.ConnectionManager;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
