package com.sinochem.crude.trade.blockchain.utils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fengzk
 * @CreateDate: 2018/8/21 15:22
 * @Version: [v1.0]
 */
public class HttpUtil {
    //    private final String getheadervalue = "text/plain";
    static Gson gson = new Gson();
    static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

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
            MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED.toString());
            headers.setContentType(type);
            //headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            List<String> cookies = new ArrayList<>();
            cookies.add("_u=" + "eNo1jcEOwUAQhiNRtBpP4bwRr+DCgRMSHGrbjnaiu9vMTBM8iMQTeENni7rMTL783/zzIGkYaDYZZ84oRuuyEoxCUek0VQZMCqQ+CbXx4z74kUUe1sv26lptIKw0y9qdwQbkKuAeYVEKH3fBq/NcRf+/XKy2/QMLoS0eMVgBSow3gaLTSSdCOgcKs1KTRx6mzbWF0TdcEzIMf57ODdrY1YIGb1rQ2Zi1r26F0XclcIGsEUd1cKicLfZvZs9Z3w==");
            headers.put("Cookie",cookies);
            String jsonstr = hashMap2Json(bobymap);
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
/*
    public static <T> T PostService(String argurl, Map<String, String> urlmap, Map<String, String> bobymap, Class<T> clazz) {

        String result = PostService(argurl, urlmap, bobymap);
        if (!StringHelper.isNullOrEmptyString(result)) {
            return gson.fromJson(result, clazz);
        } else
            return null;
    }*/




    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname"
     * :"ddd","testretries":"fffffffff"}
     */
    public static String hashMap2Json(Map<String, String> map) {
        StringBuffer resultString = new StringBuffer();
        resultString.append("{");
        int i = 0;
        for (String key : map.keySet()) {
            if (map.get(key).length() > 0 && !map.get(key).substring(0, 1).equals("{")) {
                resultString.append("\"" + key + "\":\"" + map.get(key) + "\"");
            } else {
                resultString.append("\"" + key + "\":" + map.get(key));
            }

            i++;
            if (i < map.keySet().size()) {
                resultString.append(",");
            }
        }
        resultString.append("}");
        return resultString.toString();
    }



    public static String sapPostService(String purchaseContractNo) {

        String result = "";
        try {
            //请求地址
            String url = "https://exec.mycrudeoil.com/sap/getQuerySAPStatement.json";
            //入参
            int i = 0;
            url+="?BSTKD="+purchaseContractNo;
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED.toString());
            headers.setContentType(type);
            List<String> cookies = new ArrayList<>();
            cookies.add("_u=" + "eNo1jcEOwUAQhiNRtBpP4bwRr+DCgRMSHGrbjnaiu9vMTBM8iMQTeENni7rMTL783/zzIGkYaDYZZ84oRuuyEoxCUek0VQZMCqQ+CbXx4z74kUUe1sv26lptIKw0y9qdwQbkKuAeYVEKH3fBq/NcRf+/XKy2/QMLoS0eMVgBSow3gaLTSSdCOgcKs1KTRx6mzbWF0TdcEzIMf57ODdrY1YIGb1rQ2Zi1r26F0XclcIGsEUd1cKicLfZvZs9Z3w==");
            headers.put("Cookie",cookies);
            HttpEntity<String> formEntity = new HttpEntity<String>(null, headers);
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.postForObject(url, formEntity, String.class);
            logger.info("请求get：" + url + "||返回=" + result);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }


    public static void main(String[] args){
        /*String argurl="https://exec.mycrudeoil.com/sap/getQuerySAPStatement.json";
        Map<String, String> bobymap=new HashMap();
        bobymap.put("BSTKD","18SG11XA2333GO038");*/

        String purchaseContractNo = "18SG11XA2333GO038";
        String res = sapPostService(purchaseContractNo);

        System.out.print("test..."+res);
    }


}
