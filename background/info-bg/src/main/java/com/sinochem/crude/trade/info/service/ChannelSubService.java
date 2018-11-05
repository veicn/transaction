package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.ChannelSubMapper;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.model.ChannelSubVO;
import com.sinochem.crude.trade.info.query.ChannelSubQuery;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface ChannelSubService {
	
	public abstract ChannelSubMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(ChannelSub channelSub);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(ChannelSub  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(ChannelSub channelSub) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(ChannelSub channelSub);
	
	
	/**
	 * 根据主键-查询对象
	 */
	ChannelSub findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	ChannelSub findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<ChannelSub> queryByEntitys(ChannelSub channelSub);
		
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
	 * 新增子频道
	 */
	public Result saveOrUpdateChannel(ChannelSubVO vo) throws BizException;
	
	/**
	 * 通过主频道的ID查询是否有子频道
	 */
	public int findByChannelMId(Long channelMId) throws BizException;
	
	/**
	 * 子频道列表
	 */
	public List<ChannelSubVO> listChannelSub(ChannelSubQuery query) throws BizException;
	
	/**
	 * 删除子频道
	 */
	public Result deleteByUUId(String uuid) throws BizException;
	
	/**
	 * 通过主频道ID查询子频道信息
	 */
	public abstract List<Map<String,Object>> selectListChannelSub(ChannelSub param);
	
	/**
	 * 根据主键-查询对象(包括已被删除的)
	 */
	ChannelSub selectByPrimaryKey(Long id);
	/**
	 * 通过名称查找对应频道
	 */
	public Long queryByName(String name);
	/**
	 * 通过角色权限获取主频道信息
	 */
	public abstract List<Map<String, Object>> selectListChannelMByRoles(CurrentUser user);
	/**
	 * 通过通过主频道ID+角色权限获取子频道信息
	 */
	public abstract List<Map<String, Object>> selectListChannelSubByRoles(ChannelSub param, String[] roles);

	/**
	 * 通过子频道名称查询子频道
	 * @return
	 */
	public  ChannelSub findByName(String string);
	
	/**
	 * 根据条件-分页查询
	 */
	List<Map<String, Object>> queryChannelSubList(Map<String, Object> map, SimplePageInfo pageInfo);
}
