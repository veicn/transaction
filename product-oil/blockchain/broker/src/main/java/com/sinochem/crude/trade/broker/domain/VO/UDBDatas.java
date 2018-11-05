package com.sinochem.crude.trade.broker.domain.VO;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/18 14:49
 * @Version: [v1.0]
 */
public class UDBDatas {
    private String code;
    private String message;
    private List<UDBResponseData> response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UDBResponseData> getResponse() {
        return response;
    }

    public void setResponse(List<UDBResponseData> response) {
        this.response = response;
    }
}
