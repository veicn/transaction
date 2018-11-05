package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.domain.Share;
import com.sinochem.crude.trade.info.dao.ShareMapper;
import com.sinochem.crude.trade.info.model.ShareVO;
import com.sinochem.crude.trade.member.user.CurrentUser;

public interface ShareService {
	
	public abstract ShareMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Share share);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Share record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Share share) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Share share);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Share findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Share findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Share> queryByEntitys(Share share);
		
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
	 * 资讯分享
	 */
	public Result shareInfo(String infoUUId,String sharePlatform,CurrentUser user) throws BizException;

	/**
	 * 分享资讯列表
	 */

	public ResultDatas<List<ShareVO>> queryList(ShareVO vo) throws BizException;
}
