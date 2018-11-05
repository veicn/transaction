package com.sinochem.crude.trade.shipping.domain.dictionary;

/**
 * 规格字典
 * @author Yichen Zhao
 * date: 20180226
 */
public class ProductRon {

    /**
     * ID
     */
    private Integer id;

    /**
     * 商品分类的ID
     */
    private Integer productCategoryId;

    /**
     * 名称
     */
    private String name;

    /** getters and setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
