package com.sinochem.crude.trade.orderexecute.commons.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

  

public class XmlParseUtil {
	
	private static final Log log = LogFactory.getLog(XmlParseUtil.class);
	
	public static void main(String[] args) {  
		XmlParseUtil parse = new XmlParseUtil();  
		parse.parseResponse(""); 
    }  
      
	/**
	 * 单层的xml解析
	 * @param fileName
	 * @return
	 */
	public Map<String, Object> parseResponse(String fileName) {
		Map<String, Object> map = new HashMap<>();
		
        try {
        	SAXReader reader = new SAXReader();
        	Document doc = reader.read(new File(fileName));
            // 获取根节点list
            Element root = doc.getRootElement();
            // 获取list下的所有子节点emp
            List<Element> elements = root.elements();
            //遍历集合取出没个节点的内容信息.
            for (Element element : elements) {
            	map.put(element.getName(), element.getText());
            }
        } catch (Exception e) {  
     	   log.error("", e);
        } 
        log.info("解析后的map  ---->" + map);
        return map;
           
        }  

}
