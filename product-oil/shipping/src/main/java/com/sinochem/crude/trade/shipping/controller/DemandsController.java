package com.sinochem.crude.trade.shipping.controller;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
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

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.DemandsQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatDemandsQuery;
import com.sinochem.crude.trade.shipping.model.vo.DemandsVO;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.crude.trade.shipping.service.DemandsService;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.transaction.remote.ContractSheetRemoteService;
import com.sinochem.crude.trade.transaction.vo.ContractSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 租船需求controller
 *
 * @author zhaoyulong
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_DEMANDS)
public class DemandsController {

    @Autowired
    private DemandsService demandsService;

    @Autowired
    private ConfirmationSheetService confirmationSheetService;

    @Autowired
    private ShipDictionaryService shipDictionaryService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ContractSheetRemoteService contractSheetRemoteService;

    // 中化新
    @Value("${id.singapore}")
    private Long idSingapore;

    // 泉炼
    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    public static final Log log = LogFactory.getLog(DemandsController.class);

    /**
     * 租船需求列表查询(中化信)
     *
     * @param currentUser
     * @param page
     * @param vo
     * @return
     */
    @RequestMapping(value = UrlMapping.GET_DEMANDS_PAGE_LIST)
    public void DemandsQuery(PageInfo page, DemandsVO vo, ModelMap map, CurrentUser currentUser) {
        DemandsQuery demandsQuery = new DemandsQuery();
        ResultDatas<Demands> res = new ResultDatas<>();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            // confirmationSheetUuid校验
            if (null != vo) {
                if (StringUtil.isNotBlank(vo.getUuid())) {
                    demandsQuery.setUuid(vo.getUuid());
                }
                //产品
                if (StringUtil.isNotBlank(vo.getProdoctNm())) {
                    demandsQuery.setProdoctNm(vo.getProdoctNm());
                }
                //状态
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    demandsQuery.setStatus(vo.getStatus());
                }
                //开始时间
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    demandsQuery.setCreateDateStart(vo.getCreateStart());
                }
                //结束时间
                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    demandsQuery.setCreateDateEnd(vo.getCreateEnd());
                }
                // order 编码
                if (StringUtil.isNotBlank(vo.getOrderNumber())) {
                    demandsQuery.setOrderNumber(vo.getOrderNumber());
                }
//				//放入角色 id
//				demandsQuery.setEpMemberId(currentUser.getEpMemberId());

                PageInfoResult<Demands> pageInfoResult = null;
                // 泉炼得场合可以查看中化新得订单
                if (idQuanzhou.equals(currentUser.getEpMemberId())) {
                    demandsQuery.setSellerId(currentUser.getEpMemberId());
                    demandsQuery.setSingaporeId(idSingapore);

                    pageInfoResult = demandsService.queryByEntitysQuanhuaList(demandsQuery, page);
                } else {
                    demandsQuery.setSellerId(currentUser.getEpMemberId());
                    pageInfoResult = demandsService.queryByEntitysList(demandsQuery, page);
                }

                List<Demands> list = pageInfoResult.getList();

                List<DemandsVO> voList = new ArrayList<>();

                for (Demands rec : list) {
                    DemandsVO demandsVO = new DemandsVO(rec);
                    // 泉炼得场合
                    if (idQuanzhou.equals(currentUser.getEpMemberId()) &&
                            idSingapore.equals(rec.getSellerId())) {
                        demandsVO.setQuanzhouFlag("1");
                    }
                    voList.add(demandsVO);
                }

                pageInfoResult.setList(voList);
                map.addAttribute("demandsPage", pageInfoResult);
                map.addAttribute("selectVo", demandsQuery);
                map.addAttribute("demandStutar", shipDictionaryService.shipDemanteStatusMap().values().toArray());
                map.addAttribute("demandSelect", shipDictionaryService.DatebuiltMap().values().toArray());

            }

        } catch (BizException e) {
            log.error("DemandsQuery error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("DemandsQuery error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
    }

    /**
     * 租船需求列表查询(租船代理)
     *
     * @param currentUser
     * @param page
     * @param vo
     * @return
     */
    @RequestMapping(value = UrlMapping.GET_AGENTDEMANDS_PAGE_LIST)
    public void agentDemandsQuery(PageInfo page, DemandsVO vo, ModelMap map, CurrentUser currentUser) {
        DemandsQuery demandsQuery = new DemandsQuery();
        ResultDatas<Demands> res = new ResultDatas<>();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            // confirmationSheetUuid校验
            if (null != vo) {
                if (StringUtil.isNotBlank(vo.getUuid())) {
                    demandsQuery.setUuid(vo.getUuid());
                }
                //产品
                if (StringUtil.isNotBlank(vo.getProdoctNm())) {
                    demandsQuery.setProdoctNm(vo.getProdoctNm());
                }
                //状态
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    demandsQuery.setStatus(vo.getStatus());
                }
                //开始时间
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    demandsQuery.setCreateDateStart(vo.getCreateStart());
                }
                //结束时间
                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    demandsQuery.setCreateDateEnd(vo.getCreateEnd());
                }
                // order 编码
                if (StringUtil.isNotBlank(vo.getOrderNumber())) {
                    demandsQuery.setOrderNumber(vo.getOrderNumber());
                }
                //放入角色 id
                demandsQuery.setCharteringAgentId(currentUser.getEpMemberId());

                PageInfoResult<Demands> pageInfoResult =
                        demandsService.queryByEntitysList(demandsQuery, page);
                List<Demands> list = pageInfoResult.getList();

                List<DemandsVO> voList = new ArrayList<>();

                for (Demands rec : list) {

                    voList.add(new DemandsVO(rec));
                }

                pageInfoResult.setList(voList);
                map.addAttribute("demandsPage", pageInfoResult);
                map.addAttribute("selectVo", demandsQuery);
                map.addAttribute("demandStutar", shipDictionaryService.shipDemanteStatusMap().values().toArray());
                map.addAttribute("demandSelect", shipDictionaryService.DatebuiltMap().values().toArray());
            }

        } catch (BizException e) {
            log.error("DemandsQuery error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("DemandsQuery error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
    }

    /***
     * 租船需求单添加界面（界面展示）
     *
     * @param vo
     * @param user
     * @return
     */
    @RequestMapping(value = UrlMapping.DEMANDS_SAVE_INIT)
    public void initInsertDemands(DemandsVO vo, ModelMap model, CurrentUser user) {

        // 租船代理人列表取得
        List<EnterpriseVo> agreemntList = enterpriseService.selectByCredentials(Constants.TYPE_SHIP_AGENT_WEB);
        EnterpriseVo userInfo = enterpriseService.getByMemberId(user.getEpMemberId());

        vo.setCorporationNm(userInfo.getEnglishName());
        vo.setCorporationId(userInfo.getMemberId());
        vo.setPhoneNum(userInfo.getMobile());
        vo.setEMial(userInfo.getEmail());
        vo.setLinkNm(user.getName());
        // 更新
        if (StringUtils.isNotBlank(vo.getUuid())) {
            Demands demands = demandsService.findByUuid(vo.getUuid());
            vo = new DemandsVO(demands);

            if (StringUtils.isNotBlank(vo.getBuiltDate()) && vo.getBuiltDate().length() > 1) {
                vo.setRemark(vo.getBuiltDate());
                vo.setBuiltDate(Constants.BULLT_FIVE);
            }

            model.put("demandsVO", vo);
            model.put("agreemntList", agreemntList);
        } else {
            try {
                ContractSheetRemoteVO crVO =
                        contractSheetRemoteService.getContractSheetByUuid(vo.getOrderUuid());
                vo.setOrderNumber(crVO.getOrderNumber());
                vo.setBuyerId(crVO.getBuyerId());
                vo.setSellerId(crVO.getSellerId());
                vo.setTradeTerms(crVO.getTradeTerms());
                vo.setProdoctNm(crVO.getProdoctNm());
                vo.setOrderId(crVO.getOrderId());
                if (crVO.getQuantity() != null) {
                    vo.setQuantity(crVO.getQuantity().toString());
                }
                if (crVO.getRangeOfError() != null) {
                    vo.setRangeOfError(crVO.getRangeOfError().toString());
                }
                vo.setPortOfLoading(crVO.getPortOfLoading());
                vo.setPortOfDischarge(crVO.getPortOfDischarge());
                vo.setLaycanStart(crVO.getLaycanStart());
                vo.setLaycanEnd(crVO.getLaycanEnd());
                // 商品来源
                vo.setProductSourceCode(crVO.getProductSource());

            } catch (BizException e) {
                log.error("initInsertDemands error", e);

            }

            // 新增
            model.put("demandsVO", vo);
            model.put("agreemntList", agreemntList);
        }

    }

    /***
     * 租船需求单保存到数据库
     *
     * @param vo
     * @param user
     * @return
     */
    @RequestMapping(value = UrlMapping.DEMANDS_SAVE, method = RequestMethod.POST)
    @ResponseBody
    public Result saveDemands(DemandsVO vo, CurrentUser user) {
        Result res = new Result();
        try {
            // 验证登录信息
            if (user == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (user.getEpMemberId() == null) {
                throw new TransportException("The Company's Information is Empty");
            }

            if (Constants.BULLT_FIVE.equals(vo.getBuiltDate())) {
                vo.setBuiltDate(vo.getRemark());
            }
            Demands demands = vo.getDomain();
            // 更新
            if (StringUtils.isNotBlank(vo.getUuid())) {
                demands.setUpdateDate(DateUtil.getCurrentDate());
                demands.setUpdateUser(user.getMemberId());
                demandsService.updateRecordByUuid(demands);
                res.setMessage(Constants.MODIFY_TRU);
            } else {
                demands.setStatus(ExternalApi.DEMAND_10);
                demands.setUpdateDate(DateUtil.getCurrentDate());
                demands.setAliveFlag(Constants.SAVE_FLAG);
                demands.setUuid(KeyGenUtils.newUuid());
                demands.setDemandsCd(SequenceUtils.nextSequence(Constants.DEMANDS_CODE));
                demands.setCreateDate(DateUtil.getCurrentDate());
                demands.setCreateUser(user.getMemberId());
                demands.setEpMemberId(user.getEpMemberId());
                demands.setUpdateUser(user.getMemberId());
                demandsService.insertRecord(demands);
                res.setMessage(Constants.SAVE_TRU);
            }


        } catch (BizException e) {
            log.error("savePallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("savePallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /***
     * 租船信息_取消申请
     *
     * @param vo
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.DEMANDS_UPDATE_STATUS, method = RequestMethod.POST)
    public Result updateStatus(@RequestBody DemandsVO vo, CurrentUser user) {
        log.info("--->" + vo.toString());
        Result res = new Result();
        vo.setCreateUser(user.getMemberId());
        vo.setAliveFlag(Constants.DELE_FLAG);
        Demands demands = vo.getDomain();
        try {
            demands.setUpdateDate(DateUtil.getCurrentDate());
            demandsService.updateRecordById(demands);
        } catch (BizException e) {
            log.error("deleteStatus error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("deleteStatus error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /****
     * 租船信息_删除
     *
     * @param vo
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.DEMANDS_DELETE, method = RequestMethod.POST)
    public Result delDemans(DemandsVO vo, CurrentUser user) {

        Result res = new Result();
        try {
            if (vo == null) {
                throw new TransportException(TransportException.TYPE.ITSH002);
            }
            Demands demand = vo.getDomain();
            demandsService.deleteRecords(demand);
            res.setMessage(Constants.DELE_TRU);
        } catch (BizException e) {
            log.error("delPallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("delPallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /****
     * 租船信息_修改
     *
     * @param vo
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.DEMANDS_UPDATE, method = RequestMethod.POST)
    public Result updatePallet(DemandsVO vo, CurrentUser user) {
        Result res = new Result();
        try {
            // 租船uuid校验
            if (StringUtil.isEmpty(vo.getUuid())) {
                throw new TransportException(TransportException.TYPE.ITSH102);
            }

            // 验证登录信息
            if (user == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (user.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }
            vo.setCreateUser(user.getMemberId());
            vo.setAliveFlag(Constants.DELE_FLAG);
            Demands demands = vo.getDomain();
            demands.setUpdateDate(DateUtil.getCurrentDate());
            demandsService.updateRecordById(demands);
        } catch (BizException e) {
            log.error("updatePallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        } catch (Exception e) {
            log.error("updatePallet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }

        return res;
    }

    /**
     * 根据uuid 查询详情
     */
    @RequestMapping(value = "DemandsDateiled")
    public void DemandsDateiled(String uuid, ModelMap map) {
        try {
            if (StringUtil.isBlank(uuid)) {
                throw new TransportException(TransportException.TYPE.ITSH200);
            }
            Demands findByUuid = demandsService.findByUuid(uuid);
            DemandsVO demandsVO = new DemandsVO(findByUuid);
            if (StringUtils.isNotBlank(demandsVO.getBuiltDate()) && demandsVO.getBuiltDate().length() > 1) {
                demandsVO.setRemark(demandsVO.getBuiltDate());
                demandsVO.setBuiltDate(Constants.BULLT_FIVE);
            }
            // 租船代理人列表取得
            List<EnterpriseVo> agreemntList = enterpriseService.selectByCredentials(Constants.TYPE_SHIP_AGENT_WEB);
            map.put("demandsVO", demandsVO);
            map.put("agreemntList", agreemntList);
        } catch (BizException e) {
            log.error("DemandsDateiled error", e);
        } catch (Exception e) {
            log.error("DemandsDateiled error", e);
        }
    }

    /**
     * 校验是否已租船
     *
     * @param vo
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = UrlMapping.CHECKA_DEMANDS, method = RequestMethod.POST)
    public Result checkAgreement(DemandsVO vo, CurrentUser user) {
        Result res = new Result();
        try {

            if (vo.getOrderId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH106);
            }

            int count = demandsService.checkAgreementIsExsit(vo.getOrderId());

            if (count > 0) {
                res.setFail(TransportException.TYPE.ITSH532.getDesc());
            } else {
                count = confirmationSheetService.checkConfirmationIsExsit(vo.getOrderId());

                if (count > 0) {
                    res.setFail(TransportException.TYPE.ITSH532.getDesc());
                }
            }
        } catch (BizException e) {
            log.error("checkAgreement error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("checkAgreement error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /**
     * 租船需求列表查询  微信端API
     *
     * @return
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.WECHAT_QUERY_DEMANDS)
    @ResponseBody
    public ResultDatas<List<DemandsVO>> getDemandsList(WechatDemandsQuery query) {
        ResultDatas<List<DemandsVO>> result = new ResultDatas<>();
        try {
            if (query.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }
            DemandsQuery demandsQuery = new DemandsQuery();
            if (query.getType() == Constants.TYPE_TRADER) {// 中化新
                demandsQuery.setEpMemberId(query.getEpMemberId());
            } else if (query.getType() == Constants.TYPE_DISPONENT_OWNER) {//租船代理
                demandsQuery.setCharteringAgentId(query.getEpMemberId());
            } else {
                result.setFail("角色不存在");
                return result;
            }
            if (StringUtil.isNotEmpty(query.getKeywords())) {
                demandsQuery.setKeywords(query.getKeywords());
            }

            List<Demands> demandsList = null;
            List<DemandsVO> voList = new ArrayList<>();
            if (query.getType() == Constants.TYPE_TRADER) {
                // 泉炼得场合可以查看中化新得订单
                if (idQuanzhou.equals(query.getEpMemberId())) {
                    demandsQuery.setSellerId(query.getEpMemberId());
                    demandsQuery.setSingaporeId(idSingapore);
                    demandsList = demandsService.queryByEntitysQuanhuaList(demandsQuery);
                } else {
                    demandsQuery.setSellerId(query.getEpMemberId());//?是否需要。
//				pageInfoResult = demandsService.queryByEntitysList(demandsQuery, page);
                    demandsList = demandsService.getDemandsList(demandsQuery);
                }

                for (Demands rec : demandsList) {
                    DemandsVO demandsVO = new DemandsVO(rec);
                    // 泉炼得场合
                    if (idQuanzhou.equals(query.getEpMemberId()) &&
                            idSingapore.equals(rec.getSellerId())) {
                        demandsVO.setQuanzhouFlag("1");
                    }
                    voList.add(demandsVO);
                }
            } else {
                demandsList = demandsService.getDemandsList(demandsQuery);
//				List<DemandsVO> voList = new ArrayList<>();
                for (Demands rec : demandsList) {
                    voList.add(new DemandsVO(rec));
                }
            }


            //List<Demands> demandsList = demandsService.getDemandsList(demandsQuery);
            //List<DemandsVO> voList = new ArrayList<>();
//			for(Demands rec : demandsList){
//				voList.add(new DemandsVO(rec));
//			}
            result.setDatas(voList);
        } catch (BizException e) {
            log.error("checkAgreement error", e);
            result.setStatus(Constants.EXCEPTION_STATUS);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("checkAgreement error", e);
            result.setStatus(Constants.EXCEPTION_STATUS);
            result.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return result;
    }


    /***
     * 租船信息_详情（微信）
     *
     * @param vo
     * @return
     */
    @WithoutAccess
    @ResponseBody
    @RequestMapping(value = UrlMapping.DEMANDS_SELECT_UUID)
    public ResultDatas<DemandsVO> palletDeatil(DemandsVO vo) {
        ResultDatas<DemandsVO> res = new ResultDatas<>();
        try {
            // uuid校验
            if (StringUtil.isEmpty(vo.getUuid())) {
                throw new TransportException(TransportException.TYPE.ITSH102);
            }
            Demands demands = demandsService.findByUuid(vo.getUuid());
            //设置船龄
            if (!StringHelper.isNullOrEmptyString(demands.getBuiltDate())) {
                DictionaryVO dbvo = shipDictionaryService.DatebuiltMap().get(demands.getBuiltDate());
                if (dbvo != null) {
                    String builtdata = dbvo.getEnName();
                    demands.setBuiltDate(builtdata);
                }
            }
            DemandsVO demandsVO = new DemandsVO(demands);
            res.setDatas(demandsVO);
        } catch (BizException e) {
            log.error("selectPollet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("selectPollet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

}
