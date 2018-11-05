package com.sinochem.crude.trade.broker.domain.VO;

import com.sinochem.crude.trade.broker.domain.*;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/4 15:10
 * @Version: [v1.0]
 */
public class TBrokerVO extends TBrokerDeclaration {
    public List<TBrokerGoods> gettBrokerGoods() {
        return tBrokerGoods;
    }

    public void settBrokerGoods(List<TBrokerGoods> tBrokerGoods) {
        this.tBrokerGoods = tBrokerGoods;
    }

    private List<TBrokerGoods> tBrokerGoods;
    private String dealUuid;

    public String getDealUuid() {
        return dealUuid;
    }

    public void setDealUuid(String dealUuid) {
        this.dealUuid = dealUuid;
    }


    private List<TBrokerProductspecification> tBrokerProductspecification;

    public List<TBrokerProductspecification> gettBrokerProductspecification() {
        return tBrokerProductspecification;
    }

    public void settBrokerProductspecification(List<TBrokerProductspecification> tBrokerProductspecification) {
        this.tBrokerProductspecification = tBrokerProductspecification;
    }

    private List<TBrokerCip> tBrokerCips;



    public List<TBrokerCip> gettBrokerCips() {
        return tBrokerCips;
    }

    public void settBrokerCips(List<TBrokerCip> tBrokerCips) {
        this.tBrokerCips = tBrokerCips;
    }

    public List<TCommonAttachments> gettBrokerVoucher() {
        return tBrokerVoucher;
    }

    public void settBrokerVoucher(List<TCommonAttachments> tBrokerVoucher) {
        this.tBrokerVoucher = tBrokerVoucher;
    }

    private  List<TCommonAttachments> tBrokerVoucher;
    public List<TBrokerBusinessmatters> gettBrokerBusinessmatters() {
        return tBrokerBusinessmatters;
    }

    public void settBrokerBusinessmatters(List<TBrokerBusinessmatters> tBrokerBusinessmatters) {
        this.tBrokerBusinessmatters = tBrokerBusinessmatters;
    }

    private List<TBrokerBusinessmatters> tBrokerBusinessmatters;
    private TBrokerDangerinfo tBrokerDangerinfo;
    private List<TBrokerDocuments> tBrokerDocuments;
    private List<TBrokerOthermatters> tBrokerOthermatters;

    public List<TBrokerDocuments> gettBrokerDocuments() {
        return tBrokerDocuments;
    }

    public void settBrokerDocuments(List<TBrokerDocuments> tBrokerDocuments) {
        this.tBrokerDocuments = tBrokerDocuments;
    }

    public List<TBrokerOthermatters> gettBrokerOthermatters() {
        return tBrokerOthermatters;
    }

    public void settBrokerOthermatters(List<TBrokerOthermatters> tBrokerOthermatters) {
        this.tBrokerOthermatters = tBrokerOthermatters;
    }

    public List<TBrokerOtherpackinfo> gettBrokerOtherpackinfo() {
        return tBrokerOtherpackinfo;
    }

    public void settBrokerOtherpackinfo(List<TBrokerOtherpackinfo> tBrokerOtherpackinfo) {
        this.tBrokerOtherpackinfo = tBrokerOtherpackinfo;
    }

    private List<TBrokerOtherpackinfo> tBrokerOtherpackinfo;


    public TBrokerDangerinfo gettBrokerDangerinfo() {
        return tBrokerDangerinfo;
    }

    public void settBrokerDangerinfo(TBrokerDangerinfo tBrokerDangerinfo) {
        this.tBrokerDangerinfo = tBrokerDangerinfo;
    }
}
