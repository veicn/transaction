package com.sinochem.crude.trade.orderexecute.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sinochem.crude.trade.orderexecute.model.OrderContractVO;

public class CreateContractValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(OrderContractVO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderContractVO contractVO = (OrderContractVO)target;
		if(contractVO.getGtc().length() > 500){
			errors.rejectValue("gtc", null, null, "GTC长度不能超过500个字符");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "contractNo", null, "合同编号不能为空");
		ValidationUtils.rejectIfEmpty(errors, "quantity", null, "数量不能为空");
		ValidationUtils.rejectIfEmpty(errors, "layTime", null, "装期不能为空");
		ValidationUtils.rejectIfEmpty(errors, "priceFormula", null, "价格公式不能为空");
		ValidationUtils.rejectIfEmpty(errors, "paymentTerm", null, "付款期限不能为空");
		ValidationUtils.rejectIfEmpty(errors, "tradeTerm", null, "贸易条款不能为空");
	}
}
