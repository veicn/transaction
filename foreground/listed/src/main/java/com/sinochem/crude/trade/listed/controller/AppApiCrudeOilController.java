package com.sinochem.crude.trade.listed.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.service.DemandMessageService;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandBiddingType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.enums.DemandType;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.form.CrudeOIlBiddingForm;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.DemandForm;
import com.sinochem.crude.trade.listed.model.form.IdForm;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilInfoShowVO;
import com.sinochem.crude.trade.listed.model.vo.DemandDetailVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipBerthVO;
import com.sinochem.crude.trade.listed.model.vo.DemandSpecifyEnterpriseVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BiddingDemandOutVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BiddingDemandVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BiddingOutVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BiddingVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BuyLeadsDemandOutVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BuyLeadsDemandVO;
import com.sinochem.crude.trade.listed.service.CrudeOilHallService;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.DemandOrderService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.utils.DateUtil;

/**
 * @ClassName: AppApiCrudeOilController
 * @Description: 原油APP接口维护
 * @author wangn
 * @date 2018-01-03 16:22:30
 * @version V1.0
 */
@Controller
@ApiSafeAccess
public class AppApiCrudeOilController {

    @Autowired
    private DemandService demandService;
    
    @Autowired
    private CrudeOilHallService crudeOilHallService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

	@Autowired
	private CrudeOilInfoService crudeOilInfoService;

    @Autowired(required=false)
    private DemandOrderService demandOrderService;
	
	@Autowired
	private DemandMessageService demandMessageService;

	Logger logger = LoggerFactory.getLogger(AppApiController.class);
    
    /**
	 * 采购需求列表页面
	 * @param vo
	 * @param user 当前用户
	 * @return
	 * @exception
	 */
	@RequestMapping(value= UrlMapping.APP_BUYINGLEADSDEMANDLISTAPP)
	@ResponseBody
	public ResultDatas buyingLeadsDemandListApp(BuyLeadsDemandVO vo, CurrentUser user) {
		ResultDatas res = new ResultDatas<>();
		
		// 登录验证
		if (user == null || user.getEpMemberId() == null) {
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0013));
			return res;
		}

		try {
			// 设置翻页
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageNum(vo.getPageNum());
			pageInfo.setPageSize(vo.getPageSize());
			
			// 设置查询条件
			ResourceQuery resourceQuery = new ResourceQuery();
			resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
	        resourceQuery.setType(Constant.DEMAND_TYPE_D);//需求单
	        resourceQuery.setDealType(DealType.BUY.getCode());//买
	        resourceQuery.setUserEnterpriseId(user.getEpMemberId());
	        resourceQuery.setStatus(vo.getStatus());

	        // 取得数据字典
	        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
	        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
	        
	        // 取得列表数据
	        PageInfoResult pageInfoResult = demandService.queryDemandJoinTableByCondition(resourceQuery, pageInfo);
	        List<DemandJoinResult> list = pageInfoResult.getList();
	        
	        // 输出列表数据
	        List<BuyLeadsDemandOutVO> listOut = new ArrayList<BuyLeadsDemandOutVO>();
	        
	        // 输出列表数据转换
	        if (list != null && list.size() > 0) {
	        	for (int i = 0; i < list.size(); i++) {
	        		DemandJoinResult table = list.get(i);
	        		BuyLeadsDemandOutVO out = new BuyLeadsDemandOutVO();
	        		out.setId(table.getId());
					out.setStatus(table.getStatus());
	        		// 创建日期
					out.setCreateTime(DateUtil.formatDate(table.getCreateTime()));

	        		// 单号
	        		out.setDemandNo(table.getUuid());
	        		out.setOil(table.getCrudeOilOptions());
	        		
	        		// 数量(万桶)
	        		Long num = table.getNum();
	        		
	        		// 数量判断
	        		if (num != null) {
	        			DecimalFormat df = new DecimalFormat("#0");
	        			double numD = num/10000000.0;
	        			out.setNum(df.format(numD));
	        		}
	        		
	        		// 贸易条款
	        		Integer tradeItem = table.getTradeItem();
	        		String tradeItemStr = tradeItemMap.get(tradeItem);
	        		out.setTradeItem(tradeItemStr);
	        		
	        		// 采购类型
	        		Integer purchaseType = table.getPurchaseType();
					out.setPurchaseType(purchaseType);
	        		// 采购类型判断
	        		if (purchaseType != null) {
						if (purchaseType == 1) {
							// "招标"
							out.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0135));
						} else if (purchaseType == 2) {
							// "询价"
							out.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0136));
						}
					}
					// 发布日期
					out.setPubDate(DateUtil.formatDate(table.getPubDate()));
	        		
	        		// 到货期
					if (table.getDischargeStartTime() != null && table.getDischargeEndTime() != null) {
						out.setArrivalDate(DateUtil.formatDate(table.getDischargeStartTime()) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(table.getDischargeEndTime()));
	                } else {
	                	out.setArrivalDate("");
	                }
					
	        		// 付款日期
	        		String payItem = table.getPayItem();
	        		String payItemStr = payItem;
	        		out.setPayItem(payItemStr);
	        		
	        		// 投标截止日期
					out.setStopBidTime(DateUtil.formatDate(table.getStopBidTime()));
	        		
					// 公布中标日期
					out.setTenderOutTime(DateUtil.formatDate(table.getTenderOutTime()));

	        		listOut.add(out);
	        	}
	        }
			// 设定返回数据
			res.setTotal(pageInfoResult.getTotal());
			res.setPageSize(vo.getPageSize());
			res.setPageNum(vo.getPageNum());
			res.setDatas(listOut);
			res.setStatus(0);
			// "成功"
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
			
		} catch (Exception e) {
			logger.error("buyingLeadsDemandListApp error", e);
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
		}
		
		return res;
	}
    
    /**
	 * 报价列表页面
	 * @param vo
	 * @param user 当前用户
	 * @return
	 * @exception
	 */
	@RequestMapping(value=UrlMapping.APP_BIDDINGLISTAPP)
	@ResponseBody
	public ResultDatas biddingListApp(BiddingVO vo, CurrentUser user) {

		ResultDatas res = new ResultDatas<>();
		
		// 登录验证
		if (user == null || user.getEpMemberId() == null) {
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0013));
			return res;
		}
		
		try {
			// 设定翻页设定
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageNum(vo.getPageNum());
			pageInfo.setPageSize(vo.getPageSize());
			
			// 设定查询条件
			ResourceQuery resourceQuery = new ResourceQuery();
			resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
			resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
	        resourceQuery.setType(DemandType.BIDDING.getCode());//报价
	        resourceQuery.setDealType(DealType.SELL.getCode());//卖
	        /*resourceQuery.setUserEnterpriseId(user.getEpMemberId());*/
	        resourceQuery.setUserId(user.getEpMemberId());
	        // 仅查询当前用户发出的报价单
	        resourceQuery.setProviderEnterpriseId(user.getEpMemberId());
	        resourceQuery.setStatus(vo.getStatus());
	        
	        // 取得列表数据
	        PageInfoResult pageInfoResult = demandService.queryDemandBiddingJoinTableByCondition(resourceQuery, pageInfo);
	        List<DemandJoinResult> list = pageInfoResult.getList();
	        
	        // 设定数据字典
	        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
	        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
	        Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
	        Map<Object, String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
	        
	        // 输出列表
	        List<BiddingOutVO> listOut = new ArrayList<BiddingOutVO>();

	        // 输出列表转换
	        if (list != null && list.size() > 0) {
	        	for (int i = 0; i < list.size(); i++) {
	        		DemandJoinResult table = list.get(i);
	        		BiddingOutVO out = new BiddingOutVO();
	        		out.setId(table.getId());
					out.setStatus(table.getStatus());
	        		// 创建日期
					out.setCreateTime(DateUtil.formatDate(table.getCreateTime()));

	        		// 单号
	        		out.setDemandNo(table.getUuid());
	        		
	        		// 采购需求方
	        		out.setbEnterpriseName(table.getbEnterpriseName());
	        		
	        		String crudeOilOptions = table.getCrudeOilOptions();
	        		out.setOil(crudeOilOptions);
	        		
	        		// 数量(万桶)
	        		Long num = table.getNum();
	        		
	        		// 数量处理
	        		if (num != null) {
	        			DecimalFormat df = new DecimalFormat("#0");
	        			double numD = num/10000000.0;
	        			out.setNum(df.format(numD));
	        		}
	        		
	        		// 贸易条款
	        		Integer tradeItem = table.getTradeItem();
	        		String tradeItemStr = tradeItemMap.get(tradeItem);
	        		out.setTradeItem(tradeItemStr);
	        		
	        		// 计价基准
	        		String valuationBase = table.getValuationBase();
	        		String valuationBaseStr = valuationBaseMap.get(valuationBase);
	        		out.setValuationBase(valuationBaseStr);
	        		
	        		// 付款日期
	        		String payItem = table.getPayItem();
	        		String payItemStr = payItem;
	        		out.setPayItem(payItemStr);
	        		
	        		// 计价期
	        		Integer valuationProidType = table.getValuationProidType();
					String valuationProidTypeStr = "";
					
					// 计价期判断
	        		if (valuationProidType != null) {
	        			if (valuationProidType == 4) {
							valuationProidTypeStr =
								valuationProidTypeMap.get(valuationProidType) +
								"("
								+ DateUtil.formatDate(table.getValuationProidStart())
								+ VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
								+ DateUtil.formatDate(table.getValuationProidEnd())
								+")";
						} else if (valuationProidType == 5) {
							valuationProidTypeStr =
									valuationProidTypeMap.get(valuationProidType) +
											"("
											+ table.getContractKind()
											+")";
						} else {
							valuationProidTypeStr = valuationProidTypeMap.get(valuationProidType);
						}
					}
	        		
					out.setValuationProidType(valuationProidTypeStr);

	        		// 到货期
					if (table.getDischargeStartTime() != null && table.getDischargeEndTime() != null) {
						out.setArrivalDate(DateUtil.formatDate(table.getDischargeStartTime()) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(table.getDischargeEndTime()));
	                } else {
	                	out.setArrivalDate("");
	                }
					
	        		// 计价公式
	        		String valuationFormulaStr = table.getValuationFormula();
	        		out.setValuationFormula(valuationFormulaStr);


					// 展示LOGO的企业ID
					if (user.getEpMemberId() != null && user.getEpMemberId() == table.getPeMemberId()) { // 卖家查看
						out.setImgEMemberId(table.getBeMemberId());
					} else {
						out.setImgEMemberId(table.getPeMemberId());
					}

					// 报价类型，1-意向报价，2-正式报价
					out.setBiddingType(table.getBiddingType());
					
	        		listOut.add(out);
	        	}
	        }

			// 设定返回数据
			res.setTotal(pageInfoResult.getTotal());
			res.setPageSize(vo.getPageSize());
			res.setPageNum(vo.getPageNum());
			res.setDatas(listOut);
			res.setStatus(0);
			// "成功"
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
			
		} catch (Exception e) {
			logger.error("biddingListApp error", e);
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
		}
		
		return res;
	}
    
	/**
	 * 需求的报价列表
	 * @param vo
	 * @param user 当前用户
	 * @return
	 * @exception
	 */
	@RequestMapping(value=UrlMapping.APP_BIDDINGDEMANDLISTAPP)
	@ResponseBody
	public ResultDatas biddingDemandListApp(BiddingDemandVO vo, CurrentUser user) {

		ResultDatas res = new ResultDatas<>();
		
		// 登录验证
		if (user == null || user.getEpMemberId() == null) {
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0013));
			return res;
		}
		
		try {
			// 设置翻页
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageNum(vo.getPageNum());
			pageInfo.setPageSize(vo.getPageSize());
			
			// 设置查询条件
			ResourceQuery resourceQuery = new ResourceQuery();
			resourceQuery.setParentId(vo.getDemandId());

			// 取得数据字典
	        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
	        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
	        Map<Object, String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
	        
	        // 取得列表数据
	        PageInfoResult pageInfoResult = demandService.queryDemandBiddingJoinTableByCondition(resourceQuery, pageInfo);
	        List<DemandJoinResult> list = pageInfoResult.getList();
	        
	        List<BiddingDemandOutVO> listOut = new ArrayList<BiddingDemandOutVO>();
	        
	        // 输出列表转换
	        if (list != null && list.size() > 0) {
	        	for (int i = 0; i < list.size(); i++) {
	        		DemandJoinResult table = list.get(i);
	        		BiddingDemandOutVO out = new BiddingDemandOutVO();
	        		out.setId(table.getId());
					out.setStatus(table.getStatus());
	        		// 报价单编号
	        		out.setDemandNo(table.getUuid());

					out.setCreateTime(DateUtil.formatDate(table.getCreateTime()));
	        		
	        		// 供应商
	        		out.setpEnterpriseName(table.getpEnterpriseName());
	        		
	        		// 油种
	        		String crudeOilOptions = table.getCrudeOilOptions();
	        		out.setOil(crudeOilOptions);
	        		
	        		// 数量(万桶)
	        		Long num = table.getNum();
	        		
	        		// 数量判断
	        		if (num != null) {
	        			DecimalFormat df = new DecimalFormat("#0");
	        			double numD = num/10000000.0;
	        			out.setNum(df.format(numD));
	        		}
	        		
	        		// 贸易条款
	        		Integer tradeItem = table.getTradeItem();
	        		String tradeItemStr = tradeItemMap.get(tradeItem);
	        		out.setTradeItem(tradeItemStr);
	        		
	        		// 计价公式
	        		String valuationFormulaStr = table.getValuationFormula();
	        		out.setValuationFormula(valuationFormulaStr);
	        		
	        		// 计价期
					Integer valuationProidType = table.getValuationProidType();
					String valuationProidTypeStr = "";
					
					// 计价期判断
					if (valuationProidType != null) {
						if (valuationProidType == 4) {
							valuationProidTypeStr =
								valuationProidTypeMap.get(valuationProidType) +
									"("
									+ DateUtil.formatDate(table.getValuationProidStart())
									+ VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
									+ DateUtil.formatDate(table.getValuationProidEnd())
									+")";
						} else if (valuationProidType == 5) {
							valuationProidTypeStr =
									valuationProidTypeMap.get(valuationProidType) +
											"("
											+ table.getContractKind()
											+")";
						} else {
							valuationProidTypeStr = valuationProidTypeMap.get(valuationProidType);
						}
					}
					
					out.setValuationProidType(valuationProidTypeStr);

	        		// 付款日期
	        		String payItem = table.getPayItem();
	        		String payItemStr = payItem;
	        		out.setPayItem(payItemStr);
	        		
	        		// 到货期开始
					out.setDischargeStartTime(DateUtil.formatDate(table.getDischargeStartTime()));

	        		// 到货期结束
					out.setDischargeEndTime(DateUtil.formatDate(table.getDischargeEndTime()));

	        		// 采购类型
	        		out.setStatus(table.getStatus());
	        		
	        		// 报价类型，1-意向报价，2-正式报价
					out.setBiddingType(table.getBiddingType());
					
	        		listOut.add(out);
	        	}
	        }
	        
			// 设定返回数据
			res.setTotal(pageInfoResult.getTotal());
			res.setPageSize(vo.getPageSize());
			res.setPageNum(vo.getPageNum());
			res.setDatas(listOut);
			res.setStatus(0);
			// "成功"
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
			
		} catch (Exception e) {
			logger.error("biddingDemandListApp error", e);
			res.setStatus(9999);
			res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
		}
		
		return res;
	}
	@Autowired
	private URLBroker listedServer;
	@Autowired
	private URLBroker messageServer;
	/**
     * 保存需求单  保存或发布（status:1保存 2发布）
     * @param
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_SAVEDEMAND)
    @ResponseBody
    public ResultDatas saveProcurementDemand(@RequestBody DemandForm demandForm, CurrentUser currentUser ) {
    	ResultDatas res = new ResultDatas();
    	
    	// 定向发布企业
    	demandForm.setSpecifyEnterpriseList();
		List<CrudeOilForm> oilList = demandForm.getCrudeOilFormList();
		List<CrudeOilForm> oilFormList = new ArrayList<CrudeOilForm>();
		if(CollectionUtils.isNotEmpty(oilList)){
			if(demandForm.getDemand().getPublishType().equals(1)){
				for (CrudeOilForm  crudeOil: oilList) {
					Long id = crudeOil.getPropertyId();
					if(id != null){
						CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(id);
						crudeOilInfoVO.setDesc(crudeOil.getDesc());
						CrudeOilForm cof = CrudeOilForm.convertVoToForm(crudeOilInfoVO);
						oilFormList.add(cof);
					}else{
						res.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0015));
						return res;
					}
				}

				demandForm.setCrudeOilFormList(oilFormList);
			}
		} else {
			res.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0016));
			return res;
		}

        try {
        	// 画面校验
        	Map<String, String> errMap = validForm(demandForm);
            if (errMap != null && errMap.size() > 0) {
            	 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("errorMsg", errMap);
                 res.setDatas(map);
                 res.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0017));
                 
                 return res;
            }
			if(demandForm.getBuyerRelevanter() == null || StringUtil.isEmpty(demandForm.getBuyerRelevanter().getEnterpriseName())){
				res.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0018));
				return res;
			}
            List list = new ArrayList();
			demandForm.getBuyerRelevanter().seteMemberId(currentUser.getEpMemberId());
			demandForm.getBuyerRelevanter().setType(DemandRelevanterType.BUYER.getCode());
            list.add(DemandRelevanterBuyerVO.convertVoToDomain(demandForm.getBuyerRelevanter()));

            List<DemandShipBerth> demandShipBerth = DemandShipBerthVO.convertListToDomain(demandForm.getDemandShipBerthList());
            DemandShip demandShip = new DemandShip();
            
            if (demandForm.getDemandShip() != null) {
            	demandShip = demandForm.getDemandShip().convertVoToDomain();
            }
            
            // 采购需求指定发布  by sijiliu 2018-01-18
            List<DemandSpecifyEnterprise> specifyEnterpriseList = CollectionUtils.isNotEmpty(demandForm.getSpecifyEnterpriseList()) ?
                    DemandSpecifyEnterpriseVO.convertListToDomain(demandForm.getSpecifyEnterpriseList()) : null;
		    
            Demand demand = crudeOilHallService.saveProcurementDemand(DemandVO.convertToDomain(demandForm.getDemand()),
                    demandShipBerth,
                    list,
                    demandForm.getIdForm(),
                    demandForm.getCrudeOilFormList(),
                    DemandDetailVO.convertListToDomain(demandForm.getDemandDetailList()),
                    demandShip,
                    currentUser,
                    specifyEnterpriseList);
            
            Map<String, Object> map = new HashMap<String, Object>();
            IdForm idForm = demandForm.getIdForm();
            map.put("idForm", idForm);

            res.setDatas(map);
            
            // 状态为发布时
            if (DemandStatus.DEMAND_STATUS_2.getCode() == demandForm.getDemand().getStatus()) {
            	//向定向企业发送请求
				demandMessageService.demandDirectionalRelease(demand.getId());
            }
        } catch (BizException e) {
			logger.error("saveProcurementDemand error", e);
			res.setFail(e.getMessage());
			return res;
        } catch (Exception ex) {
			logger.error("saveProcurementDemand error", ex);
        	res.setFail(ex.getMessage());
        	return res;
        }
        
        // 返回
        res.setStatus(Result.SUCCESS);
        // "保存成功"
		res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
		
		return res;
    }
    
    /**
     * 后台验证采购需求
     * @param demandForm
     */
    private Map<String, String> validForm(DemandForm demandForm) {
    	Map<String, String> errMap = new HashMap<String, String>();

		DemandRelevanterBuyerVO buyerRelevanter = demandForm.getBuyerRelevanter();
        
        if (buyerRelevanter != null) {
//            String phoneNo = buyerRelevanter.getPhoneNo();
//            if (StringUtils.isNotBlank(phoneNo) && !Pattern.matches("^((0\\d{2,3}-\\d{7,8})|(1\\d{10}))$", phoneNo)) {
//            	errMap.put("buyerRelevanter.phoneNo", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0019));
//            }
            
            String email = buyerRelevanter.getFamil();
            if (StringUtils.isNotBlank(email) && !Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$", email)) {
            	errMap.put("buyerRelevanter.famil", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0020));
            }
        }
        
        List<DemandShipBerthVO> list = demandForm.getDemandShipBerthList();
        
        if (list != null) {
        	for (int i=0; i<list.size(); i++) {
                if (StringUtils.isBlank(list.get(i).getBerthName())) {
                    list.remove(i);
                }
            }
        }
        
        demandForm.setDemandShipBerthList(list);
        
//        int i = 0;
//        for (CrudeOilForm dd : demandForm.getCrudeOilFormList()) {
//            if ("".equals(dd.getName())) {
//                errMap.put("crudeOilFormList["+i+"].name", "请输入采购基本信息！");
//            }
//            i++;
//        }
        
        if (null == demandForm.getCrudeOilFormList() || demandForm.getCrudeOilFormList().size() == 0) {
        	errMap.put("crudeOilFormList[0].indicator1Min", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0021));
        }
        
        if(StringUtils.isEmpty(demandForm.getDemand().getPayItem())) {
        	errMap.put("demand.payItem", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0022));
        }
        
        if (demandForm.getDemand().getValuationProidType() == 4) {
            if (demandForm.getDemand().getValuationProidStart() == null) {
            	errMap.put("demand.valuationProidStart", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0023));
            }
            
            if (demandForm.getDemand().getValuationProidEnd() == null) {
            	errMap.put("demand.valuationProidEnd", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0024));
            }
        }
        
        if (demandForm.getDemandShip().getDischargeStartTime() != null && demandForm.getDemandShip().getDischargeEndTime() != null) {
            if (demandForm.getDemandShip().getDischargeEndTime().getTime() < demandForm.getDemandShip().getDischargeStartTime().getTime()) {
                errMap.put("demandShip.dischargeStartTime", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0025));
            }
        }
        
        if (demandForm.getDemandShip().getShipmentStartTime() != null && demandForm.getDemandShip().getShipmentEndTime() != null) {
            if (demandForm.getDemandShip().getShipmentStartTime().getTime() > demandForm.getDemandShip().getShipmentEndTime().getTime()) {
                errMap.put("demandShip.shipmentStartTime", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0026));
            }
        }

        if (demandForm.getDemand().getSpecified() != null && demandForm.getDemand().getSpecified() == 1) {
            if (StringUtil.isEmpty(demandForm.getEpMemberIds())) {
            	errMap.put("epMemberIds", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0027));
            }
        }

        return errMap;
    }
    
    /**
     * 保存（投标、报价、正式报价）
     * @param currentUser
     * @param biddingForm
     * @return
     */
    @RequestMapping(value=UrlMapping.APP_QUOTETYPE)
    @ResponseBody
    public ResultDatas updateType(@RequestBody CrudeOIlBiddingForm biddingForm, CurrentUser currentUser) {
    	ResultDatas res = new ResultDatas();
    	Long demandId = biddingForm.getBidding().getParentId();
    	
    	try {
            // 验证从页面过来的报价数据
        	Map<String, String> errMap = validBiddingForm(biddingForm, currentUser.getEpMemberId());
            if (errMap != null && errMap.size() > 0) {
            	 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("errorMsg", errMap);
                 res.setDatas(map);
                 res.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0028));
                 return res;
            }
            
            // 保存报价数据
            Demand biddingDemand = crudeOilHallService.saveQuote(currentUser, biddingForm);
            // 发布报价消息
			demandMessageService.biddingRelease(biddingDemand.getId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("biddingId", biddingDemand.getId());

            res.setDatas(map);
            
        } catch (BizException e) {
			logger.error("updateType error", e);
        	res.setFail(e.getMessage());
			return res;
        } catch (Exception ex) {
			logger.error("updateType error", ex);
        	res.setFail(ex.getMessage());
			return res;
        }
    	
    	// 返回
        res.setStatus(Result.SUCCESS);
        // "保存成功!"
		res.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
		return res;
    }

	/**
	 * 查询原油详情
	 * @param crudeName
	 * @return
	 */
	@RequestMapping(value = UrlMapping.APP_QUERYCRUDEOILINFOS)
	@ResponseBody
	public ResultDatas queryCrudeOilInfos(String crudeName) {
    	ResultDatas resultDatas = new ResultDatas();

		try {
			List<CrudeOilInfoVO> vos = crudeOilInfoService.findCrudeOilInfos(crudeName, null);
			List<CrudeOilInfoShowVO> list = new ArrayList<CrudeOilInfoShowVO>();
			for (CrudeOilInfoVO vo : vos) {
				CrudeOilInfoShowVO cos = CrudeOilInfoShowVO.convertToShowVO(vo);
				list.add(cos);
			}

			resultDatas.setDatas(list);

		} catch (Exception ex) {
			logger.error("",ex);
			resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0029));
		}

		return resultDatas;
	}
    
    /**
     * 后台验证原油报价
     * @param biddingForm
     * @param currentEpmemberId
     */
    private Map<String, String> validBiddingForm(CrudeOIlBiddingForm biddingForm, Long currentEpmemberId){
    	// 错误消息
    	Map<String, String> errMap = new HashMap<String, String>();
    	
    	Demand demands = demandService.getDemandByKey(biddingForm.getBidding().getParentId());
        
    	if (demands != null) {
    		if (currentEpmemberId != null) {
    			// 判断当前用户是否是创建需求的用户
                if (currentEpmemberId != null && currentEpmemberId.equals(demands.getCreater())) {
                	errMap.put("epMemberId", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0030));
                }

                //DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(
                //        demands.getId(), DemandRelevanterType.SUPPLIER.getCode());
                
                // 判断当前用户是否已经报价
                //if (demandRelevanter1 != null && currentEpmemberId != null && currentEpmemberId.equals(demandRelevanter1.getEMemberId())) {
                //	errMap.put("epMemberId", "当前用户已经报价！");
                //}
    		} else {
    			errMap.put("epMemberId", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0031));
    		}
    		
    	} else {
    		errMap.put("demandInfo", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0032));
    	}
        
        return errMap;
    }
    
    /**
     * 确认中标
     * @param demandId
     * @param biddingType
     * @return
     */
    @RequestMapping(value=UrlMapping.APP_ACCEPTBIDDING)
	@ResponseBody
	public ResultDatas biddingListApp(Long demandId, Integer biddingType, CurrentUser user) {
    	String errorMessage = null;
    	ResultDatas resultDatas = new ResultDatas();
    	
        //确认意向中标
        if (DemandBiddingType.INTENTION_BIDDING.getCode().equals(biddingType)) {
            try {
                Long parentDemandId = demandService.confirmIntentionBiddingAndRemind(demandId, user);
            } catch (Exception e) {
            	errorMessage = e.getMessage();
            }
        } else if (DemandBiddingType.ACTUAL_BIDDING.getCode().equals(biddingType)) {
        	// 确认中标
            String orderNo = "";
            try {
                orderNo = demandService.confirmActualBiddingAndRemind(demandId, "", user);
                if (StringUtils.isEmpty(orderNo)) {
                    errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0033);
                }
            } catch (Exception e) {
            	errorMessage = e.getMessage();
            }
            if(StringUtil.isBlank(errorMessage)){
            	// 确认订单
                try {
                    demandOrderService.confirmContract(orderNo, user.getMemberId(), null);
                } catch (Exception e) {
                	errorMessage = e.getMessage();
                }
            }
        } else {
        	errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0034);
        }
        
        if(StringUtil.isBlank(errorMessage)){
        	resultDatas.setStatus(0);
        	// "成功"
        	resultDatas.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088));
        } else {
        	resultDatas.setStatus(9999);
        	resultDatas.setMessage(errorMessage);
        }
        
        return resultDatas;
    }
}
