package com.sinochem.crude.trade.customs.domain;

import java.io.Serializable;

/**
 * @Author: fengzk
 * @CreateDate: 2018/8/21 20:11
 * @Version: [v1.0]
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1l;
    public static final int SUCCESS = 0;
    public static final int ERROR = 9999;
    private int status;
    private String message;

    public Result() {
        this.status = 0;
        this.message = "成功";
    }

    public Result(String message) {
        this.status = 0;
        this.message = message;
    }

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFail(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public void setFail(String message) {
        this.setFail(message, 9999);
    }


}
