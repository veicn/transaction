package com.sinochem.crude.trade.shipagent.helper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class JsonUtilsHelper {

    /**
     * 将java对象转化为json
     *
     * @param object 要转化的Java对象
     * @throws IOException
     */
    public static String object2Json(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(sw);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.writeValue(jsonGenerator, object);
        jsonGenerator.close();
        return sw.toString();
    }

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


}
