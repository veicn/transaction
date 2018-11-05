package com.sinochem.crude.trade.shipagent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSof;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.crude.trade.blockchain.service.impl.TTransExtendServiceImpl;
import com.sinochem.crude.trade.broker.model.vo.ForwarderListVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/6
 */
@WithoutAccess
@Controller
@RequestMapping("/blockchain/shipagent/appointtask")
public class TaskController {

	private Logger LOG = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private AppointTaskService appointTaskService;

	@Autowired
	private MyContractDetailController myContractDetailController;

	@Autowired
	private TTransExtendService tTransExtendService;

	@Autowired
	private DocumentService documentService;



	@Value("${aliyun.oss.show.endpoint}")
	private String prefixUrl;

	private static final Gson gson = new Gson();
	/**
	 * 获取任务详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.json")
	public ResultData list(@RequestBody TBrokerAppointQueryVO shipagentAppoint){
		LOG.info("接口收到参数:"+JSONObject.toJSONString(shipagentAppoint));
		System.out.println("server接口收到参数:"+JSONObject.toJSONString(shipagentAppoint));
		ResultData resultDatas = new ResultData();
		try {
			List<ForwarderListVO> resultlist = new ArrayList<ForwarderListVO>();
			ForwarderListVO vo = null;
			Page<TShipagentAppoint> list = appointTaskService.getTBrokerAppointPage(shipagentAppoint);
			for (int i = 0; i < list.size(); i++) {
				String uuid = list.get(i).getDealUuid();
				if (!StringHelper.isNullOrEmptyString(uuid)) {
					com.sinochem.it.b2b.common.result.ResultDatas<ContractSheetWXVO> contdata = myContractDetailController.getContractSheetListByeEpmemberid(uuid);
					if (0 == contdata.getStatus()) {
						vo = new ForwarderListVO();
						vo.setNomineeCompany(list.get(i).getAppointEnterpriseName());
						if (contdata.getDatas() != null) {
							createForwarderListVO(contdata.getDatas() , vo);
						}
						if ("1".equals(list.get(i).getStatus())) {
							vo.setForwarderStatus("未完成");
						} else if ("2".equals(list.get(i).getStatus())) {
							vo.setForwarderStatus("已完成");
						} else {
							vo.setForwarderStatus(list.get(i).getStatus());
						}
						vo.setDealUuid(uuid);
						resultlist.add(vo);
					} else {
						LOG.info("货代委托查询订单信息接口失败:" + JSONObject.toJSONString(contdata));
					}

					//外部合同号

					TTransExtend tTransExtend = tTransExtendService.getTTransExtendByDealNo(null , list.get(i).getDealNo());
					if(tTransExtend != null){
						vo.setPurchaseContractNo(tTransExtend.getPurchaseContractNo());
					}

				}
			}

			resultDatas.setPageNum(list.getPageNum());
			resultDatas.setPageSize(list.getPageSize());
			resultDatas.setTotal(list.getTotal());
			resultDatas.setPageCount(list.getPages());
			resultDatas.setData(resultlist);
		} catch (Exception e) {
			e.printStackTrace();
			resultDatas.setFail("货代委托查询列表失败");
			resultDatas.setData(e.getMessage());
		}
		System.out.println("server返回参数:"+JSONObject.toJSONString(resultDatas));;
		return resultDatas;
	}


	@ResponseBody
	@RequestMapping("/get.json")
	public ResultDatas get(String uuid){
		ResultDatas resultDatas = new ResultDatas();
		Map<String , Object> result = new HashMap<>();
		Object detail = null;
		Long sofId = null;
		Long blId = null;
		Long id = null;
		try {
			if (!StringHelper.isNullOrEmptyString(uuid)) {
				com.sinochem.it.b2b.common.result.ResultDatas<ContractSheetWXVO> contdata = myContractDetailController.getContractSheetListByeuuideEN(uuid);
				if (0 == contdata.getStatus()) {
					detail = contdata.getDatas();
				} else {
					LOG.info("货代委托查询订单信息接口失败:" + gson.toJson(contdata));
				}

				String standard = contdata.getDatas().getQualityStandard();
				standard = prefixUrl + "/" + standard;
				contdata.getDatas().setQualityStandard(standard);
			} else {
				resultDatas.setFail("uuid不可以为空");
			}

			//查询任务
			TShipagentAppoint queryAppoint = new TShipagentAppoint();
			queryAppoint.setAliveFlag(Constants.ALIVE_FLAG_YES);
			queryAppoint.setDealUuid(uuid);
			List<TShipagentAppoint> appointList = appointTaskService.selectByCondition(queryAppoint);
			if(appointList != null && appointList.size() > 0){
				TShipagentAppoint appoint = appointList.get(0);
				id = appoint.getId();
				//查询提单
				TShipagentDocument queryDocument = new TShipagentDocument();
				queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
				queryDocument.setAppointId(appoint.getId());
				List<TShipagentDocument> documentList = documentService.get(queryDocument);
				if(documentList != null && documentList.size() > 0){
					sofId = documentList.get(0).getSofId();
					blId = documentList.get(0).getBlId();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultDatas.setFail("货代委托查询失败");
			resultDatas.setDatas(e.getMessage());
		}
		result.put("detail" , detail);
		result.put("sofId" , sofId);
		result.put("blId" , blId);
		result.put("id" , id);
		resultDatas.setDatas(result);
		return resultDatas;
	}


	/**
	 *
	 * 获取任务简单信息,
	 * 包括:
	 * <ul>
	 *     委托公司、外贸合同号、数量、油种、规格、装期、任务状态
	 * </ul>
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSimple.json")
	public ResultData getSimple( Long taskId){
		ResultData resultData = new ResultData();
		TShipagentAppoint appoint = appointTaskService.selectByPrimaryKey(taskId);

		if(appoint == null){
			resultData.setMessage("任务不存在");
			resultData.setStatus(Result.ERROR);
			return resultData;
		}
		ForwarderListVO vo =  new ForwarderListVO();
		try{
			if (!StringHelper.isNullOrEmptyString(appoint.getDealUuid())) {
				com.sinochem.it.b2b.common.result.ResultDatas<ContractSheetWXVO> contdata = myContractDetailController.getContractSheetListByeEpmemberid(appoint.getDealUuid());
				if (0 == contdata.getStatus()) {
					vo.setNomineeCompany(appoint.getAppointEnterpriseName());
					if (contdata.getDatas() != null) {
						createForwarderListVO(contdata.getDatas() , vo);
					}
					if ("1".equals(appoint.getStatus())) {
						vo.setForwarderStatus("已确认");
					} else if ("2".equals(appoint.getStatus())) {
						vo.setForwarderStatus("已完成");
					} else {
						vo.setForwarderStatus(appoint.getStatus());
					}
					vo.setDealUuid(appoint.getDealUuid());
				} else {
					resultData.setStatus(Result.ERROR);
					resultData.setMessage("货代委托查询订单信息接口失败");
					LOG.info("货代委托查询订单信息接口失败:" + JSONObject.toJSONString(contdata));
				}
				TTransExtend tTransExtend = tTransExtendService.getTTransExtendByDealNo(null , appoint.getDealNo());
				vo.setPurchaseContractNo(tTransExtend.getPurchaseContractNo());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		resultData.setData(vo);
		return resultData;
	}


	private void createForwarderListVO(ContractSheetWXVO wxvo,ForwarderListVO vo){
		vo.setCategory(wxvo.getProductCategory());
		vo.setSpecification(wxvo.getProductSpecification());
		vo.setQuantity(wxvo.getQuantity() + " " + wxvo.getPricingUnit());
		vo.setDealNO(wxvo.getSerialNumber());
		vo.setLaycan(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));

	}
}
