package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.ContractExtend;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.query.ContractSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.*;
import com.sinochem.crude.trade.transaction.service.BillCoreUploadService;
import com.sinochem.crude.trade.transaction.service.ContractExtendService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageUtils;
//import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/12 15:56
 * @Version: [v1.0] 后台OM订单管理
 */
@Controller
@RequestMapping(UrlMapping.BGOM_CONTRACT)
public class OMContractController {
    @Autowired
    ContractExtendService contractExtendService;
    private final Logger LOGGER = LoggerFactory.getLogger(MyContractListController.class);

    @Autowired
    private ContractSheetService contractSheetService;
    @Autowired
    URLBroker transactionServer;

    @Autowired
    ExceptionHelper exceptionHelper;

    @Autowired
    private BillCoreUploadService billCoreUploadService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private BillCoreUploadController billCoreUploadController;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;//上传云地址


    /*
  获取om订单列表页面下拉内容
   */
    @WithoutAccess
    @RequestMapping(value = "getallpulldowndata.json")
    public ResultDatas getAllPullDownData() {
        ResultDatas resultDatas = new ResultDatas();
        ContractSheetPullDownVO contractSheetPullDownVO = new ContractSheetPullDownVO();
        contractSheetPullDownVO.setCategory(new ArrayList<DictionaryVO>(dictionaryService.productCategoryMap().values()));
        contractSheetPullDownVO.setStatus(new ArrayList<DictionaryVO>(dictionaryService.contractSheetStatusMap().values()));
        contractSheetPullDownVO.setTradeTerms(new ArrayList<DictionaryVO>(dictionaryService.tradeTermMap().values()));
        contractSheetPullDownVO.setDischargePort(new ArrayList<DictionaryVO>(dictionaryService.dischargePortMap().values()));
        resultDatas.setDatas(contractSheetPullDownVO);
        return resultDatas;
    }


    /*
    获取om订单列表
     */
    @WithoutAccess
    @RequestMapping(value = "contractSheetList.json")
    public ResultDatas getContractSheetListByeEpmemberid(@RequestBody ContractSheetQueryVO queryvo) {
        ResultDatas resultDatas = new ResultDatas();
        SimplePageInfo simplePageInfo = new SimplePageInfo();
        simplePageInfo.setPageNum(queryvo.getPageNum());
        simplePageInfo.setPageSize(queryvo.getPageSize());
        ContractSheetWXVO wxvo = null;
        try {
            if (simplePageInfo == null) {
                simplePageInfo = new SimplePageInfo();
            }
            if (simplePageInfo.getPageNum() == null) {
                simplePageInfo.setPageNum(1);
            }
            if (simplePageInfo.getPageSize() == null) {
                simplePageInfo.setPageSize(9999);
            }
            if(!StringHelper.isNullOrEmptyString(queryvo.getGmtCreatedStartAsString())){

               queryvo.setGmtCreatedStartAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ChDateEn(queryvo.getGmtCreatedStartAsString()));
            }
            if(!StringHelper.isNullOrEmptyString(queryvo.getGmtCreatedEndAsString())){
                queryvo.setGmtCreatedEndAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ChDateEn(queryvo.getGmtCreatedEndAsString()));
            }


            List<ContractSheetWXVO> rlist = new ArrayList<ContractSheetWXVO>();
            Page<ContractSheet> list = contractSheetService.getOMContractSheetList(queryvo, simplePageInfo);
//            List<ContractSheet> list = contractSheetService.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());

            for (ContractSheet contractSheet : list) {
                ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
                ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
                if (contractSheetCombineVO != null) {
                    wxvo = new ContractSheetWXVO();
                    wxvo.setUuid(contractSheet.getUuid());
                    EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(contractSheet.getUserCreated());
                    if (enterpriseVo != null) {
                        wxvo.setCreateuser(enterpriseVo.getName());
                    }
                    String createstr=DateUtil.formatDateTime(contractSheet.getGmtCreated());
                    wxvo.setCreatetime(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateTimeCH(createstr));

                    ContractExtend contractExtend = contractExtendService.selectByContractId(contractSheet.getId());
                    if (contractExtend != null) { //结算量
                        if(contractExtend.getSettleAmt()!=null) {
                            wxvo.setSettleAmt(contractExtend.getSettleAmt().toString() + contractExtend.getAttr22());
                        }
                        if(contractExtend.getSettleNum()!=null) {
                            wxvo.setSettleNum(contractExtend.getSettleNum().toString() + contractExtend.getAttr21());
                        }
                        wxvo.setContractNO(contractExtend.getContractNo());
                        wxvo.setContractDate(contractExtend.getContractDate());
                    }
                    Map<String, Object> map1 = new HashMap();
                    map1.put("fileTypeCode", Mark.TEMP_INVOICE);
                    map1.put("orderId", contractSheet.getId());
                    Map<String, Object> map2 = new HashMap();
                    map2.put("fileTypeCode", Mark.SETTLE_INVOICE);
                    map2.put("orderId", contractSheet.getId());
                    List<Map<String, Object>> list1 = billCoreUploadService.queryRecords(map1);
                    List<Map<String, Object>> list2 = billCoreUploadService.queryRecords(map2);
                    if (list1.size() > 0 || list2.size() > 0||contractExtend!=null) {
                        wxvo.setUpInvoiceStatus("1");
                    }
                    else {
                        wxvo.setUpInvoiceStatus("0");
                    }



                    if (contractSheetCombineVO.getContractSheetVO() != null) {
                        wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                        if (contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO() != null) {
                            wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getZhName());
                        }
                    }
                    if (contractSheetCombineVO.getTransportInfoVO() != null) {
                        wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                        wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                        wxvo.setLaycanDateAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) +" - "+com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));
                        if (!StringHelper.isNullOrEmptyString(contractSheetCombineVO.getTransportInfoVO().getDischargePort())) {
                            String disport=contractSheetCombineVO.getTransportInfoVO().getDischargePort();
                            //遍历map中的值
                            for (DictionaryVO value : dictionaryService.dischargePortMap().values()) {
                                if(value.getEnName().equals(disport)){
                                    disport=value.getZhName();

                                }
                            }

                            wxvo.setDischargePort(disport);
                        }

                    }
                    if (contractSheetCombineVO.getPricingInfoVO() != null) {
                        String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                        pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getZhName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName();
                        wxvo.setPremiumsAndDiscounts(pdstr);
                        wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName());
                        if (contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO() != null) {
                            wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName());
                        }
                        if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO() != null) {
                            wxvo.setPricingBenchmark(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO().getZhName());
                        }

                        if (contractSheetCombineVO.getPricingInfoVO().getPricingPeriod() != null) {
                            wxvo.setPricingPeriod(contractSheetCombineVO.getPricingInfoVO().getPricingPeriod());
                        }
                    }
                    if (contractSheetCombineVO.getProductInfoVO() != null) {
                        wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getZhName());
                        wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getZhName());
                        String url = transactionServer.get("/product_images/" + contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getCode() + "/vertical.jpg").toString();
                        String quantity = contractSheetCombineVO.getProductInfoVO().getQuantityAsString();
                        if (contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO() != null) {
                            quantity += contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getZhName();
                        }
                        wxvo.setQuantity(quantity);

                        wxvo.setProductCategoryImg(url);
                        if (contractSheetCombineVO.getProductInfoVO().getTradeTermVO() != null) {
                            wxvo.setTradeTerm(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                        }
                        String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                        wxvo.setTolerance(tolerance);

                    }
                    if (contractSheetCombineVO.getOtherInfoVO() != null) {
                        wxvo.setOtherTerm(contractSheetCombineVO.getOtherInfoVO().getOtherTerm());
                    }

                    wxvo.setEndpoint(endpoint);
                    rlist.add(wxvo);
                }
            }

            resultDatas.setPageNum(list.getPageNum());
            resultDatas.setPageSize(list.getPageSize());
            resultDatas.setTotal(list.getTotal());
            resultDatas.setPageCount(list.getPages());
//            resultDatas.setMessage(String.valueOf(list.getTotal()));
            resultDatas.setDatas(rlist);
        } catch (BizException e) {
            e.printStackTrace();
        }
        return resultDatas;
    }
//    private final Logger LOGGER = LoggerFactory.getLogger(MyContractDetailController.class);
    /*
    修改上传单据
     */
//    @WithoutAccess
    @Transactional
    @RequestMapping(value = "/setcontractextend.json", method = RequestMethod.POST)
    public Result icontractextend(@RequestBody ContractExtendVO contractExtendVO, CurrentUser currentUser) {
        Result resultDatas = new Result();
        if (StringHelper.isNullOrEmptyString(contractExtendVO.getContractUUid())) {
            resultDatas.setFail("ContractUUid不允许为空", ExceptionEnum.NULL_PARAMETER.getCode());
        }
//        if (currentUser != null) {
//
//            Gson gson=new Gson();
//            LOGGER.info(gson.toJson(currentUser));
//            if(currentUser.getMemberId()!=null) {
//                contractExtendVO.setEpMemberId(currentUser.getMemberId().toString());
//                contractExtendVO.setCreateUser(currentUser.getMemberId().toString());
//            }
//            else if(currentUser.getEpMemberId()!=null) {
//                contractExtendVO.setEpMemberId(currentUser.getEpMemberId().toString());
//                contractExtendVO.setCreateUser(currentUser.getEpMemberId().toString());
//            }
//
//        }

        contractExtendVO.setEpMemberId("99999999");
        contractExtendVO.setCreateUser("99999999");

        if (StringHelper.isNullOrEmptyString(contractExtendVO.getCreateTime())) {
            contractExtendVO.setCreateTime(DateUtil.formatDateTime(new Date()));
        }
        //修改上传单据表
        BillAllUploadVO billAllUploadVO = new BillAllUploadVO();
        billAllUploadVO.setEpMemberId(contractExtendVO.getEpMemberId());
        billAllUploadVO.setContractUUid(contractExtendVO.getContractUUid());
        billAllUploadVO.setProInvoiceList(contractExtendVO.getProInvoiceList());
        billAllUploadVO.setSettleInvoiceList(contractExtendVO.getSettleInvoiceList());

        Result result = billCoreUploadController.uploadAll(billAllUploadVO);
        if (result.getStatus() == 0) {
            try {
                ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(null, billAllUploadVO.getContractUUid());
                if (contractSheetByUuid != null) {
                    contractExtendVO.setContractId(contractSheetByUuid.getId().toString());
                    int num = contractExtendService.alterContractExtend(contractExtendVO);//修改结算量表
                }
            } catch (BizException e) {
                e.printStackTrace();
            }
        } else {
            return result;
        }
        return resultDatas;
    }

    /*
    查看上传单据
     */
//    @WithoutAccess
    @RequestMapping(value = "/getcontractextendold.json", method = RequestMethod.GET)
    public ResultDatas selectcontractextend(String contractId) {
        ResultDatas resultDatas = new ResultDatas();
        if (StringHelper.isNullOrEmptyString(contractId)) {
            resultDatas.setFail("contractId不允许为空", ExceptionEnum.NULL_PARAMETER.getCode());
        }
        ContractExtend contractExtend = contractExtendService.selectByContractId(Long.valueOf(contractId));
        if (contractExtend != null) {
            ContractExtendVO vo = new ContractExtendVO(contractExtend);
            resultDatas.setDatas(vo);
        }
        return resultDatas;
    }

    /*
   查看上传单据+发票信息
    */
    @WithoutAccess
    @RequestMapping(value = "/getcontractextend.json", method = RequestMethod.GET)
    public ResultDatas selectcontractextendall(String contractUUid) {
        ResultDatas resultDatas = new ResultDatas();
        if (StringHelper.isNullOrEmptyString(contractUUid)) {
            resultDatas.setFail("contractUUid不允许为空", ExceptionEnum.NULL_PARAMETER.getCode());
        }
        try {
            ContractSheet contractSheetByUuid = contractSheetService.getContractSheetByUuid(null, contractUUid);
            if (contractSheetByUuid == null) {
                resultDatas.setFail("订单不存在", -1);
            }
            ContractExtendVO vo = null;
            ContractExtend contractExtend = contractExtendService.selectByContractId(contractSheetByUuid.getId());
            if (contractExtend != null) {
                vo = new ContractExtendVO(contractExtend);


            }
            com.sinochem.it.b2b.common.result.ResultDatas uprdata = billCoreUploadController.OMselectUpload(contractUUid);
            if (uprdata.getStatus() == 0 && uprdata.getDatas() != null) {
                List<BillCoreUploadVo> list = (List<BillCoreUploadVo>) uprdata.getDatas();
                if (list.size() > 0) {
                    if (vo == null) {
                        vo = new ContractExtendVO();
                    }
                    List<BillCoreUploadVo> proInvoiceList = new ArrayList<>();
                    List<BillCoreUploadVo> settleInvoiceList = new ArrayList<>();
                    BillCoreUploadVo billCoreUploadVo = null;
                    for (int i = 0; i < list.size(); i++) {
                        billCoreUploadVo = list.get(i);
                        if ("10".equals(billCoreUploadVo.getFileTypeCode())) {
                            proInvoiceList.add(billCoreUploadVo);
                        } else if ("11".equals(billCoreUploadVo.getFileTypeCode())) {
                            settleInvoiceList.add(billCoreUploadVo);
                        }
                    }
                    vo.setProInvoiceList(proInvoiceList);
                    vo.setSettleInvoiceList(settleInvoiceList);
                }
            }
            resultDatas.setDatas(vo);
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }
}
