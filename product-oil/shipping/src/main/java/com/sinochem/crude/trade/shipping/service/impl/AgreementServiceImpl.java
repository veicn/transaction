package com.sinochem.crude.trade.shipping.service.impl;

import java.util.*;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.enums.AgreementStatusEnums;
import com.sinochem.crude.trade.shipping.enums.BillUploadEnums;
import com.sinochem.crude.trade.shipping.enums.DemanteStatusEnums;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;

import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.MessageService;
import com.sinochem.crude.trade.transaction.remote.BillCoreUploadRemoteService;
import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.DemandsMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.model.query.AgreementQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatAgreementQuery;
import com.sinochem.crude.trade.shipping.model.vo.AgreementVO;
import com.sinochem.crude.trade.shipping.service.AgreementService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final Logger logger = LoggerFactory.getLogger(AgreementServiceImpl.class);
    @Autowired
    private AgreementMapper agreementMapper;
    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper;
    @Autowired
    private DemandsMapper demandsMapper;

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private BillCoreUploadRemoteService billCoreUploadRemoteService;

    public AgreementMapper getMapper() {
        return agreementMapper;
    }

    /**
     * 新增
     */
    @Override
    public int insertRecord(Agreement agreement) {
        return agreementMapper.insertRecord(agreement);
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long agreementId) throws BizException {
        if (agreementId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return agreementMapper.deleteById(agreementId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Agreement record) {
        return agreementMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(Agreement agreement) throws BizException {
        if (agreement.getAgreementId() == null) {
            throw new BizException("agreementId 为空，不能修改 ");
        }
        return agreementMapper.updateRecordById(agreement);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public int updateRecordByUuid(Agreement agreement) throws BizException {
        if (agreement.getUuid() == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        return agreementMapper.updateRecordByUuid(agreement);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return agreementMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Agreement agreement) {
        return agreementMapper.updateRecords(agreement.toMap());
    }


    /**
     * 根据主键-查询对象
     */
    @Override
    public Agreement findByPrimaryKey(Long agreementId) {
        return agreementMapper.findByPrimaryKey(agreementId);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public Agreement findByUuid(String uuid) {
        return agreementMapper.findByUuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<Agreement> queryByEntitys(Agreement agreement) {
        return agreementMapper.queryByEntitys(agreement);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return agreementMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
    @Override
    public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        return (Page<Map<String, Object>>) agreementMapper.queryRecords(map);
    }

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return agreementMapper.countRecords(map);
    }


    //**************************以下方法为开发者补充*********************************/

    /**
     * 修改租船协议单
     */
    @Override
    public Integer updateAgreement(AgreementVO vo, CurrentUser user) {
        int code = 0;
        //修改租船代理协议表
        Agreement agreement = new Agreement();
        agreement.setUuid(vo.getUuid());
        agreement.setVesselName(vo.getVesselName());
        agreement.setImo(vo.getImo());
        agreement.setAliveFlag(Constants.SAVE_FLAG);
        agreement.setLaycanStrat(DateUtil.getDate(vo.getLaycanStrat()));
        agreement.setLaycanEnd(DateUtil.getDate(vo.getLaycanEnd()));
        agreement.setUploadQ88(vo.getUploadQ88());
        agreement.setUploadCp(vo.getUploadCp());
        agreement.setUploadQ88FileNm(vo.getUploadQ88FileNm());
        agreement.setUploadCpFileNm(vo.getUploadCpFileNm());
        agreement.setUploadQ88(vo.getUploadQ88());
        agreement.setUploadCp(vo.getUploadCp());
        agreement.setUpdateDate(DateUtil.getCurrentDate());
        agreement.setUpdateUser(user.getMemberId());
        code = agreementMapper.updateRecordByUuid(agreement);

        if(code > 0){
            //                更新单据中心的文件
            this.updateBillFile(agreement);
        }
        //修改船舶确认单的船舶信息
        Agreement findByUuid = agreementMapper.findByUuid(vo.getUuid());
        ConfirmationSheet confirmationSheet = new ConfirmationSheet();
        confirmationSheet.setShipAgreementId(findByUuid.getAgreementId());
        confirmationSheet.setAliveFlag(Constants.SAVE_FLAG);
        List<ConfirmationSheet> list = confirmationSheetMapper.queryByEntitys2(confirmationSheet);
        if (list.size() > 0) {
            for (ConfirmationSheet confirmationSheet2 : list) {
                confirmationSheet.setUuid(confirmationSheet2.getUuid());
            }
            confirmationSheet.setVesselName(vo.getVesselName());
            confirmationSheet.setImo(vo.getImo());
            confirmationSheet.setAliveFlag(Constants.SAVE_FLAG);
            confirmationSheet.setLaycanStrat(DateUtil.getDate(vo.getLaycanStrat()));
            confirmationSheet.setLaycanEnd(DateUtil.getDate(vo.getLaycanEnd()));
            confirmationSheet.setUploadQ88(vo.getUploadQ88());
            confirmationSheet.setUploadQ88FileNm(vo.getUploadQ88FileNm());
            confirmationSheet.setUploadCp(vo.getUploadCp());
            confirmationSheet.setUploadCpFileNm(vo.getUploadCpFileNm());
            confirmationSheet.setUpdateDate(DateUtil.getCurrentDate());
            confirmationSheet.setUpdateUser(user.getMemberId());
            int code2 = confirmationSheetMapper.updateRecordByUuid(confirmationSheet);
            if (code2 <= 0) {
                code = 0;
            }
        }

        return code;

    }

    /**
     * 生成租船协议单
     */
    @Override
    public Integer saveAgreement(AgreementVO vo, CurrentUser user) {

        //根据需求uuid查看租船需求表对象
        Demands demands = demandsMapper.findByUuid(vo.getDemandsUuid());
        Agreement agreement = new Agreement();

        agreement.setUuid(KeyGenUtils.newUuid());
        agreement.setDemandsId(demands.getDemandsId());
        agreement.setOrderId(demands.getOrderId());
        agreement.setBuyerId(demands.getBuyerId());
        agreement.setSellerId(demands.getSellerId());
        agreement.setCharterPartyDate(DateUtil.getCurrentDate());
        agreement.setAgreementCd(SequenceUtils.nextSequence(Constants.AGREEMENT_CODE));
        agreement.setCharterParty(vo.getCharterParty());
        agreement.setVesselName(vo.getVesselName());
        agreement.setProductNm(demands.getProdoctNm());
        agreement.setImo(vo.getImo());
        agreement.setConsignor(vo.getConsignor());
        agreement.setCharteringAgentNm(vo.getCharteringAgentNm());
        agreement.setCharteringAgentNm(vo.getCharteringAgentNm());
        agreement.setQuantity(demands.getQuantity());
        agreement.setRangeOfError(demands.getRangeOfError());
        agreement.setPortOfLoading(demands.getPortOfLoading());
        agreement.setPortOfDischarge(demands.getPortOfDischarge());
        agreement.setLaycanStrat(demands.getLaycanStart());
        agreement.setLaycanEnd(demands.getLaycanEnd());
        agreement.setEpMemberId(user.getEpMemberId());
        agreement.setUploadQ88(vo.getUploadQ88());
        agreement.setUploadCpFileNm(vo.getUploadCpFileNm());
        agreement.setUploadCp(vo.getUploadCp());
        agreement.setUploadQ88FileNm(vo.getUploadQ88FileNm());
        agreement.setCreateDate(new Date());
        agreement.setCreateUser(user.getMemberId());
        agreement.setUpdateDate(new Date());
        agreement.setUpdateUser(user.getMemberId());
        agreement.setStatus(AgreementStatusEnums.CHARTER_CONFIRMED.getCode());
        agreement.setAliveFlag(Constants.SAVE_FLAG);
        agreement.setProductSourceCode(vo.getProductSourceCode());
        int record = this.insertRecord(agreement);

        if(record > 0){
            //                更新单据中心的文件
            this.updateBillFile(agreement);
        }
        //修改需求单状态
        if (record > 0) {
            demands.setAliveFlag(Constants.SAVE_FLAG);
            demands.setStatus(DemanteStatusEnums.CHARTER_CONFIRMED.getCode());
            demands.setUpdateUser(user.getMemberId());
            demands.setUpdateDate(DateUtil.getCurrentDate());
        }
        int res = demandsMapper.updateRecordByUuid(demands);

        if(record>0){
            //租船代理提交协议后，给贸易商创建需求单的人发送消息 (贸易商变为卖家)
            try {
                MessageSheet messageSheet = new MessageSheet();
                EnterpriseVo enterpriseVo = messageService.getByMemberId(agreement.getBuyerId());
                //memberId为贸易商的memberId  即需求单创建者的memberId
                messageSheet.setMemberId(demands.getCreateUser());
                messageSheet.setBuyer(enterpriseVo.getName());
                messageSheet.setAgreementCd(agreement.getAgreementCd());
                messageSheet.setProduct(agreement.getProductNm());
                messageSheet.setQuantity(agreement.getQuantity());
                messageSheet.setHyperlink(Constants.SELLER_CHARTER_PARTY_URL);
                if(messageSheet.getMemberId()!=null){
                    notificationHelper.sendProCreateShipAgreement(messageSheet);
                }
            } catch (Exception e) {
                logger.error("发送消息失败", e);
            }
        }

        return res;
    }

    /**
     * 租船协议确认
     */
    @Override
    public Integer confirmAgreement(AgreementVO vo, CurrentUser user) {
        int code = 0;
        if (vo != null) {
            Agreement agreement = vo.getDomain();
            agreement.setStatus(AgreementStatusEnums.CHARTER_HAVE.getCode());
            agreement.setAliveFlag(Constants.SAVE_FLAG);
            agreement.setUpdateDate(DateUtil.getCurrentDate());
            agreement.setUpdateUser(user.getMemberId());
            code = agreementMapper.updateRecordByUuid(agreement);
            Agreement findAgreement = agreementMapper.findByUuid(vo.getUuid());
            if(code>0){
                //给租船协议操作人发送消息
                try{
                    MessageSheet messageSheet = new MessageSheet();
                    messageSheet.setMemberId(findAgreement.getCreateUser());
                    messageSheet.setHyperlink(Constants.DISPONENT_OWER_CHARTER_PARTY_URL);
                    //typeCode为1表示租船协议确认
                    messageSheet.setTypeCode(Constants.TYPE_CODE_ONE);
                    //这里应该为协议单的编码而不是确认单的编码
                    messageSheet.setConfirmationSheetCd(findAgreement.getAgreementCd());
                    messageSheet.setProduct(findAgreement.getProductNm());
                    messageSheet.setQuantity(findAgreement.getQuantity());

                    if(messageSheet.getMemberId()!=null){
                        notificationHelper.sendProConfirmShipAgreement(messageSheet);
                    }
                }catch (Exception e){
                    logger.warn("租船协议确认消息发送失败！",e);
                }
            }

            if (code > 0) {
                Demands findByPrimaryKey = demandsMapper.findByPrimaryKey(findAgreement.getDemandsId());
                Demands demands = new Demands();
                demands.setUuid(findByPrimaryKey.getUuid());
                demands.setAliveFlag(Constants.SAVE_FLAG);
                demands.setStatus(DemanteStatusEnums.CHARTER_CONFIRMED.getCode());
                demands.setUpdateDate(DateUtil.getCurrentDate());
                demands.setUpdateUser(user.getMemberId());
                int code2 = demandsMapper.updateRecordByUuid(demands);
                if (code2 <= 0) {
                    code = 0;
                } else {

                }
            }
        }
        return code;
    }

    /**
     * 微信API 获取协议列表
     */
    @Override
    public List<Agreement> getAgreementList(WechatAgreementQuery query) {
        //PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        if(query==null || query.getEpMemberId()==null)
        {
            return  new ArrayList<Agreement>();
        }
        return agreementMapper.queryAgreementList(query);
    }

    /**
     * 微信API 获取协议列表(贸易商)
     */
    @Override
    public List<Agreement> getAgreementListtrader(WechatAgreementQuery query) {
        //PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        if(query==null || query.getEpMemberId()==null)
        {
            return  new ArrayList<Agreement>();
        }
        return agreementMapper.queryAgreementListtrader(query);
    }

    /*
     * 租船协议列表(二船东)
     */
    @Override
    public PageInfoResult queryByEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user) {
        //PageUtils.page(pageInfo);
        List<Agreement> agreementList = agreementMapper.queryAgreemenrByEntitys(query);
        return new PageInfoResult(agreementList);
    }

    /*
     * 租船协议列表(贸易商)
     */
    @Override
    public PageInfoResult queryByDemandsEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user) {
        List<Agreement> agreementList = agreementMapper.queryDemandsByEntitys(query);
        return new PageInfoResult(agreementList);
    }

    /*
 * 租船协议列表(贸易商 泉炼)
 */
    @Override
    public PageInfoResult queryDemandsQuanlianByEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user) {
        List<Agreement> agreementList = agreementMapper.queryDemandsQuanlianByEntitys(query);
        return new PageInfoResult(agreementList);
    }

    /*
     * 微信租船协议列表(贸易商 泉炼)
     */
    @Override
    public List<Agreement> queryDemandsQuanlianByEntitysListWX(AgreementQuery query) {
        List<Agreement> agreementList = agreementMapper.queryDemandsQuanlianByEntityswx(query);
        return agreementList;
    }

    @Override
    public Integer updateStatusByAgreementId(Agreement agreement, CurrentUser user) throws BizException {
        if (agreement == null) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        agreement.setUpdateDate(new Date());
        agreement.setUpdateUser(user.getMemberId());
        Integer res = null;
        if (StringUtil.isEmpty(agreement.getStatus())) {
            agreement.setStatus(ExternalApi.AGREEMENT_10);
        }

        res = agreementMapper.updateStatusByAgreementId(agreement);

        return res;

    }

    /**
     * 逻辑删除
     */
    @Override
    public int deleteAgreement(Agreement agreement, CurrentUser user) {
        agreement.setAliveFlag(Constants.DELE_FLAG);
        agreement.setUpdateUser(user.getMemberId());
        agreement.setUpdateDate(DateUtil.getCurrentDate());
        int code = agreementMapper.updateRecordByUuid(agreement);
        if(code>0){
            //给租船协议操作人发送消息
            try{
                Agreement findAgreement = agreementMapper.findByUuid(agreement.getUuid());
                MessageSheet messageSheet = new MessageSheet();
                messageSheet.setMemberId(findAgreement.getCreateUser());
                messageSheet.setHyperlink(Constants.SELLER_CHARTER_PARTY_URL);
                //typeCode为1表示租船协议确认
                messageSheet.setTypeCode(Constants.TYPE_CODE_TWO);
                //这里应该为协议单的编码而不是确认单的编码
                messageSheet.setConfirmationSheetCd(findAgreement.getAgreementCd());
                messageSheet.setProduct(findAgreement.getProductNm());
                messageSheet.setQuantity(findAgreement.getQuantity());
                notificationHelper.sendProConfirmShipAgreement(messageSheet);
            }catch (Exception e){
                logger.warn("租船协议删除消息发送失败！",e);
            }
        }
        return code;
    }


    /**
     * 更新单据中心的文件信息
     * @param agreement
     */
    private void updateBillFile(Agreement agreement){
        if(agreement== null){
            logger.error("协议单更新单据中心，参数为空");
            return;
        }
        BillCoreRemoteUpload billDomain = new BillCoreRemoteUpload();
        billDomain.setOrderId(agreement.getOrderId());
        billDomain.setUpdateUser(agreement.getUpdateUser());
        billDomain.setFileTypeCode(BillUploadEnums.VESSEL_DOCUMENTS.getCode());
        billDomain.setFilePath(agreement.getUploadQ88());
        billDomain.setFileName(agreement.getUploadQ88FileNm());
        if(StringUtil.isEmpty(billDomain.getFilePath())) {
            billCoreUploadRemoteService.deleteRemoteUpload(billDomain);
        }else{
            billCoreUploadRemoteService.UpdateBillupload(billDomain);
        }
    }

}
