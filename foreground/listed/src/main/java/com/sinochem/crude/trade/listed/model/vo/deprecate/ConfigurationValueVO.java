package com.sinochem.crude.trade.listed.model.vo.deprecate;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/12/11
 */
@Deprecated
public class ConfigurationValueVO {
    private int id;
    private String name;

    public ConfigurationValueVO() {

    }

    public ConfigurationValueVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
