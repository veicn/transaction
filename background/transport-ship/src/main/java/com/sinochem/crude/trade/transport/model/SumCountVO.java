package com.sinochem.crude.trade.transport.model;

/**
 * 记录操作数量
 * @author 姜秀强
 *
 */
public class SumCountVO {
	/**
	 * id
	 */
    private String id;

    /**
     * 条件
     */
    private String code;

    /**
     * 数量
     */
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}