package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeItemMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderFee;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeItemVO;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeVO;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeService;

@Service
public class OrderFeeServiceImpl implements OrderFeeService {
	@Autowired
	private OrderFeeMapper orderFeeMapper;
	@Autowired
	private OrderFeeItemMapper orderFeeItemMapper;

	public OrderFeeMapper getMapper() {
		return orderFeeMapper;
	}

	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderFee orderfee) {
		return orderFeeMapper.insertRecord(orderfee);
	}

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderFeeId) throws BizException {
		if (orderFeeId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderFeeMapper.deleteById(orderFeeId);
	}

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	@Override
	public int deleteRecords(OrderFee record) {
		return orderFeeMapper.deleteRecords(record);
	}

	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderFee orderFee) throws BizException {
		if (orderFee.getOrderFeeId() == null) {
			throw new OrderExecException("orderexecute.code.00131","orderFeeId 为空，不能修改","orderFeeId");
		}
		return orderFeeMapper.updateRecordById(orderFee);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderFeeMapper.updateRecords(map);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderFee orderFee) {
		return orderFeeMapper.updateRecords(orderFee.toMap());
	}

	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderFee findByPrimaryKey(Long orderFeeId) {
		return orderFeeMapper.findByPrimaryKey(orderFeeId);
	}

	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderFee findByUuid(String uuid) {
		return orderFeeMapper.findByUuid(uuid);
	}

	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderFee> queryByEntitys(OrderFee orderFee) {
		return orderFeeMapper.queryByEntitys(orderFee);
	}

	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return orderFeeMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map,
			SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(),
				pageInfo.getCount());
		return (Page<Map<String, Object>>) orderFeeMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map) {
		return orderFeeMapper.countRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/

	/**
	 * 查询费用信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Map<String, Object>> selectInformation(Map<String, Object> map) {

		// 查询费用信息
		return orderFeeMapper.selectInformation(map);
	}

	/**
	 * 查询费用信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Map<String, Object>> selectClassified(Map<String, Object> map) {

		// 查询费用信息
		return orderFeeMapper.selectClassified(map);
	}

	/**
	 * 查询费用信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> selectId(Map<String, Object> map) {
		// 查询费用信息
		return orderFeeMapper.selectId(map);
	}



	/**
	 * 新增或者更新信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveOrderFee(OrderFeeVO vo, CurrentUser user) {

		OrderFee entity = vo;
		List<OrderFeeItemVO> feeItemList=new ArrayList<OrderFeeItemVO>();
		feeItemList = vo.getFeeItemList();
		// 新增
		if (vo.getOrderFeeId() == null) {
			entity.setOrderFeeUuid(KeyGenUtils.newUuid());
			entity.setFeeType("0");
			entity.setLangVer(Constants.LANG_VER);
			entity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			entity.setCreateDate(DateTimeUtils.currentDate());
			entity.setCreateUser(user.getMemberId());

			orderFeeMapper.insertRecord(entity);
		} else {// 修改
			entity = new OrderFee();
			entity.setOrderFeeId(vo.getOrderFeeId());
			entity.setTotalFee(vo.getTotalFee());
			entity.setContractNo(vo.getContractNo());
			entity.setUpdateDate(DateTimeUtils.currentDate());
			entity.setUpdateUser(user.getMemberId());
			
			orderFeeMapper.updateRecordById(entity);
			OrderFeeItem feeItem = new OrderFeeItem();
			feeItem.setRelatId(vo.getOrderFeeId());
			orderFeeItemMapper.deleteRecords(feeItem);
		}
		for(OrderFeeItemVO feeItemVO :feeItemList){
			if(StringUtils.isNotBlank(feeItemVO.getSubjectName())){
				OrderFeeItem feeItem = feeItemVO;
//				feeItem.setFeeItemId(null);
				feeItem.setRelatId(entity.getOrderFeeId());
				feeItem.setRelatType("0");
				feeItem.setLangVer(Constants.LANG_VER);
				feeItem.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				feeItem.setCreateDate(DateTimeUtils.currentDate());
				feeItem.setCreateUser(user.getMemberId());
				orderFeeItemMapper.insertRecord(feeItem);
			}
		}
	}
}
