package com.sinochem.crude.trade.portal.model.vo;

import java.io.Serializable;

/**
 * 用于接收资讯模块返回JSON的VO，咨询模块的封装是统一的
 * @author Yichen Zhao
 * date: 20180204
 */
public class InfoResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -3483465772409883164L;

    private String status;

    private String message;

    private T dataList;

    /** getters and setters */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }
}
