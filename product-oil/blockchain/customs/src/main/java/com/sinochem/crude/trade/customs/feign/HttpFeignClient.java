package com.sinochem.crude.trade.customs.feign;


import com.sinochem.crude.trade.customs.domain.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "httpFeignClient", url = "${feignUrl}")
public interface HttpFeignClient {

    @RequestMapping(value = "/blockchain/customs/declaration/testApi.json")
    String testApi();

    @RequestMapping(value =  "/blockchain/getWebDemainByUserId.json")
    String getWebDemainByUserId(@RequestBody String userId);

    @RequestMapping(value =  "/blockchain/getLoginDataByUrl.json")
    TDataPartner getLoginDataByUrl(@RequestBody String url);

    /**
     * 海关概况
     * @param tBlockchainEventRecord
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/survey/get.json")
    ResultDatas surveyGetInfo(@RequestBody BlockchainInfoQuery tBlockchainEventRecord);

    /**
     * 海关首页
     * @param homePageQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/infolist/get.json")
    ResultDatas GetInfoList(@RequestBody HomePageQuery homePageQuery);

    /**
     * 海关商检详情
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/inspector/get.json")
    Map<String, Object> GetInspectorInfo(@RequestParam("deal_no") String deal_no);
    /**
     * 海关商检详情
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/broker/get.json")
    Map<String, Object> GetBrokerInfo(@RequestParam("deal_no") String deal_no);

    /**
     * 海关收付汇
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/sfh/get.json")
    Map<String, Object> GetSFHInfo(@RequestParam("deal_no") String deal_no);
    /**
     * 海关船代提单
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/shipagent/get.json")
    Map<String, Object> GetShipagentInfo(@RequestParam("deal_no") String deal_no);
    /**
     * 海关交易
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/transactioninfo/get.json")
    Map<String, Object> GetTransactioninfoInfo(@RequestParam("deal_no") String deal_no);
    /**
     * 海关船代sof
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/shippingagentsof/get.json")
    Map<String, Object> GetShippingagentsofInfo(@RequestParam("deal_no") String deal_no);
    /**
     * 海关区块链
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/blockchaincredentials/get.json")
    Map<String, Object> GetBlockChaincreDentials(@RequestParam("id") String id);
    /**
     * 海关区块链验证
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/customs/blockchainverify/get.json")
    Map<String, Object> GetBlockChainVerify(@RequestParam("id") String id);

}