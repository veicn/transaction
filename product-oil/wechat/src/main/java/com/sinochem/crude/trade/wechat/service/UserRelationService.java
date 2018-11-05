package com.sinochem.crude.trade.wechat.service;

import java.util.Map;
import java.util.List;

//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.wechat.model.vo.UserInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.crude.trade.wechat.domain.UserRelation;
import com.sinochem.crude.trade.wechat.dao.UserRelationMapper;
import com.sinochem.it.b2b.common.result.ResultDatas;

public interface UserRelationService {
	
	public abstract UserRelationMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(UserRelation userRelation);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long wechatUserId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(UserRelation record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(UserRelation userRelation) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(UserRelation userRelation) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(UserRelation userRelation);

	/**
	 * 通过openid 查询member token
	 * @param openid
	 * @return
	 */
	String GetMemberToken(String openid);
	
	
	/**
	 * 根据主键-查询对象
	 */
	UserRelation findByPrimaryKey(Long wechatUserId);

	/**
	 * 根据uuid查询对象
	 */
	UserRelation findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<UserRelation> queryByEntitys(UserRelation userRelation);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	//Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据openid查询对象
	 */
	UserRelation findByOpenid(String openid);
	
	/**
	 * 根据openid逻辑删除
	 */
	int deleteRecordByOpenid(UserRelation userRelation);

	/**
	 * 登录绑定
	 * @param userInfoVO
	 * @return
	 */
	 ResultDatas<UserRelation> MemberLogin(UserInfoVO userInfoVO);
}
