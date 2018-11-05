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
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.domain.Fabulous;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.info.dao.FabulousMapper;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.service.FabulousService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class FabulousServiceImpl implements FabulousService {
	@Autowired
	private FabulousMapper fabulousMapper;
	
	@Autowired
	private InfoMapper infoMapper;
	
	public FabulousMapper getMapper(){
		return fabulousMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Fabulous fabulous){
		 return fabulousMapper.insertRecord(fabulous);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return fabulousMapper.deleteById(id);
	}
	
	/**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Fabulous  record){
    	return fabulousMapper.deleteRecords(record);
    }
	
    /**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Fabulous fabulous) throws BizException{
		if ( fabulous.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return fabulousMapper.updateRecordById(fabulous);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Fabulous fabulous) throws BizException{
		if ( fabulous.getUuid() == null) {
			throw new BizException("uuid为空，不能修改  ");
		}
		return fabulousMapper.updateRecordByUuid(fabulous);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return fabulousMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Fabulous fabulous){
		return fabulousMapper.updateRecords(fabulous.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Fabulous findByPrimaryKey(Long id){
		return  fabulousMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Fabulous findByUuid(String uuid){
		return  fabulousMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Fabulous> queryByEntitys(Fabulous fabulous){
		return  fabulousMapper.queryByEntitys(fabulous);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return fabulousMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) fabulousMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return fabulousMapper.countRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 
	 * 踩资讯、评论
	 * 
	 * 
	 */
	@Override
	public void treadInfo(FabulousVO vo, CurrentUser user) {
		//查询是否该用户在该咨询或评论下存在
		vo.setFabulousUserId(user.getMemberId().toString());
		Fabulous fabulous = fabulousMapper.queryIfTread(vo);
		Result res = new Result();
		Info in = infoMapper.selectInfoById(vo.getObjId());
		//添加用户信息
		if(fabulous == null){
			Fabulous fab = new Fabulous();
			fab.setUuid(KeyGenUtils.newUuid());
			fab.setObjTpye(vo.getObjTpye());
			fab.setObjOption("20");
			fab.setObjId(vo.getObjId());
			fab.setFabulousUserId(user.getMemberId().toString());
			fab.setIsPersonal("1");
			fab.setCreateUser(user.getName());
			fab.setCreateDate(DateTimeUtils.currentDate());
			fab.setUpdateUser(user.getName());
			fab.setUpdateDate(DateTimeUtils.currentDate());
			fab.setAliveFlag("1");
			if(fabulousMapper.insertRecord(fab) != 1){
				res.setFail("添加失败");
			}else{
				res.setMessage("新增成功");
			}
			//更新踩次数
			Info info = new Info();
			info.setId(vo.getObjId());
			if (in.getTrampleCount() == null || in.getTrampleCount() ==0) {
				info.setTrampleCount(1);
			}else{
				info.setTrampleCount(info.getTrampleCount()+1);
			}
			if(infoMapper.updateRecordById(info) != 1){
				res.setFail("更新失败");
			}else{
				res.setMessage("更新成功");
			}
		}else{
			//删除该用户信息、取消踩
			if(fabulousMapper.deleteById(fabulous.getId()) != 1){
				res.setFail("删除失败");
			}else{
				res.setMessage("删除成功");
			}
			//踩次数-1
			Info info = new Info();
			info.setId(vo.getObjId());
			info.setTrampleCount(in.getTrampleCount()-1);
			if(infoMapper.updateRecordById(info) != 1){
				res.setFail("更新失败");
			}else{
				res.setMessage("更新成功");
			}
		}
	}

	
	/**
	 * 
	 * 点赞资讯、评论
	 * 
	 */
	@Override
	public void praiseInfo(FabulousVO vo, CurrentUser user) {
		vo.setFabulousUserId(user.getMemberId().toString());
		Fabulous fabulous = fabulousMapper.queryIfTread(vo);
		Result res = new Result();
		Info in = infoMapper.selectInfoById(vo.getObjId());
		//添加用户在该资讯、评论下信息
		if(fabulous == null){
			Fabulous fab = new Fabulous();
			fab.setUuid(KeyGenUtils.newUuid());
			fab.setObjTpye(vo.getObjTpye());
			fab.setObjOption("10");
			fab.setObjId(vo.getObjId());
			fab.setFabulousUserId(user.getMemberId().toString());
			fab.setIsPersonal("1");
			fab.setCreateUser(user.getName());
			fab.setCreateDate(DateTimeUtils.currentDate());
			fab.setUpdateUser(user.getName());
			fab.setUpdateDate(DateTimeUtils.currentDate());
			fab.setAliveFlag("1");
			if(fabulousMapper.insertRecord(fab) != 1){
				res.setFail("添加失败");
			}else{
				res.setMessage("新增成功");
			}
			
			//更新点赞次数+1
			Info info = new Info();
			info.setId(vo.getObjId());
			if (in.getFabulousCount() == null || in.getFabulousCount() ==0) {
				info.setFabulousCount(1);
			}else{
				info.setFabulousCount(in.getFabulousCount()+1);
			}
			if(infoMapper.updateRecordById(info) != 1){
				res.setFail("更新失败");
			}else{
				res.setMessage("更新成功");
			}
		}else{
			
			//删除用户信息，取消点赞
			if(fabulousMapper.deleteById(fabulous.getId()) != 1){
				res.setFail("删除失败");
			}else{
				res.setMessage("删除成功");
			}
			
			//点赞次数-1
			Info info = new Info();
			info.setId(vo.getObjId());
			info.setFabulousCount(in.getFabulousCount()-1);
			if(infoMapper.updateRecordById(info) != 1){
				res.setFail("更新失败");
			}else{
				res.setMessage("更新成功");
			}
		}

	}
	
	
}
