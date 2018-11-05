package com.sinochem.crude.trade.orderexecute.commons.utils;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    private static final String SALT = "f8e1d64347aa4e1321c99b03d359b536ebbb32fee2aeca92f7c898bd7";

    private DigestUtil(){
    	throw new IllegalAccessError("Utility class");
    }
    private static String getMD5(String str) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密失败", e);
        }
        return str;
    }

    private static String md5WithSalt(String string, String salt) {
        return getMD5(getMD5(string) + (StringUtils.isEmpty(salt) ? SALT : salt));
    }

    public static String digestWithMD5(Object[] params, String salt) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object param : params)
            stringBuilder.append(param.toString());
        return md5WithSalt(stringBuilder.toString(), salt);
    }
}
