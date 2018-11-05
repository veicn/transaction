package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.BillCoreUploadFileMapper;
import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface BillCoreUploadService {
	
	public abstract BillCoreUploadFileMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(BillCoreUpload rDocumentFile,CurrentUser currentUser) throws BizException;
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long documentFileId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(BillCoreUpload record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(BillCoreUpload rDocumentFile) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(BillCoreUpload rDocumentFile) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(BillCoreUpload rDocumentFile);
	
	
	/**
	 * 根据主键-查询对象
	 */
	BillCoreUpload findByPrimaryKey(Long documentFileId);

	/**
	 * 根据uuid查询对象
	 */
	BillCoreUpload findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<BillCoreUpload> queryByEntitys(BillCoreUpload rDocumentFile);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	

	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map);

    //**************************以下方法为开发者补充*********************************/
    /**
     *  查询票据中心列表
     *
     * @param id
     * @return
     */
    List<BillCoreUpload> findbyOrderIdList( Long id) throws BizException;

    /**
     *
     * @param documentFileUuid
     * @return
     */
    Integer deleteByUUid(String documentFileUuid, CurrentUser currentUser) throws BizException ;

	Integer deleteByOrderid(Long orderid);
}
