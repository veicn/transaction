package com.sinochem.crude.trade.broker.controller;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.dao.TBrokerAppointMapper;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.crude.trade.broker.model.vo.ForwarderListVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerDeclarationQueryVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.crude.trade.broker.model.vo.TCommonAttachmentsListVO;
import com.sinochem.crude.trade.broker.service.impl.*;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/6 15:33
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/blockchain/broker/declaration")
public class DeclarationController {
    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    @Autowired
    private DeclarationService declarationService;
    @Autowired
    private TBrokeDangerinfoService tBrokeDangerinfoService;
    @Autowired
    private TBrokerBusinessmattersService tBrokerBusinessmattersService;
    @Autowired
    private TBrokerDocumentsService tBrokerDocumentsService;
    @Autowired
    private TBrokerOthermattersService tBrokerOthermattersService;
    @Autowired
    private TBrokerOtherpackinfoService tBrokerOtherpackinfoService;
    @Autowired
    private TCommonAttachmentsService tCommonAttachmentsService;
    @Autowired
    private TBrokerCipService tBrokerCipService;
    @Autowired
    private TBrokerProductspecificationService tBrokerProductspecificationService;
    @Autowired
    private ForwarderService forwarderService;
    @Autowired
    private TTransExtendService tTransExtendService;
    @Autowired
    private TBrokerGoodsService tBrokerGoodsService;


    Gson gson = new Gson();


    @WithoutAccess
    @RequestMapping(value = "/delete.json")
    @ResponseBody
    public Result delDeclaration(@RequestBody TBrokerDeclaration declaration) {
        Result result = new Result();
        try {
            if (declaration == null || StringHelper.isNullOrEmptyString(declaration.getUuid())) {
                result.setFail("UUID 不可以为空");
                return result;
            }
            int num = declarationService.deleteByUuid(declaration.getUuid());
            if (num <= 0) {
                result.setFail("删除失败");
            }
        } catch (Exception e) {
            logger.error("查询报关单详情失败", e);
            result.setFail("查询报关单详情失败" + e.getMessage());
//            result.setDatas(e.getMessage());
        }
        return result;
    }


    /**
     * 报关单查询列表
     *
     * @param
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/detail.json")
    public ResultDatas GetDeclarationDetail(String uuid) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            if (!StringHelper.isNullOrEmptyString(uuid)) {
                TBrokerDeclaration tBrokerDeclaration = declarationService.selectByUuid(uuid);
                if (tBrokerDeclaration != null) {
                    TBrokerVO vo = new TBrokerVO();
                    String tbjson = gson.toJson(tBrokerDeclaration);
                    vo = gson.fromJson(tbjson, TBrokerVO.class);
                    if (vo != null && (!StringHelper.isNullOrEmptyString(vo.getUuid()))) {
                        vo.settBrokerDangerinfo(tBrokeDangerinfoService.selectByUuid(vo.getUuid()));
                        vo.settBrokerDocuments(tBrokerDocumentsService.selectByUuid(vo.getUuid()));
                        vo.settBrokerBusinessmatters(tBrokerBusinessmattersService.selectByUuid(vo.getUuid()));
                        vo.settBrokerOthermatters(tBrokerOthermattersService.selectByUuid(vo.getUuid()));
                        vo.settBrokerOtherpackinfo(tBrokerOtherpackinfoService.selectByUuid(vo.getUuid()));
                        vo.settBrokerCips(tBrokerCipService.selectByUuid(vo.getUuid()));
                        vo.settBrokerProductspecification(tBrokerProductspecificationService.selectByUuid(vo.getUuid()));
                        vo.settBrokerVoucher(tCommonAttachmentsService.selectByBusinessUuid(vo.getUuid()));
                        vo.settBrokerGoods(tBrokerGoodsService.selectByUuid(vo.getUuid()));

                        //交易uuid
                        TTransExtend tTransExtend=tTransExtendService.selectByPurchaseContractNo(vo.getPurchaseContractNo());
                        if(tTransExtend!=null){
                            TBrokerAppoint tBrokerAppoint=  forwarderService.getTBrokerAppointByDealNo((tTransExtend.getDealNo()));
                            if(tBrokerAppoint!=null){
                              vo.setDealUuid(tBrokerAppoint.getDealUuid());
                            }
                        }


                    }
                    resultDatas.setDatas(vo);
                }
//            resultDatas.setDatas(declarationService.selectByUuid(uuid));
            } else {
                resultDatas.setFail("uuid不可以为空");
            }
        } catch (Exception e) {
            logger.error("查询报关单详情失败", e);
            resultDatas.setFail("查询报关单详情失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }

    @WithoutAccess
    @RequestMapping(value = "/list.json")
    @ResponseBody
    public ResultDatas GetDeclarationList(@RequestBody TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            Page<TBrokerDeclaration> list = declarationService.selectByQuery(tBrokerDeclarationQueryVO);
            resultDatas.setPageNum(list.getPageNum());
            resultDatas.setPageSize(list.getPageSize());
            resultDatas.setTotal(list.getTotal());
            resultDatas.setPageCount(list.getPages());
            resultDatas.setDatas(list);
        } catch (Exception e) {
            logger.error("查询报关单列表失败", e);
            resultDatas.setFail("查询报关单列表失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }

    @WithoutAccess
    @RequestMapping("/insert.json")
    @ResponseBody
    public ResultDatas insertDeclaration(@RequestBody TBrokerVO tBrokerVO) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            int num = declarationService.insertSelective(tBrokerVO);
            if (num <= 0) {
                resultDatas.setFail("新增失败");
            }
        } catch (Exception e) {
            logger.error("新增报关单失败", e);
            resultDatas.setFail("新增失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }

    @WithoutAccess
    @RequestMapping("/update.json")
    @ResponseBody
    public ResultDatas updateDeclaration(@RequestBody TBrokerVO tBrokerVO) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            if(tBrokerVO==null || StringHelper.isNullOrEmptyString(tBrokerVO.getUuid())){
                resultDatas.setFail("uuid 不可为空");
            }
            TBrokerDeclaration old= declarationService.selectByUuid(tBrokerVO.getUuid());
            if(old==null){
                resultDatas.setFail("该报关单不存在");
                return resultDatas;
            }

            int num = declarationService.updateByUuidSelective(tBrokerVO);
            if (num <= 0) {
                resultDatas.setFail("修改失败");
            }
        } catch (Exception e) {
            logger.error("修改报关单失败", e);
            resultDatas.setFail("修改失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }


    @WithoutAccess
    @RequestMapping(value = "/BillsFile/delete.json")
    @ResponseBody
    public Result delBillsFile(@RequestBody TCommonAttachments tCommonAttachments) {
        Result result = new Result();
        try {
            if (tCommonAttachments == null || StringHelper.isNullOrEmptyString(tCommonAttachments.getBusinessUuid())) {
                result.setFail("UUID 不可以为空");
                return result;
            }
            int num =tCommonAttachmentsService.deleteByBusinessUuid(tCommonAttachments.getBusinessUuid());
            if (num <= 0) {
                result.setFail("删除失败");
            }
        } catch (Exception e) {
            logger.error("删除报关单单据文件失败", e);
            result.setFail("删除报关单单据文件失败" + e.getMessage());
        }
        return result;
    }

    @WithoutAccess
    @RequestMapping(value = "/BillsFile/insert.json")
    @ResponseBody
    public Result insertBillsFile(@RequestBody TCommonAttachmentsListVO list) {
        Result result = new Result();
        try {
            if(list==null){
                result.setFail("参数错误");
                return  result;
            }
            for (TCommonAttachments tCommonAttachments:list.getList()
                 ) {
                tCommonAttachments.setCreateDate(new Date());
                tCommonAttachments.setChannel("BROKER");
                tCommonAttachments.setFileType("4");
                tCommonAttachments.setAliveFlag("1");
            }

            int num =tCommonAttachmentsService.insertList(list.getList());
//            int num =tCommonAttachmentsService.insert(list.getList().get(0));
            if (num <= 0) {
                result.setFail("上传失败");
            }
        } catch (Exception e) {
            logger.error("上传报关单单据文件失败", e);
            result.setFail("上传报关单单据文件失败" + e.getMessage());
        }
        return result;
    }

@WithoutAccess
@RequestMapping("/BillsFile/list.json")
    public ResultDatas selectBillsFile(String businessUuid){
    ResultDatas resultDatas=new ResultDatas();
    if(StringHelper.isNullOrEmptyString(businessUuid)){
        resultDatas.setFail("businessUuid 不可以为空");
        return  resultDatas;
    }
   resultDatas.setDatas(tCommonAttachmentsService.selectByBusinessUuid(businessUuid));

    return  resultDatas;
    }

}
