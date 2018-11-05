package com.sinochem.crude.trade.wechat.helper;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpHelper {
    Gson gson=new Gson();

    Logger logger = LoggerFactory.getLogger(HttpHelper.class);
   // @Value("${msserviceurl}")
   // private String msbaseurl="https://api.weixin.qq.com/cgi-bin/user/get?";

    /**
     * 泛型get请求微信公共服务
     * @param map
     * @return
     */
   public <T> T GetWXService(String argurl,Map<String, String> map, Class<T> clazz)
    {
        //请求地址
        String url = argurl;
        //入参
        int i=0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
          if(i>0)
          {
              url+="&&";
          }
          else
          {url+="?";}
          i++;
            url+=entry.getKey()+"="+entry.getValue();
        }
        logger.info("请求get："+url);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/plain");
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();
      Gson gson=new Gson();
      //  Map map1= gson.fromJson(sttr, HashMap.class);
        logger.info("返回="+sttr);
        return gson.fromJson(sttr, clazz);

    }

    /**
     * 泛型get请求微信公共服务
     * @param map
     * @return
     */
    public String GetWXServiceMap(String argurl,Map<String, String> map)
    {
        //请求地址
        String url = argurl;
        //入参
        int i=0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(i>0)
            {
                url+="&&";
            }
            else
            {url+="?";}
            i++;
            url+=entry.getKey()+"="+entry.getValue();
        }
        logger.info("请求get："+url);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/plain");
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();
     //   Map map1= gson.fromJson(sttr, HashMap.class);
        logger.info("返回="+sttr);
        return sttr;

    }
    /**
     * boby传递参数的post接口调用
     * @param argurl
     * @param urlmap url参数
     * @return
     */
    public  <T> T PostService(String argurl,Map<String, String> urlmap,Map<String, String> bobymap, Class<T> clazz)
    {
        //请求地址
        String url = argurl;
        //入参
        int i=0;
        if(urlmap!=null) {
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
        String jsonstr= JsonUtilsHelper.hashMap2Json(bobymap);
        logger.info("请求："+url+"[boby:]"+jsonstr);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
        RestTemplate restTemplate = new RestTemplate();
        T aa=  restTemplate.postForObject(url,formEntity, clazz);
        logger.info("返回："+gson.toJson(aa));
        return aa;
    }


//    static ParameterizedType type(final Class raw, final Type... args) {
//        return new ParameterizedType() {
//            public Type getRawType() {
//                return raw;
//            }
//            public Type[] getActualTypeArguments() {
//                return args;
//            }
//
//            public Type getOwnerType() {
//                return null;
//            }
//        };
//    }


//    public ResultData GetMSService(String argurl,String json)
//    {
//        //请求地址
//        String url = msbaseurl+argurl;
//        url+="?data="+json;
//        logger.info("马上请求："+url);
//        RestTemplate restTemplate = new RestTemplate();
//        MSPayData aa=  restTemplate.getForObject(url, MSPayData.class);
//        logger.info("马上返回："+gson.toJson(aa));
//        return ResultUtil.GetResultData(aa);
//
//    }
//
//    /**
//     * get请求服务
//     * @param map
//     * @return
//     */
//    public ResultData GetService(Map<String, String> map)
//    {
//        //请求地址
//        String url = baseurl+"sendsms"+wxtoken;
//        //入参
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//            url+="&&"+entry.getKey()+"="+entry.getValue();
//        }
//        logger.info("请求："+url);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResultData aa=  restTemplate.getForObject(url, ResultData.class);
//        logger.info("返回："+gson.toJson(aa));
//        return aa;
//      //  ResponseBean responseBean = restTemplate.postForObject(url, requestBean, ResponseBean.class);
//    }
//
//    /**
//     * boby传递参数的接口调用
//     * @param argurl
//     * @param map
//     * @return
//     */
//    public  ResultData PostService(String argurl,Map<String, String> map)
//    {
//        //请求地址
//        String url = baseurl+argurl+wxtoken;
//
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        String jsonstr= JsonUtils.hashMap2Json(map);
//        logger.info("求："+url+"[boby:]"+jsonstr);
//        HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
//        logger.info("返回："+gson.toJson(aa));
//        return aa;
//    }
//
//    public  ResultData PostService(String argurl,String json)
//    {
//        //请求地址
//        String url = baseurl+argurl+wxtoken;
//        logger.info("请求："+url);
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//       // String jsonstr= JsonUtils.hashMap2Json(map);
//        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
//        logger.info("返回："+gson.toJson(aa));
//        return aa;
//    }
//    /*
//    支持url参数+boby参数
//     */
//    public  ResultData PostServiceAll(String argurl,String json,String argcs)
//    {
//        //请求地址
//        String url = baseurl+argurl+wxtoken+argcs;
//        logger.info("请求："+url);
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        // String jsonstr= JsonUtils.hashMap2Json(map);
//        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
//        logger.info("返回："+gson.toJson(aa));
//        return aa;
//    }
}
