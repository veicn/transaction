package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.InfoInterfaceMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.InfoInterface;
import com.sinochem.crude.trade.info.service.InfoInterfaceService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class InfoInterfaceServiceImpl implements InfoInterfaceService {
	@Autowired
	private InfoInterfaceMapper infoInterfaceMapper;
	
	private static final Log log = LogFactory.getLog(InfoInterfaceServiceImpl.class);
	
	public InfoInterfaceMapper getMapper(){
		return infoInterfaceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InfoInterface infointerface){
		 return infoInterfaceMapper.insertRecord(infointerface);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoInterfaceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InfoInterface  record){
    	return infoInterfaceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InfoInterface infoInterface) throws BizException{
		if ( infoInterface.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoInterfaceMapper.updateRecordById(infoInterface);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoInterfaceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InfoInterface infoInterface){
		return infoInterfaceMapper.updateRecords(infoInterface.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InfoInterface findByPrimaryKey(Long id){
		return  infoInterfaceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InfoInterface findByUuid(String uuid){
		return  infoInterfaceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InfoInterface> queryByEntitys(InfoInterface infoInterface){
		return  infoInterfaceMapper.queryByEntitys(infoInterface);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoInterfaceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 处理常规资讯接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processInfo(ExternalInteractive ei) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(ei.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		String externalId = (String)map.get("id");		
		@SuppressWarnings("unchecked")
		List<String> tags = (List<String>)map.get("infoTag");
		StringBuilder infoTag = new StringBuilder();
		for(int i=0; i<tags.size(); i++ ){
			if(i==tags.size()-1)
				infoTag.append(tags.get(i));
			else{
				infoTag.append(tags.get(i));
				infoTag.append(",");
			}
		}
		
		InfoInterface infoInterface = BeanConvertUtils.mapToBean(map, InfoInterface.class);
		infoInterface.setExternalId(externalId);
		infoInterface.setInfoTag(infoTag.toString());
		infoInterface.setAuthor((String)map.get("infoAuthor"));
		infoInterface.setEditTime(DateUtil.strToDate((String)map.get("editDate"), "yyyy-MM-dd HH:mm:ss"));
		infoInterface.setExternalSystem(ei.getSysCode());
		infoInterface.setInterfaceDate(new Date());
		infoInterface.setStatus(Constants.STATUS_00);
		infoInterface.setAliveFlag(Constants.ALIVE_FLAG_1);
		
		InfoInterface param = new InfoInterface();
		param.setExternalId(infoInterface.getExternalId());
		param.setExternalSystem(infoInterface.getExternalSystem());
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param))){
			infoInterface.setId(this.queryByEntitys(param).get(0).getId());
			this.updateRecordById(infoInterface);
			log.info("更新记录");
		}else{
			infoInterface.setId(null);
			infoInterface.setUuid(UUID.randomUUID().toString().replace("-", ""));
			this.insertRecord(infoInterface);
			log.info("新增记录");
		}
		
	}
}
