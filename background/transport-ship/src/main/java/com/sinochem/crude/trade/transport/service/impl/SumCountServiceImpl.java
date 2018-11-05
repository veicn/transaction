package com.sinochem.crude.trade.transport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.transport.dao.SumCountMapper;
import com.sinochem.crude.trade.transport.domain.SumCount;
import com.sinochem.crude.trade.transport.service.SumCountService;

/**
 * 记录操作数量接口
 * @author 姜秀强
 */
@Service
public class SumCountServiceImpl implements SumCountService {

	@Autowired
	private SumCountMapper _SumCountMapper;
	
	/**
	 * 更新航次结束次数
	 */
	@Override
	public void updatefinishShipPactCount() {
		SumCount count = new SumCount();
		count.setCode("updatefinishShipPactCount");
		
		_SumCountMapper.updatefinishShipPactCount(count);
	}

}
