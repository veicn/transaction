package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.dao.PalletMapper;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.model.ClauseVO;
import com.sinochem.crude.trade.transport.model.PalletVO;
import com.sinochem.crude.trade.transport.model.res.PalletPortList;
import com.sinochem.crude.trade.transport.query.OrderQuery;

public interface PalletService {

	public abstract PalletMapper getMapper();

	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Pallet> queryByEntitys(Pallet pallet);

	/**
	 * 根据主键-查询对象
	 */
	public abstract Pallet findByPrimaryKey(Long palletId);

	/**
	 * 根据UUID-查询对象
	 */
	public abstract Pallet findByUuid(String uuid);

	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Pallet pallet);

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long palletId, Long deleteUser);

	/**
	 * 新增
	 */
	public abstract void insertRecord(Pallet pallet);

	/*
	 * 根据主键删除数据
	 */
	// public abstract void deleteRecordByKey(Long palletId);

	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);

	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public abstract void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/
	/**
	 * 租船信息_新增
	 * 
	 * @param vo
	 * @param user
	 */
	public abstract void savePallet(PalletVO vo, CurrentUser user);
	/***
	 * 租船信息_作废
	 * 
	 * @param vo
	 * @param user
	 */
	public abstract void updateStatus(PalletVO vo, CurrentUser user);

	/**
	 * 租船信息_详细
	 * 
	 * @param vo
	 */
	public abstract Map<String,Object> palletDeatil(PalletVO vo);


	/***
	 * 租船信息_取消
	 * 
	 * @param vo
	 * @param user
	 */
	void delPallet(PalletVO vo, CurrentUser user);

	/***
	 * 租船信息_修改
	 * 
	 * @param vo
	 * @param user
	 */
	void updatePallet(PalletVO vo, CurrentUser user);


	//List<Map<String, Object>> getPalletList(PalletQuery query, Pallet pallet);

	//List<Map<String, Object>> getPalletList(PalletQuery query);

	/**
	 * 我要租船订单翻页列表
	 * @param vo
	 */
	public abstract List<Map<String, Object>> getOrderPageList(OrderQuery query);

	/**
	 * 有订单列表查询
	 * @param map
	 * @param query
	 * @return
	 */
	public Page<Map<String, Object>> findPalletListTrander(Map<String, Object> map,SimplePageInfo simplePageInfo);

	/**
	 * 无订单列表查询
	 * @param map
	 * @param simplePageInfo
	 * @return
	 */
	public Page<Map<String, Object>> getPalletListTrader(Map<String, Object> map,SimplePageInfo simplePageInfo);

	/**
	 * 检测订单是否已租船
	 * @param orderCode
	 */
	public abstract void checkAgreementIsExsit(String orderCode);
	
	/**
	 * 根据条件-查询记录条数 无订单
	 */
	public abstract int countRecords2(Map<String, Object> map);
	
	/**
	 * 根据条件-查询记录条数 有订单
	 */
	public abstract int countRecords3(Map<String, Object> map);

	/**
	 * 获取任务数量提示
	 */
	public abstract Map<String, Object> getTaskNum(Map<String, Object> map);

	/**
	 * 订单取消
	 * @param orderCode
	 * @param memberId 
	 */
	public abstract void cancelOrder(String orderCode, Long memberId);

	/**
	 * 租船信息_新增
	 */
	public abstract void insertionPallet(List<PalletPortList> list, Pallet vo, CurrentUser user);
	/**
	 * 查询货盘信息
	 */
	public Map<String, Object> getPallet(Pallet vo);
	/**
	 * 指定二船东
	 */
	public void appointTradertrader(Pallet vo,CurrentUser user);

	/**
	 * 货盘滚动
	 * @param vo
	 * @return
	 */
	public abstract List<Map<String, Object>> findPalletList(PalletVO vo);
	/**
	 * 货盘滚动查看更多
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> findMorePalletList(Map<String, Object> map,
			SimplePageInfo pageInfo);

	/**
	 * 货盘查看详情
	 * @param vo
	 * @return
	 */
	public abstract Map<String, Object> findPalletDetail(PalletVO vo);
	/**
	 * 根据Uuid查看货盘
	 * @param vo
	 * @return
	 */
	public abstract Map<String, Object> palletDeatilByUuid(PalletVO vo);
	/**
	 *根据货盘Uuid查询确认详情
	 */
	public Map<String,Object> confirmsDetails(ClauseVO vo);
	/**
	 *om租船需求列表
	 */
	public Page<Map<String, Object>> queryPallets(Map<String,Object> map,SimplePageInfo pageInfo);
}
