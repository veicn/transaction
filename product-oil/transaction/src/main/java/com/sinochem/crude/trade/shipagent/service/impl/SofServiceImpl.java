package com.sinochem.crude.trade.shipagent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Constant;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.blockchain.dao.*;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.model.BlockChainFile;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.model.vo.TShipagentSofVo;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.crude.trade.transaction.utils.DateUtil;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.tools.ant.util.DateUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/7
 */
@Service("sofService")
public class SofServiceImpl implements SofService{

	@Autowired
	private TShipagentSofMapper tShipagentSofMapper;

	@Autowired
	private TShipagentSofDetailMapper tShipagentSofDetailMapper;

	@Autowired
	private TShipagentDocumentMapper tShipagentDocumentMapper;

	@Autowired
	private TShipagentAppointMapper tShipagentAppointMapper;

	@Autowired
	private TBlockchainEventRecordMapper tBlockchainEventRecordMapper;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private TShipagentBillladingMapper tShipagentBillladingMapper;

	@Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private AppointTaskService appointTaskService;

	//TODO:上链 URL暂时放在server.xml配置文件中
	@Value("${blockchain_gateway}")
	private String url;
	/**
	 * 根据SOF主键查询SOF详情，
	 * 包含
	 *  <ul>
	 *  <li>T_SOF,
	 *  <li>T_SOF_DETAILsk
	 *  </ul>
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@Override
	public TShipagentSofVo getSofAndDetail(long id) throws BizException{
		// SOF主体信息
		TShipagentSof tShipagentSof = tShipagentSofMapper.selectByPrimaryKey(id);
		if(tShipagentSof == null || Constants.ALIVE_FLAG_NO.equals(tShipagentSof.getAliveFlag().trim())){
			throw new BizException("SOF信息不存在");
		}

		// 查询单证信息
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setSofId(tShipagentSof.getId());
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);
		if(documentList!=null && documentList.size() > 0){
			tShipagentSof.setTaskId(documentList.get(0).getAppointId());
		}

		// SOF_DETAIL 详情
		List<TShipagentSofDetail> sofDetailList =  tShipagentSofDetailMapper.selectBySof(id);
		tShipagentSof.setDetailList(sofDetailList==null?new ArrayList():sofDetailList);

		// 当前企业信息
		EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(tShipagentSof.getEnterpriseId());
		TShipagentSofVo vo = new TShipagentSofVo();
		try{
			BeanUtils.copyProperties(vo , tShipagentSof);
			vo.setFullName(enterpriseVo.getFullName());
			vo.setEnglishName(enterpriseVo.getEnglishName());
			vo.setLogo(enterpriseVo.getEpLogo());
		}catch (Exception e){
			e.printStackTrace();
		}

		return vo;
	}

	/**
	 * 根据主键查询SOF信息
	 * @param id
	 * @return
	 * @throws BizException
	 */
	@Override
	public TShipagentSof get(long id) throws BizException{
		TShipagentSof sof = tShipagentSofMapper.selectByPrimaryKey(id);
		if(sof == null){
			return null;
		}
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setSofId(id);
		queryDocument.setAliveFlag(Constants.ALIVE_FLAG_YES);
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);
		if(documentList != null && documentList.size() > 0){
			sof.setTaskId(documentList.get(0).getAppointId());
		}
		return  sof;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ResultData delete(Long id)throws BizException{
		ResultData resultDatas = new ResultData();

		TShipagentSof sof = new TShipagentSof();
		sof.setId(id);
		sof.setAliveFlag(Constants.ALIVE_FLAG_NO);
		//1、删除SOF
		int result = tShipagentSofMapper.updateByPrimaryKeySelective(sof);

		//2、删除详情


		//3、删除附件


		if(result < 1){
			resultDatas.setStatus(Result.ERROR);
			resultDatas.setMessage("删除失败");
		}
		return resultDatas;
	}



	/**
	 * 暂存SOF
	 * @param sof
	 * @return
	 */
	@Override
	public ResultData<TShipagentSof> save(TShipagentSof sof)throws BizException{
		ResultData<TShipagentSof> resultDatas = new ResultData<TShipagentSof>();

		//任务信息
		TShipagentAppoint appoint = tShipagentAppointMapper.selectByPrimaryKey(sof.getTaskId());

		if(appoint == null){
			resultDatas.setMessage("任务信息不存在");
			resultDatas.setStatus(Result.ERROR);
			return resultDatas;
		}

		//创建SOF信息
		sof.setAliveFlag(Constants.ALIVE_FLAG_YES);
		sof.setStatus(TShipagentSof.STATUS_SAVED);
		return createSof(sof ,appoint);
	}

	/**
	 * 提交SOF
	 * @param sof
	 * @return
	 */
	@Override
	public ResultData<TShipagentSof> commit(TShipagentSof sof)throws Exception{
		ResultData<TShipagentSof> resultDatas = new ResultData<>();
		BizException bizException = new BizException();
		// 任务信息
		TShipagentAppoint appoint = tShipagentAppointMapper.selectByPrimaryKey(sof.getTaskId());
		if(appoint == null){
			resultDatas.setMessage("任务信息不存在");
			resultDatas.setStatus(Result.ERROR);
			return resultDatas;
		}

		// sof上链是否为更新
		String description = BlockchainEventEnums.E3008.getZhName();//"提交装港事实记录";
		String eventCode = BlockchainEventEnums.E3008.getCode();//"3008";
		String originalStatus = TShipagentSof.STATUS_SAVED;
		if(sof.getId() != null){
			TShipagentSof originalSof = tShipagentSofMapper.selectByPrimaryKey(sof.getId());
			if(originalSof != null && TShipagentSof.STATUS_COMMITED.equals(originalSof.getStatus())){
				description = BlockchainEventEnums.E3007.getZhName();//"更新装港事实记录";
				eventCode = BlockchainEventEnums.E3007.getCode();//"3007";
				originalStatus = originalSof.getStatus();
			}
		}


		// 创建SOF
		sof.setAliveFlag(Constants.ALIVE_FLAG_YES);
		sof.setStatus(TShipagentSof.STATUS_COMMITED);
		resultDatas = createSof(sof ,appoint);
		TShipagentSof insertSof = resultDatas.getData();
		if(insertSof == null){
			bizException.setCode(ExceptionEnum.RUNTIME_ERROR.getCode());
			throw bizException;
		}

		// 封装上链数据
		String content = wrapperBlocakchainContent(insertSof , appoint);
		BlockChainEntity chainEntity = BlockChainUtil.insertToBlockChain(content, url);
		if (null==chainEntity||!chainEntity.getCode().equals("1")) {
			resultDatas = new ResultData<>(Result.ERROR , "数据上链失败");
			// 更新原有状态
			TShipagentSof updateSof = new TShipagentSof();
			updateSof.setStatus(originalStatus);
			updateSof.setId(sof.getId());
			tShipagentSofMapper.updateByPrimaryKey(updateSof);
			return resultDatas;
		}

		// 上链记录
		TBlockchainEventRecord record = new TBlockchainEventRecord();
		record.setBlockchainTxid(chainEntity.getTxId());
		record.setBlockchainId(chainEntity.getId());
		record.setBusiId(insertSof.getId());
		record.setCreateDate(new Date());
		record.setCreateUser(sof.getCreateUser());
		record.setDealNo(appoint.getDealNo());
		record.setDescription(description);
		record.setPostData(content);
		record.setEventCode(eventCode);
		tBlockchainEventRecordMapper.insertSelective(record);

		// 更改任务状态
		updateTaskStatus(appoint);

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
	public TShipagentSof selectByUuid(String uuid){
		return tShipagentSofMapper.selectByUuid(uuid);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public ResultData uploadFile(FileInfoVO fileInfoVO) throws Exception{
		ResultData responseData = new ResultData();

		WebApplicationContext wac  = ContextLoader.getCurrentWebApplicationContext();
		SofService sofService = (SofService) wac.getBean(SofService.class);
		TShipagentSof sof = sofService.get(fileInfoVO.getId());
		//如果sof不存在则创建
		if(sof == null || fileInfoVO.getId() == null){
			sof = new TShipagentSof();
			sof.setCreateUser(fileInfoVO.getCreateUser());
			sof.setCreateDate(new Date());
			sof.setUuid(KeyGenUtils.newUuid());
			sof.setUpdateDate(new Date());
			sof.setUpdateUser(fileInfoVO.getCreateUser());
			sof.setTaskId(fileInfoVO.getTaskId());
			ResultData<TShipagentSof> result = sofService.save(sof);
			if(com.sinochem.it.b2b.common.result.Result.SUCCESS != result.getStatus()){
				return result;
			}
			sof = result.getData();
		}
		//封装附件
		TCommonAttachments attachments = new TCommonAttachments();
		attachments.setChannel(TCommonAttachments.CHANNEL_SHIPAGENT);
		attachments.setAliveFlag(Constants.ALIVE_FLAG_YES);
		attachments.setCreateDate(new Date());
		attachments.setFileType(TCommonAttachments.FILETYPE_SOF);
		attachments.setPath(fileInfoVO.getPath());
		attachments.setName(fileInfoVO.getOriginalName());
		attachments.setCreateDate(new Date());
		attachments.setBusinessUuid(sof.getUuid());
		attachments.setCreateUser(fileInfoVO.getCreateUser());
		attachments.setUpdateDate(new Date());
		attachments.setUpdateUser(fileInfoVO.getCreateUser());

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

		// 单证信息
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAliveFlag(com.sinochem.crude.trade.inspector.constant.Constant.SAVE_FLAG);
		queryDocument.setSofId(sof.getId());
		List<TShipagentDocument> documentList = documentService.get(queryDocument);
		if(documentList == null || documentList.size() < 1){
			return new ResultData(Result.ERROR ,"单证不存在");
		}
		TShipagentDocument document = documentList.get(0);

		TBlockchainEventRecord record = new TBlockchainEventRecord();
		record.setCreateUser(fileInfoVO.getCreateUser());
		record.setBusiId(sof.getId());
		record.setDealNo(document.getDealNo());
		record.setEventCode(BlockchainEventEnums.E3006.getCode());
		record.setPostData(JSONObject.toJSONString(blockChainFile));
		record.setDescription(BlockchainEventEnums.E3006.getZhName());

		attachments = tCommonAttachmentsService.insert(attachments , record);

		//更新单证URL
		documentService.udateDocumentUrl(attachments);

		//更改状态
		TShipagentAppoint appoint = appointTaskService.selectByPrimaryKey(sof.getTaskId());
		sofService.updateTaskStatus(appoint);
		responseData.setData(attachments);
		return null;
	}


	/**
	 * 船名 航次 装货名称 吨数 日期 港口 联系电话 日期（sheet） 星期（sheet） 开始时间（sheet）结束时间（sheet）
	 说明（sheet） 备注  船舶登录总吨 船舶净吨 船长  代理公司

	 * @param insertSof
	 * @return
	 */
	private String wrapperBlocakchainContent(TShipagentSof insertSof , TShipagentAppoint appoint)throws ParseException{
		Map<String, Object> sofMap = new HashMap<>();
		sofMap.put("vessel", insertSof.getVessel());
		sofMap.put("voyage", insertSof.getVoyage());
		sofMap.put("goodsName", insertSof.getGoodsName());
		sofMap.put("metricTon", insertSof.getMetricTon());
		sofMap.put("sofDate", DateUtil.dateFormat(insertSof.getSofDate()));
		sofMap.put("port", insertSof.getPort());
		sofMap.put("contactNumber", insertSof.getContactNumber());
		sofMap.put("remark", insertSof.getRemark());
		sofMap.put("shipGrossWeight", insertSof.getShipGrossWeight());
		sofMap.put("shipNetWeight", insertSof.getShipNetWeight());
		sofMap.put("enterpriseName" , appoint.getEnterpriseName());

		List<Map<String , Object>> detailList = new ArrayList<>(10);
		for(TShipagentSofDetail detail : insertSof.getDetailList()){
			Map<String, Object> detailMap = new HashMap<>();
			detailMap.put("detailDate", detail.getDetailDate()==null?null:DateUtil.dateFormat(detail.getDetailDate()));
			detailMap.put("week", DateUtil.getWeekOfDate(detail.getDetailDate()));
			detailMap.put("beginTime", detail.getBeginTime());
			detailMap.put("endTime", detail.getEndTime());
			detailMap.put("instruction", detail.getInstruction());
			detailList.add(detailMap);
		}
		sofMap.put("detail" , detailList);
		return JSONObject.toJSONString(sofMap);
	}


	private ResultData<TShipagentSof> createSof(TShipagentSof sof ,TShipagentAppoint appoint) throws BizException{
		ResultData resultDatas = new ResultData();
		TShipagentDocument document = null;

		// 单证信息
		TShipagentDocument queryDocument = new TShipagentDocument();
		queryDocument.setAppointId(sof.getTaskId());
		List<TShipagentDocument> documentList = tShipagentDocumentMapper.selectByParameter(queryDocument);


		int num = 0;
		if(sof.getId() != null){
			 num = tShipagentSofMapper.updateByPrimaryKeySelective(sof);
		}else{
			num = tShipagentSofMapper.insertSelective(sof);
		}

		if(num < 1){
			BizException bizException = new BizException();
			bizException.setCode(ExceptionEnum.FAIL_SAVE.getCode());
			throw bizException;
		}


		if(documentList == null || documentList.size() < 1){
			document = new TShipagentDocument();
			wrapperDocument(document , sof , appoint);
			tShipagentDocumentMapper.insertSelective(document);
		}else {
			document = documentList.get(0);
			wrapperDocument(document , sof , appoint);
			tShipagentDocumentMapper.updateByPrimaryKeySelective(document);
		}

		// sof明细信息(更新列表:updateList   插入列表:insertList)
		List<TShipagentSofDetail> detailList = sof.getDetailList();
		List<TShipagentSofDetail> updateList = new ArrayList<>();
		List<TShipagentSofDetail> insertList = new ArrayList<>();

		if(detailList != null && detailList.size() > 0){
			for (TShipagentSofDetail detail : detailList){
				// 分批整理更新插入列表
				batchArch(updateList , insertList , detail , sof);
			}
			// 更新
			if(insertList.size() > 0){
				tShipagentSofDetailMapper.bachInsert(insertList);
			}

			// 新增
			if(updateList.size() > 0){
				tShipagentSofDetailMapper.bachUpdate(updateList);
			}
		}
		resultDatas.setData(sof);
		return resultDatas;
	}



	/**
	 * 封装提单信息
	 * @param document
	 * @param sof
	 * @param appoint
	 * @return
	 */
	private void wrapperDocument(TShipagentDocument document,TShipagentSof sof  ,TShipagentAppoint appoint){
		//当前企业信息
		EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(sof.getEnterpriseId());
		//提单信息
		document.setAppointId(sof.getTaskId());
		document.setAppointEnterpriseId(appoint.getAppointEnterpriseId());
		document.setAppointEnterpriseName(appoint.getAppointEnterpriseName());
		document.setEnterpriseId(sof.getEnterpriseId());
		document.setEnterpriseName(enterpriseVo.getFullName());
		document.setAliveFlag(Constants.ALIVE_FLAG_YES);
		document.setCreateDate(new Date());
		document.setUpdateDate(new Date());
		document.setUpdateUser(sof.getUpdateUser());
		document.setCreateUser(sof.getCreateUser());
		document.setDealNo(appoint.getDealNo());
		document.setSofId(sof.getId());
	}

	/**
	 * 将页面的sof详情分批整理，准备插入\更新
	 * @param updateList  更新列表
	 * @param insertList  插入列表
	 * @param detail  详情
	 * @param sof
	 */
	private void batchArch(List<TShipagentSofDetail> updateList,List<TShipagentSofDetail> insertList,TShipagentSofDetail detail,TShipagentSof sof){
		if(detail.getId() != null && detail.getId().longValue() > 0){
			detail.setUpdateDate(new Date());
			detail.setUpdateUser(sof.getUpdateUser());
			updateList.add(detail);
		}else {
			detail.setAliveFlag(Constants.ALIVE_FLAG_YES);
			detail.setUpdateUser(sof.getUpdateUser());
			detail.setUpdateDate(new Date());
			detail.setCreateDate(new Date());
			detail.setCreateUser(sof.getCreateUser());
			detail.setSofId(sof.getId());
			insertList.add(detail);
		}
	}

}
