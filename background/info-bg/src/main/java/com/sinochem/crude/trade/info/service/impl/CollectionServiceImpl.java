package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.dao.CollectionMapper;
import com.sinochem.crude.trade.info.domain.Collection;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.service.CollectionService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	private CollectionMapper collectionMapper;
	@Autowired
	private InfoService infoService;

	public CollectionMapper getMapper(){
		return collectionMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Collection collection){
		 return collectionMapper.insertRecord(collection);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return collectionMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Collection  record){
    	return collectionMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Collection collection) throws BizException{
		if ( collection.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return collectionMapper.updateRecordById(collection);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return collectionMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Collection collection){
		return collectionMapper.updateRecords(collection.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Collection findByPrimaryKey(Long id){
		return  collectionMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Collection findByUuid(String uuid){
		return  collectionMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Collection> queryByEntitys(Collection collection){
		return  collectionMapper.queryByEntitys(collection);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return collectionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) collectionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return collectionMapper.countRecords(map);
	}


	//**************************以下方法为开发者补充*********************************/

	/**
	 * 收藏资讯
	 * @param infoUUId
	 * @param collectUserId
	 * @return
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result collectInfo(String infoUUId, String collectUserId,CurrentUser user) throws BizException {

		Result res = new Result();
		//TODO 通过infoUUId 和 collectUserId 查看是否已收藏 暂定用 queryByEntitys
		Collection collection = new Collection();
		Info info = infoService.findByUuid(infoUUId);
		collection.setInformationId(info.getId());
		collection.setCollectionUserId(user.getMemberId().toString());
		Collection coll = collectionMapper.queryByColl(collection);
		int r1,r2;
		if(coll != null){
			res.setMessage("已取消收藏");
			collectionMapper.deleteRecords(collection);
		}else{
			collection.setInformationId(info.getId());
			collection.setUuid(infoUUId);
			collection.setCollectionUserId(user.getMemberId().toString());
			collection.setCreateUser(user.getName());
			collection.setCreateDate(DateTimeUtils.currentDate());
			collection.setUpdateUser(user.getName());
			collection.setUpdateDate(DateTimeUtils.currentDate());
			r1 = collectionMapper.insertRecord(collection);
			res.setMessage("成功收藏");
		}

		//更新资讯表中收藏数
		if(coll == null){
			if(info.getCollectionCount() == null || info.getCollectionCount() == 0){
				info.setCollectionCount(1);
			}else{
				info.setCollectionCount(info.getCollectionCount() + 1);
			}
		}else{
			info.setCollectionCount(info.getCollectionCount()-1);
		}
		r2 = infoService.updateInfoCount(info,user);
		return res;
	}

	/*用户收藏列表*/
	@Override
	public List<Map<String, Object>> findByMemberId(Long memberId) {
		return collectionMapper.findByMemberId(memberId);
	}
	
	@Override
	public Page<Map<String, Object>> queryCollectionInfo(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) collectionMapper.queryCollectionInfo(map);
	}
}
