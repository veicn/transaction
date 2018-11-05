package com.sinochem.crude.trade.shipping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.LoadPortMapper;
import com.sinochem.crude.trade.shipping.dao.LoadingProgressMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;
import com.sinochem.crude.trade.shipping.service.LoadPortService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class LoadPortServiceImpl implements LoadPortService {

    private final Logger logger = LoggerFactory.getLogger(LoadPortServiceImpl.class);


    @Autowired
    private LoadPortMapper loadPortMapper;

    @Autowired
    private ConfirmationSheetMapper confirmationSheetMapper; //确认单mapper
    @Autowired
    private LoadingProgressMapper loadingProgressMapper; //装港进度mapper

    @Autowired
    private AgreementMapper agreementMapper; //需求单mapper

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private MessageService messageService;

    public LoadPortMapper getMapper() {
        return loadPortMapper;
    }

    /**
     * 根据确认单id查询装港信息
     *
     * @param
     * @return
     */
    @Override
    public LoadPort queryConfirmationSheetId(String csheetid) {

        //add by fengzk  判断该确认单是否已经具有装港信息，如果有不许添加.
        ConfirmationSheet confirmationSheet = confirmationSheetMapper.findByUuid(csheetid);
        if (confirmationSheet != null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("confId", confirmationSheet.getConfirmationSheetId());
            return loadPortMapper.queryConfirmationSheetId(map);
        }

        return null;
    }

    /**
     * 新增
     */
    @Override
    public int insertRecord(LoadPort loadport) {
        return loadPortMapper.insertRecord(loadport);
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long shipLoadPortId) throws BizException {
        if (shipLoadPortId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return loadPortMapper.deleteById(shipLoadPortId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(LoadPort record) {
        return loadPortMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(LoadPort loadPort) throws BizException {
        if (loadPort.getShipLoadPortId() == null) {
            throw new BizException("shipLoadPortId 为空，不能修改 ");
        }
        return loadPortMapper.updateRecordById(loadPort);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public int updateRecordByUuid(LoadPort loadPort) throws BizException {
        if (loadPort.getUuid() == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        return loadPortMapper.updateRecordByUuid(loadPort);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return loadPortMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(LoadPort loadPort) {
        return loadPortMapper.updateRecords(loadPort.toMap());
    }


    /**
     * 根据主键-查询对象
     */
    @Override
    public LoadPort findByPrimaryKey(Long shipLoadPortId) {
        return loadPortMapper.findByPrimaryKey(shipLoadPortId);
    }

    /**
     * 根据关联id查询对象
     */
    @Override
    public LoadPort findByUuid(String uuid) {
        return loadPortMapper.findByUuid(uuid);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public LoadPort confirmationfindbyuuid(String uuid) {
        return loadPortMapper.confirmationfindbyuuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<LoadPort> queryByEntitys(LoadPort loadPort) {
        return loadPortMapper.queryByEntitys(loadPort);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return loadPortMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
    @Override
    public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        return (Page<Map<String, Object>>) loadPortMapper.queryRecords(map);
    }

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return loadPortMapper.countRecords(map);
    }

    //**************************以下方法为开发者补充*********************************/

    /**
     * 装港信息新增第一步(微信)
     *
     * @throws BizException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer firstStepSave(LoadPort domain, CurrentUser currentUser, List<LoadingProgress> progreeList) throws BizException {
        BizException bizException = new BizException();
        if (null == domain || domain.getStep() == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ConfirmationSheet confirmationSheet = confirmationSheetMapper.findByUuid(domain.getComUuid());

        LoadPort loadport = null;
        //判断uuid 是否存在
        if (StringUtil.isNotBlank(domain.getUuid())) {
            //查询本表信息
            loadport = loadPortMapper.findLoadUuid(domain.getUuid());
            domain.setShipLoadPortId(loadport.getShipLoadPortId());
            domain.setShipConfirmationSheetId(loadport.getShipConfirmationSheetId());
        } else {
            //判断传入的ComUuid是否为""或null 是则抛出异常
            if (StringUtil.isBlank(domain.getComUuid())) {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }
            //拿到主键
            domain.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
            domain.setUuid(KeyGenUtils.newUuid());
        }
        if (currentUser != null) {
            domain.setCreateUser(currentUser.getMemberId());
            domain.setUpdateUser(currentUser.getMemberId());
        }
        domain.setAliveFlag(Constants.SAVE_FLAG);
        Integer res = null;
        if (domain.getStep().equals(Constants.STEP_ONE)) {
            res = loadPortMapper.firstStepSave(domain);
        }
        if (domain.getStep().equals(Constants.STEP_TWO)) {
            res = loadPortMapper.theSecondStepSave(domain);

        }
        if (domain.getStep().equals(Constants.STEP_THREE)) {
            res = loadPortMapper.stepFourSave(domain);

            //第三步 携带list 装货进度表
            if (null != progreeList) {

                Integer count = loadingProgressMapper.findCountByShipLoadportId(domain.getShipLoadPortId());
                if (count > 0) {
                    loadingProgressMapper.deleteLoadPortId(domain.getShipLoadPortId());
                }
                for (int i = 0; i < progreeList.size(); i++) {
                    progreeList.get(i).setShipLoadingProgressId(domain.getShipLoadPortId());
                    progreeList.get(i).setUuid(KeyGenUtils.newUuid());
                    progreeList.get(i).setShipConfirmationSheetId(domain.getShipConfirmationSheetId());
                    progreeList.get(i).setAliveFlag(Constants.SAVE_FLAG);
                    progreeList.get(i).setShipLoadPortId(domain.getShipLoadPortId().toString());
                    if (currentUser != null) {
                        progreeList.get(i).setCreateUser(currentUser.getMemberId());
                        progreeList.get(i).setUpdateUser(currentUser.getMemberId());
                    }
                    loadingProgressMapper.insertRecord(progreeList.get(i));
                }
            }
        }
        //第四步
        if (domain.getStep().equals(Constants.STEP_FOUR)) {
            res = loadPortMapper.theThirdStepSave(domain);

            if (domain.getLeaveDatetime() != null) {
                ConfirmationSheet findByPrimaryKey = confirmationSheetMapper.findByPrimaryKey(domain.getShipConfirmationSheetId());
                if (null != findByPrimaryKey) {
                    if ("FOB".equals(findByPrimaryKey.getTradeTerms())) {
                        findByPrimaryKey.setStatus(ExternalApi.CONFIRM_40);
                        if (currentUser != null) {
                            findByPrimaryKey.setUpdateUser(currentUser.getMemberId());
                        }
                        confirmationSheetMapper.updateStatusByconfirmationId(findByPrimaryKey);
                    } else {

                        findByPrimaryKey.setStatus(ExternalApi.CONFIRM_50);
                        if (currentUser != null) {
                            findByPrimaryKey.setUpdateUser(currentUser.getMemberId());
                        }
                        confirmationSheetMapper.updateStatusByconfirmationId(findByPrimaryKey);
                        Agreement agreement = new Agreement();
                        agreement.setAgreementId(findByPrimaryKey.getShipAgreementId());
                        if (currentUser != null) {
                            agreement.setUpdateUser(currentUser.getMemberId());
                        }
                        agreement.setStatus(ExternalApi.AGREEMENT_50);
                        agreementMapper.updateStatusByAgreementId(agreement);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 根据确认单主键 查询装港信息
     *
     * @param conSellerId
     * @return
     * @throws BizException
     */
    public LoadPort conSellerIdqueryLoadPost(String conSellerId) throws BizException {
        BizException bizException = new BizException();
        LoadPort loadport = loadPortMapper.ConsellerIdqueryLoadPost(conSellerId);
        if (null == loadport) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        return loadport;
    }

    @Override
    public LoadPort findByShipConfirmationSheetId(Long confirmationSheetId) {
        return loadPortMapper.findByShipConfirmationSheetId(confirmationSheetId);
    }
}
