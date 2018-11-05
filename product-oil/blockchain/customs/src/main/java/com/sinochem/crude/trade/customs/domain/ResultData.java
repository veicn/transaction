package com.sinochem.crude.trade.customs.domain;

public class ResultData<T> extends Result {
    private static final long serialVersionUID = 1l;
    private T datas;

    public ResultData() {
    }

    public ResultData(T datas) {
        this.datas = datas;
    }

    public T getDatas() {
        return this.datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
