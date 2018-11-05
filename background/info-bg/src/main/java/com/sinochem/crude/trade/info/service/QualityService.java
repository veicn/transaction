package com.sinochem.crude.trade.info.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.domain.Quality;
import com.sinochem.crude.trade.info.model.CrudeVO;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.query.ChannelSubQuery;
import com.sinochem.crude.trade.info.query.CrudeQuery;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.QualityMapper; 

public interface QualityService {
	
	public abstract QualityMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Quality quality);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Quality  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Quality quality) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Quality quality);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Quality findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Quality findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Quality> queryByEntitys(Quality quality);
		
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
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> crudeRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-逻辑删除
	 */
	int crudeDelete(String uuid);
	
	/**
	 * 原油新增
	 */
	int crudeInsert(CrudeVO vo);
	
	/**
	 * 修改对象
	 */
	int crudeUpdate(CrudeVO vo) throws BizException;
	/**
	 * 批量导入
	 */
	public abstract ResultDatas<Void> crudeOilImport(InputStream inputStream, CurrentUser user);

	public abstract void exportList(HttpServletResponse response);
	
	/**
	 * 外部查询接口
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryQuality(PIMSVo vo);
	
	
	
	
}
