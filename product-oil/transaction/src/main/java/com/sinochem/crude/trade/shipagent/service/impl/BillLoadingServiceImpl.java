package com.sinochem.crude.trade.shipagent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.blockchain.dao.*;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.model.BlockChainFile;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.inspector.constant.Constant;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.crude.trade.transaction.utils.DateUtil;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/12
 */
@Service("billLoadingService")
public class BillLoadingServiceImpl implements BillLoadingService{

	@Autowired
	private TShipagentBillladingMapper tShipagentBillladingMapper;

	@Autowired
	private TShipagentDocumentMapper tShipagentDocumentMapper;

	@Autowired
	private TShipagentAppointMapper tShipagentAppointMapper;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private TBlockchainEventRecordMapper tBlockchainEventRecordMapper;

	@Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;

	@Autowired
	private TShipagentSofMapper tShipagentSofMapper;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private AppointTaskService appointTaskService;

	//TODO:上链 URL暂时放在server.xml配置文件中
	@Value("${blockchain_gateway}")
	private String url;

	/**
	 * 根据主键查询提单信息
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@Override
	public TShipagentBillLoading get(Long id) throws BizException{

		TShipagentBillLoading billLoading = tShipagentBillladingMapper.selectByPrimaryKey(id);

		if(billLoading == null){
			return null;
		}
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setBlId(id);
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);
		if(documentList != null && documentList.size() > 0 && billLoading != null){
			billLoading.setTaskId(documentList.get(0).getAppointId());
		}else {
			throw new BizException("数据异常");
		}
		return billLoading ;
	}

	/**
	 * 创建BL
	 * @param billlading
	 * @return
	 */
	@Override
	public ResultData create(TShipagentBillLoading billlading){
		ResultData resultDatas = new ResultData();
		int insertNum = tShipagentBillladingMapper.insertSelective(billlading);
		resultDatas.setData(insertNum);
		return resultDatas;
	}


	/**
	 * 暂存BL
	 * @param billlading
	 * @return
	 */
	@Override
	public ResultData<TShipagentBillLoading> save(TShipagentBillLoading billLoading)throws Exception{
		ResultData<TShipagentBillLoading> resultDatas = new ResultData();
		//任务信息
		TShipagentAppoint appoint = tShipagentAppointMapper.selectByPrimaryKey(billLoading.getTaskId());
		if(appoint == null){
			resultDatas.setMessage("任务信息不存在");
			resultDatas.setStatus(Result.ERROR);
			return resultDatas;
		}
		billLoading.setAliveFlag(Constants.ALIVE_FLAG_YES);
		billLoading.setStatus(TShipagentBillLoading.STATUS_SAVED);
		//插入数据
		resultDatas = createOrUpdate(billLoading , appoint);
		return resultDatas;
	}

	/**
	 * 提交BL
	 * @param billLoading
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultData commit(TShipagentBillLoading billLoading)throws Exception{
		ResultData<TShipagentBillLoading> resultDatas = new ResultData();
		BizException bizException = new BizException();

		//任务信息
		TShipagentAppoint appoint = tShipagentAppointMapper.selectByPrimaryKey(billLoading.getTaskId());
		if(appoint == null){
			resultDatas.setMessage("任务信息不存在");
			resultDatas.setStatus(Result.ERROR);
			return resultDatas;
		}

		String description = BlockchainEventEnums.E3004.getZhName();
		String eventCode = BlockchainEventEnums.E3004.getCode();//"3004";
		String originalStatus = TShipagentBillLoading.STATUS_SAVED;
		if(billLoading.getId() != null){
			TShipagentBillLoading originalBillLoading = tShipagentBillladingMapper.selectByPrimaryKey(billLoading.getId());
			if(originalBillLoading != null && TShipagentBillLoading.STATUS_COMMITED.equals(originalBillLoading.getStatus())){
				description = BlockchainEventEnums.E3003.getZhName();//"更新提单信息";
				eventCode = BlockchainEventEnums.E3003.getCode();//"3003";
				originalStatus = originalBillLoading.getStatus();
			}
		}

		//插入数据
		billLoading.setStatus(TShipagentBillLoading.STATUS_COMMITED);
		billLoading.setAliveFlag(Constants.ALIVE_FLAG_YES);
		resultDatas = createOrUpdate(billLoading , appoint);
		TShipagentBillLoading insetBillLoading = resultDatas.getData();
		//数据上链
		BlockChainEntity chainEntity = BlockChainUtil.insertToBlockChain(JSONObject.toJSONString(insetBillLoading), url);
		if (null==chainEntity||!"1".equals(chainEntity.getCode())) {
			resultDatas = new ResultData<>(Result.ERROR , "数据上链失败");
			//更新原有状态
			TShipagentBillLoading updateBillLoading = new TShipagentBillLoading();
			updateBillLoading.setStatus(originalStatus);
			updateBillLoading.setId(billLoading.getId());
			tShipagentBillladingMapper.updateByPrimaryKey(updateBillLoading);

			return resultDatas;
		}

		//上链记录
		TBlockchainEventRecord record = new TBlockchainEventRecord();
		record.setBlockchainTxid(chainEntity.getTxId());
		record.setBlockchainId(chainEntity.getId());
		record.setBusiId(insetBillLoading.getId());
		record.setCreateDate(new Date());
		record.setCreateUser(billLoading.getCreateUser());
		record.setDealNo(appoint.getDealNo());
		record.setDescription(description);
		record.setPostData(JSONObject.toJSONString(insetBillLoading));
		record.setEventCode(eventCode);
		tBlockchainEventRecordMapper.insertSelective(record);

		return resultDatas;
	}

	public ResultData<TShipagentBillLoading> createOrUpdate(TShipagentBillLoading billLoading , TShipagentAppoint appoint)throws Exception{
		ResultData<TShipagentBillLoading> resultDatas = new ResultData();
		TShipagentDocument document = null;

		//单证信息
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAppointId(billLoading.getTaskId());
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);

		int num = 0 ;
		final String  message;
		if(billLoading.getId() == null ){
			num = tShipagentBillladingMapper.insertSelective(billLoading);
		}else {
			num = tShipagentBillladingMapper.updateByPrimaryKeySelective(billLoading);
		}

		if(num < 1){
			BizException bizException = new BizException();
			bizException.setCode(ExceptionEnum.FAIL_SAVE.getCode());
			throw bizException;
		}
		if(documentList == null || documentList.size() < 1){
			document = new TShipagentDocument();
			wrapperDocument(document , billLoading , appoint);
			tShipagentDocumentMapper.insertSelective(document);
		}else {
			document = documentList.get(0);
			wrapperDocument(document , billLoading , appoint);
			tShipagentDocumentMapper.updateByPrimaryKeySelective(document);
		}

		//更新状态
		updateTaskStatus(appoint);

		resultDatas.setData(billLoading);
		return resultDatas;
	}

	@Override
	public void updateTaskStatus(TShipagentAppoint appoint) {
		Long appointId = appoint.getId();
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAppointId(appointId);
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);

		if(documentList!= null && documentList.size() > 0){
			TShipagentDocument document = documentList.get(0);

			boolean sofExist = false;
			boolean blExist = false;

			//检测sof是否完成
			TCommonAttachments attachments = new TCommonAttachments();
			attachments.setAliveFlag(Constants.ALIVE_FLAG_YES);
			attachments.setChannel(TCommonAttachments.CHANNEL_SHIPAGENT);
			if(document.getSofId() != null){
				TShipagentSof sof = tShipagentSofMapper.selectByPrimaryKey(document.getSofId());
				//检测sof附件是否完成
				if(sof != null){
					attachments.setBusinessUuid(sof.getUuid());
					attachments.setFileType(TCommonAttachments.FILETYPE_SOF);
					List<TCommonAttachments> attachmentsList = tCommonAttachmentsService.selectByParameter(attachments);
					if(attachmentsList != null && attachmentsList.size() > 0 && TShipagentSof.STATUS_COMMITED.equals(sof.getStatus())){
						sofExist = true;
					}
				}
			}

			//检测BL是否完成
			if(document.getBlId() != null ){
				TShipagentBillLoading bl = tShipagentBillladingMapper.selectByPrimaryKey(document.getBlId());
				if(bl != null){
					attachments.setBusinessUuid(bl.getUuid());
					attachments.setFileType(TCommonAttachments.FILETYPE_BL);
					List<TCommonAttachments> attachmentsList = tCommonAttachmentsService.selectByParameter(attachments);
					if(attachmentsList != null && attachmentsList.size() > 0 && TShipagentBillLoading.STATUS_COMMITED.equals(bl.getStatus())){
						blExist = true;
					}
				}
			}

			//更新任务表的状态
			if(sofExist  && blExist ){
				TShipagentAppoint updateAppoint = new TShipagentAppoint();
				updateAppoint.setId(appointId);
				updateAppoint.setStatus(TShipagentAppoint.STATUS_COMPLETED);
				tShipagentAppointMapper.updateByPrimaryKeySelective(updateAppoint);
			}
		}
	}

	@Override
	public TShipagentBillLoading selectByUuid(String uuid) {
		return tShipagentBillladingMapper.selectByUuid(uuid);
	}

	@Override
	public ResultData<TCommonAttachments> uploadFile(FileInfoVO fileInfoVO) throws Exception{
		ResultData<TCommonAttachments> responseData = new ResultData<>();

		WebApplicationContext wac  = ContextLoader.getCurrentWebApplicationContext();
		BillLoadingService billLoadingService = (BillLoadingService) wac.getBean(BillLoadingService.class);

		TShipagentBillLoading billLoading = billLoadingService.get(fileInfoVO.getId());
		//如果Bl不存在则创建
		if( billLoading == null || fileInfoVO.getId() == null){
			billLoading = new TShipagentBillLoading();
			billLoading.setCreateUser(fileInfoVO.getCreateUser());
			billLoading.setCreateDate(new Date());
			billLoading.setUuid(KeyGenUtils.newUuid());
			billLoading.setUpdateDate(new Date());
			billLoading.setUpdateUser(fileInfoVO.getCreateUser());
			billLoading.setTaskId(fileInfoVO.getTaskId());
			ResultData<TShipagentBillLoading> resultBl = billLoadingService.save(billLoading);
			if(Result.SUCCESS != resultBl.getStatus()){
				responseData = new ResultData<>(resultBl.getStatus() , resultBl.getMessage());
				return responseData;
			}
			billLoading = resultBl.getData();
		}

		//上链数据 封装POST_DATA
		String fileName = fileInfoVO.getOriginalName();
		BlockChainFile blockChainFile=new BlockChainFile();
		blockChainFile.setFileType(fileInfoVO.getSuffix());
		blockChainFile.setFileSummary(fileInfoVO.getFileSHA());
		blockChainFile.setCreateTime( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		blockChainFile.setDataCopyright("SGS");
		blockChainFile.setFileName(fileName.substring(0 , fileName.lastIndexOf(".")).toUpperCase());
		blockChainFile.setFilePath(fileInfoVO.getPath());
		blockChainFile.setFileSize(fileInfoVO.getSize());
		blockChainFile.setEventCode(BlockchainEventEnums.E3006.getCode());

		//封装附件
		TCommonAttachments attachments = new TCommonAttachments();
		attachments.setChannel(TCommonAttachments.CHANNEL_SHIPAGENT);
		attachments.setAliveFlag(Constants.ALIVE_FLAG_YES);
		attachments.setCreateDate(new Date());
		attachments.setFileType(TCommonAttachments.FILETYPE_BL);
		attachments.setPath(fileInfoVO.getPath());
		attachments.setName(fileInfoVO.getOriginalName());
		attachments.setCreateDate(new Date());
		attachments.setBusinessUuid(billLoading.getUuid());
		attachments.setCreateUser(fileInfoVO.getCreateUser());
		attachments.setUpdateDate(new Date());
		attachments.setUpdateUser(fileInfoVO.getCreateUser());

		// 单证信息
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAliveFlag(Constant.SAVE_FLAG);
		queryDocument.setBlId(billLoading.getId());
		List<TShipagentDocument> documentList = documentService.get(queryDocument);
		if(documentList == null || documentList.size() < 1){
			return new ResultData(Result.ERROR , Constant.EXCEPTION_MESSAGE);
		}
		TShipagentDocument document = documentList.get(0);

		TBlockchainEventRecord record = new TBlockchainEventRecord();
		record.setCreateUser(fileInfoVO.getCreateUser());
		record.setBusiId(billLoading.getId());
		record.setDealNo(document.getDealNo());
		record.setEventCode(BlockchainEventEnums.E3006.getCode());
		record.setPostData(JSONObject.toJSONString(blockChainFile));
		record.setDescription(BlockchainEventEnums.E3006.getZhName());

		attachments = tCommonAttachmentsService.insert(attachments , record);

		//更新单证URL
		documentService.udateDocumentUrl(attachments);

		//更改状态
		TShipagentAppoint appoint = appointTaskService.selectByPrimaryKey(billLoading.getTaskId());
		billLoadingService.updateTaskStatus(appoint);

		responseData.setData(attachments);

		return null;
	}


	/**
	 * 封装提单信息
	 * @param document
	 * @param sof
	 * @param appoint
	 * @return
	 */
	private void wrapperDocument(TShipagentDocument document, TShipagentBillLoading billLoading  , TShipagentAppoint appoint){
		//当前企业信息
		EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(billLoading.getEnterpriseId());
		//提单信息
		document.setAppointId(billLoading.getTaskId());
		document.setAppointEnterpriseId(appoint.getAppointEnterpriseId());
		document.setAppointEnterpriseName(appoint.getAppointEnterpriseName());
		document.setEnterpriseId(billLoading.getEnterpriseId());
		document.setEnterpriseName(enterpriseVo.getFullName());
		document.setAliveFlag(Constants.ALIVE_FLAG_YES);
		document.setCreateDate(new Date());
		document.setUpdateDate(new Date());
		document.setUpdateUser(billLoading.getUpdateUser());
		document.setCreateUser(billLoading.getCreateUser());
		document.setDealNo(appoint.getDealNo());
		document.setBlId(billLoading.getId());
	}

}
