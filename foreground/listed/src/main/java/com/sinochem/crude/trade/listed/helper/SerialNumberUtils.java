package com.sinochem.crude.trade.listed.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sinochem.crude.trade.listed.enums.SerialNumberBizType;

public class SerialNumberUtils {

	/**
	 * 生成12位流水号
	 * 生成规则：
	 * 		第1,3,5,7,9,11位对应yyMMdd格式的时间字符串
	 * 		第2,4位代表业务类型
	 * 		第6,8,10,12位代表时间戳后四位
	 * @param bizType 业务类型
	 * @param pubDate 需求单发布日期
	 */
	public static String  getSerialNumber12Len(SerialNumberBizType bizType,Date pubDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		// 格式化日期
		String dateStr = sdf.format(pubDate);
		// 业务类型编码
		String bizTypeStr = bizType.getBizType();

		String timeStr = String.valueOf(System.currentTimeMillis());

		// 四位流水码-时间戳的后四位
		String bizIdStr = timeStr.substring(timeStr.length()-4, timeStr.length());

		char[] serialArr = new char[12];

		serialArr[0] = dateStr.charAt(0);
		serialArr[1] = bizTypeStr.charAt(0);
		serialArr[2] = dateStr.charAt(1);
		serialArr[3] = bizTypeStr.charAt(1);
		serialArr[4] = dateStr.charAt(2);
		serialArr[5] = bizIdStr.charAt(0);
		serialArr[6] = dateStr.charAt(3);
		serialArr[7] = bizIdStr.charAt(1);
		serialArr[8] = dateStr.charAt(4);
		serialArr[9] = bizIdStr.charAt(2);
		serialArr[10] = dateStr.charAt(5);
		serialArr[11] = bizIdStr.charAt(3);

		
		return String.valueOf(serialArr);
	}
}
