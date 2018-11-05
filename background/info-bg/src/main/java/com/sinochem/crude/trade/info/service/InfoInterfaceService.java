package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.dao.InfoInterfaceMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.InfoInterface;
import com.sinochem.it.b2b.common.exception.BizException; 

public interface InfoInterfaceService {
	
	public abstract InfoInterfaceMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(InfoInterface infoInterface);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(InfoInterface  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(InfoInterface infoInterface) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(InfoInterface infoInterface);
	
	
	/**
	 * 根据主键-查询对象
	 */
	InfoInterface findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	InfoInterface findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<InfoInterface> queryByEntitys(InfoInterface infoInterface);
		
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
	 * 处理常规资讯接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processInfo(ExternalInteractive ei) throws Exception;
}
