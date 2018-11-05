package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.InfoContentMapper;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.service.InfoContentService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class InfoContentServiceImpl implements InfoContentService {
	@Autowired
	private InfoContentMapper infoContentMapper;
	
	public InfoContentMapper getMapper(){
		return infoContentMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InfoContent infocontent){
		 return infoContentMapper.insertRecord(infocontent);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoContentMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InfoContent  record){
    	return infoContentMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InfoContent infoContent) throws BizException{
		if ( infoContent.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoContentMapper.updateRecordById(infoContent);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoContentMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InfoContent infoContent){
		return infoContentMapper.updateRecords(infoContent.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InfoContent findByPrimaryKey(Long id){
		return  infoContentMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InfoContent findByUuid(String uuid){
		return  infoContentMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InfoContent> queryByEntitys(InfoContent infoContent){
		return  infoContentMapper.queryByEntitys(infoContent);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoContentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoContentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoContentMapper.countRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 发布公告
	 */
	@Override
	public int saveNotice(InfoContent infoContent, CurrentUser user) {
		//新增
		infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
		infoContent.setUuid(KeyGenUtils.newUuid());
		infoContent.setCreateDate(DateTimeUtils.currentDate());
		infoContent.setCreateUser(user.getName());
		infoContent.setUuid(KeyGenUtils.newUuid());
		return infoContentMapper.insertRecord(infoContent);
	}
	/**
	 * 修改公告
	 */
	@Override
	public int updateNotice(InfoContent infoContent, CurrentUser user) {
		//修改
		infoContent.setUpdateDate(DateTimeUtils.currentDate());
		infoContent.setUpdateUser(user.getName());
		return infoContentMapper.updateInfoContentTexAndTextHtml(infoContent);
	}
	/**
	 * 查找公告
	 */
	@Override
	public InfoContent findByInfoId(Long infoId) {
		return infoContentMapper.findByInfoId(infoId);
	}
	
	/**
	 * 根据资讯ID 删除资讯内容 
	 */
	@Override
	public int deleteByInfoId(Long infoId) {
		InfoContent infoContent = new InfoContent();
		infoContent.setInfoId(infoId);
		infoContent.setUpdateDate(DateTimeUtils.currentDate());
		infoContent.setUpdateUser("1");
		return infoContentMapper.deleteByInfoContent(infoContent);
	}
	
}
