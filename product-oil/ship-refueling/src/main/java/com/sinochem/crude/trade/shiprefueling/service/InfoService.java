package com.sinochem.crude.trade.shiprefueling.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.InfoMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

public interface InfoService {

	public abstract InfoMapper getMapper();

	/**
	 * 新增
	 */
	int insertRecord(Info info);

	/**
	 * 新增数据并插入附件信息
	 * @param info
	 * @Pa
	 * @return
	 */
	int insertRecord(Info info , List<ChmentsVO> chmentsVOList, CurrentUser user);

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long infoId) throws BizException;

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	public int deleteRecords(Info  record);

	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Info info) throws BizException;

	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Info info) throws BizException;

	/**
	 * 根据uuid-修改对象
	 * 如果传Url就修改附件信息
	 */
//	int updateRecordByUuid(Info info) throws BizException;

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Info info);


	/**
	 * 根据主键-查询对象
	 */
	Info findByPrimaryKey(Long infoId);

	/**
	 * 根据uuid查询对象
	 */
	Info findByUuid(String uuid);


	/**
	 * 根据对象-查询对象列表
	 */
	List<Info> queryByEntitys(Info info);

	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);


	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map);



	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据当前点击用户查询集合
	 * @return
	 * @param InfoQuery
	 * @param pageInfo
	 */
	Page<Map<String, Object>> queryByUserId(InfoQuery InfoQuery, SimplePageInfo pageInfo);

	/**
	 * 根据uuid修改销售单状态
	 * @param info
	 * @return
	 */
	int updateByUuidStatus(Info info);

	/**
	 * 根据uuid删除销售单
	 * @param uuid
	 * @return
	 */
	int deleteByuuid(String uuid);

	/**
	 * 前台——查询最新的销售信息
	 * @param size
	 * @return
	 */
	List<InfoVO> selectNewSellInfoList(Integer size);


	/**
	 * 根据销售单查询询价列表
	 * @param iryQuotationQuery
	 * @param pageInfo
	 * @return
	 */
	Page<Map<String, Object>> findByidInquiry(IryQuotationQuery iryQuotationQuery, SimplePageInfo pageInfo);

	/**
	 * 根据uuid修改询价表状态
	 * @param iryQuotation
	 * @return
	 */
	int updateByuuidInquiry(IryQuotation iryQuotation);


	/***
	 * 根据uuid逻辑删除信息
	 * @param uuid
	 * @return
	 */
	int deleteRecordByUuid(String uuid);

	/**
	 * 根据uuid逻辑删除销售详情以及关联的询价详情
	 * @param uuid
	 */
	void deleteInfoAndIqByUUid(String uuid)throws BizException;

	/**
	 * 销售信息详情
	 * 包括销售附件信息，企业信息
	 * @param uuid
	 * @return
	 */
	Map<String , Object> getInfoEpDetail(String uuid);


	//ADD_ 船油销售订单查询首页展示_wdh_20180731_Start
	/**
	 * 查询销售列表
	 *  查询信息类型为船燃贸易，船燃贸易(内贸)，船燃贸易(保税)中各自类型得三个不同企业的最新一条信息
//	 * @param pageInfo
	 * @return
	 */
	Page<Map<String,Object>> queryList(SimplePageInfo pageInfo);
	//ADD_ 船油销售订单查询首页展示_wdh_20180731_End
}
