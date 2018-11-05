package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.DocInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocument;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.model.OrderDocumentVO;
import com.sinochem.crude.trade.orderexecute.query.DocumentQuery;

public interface OrderDocumentService {
	
	public abstract OrderDocumentMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderDocument orderDocument);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderDocumentId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderDocument  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderDocument orderDocument) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderDocument orderDocument);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderDocument findByPrimaryKey(Long orderDocumentId);

	/**
	 * 根据uuid查询对象
	 */
	OrderDocument findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderDocument> queryByEntitys(OrderDocument orderDocument);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 取得单证已上传附件代码列表
	 */
	List<Map<String,Object>> getDocumentFileList(DocumentQuery query);	
	
	/**
	 * 取得单证类型列表
	 */
	List<Map<String,Object>> getDocumentType(DocumentQuery query);		
	
	/**
	 * 取得合同已上传附件代码列表
	 */
	List<Map<String,Object>> getContractFileList(String uuid);	
	
	/**
	 * 取得单证编辑画面上部数据
	 */
	Map<String,Object> getMainData(DocumentQuery query);

	/**
	 * 取得单证详情
	 */
	Map<String,Object> getDocumentDetail(DocumentQuery query);
	
	/**
	 * 新增或者更新单证信息
	 */
	void saveDocument(OrderDocumentVO vo, CurrentUser user);
	
	/**
	 * 合同接收信息处理
	 * @param orderContract
	 * @param contractFileList
	 */
	void receiveContractProcess(OrderDocument orderDocument, List<OrderDocumentFile> documentFileList);
	
	/**
	 * 外部系统-接受单证信息
	 */
	String receiveDocInfo(DocInfoVO vo);

	List<Map<String, Object>> queryDocumentFileList(DocumentQuery query);
}
