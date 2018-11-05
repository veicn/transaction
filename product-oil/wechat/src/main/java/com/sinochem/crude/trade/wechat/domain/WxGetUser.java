package com.sinochem.crude.trade.wechat.domain;

import com.sinochem.crude.trade.wechat.helper.HttpHelper;

import java.util.List;

public  class  WxGetUser extends WxError{
    private  int total;
    private  int count;

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    private String next_openid;
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WxGetUserData getData() {
        return data;
    }

    public void setData(WxGetUserData data) {
        this.data = data;
    }

    private WxGetUserData data;

}
class  WxGetUserData{
    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }

    private List<String> openid;
}





