package com.sinochem.crude.trade.order.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;

/**
 * 订单业务类型
 * Created by bbt on 2017/12/13.
 */
public enum EnumContractBizType {

    CrudeOilContract("CrudeOil", MsgConstart.ORDER0036),
    ProductOilContract("ProductOil", MsgConstart.ORDER0037)
    ;

    private String code;
    private String desc;
    private String[][] descs;

    EnumContractBizType(String code, String[][] descs) {
        this.code = code;
        this.descs = descs;
    }

    public String[][] getDescs() {
        return descs;
    }

    public void setDescs(String[][] descs) {
        this.descs = descs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return VisitorLocale.getByCurrentLanguage(descs);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
