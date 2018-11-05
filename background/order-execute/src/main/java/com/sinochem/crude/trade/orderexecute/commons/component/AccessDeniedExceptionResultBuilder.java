package com.sinochem.crude.trade.orderexecute.commons.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.eyeieye.melody.beans.config.BeanOverride;
import com.sinochem.it.b2b.member.access.AccessDeniedException;
import com.sinochem.it.b2b.member.access.AccessDeniedResultBuilder;

@BeanOverride(override = AccessDeniedResultBuilder.class)
@Component("newaccessDeniedResultBuilder")
public class AccessDeniedExceptionResultBuilder extends AccessDeniedResultBuilder {

    public class AccessDeniedResult extends ExceptionResult<AccessDeniedException> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3727192047980424693L;

		AccessDeniedResult(AccessDeniedException e) {
			super();
			this.setMessage("没有权限");
		}
	}

	@Override
	public boolean canBuild(Exception e) {
		return AccessDeniedException.class.equals(e.getClass());
	}

	@Override
	public ModelAndView buildExceptionView(AccessDeniedException ex) {
		ModelAndView modelAndView = super.buildExceptionView(ex);
//		modelAndView.clear();
//		modelAndView.addObject("exception","");
		modelAndView.getModel().remove("exception");
		modelAndView.addObject("code", "403");
		modelAndView.addObject("message", "没有权限");
		return modelAndView;
	}

	@Override
	public int getStatus() {
		return HttpStatus.TEMPORARY_REDIRECT.value();
	}

	/**
	 * 用于显示返回消息的地址
	 * @return
	 */
	@Override
	public String getExceptionViewPath(){
		return "error/403.htm";
	}
}
