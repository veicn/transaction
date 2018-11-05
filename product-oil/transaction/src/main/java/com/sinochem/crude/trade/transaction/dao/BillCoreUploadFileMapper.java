package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.BillCoreUpload;
import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface BillCoreUploadFileMapper {

	public int insertRecord(BillCoreUpload entity);
	
	public int deleteById(@Param("documentFileId") Long documentFileId);
	
	public int deleteRecords(BillCoreUpload entity);
	
	public int updateRecordById(BillCoreUpload entity);
	
	public int updateRecordByUuid(BillCoreUpload entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public BillCoreUpload findByPrimaryKey(Long documentFileId);
	
	public BillCoreUpload findByUuid(String uuid);
	
	//返回对象的List
	public List<BillCoreUpload> queryByEntitys(BillCoreUpload entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 根据id 查询upload 列表
	 * @param id
	 * @return
	 */
	List<BillCoreUpload> queryByOrderIdList(Long id);

	/**
	 * 根据uuid 删除
	 * @param documentFileUuid
	 * @return
	 */
	Integer deleteByUUid(String documentFileUuid);
	/**
	 * 根据删除订单 类型是10，11(临时发票，结算发票)的单据
	 * @param
	 * @return
	 */
	Integer deleteByOrderid(Long orderid);


	/**
	 * dubbo查询接口
	 * @param uploads
	 * @return
	 */
	BillCoreRemoteUpload queryByOrderIdRemoteObject(BillCoreRemoteUpload uploads);

	/**
	 * dobbo 更新接口
	 * @param uplasd
	 * @return
	 */
	Integer RemoteUpdate(BillCoreRemoteUpload uplasd);

	/**
	 * dubbo新增接口
	 * @param uplasd
	 * @return
	 */
	Integer RemoteInsertUpload(BillCoreRemoteUpload uplasd);

	/**
	 * dubbo删除接口
	 *
	 * @param stringObjectHashMap
	 * @return
	 */
	Integer deleteRemoteUpload(HashMap<String, Object> stringObjectHashMap);
}
