package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.ChannelSub;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ChannelSubMapper {

	public int insertRecord(ChannelSub entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(ChannelSub entity);
	
	public int updateRecordById(ChannelSub entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public ChannelSub findByPrimaryKey( Long id);	
	
	public ChannelSub findByUuid(String uuid);	
	
	//返回对象的List
	public List<ChannelSub> queryByEntitys(ChannelSub entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	

	/**
	 * 通过子频道代码和名称查询子频道
	 */
	public int findChannelByCodeAndName(Map<String,Object> map);
	
	/**
	 * 通过子频道代码或名称查询子频道
	 */
	public ChannelSub findChannelByCodeOrName(Map<String,Object> map);

	/**
	 * 修改子频道
	 */
	public int updateChannelCodeAndName(ChannelSub channel);
	
	/**
	 * 删除子频道
	 */
	public int updateChannelSubByUUId(String uuid);
	/**
	 * 分页查找子频道列表
	 * @return
	 */
	public List<ChannelSub> listChannelSub(ChannelSub entity);
	
	/**
	 * 通过主频道id查询子频道信息
	 */
	public List<Map<String,Object>> selectListChannelSub(ChannelSub entity);
	
	/**
	 * 根据主键查询记录(包括已被删除的)
	 */
	public ChannelSub selectByPrimaryKey( Long id);
	/**
	 * 通过角色权限获取子频道id
	 */
	public List<Integer> selectListChannelMByRoles(@Param("roles")String[] roles);
	/**
	 * 通过子频道id获取主频道信息
	 */
	public List<Map<String, Object>> selectChannelmByChannelIds(List<Integer> channelIdList);

	public List<Map<String, Object>> selectListChannelSubByRoles(@Param("param") ChannelSub param,@Param("roles") String[] roles);

	public Page<Map<String, Object>> queryLikeByName(Map<String, Object> map);
}
