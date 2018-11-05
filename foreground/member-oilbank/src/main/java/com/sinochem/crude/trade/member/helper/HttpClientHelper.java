package com.sinochem.crude.trade.member.helper;

import com.alibaba.fastjson.JSON;
import com.sinochem.crude.trade.member.util.UdbArgsUtils;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgp on 2018/7/25.
 */
@Component
public class HttpClientHelper implements IHttpClient {


    @Value("${UDB.url}")
    private String UDB_BASE_URL;
    @Value("${UDB.privateKey}")
    private String PRIVATE_KEY;

    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

    @Autowired
    private HttpConnectionManager httpConnectionManager;


    @Override
    public <T> String httpPost(T t, String url) {
        StringEntity se = null;
        try {
            se = new StringEntity(JSON.toJSONString(t), Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<NameValuePair> pairs = UdbArgsUtils.resolveToAddSign(t, PRIVATE_KEY);
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            logger.error("参数转JSON错误", ioe);
        }
        HttpPost httppost = new HttpPost(UDB_BASE_URL + url + "?" + str);
        httppost.setEntity(se);
        return getResult(httppost);

    }

    @Override
    public <T> String httpGet(T t, String url) {
        List<NameValuePair> pairs = UdbArgsUtils.resolveToNameValuePairList(t,PRIVATE_KEY);
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            logger.error("参数转JSON错误", ioe);
        }
        HttpGet httpGet = new HttpGet(UDB_BASE_URL + url + "?" + str);
        return getResult(httpGet);
    }

    @Override
    public <T> String httpDelete(T t, String url) {
        List<NameValuePair> pairs = UdbArgsUtils.resolveToNameValuePairList(t,PRIVATE_KEY);
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            logger.error("参数转JSON错误", ioe);
        }
        HttpDelete httpDelete = new HttpDelete(UDB_BASE_URL + url + "?" + str);
        return getResult(httpDelete);
    }

    @Override
    public <T> String httpPut(T t, String url) {
        StringEntity se = null;
        try {
            System.out.println(JSON.toJSONString(t));
            se = new StringEntity(JSON.toJSONString(t), Charset.forName("UTF-8"));


        } catch (Exception e) {
            logger.error("参数转JSON错误", e);
        }
        List<NameValuePair> pairs = UdbArgsUtils.resolveToAddSign(t, PRIVATE_KEY);
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        HttpPut httpput = new HttpPut(UDB_BASE_URL + url + "?" + str);
        httpput.setEntity(se);
        return getResult(httpput);
    }

    @Override
    public <T> String httpPatch(T t, String url) {
        HttpClient client = httpConnectionManager.getHttpClient();
        StringEntity se = null;
        try {
            se = new StringEntity(JSON.toJSONString(t), Charset.forName("UTF-8"));
        } catch (Exception e) {
            logger.error("参数转JSON错误", e);
        }
        List<NameValuePair> pairs = UdbArgsUtils.resolveToAddSign(t, PRIVATE_KEY);
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        HttpPatch httpPatch = new HttpPatch(UDB_BASE_URL + url + "?" + str);
        httpPatch.setEntity(se);
        return getResult(httpPatch);
    }


    private String getResult(HttpRequestBase httpEntity){
        HttpClient client = httpConnectionManager.getHttpClient();
        try {
            httpEntity.addHeader(HTTP.CONTENT_TYPE,"application/json;charset=utf-8");
            httpEntity.addHeader("Accept","application/json");
            HttpResponse response = client.execute(httpEntity);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (IOException ioe) {
            logger.error("获取udb接口数据异常", ioe);
        }
        return null;
    }

    @Override
    public  String httpGet(String key,String value, String url) {
        List<NameValuePair> pairs =new ArrayList<>();
        pairs.add(new BasicNameValuePair(key,value));
        HashMap<String,Object> map = new HashMap<>();
        map.put(key,value);
        pairs.add(new BasicNameValuePair("sign",UdbArgsUtils.createSign(map,PRIVATE_KEY)));
        String str = null;
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        } catch (IOException ioe) {
            logger.error("参数转JSON错误", ioe);
        }
        HttpGet httpGet = new HttpGet(UDB_BASE_URL + url + "?" + str);
        return getResult(httpGet);
    }



}
