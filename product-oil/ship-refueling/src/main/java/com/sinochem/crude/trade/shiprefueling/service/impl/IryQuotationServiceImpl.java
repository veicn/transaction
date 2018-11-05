package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.member.remote.service.PersonService;
import com.sinochem.crude.trade.member.remote.vo.PersonVo;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.IryQuotationMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.enums.InquiryQuotationStatusEnums;
import com.sinochem.crude.trade.shiprefueling.model.vo.IryQuotationVO;
import com.sinochem.crude.trade.shiprefueling.service.IryQuotationService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class IryQuotationServiceImpl implements IryQuotationService {
	@Autowired
	private IryQuotationMapper iryQuotationMapper;
	@Autowired
	private PersonService personService;
	
	public IryQuotationMapper getMapper(){
		return iryQuotationMapper;
	} 
	
	/**
	 * 新增  -武刚鹏修改-2018年5月28日17:43:19
	 */
	@Override
	public int insertRecord(IryQuotation iryquotation){
		if(iryquotation != null && iryquotation.getUuid() == null){
			iryquotation.setUuid(KeyGenUtils.newUuid());
		}
		if(iryquotation.getCreateDate() == null){
			iryquotation.setCreateDate(new Date());
		}
		 return iryQuotationMapper.insertRecord(iryquotation);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long inquiryQuotationId) throws BizException{
		if (inquiryQuotationId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return iryQuotationMapper.deleteById(inquiryQuotationId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(IryQuotation  record){
    	return iryQuotationMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(IryQuotation iryQuotation) throws BizException{
		if ( iryQuotation.getInquiryQuotationId() == null  ) {
			throw new BizException("inquiryQuotationId 为空，不能修改 ");
		}
		return iryQuotationMapper.updateRecordById(iryQuotation);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(IryQuotation iryQuotation) throws BizException {
		if ( iryQuotation.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return iryQuotationMapper.updateRecordByUuid(iryQuotation);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return iryQuotationMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(IryQuotation iryQuotation){
		return iryQuotationMapper.updateRecords(iryQuotation.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public IryQuotation findByPrimaryKey(Long inquiryQuotationId){
		return  iryQuotationMapper.findByPrimaryKey(inquiryQuotationId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public IryQuotation findByUuid(String uuid){
		return  iryQuotationMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<IryQuotation> queryByEntitys(IryQuotation iryQuotation){
		return  iryQuotationMapper.queryByEntitys(iryQuotation);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return iryQuotationMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return iryQuotationMapper.countRecords(map);
	}



	//**************************以下方法为开发者补充*********************************/
	

	public int insertIryQuotationRecord(IryQuotationVO vo) throws BizException {
		vo.setUuid(KeyGenUtils.newUuid());
		vo.setCreateDate(new Date());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setStatus(Constants.QUOTATION_STATUS_NOT_CONNECTED);
		return iryQuotationMapper.insertRecord(vo);
	}

	@Override
	public Page<Map<String, Object>> queryIryQuotationList(
			Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "createDate desc");
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) iryQuotationMapper.queryiryQuotationRecords(map);
		//查询用户账户信息
		if(page != null && page.size() > 0){
			for(Map<String , Object> pageMap : page){
				Long createUser = (Long) pageMap.get("createUser");
				PersonVo personVo = personService.getByMemberId(createUser);
				pageMap.put("userName" , "");
				if(personVo != null && StringUtils.isNotBlank(personVo.getUserName())){
					pageMap.put("userName" , personVo.getUserName());
				}
				// 状态
				final  Object status = pageMap.get("status");
				if( status != null && StringUtils.isNotBlank(status.toString())){
					InquiryQuotationStatusEnums enums = InquiryQuotationStatusEnums.get(status.toString());
					if(enums != null){
						pageMap.put("status" , enums.getZhName());
					}
				}
			}
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> queryListByNeedUuid(
			Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) iryQuotationMapper.queryRecords(map);
	}

	/**
	 * 根据needUuid删除
	 * @param uuid
	 */
	@Override
	public void updateRecordByNeedUUid(String uuid  ,  String type ) {
		IryQuotation iryQuotation = new IryQuotation();
		iryQuotation.setNeedUuid(uuid);
		iryQuotation.setType(type);
		iryQuotation.setAliveFlag(Constants.DELE_FLAG);
		iryQuotationMapper.updateRecordByNeedUuid(iryQuotation);
	}
}
