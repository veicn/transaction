package com.sinochem.crude.trade.order.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.sinochem.crude.trade.common.enums.BizType;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.model.valid.CrudeOilValid;
import com.sinochem.crude.trade.order.model.valid.LongTermValid;
import com.sinochem.crude.trade.order.model.valid.ProductOilValid;
import com.sinochem.crude.trade.order.model.vo.*;
import com.sinochem.crude.trade.order.service.*;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.model.form.ContractForm;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @Description:合約控制层
 * @Author : chenyz
 * @Date: 2017/12/9
 */
@Controller
@RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
public class ContractController {
	Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;
    @Autowired
    private CrudeContractService crudeContractService;
    @Autowired
    private ContractShipService contractShipService;
    @Autowired
    private CrudeOilResourceService crudeOilResourceService;
    @Autowired
    private CrudeOilLongTermContractPlanService crudeOilLongTermContractPlanService;
    @Autowired
    private ContractRelevanterService contractRelevanterService;

    @RequestMapping(value = "/create/crudeOrder",method = RequestMethod.POST)
    public String addCrudeOrder(@ModelAttribute("contract")ContractForm contractForm,
                                BindingResult bindingResult, CurrentUser user, ModelMap modelMap) {
        modelMap.put("contract", contractForm);
        validForm(contractForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            return "/create/createCrudeOilInfo";
        }
        try {
            CrudeOilBuyContract crudeOilBuyContract = convertCrudeOilContract(contractForm);
            contractService.createContract(crudeOilBuyContract, user.getEpMemberId());
            return "redirect:/queryCurdeOilList.htm";
        } catch(BizException e) {
            logger.error("新增一个订单失败",contractForm);
            logger.error("",e);
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0041));
            return "/create/createCrudeOilInfo";
        } catch (Exception e){
            logger.error("新增一个订单异常");
            logger.error("",e);
            modelMap.addAttribute("messages",VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0041));
            return "/status/error";
        }
    }

    @RequestMapping(value = "/create/productOilCrude",method = RequestMethod.POST)
    public String addProductOilCrude(@ModelAttribute("contract")ContractForm contractForm,
                                     BindingResult bindingResult, CurrentUser user, ModelMap modelMap) {
        modelMap.put("user", user);
        modelMap.put("contract", contractForm);
        validForm(contractForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            return "/create/createProductOilInfo";
        }
        try {
            ProductOilSaleContract productOilSaleContract = convertProductOilContract(contractForm);
            contractService.createContract(productOilSaleContract,user.getEpMemberId());
            return "redirect:/queryProductOilList.htm";
        } catch(BizException e) {
            logger.error("新增一个订单失败",contractForm);
            logger.error("",e);
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0041));
            return "/create/createProductOilInfo";
        } catch (Exception e){
            logger.error("新增一个订单异常");
            logger.error("",e);
            modelMap.addAttribute("messages",VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0041));
            return "/status/error";
        }
    }

    /**
     * 创建长协订单
     * @param contractForm
     * @param bindingResult
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/create/OilLongCrude",method = RequestMethod.POST)
    public String addOilLongCrude(@ModelAttribute("contract")ContractForm contractForm,
                                  BindingResult bindingResult, CurrentUser user,ModelMap modelMap){
        validForm(contractForm, bindingResult);

        modelMap.addAttribute("user",user);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            modelMap.addAttribute("contract",contractForm);
            modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
            modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
            modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
            modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
            return "/longTermContract/longContractAdd";
        }
        try {
            CrudeOilLongTermContract crudeOilLongTermContract = covertLongTermContract(contractForm);
            contractService.createContract(crudeOilLongTermContract,user.getEpMemberId());
            return "redirect:/queryCurdeOilList.htm";
        }catch (BizException e){
            logger.error("新增长约订单失败{}",contractForm);
            logger.error("",e);
            modelMap.addAttribute("errorMessage",e.getMessage());
            modelMap.addAttribute("contract",contractForm);
            modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
            modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
            modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
            modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
            return "/longTermContract/longContractAdd";
        }catch (Exception e){
            logger.error("新增长约订单异常");
            logger.error("",e);
            modelMap.addAttribute("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0042));
            modelMap.addAttribute("contract",contractForm);
            modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
            modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
            modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
            modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
            return "/longTermContract/longContractAdd";
        }
    }


    /**
     * 更新长协原油订单
     * @param contractForm
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/OilLongCrude",method = RequestMethod.POST)
    public String editOilLongCrude(@ModelAttribute("contract")ContractForm contractForm,
                                   BindingResult bindingResult, CurrentUser user,ModelMap modelMap) {
        validForm(contractForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            modelMap.addAttribute("contract",contractForm);
            modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
            modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
            modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
            modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
            return "/longTermContract/editContractForm";
        }
        try {
            CrudeOilLongTermContract crudeOilLongTermContract = covertLongTermContract(contractForm);
            contractService.updateContract(crudeOilLongTermContract, user.getEpMemberId());
            return "redirect:/queryCurdeOilList.htm";
        }
        catch (BizException e){
                logger.error("新增长约订单失败{}",contractForm);
                logger.error("",e);
                modelMap.addAttribute("errorMessage",e.getMessage());
                modelMap.addAttribute("contract",contractForm);
                modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
                modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
                modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
                modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
                return "/longTermContract/editContractForm";
            }catch (Exception e){
                logger.error("新增长约订单异常{}",contractForm);
                logger.error("",e);
                modelMap.addAttribute("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0045));
                modelMap.addAttribute("contract",contractForm);
                modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
                modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
                modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
                modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
            return "/longTermContract/editContractForm";
            }
    }
    /**
     * 更新原油订单信息
     * @param contractForm
     * @param user
     */
    @RequestMapping(value = "/update/crudeOrder",method = RequestMethod.POST)
    public String update(@ModelAttribute("contract")ContractForm contractForm,
                         BindingResult bindingResult, CurrentUser user,ModelMap modelMap){
        validForm(contractForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            modelMap.put("contract", contractForm);
            return "/create/createCrudeOilInfo";
        }
        try {
            CrudeOilBuyContract crudeOilBuyContract = convertCrudeOilContract(contractForm);
            contractService.updateContract(crudeOilBuyContract, user.getEpMemberId());
            return "redirect:/queryCurdeOilList.htm";
        }catch (Exception e){
            logger.error("修改订单失败{}",contractForm);
            logger.error("",e);
            modelMap.addAttribute("messages",e.getMessage());
            return "/status/error";
        }
    }

    /**
     * 更新成品油订单信息
     * @param contractForm
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/productOrder",method = RequestMethod.POST)
    public String productOrder(@ModelAttribute("contract")ContractForm contractForm,
                               BindingResult bindingResult, CurrentUser user,ModelMap modelMap){
        modelMap.put("user", user);
        modelMap.put("contract", contractForm);
        validForm(contractForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.put("errors", bindingResult.getAllErrors());
            return "/create/createProductOilInfo";
        }
        try {
            ProductOilSaleContract productOilSaleContract = convertProductOilContract(contractForm);
            contractService.updateContract(productOilSaleContract,user.getEpMemberId());
            return "redirect:/queryProductOilList.htm";
        } catch(BizException e) {
            logger.error("修改订单失败{}",contractForm);
            logger.error("",e);
            modelMap.put("errorMessage", e.getMessage());
            return "/create/createProductOilInfo";
        } catch (Exception e){
            logger.error("修改订单异常{}",contractForm);
            logger.error("",e);
            modelMap.addAttribute("messages",e.getMessage());
            return "/status/error";
        }
    }

    @RequestMapping("/buyer/contractOperList")
    public void contractOperList(CurrentUser user,Long contractId, ModelMap modelMap){
        if (contractId != null) {
            List<ContractLog> contractLogs = contractService.contractOperList(contractId);
            modelMap.addAttribute("contractLogs",contractLogs);
        }
    }

    /**
     * 申请取消订单
     */
    @RequestMapping(value = "/apply_cancel_contacrt.json")
    public Result applyCancelContract(String uuid, String remark, CurrentUser user){
        Result result = new Result();
        result.setStatus(Result.ERROR);
        try{
            contractService.applyCancelContract(uuid,remark, user);
            result.setStatus(Result.SUCCESS);
        }catch(BizException e){
            logger.error("申请取消订单失败{}",uuid,remark);
            logger.error("",e);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }
    /**
     * 撤销取消订单
     */
    @RequestMapping(value = "/revoke_cancel_contacrt.json")
    public Result revokeCancelContract(String uuid,String remark, CurrentUser user){
        Result result = new Result();
        result.setStatus(Result.ERROR);
        try{
            contractService.revokeCancelContract(uuid,remark, user);
            result.setStatus(Result.SUCCESS);
        }catch(BizException e){
            logger.error("撤销取消订单失败{}",uuid,remark);
            logger.error(e.getMessage());
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }
    /**
     * 确认取消订单
     */
    @RequestMapping(value = "/confirm_cancel_contacrt.json")
    public Result confirmCancelContract(String uuid,String remark,boolean operStatus, CurrentUser user){
        Result result = new Result();
        result.setStatus(Result.ERROR);
        try{
            contractService.confirmCancelContract(uuid,remark,operStatus, user);
            result.setStatus(Result.SUCCESS);
        }catch(BizException e){
            logger.error(e.getMessage()+"{}",uuid,remark);
            logger.error("",e);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 确认订单
     * @param orderId
     * @param bizType
     * @param token
     */
    @RequestMapping(value = "/confirm_contacrt")
    public String confirmContract(Long orderId,String bizType,String token,CurrentUser user){
        if(!StringUtils.isEmpty(bizType)){
            try {
                contractService.confirmContract(orderId, user.getEpMemberId(), token);
                if("CrudeOil".equals(bizType)){
                    return "redirect:/queryCurdeOilList.htm";
                }else if("ProductOil".equals(bizType)){
                    return "redirect:/queryProductOilList.htm";
                }
            } catch (BizException e) {
                logger.error(e.getMessage()+"{}",orderId,bizType,token);
                logger.error("",e);
            }
        }
        return "/status/error";
    }

    /**
     * 确认订单
     * @param orderId
     * @param bizType
     * @param token
     * @param user
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/confirm_contacrt.json")
	public Result confirmContractResult(Long orderId,String bizType,String token,CurrentUser user) {
    	Result result = new Result();
    	result.setStatus(Result.ERROR);
		if(StringUtils.isNotBlank(bizType)){
			try{
				contractService.confirmContract(orderId, user.getEpMemberId(), token);
				if(BizType.CRUDE_OIL.getCode().equals(bizType)){
					result.setStatus(Result.SUCCESS);
					result.setMessage("queryCurdeOilList.htm");
	                return result;
	            }else if(BizType.PRODUCT_OIL.getCode().equals(bizType)){
	            	result.setStatus(Result.SUCCESS);
					result.setMessage("queryProductOilList.htm");
	                return result;
	            }
			}catch(BizException e){
				logger.error(e.getMessage()+"{}",orderId,bizType,token);
                logger.error("",e);
				result.setMessage(e.getMessage());
				return result;
			}
        }else{
        	logger.error("数据错误bizType：" + bizType);
        	result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0005));
        }
		return result;
	}
    /**
     * 确认订单
     */
    @ResponseBody
	@RequestMapping(value = "/confirm_contract_new.json")
	public ResultDatas confirmContractNew(String uuid, String token, CurrentUser user) {
    	ResultDatas resultDatas = new ResultDatas();
        try{
            contractService.confirmContractNew(uuid, user.getEpMemberId(),token,user);
        }catch(BizException e){
            logger.error(e.getMessage(), e);
            resultDatas.setFail(e.getMessage());
        }
        return resultDatas;
	}
    /**
     * 撤回订单
     */
    @ResponseBody
    @RequestMapping(value = "/revoke_contract_new.json")
    public ResultDatas revokeContractNew(String uuid, String remark, String token, CurrentUser user) {
        ResultDatas resultDatas = new ResultDatas();
        try{
            contractService.revokeContractNew(uuid, user.getEpMemberId(),remark,token,user);
        }catch(BizException e){
            logger.error(e.getMessage(), e);
            resultDatas.setFail(e.getMessage());
        }
        return resultDatas;
    }
    /**
     * 拒绝订单
     */
    @ResponseBody
    @RequestMapping(value = "/rejected_contract_new.json")
    public ResultDatas rejectedContractNew(String uuid,  String remark, String token, CurrentUser user) {
        ResultDatas resultDatas = new ResultDatas();
        try{
            contractService.rejectedContractNew(uuid, user.getEpMemberId(),remark,token,user);
        }catch(BizException e){
            logger.error(e.getMessage(), e);
            resultDatas.setFail(e.getMessage());
        }
        return resultDatas;
    }

    /**
     * 获取长协查看参数
     * @param id
     * @param modelMap
     */
    @RequestMapping("/longTermContract/longContractDetail")
    public void setQueryId(String id, CurrentUser user,ModelMap modelMap) {
        Contract contract = crudeContractService.queryContractInfoByUUID(id);
        modelMap.addAttribute("contract",contract);
        modelMap.addAttribute("user",user);

    }

    /**
     * 获取长协修改参数
     * @param id
     * @param modelMap
     */
    @RequestMapping("/longTermContract/editContractForm")
    public void setEditId(String id,CurrentUser user,ModelMap modelMap) {
        ContractForm contractForm = new ContractForm();
        //合约信息
        Contract contract = crudeContractService.queryContractInfoByUUID(id);
        ContractForm.convertToContractVO(contract,contractForm);
        //船务信息
        ContractShip contractShip = contractShipService.queryByContractId(contract.getId());
        ContractShipVO contractShipVO = new ContractShipVO();
        ContractShipVO.convertToContractShipVO(contractShip,contractShipVO);
        contractForm.setContractShip(contractShipVO);
        //油种信息
        CrudeOilResource crudeOilResource = crudeOilResourceService.queryByContractId(contract.getId());
        CrudeOilResourceVO crudeOilResourceVO = new CrudeOilResourceVO();
        CrudeOilResourceVO.convertToCrudeOilResourceVO(crudeOilResource,crudeOilResourceVO);
        contractForm.setCrudeOilResource(crudeOilResourceVO);
        //查询采购计划信息
        List<CrudeOilLongTermContractPlan> planList =crudeOilLongTermContractPlanService.query(contract.getId());
        List<CrudeOilLongTermContractPlanVO> crudeOilLongTermContractPlans = new ArrayList<>();
        for (CrudeOilLongTermContractPlan crudePlan:planList) {
            CrudeOilLongTermContractPlanVO crudeOilLongTermContractPlanVO = new CrudeOilLongTermContractPlanVO();
            CrudeOilLongTermContractPlanVO.convertToCrudeOilLongTernContractPlanVO(crudePlan, crudeOilLongTermContractPlanVO);
            crudeOilLongTermContractPlanVO.setCrudeOilResource(contractForm.getCrudeOilResource());
            crudeOilLongTermContractPlans.add(crudeOilLongTermContractPlanVO);
        }
        contractForm.setPlans(crudeOilLongTermContractPlans);
        //获取购买商信息
        ContractRelevanter contractRelevanterBuy =contractRelevanterService.query(contract.getId(),"B");
        ContractRelevanterVO buyerRelevanter = new ContractRelevanterVO();
        ContractRelevanterVO.convertToContractRelevanterVO(contractRelevanterBuy,buyerRelevanter);
        contractForm.setBuyerRelevanter(buyerRelevanter);
        //获取供应商信息
        ContractRelevanter contractRelevanterSeller =contractRelevanterService.query(contract.getId(),"S");
        ContractRelevanterVO supplierRelevanter = new ContractRelevanterVO();
        ContractRelevanterVO.convertToContractRelevanterVO(contractRelevanterSeller,supplierRelevanter);
        contractForm.setSupplierRelevanter(supplierRelevanter);

        modelMap.addAttribute("contract",contractForm);
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("creditItemMap",DictUtils.getCreditItem());
        modelMap.addAttribute("inspectionFeeSharingTypeMap",DictUtils.getInspectionFeeSharingTypeMap());
        modelMap.addAttribute("tradeItemMap",DictUtils.getTradeItemMap());
        modelMap.addAttribute("payItemMap",DictUtils.getPayItemMap());
    }

    /**
     * 跳转长协页面
     * @param
     * @param modelMap
     */
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping("/longTermContract/longContractAdd")
    public void goLongContract(CurrentUser user,ModelMap modelMap) {

        Map tradeItemMap = DictUtils.getTradeItemMap();
        Map payItemMap = DictUtils.getPayItemMap();
        Map creditItemMap = DictUtils.getCreditItem();
        Map inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
        
        modelMap.addAttribute("tradeItemMap",tradeItemMap);
        modelMap.addAttribute("payItemMap",payItemMap);
        modelMap.addAttribute("creditItemMap",creditItemMap);
        modelMap.addAttribute("inspectionFeeSharingTypeMap",inspectionFeeSharingTypeMap);
        
        modelMap.addAttribute("user",user);

    }
    
    
    
    private void validForm(ContractForm contractForm, BindingResult bindingResult) {
        ContractRelevanterVO traderRelevanter = contractForm.getTraderRelevanter();
        if (traderRelevanter != null) {
//            String phoneNo = traderRelevanter.getPhoneNo();
//            if (StringUtils.isNotBlank(phoneNo) && !Pattern.matches("^((0\\d{2,3}-\\d{7,8})|(1\\d{10}))$", phoneNo)) {
//                bindingResult.rejectValue("traderRelevanter.phoneNo", null, VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0001));
//            }
            String email = traderRelevanter.getEmail();
            if (StringUtils.isNotBlank(email) && !Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$", email)) {
                bindingResult.rejectValue("traderRelevanter.email", null, VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0002));
            }
        }
        int  i = 0;
        List<CrudeOilLongTermContractPlanVO> plans = contractForm.getPlans();
        for(CrudeOilLongTermContractPlanVO it : plans){
            if(StringUtils.isBlank(it.getCrudeOilResource().getName())){
                bindingResult.rejectValue("plans["+i+"].crudeOilResource.name",null,VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0003));
            }

            i++;
        }
        if(null != contractForm.getValuationProidType() && contractForm.getValuationProidType()==4){
            if(contractForm.getValuationProidStart()==null)
                bindingResult.rejectValue("valuationProidEnd",null,VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0004));
            if(contractForm.getValuationProidEnd()==null)
                bindingResult.rejectValue("valuationProidEnd",null,VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0004));
        }
    }


    /**
     * 表单信息转原油订单
     * @param contractForm
     * @return
     */
    private CrudeOilBuyContract convertCrudeOilContract(ContractForm contractForm) {
        //重新封装原油信息
        CrudeOilBuyContract crudeOilBuyContract = new CrudeOilBuyContract();
        ContractForm.convertToContract(contractForm, crudeOilBuyContract);
        CrudeOilResource crudeOilResource = new CrudeOilResource();
        CrudeOilResourceVO.convertToCrudeOilResource(contractForm.getCrudeOilResource(),crudeOilResource);
        crudeOilBuyContract.setCrudeOilResource(crudeOilResource);
        return crudeOilBuyContract;
    }
    /**
     * 表单信息转成品油订单
     * @param contractForm
     * @return
     */
    private ProductOilSaleContract convertProductOilContract(ContractForm contractForm) {
        //重新封装成品油信息
        ProductOilSaleContract productOilSaleContract = new ProductOilSaleContract();
        ContractForm.convertToContract(contractForm, productOilSaleContract);

        ProductOilResourceVO productOilResourceVO = contractForm.getProductOilResource();
        if (productOilResourceVO != null) {
            ProductOilResource productOilResource = new ProductOilResource();
            ProductOilResourceVO.convertToProductOilResource(productOilResourceVO, productOilResource);
            productOilSaleContract.setProductOilResource(productOilResource);
        }

        return productOilSaleContract;
    }
    /**
     * 表单信息转长协订单
     * @param contractForm
     * @return
     */
    private CrudeOilLongTermContract covertLongTermContract(ContractForm contractForm){
        //重新封装长协信息
        CrudeOilLongTermContract crudeOilLongTermContract = new CrudeOilLongTermContract();
        ContractForm.convertToContract(contractForm, crudeOilLongTermContract);
        CrudeOilResource crudeOilResource = new CrudeOilResource();
        CrudeOilResourceVO.convertToCrudeOilResource(contractForm.getCrudeOilResource(),crudeOilResource);
        crudeOilLongTermContract.setCrudeOilResource(crudeOilResource);
        List<CrudeOilLongTermContractPlanVO> crudeOilLongTermContractPlanVOList= contractForm.getPlans();
        if (crudeOilLongTermContractPlanVOList != null && crudeOilLongTermContractPlanVOList.size() != 0) {
            List<CrudeOilLongTermContractPlan> crudeOilLongTermContractPlanList = new ArrayList<>();

            for (CrudeOilLongTermContractPlanVO crudeOilLongTermContractPlanVO : crudeOilLongTermContractPlanVOList) {
                CrudeOilLongTermContractPlan crudeOilLongTermContractPlan = new CrudeOilLongTermContractPlan();
                CrudeOilLongTermContractPlanVO.convertToCrudeOilLongTermContractPlan(crudeOilLongTermContractPlanVO, crudeOilLongTermContractPlan);
                crudeOilLongTermContractPlanList.add(crudeOilLongTermContractPlan);
            }

            crudeOilLongTermContract.setPlans(crudeOilLongTermContractPlanList);
        }

        return crudeOilLongTermContract;
    }

}
