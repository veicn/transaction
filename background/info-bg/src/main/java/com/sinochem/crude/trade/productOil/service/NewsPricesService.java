package com.sinochem.crude.trade.productOil.service;


import com.sinochem.crude.trade.productOil.model.TCmsDomesticProductPrice;

import java.util.List;

public interface NewsPricesService {


    /**
    * @describe: 查询价格接口
    * @Author : CM
    * @Date 2018/3/30 15:12
    * @Param [productName, lagVer, day]
    * return java.util.List<com.sinochem.crude.trade.news.model.TCmsDomesticProductPrice>
    **/
    public List<TCmsDomesticProductPrice> queryProductPrice(String productName, String lagVer, int day);

    /**
    * @describe:产生咨询信息
    * @Author : CM
    * @Date 2018/3/30 15:10
    * @Param [day, productName, htmlText, lagVel]
    * return void
    **/
    public void createInfo(int day, String productName, String htmlText, String lagVel);
}
