package com.sinochem.crude.trade.info.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class ExcelCheckUtil {

	
	public static boolean isNotEmptyStr(Object obj){  
		if(obj != null && !StringUtils.isBlank(obj.toString())) {
			return true;  
		}
		return false;
    } 
	
	
	public static boolean isNotEmptyNumeric(String str){  
		if(StringUtils.isBlank(str))
			return false;
        Pattern pattern = Pattern.compile("^(?!0\\d)\\d+(\\.\\d{1,})?(E[-]{0,1}\\d+)?$");  
        Matcher isNum = pattern.matcher(str);  
        if( !isNum.matches() ){  
            return false;  
        }  
        return true;  
    } 
	
	public static boolean isNotEmptyDate(String str){  
		boolean convertSuccess=true;
		if(StringUtils.isBlank(str))
			return false;
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       try {
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {
	           convertSuccess=false;
	       } 
	       return convertSuccess; 
    } 
}
