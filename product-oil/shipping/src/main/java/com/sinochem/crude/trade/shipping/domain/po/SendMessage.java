package com.sinochem.crude.trade.shipping.domain.po;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/13
 **/
public class SendMessage {
    private Long memberId;
    private String content;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
