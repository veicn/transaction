package com.sinochem.crude.trade.shipagent.domain;

import java.io.Serializable;

/**
 * @author songhaiqiang
 */
public class ResultData<T> extends Result implements Serializable{
    private static final long serialVersionUID = 1L;
    private T data;

    public ResultData() {
        super();
    }

    public ResultData(String message){
        super(message);
    }

    public ResultData( int status ,String message){
        super(status , message);
    }

    public ResultData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
