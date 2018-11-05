package com.sinochem.crude.trade.productOil.service.impl;

import com.sinochem.crude.trade.productOil.constant.InfoConstant;
import com.sinochem.crude.trade.productOil.dao.*;
import com.sinochem.crude.trade.productOil.model.*;
import com.sinochem.crude.trade.productOil.model.query.InfoQuery;
import com.sinochem.crude.trade.productOil.model.query.ProductCodeQuery;
import com.sinochem.crude.trade.productOil.model.query.ProductInfoQuery;
import com.sinochem.crude.trade.productOil.service.NewsPricesService;
import com.sinochem.crude.trade.productOil.utils.DateTimeUtils;
import com.sinochem.crude.trade.productOil.utils.KeyGenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NewsPricesServiceImpl implements NewsPricesService {


    @Autowired
    private TSysCodeSetMapper sysCodeSetMapper;

    @Autowired
    private TCmsDomesticProductPriceMapper productPriceMapper;

    @Autowired
    private TCmsChannelSubMapper channelSubMapper;

    @Autowired
    private TCmsInfoMapper infoMapper;

    @Autowired
    private TCmsInfoContentMapper infoContentMapper;


    public void createInfo(int day,String productName,String htmlText,String lagVel) {

        String channelCode = InfoConstant.channelType.get(productName);
        //无PRICES时不存储数据
        if(channelCode==null || "".equals(channelCode)) {
            return ;
        }

        //无数据时不存储
        if(htmlText==null || "".equals(htmlText)) {
            return ;
        }

        String title = getTime(day)+"  ";
        if(channelCode.equals("PRICES7")) {
            productName = "0# Gasoil Wholesale Prices of China";
        }else if(channelCode.equals("PRICES3")) {
            productName = "Prices of gasoline #92 and #95";
        }
        title = title+productName;

        //查询频道信息
        TCmsChannelSub channelSub = channelSubMapper.selectByChannelCode(channelCode);

        //查询是否存在
        InfoQuery infoQuery = new InfoQuery();
        infoQuery.setChannelId(channelSub.getId());
        infoQuery.setExtend10(InfoConstant.langVel_EN);
        infoQuery.setTitle(title);
        TCmsInfo mysqlInfo = infoMapper.selectByChannelTitle(infoQuery);
        if(mysqlInfo!=null) {

            TCmsInfoContent mysqlInfoContent =  infoContentMapper.selectByInfoId(mysqlInfo.getId());
            if(mysqlInfoContent!=null) {
                mysqlInfoContent.setTexHtml(htmlText);
                mysqlInfoContent.setUpdateDate(DateTimeUtils.currentDate());
                infoContentMapper.updateByPrimaryKeySelective(mysqlInfoContent);
            }
        }else {
            //创建咨询
            TCmsInfo info = new TCmsInfo();

            info.setStick("0");
            info.setUuid(KeyGenUtils.newUuid());
            info.setStatus("30");
            info.setAuthor("金凯讯");
            info.setIsShowDisclaimer("1");// 免责申明

            info.setCreateDate(DateTimeUtils.currentDate());
            info.setReleaseDate(DateTimeUtils.currentDate());
            info.setValidBegin(DateTimeUtils.currentDate());

            info.setChannelId(channelSub.getId());
            info.setTitle(title);

            info.setExtend10(lagVel);
            info.setIsShowOrigin("0");
            info.setAliveFlag("1");

            info.setBrowseCount(0);
            info.setCommentCount(0);
            info.setShareCount(0);
            info.setCollectionCount(0);
            info.setTrampleCount(0);
            info.setFabulousCount(0);

            infoMapper.insertSelective(info);

            //创建咨询内容
            TCmsInfoContent infoContent = new TCmsInfoContent();
            infoContent.setUuid(KeyGenUtils.newUuid());
            infoContent.setInfoId(info.getId());
            infoContent.setAliveFlag("1");
            infoContent.setCreateDate(DateTimeUtils.currentDate());
            infoContent.setTexHtml(htmlText);

            infoContentMapper.insertSelective(infoContent);
        }
    }

    public List<TCmsDomesticProductPrice> queryProductPrice(String productName, String lagVer, int day){


        //查询PRODUCT_CODE
        ProductCodeQuery codeQuery = new ProductCodeQuery();
        codeQuery.setItemName(productName);
        codeQuery.setLangVer(lagVer);

        List<TSysCodeSet> sysCodeSetList = sysCodeSetMapper.selectByItemName(codeQuery);
        if(sysCodeSetList==null) {
            return null;
        }
        List<TCmsDomesticProductPrice> productPriceList = new ArrayList<>();
        for (TSysCodeSet sysCodeSet : sysCodeSetList) {

            ProductInfoQuery infoQuery = new ProductInfoQuery();
            infoQuery.setPriceDate(getTime(day));
            infoQuery.setProductCode(sysCodeSet.getItemCode());
            infoQuery.setProductName(productName);
            infoQuery.setExtend10(lagVer);
            List<TCmsDomesticProductPrice> tempList = productPriceMapper.selectByProductCode(infoQuery);
            productPriceList.addAll(tempList);
        }

        return productPriceList;
    }

    public String getTime(int day) {

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE,day);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }




}
