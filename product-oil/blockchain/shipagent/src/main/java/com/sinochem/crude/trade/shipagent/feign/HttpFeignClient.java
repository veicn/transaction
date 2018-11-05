package com.sinochem.crude.trade.shipagent.feign;

import com.sinochem.crude.trade.shipagent.domain.*;
import com.sinochem.crude.trade.shipagent.domain.vo.BillCoreUploadVo;
import com.sinochem.crude.trade.shipagent.domain.vo.FileInfoVO;
import com.sinochem.crude.trade.shipagent.domain.vo.TShipagentAppointQueryVO;
import com.sinochem.crude.trade.shipagent.domain.vo.TShipagentDocumentQuery;
import com.sinochem.crude.trade.shipagent.utils.ResultDatas;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sun.security.timestamp.TSRequest;
import java.util.Map;


/**
 * @author songhaiqiang
 */
@FeignClient(value = "httpFeignClient", url = "${feignUrl}")
public interface HttpFeignClient {

    @RequestMapping(value = "/pages/backgroundom/my_contract/testHttpAPI.json")
    SaleSheet testHttpAPI(@RequestBody SaleSheet saleSheetQuery);

    @RequestMapping(value = "/blockchain/shipagent/declaration/testApi.json")
    String testApi();

    @RequestMapping(value =  "/blockchain/getWebDemainByUserId.json")
    String getWebDemainByUserId(@RequestBody String userId);

    @RequestMapping(value =  "/blockchain/getLoginDataByUrl.json")
    TDataPartner getLoginDataByUrl(@RequestBody String url);

    /** ======================SOF相关===============================**/
    /**
     * sof 暂存
     * @param sof
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/sof/save.json", method = RequestMethod.POST)
    ResultData sofSave(@RequestBody TShipagentSof sof);

    /**
     * sof 提交
     * @param sof
     * @return
     */
    @RequestMapping(value =  "/blockchain/shipagent/sof/commit.json", method = RequestMethod.POST)
    ResultData sofCommit(@RequestBody TShipagentSof sof);
    /**
     * sof 详情
     * @param id
     * @return
     */
    @RequestMapping(value =  "/blockchain/shipagent/sof/get.json", method = RequestMethod.POST)
    ResultData sofGet(@RequestBody Long id);


    /**
     * sof 更新
     * @param sof
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/sof/update.json", method = RequestMethod.POST)
    ResultData sofUpdate(@RequestBody TShipagentSof sof);

    /**
     * SOF明细详情 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/sof/detail/delete.json", method = RequestMethod.POST)
    ResultData sofDetailDelete(@RequestBody Long id);


    /**
     * sof明细模板
     * @param template
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/sof/detail/template/list.json", method = RequestMethod.POST)
    ResultData sofDetailTemplateList(@RequestBody TShipagentSofDetailTemplate template);


    /** ======================提单相关===============================**/


    /**
     * 提单 暂存
     * @param billLoading
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/bl/save.json", method = RequestMethod.POST)
    ResultData blSave(@RequestBody TShipagentBillLoading billLoading);

    /**
     * 提单 提交
     * @param billLoading
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/bl/commit.json", method = RequestMethod.POST)
    ResultData blCommit(@RequestBody TShipagentBillLoading billLoading);


    /**
     * 提单 明细
     * @param billLoading
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/bl/get.json", method = RequestMethod.POST)
    ResultData blGet(@RequestBody TShipagentBillLoading billLoading);


    /**
     * 提单 修改
     * @param billLoading
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/bl/update.json", method = RequestMethod.POST)
    ResultData blUpdate(@RequestBody TShipagentBillLoading billLoading);

    /**
     * 提单  删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/bl/delete.json", method = RequestMethod.POST)
    ResultData blDelete(@RequestBody Long id);


    /** ======================提单相关===============================**/

    /**
     * 单证 详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/documentation/get.json", method = RequestMethod.POST)
    ResultData dcget(@RequestBody Long id);


    /** ======================任务相关===============================**/

    /**
     * 任务详情
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/appointtask/get.json", method = RequestMethod.GET)
    ResultDatas taskGet(@RequestParam("uuid") String uuid);


    @RequestMapping(value = "/blockchain/shipagent/appointtask/getSimple.json", method = RequestMethod.GET)
    ResultData taskGetSimple(@RequestParam("taskId") Long taskId);
    /**
     * 任务列表
     * @param shipagentAppointQueryVO
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/appointtask/list.json", method = RequestMethod.POST)
    ResultData taskList(@RequestBody TShipagentAppointQueryVO shipagentAppointQueryVO);


    /** ======================单证相关===============================**/

    /**
     * 单证列表
     * @param tShipagentDocumentQuery
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/documentation/list.json", method = RequestMethod.POST)
    ResultData documentList(@RequestBody TShipagentDocumentQuery tShipagentDocumentQuery);


    /**
     * 单证详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/blockchain/shipagent/documentation/get.json", method = RequestMethod.POST)
    ResultData documentGet(@RequestBody Long id );


    /** ======================附件相关===============================**/
    @RequestMapping(value = "/blockchain/shipagent/sof/upload.json", method = RequestMethod.POST)
    ResultData sofUpload(@RequestBody FileInfoVO fileInfoVO );


    @RequestMapping(value = "/blockchain/shipagent/bl/upload.json", method = RequestMethod.POST)
    ResultData blUpload(@RequestBody FileInfoVO fileInfoVO);

    @RequestMapping(value = "/blockchain/shipagent/bl/file/delete.json", method = RequestMethod.POST)
    ResultData blFileDelete(@RequestBody TCommonAttachments attachments);

    @RequestMapping(value = "/blockchain/shipagent/sof/file/delete.json", method = RequestMethod.POST)
    ResultData sofFileDelete(@RequestBody TCommonAttachments attachments);

    @RequestMapping(value = "/blockchain/shipagent/sof/file/list.json", method = RequestMethod.POST)
	ResultData fileList(TShipagentDocument document);


}