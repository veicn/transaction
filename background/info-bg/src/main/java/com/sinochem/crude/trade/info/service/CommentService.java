package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.CommentMapper;
import com.sinochem.crude.trade.info.domain.Comment;
import com.sinochem.crude.trade.info.model.CommentVO;
import com.sinochem.crude.trade.info.query.CommentQuery;
import com.sinochem.crude.trade.member.user.CurrentUser;

public interface CommentService {
	
	public abstract CommentMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Comment comment);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Comment record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Comment comment) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Comment comment);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Comment findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Comment findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Comment> queryByEntitys(Comment comment);
		
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
	 * 新增资讯评论
	 */
	public int commentInfoAndUpdateInfoCount(CommentVO comment,CurrentUser user) throws BizException;

	/**
	 * 删除资讯评论
	 */
	public int delCommentInfoAndUpdateInfoCount(CommentVO vo,CurrentUser user) throws BizException;

	public abstract Page<Map<String, Object>> queryCommentList(Map<String, Object> beanToMap,SimplePageInfo pageInfo );

	public abstract int deleteCommentByUuid(String uuid);

	public abstract Page<Map<String, Object>> queryMyCommentInfo(Map<String, Object> map,
			SimplePageInfo pageInfo);
}
