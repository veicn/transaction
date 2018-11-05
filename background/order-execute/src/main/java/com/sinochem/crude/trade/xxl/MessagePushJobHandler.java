package com.sinochem.crude.trade.xxl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.orderexecute.service.OrderMessagePushService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("messagePushJob")
public class MessagePushJobHandler extends IJobHandler {
	
	@Autowired
	private OrderMessagePushService orderMessagePushService;

    private static final Log log = LogFactory.getLog(MessagePushJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        log.info("MessagePushJob  start ---->");
        orderMessagePushService.checkDocument();
        log.info("MessagePushJob  end ---->");
        return ReturnT.SUCCESS;
    }
}
