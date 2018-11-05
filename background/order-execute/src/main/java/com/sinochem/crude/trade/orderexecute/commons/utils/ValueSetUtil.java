package com.sinochem.crude.trade.orderexecute.commons.utils;

import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.LanguageEnum;

public class ValueSetUtil {
	/**
	 * 获取group分类下所有值集
	 * @param group
	 * @return
	 */
	public static ValueSet instance(String group) {
		return new ValueSet(group);
	}
	
	/**
	 * 根据当前语言环境获取单个值集，没查到默认返回原始code
	 * @param setGroup
	 * @param code
	 * @return
	 */
	public static CodeValue getByCode(String setGroup, String code) {
		return getByCode(setGroup, code, null);
	}
	
	/**
	 * 根据指定语言获取单个值集，没查到默认返回原始code
	 * @param setGroup
	 * @param code
	 * @param lang
	 * @return
	 */
	public static CodeValue getByCode(String setGroup, String code, LanguageEnum lang) {
		CodeValue codeValue;
		if(lang != null) {
			codeValue = ValueSetUtils.getValue(setGroup, code, lang.name().toLowerCase());
		}else {
			codeValue = ValueSetUtils.getValue(setGroup, code);
		}
		
		if(codeValue == null) {
			codeValue = new CodeValue();
			codeValue.setCode(code);
			codeValue.setValue(code);
		}
		
		return codeValue;
	}
	
	/**
	 * 获取中文值集
	 * @param setGroup
	 * @param code
	 * @return
	 */
	public static String getValueZh(String setGroup, String code) {
		return getByCode(setGroup, code, LanguageEnum.ZH).getValue();
	}
	
	public static String getValueByCode(String setGroup, String code) {
		return getByCode(setGroup, code).getValue();
	}
	
	public static String getValueByCode(String setGroup, String code, LanguageEnum lang) {
		return getByCode(setGroup, code).getValue();
	}
}
