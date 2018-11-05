package com.sinochem.crude.trade.orderexecute.typehandler;

import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;

public class QuantityUnitHandler extends CodeConvertHandler {

	@Override
	public String convertValue(String originalValue) {
		return ValueSetUtil.getByCode(ValueSetGroupConstant.QUANTITY_UNIT, originalValue).getValue();
	}
}
