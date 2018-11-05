package com.sinochem.crude.trade.transport.dao;

import com.sinochem.crude.trade.transport.domain.SumCount;

/**
 * 记录操作数量mapper
 * @author 姜秀强
 *
 */
//@MapperScan
public interface SumCountMapper {

	/**
	 * 更新航次结束次数
	 * @param record
	 * @return
	 */
    int updatefinishShipPactCount(SumCount record);
}