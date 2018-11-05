package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.InfoVO;
import com.sinochem.crude.trade.member.user.CurrentUser; 

public interface InfoService {
	
	public abstract InfoMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Info info);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Info record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Info info) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Info info);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Info findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Info findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Info> queryByEntitys(Info info);
		
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
	 * 更新评论、收藏、分享 - 数
	 * @param info
	 */
	int updateInfoCount(Info info ,CurrentUser user);
	
	/**
	 * 公告列表
	 */
	public List<Info> listNotice(Info info);
	
	/**
	 * 发布公告
	 */
	int saveOrUpdateNotice(Info info,CurrentUser user);
	
	/**
	 * 撤销公告
	 */
	int revokeNotice(String uuid,CurrentUser user);
	
	/**
	 * 历史公告发布
	 */
	int pushNotice(String uuid,CurrentUser user);
	
	/**
	 * 删除历史公告
	 */
	int deleteByInfo(Info info);
	
	/**
	 * 保存或编辑资讯信息
	 * @param user 
	 */
	public  Result saveOrUpdateInfo(InfoVO vo, CurrentUser user);
	
	/**
	 * 资讯预览
	 */
	public  InfoVO findPreviewInfo(String uuid);
	
	/**
	 * 跳转到编辑页
	 */
	public InfoVO findInfoToEdit(String uuid);

	/**
	 * 查询未发布资讯列表
	 */
	public List<Map<String, Object>> ListUnreleased(Map<String, Object> map);
	
	/**
	 * 资讯置顶
	 */
	public boolean settingInfoTopByUUId(Info info);
	
	/**
	 * 根据uuid查询对象(包括栏目内容)
	 */
	public Map<String, Object> selectByUuid(String uuid);
	
	public Long getIdByUuid(String uuid);
	
	/**
	 * 查询前台列表-不分页
	 */
	public List<Map<String, Object>> getFrontList(Map<String, Object> map);
	
	/**
	 * 查询前台列表-分页
	 */
	public Page<Map<String, Object>> getFrontListPage(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 查询前一篇
	 */
	public Map<String, Object> getPreInfo(Map map);
	
	/**
	 * 查询后一篇
	 */
	public Map<String, Object> getNextInfo(Map map);
	
	
	/**
	 * 测试取数据
	 */
	public void getRemoteData();
	
	int updateBrowseCount(Long infoId);
	/**
	 * 精选或取消精选
	 * @return
	 */
	public  boolean selectedByUuid(Info info);
	
	/**
	 * 发布资讯
	 */
	public boolean releaseInfo(Long id);
	
	/**
	 * 删除资讯
	 * @param uuid
	 * @return
	 */
	public  boolean delete(Info info);

	public abstract Integer findMaxInfoGrabId(String typ);

	public abstract List<Map<String, Object>> findByBizId(Info info);

	public abstract Page<Map<String, Object>> queryInfoListByBizId(Map<String, Object> beanToMap,
			SimplePageInfo pageInfo);
}
