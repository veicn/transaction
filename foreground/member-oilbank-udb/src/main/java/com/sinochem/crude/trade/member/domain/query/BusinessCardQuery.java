package com.sinochem.crude.trade.member.domain.query;

import com.sinochem.crude.trade.member.domain.BusinessCards;

/**
 * Created by AlterEgo on 2018/5/7.
 */
public class BusinessCardQuery extends BusinessCards {

    private String queryName;

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
}
