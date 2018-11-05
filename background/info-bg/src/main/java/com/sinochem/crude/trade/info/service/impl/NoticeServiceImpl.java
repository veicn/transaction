package com.sinochem.crude.trade.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.model.NoticeVO;
import com.sinochem.crude.trade.info.query.NoticeQuery;
import com.sinochem.crude.trade.info.service.InfoContentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.NoticeService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private InfoService infoService;
	@Autowired
	private InfoContentService infoContentService;
	
	private Log log = LogFactory.getLog(NoticeServiceImpl.class);
	/**
	 * 公告列表
	 */
	@Override
	public List<NoticeVO> listNotice(NoticeQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		Info entity = new Info();
		if(!StringUtils.isBlank(query.getValidEnd())){
			entity.setValidEnd(DateTimeUtils.toDate(query.getValidEnd(), "yyyy-MM-dd"));
		}
		if(!StringUtils.isBlank(query.getValidBegin())){
			entity.setValidBegin(DateTimeUtils.toDate(query.getValidBegin(), "yyyy-MM-dd"));
		}
		entity.setStatus(query.getStatus());
		List<Info> list = infoService.listNotice(entity);
		List<NoticeVO> vos = new ArrayList<NoticeVO>();
		for(Info item : list){
			//InfoContent infoContent = infoContentService.findByInfoId(item.getId());
			//vo.setContent(infoContent.getTex());
			//vo.setContentWithHtml(infoContent.getTexHtml());
			NoticeVO vo = new NoticeVO();
			vo.setTitle(item.getTitle());
			vo.setUuid(item.getUuid());
			vo.setReleaseId(item.getReleaseId());
			vo.setReleaseDate(DateTimeUtils.toDateString(item.getReleaseDate()));
			vos.add(vo);
		}
		return vos;
	}
	/**
	 * 公告列表
	 */
	@Override
	public PageInfo<NoticeVO> listNoticePage(NoticeQuery query) {
		PageHelper.startPage(query.getNextPage(), query.getPageSize());
		Info entity = new Info();
		if(!StringUtils.isBlank(query.getValidEnd())){
			entity.setValidEnd(DateTimeUtils.toDate(query.getValidEnd(), "yyyy-MM-dd"));
		}
		if(!StringUtils.isBlank(query.getValidBegin())){
			entity.setValidBegin(DateTimeUtils.toDate(query.getValidBegin(), "yyyy-MM-dd"));
		}
		entity.setStatus(query.getStatus());
		List<Info> list = infoService.listNotice(entity);
		List<NoticeVO> vos = new ArrayList<NoticeVO>();
		for(Info item : list){
			//InfoContent infoContent = infoContentService.findByInfoId(item.getId());
			//vo.setContent(infoContent.getTex());
			//vo.setContentWithHtml(infoContent.getTexHtml());
			NoticeVO vo = new NoticeVO();
			vo.setTitle(item.getTitle());
			vo.setUuid(item.getUuid());
			vo.setReleaseId(item.getReleaseId());
			vo.setReleaseDate(DateTimeUtils.toDateString(item.getReleaseDate()));
			vos.add(vo);
		}
		PageInfo<NoticeVO> pageInfo= new PageInfo<NoticeVO>(vos);
		return pageInfo;
	}
	
	/**
	 * 发布或修改公告
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result saveOrUpdateNotice(NoticeVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("公告发布成功");
		
		if(StringUtils.isBlank(vo.getUuid())){
			//新增
			log.info("发布公告");
			Info info = new Info();
			info.setTitle(vo.getTitle());
			if(infoService.saveOrUpdateNotice(info,user) != 1){
				res.setFail("发布失败");
				return res;
			}
			InfoContent infoContent = new InfoContent();
			infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
			infoContent.setInfoId(info.getId());
			infoContent.setTex(vo.getContent());
			infoContent.setTexHtml(vo.getContentWithHtml());
			if(infoContentService.saveNotice(infoContent,user) != 1){
				res.setFail("发布失败");
				return res;
			}
		}else{
			log.info("修改公告");
			Info info = infoService.findByUuid(vo.getUuid());
			info.setTitle(vo.getTitle());
			//res.setMessage("修改公告成功");
			if(infoService.saveOrUpdateNotice(info,user) != 1){
				res.setFail("发布失败");
				return res;
			}
			InfoContent infoContent = new InfoContent();
			infoContent.setInfoId(info.getId());
			infoContent.setTex(vo.getContent());
			infoContent.setTexHtml(vo.getContentWithHtml());
			if(infoContentService.updateNotice(infoContent,user) != 1){
				res.setFail("发布失败");
				return res;
			}
		}
		return res;
	}
	
	/**
	 * 查询公告
	 */
	@Override
	public NoticeVO findNoticeDetailByUUId(String uuid) {
		log.info("查询公告--> "+uuid);
		Info info = infoService.findByUuid(uuid);
		if(info != null){
			InfoContent content = infoContentService.findByInfoId(info.getId());
			NoticeVO vo = new NoticeVO();
			vo.setTitle(info.getTitle());
			vo.setUuid(info.getUuid());
			vo.setReleaseId(info.getReleaseId());
			vo.setReleaseDate(DateTimeUtils.toDateTimeString(info.getReleaseDate()));
			vo.setContent(content.getTex());
			vo.setContentWithHtml(content.getTexHtml());
			vo.setReleaseId(info.getReleaseId());
			return vo;
		}
		return null;
	}

	/**
	 * 撤销公告
	 */
	@Override
	public Result revokeNotice(String uuid,CurrentUser user) {
		log.info("撤销公告 --> " + uuid);
		log.info("撤销公告");
		Result res = new Result();
		res.setMessage("撤销公告成功");
		if(infoService.revokeNotice(uuid,user) == 1){
			return res;
		}
		res.setFail("撤销公告失败");
		return res;
	}
	/**
	 * 历史公告发布
	 */
	@Override
	public Result pushNotice(String uuid,CurrentUser user) {
		log.info("历史公告发布 --> " + uuid);
		Result res = new Result();
		res.setMessage("历史公告发布成功");
		if(infoService.pushNotice(uuid,user) == 1){
			return res;
		}
		res.setFail("历史公告发布失败");
		return res;
	}
	/**
	 * 删除历史公告
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Result deleteNotice(String uuid,CurrentUser user) {
		log.info("删除历史公告 -- > " + uuid);
		Result res = new Result();
		res.setMessage("删除成功");
		//先删除内容，在删除资讯
		Info info = infoService.findByUuid(uuid);
		info.setCreateUser(user.getName());
		info.setUpdateUser(user.getName());
		int result = 0;
		if(info!=null){
			result = infoContentService.deleteByInfoId(info.getId());
			result = result + infoService.deleteByInfo(info);
		}
		if(result != 2){
			res.setFail("删除失败");
		}
		return res;
	}

}
