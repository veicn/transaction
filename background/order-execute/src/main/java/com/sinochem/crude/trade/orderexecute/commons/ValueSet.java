package com.sinochem.crude.trade.orderexecute.commons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;

public class ValueSet {
	private String group;
	private String lang;
	private Map<String, CodeValue> groupCodeValue;
	private List<CodeValue> codeList;
	
	public ValueSet(String group) {
		this.group = group;
		this.lang = VisitorLocale.getCurrent().getLanguage();
		this.groupCodeValue = ValueSetUtils.getValuesByGroup(group);
		
		List<CodeValue> list = new ArrayList<>();
		Iterator<Entry<String, CodeValue>> iterator = this.groupCodeValue.entrySet().iterator();
		while(iterator.hasNext()) {
			list.add(iterator.next().getValue());
		}
		this.codeList = list;
	}
	
	public CodeValue getByCode(String code) {
		return getByCode(code, code);
	}
	
	public CodeValue getByCode(String code, String defaultVal) {
		CodeValue defaultValue = new CodeValue();
		defaultValue.setCode(code);
		defaultValue.setValue(defaultVal);
		
		String key = this.group + "." + this.lang + "." + code;
		
		CodeValue codeValue = groupCodeValue.get(key);
		if(codeValue == null) {
			codeValue = defaultValue;
		}
		
		return codeValue;
	}

	public List<CodeValue> getCodeList() {
		return codeList;
	}
}
