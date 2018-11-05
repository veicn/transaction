package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.ChmentsMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.enums.BusinessType;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ChmentsServiceImpl implements ChmentsService {
	@Autowired
	private ChmentsMapper chmentsMapper;
	
	public ChmentsMapper getMapper(){
		return chmentsMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Chments chments){
		 return chmentsMapper.insertRecord(chments);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long attachmentId) throws BizException {
		if (attachmentId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return chmentsMapper.deleteById(attachmentId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Chments  record){
    	return chmentsMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Chments chments) throws BizException{
		if ( chments.getAttachmentId() == null  ) {
			throw new BizException("attachmentId 为空，不能修改 ");
		}
		return chmentsMapper.updateRecordById(chments);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Chments chments) throws BizException{
		if ( chments.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return chmentsMapper.updateRecordByUuid(chments);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return chmentsMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Chments chments){
		return chmentsMapper.updateRecords(chments.toMap());
	}
	/**
	 * 根据条件-批量修改状态
	 */
	@Override
	public int updateAvFlag(Chments chments){
		return chmentsMapper.updateAvFlag(chments.toMap());
	}
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Chments findByPrimaryKey(Long attachmentId){
		return  chmentsMapper.findByPrimaryKey(attachmentId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Chments findByUuid(String uuid){
		return  chmentsMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Chments> queryByEntitys(Chments chments){
		return  chmentsMapper.queryByEntitys(chments);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return chmentsMapper.queryRecords(map);
	}
	

	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return chmentsMapper.countRecords(map);
	}

	
	
	//**************************以下方法为开发者补充*********************************/
	
	
	@Override
	public int deleteRecordByUuid(String uuid) throws BizException {
		return chmentsMapper.deleteRecordByUuid(uuid);
	}

	@Override
	public int insertAttachmentsRecord(ChmentsVO vo) throws BizException {
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(new Date());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		return chmentsMapper.insertRecord(vo);
	}

	@Override
	public int updateAttachmentsRecordByUuid(ChmentsVO vo) throws BizException {
		if ( vo.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		vo.setUpdateDate(new Date());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		
		return chmentsMapper.updateRecordByUuid(vo);
	}

	@Override
	public Page<Map<String, Object>> queryAttachmentList(
			Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) chmentsMapper.queryRecords(map);
	}

	/**
	 * 根据业务主键ID 和业务类型查询船燃和船供订单上传附件
	 * @param id
	 * @param businessType
	 * @return
	 */
	@Override
	public List<Chments> findByIdAndType(Long id, String businessType) {
		return chmentsMapper.findByIdAndType(id, businessType);
	}

	@Override
	public List<Chments> queryUploadedList(long orderId) {
		return chmentsMapper.findByOrderId(orderId);
	}

	/**
	 * 根据业务id查询附件信息
	 * @param id
	 * @return
	 */
	public List<ChmentsVO> findUrlsById(Long id){
		Chments chments = new Chments();
		chments.setId(id);
		chments.setFileType(Constants.FILE_PRODUCT_TYPE);
		List<Chments> chmentsList = this.queryByEntitys(chments);
		List<ChmentsVO> result = Lists.newArrayList();
		for(Chments c : chmentsList){
			result.add(new ChmentsVO().toVO(c));
		}
		return result;
	}

	@Override
	public List<ChmentsVO> findChmentsVoByChments(Chments chments){
		List<Chments> chmentsList = this.queryByEntitys(chments);
		List<ChmentsVO> result = Lists.newArrayList();
		for(Chments c : chmentsList){
			result.add(new ChmentsVO().toVO(c));
		}
		return result;
	}

	@Override
	public List<Chments> selectChmentListByForeignId(Long infoId) {
		return chmentsMapper.selectChmentListByForeignId(infoId);
	}

	@Override
	public int updateProductChments(List<ChmentsVO> chmentList  , Long id , CurrentUser user){
		int result = 0;
		//没有更新
		if(chmentList == null || chmentList.size() < 1){
			return result;
		}
		//如果id，path都不为空或都为空则不处理
		//如果id为空path不为空则新增
		//如果id不为空path为空则删除
		for(Chments chments : chmentList){
			String url = chments.getPath();
			Long attachmentId = chments.getAttachmentId();
			if(StringUtil.isBlank(url) && (attachmentId == null || attachmentId.longValue() < 1)){
				continue;
			}
			if(StringUtil.isNotBlank(url) && attachmentId != null && attachmentId.longValue() > 0){
				continue;
			}
			if(StringUtil.isBlank(url)){
				if (id == null || id.longValue()< 1){continue;}
				chmentsMapper.deleteById(chments.getAttachmentId());
			}else{
				chments.setAliveFlag("1");
				chments.setId(id);
				chments.setCreateDate(new Date());
				chments.setCreateUser(user.getMemberId());
				chments.setName(url.substring(url.lastIndexOf("/")+1 , url.lastIndexOf(".")));
				chments.setPath(url);
				chments.setUuid(KeyGenUtils.newUuid());
				chments.setFileType(Constants.FILE_PRODUCT_TYPE);
				chments.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				chments.setType(BusinessType.SALE.getCode());
				chmentsMapper.insertRecord(chments);
			}
			result ++;


		}
		return result;
	}
}
