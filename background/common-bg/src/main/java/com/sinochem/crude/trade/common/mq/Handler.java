/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.mq;

/**
 * 异步消息处理器
 *
 * @author HuangWj
 */
public interface Handler<T> {

    /**
     * 接收到数据时触发
     * @param input 接收到的数据
     * @throws Exception 可能抛出异常
     */
    void onReceive(T input) throws Exception;

    /**
     * 异常时的处理
     * @param cause 发生的异常
     */
    void onFail(Throwable cause);
}
