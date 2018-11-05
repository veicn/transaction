package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;

/**
 *  /**
 * 根据当前登录用户查询出所有与该用户参与贸易链的企业名称
 * Made By WangTing
  */
public class EnterpriseNameByTradingChainVO {

    /**
     * 企业id
     */
    private Long memberId;

    /**
     * 企业名称
     */
    private String enterpriseNameZH;

    private String enterpriseNameEN;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getEnterpriseNameZH() {
        return enterpriseNameZH;
    }

    public void setEnterpriseNameZH(String enterpriseNameZH) {
        this.enterpriseNameZH = enterpriseNameZH;
    }

    public String getEnterpriseNameEN() {
        return enterpriseNameEN;
    }

    public void setEnterpriseNameEN(String enterpriseNameEN) {
        this.enterpriseNameEN = enterpriseNameEN;
    }
}
