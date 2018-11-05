package com.sinochem.crude.trade.shipagent.domain.vo;

import com.sinochem.crude.trade.shipagent.domain.TShipagentAppoint;


/**
 * @author admin1
 */
public class TShipagentAppointQueryVO extends TShipagentAppoint {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    /**外贸合同号*/
    private String purchaseContractNo;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "TShipagentAppointQueryVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }


    public String getPurchaseContractNo() {
        return purchaseContractNo;
    }

    public void setPurchaseContractNo(String purchaseContractNo) {
        this.purchaseContractNo = purchaseContractNo;
    }
}
