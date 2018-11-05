package com.sinochem.crude.trade.datafeed;

public interface Callback<T> {
    void handle(T data);
}
