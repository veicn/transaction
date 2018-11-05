package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.ChannelMMapper;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.model.ChannelMVO;
import com.sinochem.crude.trade.info.query.ChannelMQuery;

public interface ChannelMService {
	
	public abstract ChannelMMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(ChannelM channelM);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(ChannelM record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(ChannelM channelM) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(ChannelM channelM);
	
	
	/**
	 * 根据主键-查询对象
	 */
	ChannelM findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	ChannelM findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<ChannelM> queryByEntitys(ChannelM channelM);
		
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
	 * 新增或修改主频道
	 */
	public Result saveOrUpdateChannelM(ChannelMVO vo) throws BizException;

	/**
	 * 删除主频道
	 */
	public Result deleteChannelM(String UUId,String createUserId) throws BizException;
	
	/**
	 * 主频道列表
	 */
	public ChannelMQuery listChannelM(ChannelMQuery query) throws BizException;
	
	/**
	 * 加载主频道列表
	 */
	public  List<Map<String,Object>> selectListChannelM(ChannelM object);
	
	/**
	 * 根据主键-查询对象(包括已被删除的)
	 */
	ChannelM selectByPrimaryKey(Long id);
}
