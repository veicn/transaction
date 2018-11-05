package com.sinochem.crude.trade.shipping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sinochem.crude.trade.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.dao.LoadPortMapper;
import com.sinochem.crude.trade.shipping.dao.LoadingProgressMapper;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;
import com.sinochem.crude.trade.shipping.service.LoadingProgressService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class LoadingProgressServiceImpl implements LoadingProgressService {
	@Autowired
	private LoadingProgressMapper loadingProgressMapper;
	@Autowired
	private LoadPortMapper loadPortMapper;
	
	@Autowired
	private ConfirmationSheetMapper confirmationSheetMapper;
	public LoadingProgressMapper getMapper(){
		return loadingProgressMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(LoadingProgress loadingprogress){
		 return loadingProgressMapper.insertRecord(loadingprogress);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long shipLoadingProgressId) throws BizException{
		if (shipLoadingProgressId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return loadingProgressMapper.deleteById(shipLoadingProgressId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(LoadingProgress  record){
    	return loadingProgressMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(LoadingProgress loadingProgress) throws BizException{
		if ( loadingProgress.getShipLoadingProgressId() == null  ) {
			throw new BizException("shipLoadingProgressId 为空，不能修改 ");
		}
		return loadingProgressMapper.updateRecordById(loadingProgress);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(LoadingProgress loadingProgress) throws BizException{
		if ( loadingProgress.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return loadingProgressMapper.updateRecordByUuid(loadingProgress);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return loadingProgressMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(LoadingProgress loadingProgress){
		return loadingProgressMapper.updateRecords(loadingProgress.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public LoadingProgress findByPrimaryKey(Long shipLoadingProgressId){
		return  loadingProgressMapper.findByPrimaryKey(shipLoadingProgressId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public LoadingProgress findByUuid(String uuid){
		return  loadingProgressMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<LoadingProgress> queryByEntitys(LoadingProgress loadingProgress){
		return  loadingProgressMapper.queryByEntitys(loadingProgress);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return loadingProgressMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) loadingProgressMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return loadingProgressMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据装港表主键查询进度
	 */
	@Override
	public List<LoadingProgress> findByConfirmationSheetId(
			LoadingProgressQuery loadingProgressQuery) {
		
		return loadingProgressMapper.queryByEntitysList(loadingProgressQuery);
	}
	
	@SuppressWarnings("null")
	@Override
	public Integer insertRecordOrUpdate(LoadingProgress domain,
			CurrentUser currentUser, String confuuid) throws BizException {
		BizException bizException = new BizException();
		if (null == currentUser||null==domain) {
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			 throw bizException;
		}
		
		//uuid  不为空  执行查询
		if (StringUtil.isNotBlank(domain.getUuid())) {
			LoadingProgress findByUuid = loadingProgressMapper.findByUuid(domain.getUuid());
			if (null != findByUuid) {
				
				domain.setShipLoadingProgressId(findByUuid.getShipLoadingProgressId());
				domain.setCreateUser(currentUser.getMemberId());
				domain.setUpdateUser(currentUser.getMemberId());
				domain.setAliveFlag(Constants.SAVE_FLAG);
				int insertRecord = loadingProgressMapper.insertRecord(domain);
				return insertRecord;
			}
			
		} else {
			if (StringUtil.isNotBlank(confuuid)) {
				bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
				 throw bizException;
			}
			ConfirmationSheet confirmationsheet = confirmationSheetMapper.findByUuid(confuuid);
			HashMap<String, Object> map =new HashMap<String, Object>();
			map.put("confId", confirmationsheet.getConfirmationSheetId());
			LoadPort queryConfirmationSheetId = loadPortMapper.queryConfirmationSheetId(map);
			
			//数据为空 
			if (null == queryConfirmationSheetId) {
				//新增装货港数据
				
				queryConfirmationSheetId.setShipConfirmationSheetId(confirmationsheet.getConfirmationSheetId());
				queryConfirmationSheetId.setUuid(UUID.randomUUID().toString());
				queryConfirmationSheetId.setCreateUser(currentUser.getMemberId());
				queryConfirmationSheetId.setUpdateUser(currentUser.getMemberId());
				queryConfirmationSheetId.setAliveFlag(Constants.SAVE_FLAG);
				 loadPortMapper.insertRecordObject(queryConfirmationSheetId);
			} 
			//装港进度数据
			domain.setShipLoadPortId(queryConfirmationSheetId.getShipLoadPortId().toString());
			domain.setShipConfirmationSheetId(queryConfirmationSheetId.getShipConfirmationSheetId());
			domain.setAliveFlag(Constants.SAVE_FLAG);
			domain.setCreateUser(currentUser.getMemberId());
			domain.setUpdateUser(currentUser.getMemberId());
			domain.setUuid(UUID.randomUUID().toString());
			
		}
		
		//新增装港进度
		int insertRecord = loadingProgressMapper.insertRecord(domain);
		
		return insertRecord;
	}

	
	/**
	 * 船舶装港进度表
	 */
	@Override
	public LoadingProgress findByConfirmationSheetId(Long confirmationSheetId) {
		return loadingProgressMapper.findByConfirmationSheetId(confirmationSheetId);
	}
	
	/**
	 * 船舶装港进度表
	 */
	@Override
	public List<LoadingProgress> findListByConfirmationSheetId(Long confirmationSheetId) {
		return loadingProgressMapper.findListByConfirmationSheetId(confirmationSheetId);
	}

	/**
	 * 微信 新增或修改装港进度
	 * @param domain
	 * @param currentUser
	 * @return
	 */
	@Override
	public Integer saveOrUpdateLoadingProgress(LoadingProgress domain, CurrentUser currentUser) {
		domain.setUpdateDate(DateUtil.getCurrentDate());

		//domain.setUpdateUser(currentUser.getEpMemberId());
		if (StringUtil.isNotBlank(domain.getUuid())) {
			return loadingProgressMapper.updateRecordByUuid(domain);
		}else{
			domain.setUuid(UUID.randomUUID().toString());
			domain.setCreateDate(DateUtil.getCurrentDate());
			domain.setAliveFlag(Constants.SAVE_FLAG);
			//domain.setCreateUser(currentUser.getEpMemberId());
			return loadingProgressMapper.insertRecord(domain);
		}
	}


//	@Override
//	public Integer saveOrUpdateLoadingProgress(LoadingProgress domain, CurrentUser currentUser) {
//		domain.setUpdateDate(DateUtil.getCurrentDate());
//
//		domain.setUpdateUser(currentUser.getEpMemberId());
//		if (StringUtil.isNotBlank(domain.getUuid())) {
//			return loadingProgressMapper.updateRecordByUuid(domain);
//		}else{
//			domain.setUuid(UUID.randomUUID().toString());
//			domain.setCreateDate(DateUtil.getCurrentDate());
//			domain.setAliveFlag(Constants.SAVE_FLAG);
//			domain.setCreateUser(currentUser.getEpMemberId());
//			return loadingProgressMapper.insertRecord(domain);
//		}
//	}
}
