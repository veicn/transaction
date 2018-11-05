package com.sinochem.crude.trade.shipping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sinochem.crude.trade.transaction.remote.ContractSheetRemoteService;
import com.sinochem.crude.trade.transaction.vo.ContractSheetRemoteVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.AgreementQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatAgreementQuery;
import com.sinochem.crude.trade.shipping.model.vo.AgreementVO;
import com.sinochem.crude.trade.shipping.service.AgreementService;
import com.sinochem.crude.trade.shipping.service.DemandsService;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.shipping.service.SysShipService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
@WithoutAccess
@RequestMapping(value = UrlMapping.MANAGE_AGREEMENT)
public class AgreementController {
    @Value("${aliyun.oss.endpoint}")
    private String imgurl;

	@Autowired
	private AgreementService agreementService;
	
	@Autowired
	private DemandsService demandsService;
	
	@Autowired
	private ShipDictionaryService shipDictionaryService;
	
	@Autowired
	private SysShipService sysShipService;

	@Autowired
	private ContractSheetRemoteService contractSheetRemoteService;

	// 中化新
	@Value("${id.singapore}")
	private Long idSingapore;

	// 泉炼
	@Value("${id.quanzhou}")
	private Long idQuanzhou;

	public static final Log log = LogFactory.getLog(AgreementController.class);
	
//	/**
//	 * 租船协议列表(贸易商) 微信端API (bug)
//	 * wh
//	 * @param
//	 * @param
//	 * @return
//	 */
//	@WithoutAccess
//	@ResponseBody
//	@RequestMapping(value = UrlMapping.WECHAT_QUERY_AGREEMENT_MYS)
//	public ResultDatas<List<Agreement>> getAgreementListMys(WechatAgreementQuery query, CurrentUser user){
//		ResultDatas<List<Agreement>> res = new ResultDatas<>();
//		try {
//
//			List<Agreement> list = agreementService.getAgreementListtrader(query);
//			res.setDatas(list);
//		}  catch (Exception e) {
//			log.error("getAgreementListMys error", e);
//			res.setMessage("系统异常");
//		}
//		return res;
//	}
    /**
     * 租船协议列表(贸易商) 微信端API
     * wh
     * @param
     * @param
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = UrlMapping.WECHAT_QUERY_AGREEMENT_MYS) //, CurrentUser user
    public ResultDatas<List<AgreementVO>> getAgreementListMys(WechatAgreementQuery query1){
        ResultDatas<List<AgreementVO>> res = new ResultDatas<>();
       // List<AgreementVO> listvo=new ArrayList<>();
        try {
			AgreementQuery query = new AgreementQuery();
			// 泉炼得场合可以查看中化新得订单
			//PageInfoResult pageInfoResult = null;
			List<Agreement> list=null;
			if(idQuanzhou.equals(query1.getEpMemberId())) {
//				query.setRoleId(user.getEpMemberId());
				query.setRoleId(query1.getEpMemberId());
				query.setSingaporeId(idSingapore);
				query.setProductLoadingAndDischarge(query1.getProductLoadingAndDischarge());
				list = agreementService.queryDemandsQuanlianByEntitysListWX(query);
			} else {
				//query.setRoleId(user.getEpMemberId());
				list = agreementService.getAgreementListtrader(query1);
			}
			//List<Agreement> list = pageInfoResult.getList();
			List<AgreementVO> voList = new ArrayList<>();
			if(!list.isEmpty()){
				for(Agreement agreement : list){
					if (agreement != null) {
						//获取绝对路径
						if(!StringHelper.isNullOrEmptyString(agreement.getUploadQ88())) {
							agreement.setUploadQ88(imgurl + "/" + agreement.getUploadQ88());
						}
						if(!StringHelper.isNullOrEmptyString(agreement.getUploadCp())) {
							agreement.setUploadCp(imgurl + "/" + agreement.getUploadCp());
						}
//						if (agreement.getUploadQ88().length() > 0) {
//							agreement.setUploadQ88(imgurl + "/" + agreement.getUploadQ88());
//						}
//						if (agreement.getUploadCp().length() > 0) {
//							agreement.setUploadCp(imgurl + "/" + agreement.getUploadCp());
//						}
					}
					AgreementVO vo = new AgreementVO();
					AgreementVO domainToVO = vo.domainToVO(agreement);
					// 泉炼得场合
					if (idQuanzhou.equals(query1.getEpMemberId()) &&
							idSingapore.equals(agreement.getSellerId())) {
						domainToVO.setQuanzhouFlag("1");
					}
					voList.add(domainToVO);
				}
			}
			res.setDatas(voList);

           // List<Agreement> list = agreementService.getAgreementListtrader(query);
//            for (Agreement item : list) {
//                if (item != null) {
//                    //获取绝对路径
//                    if (item.getUploadQ88().length() > 0) {
//                        item.setUploadQ88(imgurl + "/" + item.getUploadQ88());
//                    }
//                    if (item.getUploadCp().length() > 0) {
//                        item.setUploadCp(imgurl + "/" + item.getUploadCp());
//                    }
//                }
//                AgreementVO objectVo = new AgreementVO(item);
//                listvo.add(objectVo);
//            }
          //  res.setDatas(listvo);
        }  catch (Exception e) {
            log.error("getAgreementListMys error", e);
            res.setMessage("系统异常");
        }
        return res;
    }
	
	/**
	 * 租船协议列表(租船代理) 微信端API
	 * wh
	 * @param 
	 * @param 
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_QUERY_AGREEMENT_ZCDL)
	public ResultDatas<List<AgreementVO>> getAgreementListZcdl(@RequestBody WechatAgreementQuery query, CurrentUser user){
//		ResultDatas<List<Agreement>> res = new ResultDatas<>();
        ResultDatas<List<AgreementVO>> res = new ResultDatas<>();
        List<AgreementVO> listvo=new ArrayList<>();
		try {

            List<Agreement> list = agreementService.getAgreementList(query);
            for (Agreement item : list) {
                if (item != null) {
                    //获取绝对路径
					if(!StringHelper.isNullOrEmptyString(item.getUploadQ88())) {
						item.setUploadQ88(imgurl + "/" + item.getUploadQ88());
					}
					if(!StringHelper.isNullOrEmptyString(item.getUploadCp())) {
						item.setUploadCp(imgurl + "/" + item.getUploadCp());
					}
//                    if (item.getUploadQ88().length() > 0) {
//                        item.setUploadQ88(imgurl + "/" + item.getUploadQ88());
//                    }
//                    if (item.getUploadCp().length() > 0) {
//                        item.setUploadCp(imgurl + "/" + item.getUploadCp());
//                    }
                }

                AgreementVO objectVo = new AgreementVO(item);
                listvo.add(objectVo);
            }
            res.setDatas(listvo);
//			res.setDatas(list);
		}  catch (Exception e) {
			log.error("getAgreementListZcdl error", e);
			res.setMessage("系统异常");
		}
		return res;
	}
	
	/**
	 * 协议详情查看(uuid)
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = UrlMapping.Detail_INDEX)
	public void findAgreementDetailByUuid(String uuid,ModelMap model, CurrentUser user) {
			Result result = new Result();
		
			try {
				
				//判断用户状态
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				
				if(StringUtils.isNotBlank(uuid)){
					Agreement agreement = agreementService.findByUuid(uuid);
					if(StringUtils.isEmpty(agreement.getUploadQ88FileNm())){
						agreement.setUploadQ88FileNm(null);
					}
					if(StringUtils.isEmpty(agreement.getUploadCpFileNm())){
						agreement.setUploadCpFileNm(null);
					}
					AgreementVO objectVo = new AgreementVO(agreement);
					String fileFormatQ88 = objectVo.getUploadQ88FileNm();
					String fileFormatCp = objectVo.getUploadCpFileNm();
					if (StringUtils.isNotEmpty(fileFormatQ88)) {
						int beginIndex = fileFormatQ88.indexOf(".");
						objectVo.setFileFormat1(objectVo.getUploadQ88FileNm().substring(beginIndex+1));
					}
					if (StringUtils.isNotEmpty(fileFormatCp)) {
						int beginIndex = fileFormatCp.indexOf(".");
						objectVo.setFileFormat2(objectVo.getUploadCpFileNm().substring(beginIndex+1));
					}
					model.put("objectVo", objectVo);
				}
			} catch (TransportException e) {
				log.error("AgreementDetailsQuery error", e);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setMessage(e.getMessage());
			}
		}
			
	/**
	 * 保存租船协议数据界面的展示
	 * @param uuid
	 */
	@RequestMapping(value = UrlMapping.ADD_INDEX)
	public void demandsObject(String uuid,ModelMap model,CurrentUser user){
		Result result = new Result();
		
		try {
			//判断用户状态
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			if(StringUtils.isNotBlank(uuid)){
				Demands demands = demandsService.findByUuid(uuid);
				List<SysShip> sysList = sysShipService.findAllList();
				model.put("demands", demands);
				model.put("sysList", sysList);
			}
		} catch (Exception e) {
			log.error("ModificationAgreementQuery error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(e.getMessage());
		}
	}
	
	/**
	 * 更新租船协议界面的生成
	 * @param uuid
	 */
	@RequestMapping(value = UrlMapping.UPDATE_INDEX)
	public void agreementObject(String uuid,ModelMap model,CurrentUser user){
		Result result = new Result();
		
		try {
			
			//判断用户状态
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			if(StringUtils.isNotBlank(uuid)){
				Agreement agreement = agreementService.findByUuid(uuid);

				if(",".equals(agreement.getUploadQ88())){
					agreement.setUploadQ88("");
				}
				List<SysShip> sysList = sysShipService.findAllList();
				AgreementVO agreementVO = new AgreementVO(agreement);
				model.put("sysList", sysList);
				model.put("agreement", agreementVO);
			}
		} catch (Exception e) {
			log.error("ModificationAgreementQuery error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(e.getMessage());
		}
	}
	
	/**
	 * 生成/修改代理协议
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_AGREEMENT)
	public void saveAgreement(AgreementVO vo,CurrentUser user,HttpServletResponse response) {
		Result result = new Result();
		
		try {
			
			//判断用户是否登录
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			//路径判断
			if(",".equals(vo.getUploadQ88())){
				vo.setUploadQ88(null);
			}else{
				String removeStr = ",";
				vo.setUploadQ88(vo.getUploadQ88().replace(removeStr, ""));
			}
			
			if(StringUtils.isNotEmpty(vo.getUuid())){
				//租船协议存在，修改
				Integer code1 = agreementService.updateAgreement(vo,user);
				if (code1 !=null && code1>0) {
					response.getWriter().write(Constants.MODIFY_TRU);
					response.getWriter().close();
				} else {
					response.getWriter().write(Constants.MODIFY_FALSE);
					response.getWriter().close();
				}
			}else{
				//租船协议不存在，生成
				Integer code2 = agreementService.saveAgreement(vo,user);
				if (code2 != null && code2 > 0) {
					response.getWriter().write(Constants.SAVE_TRU);
					response.getWriter().close();
				} else {
					response.getWriter().write(Constants.SAVE_FALSE);
					response.getWriter().close();
				}
			}
			
			} catch (IOException e) {
				log.error("ModificationAgreement error", e);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("ModificationAgreement error", e);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setMessage(e.getMessage());
			}
	}
	
	
	/**
	 *  租船确认单信息 页面展示
	 * @param uuid
	 */
	@RequestMapping(value = UrlMapping.CONFIRM_DETAIL_INDEX)
	public void confirmAgreement(String uuid,ModelMap model,CurrentUser user){
		Result result = new Result();
		
			try {
				//判断用户状态
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				
					if(StringUtils.isNotBlank(uuid)){
						Agreement agreement = agreementService.findByUuid(uuid);
						
						//附件地址判断空/null
						if(StringUtils.isEmpty(agreement.getUploadQ88FileNm())){
							agreement.setUploadQ88FileNm(null);
						}
						if(StringUtils.isEmpty(agreement.getUploadCpFileNm())){
							agreement.setUploadCpFileNm(null);
						}
						AgreementVO objectVo = new AgreementVO(agreement);
						String fileFormatQ88 = objectVo.getUploadQ88FileNm();
						String fileFormatCp = objectVo.getUploadCpFileNm();
						if (StringUtils.isNotEmpty(fileFormatQ88)) {
							int beginIndex = fileFormatQ88.indexOf(".");
							objectVo.setFileFormat1(objectVo.getUploadQ88FileNm().substring(beginIndex+1));
						}
						if (StringUtils.isNotEmpty(fileFormatCp)) {
							int beginIndex = fileFormatCp.indexOf(".");
							objectVo.setFileFormat2(objectVo.getUploadCpFileNm().substring(beginIndex+1));
						}
						model.put("objectVo", objectVo);
					}
			} catch (TransportException e) {
				log.error("ModificationAgreement error", e);
				result.setStatus(Constants.EXCEPTION_STATUS);
				result.setMessage(e.getMessage());
			}
		}
	
	/**
	 * 租船协议确认
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = UrlMapping.CONFIRM_AGREEMENT)
	public void confirmAgreement(AgreementVO vo,CurrentUser user,HttpServletResponse response) {
		Integer code = null;
		Result result = new Result();
		
		try {
			
			// 验证登录信息
            if (user == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            
            if(vo != null){
    			//确认租船协议
    			code = agreementService.confirmAgreement(vo,user);
    			if (code !=null && code>0) {
    				response.getWriter().write(Constants.SAVE_TRU);
    				response.getWriter().close();
    			} else {
    				response.getWriter().write(Constants.SAVE_FALSE);
    				response.getWriter().close();
    			}
    		}else{
    			throw new TransportException(TransportException.TYPE.ITSH088);
    		}
			
		} catch (Exception e) {
			log.error("ModificationAgreement error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(e.getMessage());
		}
	}
	
	/**
	 * 删除待确认协议
	 * @param uuid
	 */
	@ResponseBody
    @RequestMapping(value = "deleteAgreement.json", method = RequestMethod.POST)
    public Result agreementDelete(@RequestBody String uuid, CurrentUser user) {
        Result res = new Result();

        try {
            // 验证登录信息
            if (user == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }

            // uuid校验
            if (StringUtils.isEmpty(uuid)) {
                throw new TransportException(TransportException.TYPE.ITSH026);
            }

            Agreement agreement = new Agreement();
            agreement.setUuid(uuid);
			int code = agreementService.deleteAgreement(agreement,user);
			if(code>0){
				res.setMessage(Constants.DELE_TRU);
			}
        } catch (TransportException e) {
            log.error("delAccessory error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }

	
	/**
	 * 协议管理列表(租船代理)
	 * @param agreementVO
	 * @param user
	 */
	@RequestMapping(value = UrlMapping.LIST_INDEX_AGENT)
	public void agreementListByAgent(AgreementVO agreementVO,CurrentUser user,PageInfo pageInfo,ModelMap model){
		ResultDatas<Agreement> res = new ResultDatas<>();
			try {
				//用户登录判断
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				
				AgreementQuery query = new AgreementQuery();
				if (null != agreementVO) {
					//uuid
					if(StringUtils.isNotEmpty(agreementVO.getUuid())){
						query.setUuid(agreementVO.getUuid());
					}
					//编号
					if (StringUtils.isNotEmpty(agreementVO.getAgreementCd())) {
						query.setCharterPartyNumber(agreementVO.getAgreementCd());
					}
					//产品
					if(StringUtils.isNotEmpty(agreementVO.getProductNm())){
						query.setProductNm(agreementVO.getProductNm());
					}
					//状态
					if(StringUtils.isNotEmpty(agreementVO.getStatus())){
						query.setStatus(agreementVO.getStatus());
					}
					//开始时间
					if(StringUtils.isNotEmpty(agreementVO.getQueryStart())){
						query.setQueryStart(agreementVO.getQueryStart());
					}
					//结束时间
					if(StringUtils.isNotEmpty(agreementVO.getQueryEnd())){
						query.setQueryEnd(agreementVO.getQueryEnd());
					}
					
					query.setRoleId(user.getEpMemberId());
					PageInfoResult pageInfoResult = agreementService.queryByEntitysList(query, pageInfo, user);
					List<Agreement> list = pageInfoResult.getList();
			        List<AgreementVO> voList = new ArrayList<>();
			        if(!list.isEmpty()){
			        	for(Agreement agreement : list){
			        		AgreementVO vo = new AgreementVO();
			        		AgreementVO domainToVO = vo.domainToVO(agreement);
				        	voList.add(domainToVO);
				        }
			        }
			        
			        model.addAttribute("selectVo", agreementVO);
			        model.addAttribute("agreementPage", pageInfoResult);
			        model.addAttribute("agreement", voList);
			        model.addAttribute("agreementStart", shipDictionaryService.shipAgreementStatusMap().values().toArray());
			        model.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
				}
			} catch (BizException e) {
				log.error("AgreementQuery error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("AgreementQuery error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
		}
	
	/**
	 * 协议管理列表(贸易商)
	 * @param agreementVO
	 * @param user
	 */
	@RequestMapping(value = UrlMapping.LIST_INDEX_MERCHANT)
	public void agreementListByMerchant(AgreementVO agreementVO,CurrentUser user,PageInfo pageInfo,ModelMap model){
		ResultDatas<Agreement> res = new ResultDatas<>();
		
			try {
				
				//用户登录判断
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				
				AgreementQuery query = new AgreementQuery();
				if (null != agreementVO) {
					//uuid
					if(StringUtils.isNotEmpty(agreementVO.getUuid())){
						query.setUuid(agreementVO.getUuid());
					}
					//编号
					if (StringUtils.isNotEmpty(agreementVO.getAgreementCd())) {
						query.setCharterPartyNumber(agreementVO.getAgreementCd());
					}
					//产品
					if(StringUtils.isNotEmpty(agreementVO.getProductNm())){
						query.setProductNm(agreementVO.getProductNm());
					}
					//状态
					if(StringUtils.isNotEmpty(agreementVO.getStatus())){
						query.setStatus(agreementVO.getStatus());
					}
					//开始时间
					if(StringUtils.isNotEmpty(agreementVO.getQueryStart())){
						query.setQueryStart(agreementVO.getQueryStart());
					}
					//结束时间
					if(StringUtils.isNotEmpty(agreementVO.getQueryEnd())){
						query.setQueryEnd(agreementVO.getQueryEnd());
					}
					//判断用户登陆
					if(user != null){
						query.setRoleId(user.getEpMemberId());
					}
					// 泉炼得场合可以查看中化新得订单
					PageInfoResult pageInfoResult = null;
					if(idQuanzhou.equals(user.getEpMemberId())) {
						query.setRoleId(user.getEpMemberId());
						query.setSingaporeId(idSingapore);

						pageInfoResult = agreementService.queryDemandsQuanlianByEntitysList(query, pageInfo,user);
					} else {
						query.setRoleId(user.getEpMemberId());
						pageInfoResult = agreementService.queryByDemandsEntitysList(query, pageInfo,user);
					}

					List<Agreement> list = pageInfoResult.getList();
			        List<AgreementVO> voList = new ArrayList<>();
			        if(!list.isEmpty()){
			        	for(Agreement agreement : list){
			        		AgreementVO domainToVO = new AgreementVO(agreement);

							// 泉炼得场合
							if (idQuanzhou.equals(user.getEpMemberId()) &&
									idSingapore.equals(agreement.getSellerId())) {
								domainToVO.setQuanzhouFlag("1");
							}
				        	voList.add(domainToVO);
				        }
			        }
			        
			        model.addAttribute("selectVo", agreementVO);
			        model.addAttribute("agreementPage", pageInfoResult);
			        model.addAttribute("agreement", voList);
			        model.addAttribute("agreementStart", shipDictionaryService.shipAgreementStatusMap().values().toArray());
			        model.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
				}
			} catch (BizException e) {
				log.error("AgreementQuery error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("AgreementQuery error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
		}

	/**
	 * 租船协议微信API
	 * 根据uuid查看详情
	 * wh
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_QUERYBYUUID_AGREEMENT)
	public ResultDatas<AgreementVO> getAgreementByUuid(AgreementVO vo, CurrentUser user){
		ResultDatas<AgreementVO> res = new ResultDatas<>();
		try {
//			if(user == null){
//				throw new TransportException(TransportException.TYPE.ITSH115);
//			}
            Agreement agreement = agreementService.findByUuid(vo.getUuid());
            //获取绝对路径
            if(agreement!=null) {
				if(!StringHelper.isNullOrEmptyString(agreement.getUploadQ88())) {
					agreement.setUploadQ88(imgurl + "/" + agreement.getUploadQ88());
				}
				if(!StringHelper.isNullOrEmptyString(agreement.getUploadCp())) {
					agreement.setUploadCp(imgurl + "/" + agreement.getUploadCp());
				}
//                if (agreement.getUploadQ88().length() > 0) {
//                    agreement.setUploadQ88(imgurl + "/" + agreement.getUploadQ88());
//                }
//                if (agreement.getUploadCp().length() > 0) {
//                    agreement.setUploadCp(imgurl + "/" + agreement.getUploadCp());
//                }
            }
            AgreementVO agreementVO = new AgreementVO(agreement);
            res.setDatas(agreementVO);
		/*} catch (BizException e) {
			log.error("getAgreementByUuid error",e);
			res.setMessage(e.getMessage());*/
		}catch (Exception e) {
			log.error("getAgreementByUuid error",e);
			res.setMessage("系统异常");
		}
		return res;
	}

	@RequestMapping("agreement/orderuuid.json")
	@ResponseBody
	public ResultDatas<String> getUuidByOrderId(Long orderId){
		ResultDatas result = new ResultDatas();
		try{
			ContractSheetRemoteVO contractSheetRemoteVO =  contractSheetRemoteService.getContractSheetById(orderId);
			if(contractSheetRemoteVO!=null){
				String uuid = contractSheetRemoteVO.getUuid();
				result.setDatas(uuid);
			}

		}catch (Exception e){
			log.error("根据订单id查询订单uuid失败",e);
			result.setStatus(Constants.EXCEPTION_STATUS);
		}finally {
			return result;
		}
	}

}
