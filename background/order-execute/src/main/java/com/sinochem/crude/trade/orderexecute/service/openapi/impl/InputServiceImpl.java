package com.sinochem.crude.trade.orderexecute.service.openapi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.ContractStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ContractInfoAttaVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ContractInfoVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ContractVO;
import com.sinochem.crude.trade.orderexecute.dao.FeeSubjectMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.FeeSubject;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderContract;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocument;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.crude.trade.orderexecute.service.OrderContractService;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentService;
import com.sinochem.crude.trade.orderexecute.service.openapi.InputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.ContractDetailVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalRecapContractVO;

@Service
public class InputServiceImpl implements InputService {
	@Autowired
	private OrderMapper ordermapper;
	@Autowired
	private OrderContractService orderContractService;
	@Autowired
	private OrderDocumentService orderDocumentService;
	@Autowired
	private InterfaceSystemService interfaceSystemService;
	@Autowired
	private OutputService outputService;
	@Autowired
	private OrderContractMapper orderContractMapper;
	@Autowired
	private OrderDocumentMapper orderDocumentMapper;
	@Autowired
	private OrderStatusService orderStatusService;
	
	private final Logger log = Logger.getLogger(getClass());
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void receiveContractInfo(ContractVO vo) {
		List<ContractInfoVO> contractDataList = vo.getContractList();

		for(ContractInfoVO contractData : contractDataList){
			String orderUuid = contractData.getUuid();
			Order order = ordermapper.findByUuid(orderUuid);
			
			//>> 数据校验
			if(order == null){
				throw new BizException( "订单不存在，uuid:"+orderUuid);
			}
			
			//>> 组装数据
			 OrderDocument document = new OrderDocument();
		     //OrderDocumentVO orderDocumentVO = new OrderDocumentVO();
			 //OrderDocumentFileVO orderDocumentFileVO = new OrderDocumentFileVO();
			 document.setContractNo(contractData.getContractNo());
//			 document.setDocumentType("T00");
			 
			 document.setOrderId(order.getId());
			 document.setOrderNo(order.getOrderNo());
			 document.setCreateDate(new Date());
			 
			
			OrderContract contract = BeanConvertUtils.beanToBean(contractData, OrderContract.class);
			contract.setOrderId(order.getId());
			contract.setOrderNo(order.getOrderNo());
			contract.setCreateDate(new Date());
			contract.setBuyerCustomerId(order.getBuyerCustomerId());
			contract.setSellerCustomerId(order.getSellerCustomerId());
			
			contract.setBuyerCustomer(order.getBuyerCustomerName());
			contract.setSellerCustomer(order.getSellerCustomerName());
			contract.setContractType(contractData.getContractType());
			
			List<ContractInfoAttaVO> contractFileVOList = contractData.getFileList();
			//存储到本地的合同数据
			List<OrderContractFile> contractFileList = new ArrayList<>();
			List<OrderDocumentFile> documentFileList = new ArrayList<>();
			//发送到外部系统的合同数据
			List<ContractDetailVO> contractFileRemoteList = new ArrayList<>();
			
			for(ContractInfoAttaVO contractFileData : contractFileVOList){
				OrderContractFile contractFile = BeanConvertUtils.beanToBean(contractFileData, OrderContractFile.class);
				
				contractFile.setFileStatus("10");
				contractFile.setCreateDate(new Date());
				contractFileList.add(contractFile);
				
				OrderDocumentFile documentFile = new OrderDocumentFile();
				documentFile.setFileStatus("10");
				documentFile.setCreateDate(new Date());
				documentFile.setFileCode("T0001");
				documentFile.setFileName("合同");
				documentFile.setFilePath(contractFileData.getFilePath());
				documentFile.setOriginalName(contractFileData.getOriginalName());
				
				documentFileList.add(documentFile);
				
				
				ContractDetailVO contractDetailVO = new ContractDetailVO();
				contractDetailVO.setFile_name(contractFileData.getContractFileName());
				contractDetailVO.setFile_path(contractFileData.getFilePath());
				contractFileRemoteList.add(contractDetailVO);
			}
			
			//>> 查询合同是否存在，如果订单合同已存在，则先删除（伪删除）
			OrderContract contractQuery = new OrderContract();
			contractQuery.setOrderId(order.getId());
			List<OrderContract> existContractlist = orderContractMapper.queryByEntitys(contractQuery);
			
			boolean newFlag = true;
			if(existContractlist != null && existContractlist.size() > 0 ){
				newFlag = false;
				
				for(OrderContract contractItem : existContractlist){
					OrderContract contractUpdate = new OrderContract();
					contractUpdate.setContractId(contractItem.getContractId());
					contractUpdate.setAliveFlag("0");
					orderContractMapper.updateRecordById(contractUpdate);
				}
			}
			//>> 查询单证里的合同是否存在，如果订单合同已存在，则先删除（伪删除）
			OrderDocument DocumentQuery = new OrderDocument();
			DocumentQuery.setOrderId(order.getId());
			List<OrderDocument> existDocumentlist = orderDocumentMapper.queryByEntitys(DocumentQuery);
			
			if(existDocumentlist !=null && existDocumentlist.size()>0){
				newFlag = false;
				for (OrderDocument documentItem : existDocumentlist) {
					 OrderDocument documentUpdate = new OrderDocument();
					 documentUpdate.setOrderDocumentId(documentItem.getOrderDocumentId());
					 documentUpdate.setAliveFlag("0");
					 orderDocumentMapper.updateRecordById(documentUpdate);
					 
				}
			}
			
			//>> 保存合同信息，合同附件
			try {
				orderContractService.receiveContractProcess(contract, contractFileList);
				orderDocumentService.receiveContractProcess(document, documentFileList);
			} catch (Exception e) {
				throw new BizException("合同信息保存失败！", e);
			}
			
			if(newFlag){
				//>> 合同状态更新到状态机
				try{
					orderStatusService.setOrderStatus(order.getTradeId(), 
							ContractStatusEnum.ORDER_STATUS_1.getCode(),
							Integer.parseInt(ContractStatusEnum.ORDER_STATUS_ITEM_1_9.getCode()), 
							"", 
							9999L);
				} catch (BizException e) {
					log.error("【合同接收】更新状态机异常！"+e.getMessage(), e);
					throw new BizException( "【合同接收】更新状态机异常！"+e.getMessage(), e);
				} catch (Exception e) {
					log.error("【合同接收】更新状态机异常！未知错误", e);
					throw new BizException( "【合同接收】更新状态机异常！未知错误", e);
				}
			}
			
			//>> 合同附件发送到对家系统
			if(!contractFileRemoteList.isEmpty()) {
				InterfaceSystem otherSys = null;
				
				if(vo.getMemberId().equals(order.getBuyerCustomerId())){
					otherSys = interfaceSystemService.findByMemberId(order.getSellerCustomerId());
				}else if(vo.getMemberId().equals(order.getSellerCustomerId())){
					otherSys = interfaceSystemService.findByMemberId(order.getBuyerCustomerId());
				}
				
				if(otherSys != null){
					InterfaceListVO interfaceListVO = new InterfaceListVO();
					interfaceListVO.setSysName(otherSys.getSysName());
					
					PhysicalRecapContractVO physicalRecapContractVO = new PhysicalRecapContractVO();
					physicalRecapContractVO.setId(orderUuid);
					physicalRecapContractVO.setFiles(contractFileRemoteList);
					
					log.info("【合同接收】附件开始发往外部系统...sysName【"+otherSys.getSysName()+"】");
					ResultDatas<Void> saveRes = outputService.physicalRecapContractSave(interfaceListVO, physicalRecapContractVO);
					log.info(saveRes.getStatus() == Result.SUCCESS?"发送成功！":"发送失败！"+saveRes.getMessage());
//					if(saveRes.getStatus() != Result.SUCCESS){
//						throw new BizException("【合同接收】发送外部系统失败！"+saveRes.getMessage());
//					}
				}
			}
		}
	}
	
	@Override
	public FeeSubjectMapper getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecord(FeeSubject feeSubject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Long feeSubjectId) throws BizException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecords(FeeSubject record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecordById(FeeSubject feeSubject) throws BizException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecords(FeeSubject feeSubject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FeeSubject findByPrimaryKey(Long feeSubjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeeSubject findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeeSubject> queryByEntitys(FeeSubject feeSubject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map,
			SimplePageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countRecords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
