package com.sinochem.crude.trade.member.util;

/**
 * Created by wgp on 2018/7/25.
 */
public class UdbResult<T> {
    private Integer code;
    private String message;
    private T response;

    public UdbResult(){

    }

    public UdbResult(String message){
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
