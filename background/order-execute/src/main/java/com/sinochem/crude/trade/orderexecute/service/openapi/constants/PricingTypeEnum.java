package com.sinochem.crude.trade.orderexecute.service.openapi.constants;

/**
 * 价格计算方式
 * @author me
 *
 */
public enum PricingTypeEnum {
	/** 固定价 */
	Fixed,
	
	/** 平均计价 */
	Average,
	
	/** 触发计价 */
	Trigger,
	
	/** 变动计价 */
	Event,
	
	/** 复杂计价 */
	Complex,
	
	/** 联信银行计价 */
	CMA,
	
	/** 实货交换计价 */
	EFP;

}
