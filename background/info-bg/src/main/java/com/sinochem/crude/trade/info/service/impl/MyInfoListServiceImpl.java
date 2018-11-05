package com.sinochem.crude.trade.info.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.service.MyInfoListService;
import com.sinochem.crude.trade.member.user.CurrentUser;

/**
 * 我的资讯列表<br>
 * Created by pengfl on 2017年11月28日
 */
@Service
public class MyInfoListServiceImpl implements MyInfoListService {

	@Autowired
	private InfoMapper infoMapper;

	/**
	 * 查询我的资讯列表
	 */
	@Override
	public List<Map<String, Object>> listInfo(Map<String, String> map) {
		int currentPage = 1;
		int pageSize = 20;
		if (null != map.get("currentPage")) {
			currentPage = Integer.parseInt(map.get("currentPage"));
		}
		if (null != map.get("pageSize")) {
			pageSize = Integer.parseInt(map.get("pageSize"));
		}
		PageHelper.startPage(currentPage, pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = infoMapper.myInfoList(map);
		return list;
	}

	/**
	 * 资讯撤销
	 */
	@Override
	public boolean revoke(String uuid, CurrentUser user) throws BizException {
		if (StringUtils.isBlank(uuid)) {
			throw new BizException("uuid为空，不能进行撤销");
		}
		Info record = infoMapper.findByUuid(uuid);
		if (null == record) {
			throw new BizException("该条记录不存在");
		}
		if (!user.getName().equals(record.getCreateUser())) { // 撤销非本人操作
			if (!Constants.INFO_ARTICLE_STATUS_YFB.equals(record.getStatus())) {
				throw new BizException("该内容未审核通过，请先审核");
			}
		}
		record.setStatus(Constants.INFO_ARTICLE_STATUS_YCX);
		record.setRevokeTime(new Date());
		return infoMapper.updateRecordById(record) == 1;
	}

	/**
	 * 资讯删除
	 */
	@Override
	public boolean delete(String uuid) throws BizException {
		if (StringUtils.isBlank(uuid)) {
			throw new BizException("uuid为空，不能进行删除");
		}
		Info record = infoMapper.findByUuid(uuid);
		if (null == record) {
			throw new BizException("该条记录不存在");
		}
		record.setAliveFlag("0"); // 设置状态为删除状态
		record.setUpdateDate(new Date()); // 设置修改时间
		return infoMapper.updateRecordById(record) == 1;
	}

}
