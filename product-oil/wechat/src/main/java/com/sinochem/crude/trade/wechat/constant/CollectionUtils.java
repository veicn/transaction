package com.sinochem.crude.trade.wechat.constant;

import java.util.List;

/**
 * author sunjianbo
 * date 2018/3/17 11:55
 */
public class CollectionUtils {

    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
}
