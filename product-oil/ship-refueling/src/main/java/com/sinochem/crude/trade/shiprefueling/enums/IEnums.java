package com.sinochem.crude.trade.shiprefueling.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 通用枚举接口
 * @author songhaiqiang
 */
public interface IEnums {

	public final String DEFAULT_VALUE_CODE = "code";

	public final String DEFAULT_VALUE_NAME_ZH = "zhName";

	public final String DEFAULT_VALUE_NAME_EN = "enName";

	/**
	 * 获取当前code值
	 * @return 当前code值
	 */
	public String getCode();

	/**
	 * 获取当前code值
	 * @return 当前中文值
	 */
	public String getZhName();

	/**
	 * 获取当前英文值
	 * @return 当前英文值
	 */
	public String getEnName();



//	public String getValue() {
//		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_CODE);
//		if (field == null)
//			return null;
//		try {
//			field.setAccessible(true);
//			return field.get(this).toString();
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@JsonValue
//	public String getLabel() {
//		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME_ZH);
//		if (field == null)
//			return null;
//		try {
//			field.setAccessible(true);
//			return field.get(this).toString();
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//
//	static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer value) {
//		if (value == null)
//			throw  new IllegalArgumentException("DisplayedEnum value should not be null");
//		if (enumClass.isAssignableFrom(IEnums.class))
//			throw new IllegalArgumentException("illegal DisplayedEnum type");
//		T[] enums = enumClass.getEnumConstants();
//		for (T t: enums) {
//			IEnums displayedEnum = (IEnums)t;
//			if (displayedEnum.getCode().equals(value))
//				return (T) displayedEnum;
//		}
//		throw new IllegalArgumentException("cannot parse integer: " + value + " to " + enumClass.getName());
//	}


}
