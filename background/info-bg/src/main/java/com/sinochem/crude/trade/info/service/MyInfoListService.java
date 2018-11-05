package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;

/**
 * 我的资讯列表<br/>
 * Created by pengfl on 2017年11月27日
 */
public interface MyInfoListService {

	/**
	 * 我的资讯列表查询
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> listInfo(Map<String, String> map);

	/**
	 * 资讯撤销
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean revoke(String uuid, CurrentUser user) throws BizException;

	/**
	 * 资讯删除
	 * 
	 * @param uuid
	 * @return
	 * @throws BizException
	 */
	public boolean delete(String uuid) throws BizException;

}
