package com.sinochem.crude.trade.member.helper;

import com.sinochem.crude.trade.member.util.UdbResult;

/**
 * Created by wgp on 2018/7/25.
 */
public interface IHttpClient {
    <T> String httpPost(T t, String url);

    <T> String httpGet(T t, String url);

    <T> String httpDelete(T t, String url);

    <T> String httpPut(T t, String url);

    <T> String httpPatch(T t, String url);

    String httpGet(String key, String value, String url);
}


