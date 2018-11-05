package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.domain.CommentReply;
import com.sinochem.crude.trade.info.model.CommentReplyVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.CommentReplyMapper; 

public interface CommentReplyService {
	
	public abstract CommentReplyMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(CommentReply commentReply);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(CommentReply  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(CommentReply commentReply) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(CommentReply commentReply) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(CommentReply commentReply);
	
	
	/**
	 * 根据主键-查询对象
	 */
	CommentReply findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	CommentReply findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<CommentReply> queryByEntitys(CommentReply commentReply);
		
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

	public abstract int saveReplyAndUpdateReplayCount(CommentReplyVO vo, CurrentUser user);

	public abstract int delReplyAndUpdateReplayCount(CommentReplyVO vo, CurrentUser user);

	public abstract List<Map<String, Object>> queryByCommentId(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
}
