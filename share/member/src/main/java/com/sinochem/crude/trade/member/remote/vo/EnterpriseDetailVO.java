package com.sinochem.crude.trade.member.remote.vo;

import java.io.Serializable;

public class EnterpriseDetailVO implements Serializable {

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
