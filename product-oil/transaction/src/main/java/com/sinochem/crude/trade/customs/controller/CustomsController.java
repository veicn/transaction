package com.sinochem.crude.trade.customs.controller;

//import com.sinochem.it.b2b.common.result.ResultDatas;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.customs.model.query.BlockchainInfoQuery;
import com.sinochem.crude.trade.customs.model.query.HomePageQuery;
import com.sinochem.crude.trade.customs.service.CustomsService;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 海关Controller
 */
@Controller
@RequestMapping("/blockchain/customs")
public class CustomsController {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomsController.class);

    @Autowired
    private CustomsService customsService;

    @Autowired
    private TCommonAttachmentsService tCommonAttachmentsService;


    /**商检 更新商检数据,提交商家数据*/
    private static String STR_INSPECTOR_TYPE = "1003,1004";

    /**报关 更新报关单信息,提交报关单信息*/
    private static String STR_BROKER_TYPE = "2003,2004";

    /**收付汇 更新数据,提交数据*/
    private static String STR_SFH_TYPE = "";

    /**船代提单 更新数据,提交数据*/
    private static String STR_SHIPAGENT_TYPE = "3003,3004";

    /**船代SOF 更新数据,提交数据*/
    private static String STR_SHIPAGENT_SOF_TYPE = "3007,3008";

    /**交易 更新数据,提交数据*/
    private static String STR_TRANSACTIONINFO_TYPE = "4007,4007";


    @WithoutAccess
    @RequestMapping(value = "/testApi.json")
    @ResponseBody
    public String testApi(){

        return "customs...";
    }


    /**
     * 海关概况查询
     *
     * @param blockchainInfoQuery
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/survey/get.json", method = RequestMethod.POST)
    public ResultDatas surveyGetInfo(@RequestBody BlockchainInfoQuery blockchainInfoQuery){

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try

        {
            //
            //            if (null == user) {
            //                //没有权限
            //                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            //                throw  bizException;
            //            }

            JSONObject jsonObject = new JSONObject();

            //根据当前用户查询当前用户所拥有的集合
            List<Map<String,Object>> page = customsService.surveyGetInfo(blockchainInfoQuery);
            res.setContent(page);
            res.setStatus(0);
            res.setMessage("海关概况查询成功");
        }catch(Exception e){
            res.setStatus(999);
            res.setMessage(e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关概况：/survey/get.json", e);
        }
        return res;
    }

    /**
     * 海关首页查询
     *
     * @param homePageQuery
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/infolist/get.json", method = RequestMethod.POST)
    public ResultDatas GetInfoList(@RequestBody HomePageQuery homePageQuery){

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try

        {
            //
            //            if (null == user) {
            //                //没有权限
            //                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            //                throw  bizException;
            //            }

            JSONObject jsonObject = new JSONObject();
            SimplePageInfo pageInfo = new SimplePageInfo();
            pageInfo.setPageNum(homePageQuery.getPageNum()!=null&&homePageQuery.getPageNum()!=0?homePageQuery.getPageNum(): 1);
            pageInfo.setPageSize(homePageQuery.getPageSize()!=null&&homePageQuery.getPageSize()!=0?homePageQuery.getPageSize(): 10);

            //根据当前用户查询当前用户所拥有的集合
            Page<Map<String, Object>> page = customsService.GetInfoList(homePageQuery, pageInfo);

            res.setDatas(page);
            res.setPageNum(page.getPageNum());
            res.setPageSize(page.getPageSize());
            res.setTotal(page.getTotal());
            res.setPageCount(page.getPages());
            res.setStatus(0);
            res.setMessage("海关首页查询成功");
        }catch(Exception e){
            res.setStatus(999);
            res.setMessage(e.getMessage());
            LOGGER.error("海关首页：/infolist/get.json", e);
            return res;
        }
        return res;
    }

    /**
     * 海关商检
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
        @RequestMapping(value = "/inspector/get.json", method = RequestMethod.GET)
    public Map<String, Object> GetInspectorInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object> res = new  HashMap<>();
        try{
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetInfo(deal_no,STR_INSPECTOR_TYPE);
            res.put("status",0);
            res.put("message","海关商检查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关商检/customs/inspector/get.json", e);
        }
        return res;
    }

    /**
     * 海关报关
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/broker/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetBrokerInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object>  res = new HashMap<>();
        try{
            //测试用
//            deal_no = "180926112313";
////            res = customsService.GetInfo(deal_no,"4007,4007");
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetInfo(deal_no,STR_BROKER_TYPE);
            res.put("status",0);
            res.put("message","海关报关查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关报关：/customs/broker/get.json", e);
        }
        return res;
    }

    /**
     * 海关收付汇
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/sfh/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetSFHInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object>  res = new HashMap<>();
        try{
            //测试用
//            deal_no = "180926112313";
////            res = customsService.GetInfo(deal_no,"4007,4007");
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetSFHInfo(deal_no);
            res.put("status",0);
            res.put("message","海关收付汇查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关收付汇：/customs/sfh/get.json", e);
        }
        return res;
    }

    /**
     * 海关船代提单
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/shipagent/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetShipagentInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object>  res = new HashMap<>();
        try{
            //测试用
//            deal_no = "180926112313";
////            res = customsService.GetInfo(deal_no,"4007,4007");
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetInfo(deal_no,STR_SHIPAGENT_TYPE);
            res.put("status",0);
            res.put("message","海关船代提单查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关船代提单:/customs/shipagent/get.json", e);
        }
        return res;
    }

    /**
     * 海关交易
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/transactioninfo/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetTransactioninfoInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object>  res = new HashMap<>();
        try{
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetInfo(deal_no,STR_TRANSACTIONINFO_TYPE);
            res.put("status",0);
            res.put("message","海关交易查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关交易/customs/transactioninfo/get.json", e);
        }
        return res;
    }

    /**
     * 海关船代提单
     *
     * @param deal_no
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/shippingagentsof/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetShippingagentsofInfo(@RequestParam("deal_no") String deal_no){
        Map<String, Object>  res = new HashMap<>();
        try{
            //测试用
//            deal_no = "180926112313";
////            res = customsService.GetInfo(deal_no,"4007,4007");
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetInfo(deal_no,STR_SHIPAGENT_SOF_TYPE);
            res.put("status",0);
            res.put("message","船代sof查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("海关船代SOF:/customs/shipagent/get.json", e);
        }
        return res;
    }

    /**
     * 海关区块链存证证书
     *
     * @param id
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/blockchaincredentials/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetBlockChaincredentials(@RequestParam("id") String id){
        Map<String, Object>  res = new HashMap<>();
        try{
            //测试用
//            deal_no = "180926112313";
////            res = customsService.GetInfo(deal_no,"4007,4007");
            //根据当前用户查询当前用户所拥有的集合
            res = customsService.GetBlockChain(id);
            res.put("status",0);
            res.put("message","海关区块链存证证书查询成功");
        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("查询区块链信息:/customs/shipagent/get.json", e);
        }
        return res;
    }

    /**
     * 海关区块链验证
     * @param id
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "/blockchainverify/get.json", method = RequestMethod.GET)
    public Map<String, Object>  GetBlockChainVerify(@RequestParam("id") String id){
            Map<String, Object>  res = new HashMap<>();
        try{
            //目前没提供验证只是假验证
            res.put("status",0);
            res.put("message","成功");

        }catch(Exception e){
            res.put("status",999);
            res.put("message",e.getMessage());
            //            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            LOGGER.error("验证区块链信息:/customs/shipagent/get.json", e);
        }
        return res;
    }
}
