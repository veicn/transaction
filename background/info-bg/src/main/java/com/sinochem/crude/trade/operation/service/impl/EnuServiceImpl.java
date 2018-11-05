package com.sinochem.crude.trade.operation.service.impl;

import java.util.Map;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.domain.Enu;
import com.sinochem.crude.trade.operation.dao.EnuMapper;
import com.sinochem.crude.trade.operation.service.EnuService;

@Service
public class EnuServiceImpl implements EnuService {
	@Autowired
	private EnuMapper enuMapper;
	
	public EnuMapper getMapper(){
		return enuMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Enu enu){
		enu.setMenuId(KeyGenUtils.newUuid());
		enu.setAliveFlag(Constants.ALIEVE_FLAG);
		enu.setCreateDate(DateTimeUtils.currentDate());
		return enuMapper.insertRecord(enu);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Enu  record){
    	return enuMapper.deleteRecords(record);
    }
	
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return enuMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Enu enu){
		return enuMapper.updateRecords(enu.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Enu findByPrimaryKey(){
		return  enuMapper.findByPrimaryKey();	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Enu findByUuid(String uuid){
		return  enuMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Enu> queryByEntitys(Enu enu){
		return  enuMapper.queryByEntitys(enu);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return enuMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) enuMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return enuMapper.countRecords(map);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByMenuId(Enu enu) throws BizException{
		if ( enu.getMenuId() == null) {
			throw new BizException("menuId为空，不能修改 ");
		}
		enu.setUpdateDate(DateTimeUtils.currentDate());
		return enuMapper.updateRecordByMenuId(enu);
	}
	@Override
	public Result saveOrUpdateMenu(Enu enu, CurrentUser user) {
		Result res=new Result();
		if(StringUtils.isBlank(enu.getMenuId())) {
			//新增
			enu.setCreateUser(user.getName());
			if(this.insertRecord(enu) == 1){
				res.setMessage("菜单新增成功");
			}
			return res;
		}else {
			enu.setUpdateUser(user.getName());
			if(this.updateRecordByMenuId(enu) == 1){
				res.setMessage("菜单修改成功");
			}
			return res;
		}
	}

	@Override
	public int tpMenuDelete(String menuId) {
		return enuMapper.tpMenuDelete(menuId);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
