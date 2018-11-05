package com.sinochem.crude.trade.orderexecute.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderContract;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderContractFileVO;
import com.sinochem.crude.trade.orderexecute.model.OrderContractVO;
import com.sinochem.crude.trade.orderexecute.query.ContractQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderContractFileService;
import com.sinochem.crude.trade.orderexecute.service.OrderContractService;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderMessagePushService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderTransportService;
import com.sinochem.crude.trade.orderexecute.validator.CreateContractValidator;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 合同管理相关功能
 * @author hexinfang
 *
 */
@Controller
@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master"})
public class OrderContractController  {

	@Autowired
	private OrderContractService orderContractService;
	@Autowired
	private OrderContractFileService contractFileService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService goodsService;
	@Autowired
	private OrderTransportService transportService;
	@Autowired
	private OrderPriceService orderPriceService;
	@Autowired
	private OrderMessagePushService orderMessagePushService;
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	@Autowired
	private OSSClient ossClient;

	Log log = LogFactory.getLog(OrderContractController.class);
	
	private CreateContractValidator validator = new CreateContractValidator();
	
	/**
	 * 合同列表查询
	 * @param user 登陆用户
	 * @param query 查询条件
	 * @param model
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_LIST_HTM)
	public void listContract(
			CurrentUser user, 
			ContractQuery query,
			ModelMap model){
		
		Map<String, Object> params = query.getQueryParam();
		params.put("customerId", user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderContractService.queryRecords(params, query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		model.put("query", queryBase);
		model.put("param", query.getQueryParam());
		
		model.put("dataList", pageResult.getResult());
	}
	
	/**
	 * 合同列表查询json
	 * @param user 登陆用户
	 * @param query 查询条件
	 * @param model
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_LIST_JSON)
	@ResponseBody
	@ApiSafeAccess
	public ResultDatas<List<Map<String, Object>>> listContractJSON(
			CurrentUser user, 
			ContractQuery query,
			ModelMap model){
		
		Map<String, Object> params = query.getQueryParam();
		params.put("customerId", user.getEpMemberId());
		Page<Map<String, Object>> pageResult = (Page<Map<String, Object>>) orderContractService.queryRecords(params, query.getPageInfo());
		
		ResultDatas<List<Map<String, Object>>> resultData = new ResultDatas<>();
		resultData.setDatas(pageResult.getResult());
		pageResult.setPageNum(pageResult.getPageNum());
		pageResult.setTotal(pageResult.getTotal());
		pageResult.setPageSize(pageResult.getPageSize());
		
		return resultData;
	}
	
	/**
	 * 删除合同
	 * @param cuid 合同UUID
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_DELETE)
	public String deleteContract(
			CurrentUser user,
			@RequestParam(value="cuid") String contractUuid){
		
		orderContractService.removeContract(contractUuid, user.getEpMemberId());
		
		return "redirect:/buyerCenter/contract/list.htm";
	}
	
	/**
	 * 查询合同详情
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.CONTRACT_DETAIL_JSON)
	@ApiSafeAccess
	public ResultDatas<OrderContractVO> getDetailJSON(
			@RequestParam(value="cuid", required=false) String contractUuid, 
			@RequestParam(value="ouid", required=false) String orderUuid, 
			ModelMap model){
		ResultDatas<OrderContractVO> resultData = new ResultDatas<OrderContractVO>();
		if(StringUtils.isBlank(contractUuid) && StringUtils.isBlank(orderUuid)) {
			return resultData;
		}
		
		OrderContract contract = null;
		if(StringUtils.isNotBlank(contractUuid)) {
			contract = orderContractService.findByUuid(contractUuid);
		} else {
			contract = orderContractService.findByOrderUuid(orderUuid);
		}
		if(contract == null){
			return resultData;
		}
		
		OrderContractVO detail = buildContractDetail(contract);
		resultData.setDatas(detail);
		
		return resultData;
	}
	
	
	/**
	 * 查询合同详情
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_DETAIL_HTM)
	public void getDetail(@RequestParam(value="cuid") String contractUuid, ModelMap model) {
		OrderContract contract = orderContractService.findByUuid(contractUuid);
		if(contract == null){
			throw new OrderExecException("404");
		}
		
		OrderContractVO detail = buildContractDetail(contract);
		
		model.put("contract", detail);
	}
	public OrderContractVO buildContractDetail(OrderContract contract){
		OrderContractVO result = null;
		
		OrderContractFile fileQuery = new OrderContractFile();
		fileQuery.setContractId(contract.getContractId());
		List<OrderContractFile> contractFileList = contractFileService.queryByEntitys(fileQuery);
		
		result = BeanConvertUtils.beanToBean(contract, OrderContractVO.class);
		List<OrderContractFileVO> contractFileVOList = new ArrayList<>();			
		for(OrderContractFile contractFile : contractFileList) {
			OrderContractFileVO contractFileVO = BeanConvertUtils.beanToBean(contractFile, OrderContractFileVO.class);
			
			if(StringUtils.isNotBlank(contractFile.getFilePath())) {
				// 设置URL过期时间为1小时
				Date expiration = new Date(new Date().getTime() + 3600 * 1000);
				String absUrl = ossClient.generatePresignedUrl(bucket, contractFile.getFilePath(),expiration,HttpMethod.GET).toString();
				
				contractFileVO.setOssPath(absUrl);
			}
			
			contractFileVOList.add(contractFileVO);
		}
		result.setContractFileList(contractFileVOList);
		
		return result;
	}
	
	/**
	 * 新增初始化
	 * @param model
	 * @throws IOException 
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_ADD, method = RequestMethod.GET)
	public String initAdd (
			@RequestParam("ouid") String orderUuid,
			@RequestParam(value="redirect") String redirect,
			ModelMap model) throws OrderExecException {
		
		Order order = orderService.findByUuid(orderUuid);
		if(order == null){
			throw new OrderExecException("404");
		}
		
		OrderContractVO contractVO = new OrderContractVO();
		contractVO.setRedirect(redirect);
		contractVO.setOuid(orderUuid);
		contractVO.setBuyerCustomer(order.getBuyerCustomerName());
		contractVO.setSellerCustomer(order.getSellerCustomerName());
		contractVO.setBuyerCustomerId(order.getBuyerCustomerId());
		contractVO.setSellerCustomerId(order.getSellerCustomerId());
		contractVO.setOrderNo(order.getOrderNo());
		
		OrderGoods goodsInfo = goodsService.findByOrderId(order.getId());
		if(goodsInfo != null){
			contractVO.setGoodsName(goodsInfo.getEnName());
			contractVO.setQuantity(goodsInfo.getQuantity());
		}
		
		OrderTransport transport = transportService.findByOrderId(order.getId());
		
		if(transport != null){
			String layTime = "";
			if(transport.getDeliveryDateStart() != null){
				layTime = DateFormatUtils.format(transport.getDeliveryDateStart(), "yyyy/MM/dd");
			}
			if(transport.getDeliveryDateEnd() != null){
				if(StringUtils.isNotBlank(layTime)) layTime += "-";
				layTime += DateFormatUtils.format(transport.getDeliveryDateEnd(), "yyyy/MM/dd");
			}
			contractVO.setLayTime(layTime);
		}
		
//		OrderShip orderShip = orderShipService.findByOrderId(order.getId());
//		if(orderShip != null){
//			contractVO.setShipName(orderShip.getName());
//		}
		
		if(goodsInfo != null){
			OrderPrice orderPrice = orderPriceService.findByOrderId(order.getId());
			if(orderPrice != null){
				contractVO.setPriceFormula(orderPrice.getPriceFormula());
				contractVO.setPricingDesc(orderPrice.getPriceDesc());
				contractVO.setTradeTerm(orderPrice.getTradeTerm());
				contractVO.setPaymentTerm(orderPrice.getPaymentTerm());
			}
		}
		
		model.put("contract", contractVO);
		
		return "common/contract/add";
	}
	
	/**
	 * 新增保存
	 * @param user
	 * @param model
	 * @param result
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_ADD, method = RequestMethod.POST)
	public String saveAdd(
			CurrentUser user, 
			@ModelAttribute("contract") OrderContractVO model, 
			BindingResult result,
			@RequestParam(value="redirect") String redirect,
			@RequestParam(value="ouid") String orderUuid){
		
		//数据校验
		Order order = orderService.findByUuid(orderUuid);
		if(order == null){
			throw new OrderExecException("404");
		}
		
		validator.validate(model, result);
		if(result.hasErrors()){
			return "common/contract/add";
		}

		
		OrderContract contract = buildContract(model);
		contract.setOrderId(order.getId());
		contract.setOrderNo(order.getOrderNo());
		contract.setContractUuid(KeyGenUtils.newUuid());
		contract.setContractType("销售");
		contract.setSellerCustomer(order.getSellerCustomerName());
		contract.setSellerCustomerId(order.getSellerCustomerId());
		contract.setBuyerCustomer(order.getBuyerCustomerName());
		contract.setBuyerCustomerId(order.getBuyerCustomerId());
		contract.setCreateDate(DateTimeUtils.currentDate());
		contract.setCreateUser(user.getMemberId());
		
		OrderGoods goodsInfo = goodsService.findByOrderId(order.getId());
		contract.setGoodsName(goodsInfo.getEnName());
		List<OrderContractFile> contractFileList = buildContractFiles(user, model);
		
		try {
			
			orderContractService.createContract(contract, contractFileList);
			
		} catch (OrderExecException e) {
			log.error("保存失败..", e);
			throw new OrderExecException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存失败..", e);
			throw new OrderExecException("保存失败..", e);
		}
		try {
			//有附件时推送消息
			if(contractFileList != null && contractFileList.size() > 0){				
				if("1".equals(order.getTradeCategory())){//原油				
					orderMessagePushService.messagePush(2, order.getUuid(), user.getMemberId(), "T10", null);
				}else if("2".equals(order.getTradeCategory())){//成品油
					orderMessagePushService.messagePush(2, order.getUuid(), user.getMemberId(), "T20", null);
				}
			}
		}catch (Exception e) {
			log.error("消息发送失败..", e);
		}
		return "redirect:/"+redirect;
	}
	
	/**
	 * 合同编辑初始化
	 * @param cuid 合同UUID
	 * @param ouid 订单UUID
	 * @param redirect 保存成功后的跳转页面
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_EDIT, method = RequestMethod.GET)
	public String initEdit(
			@RequestParam(value="cuid", required=false) String contractUuid,
			@RequestParam(value="ouid", required=false) String orderUuid,
			@RequestParam(value="redirect") String redirect,
			ModelMap model){
		if(StringUtils.isEmpty(contractUuid) && StringUtils.isEmpty(orderUuid)){
			throw new OrderExecException("404");
		}
		
		OrderContract contract = null;
		if(StringUtils.isNotEmpty(contractUuid)){
			contract = orderContractService.findByUuid(contractUuid);
			if(contract == null){
				throw new OrderExecException("404");
			}
		}else if(StringUtils.isNotEmpty(orderUuid)){
			contract = orderContractService.findByOrderUuid(orderUuid);
			if(contract == null){
				return "redirect:/common/contract/add.htm?ouid="+orderUuid+"&redirect="+redirect;
			}
		}
		
		OrderContractVO contractVO = buildContractDetail(contract);
		
		contractVO.setRedirect(redirect);
		model.put("contract", contractVO);
		
		return "common/contract/edit";
	}
	
	/**
	 * 编辑保存
	 * @param user 登陆用户
	 * @param uuid UUID
	 * @param model
	 * @param result
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONTRACT_EDIT, method = RequestMethod.POST)
	public String saveEdit(
			CurrentUser user,
			@RequestParam(value="ouid", required=false) String orderUuid,
			@RequestParam(value="cuid", required=false) String contractUuid, 
			@RequestParam(value="redirect") String redirect,
			@ModelAttribute("contract") OrderContractVO model,
			BindingResult result){
		
		if(StringUtils.isEmpty(contractUuid) && StringUtils.isEmpty(orderUuid)){
			throw new OrderExecException("404");
		}
		
		OrderContract contractInfo;
		if(StringUtils.isNotBlank(orderUuid)){
			contractInfo = orderContractService.findByOrderUuid(orderUuid);
		}else{
			contractInfo = orderContractService.findByUuid(contractUuid);
		}
		//数据校验
		if(contractInfo == null){
			throw new OrderExecException("404");
		}
		
		validator.validate(model, result);
		if(result.hasErrors()){
			return "common/contract/edit";
		}
		
		OrderContract contract = buildContract(model);
		contract.setContractId(contractInfo.getContractId());
		
		Order order = orderService.findByPrimaryKey(contractInfo.getOrderId());
		if(order == null){
			throw new OrderExecException("404");
		}
		List<OrderContractFile> contractFileList = buildContractFiles(user, model);
		
		try {
			orderContractService.updateContract(contract, contractFileList);
			//有附件时推送消息
			if(contractFileList != null && contractFileList.size() > 0){				
				if("1".equals(order.getTradeCategory())){//原油				
					orderMessagePushService.messagePush(2, order.getUuid(), user.getMemberId(), "T10", null);
				}else if("2".equals(order.getTradeCategory())){//成品油
					orderMessagePushService.messagePush(2, order.getUuid(), user.getMemberId(), "T20", null);
				}
			}
		} catch (Exception e) {
			log.error("保存失败", e);
			throw new OrderExecException();
		}
		
		return "redirect:/"+redirect;
	}
	
	private OrderContract buildContract(OrderContractVO model){
		OrderContract contract = new OrderContract();
		
		contract.setContractNo(model.getContractNo());
		contract.setQuantity(model.getQuantity());
		contract.setLayTime(model.getLayTime());
		contract.setShipName(model.getShipName());
		contract.setPriceFormula(model.getPriceFormula());
		contract.setPricingDesc(model.getPricingDesc());
		contract.setPriceRounding(model.getPriceRounding());
		contract.setPaymentTerm(model.getPaymentTerm());
		contract.setDemurrage(model.getDemurrage());
		contract.setInspection(model.getInspection());
		contract.setTradeTerm(model.getTradeTerm());
		contract.setGtc(model.getGtc());
		
		return contract;
	}
	private List<OrderContractFile> buildContractFiles(CurrentUser user, OrderContractVO model){
		List<OrderContractFile> contractFileList = new ArrayList<>();
		
		List<OrderContractFileVO> fileList = model.getContractFileList();
		
		if(fileList != null && !fileList.isEmpty()){
			for(OrderContractFileVO file : fileList){
				if(StringUtils.isNotBlank(file.getFilePath())){
					OrderContractFile contractFile = new OrderContractFile();
					contractFile.setContractFileName(file.getContractFileName());
					contractFile.setContractFileId(file.getContractFileId());
					contractFile.setFilePath(file.getFilePath());
					contractFile.setOriginalName(file.getOriginalName());
					
					contractFile.setUploadTime(new Date());
					contractFile.setUploadPerson(user.getMemberId());
					contractFile.setCreateDate(new Date());
					contractFile.setCreateUser(user.getMemberId());
					
					contractFileList.add(contractFile);
				}
			}
		}
		
		return contractFileList;
	}
	
	/**
	 * 下载合同附件
	 * @param user
	 * @param id
	 * @param response
	 */
//	@RequestMapping(value = "/buyerCenter/contract/download", method = RequestMethod.GET)
//	public void downloadFile(
//			CurrentUser user,
//			@RequestParam("id") Long id, 
//			HttpServletResponse response){
//		
//		OrderContractFile contractFile = contractFileService.findByPrimaryKeyAndCreater(id, user.getMemberId());
//		if(contractFile == null){
//			return;
//		}
//		
//		String filePath = contractFile.getFilePath();
//		
//		fileManage.download(filePath, response);
//	}
	
//	private boolean docTypeValidate(String fileName){
//		if(StringUtils.isNotEmpty(fileName)){
//			if(fileName.endsWith(".pdf")){
//				return true;
//			}
//			if(fileName.endsWith(".doc")){
//				return true;
//			}
//			if(fileName.endsWith(".docx")){
//				return true;
//			}
//		}
//		
//		return false;
//	}
}
