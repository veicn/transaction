package com.sinochem.crude.trade.broker.feign;

import com.sinochem.crude.trade.broker.domain.*;
import com.sinochem.crude.trade.broker.domain.VO.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.broker.domain.VO.TBrokerDeclarationQueryVO;
import com.sinochem.crude.trade.broker.domain.VO.TBrokerVO;
import com.sinochem.crude.trade.broker.domain.VO.TCommonAttachmentsListVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "httpFeignClient", url = "${feignUrl}")
public interface HttpFeignClient {

    @RequestMapping(value =  "/blockchain/getLoginDataByUrl.json")
    TDataPartner getLoginDataByUrl(@RequestBody String url);

    @RequestMapping(value =  "/blockchain/getWebDemainByUserId.json")
    String getWebDemainByUserId(@RequestBody String userId);

    @RequestMapping(value = "/pages/backgroundom/my_contract/testHttpAPI.json")
    SaleSheet testHttpAPI(@RequestBody SaleSheet saleSheetQuery);

    @RequestMapping(value = "/blockchain/broker/declaration/testApi.json")
    String testApi();

    @RequestMapping(value = "/blockchain/broker/forwarder/list.json")
    ResultDataPages forwarderlist(@RequestBody TBrokerAppointQueryVO tBrokerAppoint);

    @RequestMapping(value = "/blockchain/broker/forwarder/detail.json")
    ResultData forwarderdetail(@RequestParam("uuid") String uuid);

    @RequestMapping(value = "/blockchain/broker/declaration/delete.json")
    ResultData delDeclaration(@RequestBody TBrokerDeclaration uuid);

    @RequestMapping(value = "/blockchain/broker/declaration/detail.json")
    ResultData GetDeclarationDetail(@RequestParam("uuid") String uuid);

    @RequestMapping(value = "/blockchain/broker/declaration/list.json")
    ResultDataPages GetDeclarationList(@RequestBody TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO);

    @RequestMapping(value = "/blockchain/broker/declaration/insert.json")
    ResultData insertDeclaration(@RequestBody TBrokerVO tBrokerVO);

    @RequestMapping(value = "/blockchain/broker/declaration/update.json")
    ResultData updateDeclaration(@RequestBody TBrokerVO tBrokerVO);

    @RequestMapping(value = "/blockchain/broker/declaration/BillsFile/delete.json")
    ResultData delBillsFile(@RequestBody TCommonAttachments  tCommonAttachments);

    @RequestMapping(value = "/blockchain/broker/declaration/BillsFile/insert.json")
    ResultData insertBillsFile(@RequestBody TCommonAttachmentsListVO list);

    @RequestMapping(value = "/blockchain/broker/declaration/BillsFile/list.json")
    ResultData GetBillsFileList(@RequestParam("businessUuid") String businessUuid);
}