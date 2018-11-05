package com.sinochem.crude.trade.shipping.domain.dictionary;

/**
 * 品种字典
 * @author Yichen Zhao
 * date: 20180225
 */
public class ProductVariety {

    /**
     * ID
     */
    private Integer id;

    /**
     * 商品分类的ID
     */
    private Integer productCategoryId;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    /**getters and setters*/
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
