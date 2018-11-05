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
import com.github.pagehelper.PageInfo;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ChannelMMapper;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.model.ChannelMVO;
import com.sinochem.crude.trade.info.query.ChannelMQuery;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;

@Service
public class ChannelMServiceImpl implements ChannelMService {
	@Autowired
	private ChannelMMapper channelMMapper;
	@Autowired
	private ChannelSubService channelSubService;
	
	public ChannelMMapper getMapper(){
		return channelMMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(ChannelM channelm){
		 return channelMMapper.insertRecord(channelm);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return channelMMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ChannelM  record){
    	return channelMMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(ChannelM channelM) throws BizException{
		if ( channelM.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return channelMMapper.updateRecordById(channelM);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return channelMMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(ChannelM channelM){
		return channelMMapper.updateRecords(channelM.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ChannelM findByPrimaryKey(Long id){
		return  channelMMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public ChannelM findByUuid(String uuid){
		return  channelMMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<ChannelM> queryByEntitys(ChannelM channelM){
		return  channelMMapper.queryByEntitys(channelM);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return channelMMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) channelMMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return channelMMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 新增或修改主频道
	 * @param vo
	 * @return
	 * @throws BizException
	 */
	@Override
	public Result saveOrUpdateChannelM(ChannelMVO vo) throws BizException {
		Result res = new Result();
		res.setMessage("新增或修改主频道成功");
		ChannelM channelM = new ChannelM();
		BeanUtils.copyProperties(vo,channelM);
		Map<String,Object> map = new HashMap<>();
		map.put("channelMCode",vo.getChannelMCode());
		//查询主频道代码是否存在
		ChannelM cm = channelMMapper.findChannelMCountByCodeOrName(map);
		
		
		if(StringUtils.isBlank(vo.getUuid())){
			if(cm != null){
				res.setFail("主频道代码已存在");
				return res;
			}
			//新增
			channelM.setUuid(KeyGenUtils.newUuid());
			channelM.setCreateDate(DateTimeUtils.currentDate());
			channelM.setUpdateDate(DateTimeUtils.currentDate());
			channelM.setCreateUser(vo.getCreateUser());
			channelM.setChannelMDesc(vo.getChannelMDesc());
			channelM.setUpdateUser(vo.getUpdateUser());
			channelM.setAliveFlag(Constants.ALIEVE_FLAG);
			if(channelMMapper.insertRecord(channelM) != 1){
				res.setFail("新增主频道失败");
			}else{
				res.setMessage("新增主频道成功");
			}
		}else{
			if(cm != null && !cm.getUuid().equals(vo.getUuid())){
				res.setFail("主频道代码已存在,只能修改主频道名称");
				return res;
			}
			
			//修改
			ChannelM channelm = new ChannelM();
			channelm.setChannelMCode(vo.getChannelMCode());
			channelm.setChannelMName(vo.getChannelMName());
			channelm.setChannelMDesc(vo.getChannelMDesc());
			channelm.setChannelMOrder(vo.getChannelMOrder());
			channelm.setUpdateDate(DateTimeUtils.currentDate());
			channelm.setUpdateUser(vo.getUpdateUser());
			channelm.setUuid(vo.getUuid());
			channelm.setId(channelMMapper.findByUuid(vo.getUuid()).getId());
			if(channelMMapper.updateRecordById(channelm) != 1){
				res.setFail("修改主频道失败");
			}
		}
		return res;
	}

	/**
	 * 删除主频道
	 * @param UUId
	 * @return
	 * @throws BizException
	 */
	@Override
	public Result deleteChannelM(String UUId,String createUserId) throws BizException {
		Result res = new Result();
		res.setMessage("删除成功");
		ChannelM channelM1 = channelMMapper.findByUuid(UUId);
		channelM1.setAliveFlag("0");
		//TODO 若有子节点(子频道) 不可以删除
		int count = channelSubService.findByChannelMId(channelM1.getId());
		if(count > 0){
			res.setFail("该频道下有子频道无法删除");
			return res;
		}
		if(channelMMapper.deleteChannelMByUUId(channelM1) != 1){
			res.setFail("删除失败");
		}
		return res;
	}
	
	/**
	 * 主频道列表
	 * @param query
	 * @return
	 * @throws BizException
	 */
	@Override
	public ChannelMQuery listChannelM(ChannelMQuery query) throws BizException {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		ChannelM param = new ChannelM();
		param.setChannelMName(query.getChannelMName());
		List<ChannelM> list = channelMMapper.listChannelM(param);
		//pageinfo
		List<ChannelMVO> vos = new ArrayList<ChannelMVO>();
		for(ChannelM item : list){
			ChannelMVO vo = new ChannelMVO();
			BeanUtils.copyProperties(item, vo);
			vos.add(vo);
		}
		PageInfo page = new PageInfo(vos);
		query.setList(page.getList());
		query.setTotalItem(page.getTotal());
		query.setCurrentPage(page.getPageNum());
		query.setPageSize(page.getPageSize());
		return query;
	}
	
	/**
	 * 加载主频道列表
	 */
	@Override
	public List<Map<String,Object>> selectListChannelM(ChannelM channelM) {
		return channelMMapper.selectListChannelM(channelM);
	}
	
	/**
	 * 根据主键-查询对象(包括已被删除的)
	 */
	@Override
	public ChannelM selectByPrimaryKey(Long id){
		return  channelMMapper.selectByPrimaryKey(id);
	}

}
