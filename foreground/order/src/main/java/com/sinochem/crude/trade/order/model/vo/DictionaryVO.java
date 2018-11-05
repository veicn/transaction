package com.sinochem.crude.trade.order.model.vo;

import java.io.Serializable;

/**
 * 瀛楀吀鐨刅O
 * @author Yichen Zhao
 * date: 20180301
 */
public class DictionaryVO implements Serializable {

    /**
     * 编号
     */
    private Integer code;

    /**
     * 中文
     */
    private String zhName;

    /**
     * 英文
     */
    private String enName;

    public DictionaryVO() {

    }

    public DictionaryVO(Integer code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters and setters */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
