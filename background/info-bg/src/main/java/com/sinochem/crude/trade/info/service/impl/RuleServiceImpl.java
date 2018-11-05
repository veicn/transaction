package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.ChannelSubMapper;
import com.sinochem.crude.trade.info.dao.RuleMapper;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.Rule;
import com.sinochem.crude.trade.info.query.RuleQuery;
import com.sinochem.crude.trade.info.service.RuleService;

/**
 * @ClassName RuleServiceImpl
 * @Description 资讯规则设置
 * @author pengfl
 * @Date 2017年11月13日上午10:55:15
 */
@Service
public class RuleServiceImpl implements RuleService {
	@Autowired
	private RuleMapper ruleMapper;
	@Autowired
	private ChannelSubMapper channelSubMapper;

	public RuleMapper getMapper() {
		return ruleMapper;
	}

	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Rule rule) {
		// 随机生成uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		rule.setUuid(uuid);
		return ruleMapper.insertRecord(rule);
	}

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException {
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return ruleMapper.deleteById(id);
	}

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	@Override
	public int deleteRecords(Rule record) {
		return ruleMapper.deleteRecords(record);
	}

	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Rule rule) throws BizException {
		if (rule.getId() == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return ruleMapper.updateRecordById(rule);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return ruleMapper.updateRecords(map);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Rule rule) {
		return ruleMapper.updateRecords(rule.toMap());
	}

	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Rule findByPrimaryKey(Long id) {
		return ruleMapper.findByPrimaryKey(id);
	}

	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Rule findByUuid(String uuid) {
		return ruleMapper.findByUuid(uuid);
	}

	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Rule> queryByEntitys(Rule rule) {
		return ruleMapper.queryByEntitys(rule);
	}

	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return ruleMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) ruleMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map) {
		return ruleMapper.countRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Rule rule) throws BizException {
		if (rule.getUuid() == null) {
			throw new BizException("uuid 为空，不能修改 ");
		}
		return ruleMapper.updateRecordByUuid(rule);
	}

	/**
	 * 根据uuid-逻辑删除
	 */
	@Override
	public int deleteByUuid(String uuid) throws BizException {
		if (uuid == null) {
			throw new BizException("uuid 为空，不能删除 ");
		}

		Rule rule = ruleMapper.findByUuid(uuid);
		if (null == rule) {
			throw new BizException("未找到该记录信息 ");
		}
		rule.setAliveFlag("0"); // 修改数据状态为0
		return ruleMapper.updateRecordById(rule);
	}

	/**
	 * 根据条件-查询列表
	 */
	@Override
	public List<Map<String, Object>> listRule(RuleQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		Rule param = new Rule();
		param.setRoleCode(query.getRoleName());
		return ruleMapper.listRule(param);
	}

	/**
	 * 根据子频道uuid查询频道信息
	 */
	@Override
	public ChannelSub queryChannelSub(String uuid) {
		return channelSubMapper.findByUuid(uuid);
	}

	/**
	 * 得到子频道列表
	 */
	@Override
	public List<Map<String, Object>> queryChannelSubs() {
		Map<String, Object> map = null;
		return channelSubMapper.queryRecords(map);
	}
}
