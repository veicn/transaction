package com.sinochem.crude.trade.shiprefueling.model.vo;

import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shiprefueling.enums.CredentialsEnums;

/**
 * @author WGP
 * @description
 * @date 2018/6/7
 **/
public class ShipEnterpriseVo extends EnterpriseVo {
    /*
    资质
     */
    private String[] credentials;
    /*
    经营范围
     */
    private String[] manageRange;

    private String registerAddress;

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String[] getManageRange() {
        return manageRange;
    }

    public void setManageRange(String[] manageRange) {
        this.manageRange = manageRange;
    }

    public String[] getCredentials() {
        return credentials;
    }

    public void setCredentials(String[] credentials) {
        this.credentials = credentials;
    }
}
