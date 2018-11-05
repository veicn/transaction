package com.sinochem.crude.trade.member.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.model.EnterpriseDetail;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;


public interface EnterpriseService {

	/**
	 * 添加企业信息
	 */
	public void add(Enterprise enterprise) throws BizException;
	
	/**
	 * 更新企业信息
	 */
	public void update(Enterprise enterprise) throws BizException;

	/**
	 * 更新企业信息
	 */
	public void updateByPrimaryKeySelective(Enterprise enterprise,BindingResult bindingResult) throws BizException;
	


	//查询企业信息
	public List<Enterprise> selectEnter(String name);

	public String insertEnterprise(Enterprise enterprise)throws BizException;

	public void deleteEnterprise(Long id);

	public Enterprise queryById(Long id);

	public Enterprise enterpriseById(Long id);

	public Enterprise enterpriseByMemberId(Long memberId);
	//模糊查询

	public List<Enterprise> selectByNameLike(String name);

	public Enterprise enterpersiceDetail(Long memberId);

	/**
	 * 查询所有企业信息，包含企业资质信息
	 */
    List<EnterpriseDetail> selectAll();

    Enterprise selectByPrimaryKey(Long epMemberId);

	/**
	 * 查询企业列表
	 * <p>
	 *     包含企业补充信息
	 * </p>
	 * @param query
	 * @return
	 */
	List<Enterprise> selectWithCrude(EnterpriseQuery query);

	/**
	 *  查询企业列表(分页)
	 * @param query
	 * @param pageInfo
	 * @return
	 */
	List<Enterprise> selectWithCrude(EnterpriseQuery query, PageInfo pageInfo);

	/**
	 * 按企业名称模糊查询企业
	 * @return
	 */
	List<EnterpriseDetail> selectAllByName(String name);

	Enterprise getByMemberId(Long epMemberId);

	@Transactional("transactionManager")
	String fill(Enterprise enterprise, Long memberId, BindingResult bindingResult)throws BizException;


	@Transactional("transactionManager")
	String omFill(Enterprise enterprise, Long memberId, BindingResult bindingResult)throws BizException;

	@Transactional
    void register(Enterprise enterprise, EnterpriseContact contact, Long memberId, String[] types, BindingResult bindingResult)throws BizException;

	@Transactional
	void omRegister(Enterprise enterprise, EnterpriseContact contact, Long memberId, String[] types, BindingResult bindingResult)throws BizException;


}
