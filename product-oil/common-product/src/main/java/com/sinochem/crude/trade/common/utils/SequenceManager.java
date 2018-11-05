/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

/**
 * Created by GHuang on 2016/11/8. 单据号生成辅助类
 */
public interface SequenceManager {

    String SEQUENCE_KEY = SequenceManager.class.getName() + "_SEQUENCE_KEY";

    /**
     * 存储中是否已存在序列号键值(序列号前缀)
     *
     * @param key 序列号键值
     * @return 是否存在
     */
    boolean contains(String key);

    /**
     * 对应键值的序列号加一(需要自行保证原子性)
     *
     * @param key 序列号键值
     * @return 自增后的结果
     */
    long increment(String key);

    /**
     * 将对应键值的序列号设置为某个值(自行保证线程安全)
     *
     * @param key 序列号键值
     * @param value 设置的值
     */
    void save(String key, long value);
}
