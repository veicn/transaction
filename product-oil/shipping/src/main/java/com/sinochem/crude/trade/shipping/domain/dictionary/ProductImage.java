package com.sinochem.crude.trade.shipping.domain.dictionary;

/**
 * 商品图片字典
 * @author Yichen Zhao
 * date: 20180226
 */
public class ProductImage {

    /**
     * ID
     */
    private Integer id;

    /**
     * 图片路径，通过UUID能够获取到图片，以","拼接多个路径
     */
    private String paths;

    /** getters and setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }
}
