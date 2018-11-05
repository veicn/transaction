/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.common.values.ValueSetManager;

/**
 * Created by HuangWj on 2016/11/17. Utils help to get and handle value set.
 */
public class ValueSetUtils {

    private static ValueSetManager valueSetManager;
    
    private ValueSetUtils() {
    }

    public static void init(ValueSetManager valueSetManager) {
        ValueSetUtils.valueSetManager = valueSetManager;
    }

    static boolean isInited() {
        return valueSetManager != null;
    }

    private static ValueSetManager getValueSetManager() {
        if (null == valueSetManager) {
            throw new IllegalStateException("ValueSetUtils is not bean initiated.");
        }
        return valueSetManager;
    }

    public static void refresh() {
        getValueSetManager().load();
    }

    /**
     * 根据当前语言环境获取值集，默认中文
     * @param setGroup
     * @param code
     * @return
     */
    public static CodeValue getValue(String setGroup, String code) {
    	String lang = "zh";
    	Locale current = VisitorLocale.getCurrent();
    	if(current != null && StringUtils.isNotBlank(current.getLanguage())){
    		lang = current.getLanguage();
    	} else {
    		lang = "zh";
    	}
    	
    	return getValue(setGroup, code, lang);
    }
    
    /**
     * 根据指定语言获取值集
     * @param setGroup
     * @param code
     * @param lang
     * @return
     */
    public static CodeValue getValue(String setGroup, String code, String lang) {
    	return getValueSetManager().getValue(setGroup, code, lang);
    }

    public static CodeValue getValueById(String id) {
        return getValueSetManager().getValueById(id);
    }

    /**
     * 根据当前语言环境获取值集，默认中文
     * @param setGroup
     * @return
     */
    public static Map<String, CodeValue> getValuesByGroup(String setGroup) {
    	String lang = "zh";
    	Locale current = VisitorLocale.getCurrent();
    	if(current != null && StringUtils.isNotBlank(current.getLanguage())){
    		lang = current.getLanguage();
    	} else {
    		lang = "zh";
    	}
    	
    	return getValuesByGroup(setGroup, lang);
    }
    
    /**
     * 根据指定语言获取值集
     * @param setGroup
     * @param lang
     * @return
     */
    public static Map<String, CodeValue> getValuesByGroup(String setGroup, String lang) {
    	return getValueSetManager().getValuesByGroup(setGroup, lang);
    }
    
    public static Map<String, CodeValue> getValuesByParentId(String parentId) {
    	String lang = "zh";
    	Locale current = VisitorLocale.getCurrent();
    	if(current != null && StringUtils.isNotBlank(current.getLanguage())){
    		lang = current.getLanguage();
    	} else {
    		lang = "zh";
    	}
    	return getValueSetManager().getValuesByParentId(parentId,lang);
    }

    public static Map<String, CodeValue> getValuesByParentId(String parentId, String lang) {
    	return getValueSetManager().getValuesByParentId(parentId,lang);
    }
}
