package com.sinochem.crude.trade.orderexecute.controller.openapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ContractVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.DocInfoVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.InputVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortDischargeInfoVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortLoadingInfoVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ReceivablesVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ShipInfoVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.StatementSheetVO;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceInput;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.service.InterfaceInputService;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipLoadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipUnloadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.crude.trade.orderexecute.service.openapi.InputService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 提供外部系统调用接口
 * @author Administrator
 *
 */
@Controller
public class InputController  {
	Logger log = Logger.getLogger(InputController.class);
	
	private final static String APPLICATION_JSON_UTF8_VALUE = MediaType.APPLICATION_JSON.toString() + ";charset=UTF-8";
	private final static String CHECK_SYS_NAME_MSG = "来源系统未在B2B注册";
	
	@Autowired
	private OrderDocumentService orderDocumentService;
	@Autowired
	private OrderStatementService orderStatementService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private InterfaceSystemService interfaceSystemService;
	
	@Autowired
	private InterfaceInputService interfaceInputService;
	
	@Autowired
	private OrderShipService orderShipService;
	@Autowired
	private OrderShipLoadinginfoService orderShipLoadinginfoService;
	@Autowired
	private OrderShipUnloadinginfoService orderShipUnloadinginfoService;
	@Autowired
	private InputService inputService;
	
	/**
	 * 接收合同信息
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_CONTRACTINFO_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receiveContractInfo(HttpServletRequest request, @RequestBody ContractVO vo) {
		Result result = new Result();
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			inputService.receiveContractInfo(vo);
			
		} catch (BizException e) {
			log.error("receiveContractInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receiveContractInfo",
					UrlMapping.OPENAPI_CONTRACTINFO_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	/**
	 * 船舶信息
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_SHIPINFO_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receiveShipInfo(HttpServletRequest request, @RequestBody ShipInfoVO vo) {
		Result result = new Result();

		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			Order order = orderService.findByUuid(vo.getUuid());
			
			// 保存装港信息
			orderShipService.saveOrderShip(vo, order);
			

		} catch (BizException e) {
			log.error("receiveDocInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receiveShipInfo",
					UrlMapping.OPENAPI_SHIPINFO_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
				
		return result;
	}
	
	/**
	 * 装港信息
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_PORTLOADINGINFO_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receivePortLoadingInfo(HttpServletRequest request, @RequestBody PortLoadingInfoVO vo) {
		Result result = new Result();
		
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			// 订单状态机调用
			Order order = orderService.findByUuid(vo.getUuid());
			
			// 保存船舶信息
			orderShipLoadinginfoService.saveLoadingInfo(vo, order);
		} catch (BizException e) {
			log.error("receiveDocInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receivePortLoadingInfo",
					UrlMapping.OPENAPI_PORTLOADINGINFO_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	/**
	 * 卸港信息
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_PORTDISCHARGEINFO_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receivePortDischargeInfo(HttpServletRequest request, @RequestBody PortDischargeInfoVO vo) {
		Result result = new Result();
		
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			// 订单状态机调用
			Order order = orderService.findByUuid(vo.getUuid());
			
			// 保存装港信息
			orderShipUnloadinginfoService.saveUnloadingInfo(vo, order);
		} catch (BizException e) {
			log.error("receiveDocInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receivePortDischargeInfo",
					UrlMapping.OPENAPI_PORTDISCHARGEINFO_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
				
		return result;
	}
	
	/**
	 * 接收对账单
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_STATEMENTSHEET_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receiveStatementSheet(HttpServletRequest request, @RequestBody StatementSheetVO vo) {
		Result result = new Result();
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			String msg = orderStatementService.receiveOrderStatement(vo);
			if(msg != null) {
				log.error("receiveSattementSheet error" + msg);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setCode(Constants.EXCEPTION_CODE);
				result.setMessage(msg);	
			}
		} catch (Exception e) {
			log.error("receiveSattementSheet error" + e.getMessage());
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(e.getMessage());	
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receiveStatementSheet",
					UrlMapping.OPENAPI_STATEMENTSHEET_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	
	/**
	 * 单证信息
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_DOCINFO_RECEIVE)
	@ResponseBody
	@WithoutAccess
	public Result receiveDocInfo(HttpServletRequest request, @RequestBody DocInfoVO vo) {
		Result result = new Result();
		
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			String msg = orderDocumentService.receiveDocInfo(vo);
			if(msg != null) {
				log.error("receiveDocInfo error" + msg);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setCode(Constants.EXCEPTION_CODE);
				result.setMessage(msg);	
			} 
		} catch (BizException e) {
			log.error("receiveDocInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("receiveDocInfo error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "receiveDocInfo",
					UrlMapping.OPENAPI_DOCINFO_RECEIVE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	/**
	 * 收款信息同步
	 * 
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = UrlMapping.OPENAPI_RECEIVABLES_SYNCHRONIZE)
	@ResponseBody
	@WithoutAccess
	public Result synchronizeReceivables(HttpServletRequest request, @RequestBody ReceivablesVO vo) {
		Result result = new Result();
		
		try {
			if (!checkSysName(vo)) {
				result.setFail(CHECK_SYS_NAME_MSG);
				throw new BizException(CHECK_SYS_NAME_MSG);
			}
			
			String msg = orderService.savePayConfirmFromOut(vo);
			if(msg != null) {
				log.error("synchronizeReceivables error" + msg);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setCode(Constants.EXCEPTION_CODE);
				result.setMessage(msg);	
			}
		} catch (BizException e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		// 记录日志
		try {
			log.info(new JSONObject(vo).toString());
			saveLog(vo.getSysName(), "synchronizeReceivables",
					UrlMapping.OPENAPI_RECEIVABLES_SYNCHRONIZE,
					new JSONObject(vo).toString(), result);
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	/**
	 * 校验来源系统是否合法
	 * @param inputVO
	 * @return true:合法 false:非法
	 */
	boolean checkSysName(InputVO inputVO) {
		boolean result = false;
		InterfaceSystem interfaceSystem = new InterfaceSystem();
		interfaceSystem.setSysName(inputVO.getSysName());
		List<InterfaceSystem> sysList = interfaceSystemService.queryByEntitys(interfaceSystem);
		if (CollectionUtils.isNotEmpty(sysList)) {
			inputVO.setMemberId(sysList.get(0).getMemberId());
			result = true;
		}
		
		return result;
	}
	
	void saveLog(String sysName, String interfaceCode, String inputUrl, String inputPara, Result result) {
		InterfaceInput interfaceInput = new InterfaceInput();
		interfaceInput.setSysName(sysName);
		interfaceInput.setInterfaceCode(interfaceCode);
		interfaceInput.setInputUrl(inputUrl);
		interfaceInput.setMethodType("POST");
		interfaceInput.setInputTime(DateTimeUtils.currentDate());
		interfaceInput.setInputPara(inputPara);
		interfaceInput.setOutputPara(new JSONObject(result).toString());
		interfaceInput.setCreateDate(DateTimeUtils.currentDate());
		interfaceInput.setUpdateDate(DateTimeUtils.currentDate());
		interfaceInput.setStatus(result.getStatus()==Result.SUCCESS?"1":"0");
		this.interfaceInputService.insertRecord(interfaceInput);
	}
}
