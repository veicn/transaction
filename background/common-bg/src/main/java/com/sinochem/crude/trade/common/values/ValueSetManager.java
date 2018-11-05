/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.values;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by GHuang on 16/8/8. 统一不同平台上值集操作的接口
 */
public interface ValueSetManager {

    /**
     * 载入值集信息, 一般把载入缓存的动作写在这个方法中
     */
    void load();

    /**
     * 获取具体的值集项
     *
     * @param setGroup 值集组代码
     * @param code 值集具体项代码
     * @return 值集具体项的值
     */
    CodeValue getValue(String setGroup, String code, String langVer);

    /**
     * 按照id获取具体的值集项
     *
     * @param id 值集id
     * @return 值集具体项的值
     */
    CodeValue getValueById(String id);

    /**
     * 获取值集集合列表
     *
     * @param setGroup 值集组代码
     * @return 值集列表, 一般key为group.code
     */
    Map<String, CodeValue> getValuesByGroup(String setGroup, String langVer);
    
    /**
     * 获取值集集合列表
     *
     * @param parentId 值集父id
     * @return 值集列表, 一般key为group.code
     */
    Map<String, CodeValue> getValuesByParentId(String parentId, String langVer);

    /**
     * 清除值集信息, 一般把载入缓存的动作写在这个方法中
     */
    void clean();

    class CodeValueEntityComparator implements Comparator<Entry<String, CodeValue>> {
        @Override
        public int compare(Entry<String, CodeValue> o1, Entry<String, CodeValue> o2) {
            return o1.getValue().getSort().compareTo(o2.getValue().getSort());
        }
    }

    class MapFromEntry<K, V> extends AbstractMap<K, V> {

        private Set<Entry<K, V>> entries;

        public MapFromEntry(Set<Entry<K, V>> entries) {
            this.entries = entries;
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return entries;
        }
    }

}