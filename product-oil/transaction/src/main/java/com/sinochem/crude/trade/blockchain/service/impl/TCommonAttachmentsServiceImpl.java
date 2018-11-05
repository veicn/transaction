package com.sinochem.crude.trade.blockchain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.dao.TBlockchainEventRecordMapper;
import com.sinochem.crude.trade.blockchain.dao.TCommonAttachmentsMapper;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.crude.trade.broker.service.impl.DeclarationService;
import com.sinochem.crude.trade.customs.constant.Constant;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/13 16:45
 * @Version: [v1.0]
 */
@Service
public class TCommonAttachmentsServiceImpl implements TCommonAttachmentsService {
    @Autowired
    private TCommonAttachmentsMapper tCommonAttachmentsMapper;
    @Autowired
    private TBlockchainEventRecordMapper tBlockchainEventRecordMapper;
    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;
    @Autowired
    private TTransExtendService tTransExtendService;
    @Autowired
    private BlockChainService blockChainService;

    @Autowired
    private DeclarationService declarationService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AppointTaskService appointTaskService;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public int updateByUuid(TBrokerVO tBrokerVO) {
       if(tBrokerVO!=null){
           if(tBrokerVO.gettBrokerVoucher()!=null&&tBrokerVO.gettBrokerVoucher().size()>0){
              return updateByUuid(tBrokerVO.gettBrokerVoucher());
           }
           else {
               return deleteByBusinessUuid(tBrokerVO.getUuid());
           }
       }
       return 0;
    }

    @Override
    public int updateByUuid(List<TCommonAttachments> list) {
        if(list!=null && list.size()>0) {
            deleteByBusinessUuid(list.get(0).getBusinessUuid());
            return tCommonAttachmentsMapper.insertList(list);
        }
        return 0;
    }

    @Override
    public int deleteByBusinessUuid(String businessUuid) {
        return tCommonAttachmentsMapper.deleteByBusinessUuid(businessUuid, "");
    }

    @Override
    public int insert(TCommonAttachments record) {
        return tCommonAttachmentsMapper.insert(record);
    }

    Gson gson = new Gson();

    /**
     * 根据业务uuid 批量删除后新增
     * @param list
     * @return
     * @throws BizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<TCommonAttachments> list) throws BizException {
        if (list != null && list.size() > 0) {
            TBrokerDeclaration tBrokerDeclaration = declarationService.selectByUuid(list.get(0).getBusinessUuid());
            if (tBrokerDeclaration == null) {
                LOGGER.info("报关单上传附件--查询不到对应报关单");
                return 0;
            }
            tCommonAttachmentsMapper.deleteByBusinessUuid(list.get(0).getBusinessUuid(), list.get(0).getFileType());
            int num = tCommonAttachmentsMapper.insertList(list);

            //上链
            TTransExtend tTransExtend = tTransExtendService.selectByPurchaseContractNo(tBrokerDeclaration.getPurchaseContractNo());
            TBlockchainEventRecord tBlockchainEventRecord = new TBlockchainEventRecord();
            tBlockchainEventRecord.setCreateUser(tBrokerDeclaration.getEnterpriseId());
            if (tTransExtend != null) {
                tBlockchainEventRecord.setDealNo(tTransExtend.getDealNo());
            }
            String content = gson.toJson(list);
            BlockChainEntity entity = blockChainService.insertToBlockChain(content);//分别上链
            BizException bizException = new BizException();
            if (null == entity || !entity.getCode().equals("1")) {
                bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
                throw new BizException();
            }
//                for (TCommonAttachments item:list
//                     ) {

            tBlockchainEventRecord.setBusiId(Long.valueOf(tBrokerDeclaration.getId()));
            if (content.length() > 1000) {
                tBlockchainEventRecord.setPostData(content.substring(0, 1000));
            } else {
                tBlockchainEventRecord.setPostData(content);
            }
            tBlockchainEventRecord.setEventCode("2002");
            tBlockchainEventRecord.setDescription("上传报关随附单据");
            tBlockchainEventRecord.setBlockchainId(entity.getId());
            tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
            tBlockchainEventRecord.setCreateDate(new Date());
            tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
//                }


            return num;
        } else
            return 0;
    }

    @Override
    public List<TCommonAttachments> selectByBusinessUuid(String businessUuid) {
        List<TCommonAttachments> list=tCommonAttachmentsMapper.selectByBusinessUuid(businessUuid);
        if(list!=null &&list.size()>0){
            for (TCommonAttachments item:list
                 ) {
//                item.setUpdateDate(null);
//                item.setCreateDate(null);
                item.setEndpoint(endpoint);
            }
        }
        return list;
    }

    @Override
    public List<TCommonAttachments> selectByParameter(TCommonAttachments attachments){
        return tCommonAttachmentsMapper.selectByParameter(attachments);
    }


    /**
     * 该接口提取公共部分，
     * 具体业务数据封装需要放在控制层，
     * 控制层保证数据的完整性
     * @param attachments
     * @param tBlockchainEventRecord
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TCommonAttachments insert(TCommonAttachments attachments , TBlockchainEventRecord tBlockchainEventRecord)throws Exception{
        // 数据入库
        int num = tCommonAttachmentsMapper.insert(attachments);

        if(num < 1){
            throw new BizException("附件上传失败");
        }

        // 上链数据
        BlockChainEntity entity = blockChainService.insertToBlockChain(tBlockchainEventRecord.getPostData());
        BizException bizException = new BizException();
        if (null == entity || !entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setBusiId(attachments.getId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
        return attachments;
    }

    @Override
    public int updateByPrimaryKeySelective(TCommonAttachments attachments) {
        return tCommonAttachmentsMapper.updateByPrimaryKeySelective(attachments);
    }

    @Override
    public int deleteByPrimaryKey(FileInfoVO fileInfoVO, String event_code) throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("busi_id",fileInfoVO.getId());
        map.put("event_code",event_code);
        int res = tCommonAttachmentsMapper.deleteByPrimaryKey(fileInfoVO.getId());
        // 上链数据有问题
        //查询本地上链事件记录
         Map<String,Object> mapres= tBlockchainEventRecordMapper.queryDeleteInfo(map);
        TBlockchainEventRecord tBlockchainEventRecord = new TBlockchainEventRecord();
			//上链数据 封装POST_DATA
        tBlockchainEventRecord.setDealNo("" + mapres.get("dealNo"));
        tBlockchainEventRecord.setBlockchainId("" + mapres.get("blockchain_id"));
        tBlockchainEventRecord.setBlockchainTxid("" + mapres.get("blockchain_txid"));
        tBlockchainEventRecord.setBusiId(mapres.get("busi_id")!=null?Long.valueOf(""+mapres.get("busi_id")):null);
//        tBlockchainEventRecord.setId("" + mapres.get("blockchainTxid"));
        tBlockchainEventRecord.setCreateUser(fileInfoVO.getCreateUser());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E1001.getCode());
        tBlockchainEventRecord.setPostData(""+ mapres.get("post_data"));
        tBlockchainEventRecord.setDescription("商检删除附件");
        BlockChainEntity entity = blockChainService.insertToBlockChain(tBlockchainEventRecord.getPostData());
        BizException bizException = new BizException();
        if (null == entity || !entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);

        return res;

    }

    @Override
    public TCommonAttachments selectByPrimaryKey(String id) throws Exception {
        return tCommonAttachmentsMapper.selectByPrimaryKey(Long.parseLong(id));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData deleteSofOrBl(TCommonAttachments attachments) throws Exception{
        ResultData resultData = new ResultData();

        WebApplicationContext wac  = org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        TCommonAttachmentsService attachmentService = (TCommonAttachmentsService) wac.getBean(TCommonAttachmentsService.class);

        if(attachments == null || attachments.getId() == null){
            return new ResultData(com.sinochem.it.b2b.common.result.Result.ERROR , "请选择删除的附件");
        }

        //附件信息
        attachments = attachmentService.selectByPrimaryKey(String.valueOf(attachments.getId()));
        //删除附件
        attachments.setAliveFlag(Constant.DELE_FLAG);
        int num = attachmentService.updateByPrimaryKeySelective(attachments);
        if(num < 1){
            resultData.setStatus(Result.ERROR);
            resultData.setMessage(Constant.EXCEPTION_MESSAGE);
            return resultData;
        }
        //更新单证信息
        documentService.udateDocumentUrl(attachments);
        //如果没有附件则更新任务状态
        appointTaskService.updateStatusIfFileNotExist(attachments);

        blockChain(attachments);
        return resultData;
    }


    private void blockChain(TCommonAttachments attachments) throws Exception{
        //上链
        TBlockchainEventRecord record = tBlockchainEventRecordService.findByBusiId(attachments.getId());
        record.setId(null);
        record.setCreateUser(attachments.getCreateUser());
        record.setEventCode(BlockchainEventEnums.E3005.getCode());
        record.setDescription(BlockchainEventEnums.E3005.getZhName());
        // 上链数据
        BlockChainEntity entity = blockChainService.insertToBlockChain(record.getPostData());
        BizException bizException = new BizException();
        if (null == entity || !entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }
        record.setBlockchainId(entity.getId());
        record.setBlockchainTxid(entity.getTxId());
        record.setBusiId(attachments.getId());
        record.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(record);
    }

}
