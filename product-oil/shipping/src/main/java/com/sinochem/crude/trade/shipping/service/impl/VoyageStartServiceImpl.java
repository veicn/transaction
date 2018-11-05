package com.sinochem.crude.trade.shipping.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.enums.BillUploadEnums;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.AgreementService;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.crude.trade.transaction.remote.BillCoreUploadRemoteService;
import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.VoyageStartMapper;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.model.query.VoyageStartQuery;
import com.sinochem.crude.trade.shipping.model.vo.VoyageStartVO;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.crude.trade.shipping.service.VoyageStartService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoyageStartServiceImpl implements VoyageStartService {

    private final Logger logger = LoggerFactory.getLogger(VoyageStartServiceImpl.class);

    @Value("${id.quanzhou}")
    private Long producterId;

    @Autowired
    private VoyageStartMapper voyageStartMapper;
    @Autowired
    private ConfirmationSheetService confirmationSheetService;
    @Autowired
    private AgreementService agreementService;

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private BillCoreUploadRemoteService billCoreUploadRemoteService;

    /**
     * 新增
     */
    @Override
    public int insertRecord(VoyageStart voyagestart) {
        if (!StringUtil.isNotBlank(voyagestart.getUuid())) {
            voyagestart.setUuid(UUID.randomUUID().toString());
        }
        return voyageStartMapper.insertRecord(voyagestart);
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long voyageStartId) throws BizException {
        if (voyageStartId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return voyageStartMapper.deleteById(voyageStartId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(VoyageStart record) {
        return voyageStartMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(VoyageStart voyageStart) throws BizException {
        if (voyageStart.getVoyageStartId() == null) {
            throw new BizException("voyageStartId 为空，不能修改 ");
        }
        return voyageStartMapper.updateRecordById(voyageStart);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public Integer updateRecordByUuid(VoyageStart voyageStart, CurrentUser currentUser) throws BizException {
        if (voyageStart.getUuid() == null || currentUser == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        voyageStart.setUpdateUser(currentUser.getMemberId());
        Integer flag = voyageStartMapper.updateRecordByUuid(voyageStart);
        ConfirmationSheet confirmationSheet = confirmationSheetService.findByPrimaryKey(voyageStart.getShipConfirmationSheetId());
        if (flag > 0) {
            this.updateBillFile(voyageStart, confirmationSheet.getOrderId());
        }

        if (flag > 0) {
            try {
                //发送消息
                if (flag > 0) {
                    this.sendUpdateVoyageStartMessage(confirmationSheet);
                }
            } catch (Exception e) {
                logger.error("更新配载计划发送消息失败", e);
            }
        }
        return flag;
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return voyageStartMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(VoyageStart voyageStart) {
        return voyageStartMapper.updateRecords(voyageStart.toMap());
    }


    /**
     * 根据主键-查询对象
     */
    @Override
    public VoyageStart findByPrimaryKey(Long voyageStartId) {
        return voyageStartMapper.findByPrimaryKey(voyageStartId);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public VoyageStart findByUuid(String uuid) {
        return voyageStartMapper.findByUuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<VoyageStart> queryByEntitys(VoyageStart voyageStart) {
        return voyageStartMapper.queryByEntitys(voyageStart);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return voyageStartMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
    @Override
    public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        return (Page<Map<String, Object>>) voyageStartMapper.queryRecords(map);
    }

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return voyageStartMapper.countRecords(map);
    }

    //**************************以下方法为开发者补充*********************************/

    /**
     * 查询列表集合
     */
    @Override
    public List<VoyageStartVO> queryByEntitysList(
            VoyageStartQuery voyageStartQuery) {

        return voyageStartMapper.queryByEntitysList(voyageStartQuery);
    }

    /**
     * 逻辑删除
     */
    @Override
    public Integer VoyageLogicDelete(VoyageStartQuery voyageStartQuery) {
        return voyageStartMapper.VoyageLogicDelete(voyageStartQuery);
    }

    /**
     * 保存航次开始记录 同时更新协议和确认单的状态 武刚鹏-2018年3月21日15:56:45
     *
     * @param voyageStart
     * @param currentUser
     * @return Integer
     * @throws BizException
     */
    @Override
    @Transactional
    public Integer insertRecordByCurrentUser(VoyageStart voyageStart, CurrentUser currentUser) throws BizException {
        if (voyageStart == null || StringUtil.isBlank(voyageStart.getShipConfirmationSheetId().toString())) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ConfirmationSheet confirmationSheet = confirmationSheetService.findByPrimaryKey(voyageStart.getShipConfirmationSheetId());
        if (confirmationSheet != null) {
            VoyageStart domainVoyageStart = voyageStartMapper.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
            if (domainVoyageStart == null) {
                Agreement agreement = new Agreement();
                agreement.setAgreementId(confirmationSheet.getShipAgreementId());
                //状态改为已确认，才能在接下来的service修改为航次开始
                agreement.setStatus(ExternalApi.AGREEMENT_30);
                agreementService.updateStatusByAgreementId(agreement, currentUser);
                confirmationSheet.setStatus(ExternalApi.CONFIRM_30);
                confirmationSheetService.updateStatusByconfirmationId(confirmationSheet, currentUser);
                if (StringUtil.isEmpty(voyageStart.getUuid())) {
                    voyageStart.setUuid(KeyGenUtils.newUuid());
                }
                voyageStart.setCreateUser(currentUser.getMemberId());
                voyageStart.setUpdateUser(currentUser.getMemberId());
                voyageStart.setAliveFlag(Constants.SAVE_FLAG);
                int flag = voyageStartMapper.insertRecord(voyageStart);
//                更新单据中心的文件
                if (flag > 0) {
                    this.updateBillFile(voyageStart, confirmationSheet.getOrderId());
                }
                try {
                    //发送消息
                    if (flag > 0) {
                        this.sendUpdateVoyageStartMessage(confirmationSheet);
                    }
                } catch (Exception e) {
                    logger.error("添加配载计划发送消息失败", e);
                }
                return flag;
            } else {
                return 0;
            }

        } else {
            return 0;
        }

    }

    @Override
    public VoyageStart findByShipConfirmationSheetId(Long confirmationSheetId) {
        VoyageStart voyageStart = voyageStartMapper.findByShipConfirmationSheetId(confirmationSheetId);
        if (voyageStart == null) {
            voyageStart = new VoyageStart();
        }
        BillCoreRemoteUpload billModel = new BillCoreRemoteUpload();
        ConfirmationSheet confirmationSheet = confirmationSheetService.findByPrimaryKey(confirmationSheetId);
        if (confirmationSheet != null) {
            billModel.setOrderId(confirmationSheet.getOrderId());
            billModel.setFileTypeCode(BillUploadEnums.DI.getCode());
            try {
                BillCoreRemoteUpload billDiDomain = billCoreUploadRemoteService.findbyOrderIdList(billModel);
                if (billDiDomain != null && billDiDomain.getFilePath() != null) {
                    voyageStart.setDi(billDiDomain.getFilePath());
                    voyageStart.setDiFileNm(billDiDomain.getFileName());
                }
                billModel.setFileTypeCode(BillUploadEnums.STOWAGE_PLAN.getCode());
                BillCoreRemoteUpload billPlanDomain = billCoreUploadRemoteService.findbyOrderIdList(billModel);
                if (billPlanDomain != null && billPlanDomain.getFilePath() != null) {
                    voyageStart.setAccessory(billPlanDomain.getFilePath());
                    voyageStart.setAccessoryFileNm(billPlanDomain.getFileName());
                }
            } catch (BizException e) {
                logger.error("参数为空");
            }
        }
        return voyageStart;
    }

    private void sendUpdateVoyageStartMessage(ConfirmationSheet confirmationSheet) {
        MessageSheet messageSheet = new MessageSheet();
        messageSheet.setQuantity(confirmationSheet.getQuantity());
        messageSheet.setProduct(confirmationSheet.getProduct());
        messageSheet.setOrderNumber(confirmationSheet.getOrderNumber());
        Long[] memberIds = new Long[]{
                confirmationSheet.getBuyerId(),
                this.producterId,
                confirmationSheet.getShippingAgentId(),
                null
        };
        String[] urls = new String[]{
                Constants.BUYER_CONFIRMATION_URL,
                Constants.SELLER_CHARTER_PARTY_URL,
                Constants.AGENT_CONFIRMATION_URL,
                null
        };
        if ("CFR".equals(confirmationSheet.getTradeTerms())) {
            if (Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationSheet.getConfirmOnline())) {
                Agreement agreement = agreementService.findByPrimaryKey(confirmationSheet.getShipAgreementId());
                if (agreement != null) {
                    //船代
                    memberIds[3] = agreement.getEpMemberId();
                    urls[3] = Constants.AGENT_CONFIRMATION_URL;
                }
            } else {
                memberIds[3] = confirmationSheet.getSellerId();
                urls[3] = Constants.SELLER_CHARTER_PARTY_URL;
            }
        } else {
            memberIds[3] = confirmationSheet.getSellerId();
            urls[3] = Constants.SELLER_CHARTER_PARTY_URL;
        }
        int index = 0;
        for (Long memberId : memberIds) {
            if (memberId != null) {
                messageSheet.setMemberId(memberId);
                messageSheet.setHyperlink(urls[index]);
                notificationHelper.sendProDisponentOwnerUpdateStowagePlan(messageSheet);
            }
            index++;
        }
    }

    private void updateBillFile(VoyageStart voyageStart, Long orderId) {
        BillCoreRemoteUpload billDomain = new BillCoreRemoteUpload();
        billDomain.setUpdateUser(voyageStart.getUpdateUser());
        billDomain.setAliveFlag(Constants.SAVE_FLAG);
        billDomain.setOrderId(orderId);
        if (voyageStart.getAccessory() == null) {
            voyageStart.setAccessoryFileNm("");
            voyageStart.setAccessory("");
        }
        if (voyageStart.getDi() == null) {
            voyageStart.setDi("");
            voyageStart.setDiFileNm("");
        }

        //删除  upload Plan
        billDomain.setFileName(voyageStart.getAccessoryFileNm());
        billDomain.setFilePath(voyageStart.getAccessory());
        billDomain.setFileTypeCode(BillUploadEnums.STOWAGE_PLAN.getCode());
        if (StringUtil.isEmpty(billDomain.getFilePath())) {
            billCoreUploadRemoteService.deleteRemoteUpload(billDomain);
        } else {
            billCoreUploadRemoteService.UpdateBillupload(billDomain);
        }

        //删除di
        billDomain.setFileName(voyageStart.getDiFileNm());
        billDomain.setFilePath(voyageStart.getDi());
        billDomain.setFileTypeCode(BillUploadEnums.DI.getCode());
        if (StringUtil.isEmpty(billDomain.getFilePath())) {
            billCoreUploadRemoteService.deleteRemoteUpload(billDomain);
        } else {
            billCoreUploadRemoteService.UpdateBillupload(billDomain);
        }

    }
}
