package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;

/**
 * 商品规格的VO
 */
public class ProductSpecificationVO extends DictionaryVO {

    private String productCategoryCode;

    public ProductSpecificationVO() {

    }

    public ProductSpecificationVO(String code, String zhName, String enName, String productCategoryCode) {
        super(code, zhName, enName);
        this.productCategoryCode = productCategoryCode;
    }

    /** getters and setters */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }
}
