/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * Created by GHuang on 2016/11/17. Simple implement of SequenceManager, based on ConcurrentMap and not safe with
 * cluster.
 *
 * @see com.runyi.ryplat.api.utils.SequenceManager
 */
@Component
public class SimpleSequenceManager implements SequenceManager {

    private Map<String, AtomicLong> container = Maps.newConcurrentMap();

    @Override
    public boolean contains(String key) {
        return container.containsKey(key);
    }

    @Override
    public long increment(String key) {
        return container.get(key).incrementAndGet();
    }

    @Override
    public void save(String key, long value) {
        container.put(key, new AtomicLong(value));
    }
}
