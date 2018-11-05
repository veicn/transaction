package com.sinochem.crude.trade.common.values.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sinochem.crude.trade.common.utils.JedisUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.common.values.ValueSetManager;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RMap;
import org.springframework.util.Assert;

/**
 * Created by GHuang on 2016/11/25.
 * Implements of valueSetManager using BD storage.
 */
public class CommonValueSetManager implements ValueSetManager {

    private static final Log log = LogFactory.getLog(CommonValueSetManager.class);

    protected static RMap<String, CodeValue> map = JedisUtils.getRedisson().getMap("_value_code_map");

    @Override
    public void load() {
        // Have a rest :)
    }

    @Override
    public CodeValue getValue(String setGroup, String code, String langVer) {
        return map.get(setGroup + "." +langVer + "." + code );
    }

    @Override
    public CodeValue getValueById(String id) {
        for (Map.Entry<String, CodeValue> e : map.entrySet()) {
            if (e.getValue().getId().equals(id)) {
                return e.getValue();
            }
        }
        return null;
    }

    @Override
    public Map<String, CodeValue> getValuesByGroup(final String setGroup, final String langVer) {
        Assert.notNull(setGroup);
        Set<Entry<String, CodeValue>> entries = map.entrySet("*" + setGroup + "*");
        Set<Entry<String, CodeValue>> filtered = Sets.filter(entries, new Predicate<Entry<String, CodeValue>>() {
            @Override
            public boolean apply(Entry<String, CodeValue> input) {
                return setGroup.equals(input.getValue().getGroup()) && langVer.equals(input.getValue().getLangVer());
            }
        });
        TreeSet<Entry<String, CodeValue>> sorted = Sets.newTreeSet(new CodeValueEntityComparator());
        sorted.addAll(filtered);
        return new MapFromEntry<>(sorted);
    }
    
    @Override
    public Map<String, CodeValue> getValuesByParentId(final String parentId,final String langVer) {
        Assert.notNull(parentId);
        Set<Entry<String, CodeValue>> entries = Maps.filterValues(map, new Predicate<CodeValue>() {
            @Override
            public boolean apply(CodeValue codeValue) {
                return null != codeValue && parentId.equals(codeValue.getParentId()) && langVer.equals(codeValue.getLangVer());
            }
        }).entrySet();
        TreeSet<Entry<String, CodeValue>> sorted = Sets.newTreeSet(new CodeValueEntityComparator());
        sorted.addAll(entries);
        return new MapFromEntry<>(sorted);
    }

    @Override
    public void clean() {
        map.clear();
    }

}
