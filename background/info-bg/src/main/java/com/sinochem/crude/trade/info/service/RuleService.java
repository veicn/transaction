package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.RuleMapper;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.Rule;
import com.sinochem.crude.trade.info.query.RuleQuery;

/**
 * @ClassName: RuleService
 * @Description: 资讯规则设置接口
 * @author pengfl
 * @date 2017年11月13日 下午1:04:44
 *
 */
public interface RuleService {

	public abstract RuleMapper getMapper();

	/**
	 * 新增
	 */
	int insertRecord(Rule rule);

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	public int deleteRecords(Rule record);

	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Rule rule) throws BizException;

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Rule rule);

	/**
	 * 根据主键-查询对象
	 */
	Rule findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Rule findByUuid(String uuid);

	/**
	 * 根据对象-查询对象列表
	 */
	List<Rule> queryByEntitys(Rule rule);

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

	// **************************以下方法为开发者补充*********************************/
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Rule rule) throws BizException;

	/**
	 * 根据uuid-逻辑删除
	 */
	int deleteByUuid(String uuid) throws BizException;

	/**
	 * 根据条件-查询列表
	 * 
	 * @param query
	 * @return
	 */
	public List<Map<String, Object>> listRule(RuleQuery query);

	/**
	 * 根据子频道uuid查询频道信息
	 * 
	 * @param uuid
	 * @return
	 */
	public ChannelSub queryChannelSub(String uuid);

	/**
	 * 得到子频道列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryChannelSubs();
}
