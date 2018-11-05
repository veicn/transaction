package com.sinochem.crude.trade.member.util;


/**
 * Created by wgp on 2018/8/22.
 */
public class HttpUtil {

    private final static String PREFIX  = "http";
    /**
     * 检测是否含有http路径,如果含有，返回true
     * @param str
     * @return
     */
    public boolean checkHttp(String str) {
        if(str == null || str.trim().length() == 0){
            return false;
        }
        str = str.toLowerCase();
        if(str.contains("http") && str.indexOf("http") == 0){
            return true;
        }
        return false;
    }



}
