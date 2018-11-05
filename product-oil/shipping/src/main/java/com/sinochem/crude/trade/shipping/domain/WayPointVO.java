package com.sinochem.crude.trade.shipping.domain;

/**
 * @Author: fengzk
 * @CreateDate: 2018/5/5 12:01
 * @Version: [v1.0]
 */
public class WayPointVO {
    private static final long serialVersionUID = 5359233873872025867L;

    private Double lat;
    private Double lng;
    private Double cog;
    private String text;


    public Double getCog() {
        return cog;
    }
    public void setCog(Double cog) {
        this.cog = cog;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getLng() {
        return lng;
    }
    public void setLng(Double lng) {
        this.lng = lng;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
