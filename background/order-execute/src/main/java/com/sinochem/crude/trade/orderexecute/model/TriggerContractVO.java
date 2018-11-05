package com.sinochem.crude.trade.orderexecute.model;

import java.util.Date;

import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.commons.constants.TriggerContractStatusEnum;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;

public class TriggerContractVO extends TriggerContract {

	private static final long serialVersionUID = 1L;	
	
	public String getStatusDesc() {
		if(super.getEndTime() != null) {
			Date now = DateTimeUtils.currentDate();
			if(super.getEndTime().before(now)) {
				super.setStatus(TriggerContractStatusEnum.STATUS_40.getCode());
				return "已过期";
			}
		}
		
		return "未过期";
	}
}