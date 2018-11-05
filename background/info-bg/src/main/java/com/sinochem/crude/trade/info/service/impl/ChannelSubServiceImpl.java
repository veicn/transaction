package com.sinochem.crude.trade.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ChannelSubMapper;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.model.ChannelSubVO;
import com.sinochem.crude.trade.info.query.ChannelSubQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class ChannelSubServiceImpl implements ChannelSubService {
	@Autowired
	private ChannelSubMapper channelSubMapper;
	
	@Autowired
	private ChannelMService channelMService;
	
	public ChannelSubMapper getMapper(){
		return channelSubMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(ChannelSub channelsub){
		 return channelSubMapper.insertRecord(channelsub);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return channelSubMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ChannelSub  record){
    	return channelSubMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(ChannelSub channelSub) throws BizException{
		if ( channelSub.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return channelSubMapper.updateRecordById(channelSub);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return channelSubMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(ChannelSub channelSub){
		return channelSubMapper.updateRecords(channelSub.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ChannelSub findByPrimaryKey(Long id){
		return  channelSubMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public ChannelSub findByUuid(String uuid){
		return  channelSubMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<ChannelSub> queryByEntitys(ChannelSub channelSub){
		return  channelSubMapper.queryByEntitys(channelSub);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return channelSubMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) channelSubMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return channelSubMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 新增子频道
	 * @param vo
	 * @return
	 * @throws BizException
	 */
	@Override
	public Result saveOrUpdateChannel(ChannelSubVO vo) throws BizException {
		Result res = new Result();
		res.setMessage("新增子频道成功");
		ChannelSub channel = new ChannelSub();
		BeanUtils.copyProperties(vo,channel);
		Map<String,Object> map = new HashMap<>();
		map.put("channelCode",channel.getChannelCode());
		ChannelSub csb = channelSubMapper.findChannelByCodeOrName(map);
		
		ChannelM channelM = channelMService.findByUuid(vo.getChannelMUuid()); 
		if(channelM==null){
			res.setFail("未找到对应的主频道");
			return res;
		}
			
		
		if(StringUtils.isBlank(channel.getUuid())){
			if(csb != null){
				res.setFail("子频道代码已存在");
				return res;
			}
			//新增
			channel.setChannelMId(channelM.getId());
			channel.setUuid(KeyGenUtils.newUuid());
			channel.setCreateDate(DateTimeUtils.currentDate());
			channel.setUpdateDate(DateTimeUtils.currentDate());
			channel.setAliveFlag(Constants.ALIEVE_FLAG);
			channel.setCreateUser("1");
			channel.setUpdateUser("1");
			if(channelSubMapper.insertRecord(channel) != 1){
				res.setFail("新增子频道失败");
			}
		}else{
			if(csb != null && !vo.getUuid().equals(csb.getUuid())){
				res.setMessage("子频道代码已存在");
				return res;
			}else{
				res.setMessage("子频道更新成功");
			}
			//更新 ，拿到用户 信息
			channel.setChannelMId(channelM.getId());
			channel.setCreateUser("1");
			channel.setUpdateUser("1");
			channel.setUpdateDate(DateTimeUtils.currentDate());
			channel.setId(this.findByUuid(vo.getUuid()).getId());
			if(channelSubMapper.updateRecordById(channel) != 1){
				res.setFail("子频道更新失败");
			}
		}
		return res;
	}

	/**
	 * 主频道下是否有子频道存在
	 */
	@Override
	public int findByChannelMId(Long channelMId) throws BizException {
		ChannelSub sb = new ChannelSub();
		sb.setChannelMId(channelMId);
		return channelSubMapper.queryByEntitys(sb).size();
	}
	/**
	 * 子频道列表
	 */
	@Override
	public List<ChannelSubVO> listChannelSub(ChannelSubQuery query) throws BizException {
		PageHelper.startPage(query.getCurrentPage(), 20);
		ChannelSub entity = new ChannelSub();
		entity.setChannelName(query.getChannelName());
		List<ChannelSub> list = channelSubMapper.listChannelSub(entity);
		List<ChannelSubVO> vos = new ArrayList<>();
		for(ChannelSub item : list){
			ChannelSubVO vo = new ChannelSubVO();
			BeanUtils.copyProperties(item, vo);
			vos.add(vo);
		}
		return vos;
	}
	
	/**
	 * 删除子频道
	 */
	@Override
	public Result deleteByUUId(String uuid) throws BizException {
		Result res = new Result();
		res.setMessage("删除成功");
		if(channelSubMapper.updateChannelSubByUUId(uuid) != 1){
			res.setFail("删除失败");
		}
		return res;
	}

	/**
	 * 通过主频道ID查询子频道信息
	 */
	@Override
	public List<Map<String,Object>> selectListChannelSub(ChannelSub param) {
		return channelSubMapper.selectListChannelSub(param);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ChannelSub selectByPrimaryKey(Long id){
		return  channelSubMapper.selectByPrimaryKey(id);	
	}
	/**
	 * 通过名称查找对应ID
	 */
	@Override
	public Long queryByName(String name) {
		ChannelSub sub = new ChannelSub();
		sub.setChannelName(name);
		return channelSubMapper.queryByEntitys(sub).get(0).getId();
	}
	/**
	 * 通过角色权限获取主频道信息
	 */
	@Override
	public List<Map<String, Object>> selectListChannelMByRoles(CurrentUser user) {
		String[] roles = user.getRoles();
		List<Integer> channelIdList = channelSubMapper.selectListChannelMByRoles(roles);
		if(channelIdList!=null && channelIdList.size()>0){
			return channelSubMapper.selectChannelmByChannelIds(channelIdList);
		}else{
			return null;
		}
	}
	/**
	 * 通过通过主频道ID+角色权限获取子频道信息
	 */
	@Override
	public List<Map<String,Object>> selectListChannelSubByRoles(ChannelSub param,String[] roles) {
		return channelSubMapper.selectListChannelSubByRoles(param,roles);
	}
	
	/**
	 * 通过子频道名称查询子频道
	 */
	@Override
	public ChannelSub findByName(String channelCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("channelCode", channelCode);
		ChannelSub sub = channelSubMapper.findChannelByCodeOrName(map);
		return sub;
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryChannelSubList(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) channelSubMapper.queryChannelSubList(map);
	}
}
