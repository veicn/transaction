package com.sinochem.crude.trade.order.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;

/**
 * 订单取消操作类型
 * Created by zj on 2018/2/28
 * 1：申请
 * 2：同意
 * 3：驳回
 * 4：撤回
 */
public enum EnumCancelType {

    SQQX_CONTRACT(1, MsgConstart.ORDER0028),
    TYQX_CONTRACT(2, MsgConstart.ORDER0030),
    JJQX_CONTRACT(3, MsgConstart.ORDER0031),
    CHQX_CONTRACT(4, MsgConstart.ORDER0029),
    ;
    /*VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0031))*/

    private Integer code;
    private String [][] descs;
    private String desc;

    EnumCancelType(Integer code,String [][] descs) {
        this.code = code;
        this.descs = descs;
    }

    public static EnumCancelType getDoubleSignTypeByCode(Integer code) {
        for (EnumCancelType doubleSignType : EnumCancelType.values()) {
            if (doubleSignType.getCode().equals(code)) {
                return doubleSignType;
            }
        }

        return null;
    }

    public String[][] getDescs() {
        return descs;
    }

    public void setDescs(String[][] descs) {
        this.descs = descs;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return VisitorLocale.getByCurrentLanguage(descs);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
