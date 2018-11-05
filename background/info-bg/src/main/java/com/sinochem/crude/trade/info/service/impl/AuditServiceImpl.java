package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ChannelMMapper;
import com.sinochem.crude.trade.info.dao.ChannelSubMapper;
import com.sinochem.crude.trade.info.dao.InfoMapper;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.service.AuditService;

/**
 * @ClassName: auditServiceImpl
 * @Description: 资讯审核
 * @author pengfl
 * @date 2017年11月13日 下午7:17:08
 *
 */
@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private ChannelMMapper channelMMapper;
	@Autowired
	private ChannelSubMapper channelSubMapper;

	/**
	 * 根据条件-查询列表
	 */
	@Override
	public List<Map<String, Object>> listInfo(Map<String, String> map,SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return infoMapper.ListInfo(map);
	}

	/**
	 * 审核通过
	 */
	@Override
	public boolean auditYes(String uuid) throws BizException {
		if (StringUtils.isBlank(uuid)) {
			throw new BizException("uuid为空，不能进行审核");
		}
		Info record = infoMapper.findByUuid(uuid);
		if (null == record) {
			throw new BizException("该条记录不存在");
		}
		if (!Constants.INFO_ARTICLE_STATUS_DSH.equals(record.getStatus())) {
			throw new BizException("该内容未提交，请先提交");
		}
		record.setStatus(Constants.INFO_ARTICLE_STATUS_YFB);
		record.setAuditTime(new Date());
		return infoMapper.updateRecordById(record) == 1;
	}

	/**
	 * 审核驳回
	 */
	@Override
	public boolean auditNo(Info info) throws BizException {
		if (StringUtils.isBlank(info.getUuid())) {
			throw new BizException("uuid为空，不能进行审核");
		}
		Info record = infoMapper.findByUuid(info.getUuid());
		if (null == record) {
			throw new BizException("该条记录不存在");
		}
		if (!Constants.INFO_ARTICLE_STATUS_DSH.equals(record.getStatus())) {
			throw new BizException("该内容未提交，请先提交");
		}
		record.setStatus(Constants.INFO_ARTICLE_STATUS_SHBH);
		record.setExtend1(info.getResufeRemark()); // 存储驳回内容
		record.setAuditTime(new Date());
		return infoMapper.updateRecordById(record) == 1;
	}

	/**
	 * 资讯撤销
	 */
	@Override
	public boolean revoke(String uuid) throws BizException {
		if (StringUtils.isBlank(uuid)) {
			throw new BizException("uuid为空，不能进行撤销");
		}
		Info record = infoMapper.findByUuid(uuid);
		if (null == record) {
			throw new BizException("该条记录不存在");
		}
		if (!Constants.INFO_ARTICLE_STATUS_YFB.equals(record.getStatus())) {
			throw new BizException("该内容未审核通过，请先审核");
		}
		record.setStatus(Constants.INFO_ARTICLE_STATUS_YCX);
		record.setRevokeTime(new Date());
		return infoMapper.updateRecordById(record) == 1;
	}

	/**
	 * 批量审核通过
	 */
	@Override
	public Map<String, Object> batchAudit(String[] uuids) throws BizException {
		Map<String, Object> res = new HashMap<String, Object>();
		int successCount = 0; // 成功数量
		int failCount = 0; // 失败数量
		for (String uuid : uuids) {
			if (StringUtils.isBlank(uuid)) {
				throw new BizException("存在uuid为空的情况，请重新选择");
			}
			Info record = infoMapper.findByUuid(uuid);
			if (null == record) {
				throw new BizException("存在记录不存在的情况，请重新选择");
			}
			if (!Constants.INFO_ARTICLE_STATUS_DSH.equals(record.getStatus())) {
				throw new BizException("存在内容未提交的情况，请重新选择");
			}
		}
		for (String uuid : uuids) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", Constants.INFO_ARTICLE_STATUS_YFB);
			map.put("uuid", uuid);
			if (infoMapper.updateRecordsBatch(map) == 1) {
				successCount += 1;
			} else {
				failCount += 1;
			}
		}
		res.put("successCount", successCount);
		res.put("failCount", failCount);
		return res;
	}

	/**
	 * 根据主频道uuid查询主频道信息
	 */
	@Override
	public ChannelM CMByUuid(String cMUuid) {
		return channelMMapper.findByUuid(cMUuid);
	}

	/**
	 * 根据子频道uuid查询子频道信息
	 */
	@Override
	public ChannelSub CSubByUuid(String cSubUuid) {
		return channelSubMapper.findByUuid(cSubUuid);
	}
}
