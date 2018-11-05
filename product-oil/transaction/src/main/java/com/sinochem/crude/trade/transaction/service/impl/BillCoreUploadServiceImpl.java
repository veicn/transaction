package com.sinochem.crude.trade.transaction.service.impl;

import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.model.BlockChainFile;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.BillCoreUploadFileMapper;
import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.service.BillCoreUploadService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillCoreUploadServiceImpl implements BillCoreUploadService {
	@Autowired
	private BillCoreUploadFileMapper billCoreUploadFileMapper;

    @Autowired
    private ContractSheetService contractSheetService; //订单接口

    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;

    @Value("${blockchain_gateway}")
    private String url;


    public BillCoreUploadFileMapper getMapper(){
		return billCoreUploadFileMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
    @Transactional(rollbackFor = Exception.class)
	public int insertRecord(BillCoreUpload rdocumentfile,CurrentUser currentUser) throws BizException {
	     int res= billCoreUploadFileMapper.insertRecord(rdocumentfile);
         if(rdocumentfile.getFileTypeCode().equals("13")||rdocumentfile.getFileTypeCode().equals("14")){

            this.saveBlockChain(rdocumentfile,currentUser);
         }

		 return res;
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long documentFileId) throws BizException {
		if (documentFileId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return billCoreUploadFileMapper.deleteById(documentFileId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(BillCoreUpload record){
    	return billCoreUploadFileMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(BillCoreUpload rDocumentFile) throws BizException{
		if ( rDocumentFile.getDocumentFileId() == null  ) {
			throw new BizException("documentFileId 为空，不能修改 ");
		}
		return billCoreUploadFileMapper.updateRecordById(rDocumentFile);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(BillCoreUpload rDocumentFile) throws BizException{
		if ( rDocumentFile.getDocumentFileUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return billCoreUploadFileMapper.updateRecordByUuid(rDocumentFile);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return billCoreUploadFileMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(BillCoreUpload rDocumentFile){
		return billCoreUploadFileMapper.updateRecords(rDocumentFile.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public BillCoreUpload findByPrimaryKey(Long documentFileId){
		return  billCoreUploadFileMapper.findByPrimaryKey(documentFileId);
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public BillCoreUpload findByUuid(String uuid){
		return  billCoreUploadFileMapper.findByUuid(uuid);
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<BillCoreUpload> queryByEntitys(BillCoreUpload rDocumentFile){
		return  billCoreUploadFileMapper.queryByEntitys(rDocumentFile);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return billCoreUploadFileMapper.queryRecords(map);
	}
	

	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return billCoreUploadFileMapper.countRecords(map);
	}

    //**************************以下方法为开发者补充*********************************/

    @Override
    public List<BillCoreUpload> findbyOrderIdList( Long id) throws BizException {

        List<BillCoreUpload> billList= billCoreUploadFileMapper.queryByOrderIdList(id);

        return billList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByUUid(String documentFileUuid, CurrentUser currentUser) throws BizException  {

        BillCoreUpload rDocumentFile =billCoreUploadFileMapper.findByUuid(documentFileUuid) ;
        long busiId = rDocumentFile.getDocumentFileId();
        TBlockchainEventRecord tBlockchainEventRecord = tBlockchainEventRecordService.findByBusiId(busiId);
        if(rDocumentFile.getFileTypeCode().equals("13")) {
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4003.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4003.getZhName());

        }else  if(rDocumentFile.getFileTypeCode().equals("14")) {
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4001.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4001.getZhName());
        }

        this.deleteBlockChain( tBlockchainEventRecord,currentUser);

        Integer res =  billCoreUploadFileMapper.deleteByUUid(documentFileUuid);












        return res;
    }

	@Override
	public Integer deleteByOrderid(Long orderid) {
		return billCoreUploadFileMapper.deleteByOrderid(orderid);
	}


    public int saveBlockChain(BillCoreUpload rDocumentFile, CurrentUser currentUser) throws BizException {
        BizException bizException=new BizException();
        String fileName = rDocumentFile.getFileName();
        BlockChainFile blockChainFile=new BlockChainFile();
        blockChainFile.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
        blockChainFile.setFileSummary(rDocumentFile.getFileSHA());
        blockChainFile.setCreateTime( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        blockChainFile.setDataCopyright("SGS");
        blockChainFile.setFileName(rDocumentFile.getFileName());
        blockChainFile.setFilePath(rDocumentFile.getFilePath());
        blockChainFile.setFileSize(rDocumentFile.getFileSize());
        if(rDocumentFile.getFileTypeCode().equals("13")) {
            blockChainFile.setEventCode(BlockchainEventEnums.E4004.getCode());
        }else  if(rDocumentFile.getFileTypeCode().equals("14")) {
            blockChainFile.setEventCode(BlockchainEventEnums.E4004.getZhName());
        }
        Gson gson=new Gson();
        String cnt = gson.toJson(blockChainFile);
        //url="123.123.123.123";
        BlockChainEntity entity= BlockChainUtil.insertToBlockChain(cnt,url);
        if (null==entity||null==entity.getCode()||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw bizException;
        }
        ContractSheet contractSheet = contractSheetService.getContractSheetById(currentUser, rDocumentFile.getOrderId());

        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setDealNo(contractSheet.getSerialNumber());
        tBlockchainEventRecord.setBusiId(rDocumentFile.getDocumentFileId());
        tBlockchainEventRecord.setPostData(cnt);
        if(rDocumentFile.getFileTypeCode().equals("13")) {
            //上传外贸合同正本
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4004.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4004.getZhName());
        }else  if(rDocumentFile.getFileTypeCode().equals("14")) {
            //上传出口代理合同正本
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4002.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4002.getZhName());
        }

        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecord.setCreateUser(currentUser.getEpMemberId());

        return tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);


    }




    public int deleteBlockChain(TBlockchainEventRecord tBlockchainEventRecord, CurrentUser currentUser) throws BizException {
        BizException bizException=new BizException();
        String cnt = tBlockchainEventRecord.getPostData();
        //url="123.123.123.123";
        BlockChainEntity entity= BlockChainUtil.insertToBlockChain(cnt,url);
        if (null==entity||null==entity.getCode()||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw bizException;
        }
        tBlockchainEventRecord.setId(null);
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecord.setCreateUser(currentUser.getEpMemberId());

        return tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);


    }
}
