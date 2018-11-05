package com.sinochem.crude.trade.shipping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.enums.BillUploadEnums;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.*;

import com.sinochem.crude.trade.transaction.remote.BillCoreUploadRemoteService;
import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.DemandsMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;
import com.sinochem.crude.trade.shipping.model.query.ConfirmationSheetQuery;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.crude.trade.transaction.remote.ContractSheetRemoteService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

@Service
public class ConfirmationSheetServiceImpl implements ConfirmationSheetService {

    private final Logger logger = LoggerFactory.getLogger(ConfirmationSheetServiceImpl.class);

    @Value("${id.quanzhou}")
    private Long producterId;

    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper;
    @Autowired
    private AgreementMapper agreementMapper;
    @Autowired
    private DemandsMapper demandsMapper;
    @Autowired
    private ContractSheetRemoteService contractSheetRemoteService;

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SysShipService sysShipService;

    @Autowired
    private BillCoreUploadRemoteService billCoreUploadRemoteService;

    public ConfirmationSheetMapper getMapper() {
        return confirmationSheetMapper;
    }

    /**
     * 新增
     */
    @Override
    public int insertRecord(ConfirmationSheet confirmationsheet) {

        int flag = confirmationSheetMapper.insertRecord(confirmationsheet);
        if (flag > 0) {
            //在线不确认，在原油的航程追踪插入一条船舶信息
            if (!Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationsheet.getConfirmOnline())) {
                SysShip sysShip = new SysShip();
                sysShip.setImo(confirmationsheet.getImo());
                sysShipService.insertShipRemote(sysShip);
            }
        }
        //开始发送消息
        if (flag > 0) {
            //                更新单据中心的文件
            this.updateBillFile(confirmationsheet);
            this.sendInsertMessage(confirmationsheet);
        }
        return flag;
    }



    private void sendInsertMessage(ConfirmationSheet confirmationsheet) {
        try {
            MessageSheet messageSheet = new MessageSheet();
            //在线不确认
            //{merchantNm confirmationCD product quantity vesselname laycan orderNumber }
            if (!Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationsheet.getConfirmOnline())) {
                messageSheet.setMemberId(confirmationsheet.getBuyerId());
                EnterpriseVo enterpriseVo = messageService.getByMemberId(confirmationsheet.getEpMemberId());
//				{merchantNm confirmationCD product quantity vesselname laycan orderNumber }
                messageSheet.setOrderNumber(confirmationsheet.getOrderNumber());
                messageSheet.setMerchantNm(enterpriseVo != null ? enterpriseVo.getName() : null);
                messageSheet.setVesselName(confirmationsheet.getVesselName());
                messageSheet.setProduct(confirmationsheet.getProduct());
                messageSheet.setQuantity(confirmationsheet.getQuantity());
                messageSheet.setConfirmationCD(confirmationsheet.getConfirmationSheetCd());
                messageSheet.setLaycan(DateUtil.formatDate(confirmationsheet.getLaycanStrat())
                        + "——" + DateUtil.formatDate(confirmationsheet.getLaycanEnd()));
                //1 卖家，买家  2 船代  3 买家 4 船代 卖家
                Long[][] memberIds = new Long[][]{
                        {confirmationsheet.getBuyerId(), this.producterId},
                        {confirmationsheet.getShippingAgentId()},
                        {confirmationsheet.getBuyerId()},
                        {confirmationsheet.getSellerId(), confirmationsheet.getShippingAgentId()}
                };

                String[] typeCodes = new String[]{Constants.TYPE_CODE_ONE, Constants.TYPE_CODE_TWO, Constants.TYPE_CODE_THREE, Constants.TYPE_CODE_FOUR};
                String[] urls = new String[]{
                        Constants.BUYER_CONFIRMATION_URL,
                        Constants.BUYER_CONFIRMATION_URL,
                        Constants.AGENT_CONFIRMATION_URL,
                        Constants.BUYER_CONFIRMATION_URL,
                        Constants.BUYER_CONFIRMATION_URL,
                        Constants.AGENT_CONFIRMATION_URL
                };
                int index = 0;
                int codeIndex = 0;
                for (Long[] longs : memberIds) {
                    String typeCode = typeCodes[codeIndex];
                    messageSheet.setTypeCode(typeCode);
                    for (Long memberId : longs) {
                        if (memberId != null) {
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProCreateShipConfirmationNoconfirm(messageSheet);
                        }
                        index++;
                    }
                    codeIndex ++ ;
                }
            } else {
                messageSheet.setMemberId(confirmationsheet.getShippingAgentId());
                EnterpriseVo enterpriseVo = messageService.getByMemberId(confirmationsheet.getEpMemberId());
                messageSheet.setProduct(confirmationsheet.getProduct());
                messageSheet.setQuantity(confirmationsheet.getQuantity());
                messageSheet.setConfirmationCD(confirmationsheet.getConfirmationSheetCd());
                messageSheet.setEnterprise(enterpriseVo.getName());
                messageSheet.setVesselName(confirmationsheet.getVesselName());
                messageSheet.setHyperlink(Constants.SELLER_CHARTER_PARTY_URL);
                if (messageSheet.getMemberId() != null) {
                    try{
                        notificationHelper.sendProCreateShipConfirmation(messageSheet);
                    }catch (Exception e){
                        logger.error(messageSheet.getMemberId() + "发送消息失败：",e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("确认单生成发送消息失败：", e);
        }
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long confirmationSheetId) throws BizException {
        if (confirmationSheetId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return confirmationSheetMapper.deleteById(confirmationSheetId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ConfirmationSheet record) {
        return confirmationSheetMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(ConfirmationSheet confirmationSheet) throws BizException {
        if (confirmationSheet.getConfirmationSheetId() == null) {
            throw new BizException("confirmationSheetId 为空，不能修改 ");
        }
        return confirmationSheetMapper.updateRecordById(confirmationSheet);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public int updateRecordByUuid(ConfirmationSheet confirmationSheet) throws BizException {
        if (confirmationSheet.getUuid() == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        int flag = confirmationSheetMapper.updateRecordByUuid(confirmationSheet);

        if (flag > 0) {
            //                更新单据中心的文件
                this.updateBillFile(confirmationSheet);

            //确认单为在线确认，并且修改为已确认状态，在原油的航程追踪加上一条船舶信息
            if (Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationSheet.getConfirmOnline()) && ExternalApi.CONFIRM_20.equals(confirmationSheet.getStatus())) {
                SysShip sysShip = new SysShip();
                sysShip.setImo(confirmationSheet.getImo());
                sysShipService.insertShipRemote(sysShip);
            }
        }
        try {
            if (flag > 0) {
                MessageSheet messageSheet = new MessageSheet();
                messageSheet.setQuantity(confirmationSheet.getQuantity());
                messageSheet.setProduct(confirmationSheet.getProduct());
                messageSheet.setConfirmationCD(confirmationSheet.getConfirmationSheetCd());

                Long[] memberIds = null;

                if (ExternalApi.CONFIRM_20.equals(confirmationSheet.getStatus())) {
                    messageSheet.setTypeCode(Constants.TYPE_CODE_ONE);
                    memberIds = new Long[]{
                            confirmationSheet.getBuyerId(),
                            confirmationSheet.getShippingAgentId(),
                            confirmationSheet.getCreateUser()
                    };
                    String[] urls = new String[]{
                            Constants.BUYER_CONFIRMATION_URL,
                            Constants.AGENT_CONFIRMATION_URL,
                            Constants.SELLER_CHARTER_PARTY_URL
                    };
                    int index = 0;
                    for (Long memberId : memberIds) {
                        messageSheet.setHyperlink(urls[index]);
                        if (memberId != null) {
                            messageSheet.setMemberId(memberId);
                            notificationHelper.sendProConfirmShipConfirmation(messageSheet);
                        }
                        index++;
                    }

                } else if (ExternalApi.CONFIRM_80.equals(confirmationSheet.getStatus())) {
                    messageSheet.setTypeCode(Constants.TYPE_CODE_TWO);
                    memberIds = new Long[]{confirmationSheet.getCreateUser()};
                    messageSheet.setHyperlink(Constants.SELLER_CHARTER_PARTY_URL);
                    for (Long memberId : memberIds) {
                        if (memberId != null) {
                            messageSheet.setMemberId(memberId);
                            notificationHelper.sendProConfirmShipConfirmation(messageSheet);
                        }
                    }
                }

            }
        } catch (Exception e) {
            logger.warn("确认单确认或者中止发送消息失败", e);
        }

        return flag;
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return confirmationSheetMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(ConfirmationSheet confirmationSheet) {
        return confirmationSheetMapper.updateRecords(confirmationSheet.toMap());
    }

    /**
     * 根据主键-查询对象
     */
    @Override
    public ConfirmationSheet findByPrimaryKey(Long confirmationSheetId) {
        return confirmationSheetMapper.findByPrimaryKey(confirmationSheetId);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public ConfirmationSheet findByUuid(String uuid) {
        return confirmationSheetMapper.findByUuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<ConfirmationSheet> queryByEntitys(ConfirmationSheet confirmationSheet) {
        return confirmationSheetMapper.queryByEntitys(confirmationSheet);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return confirmationSheetMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
    @Override
    public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        return (Page<Map<String, Object>>) confirmationSheetMapper.queryRecords(map);
    }

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return confirmationSheetMapper.countRecords(map);
    }

    // **************************以下方法为开发者补充*********************************/

    /**
     * 查询列表集合
     */
    @Override
    public List<ConfirmationSheetVO> queryByEntitysList(ConfirmationSheetQuery confirmationSheetQuery) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 逻辑删除
     */
    @Override
    public Integer deleteLogicConfirmationSheet(ConfirmationSheet domain) {
        domain.setAliveFlag(Constants.DELE_FLAG);
        return confirmationSheetMapper.deleteLogicConfirmationSheet(domain);
    }

    @Override
    public int insertRecordByCurrentUser(ConfirmationSheet confirmationSheet, CurrentUser currentUser) {
        // TODO Auto-generated method stub
        return confirmationSheetMapper.insertRecord(confirmationSheet);
        // return 0;
    }

    /**
     * 获取船舶确认单分页列表
     */
    @Override
    public PageInfoResult getConfirmationSheetList(
            ConfirmationSheetQuery confiQuery, PageInfo pageInfo) throws BizException {

        List<ConfirmationSheet> confirmationSheetList = confirmationSheetMapper
                .queryConfirmationSheetList(confiQuery);

        return new PageInfoResult(confirmationSheetList);
    }

    /**
     * 获取船舶确认单分页列表(泉炼)
     */
    @Override
    public PageInfoResult getConfirmationQuanhuaSheetList(
            ConfirmationSheetQuery confiQuery, PageInfo pageInfo) throws BizException {

        List<ConfirmationSheet> confirmationSheetList = confirmationSheetMapper
                .queryConfirmationQuanhuaSheetList(confiQuery);

        return new PageInfoResult(confirmationSheetList);
    }


    /**
     * 获取船舶确认单分页列表(泉炼) 微信
     */
    @Override
    public List<ConfirmationSheet> getConfirmationQuanhuaSheetListWX(
            ConfirmationSheetQuery confiQuery) throws BizException {

        List<ConfirmationSheet> confirmationSheetList = confirmationSheetMapper
                .queryConfirmationQuanhuaSheetList(confiQuery);

        return confirmationSheetList;
    }

    @Override
    public List<ConfirmationSheet> findByKeywrokds(ConfirmationSheetQuery query) {
        return confirmationSheetMapper.findByKeywrokds(query);
    }

    /**
     * 校验是否租船
     *
     * @param orderID
     */
    @Override
    public int checkConfirmationIsExsit(Long orderID) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", orderID);
        int rts = this.countRecords(map);
        return rts;
    }

    /**
     * 确认单修改状态
     */
    @Override
    public Integer updateStatusByconfirmationId(ConfirmationSheet confirmationSheet, CurrentUser currentUser) throws BizException {

        if (confirmationSheet == null) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        Integer res = null;
        if (confirmationSheet.getUpdateUser() == null) {
            //confirmationSheet.setUpdateDate(new Date());
            confirmationSheet.setUpdateUser(currentUser.getMemberId());
        }
        res = confirmationSheetMapper.updateStatusByconfirmationId(confirmationSheet);
        return res;

    }

    /**
     * 修改数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateStatusById(ConfirmationSheet confirmationSheet,
                                    CurrentUser currentUser) throws BizException {
        BizException bizException = new BizException();
        if (confirmationSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ConfirmationSheet findByUuid = confirmationSheetMapper.findByUuid(confirmationSheet.getUuid());
        Integer res = null;
        if (null != findByUuid) {
            findByUuid.setUpdateUser(currentUser.getMemberId());
            findByUuid.setStatus(ExternalApi.CONFIRM_70);
            res = confirmationSheetMapper.updateStatusByconfirmationId(findByUuid);
            if (res > 0) {
                this.sendFinishMessage(confirmationSheet);
            }

            if ("CFR".equals(findByUuid.getTradeTerms())) {
                if (findByUuid.getShipAgreementId() != null) {
                    //修改协议单状态
                    Agreement agreement = agreementMapper.findByPrimaryKey(findByUuid.getShipAgreementId());
                    agreement.setAgreementId(findByUuid.getShipAgreementId());
                    agreement.setStatus(ExternalApi.AGREEMENT_70);
                    agreement.setUpdateUser(currentUser.getMemberId());
                    agreementMapper.updateStatusByAgreementId(agreement);
                    // 修改需求单状态
                    Demands demands = new Demands();
                    demands.setDemandsId(agreement.getDemandsId());
                    demands.setStatus(ExternalApi.DEMAND_50);
                    demands.setUpdateUser(currentUser.getMemberId());
                    demandsMapper.updateStatusByDemandsId(demands);
                }
            }

            int completeContractSheet = contractSheetRemoteService.completeContractSheet(findByUuid.getOrderId());
            if (completeContractSheet != 1) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }

        }

        return res;
    }

    /**
     * 发送航程结束的消息 贸易商发送
     *
     * @param confirmationSheet
     */
    private void sendFinishMessage(ConfirmationSheet confirmationSheet) {
        try {
            MessageSheet messageSheet = new MessageSheet();
            messageSheet.setProduct(confirmationSheet.getProduct());
            messageSheet.setQuantity(confirmationSheet.getQuantity());
            messageSheet.setVesselName(confirmationSheet.getVesselName());
            messageSheet.setConfirmationSheetCd(confirmationSheet.getConfirmationSheetCd());
//			{confirmationSheetCd,vesselname,product,quantity}
            Long[] memberIds = new Long[]{
                    confirmationSheet.getBuyerId(),
                    this.producterId,
                    confirmationSheet.getSellerId(),
                    null
            };

            String[] urls = new String[]{
                    Constants.BUYER_CONFIRMATION_URL,
                    Constants.SELLER_CHARTER_PARTY_URL,
                    Constants.SELLER_CHARTER_PARTY_URL,
                    Constants.AGENT_CONFIRMATION_URL
            };
            Agreement agreement = agreementMapper.findByPrimaryKey(confirmationSheet.getShipAgreementId());
            if (agreement != null) {
                memberIds[3] = agreement.getEpMemberId();
            }
            int index = 0;
            for (Long memberId : memberIds) {
                if (memberId != null) {
                    messageSheet.setHyperlink(urls[index]);
                    messageSheet.setMemberId(memberId);
                    notificationHelper.sendProShipCompleted(messageSheet);
                }
                index++;
            }
        } catch (Exception e) {
            logger.error("航程结束发送消息失败", e);
        }

    }


    @Override
    public ConfirmationSheet findByDemendsAndAgreement(ConfirmationSheetVO vo) throws BizException {
        if (vo == null) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }


        if (StringUtil.isEmpty(vo.getUuid()) && StringUtil.isEmpty(vo.getAgreementUuid()) && StringUtil.isEmpty(vo.getDemandsUuid())) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ConfirmationSheet confirmationSheet = null;

        if (vo.getUuid() != null) {
            confirmationSheet = this.findByUuid(vo.getUuid());
            if (confirmationSheet != null) {
                return confirmationSheet;
            }
        }
        if (vo.getAgreementUuid() != null) {
            confirmationSheet = this.findByAgreementUuid(vo.getAgreementUuid());
            if (confirmationSheet != null) {
                return confirmationSheet;
            }
        }
        if (vo.getDemandsUuid() != null) {
            confirmationSheet = this.findBydemandsUuid(vo.getDemandsUuid());
            return confirmationSheet;
        }
        return null;
    }

    private ConfirmationSheet findByAgreementUuid(String agreementUuid) {
        return confirmationSheetMapper.findByAgreementUuid(agreementUuid);
    }

    private ConfirmationSheet findBydemandsUuid(String demandsUuid) {
        return confirmationSheetMapper.findBydemandsUuid(demandsUuid);

    }

    /**
     * 根据协议ID查询确认单信息
     *
     * @param
     * @param
     * @return
     */
    @Override
    public ConfirmationSheet findByAgreementId(Long id) {
        return confirmationSheetMapper.findByAgreementId(id);
    }

    /**
     * 卖家确认修改状态
     *
     * @param confirmationSheet
     * @param currentUser
     * @return
     */
    @Override
    public Integer updateStatus(ConfirmationSheet confirmationSheet, CurrentUser currentUser) throws BizException {
        BizException bizException = new BizException();
        if (confirmationSheet == null) {

            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        Integer res = null;
        if (confirmationSheet.getUpdateUser() == null) {
            //confirmationSheet.setUpdateDate(new Date());
            confirmationSheet.setUpdateUser(currentUser.getMemberId());
        }

        res = confirmationSheetMapper.updateStatusByconfirmationId(confirmationSheet);
        Agreement agreement = new Agreement();
        agreement.setAgreementId(confirmationSheet.getShipAgreementId());
        agreement.setStatus(ExternalApi.AGREEMENT_22);
        agreement.setUpdateUser(currentUser.getMemberId());
        Agreement byUuid = agreementMapper.findByUuid(agreement.getUuid());
        if (null != byUuid) {
            agreementMapper.updateStatusByAgreementId(agreement);
            Demands demands = new Demands();
            demands.setDemandsId(byUuid.getDemandsId());
            demands.setStatus(ExternalApi.DEMAND_40);
            demands.setUpdateUser(currentUser.getMemberId());
            demandsMapper.updateStatusByDemandsId(demands);
        } else {
            throw bizException;
        }
        return res;
    }

    /**
     * 更新单据中心的文件信息
     * @param confirmationsheet
     */
    private void updateBillFile(ConfirmationSheet confirmationsheet) {
        if(confirmationsheet.getUploadQ88() == null) {
            confirmationsheet.setUploadQ88("");
            confirmationsheet.setUploadQ88FileNm("");
        }
            BillCoreRemoteUpload billDomain = new BillCoreRemoteUpload();
            billDomain.setOrderId(confirmationsheet.getOrderId());
            billDomain.setUpdateUser(confirmationsheet.getUpdateUser());
            billDomain.setFileTypeCode(BillUploadEnums.VESSEL_DOCUMENTS.getCode());
            billDomain.setFilePath(confirmationsheet.getUploadQ88());
            billDomain.setFileName(confirmationsheet.getUploadQ88FileNm());
            billCoreUploadRemoteService.UpdateBillupload(billDomain);

    }
}
