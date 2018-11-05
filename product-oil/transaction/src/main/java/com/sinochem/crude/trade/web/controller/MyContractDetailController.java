package com.sinochem.crude.trade.web.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.blockchain.service.TDataPartnerService;
import com.sinochem.crude.trade.broker.service.impl.ForwarderService;
import com.sinochem.crude.trade.inspector.service.TInspectorAppointService;
import com.sinochem.crude.trade.inspector.service.impl.TInspectorAppointServiceImpl;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 成品油合约（订单）列表详情
 *
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_CONTRACT_DETAIL)
public class MyContractDetailController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyContractDetailController.class);
    private static final String buyer = "1";
    private static final String seller = "2";
    @Autowired
    ExceptionHelper exceptionHelper;
    @Autowired
    URLBroker transactionServer;
    @Autowired
    ContractSheetService contractSheetService;
    @Autowired
    TTransExtendService tTransExtendService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private AppointTaskService appointTaskService;
    @Autowired
    private ForwarderService forwarderService;
    @Autowired
    private TInspectorAppointService tInspectorAppointService;
    @Autowired
    private TDataPartnerService tDataPartnerService;


    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    @RequestMapping(UrlMapping.INDEX)
    public void index(CurrentUser currentUser, String uuid, ModelMap model) {

        try {

            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(currentUser, uuid);
            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(currentUser, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            model.addAttribute("contractSheetCombineVO", contractSheetCombineVO);
            String MemberType = "";
            if (null != contractSheet && contractSheet.getSalerId() == 100008253)
                model.addAttribute("agentEnterpriseVo", enterpriseService.getByMemberId(100008254L));

            if (currentUser.getEpMemberId().equals(contractSheetCombineVO.getContractSheetVO().getBuyerId())) {
                MemberType = buyer;
            }
            if (currentUser.getEpMemberId().equals(contractSheetCombineVO.getContractSheetVO().getSalerId()) || idQuanzhou.equals(currentUser.getEpMemberId())) {
                MemberType = seller;
            }
            model.addAttribute("MemberType", MemberType);
            model.addAttribute("userId", currentUser.getEpMemberId());


            String dealNo = contractSheet.getSerialNumber();
            TTransExtend tTransExtend = tTransExtendService.getTTransExtendByDealNo(currentUser, dealNo);
            model.addAttribute("tTransExtend", tTransExtend);


            model.addAttribute("brokerAppointList", tDataPartnerService.findListByRole("broker"));
            model.addAttribute("shipAppointList", tDataPartnerService.findListByRole("shipagent"));
            model.addAttribute("inspectorAppointList", tDataPartnerService.findListByRole("inspector"));

            String s="";
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }

    /**
     * 交易查询详情  微信API
     *
     * @param uuid
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "contractSheet.json")
    public ResultDatas<ContractSheetCombineVO> queryLoadingProgerss(String uuid) {
        ResultDatas<ContractSheetCombineVO> resultDatas = new ResultDatas<>();
        ContractSheet contractSheet = null;
        try {
            contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);
            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            /*if(contractSheetCombineVO!=null &&contractSheetCombineVO.getTransportInfoVO()!=null&& contractSheetCombineVO.getTransportInfoVO().getDischargePortVO()==null)
            {
                ShipPortVO shipPortVO=new ShipPortVO();
                contractSheetCombineVO.getTransportInfoVO().setDischargePortVO(shipPortVO);
            }*/

            resultDatas.setDatas(contractSheetCombineVO);
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }

    /**
     * 交易简短信息EN（通过uuid查询）  API
     *
     * @param
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "omContractSheetSummarilyEn.json")
    public ResultDatas<ContractSheetWXVO> getContractSheetListByeuuideEN(String uuid) {
        ResultDatas<ContractSheetWXVO> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);//.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());
            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            if (contractSheetCombineVO != null) {
                wxvo = new ContractSheetWXVO();
                wxvo.setUuid(contractSheet.getUuid());
                wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getEnName());
                if (contractSheetCombineVO.getProductInfoVO() != null) {
                    wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getEnName());
                    wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getEnName());
                    String quantity = contractSheetCombineVO.getProductInfoVO().getQuantityAsString();
                    if (contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO() != null) {
                        quantity += contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getEnName();
                    }
                    wxvo.setQuantity(quantity);
                    if (contractSheetCombineVO.getProductInfoVO().getTradeTermVO() != null) {
                        wxvo.setTradeTerm(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getEnName());
                    }
                    String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                    wxvo.setTolerance(tolerance);
                    wxvo.setQualityStandard(contractSheetCombineVO.getProductInfoVO().getQualityStandard());

                }
                if (contractSheetCombineVO.getPricingInfoVO() != null) {
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingPeriod() != null) {
                        wxvo.setPricingPeriod(contractSheetCombineVO.getPricingInfoVO().getPricingPeriod());
                    }
                    wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName());
                    String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                    pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getEnName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                    wxvo.setPremiumsAndDiscounts(pdstr);
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO() != null) {
                        wxvo.setPricingBenchmark(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO().getEnName());
                    }
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2() != null) {
                        wxvo.setPricingBenchmark2(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2().getEnName());
                        wxvo.setPricingFormula("(Benchmark+Benchmark2)/2+ Premium/Discount Currency/Unit");
                    } else {
                        wxvo.setPricingFormula("Benchmark+ Premium/Discount Currency/Unit");
                    }
                    wxvo.setQuantityDter(contractSheetCombineVO.getPricingInfoVO().getSettlementQuantity());

                    wxvo.setPaymentTerm(contractSheetCombineVO.getPricingInfoVO().getPaymentTerm());
                    wxvo.setLaw(contractSheetCombineVO.getPricingInfoVO().getLaw());
                    if (contractSheetCombineVO.getPricingInfoVO().getInspectionVO() != null) {
                        wxvo.setInspection(contractSheetCombineVO.getPricingInfoVO().getInspectionVO().getEnName());
                    }
                }
                String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                wxvo.setTolerance(tolerance);
                wxvo.setTradeTermCode(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getEnName());
                if (contractSheetCombineVO.getOtherInfoVO() != null) {
                    wxvo.setOtherTerm(contractSheetCombineVO.getOtherInfoVO().getOtherTerm());
                }
                if (contractSheetCombineVO.getTransportInfoVO() != null) {
                    wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                    wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                    wxvo.setLaycanDateAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));

                    if (contractSheetCombineVO.getTransportInfoVO().getTransportModeVO() != null) {
                        wxvo.setTransportMode(contractSheetCombineVO.getTransportInfoVO().getTransportModeVO().getEnName());
                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getDischargePort() != null) {
                        wxvo.setDischargeCountry(contractSheetCombineVO.getTransportInfoVO().getDischargeCountry());
                        wxvo.setDischargePort(contractSheetCombineVO.getTransportInfoVO().getDischargePort());
                    } else {
                        wxvo.setDischargeCountry("");
                        wxvo.setDischargePort("");

                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getLoadingPort() != null) {
                        wxvo.setLoadCountry(contractSheetCombineVO.getTransportInfoVO().getLoadingCountry());
                        wxvo.setLoadPort(contractSheetCombineVO.getTransportInfoVO().getLoadingPort());
                    } else {
                        wxvo.setLoadCountry("");
                        wxvo.setLoadPort("");
                    }

                    wxvo.setDemurrageRateNum(contractSheetCombineVO.getTransportInfoVO().getDemurrageRateNumAsString());
                    wxvo.setLaytimeAsString(contractSheetCombineVO.getTransportInfoVO().getLaytimeAsString());


                }
                if (contractSheetCombineVO.getBuyerInfoVO() != null) {
                    if(null!=contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId())
                        wxvo.setBuyerId(String.valueOf(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId()));
                    wxvo.setBuyerEmail(contractSheetCombineVO.getBuyerInfoVO().getEmail());
                    wxvo.setBuyerFax(contractSheetCombineVO.getBuyerInfoVO().getFax());
                    wxvo.setBuyerName(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseName());
                    wxvo.setBuyerAddress(contractSheetCombineVO.getBuyerInfoVO().getAddress());
                    wxvo.setBuyerTelephone(contractSheetCombineVO.getBuyerInfoVO().getTelephone());
                    wxvo.setBuyerTraderName(contractSheetCombineVO.getBuyerInfoVO().getTraderName());
                }
                if (contractSheetCombineVO.getSalerInfoVO() != null) {
                    if(null!=contractSheetCombineVO.getSalerInfoVO().getEnterpriseId())
                        wxvo.setSalerId(String.valueOf(contractSheetCombineVO.getSalerInfoVO().getEnterpriseId()));
                    wxvo.setSalerEmail(contractSheetCombineVO.getSalerInfoVO().getEmail());
                    wxvo.setSalerFax(contractSheetCombineVO.getSalerInfoVO().getFax());
                    wxvo.setSalerName(contractSheetCombineVO.getSalerInfoVO().getEnterpriseName());
                    wxvo.setSalerAddress(contractSheetCombineVO.getSalerInfoVO().getAddress());
                    wxvo.setSalerTelephone(contractSheetCombineVO.getSalerInfoVO().getTelephone());
                    wxvo.setSalerTraderName(contractSheetCombineVO.getSalerInfoVO().getTraderName());
                }
                resultDatas.setDatas(wxvo);
            }
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }
    /**
     * OM交易简短信息（通过uuid查询）  API
     *
     * @param
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "omContractSheetSummarily.json")
    public ResultDatas<ContractSheetWXVO> getContractSheetListByeuuid(String uuid) {
        ResultDatas<ContractSheetWXVO> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);//.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());
            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            if (contractSheetCombineVO != null) {
                wxvo = new ContractSheetWXVO();
                wxvo.setUuid(contractSheet.getUuid());
                wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getZhName());
                if (contractSheetCombineVO.getProductInfoVO() != null) {
                    wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getZhName());
                    wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getZhName());
                    String quantity = contractSheetCombineVO.getProductInfoVO().getQuantityAsString();
                    if (contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO() != null) {
                        quantity += contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getZhName();
                    }
                    wxvo.setQuantity(quantity);
                    if (contractSheetCombineVO.getProductInfoVO().getTradeTermVO() != null) {
                        wxvo.setTradeTerm(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                    }
                    String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                    wxvo.setTolerance(tolerance);
                    wxvo.setQualityStandard(contractSheetCombineVO.getProductInfoVO().getQualityStandard());

                }
                if (contractSheetCombineVO.getPricingInfoVO() != null) {
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingPeriod() != null) {
                        wxvo.setPricingPeriod(contractSheetCombineVO.getPricingInfoVO().getPricingPeriod());
                    }
                    wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName());
                    String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                    pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getZhName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                    wxvo.setPremiumsAndDiscounts(pdstr);
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO() != null) {
                        wxvo.setPricingBenchmark(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO().getZhName());
                    }
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2() != null) {
                        wxvo.setPricingBenchmark2(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2().getZhName());
                        wxvo.setPricingFormula("(Benchmark+Benchmark2)/2+ Premium/Discount Currency/Unit");
                    } else {
                        wxvo.setPricingFormula("Benchmark+ Premium/Discount Currency/Unit");
                    }
                    wxvo.setQuantityDter(contractSheetCombineVO.getPricingInfoVO().getSettlementQuantity());

                    wxvo.setPaymentTerm(contractSheetCombineVO.getPricingInfoVO().getPaymentTerm());
                    wxvo.setLaw(contractSheetCombineVO.getPricingInfoVO().getLaw());
                    if (contractSheetCombineVO.getPricingInfoVO().getInspectionVO() != null) {
                        wxvo.setInspection(contractSheetCombineVO.getPricingInfoVO().getInspectionVO().getZhName());
                    }
                }
                String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                wxvo.setTolerance(tolerance);
                wxvo.setTradeTermCode(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                if (contractSheetCombineVO.getOtherInfoVO() != null) {
                    wxvo.setOtherTerm(contractSheetCombineVO.getOtherInfoVO().getOtherTerm());
                }
                if (contractSheetCombineVO.getTransportInfoVO() != null) {
                    wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                    wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                    wxvo.setLaycanDateAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));

                    if (contractSheetCombineVO.getTransportInfoVO().getTransportModeVO() != null) {
                        wxvo.setTransportMode(contractSheetCombineVO.getTransportInfoVO().getTransportModeVO().getZhName());
                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getDischargePort() != null) {
                        wxvo.setDischargeCountry(contractSheetCombineVO.getTransportInfoVO().getDischargeCountry());
                        wxvo.setDischargePort(contractSheetCombineVO.getTransportInfoVO().getDischargePort());
                    } else {
                        wxvo.setDischargeCountry("");
                        wxvo.setDischargePort("");

                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getLoadingPort() != null) {
                        wxvo.setLoadCountry(contractSheetCombineVO.getTransportInfoVO().getLoadingCountry());
                        wxvo.setLoadPort(contractSheetCombineVO.getTransportInfoVO().getLoadingPort());
                    } else {
                        wxvo.setLoadCountry("");
                        wxvo.setLoadPort("");
                    }
                    wxvo.setDemurrageRateNum(contractSheetCombineVO.getTransportInfoVO().getDemurrageRateNumAsString());
                    wxvo.setLaytimeAsString(contractSheetCombineVO.getTransportInfoVO().getLaytimeAsString());


                }
                if (contractSheetCombineVO.getBuyerInfoVO() != null) {
                    if(null!=contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId())
                        wxvo.setBuyerId(String.valueOf(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId()));
                    wxvo.setBuyerEmail(contractSheetCombineVO.getBuyerInfoVO().getEmail());
                    wxvo.setBuyerFax(contractSheetCombineVO.getBuyerInfoVO().getFax());
                    wxvo.setBuyerName(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseName());
                    wxvo.setBuyerAddress(contractSheetCombineVO.getBuyerInfoVO().getAddress());
                    wxvo.setBuyerTelephone(contractSheetCombineVO.getBuyerInfoVO().getTelephone());
                    wxvo.setBuyerTraderName(contractSheetCombineVO.getBuyerInfoVO().getTraderName());
                }
                if (contractSheetCombineVO.getSalerInfoVO() != null) {
                    if(null!=contractSheetCombineVO.getSalerInfoVO().getEnterpriseId())
                        wxvo.setSalerId(String.valueOf(contractSheetCombineVO.getSalerInfoVO().getEnterpriseId()));
                    wxvo.setSalerEmail(contractSheetCombineVO.getSalerInfoVO().getEmail());
                    wxvo.setSalerFax(contractSheetCombineVO.getSalerInfoVO().getFax());
                    wxvo.setSalerName(contractSheetCombineVO.getSalerInfoVO().getEnterpriseName());
                    wxvo.setSalerAddress(contractSheetCombineVO.getSalerInfoVO().getAddress());
                    wxvo.setSalerTelephone(contractSheetCombineVO.getSalerInfoVO().getTelephone());
                    wxvo.setSalerTraderName(contractSheetCombineVO.getSalerInfoVO().getTraderName());
                }
                resultDatas.setDatas(wxvo);
            }
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }

    /**
     * 微信端交易简短信息（通过uuid查询）  微信API
     *
     * @param
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "contractSheetSummarily.json")
    public ResultDatas<ContractSheetWXVO> getContractSheetListByeEpmemberid(String uuid) {
        ResultDatas<ContractSheetWXVO> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
        try {
            List<ContractSheetWXVO> rlist = new ArrayList<ContractSheetWXVO>();
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);//.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());

            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            if (contractSheetCombineVO != null) {
                wxvo = new ContractSheetWXVO();
                wxvo.setUuid(contractSheet.getUuid());
                wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getEnName());
                wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getEnName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                wxvo.setPremiumsAndDiscounts(pdstr);
                wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getEnName());
                wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getEnName());
                String url = transactionServer.get("/product_images/" + contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getCode() + "/vertical.jpg").toString();
                wxvo.setQuantity(contractSheetCombineVO.getProductInfoVO().getQuantityAsString());


                wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName());
                wxvo.setTolerance(contractSheetCombineVO.getProductInfoVO().getTolerance());
                wxvo.setTradeTermCode(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getEnName());
                wxvo.setProductCategoryImg(url);
                resultDatas.setDatas(wxvo);
            }
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }


    @RequestMapping(UrlMapping.MY_CONTRACT_DETAIL_UPLOADONE)
    public void uploadFile(CurrentUser currentUser, String contractuuid, String contractfilepath, String contractfilename, ModelMap model) {

        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(currentUser, contractuuid);
            contractSheet.setCoqRefinery(contractfilepath);
            contractSheet.setCoqRefineryName(contractfilename);
            contractSheetService.updateContractSheetSelective(currentUser, contractSheet);
            Long id = contractSheet.getId();
            contractSheetService.saveContractSheetHistory(currentUser, id);
        } catch (BizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @RequestMapping(UrlMapping.MY_CONTRACT_DETAIL_UPLOADTWO)
    public void uploadFiletwo(CurrentUser currentUser, String contractuuid, String contractfilepath, String contractfilename, ModelMap model) {

        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(currentUser, contractuuid);
            contractSheet.setCoqShoreTanks(contractfilepath);
            contractSheet.setCoqShoreTanksName(contractfilename);
            contractSheetService.updateContractSheetSelective(currentUser, contractSheet);
            Long id = contractSheet.getId();
            contractSheetService.saveContractSheetHistory(currentUser, id);
        } catch (BizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(UrlMapping.MY_CONTRACT_DETAIL_UPLOADTHREE)
    public void uploadFilethree(CurrentUser currentUser, String contractuuid, String contractfilepath, String contractfilename, ModelMap model) {

        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(currentUser, contractuuid);
            contractSheet.setCiq(contractfilepath);
            contractSheet.setCiqName(contractfilename);
            contractSheetService.updateContractSheetSelective(currentUser, contractSheet);
            Long id = contractSheet.getId();
            contractSheetService.saveContractSheetHistory(currentUser, id);
        } catch (BizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(UrlMapping.MY_CONTRACT_DETAIL_UPLOADFOUR)
    public void uploadFilefour(CurrentUser currentUser, String contractuuid, String contractfilepath, String contractfilename, ModelMap model) {

        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(currentUser, contractuuid);
            contractSheet.setLoadingSurveyReport(contractfilepath);
            contractSheet.setLoadingSurveyReportName(contractfilename);
            contractSheetService.updateContractSheetSelective(currentUser, contractSheet);
            Long id = contractSheet.getId();
            contractSheetService.saveContractSheetHistory(currentUser, id);
        } catch (BizException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @WithoutAccess
    @RequestMapping(UrlMapping.MY_CONTRACT_DETAIL_APPOINT)
    @ResponseBody
    public Result contractAppoint(CurrentUser currentUser, String contractuuid, String appointType,
                                String appointEnterpriseId,String appointEnterpriseName,ModelMap model) {

        Result res = new Result();
        try {
            ModelAndView mav = new ModelAndView();
            contractSheetService.contractAppiont(currentUser,contractuuid,appointType,appointEnterpriseId,appointEnterpriseName);
            res.setStatus(Mark.RESULT_DATA_SUCCESS);
            res.setMessage(Mark.POST_OR_SAVE_SUCCESSFULLY);

        } catch (BizException e) {
            int exceptionCode = e.getCode();
            res.setStatus(exceptionCode);
            res.setMessage(exceptionHelper.getBizExceptionByCode(exceptionCode).getEnName());

        }catch (Exception e) {
            e.printStackTrace();
            res.setStatus(Mark.RESULT_DATA_ERROR);
            res.setMessage(e.getMessage());
        }




        return res;
    }
}
