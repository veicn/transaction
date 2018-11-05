package com.sinochem.crude.trade.common.base;

import java.util.Locale;

/**
 * 领域对象VO基类
 * @author Yichen Zhao
 * date: 20180303
 */
public abstract class BaseDomainVO<T> {

    public BaseDomainVO() { }

    /**
     * 使用构造函数获得VO
     */
    public BaseDomainVO(T domain) {
        convertToVO(domain);
    }

    /**
     * 使用该方法获得领域对象
     */
    public T getDomain() {
        return this.convertToDomain();
    }

    /**
     * 将领域类转化为当前VO对象
     */
    protected abstract void convertToVO(T domain);

    /**
     * 将当前VO对象转化为领域类
     */
    protected abstract T convertToDomain();
}
