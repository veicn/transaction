package com.sinochem.crude.trade.info.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.domain.InfoAttachment;
import com.sinochem.crude.trade.info.dao.InfoAttachmentMapper;
import com.sinochem.crude.trade.info.service.InfoAttachmentService;

@Service
public class InfoAttachmentServiceImpl implements InfoAttachmentService {
	@Autowired
	private InfoAttachmentMapper infoAttachmentMapper;
	
	public InfoAttachmentMapper getMapper(){
		return infoAttachmentMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InfoAttachment infoattachment){
		 return infoAttachmentMapper.insertRecord(infoattachment);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoAttachmentMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InfoAttachment  record){
    	return infoAttachmentMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InfoAttachment infoAttachment) throws BizException{
		if ( infoAttachment.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoAttachmentMapper.updateRecordById(infoAttachment);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoAttachmentMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InfoAttachment infoAttachment){
		return infoAttachmentMapper.updateRecords(infoAttachment.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InfoAttachment findByPrimaryKey(Long id){
		return  infoAttachmentMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InfoAttachment findByUuid(String uuid){
		return  infoAttachmentMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InfoAttachment> queryByEntitys(InfoAttachment infoAttachment){
		return  infoAttachmentMapper.queryByEntitys(infoAttachment);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoAttachmentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoAttachmentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoAttachmentMapper.countRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 通过资讯ID查询 附件列表
	 */
	@Override
	public List<InfoAttachment> findListByInfoId(Long id) {
		InfoAttachment entity = new InfoAttachment();
		entity.setInfoId(id);
		return infoAttachmentMapper.queryByEntitys(entity);
	}
	
	/**
	 * 删除附件
	 */
	@Override
	public int deleteInfoAttachment(InfoAttachment item) {
		return infoAttachmentMapper.deleteInfoAttachment(item);
	}
	
}
