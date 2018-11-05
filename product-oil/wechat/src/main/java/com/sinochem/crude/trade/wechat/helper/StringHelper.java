package com.sinochem.crude.trade.wechat.helper;

import java.util.Random;

public class StringHelper {
    public static  boolean strisnull(String str)
    {
        if(str==null||str.trim().equals("")||str.trim().isEmpty())
        {
            return true;

        }
        else
            return  false;
    }
    public static String randomString(int length) {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

}
