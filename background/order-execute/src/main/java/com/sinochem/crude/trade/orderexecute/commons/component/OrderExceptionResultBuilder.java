package com.sinochem.crude.trade.orderexecute.commons.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.it.b2b.common.exception.AbstractExceptionResultBuilder;

@Component
public class OrderExceptionResultBuilder extends AbstractExceptionResultBuilder<OrderExecException> {
	public ModelAndView buildExceptionView(OrderExecException ex) {
		ModelAndView modelAndView = super.buildExceptionView(ex);
		modelAndView.addObject("code", ex.getCode());
		modelAndView.addObject("params", ex.getParams());
		
		return modelAndView;
	}

	public OrderExceptionResult build(OrderExecException exception) {
		return new OrderExceptionResult(exception);
	}

	public String getExceptionViewPath() {
		return "error/500";
	}

	public class OrderExceptionResult extends AbstractExceptionResultBuilder.ExceptionResult<OrderExecException> {
		private static final long serialVersionUID = 3727192047980424693L;

		OrderExceptionResult(OrderExecException bizException) {
			setMessage(bizException.getMessage());
		}
	}
}