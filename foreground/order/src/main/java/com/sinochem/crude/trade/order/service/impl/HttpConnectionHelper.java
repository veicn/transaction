package com.sinochem.crude.trade.order.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * http请求的帮助类
 * @author Yichen Zhao
 * date: 20180319
 */
@Component
public class HttpConnectionHelper {

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    /**
     * post请求
     */
    public JSONObject httpPost(JSONObject paramJson, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(paramJson == null ? "" : paramJson.toJSONString(), CharsetUtils.get("UTF-8")));

        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);

            return JSONObject.parseObject(responseString);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }

    /**
     * get请求，参数拼接成url的字符串
     */
    public JSONObject httpGet(String paramString, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();

        if (!StringUtil.isEmpty(paramString)) {
            if (paramString.startsWith("?")) {
                url += paramString;
            } else {
                url = url + "?" + paramString;
            }
        }

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type","application/json; charset=utf-8");

        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);

            return JSONObject.parseObject(responseString);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }
}
