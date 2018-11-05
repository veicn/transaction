package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.orderexecute.commons.constants.TriggerApplyStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;

public class TriggerApplyVO extends TriggerApply {

	private static final long serialVersionUID = 1L;	
	
	public String getStatusDesc() {
		CodeValue codeValue = ValueSetUtil.getByCode(ValueSetGroupConstant.TRIGGER_APPLY_STATUS, super.getStatus());
		return codeValue.getValue();
	}
	
	//是否可撤单
	public String getCanCancel() {
		if(super.getStatus().equals(TriggerApplyStatusEnum.STATUS_10.getCode())) {//申请状态
//			Date now = DateTimeUtils.currentDate();
					
//			if(super.getEndTime().before(now)) {//超过截止日期
//				return "0";
//			}else {
				return "1";
//			}
		}
		
		return "0";
	}
	
}