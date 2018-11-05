package com.sinochem.crude.trade.shipagent.helper;

import com.google.gson.Gson;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: fengzk
 * @CreateDate: 2018/8/21 15:22
 * @Version: [v1.0]
 */
public class HttpHelper {
    //    private final String getheadervalue = "text/plain";
    static Gson gson = new Gson();
    static Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    /**
     * 泛型get请求公共服务
     *
     * @param map url参数
     * @return 返回实体类
     */
    public static <T> T GetService(String argurl, Map<String, String> map, Class<T> clazz) {
        String result = GetServiceMap(argurl, map);
        if (!StringHelper.isNullOrEmptyString(result)) {

            return gson.fromJson(result, clazz);
        } else
            return null;
    }

    /**
     * 泛型get请求公共服务 返回string
     *
     * @param map
     * @return
     */
    public static String GetServiceMap(String argurl, Map<String, String> map) {
        String result = "";
        try {
            //请求地址
            String url = argurl;
            if (map != null && map.size() > 0) {
                //入参
                int i = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (i > 0) {
                        url += "&&";
                    } else {
                        url += "?";
                    }
                    i++;
                    url += entry.getKey() + "=" + entry.getValue();
                }
            }
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-Type", "text/plain");
            RestTemplate template = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
            result = response.getBody();
            logger.info("请求get：" + url + "||返回=" + result);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * boby传递参数的post接口调用
     *
     * @param argurl  服务url
     * @param urlmap  url参数
     * @param bobymap boby参数
     * @return
     */

    public static String PostService(String argurl, Map<String, String> urlmap, Map<String, String> bobymap) {
        String result = "";
        try {
            //请求地址
            String url = argurl;
            //入参
            int i = 0;
            if (urlmap != null) {
                for (Map.Entry<String, String> entry : urlmap.entrySet()) {
                    if (i > 0) {
                        url += "&&";
                    } else {
                        url += "?";
                    }
                    i++;
                    url += entry.getKey() + "=" + entry.getValue();
                }
            }
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            String jsonstr = JsonUtilsHelper.hashMap2Json(bobymap);
//        logger.info("请求：" + url + "[boby:]" + jsonstr);
            HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.postForObject(url, formEntity, String.class);
            logger.info("请求get：" + url + "[boby:]" + jsonstr + "||返回=" + result);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * boby传递参数的post接口调用
     *
     * @param argurl  服务url
     * @param urlmap  url参数
     * @param bobymap boby参数
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> T PostService(String argurl, Map<String, String> urlmap, Map<String, String> bobymap, Class<T> clazz) {

        String result = PostService(argurl, urlmap, bobymap);
        if (!StringHelper.isNullOrEmptyString(result)) {
            return gson.fromJson(result, clazz);
        } else
            return null;
    }
}
