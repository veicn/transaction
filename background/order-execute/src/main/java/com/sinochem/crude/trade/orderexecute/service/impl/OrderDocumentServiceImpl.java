package com.sinochem.crude.trade.orderexecute.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.runtime.directive.Foreach;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.ContractStatusEnum;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.DocInfoAttaVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.DocInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentFileMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocument;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderDocumentVO;
import com.sinochem.crude.trade.orderexecute.query.DocumentQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentService;

@Service
public class OrderDocumentServiceImpl implements OrderDocumentService {
	
	@Autowired
	private OrderDocumentMapper orderDocumentMapper;
	@Autowired
	private OrderDocumentFileMapper orderDocumentFileMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderStatusService orderStatusService;
	
	public OrderDocumentMapper getMapper(){
		return orderDocumentMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderDocument orderdocument){
		 return orderDocumentMapper.insertRecord(orderdocument);
	}
	/**
	 * 合同接收信息处理
	 * @param orderContract
	 * @param contractFileList
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void receiveContractProcess(OrderDocument orderDocument, List<OrderDocumentFile> documentFileList) {
		OrderDocument orderDucumentParam = new OrderDocument();
		orderDucumentParam.setOrderId(orderDocument.getOrderId());
		orderDucumentParam.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		List<OrderDocument> queryByEntitys = orderDocumentMapper.queryByEntitys(orderDucumentParam);

		if(queryByEntitys!=null && queryByEntitys.size() > 0){
			orderDocument.setOrderDocumentId(queryByEntitys.get(0).getOrderDocumentId());
		}else{
		orderDocument.setOrderDocumentUuid(KeyGenUtils.newUuid());
		orderDocumentMapper.insertRecord(orderDocument);
		}
		// 修改时把原纪录作废
		OrderDocumentFile deleteEntity = new OrderDocumentFile();
		deleteEntity.setOrderDocumentId(orderDocument.getOrderDocumentId());			
		deleteEntity.setFileCode("T0001");
		deleteEntity.setUpdateDate(DateTimeUtils.currentDate());
			orderDocumentMapper.updateFileAliveByCode(deleteEntity);
			
		for (OrderDocumentFile documentFile : documentFileList) {
			documentFile.setOrderDocumentId(orderDocument.getOrderDocumentId());
			orderDocumentFileMapper.insertRecord(documentFile);
		}
		
	
		
		
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderDocumentId) throws BizException{
		if (orderDocumentId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderDocumentMapper.deleteById(orderDocumentId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderDocument  record){
    	return orderDocumentMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderDocument orderDocument) throws BizException{
		if ( orderDocument.getOrderDocumentId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderDocumentId 为空，不能修改","orderDocumentId");
		}
		return orderDocumentMapper.updateRecordById(orderDocument);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderDocumentMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderDocument orderDocument){
		return orderDocumentMapper.updateRecords(orderDocument.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderDocument findByPrimaryKey(Long orderDocumentId){
		return  orderDocumentMapper.findByPrimaryKey(orderDocumentId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderDocument findByUuid(String uuid){
		return  orderDocumentMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderDocument> queryByEntitys(OrderDocument orderDocument){
		return  orderDocumentMapper.queryByEntitys(orderDocument);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderDocumentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderDocumentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderDocumentMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/	
	
	/**
	 * 取得单证已上传附件代码列表
	 */
	@Override
	public List<Map<String,Object>> getDocumentFileList(DocumentQuery query) {
		//return orderDocumentMapper.getDocumentFileList(query);
		return orderDocumentMapper.queryDocFileCheckList(query);
	}
	/**
	 * 取得单证已上传附件代码列表
	 */
	@Override
	public List<Map<String, Object>> queryDocumentFileList(DocumentQuery query) {
		List<Map<String, Object>> list = orderDocumentMapper.queryDocumentFileList(query);
		//添加自定义的单证
		List<Map<String, Object>> customFileList = orderDocumentMapper.queryCustomFileList(query);
		
		for (Map<String, Object> mapC : customFileList) {
			list.add(mapC);
		}
		return list;
	}
	
	/**
	 * 取得单证类型列表
	 */
	@Override
	public List<Map<String,Object>> getDocumentType(DocumentQuery query) {
		return orderDocumentMapper.getDocumentType(query);
	}	
	
	/**
	 * 取得合同已上传附件代码列表
	 */
	@Override
	public List<Map<String,Object>> getContractFileList(String uuid) {
		List<String> fileList = orderDocumentMapper.getContractFileList(uuid);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		// 合同附件
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("contractFileName", "英文合同");
		if(fileList != null && fileList.contains("英文合同")) {
			map1.put("isUpload", 1);
		} else {
			map1.put("isUpload", 0);
		}
		list.add(map1);
		
		// 代理协议
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("contractFileName", "代理协议");
		if(fileList != null && fileList.contains("代理协议")) {
			map2.put("isUpload", 1);
		} else {
			map2.put("isUpload", 0);
		}
		list.add(map2);
		
		return list;
	}

	/**
	 * 取得单证编辑画面上部数据
	 */
	@Override
	public Map<String, Object> getMainData(DocumentQuery query) {
		if(query.getOrderDocumentId() == null) {
			return orderDocumentMapper.getOrderData(query);
		} else {
			return orderDocumentMapper.getDocumentData(query);
		}
	}

	/**
	 * 取得单证详情
	 */
	@Override
	public Map<String,Object> getDocumentDetail(DocumentQuery query) {
		return orderDocumentMapper.getDocumentDetail(query);
	}
	
	/**
	 * 新增或者更新单证信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveDocument(OrderDocumentVO vo, CurrentUser user) {
		Order order = orderMapper.findByUuid(vo.getUuid());
		
		OrderDocument entity = new OrderDocument();

		entity.setOrderId(order.getId());
		entity.setOrderNo(vo.getOrderNo());	
		entity.setContractNo(vo.getContractNo());
		entity.setShipName(vo.getShipName());
		entity.setDocumentType(vo.getDocumentType());
		entity.setBillDate(vo.getBillDate());
		entity.setRemark(vo.getRemark());
		
		entity.setUpdateDate(DateTimeUtils.currentDate());
		entity.setUpdateUser(user.getMemberId());			
		
		Map<String,Object> documentInfo = orderDocumentMapper.getDocumentInfo(entity);
		
		// 新增
		if (documentInfo == null) {
			entity.setOrderDocumentUuid(KeyGenUtils.newUuid());
			entity.setLangVer(Constants.LANG_VER);
			entity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			entity.setCreateDate(DateTimeUtils.currentDate());
			entity.setCreateUser(user.getMemberId());
			
			orderDocumentMapper.insertRecord(entity);
		} else {
			// 修改
			entity.setOrderDocumentId((Long) documentInfo.get("orderDocumentId"));
			
			orderDocumentMapper.updateDocumentInfo(entity);
		}
		
		
		
		if(vo.getFileInfoList() != null) {
			for(int i = 0; i < vo.getFileInfoList().size(); i++) {
				OrderDocumentFile fileEntity = new OrderDocumentFile();
				OrderDocumentFile info = vo.getFileInfoList().get(i);
				
				
				// 修改时把原纪录作废
				OrderDocumentFile deleteEntity = new OrderDocumentFile();
				deleteEntity.setOrderDocumentId(entity.getOrderDocumentId());			
				deleteEntity.setFileCode(info.getFileCode());
				deleteEntity.setUpdateDate(DateTimeUtils.currentDate());
				deleteEntity.setUpdateUser(user.getMemberId());
				//查询附件列表
				OrderDocumentFile docFile = new OrderDocumentFile();
				docFile.setOrderDocumentId(entity.getOrderDocumentId());
				docFile.setFileName(info.getFileName());
				List<OrderDocumentFile> docFileList = orderDocumentFileMapper.queryByEntitys(docFile);
				if(docFileList != null && docFileList.size() > 0){
					for (OrderDocumentFile orderDocumentFile : docFileList) {
						if(info.getFileName().equals(orderDocumentFile.getFileName())){
							orderDocumentMapper.updateFileAliveByCode(deleteEntity);
						}
					}
				}
				
				fileEntity.setDocumentFileUuid(KeyGenUtils.newUuid());
				fileEntity.setOrderDocumentId(entity.getOrderDocumentId());
				fileEntity.setFileName(info.getFileName());
				fileEntity.setFileCode(info.getFileCode());
				fileEntity.setOriginalName(info.getOriginalName());
				fileEntity.setFilePath(info.getFilePath());
				fileEntity.setFileFormat(info.getFileFormat());
				fileEntity.setFileSize(info.getFileSize());
				fileEntity.setUploadTime(DateTimeUtils.currentTimestamp());
				fileEntity.setUploadPerson(user.getMemberId());

				fileEntity.setLangVer(Constants.LANG_VER);
				fileEntity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				fileEntity.setCreateDate(DateTimeUtils.currentDate());
				fileEntity.setCreateUser(user.getMemberId());
				fileEntity.setUpdateDate(DateTimeUtils.currentDate());
				fileEntity.setUpdateUser(user.getMemberId());
				
//				String message = sendToService(order.getId(), fileEntity, user);
//				if(message != null) {
//					fileEntity.setFileStatus(message);
//				} else {
//					fileEntity.setFileStatus("error!!!");
//				}
				
				orderDocumentMapper.insertFileRecord(fileEntity);
				
				if("T0001".equals(info.getFileCode())){
					//查看合同状态
					int docStatus = orderStatusService.getCurrentStatus(order.getTradeId(), ContractStatusEnum.ORDER_STATUS_1.getCode());
					
					if(docStatus != Integer.valueOf(ContractStatusEnum.ORDER_STATUS_ITEM_1_9.getCode())){
						//更改合同状态
						try {
							orderStatusService.setOrderStatus(order.getTradeId(), 
									ContractStatusEnum.ORDER_STATUS_1.getCode(), 
									Integer.parseInt(ContractStatusEnum.ORDER_STATUS_ITEM_1_9.getCode()), 
									"", 
									info.getUpdateUser());
						} catch (Exception e) {
							e.printStackTrace();
							throw new BizException(e.getMessage());
						} 
					}
				}
				
			}
		}
	}

	/**
	 * 外部系统-接受单证信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String receiveDocInfo(DocInfoVO vo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DocumentQuery query = new DocumentQuery();
		query.setUuid(vo.getUuid());
		query.setDocumentType(vo.getDocumentType());
		
//		try {
			Map<String, Object> orderData = orderDocumentMapper.getOrderData(query);
			if(orderData == null) {
				return "订单信息不存在";
				
			}
			
			// 单证主表ID
			Long orderDocumentId = null;
			
			// 判断是否已有单证主表
			if(orderData.get("orderDocumentId") == null) {
				OrderDocument entity = new OrderDocument();
	
				entity.setOrderDocumentUuid(KeyGenUtils.newUuid());
				entity.setOrderId((Long)orderData.get("orderId"));
				entity.setOrderNo(String.valueOf(orderData.get("orderNo")));
				entity.setContractNo(vo.getContractNo());
				if(orderData.get("shipName") != null) {
					entity.setShipName(String.valueOf(orderData.get("shipName")));
				}
				entity.setDocumentType(vo.getDocumentType());
				//entity.setBillDate(vo.getBillDate());
				//entity.setRemark(vo.getRemark());
				
				entity.setLangVer(Constants.LANG_VER);
				entity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				entity.setCreateDate(DateTimeUtils.currentDate());
				entity.setCreateUser(9999L);
				entity.setUpdateDate(DateTimeUtils.currentDate());
				entity.setUpdateUser(9999L);	
	
				orderDocumentMapper.insertRecord(entity);	
				orderDocumentId = entity.getOrderDocumentId();
			} else {
				orderDocumentId = (Long) orderData.get("orderDocumentId");
			}
			
			if(vo.getFileList() != null) {
				for(int i = 0; i < vo.getFileList().size(); i++) {
					DocInfoAttaVO info = vo.getFileList().get(i);
					
					// 修改时把原纪录作废
					OrderDocumentFile deleteEntity = new OrderDocumentFile();
					deleteEntity.setOrderDocumentId(orderDocumentId);
					deleteEntity.setFileCode(info.getFileCode());
					deleteEntity.setUpdateDate(DateTimeUtils.currentDate());
					deleteEntity.setUpdateUser(9999L);
					orderDocumentMapper.updateFileAliveByCode(deleteEntity);
					
					// 插入新纪录
					OrderDocumentFile fileEntity = new OrderDocumentFile();
					fileEntity.setDocumentFileUuid(KeyGenUtils.newUuid());
					fileEntity.setOrderDocumentId(orderDocumentId);
					fileEntity.setFileName(info.getFileName());
					fileEntity.setFileCode(info.getFileCode());
					fileEntity.setOriginalName(info.getOriginalName());
					fileEntity.setFilePath(info.getFilePath());
					if(info.getOriginalName() != null && info.getOriginalName().lastIndexOf(".")> 0){
						String suffix = info.getOriginalName().substring(info.getOriginalName().lastIndexOf(".") + 1);
						fileEntity.setFileFormat(suffix);
					}
					fileEntity.setFileSize(new Long(0));
//					fileEntity.setUploadTime(sdf.parse(info.getUploadTime()));
					fileEntity.setUploadTime(DateTimeUtils.currentTimestamp());
					fileEntity.setUploadPerson(9999L);
	
					fileEntity.setLangVer(Constants.LANG_VER);
					fileEntity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					fileEntity.setCreateDate(DateTimeUtils.currentDate());
					fileEntity.setCreateUser(9999L);
					fileEntity.setUpdateDate(DateTimeUtils.currentDate());
					fileEntity.setUpdateUser(9999L);

//					String message = sendToService((Long)orderData.get("orderId"), fileEntity, user);
//					if(message != null) {
//						fileEntity.setFileStatus(message);
//					} else {
//						fileEntity.setFileStatus("error!!!");
//					}
					
					orderDocumentMapper.insertFileRecord(fileEntity);
				}
			}
	/*	} catch (ParseException e) {
			e.printStackTrace();
			return "上传时间不是yyyy-MM-dd HH:mm:ss格式";
		}*/
		
		return null;
	}
	
	private String sendToService(Long orderId, OrderDocumentFile entity, CurrentUser user) {
		String message = null;
		
		try{	
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("service", "saveDoc");
			jsonMap.put("memberId", user.getMemberId().intValue());
			jsonMap.put("objectId", orderId.intValue());
			jsonMap.put("docTypeCode", entity.getFileCode());
			
			jsonMap.put("docId", null);
			jsonMap.put("fileName", entity.getFileName());
			jsonMap.put("fileType", "0");
			jsonMap.put("filePath", entity.getFilePath());
			jsonMap.put("sign", "签名信息");
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", "");
			map.put("value", "");
			list.add(map);
			jsonMap.put("metadataValues", list);
	
			String ret = HttpUtils.sendRequest("http://doc8.1chemic.com/api/service.json", new JSONObject(jsonMap).toString(), "");
			System.out.println(ret);
			JSONObject jsonResult = new JSONObject(ret);
			if(jsonResult.get("message") != null) {
				message = String.valueOf(jsonResult.get("message"));
				if(message.length() > 8) {
					message = message.substring(0, 8);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
