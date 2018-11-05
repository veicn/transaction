package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.ChannelM;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ChannelMMapper {

	public int insertRecord(ChannelM entity);
	
	public int deleteById(@Param("id") Long id);
	
	public int deleteRecords(ChannelM entity);
	
	public int updateRecordById(ChannelM entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public ChannelM findByPrimaryKey(Long id);
	
	public ChannelM findByUuid(String uuid);	
	
	//返回对象的List
	public List<ChannelM> queryByEntitys(ChannelM entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 通过频道代码或名称查询是否存在数据重复
	 * @param param
	 * @return
	 */
	public ChannelM findChannelMCountByCodeOrName(Map<String,Object> param);

	/**
	 * 删除主频道
	 */
	public int deleteChannelMByUUId(ChannelM channelM);
	
	/**
	 * 查询列表主频道
	 */
	public List<ChannelM> listChannelM(ChannelM entity);
	
	/**
	 * 通过uuid 更新主频道名称
	 */
	public int updateChannelMNameByUUId(ChannelM channelm);
	
	/**
	 * 加载下拉列表
	 */
	//返回对象的List
	public List<Map<String,Object>> selectListChannelM(ChannelM entity);
	
	/**
	 * 根据主键查询信息(包括已被删除的)
	 */
	public ChannelM selectByPrimaryKey(Long id);
}
