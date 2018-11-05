package com.sinochem.crude.trade.member.util;

import org.springframework.ui.ModelMap;

public class MessageUtils {

    public static String info(ModelMap modelMap, String title, String message, String url, String nextUrl,String opBtn) {
        modelMap.put("message", message);
        modelMap.put("title", title);
        modelMap.put("nextUrl", nextUrl);
        modelMap.put("nextBtn",opBtn);
        return url;
    }

    public static String success(ModelMap modelMap, String title, String message, String nextUrl,String opBtn) {
        return info(modelMap, title, message, "/common/success", nextUrl,opBtn);
    }


}