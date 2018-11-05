package com.sinochem.crude.trade.shiprefueling.model.query;

import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**zhaoyulong
 * Created by z1761 on 2018/5/28.
 */
public class IryQuotationQuery {

    /**业务唯一键*/
    private Long inquiryQuotationId;

    /**UUID*/
    private String uuid;

    /**公司id*/
    private Long companyId;

    /**公司名称*/
    private String companyName;

    /**电话*/
    private String tel;

    /**联系人*/
    private String contacts;

    /**状态(1:未联系 2:已联系3:已成交)*/
    private String status;

    /**需求ID*/
    private Long needId;
    //报价日期开始
    private String iryQuotationBegin;

    //报价日期结束
    private String iryQuotationEnd;

    /**需求UUID*/
    private String needUuid;
    @JsonUnwrapped
    private SimplePageInfo pageInfo;

    public String getIryQuotationBegin() {
        return iryQuotationBegin;
    }

    public void setIryQuotationBegin(String iryQuotationBegin) {
        this.iryQuotationBegin = iryQuotationBegin;
    }

    public String getIryQuotationEnd() {
        return iryQuotationEnd;
    }

    public void setIryQuotationEnd(String iryQuotationEnd) {
        this.iryQuotationEnd = iryQuotationEnd;
    }

    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Long getInquiryQuotationId() {
        return inquiryQuotationId;
    }

    public void setInquiryQuotationId(Long inquiryQuotationId) {
        this.inquiryQuotationId = inquiryQuotationId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNeedId() {
        return needId;
    }

    public void setNeedId(Long needId) {
        this.needId = needId;
    }

    public String getNeedUuid() {
        return needUuid;
    }

    public void setNeedUuid(String needUuid) {
        this.needUuid = needUuid;
    }
}
