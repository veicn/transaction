package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.EFractionMsgMapper;
import com.sinochem.crude.trade.info.domain.EFractionMsg;
import com.sinochem.crude.trade.info.service.EFractionMsgService;

@Service
public class EFractionMsgServiceImpl implements EFractionMsgService {
	@Autowired
	private EFractionMsgMapper eFractionMsgMapper;
	
	public EFractionMsgMapper getMapper(){
		return eFractionMsgMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(EFractionMsg efractionmsg){
		 return eFractionMsgMapper.insertRecord(efractionmsg);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return eFractionMsgMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(EFractionMsg  record){
    	return eFractionMsgMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(EFractionMsg eFractionMsg) throws BizException{
		if ( eFractionMsg.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return eFractionMsgMapper.updateRecordById(eFractionMsg);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(EFractionMsg eFractionMsg) throws BizException{
		if ( eFractionMsg.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return eFractionMsgMapper.updateRecordByUuid(eFractionMsg);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return eFractionMsgMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(EFractionMsg eFractionMsg){
		return eFractionMsgMapper.updateRecords(eFractionMsg.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public EFractionMsg findByPrimaryKey(Long id){
		return  eFractionMsgMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public EFractionMsg findByUuid(String uuid){
		return  eFractionMsgMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<EFractionMsg> queryByEntitys(EFractionMsg eFractionMsg){
		return  eFractionMsgMapper.queryByEntitys(eFractionMsg);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return eFractionMsgMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) eFractionMsgMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return eFractionMsgMapper.countRecords(map);
	}


	
	//**************************以下方法为开发者补充*********************************/

	//馏分信息查询
	@Override
	public Page<Map<String, Object>> queryeFractionMsg(String crudeNameE, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		Page<Map<String, Object>> mapListd = (Page<Map<String, Object>>) eFractionMsgMapper.queryeFractionMsg(crudeNameE);
		return mapListd;
	}
}
