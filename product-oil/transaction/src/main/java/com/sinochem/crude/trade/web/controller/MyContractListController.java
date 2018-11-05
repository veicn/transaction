package com.sinochem.crude.trade.web.controller;

import java.util.*;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Interaction;
import com.sinochem.crude.trade.transaction.constant.Mark;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.ExceptionHelper;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.sinochem.crude.trade.transaction.constant.Mark.RESULT_DATA_ERROR;

/**
 * 成品油订单列表
 *
 * @author Yichen Zhao
 * date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.MY_CONTRACT_LIST)
public class MyContractListController {

    private final Logger LOGGER = LoggerFactory.getLogger(MyContractListController.class);

    @Autowired
    private ContractSheetService contractSheetService;
    @Autowired
    URLBroker transactionServer;

    @Autowired
    private TTransExtendService tTransExtendService;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    ExceptionHelper exceptionHelper;

    @Autowired
    private DictionaryService dictionaryService;


    @RequestMapping(UrlMapping.INDEX)
    public void index(CurrentUser currentUser, ContractSheetQueryVO contractSheetQueryVO,
                      PageInfo pageInfo, ModelMap model) {

        try {
            if (pageInfo != null) {
                pageInfo.setPageSize(Interaction.DEFAULT_PAGE_SIZE);
            }

            PageInfoResult pageInfoResult = contractSheetService.getContractSheetList(currentUser, contractSheetQueryVO, pageInfo);
            List<ContractSheet> contractSheetList = pageInfoResult.getList();

            //类型转换，contractSheet转成contractcombinevo,再把contractcombinevo放到list中传回前端
            if (!contractSheetList.isEmpty()) {
                ArrayList<ContractSheetCombineVO> contractSheetCombineVOList = new ArrayList<>();

                for (ContractSheet contractSheet : contractSheetList) {

                    ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(currentUser, contractSheet);
                    ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
                    contractSheetCombineVOList.add(contractSheetCombineVO);


                }

                pageInfoResult.setList(contractSheetCombineVOList);

            }
            //查询下拉框里的内容
            model.addAttribute("pageInfoList", pageInfoResult);
            model.addAttribute("category", dictionaryService.productCategoryMap());
            model.addAttribute("status", dictionaryService.contractSheetStatusMap());
            model.addAttribute("tradeTerms", dictionaryService.tradeTermMap());
            model.addAttribute("userId", currentUser.getEpMemberId());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            model.addAttribute("pageInfoList", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("pageInfoList", pageInfo);
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }


    /**
     * 交易列表查询  微信API
     *
     * @param queryvo
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = "contractSheetList.json")
    public ResultDatas<List<ContractSheetWXVO>> getContractSheetListByeEpmemberid(ContractSheetQueryVO queryvo) {
        ResultDatas<List<ContractSheetWXVO>> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
        try {
            List<ContractSheetWXVO> rlist = new ArrayList<ContractSheetWXVO>();
            List<ContractSheet> list = contractSheetService.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());

            for (ContractSheet contractSheet : list) {
                ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
                ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
                if (contractSheetCombineVO != null) {
                    wxvo = new ContractSheetWXVO();
                    wxvo.setUuid(contractSheet.getUuid());
                    if(contractSheetCombineVO.getContractSheetVO()!=null) {
                        wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                        if(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO()!=null) {
                            wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getEnName());
                        }
                    }
                    if(contractSheetCombineVO.getTransportInfoVO()!=null) {
                        wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                        wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                    }
                    if(contractSheetCombineVO.getPricingInfoVO()!=null) {
                        String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                        pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getEnName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                        wxvo.setPremiumsAndDiscounts(pdstr);
                        wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName());
                    }
                    if(contractSheetCombineVO.getProductInfoVO()!=null) {
                        wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getEnName());
                        wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getEnName());
                        String url = transactionServer.get("/product_images/" + contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getCode() + "/vertical.jpg").toString();
                        wxvo.setQuantity(contractSheetCombineVO.getProductInfoVO().getQuantityAsString());
                        wxvo.setProductCategoryImg(url);
                    }

                    rlist.add(wxvo);
                }
            }
            resultDatas.setDatas(rlist);
        } catch (BizException e) {
            e.printStackTrace();
        }

        return resultDatas;
    }

    @RequestMapping(UrlMapping.GET_CONTRACT_SHEET_UUID)
    @ResponseBody
    public Result getContractSheetUuid(CurrentUser currentUser, ContractSheet contractSheet) {
        Result result = new Result();
        try {
            ContractSheet contractSheetOne = contractSheetService.getContractSheetById(currentUser, contractSheet.getId());
            String uuid= contractSheetOne.getUuid();
            result.setMessage(uuid);
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            result.setStatus(RESULT_DATA_ERROR);
            result.setMessage("fail");

        } catch (RuntimeException e) {
            result.setStatus(RESULT_DATA_ERROR);
            result.setMessage("fail");
            LOGGER.error(e.getMessage(), e);
        }finally {
            return result;
        }
    }




    /**
     * 补录合同号
     */
    @RequestMapping(value=UrlMapping.UPDATE_TRANS_EXTEND)
    @ResponseBody
    public Result updateTransExtend(TTransExtend tTransExtend, CurrentUser currentUser) {
        Result res = new Result();
        try {
            ModelAndView mav = new ModelAndView();

            int r = tTransExtendService.saveOrUpdateTTransExtend(tTransExtend,currentUser);

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




    @RequestMapping(UrlMapping.APPOINT_LIST)
    public void appointList(CurrentUser currentUser, ContractSheetQueryVO contractSheetQueryVO,
                      PageInfo pageInfo, ModelMap model) {

        try {
            if (pageInfo != null) {
                pageInfo.setPageSize(Interaction.DEFAULT_PAGE_SIZE);
            }

            PageInfoResult pageInfoResult = contractSheetService.getContractSheetList(currentUser, contractSheetQueryVO, pageInfo);
            List<ContractSheet> contractSheetList = pageInfoResult.getList();

            //类型转换，contractSheet转成contractcombinevo,再把contractcombinevo放到list中传回前端
            if (!contractSheetList.isEmpty()) {
                ArrayList<ContractSheetCombineVO> contractSheetCombineVOList = new ArrayList<>();

                for (ContractSheet contractSheet : contractSheetList) {

                    ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(currentUser, contractSheet);
                    /*if(null!=contractSheetQueryVO.getBrokerAppointStatus()&&null!=contractSheetCombine.gettBrokerAppoint()){
                        if(!contractSheetQueryVO.getBrokerAppointStatus().equals(contractSheetCombine.gettBrokerAppoint().getStatus()))
                            continue;
                    }
                    if(null!=contractSheetQueryVO.getInspectorAppointStatus()&&null!=contractSheetCombine.gettInspectorAppoint()){
                        if(!contractSheetQueryVO.getInspectorAppointStatus().equals(contractSheetCombine.gettInspectorAppoint().getStatus()))
                            continue;
                    }
                    if(null!=contractSheetQueryVO.getShipagentAppointStatus()&&null!=contractSheetCombine.gettShipagentAppoint()){
                        if(!contractSheetQueryVO.getShipagentAppointStatus().equals(contractSheetCombine.gettShipagentAppoint().getStatus()))
                            continue;
                    }*/
if(null!=contractSheetQueryVO.getBrokerAppointStatus()&&"1".equals(contractSheetQueryVO.getBrokerAppointStatus())){
    if((null!=contractSheetCombine.gettBrokerAppoint()&&null!=contractSheetCombine.gettBrokerAppoint().getStatus()&&"1".equals(contractSheetCombine.gettBrokerAppoint().getStatus()))
        ||
      (null!=contractSheetCombine.gettInspectorAppoint()&&null!=contractSheetCombine.gettInspectorAppoint().getStatus()&&"1".equals(contractSheetCombine.gettInspectorAppoint().getStatus()))
        ||
      (null!=contractSheetCombine.gettShipagentAppoint()&&null!=contractSheetCombine.gettShipagentAppoint().getStatus()&&"1".equals(contractSheetCombine.gettShipagentAppoint().getStatus()))
     )
    {

        ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
        contractSheetCombineVOList.add(contractSheetCombineVO);
    }

}else{
    ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
    contractSheetCombineVOList.add(contractSheetCombineVO);
}

                }

                pageInfoResult.setList(contractSheetCombineVOList);

            }
            //查询下拉框里的内容
            model.addAttribute("pageInfoList", pageInfoResult);
            model.addAttribute("category", dictionaryService.productCategoryMap());
            model.addAttribute("status", dictionaryService.appointStatusMap());
            model.addAttribute("tradeTerms", dictionaryService.tradeTermMap());
            model.addAttribute("userId", currentUser.getEpMemberId());
            model.addAttribute("contractSheetQueryVO", contractSheetQueryVO);
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            model.addAttribute("error", exceptionHelper.getBizExceptionByCode(exceptionCode));
            model.addAttribute("pageInfoList", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("pageInfoList", pageInfo);
            LOGGER.error(e.getMessage(), e);
            model.addAttribute("error", exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR));
        }
    }

}
