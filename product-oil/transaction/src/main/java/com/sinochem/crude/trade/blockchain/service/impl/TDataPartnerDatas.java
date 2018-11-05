package com.sinochem.crude.trade.blockchain.service.impl;

import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.blockchain.service.TDataPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class TDataPartnerDatas {
   @Autowired
   TDataPartnerService tDataPartnerService;

    /**存放TDataPartner数据*/
    public static HashMap<String, TDataPartner> tDataPartnerMap = new HashMap<String, TDataPartner>();

    @PostConstruct
    public void init(){

        List<TDataPartner> list = tDataPartnerService.findAllList();

        for(TDataPartner tDataPartner:list){
            if(null!=tDataPartner.getWebDomain()&&!tDataPartner.getWebDomain().equals(""))
                tDataPartnerMap.put(tDataPartner.getWebDomain(),tDataPartner);

        }





    }

}
