package com.sinochem.crude.trade.transport.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.AgreementStatements;
import com.sinochem.crude.trade.transport.model.AgreementStatementsVO;
import com.sinochem.crude.trade.transport.query.StatementsQuery;
import com.sinochem.crude.trade.transport.service.AgreementStatementsService;
import com.sinochem.it.b2b.member.access.RolesAccess;


/**
 * @ClassName: Controller
 * @Description:结算单管理 （代理协议）
 * @author Wh
 * @date 2018年2月5日
 * @version V1.0
 */
@Controller
public class AgreementStatementsController  {
	@Autowired
	private AgreementStatementsService agreementStatementsService;
	 
	Log log = LogFactory.getLog(AgreementStatementsController.class);
	
	/**
	 *录入计算费用
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value="/statements/jsfy.json", method = RequestMethod.POST  )
	public ResultDatas<AgreementStatementsVO> jsfyStatements(@RequestBody AgreementStatementsVO vo){
		ResultDatas<AgreementStatementsVO> res = new ResultDatas<>();
		try {
			AgreementStatementsVO vo2 = new AgreementStatementsVO();
			//非空判断
			if(vo != null){
				if(vo.getFreightQuantily()!=null&&vo.getFreightOverageQuantily()!=null&&vo.getFreightOverageRate()!=null&&vo.getFreightOverageFlatrate()!=null){
					vo2.setFreightTotal(vo.getFreightQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)))).setScale(2, BigDecimal.ROUND_HALF_UP));
					vo2.setFreightOverageTotal(vo.getFreightOverageQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)).multiply(vo.getFreightOveragePct()))).setScale(2, BigDecimal.ROUND_HALF_UP));
					vo2.setAddressCommissionTotal((vo2.getFreightTotal().add(vo2.getFreightOverageTotal())).multiply(vo.getAddressCommissionPct().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP));
					vo2.setBalance(vo2.getFreightTotal().add(vo2.getFreightOverageTotal()).subtract(vo2.getAddressCommissionTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
			res.setDatas(vo2);
		} catch (BizException e) {
			log.error("jsfy error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("jsfy error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 结算单列表（经纪人船东）
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@RequestMapping(value=URLMapping.AGREEMENT_STATEMENT_LIST)
	public void getAgreementListOwner(CurrentUser user, ModelMap modelMap, StatementsQuery query){
		//校验用户
		if(user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if(user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//查询的参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiptCode", query.getReceiptCode());
		map.put("agreementCode", query.getAgreementCode());
		map.put("agentMemberId", user.getEpMemberId());
		
		//分页设定
		SimplePageInfo pageInfo = new  SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		Page<Map<String, Object>> pages = agreementStatementsService.queryRecords(map, pageInfo);
		query.setTotalItem(pages.getTotal());
		
		modelMap.put("query", query);
		modelMap.put("datas", pages);
		modelMap.put("user", user);
	}
	
	/**
	 * 录入我对货主的结算费用
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_STATEMENTS, method = RequestMethod.POST  )
	public Result saveStatement(@RequestBody AgreementStatementsVO vo, CurrentUser user){
		Result res = new Result();
		try {
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验 协议uuid 船合同uuid 
			if(StringUtils.isNullOrEmpty(vo.getShipAgreementUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			if(StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH352);
			}
			//校验必填
			if(StringUtils.isNullOrEmpty(vo.getReceiptCode())){
				throw new TransportException(TransportException.TYPE.ITSH331);
			}
			if(StringUtils.isNullOrEmpty(vo.getAgreementCode())){
				throw new TransportException(TransportException.TYPE.ITSH332);
			}
			if(StringUtils.isNullOrEmpty(vo.getPayee())){
				throw new TransportException(TransportException.TYPE.ITSH333);
			}
			if(StringUtils.isNullOrEmpty(vo.getPayer())){
				throw new TransportException(TransportException.TYPE.ITSH334);
			}
			if(StringUtils.isNullOrEmpty(vo.getReceiptAccessory())){
				throw new TransportException(TransportException.TYPE.ITSH335);
			}
			if(StringUtils.isNullOrEmpty(vo.getReceiptAccessoryPath())){
				throw new TransportException(TransportException.TYPE.ITSH336);
			}
			if(vo.getFreightQuantily() == null){
				throw new TransportException(TransportException.TYPE.ITSH337);
			}
			if(vo.getFreightOverageQuantily() == null){
				throw new TransportException(TransportException.TYPE.ITSH338);
			}
			if(vo.getFreightOverageFlatrate() == null){
				throw new TransportException(TransportException.TYPE.ITSH339);
			}
			if(vo.getFreightOverageRate() == null){
				throw new TransportException(TransportException.TYPE.ITSH340);
			}
			if(vo.getFreightOveragePct() == null){
				throw new TransportException(TransportException.TYPE.ITSH341);
			}
			if(vo.getAddressCommissionPct() == null){
				throw new TransportException(TransportException.TYPE.ITSH342);
			}
			if(vo.getFreightTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH344);
			}
			if(vo.getFreightOverageTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH345);
			}
			if(vo.getAddressCommissionTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH346);
			}
			if(vo.getBalance() == null){
				throw new TransportException(TransportException.TYPE.ITSH350);
			}
			agreementStatementsService.saveStatements(vo,user);
			
		} catch (BizException e) {
			log.error("saveStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("saveStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 逻辑删除
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.DELETE_STATEMENTS, method = RequestMethod.POST  )
	public Result deleteStatements(@RequestBody AgreementStatementsVO vo, CurrentUser user){
		Result res = new Result();
		try {
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验uuid
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uuid", vo.getUuid());
			AgreementStatements as = agreementStatementsService.findByUuid(vo.getUuid());
			if(as != null){
				agreementStatementsService.deleteStatements(map);				
			}else {
				res.setMessage(Constants.message_003);
			}
		} catch (BizException e) {
			log.error("deleteStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("deleteStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 修改
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_STATEMENTS, method = RequestMethod.POST  )
	public Result updateStatements(@RequestBody AgreementStatementsVO vo, CurrentUser user){
		Result res = new Result();
		try {
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验 协议uuid 船合同uuid 
			if(StringUtils.isNullOrEmpty(vo.getShipAgreementUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			//校验必填
			if(StringUtils.isNullOrEmpty(vo.getReceiptCode())){
				throw new TransportException(TransportException.TYPE.ITSH331);
			}
			if(StringUtils.isNullOrEmpty(vo.getAgreementCode())){
				throw new TransportException(TransportException.TYPE.ITSH332);
			}
			if(StringUtils.isNullOrEmpty(vo.getPayee())){
				throw new TransportException(TransportException.TYPE.ITSH333);
			}
			if(StringUtils.isNullOrEmpty(vo.getPayer())){
				throw new TransportException(TransportException.TYPE.ITSH334);
			}
			if(StringUtils.isNullOrEmpty(vo.getReceiptAccessory())){
				throw new TransportException(TransportException.TYPE.ITSH335);
			}
			if(StringUtils.isNullOrEmpty(vo.getReceiptAccessoryPath())){
				throw new TransportException(TransportException.TYPE.ITSH336);
			}
			if(vo.getFreightQuantily() == null){
				throw new TransportException(TransportException.TYPE.ITSH337);
			}
			if(vo.getFreightOverageQuantily() == null){
				throw new TransportException(TransportException.TYPE.ITSH338);
			}
			if(vo.getFreightOverageFlatrate() == null){
				throw new TransportException(TransportException.TYPE.ITSH339);
			}
			if(vo.getFreightOverageRate() == null){
				throw new TransportException(TransportException.TYPE.ITSH340);
			}
			if(vo.getFreightOveragePct() == null){
				throw new TransportException(TransportException.TYPE.ITSH341);
			}
			if(vo.getAddressCommissionPct() == null){
				throw new TransportException(TransportException.TYPE.ITSH342);
			}
			/*if(vo.getFreightTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH344);
			}
			if(vo.getFreightOverageTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH345);
			}
			if(vo.getAddressCommissionTotal() == null){
				throw new TransportException(TransportException.TYPE.ITSH346);
			}
			if(vo.getBalance() == null){
				throw new TransportException(TransportException.TYPE.ITSH350);
			}*/
			vo.setUpdateUser(user.getEpMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			if(vo.getFreightQuantily()!=null&&vo.getFreightOverageQuantily()!=null&&vo.getFreightOverageRate()!=null&&vo.getFreightOverageFlatrate()!=null){
				vo.setFreightTotal(vo.getFreightQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)))).setScale(2, BigDecimal.ROUND_HALF_UP));
				vo.setFreightOverageTotal(vo.getFreightOverageQuantily().multiply(vo.getFreightOverageFlatrate().multiply(vo.getFreightOverageRate().divide(new BigDecimal(100)).multiply(vo.getFreightOveragePct()))).setScale(2, BigDecimal.ROUND_HALF_UP));
				vo.setAddressCommissionTotal((vo.getFreightTotal().add(vo.getFreightOverageTotal())).multiply(vo.getAddressCommissionPct().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP));
				vo.setBalance(vo.getFreightTotal().add(vo.getFreightOverageTotal()).subtract(vo.getAddressCommissionTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			agreementStatementsService.updateRecord(vo);
		}catch(DataIntegrityViolationException e){
			log.error("updateStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage("数字过长");
		} catch (BizException e) {
			log.error("updateStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("updateStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 根据协议uuid 查找
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FINDBYUUID_STATEMENTSS, method = RequestMethod.POST  )
	public ResultDatas<Object> findObjByUuids(String uuid, CurrentUser user){
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			AgreementStatementsVO vo = new AgreementStatementsVO();
			if(com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(uuid)){
				vo.setUuid(uuid);
			}
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			AgreementStatements statements = new AgreementStatements();
			statements.setShipAgreementUuid(vo.getUuid());
			List<AgreementStatements> list = agreementStatementsService.queryByEntitys(statements);
			if (list != null && !list.isEmpty()) {
				res.setDatas(list.get(0));
			}
		} catch (BizException e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}	
		return res;
	}
	/**
	 * 根据uuid 查找
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FINDBYUUID_STATEMENTS, method = RequestMethod.POST  )
	public ResultDatas<Object> findObjByUuid(String uuid, CurrentUser user){
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			AgreementStatementsVO vo = new AgreementStatementsVO();
			if(com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(uuid)){
				vo.setUuid(uuid);
			}
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			AgreementStatements findByUuid = agreementStatementsService.findByUuid(vo.getUuid());
			if (findByUuid == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			res.setDatas(findByUuid);
		} catch (BizException e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}	
		return res;
	}
	
	/**
	 * om结算单列表
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.ADMIN})
	@RequestMapping(value=URLMapping.AGREEMENT_STATEMENT_OM)
	public void AgreementListOm(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,
			StatementsQuery query){
		//校验用户
		if(user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if(user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//查询的参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiptCode", query.getReceiptCode());
		map.put("agreementCode", query.getAgreementCode());
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		//分页设定
		query.setPageSize(20);
		Page<Map<String, Object>> pages = agreementStatementsService.queryRecordsOm(map,query.getPageInfo());
		query.setTotalItem(pages.getTotal());
		
		modelMap.put("query", query);
		modelMap.put("datas", pages);
		modelMap.put("user", user);
	}

	/**
	 * 根据协议uuid 查找om
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER,Constants.ADMIN})
	@RequestMapping(value=URLMapping.AGREEMENT_STATEMENT_DETAILS, method = RequestMethod.POST  )
	public ResultDatas<Object> findObj(@RequestBody AgreementStatementsVO vo, CurrentUser user){
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			AgreementStatements statements = new AgreementStatements();
			statements.setUuid(vo.getUuid());
			List<AgreementStatements> list = agreementStatementsService.queryByEntitys(statements);
			if (list != null && !list.isEmpty()) {
				res.setDatas(list.get(0));
			}
		} catch (BizException e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}	
		return res;
	}
}
