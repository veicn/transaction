package com.sinochem.crude.trade.customs.controllers;


import com.sinochem.crude.trade.customs.domain.*;
import com.sinochem.crude.trade.customs.feign.HttpFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 海关
 * @author wdh
 * @date 2018/10/08
 */
@RestController
public class CustomsHttpApiController {

    @Autowired
    HttpFeignClient httpFeignClient;

    @RequestMapping("/test")
    public String show(){

        return "Hello World";
    }


    @RequestMapping(value = "/testApi")
    public String testApi() {

        return httpFeignClient.testApi();
    }

    /**
     * 海关概况查询
     * @param deal_no
     * @return
     */
    @RequestMapping(value = "/customs/survey/get.json", method = RequestMethod.GET)
    public ResultDatas surveyGetInfo(@RequestParam("deal_no") String deal_no) {
        BlockchainInfoQuery tmp = new BlockchainInfoQuery();
        tmp.setDealNo("" + deal_no);
        return httpFeignClient.surveyGetInfo(tmp);
    }

    /**
     * 海关首页
     * @param homepage
     * @return
     */
    @RequestMapping(value = "/customs/infolist/get.json", method = RequestMethod.POST)
    public ResultDatas GetInfoList(@RequestBody HomePageQuery homepage) {
        return httpFeignClient.GetInfoList(homepage);
    }

    /**
     * 海关商检
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/inspector/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetInspectorInfo(@RequestParam("deal_no") String dealno) {
        return httpFeignClient.GetInspectorInfo(dealno);
    }

    /**
     * 海关报关
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/broker/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetBrokerInfo(@RequestParam("deal_no") String dealno) {
        Map<String, Object> map = httpFeignClient.GetBrokerInfo(dealno);
        return map;
    }

    /**
     * 海关收付汇
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/sfh/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetSFHInfo(@RequestParam("deal_no") String dealno) {
        Map<String, Object> map = httpFeignClient.GetSFHInfo(dealno);
        return map;
    }


    /**
     * 海关船代提单
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/shipagent/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetShipagentInfo(@RequestParam("deal_no") String dealno) {
        Map<String, Object> map = httpFeignClient.GetShipagentInfo(dealno);
        return map;
    }

    /**
     * 海关交易
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/transactioninfo/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetTransactioninfoInfo(@RequestParam("deal_no") String dealno) {
        Map<String, Object> map = httpFeignClient.GetTransactioninfoInfo(dealno);
        return map;
    }

    /**
     * 海关船代sof
     * @param dealno
     * @return
     */
    @RequestMapping(value = "/customs/shippingagentsof/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetShippingagentsofInfo(@RequestParam("deal_no") String dealno) {
        Map<String, Object> map = httpFeignClient.GetShippingagentsofInfo(dealno);
        return map;
    }

    /**
     * 海关区块链存证证书
     * @param id
     * @return
     */
    @RequestMapping(value = "/customs/blockchaincredentials/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetBlockChaincreDentials(@RequestParam("id") String id) {
        Map<String, Object> map = httpFeignClient.GetBlockChaincreDentials(id);
        return map;
    }

    /**
     * 海关验证
     * @param id
     * @return
     */
    @RequestMapping(value = "/customs/blockchainverify/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetBlockChainVerify(@RequestParam("id") String id) {
        return httpFeignClient.GetBlockChainVerify(id);
    }
}