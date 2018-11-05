package com.sinochem.crude.trade.customs.domain.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BlockchainInfoVO  implements Serializable {
    private Long id;

    private String dealNo;

    private Long busiId;

    private String postData;

    private String eventCode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    private String blockchainId;

    private String blockchainTxid;

    private Date createDate;

    private Long createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo == null ? null : dealNo.trim();
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData == null ? null : postData.trim();
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }


    public String getBlockchainId() {
        return blockchainId;
    }

    public void setBlockchainId(String blockchainId) {
        this.blockchainId = blockchainId == null ? null : blockchainId.trim();
    }

    public String getBlockchainTxid() {
        return blockchainTxid;
    }

    public void setBlockchainTxid(String blockchainTxid) {
        this.blockchainTxid = blockchainTxid == null ? null : blockchainTxid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }


    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",this.id);
        map.put("dealNo",this.dealNo);
        map.put("busiId",this.busiId);
        map.put("postData",this.postData);
        map.put("eventCode",this.eventCode);
        map.put("description",this.description);
        map.put("blockchainId",this.blockchainId);
        map.put("blockchainTxid",this.blockchainTxid);
        map.put("createDate",this.createDate);
        map.put("createUser",this.createUser);
        return map;
    }
}