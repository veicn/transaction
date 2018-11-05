/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HuangWj on 2016/10/29.
 * 网络相关工具
 */
public class NetUtils {

    private NetUtils() {}

    /**
     * 获取客户端IP地址
     * @param request 客户端请求对象
     * @return 客户端IP地址
     */
    public static String getRemoteIp(HttpServletRequest request) {

        if (null == request) {
            return "";
        }

        // x-forwarded-for存在值则说明有经过反向代理
        String ip = request.getHeader("x-forwarded-for");
        if(Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            // 如果反向代理关闭了x-forwarded-for取值选项
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            // 同上
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(Strings.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            // 没有经过反向代理则可以直接取出请求来源IP
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取user-agent值
     * @param request 请求对象
     * @return user-agent值
     */
    public static String getUserAgent(HttpServletRequest request) {

        if (null == request) {
            return "";
        }

        String userAgent = request.getHeader("user-agent");
        return Strings.isNullOrEmpty(userAgent) ? "" : userAgent;
    }

    /**
     * 获取平台信息
     * @param request 请求对象
     * @return 平台代码
     */
    public static String getPlatform(HttpServletRequest request) {

        if (null == request) {
            return "";
        }

        String platform = request.getHeader("platform");
        return Strings.isNullOrEmpty(platform) ? "web" : platform;
    }
}
