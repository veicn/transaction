package com.sinochem.crude.trade.common.result;

import com.eyeieye.melody.beans.config.BeanOverride;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.it.b2b.member.access.NoLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 构造用户未登录异常，跳转到登录页面
 * @author : bbt
 * @Date : 2016年12月7日
 * @Version : 1.0.0
 */
@BeanOverride(override = com.sinochem.it.b2b.member.access.NoLoginResultBuilder.class)
@Component("nooLoginResultBuilder")
public class NoLoginResultBuilder extends com.sinochem.it.b2b.member.access.NoLoginResultBuilder{

	@Autowired
	private URLBroker systemServer;

	public class NoLoginResult extends ExceptionResult<NoLoginException> {
		/**
		 *
		 */
		private static final long serialVersionUID = 3727192047980424693L;

		NoLoginResult(NoLoginException e) {
			super();
			this.setMessage(e.getMessage());
		}
	}

	@Override
	public boolean canBuild(Exception e) {
		return NoLoginException.class.equals(e.getClass());
	}

	@Override
	public ModelAndView buildExceptionView(NoLoginException ex) {
		ModelAndView modelAndView = super.buildExceptionView(ex);
//		modelAndView.clear();
//		modelAndView.addObject("exception","");
		modelAndView.getModel().remove("exception");
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
		return "redirect:" + systemServer.get("/login.htm");
	}

}
