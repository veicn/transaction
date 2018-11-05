package com.sinochem.crude.trade.orderexecute.commons.vmtool;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.tools.generic.NumberTool;

/**
 * 扩展VM数字格式化方法
 * @author me
 *
 */
public class NumberToolExt extends NumberTool {
	
	/**
	 * 重写format方法，如果格式化内容非数字则直接返回原值
	 */
	@Override
	public String format(Object obj) {
		if(obj == null) {
			return null;
		}
		
		if(NumberUtils.isNumber(obj.toString())) {
			return super.format(obj);
		}
		
		return obj.toString();
		
	}
	
	/**
	 * 重写format方法，如果格式化内容非数字则直接返回原值
	 */
	@Override
	public String format(String format, Object obj) {
		if(obj == null) {
			return null;
		}
		
		if(NumberUtils.isNumber(obj.toString())) {
			return super.format(format, obj);
		}
		
		return obj.toString();
	}

}
