package com.sinochem.crude.trade.info.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sinochem.it.b2b.member.user.User;

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
		User user = (User) obj;
		if (StringUtils.isBlank(user.getName())) {
			err.rejectValue("userName", null, null, "请输入用户名");
		}
		if (StringUtils.isBlank(user.getName())) {
			err.rejectValue("password", null, null, "请输入密码");
		}
		if (StringUtils.isBlank(user.getName())) {
			err.rejectValue("code", null, null, "请输入图片验证码");
		}
	}

}
