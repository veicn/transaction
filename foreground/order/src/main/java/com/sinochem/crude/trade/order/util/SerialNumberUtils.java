package com.sinochem.crude.trade.order.util;


import com.sinochem.crude.trade.common.enums.SerialNumberBizType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumberUtils {

	/**
	 * 生成12位流水号
	 * 生成规则：六位日期+业务类型两位+流水号最后四位，不足用0补齐
	 * @param bizType 业务类型
	 * @param date 订单创建日期
	 */
	public static String  getSerialNumber12Len(SerialNumberBizType bizType, Date date){

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		// 格式化日期
		String dateStr = sdf.format(date);
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
