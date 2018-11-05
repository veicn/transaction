package com.sinochem.crude.trade.member.util;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.b2b.hyperloop.common.security.sign.SecurityUtils;
import com.sinochem.b2b.hyperloop.common.security.sign.WebRequestSignUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgp on 2018/7/26.
 */
public class UdbArgsUtils {

    /**
     * 把参数map信息和签名添加到请求信息里。
     *
     * @param t
     * @param secret
     * @return
     */
    public static <T> List<NameValuePair> resolveToNameValuePairList(T t, String secret) {
        if (t == null || secret == null) {
            return null;
        }
        HashMap<String, Object> objMap = resolveObjectToMap(t);
        List<NameValuePair> pairs = new ArrayList<>();
        try {
            for (Map.Entry entry : objMap.entrySet()) {
                if(entry.getValue() != null){
                    NameValuePair pair = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
                    pairs.add(pair);
                }

            }
            String sign = createSign(objMap, secret);
            pairs.add(new BasicNameValuePair("sign", sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pairs;
    }

    /**
     * 仅添加签名信息到请求参数里
     *
     * @param t
     * @param secret
     * @param <T>
     */
    public static <T> List<NameValuePair> resolveToAddSign(T t, String secret) {
        if (t == null || secret == null) {
            return null;
        }
        List<NameValuePair> pairs = new ArrayList<>();
        HashMap<String, Object> objMap = resolveObjectToMap(t);
        String sign = createSign(objMap, secret);
        pairs.add(new BasicNameValuePair("sign", sign));
        return pairs;
    }

    /**
     * 把udb的参数信息转为map
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> HashMap<String, Object> resolveObjectToMap(T t) {
        if (t == null) {
            return null;
        }
        HashMap<String, Object> objMap = new HashMap<>(32);
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.get(t)!=null && !StringUtil.isBlank(field.get(t).toString())){
                    objMap.put(field.getName(), field.get(t));
                }
            }
            return objMap;
        } catch (IllegalAccessException acce) {
            acce.printStackTrace();
        }
        return null;
    }

    /**
     * 生成签名信息
     *
     * @param secret
     * @param paramsMap
     * @return
     */
    public static String createSign(HashMap<String, Object> paramsMap, String secret) {
        String signValue = WebRequestSignUtils.createSign(paramsMap, SecurityUtils.MD5, secret);
        return signValue;
    }
}
