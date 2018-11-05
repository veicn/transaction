package com.sinochem.crude.trade.member.util;

import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {
    /**
     * form方式请求
     * @param httpClient
     * @param url
     * @param params
     * @return
     * @throws BizException
     */
    public static String post(HttpClient httpClient, String url, List<NameValuePair> params) throws BizException {
        return doPost(httpClient, url, params, null, null);
    }

    /**
     * form方式请求 with header
     * @param httpClient
     * @param url
     * @param params
     * @return
     * @throws BizException
     */
    public static String post(HttpClient httpClient, String url, List<NameValuePair> params, Map<String, String> headerMap) throws BizException {
        return doPost(httpClient, url, params, null, headerMap);
    }

    /**
     * raw请求方式
     * @param httpClient
     * @param url
     * @param body
     * @return
     * @throws BizException
     */
    public static String post(HttpClient httpClient, String url, String body) throws BizException {
        return doPost(httpClient, url, null, body, null);
    }

    /**
     * raw请求方式 with header
     * @param httpClient
     * @param url
     * @param body
     * @return
     * @throws BizException
     */
    public static String post(HttpClient httpClient, String url, String body, Map<String, String> headerMap) throws BizException {
        return doPost(httpClient, url, null, body, headerMap);
    }


    private static String doPost(HttpClient httpClient, String url, List<NameValuePair> params, String body, Map<String, String> headerMap) throws BizException {
        String result = "200";
        try {
            //POST的URL
            HttpPost httppost = new HttpPost(url);
            //建立HttpPost对象
            //添加参数
            if (params != null)
                httppost.setEntity(new UrlEncodedFormEntity(params, CharEncoding.UTF_8));
            //添加raw参数
            if (StringUtils.isNotBlank(body))
                httppost.setEntity(new StringEntity(body,CharEncoding.UTF_8));
            //设置请求头
            if (headerMap != null && headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httppost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
                return result;
            } else {
                throw new BizException("http请求异常:" + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BizException("字符编码不支持！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("数据读写失败！");
        }
    }
}
