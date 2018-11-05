package com.sinochem.crude.trade.transport.service;

import java.util.Map;

import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;

public interface CommonService {
	
	/**
	 * 根据epMemberId查询企业信息
	 * @param epMemberId
	 * @return
	 */
	public abstract EnterpriseVo queryUserByEpMemberId(Long epMemberId);
	
	/**
	 * 根据epMemberId查询企业名称（根据版本中文版查中文，英文版查英文）
	 * @param epMemberId
	 * @return
	 */
	public abstract String findNameByEpMemberId(Long epMemberId);
	
	/**
	 * 根据多个epMemberId查询企业名称（根据版本中文版查中文，英文版查英文）
	 * @param epMemberId
	 * @return
	 */
	public abstract Map<Long,String> findNameByEpMemberIds(Long[] epMemberId);
	
	/**
	 * 根据code查询值集名称(中英文)
	 * @param Type（1装港2卸港3油种）
	 * @param code
	 * @return
	 */
	public abstract ValueSetName findNameByCode(String Type,String code);
	
	
	/**
	 * 根据code查询值集名称(根据语言环境查询名称)
	 * @param Type（1装港2卸港3油种）
	 * @param code
	 * @return
	 */
	public abstract String findNameByCodeAndLang(String Type,String code);
	
	
	
	
}
