package com.sinochem.crude.trade.common.i18n;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.eyeieye.melody.util.ArrayUtil;
import com.sinochem.it.b2b.common.utils.DateUtil;

public class VisitorLocale extends com.eyeieye.melody.web.locale.VisitorLocale {

	public static String getDateByCurrentLocale(Date date) {
		if (date == null) {
			throw new IllegalArgumentException(
					"i18n parameter date can't be null.");
		}
		return getByCurrentLanguage(
				new String[] { "zh", DateUtil.format(date, "yyyy-MM-dd") },
				new String[] { "en", DateUtil.format(date, "dd/MM/yyyy") });
	}

	public static String getByCurrentLocale(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowLocale = now.toString();
		String find = map.get(nowLocale);
		if (find != null) {
			return find;
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}

	public static String getByCurrentLanguage(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowLanguage = now.getLanguage().toString();
		String find = map.get(nowLanguage);
		if (find != null) {
			return find;
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}

	public static String getByCurrentCountry(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowCountry = now.getCountry().toString();
		String find = map.get(nowCountry);
		if (find != null) {
			return find;
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}

	public static String getByCurrentLocale(String[]... array) {
		if (ArrayUtil.isEmpty(array)) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowLocale = now.toString();
		for (String[] strs : array) {
			if (strs[0].equals(nowLocale)) {
				return strs[1];
			}
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}

	public static String getByCurrentLanguage(String[]... languages) {
		if (ArrayUtil.isEmpty(languages)) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowLanguage = now.getLanguage().toString();
		for (String[] strs : languages) {
			if (strs[0].equals(nowLanguage)) {
				return strs[1];
			}
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}

	public static String getByCurrentCountry(String[]... countries) {
		if (ArrayUtil.isEmpty(countries)) {
			throw new IllegalArgumentException(
					"i18n parameter Map can't be null.");
		}
		Locale now = getCurrent();
		if (now == null) {
			now = getDefault();
		}
		if (now == null) {
			throw new IllegalStateException(
					"can't find current and default locale.");
		}
		String nowCountry = now.getCountry().toString();
		for (String[] strs : countries) {
			if (strs[0].equals(nowCountry)) {
				return strs[1];
			}
		}
		// 如果在map中没有找到对应的字符串,输出一个空格,而不是抛出异常
		return "";
	}
}
