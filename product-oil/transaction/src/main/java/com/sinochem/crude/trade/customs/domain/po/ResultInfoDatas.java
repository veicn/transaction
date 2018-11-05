package com.sinochem.crude.trade.customs.domain.po;

import com.sinochem.crude.trade.shipagent.utils.ResultData;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/13 14:46
 * @Version: [v1.0]
 */
public class ResultInfoDatas extends ResultData {
    private String message;

    private String CREATE_DATE;

    private String blockchain_id;

    private String CREATE_USER;

    private String deal_no;

    private String event_code;

    private String description;

    private String blockchain_txid;

    private String id;

    private String return_state;

    private String busi_id;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getBlockchain_id() {
        return blockchain_id;
    }

    public void setBlockchain_id(String blockchain_id) {
        this.blockchain_id = blockchain_id;
    }

    public String getCREATE_USER() {
        return CREATE_USER;
    }

    public void setCREATE_USER(String CREATE_USER) {
        this.CREATE_USER = CREATE_USER;
    }

    public String getDeal_no() {
        return deal_no;
    }

    public void setDeal_no(String deal_no) {
        this.deal_no = deal_no;
    }

    public String getEvent_code() {
        return event_code;
    }

    public void setEvent_code(String event_code) {
        this.event_code = event_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlockchain_txid() {
        return blockchain_txid;
    }

    public void setBlockchain_txid(String blockchain_txid) {
        this.blockchain_txid = blockchain_txid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturn_state() {
        return return_state;
    }

    public void setReturn_state(String return_state) {
        this.return_state = return_state;
    }

    public String getBusi_id() {
        return busi_id;
    }

    public void setBusi_id(String busi_id) {
        this.busi_id = busi_id;
    }
}
