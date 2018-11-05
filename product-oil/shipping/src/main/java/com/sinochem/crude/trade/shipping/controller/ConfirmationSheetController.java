package com.sinochem.crude.trade.shipping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.enums.ConfirmStatusEnums;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.ConfirmationSheetQuery;
import com.sinochem.crude.trade.shipping.model.vo.AgreementVO;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.crude.trade.shipping.service.AgreementService;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.crude.trade.shipping.service.DemandsService;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.shipping.service.SysShipService;
import com.sinochem.crude.trade.shipping.service.UnloadPortService;
import com.sinochem.crude.trade.transaction.remote.ContractSheetRemoteService;
import com.sinochem.crude.trade.transaction.vo.ContractSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
@RequestMapping(UrlMapping.VESSEL_ACCEPTANCE)
public class ConfirmationSheetController {

    public static final Log log = LogFactory.getLog(ConfirmationSheetController.class);

    @Autowired
    private ConfirmationSheetService confirmationSheetService;

    @Autowired
    private AgreementService agreementService;

    @Autowired
    private SysShipService sysShipService;

    @Autowired
    private ShipDictionaryService shipDictionaryService;

    @Autowired
    private DemandsService demandsService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ContractSheetRemoteService contractSheetRemoteService;

    @Autowired
    private UnloadPortService unloadPortService;

    @Value("${aliyun.oss.endpoint}")
    private String imgurl;
    
    // 中化新
    @Value("${id.singapore}")
    private Long idSingapore;

    // 泉炼
    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    /**
     * 船舶确认单列表 （中化新）
     *
     * @return
     */
    @RequestMapping(UrlMapping.INDEX)
    public void listOfConfirmationSheet(ConfirmationSheetVO vo, CurrentUser currentUser,
                                        PageInfo pageInfo, ModelMap mav) {

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            ConfirmationSheetQuery confiQuery = new ConfirmationSheetQuery();
            if (vo != null) {
                // 商品
                if (StringUtil.isNotBlank(vo.getProduct())) {
                    confiQuery.setProduct(vo.getProduct());
                }
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    confiQuery.setStatus(vo.getStatus());
                }

                if (StringUtil.isNotBlank(vo.getConfirmationSheetCd())) {
                    confiQuery.setConfirmationSheetCd(vo.getConfirmationSheetCd());
                }
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    Date date = DateUtil.getDate(vo.getCreateStart());
                    confiQuery.setCreateDateStart(date);
                }

                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    Date date = DateUtil.getDate(vo.getCreateEnd());
                    confiQuery.setCreateDateEnd(date);
                }

            }

//            // 放入中化新 epmemberid
//            confiQuery.setEpMemberId(currentUser.getEpMemberId());
            // 获取角色id 卖家id
            //泉炼的场合追加中化新的用户
            PageInfoResult pageInfoResult = null;
            // 泉炼得场合可以查看中化新得订单
            if(idQuanzhou.equals(currentUser.getEpMemberId())) {
                confiQuery.setSellerId(currentUser.getEpMemberId());
                confiQuery.setSingaporeId(idSingapore);

                pageInfoResult = confirmationSheetService.getConfirmationQuanhuaSheetList(
                        confiQuery, pageInfo);
            } else {
                confiQuery.setSellerId(currentUser.getEpMemberId());
                pageInfoResult = confirmationSheetService.getConfirmationSheetList(
                        confiQuery, pageInfo);
            }
            confiQuery.setSellerId(currentUser.getEpMemberId());
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            List<ConfirmationSheet> list = pageInfoResult.getList();
            List<ConfirmationSheetVO> listvo = new ArrayList<ConfirmationSheetVO>();
            if (!list.isEmpty()) {

                for (ConfirmationSheet confirmationSheet : list) {
                    ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                    ConfirmationSheetVO domainToVO = confirmationSheetVO.domainToVO(confirmationSheet);
                    for (EnterpriseVo enVO : shippingAgent) {
                        if (domainToVO.getShippingAgentId().equals(enVO.getMemberId())) {
                            domainToVO.setShippingAgent(enVO.getEnglishName());
                        }
                    }
                    // 泉炼得场合
                    if (idQuanzhou.equals(currentUser.getEpMemberId()) &&
                            idSingapore.equals(confirmationSheet.getSellerId())) {
                        domainToVO.setQuanzhouFlag("1");
                    }
                    if (StringUtil.isEmpty(domainToVO.getAgreementUuid())) {
                        domainToVO.setAgreementUuid("");
                    }
                    listvo.add(domainToVO);
                }
            }
            mav.addAttribute("selectVo", vo);
            mav.addAttribute("confirmPage", pageInfoResult);
            mav.addAttribute("confirm", listvo);
            mav.addAttribute("confirmStart", shipDictionaryService.shipConfirmStatusMap().values()
                    .toArray());
            mav.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
            mav.addAttribute("confirmonline", shipDictionaryService.ConfirmOnlineMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            // model.addAttribute(Message.ERROR,
            // exceptionHelper.getBizExceptionByCode(exceptionCode));
            log.error("listOfConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("listOfConfirmationSheet error", e);
        }

    }

    /**
     * 船舶确认单列表(船代)
     *
     * @return
     */
    @RequestMapping(UrlMapping.CONFIRMATIONAGENCYINDEX)
    public void agencyListOfConfirmationSheet(ConfirmationSheetVO vo, CurrentUser currentUser,
                                              PageInfo pageInfo, ModelMap mav) {

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            ConfirmationSheetQuery confiQuery = new ConfirmationSheetQuery();
            if (vo != null) {
                // 商品
                if (StringUtil.isNotBlank(vo.getProduct())) {
                    confiQuery.setProduct(vo.getProduct());
                }
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    confiQuery.setStatus(vo.getStatus());
                }

                if (StringUtil.isNotBlank(vo.getConfirmationSheetCd())) {
                    confiQuery.setConfirmationSheetCd(vo.getConfirmationSheetCd());

                }
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    Date date = DateUtil.getDate(vo.getCreateStart());
                    confiQuery.setCreateDateStart(date);
                }

                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    Date date = DateUtil.getDate(vo.getCreateEnd());
                    confiQuery.setCreateDateEnd(date);
                }
            }

            // 船代角色id
            confiQuery.setShippingAgentId(currentUser.getEpMemberId());

            PageInfoResult pageInfoResult = confirmationSheetService.getConfirmationSheetList(
                    confiQuery, pageInfo);
            List<ConfirmationSheet> list = pageInfoResult.getList();
            List<ConfirmationSheetVO> listvo = new ArrayList<ConfirmationSheetVO>();
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            if (!list.isEmpty()) {
                for (ConfirmationSheet confirmationSheet : list) {
                    ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                    ConfirmationSheetVO domainToVO = confirmationSheetVO
                            .domainToVO(confirmationSheet);

                    for (EnterpriseVo enVO : shippingAgent) {
                        if (domainToVO.getShippingAgentId().equals(enVO.getMemberId())) {
                            domainToVO.setShippingAgent(enVO.getEnglishName());
                        }
                    }
                    if (StringUtil.isEmpty(domainToVO.getAgreementUuid())) {
                        domainToVO.setAgreementUuid("");
                    }

                    listvo.add(domainToVO);
                }
            }
            mav.addAttribute("selectVo", vo);
            mav.addAttribute("confirmPage", pageInfoResult);
            mav.addAttribute("confirm", listvo);
            mav.addAttribute("confirmStart", shipDictionaryService.shipConfirmStatusMap().values()
                    .toArray());
            mav.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
            mav.addAttribute("confirmonline", shipDictionaryService.ConfirmOnlineMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            // model.addAttribute(Message.ERROR,
            // exceptionHelper.getBizExceptionByCode(exceptionCode));
            log.error("agencyListOfConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("agencyListOfConfirmationSheet error", e);

        }

    }

    /**
     * 船舶确认单列表(买家)
     *
     * @return
     */
    @RequestMapping(UrlMapping.CONFIRMATIONBUYERINDEX)
    public void buyerListOfConfirmationSheet(ConfirmationSheetVO vo, CurrentUser currentUser,
                                             PageInfo pageInfo, ModelMap mav) {

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            ConfirmationSheetQuery confiQuery = new ConfirmationSheetQuery();
            if (vo != null) {
                // 商品
                if (StringUtil.isNotBlank(vo.getProduct())) {
                    confiQuery.setProduct(vo.getProduct());
                }
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    confiQuery.setStatus(vo.getStatus());
                }

                if (StringUtil.isNotBlank(vo.getConfirmationSheetCd())) {
                    confiQuery.setConfirmationSheetCd(vo.getConfirmationSheetCd());

                }
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    Date date = DateUtil.getDate(vo.getCreateStart());
                    confiQuery.setCreateDateStart(date);
                }

                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    Date date = DateUtil.getDate(vo.getCreateEnd());
                    confiQuery.setCreateDateEnd(date);
                }
            }
            // 获取角色id 买家id
            confiQuery.setBuyerId(currentUser.getEpMemberId());
            PageInfoResult pageInfoResult = confirmationSheetService.getConfirmationSheetList(
                    confiQuery, pageInfo);
            List<ConfirmationSheet> list = pageInfoResult.getList();
            List<ConfirmationSheetVO> listvo = new ArrayList<ConfirmationSheetVO>();
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            if (!list.isEmpty()) {
                for (ConfirmationSheet confirmationSheet : list) {
                    ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                    ConfirmationSheetVO domainToVO = confirmationSheetVO
                            .domainToVO(confirmationSheet);
                    for (EnterpriseVo enVO : shippingAgent) {
                        if (domainToVO.getShippingAgentId().equals(enVO.getMemberId())) {
                            domainToVO.setShippingAgent(enVO.getEnglishName());
                        }
                    }
                    if (StringUtil.isEmpty(domainToVO.getAgreementUuid())) {
                        domainToVO.setAgreementUuid("");
                    }
                    listvo.add(domainToVO);
                }
            }
            mav.addAttribute("selectVo", vo);
            mav.addAttribute("confirmPage", pageInfoResult);
            mav.addAttribute("confirm", listvo);
            mav.addAttribute("confirmStart", shipDictionaryService.shipConfirmStatusMap().values()
                    .toArray());
            mav.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
            mav.addAttribute("confirmonline", shipDictionaryService.ConfirmOnlineMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            // model.addAttribute(Message.ERROR,
            // exceptionHelper.getBizExceptionByCode(exceptionCode));
            log.error("buyerListOfConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("buyerListOfConfirmationSheet error", e);
        }

    }

    /**
     * 船舶确认单列表(卖家)
     *
     * @return
     */
    @RequestMapping(UrlMapping.CONFIRMATIONSELLERINDEX)
    public void sellerListOfConfirmationSheet(ConfirmationSheetVO vo, CurrentUser currentUser,
                                              PageInfo pageInfo, ModelMap mav) {

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            ConfirmationSheetQuery confiQuery = new ConfirmationSheetQuery();
            if (vo != null) {
                // 商品
                if (StringUtil.isNotBlank(vo.getProduct())) {
                    confiQuery.setProduct(vo.getProduct());
                }
                if (StringUtil.isNotBlank(vo.getStatus())) {
                    confiQuery.setStatus(vo.getStatus());
                }

                if (StringUtil.isNotBlank(vo.getConfirmationSheetCd())) {
                    confiQuery.setConfirmationSheetCd(vo.getConfirmationSheetCd());

                }
                if (StringUtil.isNotBlank(vo.getCreateStart())) {
                    Date date = DateUtil.getDate(vo.getCreateStart());
                    confiQuery.setCreateDateStart(date);
                }

                if (StringUtil.isNotBlank(vo.getCreateEnd())) {
                    Date date = DateUtil.getDate(vo.getCreateEnd());
                    confiQuery.setCreateDateEnd(date);
                }
            }
            // 获取角色id 买家id
            confiQuery.setSellerId(currentUser.getEpMemberId());
            // 泉炼的场合查看中化新的
            PageInfoResult pageInfoResult = confirmationSheetService.getConfirmationSheetList(
                    confiQuery, pageInfo);
            List<ConfirmationSheet> list = pageInfoResult.getList();
            List<ConfirmationSheetVO> listvo = new ArrayList<ConfirmationSheetVO>();
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            if (!list.isEmpty()) {
                for (ConfirmationSheet confirmationSheet : list) {
                    ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                    ConfirmationSheetVO domainToVO = confirmationSheetVO
                            .domainToVO(confirmationSheet);
                    for (EnterpriseVo enVO : shippingAgent) {
                        if (domainToVO.getShippingAgentId().equals(enVO.getMemberId())) {
                            domainToVO.setShippingAgent(enVO.getEnglishName());
                        }
                    }
                    if (StringUtil.isEmpty(domainToVO.getAgreementUuid())) {
                        domainToVO.setAgreementUuid("");
                    }
                    listvo.add(domainToVO);
                }
            }
            mav.addAttribute("selectVo", vo);
            mav.addAttribute("confirmPage", pageInfoResult);
            mav.addAttribute("confirm", listvo);
            mav.addAttribute("confirmStart", shipDictionaryService.shipConfirmStatusMap().values()
                    .toArray());
            mav.addAttribute("product", shipDictionaryService.productStatusMap().values().toArray());
            mav.addAttribute("confirmonline", shipDictionaryService.ConfirmOnlineMap().values().toArray());
        } catch (BizException e) {
            int exceptionCode = e.getCode();
            // model.addAttribute(Message.ERROR,
            // exceptionHelper.getBizExceptionByCode(exceptionCode));
            log.error("buyerListOfConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("buyerListOfConfirmationSheet error", e);
        }

    }

    /**
     * 船舶确认单-详情页面
     *
     * @param confirmationSheetUuid
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.DETAILS)
    public void detailsOfConfirmationSheet(String confirmationSheetUuid, CurrentUser currentUser,
                                           ModelMap modelMap) {
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH200);
            }
            // 查询确认单信息详细
            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
            if(idSingapore.equals(currentUser.getEpMemberId()) &&
                    Constants.PRODUCT_SOURCE_CODE_AGREEMENT.equals(confirmationSheetVO.getProductSourceCode())) {

                confirmationSheetVO.setQuanzhouFlag("1");
            }

            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            // 计费方式
            modelMap.addAttribute("pricingMethod", shipDictionaryService.PricingMethodMap()
                    .values().toArray());
            // 按吨计费
            modelMap.addAttribute("revenueTon", shipDictionaryService.RevenueTonMap().values()
                    .toArray());
            // 船代
            modelMap.addAttribute("shippingAgent", shippingAgent);

            // 是否在线
            modelMap.addAttribute("confirmOnline", shipDictionaryService.ConfirmOnlineMap()
                    .values().toArray());
            // 是否在线-默认值
            modelMap.addAttribute("confirmOnlineDefult", Constants.COMFIRM_ONLINE_DEFULT);

            // 设定返回数据
            modelMap.put("confirmationSheetVO", confirmationSheetVO);
        } catch (BizException e) {
            log.error("findConfirmationSheetDetail error", e);
        } catch (Exception e) {
            log.error("findConfirmationSheetDetail error", e);
        }

    }

    /**
     * 船舶确认单-详情页面 微信api
     *
     * @param confirmationSheetUuid
     * @param currentUser
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.VESSEL_ACCEPTANCE_DETAILS_APP)
    public ResultDatas<ConfirmationSheetVO> detailsOfConfirmationSheetApp(
            String confirmationSheetUuid, CurrentUser currentUser) {
        ResultDatas<ConfirmationSheetVO> res = new ResultDatas<>();
        try {
            // 验证登录信息
            /*
             * if (currentUser == null) { throw new
             * TransportException(TransportException.TYPE.ITSH001); }
             */
            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH200);
            }
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);

            // 查询确认单信息详细
            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            if(confirmationSheet!=null) {
                //获取绝对路径
//                if(confirmationSheet.getUploadQ88().length()>0) {
                if(!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadQ88())) {
                    confirmationSheet.setUploadQ88(imgurl + "/" + confirmationSheet.getUploadQ88());
                }
                if(!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadCp())) {
                    confirmationSheet.setUploadCp(imgurl + "/" + confirmationSheet.getUploadCp());
                }
            }
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);

            for (EnterpriseVo enVO : shippingAgent) {
                if (confirmationSheetVO.getShippingAgentId().equals(enVO.getMemberId())) {
                    confirmationSheetVO.setShippingAgent(enVO.getEnglishName());
                }
            }
            if (StringUtil.isEmpty(confirmationSheetVO.getAgreementUuid())) {
                confirmationSheetVO.setAgreementUuid("");
            }
            // 设定返回数据
            res.setDatas(confirmationSheetVO);

        } catch (BizException e) {
            log.error("findSysShipDetail error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        } catch (Exception e) {
            log.error("findSysShipDetail error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }

        return res;
    }

    /**
     * 船舶确认单-创建页面
     * <p>
     * 订单ID或者租船协议ID
     * 判断是FOB还是CFR
     *
     * @param currentUser 当前用户
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CREATE)
    public void createConfirmationSheet(ConfirmationSheetVO vo, CurrentUser currentUser,
                                        ModelMap modelMap) {
        ResultDatas<SysShip> res = new ResultDatas<>();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }

            // 如果AgreementUuid传过来为空字符串"" 则 设置为null
            if (vo.getAgreementUuid() != null) {
                Integer lengthOfAgreementUuid = vo.getAgreementUuid().trim().length();
                if (lengthOfAgreementUuid == 0) {
                    vo.setAgreementUuid(null);
                } else {
                    vo.setAgreementUuid(vo.getAgreementUuid().trim());
                }
            }
            // agreementUuid校验
            // 如果租船协议UUID为空，则不显示租船协议名称
            if (!StringUtils.isNullOrEmpty(vo.getAgreementUuid())) {
                // 查询租船协议
                Agreement agreement = agreementService.findByUuid(vo.getAgreementUuid());
                AgreementVO agreementVO = new AgreementVO(agreement);
                // agreement转agreementVO
                agreementVO = agreementVO.toObjectVo(agreement);

                vo.setVesselName(agreement.getVesselName());
                vo.setCharterPartyNumber(agreement.getAgreementCd());
                vo.setSellerId(agreement.getSellerId());
                vo.setBuyerId(agreement.getBuyerId());
                vo.setImo(agreement.getImo());
                vo.setProduct(agreement.getProductNm());
                vo.setQuantity(agreement.getQuantity());
                vo.setRangeOfError(agreement.getRangeOfError());
                vo.setLaycanStrat(agreementVO.getLaycanStrat());
                vo.setLaycanEnd(agreementVO.getLaycanEnd());
                vo.setPortOfLoading(agreementVO.getPortOfLoading());
                vo.setPortOfDischarge(agreementVO.getPortOfDischarge());
                vo.setUploadCp(agreement.getUploadCp());
                vo.setUploadCpFileNm(agreement.getUploadCpFileNm());
                vo.setUploadQ88(agreement.getUploadQ88());
                vo.setUploadQ88FileNm(agreement.getUploadQ88FileNm());
                vo.setShipAgreementId(agreement.getAgreementId());
                vo.setOrderId(agreement.getOrderId());
                vo.setProductSourceCode(agreement.getProductSourceCode());


                long demandsId = agreement.getDemandsId();
                Demands demands = demandsService.findByPrimaryKey(demandsId);
                if (demands != null) {
                    vo.setTradeTerms(demands.getTradeTerms());
                }
            }
            if (StringUtil.isNotBlank(vo.getOrderUuid())) {
                ContractSheetRemoteVO crVO =
                        contractSheetRemoteService.getContractSheetByUuid(vo.getOrderUuid());
                vo.setOrderNumber(crVO.getOrderNumber());
                vo.setBuyerId(crVO.getBuyerId());
                vo.setSellerId(crVO.getSellerId());
                vo.setTradeTerms(crVO.getTradeTerms());
                vo.setProduct(crVO.getProdoctNm());
                vo.setOrderId(crVO.getOrderId());
                if (crVO.getQuantity() != null) {
                    vo.setQuantity(crVO.getQuantity().toString());
                }
                if (crVO.getRangeOfError() != null) {
                    vo.setRangeOfError(crVO.getRangeOfError().toString());
                }
                vo.setPortOfLoading(crVO.getPortOfLoading());
                vo.setPortOfDischarge(crVO.getPortOfDischarge());
                vo.setLaycanStrat(crVO.getLaycanStart());
                vo.setLaycanEnd(crVO.getLaycanEnd());
                // 商品来源
                vo.setProductSourceCode(crVO.getProductSource());
            }

            if(idSingapore.equals(currentUser.getEpMemberId()) &&
                    Constants.PRODUCT_SOURCE_CODE_AGREEMENT.equals(vo.getProductSourceCode())) {

                vo.setQuanzhouFlag("1");

            }

            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            modelMap.addAttribute("shippingAgent", shippingAgent);

            if (vo.getUploadCpFileNm() == null) {
                vo.setUploadCpFileNm("");
            }

            if (vo.getUploadQ88FileNm() == null) {
                vo.setUploadQ88FileNm("");
            }
            // 将VO装入map，传到vm页面
            modelMap.put("vo", vo);
            // 计费方式
            modelMap.addAttribute("pricingMethod", shipDictionaryService.PricingMethodMap()
                    .values().toArray());
            // 按吨计费
            modelMap.addAttribute("revenueTon", shipDictionaryService.RevenueTonMap().values()
                    .toArray());
            // 船舶List
            List<SysShip> sysShipList = sysShipService.findAllList();
            modelMap.put("sysShipList", sysShipList);

            //卸港集合
            /*List<UnloadPort> unloadPortList = unloadPortService.findUnloadPort();*/
            modelMap.put("portOfDisStart", shipDictionaryService.UnLoadPortMap().values().toArray());

            // 是否在线
            modelMap.addAttribute("confirmOnline", shipDictionaryService.ConfirmOnlineMap()
                    .values().toArray());
            // 是否在线-默认值
            modelMap.addAttribute("confirmOnlineDefult", Constants.COMFIRM_ONLINE_DEFULT);
        } catch (BizException e) {
            log.error("createConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        } catch (Exception e) {
            log.error("createConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }

    }

    /**
     * 船舶确认单-保存生成
     *
     * @throws IOException
     */
    @RequestMapping(value = "saveVA.json", method = RequestMethod.POST)
    public Result saveConfirmationSheet(@ModelAttribute ConfirmationSheetVO vo,
                                        HttpSession session, HttpServletResponse response, CurrentUser currentUser) {
        Result res = new Result();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }

            String confirOnline = vo.getConfirmOnline();
            // 租船协议存在得场合
            if (StringUtil.isNotEmpty(vo.getAgreementUuid())) {
                // 判断是否在线：在线状态为待确认，不在线状态为待开始
                if (!StringUtils.isNullOrEmpty(confirOnline) && !Constants.COMFIRM_ONLINE_DEFULT.equals(confirOnline.trim())) {//不在线
                    vo.setStatus(ExternalApi.CONFIRM_20);//租船确认单状态 已确认
                    // 判断是否为通过租船协议过来的租船确认单：
                    // 是:则改变租船需求单状态为进行中，并获取“订单编码”、“订单种类”加入到确认单里
                    // 不是:则不改变。
                    if (!StringUtils.isNullOrEmpty(vo.getAgreementUuid())) {
                        /** 设置租船需求单状态为 -- 进行中 **/
                        // 获取 agreement
                        Agreement agreement = new Agreement();
                        agreement = agreementService.findByUuid(vo.getAgreementUuid());
                        vo.setShipAgreementId(agreement.getAgreementId());
                        // 获取 demands
                        long demandsId = agreement.getDemandsId();
                        Demands demands = new Demands();
                        demands = demandsService.findByPrimaryKey(demandsId);
                        // 改变租船需求单状态
                        demands.setStatus(ExternalApi.DEMAND_40);//进行中
                        demandsService.updateRecordByUuid(demands);
                        // 改变租船协议状态
                        agreement.setStatus(ExternalApi.AGREEMENT_22);//租船协议已确认-船舶确认单已确认
                        agreementService.updateRecordById(agreement);
                        // 从demands获取“订单编码”、“订单种类”加入到确认单里
                        vo.setOrderNumber(demands.getOrderNumber());
                        vo.setTradeTerms(demands.getTradeTerms());
                    }
                } else {//在线
                    vo.setStatus(ExternalApi.CONFIRM_10);//租船确认单状态 待确认
                    // 判断是否为通过租船协议过来的租船确认单：
                    // 是:则改变租船需求单状态为进行中，并获取“订单编码”、“订单种类”加入到确认单里
                    // 不是:则不改变。
                    if (!StringUtils.isNullOrEmpty(vo.getAgreementUuid())) {
                        /** 设置租船需求单状态为 -- 进行中 **/
                        // 获取 agreement
                        Agreement agreement = new Agreement();
                        agreement = agreementService.findByUuid(vo.getAgreementUuid());
                        vo.setShipAgreementId(agreement.getAgreementId());
                        // 获取 demands
                        long demandsId = agreement.getDemandsId();
                        Demands demands = new Demands();
                        demands = demandsService.findByPrimaryKey(demandsId);
                        // 改变租船需求单状态
                        demands.setStatus(ExternalApi.DEMAND_40);//进行中
                        demandsService.updateRecordByUuid(demands);
                        // 改变租船协议状态
                        agreement.setStatus(ExternalApi.AGREEMENT_21);//租船协议已确认-船舶确认单待确认
                        agreementService.updateRecordById(agreement);
                        // 从demands获取“订单编码”、“订单种类”加入到确认单里
                        vo.setOrderNumber(demands.getOrderNumber());
                        vo.setTradeTerms(demands.getTradeTerms());
                    }
                }
            } else {

                if (!StringUtils.isNullOrEmpty(confirOnline) && Constants.COMFIRM_ONLINE_DEFULT.equals(confirOnline.trim())) {//不在线
                    vo.setStatus(ExternalApi.CONFIRM_10);//租船确认单状态 待确认
                } else {
                    vo.setStatus(ExternalApi.CONFIRM_20);
                }
            }

            // vo转domain
            ConfirmationSheet confirmationSheet = vo.getDomain();

            // 转化为领域类
            confirmationSheet = vo.voToDomain();

            confirmationSheet.setUuid(KeyGenUtils.newUuid());
            confirmationSheet.setAliveFlag(Constants.SAVE_FLAG);
            confirmationSheet.setCreateDate(DateUtil.getCurrentDate());
            confirmationSheet.setCreateUser(currentUser.getMemberId());
            confirmationSheet.setConfirmationSheetCd(
                    SequenceUtils.nextSequence(Constants.CONFIRMATION_CODE));
            confirmationSheet.setExt1(null);
            confirmationSheet.setVersion(null);
            confirmationSheet.setUpdateDate(DateUtil.getCurrentDate());
            confirmationSheet.setUpdateUser(currentUser.getMemberId());
            confirmationSheet.setEpMemberId(currentUser.getEpMemberId());
            confirmationSheetService.insertRecord(confirmationSheet);
            res.setMessage(Constants.SAVE_TRU);
        } catch (BizException e) {
            log.error("saveConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("saveConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }

        return res;

    }

    /**
     * 船舶确认单-修改页面
     *
     * @param confirmationSheetUuid
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.MODIFY)
    public void modifyOfConfirmationSheet(String confirmationSheetUuid, CurrentUser currentUser,
                                          ModelMap modelMap) {
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }
            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH116);
            }
            // 查询确认单信息详细
            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            // 查询确认单状态，状态为“待确认”才可以修改
            if (!ConfirmStatusEnums.CONFIRM_CONFIRMED.getCode().equals(
                    confirmationSheet.getStatus())) {
                throw new Exception("非‘待确认’状态，不可修改");
            }
            ConfirmationSheetVO vo = new ConfirmationSheetVO();
            vo = vo.domainToVO(confirmationSheet);

            if (vo.getUploadCpFileNm() == null) {
                vo.setUploadCpFileNm("");
            }

            if (vo.getUploadQ88FileNm() == null) {
                vo.setUploadQ88FileNm("");
            }

            // 获取agreement
            Agreement agreement = agreementService.findByPrimaryKey(confirmationSheet.getShipAgreementId());
            if (agreement == null) {
                vo.setAgreementUuid(null);
            } else {
                // confirmationSheetVO设置 AgreementUuid
                vo.setAgreementUuid(agreement.getUuid());
            }

            if(idSingapore.equals(currentUser.getEpMemberId()) &&
                    Constants.PRODUCT_SOURCE_CODE_AGREEMENT.equals(vo.getProductSourceCode())) {

                vo.setQuanzhouFlag("1");
            }

            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
            shippingAgent.add(nterpriseVo);
            // 设定返回数据
            modelMap.put("vo", vo);
            // 计费方式
            modelMap.addAttribute("pricingMethod", shipDictionaryService.PricingMethodMap()
                    .values().toArray());
            // 按吨计费
            modelMap.addAttribute("revenueTon", shipDictionaryService.RevenueTonMap().values()
                    .toArray());
            // 船代
            modelMap.addAttribute("shippingAgent", shippingAgent);
            // 船舶list
            List<SysShip> sysShipList = sysShipService.findAllList();
            modelMap.put("sysShipList", sysShipList);

            // 是否在线
            modelMap.addAttribute("confirmOnline", shipDictionaryService.ConfirmOnlineMap()
                    .values().toArray());
            // 是否在线-默认值
            modelMap.addAttribute("confirmOnlineDefult", Constants.COMFIRM_ONLINE_DEFULT);

        } catch (BizException e) {
            log.error("modifyOfConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("modifyOfConfirmationSheet error", e);
        }

    }

    /**
     * 船舶确认单-保存修改
     *
     * @throws IOException
     */
    @RequestMapping(value = "saveModify.json", method = RequestMethod.POST)
    public Result saveModifyConfirmationSheet(@ModelAttribute ConfirmationSheetVO vo,
                                              HttpSession session, HttpServletResponse response, CurrentUser currentUser) {
        Result res = new Result();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }
            vo.setEpMemberId(currentUser.getEpMemberId());

            // vo转domain
            ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo
                    .getUuid());

            // 转化为领域类
            confirmationSheet = vo.voToDomain();

            confirmationSheet.setExt1(null);
            confirmationSheet.setVersion(null);
            confirmationSheet.setUpdateDate(DateUtil.getCurrentDate());
            confirmationSheet.setUpdateUser(currentUser.getMemberId());
            confirmationSheetService.updateRecordByUuid(confirmationSheet);
            res.setMessage(Constants.MODIFY_TRU);
        } catch (BizException e) {
            log.error("saveModifyConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("saveModifyConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;

    }

    /**
     * 船舶确认单-逻辑删除
     *
     * @param confirmationSheetUuid
     * @param currentUser
     */
    @RequestMapping(value = "delete.json", method = RequestMethod.POST)
    public Result deleteLogicConfirmationSheet(@RequestBody String confirmationSheetUuid,
                                               CurrentUser currentUser) {
        Result res = new Result();

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }

            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH116);
            }

            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            confirmationSheet.setAliveFlag(Constants.DELE_FLAG);
            confirmationSheetService.deleteLogicConfirmationSheet(confirmationSheet);

            res.setMessage(Constants.DELE_TRU);
        } catch (TransportException e) {
            log.error("deleteLogicConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }

    /**
     * 船舶确认单-物理删除
     *
     * @param confirmationSheetUuid
     * @param currentUser
     */
    @RequestMapping(value = "deleteLogic.json", method = RequestMethod.POST)
    public Result deleteConfirmationSheet(@RequestBody String confirmationSheetUuid,
                                          CurrentUser currentUser) {
        Result res = new Result();

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }

            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH116);
            }

            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            // 只有待确认状态的才可以删除
            if (ExternalApi.CONFIRM_10.equals(confirmationSheet.getStatus())) {
                confirmationSheetService.deleteById(confirmationSheet.getConfirmationSheetId());
                // 如果有租船协议id 那么改变租船协议的状态为已确认
                if (confirmationSheet.getShipAgreementId() != null) {
                    //租船协议
                    Agreement agreement = agreementService.findByPrimaryKey(confirmationSheet
                            .getShipAgreementId());
                    //设置状态
                    agreement.setStatus(ExternalApi.AGREEMENT_20);
                    //更新
                    agreementService.updateRecordById(agreement);
                }
                res.setMessage(Constants.DELE_TRU);
            } else {
                throw new BizException(Constants.DELE_FALSE);
            }
        } catch (TransportException e) {
            log.error("deleteConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (BizException e) {
            log.error("deleteConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }

    /**
     * 船舶确认单-确认页面
     *
     * @param confirmationSheetUuid
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CONFIRM)
    public void affirmConfirmationSheet(String confirmationSheetUuid, CurrentUser currentUser,
                                        ModelMap modelMap) {
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }
            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH200);
            }
            // 查询确认单信息详细
            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByUuid(confirmationSheetUuid);
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);
            // other船代设定
            EnterpriseVo nterpriseVo = new EnterpriseVo();
            nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
            nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);

            if(idSingapore.equals(currentUser.getEpMemberId()) &&
                    Constants.PRODUCT_SOURCE_CODE_AGREEMENT.equals(confirmationSheetVO.getProductSourceCode())) {

                confirmationSheetVO.setQuanzhouFlag("1");
            }

            shippingAgent.add(nterpriseVo);
            // 计费方式
            modelMap.addAttribute("pricingMethod", shipDictionaryService.PricingMethodMap()
                    .values().toArray());
            // 按吨计费
            modelMap.addAttribute("revenueTon", shipDictionaryService.RevenueTonMap().values()
                    .toArray());
            // 船代
            modelMap.addAttribute("shippingAgent", shippingAgent);

            // 设定返回数据
            modelMap.put("confirmationSheetVO", confirmationSheetVO);
        } catch (BizException e) {
            log.error("affirmConfirmationSheet error", e);
        } catch (Exception e) {
            log.error("affirmConfirmationSheet error", e);
        }
    }

    /**
     * 船舶确认单-操作确认or拒绝
     *
     * @param confirmationSheetUuid
     * @param currentUser
     * @param flag                  确认：1；拒绝：0
     * @return
     */
    @RequestMapping(value = "affirmOrRefuse.json", method = RequestMethod.POST)
    public Result affirmOrRefuseConfirmationSheet(HttpSession session,
                                                  HttpServletResponse response, String confirmationSheetUuid, CurrentUser currentUser,
                                                  String flag) {
        Result res = new Result();

        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (currentUser.getEpMemberId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH535);
            }

            // confirmationSheetUuid校验
            if (StringUtils.isNullOrEmpty(confirmationSheetUuid)) {
                throw new TransportException(TransportException.TYPE.ITSH116);
            }

            // 判断是拒绝还是确认--确认：1；拒绝：0
            if ("1".equals(flag.trim())) {
                ConfirmationSheet confirmationSheet = confirmationSheetService
                        .findByUuid(confirmationSheetUuid);
                confirmationSheet.setStatus(ExternalApi.CONFIRM_20);
                confirmationSheetService.updateRecordByUuid(confirmationSheet);

                // 判断是否为通过租船协议过来的租船确认单：是则改变租船需求单状态，不是则不改变。
                if (confirmationSheet.getShipAgreementId() != null) {
                    /** 设置租船需求单状态为 -- 进行中 **/
                    // 获取 agreement
                    Agreement agreement = new Agreement();
                    agreement = agreementService.findByPrimaryKey(confirmationSheet
                            .getShipAgreementId());
                    // 获取 demands
                    long demandsId = agreement.getDemandsId();
                    Demands demands = new Demands();
                    demands = demandsService.findByPrimaryKey(demandsId);
                    // 改变状态
                    demands.setStatus(ExternalApi.DEMAND_40);
                    try {
                        demandsService.updateRecordByUuid(demands);
                    } catch (BizException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                res.setMessage("Confirmed!");
            } else {
                ConfirmationSheet confirmationSheet = confirmationSheetService
                        .findByUuid(confirmationSheetUuid);
                confirmationSheet.setStatus(ExternalApi.CONFIRM_80);
                confirmationSheetService.updateRecordByUuid(confirmationSheet);

                res.setMessage("Refused!");
            }
        } catch (BizException e) {
            log.error("affirmOrRefuseConfirmationSheet error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }

    /**
     * 校验是否已租船
     *
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.CHECKA_CONFIRMATION, method = RequestMethod.POST)
    @WithoutAccess
    public Result checkAgreement(ConfirmationSheetVO vo, CurrentUser user) {
        Result res = new Result();
        try {

            if (vo.getOrderId() == null) {
                throw new TransportException(TransportException.TYPE.ITSH106);
            }

            int count = confirmationSheetService.checkConfirmationIsExsit(vo.getOrderId());

            if (count > 0) {
                res.setFail(TransportException.TYPE.ITSH532.getDesc());
            } else {
                count = demandsService.checkAgreementIsExsit(vo.getOrderId());

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
     * 船程结束确认单状态修改
     *
     * @param vo
     * @param currentUser
     * @return
     */
    @RequestMapping(value = UrlMapping.UPDATECONFIRAM, method = RequestMethod.POST)
    @ResponseBody
    public Result confirmaStuartUp(@RequestBody ConfirmationSheetVO vo, CurrentUser currentUser) {
        Result res = new Result();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            if (null == vo || StringUtil.isBlank(vo.getUuid())) {
                throw new TransportException(TransportException.TYPE.ITSH002);
            }
            ConfirmationSheet confirmationSheet = vo.voToDomain();
            confirmationSheet.setStatus(ExternalApi.CONFIRM_70);
            Integer re = confirmationSheetService.updateStatusById(confirmationSheet, currentUser);
            if (re != null && re > 0) {
                res.setMessage(Constants.MODIFY_TRU);
            } else {
                res.setMessage(Constants.MODIFY_FALSE);
            }
        } catch (BizException e) {
            log.error("confirmaStuartUp error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("confirmaStuartUp error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

    /**
     * 船舶确认单列表查询 微信API
     *
     * @param keywords
     * @return
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.WECHAT_QUERY_CONFIRMATION_SHEET)
    public ResultDatas<List<ConfirmationSheetVO>> getConfirmationSheetList(Integer type,
                                                                           Long epMemberId, String keywords) {
        ResultDatas<List<ConfirmationSheetVO>> resultDatas = new ResultDatas<>();
        try {
            if (epMemberId == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            // 船代人列表取得
            List<EnterpriseVo> shippingAgent = enterpriseService
                    .selectByCredentials(Constants.TYPE_CONFIRMATION_WEB);

            ConfirmationSheetQuery query = new ConfirmationSheetQuery();
            if (type == Constants.TYPE_TRADER) {// 中化新
//                query.setEpMemberId(epMemberId);
            } else if (type == Constants.TYPE_SELLER) {// 卖家
                query.setSellerId(epMemberId);
            } else if (type == Constants.TYPE_SHIP_AGENT) {// 船代
                query.setShippingAgentId(epMemberId);
            } else if (type == Constants.TYPE_BUYER) {// 买家
                query.setBuyerId(epMemberId);
            } else {
                resultDatas.setFail("角色不存在");
                return resultDatas;
            }
            if (StringUtil.isNotEmpty(keywords)) {
                query.setKeywords(keywords);
            }

            //            // 放入中化新 epmemberid
//            confiQuery.setEpMemberId(currentUser.getEpMemberId());
            // 获取角色id 卖家id
            //泉炼的场合追加中化新的用户
            List<ConfirmationSheet>  confirmationSheetList= null;
            if (type == Constants.TYPE_TRADER) {// 中化新


                // 泉炼得场合可以查看中化新得订单
                if (idQuanzhou.equals(epMemberId)) {
                    query.setSellerId(epMemberId);
                    query.setSingaporeId(idSingapore);

                    confirmationSheetList = confirmationSheetService.getConfirmationQuanhuaSheetListWX(query);
                } else {

                    query.setSellerId(epMemberId);
//                    query.setEpMemberId(null);
                    confirmationSheetList = confirmationSheetService.findByKeywrokds(query);
                }
//                query.setSellerId(query.getEpMemberId());
            }
            else {

                // other船代设定
                EnterpriseVo nterpriseVo = new EnterpriseVo();
                nterpriseVo.setMemberId(Constants.OTHER_SHIPPINGAGENT_ID);
                nterpriseVo.setEnglishName(Constants.OTHER_SHIPPINGAGENT_NM);
                shippingAgent.add(nterpriseVo);
                confirmationSheetList = confirmationSheetService
                        .findByKeywrokds(query);
            }
            List<ConfirmationSheetVO> listvo = new ArrayList<ConfirmationSheetVO>();
            if (!confirmationSheetList.isEmpty()) {
                for (ConfirmationSheet confirmationSheet : confirmationSheetList) {
                    ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                    ConfirmationSheetVO domainToVO = confirmationSheetVO
                            .domainToVO(confirmationSheet);

                    for (EnterpriseVo enVO : shippingAgent) {
                        if (domainToVO.getShippingAgentId().equals(enVO.getMemberId())) {
                            domainToVO.setShippingAgent(enVO.getEnglishName());
                        }
                    }
                    // 泉炼得场合
                    if (idQuanzhou.equals(query.getEpMemberId()) &&
                            idSingapore.equals(confirmationSheet.getSellerId())) {
                        domainToVO.setQuanzhouFlag("1");
                    }
                    if (StringUtil.isEmpty(domainToVO.getAgreementUuid())) {
                        domainToVO.setAgreementUuid("");
                    }
                    listvo.add(domainToVO);

                }
            }
            resultDatas.setDatas(listvo);
        } catch (Exception e) {
            resultDatas.setFail(e.getMessage());
        }
        return resultDatas;
    }

    /**
     * 卖家确认 确认单
     *
     * @param vo
     * @param currentUser
     * @return
     */
    @RequestMapping(value = UrlMapping.CONCONFIRMCLI, method = RequestMethod.POST)
    @ResponseBody
    public Result confirmaConfim(@RequestBody ConfirmationSheetVO vo, CurrentUser currentUser) {
        Result res = new Result();
        try {
            // 验证登录信息
            if (currentUser == null) {
                throw new TransportException(TransportException.TYPE.ITSH001);
            }
            // 验证参数
            if (null == vo || StringUtil.isNotBlank(vo.getUuid())) {
                throw new TransportException(TransportException.TYPE.ITSH002);
            }

            ConfirmationSheet confirmationSheet = vo.voToDomain();
            confirmationSheet.setStatus(ExternalApi.CONFIRM_20);
            ConfirmationSheet byUuid = confirmationSheetService.findByUuid(vo.getUuid());
            Integer re = 0;
            if (null != byUuid) {
                if(ExternalApi.CONFIRM_10.equals(byUuid.getStatus())){
                    re = confirmationSheetService.updateStatus(confirmationSheet,
                            currentUser);
                    // Integer agreement = agreementService.updateStatus();
                }else{
                    throw new TransportException(TransportException.TYPE.ITSH105);
                }

            }else{
                throw new TransportException(TransportException.TYPE.ITSH105);
            }

            //修改协议单状态



          //  demandsService.

          /*  agreementService.updateStatusByAgreementId();


            demandsService.*/

            if (re != null && re > 0) {
                res.setMessage(Constants.MODIFY_TRU);
            } else {
                res.setMessage(Constants.DELE_FALSE);
            }
        } catch (BizException e) {
            log.error("confirmaConfim error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("confirmaConfim error", e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(Constants.EXCEPTION_MESSAGE);
        }
        return res;
    }

}
