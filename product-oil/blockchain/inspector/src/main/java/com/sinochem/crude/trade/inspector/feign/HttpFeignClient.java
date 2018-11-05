package com.sinochem.crude.trade.inspector.feign;

import com.sinochem.crude.trade.inspector.domain.*;
import com.sinochem.crude.trade.inspector.domain.vo.TCommonAttachmentsListVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "httpFeignClient", url = "${feignUrl}")
public interface HttpFeignClient {

    @RequestMapping(value = "/pages/backgroundom/my_contract/testHttpAPI.json")
    SaleSheet testHttpAPI(@RequestBody SaleSheet saleSheetQuery);

    @RequestMapping(value = "/blockchain/inspector/declaration/testApi.json")
    String testApi();

    @RequestMapping(value =  "/blockchain/getWebDemainByUserId.json")
    String getWebDemainByUserId(@RequestBody String userId);

    @RequestMapping(value =  "/blockchain/getLoginDataByUrl.json")
    TDataPartner getLoginDataByUrl(@RequestBody String url);

    /**
     * 商检报告详情查询
     * @param reportInfoQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/report/getinfo.json")
    TInspectorPresentation getinfo(@RequestBody ReportInfoQuery reportInfoQuery);

    /**
     * 商检报告列表查询
     * @param reportInfoQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/report/getlist.json")
    ResultDatas getList(@RequestBody ReportInfoQuery reportInfoQuery);

    /**
     * 商检报告新增
     * @param reportInfo
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/report/save.json")
    ResultDatas save(@RequestBody TInspectorPresentation reportInfo);

    /**
     * 商检报告更新
     * @param reportInfo
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/report/update.json")
    ResultDatas update(@RequestBody TInspectorPresentation reportInfo);


    /**
     * 商检任务查询
     * @param taskInfoQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/task/get.json")
    ResultDatas getTaskList(@RequestBody TaskInfoQuery taskInfoQuery);

    /**
     * 商检报告附件上传
     * @param list
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/BillsFile/insert.json")
    ResultDatas insertBillsFile(@RequestBody FileInfoVO list);

    /**
     * 商检报告附件查询
     * @param businessUuid
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/BillsFile/list.json")
    ResultDatas selectBillsFileList(String businessUuid);

    /**
     * 商检报告附件删除
     * @param fileInfoVO
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/BillsFile/delete.json")
    ResultDatas selectBillsFileDelete(@RequestBody FileInfoVO fileInfoVO);
    /**
     * 商检报交易信息查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/transaction/get.json")
    Map<String,Object> getTransactionInfo(String id);
    /**
     * 商检报告billno模糊查询下拉
     * @param reportInfoQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/inspector/report/getBillno.json")
    ResultDatas getReportBillNo(ReportInfoQuery reportInfoQuery);


}