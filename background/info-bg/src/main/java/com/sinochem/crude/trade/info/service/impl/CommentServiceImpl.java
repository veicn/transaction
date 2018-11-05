package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.CommentMapper;
import com.sinochem.crude.trade.info.domain.Comment;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.CommentVO;
import com.sinochem.crude.trade.info.query.CommentQuery;
import com.sinochem.crude.trade.info.service.CommentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private InfoService infoService;
	
	public CommentMapper getMapper(){
		return commentMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Comment comment){
		 return commentMapper.insertRecord(comment);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return commentMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Comment  record){
    	return commentMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Comment comment) throws BizException{
		if ( comment.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return commentMapper.updateRecordById(comment);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return commentMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Comment comment){
		return commentMapper.updateRecords(comment.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Comment findByPrimaryKey(Long id){
		return  commentMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Comment findByUuid(String uuid){
		return  commentMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Comment> queryByEntitys(Comment comment){
		return  commentMapper.queryByEntitys(comment);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return commentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) commentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return commentMapper.countRecords(map);
	}



	//**************************以下方法为开发者补充*********************************/

	/**
	 * 新增资讯评论
	 * @param vo
	 * @return
	 * @throws BizException
	 */
	@Override
	public int commentInfoAndUpdateInfoCount(CommentVO vo,CurrentUser user) throws BizException {
		Comment comment = new Comment();
		System.out.println("1");
		BeanUtils.copyProperties(vo,comment);
		System.out.println("2");
		comment.setCreateUser(user.getName());
		comment.setCreateDate(DateTimeUtils.currentDate());
		comment.setUpdateDate(DateTimeUtils.currentDate());
		comment.setAliveFlag(Constants.ALIEVE_FLAG);
		comment.setUuid(KeyGenUtils.newUuid());
		comment.setCommentUserId(user.getMemberId()+"");
		int comm = commentMapper.insertRecord(comment);
		System.out.println("3");
		//资讯表info中更新评论数
		Info info = infoService.findByPrimaryKey(comment.getInformationId());
		if(info.getCommentCount() == null || info.getCommentCount() == 0){
			info.setCommentCount(1);
		}else{
			info.setCommentCount(info.getCommentCount() + 1);
		}
		int update = infoService.updateInfoCount(info,user);
		return comm + update;
	}

	/**
	 * 删除资讯评论
	 * @param vo
	 * @return
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delCommentInfoAndUpdateInfoCount(CommentVO vo,CurrentUser user) throws BizException {
		Comment comment = commentMapper.findByUuid(vo.getUuid());
		if(comment!=null){
			comment.setAliveFlag("0");
			comment.setUuid(vo.getUuid());
			comment.setUpdateDate(DateTimeUtils.currentDate());
			comment.setUpdateUser(user.getName());
			int r1 = commentMapper.updateRecordById(comment);
			//更新资讯表info中 减一
			Info info = infoService.findByPrimaryKey(comment.getInformationId());
			if(info.getCommentCount() == null || info.getCommentCount() == 0){
				info.setCommentCount(0);
			}else{
				info.setCommentCount(info.getCommentCount() - 1);
			}
			int r2 = infoService.updateInfoCount(info,user);
			return r1 + r2;
		}else{
			return 0;
		}

	}

	@Override
	public Page<Map<String, Object>> queryCommentList(Map<String, Object> beanToMap,SimplePageInfo pageInfo ) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) commentMapper.queryCommentList(beanToMap);
	}

	@Override
	public int deleteCommentByUuid(String uuid) {
		return commentMapper.deleteCommentByUuid(uuid);
	}
	
	/*评论列表*/
	@Override
	public Page<Map<String, Object>> queryMyCommentInfo(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) commentMapper.queryMyCommentInfo(map);
	}
}
