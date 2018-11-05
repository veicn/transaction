package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.remote.ConfirmationSheetRemoteService;
import com.sinochem.crude.trade.shipping.remote.VoyageStartRemoteService;
import com.sinochem.crude.trade.shipping.vo.ConfirmationSheetRemoteVO;
import com.sinochem.crude.trade.shipping.vo.VoyageStartRemoteVO;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.Message;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.BillAllUploadVO;
import com.sinochem.crude.trade.transaction.model.vo.BillCoreUploadVo;
import com.sinochem.crude.trade.transaction.service.BillCoreUploadService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.ProductInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** 票据中心controoler
 * Created by z1761 on 2018/5/4.
 * zhaoyulong
 */

@Controller
@RequestMapping(UrlMapping.BILL_CORE_UPLOAD)
public class BillCoreUploadController extends CommonOssController{
    @Autowired
    private ExceptionHelper exceptionHelper;
    @Autowired
    private BillCoreUploadService _billCoreUploadService;
    @Autowired
    private ContractSheetService contractSheetService; //订单接口

    @Autowired
    private ProductInfoService productInfoService;//商品接口

    @Autowired
    private VoyageStartRemoteService voyageStartRemoteService; //DI
    @Autowired
    private ConfirmationSheetRemoteService confirmationSheetRemoteService;// Q88 确认单

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long idquanzhou;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;//上传云地址

    private final Logger LOGGER = LoggerFactory.getLogger(BillCoreUploadController.class);


    /**
     * 根据订单uuid 查询单据列表
     */
    @RequestMapping(UrlMapping.INDEX)
    public void selectUpload(String uuid, CurrentUser user, ModelMap map){
        BizException bizException = new BizException();

        try {
            if (null == user || StringUtil.isBlank(uuid)) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }

            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(user, uuid);
            ProductInfo productInfoById = productInfoService.getProductInfoById(user, contractSheetByUuid.getProductInfoId());//获取CFR FOB
            List<BillCoreUpload> billupload= _billCoreUploadService.findbyOrderIdList(contractSheetByUuid.getId());
            ArrayList<BillCoreUploadVo> billCoreUploadVos = new ArrayList<BillCoreUploadVo>();
            if (!billupload.isEmpty()) {
                for (BillCoreUpload billCoreUpload: billupload) {
                    if (null != billCoreUpload) {
                        BillCoreUploadVo billCoreUploadVo = new BillCoreUploadVo();
                        BillCoreUploadVo billCoreUploadVo1 = billCoreUploadVo.domainToVo(billCoreUpload);

                        billCoreUploadVos.add(billCoreUploadVo1);
                    }
                }
            }
            map.put("quanzhouId",idquanzhou);
            map.put("userId",user.getEpMemberId());
            map.put("tradeCode",productInfoById.getTradeTermCode());
            map.put("contractSheet",contractSheetByUuid);
            map.put("billList",billCoreUploadVos);
        } catch (BizException e) {
            e.printStackTrace();
            int exceptionCode = e.getCode();
            map.addAttribute(Message.ERROR, exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            map.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }

    }

/*
OM端通过订单uuid 查询单据列表
 */
@WithoutAccess
    @RequestMapping("uploadSelect.json")
    public ResultDatas OMselectUpload(String contractUUid){
        BizException bizException = new BizException();
       ResultDatas resultDatas=new ResultDatas();
        try {
            if ( StringUtil.isBlank(contractUUid)) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }

            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(null, contractUUid);
           List<BillCoreUpload> billupload= _billCoreUploadService.findbyOrderIdList(contractSheetByUuid.getId());
            List<BillCoreUploadVo> billCoreUploadVos = new ArrayList<BillCoreUploadVo>();
            if (!billupload.isEmpty()) {
                for (BillCoreUpload billCoreUpload: billupload) {
                    if (null != billCoreUpload) {

                        if(Mark.SETTLE_INVOICE.equals(billCoreUpload.getFileTypeCode())||Mark.TEMP_INVOICE.equals(billCoreUpload.getFileTypeCode()) ) {
                            BillCoreUploadVo billCoreUploadVo = new BillCoreUploadVo();
                            BillCoreUploadVo billCoreUploadVo1 = billCoreUploadVo.domainToVo(billCoreUpload);
//                            if(!StringHelper.isNullOrEmptyString(billCoreUploadVo1.getFilePath())){
//                                billCoreUploadVo1.setFilePath(endpoint+"/"+billCoreUploadVo1.getFilePath());
//                            }
                            billCoreUploadVo1.setEndpoint(endpoint);
                            billCoreUploadVos.add(billCoreUploadVo1);
                        }
                    }
                }
            }
            resultDatas.setDatas(billCoreUploadVos);
        } catch (BizException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return  resultDatas;
    }

    // 04 05 07 08 00  多个上传 insert
    /**
     * 票据中心上传新增
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.UPLOAD_SAVE)
    @ResponseBody
    public Result uploadInsert(CurrentUser currentUser,BillCoreUploadVo vo) {
        Result result = new Result();
        BizException bizException = new BizException();
        try {
            if (null == currentUser || StringUtil.isBlank(vo.getContractUUid())) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }
            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(currentUser, vo.getContractUUid());

            switch (vo.getFileTypeCode()) {
                case Mark.CORE_FLAG_00:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.CORE_FLAG_04:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.CORE_FLAG_05:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.CORE_FLAG_07:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.CORE_FLAG_08:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.TEMP_INVOICE:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.SETTLE_INVOICE:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.CONTRACT_FILE:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.PURCHASE_CONTRACT_NO:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
                case Mark.AGENCY_AGREEMENT_NO:
                    result = this.uploadSave(currentUser, contractSheetByUuid.getId(), vo);
                    break;
            }
            if (null != result&&result.getStatus()==1) {
                result.setMessage(Mark.UPLOAD_SUCCESS);
            } else {
                result.setMessage(Mark.UPLOAD_ERROR);
            }
        } catch (BizException e) {
            result.setStatus(Mark.RESULT_DATA_ERROR);
            result.setMessage(Mark.UPLOAD_ERROR);


        }
        return result;
    }

    /**
     * 票据中心上传全量临时发票，结算发票
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.UPLOAD_SAVEALL)
    @Transactional
    public Result uploadAll(@RequestBody BillAllUploadVO billAllUploadVO){
        Result result = new Result();
        if ( StringUtil.isEmpty(billAllUploadVO.getContractUUid())||StringUtil.isEmpty(billAllUploadVO.getEpMemberId())) {
          result.setFail("参数不可为空",-1);
            return result;
        }
        Long epMemberId=Long.valueOf(billAllUploadVO.getEpMemberId());
        ContractSheet  contractSheetByUuid = null;
        try {

            contractSheetByUuid = contractSheetService.getContractSheetByUuid(null,billAllUploadVO.getContractUUid());

            if(contractSheetByUuid!=null){
              int  delnum = _billCoreUploadService.deleteByOrderid(contractSheetByUuid.getId());//删除原来的单据
                if(billAllUploadVO.getProInvoiceList()!=null) {
                    for (int i = 0; i < billAllUploadVO.getProInvoiceList().size(); i++) {
                        BillCoreUploadVo vo=  billAllUploadVO.getProInvoiceList().get(i);
                        vo.setFileTypeCode("10");
                        vo.setFileTypeName("TempInvoice");
                        uploadSave(epMemberId, contractSheetByUuid.getId(), vo);
                    }
                }
                if(billAllUploadVO.getSettleInvoiceList()!=null) {
                    for (int i = 0; i < billAllUploadVO.getSettleInvoiceList().size(); i++) {
                        BillCoreUploadVo vo=  billAllUploadVO.getSettleInvoiceList().get(i);
                        vo.setFileTypeCode("11");
                        vo.setFileTypeName("SettleInvoice");
                        uploadSave(epMemberId, contractSheetByUuid.getId(), vo);
                    }
                }
            }
        } catch (BizException e) {
            e.printStackTrace();
            result.setFail("保存失败");
        }
       return  result;

    }
    // 01 02 03 06 09 单个上传 覆盖更新
    /**RollbackFor = Exception.class
     * 票据中心修改
     */
    @RequestMapping(UrlMapping.UPLOAD_UPDATE)
    @ResponseBody
    public Result uploadUpdate(CurrentUser currentUser, BillCoreUploadVo vo) {
        BizException bizException = new BizException();
        Result result1 = new Result();
        try {
            if (null == currentUser || StringUtil.isBlank(vo.getContractUUid())) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }
            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(currentUser, vo.getContractUUid());
            //修改或新增单个表数据
            Result result = this.uploadUpdates(currentUser, contractSheetByUuid.getId(), vo);
            Integer  integer = null;
            // 成功调用dubbo 接口修改交易或订单的数据
            if (result != null) {
                if (Mark.STATRU_SUCCESS == result.getStatus()) switch (vo.getFileTypeCode()) {
                    case Mark.CORE_FLAG_01:


                        break;
                    case Mark.CORE_FLAG_02:
                        VoyageStartRemoteVO voyageStartRemoteVO = new VoyageStartRemoteVO();
                        voyageStartRemoteVO.setOrderId(contractSheetByUuid.getId());
                        voyageStartRemoteVO.setDi(vo.getFilePath());
                        voyageStartRemoteVO.setDiFileNm(vo.getFileName());
                        integer = voyageStartRemoteService.updateVoyageStartDiByOrderId(voyageStartRemoteVO);
                        break;
                    case Mark.CORE_FLAG_03:
                        VoyageStartRemoteVO vos = new VoyageStartRemoteVO();
                        vos.setOrderId(contractSheetByUuid.getId());
                        vos.setAccessory(vo.getFilePath());
                        vos.setAccessoryFileNm(vo.getFileName());
                        integer = voyageStartRemoteService.updateVoyageStartFileByOrderId(vos);
                        break;
                    case Mark.CORE_FLAG_06:
                        System.out.println(1);
                        break;
                    case Mark.CORE_FLAG_09:
                        ConfirmationSheetRemoteVO confirmationSheetRemoteVO = new ConfirmationSheetRemoteVO();
                        confirmationSheetRemoteVO.setOrderId(contractSheetByUuid.getId());
                        confirmationSheetRemoteVO.setUploadQ88(vo.getFilePath());
                        confirmationSheetRemoteVO.setUploadQ88FileNm(vo.getFileName());
                        integer = confirmationSheetRemoteService.updateConfirmationSheetFileByOrderId(confirmationSheetRemoteVO);
                        System.out.println(1);
                        break;
                }
                if (integer!=null && integer!=2) {
                    result1.setMessage(Mark.UPLOAD_SUCCESS);
                } else {
                    result.setMessage(Mark.UPLOAD_ERROR);
                }
            }
        } catch (BizException e) {
            e.printStackTrace();
        }
        return result1;
    }

/*
删除某个订单的全部上传结算发票，临时发票
 */
    private int uploadDeleteAll(String contractUUid)
    {
        int num=0;
        ContractSheet contractSheetByUuid = null;
        try {
            contractSheetByUuid = contractSheetService.getContractSheetByUuid(null,contractUUid);
        } catch (BizException e) {
            e.printStackTrace();
        }
        if(contractSheetByUuid!=null) {
             num = _billCoreUploadService.deleteByOrderid(contractSheetByUuid.getId());//删除原来的单据

        }
        return num;

    }

    /**
     * 删除方法 单个数据
     * @param currentUser
     * @param vo
     */
    //@Transactional(RollbackFor = Exception.class)
    @WithoutAccess
    @RequestMapping(value = UrlMapping.UPLOAD_DELETE,method = RequestMethod.POST)
    @ResponseBody
    public Result uploadDelete(CurrentUser currentUser, BillCoreUploadVo vo){
        BizException bizException = new BizException();
        Result result = new Result();

        try {
            if (null == currentUser || StringUtil.isBlank(vo.getContractUUid())) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }
            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(currentUser, vo.getContractUUid());

            boolean b = this.deleteUpload(vo,currentUser);
            //删除成功 否 删除失败
            VoyageStartRemoteVO voyageStartRemoteVO = new VoyageStartRemoteVO();
            voyageStartRemoteVO.setOrderId(contractSheetByUuid.getId());
            Integer integer = null;
            if (b) {
                //
                switch (vo.getFileTypeCode()) {
                    case Mark.CORE_FLAG_01:

                        break;
                    case Mark.CORE_FLAG_02:
                        integer = voyageStartRemoteService.updateVoyageStartDiByOrderId(voyageStartRemoteVO);
                        break;
                    case Mark.CORE_FLAG_03:
                        integer =voyageStartRemoteService.updateVoyageStartFileByOrderId(voyageStartRemoteVO);
                        break;
                    case Mark.CORE_FLAG_06:
                        System.out.println(1);
                        break;
                    case Mark.CORE_FLAG_09:
                        ConfirmationSheetRemoteVO confirmationSheetRemoteVO = new ConfirmationSheetRemoteVO();
                        confirmationSheetRemoteVO.setOrderId(contractSheetByUuid.getId());
                        integer = confirmationSheetRemoteService.updateConfirmationSheetFileByOrderId(confirmationSheetRemoteVO);
                        break;
                }
                if (integer!= null ) {
                    //成功
                    result.setMessage(Mark.DELETE_SUCCESSFULLY);
                } else {
                    //失败
                }

            } else {
                throw bizException;
            }
        } catch (BizException e) {

        }
        return result;
    }

    /**
     * 新增多个不覆盖的文件中心
     * @param epMemeberId
     * @param orderId
     *
     */
    private Result uploadSave(Long epMemeberId,Long orderId,BillCoreUploadVo vo) throws BizException{

        Result result = new Result();
        BillCoreUpload billCoreUpload = vo.voToDomain();
        billCoreUpload.setDocumentFileUuid(UUID.randomUUID().toString());
        billCoreUpload.setUploadPerson(epMemeberId);
        billCoreUpload.setOrderId(orderId);
        billCoreUpload.setAliveFlag(Mark.ALIVE);
        billCoreUpload.setUpdateUser(epMemeberId);
        billCoreUpload.setCreateUser(epMemeberId);
        CurrentUser currentUser=null;
        int code = _billCoreUploadService.insertRecord(billCoreUpload,currentUser);

        if (code>0) {
            result.setStatus(1);
        } else {
            result.setStatus(2);
        }
        return result;
    }

    /**
     * 新增多个不覆盖的文件中心
     * @param currentUser
     * @param orderId
     *
     */
    private Result uploadSave(CurrentUser currentUser,Long orderId,BillCoreUploadVo vo) throws BizException{

        Result result = new Result();
        BillCoreUpload billCoreUpload = vo.voToDomain();
        billCoreUpload.setDocumentFileUuid(UUID.randomUUID().toString());
        billCoreUpload.setUploadPerson(currentUser.getMemberId());
        billCoreUpload.setOrderId(orderId);
        billCoreUpload.setAliveFlag(Mark.ALIVE);
        billCoreUpload.setUpdateUser(currentUser.getMemberId());
        billCoreUpload.setCreateUser(currentUser.getMemberId());


        int code = _billCoreUploadService.insertRecord(billCoreUpload,currentUser);

        if (code>0) {
            result.setStatus(1);
        } else {
            result.setStatus(2);
        }
        return result;
    }


    /**
     * 新增或者修改 单个 修改就是覆盖
     * @param currentUser
     * @param orderId
     * @param vo
     * @return
     */
    private Result uploadUpdates(CurrentUser currentUser,Long orderId,BillCoreUploadVo vo){

        Result result = new Result();
        try {
            int code = 0;
            BillCoreUpload billCoreUpload = vo.voToDomain();
            //uuid不为空 更新  否则新增
            if (StringUtil.isNotBlank(vo.getDocumentFileUuid())) {
                billCoreUpload.setUploadPerson(currentUser.getMemberId());
                billCoreUpload.setUpdateUser(currentUser.getMemberId());
                 code = _billCoreUploadService.updateRecordByUuid(billCoreUpload);

            } else {
                billCoreUpload.setOrderId(orderId);
                billCoreUpload.setDocumentFileUuid(UUID.randomUUID().toString());
                billCoreUpload.setAliveFlag(Mark.ALIVE);
                billCoreUpload.setUpdateUser(currentUser.getMemberId());
                billCoreUpload.setCreateUser(currentUser.getMemberId());
                billCoreUpload.setUploadPerson(currentUser.getMemberId());
                 code = _billCoreUploadService.insertRecord(billCoreUpload,currentUser);
            }
            if (code>0) {

            } else {
                result.setStatus(2);
            }
        } catch (BizException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 删除
     * @param vo
     * @return
     */
    private boolean deleteUpload(BillCoreUploadVo vo,CurrentUser currentUser){
        BillCoreUpload billCoreUpload = vo.voToDomain();
        Integer src = null;
        try {
            /*boolean b = super.oosDeleteOject(vo.getFilePath());
            if (b) {
                src = _billCoreUploadService.deleteByUUid(vo.getDocumentFileUuid(),currentUser);
            }*/

            src = _billCoreUploadService.deleteByUUid(vo.getDocumentFileUuid(),currentUser);
        } catch (BizException e) {
            e.printStackTrace();
        }

        if (src != null && src>0) {
             return true;
         } else {
             return false;
         }
    }


}
