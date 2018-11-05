package com.sinochem.crude.trade.inspector.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainFile;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.broker.model.vo.TCommonAttachmentsListVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.inspector.constant.Constant;
import com.sinochem.crude.trade.inspector.enums.AppointEnum;
import com.sinochem.crude.trade.inspector.enums.OilSeedEnum;
import com.sinochem.crude.trade.inspector.model.query.ReportInfoQuery;
import com.sinochem.crude.trade.inspector.model.query.TaskInfoQuery;
import com.sinochem.crude.trade.inspector.service.ReportInfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ProductCategoryEnum;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商检Controller
 */
@Controller
    @RequestMapping("/blockchain/inspector")
public class InspectorController {
    private final Logger LOGGER = LoggerFactory.getLogger(InspectorController.class);

    @Autowired
    private ReportInfoService reportInfoService;

    @Autowired
    private TCommonAttachmentsService tCommonAttachmentsService;

    @WithoutAccess
    @RequestMapping(value = "/testApi.json")
    @ResponseBody
    public String testApi(){

        return "inspector...";
    }


    /**
     * 查询商检报告详情
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/report/getinfo.json", method = RequestMethod.POST)
    @ResponseBody
    public TInspectorPresentation getinfo(@RequestBody ReportInfoQuery reportInfoQuery){
        TInspectorPresentation info = new TInspectorPresentation();
        try{
            info = reportInfoService.queryReportInfoById(reportInfoQuery.getId());
        } catch (Exception e){
            LOGGER.error("查询获取商检报告详情",e);
            return null;
        }

        return info;

    }


    /**
     * 查询商检报告列表
     */
    @WithoutAccess
    @RequestMapping(value = "report/getlist.json" , method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas getlist(@RequestBody ReportInfoQuery reportInfoQuery){

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {
//
//            if (null == user) {
//                //没有权限
//                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
//                throw  bizException;
//            }

            JSONObject jsonObject = new JSONObject();
            SimplePageInfo pageInfo = reportInfoQuery.getPageInfo();

            //根据当前用户查询当前用户所拥有的集合
            Page<Map<String, Object>> page = reportInfoService.queryReportInfoList(reportInfoQuery,pageInfo);

            jsonObject.put("page",page);
            res.setDatas(page);
            res.setPageNum(page.getPageNum());
            res.setPageSize(page.getPageSize());
            res.setTotal(page.getTotal());
            res.setPageCount(page.getPages());
       }catch (Exception e) {
            res.setStatus(999);

            LOGGER.error("selectALLOrder",e);
        }
        return res;
    }

    /**
     * 商检报告暂存
     * @param reportInfo
     * @param user
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "report/save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas save(@RequestBody TInspectorPresentation reportInfo){
        ResultDatas result  = new ResultDatas();
        try{
            result = reportInfoService.insertRecord( reportInfo);
        } catch (Exception e){
            LOGGER.error("查询获取商检报告详情",e);
            result.setStatus(999);
            result.setMessage("商检任务保存失败，失败原因："+e.getMessage());
            return result;
        }
        return result;

    }


    /**
     * 商检报告提交
     * @param reportInfo
     * @param user
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "report/update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas update(@RequestBody TInspectorPresentation reportInfo) {
        ResultDatas result = new ResultDatas();
        try {
            result = reportInfoService.updateRecord(reportInfo);
        } catch (Exception e) {
            LOGGER.error("查询获取商检报告详情", e);
            result.setStatus(999);
            result.setMessage("商检任务保存失败，失败原因："+e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 商检任务查询
     * @param taskList
//     * @param user
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "task/get.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas taskGetList(@RequestBody TaskInfoQuery taskList/** ,List<ChmentInspectorVO> chmentsVOList ,CurrentUser user*/) {

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {
//
//            if (null == user) {
//                //没有权限
//                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
//                throw  bizException;
//            }

            SimplePageInfo pageInfo = taskList.getPageInfo();

            //根据当前用户查询当前用户所拥有的集合
            Page<Map<String, Object>> page = reportInfoService.queryTaskList(taskList,pageInfo);
            //查询枚举
            HashMap<String, DictionaryVO> rlsMap = new HashMap<>();

            for (AppointEnum agreementStatusEnums : AppointEnum.values()) {
                DictionaryVO dictionaryVO = new DictionaryVO(
                        agreementStatusEnums.getCode(),
                        agreementStatusEnums.getZhName(),
                        agreementStatusEnums.getEnName()
                );

                rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
            }
            res.setAppointEnum(rlsMap);

            rlsMap = new HashMap<>();
            for (OilSeedEnum oilSeedEnum : OilSeedEnum.values()) {
                DictionaryVO dictionaryVO = new DictionaryVO(
                        oilSeedEnum.getCode(),
                        oilSeedEnum.getZhName(),
                        oilSeedEnum.getEnName()
                );

                rlsMap.put(dictionaryVO.getCode(), dictionaryVO);
            }
            res.setProductCategoryEnum(rlsMap);
            res.setDatas(page);
            res.setPageNum(page.getPageNum());
            res.setPageSize(page.getPageSize());
            res.setTotal(page.getTotal());
            res.setPageCount(page.getPages());
        }catch (Exception e) {
            res.setStatus(999);
            LOGGER.error("task/get.json",e);
        }
        return res;
    }

    /**
     * 商检报告附件上传
     * @param fileInfoVO
     * @param record
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/BillsFile/insert.json" , method = RequestMethod.POST )
    @ResponseBody
    public ResultDatas upload(@RequestBody FileInfoVO fileInfoVO ){
        ResultDatas resultData =  new ResultDatas();
        try {
            //封装附件
            TCommonAttachments attachments = new TCommonAttachments();
            attachments.setChannel(TCommonAttachments.CHANNEL_INSPECTOR);
            attachments.setAliveFlag(Constants.ALIVE_FLAG_YES);
            attachments.setFileType(TCommonAttachments.FILETYPE_INSPECTOR);
            attachments.setPath(fileInfoVO.getPath());
            attachments.setName(fileInfoVO.getOriginalName());
            attachments.setBusinessUuid(fileInfoVO.getBusinessUuid());
            attachments.setCreateDate(new Date());
            attachments.setCreateUser(fileInfoVO.getCreateUser());
            attachments.setUpdateDate(new Date());
            attachments.setUpdateUser(fileInfoVO.getCreateUser());
            //TODO: business_uuid  create_user 需要clinet传入

            //上链数据 封装POST_DATA
            String fileName = fileInfoVO.getOriginalName();
            BlockChainFile blockChainFile=new BlockChainFile();
            blockChainFile.setFileType(fileInfoVO.getSuffix());
            blockChainFile.setFileSummary(fileInfoVO.getFileSHA());
            blockChainFile.setCreateTime( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            blockChainFile.setDataCopyright("SGS");
            blockChainFile.setFileName(fileName.substring(0,fileName.lastIndexOf(".")).toUpperCase());
            blockChainFile.setFilePath(fileInfoVO.getPath());
            blockChainFile.setFileSize(fileInfoVO.getSize());
            blockChainFile.setEventCode(BlockchainEventEnums.E1002.getCode());

            TBlockchainEventRecord record = new TBlockchainEventRecord();
            record.setCreateUser(fileInfoVO.getCreateUser());
//            record.setBusiId(fileInfoVO.getId());
            record.setDealNo(fileInfoVO.getDeal_no());
            record.setEventCode(BlockchainEventEnums.E1002.getCode());
            record.setPostData(JSONObject.toJSONString(blockChainFile));
            record.setDescription("商检上传附件");

            resultData.setDatas(tCommonAttachmentsService.insert(attachments , record));


        }catch (Exception e){
            LOGGER.error("商检上传附件失败", e);
            resultData.setStatus(Result.ERROR );
            resultData.setMessage(e.getMessage());
            return resultData;
        }
        return resultData;
    }

    /**
     * 商检报告列表附件查看
     * @param businessUuid
     * @return
     */
    @WithoutAccess
    @RequestMapping("/BillsFile/list.json")
    @ResponseBody
    public ResultDatas selectBillsFile(@RequestBody String businessUuid){
        ResultDatas resultDatas=new ResultDatas();
        if(StringHelper.isNullOrEmptyString(businessUuid)){
            resultDatas.setFail("businessUuid 不可以为空");
            return  resultDatas;
        }
        resultDatas.setDatas(tCommonAttachmentsService.selectByBusinessUuid(businessUuid));

        return  resultDatas;
    }


    /**
     * 商检报告附件删除
     * @param fileInfoVO
     * @param record
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/BillsFile/delete.json")
    @ResponseBody
    public Map<String,Object> deleteBillsFile(@RequestBody FileInfoVO fileInfoVO) {
        Map<String,Object> res  =  new HashMap<>();
        try {
            if(fileInfoVO.getId()==null){
                res.put("code",999);
                res.put("message","id 不可以为空");
                return  res;
            }
            int num =tCommonAttachmentsService.deleteByPrimaryKey(fileInfoVO ,BlockchainEventEnums.E1002.getCode());
            if (num <= 0) {
                res.put("code",999);
                res.put("message","刪除失败");
                return res;
            }
            res.put("code",1);
            res.put("message","删除成功");
        } catch (Exception e) {
            LOGGER.error("上传报关单单据文件失败", e);
            res.put("code",999);
            res.put("message","上传报关单单据文件失败" + e.getMessage());
            return res;
        }
        return res;
    }

    /**
     * 商检交易信息查询
     * @param id
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/transaction/get.json")
    @ResponseBody
    public Map<String,Object> getTransactionInfo(@RequestBody String id) {

        Map<String, Object> map = new HashMap<>();
        BizException bizException = new BizException();
        try {
            //交易信息
            map = reportInfoService.getTransactionInfo(id);
        }catch (Exception e) {
            map.put("status",999);
            map.put("message","transaction/get.json商检交易查询接口调用失败，失败信息：" + e.getMessage());
            LOGGER.error("transaction/get.json接口调用失败",e);
        }
        return map;
    }

    /**
     * 商检报告billno模糊查询下拉
     * @param billno
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/report/getBillno.json")
    @ResponseBody
    ResultDatas getReportBillNo(@RequestBody ReportInfoQuery reportInfoQuery){

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {
//
//            if (null == user) {
//                //没有权限
//                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
//                throw  bizException;
//            }

            JSONObject jsonObject = new JSONObject();
            SimplePageInfo pageInfo = reportInfoQuery.getPageInfo();

            //根据当前用户查询当前用户所拥有的集合
            Map<String, Object> datas = reportInfoService.getReportBillNo(reportInfoQuery);

            res.setDatas(datas);
        }catch (Exception e) {
            res.setStatus(999);
            LOGGER.error("getbillno",e.getMessage());
            res.setMessage(e.getMessage());
            return res;
        }
        res.setStatus(0);
        res.setMessage("商检报告billno模糊查询下拉成功");
        return res;
    }
}
