package com.sinochem.crude.trade.shiprefueling.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.ChmentsMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface ChmentsService {
	
	public abstract ChmentsMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(Chments chments);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long attachmentId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Chments  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Chments chments) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Chments chments) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Chments chments);

	/**
	 * 根据条件-批量修改状态
	 * @param chments
	 * @return
	 */
	int updateAvFlag(Chments chments);

	/**
	 * 根据主键-查询对象
	 */
	Chments findByPrimaryKey(Long attachmentId);

	/**
	 * 根据uuid查询对象
	 */
	Chments findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Chments> queryByEntitys(Chments chments);
		
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
	 * 新增
	 */
	int insertAttachmentsRecord(ChmentsVO vo) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateAttachmentsRecordByUuid(ChmentsVO vo) throws BizException;
	
	
	/**
	 * 删除对象
	 */
	int deleteRecordByUuid(String uuid) throws BizException;
	
	/**
	 * 列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryAttachmentList(Map<String, Object> map,SimplePageInfo pageInfo);


	List<Chments> findByIdAndType(Long id, String businessType);

	List<Chments> queryUploadedList(long orderId);
	/**
	 * 根据业务id查询附件url信息
	 * @param id
	 * @return
	 */
	List<ChmentsVO> findUrlsById(Long id);


	/**
	 * 多条件查询
	 * @param chments
	 * @return
	 */
	public List<ChmentsVO> findChmentsVoByChments(Chments chments);


    List<Chments> selectChmentListByForeignId(Long infoId);

	/**
	 * 更新商品详情库表信息
	 * @return
	 */
	int updateProductChments(List<ChmentsVO> chmentList , Long id , CurrentUser user);
}
