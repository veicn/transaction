package org.geojson.httpclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) {
		String timeStr = "2017-8-24 11:17:10"; // 字面时间  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区  
		//1503562630000
		Date d = null;
		try {
			d = sdf.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(sdf.format(d) + ", " + d.getTime());  

	}

}
