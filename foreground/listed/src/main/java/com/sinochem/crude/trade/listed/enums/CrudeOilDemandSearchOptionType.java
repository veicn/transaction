package com.sinochem.crude.trade.listed.enums;

import com.eyeieye.melody.web.locale.velocity.LocaleRender;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;

/**
 * 原油搜索条件枚举
 * @author Yichen Zhao
 * date: 20180111
 */
public enum CrudeOilDemandSearchOptionType {

    //PURCHASE_TYPE(1L, "交易方式"),
    //CRUDE_OIL_ORIGIN(2L, "原油产地"),
    //CRUDE_OIL_KIND(3L, "油种"),
    //QUANTITY(4L, "数量"),
    //PUBLISH_RANGE(5L, "发布范围"),
    //PUBLISH_TYPE(6L, "采购方式"),
    //ENTERPRISE(7L, "发布企业");
    
    PURCHASE_TYPE(1L, Constant.LISTED_0134),
    CRUDE_OIL_ORIGIN(2L, new String[][]{new String[]{"zh","原油产地"}, new String[]{"en",""}}),
    CRUDE_OIL_KIND(3L, new String[][]{new String[]{"zh","油种"}, new String[]{"en",""}}),
    QUANTITY(4L, Constant.LISTED_0140),
    PUBLISH_RANGE(5L, Constant.LISTED_0137),
    PUBLISH_TYPE(6L, Constant.LISTED_0146),
    ENTERPRISE(7L, Constant.LISTED_0145);
    
    Long code;
    String[][] names;
    String name;

    CrudeOilDemandSearchOptionType(Long code, String[][] names) {
        this.code = code;
        this.names = names;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String[][] getNames() {
        return names;
    }

    public void setNames(String[][] names) {
        this.names = names;
    }

    public String getName() {
        return VisitorLocale.getByCurrentLanguage(names);
    }

    public void setName(String name) {
        this.name = name;
    }
}
