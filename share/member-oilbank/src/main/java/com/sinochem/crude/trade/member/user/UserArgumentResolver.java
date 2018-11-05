package com.sinochem.crude.trade.member.user;

import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.member.user.User;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

/**
 * 
 * @author leo
 * 
 */
@Component
public class UserArgumentResolver implements WebArgumentResolver {

	private final static Logger logger = LoggerFactory
			.getLogger(UserArgumentResolver.class);

	@Value("${system.devMode:false}")
	public boolean devMode;

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(CurrentUser.class)) {
			User user = (User) webRequest
					.getAttribute(CommonUtils.ATTRIBUTE_USER,
							RequestAttributes.SCOPE_SESSION);
			if (user != null) {
				CurrentUser currentUser = new CurrentUser();
				currentUser.setEpMemberId(user.getpMemberId());
				String[] roles = user.getRoles();
				if(!user.isProxy() && ArrayUtils.contains(roles, "enterprise")) {
					currentUser.setEpMemberId(user.getMemberId());
				}
				if(!user.isProxy() && ArrayUtils.contains(roles, "enter_admin") && ArrayUtils.contains(roles, "enter_master")) {
					currentUser.setEpMemberId(user.getMemberId());
				}
				currentUser.setMemberId(user.getMemberId());
				currentUser.setName(user.getName());
				currentUser.setRoles(user.getRoles());
				return currentUser;
			} else if (devMode) {
				CurrentUser currentUser = new CurrentUser();
				currentUser.setEpMemberId(20001l);
				currentUser.setMemberId(10001l);
				currentUser.setName("测试用户");
				return currentUser;
			}
			logger.warn("没有获取到CurrentUser");
			return null;
		}
		return UNRESOLVED;
	}
}
