package com.sinochem.crude.trade.info.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.domain.Comment;
import com.sinochem.crude.trade.info.domain.CommentReply;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.CommentReplyVO;
import com.sinochem.crude.trade.info.model.CommentVO;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.CommentReplyMapper;
import com.sinochem.crude.trade.info.service.CommentReplyService;
import com.sinochem.crude.trade.info.service.CommentService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class CommentReplyServiceImpl implements CommentReplyService {
	@Autowired
	private CommentReplyMapper commentReplyMapper;
	
	@Autowired
	private CommentService commentService;
	
	public CommentReplyMapper getMapper(){
		return commentReplyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(CommentReply commentreply){
		 return commentReplyMapper.insertRecord(commentreply);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return commentReplyMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(CommentReply  record){
    	return commentReplyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(CommentReply commentReply) throws BizException{
		if ( commentReply.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return commentReplyMapper.updateRecordById(commentReply);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(CommentReply commentReply) throws BizException{
		if ( commentReply.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return commentReplyMapper.updateRecordByUuid(commentReply);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return commentReplyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(CommentReply commentReply){
		return commentReplyMapper.updateRecords(commentReply.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public CommentReply findByPrimaryKey(Long id){
		return  commentReplyMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public CommentReply findByUuid(String uuid){
		return  commentReplyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<CommentReply> queryByEntitys(CommentReply commentReply){
		return  commentReplyMapper.queryByEntitys(commentReply);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return commentReplyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) commentReplyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return commentReplyMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	@Override
	public int saveReplyAndUpdateReplayCount(CommentReplyVO vo, CurrentUser user) {
		CommentReply commentReply=new CommentReply();
		BeanUtils.copyProperties(vo,commentReply);
		commentReply.setReplyUserId(user.getMemberId()+"");
		commentReply.setCreateUser(user.getName());
		commentReply.setCreateDate(DateTimeUtils.currentDate());
		commentReply.setAliveFlag(Constants.ALIEVE_FLAG);
		commentReply.setUuid(KeyGenUtils.newUuid());
		int record = this.insertRecord(commentReply);
		Comment comment = commentService.findByPrimaryKey(commentReply.getCommentId());
		if(comment.getReplyCount() == null || comment.getReplyCount() == 0){
			comment.setReplyCount(1);
		}else{
			comment.setReplyCount(comment.getReplyCount() + 1);
		}
		int up=commentService.updateRecordById(comment);
		return record+up;
	}
	/**
	 * 删除回复
	 * @param vo
	 * @return
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delReplyAndUpdateReplayCount(CommentReplyVO vo, CurrentUser user) {
		CommentReply commentReply = commentReplyMapper.findByUuid(vo.getUuid());
		if(commentReply!=null){
			commentReply.setAliveFlag("0");
			commentReply.setUuid(vo.getUuid());
			commentReply.setUpdateDate(DateTimeUtils.currentDate());
			commentReply.setUpdateUser(user.getName());
			int r1 = commentReplyMapper.updateRecordById(commentReply);
			
			Comment comment = commentService.findByPrimaryKey(commentReply.getCommentId());
			if(comment.getReplyCount() == null || comment.getReplyCount() == 0){
				comment.setReplyCount(0);
			}else{
				comment.setReplyCount(comment.getReplyCount() - 1);
			}
			int up=commentService.updateRecordById(comment);
			return r1 + up;
		}else{
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> queryByCommentId(Map<String, Object> map) {
		return commentReplyMapper.queryByCommentId(map);
	}
	
}
