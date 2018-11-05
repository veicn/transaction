package com.sinochem.crude.trade.info.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.model.NoticeVO;
import com.sinochem.crude.trade.info.query.NoticeQuery;
import com.sinochem.crude.trade.member.user.CurrentUser;

public interface NoticeService {

	/**
	 * 公告列表
	 * @param query
	 * @return
	 */
	List<NoticeVO> listNotice(NoticeQuery query);
	
	/**
	 * 公告列表
	 */
	PageInfo<NoticeVO> listNoticePage(NoticeQuery query);
	
	/**
	 * 新增公告
	 */
	Result saveOrUpdateNotice(NoticeVO vo,CurrentUser user);
	
	/**
	 * 通过info uuid 查找公告
	 */
	NoticeVO findNoticeDetailByUUId(String uuid);
	
	/**
	 * 撤销公告
	 */
	Result revokeNotice(String uuid,CurrentUser user);
	
	/**
	 * 历史公告发布
	 */
	Result pushNotice(String uuid,CurrentUser user);
	
	/**
	 * 删除历史公告
	 */
	Result deleteNotice(String uuid,CurrentUser user);
}
