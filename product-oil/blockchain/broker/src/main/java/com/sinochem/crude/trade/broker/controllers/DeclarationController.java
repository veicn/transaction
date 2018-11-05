package com.sinochem.crude.trade.broker.controllers;

import com.sinochem.crude.trade.broker.common.ThreadContextHolder;
import com.sinochem.crude.trade.broker.domain.*;
import com.sinochem.crude.trade.broker.domain.VO.*;
import com.sinochem.crude.trade.broker.feign.HttpFeignClient;
import com.sinochem.crude.trade.broker.helper.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/7 11:07
 * @Version: [v1.0]
 * 报关单接口
 */
@RestController
@RequestMapping("/declaration")
public class DeclarationController {
    @Autowired
    HttpFeignClient httpFeignClient;
    @Autowired
    ThreadContextHolder threadContextHolder;
    @Value("${udbUrl}")
    private String udbUrl;

    @PostMapping(value = "/delete.json", produces = "application/json")
    public ResultData delDeclaration(@RequestBody TBrokerDeclaration uuid) {
        return httpFeignClient.delDeclaration(uuid);
    }

    @PostMapping(value = "/list.json", produces = "application/json")
    public ResultDataPages GetDeclarationList(@RequestBody TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO) {
        if(tBrokerDeclarationQueryVO==null||tBrokerDeclarationQueryVO.getEnterpriseId()==null) {
            TShipagentUser user=threadContextHolder.getCurrentUser();
            if(user!=null) {
                tBrokerDeclarationQueryVO.setEnterpriseId(user.getEpMemberId());
            }
           // tBrokerDeclarationQueryVO.setEnterpriseId(threadContextHolder.getCurrentUser().getEpMemberId());
        }
        return httpFeignClient.GetDeclarationList(tBrokerDeclarationQueryVO);
    }

    @GetMapping(value = "/detail.json", produces = "application/json")
    public ResultData GetDeclarationDetail(String uuid) {
        return httpFeignClient.GetDeclarationDetail(uuid);
    }

    @PostMapping(value = "/insert.json", produces = "application/json")
    public  ResultData insertDeclaration(@RequestBody TBrokerVO tBrokerVO) {
        return httpFeignClient.insertDeclaration(tBrokerVO);
    }

    @PostMapping(value = "/update.json", produces = "application/json")
    public  ResultData updateDeclaration(@RequestBody TBrokerVO tBrokerVO) {
        return httpFeignClient.updateDeclaration(tBrokerVO);
    }

    @PostMapping(value = "/billsFile/delete.json", produces = "application/json")
    public ResultData delBillsFile(@RequestBody TCommonAttachments  tCommonAttachments) {
        return httpFeignClient.delBillsFile(tCommonAttachments);
    }

    @PostMapping(value = "/billsFile/insert.json", produces = "application/json")
    public ResultData insertBillsFile(@RequestBody TCommonAttachmentsListVO list) {
        return httpFeignClient.insertBillsFile(list);
    }

    @GetMapping(value = "/billsFile/list.json", produces = "application/json")
    public ResultData GetBillsFileList(String businessUuid) {
        return httpFeignClient.GetBillsFileList(businessUuid);
    }

    @GetMapping(value = "/basicsDatas.json", produces = "application/json")
    public ResultData GetbasicsDatas(){
        ResultData resultData=new ResultData();
        DeclarationBasicDataVO vo=new DeclarationBasicDataVO();
        String url=udbUrl+"/sublist";
        Map<String,String> portmap=new HashMap<>();
        portmap.put("webSite","oilbank");
        portmap.put("categoryItem","port");
        UDBDatas portdatas= HttpHelper.GetService(url,portmap, UDBDatas.class);
        if(portdatas!=null){
           vo.setPort(portdatas.getResponse());
        }

        Map<String,String> countrymap=new HashMap<>();
        countrymap.put("webSite","oilbank");
        countrymap.put("categoryItem","country");
        UDBDatas countrydatas= HttpHelper.GetService(url,countrymap, UDBDatas.class);
        if(countrydatas!=null){
            vo.setCountry(countrydatas.getResponse());
        }

        Map<String,String> entrytypemap=new HashMap<>();
        entrytypemap.put("webSite","oilbank");
        entrytypemap.put("categoryItem","entrytype");
        UDBDatas entrytypedatas= HttpHelper.GetService(url,entrytypemap, UDBDatas.class);
        if(entrytypedatas!=null){
            vo.setEntrytype(entrytypedatas.getResponse());
        }

        Map<String,String> trademodemap=new HashMap<>();
        trademodemap.put("webSite","oilbank");
        trademodemap.put("categoryItem","trademode");
        UDBDatas trademodedatas= HttpHelper.GetService(url,trademodemap, UDBDatas.class);
        if(trademodedatas!=null){
            vo.setTrademode(trademodedatas.getResponse());
        }

        Map<String,String> transportationmap=new HashMap<>();
        transportationmap.put("webSite","oilbank");
        transportationmap.put("categoryItem","transportation");
        UDBDatas transportationdatas= HttpHelper.GetService(url,transportationmap, UDBDatas.class);
        if(transportationdatas!=null){
            vo.setTransportation(transportationdatas.getResponse());
        }

        Map<String,String> packInfomap=new HashMap<>();
        packInfomap.put("webSite","oilbank");
        packInfomap.put("categoryItem","packInfo");
        UDBDatas packInfodatas= HttpHelper.GetService(url,packInfomap, UDBDatas.class);
        if(packInfodatas!=null){
            vo.setPackInfo(packInfodatas.getResponse());
        }

        Map<String,String> cutmodemap=new HashMap<>();
        cutmodemap.put("webSite","oilbank");
        cutmodemap.put("categoryItem","cutmode");
        UDBDatas cutmodedatas= HttpHelper.GetService(url,cutmodemap, UDBDatas.class);
        if(cutmodedatas!=null){
            vo.setCutmode(cutmodedatas.getResponse());
        }

        Map<String,String> transmodemap=new HashMap<>();
        transmodemap.put("webSite","oilbank");
        transmodemap.put("categoryItem","transmode");
        UDBDatas transmodedatas= HttpHelper.GetService(url,transmodemap, UDBDatas.class);
        if(transmodedatas!=null){
            vo.setTransmode(transmodedatas.getResponse());
        }

        Map<String,String> quarantinePortmap=new HashMap<>();
        quarantinePortmap.put("webSite","oilbank");
        quarantinePortmap.put("categoryItem","quarantinePort");
        UDBDatas quarantinePortdatas= HttpHelper.GetService(url,quarantinePortmap, UDBDatas.class);
        if(quarantinePortdatas!=null){
            vo.setQuarantinePort(quarantinePortdatas.getResponse());
        }

        Map<String,String> tradecurrmap=new HashMap<>();
        tradecurrmap.put("webSite","oilbank");
        tradecurrmap.put("categoryItem","tradecurr");
        UDBDatas tradecurrdatas= HttpHelper.GetService(url,tradecurrmap, UDBDatas.class);
        if(tradecurrdatas!=null){
            vo.setTradecurr(tradecurrdatas.getResponse());
        }

        Map<String,String> gunitmap=new HashMap<>();
        gunitmap.put("webSite","oilbank");
        gunitmap.put("categoryItem","gunit");
        UDBDatas gunitdatas= HttpHelper.GetService(url,gunitmap, UDBDatas.class);
        if(gunitdatas!=null){
            vo.setGunit(gunitdatas.getResponse());
        }

        Map<String,String> destinationmap=new HashMap<>();
        destinationmap.put("webSite","oilbank");
        destinationmap.put("categoryItem","destination");
        UDBDatas destinationdatas= HttpHelper.GetService(url,destinationmap, UDBDatas.class);
        if(destinationdatas!=null){
            vo.setDestination(destinationdatas.getResponse());
        }

        Map<String,String> documentTypemap=new HashMap<>();
        documentTypemap.put("webSite","oilbank");
        documentTypemap.put("categoryItem","documentType");
        UDBDatas documentTypedatas= HttpHelper.GetService(url,documentTypemap, UDBDatas.class);
        if(documentTypedatas!=null){
            vo.setDocumentType(documentTypedatas.getResponse());
        }

        Map<String,String> voucherTypemap=new HashMap<>();
        voucherTypemap.put("webSite","oilbank");
        voucherTypemap.put("categoryItem","voucherType");
        UDBDatas voucherTypedatas= HttpHelper.GetService(url,voucherTypemap, UDBDatas.class);
        if(voucherTypedatas!=null){
            vo.setVoucherType(voucherTypedatas.getResponse());
        }

        Map<String,String> businessMattersmap=new HashMap<>();
        businessMattersmap.put("webSite","oilbank");
        businessMattersmap.put("categoryItem","businessMatters");
        UDBDatas businessMattersdatas= HttpHelper.GetService(url,businessMattersmap, UDBDatas.class);
        if(voucherTypedatas!=null){
            vo.setBusinessMatters(businessMattersdatas.getResponse());
        }




        resultData.setDatas(vo);

        return  resultData;
    }
}
