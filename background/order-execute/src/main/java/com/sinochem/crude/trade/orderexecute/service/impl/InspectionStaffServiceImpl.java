package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.orderexecute.dao.InspectionStaffMapper;
import com.sinochem.crude.trade.orderexecute.domain.InspectionStaff;
import com.sinochem.crude.trade.orderexecute.service.InspectionStaffService;

@Service
public class InspectionStaffServiceImpl implements InspectionStaffService {
	@Autowired
	private InspectionStaffMapper inspectionStaffMapper;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	public InspectionStaffMapper getMapper(){
		return inspectionStaffMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InspectionStaff inspectionstaff){
		EnterpriseVo  enterprise = enterpriseService.getByMemberId(inspectionstaff.getEpMemberId());
		inspectionstaff.setEpMemberName(enterprise.getName());
		return inspectionStaffMapper.insertRecord(inspectionstaff);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return inspectionStaffMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InspectionStaff  record){
    	return inspectionStaffMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InspectionStaff inspectionStaff) throws BizException{
		if ( inspectionStaff.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return inspectionStaffMapper.updateRecordById(inspectionStaff);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return inspectionStaffMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InspectionStaff inspectionStaff){
		return inspectionStaffMapper.updateRecords(inspectionStaff.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InspectionStaff findByPrimaryKey(Long id){
		return  inspectionStaffMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InspectionStaff findByUuid(String uuid){
		return  inspectionStaffMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InspectionStaff> queryByEntitys(InspectionStaff inspectionStaff){
		return  inspectionStaffMapper.queryByEntitys(inspectionStaff);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return inspectionStaffMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) inspectionStaffMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return inspectionStaffMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
