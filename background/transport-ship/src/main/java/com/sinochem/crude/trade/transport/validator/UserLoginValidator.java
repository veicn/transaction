package com.sinochem.crude.trade.transport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sinochem.it.b2b.member.user.User;
//import com.sinochem.crude.trade.member.domain.UserForm;

/**
 * 
 * @author fish
 * 
 */
public class UserLoginValidator implements Validator {

	public boolean supports(Class<?> clz) {
		return User.class.equals(clz);
	}

	public void validate(Object obj, Errors err) {
	/*	UserForm user = (UserForm) obj;
		if (StringUtils.isBlank(user.getUserName())) {
			err.rejectValue("userName", null, null, "请输入用户名");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			err.rejectValue("password", null, null, "请输入密码");
		}
		if (StringUtils.isBlank(user.getCode())) {
			err.rejectValue("code", null, null, "请输入图片验证码");
		}*/
	}

}
