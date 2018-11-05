package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.Info;

//@Mapper
public interface InfoMapper {

	public int insertRecord(Info entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Info entity);
	
	public int updateRecordById(Info entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Info findByPrimaryKey( Long id);	
	
	public Info findByUuid(String uuid);	
	
	//返回对象的List
	public List<Info> queryByEntitys(Info entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 更新资讯表中分享,收藏,评论条数
	 */
	public int updateInfoCount(Info info);
	/**
	 * 公告列表
	 */
	public List<Info> listNotice(Info info);
	
	/**
	 * 公告状态更新
	 */
	public int updateNoticeStatus(Info info);
	/**
	 * 更新资讯
	 * @param info
	 * @return
	 */
	public int updateInfoTitle(Info info);
	/**
	 * 删除资讯
	 * @return
	 */
	public int deleteByInfo(Info info);
	
	/**
	 * 批量审核通过
	 * 
	 * @param map
	 * @return
	 */
	public int updateRecordsBatch(Map<String, Object> map);

	/**
	 * 查询待审核/已发布列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> ListInfo(Map<String, String> map);
	
	/**
	 * 查询待审核/已发布条数
	 * 
	 * @param map
	 * @return
	 */
	public int countSelective(Map<String, Object> map);
	
	/**
	 * 更新资讯
	 * @param info
	 */
	public void updatInfoByUUId(Info info);

	/**
	 * 查询未发布列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> ListUnreleased(Map<String, Object> map);

	/**
	 * 资讯置顶
	 * @param info
	 * @return
	 */
	public int settingInfoTopByUUId(Info info);
	
	/**
	 * 查询同一频道下置顶的资讯
	 * @return
	 */
	public Info findStickInfo(Long channelId);

	/**
	 * 查询我的资讯列表
	 */
	public List<Map<String, Object>> myInfoList(Map<String, String> map);
	
	/**
	 * 根据uuid查询记录（包括栏目内容）
	 */
	public Map<String, Object> selectByUuid(String uuid);
	
	/**
	 * 查询前台列表
	 */
	public List<Map<String, Object>> getFrontList(Map<String, String> map);
	
	/**
	 * 查询前一篇
	 */
	public Map<String, Object> getPreInfo(Map map);
	
	/**
	 * 查询后一篇
	 */
	public Map<String, Object> getNextInfo(Map map);
	
	int updateBrowseCount(Long infoId);

	public Integer findMaxInfoGrabId(String typ);
}
