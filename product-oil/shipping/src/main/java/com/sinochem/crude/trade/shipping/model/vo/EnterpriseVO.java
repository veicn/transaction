package com.sinochem.crude.trade.shipping.model.vo;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/23
 **/
public class EnterpriseVO {

    private Long id;

    private String name;

    private String cres;

    private Long memberId;

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCres() {
        return cres;
    }

    public void setCres(String cres) {
        this.cres = cres;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
