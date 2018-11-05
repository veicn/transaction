package com.sinochem.crude.trade.transport.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.AgreementStatements;
import com.sinochem.crude.trade.transport.model.AgreementStatementsVO;
import com.sinochem.crude.trade.transport.query.StatementsQuery;
import com.sinochem.crude.trade.transport.service.AgreementStatementsService;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.RolesAccess;


/**
 * @ClassName: Controller
 * @Description:结算单管理 （代理协议）货主接口
 * @author jiangxiuqiang
 * @date 2018年3月1日
 * @version V1.0
 */
@Controller
public class ShipperAgreementStatementsController  {
	@Autowired
	private AgreementStatementsService agreementStatementsService;
	 
	Log log = LogFactory.getLog(ShipperAgreementStatementsController.class);
	
	/**
	 * 结算单列表（代理协议）
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.SHIPPER_AGREEMENT_STATEMENT_LIST)
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
		map.put("epMemberId", user.getEpMemberId());
		
		//分页设定
		SimplePageInfo pageInfo = new  SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		Page<Map<String, Object>> pages = agreementStatementsService.queryRecords(map, pageInfo);
		query.setTotalItem(Long.valueOf(pages.getPages()));
		
		modelMap.put("query", query);
		modelMap.put("datas", pages);
		modelMap.put("user", user);
	}
	
	/**
	 * 根据协议uuid 查找
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.SHIPPER_FINDBYUUID_STATEMENTS, method = RequestMethod.POST  )
	public ResultDatas<AgreementStatementsVO> findObjByUuid(String uuid, CurrentUser user){
		ResultDatas<AgreementStatementsVO> res = new ResultDatas<>();
		try{
			AgreementStatementsVO vo = new AgreementStatementsVO();
			if(StringUtils.isNotEmpty(uuid)){
				vo.setUuid(uuid);
			}
			//校验用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if(user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if(StringUtils.isEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH351);
			}
			AgreementStatements as = agreementStatementsService.findByUuid(vo.getUuid());
			if (as != null) {
				AgreementStatementsVO datas = BeanConvertUtils.beanToBean(as, AgreementStatementsVO.class);
				res.setDatas(datas);
			}
		} catch (BizException e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findStatements error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
}
